package com.sbgl.app.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.equipment.template.EquipmentgroupFull;
import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.app.dao.EquipGroupDao;
import com.sbgl.util.EscColumnToBean;
import com.sbgl.util.Page;

@Repository("equipGroupDao")
public class EquipGroupDaoImpl extends HibernateDaoSupport implements EquipGroupDao {

	@Override
	public List<EquipmentgroupFull> findEquipmentGroup(Page page) {
		// TODO Auto-generated method stub
		final String sql = " select a.* from EquipmentGroup a where a.lanType = '"+CommonConfig.languagechStr+"'" +
			" limit "+(((page.getPageNo()-1)*page.getPageSize()))+","+page.getPageSize();
		try {
			Query query =  this.getCurrentSession().createSQLQuery(sql);
			query.setResultTransformer(new EscColumnToBean(EquipmentgroupFull.class));
			List<EquipmentgroupFull> equipmentgroupFullList =query.list();
			if(equipmentgroupFullList!=null&&!equipmentgroupFullList.isEmpty()){
				return equipmentgroupFullList; 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public  Session getCurrentSession(){
        //TODO Auto-generated method stub
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
	}

	@Override
	public OrderCountFull findCountEquipmentGroup() {
		// TODO Auto-generated method stub
		final String sql = " select (select count(*) from EquipmentGroup where lanType = '"+CommonConfig.languagechStr+"') as orderCount1  from dual  ";
		try {
			Query query =  this.getCurrentSession().createSQLQuery(sql);
			query.setResultTransformer(new EscColumnToBean(OrderCountFull.class));
			List<OrderCountFull> list =query.list();
			if(list!=null&&!list.isEmpty()){
				return list.get(0);  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteequipGroup(String[] temp){ 
		// TODO Auto-generated method stub

		int size = temp.length;
		String wherestr = "";
		for (int i = 0; i < size ; i++){
			if(i==0){
				wherestr += "'"+temp[i]+"'";
			}else{
				wherestr += ",'"+temp[i]+"'";
			}
		}
		final String sql = " delete from EquipmentGroup where comid in ("+wherestr+")";
		final String sql2 = " delete from GroupofEquipment where equipmentgroupid in ("+wherestr+")";
		Query query =  this.getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
		Query query2 =  this.getCurrentSession().createSQLQuery(sql2);
		query2.executeUpdate();
	}

	@Override
	public List<EquipmentgroupFull> findEquipmentGroup(Integer courseruleid) {
		// TODO Auto-generated method stub
		final String sql = " select a.*,b.applynumber from EquipmentGroup a " +
				" left outer join orderCourseRuleDetail b on b.equipmentid = a.equipmentgroupid and b.courseRuleId='"+courseruleid+"' " +
				" where a.lanType = '"+CommonConfig.languagechStr+"'";
		try {
			Query query =  this.getCurrentSession().createSQLQuery(sql);
			query.setResultTransformer(new EscColumnToBean(EquipmentgroupFull.class));
			List<EquipmentgroupFull> equipmentgroupFullList =query.list();
			if(equipmentgroupFullList!=null&&!equipmentgroupFullList.isEmpty()){
				return equipmentgroupFullList; 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
