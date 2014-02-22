package com.sbgl.app.entity;

/**
 * Coursecomputer entity. @author MyEclipse Persistence Tools
 */

public class Coursecomputer extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer lessonid;
	private Integer computerid;
	private Integer borrownum;
	private Integer status;

	// Constructors

	/** default constructor */
	public Coursecomputer() {
	}

	/** minimal constructor */
	public Coursecomputer(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Coursecomputer(Integer id, Integer lessonid, Integer computerid,
			Integer borrownum, Integer status) {
		this.id = id;
		this.lessonid = lessonid;
		this.computerid = computerid;
		this.borrownum = borrownum;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLessonid() {
		return this.lessonid;
	}

	public void setLessonid(Integer lessonid) {
		this.lessonid = lessonid;
	}

	public Integer getComputerid() {
		return this.computerid;
	}

	public void setComputerid(Integer computerid) {
		this.computerid = computerid;
	}

	public Integer getBorrownum() {
		return this.borrownum;
	}

	public void setBorrownum(Integer borrownum) {
		this.borrownum = borrownum;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
