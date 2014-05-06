package com.sbgl.app.entity;

import java.util.Date;

/**
 * Courseconfig entity. @author MyEclipse Persistence Tools
 */

public class Courseconfig extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String schoolyear;
	private Integer semester;
	private Date firstday;
	private Date lastday;
	private Date firstweekfirstday;
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
			Date firstday, Date lastday, Date firstweekfirstday,
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

	public Date getFirstday() {
		return this.firstday;
	}

	public void setFirstday(Date firstday) {
		this.firstday = firstday;
	}

	public Date getLastday() {
		return this.lastday;
	}

	public void setLastday(Date lastday) {
		this.lastday = lastday;
	}

	public Date getFirstweekfirstday() {
		return this.firstweekfirstday;
	}

	public void setFirstweekfirstday(Date firstweekfirstday) {
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
