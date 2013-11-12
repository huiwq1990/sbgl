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

import com.sbgl.app.dao.ComputercategoryDao;
import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.ComputercategoryFull;
import com.sbgl.util.*;

@Repository("computercategoryDao")
public class ComputercategoryDaoImpl extends HibernateDaoSupport implements ComputercategoryDao{

	private static final Log log = LogFactory.getLog(ComputercategoryDaoImpl.class);
	
//  删除实体
	public int deleteEntity(Integer computercategoryId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Computercategory as tb set tb.status = 0 where tb.id = " +computercategoryId; 
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
	public ComputercategoryFull selectComputercategoryFullById(Integer computercategoryId) {
		final String  sql = "select a.id as computercategoryid, a.parentcomputercategoryid as computercategoryparentcomputercategoryid, a.name as computercategoryname, a.createtime as computercategorycreatetime, a.createuserid as computercategorycreateuserid, a.status as computercategorystatus, b.id as parentcomputercategoryid, b.parentcomputercategoryid as parentcomputercategoryparentcomputercategoryid, b.name as parentcomputercategoryname, b.createtime as parentcomputercategorycreatetime, b.createuserid as parentcomputercategorycreateuserid, b.status as parentcomputercategorystatus, c.id as loginuserid, c.name as loginusername, c.createtime as loginusercreatetime, c.status as loginuserstatus from Computercategory a  left join Computercategory b on a.parentcomputercategoryid=b.id left join Computercategory c on a.parentcomputercategoryid=c.id " + "where a.id = "+computercategoryId;
		
		List<ComputercategoryFull> computercategoryList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputercategoryFull.class));
						return query.list();
					}
				});
		if (computercategoryList != null && !computercategoryList.isEmpty()) {
			return computercategoryList.get(0);
		}
		return null;
	}

/*
	@Override
	public List<ComputercategoryFull> selectComputercategoryFullByUserId(Integer userId) {
		final String  sql = "select a.id as computercategoryid, a.parentcomputercategoryid as computercategoryparentcomputercategoryid, a.name as computercategoryname, a.createtime as computercategorycreatetime, a.createuserid as computercategorycreateuserid, a.status as computercategorystatus, b.id as parentcomputercategoryid, b.parentcomputercategoryid as parentcomputercategoryparentcomputercategoryid, b.name as parentcomputercategoryname, b.createtime as parentcomputercategorycreatetime, b.createuserid as parentcomputercategorycreateuserid, b.status as parentcomputercategorystatus, c.id as loginuserid, c.name as loginusername, c.createtime as loginusercreatetime, c.status as loginuserstatus from Computercategory a  left join Computercategory b on a.parentcomputercategoryid=b.id left join Computercategory c on a.parentcomputercategoryid=c.id " + "where a.userid = "+userId;
		
		List<ComputercategoryFull> computercategoryList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputercategoryFull.class));
						return query.list();
					}
				});
		if (computercategoryList != null && !computercategoryList.isEmpty()) {
			return computercategoryList;
		}
		return null;
	}*/
	
//	查询全部实体full
	@Override
	public List<ComputercategoryFull> selectComputercategoryFullAll() {
		final String  sql = "select a.id as computercategoryid, a.parentcomputercategoryid as computercategoryparentcomputercategoryid, a.name as computercategoryname, a.createtime as computercategorycreatetime, a.createuserid as computercategorycreateuserid, a.status as computercategorystatus, b.id as parentcomputercategoryid, b.parentcomputercategoryid as parentcomputercategoryparentcomputercategoryid, b.name as parentcomputercategoryname, b.createtime as parentcomputercategorycreatetime, b.createuserid as parentcomputercategorycreateuserid, b.status as parentcomputercategorystatus, c.id as loginuserid, c.name as loginusername, c.createtime as loginusercreatetime, c.status as loginuserstatus from Computercategory a  left join Computercategory b on a.parentcomputercategoryid=b.id left join Computercategory c on a.parentcomputercategoryid=c.id ";
		
		List<ComputercategoryFull> computercategoryFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputercategoryFull.class));
						return query.list();
					}
				});
		if (computercategoryFullList != null && !computercategoryFullList.isEmpty()) {			
			return computercategoryFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputercategoryFull> selectComputercategoryFullByPage(Page page){
		final String  sql = "select a.id as computercategoryid, a.parentcomputercategoryid as computercategoryparentcomputercategoryid, a.name as computercategoryname, a.createtime as computercategorycreatetime, a.createuserid as computercategorycreateuserid, a.status as computercategorystatus, b.id as parentcomputercategoryid, b.parentcomputercategoryid as parentcomputercategoryparentcomputercategoryid, b.name as parentcomputercategoryname, b.createtime as parentcomputercategorycreatetime, b.createuserid as parentcomputercategorycreateuserid, b.status as parentcomputercategorystatus, c.id as loginuserid, c.name as loginusername, c.createtime as loginusercreatetime, c.status as loginuserstatus from Computercategory a  left join Computercategory b on a.parentcomputercategoryid=b.id left join Computercategory c on a.parentcomputercategoryid=c.id ";
		
		List<ComputercategoryFull> computercategoryFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputercategoryFull.class));
						return query.list();
					}
				});
		if (computercategoryFullList != null && !computercategoryFullList.isEmpty()) {			
			return computercategoryFullList;
		}
		return null;
	}
	
//  根据关联查询实体full

	//根据关联查询实体 
	public List<Computercategory> selectComputercategoryByComputercategoryId(Integer parentcomputercategoryid ){
	
		return null;
	}
	public List<Computercategory> selectComputercategoryByLoginuserId(Integer createuserid ){
	
		return null;
	}
  

	public List<ComputercategoryFull> selectComputercategoryFullByComputercategoryId(Integer parentcomputercategoryid ){
	
		return null;
	}
	public List<ComputercategoryFull> selectComputercategoryFullByLoginuserId(Integer createuserid ){
	
		return null;
	}

 
}