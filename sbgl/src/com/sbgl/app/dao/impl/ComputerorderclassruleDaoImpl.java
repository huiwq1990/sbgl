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

import com.sbgl.app.dao.ComputerorderclassruleDao;
import com.sbgl.app.entity.Computerorderclassrule;
import com.sbgl.app.entity.ComputerorderclassruleFull;
import com.sbgl.util.*;

@Repository("computerorderclassruleDao")
public class ComputerorderclassruleDaoImpl extends HibernateDaoSupport implements ComputerorderclassruleDao{

	private static final Log log = LogFactory.getLog(ComputerorderclassruleDaoImpl.class);
	private final String basicComputerorderclassruleFullSql = "select a.id as computerorderclassruleid, a.name as computerorderclassrulename, a.classname as computerorderclassruleclassname, a.classid as computerorderclassruleclassid, a.orderstarttime as computerorderclassruleorderstarttime, a.orderendtime as computerorderclassruleorderendtime, a.availableordertime as computerorderclassruleavailableordertime, a.createuserid as computerorderclassrulecreateuserid, a.createtime as computerorderclassrulecreatetime, a.status as computerorderclassrulestatus from Computerorderclassrule a  ";
	
	private final String basicComputerorderclassruleSql = "From Computerorderclassrule  ";
	
	// 根据条件查询查询实体
	@Override
	public List<Computerorderclassrule> selectComputerorderclassruleByCondition(String condition) {
		final String  sql = basicComputerorderclassruleSql +" " + condition;
		
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
        public List<Computerorderclassrule>  selectComputerorderclassruleByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerorderclassruleSql  +conditionSql;
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
	public List<ComputerorderclassruleFull> selectComputerorderclassruleFullByCondition(String condition) {
		final String  sql = basicComputerorderclassruleFullSql +" " + condition;
		
		List<ComputerorderclassruleFull> computerorderclassruleList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderclassruleFull.class));
						return query.list();
					}
				});		
		return null;
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerorderclassruleFull>  selectComputerorderclassruleFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerorderclassruleFullSql  +conditionSql;
                List<ComputerorderclassruleFull> computerorderclassruleList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                ComputerorderclassruleFull.class));
                                                return query.list();
                                        }
                                });
                if (computerorderclassruleList != null && !computerorderclassruleList.isEmpty()) {
                        return computerorderclassruleList;
                }
                return null;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer computerorderclassruleId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Computerorderclassrule as tb set tb.status = 0 where tb.id = " +computerorderclassruleId; 
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
	public ComputerorderclassruleFull selectComputerorderclassruleFullById(Integer computerorderclassruleId) {
		final String  sql =  basicComputerorderclassruleFullSql + " where a.id = "+computerorderclassruleId;
		
		List<ComputerorderclassruleFull> computerorderclassruleList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderclassruleFull.class));
						return query.list();
					}
				});
		if (computerorderclassruleList != null && !computerorderclassruleList.isEmpty()) {
			return computerorderclassruleList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<ComputerorderclassruleFull> selectComputerorderclassruleFullAll() {
		final String  sql = basicComputerorderclassruleFullSql;
		
		List<ComputerorderclassruleFull> computerorderclassruleFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderclassruleFull.class));
						return query.list();
					}
				});
		if (computerorderclassruleFullList != null && !computerorderclassruleFullList.isEmpty()) {			
			return computerorderclassruleFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputerorderclassruleFull> selectComputerorderclassruleFullByPage(final Page page){
		final String  sql = basicComputerorderclassruleFullSql;
		
		List<ComputerorderclassruleFull> computerorderclassruleFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderclassruleFull.class));
						return query.list();
					}
				});
		if (computerorderclassruleFullList != null && !computerorderclassruleFullList.isEmpty()) {			
			return computerorderclassruleFullList;
		}
		return null;
	}
	
//  根据关联查询实体full
 
}