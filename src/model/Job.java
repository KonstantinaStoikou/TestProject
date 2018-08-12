package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the Job database table.
 * 
 */
@Entity
@Table(name = "Job")
@NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String company;

	@Lob
	private String description;

	private String position;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "User_id")
	private User user;

	// bi-directional many-to-one association to Job_Skill
	@OneToMany(mappedBy = "job")
	private List<Job_Skill> jobSkills;

	public Job() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Job_Skill> getJobSkills() {
		return this.jobSkills;
	}

	public void setJobSkills(List<Job_Skill> jobSkills) {
		this.jobSkills = jobSkills;
	}

	public Job_Skill addJobSkill(Job_Skill jobSkill) {
		getJobSkills().add(jobSkill);
		jobSkill.setJob(this);

		return jobSkill;
	}

	public Job_Skill removeJobSkill(Job_Skill jobSkill) {
		getJobSkills().remove(jobSkill);
		jobSkill.setJob(null);

		return jobSkill;
	}

}