package it.polimi.awt.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class PanoramioRQ implements SocialNetworkInterface {

	@Override
	public String sendTagsRequest(String tag) throws IOException {
		String url = "http://www.panoramio.com/map/get_panoramas.php?set=public&from=0&to=20&minx=-180&miny=-90&maxx=180&maxy=90&size=medium&mapfilter=true";
		 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		return response.toString();		
	}

	@Override
	public String sendCoordinatesRequest(float latitude, float longitude) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendTextRequest(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendCoordinatesRequest(int latD, int latM, int latS, int lonD,
			int lonM, int lonS) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}	
}
