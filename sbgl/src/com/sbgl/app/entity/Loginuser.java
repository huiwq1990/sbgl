package com.sbgl.app.entity;

import java.util.Date;
import java.util.Date;

/**
 * Loginuser entity. @author MyEclipse Persistence Tools
 */

public class Loginuser extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Date createtime;
	private Integer status;

	// Constructors

	/** default constructor */
	public Loginuser() {
	}

	/** minimal constructor */
	public Loginuser(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Loginuser(Integer id, String name, Date createtime,
			Integer status) {
		this.id = id;
		this.name = name;
		this.createtime = createtime;
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
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
