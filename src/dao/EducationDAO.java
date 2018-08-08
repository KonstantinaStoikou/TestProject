package dao;

import java.util.List;

import model.Education;
import model.User;

public interface EducationDAO {
	public Education find(int id);

	public List<Education> list();

	public void create(Education ed);

	public List<Education> findByUser(User user);

	public Education findById(int id);

	public void delete(Education ed);

}