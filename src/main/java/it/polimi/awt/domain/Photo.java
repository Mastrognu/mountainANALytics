package it.polimi.awt.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Photo {
	
	@Id private String url;

	public String getUrl() {
		return url;
	}
	public void setName(String url) {
		this.url = url;
	}

}
