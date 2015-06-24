package it.polimi.awt.repository;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Photo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class JpaGenericAccess implements IJpaGenericAccess {

	@PersistenceContext
	private EntityManager em;

	public void insertPhoto(Photo photo) {
		em.persist(photo);
	}

	//TODO Questo metodo può (e dovrebbe) ritornare Mountain e non List<Mountain>
	public List<Mountain> mountainInDb(Mountain mquery) {
		//TODO Errore "500 Session is closed!"
		TypedQuery<Mountain> tqm = em.createQuery("SELECT m FROM Mountain m WHERE name LIKE :name", Mountain.class);
		List<Mountain> list = tqm.setParameter("name", "%" + mquery.getName() + "%").getResultList();
		for (Mountain m : list) {
			System.out.println("JPA Result = "+ m);
		}
		return list;
	}
}
