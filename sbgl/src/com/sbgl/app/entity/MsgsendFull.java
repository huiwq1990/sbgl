package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class MsgsendFull extends DaoAbs implements java.io.Serializable {
			private Integer msgsendid;
			private Integer msgsendsenderid;
			private Integer msgsendreceiverid;
			private String msgsendtitle;
			private String msgsendcontent;
			private Integer msgsendtype;
			private Date msgsendsendtime;
			private Date msgsendreadtime;
			private Integer msgsendstatus;
		
	
			
			public void setMsgsendid(Integer msgsendid){		
			this.msgsendid = msgsendid;
		}
		public Integer getMsgsendid(){		
			return this.msgsendid;
		}
			public void setMsgsendsenderid(Integer msgsendsenderid){		
			this.msgsendsenderid = msgsendsenderid;
		}
		public Integer getMsgsendsenderid(){		
			return this.msgsendsenderid;
		}
			public void setMsgsendreceiverid(Integer msgsendreceiverid){		
			this.msgsendreceiverid = msgsendreceiverid;
		}
		public Integer getMsgsendreceiverid(){		
			return this.msgsendreceiverid;
		}
			public void setMsgsendtitle(String msgsendtitle){		
			this.msgsendtitle = msgsendtitle;
		}
		public String getMsgsendtitle(){		
			return this.msgsendtitle;
		}
			public void setMsgsendcontent(String msgsendcontent){		
			this.msgsendcontent = msgsendcontent;
		}
		public String getMsgsendcontent(){		
			return this.msgsendcontent;
		}
			public void setMsgsendtype(Integer msgsendtype){		
			this.msgsendtype = msgsendtype;
		}
		public Integer getMsgsendtype(){		
			return this.msgsendtype;
		}
			public void setMsgsendsendtime(Date msgsendsendtime){		
			this.msgsendsendtime = msgsendsendtime;
		}
		public Date getMsgsendsendtime(){		
			return this.msgsendsendtime;
		}
			public void setMsgsendreadtime(Date msgsendreadtime){		
			this.msgsendreadtime = msgsendreadtime;
		}
		public Date getMsgsendreadtime(){		
			return this.msgsendreadtime;
		}
			public void setMsgsendstatus(Integer msgsendstatus){		
			this.msgsendstatus = msgsendstatus;
		}
		public Integer getMsgsendstatus(){		
			return this.msgsendstatus;
		}
		
	
		}
