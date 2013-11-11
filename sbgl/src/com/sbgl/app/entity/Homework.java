package com.sbgl.app.entity;

import java.util.Date;

/**
 * Homework entity. @author MyEclipse Persistence Tools
 */

public class Homework extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String content;
	private Date time;

	// Constructors

	/** default constructor */
	public Homework() {
	}

	/** minimal constructor */
	public Homework(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Homework(Integer id, String name, String content, Date time) {
		this.id = id;
		this.name = name;
		this.content = content;
		this.time = time;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
