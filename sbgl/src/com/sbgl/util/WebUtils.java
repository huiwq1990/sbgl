package com.sbgl.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public final class WebUtils {
    public static ValueStack getValueStack(){
	return ActionContext.getContext().getValueStack();
    }
       
    public static HttpServletRequest getHttpServletRequest(){
	return (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
    }
    
    public static HttpServletResponse getHttpServletResponse(){
	return (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
    }
    
    public static HttpSession getSession(boolean flag){
	return getHttpServletRequest().getSession(flag);
    }
    
    public static Map getSessionMap(){
	return ActionContext.getContext().getSession();
    }
    
    public static Map getRequestMap(){
	return getHttpServletRequest().getParameterMap();
    }
    
    public static String getRequestParameter(String key){
	return getHttpServletRequest().getParameter(key);
    }
    
    public static void setSessionValue(String key,Object value){
	getSession(false).setAttribute(key, value);
    }
    
    public static void setRequestValue(String key,Object value){
	getHttpServletRequest().setAttribute(key, value);
    }
    
    public static String getRealPath(String path){
	return getSession(false).getServletContext().getRealPath(path);
    }
    
    public static String getRequestMapValue(String key,Map valueMap){
	if(valueMap.get(key) != null)
	    return ((String[])valueMap.get(key))[0];
	else
	    return "";
    }
    
    public static void responseWrite(String write) throws IOException{
	HttpServletRequest request = getHttpServletRequest();
	HttpServletResponse response = getHttpServletResponse();
	response.setContentType(request.getContentType());
	response.setCharacterEncoding(request.getCharacterEncoding());
	PrintWriter writer = response.getWriter();
	writer.write(write);
	writer.flush();
    }
    /**
     * 页面提示信息
     *@param msg  提示信息
     *@param type  0:错误，1：警告，2：提示(暂时只为提示错误级别的)
     */
    public static void addMessage(String msg,int type){
	String typeName = "\"错误\"";
	if(type == 1)
	    typeName = "\"警告\"";
	else if(type == 2)
	    typeName = "\"提示\"";
	setRequestValue("page_k_message_key",typeName);
	setRequestValue("page_k_message_value",(new StringBuilder(msg.length()+4).append("\"").append(msg).append("\"").toString()));
    }
    /**
     * 页面提示信息
     *@param msg  提示信息
     */
    public static void addMessage(String msg){
	addMessage(msg,0);
    }
    /**
     * 
     * ?? 
     * 保存Message的request状态，当页面被从定向是，响应的 Message 会被请空掉，需要保存
     *
     */
    public static void saveMessageStatus(){
	if(getHttpServletRequest().getAttribute("page_k_message_key") != null){
	    setRequestValue("page_k_message_key", getHttpServletRequest().getAttribute("page_k_message_key"));
	    setRequestValue("page_k_message_value", getHttpServletRequest().getAttribute("page_k_message_value"));
	}
    }
    
    /**
	 * 获得客户端IP地址
	 * 
	 * @return String
	 */
	public static String getClientIp() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
