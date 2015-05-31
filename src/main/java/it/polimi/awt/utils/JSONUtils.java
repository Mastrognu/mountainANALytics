package it.polimi.awt.utils;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class JSONUtils {

	private static final int MAX_NUMBER_OF_PHOTOS = 100;

	/**
	 * 
	 * @param jp The JSON to parse
	 * @return A list of all the URLs of the photos contained in the parser
	 * @throws IOException In case there is a problem in the JSON read/write operations
	 */
	public List<String> readFlickr(JsonParser jp) throws IOException {
		List<String> result = new ArrayList<String>();
		int index = 0;

		/*
		 * Per motivi a me sconosciuti, i < 2N fa riempire la lista di N elementi, qualnque sia N.
		 * Finchè non capisco come si faccia a sapere la lunghezza / dimensione del JsonParser, teniamolo così.
		 */
		for (int i = 0; i < MAX_NUMBER_OF_PHOTOS; i++) {
			String farm = "";
			String id = "";
			String server = "";
			String secret = "";
			while (jp.nextToken() != JsonToken.END_OBJECT && jp.getCurrentName() != null) {
				String fieldName = jp.getCurrentName();
				jp.nextToken();
				if (fieldName.equals("id"))
					id = jp.getText();
				else if (fieldName.equals("secret"))
					secret = jp.getText();
				else if (fieldName.equals("farm"))
					farm = jp.getText();
				else if (fieldName.equals("server"))
					server = jp.getText();
			}
			if (farm != "" && id != "" && server !="" && secret !="") {
				result.add(index, "https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+"_n.jpg");
				System.out.println(result.get(index));
				index++;
			}
		}
		jp.close(); // important to close both parser and underlying File reader
		return result;
	}

}
