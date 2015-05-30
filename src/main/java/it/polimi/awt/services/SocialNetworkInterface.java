package it.polimi.awt.services;

import java.io.IOException;

public interface SocialNetworkInterface {

	public String sendTagsRequest(String tags) throws IOException;
	public String sendCoordinatesRequest(float latitude, float longitude);
	public String sendCoordinatesRequest(int latD, int latM, int latS, int lonD, int lonM, int lonS) throws IOException;
	public String sendTextRequest(String text);
}