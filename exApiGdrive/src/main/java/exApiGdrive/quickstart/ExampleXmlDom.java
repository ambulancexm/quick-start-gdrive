package exApiGdrive.quickstart;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.common.collect.Table.Cell;
import com.mxgraph.model.mxGeometry;

import exApiGdrive.quickstart.model.ChildGeometry;
import exApiGdrive.quickstart.model.MxCell;
import exApiGdrive.quickstart.model.MxFile;

public class ExampleXmlDom {

	 public static final String xmlFilePath = "C:\\Users\\tberezia\\xmlexfile.drawio";
	 
	    public static void main(String argv[]) {
	 
//	    	instanciation de la classe DrawioFileGenerator
//	    	et création du document
	        DrawioFileGenerator dfg = new DrawioFileGenerator();
			Document document = dfg.getDocument();
			
 
//			creation des éléments
			Element mxfile = document.createElement("mxfile");
			document.appendChild(mxfile);
 			Element diagram = document.createElement("diagram");
			mxfile.appendChild(diagram);
			Element mxGraphModel = document.createElement("mxGraphModel");
			diagram.appendChild(mxGraphModel);
			Element root = document.createElement("root");
			mxGraphModel.appendChild(root);
			
			
			Element mxCell = document.createElement("mxCell");
			root.appendChild(mxCell);
			root.appendChild(mxCell);
			root.appendChild(mxCell);
			root.appendChild(mxCell);
			
			Element mxGeometry = document.createElement("mxGeometry");
			mxCell.appendChild(mxGeometry);
			
			
//			ajout de valeur à la balise
			

			
			
			
//			creation de map d'attribut
			Map<String, String> mapMxfile = new HashMap<String, String>();
			mapMxfile.put("host","Electron");
			mapMxfile.put("modified","2021-06-02T10:12:48.121Z");
			mapMxfile.put("agent","5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) draw.io/14.6.13 Chrome/89.0.4389.128 Electron/12.0.7 Safari/537.36");
			mapMxfile.put("etag","0oRR0zHuw8bgb1IybAXD");
			mapMxfile.put("compressed","false");
			mapMxfile.put( "version","14.6.13");
			mapMxfile.put("type","device");
			mapMxfile.put("pages", "3");
//			MxFile file = new MxFile("Electron",
//					"2021-06-02T10:12:48.121Z",
//					"5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) draw.io/14.6.13 Chrome/89.0.4389.128 Electron/12.0.7 Safari/537.36",
//					"0oRR0zHuw8bgb1IybAXD",
//					"false",
//					"version","14.6.13",
//					"device",
//					3); 
			dfg.handleAttribut(mxfile, mapMxfile);
			MxCell mxCell2 = new MxCell("3eJQhlkAnYjeyvI7TBop-12",
					"ma valeur",
					"edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;",
					"3eJQhlkAnYjeyvI7TBop-4",
					"3eJQhlkAnYjeyvI7TBop-7",
					"3eJQhlkAnYjeyvI7TBop-9");
			MxCell mxCell3 = new MxCell("3eJQhlkAnYjeyvI7TBop-15",
					"SEND",
					"shape=mxgraph.bpmn.task;rectStyle=rounded;size=10;taskMarker=send;",
					"3eJQhlkAnYjeyvI7TBop-4",
					"3eJQhlkAnYjeyvI7TBop-7",
					"3eJQhlkAnYjeyvI7TBop-9");
//			dfg.handleAttribut(mxCell1, mxCell2.getAttributClass());
			dfg.handleAttribut(mxCell, mxCell3.getAttributClass());
			ChildGeometry childGeometry = new ChildGeometry(310, 70, 490, 220,"geometry");
			ChildGeometry childGeometry2 = new ChildGeometry(400, 70, 490, 220,"geometry");
			dfg.handleAttribut(mxGeometry, childGeometry.getAttributClass());
//			dfg.handleAttribut(mxGeometry1, childGeometry2.getAttributClass());
			dfg.buildXmlfile(document, xmlFilePath);

			System.out.println("Done creating XML File");
	    }
}
