package com.sbgl.app.services.order.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.OrderFinishDao;
import com.sbgl.app.entity.Equipmenborrow;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.services.order.OrderExamService;

@Scope("prototype") 
@Service("orderExamService")
@Transactional
public class OrderExamServiceImpl implements OrderExamService {

	@Resource
	private BaseDao baseDao;
	
	@Resource
	private OrderFinishDao orderFinishDao;
	
	@Override
	public boolean examorder(Integer borrowId, Integer examstate,String examcontent,Loginuser user) {
		// TODO Auto-generated method stub
		Equipmenborrow equipmenborrow = baseDao.getEntityById(Equipmenborrow.class, borrowId);
		if(examstate==4){
			equipmenborrow.setExamstate("1");
			equipmenborrow.setTeachersuggest("审核通过");
		}else{
			equipmenborrow.setExamstate("2");
		}
		equipmenborrow.setStatus(examstate);
		equipmenborrow.setExamdate(new Date());
		equipmenborrow.setExamuser(user.getId());
		equipmenborrow.setTeachersuggest(examcontent);
		baseDao.updateEntity(equipmenborrow);
		return true;
	}

	@Override
	public List<Equipmentclassification> findclassList(Integer borrowId) {
		// TODO Auto-generated method stub	
		return orderFinishDao.findclassList(borrowId); 
	}

	@Override
	public Map<Integer, List<EquipmentFull>> findMapBorrow(Integer borrowId) {
		// TODO Auto-generated method stub
		return orderFinishDao.findMapBorrow(borrowId);
	}



}
