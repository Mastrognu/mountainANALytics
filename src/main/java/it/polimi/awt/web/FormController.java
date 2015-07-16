package it.polimi.awt.web;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Photo;
import it.polimi.awt.domain.Province;
import it.polimi.awt.domain.QueryType;
import it.polimi.awt.domain.Request;
import it.polimi.awt.domain.Response;
import it.polimi.awt.repository.IJpaGenericAccess;
import it.polimi.awt.services.IGisService;
import it.polimi.awt.services.IJpaService;
import it.polimi.awt.services.ISocialNetwork;
import it.polimi.awt.utils.Coordinates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@RequestMapping("/view")
	public String addQueryFromForm(Request request) {
		try {
			/*
			 * Se la ricerca è basata sul nome di una città capoluogo di provincia,
			 * Gisgraphy ritorna tutte le montagne e tutte le città della provincia.
			 * Per risolvere il problema, controlliamo all'interno del db se la
			 * ricerca è un capoluogo
			 */
			List<Response> responseList = new ArrayList<Response>();
			if (hibernateAccess.getProvince(request.getQuery()) != null) {
				Province province = hibernateAccess.getProvince(request.getQuery());
				responseList.add(new Response(province.getName(), province.getLatitude(), province.getLongitude(), QueryType.CITY));
			} else {
				if (hibernateAccess.getMountain(request.getQuery()) != null) {
					Mountain mountain = hibernateAccess.getMountain(request.getQuery());
					responseList.add(new Response(mountain.getName(), mountain.getLatitude(), mountain.getLongitude(), QueryType.MOUNTAIN));
				} else {
					responseList = gisService.getCoordinatesFromLocation(request.getQuery(), false);
				}
			}
			for (Response response : responseList) {
				List<Mountain> allMountainsNearCity = new ArrayList<Mountain>();
				int from = 0;
				int to = 0;

				List<Mountain> mountainsNearCity = gisService.getNearbyPlacesFromCoordinates(response.getLatitude(), response.getLongitude(), RADIUS, from, to);
				allMountainsNearCity.addAll(mountainsNearCity);
				while (mountainsNearCity.size() == MAX_NUM_OF_ELEMENTS_IN_LIST && from <= MAX_FROM && to <= MAX_TO) {
					from+=10;
					to+=10;
					mountainsNearCity = gisService.getNearbyPlacesFromCoordinates(response.getLatitude(), response.getLongitude(), RADIUS, from, to);
					allMountainsNearCity.addAll(mountainsNearCity);
					//TODO E se mettessimo un wait(), riusciamo a superare il limite di 6 chiamate rest?
				}
				System.out.println("Montagne vicine a " + response.getName() + ": " + allMountainsNearCity.size());
				List<Mountain> allMountainsFoundInDb = new ArrayList<Mountain>();
				for (Mountain mountain : allMountainsNearCity)
					allMountainsFoundInDb.addAll(hibernateAccess.getMultiMountain(mountain));

				if (allMountainsFoundInDb.size() > 0) {
					for (Mountain m : allMountainsNearCity) {
						List<String> URLlist = socialNetwork.getPhotosURLs(m.getName());
						List<Photo> photoList = new ArrayList<Photo>();
						for (String url : URLlist) {
							Map<Coordinates, Double> map = new HashMap<Coordinates, Double>();
							map = socialNetwork.getPhotoInfo(url);
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
							System.out.println(">Photo: " + photo.toString());
							photoList.add(photo);
						}
						request.setResponse(photoList);
					}
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
