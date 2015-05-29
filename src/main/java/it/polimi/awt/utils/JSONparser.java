package it.polimi.awt.utils;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class JSONparser {
	public static List<String> read(JsonParser jp) throws IOException {
		// Sanity check: verify that we got "Json Object":
		if (jp.nextToken() != JsonToken.START_OBJECT) {
			throw new IOException(
					"Expected data to start with a new list of URL");
		}
		List<String> result = new ArrayList<String>();
		int index = 0;
		// Manca l'iterate on each object esterno
		// Iterate over object fields:

		while (jp.nextToken() != JsonToken.END_OBJECT) {
			String fieldName = jp.getCurrentName();
			jp.nextToken();
			String owner = "";
			String id = "";
			if (fieldName.equals("id")) {
				id = (jp.getText());
			} else if (fieldName.equals("owner")) {
				owner = (jp.getText());
			} else { // ignore, or signal error?
				throw new IOException("Unrecognized field '" + fieldName + "'");
			}
			
			result.add(index, "https://www.flickr.com/" + id + "/" + owner);
			index++;
		}
		jp.close(); // important to close both parser and underlying File reader
		return result;
	}

}
