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

import com.sbgl.app.dao.ComputerorderclassruledetailDao;
import com.sbgl.app.entity.Computerorderclassruledetail;
import com.sbgl.app.entity.ComputerorderclassruledetailFull;
import com.sbgl.util.*;

@Repository("computerorderclassruledetailDao")
public class ComputerorderclassruledetailDaoImpl extends HibernateDaoSupport implements ComputerorderclassruledetailDao{

	private static final Log log = LogFactory.getLog(ComputerorderclassruledetailDaoImpl.class);
	private final String basicComputerorderclassruledetailFullSql = "select a.id as computerorderclassruledetailid, a.computerorderclassruleid as computerorderclassruledetailcomputerorderclassruleid, a.allowedcomputermodelid as computerorderclassruledetailallowedcomputermodelid, b.id as computermodelid, b.computermodeltype as computermodelcomputermodeltype, b.languagetype as computermodellanguagetype, b.name as computermodelname, b.computercategoryid as computermodelcomputercategoryid, b.picpath as computermodelpicpath, b.createtime as computermodelcreatetime, b.createuserid as computermodelcreateuserid, b.computercount as computermodelcomputercount, b.availableborrowcountnumber as computermodelavailableborrowcountnumber, b.description as computermodeldescription, b.status as computermodelstatus " +
			"from Computerorderclassruledetail a  " +
			"left join Computermodel b on a.allowedcomputermodelid=b.computermodeltype ";
	
	private final String basicComputerorderclassruledetailSql = "From Computerorderclassruledetail as a  ";
	
	
	@Override
	public List<ComputerorderclassruledetailFull> selByComputerorderclassruleId(int ruleid,int language) {
		String borrowPcSql  = " where a.computerorderclassruleid = "+ ruleid+ " and b.languagetype = "+language;
		return selectComputerorderclassruledetailFullByCondition(borrowPcSql);				
	}
	
	
	
	
	// 根据条件查询查询实体
	@Override
	public List<Computerorderclassruledetail> selectComputerorderclassruledetailByCondition(String condition) {
		final String  sql = basicComputerorderclassruledetailSql +" " + condition;
		
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
        public List<Computerorderclassruledetail>  selectComputerorderclassruledetailByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerorderclassruledetailSql  +conditionSql;
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
	public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullByCondition(String condition) {
		final String  sql = basicComputerorderclassruledetailFullSql +" " + condition;
		
		List<ComputerorderclassruledetailFull> computerorderclassruledetailList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderclassruledetailFull.class));
						return query.list();
					}
				});		
		return computerorderclassruledetailList;
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerorderclassruledetailFull>  selectComputerorderclassruledetailFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerorderclassruledetailFullSql  +conditionSql;
                List<ComputerorderclassruledetailFull> computerorderclassruledetailList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                ComputerorderclassruledetailFull.class));
                                                return query.list();
                                        }
                                });
                if (computerorderclassruledetailList != null && !computerorderclassruledetailList.isEmpty()) {
                        return computerorderclassruledetailList;
                }
                return computerorderclassruledetailList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer computerorderclassruledetailId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Computerorderclassruledetail as tb set tb.status = 0 where tb.id = " +computerorderclassruledetailId; 
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
	public ComputerorderclassruledetailFull selectComputerorderclassruledetailFullById(Integer computerorderclassruledetailId) {
		final String  sql =  basicComputerorderclassruledetailFullSql + " where a.id = "+computerorderclassruledetailId;
		
		List<ComputerorderclassruledetailFull> computerorderclassruledetailList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderclassruledetailFull.class));
						return query.list();
					}
				});
		if (computerorderclassruledetailList != null && !computerorderclassruledetailList.isEmpty()) {
			return computerorderclassruledetailList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullAll() {
		final String  sql = basicComputerorderclassruledetailFullSql;
		
		List<ComputerorderclassruledetailFull> computerorderclassruledetailFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderclassruledetailFull.class));
						return query.list();
					}
				});
		if (computerorderclassruledetailFullList != null && !computerorderclassruledetailFullList.isEmpty()) {			
			return computerorderclassruledetailFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullByPage(final Page page){
		final String  sql = basicComputerorderclassruledetailFullSql;
		
		List<ComputerorderclassruledetailFull> computerorderclassruledetailFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderclassruledetailFull.class));
						return query.list();
					}
				});
		if (computerorderclassruledetailFullList != null && !computerorderclassruledetailFullList.isEmpty()) {			
			return computerorderclassruledetailFullList;
		}
		return null;
	}
	
//  根据关联查询实体full

	//根据关联查询实体 
	public List<Computerorderclassruledetail> selectComputerorderclassruledetailByComputermodelId(Integer allowedcomputermodelid ){
	
		return null;
	}
  

	public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullByComputermodelId(Integer allowedcomputermodelid ){
	
		return null;
	}

 
}