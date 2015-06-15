package it.polimi.awt.services;

import it.polimi.awt.domain.Response;

import java.io.IOException;
import java.util.List;

public interface IGisService {

	public List<Response> getCoordinatesFromLocation(String text) throws IOException;
	public List<Response> getNearbyPlacesFromCoordinates(double lat, double lng) throws IOException;
}
