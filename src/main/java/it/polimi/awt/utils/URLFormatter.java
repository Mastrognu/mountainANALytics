package it.polimi.awt.utils;

public class URLFormatter {

	private static final String BASE_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=fb38cbad82a1353f5b454f7d64b6973b";
	private static final String APPENDIX = "&format=rest";

	public static String getTagsQueryURL(String text) {
		return BASE_URL + "&tags" + text.replace(" ", "+") + APPENDIX;
	}

	public static String getCoordinatesQueryURL(int latD, int latM, int latS, int lonD, int lonM, int lonS) {
		return BASE_URL + "&lat" + convertCoordsToDecimal(latD, latM, latS) + "&lon" + convertCoordsToDecimal(lonD, lonM, lonS) + APPENDIX;
	}

	private static double convertCoordsToDecimal(int d, int m, int s) {
		return Math.signum(d) * (Math.abs(d) + (m / 60.0) + (s / 3600.0));
	}
}