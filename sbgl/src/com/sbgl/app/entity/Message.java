package com.sbgl.app.entity;

import java.util.Date;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer senderid;
	private String content;
	private Date createtime;
	private Integer status;
	private Integer isbigfile;
	private String filepath;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** minimal constructor */
	public Message(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Message(Integer id, Integer senderid, String content,
			Date createtime, Integer status, Integer isbigfile,
			String filepath) {
		this.id = id;
		this.senderid = senderid;
		this.content = content;
		this.createtime = createtime;
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

	public Integer getSenderid() {
		return this.senderid;
	}

	public void setSenderid(Integer senderid) {
		this.senderid = senderid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatetime() {
		return this.createtime;
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

	public Integer getIsbigfile() {
		return this.isbigfile;
	}

	public void setIsbigfile(Integer isbigfile) {
		this.isbigfile = isbigfile;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
