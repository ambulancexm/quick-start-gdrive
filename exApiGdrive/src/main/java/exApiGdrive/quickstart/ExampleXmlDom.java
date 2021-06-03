package exApiGdrive.quickstart;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ExampleXmlDom {

	 public static final String xmlFilePath = "C:\\Users\\tberezia\\xmlexfile.drawio";
	 
	    public static void main(String argv[]) {
	 
	        try {
	 
//	            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
//	 
//	            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
//	 
//	            Document document = documentBuilder.newDocument();
	        	
	        	DrawioFileGenerator dfg = new DrawioFileGenerator();
	        	Document document = dfg.getDocument();
	 
	            // mxfile element
	            Element mxfile = document.createElement("mxfile");
	            document.appendChild(mxfile);
	 
	            // diagram element
	            Element diagram = document.createElement("diagram");
	            mxfile.appendChild(diagram);
	            Element mxGraphModel = document.createElement("mxGraphModel");
	            diagram.appendChild(mxGraphModel);
	            Element root = document.createElement("root");
	            mxGraphModel.appendChild(root);
	            Element mxCell = document.createElement("mxCell");
	            root.appendChild(mxCell);
	            Element mxGeometry = document.createElement("mxGeometry");
	            mxCell.appendChild(mxGeometry);
	            
//	            mxfile.appendChild(diagram);
	            
	            Map<String, String> mapMxfile = new HashMap<String, String>();
	            mapMxfile.put("host","Electron");
	            mapMxfile.put("modified","2021-06-02T10:12:48.121Z");
	            mapMxfile.put("agent","5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) draw.io/14.6.13 Chrome/89.0.4389.128 Electron/12.0.7 Safari/537.36");
	            mapMxfile.put("etag","0oRR0zHuw8bgb1IybAXD");
	            mapMxfile.put("compressed","false");
	            mapMxfile.put( "version","14.6.13");
	            mapMxfile.put("type","device");
	            mapMxfile.put("pages", "3");
	            
//	             version="14.6.13"  pages="3"
	            // set an attribute to staff element
	            Attr attr; 
	            for (Map.Entry mapentry : mapMxfile.entrySet()) {
	            	
	            	attr= document.createAttribute(mapentry.getKey().toString());
	            	attr.setValue(mapentry.getValue().toString());
	            	mxfile.setAttributeNode(attr);
	            	
	                System.out.println("clé: "+mapentry.getKey() 
	                                   + " | valeur: " + mapentry.getValue());
	             }
	 
	            //you can also use staff.setAttribute("id", "1") for this
	 
	            // firstname element
//	            Element firstName = document.createElement("firstname");
//	            firstName.appendChild(document.createTextNode("James"));
//	            diagram.appendChild(firstName);
//	 
//	            // lastname element
//	            Element lastname = document.createElement("lastname");
//	            lastname.appendChild(document.createTextNode("Harley"));
//	            diagram.appendChild(lastname);
//	 
//	            // email element
//	            Element email = document.createElement("email");
//	            email.appendChild(document.createTextNode("james@example.org"));
//	            diagram.appendChild(email);
//	 
//	            // department elements
//	            Element department = document.createElement("department");
//	            department.appendChild(document.createTextNode("Human Resources"));
//	            diagram.appendChild(department);
	 
	            // create the xml file
	            //transform the DOM Object to an XML File
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource domSource = new DOMSource(document);
	            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
	 
	            // If you use
	            // StreamResult result = new StreamResult(System.out);
	            // the output will be pushed to the standard output ...
	            // You can use that for debugging 
	 
	            transformer.transform(domSource, streamResult);
	 
	            System.out.println("Done creating XML File");
	 
	        } catch (TransformerException tfe) {
	            tfe.printStackTrace();
	        }
	    }
}
