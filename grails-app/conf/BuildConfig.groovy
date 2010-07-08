grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir	= "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits( "global" ) {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {        
        grailsPlugins()
        grailsHome()
		grailsCentral()

        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenLocal()
        mavenCentral()
		ebr()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.5'
		build 'org.slf4j:jcl-over-slf4j:1.5.11'
		
		// contained in Equinox jar
		//compile 'org.osgi:org.osgi.core:4.2.0'
		
		def equinoxDrop = 'R-3.6-201006080911' // Helios release // old: 'S-3.6M6-201003121448'
		def equinoxVersion = '3.6.0.v20100517' // Helios release // old: '3.5.1.R35x_v20091005'
		def springDMVersion = '2.0.0.M1' 
		
		def url = "http://ftp-stud.fht-esslingen.de/pub/Mirrors/eclipse/equinox/drops/$equinoxDrop" // old: "http://eclipsemirror.yoxos.com/eclipse.org/equinox/drops/$equinoxDrop"
		def pattern = "${url}/[module]_[revision].[ext]" // old: "${url}/[organisation].[module]_[revision].[ext]"
		def equinoxResolver = new org.apache.ivy.plugins.resolver.URLResolver(name: 'Equinox' )
		equinoxResolver.addArtifactPattern(pattern)
		equinoxResolver.settings = ivySettings
		equinoxResolver.latestStrategy = new org.apache.ivy.plugins.latest.LatestTimeStrategy()
		equinoxResolver.changingPattern = ".*SNAPSHOT"
		equinoxResolver.setCheckmodified(true)
		resolver equinoxResolver 
		
		build "org.eclipse.osgi:org.eclipse.osgi:$equinoxVersion"
		compile "org.eclipse.osgi:org.eclipse.osgi:$equinoxVersion"
		compile ("org.springframework.osgi:spring-osgi-core:$springDMVersion") {
			transitive = false
		}
		compile ("org.springframework.osgi:spring-osgi-io:$springDMVersion") {
			transitive = false
		}
		
		//build 'org.ops4j.pax.runner:pax-runner:1.3.0'
		//provided 'org.ops4j.pax.runner:pax-runner:1.3.0'
		
		[ 'org.osgi:org.osgi.compendium:4.2.0',
			'org.ops4j.pax.swissbox:pax-swissbox-tinybundles:1.2.0',
			'org.ops4j.base:ops4j-base-lang:1.2.1',
			'org.ops4j.base:ops4j-base-io:1.2.1',
			'org.ops4j.base:ops4j-base-store:1.2.1',
			'org.ops4j.pax.swissbox:pax-swissbox-optional-jcl:1.2.0',
			'org.ops4j.pax.swissbox:pax-swissbox-bnd:1.2.0',
			'biz.aQute:bndlib:0.0.357',
			
			'org.apache.ivy:ivy:2.1.0'
		].each { dep ->
			// build dependencies are put on the classpath for scripts
			build (dep) {
				// we don't want transitive dependencies, as this would import 
				// OSGi packages, which breaks Equinox's signing (packages from 
				// a different bundle)
				transitive = false
			}
			// this is necessary so STS uses the dependency for compiling the script
			compile (dep) {
				transitive = false
			}
		}
    }
}
