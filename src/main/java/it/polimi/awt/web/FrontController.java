package it.polimi.awt.web;

import it.polimi.awt.domain.GenericLocation;
import it.polimi.awt.domain.Model;
import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Photo;
import it.polimi.awt.domain.Province;
import it.polimi.awt.domain.User;
import it.polimi.awt.exceptions.NoCityNoMountainException;
import it.polimi.awt.repository.IJpaGenericAccess;
import it.polimi.awt.services.IGisService;
import it.polimi.awt.services.ISocialNetwork;
import it.polimi.awt.utils.Coordinates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@Scope("session")
public class FrontController {
	@Autowired
	ISocialNetwork socialNetwork;

	@Autowired
	IGisService gisService;

	@Autowired
	IJpaGenericAccess hibernateAccess;

	private static final int RADIUS = 10000; //meters
	private static final int MAX_NUM_OF_ELEMENTS_IN_LIST = 10;
	private static final int MAX_FROM = 31; // Limitazione di Gisgraphy
	private static final int MAX_TO = 40; // Limitazione di Gisgraphy

	@RequestMapping("/map")
	public String addQueryFromForm(Model model) throws NoCityNoMountainException {
		try {
			/*
			 * Se la ricerca è basata sul nome di una città capoluogo di provincia,
			 * Gisgraphy ritorna tutte le montagne e tutte le città della provincia.
			 * Per risolvere il problema, controlliamo all'interno del db se la
			 * ricerca è un capoluogo
			 */
			GenericLocation location = new GenericLocation();
			if (hibernateAccess.getProvince(model.getQuery()) != null) {
				Province province = hibernateAccess.getProvince(model.getQuery());
				location = new GenericLocation(province.getName(), province.getLatitude(), province.getLongitude());
			} else {
				if (hibernateAccess.getMountain(model.getQuery()) != null) {
					Mountain mountain = hibernateAccess.getMountain(model.getQuery());
					location = new GenericLocation(mountain.getName(), mountain.getLatitude(), mountain.getLongitude());
				} else {
					location = gisService.getCoordinatesFromLocation(model.getQuery(), false);
					if (location == null)
						return "400BadRequest";
				}
			}

			model.setQueryLatitude(location.getLatitude());
			model.setQueryLongitude(location.getLongitude());
			model.setQueryName(location.getName());
			List<Mountain> allMountainsNearCity = new ArrayList<Mountain>();
			int from = 0;
			int to = 0;

			List<Mountain> mountainsNearCity = gisService.getNearbyPlacesFromCoordinates(location.getLatitude(), location.getLongitude(), RADIUS, from, to);
			allMountainsNearCity.addAll(mountainsNearCity);
			while (mountainsNearCity.size() == MAX_NUM_OF_ELEMENTS_IN_LIST && from <= MAX_FROM && to <= MAX_TO) {
				from+=10;
				to+=10;
				mountainsNearCity = gisService.getNearbyPlacesFromCoordinates(location.getLatitude(), location.getLongitude(), RADIUS, from, to);
				allMountainsNearCity.addAll(mountainsNearCity);
				//TODO E se mettessimo un wait(), riusciamo a superare il limite di 6 chiamate rest?
			}
			System.out.println("Montagne vicine a " + location.getName() + ": " + allMountainsNearCity.size());
			List<Mountain> allMountainsFoundInDb = new ArrayList<Mountain>();
			for (Mountain mountain : allMountainsNearCity)
				allMountainsFoundInDb.addAll(hibernateAccess.getMultiMountain(mountain));

			if (allMountainsFoundInDb.size() > 0) {
				for (Mountain m : allMountainsNearCity) {
					List<String> URLlist = socialNetwork.getPhotosURLs(m.getName());
					List<Photo> photoList = new ArrayList<Photo>();
					for (String url : URLlist) {
						Map<Coordinates, Double> map = socialNetwork.getExifLocation(url);
						Photo photo = new Photo();
						photo.setMountainName(m.getName());
						photo.setUrl(url);
						//TODO photo.setUserID(userID);
						// Se la foto ha gli attributi latitude e longitude
						if (map.size() > 0) {
							photo.setLatitude(map.get(Coordinates.LATITUDE));
							photo.setLongitude(map.get(Coordinates.LONGITUDE));
						} else {
							photo.setLatitude(m.getLatitude() + nextDouble());
							photo.setLongitude(m.getLongitude()  + nextDouble());
						}
						System.out.println(">Photo: " + photo.toString());
						photoList.add(photo);
					}
					model.addToResponse(photoList);
				}
			} else {
				//TODO Che famo?
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("--------- END OF STUFF ON CONTROLLER ------------------");
		return "MapView";
	}

	@RequestMapping(value = "/selection", method = RequestMethod.POST)
	public String savePhotoFromMap(Photo photo) {
		System.out.println(">Foto ricevuta: " + photo);
		User currentUser = (User) getSession().getAttribute("currentUser");
		Photo dbPhoto = new Photo(currentUser.getEmail(), photo.getMountainName(), photo.getUrl(), photo.getLatitude(), photo.getLongitude());
		hibernateAccess.insertPhoto(dbPhoto, currentUser);

		return "saved";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String validateLogin(String email) {
		User user = new User(email);
		if (!hibernateAccess.checkUserExistence(user))
			hibernateAccess.createUser(user);

		getSession().setAttribute("currentUser", user);

		return "Home";
	}

	@RequestMapping(value = "/photos", method = RequestMethod.POST)
	public String findPhotoByUser(Model model) {
		List<Photo> list = hibernateAccess.getPhotoByUser((User) getSession().getAttribute("currentUser"));
		model.addToResponse(list);
		return "PhotoList";
	}

	private double nextDouble(){
		return 0.06 * new Random().nextFloat() - 0.03;
	}

	private HttpSession getSession() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
	}
}
