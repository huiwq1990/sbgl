package com.sbgl.app.actions.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.services.common.CommonService;
import com.sbgl.app.services.equipment.EquipService;
import com.sbgl.util.PropertyUtil;

public class FileUploadAction  extends ActionSupport{
	
	@Resource
	private CommonService commonService;
	
	private File file;
	private String fileFileName;
	private String fileContentType;
	private String savedFileName;
	private String tag;
//	private String equipmentid;
	
	public String uploadFile() throws Exception {
//		System.out.println("##############################################################");
//		System.out.println("file name = " + file.getName());
//		System.out.println("fileContentType = " + fileContentType);
//		System.out.println("equipmentid = " + equipmentid);
		String fileType = fileFileName.substring( fileFileName.indexOf('.') );
		//拦截格式不正确的文件，仅允许保存图片格式
		if(!fileType.toLowerCase().equals(".jpg") && !fileType.toLowerCase().equals(".png") &&!fileType.toLowerCase().equals(".gif")) {
			tag = "1";
			return SUCCESS;
		} else {
			tag = "0";
		}
//		String fileType = "";
		
		if(fileType != null) {
			System.out.println("commonService.getCode(\"equipImgCode\").toString() = " + commonService.getCode("equipImgCode").toString());
			System.out.println("fileType = " + fileType);
			savedFileName = commonService.getCode("equipImgCode").toString() + fileType;
			System.out.println("savedFileName = " + savedFileName);
		} else {
			savedFileName = fileFileName;
		}
		InputStream is = new FileInputStream(file);
		String equipmentImagePath = PropertyUtil.readValue("/system.properties", "equipmentImagePath");
		//String root = ServletActionContext.getRequest().getRealPath("/equipImage");
		File deskFile = new File(equipmentImagePath, savedFileName);
		OutputStream os = new FileOutputStream(deskFile);
		byte[] bytefer = new byte[1024];
		int length = 0;
		while ((length = is.read(bytefer)) != -1) {
			os.write(bytefer, 0, length);
		}
		os.close();
		is.close();
		
//		Equipment e = equipService.getEquipmentById( Integer.valueOf(equipmentid) );
//		e.setImgName( fileFileName );
//		e.setImgNameSaved( savedFileName );
//		equipService.alterEquipInfo( e );
		
		return SUCCESS;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getSavedFileName() {
		return savedFileName;
	}

	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
	}
//	public String getEquipmentid() {
//		return equipmentid;
//	}
//
//	public void setEquipmentid(String equipmentid) {
//		this.equipmentid = equipmentid;
//	}
}
