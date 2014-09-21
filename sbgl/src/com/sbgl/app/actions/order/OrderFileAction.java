package com.sbgl.app.actions.order;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.services.order.OrderExamService;
import com.sbgl.app.services.order.OrderFinishService;
import com.sbgl.app.services.order.OrderMainService;
import com.sbgl.util.DateUtil;

@Scope("prototype") 
@Controller("OrderFileAction")
public class OrderFileAction  extends ActionSupport  implements SessionAware {
	private static final Log log = LogFactory.getLog(OrderFileAction.class);
	private Map<String, Object> session;
	private Integer borrowId;
	private String fileName;
	private InputStream inputStream;
	private String inputPath;

	@Resource
	private OrderExamService orderExamService;
	
	@Resource
	private OrderFinishService orderFinishService;
	
	@Resource
	private OrderMainService orderMainService;
	
	
	public String downloadOrder(){	
		try {  
			EquipmenborrowFull equipmenborrowFull = orderMainService.findEquipmenborrow(borrowId);
			Loginuser userdetail = orderExamService.userdetail(equipmenborrowFull.getUserid());
			fileName="LeaseOrder.xlsx";
			inputPath = ServletActionContext.getRequest().getRealPath("/upload/");  
			inputStream = new FileInputStream(inputPath+"/"+fileName);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			
			XSSFSheet sheet = workbook.getSheetAt(0); 
			
            
            //订单号和起租日期
            XSSFRow borrowIdRow = sheet.getRow(4); 
            XSSFCell borrowIdCell   = borrowIdRow.getCell(2);
            borrowIdCell.setCellValue(equipmenborrowFull.getBorrowallid());
            XSSFCell borrowTimeCell   = borrowIdRow.getCell(7);
            borrowTimeCell.setCellValue(DateUtil.date.format(equipmenborrowFull.getBorrowtime()));
            
            //客户姓名和归还日期
            XSSFRow userNameRow = sheet.getRow(7); 
            XSSFCell userNameCell   = userNameRow.getCell(2);
            userNameCell.setCellValue(userdetail.getName());
            XSSFCell returnTimeCell   = userNameRow.getCell(7);
            returnTimeCell.setCellValue(DateUtil.date.format(equipmenborrowFull.getReturntime()));
            
            //组装设备列表
            String lantype = CommonConfig.languagechStr;
            List<EquipmentFull> equipmentList = orderFinishService.queryEqumentBorrowallId(equipmenborrowFull.getBorrowid(),lantype);
            
            int rownum = 14;
            double sumprice = 0;
            int size = equipmentList.size();
            DecimalFormat df = new DecimalFormat("#.00");
            XSSFRow row;   
            XSSFCell cell = null;   
            for(int i=0;i<size;i++){
            	EquipmentFull equipmentFull = equipmentList.get(i);
            	sheet.shiftRows(rownum+i, sheet.getLastRowNum(), 1,true,false);
            	row = sheet.createRow(rownum+i);  
            	row.setHeight((short) 400);
            	cell = row.createCell((short) 0);   
                cell.setCellValue(i+1);   
                sheet.addMergedRegion(new CellRangeAddress(rownum+i, rownum+i, 1, 3));      
                cell = row.createCell((short) 1); 
                cell.setCellValue(equipmentFull.getEquipmentname());         
                cell = row.createCell((short) 4); 
                cell.setCellValue(equipmentFull.getEquipserial());
                cell = row.createCell((short) 5); 
                cell.setCellValue(equipmentFull.getStorenumber());
                cell = row.createCell((short) 6); 
                cell.setCellValue(1);
                cell = row.createCell((short) 7); 
                sumprice += equipmentFull.getWorth()==null?0:equipmentFull.getWorth();
                cell.setCellValue(df.format(equipmentFull.getWorth()));         	
            }
            
            
            
            //联系电话和总价
            XSSFRow phoneRow = sheet.getRow(10); 
            XSSFCell phoneCell   = phoneRow.getCell(2);
            phoneCell.setCellValue(userdetail.getTelephone());
            XSSFCell priceCell   = phoneRow.getCell(7);
            priceCell.setCellValue(df.format(sumprice));
            
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            workbook.write(os);
            inputStream = new ByteArrayInputStream(os.toByteArray());
                     
			fileName="LeaseOrder.xlsx";
	    } catch (Exception e) {   
	        e.printStackTrace();   
	    }    
		
		return SUCCESS;
	}
	
	
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session = session;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	//	下载
	public InputStream getInputStream() throws Exception
    {	
    	return inputStream;
    }
	
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	public Integer getBorrowId() {
		return borrowId;
	}


	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}


	public String getInputPath() {
		return inputPath;
	}


	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	
}
