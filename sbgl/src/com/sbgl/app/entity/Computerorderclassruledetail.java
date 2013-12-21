package com.sbgl.app.entity;

/**
 * Computerorderclassruledetail entity. @author MyEclipse Persistence Tools
 */

public class Computerorderclassruledetail extends com.sbgl.app.dao.DaoAbs
		implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer computerorderclassruleid;
	private Integer allowedcomputermodelid;

	// Constructors

	/** default constructor */
	public Computerorderclassruledetail() {
	}

	/** minimal constructor */
	public Computerorderclassruledetail(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Computerorderclassruledetail(Integer id,
			Integer computerorderclassruleid, Integer allowedcomputermodelid) {
		this.id = id;
		this.computerorderclassruleid = computerorderclassruleid;
		this.allowedcomputermodelid = allowedcomputermodelid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getComputerorderclassruleid() {
		return this.computerorderclassruleid;
	}

	public void setComputerorderclassruleid(Integer computerorderclassruleid) {
		this.computerorderclassruleid = computerorderclassruleid;
	}

	public Integer getAllowedcomputermodelid() {
		return this.allowedcomputermodelid;
	}

	public void setAllowedcomputermodelid(Integer allowedcomputermodelid) {
		this.allowedcomputermodelid = allowedcomputermodelid;
	}

}
