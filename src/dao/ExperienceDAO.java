package dao;

import java.util.List;

import model.Experience;

public interface ExperienceDAO {
	public Experience find(int id);

	public List<Experience> list();

	public void create(Experience exp);

	public void delete(Experience exp);

}