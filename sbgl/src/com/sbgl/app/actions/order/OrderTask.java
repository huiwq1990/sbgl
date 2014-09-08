package com.sbgl.app.actions.order;

import javax.annotation.Resource;

import com.sbgl.app.services.order.OrderFinishService;

public class OrderTask {

	@Resource
	private OrderFinishService orderFinishService;
	
	public void run(){
		System.out.println("asddd"); 
		orderFinishService.delay();
	}
}
