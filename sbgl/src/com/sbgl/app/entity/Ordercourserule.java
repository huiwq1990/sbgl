package com.sbgl.app.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Ordercourserule entity. @author MyEclipse Persistence Tools
 */

public class Ordercourserule extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer courseruleid;
	private Integer courseid;
	private Integer teacherid;
	private String courserulename;
	private Date createtime;

	// Constructors

	/** default constructor */
	public Ordercourserule() {
	}

	/** minimal constructor */
	public Ordercourserule(Integer courseruleid, Integer courseid) {
		this.courseruleid = courseruleid;
		this.courseid = courseid;
	}

	/** full constructor */
	public Ordercourserule(Integer courseruleid, Integer courseid,
			Integer teacherid, String courserulename, Date createtime) {
		this.courseruleid = courseruleid;
		this.courseid = courseid;
		this.teacherid = teacherid;
		this.courserulename = courserulename;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getCourseruleid() {
		return this.courseruleid;
	}

	public void setCourseruleid(Integer courseruleid) {
		this.courseruleid = courseruleid;
	}

	public Integer getCourseid() {
		return this.courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}

	public Integer getTeacherid() {
		return this.teacherid;
	}

	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}

	public String getCourserulename() {
		return this.courserulename;
	}

	public void setCourserulename(String courserulename) {
		this.courserulename = courserulename;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}