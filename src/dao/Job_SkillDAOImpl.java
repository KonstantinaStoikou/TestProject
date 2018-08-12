package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Job_Skill;

public class Job_SkillDAOImpl implements Job_SkillDAO {

	@Override
	public Job_Skill find(int id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Job_Skill jobSk = em.find(Job_Skill.class, id);
		return jobSk;
	}

	@Override
	public List<Job_Skill> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Job_Skill.findAll");
		query.setHint("eclipselink.refresh", "true");
		@SuppressWarnings("unchecked")
		List<Job_Skill> jobSk = query.getResultList();
		return jobSk;
	}

	@Override
	public void create(Job_Skill jobSk) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(jobSk);
		jobSk.getJob().addJobSkill(jobSk);
		em.getTransaction().commit();

	}

	@Override
	public void delete(Job_Skill jobSk) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.remove(jobSk);
		jobSk.getJob().removeJobSkill(jobSk);
		em.getTransaction().commit();
	}

}
