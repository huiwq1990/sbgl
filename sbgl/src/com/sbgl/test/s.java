package com.sbgl.test;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class s {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CompositeConfiguration config = new CompositeConfiguration();
		config.addConfiguration(new PropertiesConfiguration("messages_en_US.properties"));
		Iterator<String> keys = config.getKeys();        
		 Map<String,String>  textMapEn = new ConcurrentHashMap<String,String>();
		 AbstractConfiguration.setDefaultListDelimiter('-');  
        while(keys.hasNext()){
        	String key = keys.next();
        	if(key.equals("computerordersuccess_p3")){
        		System.out.println( config.getString(key));
        	}
        	textMapEn.put(key, config.getString(key));
        }
        System.out.println(textMapEn.get("computerordersuccess_p3"));
	}

}
