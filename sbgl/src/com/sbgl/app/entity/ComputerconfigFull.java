package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputerconfigFull extends DaoAbs implements java.io.Serializable {
			private Integer computerconfigid;
			private String computerconfigname;
			private String computerconfigvalue;
		
	
			
			public void setComputerconfigid(Integer computerconfigid){		
			this.computerconfigid = computerconfigid;
		}
		public Integer getComputerconfigid(){		
			return this.computerconfigid;
		}
			public void setComputerconfigname(String computerconfigname){		
			this.computerconfigname = computerconfigname;
		}
		public String getComputerconfigname(){		
			return this.computerconfigname;
		}
			public void setComputerconfigvalue(String computerconfigvalue){		
			this.computerconfigvalue = computerconfigvalue;
		}
		public String getComputerconfigvalue(){		
			return this.computerconfigvalue;
		}
		
	
		}
