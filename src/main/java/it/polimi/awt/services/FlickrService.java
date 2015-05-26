package it.polimi.awt.services;

import java.util.Collection;
import java.util.Collections;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import com.flickr4java.flickr.test.TestInterface;

public class FlickrService implements FlickrInterface {

	private static final String API_KEY = "8ba1865ec3cc2789404826aaf1ec82c3";
	private static final String SHARED_SECRET = "9794f74bdc128d35";
	
	public FlickrService() throws FlickrException {
		// Create a Flickr instance with your data. No need to authenticate
		Flickr flickr = new Flickr(API_KEY, SHARED_SECRET, new REST());
		TestInterface testInterface = flickr.getTestInterface();
		Collection<?> coll = testInterface.echo(Collections.emptyMap());
		for (Object element : coll) {
			System.out.print(element.toString());
		}

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
