import java.io.File;


import org.codehaus.groovy.grails.resolve.IvyDependencyManager

import org.eclipse.core.runtime.adaptor.EclipseStarter

import org.osgi.framework.BundleContext
import org.osgi.framework.Bundle

import grails.util.BuildScope

//import org.ops4j.pax.runner.Run;
import org.ops4j.pax.swissbox.tinybundles.core.TinyBundles

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;


// TODO what are build scopes for?
//scriptScope = BuildScope.WAR

includeTargets << grailsScript("Init")
includeTargets << grailsScript("_GrailsSettings")
includeTargets << grailsScript("_GrailsPackage")
includeTargets << grailsScript("_GrailsWar")

def osgiSettingsDir="$osgiPluginDir/grails-app/osgi"

// TODO support more than one profile
//def paxRunnerGrailsProfile="$osgiPluginDir/grails-app/paxrunner-grails.profile"
def paxRunnerGrailsProfile = new File("$osgiSettingsDir/grails.profile").canonicalPath
def paxRunnerSettings = new File("$osgiSettingsDir/settings.conf").canonicalPath

// used bundles
allBundles = []

systemBundles = [ 
	//'org.eclipse/osgi/3.5.0.v20090520',
	'org.eclipse.osgi:util:3.2.0.v20090520-1800',
	'org.eclipse.osgi:services:3.2.0.v20090520-1800',
]
allBundles << systemBundles

loggingBundles = [
    'org.apache.log4j:com.springsource.org.apache.log4j:1.2.15',
    'org.ops4j.pax.logging:pax-logging-api:1.4',
	'org.ops4j.pax.logging:pax-logging-service:1.4',
	
	//'org.slf4j:slf4j-api:1.5.10',
	//'org.slf4j:slf4j-log4j12:1.5.10',
	//'org.slf4j:jcl-over-slf4j:1.5.10',
]
allBundles << loggingBundles

springVersion = '3.0.0.RELEASE'
springBundles = [
	"org.springframework:org.springframework.aop:$springVersion",
	"org.springframework:org.springframework.asm:$springVersion",
	"org.springframework:org.springframework.aspects:$springVersion",
	"org.springframework:org.springframework.beans:$springVersion",
	"org.springframework:org.springframework.context:$springVersion",
	"org.springframework:org.springframework.context.support:$springVersion",
	"org.springframework:org.springframework.core:$springVersion",
	"org.springframework:org.springframework.expression:$springVersion",
	"org.springframework:org.springframework.instrument:$springVersion",
	//"org.springframework:org.springframework.instrument.tomcat:$springVersion",
	"org.springframework:org.springframework.jdbc:$springVersion",
	"org.springframework:org.springframework.jms:$springVersion",
	"org.springframework:org.springframework.orm:$springVersion",
	//"org.springframework:org.springframework.oxm:$springVersion",
	"org.springframework:org.springframework.transaction:$springVersion",
	"org.springframework:org.springframework.web:$springVersion",
	"org.springframework:org.springframework.web.servlet:$springVersion",
	//"org.springframework:org.springframework.web.portlet:$springVersion",
	
	// Spring dependencies
	'org.aopalliance:com.springsource.org.aopalliance:1.0.0',
	//'org.objectweb.asm:com.springsource.org.objectweb.asm:2.2.3',
	//'net.sourceforge.cglib:com.springsource.net.sf.cglib:2.1.3',
	'net.sourceforge.cglib:com.springsource.net.sf.cglib:2.2.0',
]
allBundles << springBundles

webDeps = [
     'javax.activation:com.springsource.javax.activation:1.1.1',
     'javax.annotation:com.springsource.javax.annotation:1.0.0',
     'javax.el:com.springsource.javax.el:1.0.0',
     'javax.ejb:com.springsource.javax.ejb:3.0.0',
     'javax.mail:com.springsource.javax.mail:1.4.1',
     'javax.persistence:com.springsource.javax.persistence:1.99.0',
     'javax.transaction:com.springsource.javax.transaction:1.1.0',
     'javax.servlet:com.springsource.javax.servlet:2.5.0',
     'javax.servlet:com.springsource.javax.servlet.jsp:2.1.0',
     'javax.xml.bind:com.springsource.javax.xml.bind:2.1.7',
     'javax.xml.rpc:com.springsource.javax.xml.rpc:1.1.0',
     'javax.xml.soap:com.springsource.javax.xml.soap:1.3.0',
     'javax.xml.stream:com.springsource.javax.xml.stream:1.0.1',
     'javax.xml.ws:com.springsource.javax.xml.ws:2.1.1',
     'javax.jms:com.springsource.javax.jms:1.1.0',
]
allBundles << webDeps

jettyBundles = [
	'org.mortbay.jetty:com.springsource.org.mortbay.jetty.server:6.1.9',
	'org.mortbay.jetty:com.springsource.org.mortbay.util:6.1.9',
	// jetty starter and default configuration
	'org.springframework.osgi:jetty.start.osgi:1.0.0',
	'org.springframework.osgi:jetty.web.extender.fragment.osgi:1.0.1',
]
allBundles << jettyBundles

tomcatBundles = [
	// Tomcat starter and default configuration
	'org.springframework.osgi:catalina.start.osgi:1.0.0',
	// version 6.0.20
	'org.apache.catalina.springsource:com.springsource.org.apache.catalina.springsource:6.0.20.S2-r5956',
	'org.apache.jasper.springsource:com.springsource.org.apache.jasper.springsource:6.0.20.S2-r5956',
	'org.apache.jasper:com.springsource.org.apache.jasper.org.eclipse.jdt:6.0.16',
	'org.apache.coyote.springsource:com.springsource.org.apache.coyote.springsource:6.0.20.S2-r5956',
	'org.apache.juli.springsource:com.springsource.org.apache.juli.extras.springsource:6.0.20.S2-r5956',
	'org.apache.el.springsource:com.springsource.org.apache.el.springsource:6.0.20.S2-r5956',
]
//allBundles << tomcatBundles

springDMVersion = '2.0.0.M1'

springDMBundles = [
	//"org.springframework.osgi:spring-osgi-annotation:$springDMVersion",
	//"org.springframework.osgi:spring-osgi-annotation.app:$springDMVersion",
	"org.springframework.osgi:spring-osgi-core:$springDMVersion",
	"org.springframework.osgi:spring-osgi-extender:$springDMVersion",
	"org.springframework.osgi:spring-osgi-io:$springDMVersion",
	//'org.springframework.osgi:spring-osgi-mock:$springDMVersion",
	//'org.springframework.osgi:spring-osgi-test:$springDMVersion",
	//'org.springframework.osgi:spring-osgi-test-support:$springDMVersion",
	"org.springframework.osgi:spring-osgi-web:$springDMVersion",
	"org.springframework.osgi:spring-osgi-web-extender:$springDMVersion",
]
allBundles << springDMBundles

commonBundles = [
	'org.apache.commons:com.springsource.org.apache.commons.beanutils:1.8.0',
	'org.apache.commons:com.springsource.org.apache.commons.collections:3.2.1',
	'org.apache.commons:com.springsource.org.apache.commons.codec:1.3.0',
	'org.apache.commons:com.springsource.org.apache.commons.dbcp:1.2.2.osgi',
	'org.apache.commons:com.springsource.org.apache.commons.el:1.0.0',
	'org.apache.commons:com.springsource.org.apache.commons.digester:1.8.1',
	'org.apache.commons:com.springsource.org.apache.commons.fileupload:1.2.1',
	'org.apache.commons:com.springsource.org.apache.commons.httpclient:3.1.0',
	'org.apache.commons:com.springsource.org.apache.commons.io:1.4.0',
	'org.apache.commons:com.springsource.org.apache.commons.lang:2.4.0',
	'org.apache.commons:com.springsource.org.apache.commons.pool:1.5.3',
	'org.apache.commons:com.springsource.org.apache.commons.validator:1.3.1',
	
	'org.apache.oro:com.springsource.org.apache.oro:2.0.8',
	
	'org.apache.ant:com.springsource.org.apache.ivy:2.0.0',
	'org.apache.ant:com.springsource.org.apache.tools.ant:1.7.1',
	
	'org.antlr:com.springsource.antlr:2.7.7',
	
	'org.dom4j:com.springsource.org.dom4j:1.6.1',
	
	'com.opensymphony.sitemesh:com.springsource.com.opensymphony.sitemesh:2.4.1',
	
	'org.hibernate:com.springsource.org.hibernate.annotations.common:3.3.0.ga',
	'org.hibernate:com.springsource.org.hibernate.annotations:3.4.0.GA',
	'org.hibernate:com.springsource.org.hibernate:3.3.2.GA',
	'org.hibernate:com.springsource.org.hibernate.ejb:3.4.0.GA',
	'org.hibernate:com.springsource.org.hibernate.cache:3.3.2.GA',
	
	'javax.persistence:com.springsource.javax.persistence:1.99.0',
	'org.jboss.javassist:com.springsource.javassist:3.9.0.GA',
	'org.objectweb.asm:com.springsource.org.objectweb.asm:1.5.3',

	'org.jboss.cache:com.springsource.org.jboss.cache:3.2.0.GA',
	'org.jboss.util:com.springsource.org.jboss.util:2.2.13.GA',
	'org.jboss.logging:com.springsource.org.jboss.logging:2.0.5.GA',
	'org.jgroups:com.springsource.org.jgroups:2.5.1',

	//'net.sf.ehcache:ehcache-core:1.7.1',
	'net.sourceforge.ehcache:com.springsource.net.sf.ehcache:1.6.2',
]
allBundles << commonBundles

grailsBundles = [
	'org.grails:grails-osgi:1.2.0',	// combines -core and -bootstrap
	'org.grails:grails-crud:1.2.0',
	//'org.grails:grails-docs:1.2.0',
	'org.grails:grails-gorm:1.2.0',
	'org.grails:grails-resources:1.2.0',
	'org.grails:grails-spring:1.2.0',
	'org.grails:grails-web:1.2.0',

	'org.codehaus.groovy:groovy-all:1.6.7',
]
allBundles << grailsBundles

auxBundles = [
	'org.apache.felix:org.apache.felix.webconsole:2.0.2'
]
allBundles << auxBundles

appBundles = []
allBundles << appBundles

allBundles = allBundles.flatten().collect { it.toString() }

def osgiDependencies = {
	log "info" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
	repositories {        
		grailsPlugins()
		grailsHome()
		
		// uncomment the below to enable remote dependency resolution
		// from public Maven repositories
		mavenLocal()
		ebr()
		mavenCentral()
		
		//mavenRepo 'http://repository.ops4j.org/mvn-snapshots/'
		//mavenRepo 'http://repository.ops4j.org/mvn-releases/'
		mavenRepo 'http://repository.ops4j.org/maven2/'
		
		//mavenRepo 'http://repository.springsource.com/maven/bundles/release'
		//mavenRepo 'http://repository.springsource.com/maven/bundles/external'
		mavenRepo 'http://s3.amazonaws.com/maven.springframework.org/osgi'
		mavenRepo 'http://s3.amazonaws.com/maven.springframework.org/milestone'
		
		//mavenRepo "http://snapshots.repository.codehaus.org"
		//mavenRepo "http://repository.codehaus.org"
		//mavenRepo "http://download.java.net/maven/2/"
		//mavenRepo "http://repository.jboss.com/maven2/"
	}
	dependencies {
		/*
		build ("org.springframework.osgi:spring-osgi-core:$springDMVersion".toString(),
				"org.springframework.osgi:spring-osgi-extender:$springDMVersion".toString(),
				"org.springframework.osgi:spring-osgi-io:$springDMVersion".toString(),
				"org.springframework.osgi:spring-osgi-web:$springDMVersion".toString(),
				"org.springframework.osgi:spring-osgi-web-extender:$springDMVersion".toString()) {
			transitive = false
		}
		build ('org.ops4j.pax.logging:pax-logging-api:1.4',
				'org.ops4j.pax.logging:pax-logging-service:1.4') {
			transitive = false
		}
		*/
		allBundles.each{ dep -> 
			build (dep) {
				transitive = false
			}
		}
	}
}

target(runBundlePaxRunner: '''Package and run the application as OSGi bundle
	
	Examples: 
	grails run-bundle
	grails prod run-bundle
	''') {
	
	// bundle application
	bundle()
	
	echo "================================================================================================================"
	echo " Starting OSGi framework"
	echo " NOTE: The first startup might take a some time, while the OSGi framework and some dependencies are downloaded! "
	echo "================================================================================================================"
	
	// TODO set server ports from serverPort and serverPortHttps:
	// -Dorg.osgi.service.http.port=$serverPort
	// -Dorg.osgi.service.http.port.secure=$serverPortHttps
	// -Dorg.osgi.service.http.secure.enabled=true
	
	// configure logging
	// JUL:
	//java.util.logging.Logger.getLogger(Run.class.getName())?.setLevel(java.util.logging.Level.FINEST)
	// log4j:
	Logger rootLogger = Logger.getRootLogger();
	if (!rootLogger.getAllAppenders().hasMoreElements()) {
		rootLogger.setLevel(Level.INFO);
		rootLogger.addAppender(new ConsoleAppender(
				new PatternLayout("%-5p [%t]: %m%n")));
	}
		
	Logger pkgLogger = rootLogger.getLoggerRepository().getLogger("org.ops4j");
	pkgLogger.setLevel(Level.INFO);
	
	println "using settings $paxRunnerSettings"
	println "using profile $paxRunnerGrailsProfile"
	org.ops4j.pax.runner.Run.main(["--log=debug", "--args=file:$paxRunnerSettings", "scan-composite:file:$paxRunnerGrailsProfile", "scan-bundle:file:${warName}@update" ] as String[]);
}

target(runBundleEquinox: '''Package and run the application as OSGi bundle
	
	Examples: 
	grails run-bundle
	grails prod run-bundle
	''') {
	depends(checkVersion, parseArguments)
			
	bundle()
	
	echo "================================================================================================================"
	echo " Starting OSGi framework"
	echo " NOTE: The first startup might take a some time, while the OSGi framework and some dependencies are downloaded! "
	echo "================================================================================================================"
	
	echo "press CTRL-C to abort"
	
	//println "bundles to install: ${allBundles.join(', ')}"
	
	// TODO set server ports from serverPort and serverPortHttps:
	// -Dorg.osgi.service.http.port=$serverPort
	// -Dorg.osgi.service.http.port.secure=$serverPortHttps
	// -Dorg.osgi.service.http.secure.enabled=true
	
	// configure logging
	// JUL:
	//java.util.logging.Logger.getLogger(Run.class.getName())?.setLevel(java.util.logging.Level.FINEST)
	// log4j:
	Logger rootLogger = Logger.getRootLogger();
	if (!rootLogger.getAllAppenders().hasMoreElements()) {
		rootLogger.setLevel(Level.INFO);
		rootLogger.addAppender(new ConsoleAppender(
				//new PatternLayout("%-5p [%t]: %m%n")));
				new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN)));
	}
	
	echo "resolving OSGi dependencies..."
	def bundleFiles = []
	def manager = new IvyDependencyManager(grailsAppName, grailsAppVersion, grailsSettings)
	manager.parseDependencies(osgiDependencies)

	def report = manager.resolveDependencies()
	if(report.hasError()) {
		println """
There was an error resolving the OSGi dependencies.
This could be because you have passed an invalid dependency name or because the dependency was not found in one of the default repositories.
Try passing a valid Maven repository with the --repository argument."""
		report.allProblemMessages.each { problem -> println ": $problem" }
		exit 1
	}
	else {
		bundleFiles = report.allArtifactsReports*.localFile
		//bundleFiles.each { file ->
		//	println file.name					
		//}
	}
	
	def osgiRuntimeDir = new File(new File(warName).parentFile, 'osgi').canonicalPath

	EquinoxRunner runner = new EquinoxRunner()
	runner.osgiRuntimeDir = osgiRuntimeDir
	BundleContext ctx = runner.start()

	// install and start infrastructure bundles
	runner.install(bundleFiles, true)

	// install and start grails app bundle
	runner.install(new File(warName), true)
	
	// wait for user to press CTRL-C
	def locker = new Object()
	synchronized(locker) {
		locker.wait()
	}
	//Thread.sleep(10000)
}


target(assembleOsgiRuntime: '''assemble a zipped OSGi runtime for the application 
	
	Examples: 
	grails assemble-osgi-runtime
	grails prod assemble-osgi-runtime
	''') {
			
	// bundle application
	bundle()
	
	echo "================================================================================================================"
	echo " Starting OSGi framework"
	echo " NOTE: The first startup might take some time, while the OSGi framework and some dependencies are downloaded! "
	echo "================================================================================================================"
	
	//println "using profile $paxRunnerGrailsProfile"
	org.ops4j.pax.runner.Run.main(["--executor=zip", "--args=file:$paxRunnerSettings", "scan-composite:file:$paxRunnerGrailsProfile", "scan-bundle:file:${warName}@update", "--log=INFO" ] as String[]);

	// rename "paxrunner-osgi.zip" -> <appname-osgi.zip>
	// NOTE: the "-osgi" part in the filename comes from the name of the working directory
	//       (pax runner option --workingDirectory)
	def outFile = new File("paxrunner-osgi.zip")
	def targetFile = new File("${grailsAppName}-${metadata.getApplicationVersion()}-osgi.zip")
	if (!outFile.renameTo(targetFile)) {
		targetFile = outFile
	}
	if (targetFile.exists()) {
		echo "The assembled OSGi environment can be found in ${targetFile}"
	}
	else {
		echo "Failed to assemble an OSGi environment! See log output for details"
	}
}

target(bundle: '''Package the application as OSGi bundle
	
	Examples: 
	grails bundle
	grails prod bundle
	''') {
	depends(checkVersion, parseArguments, configureWarName)

	// currently we simply call the war target provided by Grails
	// The appropriate OSGi bundle manifest headers are added via
	// The CreateWarStart event in _Events.groovy

	// no point doing this stuff more than once.
	if (!getBinding().variables.containsKey("_bundle_called")) {
		_bundle_called = true
		
		// bundle application
		// TODO always create war or check whether it must be re-created
		if (argsMap?.forceBundle || !new File(warName).exists()) {
			echo "creating bundle"
			war()
		}
		else {
			echo "skipping bundle creation because of existing bundle; use '-forceBundle' to force recreation of bundle"
		}
	}

	appBundles << warName
}


class EquinoxRunner {
	String osgiRuntimeDir
	BundleContext bundleContext
	
	/**
	 * Start OSGi framework.
	 * 
	 * @return root bundle context
	 */
	BundleContext start() {
		if (this.bundleContext) {
			return this.bundleContext
		}
		
		// create runtime directory
		def dir = new File(osgiRuntimeDir)
		if (!dir.exists()) {
			dir.mkdirs()
		}
		
		println "OSGi directory: ${osgiRuntimeDir}"
		
		// initialize framework
		def frameworkProperties = new Properties()
		frameworkProperties.put("osgi.clean", "true");
		frameworkProperties.put("osgi.console", "true");
		frameworkProperties.put("osgi.noShutdown", "true");
		frameworkProperties.put("osgi.install.area", osgiRuntimeDir as String);
		frameworkProperties.put("osgi.configuration.area", "$osgiRuntimeDir/configuration" as String);
		frameworkProperties.put("eclipse.ignoreApp", "true");
		frameworkProperties.put("eclipse.application.noDefault", "true");
		//frameworkProperties.put("log4j.configuration", logConfig.absolutePath);
		EclipseStarter.setInitialProperties(frameworkProperties);
		
		// start framework
		def args = [ "-clean", "-console", "-consoleLog" ] 
		this.bundleContext = EclipseStarter.startup( args as String[], null );
		
		configureLogging()
		
		return this.bundleContext
	}
	
	void configureLogging() {
		if (!this.bundleContext) {
			return
		}
		
		// create log configuration
		def dir = new File(osgiRuntimeDir)
		File logConfig = new File(dir, 'log4j.properties')
		if (!logConfig.exists()) {
			println "preparing log configuration in file ${logConfig}"
			logConfig.withWriter { writer ->
				writer << """# log configuration
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n
log4j.rootLogger=debug, stdout

log4j.logger.org.springframework.osgi.extender.internal.blueprint=WARN
log4j.logger.org.springframework.osgi.extender.internal.activator=WARN
"""
			}
		}
		
		File logConfigBundle = new File(logConfig.parent, 'Log4JConfigurationFragment-1.0.0.jar')
		if (!logConfigBundle.exists()
		|| (logConfigBundle.lastModified() < logConfig.lastModified())) {
			println "creating log configuration bundle in file ${logConfigBundle}"
			// create log4j fragment bundle containing logging configuration
			// see http://lists.ops4j.org/pipermail/general/2009q4/003297.html
			// for TinyBundles usage notes
			InputStream bundleStream = TinyBundles.newBundle()
					.add('log4j.properties', new FileInputStream(logConfig))
					.set(org.osgi.framework.Constants.BUNDLE_MANIFESTVERSION, '2')
					.set(org.osgi.framework.Constants.BUNDLE_SYMBOLICNAME, 'Log4JConfigurationFragment')
					.set(org.osgi.framework.Constants.BUNDLE_NAME, 'Log4JConfigurationFragment')
					.set(org.osgi.framework.Constants.BUNDLE_VERSION, '1.0.0')
					.set(org.osgi.framework.Constants.BUNDLE_CLASSPATH, '.')
					.set(org.osgi.framework.Constants.FRAGMENT_HOST, 'com.springsource.org.apache.log4j')
					.build(TinyBundles.with()) //withBnd())
			if (bundleStream) {
				// save bundle to file
				logConfigBundle.withOutputStream { out ->
					out << bundleStream
				}
			}
		}
		
		// TODO set log configuration as Dictionary using ConfigAdmin to pid 'org.ops4j.pax.logging'
		// see http://wiki.ops4j.org/display/paxlogging/Configuration
		
		if (logConfigBundle.exists()) {
			// install log configuration fragment
			this.bundleContext.installBundle("file:${logConfigBundle.canonicalPath}")
		}
	}
	
	/**
	 * Shutdown OSGi framework.
	 */
	void stop() {
		EclipseStarter.shutdown();
		this.bundleContext = null
	}
	
	void install(File bundleFile, def autoStart = true) {
		install([bundleFile], autoStart)
	}
	
	void install(List<File> bundleFiles, def autoStart = true) {
		def bundles = []
		// install each file
		bundleFiles.each { file ->
			println "installing bundle ${file.name}"//" (${file.absolutePath})"
			try {
				def bundle = this.bundleContext.installBundle("file://${file.absolutePath}");
				bundles << bundle
			}
			catch (e) {
				println "failed to install bundle ${file.name}: ${e.message}"
			}
		}
		
		if (autoStart) {
			// start each bundle
			bundles.each { bundle ->
				try {
					// skip start for fragments
					if (!isFragment(bundle)) {
						bundle.start();
					}
				}
				catch (e) {
					println "failed to start bundle ${bundle.symbolicName}: ${e.message}"
				}	
			}
		}
	}
	
	static isFragment(Bundle bundle) {
		// if a bundle is a fragment, its bundle context is null
		String fragmentHost = bundle.headers.get(org.osgi.framework.Constants.FRAGMENT_HOST)
		return (fragmentHost != null)
	}
}

