package com.sbgl.app.entity;

/**
 * Computerhomeworkreceiver entity. @author MyEclipse Persistence Tools
 */

public class Computerhomeworkreceiver extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer computerhomeworkid;
	private Integer userid;
	private Integer hasview;
	private Integer hasorder;
	private Integer status;

	// Constructors

	/** default constructor */
	public Computerhomeworkreceiver() {
	}

	/** minimal constructor */
	public Computerhomeworkreceiver(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Computerhomeworkreceiver(Integer id, Integer computerhomeworkid,
			Integer userid, Integer hasview, Integer hasorder, Integer status) {
		this.id = id;
		this.computerhomeworkid = computerhomeworkid;
		this.userid = userid;
		this.hasview = hasview;
		this.hasorder = hasorder;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getComputerhomeworkid() {
		return this.computerhomeworkid;
	}

	public void setComputerhomeworkid(Integer computerhomeworkid) {
		this.computerhomeworkid = computerhomeworkid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getHasview() {
		return this.hasview;
	}

	public void setHasview(Integer hasview) {
		this.hasview = hasview;
	}

	public Integer getHasorder() {
		return this.hasorder;
	}

	public void setHasorder(Integer hasorder) {
		this.hasorder = hasorder;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}