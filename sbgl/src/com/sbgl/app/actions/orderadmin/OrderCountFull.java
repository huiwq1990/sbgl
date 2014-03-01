package com.sbgl.app.actions.orderadmin;

import com.sbgl.app.dao.DaoAbs;

public class OrderCountFull extends DaoAbs {
	
	private Long orderCount1;   //待审核订单
	private Long orderCount2;   //待出库订单
	private Long orderCount3;   //待入库订单
	private Long orderCount4;	//已完成订单数量
	private Long orderCount5;	//订单总数量，
	
	public Long getOrderCount1() {
		return orderCount1;
	}
	public void setOrderCount1(Long orderCount1) {
		this.orderCount1 = orderCount1;
	}
	public Long getOrderCount2() {
		return orderCount2;
	}
	public void setOrderCount2(Long orderCount2) {
		this.orderCount2 = orderCount2;
	}
	public Long getOrderCount3() {
		return orderCount3;
	}
	public void setOrderCount3(Long orderCount3) {
		this.orderCount3 = orderCount3;
	}
	public Long getOrderCount4() {
		return orderCount4;
	}
	public void setOrderCount4(Long orderCount4) {
		this.orderCount4 = orderCount4;
	}
	public Long getOrderCount5() {
		return orderCount5;
	}
	public void setOrderCount5(Long orderCount5) {
		this.orderCount5 = orderCount5;
	}

}
