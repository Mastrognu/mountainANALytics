package it.polimi.awt.db.service;

import it.polimi.awt.db.domain.Mountain;

import java.util.List;

public interface MountainService {
	
	public List<Mountain> findAll();
	public Mountain find(int mountainID);

}
