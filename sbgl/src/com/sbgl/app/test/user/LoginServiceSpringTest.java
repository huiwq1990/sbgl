package com.sbgl.app.test.user;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;



import com.sbgl.app.entity.Computerconfig;
import com.sbgl.app.entity.ComputerconfigFull;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.services.computer.ComputerconfigService;
import com.sbgl.util.*;
import com.sbgl.app.services.common.*;
import com.sbgl.app.services.login.LoginService;

public class LoginServiceSpringTest {

	public static void main(String[] args) {
		ApplicationContext cxt = new FileSystemXmlApplicationContext(SpringUtil.getAppPath());

		LoginService loginService = (LoginService) cxt.getBean("loginService");
		
		Loginuser lu = loginService.selById(1);
		System.out.println(lu.getName());
	}
	
}
