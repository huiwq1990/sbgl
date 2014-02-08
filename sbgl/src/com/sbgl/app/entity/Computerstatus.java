package com.sbgl.app.entity;

/**
 * Computerstatus entity. @author MyEclipse Persistence Tools
 */

public class Computerstatus extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer availableborrow;

	// Constructors

	/** default constructor */
	public Computerstatus() {
	}

	/** minimal constructor */
	public Computerstatus(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Computerstatus(Integer id, String name, Integer availableborrow) {
		this.id = id;
		this.name = name;
		this.availableborrow = availableborrow;
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

	public Integer getAvailableborrow() {
		return this.availableborrow;
	}

	public void setAvailableborrow(Integer availableborrow) {
		this.availableborrow = availableborrow;
	}

}