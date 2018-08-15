package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Post;

public class PostDAOImpl implements PostDAO {

	@Override
	public Post find(int id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Post post = em.find(Post.class, id);
		return post;
	}

	@Override
	public List<Post> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Post.findAll");
		query.setHint("eclipselink.refresh", "true");
		@SuppressWarnings("unchecked")
		List<Post> post = query.getResultList();
		return post;
	}

	@Override
	public void create(Post post) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(post);
		post.getUser().addPosts(post);
		em.getTransaction().commit();

	}

	@Override
	public void delete(Post post) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.remove(post);
		post.getUser().removePosts(post);
		em.getTransaction().commit();
	}

}
