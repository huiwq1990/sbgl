package com.sbgl.app.actions.upload;

import java.util.Date;

import com.sbgl.app.dao.DaoAbs;

public class UploaddocFull extends DaoAbs {
	private String doccode;
	private String projcode;
	private String docname;
	private String doctypecode;
	private String docpath;
	private Date makedate;
	private String fileno;
	public String getDoccode() {
		return doccode;
	}
	public void setDoccode(String doccode) {
		this.doccode = doccode;
	}
	public String getProjcode() {
		return projcode;
	}
	public void setProjcode(String projcode) {
		this.projcode = projcode;
	}
	public String getDocname() {
		return docname;
	}
	public void setDocname(String docname) {
		this.docname = docname;
	}
	public String getDoctypecode() {
		return doctypecode;
	}
	public void setDoctypecode(String doctypecode) {
		this.doctypecode = doctypecode;
	}
	public String getDocpath() {
		return docpath;
	}
	public void setDocpath(String docpath) {
		this.docpath = docpath;
	}
	public Date getMakedate() {
		return makedate;
	}
	public void setMakedate(Date makedate) {
		this.makedate = makedate;
	}
	public String getFileno() {
		return fileno;
	}
	public void setFileno(String fileno) {
		this.fileno = fileno;
	}
}
