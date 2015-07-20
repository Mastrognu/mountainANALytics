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

	public List<Mountain> getMultiMountain(Mountain mountain) {
		TypedQuery<Mountain> tqm = em.createNamedQuery("findMountain", Mountain.class);
		List<Mountain> list = tqm.setParameter("name", "%" + mountain.getName() + "%").getResultList();
		for (Mountain m : list)
			System.out.println("Mountain found in db = "+ m);
		return list;
	}

	public Mountain getMountain(String mountainName) {
		TypedQuery<Mountain> tqp = em.createNamedQuery("findMountain", Mountain.class);
		List<Mountain> list = tqp.setParameter("name", "%" + mountainName + "%").getResultList();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	public Province getProvince(String provinceName) {
		TypedQuery<Province> tqp = em.createNamedQuery("findProvince", Province.class);
		List<Province> list = tqp.setParameter("name", "%" + provinceName + "%").getResultList();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
}
