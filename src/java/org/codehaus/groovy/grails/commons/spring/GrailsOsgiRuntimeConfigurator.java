package org.codehaus.groovy.grails.commons.spring;

import org.codehaus.groovy.grails.commons.GrailsApplication;
import org.springframework.context.ApplicationContext;

public class GrailsOsgiRuntimeConfigurator extends GrailsRuntimeConfigurator {

	public GrailsOsgiRuntimeConfigurator(GrailsApplication application,
			ApplicationContext parent) {
		super(application, parent);
	}

	public GrailsOsgiRuntimeConfigurator(GrailsApplication application) {
		super(application);
	}
	
	protected WebRuntimeSpringConfiguration createWebRuntimeSpringConfiguration(GrailsApplication application, 
			ApplicationContext parent, ClassLoader classLoader) {
		return new OsgiWebRuntimeSpringConfiguration(parent, classLoader);
	}
	
}
