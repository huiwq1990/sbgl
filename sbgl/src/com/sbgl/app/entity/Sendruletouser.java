package com.sbgl.app.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Sendruletouser entity. @author MyEclipse Persistence Tools
 */

public class Sendruletouser extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer sendruleid;
	private String msgtitle;
	private String content;
	private Date startdate;
	private Date enddate;
	private Integer courseruleid;
	private Date createtime;
	private Integer operate;

	// Constructors

	/** default constructor */
	public Sendruletouser() {
	}

	/** minimal constructor */
	public Sendruletouser(Integer sendruleid) {
		this.sendruleid = sendruleid;
	}

	/** full constructor */
	public Sendruletouser(Integer sendruleid, String msgtitle, String content,
			Date startdate, Date enddate, Integer courseruleid,
			Date createtime, Integer operate) {
		this.sendruleid = sendruleid;
		this.msgtitle = msgtitle;
		this.content = content;
		this.startdate = startdate;
		this.enddate = enddate;
		this.courseruleid = courseruleid;
		this.createtime = createtime;
		this.operate = operate;
	}

	// Property accessors

	public Integer getSendruleid() {
		return this.sendruleid;
	}

	public void setSendruleid(Integer sendruleid) {
		this.sendruleid = sendruleid;
	}

	public String getMsgtitle() {
		return this.msgtitle;
	}

	public void setMsgtitle(String msgtitle) {
		this.msgtitle = msgtitle;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	public Integer getOperate() {
		return this.operate;
	}

	public void setOperate(Integer operate) {
		this.operate = operate;
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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getCourseruleid() {
		return courseruleid;
	}

	public void setCourseruleid(Integer courseruleid) {
		this.courseruleid = courseruleid;
	}

}