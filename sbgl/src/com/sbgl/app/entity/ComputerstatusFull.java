package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputerstatusFull extends DaoAbs implements java.io.Serializable {
			private Integer computerstatusid;
			private String computerstatusname;
		
	
			
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
		
	
		}
