package com.sbgl.app.entity;

import java.util.Date;

/**
 * Ordermessage entity. @author MyEclipse Persistence Tools
 */

public class Ordermessage extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sender;
	private Integer content;
	private Date sendtime;
	private Integer status;
	private Integer isbigfile;
	private Integer filepath;

	// Constructors

	/** default constructor */
	public Ordermessage() {
	}

	/** minimal constructor */
	public Ordermessage(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Ordermessage(Integer id, Integer sender, Integer content,
			Date sendtime, Integer status, Integer isbigfile,
			Integer filepath) {
		this.id = id;
		this.sender = sender;
		this.content = content;
		this.sendtime = sendtime;
		this.status = status;
		this.isbigfile = isbigfile;
		this.filepath = filepath;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSender() {
		return this.sender;
	}

	public void setSender(Integer sender) {
		this.sender = sender;
	}

	public Integer getContent() {
		return this.content;
	}

	public void setContent(Integer content) {
		this.content = content;
	}

	public Date getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsbigfile() {
		return this.isbigfile;
	}

	public void setIsbigfile(Integer isbigfile) {
		this.isbigfile = isbigfile;
	}

	public Integer getFilepath() {
		return this.filepath;
	}

	public void setFilepath(Integer filepath) {
		this.filepath = filepath;
	}

}
