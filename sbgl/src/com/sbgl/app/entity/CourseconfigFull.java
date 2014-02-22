package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class CourseconfigFull extends DaoAbs implements java.io.Serializable {
			private Integer courseconfigid;
			private String courseconfigschoolyear;
			private Integer courseconfigsemester;
			private Date courseconfigfirstday;
			private Date courseconfiglastday;
			private Date courseconfigfirstweekfirstday;
			private Integer courseconfigcurrentsemester;
			private Integer courseconfigstatus;
		
	
			
			public void setCourseconfigid(Integer courseconfigid){		
			this.courseconfigid = courseconfigid;
		}
		public Integer getCourseconfigid(){		
			return this.courseconfigid;
		}
			public void setCourseconfigschoolyear(String courseconfigschoolyear){		
			this.courseconfigschoolyear = courseconfigschoolyear;
		}
		public String getCourseconfigschoolyear(){		
			return this.courseconfigschoolyear;
		}
			public void setCourseconfigsemester(Integer courseconfigsemester){		
			this.courseconfigsemester = courseconfigsemester;
		}
		public Integer getCourseconfigsemester(){		
			return this.courseconfigsemester;
		}
			public void setCourseconfigfirstday(Date courseconfigfirstday){		
			this.courseconfigfirstday = courseconfigfirstday;
		}
		public Date getCourseconfigfirstday(){		
			return this.courseconfigfirstday;
		}
			public void setCourseconfiglastday(Date courseconfiglastday){		
			this.courseconfiglastday = courseconfiglastday;
		}
		public Date getCourseconfiglastday(){		
			return this.courseconfiglastday;
		}
			public void setCourseconfigfirstweekfirstday(Date courseconfigfirstweekfirstday){		
			this.courseconfigfirstweekfirstday = courseconfigfirstweekfirstday;
		}
		public Date getCourseconfigfirstweekfirstday(){		
			return this.courseconfigfirstweekfirstday;
		}
			public void setCourseconfigcurrentsemester(Integer courseconfigcurrentsemester){		
			this.courseconfigcurrentsemester = courseconfigcurrentsemester;
		}
		public Integer getCourseconfigcurrentsemester(){		
			return this.courseconfigcurrentsemester;
		}
			public void setCourseconfigstatus(Integer courseconfigstatus){		
			this.courseconfigstatus = courseconfigstatus;
		}
		public Integer getCourseconfigstatus(){		
			return this.courseconfigstatus;
		}
		
	
		}
