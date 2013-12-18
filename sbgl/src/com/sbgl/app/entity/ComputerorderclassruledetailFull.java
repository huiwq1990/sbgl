package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputerorderclassruledetailFull extends DaoAbs implements java.io.Serializable {
			private Integer computerorderclassruledetailid;
			private Integer computerorderclassruledetailcomputerorderclassruleid;
			private Integer computerorderclassruledetailallowedcomputermodelid;
			private Integer computermodelid;
			private Integer computermodelcomputermodeltype;
			private String computermodellanguagetype;
			private String computermodelname;
			private Integer computermodelcomputercategoryid;
			private String computermodelpicpath;
			private Date computermodelcreatetime;
			private Integer computermodelcreateuserid;
			private Integer computermodelcomputercount;
			private Integer computermodelavailableborrowcountnumber;
			private String computermodeldescription;
			private Integer computermodelstatus;
		
	
			
			public void setComputerorderclassruledetailid(Integer computerorderclassruledetailid){		
			this.computerorderclassruledetailid = computerorderclassruledetailid;
		}
		public Integer getComputerorderclassruledetailid(){		
			return this.computerorderclassruledetailid;
		}
			public void setComputerorderclassruledetailcomputerorderclassruleid(Integer computerorderclassruledetailcomputerorderclassruleid){		
			this.computerorderclassruledetailcomputerorderclassruleid = computerorderclassruledetailcomputerorderclassruleid;
		}
		public Integer getComputerorderclassruledetailcomputerorderclassruleid(){		
			return this.computerorderclassruledetailcomputerorderclassruleid;
		}
			public void setComputerorderclassruledetailallowedcomputermodelid(Integer computerorderclassruledetailallowedcomputermodelid){		
			this.computerorderclassruledetailallowedcomputermodelid = computerorderclassruledetailallowedcomputermodelid;
		}
		public Integer getComputerorderclassruledetailallowedcomputermodelid(){		
			return this.computerorderclassruledetailallowedcomputermodelid;
		}
			public void setComputermodelid(Integer computermodelid){		
			this.computermodelid = computermodelid;
		}
		public Integer getComputermodelid(){		
			return this.computermodelid;
		}
			public void setComputermodelcomputermodeltype(Integer computermodelcomputermodeltype){		
			this.computermodelcomputermodeltype = computermodelcomputermodeltype;
		}
		public Integer getComputermodelcomputermodeltype(){		
			return this.computermodelcomputermodeltype;
		}
			public void setComputermodellanguagetype(String computermodellanguagetype){		
			this.computermodellanguagetype = computermodellanguagetype;
		}
		public String getComputermodellanguagetype(){		
			return this.computermodellanguagetype;
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
			public void setComputermodelcomputercount(Integer computermodelcomputercount){		
			this.computermodelcomputercount = computermodelcomputercount;
		}
		public Integer getComputermodelcomputercount(){		
			return this.computermodelcomputercount;
		}
			public void setComputermodelavailableborrowcountnumber(Integer computermodelavailableborrowcountnumber){		
			this.computermodelavailableborrowcountnumber = computermodelavailableborrowcountnumber;
		}
		public Integer getComputermodelavailableborrowcountnumber(){		
			return this.computermodelavailableborrowcountnumber;
		}
			public void setComputermodeldescription(String computermodeldescription){		
			this.computermodeldescription = computermodeldescription;
		}
		public String getComputermodeldescription(){		
			return this.computermodeldescription;
		}
			public void setComputermodelstatus(Integer computermodelstatus){		
			this.computermodelstatus = computermodelstatus;
		}
		public Integer getComputermodelstatus(){		
			return this.computermodelstatus;
		}
		
	
		}
