package exApiGdrive.quickstart;

import java.io.File;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DrawioFileGenerator {

	DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
	
	DocumentBuilder documentBuilder;
	
	Document document;
	Element element;
	Map<Element, String> mapElement;
	
	public DrawioFileGenerator() {
		try {
			this.documentBuilder = documentFactory.newDocumentBuilder();
			this.document = documentBuilder.newDocument();
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void buildXmlfile(Document document, String xmlFilePath){
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer;
			transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlFilePath));
			transformer.transform(domSource, streamResult);
			
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Document getDrawioFileGenerator() {
		 DrawioFileGenerator drawioFileGenerator = new DrawioFileGenerator();
		return drawioFileGenerator.getDocument();
	}
	
	public void handleAttribut(Element element , Map<String, String> mapMxfile) {
		Attr attr; 
		for (Map.Entry mapentry : mapMxfile.entrySet()) {
			
			attr= document.createAttribute(mapentry.getKey().toString());
			attr.setValue(mapentry.getValue().toString());
			element.setAttributeNode(attr);
			
		    System.out.println("clé: "+mapentry.getKey() 
		                       + " | valeur: " + mapentry.getValue());
		 }
	}
	

	
	public void CreateAttribut (Element parent, String id, String value ) {
		Attr attr = document.createAttribute(id);
        attr.setValue(value);
        parent.setAttributeNode(attr);
	 }
	
	public void CreateElement(String name) {
		Element element = document.createElement("mxfile");
		document.appendChild(element);
	}
	
	
	
	public Document getDocument() {
		return document;
	}
	
	public void setDocument(Document document) {
		this.document = document;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}
	
	
}
