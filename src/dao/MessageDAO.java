package dao;

import java.util.List;

import model.Message;
import model.MessagePK;

public interface MessageDAO 
{
	public Message find(MessagePK msg);

    public List<Message> list();

    public void create(Message msg);

}
