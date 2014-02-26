package com.sbgl.app.entity;

import java.util.Date;

/**
 * Msgreceive entity. @author MyEclipse Persistence Tools
 */

public class Msgreceive extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer senderid;
	private Integer receiverid;
	private String title;
	private String content;
	private Integer type;
	private Date sendtime;
	private Date readtime;
	private Integer status;

	// Constructors

	/** default constructor */
	public Msgreceive() {
	}

	/** minimal constructor */
	public Msgreceive(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Msgreceive(Integer id, Integer senderid, Integer receiverid,
			String title, String content, Integer type, Date sendtime,
			Date readtime, Integer status) {
		this.id = id;
		this.senderid = senderid;
		this.receiverid = receiverid;
		this.title = title;
		this.content = content;
		this.type = type;
		this.sendtime = sendtime;
		this.readtime = readtime;
		this.status = status;
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

	public Integer getReceiverid() {
		return this.receiverid;
	}

	public void setReceiverid(Integer receiverid) {
		this.receiverid = receiverid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public Date getReadtime() {
		return this.readtime;
	}

	public void setReadtime(Date readtime) {
		this.readtime = readtime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
