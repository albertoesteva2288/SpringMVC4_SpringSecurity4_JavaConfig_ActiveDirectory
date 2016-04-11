# SpringMVC4_SpringSecurity4_JavaConfig_ActiveDirectory
This is a starter project that implements Spring MVC 4, Spring Security 4--all with only Java Config (No annotations) with authentication through Active Directory. 

I would first start with this project. Then, download a free copy of ApacheDirectoryStudio and make sure that you can first connect to your local LDAP instance before you try connecting from Spring. Most critical that you have your local Active Directory LDAP instance values for the following:

LDAP Domain: something like myco.com, but don't assume they are the same until you prove it. When you look up yourself in AD using the Apache Directory Studio, you will see your user principal listed as: auser@myco.com   <--The value you find like 'myco.com' will be your Domain. 
LDAP Host: this is the actual path to your internal AD/LDAP server and it may be some variant on the domain--such as myco.local. None of the code above will have a prayer's chance of working until you figure out those two values.

Likewise, the dc= values (Domain Components) must also match what you use locally. 

The Active Directory query is made when you try to hit: http://localhost:8080/MyWar/admin  <--that assumes you're using a server (like Tomcat) with a default port of :8080.
