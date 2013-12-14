package com.sbgl.app.actions.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentdetail;
import com.sbgl.app.services.common.CommonService;
import com.sbgl.app.services.equipment.EquipService;
import com.sbgl.util.PropertyUtil;

public class FileUploadAction  extends ActionSupport {
	
	@Resource
	private CommonService commonService;
	@Resource
	private EquipService equipService;
	
	private File file;
	private String fileFileName;
	private String fileContentType;
	private String savedFileName;
	private String tag;
	private String msg;
//	private String equipmentid;
	
	public String getMsg() {
		return msg;
	}

	public String uploadFile() throws Exception {
//		System.out.println("##############################################################");
//		System.out.println("file name = " + file.getName());
//		System.out.println("fileContentType = " + fileContentType);
//		System.out.println("equipmentid = " + equipmentid);
		String fileType = fileFileName.substring( fileFileName.indexOf('.') );
		//拦截格式不正确的文件，仅允许保存图片格式
		if(!fileType.toLowerCase().equals(".jpg") && !fileType.toLowerCase().equals(".png") &&!fileType.toLowerCase().equals(".gif")) {
			tag = "1";
			msg = "请上传图片格式的文件！";
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
	
	public String uploadExcelFile() throws Exception {
		String fileType = fileFileName.substring( fileFileName.indexOf('.') );
		if(!fileType.toLowerCase().equals(".xls") && !fileType.toLowerCase().equals(".xlsx")) {
			tag = "1";
			msg = "请上传正确的Excel文件！";
			return SUCCESS;
		} else {
			tag = "0";
		}
		
		InputStream stream = new FileInputStream( file );  
        Workbook wb = null;  
        if (fileType.equals(".xls")) {  
            wb = new HSSFWorkbook(stream);  
        } else if (fileType.equals(".xlsx")) {  
            wb = new XSSFWorkbook(stream);  
        }
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            	Sheet s = wb.getSheetAt(i);
                for (Row row : s) {
                	//System.out.println("row number is " + row.getRowNum());
                	if(row.getRowNum() == 0) {  //不读第一行表头
                		continue;
                	}
                	Equipmentdetail detail = new Equipmentdetail();
                	//System.out.println("*****************************" + row.getCell(2).getStringCellValue());
                	if(row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(2) != null ) {  //当库存编号存在时开始将此记录存入数据库
                		if(row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(3) != null) {  //一级分类不为空
                    		Equipmentclassification ec = new Equipmentclassification();
                    		Equipmentclassification ecEN = new Equipmentclassification();
                    		
                    		if( !equipService.isExistThisClassification( row.getCell(3).getStringCellValue() ) ) {
                    			
                    			int comeId = equipService.getClassificationComId();
                        		ec.setName( row.getCell(3).getStringCellValue() );
                        		ecEN.setName( row.getCell(3).getStringCellValue() );
                        		ec.setParentid(0);
                        		ecEN.setParentid(0);
                        		ec.setLanType( "CH" );
                        		ecEN.setLanType( "EN" );
                        		ec.setComId( comeId );
                        		ecEN.setComId( comeId );
                    			equipService.addEquipmentclassification( ec );
                        		equipService.addEquipmentclassification( ecEN );
                    		}
                    	}
                    	if(row.getCell(4).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(4) != null && row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(3) != null) {  //一二级分类不为空
                    		Equipmentclassification ec = new Equipmentclassification();
                    		Equipmentclassification ecEN = new Equipmentclassification();
                    		if( row.getCell(4).getStringCellValue().equals( row.getCell(3).getStringCellValue() ) ) {
                    			if( !equipService.isExistThisClassification( row.getCell(4).getStringCellValue()+"(资产名称)" ) ) {
                    				int comeId = equipService.getClassificationComId();
                            		Equipmentclassification pEc = equipService.getEquipmentclassificationByName( row.getCell(3).getStringCellValue() );
                    				ec.setName( row.getCell(4).getStringCellValue()+"(资产名称)" );
                            		ecEN.setName( row.getCell(4).getStringCellValue()+"(资产名称)" );
                            		ec.setParentid( pEc == null ? 0 : pEc.getClassificationid() );
                            		ecEN.setParentid( pEc == null ? 0 : pEc.getClassificationid() );
                            		ec.setLanType( "CH" );
                            		ecEN.setLanType( "EN" );
                            		ec.setComId( comeId );
                            		ecEN.setComId( comeId );
                            		equipService.addEquipmentclassification( ec );
                            		equipService.addEquipmentclassification( ecEN );
                    			}
                    		} else {
                    			if( !equipService.isExistThisClassification( row.getCell(4).getStringCellValue() ) ) {
                    				int comeId = equipService.getClassificationComId();
                            		Equipmentclassification pEc = equipService.getEquipmentclassificationByName( row.getCell(3).getStringCellValue() );
                    				ec.setName( row.getCell(4).getStringCellValue() );
                            		ecEN.setName( row.getCell(4).getStringCellValue() );
                            		ec.setParentid( pEc == null ? 0 : pEc.getClassificationid() );
                            		ecEN.setParentid( pEc == null ? 0 : pEc.getClassificationid() );
                            		ec.setLanType( "CH" );
                            		ecEN.setLanType( "EN" );
                            		ec.setComId( comeId );
                            		ecEN.setComId( comeId );
                            		equipService.addEquipmentclassification( ec );
                            		equipService.addEquipmentclassification( ecEN );
                    			}
                    		}
                    	}
                    	
                    	if(row.getCell(9).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(9) != null) {  //存在型号
                    		Equipment e = new Equipment();
                    		Equipment eEN = new Equipment();
                    		
                    		if( !"*".equals( row.getCell(9).getStringCellValue() ) ) {  //标示为“*”的型号为未知型号
                    			if(!equipService.isExistEquipment( row.getCell(9).getStringCellValue() )) {
                        			e.setEquipmentname( row.getCell(9).getStringCellValue() );
                            		eEN.setEquipmentname( row.getCell(9).getStringCellValue() );
                            		Equipmentclassification ec = null;
                            		
                            		if(row.getCell(4).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(4) != null && row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(3) != null) {
                            			String className = "";
                            			if(row.getCell(4).getStringCellValue().equals( row.getCell(3).getStringCellValue() ) && row.getCell(4).getCellType() == row.getCell(3).getCellType()) {  //一二级分类名称相同时
                            				className =  row.getCell(4).getStringCellValue()+"(资产名称)";
                                		} else {
                                			className =  row.getCell(4).getStringCellValue() ;
                                		}
                            			ec = equipService.getEquipmentclassificationByName( className );
                            			e.setClassificationid( ec.getClassificationid() );
                            			eEN.setClassificationid( ec.getClassificationid() );
                            		}
                            		int comId = equipService.getEquipmentComId();
                            		e.setLanType( "CH" );
                            		eEN.setLanType( "EN" );
                            		e.setComId( comId );
                            		eEN.setComId( comId );
                        			equipService.addEquipInfo( e );
                        			equipService.addEquipInfo( eEN );
                        		}
                    		}
                    	}
                		
                		
                		detail.setAssetNumber( Integer.valueOf( row.getCell(2).getStringCellValue() ) );
                		if(!equipService.isExistEquipDetial( detail.getAssetNumber() )) {
	                		if(row.getCell(1).getCellType() == Cell.CELL_TYPE_NUMERIC && row.getCell(1) != null ) {
	                			detail.setStorenumber( (int)row.getCell(1).getNumericCellValue() );
	                    	}
	                		if(row.getCell(1).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(1) != null ) {
	                			detail.setStorenumber( Integer.valueOf( row.getCell(1).getStringCellValue() ) );
	                		}
	                		if(row.getCell(4).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(4) != null && row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(3) != null) {
	                			String className = "";
	                			if(row.getCell(4).getStringCellValue().equals( row.getCell(3).getStringCellValue() ) && row.getCell(4).getCellType() == row.getCell(3).getCellType()) {  //一二级分类名称相同时
	                				className =  row.getCell(4).getStringCellValue()+"(资产名称)";
	                    		} else {
	                    			className =  row.getCell(4).getStringCellValue() ;
	                    		}
	                			Equipmentclassification ec = equipService.getEquipmentclassificationByName( className );
	                			detail.setClassificationid( ec.getClassificationid() );
	                		}
	                		if(row.getCell(5).getCellType() == Cell.CELL_TYPE_NUMERIC && row.getCell(5) != null ) {
	                    		detail.setEquipserial( String.valueOf( row.getCell(5).getNumericCellValue() ) );
	                    	}
	                		if(row.getCell(5).getCellType() == Cell.CELL_TYPE_STRING  && row.getCell(5) != null ) {
	                			detail.setEquipserial( row.getCell(5).getStringCellValue() );
	                		}
	                    	if(row.getCell(6).getCellType() == Cell.CELL_TYPE_STRING  && row.getCell(6) != null ) {
	                    		Date mDate = null;
	                    		try {
	                    			mDate = sdf.parse( row.getCell(6).getStringCellValue() );
	                    			detail.setManufactureDate( mDate );
	                    		} catch (ParseException e) {
	                    			
	                    		}
	                    	}
	                    	if(row.getCell(7).getCellType() == Cell.CELL_TYPE_STRING  && row.getCell(7) != null ) {
	                    		Date aDate = null;
	                    		try {
	                    			aDate = sdf.parse( row.getCell(7).getStringCellValue() );
	                    			detail.setAcquireDate( aDate );
	                    		} catch (ParseException e) {
	                    			
	                    		}
	                    	}
	                    	if(row.getCell(8).getCellType() == Cell.CELL_TYPE_STRING  && row.getCell(8) != null ) {
	                    		detail.setManufacturer( row.getCell(8).getStringCellValue() );
	                    	}
	                    	if(row.getCell(9).getCellType() == Cell.CELL_TYPE_STRING  && row.getCell(8) != null ) {
	                    		if( !"*".equals( row.getCell(9).getStringCellValue() ) ) {
	                    			Equipment e = equipService.getEquipmentByName( row.getCell(9).getStringCellValue() );
	                    			detail.setEquipmentid( e.getEquipmentid() );
	                    		} else {
	                    			detail.setEquipmentid( -1 );
	                    		}
	                    	}
	                    	if(row.getCell(11).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(11) != null ) {
	                    		detail.setSupplyer( row.getCell(11).getStringCellValue() );
	                    	}
	                    	if(row.getCell(12).getCellType() == Cell.CELL_TYPE_NUMERIC && row.getCell(12) != null ) {
	                    		detail.setWorth( (float)row.getCell(12).getNumericCellValue() );
	                    	}
	                    	if(row.getCell(13).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(13) != null ) {
	                    		detail.setUseManageDept( row.getCell(13).getStringCellValue() );
	                    	}
	                    	if(row.getCell(14).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(14) != null ) {
	                    		detail.setManager( row.getCell(14).getStringCellValue() );
	                    	}
	                    	if(row.getCell(15).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(15) != null ) {
	                    		detail.setStoragePlace( row.getCell(15).getStringCellValue() );
	                    	}
	                    	if(row.getCell(16).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(16) != null ) {
	                    		detail.setStoragePosition( row.getCell(16).getStringCellValue() );
	                    	}
	                    	if(row.getCell(18).getCellType() == Cell.CELL_TYPE_STRING && row.getCell(18) != null ) {
	                    		detail.setUsermark( row.getCell(18).getStringCellValue() );
	                    	}
	                    	detail.setStatus( "0" );
                    	
                    		equipService.addEquipmentdetail( detail );
                    	}
                	}
                }
    		}
        	tag = "0";
        } catch(Exception e) {
        	tag = "2";
			msg = "解析Excel错误！";
        }
        
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
