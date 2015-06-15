package it.polimi.awt.services;

import it.polimi.awt.utils.JSONUtils;
import it.polimi.awt.utils.URLUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;

@Service
public class FlickrRQ implements ISocialNetwork {

	public static final String API_KEY = "8ba1865ec3cc2789404826aaf1ec82c3";
	private static final String SHARED_SECRET = "9794f74bdc128d35";
	private static final String FLICKR_TEST_URL = "https://api.flickr.com/services/rest/?method=flickr.test.echo&name=value";

	public List<String> sendPingRequest() throws IOException {
		return getConnection(FLICKR_TEST_URL);
	}

	public List<String> sendTagsRequest(String tags) throws IOException {
		return getConnection(URLUtils.getFlickrTagsURL(tags));
	}

	private List<String> getConnection(String url) throws IOException {
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

		JSONUtils parser = new JSONUtils();
		JsonFactory jsonF = new JsonFactory();
		@SuppressWarnings("deprecation")
		JsonParser jp = jsonF.createJsonParser(response.toString());
		List<String> entry = parser.readFlickr(jp);

		System.out.println(entry.size());

		return entry;
	}
}
