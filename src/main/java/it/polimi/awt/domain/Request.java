package it.polimi.awt.domain;

import java.util.List;

public class Request {

	private String query;
	private List<String> response;
	private boolean flickrOk = true;
	private boolean panoramioOk = true;

	public String getQuery(){
		return query;
	}

	public void setQuery(String query){
		this.query = query;
	}

	public List<String> getResponse(){
		return response;
	}

	public void setResponse(List<String> response){
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
}
