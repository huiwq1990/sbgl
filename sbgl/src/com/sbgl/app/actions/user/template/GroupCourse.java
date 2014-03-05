package com.sbgl.app.actions.user.template;

public class GroupCourse {
	private Integer groupId;
	private String groupName;
	private Integer usersNum;
	
	public GroupCourse() {
		super();
	}

	public GroupCourse(Integer groupId, String groupName, Integer usersNum) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.usersNum = usersNum;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getUsersNum() {
		return usersNum;
	}

	public void setUsersNum(Integer usersNum) {
		this.usersNum = usersNum;
	}
	
	
}
