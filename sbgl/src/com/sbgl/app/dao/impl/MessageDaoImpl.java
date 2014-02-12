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

import com.sbgl.app.dao.MessageDao;
import com.sbgl.app.entity.Message;
import com.sbgl.app.entity.MessageFull;
import com.sbgl.util.*;

@Repository("messageDao")
public class MessageDaoImpl extends HibernateDaoSupport implements MessageDao{

	private static final Log log = LogFactory.getLog(MessageDaoImpl.class);
	private final String basicMessageFullSql = "select a.id as messageid, a.title as messagetitle, a.content as messagecontent, a.senderid as messagesenderid, a.sendtime as messagesendtime, a.replyid as messagereplyid, a.readstatus as messagereadstatus, a.filepath as messagefilepath, a.isbigfile as messageisbigfile, a.type as messagetype, a.status as messagestatus, b.id as senderloginuserid, b.name as senderloginusername, b.createtime as senderloginusercreatetime, b.status as senderloginuserstatus from Message a  left join Loginuser b on a.senderid=b.id ";
	
	private final String basicMessageSql = "From Message as a ";
	
	// 根据条件查询查询实体
	@Override
	public List<Message> selectMessageByCondition(String condition) {
		final String  sql = basicMessageSql +" " + condition;
		
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
        public List<Message>  selectMessageByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicMessageSql  +conditionSql;
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
	public List<MessageFull> selectMessageFullByCondition(String condition) {
		final String  sql = basicMessageFullSql +" " + condition;
		
		List<MessageFull> messageList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								MessageFull.class));
						return query.list();
					}
				});		
		return messageList;
	}
	
	
	// 查询实体full        
        @Override
        public List<MessageFull>  selectMessageFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicMessageFullSql  +conditionSql;
                List<MessageFull> messageList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                MessageFull.class));
                                                return query.list();
                                        }
                                });
                if (messageList != null && !messageList.isEmpty()) {
                        return messageList;
                }
                return messageList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer messageId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Message as tb set tb.status = 0 where tb.id = " +messageId; 
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
	public MessageFull selectMessageFullById(Integer messageId) {
		final String  sql =  basicMessageFullSql + " where a.id = "+messageId;
		
		List<MessageFull> messageList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								MessageFull.class));
						return query.list();
					}
				});
		if (messageList != null && !messageList.isEmpty()) {
			return messageList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<MessageFull> selectMessageFullAll() {
		final String  sql = basicMessageFullSql;
		
		List<MessageFull> messageFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								MessageFull.class));
						return query.list();
					}
				});
		if (messageFullList != null && !messageFullList.isEmpty()) {			
			return messageFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<MessageFull> selectMessageFullByPage(final Page page){
		final String  sql = basicMessageFullSql;
		
		List<MessageFull> messageFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								MessageFull.class));
						return query.list();
					}
				});
		if (messageFullList != null && !messageFullList.isEmpty()) {			
			return messageFullList;
		}
		return null;
	}
	
//  根据关联查询实体full

	//根据关联查询实体 
	public List<Message> selectMessageByLoginuserId(Integer senderid){
	
		return null;
	}
  

	public List<MessageFull> selectMessageFullByLoginuserId(Integer senderid){
	
		return null;
	}

 
}