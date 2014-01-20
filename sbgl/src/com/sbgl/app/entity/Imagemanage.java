package com.sbgl.app.entity;

/**
 * Imagemanage entity. @author MyEclipse Persistence Tools
 */

public class Imagemanage extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer imagemanageid;
	private Integer ownerid;
	private String imagecategory;
	private String imageurl;
	private String imagetype;

	// Constructors

	/** default constructor */
	public Imagemanage() {
	}

	/** minimal constructor */
	public Imagemanage(Integer imagemanageid) {
		this.imagemanageid = imagemanageid;
	}

	/** full constructor */
	public Imagemanage(Integer imagemanageid, Integer ownerid,
			String imagecategory, String imageurl, String imagetype) {
		this.imagemanageid = imagemanageid;
		this.ownerid = ownerid;
		this.imagecategory = imagecategory;
		this.imageurl = imageurl;
		this.imagetype = imagetype;
	}

	// Property accessors

	public Integer getImagemanageid() {
		return this.imagemanageid;
	}

	public void setImagemanageid(Integer imagemanageid) {
		this.imagemanageid = imagemanageid;
	}

	public Integer getOwnerid() {
		return this.ownerid;
	}

	public void setOwnerid(Integer ownerid) {
		this.ownerid = ownerid;
	}

	public String getImagecategory() {
		return this.imagecategory;
	}

	public void setImagecategory(String imagecategory) {
		this.imagecategory = imagecategory;
	}

	public String getImageurl() {
		return this.imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getImagetype() {
		return this.imagetype;
	}

	public void setImagetype(String imagetype) {
		this.imagetype = imagetype;
	}

}
