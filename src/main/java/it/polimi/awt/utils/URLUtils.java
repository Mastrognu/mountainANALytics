package it.polimi.awt.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import it.polimi.awt.services.FlickrRQ;

public class URLUtils {

	private static final String FLICKR_BASE_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=";
	private static final String FLICKR_APPENDIX = "&format=json&nojsoncallback=1";
	private static final String PANORAMIO_URL = "http://www.panoramio.com/map/get_panoramas.php?set=public";

	public static String startGetConnection(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("Sending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		StringBuilder response = new StringBuilder();

		String tmp;
		while ((tmp = in.readLine()) != null)
			response.append(tmp);

		return response.toString();
	}

	/**
	 * 
	 * @param text The tags to search for
	 * @return The URL for the Flickr GET request
	 */
	public static String getFlickrTagsURL(String text) {
		return FLICKR_BASE_URL + FlickrRQ.API_KEY + "&tags=" + text.toLowerCase().replace(" ", "+") + FLICKR_APPENDIX;
	}

	/**
	 * 
	 * @param from The first photo of the set required
	 * @param to The last photo of the set required
	 * @param minx Minimum longitude
	 * @param miny Minimum latitude
	 * @param maxx Maximum longitude
	 * @param maxy Maximum latitude
	 * @return The URL for the Panoramio GET request
	 */
	public static String getPanoramioURL(int from, int to, int minx, int miny, int maxx, int maxy) {
		return PANORAMIO_URL + "&from=" + from + "&to=" + to + "&minx=" + minx + "&miny=" + miny + "&maxx=" + maxx + "&maxy=" + maxy + "&size=medium&mapfilter=true";
	}

	public static String urlFormatter(String url) {
		return url.replace("%3A", ":").replace("%2F", "/").replace("=", "").substring(1);
	}
}