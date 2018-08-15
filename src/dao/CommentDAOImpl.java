package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Comment;

public class CommentDAOImpl implements CommentDAO {

	@Override
	public Comment find(int id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Comment com = em.find(Comment.class, id);
		return com;
	}

	@Override
	public List<Comment> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Comment.findAll");
		query.setHint("eclipselink.refresh", "true");
		@SuppressWarnings("unchecked")
		List<Comment> com = query.getResultList();
		return com;
	}

	@Override
	public void create(Comment com) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(com);
		com.getUser().addComment(com);
		em.getTransaction().commit();

	}

	@Override
	public void delete(Comment com) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.remove(com);
		com.getUser().removeComment(com);
		em.getTransaction().commit();
	}

}
