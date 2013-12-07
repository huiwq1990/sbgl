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

import com.sbgl.app.dao.ComputerstatusDao;
import com.sbgl.app.entity.Computerstatus;
import com.sbgl.app.entity.ComputerstatusFull;
import com.sbgl.util.*;

@Repository("computerstatusDao")
public class ComputerstatusDaoImpl extends HibernateDaoSupport implements ComputerstatusDao{

	private static final Log log = LogFactory.getLog(ComputerstatusDaoImpl.class);
	private final String basicComputerstatusFullSql = "select a.id as computerstatusid, a.name as computerstatusname from Computerstatus a  ";
	
	private final String basicComputerstatusSql = "From Computerstatus  ";
	
	// 根据条件查询查询实体
	@Override
	public List<Computerstatus> selectComputerstatusByCondition(String condition) {
		final String  sql = basicComputerstatusSql +" " + condition;
		
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
        public List<Computerstatus>  selectComputerstatusByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerstatusSql  +conditionSql;
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
	public List<ComputerstatusFull> selectComputerstatusFullByCondition(String condition) {
		final String  sql = basicComputerstatusFullSql +" " + condition;
		
		List<ComputerstatusFull> computerstatusList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerstatusFull.class));
						return query.list();
					}
				});		
		return computerstatusList;
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerstatusFull>  selectComputerstatusFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerstatusFullSql  +conditionSql;
                List<ComputerstatusFull> computerstatusList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                ComputerstatusFull.class));
                                                return query.list();
                                        }
                                });
                if (computerstatusList != null && !computerstatusList.isEmpty()) {
                        return computerstatusList;
                }
                return computerstatusList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer computerstatusId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Computerstatus as tb set tb.status = 0 where tb.id = " +computerstatusId; 
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
	public ComputerstatusFull selectComputerstatusFullById(Integer computerstatusId) {
		final String  sql =  basicComputerstatusFullSql + " where a.id = "+computerstatusId;
		
		List<ComputerstatusFull> computerstatusList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerstatusFull.class));
						return query.list();
					}
				});
		if (computerstatusList != null && !computerstatusList.isEmpty()) {
			return computerstatusList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<ComputerstatusFull> selectComputerstatusFullAll() {
		final String  sql = basicComputerstatusFullSql;
		
		List<ComputerstatusFull> computerstatusFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerstatusFull.class));
						return query.list();
					}
				});
		if (computerstatusFullList != null && !computerstatusFullList.isEmpty()) {			
			return computerstatusFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputerstatusFull> selectComputerstatusFullByPage(final Page page){
		final String  sql = basicComputerstatusFullSql;
		
		List<ComputerstatusFull> computerstatusFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								ComputerstatusFull.class));
						return query.list();
					}
				});
		if (computerstatusFullList != null && !computerstatusFullList.isEmpty()) {			
			return computerstatusFullList;
		}
		return null;
	}
	
//  根据关联查询实体full
 
}