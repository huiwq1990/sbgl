package com.sbgl.app.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.dao.OrderFinishDao;
import com.sbgl.util.EscColumnToBean;

@Repository("orderFinishDao")
public class OrderFinishDaoImpl extends HibernateDaoSupport implements OrderFinishDao {
	
	public EquipmenborrowFull findEquipmenborrow(Integer borrowId){
		
		final String sql = " select a.*,b.name as userName,c.name as teacherName from EquipmenBorrow a left outer join loginUser b on a.userid = b.id "
			+ " left outer join loginUser c on c.id=a.teacherid "
			+ " where b.borrowlistid='"+borrowId+"' ";
		List<EquipmenborrowFull> equipmenborrowFullList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(new EscColumnToBean(EquipmenborrowFull.class));
				return query.list();
			}
		});	
		if(equipmenborrowFullList!=null&&!equipmenborrowFullList.isEmpty()){
			return equipmenborrowFullList.get(0);
		}	
		return null;
	}
	
	public List<EquipmentFull> findListBorrow(Integer borrowId) {
		// TODO Auto-generated method stub
		final String sql = " select a.*,b.applynumber,c.name as categoryName from Equipment a left outer join ListDetail b on a.equipmentid = b.equipmentid "
			+ " left outer join EquipmentClassification c on c.classificationid=a.classificationid "
			+ " where b.borrowlistid='"+borrowId+"' ";
		List<EquipmentFull> equipmentFullList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(equipmentFullList!=null&&!equipmentFullList.isEmpty()){
			return equipmentFullList;
		}	
		return null;
	}
	
}
