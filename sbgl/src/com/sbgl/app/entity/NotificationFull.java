package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class NotificationFull extends DaoAbs implements java.io.Serializable {
			private Integer notificationid;
			private String notificationtitle;
			private String notificationcontent;
			private Integer notificationsenderrid;
			private Integer notificationreceiverid;
			private Date notificationsendtime;
			private Integer notificationreadstatus;
			private Integer notificationmodeltype;
			private Integer notificationstatus;
			private Integer senderloginuserid;
			private String senderloginusername;
			private Date senderloginusercreatetime;
			private Integer senderloginuserstatus;
			private Integer receiverloginuserid;
			private String receiverloginusername;
			private Date receiverloginusercreatetime;
			private Integer receiverloginuserstatus;
		
	
			
			public void setNotificationid(Integer notificationid){		
			this.notificationid = notificationid;
		}
		public Integer getNotificationid(){		
			return this.notificationid;
		}
			public void setNotificationtitle(String notificationtitle){		
			this.notificationtitle = notificationtitle;
		}
		public String getNotificationtitle(){		
			return this.notificationtitle;
		}
			public void setNotificationcontent(String notificationcontent){		
			this.notificationcontent = notificationcontent;
		}
		public String getNotificationcontent(){		
			return this.notificationcontent;
		}
			public void setNotificationsenderrid(Integer notificationsenderrid){		
			this.notificationsenderrid = notificationsenderrid;
		}
		public Integer getNotificationsenderrid(){		
			return this.notificationsenderrid;
		}
			public void setNotificationreceiverid(Integer notificationreceiverid){		
			this.notificationreceiverid = notificationreceiverid;
		}
		public Integer getNotificationreceiverid(){		
			return this.notificationreceiverid;
		}
			public void setNotificationsendtime(Date notificationsendtime){		
			this.notificationsendtime = notificationsendtime;
		}
		public Date getNotificationsendtime(){		
			return this.notificationsendtime;
		}
			public void setNotificationreadstatus(Integer notificationreadstatus){		
			this.notificationreadstatus = notificationreadstatus;
		}
		public Integer getNotificationreadstatus(){		
			return this.notificationreadstatus;
		}
			public void setNotificationmodeltype(Integer notificationmodeltype){		
			this.notificationmodeltype = notificationmodeltype;
		}
		public Integer getNotificationmodeltype(){		
			return this.notificationmodeltype;
		}
			public void setNotificationstatus(Integer notificationstatus){		
			this.notificationstatus = notificationstatus;
		}
		public Integer getNotificationstatus(){		
			return this.notificationstatus;
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
