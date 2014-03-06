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

import com.sbgl.app.dao.CoursecomputerorderDao;
import com.sbgl.app.entity.Coursecomputerorder;
import com.sbgl.app.entity.CoursecomputerorderFull;
import com.sbgl.util.*;

@Repository("coursecomputerorderDao")
public class CoursecomputerorderDaoImpl extends HibernateDaoSupport implements CoursecomputerorderDao{

	private static final Log log = LogFactory.getLog(CoursecomputerorderDaoImpl.class);
	private final String basicCoursecomputerorderFullSql = "select a.id as coursecomputerorderid, a.semesterid as coursecomputerordersemesterid, a.courseid as coursecomputerordercourseid, a.computerorderid as coursecomputerordercomputerorderid, a.status as coursecomputerorderstatus from Coursecomputerorder a  ";
	
	private final String basicCoursecomputerorderSql = "From Coursecomputerorder as a ";
	
	
	/**
	 * 查询某学期某个课程的 某个学期的预约
	 * 学期信息
	 * 课程类型
	 */
	@Override
	public Coursecomputerorder selectBySemesterCourse(int semesterid,int courseid){
		
		String sql = " where semesterid = "+semesterid+" and courseid="+courseid;
		List<Coursecomputerorder> temp = selectCoursecomputerorderByCondition(sql);
		
		if(temp!=null && temp.size()!=0){
			return temp.get(0);
		}
		return null;
	}
	
	
	
	// 根据条件查询查询实体
	@Override
	public List<Coursecomputerorder> selectCoursecomputerorderByCondition(String condition) {
		final String  sql = basicCoursecomputerorderSql +" " + condition;
		
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
        public List<Coursecomputerorder>  selectCoursecomputerorderByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicCoursecomputerorderSql  +conditionSql;
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
	public List<CoursecomputerorderFull> selectCoursecomputerorderFullByCondition(String condition) {
		final String  sql = basicCoursecomputerorderFullSql +" " + condition;
		
		List<CoursecomputerorderFull> coursecomputerorderList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CoursecomputerorderFull.class));
						return query.list();
					}
				});		
		return coursecomputerorderList;
	}
	
	
	// 查询实体full        
        @Override
        public List<CoursecomputerorderFull>  selectCoursecomputerorderFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicCoursecomputerorderFullSql  +conditionSql;
                List<CoursecomputerorderFull> coursecomputerorderList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                CoursecomputerorderFull.class));
                                                return query.list();
                                        }
                                });
                if (coursecomputerorderList != null && !coursecomputerorderList.isEmpty()) {
                        return coursecomputerorderList;
                }
                return coursecomputerorderList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer coursecomputerorderId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Coursecomputerorder as tb set tb.status = 0 where tb.id = " +coursecomputerorderId; 
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
	public CoursecomputerorderFull selectCoursecomputerorderFullById(Integer coursecomputerorderId) {
		final String  sql =  basicCoursecomputerorderFullSql + " where a.id = "+coursecomputerorderId;
		
		List<CoursecomputerorderFull> coursecomputerorderList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CoursecomputerorderFull.class));
						return query.list();
					}
				});
		if (coursecomputerorderList != null && !coursecomputerorderList.isEmpty()) {
			return coursecomputerorderList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<CoursecomputerorderFull> selectCoursecomputerorderFullAll() {
		final String  sql = basicCoursecomputerorderFullSql;
		
		List<CoursecomputerorderFull> coursecomputerorderFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CoursecomputerorderFull.class));
						return query.list();
					}
				});
		if (coursecomputerorderFullList != null && !coursecomputerorderFullList.isEmpty()) {			
			return coursecomputerorderFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<CoursecomputerorderFull> selectCoursecomputerorderFullByPage(final Page page){
		final String  sql = basicCoursecomputerorderFullSql;
		
		List<CoursecomputerorderFull> coursecomputerorderFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								CoursecomputerorderFull.class));
						return query.list();
					}
				});
		if (coursecomputerorderFullList != null && !coursecomputerorderFullList.isEmpty()) {			
			return coursecomputerorderFullList;
		}
		return null;
	}
	
//  根据关联查询实体full
 
}