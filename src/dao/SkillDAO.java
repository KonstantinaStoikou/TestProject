package dao;

import java.util.List;

import model.Skill;

public interface SkillDAO {
	public Skill find(int id);

	public List<Skill> list();

	public void create(Skill sk);

	public void delete(Skill sk);

}
