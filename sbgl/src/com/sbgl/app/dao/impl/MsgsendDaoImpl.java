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

import com.sbgl.app.dao.MsgsendDao;
import com.sbgl.app.entity.Msgsend;
import com.sbgl.app.entity.MsgsendFull;
import com.sbgl.util.*;

@Repository("msgsendDao")
public class MsgsendDaoImpl extends HibernateDaoSupport implements MsgsendDao{

	private static final Log log = LogFactory.getLog(MsgsendDaoImpl.class);
	private final String basicMsgsendFullSql = "select a.id as msgsendid, a.senderid as msgsendsenderid, a.receiverid as msgsendreceiverid, a.title as msgsendtitle, a.content as msgsendcontent, a.type as msgsendtype, a.sendtime as msgsendsendtime, a.readtime as msgsendreadtime, a.status as msgsendstatus from Msgsend a  ";
	
	private final String basicMsgsendSql = "From Msgsend as a ";
	
	// 根据条件查询查询实体
	@Override
	public List<Msgsend> selectMsgsendByCondition(String condition) {
		final String  sql = basicMsgsendSql +" " + condition;
		
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
        public List<Msgsend>  selectMsgsendByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicMsgsendSql  +conditionSql;
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
	public List<MsgsendFull> selectMsgsendFullByCondition(String condition) {
		final String  sql = basicMsgsendFullSql +" " + condition;
		
		List<MsgsendFull> msgsendList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								MsgsendFull.class));
						return query.list();
					}
				});		
		return msgsendList;
	}
	
	
	// 查询实体full        
        @Override
        public List<MsgsendFull>  selectMsgsendFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicMsgsendFullSql  +conditionSql;
                List<MsgsendFull> msgsendList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                MsgsendFull.class));
                                                return query.list();
                                        }
                                });
                if (msgsendList != null && !msgsendList.isEmpty()) {
                        return msgsendList;
                }
                return msgsendList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer msgsendId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Msgsend as tb set tb.status = 0 where tb.id = " +msgsendId; 
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
	public MsgsendFull selectMsgsendFullById(Integer msgsendId) {
		final String  sql =  basicMsgsendFullSql + " where a.id = "+msgsendId;
		
		List<MsgsendFull> msgsendList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								MsgsendFull.class));
						return query.list();
					}
				});
		if (msgsendList != null && !msgsendList.isEmpty()) {
			return msgsendList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<MsgsendFull> selectMsgsendFullAll() {
		final String  sql = basicMsgsendFullSql;
		
		List<MsgsendFull> msgsendFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								MsgsendFull.class));
						return query.list();
					}
				});
		if (msgsendFullList != null && !msgsendFullList.isEmpty()) {			
			return msgsendFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<MsgsendFull> selectMsgsendFullByPage(final Page page){
		final String  sql = basicMsgsendFullSql;
		
		List<MsgsendFull> msgsendFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								MsgsendFull.class));
						return query.list();
					}
				});
		if (msgsendFullList != null && !msgsendFullList.isEmpty()) {			
			return msgsendFullList;
		}
		return null;
	}
	
//  根据关联查询实体full
 
}