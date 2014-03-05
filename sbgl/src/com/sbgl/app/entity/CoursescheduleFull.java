package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class CoursescheduleFull extends DaoAbs implements java.io.Serializable {
			private Integer coursescheduleid;
			private Integer courseschedulecourseid;
			private Integer courseschedulesemester;
			private Integer coursescheduleweek;
			private Integer coursescheduleday;
			private Integer coursescheduleperiod;
			private Integer coursescheduleadduserid;
			private Integer courseschedulestatus;
			private Integer courseid;
			private String coursename;
			private String coursedescription;
			private Integer coursetype;
			private Integer coursecoursetype;
			private Integer courselanguagetype;
			private Integer courseadduserid;
			private Integer courseteacherid;
			private Date courseaddtime;
			private Integer coursestatus;
			private Integer adduserid;
			private String adduseruserid;
			private String addusername;
			private String addusergender;
			private String addusertelephone;
			private String adduseremail;
			private String adduserroletype;
			private String adduserprivilege;
			private String adduserpassword;
			private String adduserphoto;
		
	
			
			public void setCoursescheduleid(Integer coursescheduleid){		
			this.coursescheduleid = coursescheduleid;
		}
		public Integer getCoursescheduleid(){		
			return this.coursescheduleid;
		}
			public void setCourseschedulecourseid(Integer courseschedulecourseid){		
			this.courseschedulecourseid = courseschedulecourseid;
		}
		public Integer getCourseschedulecourseid(){		
			return this.courseschedulecourseid;
		}
			public void setCourseschedulesemester(Integer courseschedulesemester){		
			this.courseschedulesemester = courseschedulesemester;
		}
		public Integer getCourseschedulesemester(){		
			return this.courseschedulesemester;
		}
			public void setCoursescheduleweek(Integer coursescheduleweek){		
			this.coursescheduleweek = coursescheduleweek;
		}
		public Integer getCoursescheduleweek(){		
			return this.coursescheduleweek;
		}
			public void setCoursescheduleday(Integer coursescheduleday){		
			this.coursescheduleday = coursescheduleday;
		}
		public Integer getCoursescheduleday(){		
			return this.coursescheduleday;
		}
			public void setCoursescheduleperiod(Integer coursescheduleperiod){		
			this.coursescheduleperiod = coursescheduleperiod;
		}
		public Integer getCoursescheduleperiod(){		
			return this.coursescheduleperiod;
		}
			public void setCoursescheduleadduserid(Integer coursescheduleadduserid){		
			this.coursescheduleadduserid = coursescheduleadduserid;
		}
		public Integer getCoursescheduleadduserid(){		
			return this.coursescheduleadduserid;
		}
			public void setCourseschedulestatus(Integer courseschedulestatus){		
			this.courseschedulestatus = courseschedulestatus;
		}
		public Integer getCourseschedulestatus(){		
			return this.courseschedulestatus;
		}
			public void setCourseid(Integer courseid){		
			this.courseid = courseid;
		}
		public Integer getCourseid(){		
			return this.courseid;
		}
			public void setCoursename(String coursename){		
			this.coursename = coursename;
		}
		public String getCoursename(){		
			return this.coursename;
		}
			public void setCoursedescription(String coursedescription){		
			this.coursedescription = coursedescription;
		}
		public String getCoursedescription(){		
			return this.coursedescription;
		}
			public void setCoursetype(Integer coursetype){		
			this.coursetype = coursetype;
		}
		public Integer getCoursetype(){		
			return this.coursetype;
		}
			public void setCoursecoursetype(Integer coursecoursetype){		
			this.coursecoursetype = coursecoursetype;
		}
		public Integer getCoursecoursetype(){		
			return this.coursecoursetype;
		}
			public void setCourselanguagetype(Integer courselanguagetype){		
			this.courselanguagetype = courselanguagetype;
		}
		public Integer getCourselanguagetype(){		
			return this.courselanguagetype;
		}
			public void setCourseadduserid(Integer courseadduserid){		
			this.courseadduserid = courseadduserid;
		}
		public Integer getCourseadduserid(){		
			return this.courseadduserid;
		}
			public void setCourseteacherid(Integer courseteacherid){		
			this.courseteacherid = courseteacherid;
		}
		public Integer getCourseteacherid(){		
			return this.courseteacherid;
		}
			public void setCourseaddtime(Date courseaddtime){		
			this.courseaddtime = courseaddtime;
		}
		public Date getCourseaddtime(){		
			return this.courseaddtime;
		}
			public void setCoursestatus(Integer coursestatus){		
			this.coursestatus = coursestatus;
		}
		public Integer getCoursestatus(){		
			return this.coursestatus;
		}
			public void setAdduserid(Integer adduserid){		
			this.adduserid = adduserid;
		}
		public Integer getAdduserid(){		
			return this.adduserid;
		}
			public void setAdduseruserid(String adduseruserid){		
			this.adduseruserid = adduseruserid;
		}
		public String getAdduseruserid(){		
			return this.adduseruserid;
		}
			public void setAddusername(String addusername){		
			this.addusername = addusername;
		}
		public String getAddusername(){		
			return this.addusername;
		}
			public void setAddusergender(String addusergender){		
			this.addusergender = addusergender;
		}
		public String getAddusergender(){		
			return this.addusergender;
		}
			public void setAddusertelephone(String addusertelephone){		
			this.addusertelephone = addusertelephone;
		}
		public String getAddusertelephone(){		
			return this.addusertelephone;
		}
			public void setAdduseremail(String adduseremail){		
			this.adduseremail = adduseremail;
		}
		public String getAdduseremail(){		
			return this.adduseremail;
		}
			public void setAdduserroletype(String adduserroletype){		
			this.adduserroletype = adduserroletype;
		}
		public String getAdduserroletype(){		
			return this.adduserroletype;
		}
			public void setAdduserprivilege(String adduserprivilege){		
			this.adduserprivilege = adduserprivilege;
		}
		public String getAdduserprivilege(){		
			return this.adduserprivilege;
		}
			public void setAdduserpassword(String adduserpassword){		
			this.adduserpassword = adduserpassword;
		}
		public String getAdduserpassword(){		
			return this.adduserpassword;
		}
			public void setAdduserphoto(String adduserphoto){		
			this.adduserphoto = adduserphoto;
		}
		public String getAdduserphoto(){		
			return this.adduserphoto;
		}
		
	
		}
