package exApiGdrive.quickstart;

import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
	
	public Document getDrawioFileGenerator() {
		 DrawioFileGenerator drawioFileGenerator = new DrawioFileGenerator();
		return drawioFileGenerator.getDocument();
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
