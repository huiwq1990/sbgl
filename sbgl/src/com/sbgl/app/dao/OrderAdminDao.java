package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.app.actions.orderadmin.OrdercourseruleFull;
import com.sbgl.util.Page;

public interface OrderAdminDao {
	//查找后台课程规则信息
	public List<OrdercourseruleFull> findOrderClassRule(Integer courseId,Page page);
	//查找后台课程规则统计数据
	public OrderCountFull findOrderCountRule(Integer courseId);
	//根据日期获得当天所有订单
	public List<EquipmenborrowFull>  getEquipmenborrowByDate(String date);
}
