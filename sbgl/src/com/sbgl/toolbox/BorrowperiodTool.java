package com.sbgl.toolbox;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.tools.view.context.ViewContext;

import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.common.computer.BorrowperiodUtil;
import com.sbgl.app.entity.Borrowperiod;

public class BorrowperiodTool {
	private HttpServletRequest request;
	private VelocityContext velocity;

	/*
	 * Initialize toolbox
	 * @see org.apache.velocity.tools.view.tools.ViewTool#init(java.lang.Object)
	 */
	public void init(Object arg0) {
		//scope: request or session
		if(arg0 instanceof ViewContext){
			ViewContext viewContext = (ViewContext) arg0;
			request = viewContext.getRequest();
			velocity = (VelocityContext)viewContext.getVelocityContext();
		}
	}
	
	
	public String getName(int periodnum){
		return BorrowperiodUtil.getBorrowperiodByNum(periodnum).getPeroidname();
	}
}
