package com.sbgl.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;

public final class PropertyUtil {
    private final static Logger logger = Logger.getLogger(PropertyUtil.class);
    
    public static String readValue(String filePath, String key) {
	Properties props = new Properties();
	InputStream in = null;
	try {
	    in = PropertyUtil.class.getResourceAsStream(filePath);
	    props.load(in);
	    String value = props.getProperty(key);
	    if(logger.isDebugEnabled()){
		logger.debug(key + value);
	    }
	    return value;
	} catch (Exception e) {
	    logger.error(e.getMessage(),e);
	}finally{
	    if(in != null){
		try {
		    in.close();
		} catch (IOException e) {
		    logger.error(e.getMessage(),e);
		}
	    }	
	}
	return null;
    }

    public static void readProperties(String filePath) {
	Properties props = new Properties();
	InputStream in = null;
	try {
	    in = PropertyUtil.class.getResourceAsStream(filePath);
	    props.load(in);
	    Enumeration en = props.propertyNames();
	    String key,property;
	    while (en.hasMoreElements()) {
		key = (String) en.nextElement();
		property = props.getProperty(key);
		if(logger.isDebugEnabled()){
		    logger.debug(key + property);
		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage(),e);
	}finally{
	    if(in != null){
		try {
		    in.close();
		} catch (IOException e) {
		    logger.error(e.getMessage(),e);
		}
	    }	
	}
    }

    public static void writeProperties(String filePath, String parameterName,String parameterValue) {
	Properties prop = new Properties();
	InputStream fis = null;
	OutputStream fos = null;
	try {
	    fis = PropertyUtil.class.getResourceAsStream(filePath);
	    prop.load(fis);
	    String path = PropertyUtil.class.getResource(filePath).getPath();
	    fos = new FileOutputStream(path);
	    prop.setProperty(parameterName, parameterValue);
	    synchronized (prop) {
		prop.store(fos, "Update '" + parameterName + "' value");
	    }
	} catch (IOException e) {
	    logger.error(new StringBuilder("Visit ").append(filePath).append(" for updating ").append(parameterName).append(" value error").toString(), e);
	}finally{
	    try {
		if(fis != null)
		    fis.close();
		if(fos != null)
		    fos.close();
	    } catch (Exception e) {
		logger.error(e.getMessage(),e);
	    }
	}
    }

    public static void main(String[] args) {
	readValue("/config.properties", "curlucenestatus");
	writeProperties("/config.properties", "curlucenestatus", "222");
	System.out.println("OK");
    }
}