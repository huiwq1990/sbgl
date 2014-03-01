package com.sbgl.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.transform.ResultTransformer;

import com.sbgl.app.dao.DaoAbs;
 
/**
 * 自定义的数据库字库转换成POJO 
 * @author wm
 *
 */
public class EscColumnToBean implements ResultTransformer {  
    private static final long serialVersionUID = 1L;  
    private final Class<? extends DaoAbs> resultClass;  
    private Setter[] setters;  
    private PropertyAccessor propertyAccessor;  
      
    public EscColumnToBean(Class<? extends DaoAbs> resultClass) {  
        if(resultClass==null) throw new IllegalArgumentException("resultClass cannot be null");  
        this.resultClass = resultClass;  
        propertyAccessor = new ChainedPropertyAccessor(new PropertyAccessor[] { PropertyAccessorFactory.getPropertyAccessor(resultClass,null), PropertyAccessorFactory.getPropertyAccessor("field")});        
    }  

	//结果转换时，HIBERNATE调用此方法  
    public Object transformTuple(Object[] tuple, String[] aliases) {  
        Object result;  
          
        try {  
            if(setters==null) {//首先初始化，取得目标POJO类的所有SETTER方法  
                setters = new Setter[aliases.length];  
                for (int i = 0; i < aliases.length; i++) {  
                    String alias = aliases[i];  
                   if(alias != null) {  
                        //我的逻辑主要是在getSetterByColumnName方法里面，其它都是HIBERNATE的另一个类中COPY的  
                        //这里填充所需要的SETTER方法  
                        setters[i] = getSetterByColumnName(alias);  
                    }  
               }  
            }  
            result = resultClass.newInstance();          
            //这里使用SETTER方法填充POJO对象  
            for (int i = 0; i < aliases.length; i++) {  
                if(setters[i]!=null) { 
                	if (tuple[i] instanceof Integer) {
                        int value = ((Integer) tuple[i]).intValue();
                        setters[i].set(result, value, null);
                    } else if (tuple[i] instanceof String) {
                        String s = (String) tuple[i];
                        setters[i].set(result, s, null);
                    } else if (tuple[i] instanceof Double) {
                        double d = Double.parseDouble(tuple[i].toString());                    
                        setters[i].set(result, d, null);
                    } else if (tuple[i] instanceof Float) {
                        float f = ((Float) tuple[i]).floatValue();
                        setters[i].set(result, f, null);
                    } else if (tuple[i] instanceof Long) {
                    	long l = Long.parseLong(tuple[i].toString());  
                        setters[i].set(result, l, null);
                    } else if (tuple[i] instanceof Boolean) {
                        boolean b = ((Boolean) tuple[i]).booleanValue();
                        setters[i].set(result, b, null);
                    } else if (tuple[i] instanceof Date) {
                        Date d = (Date) tuple[i];
                        setters[i].set(result, d, null);
                    } else if (tuple[i] instanceof BigDecimal) {
                    	Field[] fields = resultClass.getDeclaredFields();
                    	String alias = aliases[i].replaceAll("_", "").toLowerCase(); 
                    	for (Field field : fields) {  
                            if(field.getName().toLowerCase().equals(alias)){//去除下杠的字段名如果和属性名对得上，就取这个SETTER方法  
                            	if(field.getType().toString().indexOf("Double")>0){
                            		double l = ((BigDecimal) tuple[i]).doubleValue(); 
                                    setters[i].set(result, l, null);
                            	}else if(field.getType().toString().indexOf("Long")>0){
                            		Long l = ((BigDecimal) tuple[i]).longValue(); 
                            		setters[i].set(result, l, null);
                            	}else{
                            		setters[i].set(result, tuple[i], null);
                            	}   
                            	break;
                            }  
                        }    	        
                    }  else if (tuple[i] instanceof Character) {
                    	String s = ((Character) tuple[i]).toString(); 
                        setters[i].set(result, s, null);
                    } else if (tuple[i] instanceof BigInteger) {
                    	long l = Long.parseLong(tuple[i].toString());  
                        setters[i].set(result, l, null);
                    } else {
                        setters[i].set(result, tuple[i], null);
                    }
                  
                }  
            }  
        } catch (InstantiationException e) {  
            throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());  
        } catch (IllegalAccessException e) {  
            throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());  
        }  
        
    
        
          
        return result;  
    }  
  
    //根据数据库字段名在POJO查找JAVA属性名，参数就是数据库字段名，如：USER_ID  
    private Setter getSetterByColumnName(String alias) {  
        //取得POJO所有属性名  
    	Field[] fields = resultClass.getDeclaredFields();  

        if(fields==null || fields.length==0){  
            throw new RuntimeException("实体"+resultClass.getName()+"不含任何属性");  
        }  
        //把字段名中所有的下杠去除  
        String proName = alias.replaceAll("_", "").toLowerCase();  
        
       for (Field field : fields) {  
            if(field.getName().toLowerCase().equals(proName)){//去除下杠的字段名如果和属性名对得上，就取这个SETTER方法  
                return propertyAccessor.getSetter(resultClass, field.getName());  
            }else if(field.getName().toLowerCase().equals("void_")&&proName.equals("void")){
            	 return propertyAccessor.getSetter(resultClass, field.getName()); 
            }
        }  
        throw new RuntimeException("找不到数据库字段 ："+ alias + " 对应的POJO属性或其getter方法，比如数据库字段为USER_ID或USERID，那么JAVA属性应为userId");  
    }  
  
    @SuppressWarnings("unchecked")  
    public List transformList(List collection) {  
        return collection;  
    }  
  
} 
