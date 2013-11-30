package com.sbgl.util;

import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.InternalContextAdapter;

public interface FrontDirective {
	@SuppressWarnings("unchecked")
	public abstract void head(InternalContextAdapter cxt,Map param,VelocityContext context);
	@SuppressWarnings("unchecked")
	public abstract void foot(InternalContextAdapter cxt,Map param,VelocityContext context);
	@SuppressWarnings("unchecked")
	public abstract void window(InternalContextAdapter cxt,Map param,VelocityContext context);
}
