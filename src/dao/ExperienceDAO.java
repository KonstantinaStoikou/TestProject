package dao;

import java.util.List;

import model.Experience;
import model.User;

public interface ExperienceDAO {
	public Experience find(int id);

	public List<Experience> list();

	public void create(Experience exp);

	public List<Experience> findByUser(User user);

	public Experience findById(int id);

	public void delete(Experience exp);

}