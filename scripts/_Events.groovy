// TODO move all of this into _Osgi.groovy

def osgiImportSpecs = "version=\"[4.1.0, 5.0.0)\""
def servletImportSpecs = "version=\"[2.4.0, 2.6.0)\""
def jspImportSpecs = "$servletImportSpecs;resolution:=optional"
def springImportSpecs = "version=\"[3.0.0, 4.0.0)\""
def springDMImportSpecs = "version=\"[2.0.0, 3.0.0)\";resolution:=optional"

def libToBundleImport = [
    'activation-1.1.jar': 'package:javax.activation;version="1.1.1"',
    'activation.jar': '',
    'antlr-2.7.6.jar': 'bundle:com.springsource.antlr;version="[2.7.7,2.7.7]"',
    'aopalliance-1.0.jar': 'package:org.aopalliance.aop;version="1.0.0",org.aopalliance.intercept;version="1.0.0"', //bundle:com.springsource.org.aopalliance;version="[3.0.0.RELEASE, 4.0.0)"
	//'aspectjrt-1.6.6.jar': '',
    //'aspectjweaver-1.6.6.jar': '',
    //'catalina-ant.jar': '',
    'cglib-nodep-2.1_3.jar': 'bundle:com.springsource.net.sf.cglib;version="[2.1.3,2.2.0)',
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
    'dom4j-1.6.1.jar': 'bundle:com.springsource.org.dom4j;version="1.6.1"',
    //'ehcache-core-1.7.1.jar': '',
    'ejb3-persistence-1.0.2.GA.jar': 'bundle:com.springsource.javax.persistence;version="[1.0.0,2.0.0)',
    //'facebook-java-api-2.0.4.jar': '',
    //'facebook-java-api-schema-2.0.4.jar': '',
    'grails-bootstrap-1.2.0.jar': '',
    'grails-core-1.2.0.jar': 'bundle:org.grails.osgi;version="[1.2.0,2.0.0)"', // bundle contains core and bootstrap
    'grails-crud-1.2.0.jar': 'bundle:org.grails.crud;version="[1.2.0,2.0.0)"',
    'grails-docs-1.2.0.jar': 'bundle:org.grails.docs;version="[1.2.0,2.0.0)"',
    'grails-gorm-1.2.0.jar': 'bundle:org.grails.gorm;version="[1.2.0,2.0.0)"',
    'grails-resources-1.2.0.jar': 'bundle:org.grails.resources;version="[1.2.0,2.0.0)"',
    'grails-spring-1.2.0.jar': 'bundle:org.grails.spring;version="[1.2.0,2.0.0)"',
    'grails-web-1.2.0.jar': 'bundle:org.grails.web;version="[1.2.0,2.0.0)"',
    'groovy-all-1.6.7.jar': 'bundle:groovy-all;version="1.6.7"',
    'hibernate-annotations-3.4.0.GA.jar': 'bundle:com.springsource.org.hibernate.annotations;version="[3.4.0,3.5.0)"',
    'hibernate-commons-annotations-3.3.0.ga.jar': 'bundle:com.springsource.org.hibernate.annotations.common;version="[3.3.0,3.4.0)"',
    'hibernate-core-3.3.1.GA.jar': 'bundle:com.springsource.org.hibernate.;version="[3.3.1,3.4.0)"',
    //'hibernate-ehcache-3.3.1.GA.jar': 'bundle:com.springsource.org.hibernate.;version="[3.4.0,3.5.0)"',
    //'hsqldb-1.8.0.10.jar': '',
    //'htmlparser-1.6.jar': '',
    //'icu4j-3.4.4.jar': '',
    //'jasper-jdt.jar': '',
    //'java-openid-sxip-0.9.4-rebuilt.jar': '',
    'javassist-3.8.0.GA.jar': 'bundle:com.springsource.javassist;version="[3.9.0, 4.0.0)',
    //'jaxb-api-2.1.jar': '',
    //'jaxb-impl-2.1.3.jar': '',
    'jcl-over-slf4j-1.5.8.jar': 'package:org.apache.commons.logging;version="[1.1.1, 1.2.0)"',
    //'json-20070829.jar': '',
    //'jta-1.1.jar': '',
    //'jug-1.1.2.jar': '',
    'jul-to-slf4j-1.5.8.jar': '',
    'log4j-1.2.15.jar': '',
    'mail.jar': 'bundle:com.springsource.javax.mail;version="1.4.1"',
    //'nekohtml-1.9.12.jar': '',
    //'openxri-client-1.0.1.jar': '',
    //'openxri-syntax-1.0.1.jar': '',
    'org.springframework.aop-3.0.0.RELEASE.jar': 'bundle:org.springframework.aop;version="[3.0.0.RELEASE, 4.0.0)"',
    'org.springframework.asm-3.0.0.RELEASE.jar': 'bundle:org.springframework.asm;version="[3.0.0.RELEASE, 4.0.0)"',
    'org.springframework.aspects-3.0.0.RELEASE.jar': 'bundle:org.springframework.aspects;version="[3.0.0.RELEASE, 4.0.0)"',
    'org.springframework.beans-3.0.0.RELEASE.jar': 'bundle:org.springframework.beans;version="[3.0.0.RELEASE, 4.0.0)"',
    'org.springframework.context-3.0.0.RELEASE.jar': 'bundle:org.springframework.context;version="[3.0.0.RELEASE, 4.0.0)"',
    'org.springframework.context.support-3.0.0.RELEASE.jar': 'bundle:org.springframework.context.support;version="[3.0.0.RELEASE, 4.0.0)"',
    'org.springframework.core-3.0.0.RELEASE.jar': 'bundle:org.springframework.core;version="[3.0.0.RELEASE, 4.0.0)"',
    'org.springframework.expression-3.0.0.RELEASE.jar': 'bundle:org.springframework.expression;version="[3.0.0.RELEASE, 4.0.0)"',
    'org.springframework.instrument-3.0.0.RELEASE.jar': 'org.springframework.instrument;version="[3.0.0.RELEASE, 4.0.0)"',
    'org.springframework.jdbc-3.0.0.RELEASE.jar': 'bundle:org.springframework.jdbc;version="[3.0.0.RELEASE, 4.0.0)"',
    'org.springframework.jms-3.0.0.RELEASE.jar': 'bundle:org.springframework.jms;version="[3.0.0.RELEASE, 4.0.0)"',
    'org.springframework.orm-3.0.0.RELEASE.jar': 'bundle:org.springframework.orm;version="[3.0.0.RELEASE, 4.0.0)"',
    'org.springframework.oxm-3.0.0.RELEASE.jar': 'bundle:org.springframework.oxm;version="[3.0.0.RELEASE, 4.0.0)"',
    'org.springframework.transaction-3.0.0.RELEASE.jar': 'bundle:org.springframework.transaction;version="[3.0.0.RELEASE, 4.0.0)"',
    'org.springframework.web-3.0.0.RELEASE.jar': 'bundle:org.springframework.web;version="[3.0.0.RELEASE, 4.0.0)"',
    'org.springframework.web.servlet-3.0.0.RELEASE.jar': 'bundle:org.springframework.web.servlet;version="[3.0.0.RELEASE, 4.0.0)"',
	'org.springframework.web.portlet-3.0.0.RELEASE.jar': 'bundle:org.springframework.web.portlet;version="[3.0.0.RELEASE, 4.0.0)"',
	
    'oro-2.0.8.jar': 'bundle:com.springsource.org.apache.oro;version="[2.0.8, 2.1.0)"',
	//'recaptcha4j-0.0.7.jar': '',
	//'shiro-all-1.0-incubating-SNAPSHOT.jar': '',
    'sitemesh-2.4.jar': 'bundle:com.springsource.com.opensymphony.sitemesh;version="[2.4.0, 2.5.0)"',
    'slf4j-api-1.5.8.jar': 'package:org.slf4j;version="[1.5.6, 1.6.0)"',
    'slf4j-log4j12-1.5.8.jar': '',
    //'standard-1.1.2.jar': '',
    //'stax-api-1.0-2.jar': '',
    //'xalan-2.7.1.jar': '',
    //'xercesImpl-2.9.1.jar': '',
    //'xmlsec-1.4.2.jar': '',
    //'xpp3_min-1.1.3.4.O.jar': '',
]

eventCreateWarStart = { warName, stagingDir -> 
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
		def importedPackageList = [
				// web upport
				"javax.servlet;$servletImportSpecs",
				"javax.servlet.http;$servletImportSpecs",
				"javax.servlet.resources;$servletImportSpecs",
				"javax.servlet.jsp;$jspImportSpecs",
				"javax.servlet.jsp.el;$jspImportSpecs",
				"javax.servlet.jsp.jstl;$jspImportSpecs",
				"javax.servlet.jsp.jstl.core;$jspImportSpecs",
				"javax.servlet.jsp.jstl.fmt;$jspImportSpecs",
				"javax.servlet.jsp.jstl.sql;$jspImportSpecs",
				"javax.servlet.jsp.jstl.tlv;$jspImportSpecs",
				"javax.servlet.jsp.tagext;$jspImportSpecs",
				"javax.servlet.jsp.resources;$jspImportSpecs",
				// XML parsing
				"javax.xml.parsers",
				"org.w3c.dom",
				"org.xml.sax",
				"org.xml.sax.ext",
				"org.xml.sax.helpers",
				// OSGi related
				"org.osgi.framework;$osgiImportSpecs;resolution:=optional",
				// Spring OSGi related
				"org.springframework.context;$springImportSpecs",
				"org.springframework.web.context;$springImportSpecs",
				"org.springframework.osgi.context;$springDMImportSpecs",
				"org.springframework.osgi.web.context.support;$springDMImportSpecs",
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
				def replacement = libToBundleImport.get(lib)
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
		attribute(name:"Bundle-ClassPath",value:"${classPath}")
		
		def importedPackages = importedPackageList.join(',')
		attribute(name:"Import-Package", value:"${importedPackages}")
		def importedBundles = importedBundleList.join(',')
		attribute(name:"Import-Bundle", value:"${importedBundles}")
		attribute(name:"Webapp-Context",value:"${grailsAppName}")
	} 
}