package it.polimi.awt.services;

import java.util.List;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Photo;
import it.polimi.awt.repository.PhotoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhotoService implements IPhotoService {

	@Autowired
	private PhotoRepository photoRepository;

	public void insertPhoto(Photo photo) {
		photoRepository.insertPhoto(photo);
	}

	public List<Mountain> mountainInDb(Mountain mquery) {
		return photoRepository.mountainInDb(mquery);
	}

}