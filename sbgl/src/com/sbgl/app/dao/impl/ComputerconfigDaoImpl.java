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

import com.sbgl.app.dao.ComputerconfigDao;
import com.sbgl.app.entity.Computerconfig;
import com.sbgl.app.entity.ComputerconfigFull;
import com.sbgl.util.*;

@Repository("computerconfigDao")
public class ComputerconfigDaoImpl extends HibernateDaoSupport implements ComputerconfigDao{

	private static final Log log = LogFactory.getLog(ComputerconfigDaoImpl.class);
	private final String basicComputerconfigFullSql = "select a.id as computerconfigid, a.name as computerconfigname, a.value as computerconfigvalue from Computerconfig a  ";
	
	private final String basicComputerconfigSql = "From Computerconfig as a ";
	
	// 根据条件查询查询实体
	@Override
	public List<Computerconfig> selectComputerconfigByCondition(String condition) {
		final String  sql = basicComputerconfigSql +" " + condition;
		
		try {
             List l = this.getHibernateTemplate().find(sql);
			 return l;
        } catch (RuntimeException re) {
            log.error("失败", re);
            throw re;
        }
	}
	
	
	//  根据条件分页查询实体        
        @Override
        public List<Computerconfig>  selectComputerconfigByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerconfigSql  +conditionSql;
              try {
	        
	         Query q = this.getSession().createQuery(sql).setFirstResult(page.getPageNo()).setMaxResults(page.getPageSize());
	         
	         List l = q.list(); 
			 return l;
	      } catch (RuntimeException re) {
	         log.error("查询失败", re);
	         throw re;
	      }
        }
	
	
	//条件查询full
	@Override
	public List<ComputerconfigFull> selectComputerconfigFullByCondition(String condition) {
		final String  sql = basicComputerconfigFullSql +" " + condition;
		
		List<ComputerconfigFull> computerconfigList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerconfigFull.class));
						return query.list();
					}
				});		
		return computerconfigList;
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerconfigFull>  selectComputerconfigFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerconfigFullSql  +conditionSql;
                List<ComputerconfigFull> computerconfigList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                ComputerconfigFull.class));
                                                return query.list();
                                        }
                                });
                if (computerconfigList != null && !computerconfigList.isEmpty()) {
                        return computerconfigList;
                }
                return computerconfigList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer computerconfigId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Computerconfig as tb set tb.status = 0 where tb.id = " +computerconfigId; 
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
	public ComputerconfigFull selectComputerconfigFullById(Integer computerconfigId) {
		final String  sql =  basicComputerconfigFullSql + " where a.id = "+computerconfigId;
		
		List<ComputerconfigFull> computerconfigList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerconfigFull.class));
						return query.list();
					}
				});
		if (computerconfigList != null && !computerconfigList.isEmpty()) {
			return computerconfigList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<ComputerconfigFull> selectComputerconfigFullAll() {
		final String  sql = basicComputerconfigFullSql;
		
		List<ComputerconfigFull> computerconfigFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerconfigFull.class));
						return query.list();
					}
				});
		if (computerconfigFullList != null && !computerconfigFullList.isEmpty()) {			
			return computerconfigFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputerconfigFull> selectComputerconfigFullByPage(final Page page){
		final String  sql = basicComputerconfigFullSql;
		
		List<ComputerconfigFull> computerconfigFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								ComputerconfigFull.class));
						return query.list();
					}
				});
		if (computerconfigFullList != null && !computerconfigFullList.isEmpty()) {			
			return computerconfigFullList;
		}
		return null;
	}
	
//  根据关联查询实体full
 
}