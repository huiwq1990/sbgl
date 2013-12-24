package com.sbgl.app.entity;

import java.util.Date;

/**
 * Computerhomework entity. @author MyEclipse Persistence Tools
 */

public class Computerhomework extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer computerorderclassruleid;
	private String content;
	private Integer createuserid;
	private String attachment;
	private Integer status;
	private Date createtime;

	// Constructors

	/** default constructor */
	public Computerhomework() {
	}

	/** minimal constructor */
	public Computerhomework(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Computerhomework(Integer id, String name,
			Integer computerorderclassruleid, String content,
			Integer createuserid, String attachment, Integer status,
			Date createtime) {
		this.id = id;
		this.name = name;
		this.computerorderclassruleid = computerorderclassruleid;
		this.content = content;
		this.createuserid = createuserid;
		this.attachment = attachment;
		this.status = status;
		this.createtime = createtime;
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

	public Integer getComputerorderclassruleid() {
		return this.computerorderclassruleid;
	}

	public void setComputerorderclassruleid(Integer computerorderclassruleid) {
		this.computerorderclassruleid = computerorderclassruleid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}

	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
