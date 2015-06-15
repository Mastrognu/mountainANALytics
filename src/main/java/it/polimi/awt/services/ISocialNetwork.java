package it.polimi.awt.services;

import java.io.IOException;
import java.util.List;

public interface ISocialNetwork {

	public List<String> sendTagsRequest(String tags) throws IOException;
}