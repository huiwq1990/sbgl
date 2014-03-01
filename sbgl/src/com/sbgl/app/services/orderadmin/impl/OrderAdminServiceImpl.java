package com.sbgl.app.services.orderadmin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.app.actions.orderadmin.OrdercourseruleFull;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.OrderAdminDao;
import com.sbgl.app.dao.OrderMainDao;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.services.orderadmin.OrderAdminService;
import com.sbgl.util.Page;


@Scope("prototype") 
@Service("orderAdminService")
@Transactional
public class OrderAdminServiceImpl implements  OrderAdminService{

	@Resource
	private OrderMainDao orderMainDao;
	
	@Resource
	private OrderAdminDao orderAdminDao;
	

	@Resource
	private BaseDao baseDao;
	
	@Override
	public List<EquipmenborrowFull> findEquipmenborrow(String dealtype,
			String ordertype, Page page) {
		// TODO Auto-generated method stub
		
		return orderMainDao.findEquipmenborrow(dealtype,ordertype,page);
	}


	@Override
	public OrderCountFull findOrderCount(String ordertype) {
		// TODO Auto-generated method stub
		return orderMainDao.findOrderCount(ordertype);
	}


	@Override
	public List<OrdercourseruleFull> findOrderClassRule(Integer courseId,
			Page page) {
		// TODO Auto-generated method stub
		return orderAdminDao.findOrderClassRule(courseId,page);
	}


	@Override
	public OrderCountFull findOrderCountRule(Integer courseId) {
		// TODO Auto-generated method stub
		return orderAdminDao.findOrderCountRule(courseId);
	}


	@Override
	public List<Equipmentclassification> findTopEquipmentclass() {
		// TODO Auto-generated method stub
		List<Equipmentclassification> equipmentclassList = baseDao.getEntityByProperty("Equipmentclassification", "parentid", "0");
		return equipmentclassList;
	}


	@Override
	public Map<Integer, List<EquipmentFull>> fingclassequipMap(
			List<Equipmentclassification> eclist) {
		// TODO Auto-generated method stub
		Map<Integer, List<EquipmentFull>> map = new HashMap<Integer, List<EquipmentFull>>();
		for(Equipmentclassification equipmentclassification:eclist){
			List<EquipmentFull> equipmentFullList =  orderMainDao.findEquipmentByClss(equipmentclassification.getClassificationid());
			map.put(equipmentclassification.getClassificationid(), equipmentFullList);
		}
		return map;
	}
	
}
