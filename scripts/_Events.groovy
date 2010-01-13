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
		// TODO re-add "." to bundle class path?
		def classPathEntries = [ ".", "WEB-INF/classes" ] 
		if(includeJars) {
			libDir.eachFileMatch(~/.*\.jar/) { classPathEntries << "WEB-INF/lib/${it.name}" }
		}
		def classPath = classPathEntries.join(',')
		attribute(name:"Bundle-ClassPath",value:"${classPath}")
		def importedPackageList = [
		    // web upport
			"javax.servlet",
			"javax.servlet.http",
			"javax.servlet.resources",
			"javax.servlet.jsp;resolution:=optional",
			"javax.servlet.jsp.el;resolution:=optional",
			"javax.servlet.jsp.jstl;resolution:=optional",
			"javax.servlet.jsp.jstl.core;resolution:=optional",
			"javax.servlet.jsp.jstl.fmt;resolution:=optional",
			"javax.servlet.jsp.jstl.sql;resolution:=optional",
			"javax.servlet.jsp.jstl.tlv;resolution:=optional",
			"javax.servlet.jsp.tagext;resolution:=optional",
			"javax.servlet.jsp.resources;resolution:=optional",
			// XML parsing
			"javax.xml.parsers",
			"org.w3c.dom",
			"org.xml.sax",
			"org.xml.sax.ext",
			"org.xml.sax.helpers",
			// OSGi related
			"org.osgi.framework;resolution:=optional",
			// Spring OSGi related
			"org.springframework.context;resolution:=optional",
			"org.springframework.web.context;resolution:=optional",
			"org.springframework.osgi.context;resolution:=optional",
			"org.springframework.osgi.web.context.support;resolution:=optional",
		];
		def importedPackages = importedPackageList.join(',')
		attribute(name:"Import-Package", value:"${importedPackages}")
		attribute(name:"Webapp-Context",value:"${grailsAppName}")
	} 
}