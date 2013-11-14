package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputerFull extends DaoAbs implements java.io.Serializable {
			private Integer computerid;
			private String computerserialnumber;
			private Integer computercomputermodelid;
			private Date computercreatetime;
			private Integer computercreateuserid;
			private Integer computerstatus;
			private Integer loginuserid;
			private String loginusername;
			private Timestamp loginusercreatetime;
			private Integer loginuserstatus;
		
	
			
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
