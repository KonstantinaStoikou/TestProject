package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpautils.EntityManagerHelper;
import model.Message;

public class MessageDAOImpl implements MessageDAO {

	@Override
	public Message find(int id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Message msg = em.find(Message.class, id);
		return msg;
	}

	@Override
	public List<Message> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query query = em.createNamedQuery("Message.findAll");
		query.setHint("eclipselink.refresh", "true");
		@SuppressWarnings("unchecked")
		List<Message> msg = query.getResultList();
		return msg;
	}

	@Override
	public void create(Message msg) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(msg);
		msg.getSender().addSentMessages(msg);
		msg.getReceiver().addReceivedMessages(msg);
		em.getTransaction().commit();
	}

}