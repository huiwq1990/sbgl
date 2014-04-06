package com.sbgl.app.actions.admin.template;

import com.sbgl.app.entity.Usergroup;

public class ClassTypeTemplate extends Usergroup {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8590093995392815714L;
	private String sum;

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}
	
	public ClassTypeTemplate() {}
	
	public ClassTypeTemplate(Usergroup ug) {
		super.setId( ug.getId() );
		super.setName( ug.getName() );
		super.setType( ug.getType() );
	}
}
