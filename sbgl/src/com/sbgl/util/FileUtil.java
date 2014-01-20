package com.sbgl.util;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author wm
 *
 */
public class FileUtil {

	/**
	 * 
	 */
	public FileUtil() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 拷贝一个文件到指定的文件夹
	 * @param src
	 * @param destDirectory
	 * @return
	 */
	public static boolean copy(String src,String destDirectory){
		
		try {
			java.io.File srcFile = new java.io.File(src);
			String fileName = srcFile.getName();
			boolean dirMade = true;
			java.io.File descDir = new java.io.File(destDirectory);
			if(!descDir.exists()){
				System.out.println("目标目录不存在， 开始创建!");
				dirMade=descDir.mkdir();
			}
			if(!dirMade){
				System.out.println("目标目录创建失败!");
				return false;
			}
			String destFile = null;
			if(destDirectory.endsWith("\\") || destDirectory.equals("/")){
				destFile = destDirectory + fileName;
			}else{
				destFile = destDirectory +"/"+ fileName;
			}
			
			java.io.FileInputStream fin  = new java.io.FileInputStream(src);
			java.io.FileOutputStream fout  = new java.io.FileOutputStream(destFile);
			int x = -1;
			while((x=fin.read()) != -1){
				fout.write(x);
			}
			fin.close();
			fin = null;
			fout.flush();
			fout.close();
			System.out.println("移动文件到目标地址成功!");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("移动文件到目标地址失败 error:" + e.getMessage());
			return false;
		}
	
	}
	
	/**
	 * 拷贝一个文件到指定的文件夹
	 * @param src
	 * @param destDirectory
	 * @return
	 */
	public static boolean copyFileToFile(String srcfile,String destfile){
		
		try {
			java.io.FileInputStream fin = new java.io.FileInputStream(srcfile);
			java.io.FileOutputStream fout = new java.io.FileOutputStream(destfile);
			int x = -1;
			while((x=fin.read()) != -1){
				fout.write(x);
			}
			fin.close();
			fout.flush();
			fout.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("移动文件到目标地址失败 error:" + e.getMessage());
			return false;
		}
	
	}
	
	
	public static List listFile(String path){
		
		java.io.File dir = new java.io.File(path);
		List lst = new ArrayList();
	
		if(dir.isDirectory()){
			java.io.File []files = dir.listFiles();
			int len = files.length;
			for(int i = 0; i < len ;i++ ){
				lst.add(files[i].getAbsolutePath());
			}
		}
		return lst;
		
	}
	
	
	public static String getWebInfoAddress(){
	
		String address="";
		try {
			System.out.println(FileUtil.class.getResource("/").toURI()); 
			address = FileUtil.class.getResource("/").toURI().toString();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(address.indexOf("file:")>=0){
			address = address.substring(6);
		}
		address = address.substring(0,address.lastIndexOf("WEB-INF"));
		address +="WEB-INF";
		return address;
		
	}
	
	public static String getWebClassAddress(){
		
		return getWebInfoAddress()+"/classes/";
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(FileUtil.getWebClassAddress());
	}

}
