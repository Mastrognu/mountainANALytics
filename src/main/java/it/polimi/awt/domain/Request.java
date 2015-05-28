package it.polimi.awt.domain;

public class Request {

	private String query;
	private String response;
	private boolean flickrOk = true;
	private boolean panoramioOk = true;

	public String getQuery(){
		return query;
	}

	public void setQuery(String query){
		this.query = query;
	}

	public String getResponse(){
		return response;
	}

	public void setResponse(String response){
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
