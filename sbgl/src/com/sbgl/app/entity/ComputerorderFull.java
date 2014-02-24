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
			private String computerorderrejectreason;
			private Integer computerorderaudituserid;
			private Integer computerorderstatus;
			private String loginuseruserid;
			private String loginusername;
			private String loginuserroletype;
			private Integer loginuserprivilege;
			private String loginuserpassword;
		
	
			
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
			public void setComputerorderrejectreason(String computerorderrejectreason){		
			this.computerorderrejectreason = computerorderrejectreason;
		}
		public String getComputerorderrejectreason(){		
			return this.computerorderrejectreason;
		}
			public void setComputerorderaudituserid(Integer computerorderaudituserid){		
			this.computerorderaudituserid = computerorderaudituserid;
		}
		public Integer getComputerorderaudituserid(){		
			return this.computerorderaudituserid;
		}
			public void setComputerorderstatus(Integer computerorderstatus){		
			this.computerorderstatus = computerorderstatus;
		}
		public Integer getComputerorderstatus(){		
			return this.computerorderstatus;
		}
			public void setLoginuseruserid(String loginuseruserid){		
			this.loginuseruserid = loginuseruserid;
		}
		public String getLoginuseruserid(){		
			return this.loginuseruserid;
		}
			public void setLoginusername(String loginusername){		
			this.loginusername = loginusername;
		}
		public String getLoginusername(){		
			return this.loginusername;
		}
			public void setLoginuserroletype(String loginuserroletype){		
			this.loginuserroletype = loginuserroletype;
		}
		public String getLoginuserroletype(){		
			return this.loginuserroletype;
		}
			public void setLoginuserprivilege(Integer loginuserprivilege){		
			this.loginuserprivilege = loginuserprivilege;
		}
		public Integer getLoginuserprivilege(){		
			return this.loginuserprivilege;
		}
			public void setLoginuserpassword(String loginuserpassword){		
			this.loginuserpassword = loginuserpassword;
		}
		public String getLoginuserpassword(){		
			return this.loginuserpassword;
		}
		
	
		}
