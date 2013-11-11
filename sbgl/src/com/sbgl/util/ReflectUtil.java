package com.sbgl.util;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.sbgl.app.entity.Bbstag;

/**
 * 通过class信息获取javebean中属性信息
 * 
 * @author liub
 * 
 *         2012-10-23
 */
public class ReflectUtil {
	public static HashMap<String, Method> ConverBean(Class<?> drbean) {
		Class<?> stopClass = null;
		// 存放class信息
		BeanInfo drbeaninfo = null;
		// 存放属性信息
		PropertyDescriptor[] props;
		HashMap<String, Method> map = new HashMap<String, Method>();
		try {
			// 获取class中得属性方法信息
			drbeaninfo = Introspector.getBeanInfo(drbean, stopClass);
			// 把class中属性放入PropertyDescriptor数组中
			props = drbeaninfo.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				// 获取属性所对应的set方法
				Method setMethod = props[i].getWriteMethod();
				// 判断属性是否有set方法 如果有放入map<属性名，set方法>中
				if (setMethod != null) {
					String field = props[i].getName().toLowerCase();
					map.put(field, setMethod);
				}
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		HashMap<String, Method> map = ConverBean(Bbstag.class);
		Object obj = Bbstag.class.newInstance();
		String[] attrs = { "id", "name"};
		
		Object[] params = { Long.valueOf("1"), "123456" };
		Method method = null;
		for (int i = 0; i < attrs.length; i++) {
			method = map.get(attrs[i]);
			method.invoke(obj, params[i]);
		}

		Bbstag user = (Bbstag) obj;
//		System.out.println(user.getUsername() + "\t" + user.getUserpwd() + "\t"
//				+ user.getArg() + "\t" + user.getMoney());

	}
}