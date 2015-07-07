package it.polimi.awt.services;

import it.polimi.awt.utils.ConnectionUtils;
import it.polimi.awt.utils.Coordinates;
import it.polimi.awt.utils.JSONUtils;
import it.polimi.awt.utils.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

	public List<String> getPhotosURLs(String tags) throws IOException {
		return getConnection(ConnectionUtils.getFlickrTagsURL(tags));
	}

	public Map<Coordinates, Double> getPhotoInfo(String url) throws IOException {
		String response = ConnectionUtils.startGetConnection(ConnectionUtils.getURLLocation(StringUtils.getPhotoIdFromURL(url)));
		JSONUtils parser = new JSONUtils();
		JsonFactory jsonF = new JsonFactory();
		JsonParser jp = jsonF.createJsonParser(response);
		return parser.getLatitudeLongitude(jp);
	}

	private List<String> getConnection(String url) throws IOException {
		String response = ConnectionUtils.startGetConnection(url);

		System.out.println(">>FlickrRQ response: " + response);

		JSONUtils parser = new JSONUtils();
		JsonFactory jsonF = new JsonFactory();
		JsonParser jp = jsonF.createJsonParser(response);
		List<String> entry = parser.readFlickr(jp);

		System.out.println(entry.size());

		return entry;
	}
}
