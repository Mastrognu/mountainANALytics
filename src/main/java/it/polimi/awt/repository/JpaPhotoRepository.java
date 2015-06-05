package it.polimi.awt.repository;

import it.polimi.awt.domain.Photo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class JpaPhotoRepository implements PhotoRepository{

	@PersistenceContext
	private EntityManager em;
	
	public void insertPhoto (String url) {
		em.persist(url);
	}
}
