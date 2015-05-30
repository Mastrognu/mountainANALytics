package it.polimi.awt.utils;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class JSONparser {

	public List<String> read(JsonParser jp) throws IOException {
		// Sanity check: verify that we got "Json Object":
		if (jp.nextToken() != JsonToken.START_OBJECT) {
			throw new IOException("Expected data to start with a new list of URL");
		}
		List<String> result = new ArrayList<String>();
		int index = 0;

		/*
		 * Per motivi a me sconosciuti, i < 2N fa riempire la lista di N elementi, qualnque sia N.
		 * Finchè non capisco come si faccia a sapere la lunghezza / dimensione del JsonParser, teniamolo così.
		 */
		for (int i = 0; i < 100; i++) {
			String owner = "";
			String id = "";
			while (jp.nextToken() != JsonToken.END_OBJECT && jp.getCurrentName() != null) {
				String fieldName = jp.getCurrentName();
				jp.nextToken();
				System.out.println(fieldName);
				if (fieldName.equals("id"))
					id = jp.getText();
				else if (fieldName.equals("owner"))
					owner = jp.getText();
			}
			if (owner != "" && id != "") {
				result.add(index, "https://www.flickr.com/" + owner + "/" + id);
				index++;
			}
		}
		jp.close(); // important to close both parser and underlying File reader
		return result;
	}

}
