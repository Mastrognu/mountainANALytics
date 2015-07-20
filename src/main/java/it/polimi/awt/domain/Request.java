package it.polimi.awt.domain;

import java.util.ArrayList;
import java.util.List;

public class Request {

	private String query;
	private List<Photo> response = new ArrayList<Photo>();
	private double queryLatitude;
	private double queryLongitude;
	private String queryName;
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

	public double getQueryLatitude() {
		return queryLatitude;
	}

	public void setQueryLatitude(double queryLatitude) {
		this.queryLatitude = queryLatitude;
	}

	public double getQueryLongitude() {
		return queryLongitude;
	}

	public void setQueryLongitude(double queryLongitude) {
		this.queryLongitude = queryLongitude;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public void addToResponse(List<Photo> photoList) {
		this.response.addAll(photoList);
	}
}
