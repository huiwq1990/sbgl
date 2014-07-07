package com.sbgl.toolbox;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.tools.view.context.ViewContext;

import com.sbgl.app.actions.common.CommonConfig;

public class ComputerImageUrlTool {
	
	private HttpServletRequest request;
	private VelocityContext velocity;
	private String defaulturl = "http://localhost:8080/sbgl";

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
	
	
	public String url(String pcname){
		if(request==null){
			return defaulturl+ "/computerImage"+"/default.jpg";
		}
		
		String webbaseurl = (String) request.getAttribute("webbaseurl");
		if(webbaseurl==null){
			return defaulturl+ "/computerImage"+"/default.jpg";
		}
//		System.out.println("webbaseurl" + webbaseurl);
		if(pcname == null || pcname.trim().length()==0){
			return webbaseurl + "/computerImage"+"/default.jpg";
		}
		return webbaseurl + "/computerImage"+"/"+pcname;
	}
}
