import grails.util.BuildScope

import org.ops4j.pax.runner.Run;

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

// TODO support more than one profile
def paxRunnerGrailsConfigDir="$osgiPluginDir/grails-app/osgi"
//def paxRunnerGrailsProfile="$osgiPluginDir/grails-app/paxrunner-grails.profile"
def paxRunnerGrailsProfile = new File("$paxRunnerGrailsConfigDir/grails.profile").canonicalPath
def paxRunnerSettings = new File("$paxRunnerGrailsConfigDir/settings.conf").canonicalPath

// used bundles
systemBundles = []
auxBundles = []
appBundles = []

target(runBundle: '''Package and run the application as OSGi bundle
	
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
		
	// The TTCC_CONVERSION_PATTERN contains more info than
	// the pattern we used for the root logger
	Logger pkgLogger = rootLogger.getLoggerRepository().getLogger("org.ops4j");
	pkgLogger.setLevel(Level.DEBUG);
	pkgLogger.addAppender(new ConsoleAppender(
			new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN)));
	
	
	println "using settings $paxRunnerSettings"
	println "using profile $paxRunnerGrailsProfile"
	Run.main(["--log=debug", "--args=file:$paxRunnerSettings", "scan-composite:file:$paxRunnerGrailsProfile", "scan-bundle:file:${warName}@update" ] as String[]);
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
	Run.main(["--executor=zip", "--args=file:$paxRunnerSettings", "scan-composite:file:$paxRunnerGrailsProfile", "scan-bundle:file:${warName}@update", "--log=INFO" ] as String[]);

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
	depends(checkVersion)

	// currently we simply call the war target provided by Grails
	// The appropriate OSGi bundle manifest headers are added via
	// The CreateWarStart event in _Events.groovy

	// no point doing this stuff more than once.
	if (!getBinding().variables.containsKey("_bundle_called")) {
		_bundle_called = true
		
		// TODO: avoid bundling if there were no changes
		war()
	}

	appBundles << warName
}
