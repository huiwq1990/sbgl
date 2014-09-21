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
import com.sbgl.app.entity.Equipmentdetail;
import com.sbgl.app.entity.Listdetail;
import com.sbgl.app.entity.Listequipdetail;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.services.order.OrderExamService;

@Scope("prototype") 
@Service("orderExamService")
@Transactional(rollbackFor=Exception.class)
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
	public List<Equipmentclassification> findclassList(Integer borrowId,String lantype) {
		// TODO Auto-generated method stub	
		return orderFinishDao.findclassList(borrowId,lantype); 
	}

	@Override
	public Map<Integer, List<EquipmentFull>> findMapBorrow(Integer borrowId,Integer type,String lantype) {
		// TODO Auto-generated method stub
		return orderFinishDao.findMapBorrow(borrowId,type,lantype);
	}

	@Override
	public boolean alibraryorder(Integer borrowId,
			String ids, Loginuser user) {
		// TODO Auto-generated method stub
		String[] strs =  ids.split(",");
		Equipmenborrow equipmenborrow = baseDao.getEntityById(Equipmenborrow.class, borrowId);
		equipmenborrow.setStatus(5);
		equipmenborrow.setBorrowaudituser(user.getId());
		equipmenborrow.setBorrowtime(new Date());
		baseDao.updateEntity(equipmenborrow);		
		for(int i=0;i<strs.length;i++){
			if(strs[i]!=null&&!"".equals(strs[i])){
				Equipmentdetail equipmentdetail = baseDao.getEntityById(Equipmentdetail.class, Integer.parseInt(strs[i]));
				Listdetail listdetail = new Listdetail();
				listdetail =  orderFinishDao.findListDetail(borrowId,Integer.parseInt(strs[i]));
				if(listdetail.getBorrownumber()==null||listdetail.getBorrownumber()==0){
					listdetail.setBorrownumber(1);
				}else{
					listdetail.setBorrownumber(listdetail.getBorrownumber()+1);
				}
				listdetail.setBorrowtime(equipmenborrow.getBorrowtime());
				baseDao.updateEntity(listdetail);
				Listequipdetail listequipdetail =  new Listequipdetail();
				listequipdetail.setBorrowlistid(borrowId);
				listequipdetail.setListequipdetailid(baseDao.getCode("Listequipdetail")); 
				listequipdetail.setEquipdetailid(Integer.parseInt(strs[i]));
				listequipdetail.setEquipmentid(equipmentdetail.getEquipmentid());
				listequipdetail.setEquipstatus("0");
				listequipdetail.setListdetailid(listdetail.getListdetailid());	
				baseDao.saveEntity(listequipdetail);
			}
		}	
		return true;
	}

	@Override
	public boolean storageorder(Integer borrowId, String ids, Loginuser user) throws Exception { 
		// TODO Auto-generated method stub
		String[] strs1 =  ids.split(",");
		Equipmenborrow equipmenborrow = baseDao.getEntityById(Equipmenborrow.class, borrowId);
		equipmenborrow.setReturnaudituser(user.getId());	
		equipmenborrow.setReturntime(new Date());
		if(strs1!=null&&strs1.length>0){
			for(int i=0;i<strs1.length;i++){
				String[] strs2 = strs1[i].split("、");			
				Listdetail listdetail = new Listdetail();
				listdetail =  orderFinishDao.findListDetail(borrowId,Integer.parseInt(strs2[0]));
				listdetail.setBorrownumber(listdetail.getBorrownumber()-1);
				listdetail.setReturntime(equipmenborrow.getReturntime());
				baseDao.updateEntity(listdetail);
				Listequipdetail listequipdetail =  new Listequipdetail();
				listequipdetail = orderFinishDao.findlistequipdetail(borrowId,Integer.parseInt(strs2[0]));
				listequipdetail.setEquipstatus(strs2[1]);					
				baseDao.updateEntity(listequipdetail);
				if(strs2[1]!=null&&!"4".equals(strs2[1])){
					orderFinishDao.updateEquipmenNum(strs2[1], listdetail.getEquipmentid(),listequipdetail.getEquipdetailid());
				}
			}
		}
		equipmenborrow.setStatus(8);
		baseDao.updateEntity(equipmenborrow);
		return true;
	}

	//用户详情
	public Loginuser userdetail(Integer userId){
		return orderFinishDao.userdetail(userId);
	}
}
