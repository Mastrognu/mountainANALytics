package it.polimi.awt.services;

import java.io.IOException;

public interface SocialNetworkInterface {
	
	public void sendTagsRequest(String tags) throws IOException;
	public void sendCoordinatesRequest(float latitude, float longitude);
	public void sendCoordinatesRequest(int latD, int latM, int latS, int lonD, int lonM, int lonS) throws IOException;
	public void sendTextRequest(String text);

}