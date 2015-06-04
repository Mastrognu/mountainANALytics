package it.polimi.awt.web;

import it.polimi.awt.domain.Photo;
import it.polimi.awt.domain.Request;
import it.polimi.awt.services.GisService;
import it.polimi.awt.services.ISocialNetwork;
import it.polimi.awt.services.PhotoService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FormController {
	@Autowired
	ISocialNetwork sni;
	
//	@Autowired
//	PhotoService photoService;

	@RequestMapping("/view")
	public String addQueryFromForm(Request request) {
		//Va fatto con l'injection, non come una semplice chiamata
		try {
			GisService gis = new GisService();
			gis.getCoordinatesFromLocation("Milano Marittima");
			request.setResponse(sni.sendTagsRequest(request.getQuery()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "jspdafare";
	}
	
//	@RequestMapping(value="/selection", params="Save", method=RequestMethod.POST)
//	public String saveUrlFromForm(Photo photo) {
//		
//		photoService.insertPhoto(photo);
//		return "redirect:/views";
//	}
		

	
}