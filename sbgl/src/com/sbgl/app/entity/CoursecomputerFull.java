package com.sbgl.app.entity;

import com.sbgl.app.dao.DaoAbs;
import java.util.ArrayList;
import java.util.Date;


public class CoursecomputerFull extends DaoAbs implements java.io.Serializable {
			private Integer coursecomputerid;
			private Integer coursecomputerlessonid;
			private Integer coursecomputercomputerid;
			private Integer coursecomputerborrownum;
			private Integer coursecomputerstatus;
		
	
			
			public void setCoursecomputerid(Integer coursecomputerid){		
			this.coursecomputerid = coursecomputerid;
		}
		public Integer getCoursecomputerid(){		
			return this.coursecomputerid;
		}
			public void setCoursecomputerlessonid(Integer coursecomputerlessonid){		
			this.coursecomputerlessonid = coursecomputerlessonid;
		}
		public Integer getCoursecomputerlessonid(){		
			return this.coursecomputerlessonid;
		}
			public void setCoursecomputercomputerid(Integer coursecomputercomputerid){		
			this.coursecomputercomputerid = coursecomputercomputerid;
		}
		public Integer getCoursecomputercomputerid(){		
			return this.coursecomputercomputerid;
		}
			public void setCoursecomputerborrownum(Integer coursecomputerborrownum){		
			this.coursecomputerborrownum = coursecomputerborrownum;
		}
		public Integer getCoursecomputerborrownum(){		
			return this.coursecomputerborrownum;
		}
			public void setCoursecomputerstatus(Integer coursecomputerstatus){		
			this.coursecomputerstatus = coursecomputerstatus;
		}
		public Integer getCoursecomputerstatus(){		
			return this.coursecomputerstatus;
		}
		
	
		}
