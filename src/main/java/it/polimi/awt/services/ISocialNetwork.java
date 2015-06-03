package it.polimi.awt.services;

import java.io.IOException;
import java.util.List;

public interface ISocialNetwork {

	public List<String> sendTagsRequest(String tags) throws IOException;
	public List<String> sendCoordinatesRequest(float latitude, float longitude);
	public List<String> sendCoordinatesRequest(int latD, int latM, int latS, int lonD, int lonM, int lonS) throws IOException;
	public List<String> sendTextRequest(String text);
}