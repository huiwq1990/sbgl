package com.sbgl.util;

import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.InternalContextAdapter;

public interface ComputerDirective {
	@SuppressWarnings("unchecked")
	public abstract void manageComputercategoryFull(InternalContextAdapter cxt,Map param,VelocityContext context);
}
