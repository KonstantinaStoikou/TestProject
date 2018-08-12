package dao;

import java.util.List;

import model.Job_Skill;

public interface Job_SkillDAO {
	public Job_Skill find(int id);

	public List<Job_Skill> list();

	public void create(Job_Skill jobSk);

	public void delete(Job_Skill jobSk);
}
