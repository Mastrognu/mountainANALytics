package it.polimi.awt.services;

import it.polimi.awt.domain.GenericLocation;
import it.polimi.awt.domain.Mountain;

import java.io.IOException;
import java.util.List;

public interface IGisService {

	/**
	 *
	 * @param text The query searched by the user
	 * @return A list of Response from GisGraphy
	 * @throws IOException
	 * @throws NoCityNoMountainException
	 */
	public GenericLocation getCoordinatesFromLocation(String text) throws IOException;
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
