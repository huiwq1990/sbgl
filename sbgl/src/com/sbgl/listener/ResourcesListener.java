package com.sbgl.listener;

import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.sbgl.app.actions.common.CommonConfig;

/**http://hi.baidu.com/wangguoqingsll/item/c8c3cd3520deb8403175a1bc?qq-pf-to=pcqq.c2c*/
public class ResourcesListener extends HttpServlet implements
		ServletContextListener {
	public ResourcesListener() {
	}

	private Timer timer = null;
	/**
  * 
  * 
  */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
		arg0.getServletContext().log("定时器销毁");
	}

	public void contextInitialized(ServletContextEvent sce) {
		timer = new Timer(true);
		sce.getServletContext().log("定时器启动");
//		timer.schedule(new MyTask(sce.getServletContext()), 0, 60 * 60 * 1000);
		timer.schedule(new MyTask(sce.getServletContext()), 0,  2 * 1000);
		sce.getServletContext().log("添加到任务调度表");
	}

	class MyTask extends TimerTask {

		private boolean isRunning = false;
		private ServletContext context = null;

		public MyTask(ServletContext context) {
			this.context = context;
		}

		public void run() {
			
			if (!isRunning) {
		        CompositeConfiguration config = new CompositeConfiguration();
		        Map<String,String> textMap = new ConcurrentHashMap<String,String>();
		        try {
					//config.addConfiguration(new PropertiesConfiguration("messages_zh_CN.properties"));
					config.addConfiguration(new PropertiesConfiguration("messages_zh_CN_hg.properties"));
					config.addConfiguration(new PropertiesConfiguration("messages_zh_CN_wm.properties"));
					config.addConfiguration(new PropertiesConfiguration("messages_zh_CN_be.properties"));
					Iterator<String> keys = config.getKeys();        
			       
			        while(keys.hasNext()){
			        	String key = keys.next();
			        	textMap.put(key, config.getString(key));
			        }
			        
				} catch (ConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
				context.setAttribute(CommonConfig.resourcetextmapch, textMap);
				
				config = new CompositeConfiguration();
				 Map<String,String>  textMapEn = new ConcurrentHashMap<String,String>();
		        try {
					//config.addConfiguration(new PropertiesConfiguration("messages_en_US.properties"));
					config.addConfiguration(new PropertiesConfiguration("messages_en_US_hg.properties"));
					config.addConfiguration(new PropertiesConfiguration("messages_en_US_wm.properties"));
					config.addConfiguration(new PropertiesConfiguration("messages_en_US_be.properties"));
					Iterator<String> keys = config.getKeys();        
//			       System.out.println(config.getString("usercenter_accountsetup"));
			        while(keys.hasNext()){
			        	String key = keys.next();
			        	textMapEn.put(key, config.getString(key));
			        }
			        
				} catch (ConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				context.setAttribute(CommonConfig.resourcetextmapen, textMapEn);
				
			}
		}
	}
	
}
