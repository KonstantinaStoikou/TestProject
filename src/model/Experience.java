package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Experience database table.
 * 
 */
@Entity
@Table(name="Experience")
@NamedQuery(name="Experience.findAll", query="SELECT e FROM Experience e")
public class Experience implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ExperiencePK id;

	private String company;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	private String position;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_id")
	private User user;

	public Experience() {
	}

	public ExperiencePK getId() {
		return this.id;
	}

	public void setId(ExperiencePK id) {
		this.id = id;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}