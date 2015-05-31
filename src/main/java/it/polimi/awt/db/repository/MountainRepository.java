package it.polimi.awt.db.repository;

import it.polimi.awt.db.domain.Mountain;

import java.util.List;

public interface MountainRepository {

	public List<Mountain> findAll();
	public Mountain find(int mountainID);
}
