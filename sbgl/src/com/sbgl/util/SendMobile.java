package com.sbgl.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendMobile {
	
	String strURL;
	String mobile;
	String content;
	String msgid;
	String rLine;
	String returnValue="";
    Date date=new Date();
    DateFormat d=new SimpleDateFormat("yyMMddHHmmssSSSS");      
    public SendMobile(String inMobile,String inContent)
	{
		this.mobile=inMobile;
		this.content=inContent;  
		msgid=d.format(date);
	}
	
	public String start()
	{
		try {
			strURL="http://s.ccme.cc/qxt/send.jsp?circle=159net_5920&pwd=hls3655&mobile="+mobile+"&service=9244866859506&msgid="+msgid+"&message="+content;
			URL url = new URL(strURL);	
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			InputStream in = null;
			in = url.openStream();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(in,"utf-8"));			
			while ((rLine = bReader.readLine()) != null) {
				returnValue=returnValue+rLine;
			}
			returnValue=returnValue.trim();		
		} catch (Exception e) {
			returnValue="-1";
			e.printStackTrace();
		}    
		return returnValue;
	}	
}
