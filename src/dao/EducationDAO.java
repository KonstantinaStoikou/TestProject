package dao;

import java.util.List;

import model.Education;

public interface EducationDAO {
	public Education find(int id);

	public List<Education> list();

	public void create(Education ed);

	public void delete(Education ed);

}