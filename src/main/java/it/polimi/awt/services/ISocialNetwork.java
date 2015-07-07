package it.polimi.awt.services;

import it.polimi.awt.domain.Photo;
import it.polimi.awt.utils.Coordinates;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ISocialNetwork {

	/**
	 *
	 * @param tags The query performed by the user
	 * @return A list of photos URLs related to the searched text
	 * @throws IOException
	 */
	public List<String> getPhotosURLs(String tags) throws IOException;
	public Map<Coordinates, Double> getPhotoInfo(String url) throws IOException;
}