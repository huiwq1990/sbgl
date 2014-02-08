package com.sbgl.app.entity;

/**
 * Computerconfig entity. @author MyEclipse Persistence Tools
 */

public class Computerconfig extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String value;

	// Constructors

	/** default constructor */
	public Computerconfig() {
	}

	/** minimal constructor */
	public Computerconfig(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Computerconfig(Integer id, String name, String value) {
		this.id = id;
		this.name = name;
		this.value = value;
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

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
