package com.sbgl.toolbox;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.tools.view.context.ViewContext;

import com.sbgl.util.ResourceUtils;

public class DateTool {

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
	
	/**
	 * 以友好的方式显示时间
	 * @param req
	 * @param time
	 * @return
	 */
	public static String friendly_time(HttpServletRequest req, Date time) {
		Locale loc = (req!=null)?req.getLocale():Locale.getDefault();
		if(time == null) return ResourceUtils.getString("ui", "unknown", loc);
		int ct = (int)((System.currentTimeMillis() - time.getTime())/1000);
		if(ct < 3600)
			return ResourceUtils.getStringForLocale(loc, "ui", "minutes_before", Math.max(ct / 60,1));
		if(ct >= 3600 && ct < 86400)
			return ResourceUtils.getStringForLocale(loc, "ui", "hours_before", ct / 3600);
		if(ct >= 86400 && ct < 2592000){ //86400 * 30
			int day = ct / 86400 ;			
			return ResourceUtils.getStringForLocale(loc, "ui", (day>1)?"days_before":"yesterday", day);
		}
		if(ct >= 2592000 && ct < 31104000) //86400 * 30
			return ResourceUtils.getStringForLocale(loc, "ui", "months_before", ct / 2592000);
		return ResourceUtils.getStringForLocale(loc, "ui", "years_before", ct / 31104000);		
	}
}
