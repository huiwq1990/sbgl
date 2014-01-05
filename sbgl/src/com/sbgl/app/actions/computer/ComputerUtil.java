package com.sbgl.app.actions.computer;

import java.util.UUID;

import javax.servlet.http.Cookie;

public class ComputerUtil {

/**
 * 获取ComputerOrder的序列号
 * @param str
 * @return
 */
	public static String genSerialnumber(String str) {
		return  UUID.randomUUID().toString();      
	}

	public String getCookieValue(Cookie[] cookies, String str) {
		  String result = null;
		  for (Cookie cookie : cookies) {
		   if (cookie.getName().equals(str)) {
		    result = cookie.getValue();
		    break;
		   }
		  }
		  return result;
	}
	
	  public static void main(String[] args) {      
	        UUID uuid = UUID.randomUUID();      
	        System.out.println(uuid.toString());      
	    
	  }     
}
