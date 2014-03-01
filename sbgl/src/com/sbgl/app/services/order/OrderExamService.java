package com.sbgl.app.services.order;

import java.util.List;
import java.util.Map;

import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Loginuser;

public interface OrderExamService {
	//审核订单
	public boolean examorder(Integer borrowId,Integer examstate,String examcontent,Loginuser user);
	//获得订单设备的分类
	public List<Equipmentclassification> findclassList(Integer borrowId);
	//获得商品详情信息，根据分类获得
	public Map<Integer,List<EquipmentFull>> findMapBorrow(Integer borrowId);
	
}
