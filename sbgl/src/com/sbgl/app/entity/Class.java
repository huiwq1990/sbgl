package com.sbgl.app.entity;

/**
 * Class entity. @author MyEclipse Persistence Tools
 */

public class Class extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer classid;
	private String classname;

	// Constructors

	/** default constructor */
	public Class() {
	}

	/** minimal constructor */
	public Class(Integer classid) {
		this.classid = classid;
	}

	/** full constructor */
	public Class(Integer classid, String classname) {
		this.classid = classid;
		this.classname = classname;
	}

	// Property accessors

	public Integer getClassid() {
		return this.classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public String getClassname() {
		return this.classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

}
