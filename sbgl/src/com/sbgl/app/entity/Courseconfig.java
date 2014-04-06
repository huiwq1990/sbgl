package com.sbgl.app.entity;

import java.sql.Timestamp;

/**
 * Courseconfig entity. @author MyEclipse Persistence Tools
 */

public class Courseconfig extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String schoolyear;
	private Integer semester;
	private Timestamp firstday;
	private Timestamp lastday;
	private Timestamp firstweekfirstday;
	private Integer weeknum;
	private Integer currentsemester;
	private Integer status;

	// Constructors

	/** default constructor */
	public Courseconfig() {
	}

	/** minimal constructor */
	public Courseconfig(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Courseconfig(Integer id, String schoolyear, Integer semester,
			Timestamp firstday, Timestamp lastday, Timestamp firstweekfirstday,
			Integer weeknum, Integer currentsemester, Integer status) {
		this.id = id;
		this.schoolyear = schoolyear;
		this.semester = semester;
		this.firstday = firstday;
		this.lastday = lastday;
		this.firstweekfirstday = firstweekfirstday;
		this.weeknum = weeknum;
		this.currentsemester = currentsemester;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSchoolyear() {
		return this.schoolyear;
	}

	public void setSchoolyear(String schoolyear) {
		this.schoolyear = schoolyear;
	}

	public Integer getSemester() {
		return this.semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public Timestamp getFirstday() {
		return this.firstday;
	}

	public void setFirstday(Timestamp firstday) {
		this.firstday = firstday;
	}

	public Timestamp getLastday() {
		return this.lastday;
	}

	public void setLastday(Timestamp lastday) {
		this.lastday = lastday;
	}

	public Timestamp getFirstweekfirstday() {
		return this.firstweekfirstday;
	}

	public void setFirstweekfirstday(Timestamp firstweekfirstday) {
		this.firstweekfirstday = firstweekfirstday;
	}

	public Integer getWeeknum() {
		return this.weeknum;
	}

	public void setWeeknum(Integer weeknum) {
		this.weeknum = weeknum;
	}

	public Integer getCurrentsemester() {
		return this.currentsemester;
	}

	public void setCurrentsemester(Integer currentsemester) {
		this.currentsemester = currentsemester;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}