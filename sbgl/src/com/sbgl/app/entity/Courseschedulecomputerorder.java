package com.sbgl.app.entity;

/**
 * Courseschedulecomputerorder entity. @author MyEclipse Persistence Tools
 */

public class Courseschedulecomputerorder extends com.sbgl.app.dao.DaoAbs
		implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer computercoursescheduleid;
	private Integer computerorderid;
	private Integer status;

	// Constructors

	/** default constructor */
	public Courseschedulecomputerorder() {
	}

	/** minimal constructor */
	public Courseschedulecomputerorder(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Courseschedulecomputerorder(Integer id,
			Integer computercoursescheduleid, Integer computerorderid,
			Integer status) {
		this.id = id;
		this.computercoursescheduleid = computercoursescheduleid;
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

	public Integer getComputercoursescheduleid() {
		return this.computercoursescheduleid;
	}

	public void setComputercoursescheduleid(Integer computercoursescheduleid) {
		this.computercoursescheduleid = computercoursescheduleid;
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