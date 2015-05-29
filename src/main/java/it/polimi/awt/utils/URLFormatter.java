package it.polimi.awt.utils;

import it.polimi.awt.services.FlickrRQ;

public class URLFormatter {

	private static final String FLICKR_BASE_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=";
	private static final String FLICKR_APPENDIX = "&format=json&nojsoncallback=1";

	public static String getFlickrTagsQueryURL(String text) {
		return FLICKR_BASE_URL + FlickrRQ.getApiKey() + "&tags=" + text.replace(" ", "+") + FLICKR_APPENDIX;
	}

	public static String getFlickrCoordinatesQueryURL(int latD, int latM, int latS, int lonD, int lonM, int lonS) {
		return FLICKR_BASE_URL + FlickrRQ.getApiKey() + "&lat=" + convertCoordsToDecimal(latD, latM, latS) + "&lon=" + convertCoordsToDecimal(lonD, lonM, lonS) + FLICKR_APPENDIX;
	}

	private static double convertCoordsToDecimal(int d, int m, int s) {
		return Math.signum(d) * (Math.abs(d) + (m / 60.0) + (s / 3600.0));
	}
}