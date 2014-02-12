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

import com.sbgl.app.dao.MessagereceiverDao;
import com.sbgl.app.entity.Messagereceiver;
import com.sbgl.app.entity.MessagereceiverFull;
import com.sbgl.util.*;

@Repository("messagereceiverDao")
public class MessagereceiverDaoImpl extends HibernateDaoSupport implements MessagereceiverDao{

	private static final Log log = LogFactory.getLog(MessagereceiverDaoImpl.class);
	private final String basicMessagereceiverFullSql = "select a.id as messagereceiverid, a.messageid as messagereceivermessageid, a.receiverid as messagereceiverreceiverid, a.hasview as messagereceiverhasview, a.viewdate as messagereceiverviewdate, a.status as messagereceiverstatus, b.id as receiverloginuserid, b.name as receiverloginusername, b.createtime as receiverloginusercreatetime, b.status as receiverloginuserstatus from Messagereceiver a  left join Loginuser b on a.receiverid=b.id ";
	
	private final String basicMessagereceiverSql = "From Messagereceiver as a ";
	
	// 根据条件查询查询实体
	@Override
	public List<Messagereceiver> selectMessagereceiverByCondition(String condition) {
		final String  sql = basicMessagereceiverSql +" " + condition;
		
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
        public List<Messagereceiver>  selectMessagereceiverByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicMessagereceiverSql  +conditionSql;
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
	public List<MessagereceiverFull> selectMessagereceiverFullByCondition(String condition) {
		final String  sql = basicMessagereceiverFullSql +" " + condition;
		
		List<MessagereceiverFull> messagereceiverList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								MessagereceiverFull.class));
						return query.list();
					}
				});		
		return messagereceiverList;
	}
	
	
	// 查询实体full        
        @Override
        public List<MessagereceiverFull>  selectMessagereceiverFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicMessagereceiverFullSql  +conditionSql;
                List<MessagereceiverFull> messagereceiverList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                MessagereceiverFull.class));
                                                return query.list();
                                        }
                                });
                if (messagereceiverList != null && !messagereceiverList.isEmpty()) {
                        return messagereceiverList;
                }
                return messagereceiverList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer messagereceiverId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Messagereceiver as tb set tb.status = 0 where tb.id = " +messagereceiverId; 
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
	public MessagereceiverFull selectMessagereceiverFullById(Integer messagereceiverId) {
		final String  sql =  basicMessagereceiverFullSql + " where a.id = "+messagereceiverId;
		
		List<MessagereceiverFull> messagereceiverList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								MessagereceiverFull.class));
						return query.list();
					}
				});
		if (messagereceiverList != null && !messagereceiverList.isEmpty()) {
			return messagereceiverList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<MessagereceiverFull> selectMessagereceiverFullAll() {
		final String  sql = basicMessagereceiverFullSql;
		
		List<MessagereceiverFull> messagereceiverFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								MessagereceiverFull.class));
						return query.list();
					}
				});
		if (messagereceiverFullList != null && !messagereceiverFullList.isEmpty()) {			
			return messagereceiverFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<MessagereceiverFull> selectMessagereceiverFullByPage(final Page page){
		final String  sql = basicMessagereceiverFullSql;
		
		List<MessagereceiverFull> messagereceiverFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								MessagereceiverFull.class));
						return query.list();
					}
				});
		if (messagereceiverFullList != null && !messagereceiverFullList.isEmpty()) {			
			return messagereceiverFullList;
		}
		return null;
	}
	
//  根据关联查询实体full

	//根据关联查询实体 
	public List<Messagereceiver> selectMessagereceiverByLoginuserId(Integer receiverid){
	
		return null;
	}
  

	public List<MessagereceiverFull> selectMessagereceiverFullByLoginuserId(Integer receiverid){
	
		return null;
	}

 
}