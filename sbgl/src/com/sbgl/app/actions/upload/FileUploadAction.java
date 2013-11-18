package com.sbgl.app.actions.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.util.PropertyUtil;

public class FileUploadAction  extends ActionSupport{
	private File file;
	private String fileFileName;
	private String fileContentType;
	
	public String uploadFile() throws Exception {
		System.out.println("##############################################################");
		System.out.println("fileFileName = " + fileFileName);
		InputStream is = new FileInputStream(file);
		String equipmentImagePath = PropertyUtil.readValue("/system.properties", "equipmentImagePath");
		//String root = ServletActionContext.getRequest().getRealPath("/equipImage");
		File deskFile = new File(equipmentImagePath, this.getFileFileName());
		OutputStream os = new FileOutputStream(deskFile);
		byte[] bytefer = new byte[1024];
		int length = 0;
		while ((length = is.read(bytefer)) != -1) {
			os.write(bytefer, 0, length);
		}
		os.close();
		is.close();
		return "success";
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
}
