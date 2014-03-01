package com.sbgl.app.actions.teach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Clazz;
import com.sbgl.app.entity.Student;
import com.sbgl.app.services.user.ClazzService;

@Scope("prototype") 
@Controller("RoomReservationAction")
public class RoomReservationAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1784909644167565509L;
	@Resource
	private ClazzService clazzService;
	
	//返回数据Map结构
	private Map<String,Object> dtoMap;
	public Map<String, Object> getDtoMap() {
		return dtoMap;
	}
	
	public String getStuInClass() {
		dtoMap = new HashMap<String, Object>();
		List<Clazz> cList = new ArrayList<Clazz>();
		List<StuInClassDto> dtoList = new ArrayList<StuInClassDto>();
		
		List<Map<Clazz, List<Student>>> tempList =  clazzService.getAllClazzDetail();
		Clazz clazz = null;
		List<Student> stuList = null;
		StuInClassDto dto = null;
		if(tempList != null) {
			for (Map<Clazz, List<Student>> map : tempList) {
				Set<Clazz> key = map.keySet();
				for (Iterator<Clazz> it = key.iterator(); it.hasNext();) {
					clazz = (Clazz)it.next();
					cList.add( clazz );
					
					stuList = (List<Student>)map.get( clazz );
					for (Student stu : stuList) {
						dto = new StuInClassDto();
						dto.setClassId( clazz.getClassId() );
						dto.setClassName( clazz.getClassname() );
						dto.setStudentid( stu.getStudentid() );
						dto.setId( stu.getId() );
						dto.setName( stu.getName() );
						dto.setPhoto( stu.getPhoto() );
						dtoList.add( dto );
					}
				}
			}
		}
		
		
		dtoMap.put( "allClass", cList );
		dtoMap.put( "allSC", dtoList );
		
		return SUCCESS;
	}
}
