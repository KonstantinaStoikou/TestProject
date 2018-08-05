package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Skill;
import model.SkillPK;
import model.User;

public class SkillDAOImpl implements SkillDAO {
	
	@Override
	public Skill find(SkillPK pk) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Skill sk = em.find(Skill.class, pk); 
        return sk;
	}

	@Override
	public List<Skill> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Skill.findAll");
		query.setHint("eclipselink.refresh", "true");
		@SuppressWarnings("unchecked")
		List<Skill> sk = query.getResultList();  
        return sk;
	}

	@Override
	public void create(Skill sk) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(sk);
		sk.getUser().addSkill(sk);
		em.getTransaction().commit();
		
	}

	@Override
	public List<Skill> findByUser(User user) {
		String queryString = "SELECT s FROM Skill s WHERE s.user = :user";
		
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery(queryString);
		query.setParameter("user", user);
		query.setHint("eclipselink.refresh", "true");
		@SuppressWarnings("unchecked")
		List<Skill> sk = query.getResultList();
		if (sk.isEmpty()) {
			return null;
		} else {
			return sk;
		}
	}

	@Override
	public void delete(Skill sk) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.remove(sk);
		em.getTransaction().commit();
		em.getTransaction().begin();
		sk.getUser().removeSkill(sk);
		em.getTransaction().commit();
	}

	@Override
	public Skill findById(int id) {
		String queryString = "SELECT s FROM Skill s WHERE s.id.id = :id";
		
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createQuery(queryString);
		query.setParameter("id", id);
		query.setHint("eclipselink.refresh", "true");
		@SuppressWarnings("unchecked")
		List<Skill> sk = query.getResultList();
		if (sk.isEmpty()) {
			return null;
		} else {
			return sk.get(0);
		}
	}
	
	

}
