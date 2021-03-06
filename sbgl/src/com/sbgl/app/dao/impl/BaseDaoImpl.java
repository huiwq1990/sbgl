package com.sbgl.app.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.QueryResult;
import com.sbgl.app.entity.Maxno;
import com.sbgl.common.HQLOption;
import com.sbgl.common.SBGLConsistent;
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
	
	public <T> List<T> getEntityByIntProperty(String entityClass, String propertyName, int propertyValue) {
		// TODO Auto-generated method stub
		log.debug("正在查询");
		try {
			String queryString = "from "+entityClass+" as model where model." 
					+ propertyName + "="+propertyValue;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("查询失败", re);
			throw re;
		}
	}
	
	public <T> List<T> executeHQL(String hql) {
		List<T> resultList = getHibernateTemplate().find(hql);
		return resultList;
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
	         Query q = this.getCurrentSession().createQuery(queryString).setFirstResult(page.getPageNo()).setMaxResults(page.getPageSize());
	         
	         List l = q.list(); 
			 return l;
	      } catch (RuntimeException re) {
	         log.error("查询失败", re);
	         throw re;
	      }
	}

	@Override
	public <T> Boolean isExist(Class<T> entityClass, String propertyName, String propertyValue) {
		List<T> l = getEntityByProperty(entityClass.getName(), propertyName, propertyValue);
		if(l != null && l.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public <T> QueryResult getEntityByPageWithOptions(Class<T> entityClass,
			List<HQLOption> hqlOptionList, Page page) {
		List<T> resultList = null;
		String queryString = "from " + entityClass.getName() + " as m where 1=1";
		
		if(hqlOptionList != null && hqlOptionList.size() > 0) {
			for(HQLOption option : hqlOptionList) {
				if(option.getType() == SBGLConsistent.HQL_VALUE_STR) {  //字符串
					switch( option.getOption() ) {
						case SBGLConsistent.HQL_OPTION_EQ:
							if(option.getJoinType() == SBGLConsistent.HQL_OPTION_AD) {
								queryString += " and m." + option.getPropertyName() + "='" + option.getValue() + "'";
							} else if(option.getJoinType() == SBGLConsistent.HQL_OPTION_OR) {
								queryString += " or m." + option.getPropertyName() + "='" + option.getValue() + "'";
							}
							break;
						case SBGLConsistent.HQL_OPTION_LK:
							if(option.getJoinType() == SBGLConsistent.HQL_OPTION_AD) {
								queryString += " and m." + option.getPropertyName() + " like '" + option.getValue() + "%'";
							} else if(option.getJoinType() == SBGLConsistent.HQL_OPTION_OR) {
								queryString += " or m." + option.getPropertyName() + " like '" + option.getValue() + "%'";
							}
							break;
						case SBGLConsistent.HQL_OPTION_IN:
							String[] values = option.getValue().toString().split(",");
							for (int i=0; i<values.length; i++) {
								if(option.getJoinType() == SBGLConsistent.HQL_OPTION_AD) {
									if(i == 0) {
										queryString += " and m." + option.getPropertyName() + " in ('" + values[i] + "',";
									} else if(i == values.length-1) {
										queryString += "'" + values[i] + "')";
									} else {
										queryString += "'" + values[i] + "',";
									}
								}
							}
					}
				} else if(option.getType() == SBGLConsistent.HQL_VALUE_INT) {  //数字
					switch( option.getOption() ) {
						case SBGLConsistent.HQL_OPTION_EQ:
							if(option.getJoinType() == SBGLConsistent.HQL_OPTION_AD) {
								queryString += " and m." + option.getPropertyName() + "=" + option.getValue();
							} else if(option.getJoinType() == SBGLConsistent.HQL_OPTION_OR) {
								queryString += " or m." + option.getPropertyName() + "=" + option.getValue();
							}
							break;
						case SBGLConsistent.HQL_OPTION_GT:
							if(option.getJoinType() == SBGLConsistent.HQL_OPTION_AD) {
								queryString += " and m." + option.getPropertyName() + ">" + option.getValue();
							} else if(option.getJoinType() == SBGLConsistent.HQL_OPTION_OR) {
								queryString += " or m." + option.getPropertyName() + ">" + option.getValue();
							}
							break;
						case SBGLConsistent.HQL_OPTION_LT:
							if(option.getJoinType() == SBGLConsistent.HQL_OPTION_AD) {
								queryString += " and m." + option.getPropertyName() + "<" + option.getValue();
							} else if(option.getJoinType() == SBGLConsistent.HQL_OPTION_OR) {
								queryString += " or m." + option.getPropertyName() + "<" + option.getValue();
							}
							break;
						case SBGLConsistent.HQL_OPTION_IN:
							String[] values = option.getValue().toString().split(",");
							if(values.length == 1) {
								queryString += " and m." + option.getPropertyName() + " in (" + Integer.valueOf(values[0]) + ")";
							} else if(values.length > 1) {
								for (int i=0; i<values.length; i++) {
									if(option.getJoinType() == SBGLConsistent.HQL_OPTION_AD) {
										if(i == 0) {
											queryString += " and m." + option.getPropertyName() + " in (" + Integer.valueOf(values[i]) + ",";
										} else if(i == values.length-1) {
											queryString += Integer.valueOf(values[i]) + ")";
										} else {
											queryString += Integer.valueOf(values[i]) + ",";
										}
									}
								}
							}
					}
				}
			}
		}
		
		final String hql = queryString;
		final int curPageNo = page.getPageNo();
		final int maxSize = page.getPageSize();
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
		    List result = session.createQuery(hql).setFirstResult(curPageNo)
		        .setMaxResults(maxSize)
		        .list();
		    	return result;
		   }
		});
		
		List<T> temp = getHibernateTemplate().find(queryString);
		int n = temp == null ? 0 : temp.size();
		
//		Query q = this.getCurrentSession().createQuery(queryString).setFirstResult(page.getPageNo()).setMaxResults(page.getPageSize());
		resultList = list;
		return new QueryResult(resultList, n);
	}
}
