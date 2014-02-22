package com.sbgl.app.entity;

/**
 * Courseschedule entity. @author MyEclipse Persistence Tools
 */

public class Courseschedule extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer courseid;
	private Integer semester;
	private Integer week;
	private Integer day;
	private Integer period;
	private Integer adduserid;
	private Integer status;

	// Constructors

	/** default constructor */
	public Courseschedule() {
	}

	/** minimal constructor */
	public Courseschedule(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Courseschedule(Integer id, Integer courseid, Integer semester,
			Integer week, Integer day, Integer period, Integer adduserid,
			Integer status) {
		this.id = id;
		this.courseid = courseid;
		this.semester = semester;
		this.week = week;
		this.day = day;
		this.period = period;
		this.adduserid = adduserid;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCourseid() {
		return this.courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}

	public Integer getSemester() {
		return this.semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public Integer getWeek() {
		return this.week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getDay() {
		return this.day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getPeriod() {
		return this.period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getAdduserid() {
		return this.adduserid;
	}

	public void setAdduserid(Integer adduserid) {
		this.adduserid = adduserid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}