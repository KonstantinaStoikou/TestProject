package dao;

import java.util.List;

import model.Skill;
import model.SkillPK;
import model.User;

public interface SkillDAO 
{
	public Skill find(SkillPK pk);

    public List<Skill> list();

    public void create(Skill sk);
    
    public List<Skill> findByUser(User user);
    
    public Skill findById(int id);
    
    public void delete(Skill sk);

}
