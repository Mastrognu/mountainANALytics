package it.polimi.awt.repository;

import it.polimi.awt.domain.Mountain;

import java.util.List;

public interface MountainRepository {

	public List<Mountain> findAll();
	public Mountain find(int mountainID);
}
