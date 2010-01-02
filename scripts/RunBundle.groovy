import org.ops4j.pax.runner.Run;

includeTargets << grailsScript("_Osgi")

target(main: '''Package and run the application as OSGi bundle
	
	Examples: 
	grails run-bundle
	grails prod run-bundle
	''') {
	runBundle()
}

setDefaultTarget(main)
