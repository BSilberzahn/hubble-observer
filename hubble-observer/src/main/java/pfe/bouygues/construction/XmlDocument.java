package pfe.bouygues.construction;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDocument {

	private final static Logger logger = LoggerFactory.getLogger(DbConnection.class); 
	
	private static HashMap<Integer, ControlFile> files;

	private XmlDocument()
	{
		try {
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = factory.newDocumentBuilder();
			XmlDocument.files = this.extractFiles(builder.parse(new File("fiches/fiches.xml")));
		} catch (Exception e) {
			logger.error("Can't open the xml", e);
		}
	}
	
	public static HashMap<Integer, ControlFile> getDocument(){
		if(XmlDocument.files == null)
		{
			new XmlDocument();
		}
		return XmlDocument.files;
	}   
	
	private HashMap<Integer, ControlFile> extractFiles(Document doc){
		NodeList list = doc.getElementsByTagName("fiche");
		HashMap<Integer, ControlFile> map = new HashMap<Integer, ControlFile>();
		for(int i = 0; i < list.getLength(); i++){
			Node n = list.item(i);
			int number = Integer.parseInt(n.getAttributes().getNamedItem("num").getTextContent());
			String path = n.getAttributes().getNamedItem("fichier").getTextContent();
			String name = null;
			String location = null;
			String batch = null;
			String marker = null;
			String comment = null;
			String domain = null;
			int ofset = 0;
			String service = null;
			String eventLocation = null;
			for(int j = 0; j < n.getChildNodes().getLength(); j++){
				if(n.getChildNodes().item(j).getNodeName().equals("objet"))
					name = n.getChildNodes().item(j).getTextContent();
				if(n.getChildNodes().item(j).getNodeName().equals("delai"))
					ofset = Integer.parseInt(n.getChildNodes().item(j).getTextContent());
				if(n.getChildNodes().item(j).getNodeName().equals("localisation"))
					location = n.getChildNodes().item(j).getTextContent();
				if(n.getChildNodes().item(j).getNodeName().equals("lot"))
					batch = n.getChildNodes().item(j).getTextContent();
				if(n.getChildNodes().item(j).getNodeName().equals("jalon"))
					marker = n.getChildNodes().item(j).getTextContent();
				if(n.getChildNodes().item(j).getNodeName().equals("remarque"))
					comment = n.getChildNodes().item(j).getTextContent();
				if(n.getChildNodes().item(j).getNodeName().equals("domaine"))
					domain = n.getChildNodes().item(j).getTextContent();
				if(n.getChildNodes().item(j).getNodeName().equals("service"))
					service = n.getChildNodes().item(j).getTextContent();
				if(n.getChildNodes().item(j).getNodeName().equals("localisation"))
					eventLocation = n.getChildNodes().item(j).getTextContent();
			}
			
			
			ControlFile cf = new ControlFile(number, name, path);
			cf.setOfset(ofset);
			cf.setLocation(location);
			cf.setBatch(batch);
			cf.setMarker(marker);
			cf.setComment(comment);
			cf.setDomain(domain);
			cf.setService(service);
			cf.setEventLocation(eventLocation);
			map.put(number, cf);
		}
		return map;
	}
}
