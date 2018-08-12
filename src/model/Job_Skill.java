package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the Job_Skill database table.
 * 
 */
@Entity
@Table(name = "Job_Skill")
@NamedQuery(name = "Job_Skill.findAll", query = "SELECT j FROM Job_Skill j")
public class Job_Skill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	// bi-directional many-to-one association to Job
	@ManyToOne
	@JoinColumn(name = "Job_id")
	private Job job;

	public Job_Skill() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}