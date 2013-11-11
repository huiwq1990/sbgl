package com.sbgl.app.entity;

/**
 * Usergrouprelation entity. @author MyEclipse Persistence Tools
 */

public class Usergrouprelation extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer groupid;
	private Integer userid;
	private Integer status;

	// Constructors

	/** default constructor */
	public Usergrouprelation() {
	}

	/** minimal constructor */
	public Usergrouprelation(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Usergrouprelation(Integer id, Integer groupid, Integer userid,
			Integer status) {
		this.id = id;
		this.groupid = groupid;
		this.userid = userid;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
