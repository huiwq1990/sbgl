package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputermodelFull extends DaoAbs implements java.io.Serializable {
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
			
			private Integer computermodelhourrentprice;
			
			private Integer computercategoryid;
			private Integer computercategorycomputercategorytype;
			private String computercategorylanguagetype;
			private Integer computercategoryparentcomputercategoryid;
			private String computercategoryname;
			private Date computercategorycreatetime;
			private Integer computercategorycreateuserid;
			private Integer computercategorystatus;
		
	
			
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
			public void setComputercategoryid(Integer computercategoryid){		
			this.computercategoryid = computercategoryid;
		}
		public Integer getComputercategoryid(){		
			return this.computercategoryid;
		}
			public void setComputercategorycomputercategorytype(Integer computercategorycomputercategorytype){		
			this.computercategorycomputercategorytype = computercategorycomputercategorytype;
		}
		public Integer getComputercategorycomputercategorytype(){		
			return this.computercategorycomputercategorytype;
		}
			public void setComputercategorylanguagetype(String computercategorylanguagetype){		
			this.computercategorylanguagetype = computercategorylanguagetype;
		}
		public String getComputercategorylanguagetype(){		
			return this.computercategorylanguagetype;
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
		public Integer getComputermodelhourrentprice() {
			return computermodelhourrentprice;
		}
		public void setComputermodelhourrentprice(Integer computermodelhourrentprice) {
			this.computermodelhourrentprice = computermodelhourrentprice;
		}
		
	
		}
