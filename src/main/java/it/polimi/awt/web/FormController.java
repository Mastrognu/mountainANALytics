package it.polimi.awt.web;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Photo;
import it.polimi.awt.domain.QueryType;
import it.polimi.awt.domain.Request;
import it.polimi.awt.domain.Response;
import it.polimi.awt.repository.IJpaGenericAccess;
import it.polimi.awt.services.IGisService;
import it.polimi.awt.services.IJpaService;
import it.polimi.awt.services.ISocialNetwork;
import it.polimi.awt.utils.Coordinates;
import it.polimi.awt.utils.JSONUtils;
import it.polimi.awt.utils.URLUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;

@Controller
public class FormController {
	@Autowired
	ISocialNetwork socialNetwork;

	@Autowired
	IJpaService photoService;

	@Autowired
	IGisService gisService;

	@Autowired
	IJpaGenericAccess hibernateAccess;

	private static final int RADIUS = 20000; //meters
	private static final int MAX_NUM_OF_ELEMENTS_IN_LIST = 10;
	private static final int MAX_FROM = 31; // Limitazione di Gisgraphy
	private static final int MAX_TO = 40; // Limitazione di Gisgraphy

	@RequestMapping("/view")
	public String addQueryFromForm(Request request) {
		//TODO Va fatto con l'injection, non come una semplice chiamata
		try {
			/*
			 * Se la ricerca è basata sul nome di una città capoluogo di provincia,
			 * Gisgraphy ritorna tutte le montagne e tutte le città della provincia.
			 * Per risolvere il problema, controlliamo all'interno del db se la
			 * ricerca è un capoluogo
			 */
			List<Response> responseList;
			//TODO Fa schifo farlo qui il controllo ma nel GisService non funziona
			if (hibernateAccess.isThisQueryAProvince(request.getQuery())) {
				responseList = gisService.getCoordinatesFromLocation(request.getQuery(), true);
			} else {
				responseList = gisService.getCoordinatesFromLocation(request.getQuery(), false);
			}
			System.out.println("Response list = " + responseList);
			//TODO Attualmente cerchiamo solo le montagne vicino alle city, ha senso cercare anche le montagne vicino a una montagna?
			/*
			 * Di tutta a lista di città consideriamo solo la prima perchè è la
			 * città più importante aka quella che probabilmente ha cercato l'utente
			 */
			if (responseList.get(0).getType().equals(QueryType.CITY)) {
				Response city = responseList.get(0);
				List<Mountain> allMountainsNearCity = new ArrayList<Mountain>();
				int from = 0;
				int to = 0;

				List<Mountain> mountainsNearCity = gisService.getNearbyPlacesFromCoordinates(city.getLatitude(), city.getLongitude(), RADIUS, from, to);
				allMountainsNearCity.addAll(mountainsNearCity);
				while (mountainsNearCity.size() == MAX_NUM_OF_ELEMENTS_IN_LIST && from <= MAX_FROM && to <= MAX_TO) {
					from+=10;
					to+=10;
					mountainsNearCity = gisService.getNearbyPlacesFromCoordinates(city.getLatitude(), city.getLongitude(), RADIUS, from, to);
					allMountainsNearCity.addAll(mountainsNearCity);
					//TODO E se mettessimo un wait(), riusciamo a superare il limite di 6 chiamate rest?
				}
				System.out.println("Montagne vicine a " + city.getName() + ": " + allMountainsNearCity.size());
				//TODO E' giusto fare tutti questo lavoro all'interno del controller?
				List<Mountain> allMountainsFoundInDb = new ArrayList<Mountain>();
				List<Mountain> mountainsNotInDb = new ArrayList<Mountain>();
				for (Mountain mountain : allMountainsNearCity) {
					List<Mountain> mountainsFoundInDb = hibernateAccess.mountainInDb(mountain);
					allMountainsFoundInDb.addAll(mountainsFoundInDb);
					if (!(mountainsFoundInDb.size() > 0))
						mountainsNotInDb.add(mountain);
				}
				if (allMountainsFoundInDb.size() > 0) {
					for (Mountain m : allMountainsFoundInDb) {
						List<String> URLlist = socialNetwork.getPhotosURLs(m.getName());
						List<Photo> photoList = new ArrayList<Photo>();
						for (String url : URLlist) {
							String response = URLUtils.startGetConnection(url);
							JSONUtils parser = new JSONUtils();
							JsonFactory jsonF = new JsonFactory();
							JsonParser jp = jsonF.createJsonParser(response);
							Map<Coordinates, Double> map = parser.getLatitudeLongitude(jp);

							Photo photo = new Photo();
							photo.setUrl(url);
							//TODO Dobbiamo aggiungere l'user alla foto
							// Se la foto ha gli attributi latitude e longitude
							if (map.size() > 0) {
								photo.setLatitude(map.get(Coordinates.LATITUDE));
								photo.setLongitude(map.get(Coordinates.LONGITUDE));
							} else {
								photo.setLatitude(m.getLatitude());
								photo.setLongitude(m.getLongitude());
							}
							photoList.add(photo);
						}
						request.setResponse(photoList);
					}
					//TODO Mandare a Flickr anche le montagne in <mountainsNotInDb> e colorarle con un marker diverso, se ce n'è almeno una nel db
				} else {
					//TODO Che famo?
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "MapView";
	}

	@RequestMapping(value = "/selection", method = RequestMethod.POST)
	public String saveUrlFromForm(Photo photo) {
		//TODO Problema con l'ID della photo passata come parametro
		Photo photo2 = new Photo();
		photo2.setUrl(photo.getUrl());
		photoService.insertPhoto(photo2);

		return "saved";
	}
}
