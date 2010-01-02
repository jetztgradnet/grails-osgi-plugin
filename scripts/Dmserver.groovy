includeTargets << grailsScript("_Osgi")

// this script is modeled after the Tomcat plugin 

target(main: '''\
	Script used to interact with remote SpringSource DM server.
	The following subcommands are available:

		grails dmserver deploy - Deploy to a dmserver
		grails dmserver undeploy - Undeploy from a dmserver
		grails dmserver list - List bundles
		''') {
	depends(parseArguments, createConfig)

	def cmd = argsMap.params ? argsMap.params[0] : 'deploy'
	argsMap.params.clear()
	def user = config.dmserver?.deploy.username ?: 'admin'
	def pass = config.dmserver?.deploy.password ?: 'springsource'	
	def url = config.dmserver?.deploy.url ?: 'http://localhost:8080/manager'
	
	switch(cmd) {
		case 'deploy':
			bundle()
			println "Deploying application $serverContextPath to DM Server"
			
			// TODO: Implement script here
			println "************ Sorry, deploying applications is not yet implemented..."
			/*
			deploy(war:warName,
					url:url,
					path:serverContextPath,
					username:user,
					password:pass)
			*/
			break
		case 'list':
			// TODO: Implement script here
			println "************ Sorry, listing bundles is not yet implemented..."
			/*
			list(url:url,
				username:user,
				password:pass)
			*/
			break
		case 'undeploy':
			configureServerContextPath()
			println "Undeploying application $serverContextPath from DM Server"
			
			// TODO: Implement script here
			println "************ Sorry, undeploying applications is not yet implemented..."
			/*
			undeploy(
					url:url,
					path:serverContextPath,
					username:user,
					password:pass)
			*/		
			break
	}
}

setDefaultTarget(main)
