package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class CoursescheduleFull extends DaoAbs implements java.io.Serializable {
			private Integer coursescheduleid;
			private Integer courseschedulecourseid;
			private Integer coursescheduleyears;
			private Integer coursescheduleweek;
			private Integer coursescheduleday;
			private Integer coursescheduleperiod;
			private Integer coursescheduleadduserid;
			private Integer courseschedulestatus;
		
	
			
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
			public void setCoursescheduleyears(Integer coursescheduleyears){		
			this.coursescheduleyears = coursescheduleyears;
		}
		public Integer getCoursescheduleyears(){		
			return this.coursescheduleyears;
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
		
	
		}
