package it.polimi.awt.domain;

public class Response {

	private String name;
	private double latitude;
	private double longitude;
	private QueryType type;

	public Response() {}

	public Response(QueryType type) {
		this.type = type;
	}

	public Response(String name, double latitude, double longitude, QueryType type) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.type = type;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public QueryType getType() {
		return type;
	}
	public void setType(QueryType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Response [name=" + name + ", lat=" + latitude + ", lng=" + longitude
				+ ", type=" + type + "]";
	}
}
