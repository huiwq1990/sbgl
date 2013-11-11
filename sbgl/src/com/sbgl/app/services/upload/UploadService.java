package com.sbgl.app.services.upload;

import com.sbgl.app.entity.Uploaddoc;

public interface UploadService {
	//新增一个文件信息
	public void addFlie(Uploaddoc uploaddoc);
	//下载一个文件信息
	public Uploaddoc downFlie(String doccode);
	//删除一个文件信息
	public void delFile(String doccode);
	//打包下载
	public String toPackage(Uploaddoc uploaddoc) throws Exception;
}
