import groovy.xml.StreamingMarkupBuilder

File webXmlFile = new File('web.xml')
def webxml = new XmlSlurper().parse(webXmlFile)

def contextConfigLocationParam = webxml.'context-param'.find { node ->
	node.'param-name'.text() == 'contextConfigLocation'
}

println contextConfigLocationParam

if (contextConfigLocationParam) {
	println "found param"
	def currentText = contextConfigLocationParam.'param-value'.text()
	println "current text is '$currentText'"
	
	contextConfigLocationParam.'param-value'.replaceBody("$currentText /WEB-INF/*-osgi.xml")
}

webXmlFile.text = new StreamingMarkupBuilder().bind {
	mkp.declareNamespace("": "http://java.sun.com/xml/ns/j2ee")
	mkp.declareNamespace("xsi": "http://www.w3.org/2001/XMLSchema-instance")
	mkp.yield(webxml)
}

