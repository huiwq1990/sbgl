package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputerhomeworkreceiverFull extends DaoAbs implements java.io.Serializable {
			private Integer computerhomeworkreceiverid;
			private Integer computerhomeworkreceivercomputerhomeworkid;
			private Integer computerhomeworkreceiveruserid;
			private Integer loginuserid;
			private String loginusername;
			private Date loginusercreatetime;
			private Integer loginuserstatus;
		
	
			
			public void setComputerhomeworkreceiverid(Integer computerhomeworkreceiverid){		
			this.computerhomeworkreceiverid = computerhomeworkreceiverid;
		}
		public Integer getComputerhomeworkreceiverid(){		
			return this.computerhomeworkreceiverid;
		}
			public void setComputerhomeworkreceivercomputerhomeworkid(Integer computerhomeworkreceivercomputerhomeworkid){		
			this.computerhomeworkreceivercomputerhomeworkid = computerhomeworkreceivercomputerhomeworkid;
		}
		public Integer getComputerhomeworkreceivercomputerhomeworkid(){		
			return this.computerhomeworkreceivercomputerhomeworkid;
		}
			public void setComputerhomeworkreceiveruserid(Integer computerhomeworkreceiveruserid){		
			this.computerhomeworkreceiveruserid = computerhomeworkreceiveruserid;
		}
		public Integer getComputerhomeworkreceiveruserid(){		
			return this.computerhomeworkreceiveruserid;
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
