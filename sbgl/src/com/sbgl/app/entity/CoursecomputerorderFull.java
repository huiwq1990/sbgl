package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class CoursecomputerorderFull extends DaoAbs implements java.io.Serializable {
			private Integer coursecomputerorderid;
			private Integer coursecomputerordersemesterid;
			private Integer coursecomputerordercourseid;
			private Integer coursecomputerordercomputerorderid;
			private Integer coursecomputerorderstatus;
		
	
			
			public void setCoursecomputerorderid(Integer coursecomputerorderid){		
			this.coursecomputerorderid = coursecomputerorderid;
		}
		public Integer getCoursecomputerorderid(){		
			return this.coursecomputerorderid;
		}
			public void setCoursecomputerordersemesterid(Integer coursecomputerordersemesterid){		
			this.coursecomputerordersemesterid = coursecomputerordersemesterid;
		}
		public Integer getCoursecomputerordersemesterid(){		
			return this.coursecomputerordersemesterid;
		}
			public void setCoursecomputerordercourseid(Integer coursecomputerordercourseid){		
			this.coursecomputerordercourseid = coursecomputerordercourseid;
		}
		public Integer getCoursecomputerordercourseid(){		
			return this.coursecomputerordercourseid;
		}
			public void setCoursecomputerordercomputerorderid(Integer coursecomputerordercomputerorderid){		
			this.coursecomputerordercomputerorderid = coursecomputerordercomputerorderid;
		}
		public Integer getCoursecomputerordercomputerorderid(){		
			return this.coursecomputerordercomputerorderid;
		}
			public void setCoursecomputerorderstatus(Integer coursecomputerorderstatus){		
			this.coursecomputerorderstatus = coursecomputerorderstatus;
		}
		public Integer getCoursecomputerorderstatus(){		
			return this.coursecomputerorderstatus;
		}
		
	
		}
