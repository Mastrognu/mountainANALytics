package it.polimi.awt.utils;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.QueryType;
import it.polimi.awt.domain.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLUtils {

	/**
	 * If XML main node is 'doc', use this method
	 * @param xml The string representation of the XML structure
	 * @param queryType The type of query to perform
	 * @return A list of responses with 'name', 'lat' and 'lng'
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static List<Response> parseFromGeolocalization(String xml, QueryType queryType) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		Document doc = builder.parse(is);
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("doc");

		List<Response> responseList = new ArrayList<Response>();

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				NodeList nl = nNode.getChildNodes();
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
		return responseList;
	}

	/**
	 * If XML main node is 'result', use this method
	 * @param xml The string representation of the XML structure
	 * @return A list of mountains with 'name', 'lat' and 'lng'
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static List<Mountain> parseFromGetNearby(String xml) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		Document doc = builder.parse(is);
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("result");

		List<Mountain> mountainList = new ArrayList<Mountain>();

		for(int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			Mountain mount = new Mountain();
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				mount.setName(element.getElementsByTagName("name").item(0).getTextContent());
				mount.setLatitude(Double.parseDouble(element.getElementsByTagName("lat").item(0).getTextContent()));
				mount.setLongitude(Double.parseDouble(element.getElementsByTagName("lng").item(0).getTextContent()));
			}
			mountainList.add(mount);
		}
		for(Mountain m : mountainList)
			System.out.println(">>Mountain from parser: " + m.toString());
		return mountainList;
	}

	public Map<String, Double> getLatitudeLongitude(String photoID) throws ParserConfigurationException, IOException, SAXException {
		return null;
	}
}