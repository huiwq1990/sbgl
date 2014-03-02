package com.sbgl.app.services.orderadmin.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.app.actions.orderadmin.OrdercourseruleFull;
import com.sbgl.app.dao.OrderAdminDao;
import com.sbgl.app.dao.OrderMainDao;
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
	
}
