package it.polimi.awt.repository;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Photo;
import it.polimi.awt.domain.Province;

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

	public List<Mountain> mountainInDb(Mountain mquery) {
		TypedQuery<Mountain> tqm = em.createQuery("SELECT m FROM Mountain m WHERE name LIKE :name", Mountain.class);
		List<Mountain> list = tqm.setParameter("name", "%" + mquery.getName() + "%").getResultList();
		for (Mountain m : list)
			System.out.println("DB Mountain = "+ m);
		return list;
	}

	/**
	 *
	 * @param queryText The text to query in the province table
	 * @return true if the text searched is a province city
	 */
	public boolean isThisQueryAProvince(String queryText) {
		TypedQuery<Province> tqp = em.createQuery("SELECT p FROM Province p WHERE name LIKE :name", Province.class);
		List<Province> list = tqp.setParameter("name", "%" + queryText + "%").getResultList();
		if (list.size() > 0) {
			System.out.println("DB Province = " + list.get(0));
			return true;
		}
		return false;
	}
}
