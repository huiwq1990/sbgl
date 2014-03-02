package com.sbgl.app.services.orderadmin;

import java.util.List;
import java.util.Map;

import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.app.actions.orderadmin.OrdercourseruleFull;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentnum;
import com.sbgl.app.entity.Loginuser;
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
	public Map<Integer,List<EquipmentFull>> fingclassequipMap(List<Equipmentclassification> eclist);
	//创建规则
	public boolean addorderclassrule(Integer courseId,String ruleName,String ids,Loginuser loginuser);
}
