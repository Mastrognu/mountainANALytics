package it.polimi.awt.controllers;

import it.polimi.awt.domain.Request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {
	
	@RequestMapping("/mountainRQ")
	public String addqueryFromForm(Request request) {		
		return "jspdafare";
	}
	
	

}
