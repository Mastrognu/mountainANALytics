package it.polimi.awt.web;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Photo;
import it.polimi.awt.domain.QueryType;
import it.polimi.awt.domain.Request;
import it.polimi.awt.domain.Response;
import it.polimi.awt.repository.PhotoRepository;
import it.polimi.awt.services.IGisService;
import it.polimi.awt.services.IPhotoService;
import it.polimi.awt.services.ISocialNetwork;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.org.apache.regexp.internal.recompile;

@Controller
public class FormController {
	@Autowired
	ISocialNetwork sni;

	@Autowired
	IPhotoService photoService;

	@Autowired
	IGisService gisService;

	@Autowired
	PhotoRepository photoRepository;

	private static final int RADIUS = 20000; //meters

	@RequestMapping("/view")
	public String addQueryFromForm(Request request) {
		// Va fatto con l'injection, non come una semplice chiamata
		try {
			List<Response> lr = gisService.getCoordinatesFromLocation(request.getQuery());
			System.out.println("BBBBB lr= "+lr);
			if (lr.get(0).getType().equals(QueryType.CITY)) {
				for (Response resp : lr) {
					// Lista di montagne vicine ad ogni city
					List<Mountain> newList = gisService.getNearbyPlacesFromCoordinates(resp.getLatitude(), resp.getLongitude(), RADIUS);
					for (Mountain newResp : newList) {
						photoRepository.mountainInDb(newResp);
					}
				}
			}
//			request.setResponse(sni.sendTagsRequest(request.getQuery()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "MapView";
	}

	@RequestMapping(value = "/selection", method = RequestMethod.POST)
	public String saveUrlFromForm(Photo photo) {

		// System.out.println("URL= "+url);
		// System.out.println("NEWURL= "+URLUtils.urlFormatter(url));
		// photo.setPhotoID(new Random().nextInt(65536));
		// photo.setUrl(url);

		Photo photo2 = new Photo();
		//TODO CHE TETTE ENORMI!
		photo2.setUrl(photo.getUrl());
		photoService.insertPhoto(photo2);

		return "saved";

		// @RequestMapping(value="/selection")
		// public String saveUrlFromForm(Photo photo) {
		//
		// System.out.println(photo.getUrl());
		// String saved = "Saved";
		// photoService.insertPhoto(photo);
		//
		// return saved;
		//
	}
}
