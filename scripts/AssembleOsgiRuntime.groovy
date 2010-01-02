includeTargets << grailsScript("_Osgi")

target(main: '''assemble a zipped OSGi runtime for the application 
	
	Examples: 
	grails assemble-osgi-runtime
	grails prod assemble-osgi-runtime
	''') {
	assembleOsgiRuntime()
}

setDefaultTarget(main)
