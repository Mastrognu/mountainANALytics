package it.polimi.awt.services;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Response;

import java.io.IOException;
import java.util.List;

public interface IGisService {

	/**
	 *
	 * @param text The query searched by the user
	 * @param provinceFlag true if the user searched a city which is an Italian province, false otherwise
	 * @return A list of Response from GisGraphy
	 * @throws IOException
	 */
	public List<Response> getCoordinatesFromLocation(String text, boolean provinceFlag) throws IOException;
	/**
	 *
	 * @param lat Latitude of the place (in decimal)
	 * @param lng Longitude of the place (in decimal)
	 * @param radius The width in which to search other places
	 * @param from The beginning of the range of values
	 * @param to The end of the rang of values. to - from <= 10
	 * @return A list of Mountain near to the place searched
	 * @throws IOException
	 */
	public List<Mountain> getNearbyPlacesFromCoordinates(double lat, double lng, int radius, int from, int to) throws IOException;
}
