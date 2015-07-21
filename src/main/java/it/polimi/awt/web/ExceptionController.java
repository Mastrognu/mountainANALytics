package it.polimi.awt.web;

import it.polimi.awt.exceptions.NoCityNoMountainException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ExceptionController {

	@ExceptionHandler({NoCityNoMountainException.class})
	public String queryError() {
		return "400BadRequest";
	}
}