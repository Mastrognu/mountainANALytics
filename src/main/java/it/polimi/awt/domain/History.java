package it.polimi.awt.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "search")
public class History implements Serializable {

	private static final long serialVersionUID = 5117568888708854860L;

	@Id private String userEmail;
	@Id private Date timestamp;
	private int photoID;
	private String query;

	public History() {
		super();
	}

	public History(String userEmail, Date timestamp, int photoID, String query) {
		super();
		this.userEmail = userEmail;
		this.timestamp = timestamp;
		this.photoID = photoID;
		this.query = query;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getPhotoID() {
		return photoID;
	}

	public void setPhotoID(int photoID) {
		this.photoID = photoID;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "History [userEmail=" + userEmail + ", timestamp=" + timestamp
				+ ", photoID=" + photoID + ", query=" + query + "]";
	}
}
