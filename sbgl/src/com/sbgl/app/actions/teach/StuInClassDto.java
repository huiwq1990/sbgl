package com.sbgl.app.actions.teach;

public class StuInClassDto {
	private int stuId;
	private String stuName;
	private int classId;
	private String className;
	
	public StuInClassDto(int stuId, String stuName, int classId,
			String className) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.classId = classId;
		this.className = className;
	}
	
	public StuInClassDto() {
		super();
	}
	
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
