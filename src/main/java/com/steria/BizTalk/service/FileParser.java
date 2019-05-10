package com.steria.BizTalk.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.steria.BizTalk.Repository.BizDatabase;

public class FileParser {

	private String filePath;

	public FileParser(String path) {
		this.filePath = path;
	}

	public static void main(String arg[]) throws ParserConfigurationException, SAXException, IOException {

		FileParser parser = new FileParser("D:\\Projects\\BizTalk\\src\\main\\resources\\static\\GHS_MIC.xml");
		parser.start();
	}

	public void start() throws ParserConfigurationException, SAXException, IOException {

		File file = new File(this.filePath);
		FileInputStream stream = new FileInputStream(file);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(stream);

		NodeList journey = document.getElementsByTagName("journey");
		for (int i = 0; i < journey.getLength(); i++) {
			Node element = journey.item(i);
			NodeList sites = ((Element) element).getElementsByTagName("site");
			for (int j = 0; j < sites.getLength(); j++) {
				Node site = sites.item(j);
				Element siteElement = (Element) site;
				HashMap<String, Object> dbDoc = new HashMap<String, Object>();
				dbDoc.put("nodeid", siteElement.getAttribute("id"));
				dbDoc.put("pta", siteElement.getElementsByTagName("pta").item(0).getTextContent());
				dbDoc.put("ptd", siteElement.getElementsByTagName("ptd").item(0).getTextContent());
				dbDoc.put("callSequence", siteElement.getElementsByTagName("callSequence").item(0).getTextContent());
				
				Object siteInfo = getAllNodesInMap((Element) siteElement.getElementsByTagName("siteInfo").item(0));
				if(siteInfo != null){
					dbDoc.put("siteinfo", siteInfo);
				}
				
				Object consignment = getAllNodesInMap((Element) siteElement.getElementsByTagName("consignment").item(0));
				if(consignment != null){
					dbDoc.put("consignment", consignment);
				}
			}
			BizDatabase.insertObj("journey", new org.bson.Document());
		}
		System.out.println("Finished");

	}

	private HashMap getAllNodesInMap(Element element) {
		if(element != null){
			
			HashMap<String, String> map = new HashMap<String, String>();
			NodeList nodes = element.getChildNodes();
			for (int k = 0; k < nodes.getLength(); k++) {
				Node node = nodes.item(k);
				String name = node.getNodeName();
				if (name != "#text") {
					map.put(name, node.getTextContent());
				}
			}
			
			return map;
		}
		return null;
	}

}
