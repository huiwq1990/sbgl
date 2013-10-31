<%@ page language="java" import="java.util.*,com.sbgl.app.entity.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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

<body id="admin">

	
	
	<section id="main-wrap" class="clearfix"><!--main-wrap-->

      <!--admin-wrap start-->
      <div id="admin-wrap">
        <div id="admin-content" class="clearfix">    
            <!--control-bar start-->
            <div class="control-bar clearfix">
              <div class="title pull-left">Edit</div>
              <div class="tips">edit</div>
            </div><!--control-bar end-->
            <div class="panel panel-default">
              <div class="panel-body">
<!--put data here-->

<!--表单-->             
               <form action="" method="" name="updateComputercategoryForm">

		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">id</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="input_computercategory_id" value="${computercategoryModel.id}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">name</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="input_computercategory_name" value="${computercategoryModel.name}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">createtime</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="input_computercategory_createtime" value="${computercategoryModel.createtime}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">createuserid</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="input_computercategory_createuserid" value="${computercategoryModel.createuserid}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">status</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="input_computercategory_status" value="${computercategoryModel.status}">
                    </div>
                  </div>
      
               </form>
              
			     <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-4">
                      <button class="btn btn-white" data-dismiss="modal" aria-hidden="true">取 消</button>
                      <button type="submit" onClick = "updateComputercategoryAjax()" class="btn btn-primary">保存</button>
                    </div>
                  </div>
<!--表单-->                                  

<!--put data here end-->

              </div>
            </div>
          </div>

      </div>
      <!--equip-wrap end-->
    
      <!--sidebar end-->

</section>




    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
<script src="http://localhost:8080/sbgl/js/jquery.js"></script>
<script src="http://localhost:8080/sbgl/js/loader.min.js"></script>
<script src="http://localhost:8080/sbgl/js/select2.js"></script>
<script src="http://localhost:8080/sbgl/js/bootstrap-hover-dropdown.min.js"></script>
<script src="http://localhost:8080/sbgl/js/computer/computercategory.js"></script>
</body>
</html>