package com.sbgl.app.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.app.actions.orderadmin.OrdercourseruleFull;
import com.sbgl.app.dao.OrderAdminDao;
import com.sbgl.util.EscColumnToBean;
import com.sbgl.util.Page;

@Repository("orderAdminDao")
public class OrderAdminDaoImpl extends HibernateDaoSupport implements OrderAdminDao  {

	@Override
	public List<OrdercourseruleFull> findOrderClassRule(Integer courseId,
			Page page) {
		// TODO Auto-generated method stub
		
		String sql2 = " select a.*,b.name as teacherName,(select name from Course where languagetype=0  and   id in (select id from Course where coursetype in (select coursetype from Course where id = a.courseId))) as courseName from orderCourseRule a left outer join LoginUser b on teacherid=b.id ";
		if(courseId!=0){
			sql2+=" where a.courseId in (select id from Course where coursetype = '"+courseId+"')";
		}
		sql2+= " limit "+(((page.getPageNo()-1)*page.getPageSize()))+","+page.getPageSize();
		final String sql = sql2;
		try {
			Query query =  this.getCurrentSession().createSQLQuery(sql);
			query.setResultTransformer(new EscColumnToBean(OrdercourseruleFull.class));
			List<OrdercourseruleFull> ordercourseruleFullList =query.list();
			if(ordercourseruleFullList!=null&&!ordercourseruleFullList.isEmpty()){
				return ordercourseruleFullList; 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public OrderCountFull findOrderCountRule(Integer courseId) {
		// TODO Auto-generated method stub
		String sql2 = " select (select count(*) from orderCourseRule a ";
		if(courseId!=0){
			sql2+=" where a.courseId in (select id from Course where coursetype in (select coursetype from Course where id = '"+courseId+"'))";
		}
		sql2+=" ) as orderCount1 from dual  ";
		final String sql = sql2;
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
	
	public  Session getCurrentSession(){
        //TODO Auto-generated method stub
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
	}

	@Override
	public List<EquipmenborrowFull> getEquipmenborrowByDate(String date) {
		// TODO Auto-generated method stub
		String sql2 = " select title,borrowid,status from equipmenborrow where date_format(borrowtime,'%Y-%m-%d') <='"+date+"' and  date_format(returntime,'%Y-%m-%d') >= '"+date+"'";
		final String sql = sql2;
		try {
			Query query =  this.getCurrentSession().createSQLQuery(sql);
			query.setResultTransformer(new EscColumnToBean(EquipmenborrowFull.class));
			List<EquipmenborrowFull> list =query.list();
			if(list!=null&&!list.isEmpty()){
				return list;  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return null;
	}

}
