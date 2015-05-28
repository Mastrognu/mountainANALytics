package it.polimi.awt.controllers;

import it.polimi.awt.domain.Request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FormController {
	
	@RequestMapping("/view")
	public String addQueryFromForm(Request request) {		
		return "jspdafare";
	}
	
	

}
