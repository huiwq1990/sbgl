package com.sbgl.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import com.sbgl.app.entity.Loginuser;

import java.util.Map;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.URLDecoder;

public class CookiesUtil {

    public static final String DOMAIN = ".localhost";


    
    public static void addCookie(String name, String value) {
        try {
        	HttpServletResponse response = WebUtils.getHttpServletResponse();
        	Cookie cookie= new Cookie(name, URLEncoder.encode(value,"utf-8"));
        	cookie.setPath("/");
        	cookie.setMaxAge(-1);  
        	response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void addLoginCookie(String name, String value) {
        try {
        	HttpServletResponse response = WebUtils.getHttpServletResponse();
        	Cookie cookie= new Cookie(name, URLEncoder.encode(value,"utf-8"));
        	cookie.setPath("/");
        	cookie.setMaxAge(604800);  
        	response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void removeCookie(String name){
    	HttpServletRequest request = WebUtils.getHttpServletRequest();
    	HttpServletResponse response = WebUtils.getHttpServletResponse();
    	Cookie[] cookies = request.getCookies();
    	if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
               if (cookies[i].getName().equals(name)){
            	   cookies[i].setPath("/");
            	   cookies[i].setMaxAge(0);
            	   response.addCookie(cookies[i]);
            	   break;
               }
            }
        }
    }
    public static Map readCookieMap(HttpServletRequest request) {
        Map cookieMap = new HashMap();
        Cookie[] cookies = request.getCookies();

        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                cookieMap.put(cookies[i].getName(), cookies[i]);
            }
        }
        return cookieMap;
    }
    
    public static String getCookie(String name) {
        Map cookieMap = readCookieMap(WebUtils.getHttpServletRequest());
        String result = null;
        if (cookieMap.containsKey(name))
        	result = ((Cookie)cookieMap.get(name)).getValue();
        if(result!=null)
			try {
				result = URLDecoder.decode(result,"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return result;
    }
    
    public static String getCookie(String name,HttpServletRequest request) {
        Map cookieMap = readCookieMap(request);
        String result = null;
        if (cookieMap.containsKey(name))
        	result = ((Cookie)cookieMap.get(name)).getValue();
        if(result!=null)
			try {
				result = URLDecoder.decode(result,"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return result;
    }

    public static String decode(String value){
    	String str="";
        try {
        	 if(value==null)
                 return null;
			  str = java.net.URLDecoder.decode(value,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
    }

    public static String utf2GB(String s) {
        String s1 = null;
        try {
            byte abyte0[] = s.getBytes("ISO8859_1");
            s1 = new String(abyte0, "gbk");
        }
        catch (Exception exception) {
            return "";
        }
        return s1;
    }

    public static String gb2UTF(String s) {
        String s1 = null;
        try {
            byte abyte0[] = s.getBytes("gbk");
            s1 = new String(abyte0, "utf-8");
        }
        catch (Exception exception) {
            return "";
        }
        return s1;
    }

}
