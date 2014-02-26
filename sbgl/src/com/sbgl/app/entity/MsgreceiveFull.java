package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class MsgreceiveFull extends DaoAbs implements java.io.Serializable {
			private Integer msgreceiveid;
			private Integer msgreceivesenderid;
			private Integer msgreceivereceiverid;
			private String msgreceivetitle;
			private String msgreceivecontent;
			private Integer msgreceivetype;
			private Date msgreceivesendtime;
			private Date msgreceivereadtime;
			private Integer msgreceivestatus;
			private Integer senderid;
			private String senderuserid;
			private String sendername;
			private String senderroletype;
			private String senderprivilege;
			private String senderpassword;
			private Integer receiverid;
			private String receiveruserid;
			private String receivername;
			private String receiverroletype;
			private String receiverprivilege;
			private String receiverpassword;
		
	
			
			public void setMsgreceiveid(Integer msgreceiveid){		
			this.msgreceiveid = msgreceiveid;
		}
		public Integer getMsgreceiveid(){		
			return this.msgreceiveid;
		}
			public void setMsgreceivesenderid(Integer msgreceivesenderid){		
			this.msgreceivesenderid = msgreceivesenderid;
		}
		public Integer getMsgreceivesenderid(){		
			return this.msgreceivesenderid;
		}
			public void setMsgreceivereceiverid(Integer msgreceivereceiverid){		
			this.msgreceivereceiverid = msgreceivereceiverid;
		}
		public Integer getMsgreceivereceiverid(){		
			return this.msgreceivereceiverid;
		}
			public void setMsgreceivetitle(String msgreceivetitle){		
			this.msgreceivetitle = msgreceivetitle;
		}
		public String getMsgreceivetitle(){		
			return this.msgreceivetitle;
		}
			public void setMsgreceivecontent(String msgreceivecontent){		
			this.msgreceivecontent = msgreceivecontent;
		}
		public String getMsgreceivecontent(){		
			return this.msgreceivecontent;
		}
			public void setMsgreceivetype(Integer msgreceivetype){		
			this.msgreceivetype = msgreceivetype;
		}
		public Integer getMsgreceivetype(){		
			return this.msgreceivetype;
		}
			public void setMsgreceivesendtime(Date msgreceivesendtime){		
			this.msgreceivesendtime = msgreceivesendtime;
		}
		public Date getMsgreceivesendtime(){		
			return this.msgreceivesendtime;
		}
			public void setMsgreceivereadtime(Date msgreceivereadtime){		
			this.msgreceivereadtime = msgreceivereadtime;
		}
		public Date getMsgreceivereadtime(){		
			return this.msgreceivereadtime;
		}
			public void setMsgreceivestatus(Integer msgreceivestatus){		
			this.msgreceivestatus = msgreceivestatus;
		}
		public Integer getMsgreceivestatus(){		
			return this.msgreceivestatus;
		}
			public void setSenderid(Integer senderid){		
			this.senderid = senderid;
		}
		public Integer getSenderid(){		
			return this.senderid;
		}
			public void setSenderuserid(String senderuserid){		
			this.senderuserid = senderuserid;
		}
		public String getSenderuserid(){		
			return this.senderuserid;
		}
			public void setSendername(String sendername){		
			this.sendername = sendername;
		}
		public String getSendername(){		
			return this.sendername;
		}
			public void setSenderroletype(String senderroletype){		
			this.senderroletype = senderroletype;
		}
		public String getSenderroletype(){		
			return this.senderroletype;
		}
			public void setSenderprivilege(String senderprivilege){		
			this.senderprivilege = senderprivilege;
		}
		public String getSenderprivilege(){		
			return this.senderprivilege;
		}
			public void setSenderpassword(String senderpassword){		
			this.senderpassword = senderpassword;
		}
		public String getSenderpassword(){		
			return this.senderpassword;
		}
			public void setReceiverid(Integer receiverid){		
			this.receiverid = receiverid;
		}
		public Integer getReceiverid(){		
			return this.receiverid;
		}
			public void setReceiveruserid(String receiveruserid){		
			this.receiveruserid = receiveruserid;
		}
		public String getReceiveruserid(){		
			return this.receiveruserid;
		}
			public void setReceivername(String receivername){		
			this.receivername = receivername;
		}
		public String getReceivername(){		
			return this.receivername;
		}
			public void setReceiverroletype(String receiverroletype){		
			this.receiverroletype = receiverroletype;
		}
		public String getReceiverroletype(){		
			return this.receiverroletype;
		}
			public void setReceiverprivilege(String receiverprivilege){		
			this.receiverprivilege = receiverprivilege;
		}
		public String getReceiverprivilege(){		
			return this.receiverprivilege;
		}
			public void setReceiverpassword(String receiverpassword){		
			this.receiverpassword = receiverpassword;
		}
		public String getReceiverpassword(){		
			return this.receiverpassword;
		}
		
	
		}
