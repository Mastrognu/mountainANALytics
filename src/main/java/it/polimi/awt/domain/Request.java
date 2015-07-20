package it.polimi.awt.domain;

import java.util.ArrayList;
import java.util.List;

public class Request {

	private String query;
	private List<Photo> response = new ArrayList<Photo>();
	private boolean flickrOk = true;
	private boolean panoramioOk = true;

	public String getQuery(){
		return query;
	}

	public void setQuery(String query){
		this.query = query;
	}

	public List<Photo> getResponse(){
		return response;
	}

	public void setResponse(List<Photo> response){
		this.response = response;
	}

	public boolean isFlickrOk() {
		return flickrOk;
	}

	public void setFlickrOk(boolean flickrOk) {
		this.flickrOk = flickrOk;
	}	

	public boolean isPanoramioOk() {
		return panoramioOk;
	}

	public void setPanoramioOk(boolean panoramioOk) {
		this.panoramioOk = panoramioOk;
	}

	public void addToResponse(List<Photo> photoList) {
		this.response.addAll(photoList);
	}
}
