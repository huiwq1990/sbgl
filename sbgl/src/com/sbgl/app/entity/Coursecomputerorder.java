package com.sbgl.app.entity;

/**
 * Coursecomputerorder entity. @author MyEclipse Persistence Tools
 */

public class Coursecomputerorder extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer semesterid;
	private Integer courseid;
	private Integer computerorderid;
	private Integer status;

	// Constructors

	/** default constructor */
	public Coursecomputerorder() {
	}

	/** minimal constructor */
	public Coursecomputerorder(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Coursecomputerorder(Integer id, Integer semesterid,
			Integer courseid, Integer computerorderid, Integer status) {
		this.id = id;
		this.semesterid = semesterid;
		this.courseid = courseid;
		this.computerorderid = computerorderid;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSemesterid() {
		return this.semesterid;
	}

	public void setSemesterid(Integer semesterid) {
		this.semesterid = semesterid;
	}

	public Integer getCourseid() {
		return this.courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}

	public Integer getComputerorderid() {
		return this.computerorderid;
	}

	public void setComputerorderid(Integer computerorderid) {
		this.computerorderid = computerorderid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}