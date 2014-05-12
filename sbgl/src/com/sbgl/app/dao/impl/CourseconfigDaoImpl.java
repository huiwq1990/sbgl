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

import com.sbgl.app.dao.CourseconfigDao;
import com.sbgl.app.entity.Courseconfig;
import com.sbgl.app.entity.CourseconfigFull;
import com.sbgl.common.DataError;
import com.sbgl.util.*;

@Repository("courseconfigDao")
public class CourseconfigDaoImpl extends HibernateDaoSupport implements CourseconfigDao{

	private static final Log log = LogFactory.getLog(CourseconfigDaoImpl.class);
	private final String basicCourseconfigFullSql = "select a.id as courseconfigid, a.schoolyear as courseconfigschoolyear, a.semester as courseconfigsemester, a.firstday as courseconfigfirstday, a.lastday as courseconfiglastday, a.firstweekfirstday as courseconfigfirstweekfirstday, a.status as courseconfigstatus from Courseconfig a  ";
	
	private final String basicCourseconfigSql = "From Courseconfig as a ";
	
	
	
	/**
	 * 如果存在当前学期则返回，否则为null
	 */
	@Override
	public Courseconfig getCurrentCourseconfig( ) throws DataError{		
		
		String sql = " where currentsemester =" +TeachConstant.coursesconfigcurrentsemester+ " and status >= 0 ";
		List<Courseconfig> list =  selectCourseconfigByCondition(sql);
//		System.out.println(list);
//		System.out.println(list.get(0));
		if(list == null || list.size()==0){
			return null;
		}
		
		if(list!=null && list.size()!=1){
			throw new DataError("学期信息不唯一，获取学期信息出错");
		}
		
		return list.get(0);
	}
	
	@Override
	public List<Courseconfig> selAll( ) {
		return selectCourseconfigByCondition(" where status >=0 ");
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Courseconfig> selectCourseconfigByCondition(String condition) {
		final String  sql = basicCourseconfigSql +" " + condition;
		
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
        public List<Courseconfig>  selectCourseconfigByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicCourseconfigSql  +conditionSql;
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
	public List<CourseconfigFull> selectCourseconfigFullByCondition(String condition) {
		final String  sql = basicCourseconfigFullSql +" " + condition;
		
		List<CourseconfigFull> courseconfigList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CourseconfigFull.class));
						return query.list();
					}
				});		
		return courseconfigList;
	}
	
	
	// 查询实体full        
        @Override
        public List<CourseconfigFull>  selectCourseconfigFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicCourseconfigFullSql  +conditionSql;
                List<CourseconfigFull> courseconfigList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                CourseconfigFull.class));
                                                return query.list();
                                        }
                                });
                if (courseconfigList != null && !courseconfigList.isEmpty()) {
                        return courseconfigList;
                }
                return courseconfigList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer courseconfigId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Courseconfig as tb set tb.status = 0 where tb.id = " +courseconfigId; 
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
	public CourseconfigFull selectCourseconfigFullById(Integer courseconfigId) {
		final String  sql =  basicCourseconfigFullSql + " where a.id = "+courseconfigId;
		
		List<CourseconfigFull> courseconfigList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CourseconfigFull.class));
						return query.list();
					}
				});
		if (courseconfigList != null && !courseconfigList.isEmpty()) {
			return courseconfigList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<CourseconfigFull> selectCourseconfigFullAll() {
		final String  sql = basicCourseconfigFullSql;
		
		List<CourseconfigFull> courseconfigFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								CourseconfigFull.class));
						return query.list();
					}
				});
		if (courseconfigFullList != null && !courseconfigFullList.isEmpty()) {			
			return courseconfigFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<CourseconfigFull> selectCourseconfigFullByPage(final Page page){
		final String  sql = basicCourseconfigFullSql;
		
		List<CourseconfigFull> courseconfigFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								CourseconfigFull.class));
						return query.list();
					}
				});
		if (courseconfigFullList != null && !courseconfigFullList.isEmpty()) {			
			return courseconfigFullList;
		}
		return null;
	}
	
//  根据关联查询实体full
 
}