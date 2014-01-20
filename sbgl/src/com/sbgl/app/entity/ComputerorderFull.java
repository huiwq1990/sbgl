package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputerorderFull extends DaoAbs implements java.io.Serializable {
			private Integer computerorderid;
			private String computerorderserialnumber;
			private Integer computerorderuserid;
			private String computerordertitle;
			private Integer computerorderordertype;
			private Date computerordercreatetime;
			private String computerorderremark;
			private Integer computerorderstatus;
			private Integer loginuserid;
			private String loginusername;
			private Date loginusercreatetime;
			private Integer loginuserstatus;
		
	
			
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
			public void setComputerorderuserid(Integer computerorderuserid){		
			this.computerorderuserid = computerorderuserid;
		}
		public Integer getComputerorderuserid(){		
			return this.computerorderuserid;
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
			public void setLoginuserid(Integer loginuserid){		
			this.loginuserid = loginuserid;
		}
		public Integer getLoginuserid(){		
			return this.loginuserid;
		}
			public void setLoginusername(String loginusername){		
			this.loginusername = loginusername;
		}
		public String getLoginusername(){		
			return this.loginusername;
		}
			public void setLoginusercreatetime(Date loginusercreatetime){		
			this.loginusercreatetime = loginusercreatetime;
		}
		public Date getLoginusercreatetime(){		
			return this.loginusercreatetime;
		}
			public void setLoginuserstatus(Integer loginuserstatus){		
			this.loginuserstatus = loginuserstatus;
		}
		public Integer getLoginuserstatus(){		
			return this.loginuserstatus;
		}
		
	
		}
