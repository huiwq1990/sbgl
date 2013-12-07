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
import com.sbgl.app.entity.Computercategoryi18n;
import com.sbgl.util.*;

@Repository("computercategoryDao")
public class ComputercategoryDaoImpl extends HibernateDaoSupport implements ComputercategoryDao{

	private static final Log log = LogFactory.getLog(ComputercategoryDaoImpl.class);
	private final String basicComputercategoryFullSql = "select a.id as computercategoryid, a.computercategorytype as computercategorycomputercategorytype, a.languagetype as computercategorylanguagetype, a.parentcomputercategoryid as computercategoryparentcomputercategoryid, a.name as computercategoryname, a.createtime as computercategorycreatetime, a.createuserid as computercategorycreateuserid, a.status as computercategorystatus, b.id as parentcomputercategoryid, b.computercategorytype as parentcomputercategorycomputercategorytype, b.languagetype as parentcomputercategorylanguagetype, b.parentcomputercategoryid as parentcomputercategoryparentcomputercategoryid, b.name as parentcomputercategoryname, b.createtime as parentcomputercategorycreatetime, b.createuserid as parentcomputercategorycreateuserid, b.status as parentcomputercategorystatus from Computercategory a  left join Computercategory b on a.parentcomputercategoryid=b.id ";
	private final String i18nsql = "select ch.id as idch, ch.computercategorytype as computercategorytypech, ch.languagetype as languagetypech, ch.parentcomputercategoryid as parentcomputercategoryidch, ch.name as namech, ch.createtime as createtimech, ch.createuserid as createuseridch, ch.status as statusch, en.id as iden, en.computercategorytype as computercategorytypeen, en.languagetype as languagetypeen, en.parentcomputercategoryid as parentcomputercategoryiden, en.name as nameen, en.createtime as createtimeen, en.createuserid as createuseriden, en.status as statusen from Computercategory ch left join Computercategory en on ch.Computercategorytype = en.Computercategorytype  where ch.languagetype=0 and en.languagetype=1";
	private final String basicComputercategorySql = "From Computercategory  ";
	
	
//	@Override
//	public static void deleteComputercategoryByCondition(String conditionSql) {
//		
//	}
	
	@Override
	public List<Computercategoryi18n> selectComputercategoryi18nByCondition(String conditionSql) {
        final String  sql = i18nsql  +conditionSql;
        List<Computercategoryi18n> computercategoryList = getHibernateTemplate()
                        .executeFind(new HibernateCallback() {
                                public Object doInHibernate(Session session)
                                                throws HibernateException {
                                                
                                                //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                        Query query = session.createSQLQuery(sql);
                                       
                                        query.setResultTransformer(new EscColumnToBean(
                                        		Computercategoryi18n.class));
                                        return query.list();
                                }
                        });
        if (computercategoryList != null && !computercategoryList.isEmpty()) {
                return computercategoryList;
        }
        return null;
	}
	
	@Override
	public List<Computercategoryi18n> selectComputercategoryi18nByConditionAndPage(String conditionSql,final Page page) {
        final String  sql = i18nsql  +conditionSql;
        List<Computercategoryi18n> computercategoryList = getHibernateTemplate()
                        .executeFind(new HibernateCallback() {
                                public Object doInHibernate(Session session)
                                                throws HibernateException {
                                                
                                                //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                        Query query = session.createSQLQuery(sql);
                                        query.setFirstResult(page.getStartNum());
                                        query.setMaxResults(page.getPageSize());
                                        query.setResultTransformer(new EscColumnToBean(
                                        		Computercategoryi18n.class));
                                        return query.list();
                                }
                        });
        if (computercategoryList != null && !computercategoryList.isEmpty()) {
                return computercategoryList;
        }
        return null;
	}
	
	
	// 根据条件查询查询实体
	@Override
	public List<Computercategory> selectComputercategoryByCondition(String condition) {
		final String  sql = basicComputercategorySql +" " + condition;
		
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
        public List<Computercategory>  selectComputercategoryByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputercategorySql  +conditionSql;
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
	public List<ComputercategoryFull> selectComputercategoryFullByCondition(String condition) {
		final String  sql = basicComputercategoryFullSql +" " + condition;
		
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
		return computercategoryList;
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputercategoryFull>  selectComputercategoryFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputercategoryFullSql  +conditionSql;
                List<ComputercategoryFull> computercategoryList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                ComputercategoryFull.class));
                                                return query.list();
                                        }
                                });
                if (computercategoryList != null && !computercategoryList.isEmpty()) {
                        return computercategoryList;
                }
                return null;
        }
	
	
	
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
		final String  sql =  basicComputercategoryFullSql + " where a.id = "+computercategoryId;
		
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
	
//	查询全部实体full
	@Override
	public List<ComputercategoryFull> selectComputercategoryFullAll() {
		final String  sql = basicComputercategoryFullSql;
		
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
	public List<ComputercategoryFull> selectComputercategoryFullByPage(final Page page){
		final String  sql = basicComputercategoryFullSql;
		
		List<ComputercategoryFull> computercategoryFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
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
  

	public List<ComputercategoryFull> selectComputercategoryFullByComputercategoryId(Integer parentcomputercategoryid ){
	
		return null;
	}

 
}