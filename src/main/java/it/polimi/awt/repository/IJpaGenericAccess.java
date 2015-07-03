package it.polimi.awt.repository;

import java.util.List;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Photo;

public interface IJpaGenericAccess {

	public void insertPhoto(Photo photo);
	public List<Mountain> mountainInDb(Mountain mquery);
	public boolean isThisQueryAProvince(String queryText);
}
