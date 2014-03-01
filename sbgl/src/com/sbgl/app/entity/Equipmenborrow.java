package com.sbgl.app.entity;

import java.sql.Timestamp;
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
	private Integer examuser;
	private Integer homeworkid;
	private Date examdate;

	// Constructors

	/** default constructor */
	public Equipmenborrow() {
	}

	/** minimal constructor */
	public Equipmenborrow(Integer borrowid, Integer category) {
		this.borrowid = borrowid;
		this.category = category;
	}

	/** full constructor */
	public Equipmenborrow(Integer borrowid, Integer category, Integer userid,
			Integer teacherid, Timestamp applytime, Integer status,
			Timestamp borrowtime, Timestamp returntime, String reason,
			Integer borrowaudituser, Integer returnaudituser,
			String teachersuggest, String examstate, String title,
			String remark, Integer examuser, Integer homeworkid,
			Timestamp examdate) {
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
		this.examuser = examuser;
		this.homeworkid = homeworkid;
		this.examdate = examdate;
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

	public Integer getExamuser() {
		return this.examuser;
	}

	public void setExamuser(Integer examuser) {
		this.examuser = examuser;
	}

	public Integer getHomeworkid() {
		return this.homeworkid;
	}

	public void setHomeworkid(Integer homeworkid) {
		this.homeworkid = homeworkid;
	}

	public Date getExamdate() {
		return this.examdate;
	}

	public void setExamdate(Date examdate) {
		this.examdate = examdate;
	}

}