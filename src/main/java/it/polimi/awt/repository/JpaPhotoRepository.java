package it.polimi.awt.repository;

import java.util.List;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Photo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class JpaPhotoRepository implements PhotoRepository {

	@PersistenceContext
	private EntityManager em;

	public void insertPhoto(Photo photo) {
		em.persist(photo);
	}

	public List<Mountain> mountainInDb(Mountain mquery) {
		TypedQuery<Mountain> tqm = em.createQuery("SELECT m FROM Mountain m WHERE name LIKE \'" + mquery.getName() + "\'", Mountain.class);
		for (int i = 0; i < tqm.getResultList().size(); i++)
			System.out.println("AAAAAAAAAAAAAAAAAAA tqm= "+ tqm.getResultList().get(i));
		return null;
	}
}
