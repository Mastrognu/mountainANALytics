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
	 * @param latD Latitude degrees
	 * @param latM Latitude minutes
	 * @param latS Latitude seconds
	 * @param lonD Longitude degrees
	 * @param lonM Longitude minutes
	 * @param lonS Longitude seconds
	 * @return The URL for the Flickr GET request
	 */
	public static String getFlickrCoordinatesURL(int latD, int latM, int latS, int lonD, int lonM, int lonS) {
		return FLICKR_BASE_URL + FlickrRQ.API_KEY + "&lat=" + convertCoordsToDecimal(latD, latM, latS) + "&lon=" + convertCoordsToDecimal(lonD, lonM, lonS) + FLICKR_APPENDIX;
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

	private static double convertCoordsToDecimal(int d, int m, int s) {
		return Math.signum(d) * (Math.abs(d) + (m / 60.0) + (s / 3600.0));
	}
}