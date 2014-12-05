package com.sbgl.toolbox;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.tools.view.context.ViewContext;

import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.util.ResourceUtils;

/**
 * 用于国际化资源的Velocity工具类
 * @author Winter Lau
 */
public class ResourceTool {

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

	public String str(String key){
		System.out.println("tool get str:"+key);
		System.out.println();
		System.out.println();
		
		System.out.println();
		System.out.println();
		Locale loc = (request!=null)?request.getLocale():Locale.getDefault();
		System.out.println("ssssssss" +request.getSession());
		String languagetype = (String) request.getSession().getAttribute(CommonConfig.sessionLanguagetype);
		if(languagetype !=null && languagetype.equals(CommonConfig.languageenStr)){
			//languageSuffix = "";
			loc = Locale.US;
			System.out.println("ssssssss");
		}else{
		//	languageSuffix = "_en";
			loc = Locale.CHINA;
		}
		
		return ResourceUtils.getStringForLocale(loc, "messages", key); 

	}
	
	public String this_vm(){
		return velocity.getCurrentTemplateName();
	}
}