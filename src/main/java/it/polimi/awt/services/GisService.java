package it.polimi.awt.services;

import it.polimi.awt.domain.Response;
import it.polimi.awt.utils.JAXBMapper;
import it.polimi.awt.utils.XMLUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

@Service
public class GisService implements IGisService {

	public List<Response> getCoordinatesFromLocation(String text)
			throws IOException {
		List<Response> listMountain = getConnection("http://services.gisgraphy.com/fulltext/fulltextsearch?q="
				+ text.toLowerCase().replace(" ", "%20")
				+ "&country=IT"
				+ "&placetype=Mountain");
//		List<Response> listCity = getConnection("http://services.gisgraphy.com/fulltext/fulltextsearch?q="
//				+ text.toLowerCase().replace(" ", "%20")
//				+ "&country=IT"
//				+ "&placetype=City");
		// TODO in qualche modo col parser xml bisogna dire che se c'è una
		// montagna allora si ritorn quella, se no la city pertinente, che di
		// solito è la prima si manda al nearby
//		if (listMountain.size() > 0)
//			return listMountain;
//		return listCity;
		return listMountain;
	}

	public List<Response> getNearbyPlacesFromCoordinates(double lat, double lng)
			throws IOException {

		List<Response> nearbySet = getConnection("http://services.gisgraphy.com/geoloc/search?lat="
				+ lat + "&lng=" + lng + "&radius=7000");

		return nearbySet;

	}

	private List<Response> getConnection(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		System.out.println("Sending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + con.getResponseCode());

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		StringBuilder response = new StringBuilder();

		String tmp;
		while ((tmp = in.readLine()) != null)
			response.append(tmp);

		System.out.print("RESPONSE= "+response+"\n");
		List<Response> doc = null;
		try {
			doc = XMLUtils.loadXMLFromString(response.toString());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
}
