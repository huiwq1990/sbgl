package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class MessagereceiverFull extends DaoAbs implements java.io.Serializable {
			private Integer messagereceiverid;
			private Integer messagereceivermessageid;
			private Integer messagereceiverreceiverid;
			private Integer messagereceiverhasview;
			private Date messagereceiverviewdate;
			private Integer messagereceiverstatus;
			private Integer receiverloginuserid;
			private String receiverloginusername;
			private Date receiverloginusercreatetime;
			private Integer receiverloginuserstatus;
		
	
			
			public void setMessagereceiverid(Integer messagereceiverid){		
			this.messagereceiverid = messagereceiverid;
		}
		public Integer getMessagereceiverid(){		
			return this.messagereceiverid;
		}
			public void setMessagereceivermessageid(Integer messagereceivermessageid){		
			this.messagereceivermessageid = messagereceivermessageid;
		}
		public Integer getMessagereceivermessageid(){		
			return this.messagereceivermessageid;
		}
			public void setMessagereceiverreceiverid(Integer messagereceiverreceiverid){		
			this.messagereceiverreceiverid = messagereceiverreceiverid;
		}
		public Integer getMessagereceiverreceiverid(){		
			return this.messagereceiverreceiverid;
		}
			public void setMessagereceiverhasview(Integer messagereceiverhasview){		
			this.messagereceiverhasview = messagereceiverhasview;
		}
		public Integer getMessagereceiverhasview(){		
			return this.messagereceiverhasview;
		}
			public void setMessagereceiverviewdate(Date messagereceiverviewdate){		
			this.messagereceiverviewdate = messagereceiverviewdate;
		}
		public Date getMessagereceiverviewdate(){		
			return this.messagereceiverviewdate;
		}
			public void setMessagereceiverstatus(Integer messagereceiverstatus){		
			this.messagereceiverstatus = messagereceiverstatus;
		}
		public Integer getMessagereceiverstatus(){		
			return this.messagereceiverstatus;
		}
			public void setReceiverloginuserid(Integer receiverloginuserid){		
			this.receiverloginuserid = receiverloginuserid;
		}
		public Integer getReceiverloginuserid(){		
			return this.receiverloginuserid;
		}
			public void setReceiverloginusername(String receiverloginusername){		
			this.receiverloginusername = receiverloginusername;
		}
		public String getReceiverloginusername(){		
			return this.receiverloginusername;
		}
			public void setReceiverloginusercreatetime(Date receiverloginusercreatetime){		
			this.receiverloginusercreatetime = receiverloginusercreatetime;
		}
		public Date getReceiverloginusercreatetime(){		
			return this.receiverloginusercreatetime;
		}
			public void setReceiverloginuserstatus(Integer receiverloginuserstatus){		
			this.receiverloginuserstatus = receiverloginuserstatus;
		}
		public Integer getReceiverloginuserstatus(){		
			return this.receiverloginuserstatus;
		}
		
	
		}
