package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Skill database table.
 * 
 */
@Entity
@Table(name="Skill")
@NamedQuery(name="Skill.findAll", query="SELECT s FROM Skill s")
public class Skill implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SkillPK id;

	private String name;

	private boolean privacy;

	private String type;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_id")
	private User user;

	public Skill() {
	}

	public SkillPK getId() {
		return this.id;
	}

	public void setId(SkillPK id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getPrivacy() {
		return this.privacy;
	}

	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}