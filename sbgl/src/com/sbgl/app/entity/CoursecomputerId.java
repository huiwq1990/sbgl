package com.sbgl.app.entity;

/**
 * CoursecomputerId entity. @author MyEclipse Persistence Tools
 */

public class CoursecomputerId extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer lessonid;
	private Integer computerid;
	private Integer borrownum;
	private Integer status;

	// Constructors

	/** default constructor */
	public CoursecomputerId() {
	}

	/** full constructor */
	public CoursecomputerId(Integer id, Integer lessonid, Integer computerid,
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CoursecomputerId))
			return false;
		CoursecomputerId castOther = (CoursecomputerId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getLessonid() == castOther.getLessonid()) || (this
						.getLessonid() != null
						&& castOther.getLessonid() != null && this
						.getLessonid().equals(castOther.getLessonid())))
				&& ((this.getComputerid() == castOther.getComputerid()) || (this
						.getComputerid() != null
						&& castOther.getComputerid() != null && this
						.getComputerid().equals(castOther.getComputerid())))
				&& ((this.getBorrownum() == castOther.getBorrownum()) || (this
						.getBorrownum() != null
						&& castOther.getBorrownum() != null && this
						.getBorrownum().equals(castOther.getBorrownum())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getLessonid() == null ? 0 : this.getLessonid().hashCode());
		result = 37
				* result
				+ (getComputerid() == null ? 0 : this.getComputerid()
						.hashCode());
		result = 37 * result
				+ (getBorrownum() == null ? 0 : this.getBorrownum().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		return result;
	}

}