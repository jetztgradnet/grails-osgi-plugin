
#===  config/equinox-springdm-web-app.profile  ===
scan-composite:file:config/equinox.profile
scan-composite:file:config/misc.profile
scan-composite:file:config/spring.profile
scan-composite:file:config/springdm.profile
scan-composite:file:config/web.profile


#===  config/equinox.profile  ===
# additional Equinox services and bundles
scan-bundle:mvn:org.eclipse.osgi/util/3.2.0.v20090520-1800
scan-bundle:mvn:org.eclipse.osgi/services/3.2.0.v20090520-1800



#===  config/grails.profile  ===
# boot delegation is currently required for groovy to work
# without NoClassDefFound exceptions
-Dorg.osgi.framework.bootdelegation=*
#-Dosgi.java.profile.bootdelegation=*

# load basic web app files
scan-composite:file:config/equinox-springdm-web-app.profile

#===  config/jetty.profile  ===
# Jetty
scan-bundle:mvn:org.mortbay.jetty/com.springsource.org.mortbay.jetty.server/6.1.9
scan-bundle:mvn:org.mortbay.jetty/com.springsource.org.mortbay.util/6.1.9
# jetty starter and default configuration
scan-bundle:mvn:org.springframework.osgi/jetty.start.osgi/1.0.0
scan-bundle:mvn:org.springframework.osgi/jetty.web.extender.fragment.osgi/1.0.1

# required maven repositories
# http://repository.springsource.com/maven/bundles/release
# http://repository.springsource.com/maven/bundles/external
# http://s3.amazonaws.com/maven.springframework.org/milestone
# http://s3.amazonaws.com/maven.springframework.org/osgi (for jetty.starter.osgi)
# http://s3.amazonaws.com/maven.springframework.org/release


#===  config/logging.conf  ===
-Dpax.log4j.logger.org.springframework=WARN
-Dpax.log4j.logger.org.springframework.osgi=DEBUG
-Dpax.log4j.logger.org.springframework.osgi.extender.internal.activator.LifecycleManager=INFO
-Dpax.log4j.logger.org.springframework.osgi.extender.internal.blueprint=INFO
-Dpax.log4j.logger.org.apache=WARN
-Dpax.log4j.logger.org.apache.catalina=DEBUG



#===  config/misc.profile  ===
# misc bundles

#===  config/osgitest-app.profile  ===
scan-composite:file:config/grails.profile

# Grails application "osgitest"
#war:file:///Users/wolfgang/dev/workspaces/grails/osgitest/target/osgitest-0.1.war?Webapp-Context=osgitest
#scan-bundle:file:/Users/wolfgang/dev/workspaces/grails/osgitest/target/osgitest-0.1.war


#===  config/settings.conf  ===
--platform=equinox
--version=3.5.1
#--version=3.5.0
--profiles=log,equinox.ds,equinox.config,equinox.config,equinox.prefs,url
#--profiles=log,equinox.ds,equinox.config,equinox.config,equinox.prefs,url,felix.webconsole,spring.dm/1.2.0
#--profiles=log,equinox.ds,equinox.config,equinox.config,equinox.prefs,url,felix.webconsole,web,war
#--skipInvalidBundles
#--noBundleValidation
#--vmOptions=-Xdebug
#--vmOptions=-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005
#--vmOptions=-Xmx512m
--vmOptions=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -Xmx512m
# this is for debugging

# keep runner files and bundle cache in directory system
--workingDirectory=system

--repositories=+\
http://repository.springsource.com/maven/bundles/release,\
http://repository.springsource.com/maven/bundles/external,\
http://s3.amazonaws.com/maven.springframework.org/osgi,\
http://s3.amazonaws.com/maven.springframework.org/milestone

# logging configuration
scan-composite:file:config/logging.conf


#===  config/spring.profile  ===
# Spring 3.0RC1
scan-bundle:mvn:org.springframework/spring-aop/3.0.0.RC1
scan-bundle:mvn:org.springframework/spring-asm/3.0.0.RC1
scan-bundle:mvn:org.springframework/spring-aspects/3.0.0.RC1
scan-bundle:mvn:org.springframework/spring-beans/3.0.0.RC1
scan-bundle:mvn:org.springframework/spring-context/3.0.0.RC1
scan-bundle:mvn:org.springframework/spring-context-support/3.0.0.RC1
scan-bundle:mvn:org.springframework/spring-core/3.0.0.RC1
scan-bundle:mvn:org.springframework/spring-expression/3.0.0.RC1
#scan-bundle:mvn:org.springframework/spring-faces/3.0.0.RC1
#scan-bundle:mvn:org.springframework/spring-instrument/3.0.0.RC1
#scan-bundle:mvn:org.springframework/spring-instrument-classloading/3.0.0.RC1
#scan-bundle:mvn:org.springframework/spring-instrument-tomcat/3.0.0.RC1
#scan-bundle:mvn:org.springframework/spring-integration-tests/3.0.0.RC1
scan-bundle:mvn:org.springframework/spring-jdbc/3.0.0.RC1
scan-bundle:mvn:org.springframework/spring-jms/3.0.0.RC1
scan-bundle:mvn:org.springframework/spring-orm/3.0.0.RC1
#scan-bundle:mvn:org.springframework/spring-oxm/3.0.0.RC1
#scan-bundle:mvn:org.springframework/spring-struts/3.0.0.RC1
#scan-bundle:mvn:org.springframework/spring-test/3.0.0.RC1
#scan-bundle:mvn:org.springframework/spring-tomcat-weaver/3.0.0.RC1
scan-bundle:mvn:org.springframework/spring-tx/3.0.0.RC1
scan-bundle:mvn:org.springframework/spring-web/3.0.0.RC1
scan-bundle:mvn:org.springframework/spring-webmvc/3.0.0.RC1
#scan-bundle:mvn:org.springframework/spring-webmvc-portlet/3.0.0.RC1

# Spring dependencies
#scan-bundle:mvn:org.springframework.osgi/aopalliance.osgi/1.0-SNAPSHOT
#scan-bundle:mvn:org.springframework.osgi/asm.osgi/2.2.3-SNAPSHOT
scan-bundle:mvn:org.aopalliance/com.springsource.org.aopalliance/1.0.0
scan-bundle:mvn:org.objectweb.asm/com.springsource.org.objectweb.asm/2.2.3
scan-bundle:mvn:net.sourceforge.cglib/com.springsource.net.sf.cglib/2.1.3

# required maven repositories
# http://repository.springsource.com/maven/bundles/release
# http://repository.springsource.com/maven/bundles/external
# http://s3.amazonaws.com/maven.springframework.org/milestone
# http://s3.amazonaws.com/maven.springframework.org/osgi
## http://s3.amazonaws.com/maven.springframework.org/release


#===  config/springdm.profile  ===
# Spring DM 2.0M1
#scan-bundle:mvn:org.springframework.osgi/spring-osgi/2.0.0.M1
#scan-bundle:mvn:org.springframework.osgi/spring-osgi-annotation/2.0.0.M1
#scan-bundle:mvn:org.springframework.osgi/spring-osgi-annotation.app/2.0.0.M1
scan-bundle:mvn:org.springframework.osgi/spring-osgi-core/2.0.0.M1
scan-bundle:mvn:org.springframework.osgi/spring-osgi-extender/2.0.0.M1
scan-bundle:mvn:org.springframework.osgi/spring-osgi-io/2.0.0.M1
#scan-bundle:mvn:org.springframework.osgi/spring-osgi-mock/2.0.0.M1
#scan-bundle:mvn:org.springframework.osgi/spring-osgi-test/2.0.0.M1
#scan-bundle:mvn:org.springframework.osgi/spring-osgi-test-support/2.0.0.M1
scan-bundle:mvn:org.springframework.osgi/spring-osgi-web/2.0.0.M1
scan-bundle:mvn:org.springframework.osgi/spring-osgi-web-extender/2.0.0.M1

# spring
scan-composite:file:config/spring.profile

# web container
scan-composite:file:config/web.profile

# required maven repositories
# http://repository.springsource.com/maven/bundles/release
# http://repository.springsource.com/maven/bundles/external
# http://s3.amazonaws.com/maven.springframework.org/milestone
# http://s3.amazonaws.com/maven.springframework.org/osgi
## http://s3.amazonaws.com/maven.springframework.org/release


#===  config/tomcat.profile  ===
# OSGi-fied Tomcat
# jetty starter and default configuration
#scan-bundle:mvn:org.springframework.osgi/catalina.start.osgi/1.0.0

# version 6.0.18
#scan-bundle:mvn:org.apache.catalina/com.springsource.org.apache.catalina/6.0.18
#scan-bundle:mvn:org.apache.jasper/com.springsource.org.apache.jasper/6.0.18
#scan-bundle:mvn:org.apache.jasper/com.springsource.org.apache.jasper.org.eclipse.jdt/6.0.18
#scan-bundle:mvn:org.apache.coyote/com.springsource.org.apache.coyote/6.0.18
#scan-bundle:mvn:org.apache.juli/com.springsource.org.apache.juli.extras/6.0.18
#scan-bundle:mvn:org.apache.el/com.springsource.org.apache.el/6.0.18
# version 6.0.20
scan-bundle:mvn:org.apache.catalina.springsource/com.springsource.org.apache.catalina.springsource/6.0.20.S2-r5956
scan-bundle:mvn:org.apache.jasper.springsource/com.springsource.org.apache.jasper.springsource/6.0.20.S2-r5956
scan-bundle:mvn:org.apache.jasper/com.springsource.org.apache.jasper.org.eclipse.jdt/6.0.16
scan-bundle:mvn:org.apache.coyote.springsource/com.springsource.org.apache.coyote.springsource/6.0.20.S2-r5956
scan-bundle:mvn:org.apache.juli.springsource/com.springsource.org.apache.juli.extras.springsource/6.0.20.S2-r5956
scan-bundle:mvn:org.apache.el.springsource/com.springsource.org.apache.el.springsource/6.0.20.S2-r5956

# Tomcat dependencies
scan-bundle:mvn:javax.activation/com.springsource.javax.activation/1.1.1
scan-bundle:mvn:javax.annotation/com.springsource.javax.annotation/1.0.0
scan-bundle:mvn:javax.el/com.springsource.javax.el/1.0.0
scan-bundle:mvn:javax.ejb/com.springsource.javax.ejb/3.0.0
scan-bundle:mvn:javax.mail/com.springsource.javax.mail/1.4.1
scan-bundle:mvn:javax.persistence/com.springsource.javax.persistence/1.99.0
scan-bundle:mvn:javax.transaction/com.springsource.javax.transaction/1.1.0
scan-bundle:mvn:javax.servlet/com.springsource.javax.servlet/2.5.0
scan-bundle:mvn:javax.servlet/com.springsource.javax.servlet.jsp/2.1.0
scan-bundle:mvn:javax.xml.bind/com.springsource.javax.xml.bind/2.1.7
scan-bundle:mvn:javax.xml.rpc/com.springsource.javax.xml.rpc/1.1.0
scan-bundle:mvn:javax.xml.soap/com.springsource.javax.xml.soap/1.3.0
scan-bundle:mvn:javax.xml.stream/com.springsource.javax.xml.stream/1.0.1
scan-bundle:mvn:javax.xml.ws/com.springsource.javax.xml.ws/2.1.1

# required maven repositories
# http://repository.springsource.com/maven/bundles/release
# http://repository.springsource.com/maven/bundles/external
# http://s3.amazonaws.com/maven.springframework.org/milestone
# http://s3.amazonaws.com/maven.springframework.org/osgi
## http://s3.amazonaws.com/maven.springframework.org/release


#===  config/web.profile  ===
# web container
# use jetty, as tomcat currently does not seem to work:
# only index.gsp is displayed
#scan-composite:file:config/tomcat.profile
scan-composite:file:config/jetty.profile

# Tomcat and Jetty dependencies
scan-bundle:mvn:javax.activation/com.springsource.javax.activation/1.1.1
scan-bundle:mvn:javax.annotation/com.springsource.javax.annotation/1.0.0
scan-bundle:mvn:javax.el/com.springsource.javax.el/1.0.0
scan-bundle:mvn:javax.ejb/com.springsource.javax.ejb/3.0.0
scan-bundle:mvn:javax.mail/com.springsource.javax.mail/1.4.1
scan-bundle:mvn:javax.persistence/com.springsource.javax.persistence/1.99.0
scan-bundle:mvn:javax.transaction/com.springsource.javax.transaction/1.1.0
scan-bundle:mvn:javax.servlet/com.springsource.javax.servlet/2.5.0
scan-bundle:mvn:javax.servlet/com.springsource.javax.servlet.jsp/2.1.0
scan-bundle:mvn:javax.xml.bind/com.springsource.javax.xml.bind/2.1.7
scan-bundle:mvn:javax.xml.rpc/com.springsource.javax.xml.rpc/1.1.0
scan-bundle:mvn:javax.xml.soap/com.springsource.javax.xml.soap/1.3.0
scan-bundle:mvn:javax.xml.stream/com.springsource.javax.xml.stream/1.0.1
scan-bundle:mvn:javax.xml.ws/com.springsource.javax.xml.ws/2.1.1

# Felix Web console
scan-bundle:mvn:org.apache.felix/org.apache.felix.webconsole/2.0.2



