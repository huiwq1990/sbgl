package com.ruanwei.interfacej;

import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import com.ruanwei.tool.SmsClientAccessTool;
import com.sbgl.app.entity.SmsOverage;

public class KarlosSmsTest {

	public static String url = "http://121.199.50.122:8888/sms.aspx";
	public static String userid = "760";
	public static String account = "sbgl";
	public static String password = "sbgl12345678";
	public static String checkWord = "这个字符串中是否包含了屏蔽字";

	public static void main(String[] args) throws IOException, SAXException {

//		keyword();
		 overage();
//		 test();
	}

	public static void keyword() {

		String keyword = SmsClientKeyword.queryKeyWord(url, userid, account,
				password, checkWord);
		System.out.println(keyword);
	}

	public static SmsOverage overage(){

		String overagexml = SmsClientOverage.queryOverage(url, userid, account,
				password);
//		String overagexml="<?xml version=\"1.0\" encoding=\"utf-8\" ?><returnsms> <returnstatus>Faild</returnstatus> <message>用户名或密码错误</message> <remainpoint>0</remainpoint> <taskID>0</taskID> <successCounts>0</successCounts></returnsms>";
		Digester digester=new Digester();
		digester.setValidating(false);

		digester.addObjectCreate("returnsms", SmsOverage.class);
		digester.addBeanPropertySetter("returnsms/returnstatus");	        
		digester.addBeanPropertySetter("returnsms/message");  
		digester.addBeanPropertySetter("returnsms/payinfo");  
		digester.addBeanPropertySetter("returnsms/overage");  
		digester.addBeanPropertySetter("returnsms/sendTotal");  
		
		try {
			SmsOverage ret = (SmsOverage) digester.parse(new StringReader(overagexml));		
//			SmsOverage ret = temp;
			if(ret.getReturnstatus().equals("Sucess")){
				ret =(SmsOverage) digester.parse(new StringReader(overagexml));
			}
			System.out.println(ret.getReturnstatus()+ret.getMessage()+ret.getPayinfo());
			return ret;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		SmsOverage ret = new SmsOverage();
		ret.setReturnstatus("Faild");
		ret.setMessage("余额查询接口不能解析");
		return ret;
	}

	public static void test(){
		String send = SmsClientAccessTool.getInstance().doAccessHTTPPost(
				"http://118.145.30.35/sms.aspx", "", "utf-8");
		System.out.println(send);
	}
	
//	public static void parse
}
