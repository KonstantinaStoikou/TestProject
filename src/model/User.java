package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Users database table.
 * 
 */
@Entity
@Table(name="Users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String emai;

	private int password;

	public User() {
	}

	public String getEmai() {
		return this.emai;
	}

	public void setEmai(String emai) {
		this.emai = emai;
	}

	public int getPassword() {
		return this.password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

}