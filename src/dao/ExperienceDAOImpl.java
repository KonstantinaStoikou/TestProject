package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Experience;
import model.ExperiencePK;
import model.User;

public class ExperienceDAOImpl implements ExperienceDAO {
	
	@Override
	public Experience find(ExperiencePK pk) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Experience exp = em.find(Experience.class, pk); 
        return exp;
	}

	@Override
	public List<Experience> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Experience.findAll");
		@SuppressWarnings("unchecked")
		List<Experience> exp = query.getResultList();  
        return exp;
	}

	@Override
	public void create(Experience exp) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(exp);
		em.getTransaction().commit();
		
	}

	@Override
	public List<Experience> findByUser(User user) {
		String queryString = "SELECT e FROM Experience e WHERE e.user = :user";
		
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery(queryString);
		query.setParameter("user", user);
		@SuppressWarnings("unchecked")
		List<Experience> exp = query.getResultList();
		if (exp.isEmpty()) {
			return null;
		} else {
			return exp;
		}
	}

	@Override
	public void delete(Experience exp) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.remove(exp);
		em.getTransaction().commit();	
	}

	@Override
	public Experience findById(int id) {
		String queryString = "SELECT e FROM Experience e WHERE e.id.id = :id";
		
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery(queryString);
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Experience> exp = query.getResultList();
		if (exp.isEmpty()) {
			return null;
		} else {
			return exp.get(0);
		}
	}
	
	

}
