package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Education database table.
 * 
 */
@Entity
@NamedQuery(name="Education.findAll", query="SELECT e FROM Education e")
public class Education implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EducationPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="end_year")
	private Date endYear;

	private String institution;

	@Temporal(TemporalType.DATE)
	@Column(name="start_year")
	private Date startYear;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_id")
	private User user;

	public Education() {
	}

	public EducationPK getId() {
		return this.id;
	}

	public void setId(EducationPK id) {
		this.id = id;
	}

	public Date getEndYear() {
		return this.endYear;
	}

	public void setEndYear(Date endYear) {
		this.endYear = endYear;
	}

	public String getInstitution() {
		return this.institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public Date getStartYear() {
		return this.startYear;
	}

	public void setStartYear(Date startYear) {
		this.startYear = startYear;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}