package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Education;

public class EducationDAOImpl implements EducationDAO {

	@Override
	public Education find(int id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Education ed = em.find(Education.class, id);
		return ed;
	}

	@Override
	public List<Education> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Education.findAll");
		query.setHint("eclipselink.refresh", "true");
		@SuppressWarnings("unchecked")
		List<Education> ed = query.getResultList();
		return ed;
	}

	@Override
	public void create(Education ed) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(ed);
		ed.getUser().addEducation(ed);
		em.getTransaction().commit();

	}

	@Override
	public void delete(Education ed) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.remove(ed);
		ed.getUser().removeEducation(ed);
		em.getTransaction().commit();
	}

}
