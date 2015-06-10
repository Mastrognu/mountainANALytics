package it.polimi.awt.domain;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "photo")
public class Photo {

	@Id
	private int id;
	private String url;

	public Photo() {
		super();
		Random rand = new Random();
		this.id = rand.nextInt();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
