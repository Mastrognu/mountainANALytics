package it.polimi.awt.services;

import it.polimi.awt.domain.Photo;
import it.polimi.awt.repository.PhotoRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private PhotoRepository photoRepository;
	
	public void insertPhoto(Photo photo) {
		
		photoRepository.insertPhoto(photo);
	}


}