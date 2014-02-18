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
	private Integer type;
	private Integer coursetype;
	private Integer languagetype;
	private Integer adduserid;
	private Integer teacherid;
	private Date addtime;
	private Integer status;

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** minimal constructor */
	public Course(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Course(Integer id, String name, String description, Integer type,
			Integer coursetype, Integer languagetype, Integer adduserid,
			Integer teacherid, Date addtime, Integer status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.coursetype = coursetype;
		this.languagetype = languagetype;
		this.adduserid = adduserid;
		this.teacherid = teacherid;
		this.addtime = addtime;
		this.status = status;
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

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCoursetype() {
		return this.coursetype;
	}

	public void setCoursetype(Integer coursetype) {
		this.coursetype = coursetype;
	}

	public Integer getLanguagetype() {
		return this.languagetype;
	}

	public void setLanguagetype(Integer languagetype) {
		this.languagetype = languagetype;
	}

	public Integer getAdduserid() {
		return this.adduserid;
	}

	public void setAdduserid(Integer adduserid) {
		this.adduserid = adduserid;
	}

	public Integer getTeacherid() {
		return this.teacherid;
	}

	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}

	public Date getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
