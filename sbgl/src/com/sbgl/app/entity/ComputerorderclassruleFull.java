package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputerorderclassruleFull extends DaoAbs implements java.io.Serializable {
			private Integer computerorderclassruleid;
			private String computerorderclassrulename;
			private String computerorderclassruleclassname;
			private Integer computerorderclassruleclassid;
			private Date computerorderclassruleorderstarttime;
			private Date computerorderclassruleorderendtime;
			private Integer computerorderclassruleavailableordertime;
			private Integer computerorderclassrulecreateuserid;
			private Date computerorderclassrulecreatetime;
			private Integer computerorderclassrulestatus;
		
	
			
			public void setComputerorderclassruleid(Integer computerorderclassruleid){		
			this.computerorderclassruleid = computerorderclassruleid;
		}
		public Integer getComputerorderclassruleid(){		
			return this.computerorderclassruleid;
		}
			public void setComputerorderclassrulename(String computerorderclassrulename){		
			this.computerorderclassrulename = computerorderclassrulename;
		}
		public String getComputerorderclassrulename(){		
			return this.computerorderclassrulename;
		}
			public void setComputerorderclassruleclassname(String computerorderclassruleclassname){		
			this.computerorderclassruleclassname = computerorderclassruleclassname;
		}
		public String getComputerorderclassruleclassname(){		
			return this.computerorderclassruleclassname;
		}
			public void setComputerorderclassruleclassid(Integer computerorderclassruleclassid){		
			this.computerorderclassruleclassid = computerorderclassruleclassid;
		}
		public Integer getComputerorderclassruleclassid(){		
			return this.computerorderclassruleclassid;
		}
			public void setComputerorderclassruleorderstarttime(Date computerorderclassruleorderstarttime){		
			this.computerorderclassruleorderstarttime = computerorderclassruleorderstarttime;
		}
		public Date getComputerorderclassruleorderstarttime(){		
			return this.computerorderclassruleorderstarttime;
		}
			public void setComputerorderclassruleorderendtime(Date computerorderclassruleorderendtime){		
			this.computerorderclassruleorderendtime = computerorderclassruleorderendtime;
		}
		public Date getComputerorderclassruleorderendtime(){		
			return this.computerorderclassruleorderendtime;
		}
			public void setComputerorderclassruleavailableordertime(Integer computerorderclassruleavailableordertime){		
			this.computerorderclassruleavailableordertime = computerorderclassruleavailableordertime;
		}
		public Integer getComputerorderclassruleavailableordertime(){		
			return this.computerorderclassruleavailableordertime;
		}
			public void setComputerorderclassrulecreateuserid(Integer computerorderclassrulecreateuserid){		
			this.computerorderclassrulecreateuserid = computerorderclassrulecreateuserid;
		}
		public Integer getComputerorderclassrulecreateuserid(){		
			return this.computerorderclassrulecreateuserid;
		}
			public void setComputerorderclassrulecreatetime(Date computerorderclassrulecreatetime){		
			this.computerorderclassrulecreatetime = computerorderclassrulecreatetime;
		}
		public Date getComputerorderclassrulecreatetime(){		
			return this.computerorderclassrulecreatetime;
		}
			public void setComputerorderclassrulestatus(Integer computerorderclassrulestatus){		
			this.computerorderclassrulestatus = computerorderclassrulestatus;
		}
		public Integer getComputerorderclassrulestatus(){		
			return this.computerorderclassrulestatus;
		}
		
	
		}
