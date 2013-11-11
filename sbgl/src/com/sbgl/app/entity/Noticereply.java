package com.sbgl.app.entity;

import java.util.Date;

/**
 * Noticereply entity. @author MyEclipse Persistence Tools
 */

public class Noticereply extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private String noticeId;
	private String cotent;
	private Date time;

	// Constructors

	/** default constructor */
	public Noticereply() {
	}

	/** minimal constructor */
	public Noticereply(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Noticereply(Integer id, Integer userId, String noticeId,
			String cotent, Date time) {
		this.id = id;
		this.userId = userId;
		this.noticeId = noticeId;
		this.cotent = cotent;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getCotent() {
		return this.cotent;
	}

	public void setCotent(String cotent) {
		this.cotent = cotent;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
