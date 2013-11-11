package com.sbgl.util;

import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.InternalContextAdapter;

public interface ScDirective {
	public abstract void head(InternalContextAdapter cxt,Map param,VelocityContext context);
}
