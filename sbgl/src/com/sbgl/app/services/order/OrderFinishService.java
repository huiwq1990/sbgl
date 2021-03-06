package com.sbgl.app.services.order;

import java.util.List;
import java.util.Map;

import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.entity.Equipmenborrow;

public interface OrderFinishService {
	//根据id查找订单
	public EquipmenborrowFull findEquipmenborrow(Integer borrowId);
	//根据id查找订单，获得订单设备详情
	public List<EquipmentFull> findListBorrow(Integer borrowId,String lantype);
	//提交订单备注
    public boolean finishorder(Integer borrowId,String equtitle,String equremark,String lantype) throws Exception;
    //获得设备详情
	public EquipmentFull findEquipmentById(Integer equipmentId,String lantype);
	//删除订单
	public boolean deleteorder(Integer borrowId);
	//提交订单审核 
	public boolean subexamorder(Integer borrowId);
	//判断归还时间到期是否归还
	public void delay();
	//根据订单号，查询显示订单号
	public String queryBorrowallId(Integer borrowId);
	//根据订单号，订单中设备信息(用于下载)
	public List<EquipmentFull> queryEqumentBorrowallId(Integer borrowId,String lantype);
}
