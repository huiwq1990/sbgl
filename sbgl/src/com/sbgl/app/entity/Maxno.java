package com.sbgl.app.entity;

/**
 * Maxno entity. @author MyEclipse Persistence Tools
 */

public class Maxno extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private String notype;
	private Integer maxno;

	// Constructors

	/** default constructor */
	public Maxno() {
	}

	/** full constructor */
	public Maxno(String notype, Integer maxno) {
		this.notype = notype;
		this.maxno = maxno;
	}

	// Property accessors

	public String getNotype() {
		return this.notype;
	}

	public void setNotype(String notype) {
		this.notype = notype;
	}

	public Integer getMaxno() {
		return this.maxno;
	}

	public void setMaxno(Integer maxno) {
		this.maxno = maxno;
	}

}