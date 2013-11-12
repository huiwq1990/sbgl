package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class LoginuserFull extends DaoAbs implements java.io.Serializable {
			private Integer loginuserid;
			private String loginusername;
			private Date loginusercreatetime;
			private Integer loginuserstatus;
		
	
			
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
