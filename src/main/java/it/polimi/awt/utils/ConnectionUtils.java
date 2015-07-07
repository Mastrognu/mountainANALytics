package it.polimi.awt.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import it.polimi.awt.services.FlickrRQ;

public class ConnectionUtils {

	private static final String FLICKR_BASE_URL = "https://api.flickr.com/services/rest/";
	private static final String PHOTO_SEARCH = "?method=flickr.photos.search&api_key=";
	private static final String GEOLOCATION = "?method=flickr.photos.geo.getLocation";
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
		return FLICKR_BASE_URL + PHOTO_SEARCH + FlickrRQ.API_KEY + "&tags=" + text.toLowerCase().replace(" ", "+") + FLICKR_APPENDIX;
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

	/**
	 *
	 * @param photoID The id of the photo
	 * @return The URL for the Flickr getGeolocation request
	 */
	public static String getURLLocation(String photoID) {
		return FLICKR_BASE_URL + GEOLOCATION + "&api_key=" + FlickrRQ.API_KEY + "&photo_id=" + photoID + FLICKR_APPENDIX;
	}

	public static String urlFormatter(String url) {
		return url.replace("%3A", ":").replace("%2F", "/").replace("=", "").substring(1);
	}
}