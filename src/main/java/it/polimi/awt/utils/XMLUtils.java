package it.polimi.awt.utils;

import it.polimi.awt.domain.QueryType;
import it.polimi.awt.domain.Response;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLUtils {

	public static List<Response> loadXMLFromString(String xml, QueryType queryType) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		Document doc = builder.parse(is);
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("doc");

		System.out.println("----------------------------");

		List<Response> responseList = new LinkedList<Response>();

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				NodeList nl = (NodeList) eElement.getChildNodes();
				Response response = new Response(queryType);
				for (int i = 0; i < nl.getLength(); i++) {
					NamedNodeMap nnm = nl.item(i).getAttributes();
					for (int j = 0; j < nnm.getLength(); j++) {
						Node n = nnm.item(j);
						if (n.getTextContent().equals("name"))
							response.setName(nl.item(i).getTextContent());
						else if (n.getTextContent().equals("lat"))
							response.setLatitude(Double.parseDouble(nl.item(i).getTextContent()));
						else if (n.getTextContent().equals("lng"))
							response.setLongitude(Double.parseDouble(nl.item(i).getTextContent()));
					}
				}
				responseList.add(response);
			}
		}
		System.out.println("ResponseList= "+responseList.toString());
		return responseList;
	}
}