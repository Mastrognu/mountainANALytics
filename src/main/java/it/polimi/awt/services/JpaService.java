package it.polimi.awt.services;

import java.util.List;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Photo;
import it.polimi.awt.repository.IJpaGenericAccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JpaService implements IJpaService {

	@Autowired
	private IJpaGenericAccess photoRepository;

	public void insertPhoto(Photo photo) {
		photoRepository.insertPhoto(photo);
	}

	public List<Mountain> mountainInDb(Mountain mquery) {
		return photoRepository.mountainInDb(mquery);
	}

}