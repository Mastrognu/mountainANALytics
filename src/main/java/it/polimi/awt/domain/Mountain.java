package it.polimi.awt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mountain {

	@Id private String mountainID;
	@Column(unique=true) private String name;
	private double latitude;
	private double longitude;
	private double elevation;

	public String getMountainID() {
		return mountainID;
	}

	public void setMountainID(String mountainID) {
		this.mountainID = mountainID;
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

	public double getElevation() {
		return elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

}
