package com.myco.mywar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.InetOrgPersonContextMapper;

@Configuration
@EnableWebSecurity
@PropertySource("file:/etc/myco/mywar.properties")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Value("${mywar.example.value.from.propertyfile}")
    private String exampleValueFromProp;
    
    @Value("${mycoActiveDirectoryDomainExampleKey}")
    private String mycoActiveDirectoryDomain;
    
    @Value("${myActiveDirectoryLdapUrlExampleKey}")
    private String myActiveDirectoryLdapUrl;
    
    @Value("${myActiveDirectoryLdapDomainComponentsExampleKey}")
    private String myActiveDirectoryLdapDomainComponents;
    
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception
    {                    
        auth.authenticationProvider( this.activeDirectoryLdapAuthenticationProvider() );        
    }

    @Bean
    public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() 
    {
        ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider( this.mycoActiveDirectoryDomain, this.myActiveDirectoryLdapUrl, this.myActiveDirectoryLdapDomainComponents );

        provider.setConvertSubErrorCodesToExceptions(true);
        provider.setUseAuthenticationRequestCredentials(true);
        provider.setAuthoritiesMapper(new SimpleAuthorityMapper());
        provider.setSearchFilter("(&(objectCategory=person)(objectClass=user)(|(sAMAccountName={0})(userPrincipalName={0})))");
        provider.setUseAuthenticationRequestCredentials(true);
        provider.setUserDetailsContextMapper(inetOrgPersonContextMapper());
        
        return provider;
    }

    @Bean
    public InetOrgPersonContextMapper inetOrgPersonContextMapper() 
    {
        return new InetOrgPersonContextMapper();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/admin/**").access("hasRole('CNforMyWar')")            
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .and()
                .formLogin()
                .and()
                .exceptionHandling().accessDeniedPage("/Access_Denied");
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }

    
}
