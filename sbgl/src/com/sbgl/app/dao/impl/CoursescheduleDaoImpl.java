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

import com.sbgl.app.dao.CoursescheduleDao;
import com.sbgl.app.entity.Courseschedule;
import com.sbgl.app.entity.CoursescheduleFull;
import com.sbgl.util.*;

@Repository("coursescheduleDao")
public class CoursescheduleDaoImpl extends HibernateDaoSupport implements CoursescheduleDao{

	private static final Log log = LogFactory.getLog(CoursescheduleDaoImpl.class);
	private final String basicCoursescheduleFullSql = "select a.id as coursescheduleid from Courseschedule a  ";
	
	private final String basicCoursescheduleSql = "From Courseschedule as a ";
	
	// 根据条件查询查询实体
	@Override
	public List<Courseschedule> selectCoursescheduleByCondition(String condition) {
		final String  sql = basicCoursescheduleSql +" " + condition;
		
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
        public List<Courseschedule>  selectCoursescheduleByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicCoursescheduleSql  +conditionSql;
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
	public List<CoursescheduleFull> selectCoursescheduleFullByCondition(String condition) {
		final String  sql = basicCoursescheduleFullSql +" " + condition;
		
		List<CoursescheduleFull> coursescheduleList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CoursescheduleFull.class));
						return query.list();
					}
				});		
		return coursescheduleList;
	}
	
	
	// 查询实体full        
        @Override
        public List<CoursescheduleFull>  selectCoursescheduleFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicCoursescheduleFullSql  +conditionSql;
                List<CoursescheduleFull> coursescheduleList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                CoursescheduleFull.class));
                                                return query.list();
                                        }
                                });
                if (coursescheduleList != null && !coursescheduleList.isEmpty()) {
                        return coursescheduleList;
                }
                return coursescheduleList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer coursescheduleId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Courseschedule as tb set tb.status = 0 where tb.id = " +coursescheduleId; 
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
	public CoursescheduleFull selectCoursescheduleFullById(Integer coursescheduleId) {
		final String  sql =  basicCoursescheduleFullSql + " where a.id = "+coursescheduleId;
		
		List<CoursescheduleFull> coursescheduleList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CoursescheduleFull.class));
						return query.list();
					}
				});
		if (coursescheduleList != null && !coursescheduleList.isEmpty()) {
			return coursescheduleList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<CoursescheduleFull> selectCoursescheduleFullAll() {
		final String  sql = basicCoursescheduleFullSql;
		
		List<CoursescheduleFull> coursescheduleFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CoursescheduleFull.class));
						return query.list();
					}
				});
		if (coursescheduleFullList != null && !coursescheduleFullList.isEmpty()) {			
			return coursescheduleFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<CoursescheduleFull> selectCoursescheduleFullByPage(final Page page){
		final String  sql = basicCoursescheduleFullSql;
		
		List<CoursescheduleFull> coursescheduleFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								CoursescheduleFull.class));
						return query.list();
					}
				});
		if (coursescheduleFullList != null && !coursescheduleFullList.isEmpty()) {			
			return coursescheduleFullList;
		}
		return null;
	}
	
//  根据关联查询实体full
 
}