package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputerFull extends DaoAbs implements java.io.Serializable {
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
			private Integer computerstatusid;
			private String computerstatusname;
		
	
			private String computerparameter;
			private String computerpurchasetime;
			private String 	computermaintainlog;
			
			
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
			public void setComputerstatusid(Integer computerstatusid){		
			this.computerstatusid = computerstatusid;
		}
		public Integer getComputerstatusid(){		
			return this.computerstatusid;
		}
			public void setComputerstatusname(String computerstatusname){		
			this.computerstatusname = computerstatusname;
		}
		public String getComputerstatusname(){		
			return this.computerstatusname;
		}
		public String getComputerparameter() {
			return computerparameter;
		}
		public void setComputerparameter(String computerparameter) {
			this.computerparameter = computerparameter;
		}

		
		public String getComputerpurchasetime() {
			return computerpurchasetime;
		}
		public void setComputerpurchasetime(String computerpurchasetime) {
			this.computerpurchasetime = computerpurchasetime;
		}
		public String getComputermaintainlog() {
			return computermaintainlog;
		}
		public void setComputermaintainlog(String computermaintainlog) {
			this.computermaintainlog = computermaintainlog;
		}
		
	
		}
