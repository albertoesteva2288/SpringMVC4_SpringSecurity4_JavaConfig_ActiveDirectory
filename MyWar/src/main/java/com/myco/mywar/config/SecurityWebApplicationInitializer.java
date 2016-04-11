package com.myco.mywar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer
{        
    @Bean
    public ResourceUrlEncodingFilter resourceUrlEncodingFilter() 
    {
       return new ResourceUrlEncodingFilter();
    }
}
