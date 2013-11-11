package com.sbgl.app.entity;

import java.util.Date;

/**
 * Bbstag entity. @author MyEclipse Persistence Tools
 */

public class Bbstag extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Date createtime;
	private Integer issystemtag;
	private Integer status;

	// Constructors

	/** default constructor */
	public Bbstag() {
	}

	/** minimal constructor */
	public Bbstag(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Bbstag(Integer id, String name, Date createtime,
			Integer issystemtag, Integer status) {
		this.id = id;
		this.name = name;
		this.createtime = createtime;
		this.issystemtag = issystemtag;
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

	public Integer getIssystemtag() {
		return this.issystemtag;
	}

	public void setIssystemtag(Integer issystemtag) {
		this.issystemtag = issystemtag;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
