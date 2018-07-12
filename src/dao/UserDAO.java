package dao;

import java.util.List;

import model.User;

public interface UserDAO 
{
	public User find(String email);

    public List<User> list();

    public int create(User user);

}
