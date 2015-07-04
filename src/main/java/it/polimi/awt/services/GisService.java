package it.polimi.awt.services;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.QueryType;
import it.polimi.awt.domain.Response;
import it.polimi.awt.utils.URLUtils;
import it.polimi.awt.utils.XMLUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

@Service
public class GisService implements IGisService {

	public List<Response> getCoordinatesFromLocation(String text, boolean provinceFlag) throws IOException {
		List<Response> listMountain = new ArrayList<Response>();
		if(!provinceFlag) {
			listMountain = getConnection(
					"http://services.gisgraphy.com/fulltext/fulltextsearch?q="
							+ text.toLowerCase().replace(" ", "%20")
							+ "&country=IT" + "&placetype=Mountain",
							QueryType.MOUNTAIN);
		}
		List<Response> listCity = getConnection(
				"http://services.gisgraphy.com/fulltext/fulltextsearch?q="
						+ text.toLowerCase().replace(" ", "%20")
						+ "&country=IT" + "&placetype=City", QueryType.CITY);
		System.out.println("ListMountain size: " + listMountain.size() + " ListCity size: " + listCity.size());
		if (listMountain.size() > 0)
			return listMountain;
		return listCity;
	}

	public List<Mountain> getNearbyPlacesFromCoordinates(double lat, double lng, int radius, int from, int to) throws IOException {

		List<Mountain> nearbySet = getConnection("http://services.gisgraphy.com/geoloc/search?lat="
				+ lat + "&lng="	+ lng + "&radius=" + radius	+ "&placetype=mountain&from="
				+ from + "&to=" + to+ "&distance=true");
		return nearbySet;
	}

	private List<Response> getConnection(String url, QueryType queryType) throws IOException {
		String restpost = URLUtils.startGetConnection(url);
		List<Response> responseList = null;
		try {
			responseList = XMLUtils.parseFromGeolocalization(restpost, queryType);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseList;
	}

	private List<Mountain> getConnection(String url) throws IOException {
		String restpost = URLUtils.startGetConnection(url);
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
