package it.polimi.awt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mountain")
public class Mountain {

	@Id private int mountainID;
	@Column(nullable = false) private String name;
	@Column(nullable = false) private double latitude;
	@Column(nullable = false) private double longitude;
	private int elevation;
	private int drop;

	public Mountain() {
	}

	public Mountain(String name, double latitude, double longitude) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getDrop() {
		return drop;
	}

	public void setDrop(int drop) {
		this.drop = drop;
	}

	public int getMountainID() {
		return mountainID;
	}

	public void setMountainID(int mountainID) {
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

	public int getElevation() {
		return elevation;
	}

	public void setElevation(int elevation) {
		this.elevation = elevation;
	}

	@Override
	public String toString() {
		return "Mountain [mountainID=" + mountainID + ", name=" + name
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", elevation=" + elevation + ", drop=" + drop + "]";
	}
}