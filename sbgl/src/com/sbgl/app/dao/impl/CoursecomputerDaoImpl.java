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

import com.sbgl.app.dao.CoursecomputerDao;
import com.sbgl.app.entity.Coursecomputer;
import com.sbgl.app.entity.CoursecomputerFull;
import com.sbgl.app.entity.Courseschedule;
import com.sbgl.util.*;

@Repository("coursecomputerDao")
public class CoursecomputerDaoImpl extends HibernateDaoSupport implements CoursecomputerDao{

	private static final Log log = LogFactory.getLog(CoursecomputerDaoImpl.class);
	private final String basicCoursecomputerFullSql =
		"select a.id as coursecomputerid, a.lessonid as coursecomputerlessonid, a.computerid as coursecomputercomputerid, a.borrownum as coursecomputerborrownum, a.status as coursecomputerstatus," +
		" b.id as csid, b.courseid as cscourseid, b.semester as cssemester, b.week as csweek, b.day as csday, b.period as csperiod, b.adduserid as csadduserid, b.status as csstatus," +
		" c.id as cmid, c.computermodeltype as cmcomputermodeltype, c.languagetype as cmlanguagetype, c.name as cmname, c.computercategoryid as cmcomputercategoryid, c.picpath as cmpicpath, c.createtime as cmcreatetime, c.createuserid as cmcreateuserid, c.computercount as cmcomputercount, c.availableborrowcountnumber as cmavailableborrowcountnumber, c.description as cmdescription, c.status as cmstatus " +
		"from Coursecomputer a  left join Courseschedule b on a.lessonid=b.id left join Computermodel c on a.computerid=c.computermodeltype ";
	
	private final String basicCoursecomputerSql = "From Coursecomputer as a ";
	
	
	@Override
	public List<CoursecomputerFull> selectCoursecomputerFullByPeriod(Integer courseid,Integer semesterid,Integer week,Integer day,Integer period,int language) {
		
		String condition = " where a.status="+TeachConstant.courseschedulevalidstatus+" and b.courseid = "+courseid +" and b.semester = "+ semesterid + " and b.week="+week + " and b.day ="+day+" and b.period="+period+" and c.languagetype = "+language;
		return this.selectCoursecomputerFullByCondition(condition);
	} 

	@Override
	public void delCoursecomputerByCourseschedule(int csId) {
		
		String sql = " update Coursecomputer set status = "+TeachConstant.coursescheduledelstatus+" where lessonid = "+csId;
		try {
			Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
	         Query query = session.createSQLQuery(sql);
			 query.executeUpdate();
			} catch (RuntimeException re) {
	            log.error("查询失败", re);           
	            throw re;
	            
	        }
	}
	
	
	// 根据条件查询查询实体
	@Override
	public List<Coursecomputer> selectCoursecomputerByCondition(String condition) {
		final String  sql = basicCoursecomputerSql +" " + condition;
		
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
        public List<Coursecomputer>  selectCoursecomputerByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicCoursecomputerSql  +conditionSql;
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
	public List<CoursecomputerFull> selectCoursecomputerFullByCondition(String condition) {
		final String  sql = basicCoursecomputerFullSql +" " + condition;
		
		List<CoursecomputerFull> coursecomputerList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CoursecomputerFull.class));
						return query.list();
					}
				});		
		return coursecomputerList;
	}
	
	
	// 查询实体full        
        @Override
        public List<CoursecomputerFull>  selectCoursecomputerFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicCoursecomputerFullSql  +conditionSql;
                List<CoursecomputerFull> coursecomputerList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                CoursecomputerFull.class));
                                                return query.list();
                                        }
                                });
                if (coursecomputerList != null && !coursecomputerList.isEmpty()) {
                        return coursecomputerList;
                }
                return coursecomputerList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer coursecomputerId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Coursecomputer as tb set tb.status = 0 where tb.id = " +coursecomputerId; 
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
	public CoursecomputerFull selectCoursecomputerFullById(Integer coursecomputerId) {
		final String  sql =  basicCoursecomputerFullSql + " where a.id = "+coursecomputerId;
		
		List<CoursecomputerFull> coursecomputerList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CoursecomputerFull.class));
						return query.list();
					}
				});
		if (coursecomputerList != null && !coursecomputerList.isEmpty()) {
			return coursecomputerList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<CoursecomputerFull> selectCoursecomputerFullAll() {
		final String  sql = basicCoursecomputerFullSql;
		
		List<CoursecomputerFull> coursecomputerFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CoursecomputerFull.class));
						return query.list();
					}
				});
		if (coursecomputerFullList != null && !coursecomputerFullList.isEmpty()) {			
			return coursecomputerFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<CoursecomputerFull> selectCoursecomputerFullByPage(final Page page){
		final String  sql = basicCoursecomputerFullSql;
		
		List<CoursecomputerFull> coursecomputerFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								CoursecomputerFull.class));
						return query.list();
					}
				});
		if (coursecomputerFullList != null && !coursecomputerFullList.isEmpty()) {			
			return coursecomputerFullList;
		}
		return null;
	}
	
//  根据关联查询实体full
 
}