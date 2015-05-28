package it.polimi.awt.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class FlickrRQ {

	private static final String FLICKR_URL = "https://api.flickr.com/services/rest/?method=flickr.test.echo&name=value";
	
	public void sendRequest() throws IOException {
		URL obj = new URL(FLICKR_URL);
		HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();

		httpConnection.setRequestMethod("GET");
 
		int responseCode = httpConnection.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + FLICKR_URL);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
	}
}
