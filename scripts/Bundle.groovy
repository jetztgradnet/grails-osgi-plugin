includeTargets << new File("$osgiPluginDir/scripts/_Osgi.groovy")

target(main: '''Package the application as OSGi bundle
	
Examples: 
grails bundle
grails prod bundle
''') {
	bundle()
}

setDefaultTarget(main)
