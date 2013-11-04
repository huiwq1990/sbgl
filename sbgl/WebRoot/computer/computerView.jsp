<%@ page language="java" import="java.util.*,com.sbgl.app.entity.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>View page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		
<link href="http://localhost:8080/sbgl/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="http://localhost:8080/sbgl/css/global.css" rel="stylesheet" type="text/css" />

<!--[if lt IE 10]>
  <script src="../js/jquery.placeholder.js"></script>
  <script>
  $('input, textarea').placeholder();
  </script>
<![endif]-->
<!--[if lt IE 9]>
  <script src="../js/html5shiv.js"></script>
  <script src="../js/respond.min.js"></script>
<![endif]-->
<!--[if IE 7]>
<link rel="stylesheet" href="css/font-awesome-ie7.min.css">
<![endif]-->
</head>

  <body>

 
 
 
 
 
 
<section id="main-wrap" class="clearfix"><!--main-wrap-->

      <!--admin-wrap start-->
      <div id="admin-wrap">
        <div id="admin-content" class="clearfix">    

            <div class="panel panel-default">

				
              <table class="table table-hover">
                <thead>
                  <tr>
                    <th class="chk-column"><input class="chk" id="chk-all" type="checkbox" rel="tooltip" data-original-title="全选"></th>
                    <th class="name">名称</th>
                    <th class="f-category">父级分类</th>
                    
                  </tr>
                </thead>
                <tbody>					
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>id<a class="btn btn-white btn-mini" data-toggle="modal" href="#modify-equip-category" role="button">修改</a></td>
                    <td>${computerModel.id}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>serialnumber<a class="btn btn-white btn-mini" data-toggle="modal" href="#modify-equip-category" role="button">修改</a></td>
                    <td>${computerModel.serialnumber}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computermodelid<a class="btn btn-white btn-mini" data-toggle="modal" href="#modify-equip-category" role="button">修改</a></td>
                    <td>${computerModel.computermodelid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>createtime<a class="btn btn-white btn-mini" data-toggle="modal" href="#modify-equip-category" role="button">修改</a></td>
                    <td>${computerModel.createtime}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>createuserid<a class="btn btn-white btn-mini" data-toggle="modal" href="#modify-equip-category" role="button">修改</a></td>
                    <td>${computerModel.createuserid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>status<a class="btn btn-white btn-mini" data-toggle="modal" href="#modify-equip-category" role="button">修改</a></td>
                    <td>${computerModel.status}</td>                    
                  </tr>               
                </tbody>
              </table>
			  
			  
            </div>
          </div>

      </div>
     

</section>
 

 
<script src="http://localhost:8080/sbgl/js/jquery.js"></script>
<script src="http://localhost:8080/sbgl/js/loader.min.js"></script>
<script src="http://localhost:8080/sbgl/js/select2.js"></script>
<script src="http://localhost:8080/sbgl/js/bootstrap-hover-dropdown.min.js"></script>
<script>
	$("#modifyFaCategoryId").select2("val","2");
</script>
</body>
</html>


