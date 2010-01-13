# Spring DM 2.0.0 M1
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
scan-composite:${this.relative}/spring.profile

# web container
scan-composite:${this.relative}/web.profile

# required maven repositories
# http://repository.springsource.com/maven/bundles/release
# http://repository.springsource.com/maven/bundles/external
# http://s3.amazonaws.com/maven.springframework.org/milestone
# http://s3.amazonaws.com/maven.springframework.org/osgi
## http://s3.amazonaws.com/maven.springframework.org/release
