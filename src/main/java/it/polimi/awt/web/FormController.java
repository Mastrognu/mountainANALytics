package it.polimi.awt.web;

import it.polimi.awt.domain.Request;
import it.polimi.awt.services.GisService;
import it.polimi.awt.services.ISocialNetwork;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {
	@Autowired
	ISocialNetwork sni;

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
}
