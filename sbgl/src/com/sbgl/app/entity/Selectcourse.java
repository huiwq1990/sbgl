package com.sbgl.app.entity;

/**
 * Selectcourse entity. @author MyEclipse Persistence Tools
 */

public class Selectcourse extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer studentId;
	private Integer semester;

	// Constructors

	/** default constructor */
	public Selectcourse() {
	}

	/** minimal constructor */
	public Selectcourse(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Selectcourse(Integer id, Integer studentId, Integer semester) {
		this.id = id;
		this.studentId = studentId;
		this.semester = semester;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getSemester() {
		return this.semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

}
