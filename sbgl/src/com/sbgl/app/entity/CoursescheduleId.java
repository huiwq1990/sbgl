package com.sbgl.app.entity;

/**
 * CoursescheduleId entity. @author MyEclipse Persistence Tools
 */

public class CoursescheduleId extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer courseid;
	private Integer years;
	private Integer week;
	private Integer day;
	private Integer period;
	private Integer adduserid;
	private Integer status;

	// Constructors

	/** default constructor */
	public CoursescheduleId() {
	}

	/** full constructor */
	public CoursescheduleId(Integer id, Integer courseid, Integer years,
			Integer week, Integer day, Integer period, Integer adduserid,
			Integer status) {
		this.id = id;
		this.courseid = courseid;
		this.years = years;
		this.week = week;
		this.day = day;
		this.period = period;
		this.adduserid = adduserid;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCourseid() {
		return this.courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}

	public Integer getYears() {
		return this.years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

	public Integer getWeek() {
		return this.week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getDay() {
		return this.day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getPeriod() {
		return this.period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getAdduserid() {
		return this.adduserid;
	}

	public void setAdduserid(Integer adduserid) {
		this.adduserid = adduserid;
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
		if (!(other instanceof CoursescheduleId))
			return false;
		CoursescheduleId castOther = (CoursescheduleId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getCourseid() == castOther.getCourseid()) || (this
						.getCourseid() != null
						&& castOther.getCourseid() != null && this
						.getCourseid().equals(castOther.getCourseid())))
				&& ((this.getYears() == castOther.getYears()) || (this
						.getYears() != null && castOther.getYears() != null && this
						.getYears().equals(castOther.getYears())))
				&& ((this.getWeek() == castOther.getWeek()) || (this.getWeek() != null
						&& castOther.getWeek() != null && this.getWeek()
						.equals(castOther.getWeek())))
				&& ((this.getDay() == castOther.getDay()) || (this.getDay() != null
						&& castOther.getDay() != null && this.getDay().equals(
						castOther.getDay())))
				&& ((this.getPeriod() == castOther.getPeriod()) || (this
						.getPeriod() != null && castOther.getPeriod() != null && this
						.getPeriod().equals(castOther.getPeriod())))
				&& ((this.getAdduserid() == castOther.getAdduserid()) || (this
						.getAdduserid() != null
						&& castOther.getAdduserid() != null && this
						.getAdduserid().equals(castOther.getAdduserid())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getCourseid() == null ? 0 : this.getCourseid().hashCode());
		result = 37 * result
				+ (getYears() == null ? 0 : this.getYears().hashCode());
		result = 37 * result
				+ (getWeek() == null ? 0 : this.getWeek().hashCode());
		result = 37 * result
				+ (getDay() == null ? 0 : this.getDay().hashCode());
		result = 37 * result
				+ (getPeriod() == null ? 0 : this.getPeriod().hashCode());
		result = 37 * result
				+ (getAdduserid() == null ? 0 : this.getAdduserid().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		return result;
	}

}