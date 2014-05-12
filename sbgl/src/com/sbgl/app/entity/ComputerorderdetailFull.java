package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputerorderdetailFull extends DaoAbs implements java.io.Serializable {
			private Integer computerorderdetailid;
			private Integer computerorderdetailcomputerorderid;
			private Integer computerorderdetailcomputermodelid;
			private Integer computerorderdetailborrownumber;
			private Date computerorderdetailcreatetime;
			private Date computerorderdetailborrowday;
			private Integer computerorderdetailborrowperiod;
			private String computerorderdetailcomputerid;
			private Integer computerorderdetailstatus;
			private Integer computerorderid;
			private String computerorderserialnumber;
			private Integer computerordercreateuserid;
			private String computerordertitle;
			private Integer computerorderordertype;
			private Date computerordercreatetime;
			private String computerorderremark;
			private Integer computerorderstatus;
			private Integer computermodelid;
			private Integer computermodelcomputermodeltype;
			private String computermodellanguagetype;
			private String computermodelname;
			private Integer computermodelcomputercategoryid;
			private String computermodelpicpath;
			private Date computermodelcreatetime;
			private Integer computermodelcreateuserid;
			private Integer computermodelcomputercount;
			private Integer computermodelavailableborrowcountnumber;
			private String computermodeldescription;
			private Integer computermodelstatus;
			private Integer computerid;
			private String computerserialnumber;
			private Integer computercomputertype;
			private String computerlanguagetype;
			private Integer computercomputermodelid;
			private Date computercreatetime;
			private Integer computercreateuserid;
			private Integer computerstatus;
			private String computerremark;
			private Integer computercomputerstatusid;
		
			private Integer orderuserid;
			private String  orderusername;
			
			private Integer oldorder = 0;
			
			
			
			
			
			public Integer getOldorder() {
				return oldorder;
			}
			public void setOldorder(Integer oldorder) {
				this.oldorder = oldorder;
			}
			public Integer getOrderuserid() {
				return orderuserid;
			}
			public void setOrderuserid(Integer orderuserid) {
				this.orderuserid = orderuserid;
			}
			public String getOrderusername() {
				return orderusername;
			}
			public void setOrderusername(String orderusername) {
				this.orderusername = orderusername;
			}
			public void setComputerorderdetailid(Integer computerorderdetailid){		
			this.computerorderdetailid = computerorderdetailid;
		}
		public Integer getComputerorderdetailid(){		
			return this.computerorderdetailid;
		}
			public void setComputerorderdetailcomputerorderid(Integer computerorderdetailcomputerorderid){		
			this.computerorderdetailcomputerorderid = computerorderdetailcomputerorderid;
		}
		public Integer getComputerorderdetailcomputerorderid(){		
			return this.computerorderdetailcomputerorderid;
		}
			public void setComputerorderdetailcomputermodelid(Integer computerorderdetailcomputermodelid){		
			this.computerorderdetailcomputermodelid = computerorderdetailcomputermodelid;
		}
		public Integer getComputerorderdetailcomputermodelid(){		
			return this.computerorderdetailcomputermodelid;
		}
			public void setComputerorderdetailborrownumber(Integer computerorderdetailborrownumber){		
			this.computerorderdetailborrownumber = computerorderdetailborrownumber;
		}
		public Integer getComputerorderdetailborrownumber(){		
			return this.computerorderdetailborrownumber;
		}
			public void setComputerorderdetailcreatetime(Date computerorderdetailcreatetime){		
			this.computerorderdetailcreatetime = computerorderdetailcreatetime;
		}
		public Date getComputerorderdetailcreatetime(){		
			return this.computerorderdetailcreatetime;
		}
			public void setComputerorderdetailborrowday(Date computerorderdetailborrowday){		
			this.computerorderdetailborrowday = computerorderdetailborrowday;
		}
		public Date getComputerorderdetailborrowday(){		
			return this.computerorderdetailborrowday;
		}
			public void setComputerorderdetailborrowperiod(Integer computerorderdetailborrowperiod){		
			this.computerorderdetailborrowperiod = computerorderdetailborrowperiod;
		}
		public Integer getComputerorderdetailborrowperiod(){		
			return this.computerorderdetailborrowperiod;
		}
			public void setComputerorderdetailcomputerid(String computerorderdetailcomputerid){		
			this.computerorderdetailcomputerid = computerorderdetailcomputerid;
		}
		public String getComputerorderdetailcomputerid(){		
			return this.computerorderdetailcomputerid;
		}
			public void setComputerorderdetailstatus(Integer computerorderdetailstatus){		
			this.computerorderdetailstatus = computerorderdetailstatus;
		}
		public Integer getComputerorderdetailstatus(){		
			return this.computerorderdetailstatus;
		}
			public void setComputerorderid(Integer computerorderid){		
			this.computerorderid = computerorderid;
		}
		public Integer getComputerorderid(){		
			return this.computerorderid;
		}
			public void setComputerorderserialnumber(String computerorderserialnumber){		
			this.computerorderserialnumber = computerorderserialnumber;
		}
		public String getComputerorderserialnumber(){		
			return this.computerorderserialnumber;
		}
	
			public Integer getComputerordercreateuserid() {
			return computerordercreateuserid;
		}
		public void setComputerordercreateuserid(Integer computerordercreateuserid) {
			this.computerordercreateuserid = computerordercreateuserid;
		}
			public void setComputerordertitle(String computerordertitle){		
			this.computerordertitle = computerordertitle;
		}
		public String getComputerordertitle(){		
			return this.computerordertitle;
		}
			public void setComputerorderordertype(Integer computerorderordertype){		
			this.computerorderordertype = computerorderordertype;
		}
		public Integer getComputerorderordertype(){		
			return this.computerorderordertype;
		}
			public void setComputerordercreatetime(Date computerordercreatetime){		
			this.computerordercreatetime = computerordercreatetime;
		}
		public Date getComputerordercreatetime(){		
			return this.computerordercreatetime;
		}
			public void setComputerorderremark(String computerorderremark){		
			this.computerorderremark = computerorderremark;
		}
		public String getComputerorderremark(){		
			return this.computerorderremark;
		}
			public void setComputerorderstatus(Integer computerorderstatus){		
			this.computerorderstatus = computerorderstatus;
		}
		public Integer getComputerorderstatus(){		
			return this.computerorderstatus;
		}
			public void setComputermodelid(Integer computermodelid){		
			this.computermodelid = computermodelid;
		}
		public Integer getComputermodelid(){		
			return this.computermodelid;
		}
			public void setComputermodelcomputermodeltype(Integer computermodelcomputermodeltype){		
			this.computermodelcomputermodeltype = computermodelcomputermodeltype;
		}
		public Integer getComputermodelcomputermodeltype(){		
			return this.computermodelcomputermodeltype;
		}
			public void setComputermodellanguagetype(String computermodellanguagetype){		
			this.computermodellanguagetype = computermodellanguagetype;
		}
		public String getComputermodellanguagetype(){		
			return this.computermodellanguagetype;
		}
			public void setComputermodelname(String computermodelname){		
			this.computermodelname = computermodelname;
		}
		public String getComputermodelname(){		
			return this.computermodelname;
		}
			public void setComputermodelcomputercategoryid(Integer computermodelcomputercategoryid){		
			this.computermodelcomputercategoryid = computermodelcomputercategoryid;
		}
		public Integer getComputermodelcomputercategoryid(){		
			return this.computermodelcomputercategoryid;
		}
			public void setComputermodelpicpath(String computermodelpicpath){		
			this.computermodelpicpath = computermodelpicpath;
		}
		public String getComputermodelpicpath(){		
			return this.computermodelpicpath;
		}
			public void setComputermodelcreatetime(Date computermodelcreatetime){		
			this.computermodelcreatetime = computermodelcreatetime;
		}
		public Date getComputermodelcreatetime(){		
			return this.computermodelcreatetime;
		}
			public void setComputermodelcreateuserid(Integer computermodelcreateuserid){		
			this.computermodelcreateuserid = computermodelcreateuserid;
		}
		public Integer getComputermodelcreateuserid(){		
			return this.computermodelcreateuserid;
		}
			public void setComputermodelcomputercount(Integer computermodelcomputercount){		
			this.computermodelcomputercount = computermodelcomputercount;
		}
		public Integer getComputermodelcomputercount(){		
			return this.computermodelcomputercount;
		}
			public void setComputermodelavailableborrowcountnumber(Integer computermodelavailableborrowcountnumber){		
			this.computermodelavailableborrowcountnumber = computermodelavailableborrowcountnumber;
		}
		public Integer getComputermodelavailableborrowcountnumber(){		
			return this.computermodelavailableborrowcountnumber;
		}
			public void setComputermodeldescription(String computermodeldescription){		
			this.computermodeldescription = computermodeldescription;
		}
		public String getComputermodeldescription(){		
			return this.computermodeldescription;
		}
			public void setComputermodelstatus(Integer computermodelstatus){		
			this.computermodelstatus = computermodelstatus;
		}
		public Integer getComputermodelstatus(){		
			return this.computermodelstatus;
		}
			public void setComputerid(Integer computerid){		
			this.computerid = computerid;
		}
		public Integer getComputerid(){		
			return this.computerid;
		}
			public void setComputerserialnumber(String computerserialnumber){		
			this.computerserialnumber = computerserialnumber;
		}
		public String getComputerserialnumber(){		
			return this.computerserialnumber;
		}
			public void setComputercomputertype(Integer computercomputertype){		
			this.computercomputertype = computercomputertype;
		}
		public Integer getComputercomputertype(){		
			return this.computercomputertype;
		}
			public void setComputerlanguagetype(String computerlanguagetype){		
			this.computerlanguagetype = computerlanguagetype;
		}
		public String getComputerlanguagetype(){		
			return this.computerlanguagetype;
		}
			public void setComputercomputermodelid(Integer computercomputermodelid){		
			this.computercomputermodelid = computercomputermodelid;
		}
		public Integer getComputercomputermodelid(){		
			return this.computercomputermodelid;
		}
			public void setComputercreatetime(Date computercreatetime){		
			this.computercreatetime = computercreatetime;
		}
		public Date getComputercreatetime(){		
			return this.computercreatetime;
		}
			public void setComputercreateuserid(Integer computercreateuserid){		
			this.computercreateuserid = computercreateuserid;
		}
		public Integer getComputercreateuserid(){		
			return this.computercreateuserid;
		}
			public void setComputerstatus(Integer computerstatus){		
			this.computerstatus = computerstatus;
		}
		public Integer getComputerstatus(){		
			return this.computerstatus;
		}
			public void setComputerremark(String computerremark){		
			this.computerremark = computerremark;
		}
		public String getComputerremark(){		
			return this.computerremark;
		}
			public void setComputercomputerstatusid(Integer computercomputerstatusid){		
			this.computercomputerstatusid = computercomputerstatusid;
		}
		public Integer getComputercomputerstatusid(){		
			return this.computercomputerstatusid;
		}
		
	
		}
