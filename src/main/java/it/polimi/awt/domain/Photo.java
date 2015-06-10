package it.polimi.awt.domain;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "photo")
public class Photo {

	@Id
	private int photoID;
	private String url;

	private Random rnd;

	public Photo() {
		rnd = new Random();
	}

	public int getId() {
		return photoID;
	}

	public void setId(int id) {
		this.photoID = rnd.nextInt(65536);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
