package com.sbgl.app.entity;

/**
 * Managergroup entity. @author MyEclipse Persistence Tools
 */

public class Managergroup extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Integer managertype;
	private Integer usertype;

	// Constructors

	/** default constructor */
	public Managergroup() {
	}

	/** minimal constructor */
	public Managergroup(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Managergroup(Integer id, Integer userid, Integer managertype,
			Integer usertype) {
		this.id = id;
		this.userid = userid;
		this.managertype = managertype;
		this.usertype = usertype;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getManagertype() {
		return this.managertype;
	}

	public void setManagertype(Integer managertype) {
		this.managertype = managertype;
	}

	public Integer getUsertype() {
		return this.usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

}