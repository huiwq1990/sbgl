package com.sbgl.app.entity;

import java.util.Date;

/**
 * Equipmenborrow entity. @author MyEclipse Persistence Tools
 */

public class Equipmenborrow extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer borrowid;
	private Integer category;
	private Integer userid;
	private Integer teacherid;
	private Date applytime;
	private Integer status;
	private Date borrowtime;
	private Date returntime;
	private String reason;
	private Integer borrowaudituser;
	private Integer returnaudituser;
	private String teachersuggest;
	private String examstate;
	private String title;
	private String remark;

	// Constructors

	/** default constructor */
	public Equipmenborrow() {
	}

	/** minimal constructor */
	public Equipmenborrow(Integer borrowid) {
		this.borrowid = borrowid;
	}

	/** full constructor */
	public Equipmenborrow(Integer borrowid, Integer category, Integer userid,
			Integer teacherid, Date applytime, Integer status,
			Date borrowtime, Date returntime, String reason,
			Integer borrowaudituser, Integer returnaudituser,
			String teachersuggest, String examstate, String title, String remark) {
		this.borrowid = borrowid;
		this.category = category;
		this.userid = userid;
		this.teacherid = teacherid;
		this.applytime = applytime;
		this.status = status;
		this.borrowtime = borrowtime;
		this.returntime = returntime;
		this.reason = reason;
		this.borrowaudituser = borrowaudituser;
		this.returnaudituser = returnaudituser;
		this.teachersuggest = teachersuggest;
		this.examstate = examstate;
		this.title = title;
		this.remark = remark;
	}

	// Property accessors

	public Integer getBorrowid() {
		return this.borrowid;
	}

	public void setBorrowid(Integer borrowid) {
		this.borrowid = borrowid;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getTeacherid() {
		return this.teacherid;
	}

	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}

	public Date getApplytime() {
		return this.applytime;
	}

	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getBorrowtime() {
		return this.borrowtime;
	}

	public void setBorrowtime(Date borrowtime) {
		this.borrowtime = borrowtime;
	}

	public Date getReturntime() {
		return this.returntime;
	}

	public void setReturntime(Date returntime) {
		this.returntime = returntime;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getBorrowaudituser() {
		return this.borrowaudituser;
	}

	public void setBorrowaudituser(Integer borrowaudituser) {
		this.borrowaudituser = borrowaudituser;
	}

	public Integer getReturnaudituser() {
		return this.returnaudituser;
	}

	public void setReturnaudituser(Integer returnaudituser) {
		this.returnaudituser = returnaudituser;
	}

	public String getTeachersuggest() {
		return this.teachersuggest;
	}

	public void setTeachersuggest(String teachersuggest) {
		this.teachersuggest = teachersuggest;
	}

	public String getExamstate() {
		return this.examstate;
	}

	public void setExamstate(String examstate) {
		this.examstate = examstate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
