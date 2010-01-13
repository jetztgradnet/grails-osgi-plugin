# web container
# use jetty, as tomcat currently does not seem to work:
# only index.gsp is displayed
#scan-composite:${this.relative}/tomcat.profile
scan-composite:${this.relative}/jetty.profile

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

