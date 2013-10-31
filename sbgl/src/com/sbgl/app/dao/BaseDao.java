package com.sbgl.app.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sbgl.util.Page;

public interface BaseDao {
	public <T> void saveEntity(T entity);
	public <T> boolean deleteByProperty(String tableName,String propertyName,T propertyValue);
	public <T> void deleteEntity(T entity);
	public <T> void updateEntity(T entity);
	public <T> List<T> getAllEntity (java.lang.Class<T> entityClass);
	public <T> T getEntityById(java.lang.Class<T> entityClass,Serializable id);
	public  Session getCurrentSession();
	public HibernateTemplate getHibernateTemplate();
	//根据列值得到
	public <T> List<T> getEntityByProperty(String tableName,String propertyName, String propertyValue);
	public <T> void createSQL(String sql);
	//获得主键
	public Integer getCode(String codeType);


	public <T> int getRowCount(java.lang.Class<T> entity);
	public <T> List<T> selectByPage(java.lang.Class<T> entityClass, Page page);
}
