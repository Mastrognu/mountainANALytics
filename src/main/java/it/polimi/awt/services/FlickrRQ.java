package it.polimi.awt.services;

import it.polimi.awt.utils.URLFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

@Service
public class FlickrRQ implements SocialNetworkInterface {

	private static final String API_KEY = "8ba1865ec3cc2789404826aaf1ec82c3";
	private static final String SHARED_SECRET = "9794f74bdc128d35";
	private static final String FLICKR_TEST_URL = "https://api.flickr.com/services/rest/?method=flickr.test.echo&name=value";
	
	public String sendPingRequest() throws IOException {
		return getConnection(FLICKR_TEST_URL);
	}

	@Override
	public String sendTagsRequest(String tags) throws IOException {
		return getConnection(URLFormatter.getFlickrTagsQueryURL(tags));
	}

	@Override
	public String sendCoordinatesRequest(int latD, int latM, int latS, int lonD, int lonM, int lonS) throws IOException {
		return getConnection(URLFormatter.getFlickrCoordinatesQueryURL(latD, latM, latS, lonD, lonM, lonS));
	}

	@Override
	public String sendTextRequest(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendCoordinatesRequest(float latitude, float longitude) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getConnection(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		con.setRequestMethod("GET");
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		StringBuilder response = new StringBuilder();
 
		String tmp;
		if ((tmp = in.readLine()) != null)
			response.append(tmp);

		System.out.println(response.toString());

		return response.toString();
	}

	public static String getApiKey() {
		return API_KEY;
	}

	private static Document loadXMLFromString(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}
}
