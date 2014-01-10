includeTargets << grailsScript("_GrailsInit")

eventCreateWarStart = { warName, stagingDir ->
    ant.copy(todir: "$stagingDir/META-INF", file: "web-app/META-INF/MANIFEST.MF", overwrite: true)
}

eventWebXmlEnd = { String filename ->
          
     	def webxml = webXmlFile
     	def newxml = new File(webxml.path + ".bak")
     	def root = new XmlParser().parse(webxml)

         	// add the jdbc/mydatasource resource reference to the web.xml
          
     	def resourceRef = root.appendNode('resource-ref')
     	resourceRef.appendNode('description','The SAP NetWeaver JNDI Database resource')
     	resourceRef.appendNode('res-ref-name','jdbc/DefaultDB')
     	resourceRef.appendNode('res-type','javax.sql.DataSource')

     	def writer = new StringWriter()
     	new XmlNodePrinter(new PrintWriter(writer)).print(root)
     	newxml.withWriter { out ->
          		out.writeLine(writer.toString())
     	}
     	webxml.delete()
     	newxml.renameTo(webxml.path)
}