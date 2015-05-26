package it.polimi.awt.controllers;

import it.polimi.awt.services.FlickrService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flickr4java.flickr.FlickrException;

@Controller
public class HomoController {
	
	@Autowired
	FlickrService fs;
	
	@RequestMapping(value = {"/HomePage", "/"}, method = RequestMethod.GET)
	public String home(Model model) throws FlickrException {
		fs.doStuff();
		return "HomePage";
	}

}
