package it.polimi.awt.services;

import it.polimi.awt.domain.GenericLocation;
import it.polimi.awt.domain.Mountain;
import it.polimi.awt.utils.ConnectionUtils;
import it.polimi.awt.utils.XMLUtils;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

@Service
public class GisService implements IGisService {

	public GenericLocation getCoordinatesFromLocation(String text, boolean provinceFlag) throws IOException {
		GenericLocation respMountain = null;
		if(!provinceFlag) {
			respMountain = getResponseConnection(
					"http://services.gisgraphy.com/fulltext/fulltextsearch?q="
							+ text.toLowerCase().replace(" ", "%20")
							+ "&country=IT" + "&placetype=Mountain");
		}
		GenericLocation respCity = getResponseConnection(
				"http://services.gisgraphy.com/fulltext/fulltextsearch?q="
						+ text.toLowerCase().replace(" ", "%20")
						+ "&country=IT" + "&placetype=City");
		/*
		 * To avoid problems related to places that are called like mountains, if we found a mountain we return it instead of the city
		 */
		if (respMountain != null)
			return respMountain;
		else if (respCity != null)
			return respCity;
		return null;
	}

	public List<Mountain> getNearbyPlacesFromCoordinates(double lat, double lng, int radius, int from, int to) throws IOException {

		List<Mountain> nearbySet = getConnection("http://services.gisgraphy.com/geoloc/search?lat="
				+ lat + "&lng="	+ lng + "&radius=" + radius	+ "&placetype=mountain&from="
				+ from + "&to=" + to+ "&distance=true");
		return nearbySet;
	}

	private GenericLocation getResponseConnection(String url) throws IOException {
		String restpost = ConnectionUtils.startGetConnection(url);
		List<GenericLocation> responseList = null;
		try {
			responseList = XMLUtils.parseFromGeolocalization(restpost);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (responseList.size() > 0)
			return responseList.get(0);
		return null;
	}

	private List<Mountain> getConnection(String url) throws IOException {
		String restpost = ConnectionUtils.startGetConnection(url);
		List<Mountain> responseList = null;
		try {
			responseList = XMLUtils.parseFromGetNearby(restpost);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseList;
	}
}
