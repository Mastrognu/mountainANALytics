package it.polimi.awt.services;

import it.polimi.awt.domain.Photo;

import java.io.IOException;
import java.util.List;

public interface ISocialNetwork {

	/**
	 *
	 * @param tags The query performed by the user
	 * @return A list of photos URLs related to the searched text
	 * @throws IOException
	 */
	public List<String> getPhotosURLs(String tags) throws IOException;
	public Photo getPhotoInfo();
}