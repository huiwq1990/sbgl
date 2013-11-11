<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<script src="./js/jquery.js"></script>
    <input type="button" onclick="getData()" value="获取全部分类信息"/>
    <input type="button" onclick="addData()" value="添加分类信息"/>
    <input type="button" onclick="alterData()" value="修改分类信息"/>
    <input type="button" onclick="deleteData()" value="修改分类信息"/>
    <input type="button" onclick="getClassCourse()" value="获取分类进程单列表"/>
    <input type="button" onclick="deleteClassCourse()" value="删除分类信息"/>
    <br/>
    <input type="button" onclick="addEquipModel()" value="添加设备型号"/>
    <input type="button" onclick="delEquipModel()" value="删除设备型号"/>
    <input type="button" onclick="altEquipModel()" value="修改设备型号"/>
    <input type="button" onclick="getAllEquipModel()" value="获取全部设备型号"/>
    
    
    <script type="text/javascript">
	    function getData() {
	    	$.ajax({  
			    url:"getAllClassification.do",  
			    type:"post", 
			    success:function(data,textStatus,jq){  
			    	console.log(data);
		        },  
			    error:function(data,textStatus,jq){alert(2);}  
			});
	    }
	    
	    function addData() {
	    	$.ajax({  
			    url:"addClassification.do",  
			    type:"post",
			    data:"equipClassforAdd.parentid=1&equipClassforAdd.name=立体摄影机&equipClassforAdd.userid=3",
			    success:function(data,textStatus,jq){  
			    	console.log(data);
		        },  
			    error:function(data,textStatus,jq){alert(2);}  
			});
	    }
	    
	    function alterData() {
	    	$.ajax({  
			    url:"alterClassification.do",  
			    type:"post",
			    data:"equipClassforAltert.classificationid=7&equipClassforAltert.parentid=1&equipClassforAltert.name=胶卷摄影机&equipClassforAltert.userid=1",
			    success:function(data,textStatus,jq){  
			    	console.log(data);
		        },  
			    error:function(data,textStatus,jq){alert(2);}  
			});
	    }
	    
	    function getClassCourse() {
	    	$.ajax({  
			    url:"getAllClassCourse.do",  
			    type:"post",
			    success:function(data,textStatus,jq){  
			    	console.log(data);
		        },  
			    error:function(data,textStatus,jq){alert(2);}  
			});
	    }
	    
	    function deleteClassCourse() {
	    	$.ajax({  
			    url:"deleteClassCourse.do",  
			    type:"post",
			    data:"classIds=7_6",
			    success:function(data,textStatus,jq){  
			    	console.log(data);
		        },  
			    error:function(data,textStatus,jq){alert(2);}  
			});
	    }
    </script>
  
  	<script type="text/javascript">
  		function addEquipModel() {
	    	$.ajax({  
			    url:"addEquipModel.do",  
			    type:"post",
			    data:"equipment.equipmentname=Canon EOS 5D MARK II&equipment.brandid=1&equipment.classificationid=3&equipment.administrationid=1&equipment.equipmentdetail=我是描述&equipment.remark=我是备注",
			    success:function(data,textStatus,jq){  
			    	console.log(data);
		        },  
			    error:function(data,textStatus,jq){alert(2);}  
			});
	    }
	    
  		function delEquipModel() {
	    	$.ajax({  
			    url:"delEquipModel.do",  
			    type:"post",
			    data:"equipInfoIds=2_3",
			    success:function(data,textStatus,jq){  
			    	console.log(data);
		        },  
			    error:function(data,textStatus,jq){alert(2);}  
			});
	    }
	    
  		function altEquipModel() {
	    	$.ajax({  
			    url:"altEquipModel.do",  
			    type:"post",
			    data:"equipment.equipmentid=4&equipment.equipmentname=Canon II&equipment.brandid=1&equipment.classificationid=3&equipment.administrationid=1&equipment.equipmentdetail=我是修改描述&equipment.remark=我是修改备注",
			    success:function(data,textStatus,jq){  
			    	console.log(data);
		        },  
			    error:function(data,textStatus,jq){alert(2);}  
			});
	    }
	    
  		function getAllEquipModel() {
	    	$.ajax({  
			    url:"getAllEquipModels.do",  
			    type:"post",
			    success:function(data,textStatus,jq){  
			    	console.log(data);
		        },  
			    error:function(data,textStatus,jq){alert(2);}  
			});
	    }
  	</script>
  </body>
</html>
