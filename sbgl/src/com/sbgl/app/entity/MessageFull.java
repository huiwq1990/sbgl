package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class MessageFull extends DaoAbs implements java.io.Serializable {
			private Integer messageid;
			private String messagetitle;
			private String messagecontent;
			private Integer messagesenderid;
			private Date messagesendtime;
			private Integer messagereplyid;
			private Integer messagereadstatus;
			private String messagefilepath;
			private Integer messageisbigfile;
			private Integer messagetype;
			private Integer messagestatus;
			private Integer senderloginuserid;
			private String senderloginusername;
			private Date senderloginusercreatetime;
			private Integer senderloginuserstatus;
		
	
			
			public void setMessageid(Integer messageid){		
			this.messageid = messageid;
		}
		public Integer getMessageid(){		
			return this.messageid;
		}
			public void setMessagetitle(String messagetitle){		
			this.messagetitle = messagetitle;
		}
		public String getMessagetitle(){		
			return this.messagetitle;
		}
			public void setMessagecontent(String messagecontent){		
			this.messagecontent = messagecontent;
		}
		public String getMessagecontent(){		
			return this.messagecontent;
		}
			public void setMessagesenderid(Integer messagesenderid){		
			this.messagesenderid = messagesenderid;
		}
		public Integer getMessagesenderid(){		
			return this.messagesenderid;
		}
			public void setMessagesendtime(Date messagesendtime){		
			this.messagesendtime = messagesendtime;
		}
		public Date getMessagesendtime(){		
			return this.messagesendtime;
		}
			public void setMessagereplyid(Integer messagereplyid){		
			this.messagereplyid = messagereplyid;
		}
		public Integer getMessagereplyid(){		
			return this.messagereplyid;
		}
			public void setMessagereadstatus(Integer messagereadstatus){		
			this.messagereadstatus = messagereadstatus;
		}
		public Integer getMessagereadstatus(){		
			return this.messagereadstatus;
		}
			public void setMessagefilepath(String messagefilepath){		
			this.messagefilepath = messagefilepath;
		}
		public String getMessagefilepath(){		
			return this.messagefilepath;
		}
			public void setMessageisbigfile(Integer messageisbigfile){		
			this.messageisbigfile = messageisbigfile;
		}
		public Integer getMessageisbigfile(){		
			return this.messageisbigfile;
		}
			public void setMessagetype(Integer messagetype){		
			this.messagetype = messagetype;
		}
		public Integer getMessagetype(){		
			return this.messagetype;
		}
			public void setMessagestatus(Integer messagestatus){		
			this.messagestatus = messagestatus;
		}
		public Integer getMessagestatus(){		
			return this.messagestatus;
		}
			public void setSenderloginuserid(Integer senderloginuserid){		
			this.senderloginuserid = senderloginuserid;
		}
		public Integer getSenderloginuserid(){		
			return this.senderloginuserid;
		}
			public void setSenderloginusername(String senderloginusername){		
			this.senderloginusername = senderloginusername;
		}
		public String getSenderloginusername(){		
			return this.senderloginusername;
		}
			public void setSenderloginusercreatetime(Date senderloginusercreatetime){		
			this.senderloginusercreatetime = senderloginusercreatetime;
		}
		public Date getSenderloginusercreatetime(){		
			return this.senderloginusercreatetime;
		}
			public void setSenderloginuserstatus(Integer senderloginuserstatus){		
			this.senderloginuserstatus = senderloginuserstatus;
		}
		public Integer getSenderloginuserstatus(){		
			return this.senderloginuserstatus;
		}
		
	
		}
