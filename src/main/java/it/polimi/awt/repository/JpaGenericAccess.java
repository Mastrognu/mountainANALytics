package it.polimi.awt.repository;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Photo;
import it.polimi.awt.domain.Province;
import it.polimi.awt.domain.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class JpaGenericAccess implements IJpaGenericAccess {

	@PersistenceContext
	private EntityManager em;

	public void insertPhoto(Photo photo, User user) {
		TypedQuery<Photo> tq = em.createNamedQuery("findPhoto", Photo.class);
		List<Photo> list = tq.setParameter("userEmail", user.getEmail()).setParameter("url", photo.getUrl()).getResultList();
		if (list.size() == 0) {
			em.persist(photo);
			System.out.println("Photo saved!");
		}			
	}

	public List<Photo> getPhotoByUser(User user) {
		TypedQuery<Photo> tq = em.createNamedQuery("findPhotoByUser", Photo.class);
		List<Photo> list = tq.setParameter("userEmail", user.getEmail()).getResultList();
		return list;
	}

	public List<Mountain> getMultiMountain(Mountain mountain) {
		TypedQuery<Mountain> tq = em.createNamedQuery("findMountain", Mountain.class);
		List<Mountain> list = tq.setParameter("name", "%" + mountain.getName() + "%").getResultList();
		for (Mountain m : list)
			System.out.println("Mountain found in db = "+ m);
		return list;
	}

	public Mountain getMountain(String mountainName) {
		TypedQuery<Mountain> tq = em.createNamedQuery("findMountain", Mountain.class);
		List<Mountain> list = tq.setParameter("name", "%" + mountainName + "%").getResultList();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	public Province getProvince(String provinceName) {
		TypedQuery<Province> tq = em.createNamedQuery("findProvince", Province.class);
		List<Province> list = tq.setParameter("name", "%" + provinceName + "%").getResultList();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	public boolean checkUserExistence(User user) {
		return em.find(User.class, user.getEmail()) != null ? true : false;
	}

	public void createUser(User user) {
		em.persist(user);
	}
}
