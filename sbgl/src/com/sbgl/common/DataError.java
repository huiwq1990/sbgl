package com.sbgl.common;

public class DataError extends Exception {
	String errorMessage;
	 
    public DataError(String errorMessage)
    {
         this.errorMessage = errorMessage;
    }
 
    public String toString()
    {
         return errorMessage;
    }
 
    public String getMessage()
    {
         return errorMessage;
    }
}
