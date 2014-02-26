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

import com.sbgl.app.dao.MsgreceiveDao;
import com.sbgl.app.entity.Msgreceive;
import com.sbgl.app.entity.MsgreceiveFull;
import com.sbgl.util.*;

@Repository("msgreceiveDao")
public class MsgreceiveDaoImpl extends HibernateDaoSupport implements MsgreceiveDao{

	private static final Log log = LogFactory.getLog(MsgreceiveDaoImpl.class);
	private final String basicMsgreceiveFullSql = "select a.id as msgreceiveid, a.senderid as msgreceivesenderid, a.receiverid as msgreceivereceiverid, a.title as msgreceivetitle, a.content as msgreceivecontent, a.type as msgreceivetype, a.sendtime as msgreceivesendtime, a.readtime as msgreceivereadtime, a.status as msgreceivestatus, b.id as senderid, b.userId as senderuserid, b.name as sendername, b.roletype as senderroletype, b.privilege as senderprivilege, b.password as senderpassword, c.id as receiverid, c.userId as receiveruserid, c.name as receivername, c.roletype as receiverroletype, c.privilege as receiverprivilege, c.password as receiverpassword from Msgreceive a  left join Loginuser b on a.senderid=b.id left join Loginuser c on a.receiverid=c.id ";
	
	private final String basicMsgreceiveSql = "From Msgreceive as a ";
	
	// 根据条件查询查询实体
	@Override
	public List<Msgreceive> selectMsgreceiveByCondition(String condition) {
		final String  sql = basicMsgreceiveSql +" " + condition;
		
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
        public List<Msgreceive>  selectMsgreceiveByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicMsgreceiveSql  +conditionSql;
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
	public List<MsgreceiveFull> selectMsgreceiveFullByCondition(String condition) {
		final String  sql = basicMsgreceiveFullSql +" " + condition;
		
		List<MsgreceiveFull> msgreceiveList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								MsgreceiveFull.class));
						return query.list();
					}
				});		
		return msgreceiveList;
	}
	
	
	// 查询实体full        
        @Override
        public List<MsgreceiveFull>  selectMsgreceiveFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicMsgreceiveFullSql  +conditionSql;
                List<MsgreceiveFull> msgreceiveList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                MsgreceiveFull.class));
                                                return query.list();
                                        }
                                });
                if (msgreceiveList != null && !msgreceiveList.isEmpty()) {
                        return msgreceiveList;
                }
                return msgreceiveList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer msgreceiveId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Msgreceive as tb set tb.status = 0 where tb.id = " +msgreceiveId; 
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
	public MsgreceiveFull selectMsgreceiveFullById(Integer msgreceiveId) {
		final String  sql =  basicMsgreceiveFullSql + " where a.id = "+msgreceiveId;
		
		List<MsgreceiveFull> msgreceiveList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								MsgreceiveFull.class));
						return query.list();
					}
				});
		if (msgreceiveList != null && !msgreceiveList.isEmpty()) {
			return msgreceiveList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<MsgreceiveFull> selectMsgreceiveFullAll() {
		final String  sql = basicMsgreceiveFullSql;
		
		List<MsgreceiveFull> msgreceiveFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								MsgreceiveFull.class));
						return query.list();
					}
				});
		if (msgreceiveFullList != null && !msgreceiveFullList.isEmpty()) {			
			return msgreceiveFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<MsgreceiveFull> selectMsgreceiveFullByPage(final Page page){
		final String  sql = basicMsgreceiveFullSql;
		
		List<MsgreceiveFull> msgreceiveFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								MsgreceiveFull.class));
						return query.list();
					}
				});
		if (msgreceiveFullList != null && !msgreceiveFullList.isEmpty()) {			
			return msgreceiveFullList;
		}
		return null;
	}
	
//  根据关联查询实体full



 
}