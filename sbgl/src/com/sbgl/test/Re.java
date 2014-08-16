package com.sbgl.test;

import java.util.Iterator;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Re {

	/**
	 * @param args
	 * @throws ConfigurationException 
	 */
	public static void main(String[] args) throws ConfigurationException {
		// TODO Auto-generated method stub
		   CompositeConfiguration config = new CompositeConfiguration();
		config.addConfiguration(new PropertiesConfiguration("messages_en_US_be.properties"));
		Iterator<String> keys = config.getKeys();        
       System.out.println(config.getString("usercenter_accountsetup"));
	}

}
