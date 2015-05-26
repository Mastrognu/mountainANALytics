package it.polimi.awt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlickrRQ {
	
	@RequestMapping(value = "/jspdafare", method = RequestMethod.POST)
	public String commonFriendsList(
			@RequestParam(value = "query", required = false) String query,
			Model model) {
		model.addAttribute("param", query);
		return "jspdafare";
	}

}
