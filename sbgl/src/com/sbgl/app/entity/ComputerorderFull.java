package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputerorderFull extends DaoAbs implements java.io.Serializable {
			private Integer computerorderid;
			private String computerordernumber;
			private Integer computerorderuserid;
			private Date computerordercreatetime;
			private Integer computerorderstatus;
			private Date computerorderstarttime;
			private Date computerorderendtime;
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
			public void setComputerordernumber(String computerordernumber){		
			this.computerordernumber = computerordernumber;
		}
		public String getComputerordernumber(){		
			return this.computerordernumber;
		}
			public void setComputerorderuserid(Integer computerorderuserid){		
			this.computerorderuserid = computerorderuserid;
		}
		public Integer getComputerorderuserid(){		
			return this.computerorderuserid;
		}
			public void setComputerordercreatetime(Date computerordercreatetime){		
			this.computerordercreatetime = computerordercreatetime;
		}
		public Date getComputerordercreatetime(){		
			return this.computerordercreatetime;
		}
			public void setComputerorderstatus(Integer computerorderstatus){		
			this.computerorderstatus = computerorderstatus;
		}
		public Integer getComputerorderstatus(){		
			return this.computerorderstatus;
		}
			public void setComputerorderstarttime(Date computerorderstarttime){		
			this.computerorderstarttime = computerorderstarttime;
		}
		public Date getComputerorderstarttime(){		
			return this.computerorderstarttime;
		}
			public void setComputerorderendtime(Date computerorderendtime){		
			this.computerorderendtime = computerorderendtime;
		}
		public Date getComputerorderendtime(){		
			return this.computerorderendtime;
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
