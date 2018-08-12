package dao;

import java.util.List;

import model.Skill;
import model.User;

public interface SkillDAO {
	public Skill find(int id);

	public List<Skill> list();

	public void create(Skill sk);

	public List<Skill> findByUser(User user);

	public void delete(Skill sk);

}
