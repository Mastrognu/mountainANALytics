package it.polimi.awt.controllers;

import it.polimi.awt.domain.Request;
import it.polimi.awt.services.FlickrRQ;
import it.polimi.awt.services.SocialNetworkInterface;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {

	@RequestMapping("/view")
	public String addQueryFromForm(Request request) {
		SocialNetworkInterface sni = new FlickrRQ();
		try {
			request.setResponse(sni.sendTagsRequest(request.getQuery()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "jspdafare";
	}
}