package com.sbgl.app.entity;

/**
 * Clazz entity. @author MyEclipse Persistence Tools
 */

public class Clazz extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer classId;
	private String classname;

	// Constructors

	/** default constructor */
	public Clazz() {
	}

	/** minimal constructor */
	public Clazz(Integer classId) {
		this.classId = classId;
	}

	/** full constructor */
	public Clazz(Integer classId, String classname) {
		this.classId = classId;
		this.classname = classname;
	}

	// Property accessors

	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassname() {
		return this.classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

}