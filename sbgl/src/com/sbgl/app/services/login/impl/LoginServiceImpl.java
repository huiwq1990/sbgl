package com.sbgl.app.services.login.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.LoginDao;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.services.login.LoginService;
import com.sbgl.util.EscColumnToBean;

@Scope("prototype") 
@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService{

	@Resource
	private BaseDao baseDao;
	
	@Resource
	private LoginDao loginDao;
	
	@Override
	public Loginuser findUser(Loginuser loginuser) {
		// TODO Auto-generated method stub
		return loginDao.findUser(loginuser);
	}

}
