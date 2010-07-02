package org.codehaus.groovy.grails.commons.spring;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.osgi.context.ConfigurableOsgiBundleApplicationContext;

public class GrailsOsgiWebApplicationContext extends
		GrailsWebApplicationContext implements ConfigurableOsgiBundleApplicationContext {

	private BundleContext bundleContext = null;
	private boolean publishContextAsService = true;
	
	

	public GrailsOsgiWebApplicationContext(ApplicationContext parent)
			throws BeansException {
		super(parent);
		
		setBundleContextFromParent(parent);
	}

	public GrailsOsgiWebApplicationContext(
			DefaultListableBeanFactory defaultListableBeanFactory,
			ApplicationContext parent) {
		super(defaultListableBeanFactory, parent);
		
		setBundleContextFromParent(parent);
	}
	
	/**
	 * Check whether parent is of type {@link ConfigurableOsgiBundleApplicationContext} and
	 * use its {@link BundleContext}, if available
	 * @param parent
	 */
	private void setBundleContextFromParent(ApplicationContext parent) {
		if (bundleContext != null) {
			return;
		}
		if (parent instanceof ConfigurableOsgiBundleApplicationContext) {
			ConfigurableOsgiBundleApplicationContext osgiParent = (ConfigurableOsgiBundleApplicationContext) parent;
			BundleContext context = osgiParent.getBundleContext();
			if (context != null) {
				setBundleContext(context);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.osgi.context.ConfigurableOsgiBundleApplicationContext#getBundle()
	 */
	public Bundle getBundle() {
		if (bundleContext != null) {
			return bundleContext.getBundle();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.osgi.context.ConfigurableOsgiBundleApplicationContext#getBundleContext()
	 */
	public BundleContext getBundleContext() {
		return bundleContext;
	}

	/* (non-Javadoc)
	 * @see org.springframework.osgi.context.ConfigurableOsgiBundleApplicationContext#setBundleContext(org.osgi.framework.BundleContext)
	 */
	public void setBundleContext(BundleContext context) {
		this.bundleContext  = context;
	}

	public void setPublishContextAsService(boolean publishContextAsService) {
		this.publishContextAsService  = publishContextAsService;
	}
	
	protected boolean isPublishContextAsService() {
		return publishContextAsService;
	}
	
	// TODO add OSGi live cycle handling (see AbstractOsgiBundleApplicationContext):  
	// (un)publish context, 
}
