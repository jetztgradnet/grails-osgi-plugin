import grails.util.BuildScope

import org.ops4j.pax.runner.Run;

// TODO what are build scopes for?
//scriptScope = BuildScope.WAR

includeTargets << grailsScript("Init")
includeTargets << grailsScript("_GrailsWar")
includeTargets << grailsScript("_GrailsPackage")

def paxRunnerGrailsProfile="$osgiPluginDir/grails-app/paxrunner-grails.profile"


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
	
	//println "using profile $paxRunnerGrailsProfile"
	Run.main(["--args=file:$paxRunnerGrailsProfile", "scan-bundle:file:${warName}@update", "--log=DEBUG" ] as String[]);
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
	echo " NOTE: The first startup might take a some time, while the OSGi framework and some dependencies are downloaded! "
	echo "================================================================================================================"
	
	//println "using profile $paxRunnerGrailsProfile"
	Run.main(["--executor=zip", "--args=file:$paxRunnerGrailsProfile", "scan-bundle:file:${warName}@update", "--log=INFO" ] as String[]);

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

	// TODO: avoid bundling if there were no changes
	war()
}
