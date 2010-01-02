includeTargets << grailsScript("_GrailsWar")
includeTargets << grailsScript("_GrailsPackage")

target(main: '''Package the application as OSGi bundle
	
Examples: 
grails bundle
grails prod bundle
''') {
	depends(checkVersion, war)
}

setDefaultTarget(main)
