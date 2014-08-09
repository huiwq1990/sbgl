package com.sbgl.app.services.login.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.LoginDao;
import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.services.login.LoginService;

@Scope("prototype") 
@Service("loginService")
public class LoginServiceImpl implements LoginService{

	@Resource
	private LoginDao loginDao;
	@Resource
	private BaseDao baseDao;
	
	@Override
	public Loginuser findUser(Loginuser loginuser) {
		// TODO Auto-generated method stub
		return loginDao.findUser(loginuser);
	}

	@Override
	public Loginuser checkUser(int uid) {
		// TODO Auto-generated method stub
		return loginDao.checkUser(uid);
	}

	@Override
	public Loginuser selById(int uid) {
		// TODO Auto-generated method stub
		return loginDao.selById(uid);
	}

}
