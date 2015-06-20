package it.polimi.awt.services;

import java.util.List;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Photo;

public interface IPhotoService {

	public void insertPhoto(Photo photo);
	public List<Mountain> mountainInDb(Mountain mquery);
}
