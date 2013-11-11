package com.sbgl.app.dao.impl;


import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.DaoAbs;

import com.sbgl.app.dao.ComputerDao;
import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.ComputerFull;
import com.sbgl.util.*;

@Repository("computerDao")
public class ComputerDaoImpl extends HibernateDaoSupport implements ComputerDao{

	private static final Log log = LogFactory.getLog(ComputerDaoImpl.class);
	
//  删除实体
	public int deleteEntity(Integer computerId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Computer as tb set tb.status = 0 where tb.id = " +computerId; 
        	Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hqlString);    	   	
        	int ret=query.executeUpdate();

            log.debug("删除:"+ret);
            return ret;
        } catch (RuntimeException re) {
            log.error("删除失败", re);
            throw re;
        }
	}

//  根据实体id查询实体full	
	@Override
	public ComputerFull selectComputerFullById(Integer computerId) {
		final String  sql = "select a.id as computerid, a.serialnumber as computerserialnumber, a.computermodelid as computercomputermodelid, a.createtime as computercreatetime, a.createuserid as computercreateuserid, a.status as computerstatus, b.id as computermodelid, b.name as computermodelname, b.computercategoryid as computermodelcomputercategoryid, b.picpath as computermodelpicpath, b.createtime as computermodelcreatetime, b.createuserid as computermodelcreateuserid, b.status as computermodelstatus, c.id as loginuserid, c.name as loginusername, c.createtime as loginusercreatetime, c.status as loginuserstatus from Computer a  left join Computermodel b on a.computermodelid=b.id left join Computermodel c on a.computermodelid=c.id " + "where a.id = "+computerId;
		
		List<ComputerFull> computerList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerFull.class));
						return query.list();
					}
				});
		if (computerList != null && !computerList.isEmpty()) {
			return computerList.get(0);
		}
		return null;
	}

/*
	@Override
	public List<ComputerFull> selectComputerFullByUserId(Integer userId) {
		final String  sql = "select a.id as computerid, a.serialnumber as computerserialnumber, a.computermodelid as computercomputermodelid, a.createtime as computercreatetime, a.createuserid as computercreateuserid, a.status as computerstatus, b.id as computermodelid, b.name as computermodelname, b.computercategoryid as computermodelcomputercategoryid, b.picpath as computermodelpicpath, b.createtime as computermodelcreatetime, b.createuserid as computermodelcreateuserid, b.status as computermodelstatus, c.id as loginuserid, c.name as loginusername, c.createtime as loginusercreatetime, c.status as loginuserstatus from Computer a  left join Computermodel b on a.computermodelid=b.id left join Computermodel c on a.computermodelid=c.id " + "where a.userid = "+userId;
		
		List<ComputerFull> computerList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerFull.class));
						return query.list();
					}
				});
		if (computerList != null && !computerList.isEmpty()) {
			return computerList;
		}
		return null;
	}*/
	
//	查询全部实体full
	@Override
	public List<ComputerFull> selectComputerFullAll() {
		final String  sql = "select a.id as computerid, a.serialnumber as computerserialnumber, a.computermodelid as computercomputermodelid, a.createtime as computercreatetime, a.createuserid as computercreateuserid, a.status as computerstatus, b.id as computermodelid, b.name as computermodelname, b.computercategoryid as computermodelcomputercategoryid, b.picpath as computermodelpicpath, b.createtime as computermodelcreatetime, b.createuserid as computermodelcreateuserid, b.status as computermodelstatus, c.id as loginuserid, c.name as loginusername, c.createtime as loginusercreatetime, c.status as loginuserstatus from Computer a  left join Computermodel b on a.computermodelid=b.id left join Computermodel c on a.computermodelid=c.id ";
		
		List<ComputerFull> computerFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerFull.class));
						return query.list();
					}
				});
		if (computerFullList != null && !computerFullList.isEmpty()) {			
			return computerFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputerFull> selectComputerFullByPage(Page page){
		final String  sql = "select a.id as computerid, a.serialnumber as computerserialnumber, a.computermodelid as computercomputermodelid, a.createtime as computercreatetime, a.createuserid as computercreateuserid, a.status as computerstatus, b.id as computermodelid, b.name as computermodelname, b.computercategoryid as computermodelcomputercategoryid, b.picpath as computermodelpicpath, b.createtime as computermodelcreatetime, b.createuserid as computermodelcreateuserid, b.status as computermodelstatus, c.id as loginuserid, c.name as loginusername, c.createtime as loginusercreatetime, c.status as loginuserstatus from Computer a  left join Computermodel b on a.computermodelid=b.id left join Computermodel c on a.computermodelid=c.id ";
		
		List<ComputerFull> computerFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerFull.class));
						return query.list();
					}
				});
		if (computerFullList != null && !computerFullList.isEmpty()) {			
			return computerFullList;
		}
		return null;
	}
	
//  根据关联查询实体full

	//根据关联查询实体 
	public List<Computer> selectComputerByComputermodelId(Integer computermodelid ){
	
		return null;
	}
	public List<Computer> selectComputerByLoginuserId(Integer createuserid ){
	
		return null;
	}
  

	public List<ComputerFull> selectComputerFullByComputermodelId(Integer computermodelid ){
	
		return null;
	}
	public List<ComputerFull> selectComputerFullByLoginuserId(Integer createuserid ){
	
		return null;
	}

 
}