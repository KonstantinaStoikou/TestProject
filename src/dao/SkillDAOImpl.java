package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Skill;

public class SkillDAOImpl implements SkillDAO {

	@Override
	public Skill find(int id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Skill sk = em.find(Skill.class, id);
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
	public void delete(Skill sk) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.remove(sk);
		sk.getUser().removeSkill(sk);
		em.getTransaction().commit();
	}

}
