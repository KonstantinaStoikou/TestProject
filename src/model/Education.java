package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Education database table.
 * 
 */
@Entity
@Table(name="Education")
@NamedQuery(name="Education.findAll", query="SELECT e FROM Education e")
public class Education implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EducationPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	private String institution;

	private String level;

	private boolean privacy;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getInstitution() {
		return this.institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public boolean getPrivacy() {
		return this.privacy;
	}

	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
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