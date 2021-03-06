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

import com.sbgl.app.dao.CourseDao;
import com.sbgl.app.entity.Course;
import com.sbgl.app.entity.CourseFull;
import com.sbgl.util.*;

@Repository("courseDao")
public class CourseDaoImpl extends HibernateDaoSupport implements CourseDao{

	private static final Log log = LogFactory.getLog(CourseDaoImpl.class);
	private final String basicCourseFullSql = " select a.id as courseid, a.name as coursename, a.description as coursedescription, a.type as coursetype, a.coursetype as coursecoursetype, a.languagetype as courselanguagetype, a.adduserid as courseadduserid, a.teacherid as courseteacherid, a.addtime as courseaddtime, a.status as coursestatus, "+
//			"b.id as adduserloginuserid, b.name as adduserloginusername, b.userid as adduserloginuseruserid, b.password as adduserloginuserpassword, b.createtime as adduserloginusercreatetime, b.status as adduserloginuserstatus, b.roletype as adduserloginuserroletype, " +
			"b.id as adduserloginuserid, b.name as adduserloginusername, "+
//			"c.id as teacherloginuserid, c.name as teacherloginusername, c.userid as teacherloginuseruserid, c.password as teacherloginuserpassword, c.createtime as teacherloginusercreatetime, c.status as teacherloginuserstatus, c.roletype as teacherloginuserroletype " +
			"c.id as teacherloginuserid, c.name as teacherloginusername, "+
			"d.name as usergroupname "+
			"from Course a  left join Loginuser b on a.adduserid=b.id left join Loginuser c on a.teacherid=c.id left join Usergroup d on a.type=d.id ";
	
	private final String basicCourseSql = "From Course as a ";
	private final String countSql = "select count(*) from Course as a ";
	
	
	/**
	 * 根据年级查询数据
	 * @param grade
	 * @return
	 */
	@Override
	public int countRowByGrade(int grade){
		
		String condition = "";
		if(grade !=0){
			condition = " where a.status >=0  and a.type=" + grade;
		}else{
			condition = " where a.status >=0 " ;
		}
		
		Long count = (Long)this.getSession()
        .createQuery(countSql + condition)
        .uniqueResult();
		return count.intValue();
	}
	
	/**
	 * 根据课程种类及语言查询课程
	 * @param type
	 * @return
	 */
	@Override
	public Course sel(int coursetype,int language){
		String condition = "";
		
		condition = " where a.status >=0  and a.coursetype=" + coursetype + " and a.languagetype = "+ language;
		List l = this.selectCourseByCondition(condition);
		if(l == null){
			return null;
		}else{
			return (Course) l.get(0);
		}
		
	}
	
	
	@Override
	public List<CourseFull> selFullByGrade(Integer grade, int language){
		String sql = "";
		if(grade == 0){
			 sql =" where a.status >=0 " +
	  				" and a.languagetype="+language+

	  				" order by a.id";	
		}else{
			 sql =" where a.status >=0 " +
				" and a.languagetype="+language+

				" and a.type="+grade+
				" order by a.id";				
		}
		return this.selectCourseFullByCondition(sql);
	}
	
	@Override
	public List<CourseFull> selFullByGradePage(Integer grade,Page page,int language){
		String sql = "";
		if(grade == 0){
			 sql =" where a.status >=0 " +
	  				" and a.languagetype="+language+

	  				" order by a.id";	
		}else{
			 sql =" where a.status >=0 " +
				" and a.languagetype="+language+

				" and a.type="+grade+
				" order by a.id";				
		}
		return this.selectCourseFullByConditionAndPage(sql,page);
	}
	
	
//	根据课程类型、语言类型查询实体类			
	@Override
	public List<CourseFull> selectCourseFullByCoursetype(Integer coursetype,int language){
		List<CourseFull> l = selectCourseFullByCondition("where a.languagetype="+language+" and a.coursetype ="+coursetype);
		
		return l;
	}
	
	@Override
	public List<Course> selectCourseByCoursetype(Integer coursetype,int language){
		List<Course> l = selectCourseByCondition("where languagetype="+language+" and coursetype ="+coursetype);
		
		return l;
	}
	
	
	// 根据条件查询查询实体
	@Override
	public List<Course> selectCourseByCondition(String condition) {
		final String  sql = basicCourseSql +" " + condition;
		
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
        public List<Course>  selectCourseByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicCourseSql  +conditionSql;
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
	public List<CourseFull> selectCourseFullByCondition(String condition) {
		final String  sql = basicCourseFullSql +" " + condition;
		
		List<CourseFull> courseList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CourseFull.class));
						return query.list();
					}
				});		
		return courseList;
	}
	
	
	// 查询实体full        
        @Override
        public List<CourseFull>  selectCourseFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicCourseFullSql  +conditionSql;
                List<CourseFull> courseList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                CourseFull.class));
                                                return query.list();
                                        }
                                });
                if (courseList != null && !courseList.isEmpty()) {
                        return courseList;
                }
                return courseList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer courseId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Course as tb set tb.status = 0 where tb.id = " +courseId; 
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
	public CourseFull selectCourseFullById(Integer courseId) {
		final String  sql =  basicCourseFullSql + " where a.id = "+courseId;
		
		List<CourseFull> courseList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CourseFull.class));
						return query.list();
					}
				});
		if (courseList != null && !courseList.isEmpty()) {
			return courseList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<CourseFull> selectCourseFullAll() {
		final String  sql = basicCourseFullSql;
		
		List<CourseFull> courseFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CourseFull.class));
						return query.list();
					}
				});
		if (courseFullList != null && !courseFullList.isEmpty()) {			
			return courseFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<CourseFull> selectCourseFullByPage(final Page page){
		final String  sql = basicCourseFullSql;
		
		List<CourseFull> courseFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								CourseFull.class));
						return query.list();
					}
				});
		if (courseFullList != null && !courseFullList.isEmpty()) {			
			return courseFullList;
		}
		return null;
	}



 
}