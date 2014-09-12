package com.sbgl.app.exception;

public class ServiceDataError extends Exception {
	String errorcode;
	 
    public ServiceDataError(String errorcode)
    {
         this.errorcode = errorcode;
    }
 
    public String toString()
    {
         return errorcode;
    }
 
    public String getMessage()
    {
         return errorcode;
    }
}
