package it.polimi.awt.services;

import java.io.IOException;
import java.util.List;

public interface IGisService {

	public List<String> getCoordinatesFromLocation(String text) throws IOException;
}