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

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;

import exApiGdrive.quickstart.model.Cell;

public class Jgraphix {

	public static void main(String[] args) {
		
		final String xmlFilePath = "C:\\Users\\tberezia\\xmlfile.drawio";
		
		try {
//			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
//			
//			DocumentBuilder documentBuilder;
//			documentBuilder = documentFactory.newDocumentBuilder();
//			Document document = documentBuilder.newDocument();
			
//			Document document = DrawioFileGenerator.getDrawioFileGenerator();
			DrawioFileGenerator dfg = new DrawioFileGenerator();
			dfg.CreateElement("monElement");
			dfg.CreateAttribut(dfg.getElement(),"modified", "2021-06-02T10:12:48.121Z");
//			Element root = document.createElement("mxfile");
//			document.appendChild(root);
//			
//			DrawioFileGenerator.CreateAttribut(document, root, "modified", "2021-06-02T10:12:48.121Z");
			
            
//            CreateAttribut(document, root, "modified", "2021-06-02T10:12:48.121Z");
//            CreateAttribut(document, root, "agent", "5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) draw.io/14.6.13 Chrome/89.0.4389.128 Electron/12.0.7 Safari/537.36" );
            
         // employee element
//            Element employee = document.createElement("employee");
// 
//            root.appendChild(employee);
            
         // set an attribute to staff element
//            Attr attr = document.createAttribute("id");
//            attr.setValue("10");
//            employee.setAttributeNode(attr);
            
            Cell cell = new Cell();
            
            for (Map.Entry mapentry : cell.getAttributClass().entrySet()) {
                System.out.println("clé: "+mapentry.getKey() 
                                   + " | valeur: " + mapentry.getValue());
             }
            
            
			
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(dfg.getDocument());
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
            
            transformer.transform(domSource, streamResult);
            
            System.out.println("Done creating XML File");
            
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		double x=400;
		double y=500;
		double width=150;
		double height = 100;
		
		mxCell mxCell = new mxCell();
		mxCell.setId("213321321321L");
		mxCell.setGeometry(new mxGeometry(x,y,width, height));
		
		mxGraphModel graphModel = new mxGraphModel();
		
		graphModel.add(mxCell, mxCell, 1);
		
		System.out.println(mxCell.toString());
		System.out.println(graphModel.toString());
		
		String indent ="";
		mxCell cell = mxCell;
		displayModel(mxCell, "");
		
//		 System.out.println(indent+cell.getValue()+"("+cell.getClass().getName()+")");
//	      int nbChilds = cell.getChildCount();
//	      indent = indent + "  ";
//	      for (int i=0; i<nbChilds ; i++) {
//	        displayModel((mxCell) cell.getChildAt(i), indent);
//	      }
		
	}
	
	 private static void displayModel(mxCell cell, String indent) {
	      System.out.println(indent+cell.getValue()+"("+cell.getClass().getName()+")");
	      int nbChilds = cell.getChildCount();
	      indent = indent + "  ";
	      for (int i=0; i<nbChilds ; i++) {
	        displayModel((mxCell) cell.getChildAt(i), indent);
	      }
	   }
	 
	 
}
