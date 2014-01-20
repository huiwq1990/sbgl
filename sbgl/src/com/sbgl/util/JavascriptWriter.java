package com.sbgl.util;

import java.io.PrintWriter;

public class JavascriptWriter {
	
	PrintWriter tPrintWriter;
	public  JavascriptWriter(PrintWriter writer){
		this.tPrintWriter = writer;
	}
	
	
	/**
	 * 
	 * 调用父页面的aftersubmit
	 * @param flag
	 * @param msg
	 */
	public void wirteToParent(String flag,String msg){
		StringBuffer sb = new StringBuffer();
		sb.append("<script language=\"javascript\">parent.afterSubmit('"+flag+"','"+msg+"')</script>");
		tPrintWriter.write(sb.toString());
		tPrintWriter.flush();
		tPrintWriter.close();
		
	}
	
	

}
