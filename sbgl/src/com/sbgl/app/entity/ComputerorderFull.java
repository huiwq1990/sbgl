package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class ComputerorderFull extends DaoAbs implements java.io.Serializable {
			private Integer computerorderid;
			private String computerorderserialnumber;
			private Integer computerordercreateuserid;
			private String computerordertitle;
			private Integer computerorderordertype;
			private Date computerordercreatetime;
			
			private Date computerorderorderstarttime;
			private Date computerorderorderendtime;
			private String computerorderremark;
			private String computerorderrejectreason;
			private Integer computerordercomputerhomeworkid;
			private Integer computerorderaudituserid;
			private Integer computerorderstatus;
			private Integer createuserid;
			private String createuseruserid;
			private String createusername;
			private String createuserroletype;
			private String createuserprivilege;
			private String createuserpassword;
			private String createuserrolename;
			
			private Integer audituserid;
			private String audituseruserid;
			private String auditusername;
			private String audituserroletype;
			private String audituserprivilege;
			private String audituserpassword;
			private String audituserphoto;
			private Integer computerhomeworkid;
			private String computerhomeworkname;
			private Integer computerhomeworkcomputerorderclassruleid;
			private String computerhomeworkcontent;
			private Integer computerhomeworkcreateuserid;
			private String computerhomeworkattachment;
			private Integer computerhomeworkstatus;
			private Date computerhomeworkcreatetime;
		
	
			
			public void setComputerorderid(Integer computerorderid){		
			this.computerorderid = computerorderid;
		}
		public Integer getComputerorderid(){		
			return this.computerorderid;
		}
			public void setComputerorderserialnumber(String computerorderserialnumber){		
			this.computerorderserialnumber = computerorderserialnumber;
		}
		public String getComputerorderserialnumber(){		
			return this.computerorderserialnumber;
		}
			public void setComputerordercreateuserid(Integer computerordercreateuserid){		
			this.computerordercreateuserid = computerordercreateuserid;
		}
		public Integer getComputerordercreateuserid(){		
			return this.computerordercreateuserid;
		}
			public void setComputerordertitle(String computerordertitle){		
			this.computerordertitle = computerordertitle;
		}
		public String getComputerordertitle(){		
			return this.computerordertitle;
		}
			public void setComputerorderordertype(Integer computerorderordertype){		
			this.computerorderordertype = computerorderordertype;
		}
		public Integer getComputerorderordertype(){		
			return this.computerorderordertype;
		}
			public void setComputerordercreatetime(Date computerordercreatetime){		
			this.computerordercreatetime = computerordercreatetime;
		}
		public Date getComputerordercreatetime(){		
			return this.computerordercreatetime;
		}
			public void setComputerorderremark(String computerorderremark){		
			this.computerorderremark = computerorderremark;
		}
		public String getComputerorderremark(){		
			return this.computerorderremark;
		}
			public void setComputerorderrejectreason(String computerorderrejectreason){		
			this.computerorderrejectreason = computerorderrejectreason;
		}
		public String getComputerorderrejectreason(){		
			return this.computerorderrejectreason;
		}
			public void setComputerordercomputerhomeworkid(Integer computerordercomputerhomeworkid){		
			this.computerordercomputerhomeworkid = computerordercomputerhomeworkid;
		}
		public Integer getComputerordercomputerhomeworkid(){		
			return this.computerordercomputerhomeworkid;
		}
			public void setComputerorderaudituserid(Integer computerorderaudituserid){		
			this.computerorderaudituserid = computerorderaudituserid;
		}
		public Integer getComputerorderaudituserid(){		
			return this.computerorderaudituserid;
		}
			public void setComputerorderstatus(Integer computerorderstatus){		
			this.computerorderstatus = computerorderstatus;
		}
		public Integer getComputerorderstatus(){		
			return this.computerorderstatus;
		}
			public void setCreateuserid(Integer createuserid){		
			this.createuserid = createuserid;
		}
		public Integer getCreateuserid(){		
			return this.createuserid;
		}
			public void setCreateuseruserid(String createuseruserid){		
			this.createuseruserid = createuseruserid;
		}
		public String getCreateuseruserid(){		
			return this.createuseruserid;
		}
			public void setCreateusername(String createusername){		
			this.createusername = createusername;
		}
		public String getCreateusername(){		
			return this.createusername;
		}
			public void setCreateuserroletype(String createuserroletype){		
			this.createuserroletype = createuserroletype;
		}
		public String getCreateuserroletype(){		
			return this.createuserroletype;
		}
			public void setCreateuserprivilege(String createuserprivilege){		
			this.createuserprivilege = createuserprivilege;
		}
		public String getCreateuserprivilege(){		
			return this.createuserprivilege;
		}
			public void setCreateuserpassword(String createuserpassword){		
			this.createuserpassword = createuserpassword;
		}
		public String getCreateuserpassword(){		
			return this.createuserpassword;
		}
			public void setAudituserid(Integer audituserid){		
			this.audituserid = audituserid;
		}
		public Integer getAudituserid(){		
			return this.audituserid;
		}
			public void setAudituseruserid(String audituseruserid){		
			this.audituseruserid = audituseruserid;
		}
		public String getAudituseruserid(){		
			return this.audituseruserid;
		}
			public void setAuditusername(String auditusername){		
			this.auditusername = auditusername;
		}
		public String getAuditusername(){		
			return this.auditusername;
		}
			public void setAudituserroletype(String audituserroletype){		
			this.audituserroletype = audituserroletype;
		}
		public String getAudituserroletype(){		
			return this.audituserroletype;
		}
			public void setAudituserprivilege(String audituserprivilege){		
			this.audituserprivilege = audituserprivilege;
		}
		public String getAudituserprivilege(){		
			return this.audituserprivilege;
		}
			public void setAudituserpassword(String audituserpassword){		
			this.audituserpassword = audituserpassword;
		}
		public String getAudituserpassword(){		
			return this.audituserpassword;
		}
			public void setComputerhomeworkid(Integer computerhomeworkid){		
			this.computerhomeworkid = computerhomeworkid;
		}
		public Integer getComputerhomeworkid(){		
			return this.computerhomeworkid;
		}
			public void setComputerhomeworkname(String computerhomeworkname){		
			this.computerhomeworkname = computerhomeworkname;
		}
		public String getComputerhomeworkname(){		
			return this.computerhomeworkname;
		}
			public void setComputerhomeworkcomputerorderclassruleid(Integer computerhomeworkcomputerorderclassruleid){		
			this.computerhomeworkcomputerorderclassruleid = computerhomeworkcomputerorderclassruleid;
		}
		public Integer getComputerhomeworkcomputerorderclassruleid(){		
			return this.computerhomeworkcomputerorderclassruleid;
		}
			public void setComputerhomeworkcontent(String computerhomeworkcontent){		
			this.computerhomeworkcontent = computerhomeworkcontent;
		}
		public String getComputerhomeworkcontent(){		
			return this.computerhomeworkcontent;
		}
			public void setComputerhomeworkcreateuserid(Integer computerhomeworkcreateuserid){		
			this.computerhomeworkcreateuserid = computerhomeworkcreateuserid;
		}
		public Integer getComputerhomeworkcreateuserid(){		
			return this.computerhomeworkcreateuserid;
		}
			public void setComputerhomeworkattachment(String computerhomeworkattachment){		
			this.computerhomeworkattachment = computerhomeworkattachment;
		}
		public String getComputerhomeworkattachment(){		
			return this.computerhomeworkattachment;
		}
			public void setComputerhomeworkstatus(Integer computerhomeworkstatus){		
			this.computerhomeworkstatus = computerhomeworkstatus;
		}
		public Integer getComputerhomeworkstatus(){		
			return this.computerhomeworkstatus;
		}
			public void setComputerhomeworkcreatetime(Date computerhomeworkcreatetime){		
			this.computerhomeworkcreatetime = computerhomeworkcreatetime;
		}
		public Date getComputerhomeworkcreatetime(){		
			return this.computerhomeworkcreatetime;
		}
		public String getAudituserphoto() {
			return audituserphoto;
		}
		public void setAudituserphoto(String audituserphoto) {
			this.audituserphoto = audituserphoto;
		}
		public Date getComputerorderorderstarttime() {
			return computerorderorderstarttime;
		}
		public void setComputerorderorderstarttime(Date computerorderorderstarttime) {
			this.computerorderorderstarttime = computerorderorderstarttime;
		}
		public Date getComputerorderorderendtime() {
			return computerorderorderendtime;
		}
		public void setComputerorderorderendtime(Date computerorderorderendtime) {
			this.computerorderorderendtime = computerorderorderendtime;
		}
		
	
		}
