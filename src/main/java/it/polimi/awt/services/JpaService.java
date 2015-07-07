package it.polimi.awt.services;

import java.util.List;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Photo;
import it.polimi.awt.domain.Province;
import it.polimi.awt.repository.IJpaGenericAccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JpaService implements IJpaService {

	@Autowired
	private IJpaGenericAccess jpa;

	public void insertPhoto(Photo photo) {
		jpa.insertPhoto(photo);
	}

	public List<Mountain> mountainInDb(Mountain mquery) {
		return jpa.mountainInDb(mquery);
	}

	public Province isThisQueryAProvince(String queryText) {
		return jpa.findProvinceInDb(queryText);
	}
}