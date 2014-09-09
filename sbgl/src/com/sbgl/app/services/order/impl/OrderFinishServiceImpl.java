package com.sbgl.app.services.order.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.OrderFinishDao;
import com.sbgl.app.dao.OrderMainDao;
import com.sbgl.app.entity.Equipmenborrow;
import com.sbgl.app.entity.Listdetail;
import com.sbgl.app.services.order.OrderFinishService;
import com.sbgl.util.DateUtil;


@Scope("prototype") 
@Service("orderFinishService")
@Transactional
public class OrderFinishServiceImpl  implements OrderFinishService  {

	@Resource
	private OrderFinishDao orderFinishDao;
	

	@Resource
	private OrderMainDao orderMainDao;
	
	@Resource
	private BaseDao baseDao;
	
	
	
	public EquipmenborrowFull findEquipmenborrow(Integer borrowId) {
		// TODO Auto-generated method stub
		
		return orderFinishDao.findEquipmenborrow(borrowId);
	} 

	
	public List<EquipmentFull> findListBorrow(Integer borrowId,String lantype) {
		// TODO Auto-generated method stub
		return orderFinishDao.findListBorrow(borrowId,lantype);
	}
	
	public boolean finishorder(Integer borrowId,String equtitle,String equremark,String lantype) throws Exception{
		Equipmenborrow equipmenborrow = baseDao.getEntityById(Equipmenborrow.class, borrowId);
		List<Listdetail> listdetails = orderFinishDao.quarylistdetails(borrowId);
		for(Listdetail listdetail:listdetails){
			boolean flag = orderFinishDao.ifborrow(listdetail.getComid(), DateUtil.getDateDay(equipmenborrow.getBorrowtime()), DateUtil.getDateDay(equipmenborrow.getReturntime()), listdetail.getApplynumber());
			if(!flag){
				throw new Exception("设备数量不足");
			}
		}
		
		if(equtitle==null||equtitle.equals("")){
			if(lantype.equals(CommonConfig.languagechStr)){
				equipmenborrow.setTitle(DateUtil.getChineseTime(equipmenborrow.getBorrowtime())+"-设备预约");
			}else{
				equipmenborrow.setTitle(DateUtil.getEnglishTime(equipmenborrow.getBorrowtime())+"-Equipment Booking");	
			}
		}else{
			equipmenborrow.setTitle(equtitle);
		}
		equipmenborrow.setRemark(equremark);
		equipmenborrow.setStatus(2);
		baseDao.updateEntity(equipmenborrow);
		return true;
	}
	
	public EquipmentFull findEquipmentById(Integer equipmentId,String lantype) {
		// TODO Auto-generated method stub
		return orderFinishDao.findEquipmentById(equipmentId,lantype);
	}


	@Override
	public boolean deleteorder(Integer borrowId) {
		// TODO Auto-generated method stub
		baseDao.deleteByProperty("EquipmenBorrow", "borrowId", borrowId);
		baseDao.deleteByProperty("ListDetail", "borrowlistid", borrowId);
		return true;
	}
	
	public boolean subexamorder(Integer borrowId) {
		// TODO Auto-generated method stub
		Equipmenborrow equipmenBorrow = baseDao.getEntityById(Equipmenborrow.class, borrowId);
		equipmenBorrow.setStatus(2);
		baseDao.updateEntity(equipmenBorrow);
		return true;
	}


	@Override
	public void delay() {
		// TODO Auto-generated method stub
		orderFinishDao.delay();
	}

}
