package dao;

import java.util.List;

import model.Job;

public interface JobDAO {
	public Job find(int id);

	public List<Job> list();

	public void create(Job job);

	public void delete(Job job);

}