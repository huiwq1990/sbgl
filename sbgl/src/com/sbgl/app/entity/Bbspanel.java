package com.sbgl.app.entity;

import java.util.Date;

/**
 * Bbspanel entity. @author MyEclipse Persistence Tools
 */

public class Bbspanel extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Date createtime;
	private String createuser;
	private Integer status;

	// Constructors

	/** default constructor */
	public Bbspanel() {
	}

	/** minimal constructor */
	public Bbspanel(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Bbspanel(Integer id, String name, Date createtime,
			String createuser, Integer status) {
		this.id = id;
		this.name = name;
		this.createtime = createtime;
		this.createuser = createuser;
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

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
