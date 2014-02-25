package com.sbgl.app.actions.login;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.Filter;

import com.sbgl.app.entity.Loginuser;
import com.sbgl.util.CookiesUtil;

public class LoginFilter implements Filter  {
	
	private HttpServletRequest request;

    private HttpServletResponse response;
    
    private HttpSession session;

	public void destroy(){
	}
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain filterChain) throws IOException, ServletException{
		request = (HttpServletRequest) sRequest;
		response = (HttpServletResponse) sResponse;  
		session = request.getSession();
		Loginuser u = (Loginuser)session.getValue("loginUser");
        String url=request.getServletPath();
        String contextPath=request.getContextPath();  
        if(url.equals("")) url+="/";
        if((url.startsWith("/")&&!(url.startsWith("/login")||url.startsWith("/doLogin")))){
	        //若访问后台资源 过滤到login
        	String loginUserId =CookiesUtil.getCookie("userid",request);
        	
        	if(session.getValue("loginUser") == null) {
        		if((loginUserId==null||loginUserId.equals(""))){//转入管理员登陆页面	        	
    	        	java.io.PrintWriter out = response.getWriter();   
    	            out.println("<html>");   
    	            out.println("<script>");   
    	            out.println("window.open ('/sbgl/login.vm','_top')");   
    	            out.println("</script>");   
    	            out.println("</html>"); 
    	        	return;
            	} else {
            		Loginuser user = new Loginuser();
            		user.setId(Integer.valueOf(CookiesUtil.getCookie("id",request)));
            		user.setUserId(CookiesUtil.getCookie("userid",request));
            		user.setPassword(CookiesUtil.getCookie("userpass",request));
            		user.setName(CookiesUtil.getCookie("username",request));
            		user.setPrivilege(CookiesUtil.getCookie("privilege",request));
            		user.setRoletype(CookiesUtil.getCookie("roletype",request));
            		session.setAttribute("loginUser", user);
            	}
        	}
        	
	        
        }else{
        	String loginUserId =CookiesUtil.getCookie("userid",request);
        	if((loginUserId!=null&&!loginUserId.equals(""))){//转入管理员登陆页面	        		    
        		java.io.PrintWriter out = response.getWriter();   
	            out.println("<html>");   
	            out.println("<script>");   
	            out.println("window.open ('/sbgl/index.vm','_top')");   
	            out.println("</script>");   
	            out.println("</html>"); 
	        	return;
        	}
        }
        filterChain.doFilter(sRequest, sResponse);    
    }  
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
