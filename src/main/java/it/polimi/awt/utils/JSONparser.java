package it.polimi.awt.utils;

import java.io.IOException;

import it.polimi.awt.domain.Photo;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//questo metodo prende un json per volta e lo traduce in un oggetto foto per volta
//bisogna capire come ciclare su tutto lo stream e creare un botto di oggetti

public class JSONparser {
	
	public static void mapper(String jsonObj) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		Photo photo = mapper.readValue(jsonObj, Photo.class);
	}

}
