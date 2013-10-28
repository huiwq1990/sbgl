package com.sbgl.app.actions.upload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbgl.app.actions.upload.UploaddocFull;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Uploaddoc;
import com.sbgl.app.services.upload.UploadService;
import com.sbgl.util.CommUtil;
import com.sbgl.util.EscColumnToBean;
import com.sbgl.util.FileUtil;

@Scope("prototype") 
@Service("uploadService")
@Transactional
public class UploadAction implements UploadService{

	private List<UploaddocFull> uploaddocList = new ArrayList<UploaddocFull>();  
	
	@Resource
	private BaseDao baseDao;
	
	@Override
	public void addFlie(Uploaddoc uploaddoc) {
		// TODO Auto-generated method stub
		baseDao.saveEntity(uploaddoc);
	}

	@Override
	public Uploaddoc downFlie(String doccode) {
		// TODO Auto-generated method stub
		Uploaddoc uploaddoc = baseDao.getEntityById(Uploaddoc.class, doccode);
		return uploaddoc;
	}

	@Override
	public void delFile(String doccode) {
		// TODO Auto-generated method stub
		Uploaddoc uploaddoc = baseDao.getEntityById(Uploaddoc.class, doccode);
		File deletefile = new File(uploaddoc.getDocpath());   
		deletefile.delete();
	}

	@Override
	public String toPackage(Uploaddoc uploaddoc) throws Exception {
		// TODO Auto-generated method stub
		this.findbyList(uploaddoc);
		Long name = System.currentTimeMillis();
		//获得system.properties文件中的uploadPath路径
		String path = FileUtil.getWebClassAddress();
		String root = "";
		Properties prop = new Properties();
		InputStream stream = new BufferedInputStream(new FileInputStream(new File(path+"system.properties")));
		prop.load(stream);
		root = prop.getProperty("uploadPath");
		//如果uploadPath中为空则放进项目下的upload文件中
		if(root==null||root.equals("")){
			//获得绝对路径
			String path2 = ServletActionContext.getRequest().getRealPath("/");
			root = path2 +"/upload";
		}
		File filePath = new File(root);
		//判断文件夹是否存在,如果不存在则创建文件夹
		if (!filePath.exists()) {
			filePath.mkdir();
		}
		root = root +"/package";
		filePath = new File(root);
		//判断文件夹是否存在,如果不存在则创建文件夹
		if (!filePath.exists()) {
			filePath.mkdir();
		}
		File zipFile = new File(root+"/"+ name  +".zip"); 
		if (!zipFile.exists()) { 
			zipFile.createNewFile(); 
		} 
		ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFile));	
		FileInputStream fin =null;	
		write(zout,fin,root+"/"+ name  +".zip");

		return root+"/"+ name  +".zip";
	}

	
	/** 
	 * 循环list中的所有文件调用打包方法
	 * add by wm 
	 */
	private  void write (ZipOutputStream outputStream,FileInputStream input,String name) throws Exception {
       
		int size = uploaddocList.size();
        for(int i = 0; i < size; i++) {	
        	UploaddocFull uploaddocFull = new UploaddocFull();
        	uploaddocFull = uploaddocList.get(i);
            String file = uploaddocFull.getDocpath();
            String name2 = uploaddocFull.getDocname();
            write(outputStream,file,input,name,name2); 
        }
        
        outputStream.close();		
	}  
	
	/** 
	 * 该方法执行打包处理，采用gbk编码防止出现中文乱码
	 * add by wm 
	 */
	private void write(ZipOutputStream outputStream,String file,FileInputStream input,String name,String name2) throws Exception {  
		try {							
			File srcFile = new File(file);			
			input = new FileInputStream(srcFile);	
			byte[] bb = new byte[4096];			
			int i = 0;			
			outputStream.putNextEntry(new ZipEntry(name2));
			outputStream.setEncoding("gbk");
			while ((i = input.read(bb)) != -1) {		
				outputStream.setLevel(9);				
				outputStream.write(bb, 0, i);	
			}		
		} catch (IOException e) {			
			throw new RuntimeException("压缩失败!", e);		
		} finally {			
			try {				
				outputStream.flush();
				input.close();			
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
	}  
	
	private void findbyList(Uploaddoc uploaddoc){
		final String strSQL="select * from uploaddoc a "
		   	   +" where 1=1  "
		           + CommUtil.getWherePart("a.ProjCode", uploaddoc.getProjcode(), "=", "")
		           + CommUtil.getWherePart("a.DocTypeCode",uploaddoc.getDoctypecode(), "=", "")
		           + CommUtil.getWherePart("a.FileNo", uploaddoc.getFileno(), "=", ""); 
		uploaddocList = baseDao.getHibernateTemplate().executeFind(new HibernateCallback(){
				public Object doInHibernate(Session session) throws HibernateException{
					Query query = session.createSQLQuery(strSQL);
					query.setResultTransformer(new EscColumnToBean(UploaddocFull.class));
					return query.list();
				}
		});	
	}
}
