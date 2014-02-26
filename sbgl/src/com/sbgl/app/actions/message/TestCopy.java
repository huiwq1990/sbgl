package com.sbgl.app.actions.message;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.sbgl.app.entity.Msgreceive;
import com.sbgl.app.entity.Msgsend;

public class TestCopy {

	/**
	 * @param args
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub

		Msgreceive r = new Msgreceive();
		
		r.setId(1);
		Msgsend s = new Msgsend();
		
		BeanUtils.copyProperties(s, r);	
		
		System.out.println(s.getId());
	}

}
