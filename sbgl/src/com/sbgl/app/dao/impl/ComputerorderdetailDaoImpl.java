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
		final String  sql = "select a.id as computerorderdetailid, a.computerorderid as computerorderdetailcomputerorderid, a.computerid as computerorderdetailcomputerid, a.computernumber as computerorderdetailcomputernumber, a.createtime as computerorderdetailcreatetime, a.borrowday as computerorderdetailborrowday, a.borrowperiod as computerorderdetailborrowperiod, a.status as computerorderdetailstatus from Computerorderdetail a  " + "where a.id = "+computerorderdetailId;
		
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
	
//	查询全部实体full
	@Override
	public List<ComputerorderdetailFull> selectComputerorderdetailFullAll() {
		final String  sql = "select a.id as computerorderdetailid, a.computerorderid as computerorderdetailcomputerorderid, a.computerid as computerorderdetailcomputerid, a.computernumber as computerorderdetailcomputernumber, a.createtime as computerorderdetailcreatetime, a.borrowday as computerorderdetailborrowday, a.borrowperiod as computerorderdetailborrowperiod, a.status as computerorderdetailstatus from Computerorderdetail a  ";
		
		List<ComputerorderdetailFull> computerorderdetailFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderdetailFull.class));
						return query.list();
					}
				});
		if (computerorderdetailFullList != null && !computerorderdetailFullList.isEmpty()) {			
			return computerorderdetailFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByPage(final Page page){
		final String  sql = "select a.id as computerorderdetailid, a.computerorderid as computerorderdetailcomputerorderid, a.computerid as computerorderdetailcomputerid, a.computernumber as computerorderdetailcomputernumber, a.createtime as computerorderdetailcreatetime, a.borrowday as computerorderdetailborrowday, a.borrowperiod as computerorderdetailborrowperiod, a.status as computerorderdetailstatus from Computerorderdetail a  ";
		
		List<ComputerorderdetailFull> computerorderdetailFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderdetailFull.class));
						return query.list();
					}
				});
		if (computerorderdetailFullList != null && !computerorderdetailFullList.isEmpty()) {			
			return computerorderdetailFullList;
		}
		return null;
	}
	
//  根据关联查询实体full
 
}