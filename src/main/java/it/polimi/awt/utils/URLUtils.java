package it.polimi.awt.utils;

import it.polimi.awt.services.FlickrRQ;

public class URLUtils {

	private static final String FLICKR_BASE_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=";
	private static final String FLICKR_APPENDIX = "&format=json&nojsoncallback=1";
	private static final String PANORAMIO_URL = "http://www.panoramio.com/map/get_panoramas.php?set=public";

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