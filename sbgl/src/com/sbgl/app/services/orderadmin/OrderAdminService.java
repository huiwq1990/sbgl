package com.sbgl.app.services.orderadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.app.actions.orderadmin.OrdercourseruleFull;
import com.sbgl.app.entity.Clazz;
import com.sbgl.app.entity.Course;
import com.sbgl.app.entity.CourseFull;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentnum;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.entity.Ordercourserule;
import com.sbgl.app.entity.Ordercourseruledetail;
import com.sbgl.util.Page;

public interface OrderAdminService {
	//查找后台预约信息
	public List<EquipmenborrowFull> findEquipmenborrow(String dealtype,String ordertype,Page page);
	//查找后台订单统计数据
	public OrderCountFull findOrderCount(String ordertype);
	//查找后台课程规则信息
	public List<OrdercourseruleFull> findOrderClassRule(Integer courseId,Page page);
	//查找后台课程规则统计数据
	public OrderCountFull findOrderCountRule(Integer courseId);
	//获得一级器材分类
	public List<Equipmentclassification> findTopEquipmentclass(String lantype);
	//获得一级分类下的器材
	public Map<Integer,List<EquipmentFull>> fingclassequipMap(List<Equipmentclassification> eclist,Integer courseruleid);
	//创建规则
	public boolean addorderclassrule(Integer courseId,String ruleName,String ids,Loginuser loginuser,Integer courseruleid);
	//批量删除预约
	public boolean deleteBorrow(String[] ids);
	//获得规则
	public Ordercourserule findOrdercourserule(Integer courseruleid);
	//删除规则
	public boolean delequipclassrule(Integer courseruleid);
	//根据课程分组找课程规则
	public HashMap<Integer, ArrayList<Ordercourserule>> findCourseRule(List<CourseFull> courseFullList);
	//找到所有课程
	public List<Clazz>  findAllClazz();
	//发送规则
	public boolean sendRule(String inputSendTo,String inputEquipRule,String inputMsgTitle,String inputContent,String inputDataRange,String inputDataRange2,Loginuser loginuser);
	//根据日期获得当天所有订单
	public List<EquipmenborrowFull>  getEquipmenborrowByDate(String date);
}
