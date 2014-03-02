package com.sbgl.app.actions.teach;

import com.sbgl.app.entity.Student;

public class StuInClassDto extends Student {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6488368447266302718L;
	
	private int classId;
	private String className;
	
	
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
