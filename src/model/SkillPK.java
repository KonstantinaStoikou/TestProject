package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Skill database table.
 * 
 */
@Embeddable
public class SkillPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int id;

	@Column(name="User_id", insertable=false, updatable=false)
	private int user_id;

	public SkillPK() {
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return this.user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SkillPK)) {
			return false;
		}
		SkillPK castOther = (SkillPK)other;
		return 
			(this.id == castOther.id)
			&& (this.user_id == castOther.user_id);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.user_id;
		
		return hash;
	}
}