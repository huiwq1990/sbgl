package com.sbgl.app.services.order.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.OrderFinishDao;
import com.sbgl.app.entity.Equipmenborrow;
import com.sbgl.app.services.order.OrderFinishService;


@Scope("prototype") 
@Service("orderFinishService")
public class OrderFinishServiceImpl  implements OrderFinishService  {

	@Resource
	private OrderFinishDao orderFinishDao;
	
	@Resource
	private BaseDao baseDao;
	
	
	
	public EquipmenborrowFull findEquipmenborrow(Integer borrowId) {
		// TODO Auto-generated method stub
		
		return orderFinishDao.findEquipmenborrow(borrowId);
	} 

	
	public List<EquipmentFull> findListBorrow(Integer borrowId) {
		// TODO Auto-generated method stub
		return orderFinishDao.findListBorrow(borrowId);
	}
	
	public boolean finishorder(Integer borrowId,String equtitle,String equremark) throws Exception{
		Equipmenborrow equipmenborrow = baseDao.getEntityById(Equipmenborrow.class, borrowId);
		equipmenborrow.setTitle(equtitle);
		equipmenborrow.setRemark(equremark);
		baseDao.updateEntity(equipmenborrow);
		return true;
	}

}
