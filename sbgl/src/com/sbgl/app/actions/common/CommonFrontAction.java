package com.sbgl.app.actions.common;

import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.InternalContextAdapter;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.util.FrontDirective;

public class CommonFrontAction  extends ActionSupport implements FrontDirective  {

	@Override
	public void head(InternalContextAdapter cxt, Map param,
			VelocityContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void foot(InternalContextAdapter cxt, Map param,
			VelocityContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void window(InternalContextAdapter cxt, Map param,
			VelocityContext context) {
		// TODO Auto-generated method stub
		context.put("keyName", "asd");
	}
}