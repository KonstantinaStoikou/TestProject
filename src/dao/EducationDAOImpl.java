package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Education;
import model.EducationPK;
import model.Experience;
import model.ExperiencePK;
import model.User;

public class EducationDAOImpl implements EducationDAO {
	
	@Override
	public Education find(EducationPK pk) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Education ed = em.find(Education.class, pk); 
        return ed;
	}

	@Override
	public List<Education> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Education.findAll");
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
	public List<Education> findByUser(User user) {
		String queryString = "SELECT e FROM Education e WHERE e.user = :user";
		
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery(queryString);
		query.setParameter("user", user);
		@SuppressWarnings("unchecked")
		List<Education> ed = query.getResultList();
		if (ed.isEmpty()) {
			return null;
		} else {
			return ed;
		}
	}

	@Override
	public void delete(Education ed) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.remove(ed);
		em.getTransaction().commit();	
		em.getTransaction().begin();
		ed.getUser().removeEducation(ed);
		em.getTransaction().commit();
	}

	@Override
	public Education findById(int id) {
		String queryString = "SELECT e FROM Education e WHERE e.id.id = :id";
		
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery(queryString);
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Education> ed = query.getResultList();
		if (ed.isEmpty()) {
			return null;
		} else {
			return ed.get(0);
		}
	}
	
	

}
