package it.polimi.awt.controllers;

import it.polimi.awt.services.FlickrRQ;
import it.polimi.awt.services.PanoramioRQ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomoController {
	@Autowired
	PanoramioRQ pr;
	@Autowired
	FlickrRQ flick;
	@RequestMapping(value = {"/HomePage", "/"}, method = RequestMethod.GET)
	public String home(Model model) throws Exception {
		pr.sendGet();
		flick.sendRequest();
		return "HomePage";
	}
}
