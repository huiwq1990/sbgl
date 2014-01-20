package com.sbgl.test;

import java.lang.reflect.Field;

import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentdetail;


public class test {
public static void main(String[] args) {
		
		Field[] fields = Equipment.class.getDeclaredFields();  
		for (Field field : fields) {
			String name = field.getName();
			name = name.substring(0,1).toUpperCase() + name.substring(1,name.length()); 
			name = "a."+name+",";
			System.out.print(name);
        }  
	}
}
