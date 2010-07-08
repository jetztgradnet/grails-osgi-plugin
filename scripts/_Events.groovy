import grails.util.BuildSettingsHolder


// TODO move all of this into _Osgi.groovy

def optional="resolution:=optional"
def osgiImportSpecs = "version=\"[1.5.0, 5.0.0)\""
def servletImportSpecs = "version=\"[2.4.0, 2.6.0)\""
def jspImportSpecs = "version=\"[2.1.0, 2.3.0)\";$optional"
def jstlImportSpecs = "version=\"[1.1.0, 2.0.0)\";$optional"
def springImportSpecs = "version=\"[3.0.1, 4.0.0)\""
def springDMImportSpecs = "version=\"[2.0.0, 3.0.0)\";$optional"

def springVersion = '3.0.3.RELEASE'

def commonModulesConfig = [
	// AspectJ, byte code generation, and weaving support
	'antlr-2.7.6.jar': 'bundle:com.springsource.antlr;version="[2.7.7,2.7.7]"',
	'aopalliance-1.0.jar': 'package:org.aopalliance.aop;version="1.0.0",org.aopalliance.intercept;version="1.0.0"', //bundle:com.springsource.org.aopalliance;version="[3.0.0.RELEASE, 4.0.0)"
	'aspectjrt-1.6.6.jar': 'bundle:com.springsource.org.aspectj.runtime;version="1.6.6.RELEASE"',
	'aspectjweaver-1.6.6.jar': 'bundle:com.springsource.org.aspectj.runtime;version="1.6.6.RELEASE"',
	'aspectjrt-1.6.8.jar': 'bundle:com.springsource.org.aspectj.runtime;version="1.6.8.RELEASE"',
	'aspectjweaver-1.6.8.jar': 'bundle:com.springsource.org.aspectj.runtime;version="1.6.8.RELEASE"',
	'cglib-nodep-2.1_3.jar': 'bundle:com.springsource.net.sf.cglib;version="[2.1.3,2.2.0)"',
	'javassist-3.8.0.GA.jar': 'bundle:com.springsource.javassist;version="[3.9.0, 4.0.0)"',
	'javassist-3.11.0.GA.jar': 'bundle:com.springsource.javassist;version="[3.9.0, 4.0.0)"',

	// Apache Jakarta Commons
	'commons-beanutils-1.8.0.jar': 'bundle:com.springsource.org.apache.commons.beanutils;version="1.8.0"',
	'commons-codec-1.3.jar': 'bundle:com.springsource.org.apache.commons.codec;version="1.3.0"',
	'commons-collections-3.2.1.jar': 'bundle:com.springsource.org.apache.commons.collections;version="3.2.1"',
	'commons-dbcp-1.2.2.jar': 'bundle:com.springsource.org.apache.commons.dbcp;version="1.2.2.osgi"',
	'commons-el-1.0.jar': 'bundle:com.springsource.org.apache.commons.el;version="1.0.0"',
	'commons-fileupload-1.2.1.jar': 'bundle:com.springsource.org.apache.commons.fileupload;version="1.2.1"',
	'commons-httpclient-3.1.jar': 'bundle:com.springsource.org.apache.commons.httpclient;version="3.1.0"',
	'commons-io-1.4.jar': 'bundle:com.springsource.org.apache.commons.io;version="1.4.0"',
	'commons-lang-2.4.jar': 'bundle:com.springsource.org.apache.commons.lang;version="2.4.0"',
	'commons-pool-1.5.3.jar': 'bundle:com.springsource.org.apache.commons.pool;version="1.5.3"',
	'commons-validator-1.3.1.jar': 'bundle:com.springsource.org.apache.commons.validator;version="1.3.1"',

	// XML and XSL(T)
	'xalan-2.7.1.jar': '',
	'xercesImpl-2.9.1.jar': 'bundle:com.springsource.org.apache.xmlcommons;version="[1.3.4,1.4.0)"', // Xerces is a fragment and attaches to xmlxcommons 'bundle:com.springsource.org.apache.xerces;version="[2.9.1,3.0.0)"',
	'xmlsec-1.4.2.jar': '',
	'xpp3_min-1.1.3.4.O.jar': '',
	'dom4j-1.6.1.jar': 'bundle:com.springsource.org.dom4j;version="1.6.1"',

	// logging related
	'jcl-over-slf4j-1.5.8.jar': 'package:org.apache.commons.logging;version="[1.1.1, 1.2.0)"',
	'jul-to-slf4j-1.5.8.jar': '',
	'log4j-1.2.15.jar': '',
	'slf4j-api-1.5.8.jar': 'package:org.slf4j;version="[1.5.6, 1.6.0)"',
	'slf4j-log4j12-1.5.8.jar': '',

	// standard Java SE/EE apis
	'jta-1.1.jar': 'package:javax.transaction',
	'jaxb-api-2.1.jar': '',
	'jaxb-impl-2.1.3.jar': '',
	'stax-api-1.0-2.jar': '',
	'mail.jar': 'bundle:com.springsource.javax.mail;version="1.4.1"',
	'activation-1.1.jar':  'package:javax.activation',// 'package:javax.activation;version="1.1.1"',
	'activation.jar': '', //'package:javax.activation',

	// web related
	'standard-1.1.2.jar': '', //'bundle:com.springsource.javax.servlet.jsp.jstl;version="[1.1.0, 2.0.0)"',
	'jstl-1.1.2.jar': '',

	// misc
	'oro-2.0.8.jar': 'bundle:com.springsource.org.apache.oro;version="[2.0.8, 2.1.0)"',
	'ivy-2.1.0.jar': 'bundle:com.springsource.org.apache.ivy;version="[2.0, 2.2.0)"',
]

def springModulesConfig = [
	("org.springframework.aop-${springVersion}.jar".toString()) : 'bundle:org.springframework.aop;version="[3.0.0.RELEASE, 4.0.0)"',
	("org.springframework.asm-${springVersion}.jar".toString()) : 'bundle:org.springframework.asm;version="[3.0.0.RELEASE, 4.0.0)"',
	("org.springframework.aspects-${springVersion}.jar".toString()) : 'bundle:org.springframework.aspects;version="[3.0.0.RELEASE, 4.0.0)"',
	("org.springframework.beans-${springVersion}.jar".toString()) : 'bundle:org.springframework.beans;version="[3.0.0.RELEASE, 4.0.0)"',
	("org.springframework.context-${springVersion}.jar".toString()) : 'bundle:org.springframework.context;version="[3.0.0.RELEASE, 4.0.0)"',
	("org.springframework.context.support-${springVersion}.jar".toString()) : 'bundle:org.springframework.context.support;version="[3.0.0.RELEASE, 4.0.0)"',
	("org.springframework.core-${springVersion}.jar".toString()) : 'bundle:org.springframework.core;version="[3.0.0.RELEASE, 4.0.0)"',
	("org.springframework.expression-${springVersion}.jar".toString()) : 'bundle:org.springframework.expression;version="[3.0.0.RELEASE, 4.0.0)"',
	("org.springframework.instrument-${springVersion}.jar".toString()) : 'bundle:org.springframework.instrument;version="[3.0.0.RELEASE, 4.0.0)"',
	("org.springframework.jdbc-${springVersion}.jar".toString()) : 'bundle:org.springframework.jdbc;version="[3.0.0.RELEASE, 4.0.0)"',
	("org.springframework.jms-${springVersion}.jar".toString()) : 'bundle:org.springframework.jms;version="[3.0.0.RELEASE, 4.0.0)"',
//	("org.springframework.orm-${springVersion}.jar".toString()) : 'bundle:org.springframework.orm;version="[3.0.0.RELEASE, 4.0.0)"',
	("org.springframework.oxm-${springVersion}.jar".toString()) : 'bundle:org.springframework.oxm;version="[3.0.0.RELEASE, 4.0.0)"',
	("org.springframework.transaction-${springVersion}.jar".toString()) : 'bundle:org.springframework.transaction;version="[3.0.0.RELEASE, 4.0.0)"',
	("org.springframework.web-${springVersion}.jar".toString()) : 'bundle:org.springframework.web;version="[3.0.0.RELEASE, 4.0.0)"',
	("org.springframework.web.servlet-${springVersion}.jar".toString()) : 'bundle:org.springframework.web.servlet;version="[3.0.0.RELEASE, 4.0.0)"',
	("org.springframework.web.portlet-${springVersion}.jar".toString()) : 'bundle:org.springframework.web.portlet;version="[3.0.0.RELEASE, 4.0.0)"',
    
]

def persistenceModulesConfig = [
	'hibernate-annotations-3.4.0.GA.jar': '', // this is a fragment to the core hibernate module... 'bundle:com.springsource.org.hibernate.annotations;version="[3.4.0,3.5.0)"',
	'hibernate-commons-annotations-3.3.0.ga.jar': 'bundle:com.springsource.org.hibernate.annotations.common;version="[3.3.0,3.4.0)"',
	'hibernate-commons-annotations-3.1.0.GA.jar': 'bundle:com.springsource.org.hibernate.annotations.common;version="[3.3.0,3.4.0)"',
	'hibernate-core-3.3.1.GA.jar': 'bundle:com.springsource.org.hibernate;version="[3.3.1,3.4.0)"',
	'hibernate-ehcache-3.3.1.GA.jar': '', // this is a fragment to the core hibernate module... 'bundle:com.springsource.org.hibernate.cache;version="[3.3.0,3.5.0)",com.springsource.net.sf.ehcache;version="[1.6.2,1.8.0)"',

	("org.springframework.orm-${springVersion}.jar".toString()) : 'bundle:org.springframework.orm;version="[3.0.0.RELEASE, 4.0.0)"',

	'ejb3-persistence-1.0.2.GA.jar': 'bundle:com.springsource.javax.persistence;version="[1.0.0,2.0.0)"',
]

def groovyModulesConfig = [
	'groovy-all-1.6.7.jar': 'bundle:groovy-all;version="1.6.7"',
	'groovy-all-1.7.3.jar': 'bundle:groovy-all;version="1.7.3"',
]

def grailsModulesConfig = [
	'grails-bootstrap-1.2.0.jar': '',
//	'grails-core-1.2.0.jar': 'bundle:org.grails.osgi;version="[1.2.0,2.0.0)"', // bundle contains core and bootstrap
//	'grails-crud-1.2.0.jar': 'bundle:org.grails.crud;version="[1.2.0,2.0.0)"',
////	'grails-docs-1.2.0.jar': 'bundle:org.grails.docs;version="[1.2.0,2.0.0)"',
//	'grails-gorm-1.2.0.jar': 'bundle:org.grails.gorm;version="[1.2.0,2.0.0)"',
//	'grails-resources-1.2.0.jar': 'bundle:org.grails.resources;version="[1.2.0,2.0.0)"',
//	'grails-spring-1.2.0.jar': 'bundle:org.grails.spring;version="[1.2.0,2.0.0)"',
//	'grails-web-1.2.0.jar': 'bundle:org.grails.web;version="[1.2.0,2.0.0)"',
	
	'grails-bootstrap-1.2.1.jar': '',
//	'grails-core-1.2.1.jar': 'bundle:org.grails.osgi;version="[1.2.0,2.0.0)"', // bundle contains core and bootstrap
//	'grails-crud-1.2.1.jar': 'bundle:org.grails.crud;version="[1.2.0,2.0.0)"',
////	'grails-docs-1.2.1.jar': 'bundle:org.grails.docs;version="[1.2.0,2.0.0)"',
//	'grails-gorm-1.2.1.jar': 'bundle:org.grails.gorm;version="[1.2.0,2.0.0)"',
//	'grails-resources-1.2.1.jar': 'bundle:org.grails.resources;version="[1.2.0,2.0.0)"',
//	'grails-spring-1.2.1.jar': 'bundle:org.grails.spring;version="[1.2.0,2.0.0)"',
//	'grails-web-1.2.1.jar': 'bundle:org.grails.web;version="[1.2.0,2.0.0)"',
]

def miscGrailsModulesConfig = [
    //'ehcache-core-1.7.1.jar': '',
    
    //'facebook-java-api-2.0.4.jar': '',
    //'facebook-java-api-schema-2.0.4.jar': '',

    //'hsqldb-1.8.0.10.jar': '',
    //'htmlparser-1.6.jar': '',
    //'icu4j-3.4.4.jar': '',
    //'java-openid-sxip-0.9.4-rebuilt.jar': '',
    //'json-20070829.jar': '',

    //'jug-1.1.2.jar': '',
    //'nekohtml-1.9.12.jar': '',
    //'openxri-client-1.0.1.jar': '',
    //'openxri-syntax-1.0.1.jar': '',

	//'recaptcha4j-0.0.7.jar': '',
	//'shiro-all-1.0-incubating-SNAPSHOT.jar': '',
    'sitemesh-2.4.jar': 'bundle:com.springsource.com.opensymphony.sitemesh;version="[2.4.0, 2.5.0)"',
]

def blockedModulesConfig = [
	// Equinox and OSGi framework
	'org.eclipse.osgi-3.6.0.v20100517.jar': '',
	'org.osgi.compendium-4.2.0.jar': '',

	// tools (bnd, PAX)
	'bndlib-0.0.357.jar': '',
	'pax-swissbox-bnd-1.2.0.jar': '',
	'pax-swissbox-optional-jcl-1.2.0.jar': '',
	'pax-swissbox-tinybundles-1.2.0.jar': '',
	'ops4j-base-io-1.2.1.jar': '',
	'ops4j-base-lang-1.2.1.jar': '',
	'ops4j-base-store-1.2.1.jar': '',

	// Spring DM
	'spring-osgi-core-2.0.0.M1.jar': '',
	'spring-osgi-io-2.0.0.M1.jar': '',
]

def tomcatModulesConfig = [
	// Tomcat
	'catalina-ant.jar': '',
	'jasper-jdt.jar': '',
	'tomcat-core.jar': '',
	'tomcat-dbcp.jar': '',
	'tomcat-jasper.jar': '',
	'tomcat-juli-adapters.jar': '',
	'tomcat-juli.jar': '',
]

def packagingMode = BuildSettingsHolder.getSettings()?.config?.grails?.plugins?.osgi?.packagingMode
if (!packagingMode) {
	packagingMode = 'modular'
}

eventCreateWarStart = { warName, stagingDir -> 
	def moduleConfig = [:]
	
	
	

	switch (packagingMode) {
	case 'modular':
		// replace some libs by importing bundles or packages
		moduleConfig << blockedModulesConfig
		moduleConfig << tomcatModulesConfig
		moduleConfig << commonModulesConfig
		moduleConfig << springModulesConfig
		moduleConfig << persistenceModulesConfig
		moduleConfig << groovyModulesConfig
		moduleConfig << grailsModulesConfig
		moduleConfig << miscGrailsModulesConfig
		break
	case 'basic':
		// we put everything but Spring and its dependencies into the bundled war file
		moduleConfig << blockedModulesConfig
		moduleConfig << tomcatModulesConfig
		moduleConfig << commonModulesConfig
		moduleConfig << springModulesConfig
		break
	case 'monolithic':
		// we put everything into the bundled war file
		moduleConfig << blockedModulesConfig
		break
	default:
		println "unknown packaging mode: $packagingMode"
		break
	}

	println "packaging application $grailsAppName as $packagingMode bundle"

	String metaInfo = "$stagingDir/META-INF" 
	ant.mkdir(dir:metaInfo) 
	String manifestFile = "$metaInfo/MANIFEST.MF" 
	ant.manifest(file:manifestFile) { 
		// OSGi bundle headers
		attribute(name:"Bundle-ManifestVersion",value:"2")
		attribute(name:"Bundle-Name",value:"${grailsAppName}")
		attribute(name:"Bundle-SymbolicName",value:"${grailsAppName}")
		attribute(name:"Bundle-Version",value:"${metadata.getApplicationVersion()}")
		// add all jars in WEB-INF/lib
		def libDir = new File("${stagingDir}/WEB-INF/lib")
		def libs = []
		libDir.eachFileMatch(~/.*\.jar/) { libs << it.name }
		
		// "." must be first element of bundle class path
		// in order to support resource loading
		def classPathEntries = [ ".", "WEB-INF/classes" ]
		def importedBundleList = []
		def exportedPackageList = []
		def importedPackageList = [
				// web upport
				"javax.servlet;$servletImportSpecs",
				"javax.servlet.http;$servletImportSpecs",
				"javax.servlet.resources;$servletImportSpecs",
				"javax.servlet.jsp;$jspImportSpecs",
				"javax.servlet.jsp.el;$jspImportSpecs",
				"javax.servlet.jsp.jstl.core;$jstlImportSpecs",
				"javax.servlet.jsp.jstl.fmt;$jstlImportSpecs",
				"javax.servlet.jsp.jstl.sql;$jstlImportSpecs",
				"javax.servlet.jsp.jstl.tlv;$jstlImportSpecs",
				"javax.servlet.jsp.tagext;$jspImportSpecs",
				"javax.servlet.jsp.resources;$jspImportSpecs",
				// XML parsing
				"javax.xml.parsers",
				"org.w3c.dom",
				"org.xml.sax",
				"org.xml.sax.ext",
				"org.xml.sax.helpers",
				"org.apache.xml.security;$optional",
				"org.apache.xml.security.exceptions;$optional",
				// OSGi related
				"org.osgi.framework;$osgiImportSpecs;$optional",
				// Spring OSGi related
				"org.springframework.context;$springImportSpecs",
				"org.springframework.web.context;$springImportSpecs",
				"org.springframework.osgi.context;$springDMImportSpecs",
				"org.springframework.osgi.web.context.support;$springDMImportSpecs",
				// logging
				"org.apache.log4j",
				"org.apache.log4j.xml",
				"org.apache.log4j.helpers",
				"org.apache.log4j.jdbc;$optional",
				"org.apache.log4j.varia;$optional",
				]
		
		// TODO make configurable
		def includeJars = false
		if (includeJars) {
			libs.each { lib ->
				classPathEntries << "WEB-INF/lib/${lib}"
			}
		}
		else {
			def replacedLibs = []
			libs.each { lib ->
				// check whether we have a replacement bundle for a lib 
				def replacement = moduleConfig.get(lib)
				if (replacement != null) {
					if (replacement.size() == 0) {
						// empty replacement -> skip jar
						
						// TODO remove println
						println "skipping $lib"
					}
					else if (replacement.toString().startsWith('bundle:')) {
						replacement = replacement.substring('bundle:'.length())
						// TODO remove println
						println "importing bundle $replacement instead of $lib"

						importedBundleList << replacement
					}
					else if (replacement.toString().startsWith('package:')) {
						replacement = replacement.substring('package:'.length())

						// TODO remove println
						println "importing package $replacement instead of $lib"

						importedPackageList << replacement
					}
					else if (replacement.toString().startsWith('export:')) {
						replacement = replacement.substring('export:'.length())

						// TODO remove println
						println "exporting package $replacement"

						exportedPackageList << replacement
					}
					else {
						// TODO remove println
						println "undefined replacement for $lib: $replacement"
					}
					
					replacedLibs << lib 
				}
				else {
					classPathEntries << "WEB-INF/lib/${lib}"
				}
			}
			
			replacedLibs.each { lib ->
				// TODO remove println
				println "removing replaced lib $lib"
				
				new File(libDir, lib)?.delete()
			}
		}
		
		def classPath = classPathEntries.join(',')
		if (classPath) {
			attribute(name:"Bundle-ClassPath",value:"${classPath}")
		}
		
		def importedPackages = importedPackageList.join(',')
		if (importedPackages) {
			attribute(name:"Import-Package", value:"${importedPackages}")
		}

		def exportedPackages = exportedPackageList.join(',')
		if (exportedPackages) {
			attribute(name:"Export-Package", value:"${exportedPackages}")
		}

		def importedBundles = importedBundleList.join(',')
		if (importedBundles) {
			attribute(name:"Require-Bundle", value:"${importedBundles}")
		}

		attribute(name:"Webapp-Context",value:"${grailsAppName}")
	}
	
	// here we create spring config files defining namespace handlers
	println "creating surrogate spring.handlers and spring.schemas"
	// this works, as the Spring bundles are referenced in header Require-Bundle
	// TODO remove this hack
	// the correct way is to lookup the namespace handler provider service from
	// the OSGi service registry and hand it to BeanBuilder
	File metaInfoDir = new File(metaInfo)
	new File(metaInfoDir, "spring.handlers").withWriter { out ->
		out << """# default spring handlers
# org.springframework.aop
http\\://www.springframework.org/schema/aop=org.springframework.aop.config.AopNamespaceHandler

# org.springframework.beans
http\\://www.springframework.org/schema/p=org.springframework.beans.factory.xml.SimplePropertyNamespaceHandler
http\\://www.springframework.org/schema/util=org.springframework.beans.factory.xml.UtilNamespaceHandler

# org.springframework.context
http\\://www.springframework.org/schema/context=org.springframework.context.config.ContextNamespaceHandler
http\\://www.springframework.org/schema/jee=org.springframework.ejb.config.JeeNamespaceHandler
http\\://www.springframework.org/schema/lang=org.springframework.scripting.config.LangNamespaceHandler
http\\://www.springframework.org/schema/task=org.springframework.scheduling.config.TaskNamespaceHandler

# org.springframework.jdbc
http\\://www.springframework.org/schema/jdbc=org.springframework.jdbc.config.JdbcNamespaceHandler
		
# org.springframework.jms
http\\://www.springframework.org/schema/jms=org.springframework.jms.config.JmsNamespaceHandler

# org.springframework.orm
caution: filename not matched:  META-INF/spring.handlers

# org.springframework.oxm
http\\://www.springframework.org/schema/oxm=org.springframework.oxm.config.OxmNamespaceHandler
		
# org.springframework.transaction
http\\://www.springframework.org/schema/tx=org.springframework.transaction.config.TxNamespaceHandler

# org.springframework.web.servlet
http\\://www.springframework.org/schema/mvc=org.springframework.web.servlet.config.MvcNamespaceHandler
"""
	}
	
	new File(metaInfoDir, "spring.schemas").withWriter { out ->
	out << """# default spring schema locations
# org.springframework.aop
http\\://www.springframework.org/schema/aop/spring-aop-2.0.xsd=org/springframework/aop/config/spring-aop-2.0.xsd
http\\://www.springframework.org/schema/aop/spring-aop-2.5.xsd=org/springframework/aop/config/spring-aop-2.5.xsd
http\\://www.springframework.org/schema/aop/spring-aop-3.0.xsd=org/springframework/aop/config/spring-aop-3.0.xsd
http\\://www.springframework.org/schema/aop/spring-aop.xsd=org/springframework/aop/config/spring-aop-3.0.xsd

# org.springframework.beans
http\\://www.springframework.org/schema/beans/spring-beans-2.0.xsd=org/springframework/beans/factory/xml/spring-beans-2.0.xsd
http\\://www.springframework.org/schema/beans/spring-beans-2.5.xsd=org/springframework/beans/factory/xml/spring-beans-2.5.xsd
http\\://www.springframework.org/schema/beans/spring-beans-3.0.xsd=org/springframework/beans/factory/xml/spring-beans-3.0.xsd
http\\://www.springframework.org/schema/beans/spring-beans.xsd=org/springframework/beans/factory/xml/spring-beans-3.0.xsd
http\\://www.springframework.org/schema/tool/spring-tool-2.0.xsd=org/springframework/beans/factory/xml/spring-tool-2.0.xsd
http\\://www.springframework.org/schema/tool/spring-tool-2.5.xsd=org/springframework/beans/factory/xml/spring-tool-2.5.xsd
http\\://www.springframework.org/schema/tool/spring-tool-3.0.xsd=org/springframework/beans/factory/xml/spring-tool-3.0.xsd
http\\://www.springframework.org/schema/tool/spring-tool.xsd=org/springframework/beans/factory/xml/spring-tool-3.0.xsd
http\\://www.springframework.org/schema/util/spring-util-2.0.xsd=org/springframework/beans/factory/xml/spring-util-2.0.xsd
http\\://www.springframework.org/schema/util/spring-util-2.5.xsd=org/springframework/beans/factory/xml/spring-util-2.5.xsd
http\\://www.springframework.org/schema/util/spring-util-3.0.xsd=org/springframework/beans/factory/xml/spring-util-3.0.xsd
http\\://www.springframework.org/schema/util/spring-util.xsd=org/springframework/beans/factory/xml/spring-util-3.0.xsd

# org.springframework.context
http\\://www.springframework.org/schema/context/spring-context-2.5.xsd=org/springframework/context/config/spring-context-2.5.xsd
http\\://www.springframework.org/schema/context/spring-context-3.0.xsd=org/springframework/context/config/spring-context-3.0.xsd
http\\://www.springframework.org/schema/context/spring-context.xsd=org/springframework/context/config/spring-context-3.0.xsd
http\\://www.springframework.org/schema/jee/spring-jee-2.0.xsd=org/springframework/ejb/config/spring-jee-2.0.xsd
http\\://www.springframework.org/schema/jee/spring-jee-2.5.xsd=org/springframework/ejb/config/spring-jee-2.5.xsd
http\\://www.springframework.org/schema/jee/spring-jee-3.0.xsd=org/springframework/ejb/config/spring-jee-3.0.xsd
http\\://www.springframework.org/schema/jee/spring-jee.xsd=org/springframework/ejb/config/spring-jee-3.0.xsd
http\\://www.springframework.org/schema/lang/spring-lang-2.0.xsd=org/springframework/scripting/config/spring-lang-2.0.xsd
http\\://www.springframework.org/schema/lang/spring-lang-2.5.xsd=org/springframework/scripting/config/spring-lang-2.5.xsd
http\\://www.springframework.org/schema/lang/spring-lang-3.0.xsd=org/springframework/scripting/config/spring-lang-3.0.xsd
http\\://www.springframework.org/schema/lang/spring-lang.xsd=org/springframework/scripting/config/spring-lang-3.0.xsd
http\\://www.springframework.org/schema/task/spring-task-3.0.xsd=org/springframework/scheduling/config/spring-task-3.0.xsd
http\\://www.springframework.org/schema/task/spring-task.xsd=org/springframework/scheduling/config/spring-task-3.0.xsd

# org.springframework.jdbc
http\\://www.springframework.org/schema/jdbc/spring-jdbc-3.0.xsd=org/springframework/jdbc/config/spring-jdbc-3.0.xsd
http\\://www.springframework.org/schema/jdbc/spring-jdbc.xsd=org/springframework/jdbc/config/spring-jdbc-3.0.xsd
	
# org.springframework.jms
http\\://www.springframework.org/schema/jms/spring-jms-2.5.xsd=org/springframework/jms/config/spring-jms-2.5.xsd
http\\://www.springframework.org/schema/jms/spring-jms-3.0.xsd=org/springframework/jms/config/spring-jms-3.0.xsd
http\\://www.springframework.org/schema/jms/spring-jms.xsd=org/springframework/jms/config/spring-jms-3.0.xsd

# org.springframework.oxm
http\\://www.springframework.org/schema/oxm/spring-oxm-3.0.xsd=org/springframework/oxm/config/spring-oxm-3.0.xsd
http\\://www.springframework.org/schema/oxm/spring-oxm.xsd=org/springframework/oxm/config/spring-oxm-3.0.xsd
	
# org.springframework.transaction
http\\://www.springframework.org/schema/tx/spring-tx-2.0.xsd=org/springframework/transaction/config/spring-tx-2.0.xsd
http\\://www.springframework.org/schema/tx/spring-tx-2.5.xsd=org/springframework/transaction/config/spring-tx-2.5.xsd
http\\://www.springframework.org/schema/tx/spring-tx-3.0.xsd=org/springframework/transaction/config/spring-tx-3.0.xsd
http\\://www.springframework.org/schema/tx/spring-tx.xsd=org/springframework/transaction/config/spring-tx-3.0.xsd

# org.springframework.web.servlet
http\\://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd=org/springframework/web/servlet/config/spring-mvc-3.0.xsd
http\\://www.springframework.org/schema/mvc/spring-mvc.xsd=org/springframework/web/servlet/config/spring-mvc-3.0.xsd
"""
	}
}

// dummy event handler to get available properties
/*
eventPluginLoadEnd = {
	binding?.variables?.each { name, value ->
		try {
			println "############## prop $name: $value (${value?.getClass()?.name})"
		}
		catch (Throwable t) {
			// ignore
		}
	}
}
*/