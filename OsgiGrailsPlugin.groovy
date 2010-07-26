class OsgiGrailsPlugin {
    // the plugin version
    def version = "0.2.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.2.0 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp",
    ]

    def author = "Wolfgang Schell"
    def authorEmail = "grails@jetztgrad.net"
    def title = "OSGi integration for Grails applications"
    def description = '''The OSGi plugin provides scripts to package a Grails application as OSGi bundle.'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/osgi"

    def doWithWebDescriptor = { webXml ->
    	// use this applicatio context implementation only when creating a bundle
		if (!isOsgiEnvironment()) {
			return
		}
		
		def contextParam = webXml.'context-param'
		def lastContextParam = contextParam[contextParam.size()-1]
		lastContextParam + {
			'context-param' {
				'param-name'("contextClass")
				'param-value'("org.springframework.osgi.web.context.support.OsgiBundleXmlWebApplicationContext")
			}
		}
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
    
    boolean isOsgiEnvironment() {
   		return Boolean.getBoolean('grails.osgi')
    }
}
