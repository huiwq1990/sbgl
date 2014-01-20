package com.sbgl.app.entity;

import java.util.Date;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */

public class Course extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String description;
	private Integer manager;
	private Date addTime;

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** minimal constructor */
	public Course(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Course(Integer id, String name, String description, Integer manager,
			Date addTime) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.manager = manager;
		this.addTime = addTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getManager() {
		return this.manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}
