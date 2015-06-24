package it.polimi.awt.repository;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Photo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class JpaPhotoRepository implements PhotoRepository {

	@PersistenceContext
	private EntityManager em;

	public void insertPhoto(Photo photo) {
		em.persist(photo);
	}

	public List<Mountain> mountainInDb(Mountain mquery) {
		//TODO Errore "500 Session is closed!"
		TypedQuery<Mountain> tqm = em.createQuery("SELECT m FROM Mountain m WHERE name LIKE :name", Mountain.class);
		List<Mountain> list = tqm.setParameter("name", "%" + mquery.getName() + "%").getResultList();
		/*for (int i = 0; i < tqm.getResultList().size(); i++)
			System.out.println("AAAAAAAAAAAAAAAAAAA tqm= "+ tqm.getResultList().get(i));*/
		for (Mountain m : list) {
			System.out.println("JPA Result = "+ m);
		}
		return null;
	}
}
