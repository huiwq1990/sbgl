package com.sbgl.app.actions.orderadmin;

import java.util.Date;

import com.sbgl.app.dao.DaoAbs;

public class OrdercourseruleFull extends DaoAbs {
	private Integer courseruleid;
	private Integer courseid;
	private Integer teacherid;
	private String courserulename;
	private Date createtime;
	
	private String courseName;
	private String teacherName;
	public Integer getCourseruleid() {
		return courseruleid;
	}
	public void setCourseruleid(Integer courseruleid) {
		this.courseruleid = courseruleid;
	}
	public Integer getCourseid() {
		return courseid;
	}
	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}
	public Integer getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}
	public String getCourserulename() {
		return courserulename;
	}
	public void setCourserulename(String courserulename) {
		this.courserulename = courserulename;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

}
