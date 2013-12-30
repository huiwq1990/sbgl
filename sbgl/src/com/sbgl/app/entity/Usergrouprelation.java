package com.sbgl.app.entity;

/**
 * Usergrouprelation entity. @author MyEclipse Persistence Tools
 */

public class Usergrouprelation extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer groupId;
	private Integer userId;
	private Integer status;
	private Integer groupType;

	// Constructors

	/** default constructor */
	public Usergrouprelation() {
	}

	/** minimal constructor */
	public Usergrouprelation(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Usergrouprelation(Integer id, Integer groupId, Integer userId,
			Integer status, Integer groupType) {
		this.id = id;
		this.groupId = groupId;
		this.userId = userId;
		this.status = status;
		this.groupType = groupType;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getGroupType() {
		return this.groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

}