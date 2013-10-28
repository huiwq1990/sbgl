package com.sbgl.app.entity;

import java.util.Date;

/**
 * Equipmentdamage entity. @author MyEclipse Persistence Tools
 */

public class Equipmentdamage extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer studentId;
	private Integer auditAdministrationId;
	private String detail;
	private Date time;

	// Constructors

	/** default constructor */
	public Equipmentdamage() {
	}

	/** minimal constructor */
	public Equipmentdamage(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Equipmentdamage(Integer id, Integer studentId,
			Integer auditAdministrationId, String detail, Date time) {
		this.id = id;
		this.studentId = studentId;
		this.auditAdministrationId = auditAdministrationId;
		this.detail = detail;
		this.time = time;
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

	public Integer getAuditAdministrationId() {
		return this.auditAdministrationId;
	}

	public void setAuditAdministrationId(Integer auditAdministrationId) {
		this.auditAdministrationId = auditAdministrationId;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
