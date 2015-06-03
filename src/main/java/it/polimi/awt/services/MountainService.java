package it.polimi.awt.services;

import it.polimi.awt.domain.Mountain;

import java.util.List;

public interface MountainService {
	
	public List<Mountain> findAll();
	public Mountain find(int mountainID);

}
