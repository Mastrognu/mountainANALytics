package it.polimi.awt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "photo")
@NamedQuery(name = "findPhotoByUser", query = "SELECT p FROM Photo p WHERE p.userEmail = :userEmail")
public class Photo {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name="photoID") private int photoID;
	private String mountainName;
	private String url;
	private String userEmail;
	private double latitude;
	private double longitude;

	public Photo() {
	}

	public Photo(String userEmail, String mountainName, String url, double latitude, double longitude) {
		this.userEmail = userEmail;
		this.mountainName = mountainName;
		this.url = url;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getPhotoID() {
		return photoID;
	}
	public void setPhotoID(int photoID) {
		this.photoID = photoID;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getuserEmail() {
		return userEmail;
	}
	public void setuserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getMountainName() {
		return mountainName;
	}
	public void setMountainName(String mountainName) {
		this.mountainName = mountainName;
	}

	@Override
	public String toString() {
		return "Photo [photoID=" + photoID + ", mountainID=" + mountainName
				+ ", url=" + url + ", userID=" + userEmail + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}
}
