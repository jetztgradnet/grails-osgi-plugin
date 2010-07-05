includeTargets << new File("$osgiPluginDir/scripts/_Osgi.groovy")

target(main: '''list all bundles and their origin
	
Examples: 
grails list-bundles
''') {
	listBundles()
}

setDefaultTarget(main)
