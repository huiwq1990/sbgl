<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


Cookie cookie=new Cookie("userid", "1"); 
cookie.setMaxAge(1000000);   //存活期为10秒

cookie.setPath("/sbgl");
response.addCookie(cookie); 

Cookie[] testcookies=request.getCookies();
if(testcookies!=null){
  for(int i=0;i<testcookies.length;i++){
   Cookie c=testcookies[i];
   out.print(c.getName()+":"+c.getValue());
  }
}

%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testCookie.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="${jsDomain}/computer/computerutil.js"></script>
	<script>
	//	addCookie("userid","1",2);
	//addCookie("username","1",2);
	</script>
  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
