package com.sbgl.app.entity;

import java.sql.Timestamp;

/**
 * CourseconfigId entity. @author MyEclipse Persistence Tools
 */

public class CourseconfigId extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String schoolyear;
	private Integer semester;
	private Timestamp firstday;
	private Timestamp lastday;
	private Timestamp firstweekfirstday;
	private Integer status;

	// Constructors

	/** default constructor */
	public CourseconfigId() {
	}

	/** full constructor */
	public CourseconfigId(Integer id, String schoolyear, Integer semester,
			Timestamp firstday, Timestamp lastday, Timestamp firstweekfirstday,
			Integer status) {
		this.id = id;
		this.schoolyear = schoolyear;
		this.semester = semester;
		this.firstday = firstday;
		this.lastday = lastday;
		this.firstweekfirstday = firstweekfirstday;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSchoolyear() {
		return this.schoolyear;
	}

	public void setSchoolyear(String schoolyear) {
		this.schoolyear = schoolyear;
	}

	public Integer getSemester() {
		return this.semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public Timestamp getFirstday() {
		return this.firstday;
	}

	public void setFirstday(Timestamp firstday) {
		this.firstday = firstday;
	}

	public Timestamp getLastday() {
		return this.lastday;
	}

	public void setLastday(Timestamp lastday) {
		this.lastday = lastday;
	}

	public Timestamp getFirstweekfirstday() {
		return this.firstweekfirstday;
	}

	public void setFirstweekfirstday(Timestamp firstweekfirstday) {
		this.firstweekfirstday = firstweekfirstday;
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
		if (!(other instanceof CourseconfigId))
			return false;
		CourseconfigId castOther = (CourseconfigId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getSchoolyear() == castOther.getSchoolyear()) || (this
						.getSchoolyear() != null
						&& castOther.getSchoolyear() != null && this
						.getSchoolyear().equals(castOther.getSchoolyear())))
				&& ((this.getSemester() == castOther.getSemester()) || (this
						.getSemester() != null
						&& castOther.getSemester() != null && this
						.getSemester().equals(castOther.getSemester())))
				&& ((this.getFirstday() == castOther.getFirstday()) || (this
						.getFirstday() != null
						&& castOther.getFirstday() != null && this
						.getFirstday().equals(castOther.getFirstday())))
				&& ((this.getLastday() == castOther.getLastday()) || (this
						.getLastday() != null && castOther.getLastday() != null && this
						.getLastday().equals(castOther.getLastday())))
				&& ((this.getFirstweekfirstday() == castOther
						.getFirstweekfirstday()) || (this
						.getFirstweekfirstday() != null
						&& castOther.getFirstweekfirstday() != null && this
						.getFirstweekfirstday().equals(
								castOther.getFirstweekfirstday())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37
				* result
				+ (getSchoolyear() == null ? 0 : this.getSchoolyear()
						.hashCode());
		result = 37 * result
				+ (getSemester() == null ? 0 : this.getSemester().hashCode());
		result = 37 * result
				+ (getFirstday() == null ? 0 : this.getFirstday().hashCode());
		result = 37 * result
				+ (getLastday() == null ? 0 : this.getLastday().hashCode());
		result = 37
				* result
				+ (getFirstweekfirstday() == null ? 0 : this
						.getFirstweekfirstday().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		return result;
	}

}