package com.sbgl.app.services.orderadmin.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.sbgl.app.entity.Clazz;
import com.sbgl.app.entity.Course;
import com.sbgl.app.entity.CourseFull;
import com.sbgl.app.entity.Equipmenborrow;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.entity.Ordercourserule;
import com.sbgl.app.entity.Ordercourseruledetail;
import com.sbgl.app.entity.Sendruletouser;
import com.sbgl.app.services.orderadmin.OrderAdminService;
import com.sbgl.util.DateUtil;
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
	public List<Equipmentclassification> findTopEquipmentclass(String lantype) {
		// TODO Auto-generated method stub
		List<Equipmentclassification> equipmentclassList =orderMainDao.findTopEquipmentclass(lantype);
		return equipmentclassList;
	}


	@Override
	public Map<Integer, List<EquipmentFull>> fingclassequipMap(
			List<Equipmentclassification> eclist,Integer courseruleid) {
		// TODO Auto-generated method stub
		Map<Integer, List<EquipmentFull>> map = new HashMap<Integer, List<EquipmentFull>>();
		for(Equipmentclassification equipmentclassification:eclist){
			List<EquipmentFull> equipmentFullList =  orderMainDao.findEquipmentByClss2(equipmentclassification.getClassificationid(),courseruleid);
			map.put(equipmentclassification.getClassificationid(), equipmentFullList);
		}
		return map;
	}


	@Override
	public boolean addorderclassrule(Integer courseId, String ruleName,
			String ids, Loginuser loginuser,Integer courseruleid) {
		// TODO Auto-generated method stub
		Ordercourserule ordercourserule = new Ordercourserule();
		if(courseruleid!=null){
			ordercourserule = baseDao.getEntityById(Ordercourserule.class, courseruleid);
			baseDao.deleteByProperty("Ordercourseruledetail", "courseruleid", courseruleid);
		}else{
			ordercourserule.setCourseruleid(baseDao.getCode("Ordercourserule"));
		}
		ordercourserule.setCourseid(courseId);
		ordercourserule.setCourserulename(ruleName);
		ordercourserule.setCreatetime(new Date());
		ordercourserule.setTeacherid(loginuser.getId());
		baseDao.updateEntity(ordercourserule);
		String[] str = ids.split(",");
		for(int i=0;i<str.length;i++){
			String[] str2 = str[i].split("_");
			Equipment equipment = baseDao.getEntityById(Equipment.class, Integer.parseInt(str2[0]));  
			Ordercourseruledetail ordercourseruledetail = new Ordercourseruledetail();
			ordercourseruledetail.setCourseruleid(ordercourserule.getCourseruleid());
			ordercourseruledetail.setApplynumber(Integer.parseInt(str2[1]));
			ordercourseruledetail.setCourseruledetailid(baseDao.getCode("Ordercourseruledetail"));
			ordercourseruledetail.setComid(equipment.getComid());
			ordercourseruledetail.setLantype(equipment.getLantype());
			ordercourseruledetail.setEquipmentid(equipment.getEquipmentid());
			baseDao.saveEntity(ordercourseruledetail);
		}
		
		return true;
	}


	@Override
	public boolean deleteBorrow(String[] ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.length;i++){
			baseDao.deleteByProperty("EquipmenBorrow", "borrowid", ids[i]);
			baseDao.deleteByProperty("ListDetail", "borrowlistid", ids[i]);
		}
		return true;
	}
	
	public Ordercourserule findOrdercourserule(Integer courseruleid){
		return baseDao.getEntityById(Ordercourserule.class, courseruleid);
	}
	
	public boolean delequipclassrule(Integer courseruleid){
		return baseDao.deleteByProperty("Ordercourserule", "courseruleid", courseruleid);
	}
	
	public HashMap<Integer, ArrayList<Ordercourserule>> findCourseRule(List<CourseFull> courseFullList){
		HashMap<Integer, ArrayList<Ordercourserule>> map = new HashMap<Integer, ArrayList<Ordercourserule>>();
		int size = courseFullList.size();
		for(int i=0;i<size;i++){
			CourseFull Course = courseFullList.get(i);
			ArrayList<Ordercourserule> list = new ArrayList<Ordercourserule>();
			list = new ArrayList(baseDao.getEntityByProperty("Ordercourserule", "courseid", Course.getCourseid().toString()));
			map.put(Course.getCourseid(), list);
		}
		return map;
	}
	
	public List<Clazz>  findAllClazz(){
		return baseDao.getAllEntity(Clazz.class);
	}
	
	public boolean sendRule(String inputSendTo,String inputEquipRule,String inputMsgTitle,String inputContent,String inputDataRange,String inputDataRange2,Loginuser loginuser){
		
		Sendruletouser sendruletouser = new Sendruletouser();
		sendruletouser.setSendruleid(baseDao.getCode("sendruletouser")); 
		sendruletouser.setCourseruleid(Integer.parseInt(inputEquipRule)); 
		sendruletouser.setMsgtitle(inputMsgTitle);
		sendruletouser.setContent(inputContent);
		sendruletouser.setStartdate(DateUtil.parseDate(inputDataRange));
		sendruletouser.setEnddate(DateUtil.parseDate(inputDataRange2));
		sendruletouser.setCreatetime(new Date());
		sendruletouser.setOperate(loginuser.getId());
		baseDao.saveEntity(sendruletouser);
		String[] str = inputSendTo.split(",");
		int size = str.length - 1;
		for(int i=0;i<size;i++){
			Equipmenborrow equipmenBorrow = new Equipmenborrow();
			equipmenBorrow.setBorrowid(baseDao.getCode("equipmenborrow"));
			equipmenBorrow.setCategory(2);
			equipmenBorrow.setUserid(Integer.parseInt(str[i]));
			equipmenBorrow.setTeacherid(loginuser.getId());
			equipmenBorrow.setStatus(9);
			equipmenBorrow.setSendruleid(sendruletouser.getSendruleid());
			equipmenBorrow.setCourseruleid(sendruletouser.getCourseruleid());
			baseDao.saveEntity(equipmenBorrow);
		}
		return true;
	}
	
}
