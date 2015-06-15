package it.polimi.awt.domain;

public class Response {

	private String name;
	private double lat;
	private double lng;
//	public enum Type {
//		MOUNTAIN,
//		CITY;
//	}
	private String type;
	
	public Response(String name, double latitude, double longitude, String type) {
		this.name = name;
		this.lat = latitude;
		this.lng = longitude;
		this.setType(type);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
