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
import com.sbgl.app.services.user.StudentService;

@Scope("prototype") 
@Controller("RoomReservationAction")
public class RoomReservationAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1784909644167565509L;
	@Resource
	private ClazzService clazzService;
	@Resource
	private StudentService studentService;
	
	//返回数据Map结构
	private Map<String,Object> dtoMap;
	public Map<String, Object> getDtoMap() {
		return dtoMap;
	}
	
	public String getStuInClass() {
		dtoMap = new HashMap<String, Object>();
		List<StuInClassDto> dtoList = new ArrayList<StuInClassDto>();
		
		List<Clazz> allClazz = clazzService.getAllClazz();
		
		List<Student> stuList = studentService.getAllStudent();
		StuInClassDto dto = null;
		if( allClazz != null ) {
			for (Clazz c : allClazz) {
				if( stuList != null ) {
					for (Student stu : stuList) {
						if( stu.getClassid().equals( c.getClassId() ) ) {
							dto = new StuInClassDto();
							dto.setClassId( c.getClassId() );
							dto.setClassName( c.getClassname() );
							dto.setStudentid( stu.getStudentid() );
							dto.setId( stu.getId() );
							dto.setName( stu.getName() );
							dto.setPhoto( stu.getPhoto() );
							dtoList.add( dto );
						}
					}
				}
			}
		}
		
		
		dtoMap.put( "allClass", allClazz != null ? allClazz : new ArrayList<Clazz>() );
		dtoMap.put( "allSC", dtoList );
		
		return SUCCESS;
	}
}
