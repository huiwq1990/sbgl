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
	private String userid;
	private String password;
	private Date createtime;
	private Integer status;
	private String roletype;
	

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
			Integer status,String userid,String password,String roletype) {
		this.id = id;
		this.name = name;
		this.createtime = createtime;
		this.status = status;
		this.password=password;
		this.userid=userid;
		this.roletype = roletype;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

}
