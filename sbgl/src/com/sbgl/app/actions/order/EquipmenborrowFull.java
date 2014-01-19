package com.sbgl.app.actions.order;

import java.sql.Timestamp;
import java.util.Date;

import com.sbgl.app.dao.DaoAbs;

public class EquipmenborrowFull extends DaoAbs {
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
	
	private String userName;
	private String cateName;
	private String teacherName;
	public Integer getBorrowid() {
		return borrowid;
	}
	public void setBorrowid(Integer borrowid) {
		this.borrowid = borrowid;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}
	public Date getApplytime() {
		return applytime;
	}
	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getBorrowtime() {
		return borrowtime;
	}
	public void setBorrowtime(Date borrowtime) {
		this.borrowtime = borrowtime;
	}
	public Date getReturntime() {
		return returntime;
	}
	public void setReturntime(Date returntime) {
		this.returntime = returntime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getBorrowaudituser() {
		return borrowaudituser;
	}
	public void setBorrowaudituser(Integer borrowaudituser) {
		this.borrowaudituser = borrowaudituser;
	}
	public Integer getReturnaudituser() {
		return returnaudituser;
	}
	public void setReturnaudituser(Integer returnaudituser) {
		this.returnaudituser = returnaudituser;
	}
	public String getTeachersuggest() {
		return teachersuggest;
	}
	public void setTeachersuggest(String teachersuggest) {
		this.teachersuggest = teachersuggest;
	}
	public String getExamstate() {
		return examstate;
	}
	public void setExamstate(String examstate) {
		this.examstate = examstate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
}
