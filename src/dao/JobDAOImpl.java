package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Job;

public class JobDAOImpl implements JobDAO {

	@Override
	public Job find(int id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Job job = em.find(Job.class, id);
		return job;
	}

	@Override
	public List<Job> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Job.findAll");
		query.setHint("eclipselink.refresh", "true");
		@SuppressWarnings("unchecked")
		List<Job> job = query.getResultList();
		return job;
	}

	@Override
	public void create(Job job) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(job);
		job.getUser().addJob(job);
		em.getTransaction().commit();

	}

	@Override
	public void delete(Job job) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.remove(job);
		job.getUser().removeJob(job);
		em.getTransaction().commit();
	}

}
