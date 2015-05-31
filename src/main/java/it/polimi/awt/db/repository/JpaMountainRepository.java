package it.polimi.awt.db.repository;

import it.polimi.awt.db.domain.Mountain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class JpaMountainRepository implements MountainRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Mountain> findAll() {
		TypedQuery<Mountain> query = em.createQuery("SELECT * FROM Mountain", Mountain.class);
		return query.getResultList();
	}

	@Override
	public Mountain find(int mountainID) {
		return em.find(Mountain.class, mountainID);
	}
}