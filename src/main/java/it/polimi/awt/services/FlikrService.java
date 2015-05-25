package it.polimi.awt.services;

import java.util.Collections;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import com.flickr4java.flickr.test.TestInterface;

public class FlikrService {

	String apiKey = "8ba1865ec3cc2789404826aaf1ec82c3";
	String sharedSecret = "9794f74bdc128d35";
	
	public void FlickrService() {
		// Create a Flickr instance with your data. No need to authenticate
		Flickr flickr = new Flickr(apiKey, sharedSecret, new REST());
		
		// Set the wanted search parameters
	    SearchParameters searchParameters = new SearchParameters();
//	    searchParameters.setAccuracy(accuracyLevel);
//	    searchParameters.setBBox(minimum_longitude, 
//	                                 minimum_latitude, 
//	                                 maximum_longitude, 
//	                                 maximum_latitude);
//
//	    PhotoList<Photo> list = flickr.getPhotosInterface().search(searchParameters, 0, 0);
	}
}
