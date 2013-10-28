package com.sbgl.app.entity;

import java.util.Date;

/**
 * Uploaddoc entity. @author MyEclipse Persistence Tools
 */

public class Uploaddoc extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private String doccode;
	private String projcode;
	private String docname;
	private String doctypecode;
	private String docpath;
	private Date makedate;
	private String fileno;

	// Constructors

	/** default constructor */
	public Uploaddoc() {
	}

	/** minimal constructor */
	public Uploaddoc(String doccode) {
		this.doccode = doccode;
	}

	/** full constructor */
	public Uploaddoc(String doccode, String projcode, String docname,
			String doctypecode, String docpath, Date makedate,
			String fileno) {
		this.doccode = doccode;
		this.projcode = projcode;
		this.docname = docname;
		this.doctypecode = doctypecode;
		this.docpath = docpath;
		this.makedate = makedate;
		this.fileno = fileno;
	}

	// Property accessors

	public String getDoccode() {
		return this.doccode;
	}

	public void setDoccode(String doccode) {
		this.doccode = doccode;
	}

	public String getProjcode() {
		return this.projcode;
	}

	public void setProjcode(String projcode) {
		this.projcode = projcode;
	}

	public String getDocname() {
		return this.docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getDoctypecode() {
		return this.doctypecode;
	}

	public void setDoctypecode(String doctypecode) {
		this.doctypecode = doctypecode;
	}

	public String getDocpath() {
		return this.docpath;
	}

	public void setDocpath(String docpath) {
		this.docpath = docpath;
	}

	public Date getMakedate() {
		return this.makedate;
	}

	public void setMakedate(Date makedate) {
		this.makedate = makedate;
	}

	public String getFileno() {
		return this.fileno;
	}

	public void setFileno(String fileno) {
		this.fileno = fileno;
	}

}
