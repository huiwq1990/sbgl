package com.sbgl.util;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.FilterDispatcher;

import com.opensymphony.xwork2.ActionContext;
import com.sbgl.app.actions.common.CommonConfig;

public class SbglFilterDispatcher extends FilterDispatcher {
	 private static String imageDomain = PropertyUtil.readValue("/system.properties", "imageDomain");
	    private static String cssDomain = PropertyUtil.readValue("/system.properties", "cssDomain");
	    private static String jsDomain = PropertyUtil.readValue("/system.properties", "jsDomain");
	    private static String sbglpath = PropertyUtil.readValue("/system.properties", "sbglpath");
	    private static String webbaseurl = PropertyUtil.readValue("/system.properties", "webbaseurl");
	    private static String equipmentImagePath = PropertyUtil.readValue("/system.properties", "equipmentImagePath");
	    private static String computerImagePath = PropertyUtil.readValue("/system.properties", "computerImagePath");
	    private static String strutsaction = PropertyUtil.readValue("/system.properties", "strutsaction");
	    private static String userImagePath = PropertyUtil.readValue("/system.properties", "userImagePath");
	    private static String computerImageUrl = PropertyUtil.readValue("/system.properties", "computerImageUrl");
	    ActionContext cnaplicaction = ActionContext.getContext();
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		super.init(filterConfig);
		System.out.println("-------------------------------------------------");
		//cnaplicaction.put("imageDomain", imageDomain);
		//cnaplicaction.put("cssDomain", cssDomain);
	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		//HttpServletResponse response = (HttpServletResponse) arg1;
		ServletContext servletContext = getServletContext();
		@SuppressWarnings("unused")
		String ctx = servletContext.getServletContextName();
		// TODO Auto-generated method stub
		request.setAttribute("imageDomain", imageDomain);
		request.setAttribute("cssDomain", cssDomain);
		request.setAttribute("jsDomain", jsDomain);
		request.setAttribute("sbglpath", sbglpath);
		request.setAttribute("webbaseurl", webbaseurl);
		
		request.setAttribute("equipmentImagePath", equipmentImagePath);
		request.setAttribute("computerImagePath", computerImagePath);
		request.setAttribute("userImagePath", userImagePath);
		request.setAttribute("computerImageUrl", computerImageUrl);
		request.setAttribute("strutsaction", strutsaction);
		String lan = (String) request.getSession().getAttribute(CommonConfig.sessionLanguagetype);
		if(lan == null){
			lan = "0";
		}
		
		
		Map<String,String> textmap = new ConcurrentHashMap<String,String>();
		if(lan.equals(CommonConfig.languageenStr)){
			textmap.clear();
			textmap = (Map<String, String>) servletContext.getAttribute(CommonConfig.resourcetextmapen);	
		}else{
			textmap.clear();
			textmap = (Map<String, String>) servletContext.getAttribute(CommonConfig.resourcetextmapch);
		}
		
//		System.out.println("textmaptextmap"+textmap);
		request.setAttribute(CommonConfig.resourcetextmap, textmap);

		
		super.doFilter(request, arg1, arg2);
	}

}

