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

import com.sbgl.app.dao.ComputerorderdetailDao;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.util.*;

@Repository("computerorderdetailDao")
public class ComputerorderdetailDaoImpl extends HibernateDaoSupport implements ComputerorderdetailDao{

	private static final Log log = LogFactory.getLog(ComputerorderdetailDaoImpl.class);
	
//  删除实体
	public int deleteEntity(Integer computerorderdetailId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Computerorderdetail as tb set tb.status = 0 where tb.id = " +computerorderdetailId; 
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
	public ComputerorderdetailFull selectComputerorderdetailFullById(Integer computerorderdetailId) {
		final String  sql = "select a.id as computerorderdetailid, a.computerorderid as computerorderdetailcomputerorderid, a.computerid as computerorderdetailcomputerid, a.computernumber as computerorderdetailcomputernumber, a.createtime as computerorderdetailcreatetime, a.status as computerorderdetailstatus, b.id as computerorderid, b.userid as computerorderuserid, b.createtime as computerordercreatetime, b.status as computerorderstatus, b.starttime as computerorderstarttime, b.endtime as computerorderendtime, c.id as computerid, c.serialnumber as computerserialnumber, c.name as computername, c.computercategoryid as computercomputercategoryid, c.createtime as computercreatetime, c.createuserid as computercreateuserid, c.status as computerstatus from Computerorderdetail a  left join Computerorder b on a.computerorderid=b.id left join Computerorder c on a.computerorderid=c.id " + "where a.id = "+computerorderdetailId;
		
		List<ComputerorderdetailFull> computerorderdetailList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderdetailFull.class));
						return query.list();
					}
				});
		if (computerorderdetailList != null && !computerorderdetailList.isEmpty()) {
			return computerorderdetailList.get(0);
		}
		return null;
	}

/*
	@Override
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByUserId(Integer userId) {
		final String  sql = "select a.id as computerorderdetailid, a.computerorderid as computerorderdetailcomputerorderid, a.computerid as computerorderdetailcomputerid, a.computernumber as computerorderdetailcomputernumber, a.createtime as computerorderdetailcreatetime, a.status as computerorderdetailstatus, b.id as computerorderid, b.userid as computerorderuserid, b.createtime as computerordercreatetime, b.status as computerorderstatus, b.starttime as computerorderstarttime, b.endtime as computerorderendtime, c.id as computerid, c.serialnumber as computerserialnumber, c.name as computername, c.computercategoryid as computercomputercategoryid, c.createtime as computercreatetime, c.createuserid as computercreateuserid, c.status as computerstatus from Computerorderdetail a  left join Computerorder b on a.computerorderid=b.id left join Computerorder c on a.computerorderid=c.id " + "where a.userid = "+userId;
		
		List<ComputerorderdetailFull> computerorderdetailList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderdetailFull.class));
						return query.list();
					}
				});
		if (computerorderdetailList != null && !computerorderdetailList.isEmpty()) {
			return computerorderdetailList;
		}
		return null;
	}*/
	
//	查询全部实体full
	@Override
	public List<ComputerorderdetailFull> selectComputerorderdetailFullAll() {
	final String  sql = "select a.id as computerorderdetailid, a.computerorderid as computerorderdetailcomputerorderid, a.computerid as computerorderdetailcomputerid, a.computernumber as computerorderdetailcomputernumber, a.createtime as computerorderdetailcreatetime, a.status as computerorderdetailstatus, b.id as computerorderid, b.userid as computerorderuserid, b.createtime as computerordercreatetime, b.status as computerorderstatus, b.starttime as computerorderstarttime, b.endtime as computerorderendtime, c.id as computerid, c.serialnumber as computerserialnumber, c.name as computername, c.computercategoryid as computercomputercategoryid, c.createtime as computercreatetime, c.createuserid as computercreateuserid, c.status as computerstatus from Computerorderdetail a  left join Computerorder b on a.computerorderid=b.id left join Computerorder c on a.computerorderid=c.id ";
		
		List<ComputerorderdetailFull> computerorderdetailList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderdetailFull.class));
						return query.list();
					}
				});
		if (computerorderdetailList != null && !computerorderdetailList.isEmpty()) {			
			return computerorderdetailList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByPage(Page page){
		
			return null;
	}
	
//  根据关联查询实体full

	//根据关联查询实体 
	public List<Computerorderdetail> selectComputerorderdetailByComputerorderId(Integer computerorderid ){
	
		return null;
	}
	public List<Computerorderdetail> selectComputerorderdetailByComputerId(Integer computerid ){
	
		return null;
	}
  

	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputerorderId(Integer computerorderid ){
	
		return null;
	}
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputerId(Integer computerid ){
	
		return null;
	}

 
}