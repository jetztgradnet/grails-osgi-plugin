package org.codehaus.groovy.grails.commons.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class OsgiWebRuntimeSpringConfiguration extends
		WebRuntimeSpringConfiguration {

	public OsgiWebRuntimeSpringConfiguration(ApplicationContext parent,
			ClassLoader cl) {
		super(parent, cl);
	}

	public OsgiWebRuntimeSpringConfiguration(ApplicationContext parent) {
		super(parent);
	}
	
	protected GenericApplicationContext createApplicationContext(ApplicationContext parent) {
		if (parent != null && beanFactory != null) {
			if (beanFactory instanceof DefaultListableBeanFactory) {
				return new GrailsWebApplicationContext(
						(DefaultListableBeanFactory) beanFactory, parent);
			} else {
				throw new IllegalArgumentException(
						"ListableBeanFactory set must be a subclass of DefaultListableBeanFactory");
			}
		}
		else {
			return super.createApplicationContext(parent);
		}
	}
}
