package it.polimi.awt.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class JSONUtils {

	private static final int MAX_NUMBER_OF_PHOTOS = 100;

	/**
	 * 
	 * @param jp The Flickr JSON to parse
	 * @return A list of all the URLs of the photos contained in the parser
	 * @throws IOException In case there is a problem in the JSON read/write operations
	 */
	public List<String> readFlickr(JsonParser jp) throws IOException {
		List<String> result = new ArrayList<String>();
		int index = 0;

		/*
		 * Per motivi a me sconosciuti, i < 2N fa riempire la lista di N elementi, qualnque sia N.
		 * Finchè non capisco come si fetch a sapere la lunghezza / dimensione del JsonParser, teniamolo così.
		 */
		for (int i = 0; i < MAX_NUMBER_OF_PHOTOS; i++) {
			String farm = "";
			String id = "";
			String server = "";
			String secret = "";
			while (jp.nextToken() != JsonToken.END_OBJECT && jp.getCurrentName() != null) {
				String fieldName = jp.getCurrentName();
				jp.nextToken();
				if (fieldName.equals("id"))
					id = jp.getText();
				else if (fieldName.equals("secret"))
					secret = jp.getText();
				else if (fieldName.equals("farm"))
					farm = jp.getText();
				else if (fieldName.equals("server"))
					server = jp.getText();
			}
			if (farm != "" && id != "" && server !="" && secret !="") {
				result.add(index, "https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+"_n.jpg");
				System.out.println("Flickr photo url: " + result.get(index));
				index++;
			}
		}
		jp.close(); // important to close both parser and underlying File reader
		return result;
	}

	public Map<Coordinates, Double> getLatitudeLongitude(JsonParser jp) throws IOException {
		Map<Coordinates, Double> map = new HashMap<Coordinates, Double>();
		
		for (int i = 0; i < MAX_NUMBER_OF_PHOTOS; i++) {
			String latitude = "";
			String longitude = "";
			
			while (jp.nextToken() != JsonToken.END_OBJECT && jp.getCurrentName() != null) {
				String fieldName = jp.getCurrentName();
				jp.nextToken();
				if (fieldName.equals("latitude"))
					latitude = jp.getText();
				else if (fieldName.equals("longitude"))
					longitude = jp.getText();
			}
			if (longitude != "" && latitude != "") {
				map.put(Coordinates.LATITUDE, Double.valueOf(latitude));
				map.put(Coordinates.LONGITUDE, Double.valueOf(longitude));
			}
		}
		jp.close();
		return map;
	}
}
