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

import com.sbgl.app.dao.ComputerorderDao;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.util.*;

@Repository("computerorderDao")
public class ComputerorderDaoImpl extends HibernateDaoSupport implements ComputerorderDao{

	private static final Log log = LogFactory.getLog(ComputerorderDaoImpl.class);
	private final String basicComputerorderFullSql = "select a.id as computerorderid, a.serialnumber as computerorderserialnumber, a.userid as computerorderuserid, a.createtime as computerordercreatetime, a.status as computerorderstatus from Computerorder a  ";
	
	private final String basicComputerorderSql = "From Computerorder  ";
	
	// 根据条件查询查询实体
	@Override
	public List<Computerorder> selectComputerorderByCondition(String condition) {
		final String  sql = basicComputerorderSql +" " + condition;
		
		List<Computerorder> computerorderList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								Computerorder.class));
						return query.list();
					}
				});		
		return null;
	}
	
	
	//  根据条件分页查询实体        
        @Override
        public List<Computerorder>  selectComputerorderByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerorderSql  +conditionSql;
                List<Computerorder> computerorderList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                Computerorder.class));
                                                return query.list();
                                        }
                                });
                if (computerorderList != null && !computerorderList.isEmpty()) {
                        return computerorderList;
                }
                return null;
        }
	
	
	//条件查询full
	@Override
	public List<ComputerorderFull> selectComputerorderFullByCondition(String condition) {
		final String  sql = basicComputerorderFullSql +" " + condition;
		
		List<ComputerorderFull> computerorderList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderFull.class));
						return query.list();
					}
				});		
		return null;
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerorderFull>  selectComputerorderFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerorderFullSql  +conditionSql;
                List<ComputerorderFull> computerorderList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                ComputerorderFull.class));
                                                return query.list();
                                        }
                                });
                if (computerorderList != null && !computerorderList.isEmpty()) {
                        return computerorderList;
                }
                return null;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer computerorderId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Computerorder as tb set tb.status = 0 where tb.id = " +computerorderId; 
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
	public ComputerorderFull selectComputerorderFullById(Integer computerorderId) {
		final String  sql =  basicComputerorderFullSql + " where a.id = "+computerorderId;
		
		List<ComputerorderFull> computerorderList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderFull.class));
						return query.list();
					}
				});
		if (computerorderList != null && !computerorderList.isEmpty()) {
			return computerorderList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<ComputerorderFull> selectComputerorderFullAll() {
		final String  sql = basicComputerorderFullSql;
		
		List<ComputerorderFull> computerorderFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderFull.class));
						return query.list();
					}
				});
		if (computerorderFullList != null && !computerorderFullList.isEmpty()) {			
			return computerorderFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputerorderFull> selectComputerorderFullByPage(final Page page){
		final String  sql = basicComputerorderFullSql;
		
		List<ComputerorderFull> computerorderFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderFull.class));
						return query.list();
					}
				});
		if (computerorderFullList != null && !computerorderFullList.isEmpty()) {			
			return computerorderFullList;
		}
		return null;
	}
	
//  根据关联查询实体full
 
}