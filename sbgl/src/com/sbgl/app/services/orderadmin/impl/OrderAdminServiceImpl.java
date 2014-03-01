package com.sbgl.app.services.orderadmin.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.app.dao.OrderMainDao;
import com.sbgl.app.services.orderadmin.OrderAdminService;
import com.sbgl.util.Page;


@Scope("prototype") 
@Service("orderAdminService")
@Transactional
public class OrderAdminServiceImpl implements  OrderAdminService{

	@Resource
	private OrderMainDao orderMainDao;
	
	
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
	
}
