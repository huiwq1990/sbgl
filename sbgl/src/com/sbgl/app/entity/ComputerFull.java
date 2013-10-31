package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputerFull extends DaoAbs implements java.io.Serializable {
			private Integer computerid;
			private Integer computerserialnumber;
			private String computername;
			private Integer computercomputercategoryid;
			private Date computercreatetime;
			private Integer computercreateuserid;
			private Integer computerstatus;
			private Integer computercategoryid;
			private String computercategoryname;
			private Date computercategorycreatetime;
			private Integer computercategorycreateuserid;
			private Integer computercategorystatus;
			private Integer loginuserid;
			private String loginusername;
			private Date loginusercreatetime;
			private Integer loginuserstatus;
		
	
			
			public void setComputerid(Integer computerid){		
			this.computerid = computerid;
		}
		public Integer getComputerid(){		
			return this.computerid;
		}
			public void setComputerserialnumber(Integer computerserialnumber){		
			this.computerserialnumber = computerserialnumber;
		}
		public Integer getComputerserialnumber(){		
			return this.computerserialnumber;
		}
			public void setComputername(String computername){		
			this.computername = computername;
		}
		public String getComputername(){		
			return this.computername;
		}
			public void setComputercomputercategoryid(Integer computercomputercategoryid){		
			this.computercomputercategoryid = computercomputercategoryid;
		}
		public Integer getComputercomputercategoryid(){		
			return this.computercomputercategoryid;
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
			public void setComputercategoryid(Integer computercategoryid){		
			this.computercategoryid = computercategoryid;
		}
		public Integer getComputercategoryid(){		
			return this.computercategoryid;
		}
			public void setComputercategoryname(String computercategoryname){		
			this.computercategoryname = computercategoryname;
		}
		public String getComputercategoryname(){		
			return this.computercategoryname;
		}
			public void setComputercategorycreatetime(Date computercategorycreatetime){		
			this.computercategorycreatetime = computercategorycreatetime;
		}
		public Date getComputercategorycreatetime(){		
			return this.computercategorycreatetime;
		}
			public void setComputercategorycreateuserid(Integer computercategorycreateuserid){		
			this.computercategorycreateuserid = computercategorycreateuserid;
		}
		public Integer getComputercategorycreateuserid(){		
			return this.computercategorycreateuserid;
		}
			public void setComputercategorystatus(Integer computercategorystatus){		
			this.computercategorystatus = computercategorystatus;
		}
		public Integer getComputercategorystatus(){		
			return this.computercategorystatus;
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
