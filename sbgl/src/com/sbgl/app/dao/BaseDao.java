package com.sbgl.app.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sbgl.common.HQLOption;
import com.sbgl.util.Page;

public interface BaseDao {
	public <T> void saveEntity(T entity);
	public <T> boolean deleteByProperty(String tableName,String propertyName,T propertyValue);
	public <T> void deleteEntity(T entity);
	public <T> void deleteEntityById(java.lang.Class<T> entityClass, Serializable id) ;
	
	public <T> void updateEntity(T entity);
	public <T> List<T> getAllEntity (java.lang.Class<T> entityClass);
	public <T> List<T> executeHQL(String hql);
	public <T> T getEntityById(java.lang.Class<T> entityClass,Serializable id);
	public  Session getCurrentSession();
	public HibernateTemplate getHibernateTemplate();
	//根据列值得到

	public <T> List<T> getEntityByProperty(String tableName,String propertyName, String propertyValue);
	public <T> List<T> getEntityByIntProperty(String tableName,String propertyName, int propertyValue);
	public <T> void createSQL(String sql);
	//获得主键
	public Integer getCode(String codeType);
	
	//判断是否存在
	public <T> Boolean isExist(java.lang.Class<T> entityClass, String propertyName, String propertyValue);
	//根据条件进行分页查询
	public <T> QueryResult getEntityByPageWithOptions(java.lang.Class<T> entityClass, List<HQLOption> hqlOption, Page page);

	public <T> int getRowCount(java.lang.Class<T> entity);
	public <T> List<T> selectByPage(java.lang.Class<T> entityClass, Page page);
}
