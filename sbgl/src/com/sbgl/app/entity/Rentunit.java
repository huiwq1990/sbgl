package com.sbgl.app.entity;

/**
 * Rentunit entity. @author MyEclipse Persistence Tools
 */

public class Rentunit extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String unitname;
	private String formula;

	// Constructors

	/** default constructor */
	public Rentunit() {
	}

	/** minimal constructor */
	public Rentunit(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Rentunit(Integer id, String unitname, String formula) {
		this.id = id;
		this.unitname = unitname;
		this.formula = formula;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUnitname() {
		return this.unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

}