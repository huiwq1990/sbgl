package com.sbgl.app.actions.computer;

import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.Loginuser;

public class ComputerorderPreemption {
	Computerorder computerorder;
	Computermodel computermodel;
	Loginuser loginuser;
	
	String periodname;

	public Computerorder getComputerorder() {
		return computerorder;
	}

	public void setComputerorder(Computerorder computerorder) {
		this.computerorder = computerorder;
	}

	public Computermodel getComputermodel() {
		return computermodel;
	}

	public void setComputermodel(Computermodel computermodel) {
		this.computermodel = computermodel;
	}

	public Loginuser getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(Loginuser loginuser) {
		this.loginuser = loginuser;
	}

	public String getPeriodname() {
		return periodname;
	}

	public void setPeriodname(String periodname) {
		this.periodname = periodname;
	}
	
	
	
//	private 
}
