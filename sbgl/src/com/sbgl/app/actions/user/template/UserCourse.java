package com.sbgl.app.actions.user.template;

public class UserCourse {
	private String id;
	private String gender;
	private String userCode;
	private String userName;
	private String userPass;
	private String userGroupId;
	private String userGroupName;
	private String userGroupType;
	private String clazzId;
	private String clazzName;
	private String tel;
	private String mail;
	private String canBorrow;
	private String photo;
	private String privilege;
	
	public UserCourse(String id, String gender, String userCode,
			String userName, String userPass, String userGroupId,
			String userGroupName, String userGroupType, String clazzId,
			String clazzName, String tel, String mail, String canBorrow,
			String photo, String privilege) {
		super();
		this.id = id;
		this.gender = gender;
		this.userCode = userCode;
		this.userName = userName;
		this.userPass = userPass;
		this.userGroupId = userGroupId;
		this.userGroupName = userGroupName;
		this.userGroupType = userGroupType;
		this.clazzId = clazzId;
		this.clazzName = clazzName;
		this.tel = tel;
		this.mail = mail;
		this.canBorrow = canBorrow;
		this.photo = photo;
		this.privilege = privilege;
	}

	public UserCourse() {
		super();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}
	public String getUserGroupName() {
		return userGroupName;
	}
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}
	public String getClazzId() {
		return clazzId;
	}
	public void setClazzId(String clazzId) {
		this.clazzId = clazzId;
	}
	public String getClazzName() {
		return clazzName;
	}
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUserGroupType() {
		return userGroupType;
	}

	public void setUserGroupType(String userGroupType) {
		this.userGroupType = userGroupType;
	}
	
	public String getCanBorrow() {
		return canBorrow;
	}

	public void setCanBorrow(String canBorrow) {
		this.canBorrow = canBorrow;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
}
