package com.sbgl.util;



import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class SpringUtil {
//	private static final String SubPackage = "bbs";	
	
	public static String getAppPath(){
		File f = new File("");
		String basePath = f.getAbsolutePath();
		return  basePath+"/src/applicationContext.xml";
	}
	

}
