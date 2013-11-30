package com.sbgl.app.entity;

/**
 * Score entity. @author MyEclipse Persistence Tools
 */

public class Score extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer admId;
	private Integer studentId;
	private Integer courseId;
	private Integer score;

	// Constructors

	/** default constructor */
	public Score() {
	}

	/** minimal constructor */
	public Score(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Score(Integer id, Integer admId, Integer studentId,
			Integer courseId, Integer score) {
		this.id = id;
		this.admId = admId;
		this.studentId = studentId;
		this.courseId = courseId;
		this.score = score;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdmId() {
		return this.admId;
	}

	public void setAdmId(Integer admId) {
		this.admId = admId;
	}

	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
