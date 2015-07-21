package it.polimi.awt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Query unsupported")  // 400
public class NoCityNoMountainException extends RuntimeException {

	private static final long serialVersionUID = 4007001360130290025L;

	public NoCityNoMountainException(){
	}
}
