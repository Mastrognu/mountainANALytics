package it.polimi.awt.services;

import it.polimi.awt.utils.URLFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class FlickrRQ implements SocialNetworkInterface {

	private static final String API_KEY = "8ba1865ec3cc2789404826aaf1ec82c3";
	private static final String SHARED_SECRET = "9794f74bdc128d35";
	private static final String FLICKR_TEST_URL = "https://api.flickr.com/services/rest/?method=flickr.test.echo&name=value";
	
	public void sendPingRequest() throws IOException {
		URL obj = new URL(FLICKR_TEST_URL);
		HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();

		httpConnection.setRequestMethod("GET");
 
		int responseCode = httpConnection.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + FLICKR_TEST_URL);
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

	@Override
	public void sendTagsRequest(String tags) throws IOException {
		String url = URLFormatter.getTagsQueryURL(tags);
		URL obj = new URL(url);
		HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();

		httpConnection.setRequestMethod("GET");
 
		int responseCode = httpConnection.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
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

	@Override
	public void sendCoordinatesRequest(int latD, int latM, int latS, int lonD, int lonM, int lonS) throws IOException {
		String url = URLFormatter.getCoordinatesQueryURL(latD, latM, latS, lonD, lonM, lonS);
		URL obj = new URL(url);
		HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();

		httpConnection.setRequestMethod("GET");
 
		int responseCode = httpConnection.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
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

	@Override
	public void sendTextRequest(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendCoordinatesRequest(float latitude, float longitude) {
		// TODO Auto-generated method stub
		
	}
}
