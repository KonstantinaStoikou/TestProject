package dao;

import java.util.List;

import model.Message;

public interface MessageDAO {
	public Message find(int id);

	public List<Message> list();

	public void create(Message msg);

}
