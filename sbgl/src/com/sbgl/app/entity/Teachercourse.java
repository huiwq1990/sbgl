package com.sbgl.app.entity;

/**
 * Teachercourse entity. @author MyEclipse Persistence Tools
 */

public class Teachercourse extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer courseId;
	private Integer semester;

	// Constructors

	/** default constructor */
	public Teachercourse() {
	}

	/** minimal constructor */
	public Teachercourse(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Teachercourse(Integer id, Integer courseId, Integer semester) {
		this.id = id;
		this.courseId = courseId;
		this.semester = semester;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getSemester() {
		return this.semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

}
