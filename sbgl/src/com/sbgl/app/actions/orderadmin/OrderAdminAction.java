package com.sbgl.app.actions.orderadmin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.OrderMainAction;
import com.sbgl.app.services.order.OrderMainService;
import com.sbgl.app.services.orderadmin.OrderAdminService;
import com.sbgl.util.Page;


@Scope("prototype") 
@Controller("OrderAdminAction")
public class OrderAdminAction  extends ActionSupport {
	private static final Log log = LogFactory.getLog(OrderMainAction.class);
	
	@Resource
	private OrderAdminService orderAdminService;
	
	private String dealtype;  //0:全部，1：待审核，2：待出库，3：已出库	
	private String ordertype;
	private String pageNo;
	private Page page;
	private Integer borrowId;
	
	private List<EquipmenborrowFull> equipmenborrowFullList;
	
	private OrderCountFull orderCountFull;
	
	
	public String orderAdmin(){
		if(dealtype==null||dealtype.equals("")){
			dealtype = "0";
		}
		if(ordertype==null||ordertype.equals("")){
			ordertype= "0";
		}
		if(pageNo==null||pageNo.equals("")){
			pageNo = "1";
		}
		
		page = new Page(Integer.parseInt(pageNo),10);
		equipmenborrowFullList = orderAdminService.findEquipmenborrow(dealtype, ordertype, page);
		orderCountFull = orderAdminService.findOrderCount(ordertype);
		if(dealtype.equals("0")){
			page.setTotalCount(orderCountFull.getOrderCount5().intValue());
			int temp = orderCountFull.getOrderCount5().intValue()%page.getPageSize();
			int temp2 = orderCountFull.getOrderCount5().intValue()/page.getPageSize();
			if(temp==0){
				page.setTotalpage(temp2);
			}else{
				page.setTotalpage(temp2+1);
			}
		}else if(dealtype.equals("1")){
			page.setTotalCount(orderCountFull.getOrderCount1().intValue());
			int temp = orderCountFull.getOrderCount1().intValue()%page.getPageSize();
			int temp2 = orderCountFull.getOrderCount1().intValue()/page.getPageSize();
			if(temp==0){
				page.setTotalpage(temp2);
			}else{
				page.setTotalpage(temp2+1);
			}
		}else if(dealtype.equals("2")){
			page.setTotalCount(orderCountFull.getOrderCount2().intValue());
			int temp = orderCountFull.getOrderCount2().intValue()%page.getPageSize();
			int temp2 = orderCountFull.getOrderCount2().intValue()/page.getPageSize();
			if(temp==0){
				page.setTotalpage(temp2);
			}else{
				page.setTotalpage(temp2+1);
			}
		}else if(dealtype.equals("3")){
			page.setTotalCount(orderCountFull.getOrderCount3().intValue());
			int temp = orderCountFull.getOrderCount3().intValue()%page.getPageSize();
			int temp2 = orderCountFull.getOrderCount3().intValue()/page.getPageSize();
			if(temp==0){
				page.setTotalpage(temp2);
			}else{
				page.setTotalpage(temp2+1);
			}
		}
		return SUCCESS;
	}


	public List<EquipmenborrowFull> getEquipmenborrowFullList() {
		return equipmenborrowFullList;
	}


	public void setEquipmenborrowFullList(
			List<EquipmenborrowFull> equipmenborrowFullList) {
		this.equipmenborrowFullList = equipmenborrowFullList;
	}


	public String getDealtype() {
		return dealtype;
	}


	public void setDealtype(String dealtype) {
		this.dealtype = dealtype;
	}


	public String getOrdertype() {
		return ordertype;
	}


	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}


	public String getPageNo() {
		return pageNo;
	}


	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}


	public OrderCountFull getOrderCountFull() {
		return orderCountFull;
	}


	public void setOrderCountFull(OrderCountFull orderCountFull) {
		this.orderCountFull = orderCountFull;
	}


	public Page getPage() {
		return page;
	}


	public void setPage(Page page) {
		this.page = page;
	}


	public Integer getBorrowId() {
		return borrowId;
	}


	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}
	
}
