package com.sbgl.app.actions.order;

import java.sql.Timestamp;
import java.util.Date;

import com.sbgl.app.dao.DaoAbs;

public class EquipmenborrowFull extends DaoAbs {
	private Integer borrowid;
	private Integer category;  //1：个人预约，2：课程预约
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
	private Integer sendruleid;
	private Integer courseruleid;
	private String borrowallid;
	
	private String userName;
	private String userPhone;
	private String cateName;
	private String statusName;
	private String teacherName;
	private String teacherPic;
	private String examuserPic;
	private String examuserName;
	private Date createtime;
	private Date startdate;
	private Date enddate;
	private String msgtitle;
	private String content;
	private String teacherphoto;
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
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getExamuser() {
		return examuser;
	}
	public void setExamuser(Integer examuser) {
		this.examuser = examuser;
	}
	public Integer getHomeworkid() {
		return homeworkid;
	}
	public void setHomeworkid(Integer homeworkid) {
		this.homeworkid = homeworkid;
	}
	public Date getExamdate() {
		return examdate;
	}
	public void setExamdate(Date examdate) {
		this.examdate = examdate;
	}
	public String getExamuserName() {
		return examuserName;
	}
	public void setExamuserName(String examuserName) {
		this.examuserName = examuserName;
	}
	public Integer getSendruleid() {
		return sendruleid;
	}
	public void setSendruleid(Integer sendruleid) {
		this.sendruleid = sendruleid;
	}
	public Integer getCourseruleid() {
		return courseruleid;
	}
	public void setCourseruleid(Integer courseruleid) {
		this.courseruleid = courseruleid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getMsgtitle() {
		return msgtitle;
	}
	public void setMsgtitle(String msgtitle) {
		this.msgtitle = msgtitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTeacherphoto() {
		return teacherphoto;
	}
	public void setTeacherphoto(String teacherphoto) {
		this.teacherphoto = teacherphoto;
	}
	public String getTeacherPic() {
		return teacherPic;
	}
	public void setTeacherPic(String teacherPic) {
		this.teacherPic = teacherPic;
	}
	public String getExamuserPic() {
		return examuserPic;
	}
	public void setExamuserPic(String examuserPic) {
		this.examuserPic = examuserPic;
	}
	public String getBorrowallid() {
		return borrowallid;
	}
	public void setBorrowallid(String borrowallid) {
		this.borrowallid = borrowallid;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
}
