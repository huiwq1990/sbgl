package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputermodelFull extends DaoAbs implements java.io.Serializable {
			private Integer computermodelid;
			private String computermodelname;
			private Integer computermodelcomputercategoryid;
			private String computermodelpicpath;
			private Date computermodelcreatetime;
			private Integer computermodelcreateuserid;
			private Integer computermodelstatus;
			private Integer computercategoryid;
			private Integer computercategoryparentcomputercategoryid;
			private String computercategoryname;
			private Date computercategorycreatetime;
			private Integer computercategorycreateuserid;
			private Integer computercategorystatus;
			private Integer loginuserid;
			private String loginusername;
			private Timestamp loginusercreatetime;
			private Integer loginuserstatus;
		
	
			
			public void setComputermodelid(Integer computermodelid){		
			this.computermodelid = computermodelid;
		}
		public Integer getComputermodelid(){		
			return this.computermodelid;
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
			public void setComputermodelstatus(Integer computermodelstatus){		
			this.computermodelstatus = computermodelstatus;
		}
		public Integer getComputermodelstatus(){		
			return this.computermodelstatus;
		}
			public void setComputercategoryid(Integer computercategoryid){		
			this.computercategoryid = computercategoryid;
		}
		public Integer getComputercategoryid(){		
			return this.computercategoryid;
		}
			public void setComputercategoryparentcomputercategoryid(Integer computercategoryparentcomputercategoryid){		
			this.computercategoryparentcomputercategoryid = computercategoryparentcomputercategoryid;
		}
		public Integer getComputercategoryparentcomputercategoryid(){		
			return this.computercategoryparentcomputercategoryid;
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
			public void setLoginusercreatetime(Timestamp loginusercreatetime){		
			this.loginusercreatetime = loginusercreatetime;
		}
		public Timestamp getLoginusercreatetime(){		
			return this.loginusercreatetime;
		}
			public void setLoginuserstatus(Integer loginuserstatus){		
			this.loginuserstatus = loginuserstatus;
		}
		public Integer getLoginuserstatus(){		
			return this.loginuserstatus;
		}
		
	
		}
