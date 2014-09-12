package com.sbgl.app.exception;

public class ServiceDataCodeError extends Exception {
	String errorcode;
	 
    public ServiceDataCodeError(String errorcode)
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
