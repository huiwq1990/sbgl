package com.sbgl.toolbox;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.tools.view.context.ViewContext;

import com.sbgl.app.actions.common.CommonConfig;

public class ImageUrlTool {
	
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
	
	
	public String url(String cate,String name){
		
		String webbaseurl = (String) request.getAttribute("webbaseurl");
		System.out.println("webbaseurl" + webbaseurl);
		if(cate == null || cate.trim().length()==0){
			return "";
		}
		if(name == null || name.trim().length()==0){
			return "";
		}
		
		if(cate.equals("user")){
			return webbaseurl + "/userImage"+"/"+name;
		}else if(cate.equals("pc")){
			return webbaseurl + "/computerImage"+"/"+name;
		}
		
		return "";
		
	}
}
