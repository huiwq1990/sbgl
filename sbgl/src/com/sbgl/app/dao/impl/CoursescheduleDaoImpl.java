package com.sbgl.app.dao.impl;


import java.io.Serializable;
import java.util.ArrayList;
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

import com.sbgl.app.dao.CoursescheduleDao;
import com.sbgl.app.entity.Courseschedule;
import com.sbgl.app.entity.CoursescheduleFull;
import com.sbgl.util.*;

@Repository("coursescheduleDao")
public class CoursescheduleDaoImpl extends HibernateDaoSupport implements CoursescheduleDao{

	private static final Log log = LogFactory.getLog(CoursescheduleDaoImpl.class);
	
	private final String basicCoursescheduleFullSql = "select a.id as coursescheduleid, a.courseid as courseschedulecourseid, a.semester as courseschedulesemester, a.week as coursescheduleweek, a.day as coursescheduleday, a.period as coursescheduleperiod, a.adduserid as coursescheduleadduserid, a.status as courseschedulestatus, " +
			"b.id as courseid, b.name as coursename, b.description as coursedescription, b.type as coursetype, b.coursetype as coursecoursetype, b.languagetype as courselanguagetype, b.adduserid as courseadduserid, b.teacherid as courseteacherid, b.addtime as courseaddtime, b.status as coursestatus," +
			" c.id as adduserid, c.userid as adduseruserid, c.name as addusername, c.gender as addusergender, c.telephone as addusertelephone, c.email as adduseremail, c.roletype as adduserroletype, c.privilege as adduserprivilege, c.password as adduserpassword, c.photo as adduserphoto " +
			"from Courseschedule a  left join Course b on a.courseid=b.coursetype left join Loginuser c on a.adduserid=c.id ";
	
	private final String basicCoursescheduleSql = "From Courseschedule as a ";
	
	/**
	 * 根据 课程、学期、
	 */
	@Override
	public void delCoursescheduleByPeriod(Courseschedule temp) {
		
		String sql = " update Courseschedule set status = "+TeachConstant.coursescheduledelstatus+" where courseid = "+temp.getCourseid()+" and semester = "+temp.getSemester()+" and week="+temp.getWeek()+" and day = "+temp.getDay()+"  and period = "+temp.getPeriod();
		try {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
         Query query = session.createSQLQuery(sql);
		 query.executeUpdate();
		} catch (RuntimeException re) {
            log.error("查询失败", re);           
            throw re;
            
        }
	}
	
//	public int deleteCourseschedule(Integer courseId,Integer semesterId,Integer weeknum,Integer day,Integer period){
//		
//	}
	
	/**
	 * 查询某个学期某周的全部课程
	 */
	@Override
	public List<CoursescheduleFull> selectCoursescheduleFullByWeek(Integer semesterId,Integer weeknum,int languagetype){
		List<CoursescheduleFull> coursescheduleList = new ArrayList<CoursescheduleFull>();
		String addedsql = "   where a.status = "+TeachConstant.courseschedulevalidstatus+" and a.semester = "+semesterId+" and a.week="+weeknum + " and b.languagetype = 0";
		coursescheduleList = selectCoursescheduleFullByCondition(addedsql);
		
		return coursescheduleList;
		
	}
	
	
	/**
	 * 获取某个课程某学期某周的课程
	 * @param coursescheduleId
	 * @return
	 */
	@Override
	public List<Courseschedule> selectCoursescheduleByPeriod(Integer courseId,Integer semesterId,Integer weeknum,Integer day,Integer period){
		List<Courseschedule> coursescheduleList = new ArrayList<Courseschedule>();
		String addedsql = "   where status = "+TeachConstant.courseschedulevalidstatus+" and courseid = "+courseId+" and semester = "+semesterId+" and week="+weeknum +" and day="+day +" and period="+period;
		coursescheduleList = selectCoursescheduleByCondition(addedsql);
		
		return coursescheduleList;
		
	}
	
	
	// 根据条件查询查询实体
	@Override
	public List<Courseschedule> selectCoursescheduleByCondition(String condition) {
		final String  sql = basicCoursescheduleSql +" " + condition;
		
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
        public List<Courseschedule>  selectCoursescheduleByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicCoursescheduleSql  +conditionSql;
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
	public List<CoursescheduleFull> selectCoursescheduleFullByCondition(String condition) {
		final String  sql = basicCoursescheduleFullSql +" " + condition;
		
		List<CoursescheduleFull> coursescheduleList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CoursescheduleFull.class));
						return query.list();
					}
				});		
		return coursescheduleList;
	}
	
	
	// 查询实体full        
        @Override
        public List<CoursescheduleFull>  selectCoursescheduleFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicCoursescheduleFullSql  +conditionSql;
                List<CoursescheduleFull> coursescheduleList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                CoursescheduleFull.class));
                                                return query.list();
                                        }
                                });
                if (coursescheduleList != null && !coursescheduleList.isEmpty()) {
                        return coursescheduleList;
                }
                return coursescheduleList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer coursescheduleId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Courseschedule as tb set tb.status = 0 where tb.id = " +coursescheduleId; 
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
	public CoursescheduleFull selectCoursescheduleFullById(Integer coursescheduleId) {
		final String  sql =  basicCoursescheduleFullSql + " where a.id = "+coursescheduleId;
		
		List<CoursescheduleFull> coursescheduleList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CoursescheduleFull.class));
						return query.list();
					}
				});
		if (coursescheduleList != null && !coursescheduleList.isEmpty()) {
			return coursescheduleList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<CoursescheduleFull> selectCoursescheduleFullAll() {
		final String  sql = basicCoursescheduleFullSql;
		
		List<CoursescheduleFull> coursescheduleFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CoursescheduleFull.class));
						return query.list();
					}
				});
		if (coursescheduleFullList != null && !coursescheduleFullList.isEmpty()) {			
			return coursescheduleFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<CoursescheduleFull> selectCoursescheduleFullByPage(final Page page){
		final String  sql = basicCoursescheduleFullSql;
		
		List<CoursescheduleFull> coursescheduleFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								CoursescheduleFull.class));
						return query.list();
					}
				});
		if (coursescheduleFullList != null && !coursescheduleFullList.isEmpty()) {			
			return coursescheduleFullList;
		}
		return null;
	}
	
//  根据关联查询实体full
 
}