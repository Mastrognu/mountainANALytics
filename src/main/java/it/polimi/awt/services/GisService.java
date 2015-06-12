package it.polimi.awt.services;

import it.polimi.awt.utils.XMLUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

@Service
public class GisService implements IGisService {

	public List<String> getCoordinatesFromLocation(String text) throws IOException {
		List<String> list = getConnection("http://services.gisgraphy.com/fulltext/fulltextsearch?q="+text.toLowerCase().replace(" ", "%20")+"&country=IT");
		//TODO Selezionare solo i risultati che sono CITY o MONUNTAIN/PEEKS, si possono filtrare direttamente dall'url!!!!!!!!
		return null;
	}

	private List<String> getConnection(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		con.setRequestMethod("GET");
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		List<String> response = new ArrayList<String>();
 
		String tmp;
		if ((tmp = in.readLine()) != null)
			response.add(tmp);

		Document doc;
		try {
			doc = XMLUtils.loadXMLFromString(response.toString());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			
		}
		return response;
	}
}
