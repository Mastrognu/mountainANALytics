package it.polimi.awt.services;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.QueryType;
import it.polimi.awt.domain.Response;
import it.polimi.awt.utils.XMLUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

@Service
public class GisService implements IGisService {

	public List<Response> getCoordinatesFromLocation(String text)
			throws IOException {
		List<Response> listMountain = getConnection(
				"http://services.gisgraphy.com/fulltext/fulltextsearch?q="
						+ text.toLowerCase().replace(" ", "%20")
						+ "&country=IT" + "&placetype=Mountain",
				QueryType.MOUNTAIN);
		List<Response> listCity = getConnection(
				"http://services.gisgraphy.com/fulltext/fulltextsearch?q="
						+ text.toLowerCase().replace(" ", "%20")
						+ "&country=IT" + "&placetype=City", QueryType.CITY);
		// TODO in qualche modo col parser xml bisogna dire che se c'è una
		// montagna allora si ritorn quella, se no la city pertinente, che di
		// solito è la prima si manda al nearby
		if (listMountain.size() > 0)
			return listMountain;
		return listCity;
	}

	public List<Mountain> getNearbyPlacesFromCoordinates(double lat, double lng, int radius) throws IOException {

		List<Mountain> nearbySet = getConnection(
				"http://services.gisgraphy.com/geoloc/search?lat=" + lat
						+ "&lng=" + lng + "&radius=" + radius + "&placetype=Mountain");

		return nearbySet;

	}

	private List<Response> getConnection(String url, QueryType queryType) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		System.out.println("Sending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + con.getResponseCode());

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		StringBuilder restpost = new StringBuilder();

		String tmp;
		while ((tmp = in.readLine()) != null)
			restpost.append(tmp);

		List<Response> responseList = null;
		try {
			responseList = XMLUtils.parseFromGeolocalization(restpost.toString(), queryType);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseList;
	}

	private List<Mountain> getConnection(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		System.out.println("Sending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + con.getResponseCode());

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		StringBuilder restpost = new StringBuilder();

		String tmp;
		while ((tmp = in.readLine()) != null)
			restpost.append(tmp);

		List<Mountain> responseList = null;
		try {
			responseList = XMLUtils.parseFromGetNearby(restpost.toString());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseList;
	}
}
