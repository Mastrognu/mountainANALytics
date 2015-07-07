package it.polimi.awt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "province")
public class Province {

	@Id @Column(nullable = false) private String provinceID;
	@Column(nullable = false) private String name;
	@Column(nullable = false) private String region;

	public Province() {
	}

	public String getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "Province [provinceID=" + provinceID + ", name=" + name
				+ ", region=" + region + "]";
	}
}
