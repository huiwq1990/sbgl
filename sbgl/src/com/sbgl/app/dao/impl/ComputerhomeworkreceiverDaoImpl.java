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

import com.sbgl.app.dao.ComputerhomeworkreceiverDao;
import com.sbgl.app.entity.Computerhomeworkreceiver;
import com.sbgl.app.entity.ComputerhomeworkreceiverFull;
import com.sbgl.util.*;

@Repository("computerhomeworkreceiverDao")
public class ComputerhomeworkreceiverDaoImpl extends HibernateDaoSupport implements ComputerhomeworkreceiverDao{

	private static final Log log = LogFactory.getLog(ComputerhomeworkreceiverDaoImpl.class);
	private final String basicComputerhomeworkreceiverFullSql = "select a.id as computerhomeworkreceiverid, a.computerhomeworkid as computerhomeworkreceivercomputerhomeworkid, a.userid as computerhomeworkreceiveruserid, " +
			"b.id as loginuserid, b.name as loginusername, b.createtime as loginusercreatetime from Computerhomeworkreceiver a  left join Loginuser b on a.userid=b.id ";
	
	private final String basicComputerhomeworkreceiverSql = "From Computerhomeworkreceiver as a ";
	
	// 根据条件查询查询实体
	@Override
	public List<Computerhomeworkreceiver> selectComputerhomeworkreceiverByCondition(String condition) {
		final String  sql = basicComputerhomeworkreceiverSql +" " + condition;
		
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
        public List<Computerhomeworkreceiver>  selectComputerhomeworkreceiverByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerhomeworkreceiverSql  +conditionSql;
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
	public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullByCondition(String condition) {
		final String  sql = basicComputerhomeworkreceiverFullSql +" " + condition;
		
		List<ComputerhomeworkreceiverFull> computerhomeworkreceiverList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerhomeworkreceiverFull.class));
						return query.list();
					}
				});		
		return computerhomeworkreceiverList;
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerhomeworkreceiverFull>  selectComputerhomeworkreceiverFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerhomeworkreceiverFullSql  +conditionSql;
                List<ComputerhomeworkreceiverFull> computerhomeworkreceiverList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                ComputerhomeworkreceiverFull.class));
                                                return query.list();
                                        }
                                });
                if (computerhomeworkreceiverList != null && !computerhomeworkreceiverList.isEmpty()) {
                        return computerhomeworkreceiverList;
                }
                return computerhomeworkreceiverList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer computerhomeworkreceiverId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Computerhomeworkreceiver as tb set tb.status = 0 where tb.id = " +computerhomeworkreceiverId; 
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
	public ComputerhomeworkreceiverFull selectComputerhomeworkreceiverFullById(Integer computerhomeworkreceiverId) {
		final String  sql =  basicComputerhomeworkreceiverFullSql + " where a.id = "+computerhomeworkreceiverId;
		
		List<ComputerhomeworkreceiverFull> computerhomeworkreceiverList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerhomeworkreceiverFull.class));
						return query.list();
					}
				});
		if (computerhomeworkreceiverList != null && !computerhomeworkreceiverList.isEmpty()) {
			return computerhomeworkreceiverList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullAll() {
		final String  sql = basicComputerhomeworkreceiverFullSql;
		
		List<ComputerhomeworkreceiverFull> computerhomeworkreceiverFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerhomeworkreceiverFull.class));
						return query.list();
					}
				});
		if (computerhomeworkreceiverFullList != null && !computerhomeworkreceiverFullList.isEmpty()) {			
			return computerhomeworkreceiverFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullByPage(final Page page){
		final String  sql = basicComputerhomeworkreceiverFullSql;
		
		List<ComputerhomeworkreceiverFull> computerhomeworkreceiverFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								ComputerhomeworkreceiverFull.class));
						return query.list();
					}
				});
		if (computerhomeworkreceiverFullList != null && !computerhomeworkreceiverFullList.isEmpty()) {			
			return computerhomeworkreceiverFullList;
		}
		return null;
	}
	
//  根据关联查询实体full

	//根据关联查询实体 
	public List<Computerhomeworkreceiver> selectComputerhomeworkreceiverByLoginuserId(Integer userid ){
	
		return null;
	}
  

	public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullByLoginuserId(Integer userid ){
	
		return null;
	}

 
}