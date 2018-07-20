package dao;

import java.util.List;

import model.User;

public interface UserDAO 
{
	public User find(int id);

    public List<User> list();

    public void create(User user);
    
    public User findByEmail(String email);

}
