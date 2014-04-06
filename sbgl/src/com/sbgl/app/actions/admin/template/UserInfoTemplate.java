package com.sbgl.app.actions.admin.template;

import com.sbgl.app.entity.User;

public class UserInfoTemplate extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9056881885239618440L;
	private Integer classid;
	private String classname;
	private Integer groupid;
	private String groupname;
	private Integer managerGroupid;
	private String manaagerGroupname;
	private Integer managerGroupType;
	
	public UserInfoTemplate(User user) {
		super.setCardnumber(user.getCardnumber());
		super.setClassid(user.getClassid());
		super.setEmail(user.getEmail());
		super.setGender(user.getGender());
		super.setId(user.getId());
		super.setInitlan(user.getInitlan());
		super.setName(user.getName());
		super.setPassword(user.getPassword());
		super.setPhoto(user.getPhoto());
		super.setTelephone(user.getTelephone());
		super.setUserid(user.getUserid());
		super.setUsertype(user.getUsertype());
	}
	
	public UserInfoTemplate(User user, Integer classid, String classname) {
		super.setCardnumber(user.getCardnumber());
		super.setClassid(user.getClassid());
		super.setEmail(user.getEmail());
		super.setGender(user.getGender());
		super.setId(user.getId());
		super.setInitlan(user.getInitlan());
		super.setName(user.getName());
		super.setPassword(user.getPassword());
		super.setPhoto(user.getPhoto());
		super.setTelephone(user.getTelephone());
		super.setUserid(user.getUserid());
		super.setUsertype(user.getUsertype());
		this.classid = classid;
		this.classname = classname;
	}
	
	
	public UserInfoTemplate() {
		super();
	}
	
	
	public Integer getClassid() {
		return classid;
	}
	public void setClassid(Integer classid) {
		this.classid = classid;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public Integer getManagerGroupid() {
		return managerGroupid;
	}

	public void setManagerGroupid(Integer managerGroupid) {
		this.managerGroupid = managerGroupid;
	}

	public String getManaagerGroupname() {
		return manaagerGroupname;
	}

	public void setManaagerGroupname(String manaagerGroupname) {
		this.manaagerGroupname = manaagerGroupname;
	}
	
	public Integer getManagerGroupType() {
		return managerGroupType;
	}

	public void setManagerGroupType(Integer managerGroupType) {
		this.managerGroupType = managerGroupType;
	}
	
}
