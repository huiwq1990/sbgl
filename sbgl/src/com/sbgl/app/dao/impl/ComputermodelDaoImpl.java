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

import com.sbgl.app.dao.ComputermodelDao;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.util.*;

@Repository("computermodelDao")
public class ComputermodelDaoImpl extends HibernateDaoSupport implements ComputermodelDao{

	private static final Log log = LogFactory.getLog(ComputermodelDaoImpl.class);
	
//  删除实体
	public int deleteEntity(Integer computermodelId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Computermodel as tb set tb.status = 0 where tb.id = " +computermodelId; 
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
	public ComputermodelFull selectComputermodelFullById(Integer computermodelId) {
		final String  sql = "select a.id as computermodelid, a.name as computermodelname, a.computercategoryid as computermodelcomputercategoryid, a.picpath as computermodelpicpath, a.createtime as computermodelcreatetime, a.createuserid as computermodelcreateuserid, a.status as computermodelstatus, b.id as computercategoryid, b.parentcomputercategoryid as computercategoryparentcomputercategoryid, b.name as computercategoryname, b.createtime as computercategorycreatetime, b.createuserid as computercategorycreateuserid, b.status as computercategorystatus, c.id as loginuserid, c.name as loginusername, c.createtime as loginusercreatetime, c.status as loginuserstatus from Computermodel a  left join Computercategory b on a.computercategoryid=b.id left join Computercategory c on a.computercategoryid=c.id " + "where a.id = "+computermodelId;
		
		List<ComputermodelFull> computermodelList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputermodelFull.class));
						return query.list();
					}
				});
		if (computermodelList != null && !computermodelList.isEmpty()) {
			return computermodelList.get(0);
		}
		return null;
	}

/*
	@Override
	public List<ComputermodelFull> selectComputermodelFullByUserId(Integer userId) {
		final String  sql = "select a.id as computermodelid, a.name as computermodelname, a.computercategoryid as computermodelcomputercategoryid, a.picpath as computermodelpicpath, a.createtime as computermodelcreatetime, a.createuserid as computermodelcreateuserid, a.status as computermodelstatus, b.id as computercategoryid, b.parentcomputercategoryid as computercategoryparentcomputercategoryid, b.name as computercategoryname, b.createtime as computercategorycreatetime, b.createuserid as computercategorycreateuserid, b.status as computercategorystatus, c.id as loginuserid, c.name as loginusername, c.createtime as loginusercreatetime, c.status as loginuserstatus from Computermodel a  left join Computercategory b on a.computercategoryid=b.id left join Computercategory c on a.computercategoryid=c.id " + "where a.userid = "+userId;
		
		List<ComputermodelFull> computermodelList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputermodelFull.class));
						return query.list();
					}
				});
		if (computermodelList != null && !computermodelList.isEmpty()) {
			return computermodelList;
		}
		return null;
	}*/
	
//	查询全部实体full
	@Override
	public List<ComputermodelFull> selectComputermodelFullAll() {
	final String  sql = "select a.id as computermodelid, a.name as computermodelname, a.computercategoryid as computermodelcomputercategoryid, a.picpath as computermodelpicpath, a.createtime as computermodelcreatetime, a.createuserid as computermodelcreateuserid, a.status as computermodelstatus, b.id as computercategoryid, b.parentcomputercategoryid as computercategoryparentcomputercategoryid, b.name as computercategoryname, b.createtime as computercategorycreatetime, b.createuserid as computercategorycreateuserid, b.status as computercategorystatus, c.id as loginuserid, c.name as loginusername, c.createtime as loginusercreatetime, c.status as loginuserstatus from Computermodel a  left join Computercategory b on a.computercategoryid=b.id left join Computercategory c on a.computercategoryid=c.id ";
		
		List<ComputermodelFull> computermodelList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputermodelFull.class));
						return query.list();
					}
				});
		if (computermodelList != null && !computermodelList.isEmpty()) {			
			return computermodelList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputermodelFull> selectComputermodelFullByPage(Page page){
		
			return null;
	}
	
//  根据关联查询实体full

	//根据关联查询实体 
	public List<Computermodel> selectComputermodelByComputercategoryId(Integer computercategoryid ){
	
		return null;
	}
	public List<Computermodel> selectComputermodelByLoginuserId(Integer createuserid ){
	
		return null;
	}
  

	public List<ComputermodelFull> selectComputermodelFullByComputercategoryId(Integer computercategoryid ){
	
		return null;
	}
	public List<ComputermodelFull> selectComputermodelFullByLoginuserId(Integer createuserid ){
	
		return null;
	}

 
}