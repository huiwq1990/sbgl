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

import com.sbgl.app.dao.ComputerorderconfigDao;
import com.sbgl.app.entity.Computerorderconfig;
import com.sbgl.app.entity.ComputerorderconfigFull;
import com.sbgl.util.*;

@Repository("computerorderconfigDao")
public class ComputerorderconfigDaoImpl extends HibernateDaoSupport implements ComputerorderconfigDao{

	private static final Log log = LogFactory.getLog(ComputerorderconfigDaoImpl.class);
	private final String basicComputerorderconfigFullSql = "select a.id as computerorderconfigid, a.openorder as computerorderconfigopenorder, a.orderperiod as computerorderconfigorderperiod, a.orderperiodnum as computerorderconfigorderperiodnum, a.maxorderday as computerorderconfigmaxorderday, a.orderintroduction as computerorderconfigorderintroduction, a.createuserid as computerorderconfigcreateuserid, a.createtime as computerorderconfigcreatetime, a.currentconfig as computerorderconfigcurrentconfig, a.status as computerorderconfigstatus, b.id as createuserid, b.userId as createuseruserid, b.name as createusername, b.roletype as createuserroletype, b.privilege as createuserprivilege, b.password as createuserpassword from Computerorderconfig a  left join Loginuser b on a.createuserid=b.id ";
	
	private final String basicComputerorderconfigSql = "From Computerorderconfig as a ";
	

	
	@Override
	public Computerorderconfig selectCurrentComputerorderconfig() {
		
		final String  sql = basicComputerorderconfigSql +" " + " where currentconfig = 1";
		
		try {
             List<Computerorderconfig> l = this.getHibernateTemplate().find(sql);
             if(l == null || l.size()==0){
            	 return null;
             }else{
            	 return l.get(0);
             }
			 
        } catch (RuntimeException re) {
            log.error("失败", re);
            throw re;
        }
	}
	
	
	// 根据条件查询查询实体
	@Override
	public List<Computerorderconfig> selectComputerorderconfigByCondition(String condition) {
		final String  sql = basicComputerorderconfigSql +" " + condition;
		
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
        public List<Computerorderconfig>  selectComputerorderconfigByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerorderconfigSql  +conditionSql;
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
	public List<ComputerorderconfigFull> selectComputerorderconfigFullByCondition(String condition) {
		final String  sql = basicComputerorderconfigFullSql +" " + condition;
		
		List<ComputerorderconfigFull> computerorderconfigList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderconfigFull.class));
						return query.list();
					}
				});		
		return computerorderconfigList;
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerorderconfigFull>  selectComputerorderconfigFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerorderconfigFullSql  +conditionSql;
                List<ComputerorderconfigFull> computerorderconfigList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                ComputerorderconfigFull.class));
                                                return query.list();
                                        }
                                });
                if (computerorderconfigList != null && !computerorderconfigList.isEmpty()) {
                        return computerorderconfigList;
                }
                return computerorderconfigList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer computerorderconfigId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Computerorderconfig as tb set tb.status = 0 where tb.id = " +computerorderconfigId; 
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
	public ComputerorderconfigFull selectComputerorderconfigFullById(Integer computerorderconfigId) {
		final String  sql =  basicComputerorderconfigFullSql + " where a.id = "+computerorderconfigId;
		
		List<ComputerorderconfigFull> computerorderconfigList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderconfigFull.class));
						return query.list();
					}
				});
		if (computerorderconfigList != null && !computerorderconfigList.isEmpty()) {
			return computerorderconfigList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<ComputerorderconfigFull> selectComputerorderconfigFullAll() {
		final String  sql = basicComputerorderconfigFullSql;
		
		List<ComputerorderconfigFull> computerorderconfigFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderconfigFull.class));
						return query.list();
					}
				});
		if (computerorderconfigFullList != null && !computerorderconfigFullList.isEmpty()) {			
			return computerorderconfigFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputerorderconfigFull> selectComputerorderconfigFullByPage(final Page page){
		final String  sql = basicComputerorderconfigFullSql;
		
		List<ComputerorderconfigFull> computerorderconfigFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderconfigFull.class));
						return query.list();
					}
				});
		if (computerorderconfigFullList != null && !computerorderconfigFullList.isEmpty()) {			
			return computerorderconfigFullList;
		}
		return null;
	}
	
//  根据关联查询实体full

	//根据关联查询实体 
	public List<Computerorderconfig> selectComputerorderconfigByLoginuserId(Integer createuserid){
	
		return null;
	}
  

	public List<ComputerorderconfigFull> selectComputerorderconfigFullByLoginuserId(Integer createuserid){
	
		return null;
	}

 
}