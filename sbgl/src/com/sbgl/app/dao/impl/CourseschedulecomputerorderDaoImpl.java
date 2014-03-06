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

import com.sbgl.app.actions.teach.TeachConstant;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.DaoAbs;

import com.sbgl.app.dao.CourseschedulecomputerorderDao;
import com.sbgl.app.entity.Courseschedulecomputerorder;
import com.sbgl.app.entity.CourseschedulecomputerorderFull;
import com.sbgl.util.*;

@Repository("courseschedulecomputerorderDao")
public class CourseschedulecomputerorderDaoImpl extends HibernateDaoSupport implements CourseschedulecomputerorderDao{

	private static final Log log = LogFactory.getLog(CourseschedulecomputerorderDaoImpl.class);
	private final String basicCourseschedulecomputerorderFullSql = "select a.id as courseschedulecomputerorderid, a.computercoursescheduleid as courseschedulecomputerordercomputercoursescheduleid, a.computerorderid as courseschedulecomputerordercomputerorderid, a.status as courseschedulecomputerorderstatus, b.id as computerorderid, b.serialnumber as computerorderserialnumber, b.createuserid as computerordercreateuserid, b.title as computerordertitle, b.ordertype as computerorderordertype, b.createtime as computerordercreatetime, b.remark as computerorderremark, b.rejectreason as computerorderrejectreason, b.computerhomeworkid as computerordercomputerhomeworkid, b.audituserid as computerorderaudituserid, b.status as computerorderstatus from Courseschedulecomputerorder a  left join Computerorder b on a.computerorderid=b.id ";
	
	private final String basicCourseschedulecomputerorderSql = "From Courseschedulecomputerorder as a ";
	
	/**
	 * 根据课程序号删除相应预约关系
	 * @param csid
	 */
	@Override
	public void delByCoursescheduleid(int csid){
//		String sql = " update Courseschedulecomputerorder set status = "+TeachConstant.coursescheduledelstatus+" where lessonid = "+csId;
		String sql = " delete Courseschedulecomputerorder where computercoursescheduleid = "+csid;
		try {
			Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("查询失败", re);
			throw re;

		}
	}
	
	
	/**
	 * 根据课程的小节获取订单信息
	 * 由于小节是唯一的，所以返回是一个实体
	 */
	@Override
	public List<Courseschedulecomputerorder> selectByCoursescheduleid(int coursescheduleid) {
		final String  sql = basicCourseschedulecomputerorderSql +" where  computercoursescheduleid = " + coursescheduleid;
		
		try {
             List l = this.getHibernateTemplate().find(sql);

			 return l;
        } catch (RuntimeException re) {
            log.error("失败", re);
            throw re;
        }
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Courseschedulecomputerorder> selectCourseschedulecomputerorderByCondition(String condition) {
		final String  sql = basicCourseschedulecomputerorderSql +" " + condition;
		
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
        public List<Courseschedulecomputerorder>  selectCourseschedulecomputerorderByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicCourseschedulecomputerorderSql  +conditionSql;
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
	public List<CourseschedulecomputerorderFull> selectCourseschedulecomputerorderFullByCondition(String condition) {
		final String  sql = basicCourseschedulecomputerorderFullSql +" " + condition;
		
		List<CourseschedulecomputerorderFull> courseschedulecomputerorderList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CourseschedulecomputerorderFull.class));
						return query.list();
					}
				});		
		return courseschedulecomputerorderList;
	}
	
	
	// 查询实体full        
        @Override
        public List<CourseschedulecomputerorderFull>  selectCourseschedulecomputerorderFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicCourseschedulecomputerorderFullSql  +conditionSql;
                List<CourseschedulecomputerorderFull> courseschedulecomputerorderList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                CourseschedulecomputerorderFull.class));
                                                return query.list();
                                        }
                                });
                if (courseschedulecomputerorderList != null && !courseschedulecomputerorderList.isEmpty()) {
                        return courseschedulecomputerorderList;
                }
                return courseschedulecomputerorderList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer courseschedulecomputerorderId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Courseschedulecomputerorder as tb set tb.status = 0 where tb.id = " +courseschedulecomputerorderId; 
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
	public CourseschedulecomputerorderFull selectCourseschedulecomputerorderFullById(Integer courseschedulecomputerorderId) {
		final String  sql =  basicCourseschedulecomputerorderFullSql + " where a.id = "+courseschedulecomputerorderId;
		
		List<CourseschedulecomputerorderFull> courseschedulecomputerorderList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CourseschedulecomputerorderFull.class));
						return query.list();
					}
				});
		if (courseschedulecomputerorderList != null && !courseschedulecomputerorderList.isEmpty()) {
			return courseschedulecomputerorderList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<CourseschedulecomputerorderFull> selectCourseschedulecomputerorderFullAll() {
		final String  sql = basicCourseschedulecomputerorderFullSql;
		
		List<CourseschedulecomputerorderFull> courseschedulecomputerorderFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CourseschedulecomputerorderFull.class));
						return query.list();
					}
				});
		if (courseschedulecomputerorderFullList != null && !courseschedulecomputerorderFullList.isEmpty()) {			
			return courseschedulecomputerorderFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<CourseschedulecomputerorderFull> selectCourseschedulecomputerorderFullByPage(final Page page){
		final String  sql = basicCourseschedulecomputerorderFullSql;
		
		List<CourseschedulecomputerorderFull> courseschedulecomputerorderFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								CourseschedulecomputerorderFull.class));
						return query.list();
					}
				});
		if (courseschedulecomputerorderFullList != null && !courseschedulecomputerorderFullList.isEmpty()) {			
			return courseschedulecomputerorderFullList;
		}
		return null;
	}
	
//  根据关联查询实体full

	//根据关联查询实体 
	public List<Courseschedulecomputerorder> selectCourseschedulecomputerorderByComputerorderId(Integer computerorderid){
	
		return null;
	}
  

	public List<CourseschedulecomputerorderFull> selectCourseschedulecomputerorderFullByComputerorderId(Integer computerorderid){
	
		return null;
	}

 
}