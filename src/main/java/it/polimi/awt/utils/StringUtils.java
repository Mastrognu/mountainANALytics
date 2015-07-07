package it.polimi.awt.utils;

public class StringUtils {

	public static String getPhotoIdFromURL(String url) {
		return url.split("/")[4].split("_")[0];
	}
}
