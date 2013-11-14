package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputerorderdetailFull extends DaoAbs implements java.io.Serializable {
			private Integer computerorderdetailid;
			private Integer computerorderdetailcomputerorderid;
			private Integer computerorderdetailcomputerid;
			private Integer computerorderdetailcomputernumber;
			private Date computerorderdetailcreatetime;
			private Date computerorderdetailborrowday;
			private Integer computerorderdetailborrowperiod;
			private Integer computerorderdetailstatus;
		
	
			
			public void setComputerorderdetailid(Integer computerorderdetailid){		
			this.computerorderdetailid = computerorderdetailid;
		}
		public Integer getComputerorderdetailid(){		
			return this.computerorderdetailid;
		}
			public void setComputerorderdetailcomputerorderid(Integer computerorderdetailcomputerorderid){		
			this.computerorderdetailcomputerorderid = computerorderdetailcomputerorderid;
		}
		public Integer getComputerorderdetailcomputerorderid(){		
			return this.computerorderdetailcomputerorderid;
		}
			public void setComputerorderdetailcomputerid(Integer computerorderdetailcomputerid){		
			this.computerorderdetailcomputerid = computerorderdetailcomputerid;
		}
		public Integer getComputerorderdetailcomputerid(){		
			return this.computerorderdetailcomputerid;
		}
			public void setComputerorderdetailcomputernumber(Integer computerorderdetailcomputernumber){		
			this.computerorderdetailcomputernumber = computerorderdetailcomputernumber;
		}
		public Integer getComputerorderdetailcomputernumber(){		
			return this.computerorderdetailcomputernumber;
		}
			public void setComputerorderdetailcreatetime(Date computerorderdetailcreatetime){		
			this.computerorderdetailcreatetime = computerorderdetailcreatetime;
		}
		public Date getComputerorderdetailcreatetime(){		
			return this.computerorderdetailcreatetime;
		}
			public void setComputerorderdetailborrowday(Date computerorderdetailborrowday){		
			this.computerorderdetailborrowday = computerorderdetailborrowday;
		}
		public Date getComputerorderdetailborrowday(){		
			return this.computerorderdetailborrowday;
		}
			public void setComputerorderdetailborrowperiod(Integer computerorderdetailborrowperiod){		
			this.computerorderdetailborrowperiod = computerorderdetailborrowperiod;
		}
		public Integer getComputerorderdetailborrowperiod(){		
			return this.computerorderdetailborrowperiod;
		}
			public void setComputerorderdetailstatus(Integer computerorderdetailstatus){		
			this.computerorderdetailstatus = computerorderdetailstatus;
		}
		public Integer getComputerorderdetailstatus(){		
			return this.computerorderdetailstatus;
		}
		
	
		}
