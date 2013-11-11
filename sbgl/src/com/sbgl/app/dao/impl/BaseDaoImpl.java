package com.sbgl.app.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Maxno;
import com.sbgl.util.Page;

@Repository("baseDao")
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {
	
	private static final Log log = LogFactory.getLog(BaseDaoImpl.class);

	public <T> void deleteEntity(T entity) {
		// TODO Auto-generated method stub		
		log.debug("正在删除");
        try {
        	this.getHibernateTemplate().delete(entity);
            log.debug("删除成功");
        } catch (RuntimeException re) {
            log.error("删除失败", re);
            throw re;
        }

	}

	public <T> void deleteEntityById(java.lang.Class<T> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		log.debug("正在删除");
        try {
        	this.getHibernateTemplate().delete(entityClass.getName(), id);
            log.debug("删除成功");
        } catch (RuntimeException re) {
            log.error("删除失败", re);
            throw re;
        }

	}

	public <T> void saveEntity(T entity) {
		// TODO Auto-generated method stub	
		log.debug("正在保存");
        try {
        	this.getHibernateTemplate().save(entity);	
            log.debug("保存成功");
        } catch (RuntimeException re) {
            log.error("保存失败", re);
            throw re;
        }

	}

	public <T> void updateEntity(T entity) {
		// TODO Auto-generated method stub		
		log.debug("正在更新");
        try {
        	this.getHibernateTemplate().saveOrUpdate(entity);
            log.debug("更新成功");
        } catch (RuntimeException re) {
            log.error("更新失败", re);
            throw re;
        }

	}
	
	public <T> List<T> getAllEntity (java.lang.Class<T> entityClass){
        //TODO Auto-generated method stub		
		log.debug("正在查询");
        try {
        	return this.getHibernateTemplate().find("from "+entityClass.getName());
            
        } catch (RuntimeException re) {
            log.error("查询失败", re);
            throw re;
            
        }
		
	}
	
	public <T> T getEntityById(java.lang.Class<T> entityClass,Serializable id){
        //TODO Auto-generated method stub		
		log.debug("正在查询");
        try {
        	return this.getHibernateTemplate().get(entityClass,id);
            
        } catch (RuntimeException re) {
            log.error("查询失败", re);
            throw re;
            
        }
		
	}
	
	/*public  List getEntityBySql(String sql){
        //TODO Auto-generated method stub
		return this.getHibernateTemplate().find(sql);
		
	}*/
	
	public  Session getNewSession(){
        //TODO Auto-generated method stub
		return this.getSession();
		
	}
	
	public  Session getCurrentSession(){
        //TODO Auto-generated method stub
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
	}

	
	public <T> boolean deleteByProperty(String tableName, String propertyName,
			T propertyValue) {
		log.debug("正在查询");
        try {
        	String sql = "delete from "+tableName+ " where " +propertyName+ " = '"+propertyValue+"'";
    		Session session = this.getCurrentSession();
    		Query query = session.createSQLQuery(sql);
    		query.executeUpdate();
    		return true;
        } catch (RuntimeException re) {
            log.error("查询失败", re);           
            throw re;
            
        }
        
		// TODO Auto-generated method stub
		
	}

	public <T> List<T> getEntityByProperty(String entityClass, String propertyName, String propertyValue) {
		// TODO Auto-generated method stub
		log.debug("正在查询");
		try {
	         String queryString = "from "+entityClass+" as model where model." 
	         						+ propertyName + "= '"+propertyValue+"'";
			 return getHibernateTemplate().find(queryString);
	      } catch (RuntimeException re) {
	         log.error("查询失败", re);
	         throw re;
	      }
	}
	
	
	public <T> void createSQL(String sql) {
		// TODO Auto-generated method stub
		log.debug("正在执行sql");
		try {
	         Session session = this.getCurrentSession();
	         Query query = session.createSQLQuery(sql);
			 query.executeUpdate();
      } catch (RuntimeException re) {
         log.error("执行sql失败", re);
         throw re;
      }
	}

	@Override
	public Integer getCode(String codeType) {
		// TODO Auto-generated method stub
		Maxno maxno = new Maxno();
		Integer reCode;
		maxno = this.getHibernateTemplate().get(Maxno.class,codeType);
		if(maxno==null){
			maxno = new  Maxno();
			maxno.setMaxno(1);
			maxno.setNotype(codeType);
			this.getHibernateTemplate().save(maxno);
			reCode = maxno.getMaxno();		
		}else{
			Integer sum = maxno.getMaxno();
			sum++;
			maxno.setMaxno(sum);
			this.getHibernateTemplate().saveOrUpdate(maxno);
			reCode = maxno.getMaxno();
		}
		return reCode;
	}
	
	  /***
	   * 返回共有多少记录
	   */
	@Override
	public <T> int getRowCount(java.lang.Class<T> entityClass){
      //TODO Auto-generated method stub		
		log.debug("正在查询");
		List list = new ArrayList();
      try {
      	 list = this.getCurrentSession().createQuery("from "+entityClass.getName()+" as model ").list();        
      	
      } catch (RuntimeException re) {
          log.error("查询失败", re);
          throw re;
          
      }finally{
      	return list.size();
      }		
	}
	
	
	public <T> List<T> selectByPage(java.lang.Class<T> entityClass, Page page) {
		// TODO Auto-generated method stub
		log.debug("正在查询");
		List list = new ArrayList();
//		设置表记录数目
		page.setTotalCount(getRowCount(entityClass));
		try {
	        String queryString = "from "+entityClass.getName()+" as a ";
	         Query q = this.getCurrentSession().createQuery(queryString).setFirstResult(page.getStartNum()).setMaxResults(page.getPageSize());
	         
	         List l = q.list(); 
			 return l;
	      } catch (RuntimeException re) {
	         log.error("查询失败", re);
	         throw re;
	      }
	}
}
