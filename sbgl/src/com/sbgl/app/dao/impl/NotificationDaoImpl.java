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

import com.sbgl.app.dao.NotificationDao;
import com.sbgl.app.entity.Notification;
import com.sbgl.app.entity.NotificationFull;
import com.sbgl.util.*;

@Repository("notificationDao")
public class NotificationDaoImpl extends HibernateDaoSupport implements NotificationDao{

	private static final Log log = LogFactory.getLog(NotificationDaoImpl.class);
	private final String basicNotificationFullSql = "select a.id as notificationid, a.title as notificationtitle, a.content as notificationcontent, a.senderrid as notificationsenderrid, a.receiverid as notificationreceiverid, a.sendtime as notificationsendtime, a.readstatus as notificationreadstatus, a.modeltype as notificationmodeltype, a.status as notificationstatus," +
			" b.id as senderloginuserid, b.name as senderloginusername  " +
			"c.id as receiverloginuserid, c.name as receiverloginusername    " +
			"from Notification a  left join Loginuser b on a.senderid=b.id left join Loginuser c on a.receiverid=c.id ";
	
	private final String basicNotificationSql = "From Notification as a ";
	
	// 根据条件查询查询实体
	@Override
	public List<Notification> selectNotificationByCondition(String condition) {
		final String  sql = basicNotificationSql +" " + condition;
		
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
        public List<Notification>  selectNotificationByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicNotificationSql  +conditionSql;
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
	public List<NotificationFull> selectNotificationFullByCondition(String condition) {
		final String  sql = basicNotificationFullSql +" " + condition;
		
		List<NotificationFull> notificationList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								NotificationFull.class));
						return query.list();
					}
				});		
		return notificationList;
	}
	
	
	// 查询实体full        
        @Override
        public List<NotificationFull>  selectNotificationFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicNotificationFullSql  +conditionSql;
                List<NotificationFull> notificationList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                NotificationFull.class));
                                                return query.list();
                                        }
                                });
                if (notificationList != null && !notificationList.isEmpty()) {
                        return notificationList;
                }
                return notificationList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer notificationId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Notification as tb set tb.status = 0 where tb.id = " +notificationId; 
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
	public NotificationFull selectNotificationFullById(Integer notificationId) {
		final String  sql =  basicNotificationFullSql + " where a.id = "+notificationId;
		
		List<NotificationFull> notificationList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								NotificationFull.class));
						return query.list();
					}
				});
		if (notificationList != null && !notificationList.isEmpty()) {
			return notificationList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<NotificationFull> selectNotificationFullAll() {
		final String  sql = basicNotificationFullSql;
		
		List<NotificationFull> notificationFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								NotificationFull.class));
						return query.list();
					}
				});
		if (notificationFullList != null && !notificationFullList.isEmpty()) {			
			return notificationFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<NotificationFull> selectNotificationFullByPage(final Page page){
		final String  sql = basicNotificationFullSql;
		
		List<NotificationFull> notificationFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								NotificationFull.class));
						return query.list();
					}
				});
		if (notificationFullList != null && !notificationFullList.isEmpty()) {			
			return notificationFullList;
		}
		return null;
	}
	
//  根据关联查询实体full



 
}