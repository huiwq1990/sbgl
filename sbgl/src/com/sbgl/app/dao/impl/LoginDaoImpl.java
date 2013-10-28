package com.sbgl.app.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sbgl.app.dao.LoginDao;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.util.EscColumnToBean;

@Repository("loginDao")
public class LoginDaoImpl extends HibernateDaoSupport implements LoginDao {

	@Override
	public Loginuser findUser(Loginuser loginuser) {
		// TODO Auto-generated method stub
		final String sql = "select * from loginuser where userid='"+loginuser.getUserId()+"' " +
				" and password='"+loginuser.getPassword()+"' ";
		List<Loginuser> loginUserList = getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(new EscColumnToBean(Loginuser.class));
				return query.list();
			}
		});	
		if(loginUserList!=null&&!loginUserList.isEmpty()){
			loginuser = loginUserList.get(0);
			return loginuser;
		}
		return null;
	}
	
	

}
