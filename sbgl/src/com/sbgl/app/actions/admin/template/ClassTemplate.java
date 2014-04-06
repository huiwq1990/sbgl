package com.sbgl.app.actions.admin.template;

import com.sbgl.app.entity.Clazz;

public class ClassTemplate extends Clazz {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7191078687995904475L;
	private String typeName;
	

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public ClassTemplate() {}
	
	public ClassTemplate(Clazz clazz) {
		super.setClassid(clazz.getClassid());
		super.setClassname(clazz.getClassname());
		super.setClasstype(clazz.getClasstype());
	}
	
	public ClassTemplate(Clazz clazz, String typeName) {
		super.setClassid(clazz.getClassid());
		super.setClassname(clazz.getClassname());
		super.setClasstype(clazz.getClasstype());
		this.typeName = typeName;
	}
}
