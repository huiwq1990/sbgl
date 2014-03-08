package com.sbgl.util;

import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;



public class DateConverter implements Converter {

    public Object convert(Class type, Object value) {
    if(value == null) {
        return null;
    }
    
    if(value instanceof Date) {
        return value;
    }
    

        
 
    }
}