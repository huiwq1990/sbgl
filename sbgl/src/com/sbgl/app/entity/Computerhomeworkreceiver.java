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
			Integer userid) {
		this.id = id;
		this.computerhomeworkid = computerhomeworkid;
		this.userid = userid;
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

}
