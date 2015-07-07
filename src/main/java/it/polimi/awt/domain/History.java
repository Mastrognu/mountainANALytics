package it.polimi.awt.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "search")
public class History implements Serializable {

	private static final long serialVersionUID = 5117568888708854860L;
	@Id @JoinColumn(name = "mountainID") private int mountainID;
	@Id @JoinColumn(name = "photoID") private int photoID;
	@Id @JoinColumn(name = "userID") private int userID;
	private Date timestamp;

	public History() {
		super();
	}

	public History(int mountainID, int photoID, int userID, Date timestamp) {
		super();
		this.mountainID = mountainID;
		this.photoID = photoID;
		this.userID = userID;
		this.timestamp = timestamp;
	}

	public int getMountainID() {
		return mountainID;
	}

	public void setMountain(int mountainID) {
		this.mountainID = mountainID;
	}

	public int getPhotoID() {
		return photoID;
	}

	public void setPhotoID(int photoID) {
		this.photoID = photoID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUser(int userID) {
		this.userID = userID;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mountainID;
		result = prime * result + photoID;
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + userID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		History other = (History) obj;
		if (mountainID != other.mountainID)
			return false;
		if (photoID != other.photoID)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}
}
