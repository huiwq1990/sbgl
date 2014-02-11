package com.sbgl.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

import com.sbgl.app.common.computer.ComputerConfig;



public class WebpageSidebarDirective extends Directive {
	
	@Override
	public int getLine() {
		// TODO Auto-generated method stub
		return super.getLine();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return ComputerConfig.IncludeSidebarDirectiveName;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return LINE;
	}

	@Override
	public boolean render(InternalContextAdapter icad, Writer writer, Node node)
			throws IOException, ResourceNotFoundException, ParseErrorException,
			MethodInvocationException {
		Map params = getPareMap(icad, node);
		// 处理参数
		Object isPas = params.get("parameters");
		if (isPas != null) {
			setParem(params);
		}
		
//		当前页面
		String currentWebpage =  params.get("currentWebpage").toString();
//		模块
		String currentModel =  params.get("currentModel").toString().trim();
//		导航栏类型
		String currentType =  params.get("currentType").toString().trim();
		

//		 获取文件路径		
		String vmpath = "";
		if(params.containsKey("vmpath")){
			vmpath = params.get("vmpath").toString();
		}
//		如果文件为空，这根据模块设置默认
		if(vmpath==null || vmpath.length() == 0){
//			computer信息管理的导航栏
			if(ComputerConfig.ComputerModelName.equals(currentModel) && ComputerConfig.ComputerManageInfoIncludeSidebarType.equals(currentType) ){
				vmpath = ComputerConfig.ComputerManageInfoIncludeSidebarPath;
			}			
		}
		
		VelocityContext context = new VelocityContext();
		context.put("currentWebpage", currentWebpage);
//		web url
		context.put("webbaseurl", PropertyUtil.readValue("/system.properties", "webbaseurl"));

		try {
			
			icad.pushCurrentTemplateName(vmpath);
			Properties properties = new Properties();
			String basePath = ServletActionContext.getServletContext()
					.getRealPath("/");
			properties
					.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, basePath);
			// 设置velocity的编码
			properties.setProperty(Velocity.ENCODING_DEFAULT, "utf-8");
			properties.setProperty(Velocity.INPUT_ENCODING, "utf-8");
			properties.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
			// 首先创建一个模板引擎的实例，并予以初始化
			VelocityEngine engine = new VelocityEngine();
			engine.init(properties);
			// 接着，获得一个模板
			Template template = engine.getTemplate(icad
					.getCurrentTemplateName());

			// 现在，把模板和数据合并，输出到StringWriter
			StringWriter wri = new StringWriter();
			template.merge(context, wri);
			// 显示结果
			writer.write(wri.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	protected Map getPareMap(InternalContextAdapter contextAdapter, Node node)
			throws ParseErrorException, MethodInvocationException {

		Map paremMap = null;
		Node fChild = null;
		Object fValue = null;
		int paNum = node.jjtGetNumChildren();
		if (getType() == BLOCK) {
			paNum--;
		}
		if (paNum == 1 && null != (fChild = node.jjtGetChild(0))
				&& null != (fValue = fChild.value(contextAdapter))
				&& fValue instanceof Map) {
			paremMap = (Map) fValue;
		} else {
			paremMap = new HashMap();
			for (int index = 0, length = paNum; index < length; index++) {
				setParem(paremMap, contextAdapter, node.jjtGetChild(index));
			}
		}

		return paremMap;
	}

	protected void setParem(Map pasMap) throws ParseErrorException,
			MethodInvocationException {

		String[] str = pasMap.get("parameters").toString().split(",");
		int pasNum = str.length;
		for (int i = 0; i < pasNum; i++) {
			String key = str[i].split(":")[0];
			String value = str[i].split(":")[1];
			pasMap.put(key, value);
		}
	}

	protected void setParem(Map propertyMap,
			InternalContextAdapter contextAdapter, Node node)
			throws ParseErrorException, MethodInvocationException {
		// node.value uses the StrutsValueStack to evaluate the directive's
		// value parameter
		String param = node.value(contextAdapter).toString();
		int index = param.indexOf("=");
		if (index != -1) {
			String property = param.substring(0, index);
			String value = param.substring(index + 1);
			propertyMap.put(property, value);
		}
	}

}
