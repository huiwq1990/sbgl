package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputerorderFull extends DaoAbs implements java.io.Serializable {
			private Integer computerorderid;
			private String computerorderserialnumber;
			private Integer computerorderuserid;
			private Date computerordercreatetime;
			private Integer computerorderstatus;
		
	
			
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
		
	
		}
