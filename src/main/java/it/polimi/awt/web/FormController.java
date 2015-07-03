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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	private int from = 1;
	private int to = 10;

	@RequestMapping("/view")
	public String addQueryFromForm(Request request) {
		// Va fatto con l'injection, non come una semplice chiamata
		try {
			/*
			 * Se la ricerca � basata sul nome di una citt� capoluogo di provincia,
			 * Gisgraphy ritorna tutte le montagne e tutte le citt� della provincia.
			 * Per risolvere il problema, controlliamo all'interno del db se la
			 * ricerca � un capoluogo
			 */
			List<Response> responseList;
			//TODO Fa schifo farlo qui il controllo ma nel GisService non funziona
			if (hibernateAccess.isThisQueryAProvince(request.getQuery())) {
				responseList = gisService.getCoordinatesFromLocation(request.getQuery(), true); //
			} else {
				responseList = gisService.getCoordinatesFromLocation(request.getQuery(), false);
			}
			System.out.println("Response list = " + responseList);
			//TODO Attualmente cerchiamo solo le montagne vicino alle city, ha senso cercare anche le montagne vicino a una montagna?
			if (responseList.get(0).getType().equals(QueryType.CITY)) {
				List<Mountain> allMountainsNearCity = new ArrayList<Mountain>();
				List<Mountain> mountainsNearCity = gisService.getNearbyPlacesFromCoordinates(responseList.get(0).getLatitude(), responseList.get(0).getLongitude(), RADIUS, from, to);
				allMountainsNearCity.addAll(mountainsNearCity);
				while (mountainsNearCity.size() == MAX_NUM_OF_ELEMENTS_IN_LIST && from <= MAX_FROM && to <= MAX_TO) {
					from+=10;
					to+=10;
					mountainsNearCity = gisService.getNearbyPlacesFromCoordinates(responseList.get(0).getLatitude(), responseList.get(0).getLongitude(), RADIUS, from, to);
					allMountainsNearCity.addAll(mountainsNearCity);
					//TODO E se mettessimo un wait(), riusciamo a superare il limite di 6 chiamate rest?
				}
				System.out.println(">>>>>>>>>< List size == " + allMountainsNearCity.size());
				//TODO E' giusto fare tutti questo lavoro all'interno del controller?
				List<Mountain> allMountainsFoundInDb = new ArrayList<Mountain>();
				for (Mountain mountain : allMountainsNearCity) {
//					System.out.println("Query per montagna " + mountain);
					List<Mountain> mountainsFoundInDb = hibernateAccess.mountainInDb(mountain);
					allMountainsFoundInDb.addAll(mountainsFoundInDb);
				}
				for (Mountain m : allMountainsFoundInDb) {
					System.out.println("Mountain in db :" + m);
					request.setResponse(socialNetwork.sendTagsRequest(m.getName()));
				}
				/*
				for (Response resp : responseList) {
					// Lista di montagne vicine ad ogni city
					List<Mountain> mountainsNearCity = gisService.getNearbyPlacesFromCoordinates(resp.getLatitude(), resp.getLongitude(), RADIUS, from, to);
					allMountainsNearCity.addAll(mountainsNearCity);
					while (mountainsNearCity.size() == MAX_NUM_OF_ELEMENTS_IN_LIST && from <= MAX_FROM && to <= MAX_TO) {
						from+=10;
						to+=10;
						mountainsNearCity = gisService.getNearbyPlacesFromCoordinates(resp.getLatitude(), resp.getLongitude(), RADIUS, from, to);
						allMountainsNearCity.addAll(mountainsNearCity);
						//TODO E se mettessimo un wait(), riusciamo a superare il limite di 6 chiamate rest?
					}
					System.out.println("List size == " + allMountainsNearCity.size());
					//TODO E' giusto fare tutti questo lavoro all'interno del controller?
					List<Mountain> allMountainsFoundInDb = new ArrayList<Mountain>();
					for (Mountain mountain : allMountainsNearCity) {
//						System.out.println("Query per montagna " + mountain);
						List<Mountain> mountainsFoundInDb = hibernateAccess.mountainInDb(mountain);
						allMountainsFoundInDb.addAll(mountainsFoundInDb);
					}
					for (Mountain m : allMountainsFoundInDb) {
						System.out.println("Mountain in db :" + m);
						request.setResponse(socialNetwork.sendTagsRequest(m.getName()));
					}
				}
				*/
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
