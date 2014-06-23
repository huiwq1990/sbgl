package com.sbgl.util;

import java.util.Iterator;
import java.util.Properties;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesToMap {
    public static void main(String[] args) throws ConfigurationException {

    	
//    	PropertiesConfiguration configuration = new PropertiesConfiguration("messages_en_US.properties");

        CompositeConfiguration config = new CompositeConfiguration();
        
        config.addConfiguration(new PropertiesConfiguration("messages_en_US.properties"));
        Iterator<String> keys = config.getKeys();        
        Map<String,String> textMap = new ConcurrentHashMap<String,String>();
        while(keys.hasNext()){
        	String key = keys.next();
        	textMap.put(key, config.getString(key));
        }
        
        Set propertySet = textMap.entrySet();
        for (Object o : propertySet) {
            Map.Entry entry = (Map.Entry) o;
            System.out.printf("%s = %s%n", entry.getKey(), entry.getValue());
        }
    }
}
