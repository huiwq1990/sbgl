<%@ page language="java" import="java.util.*,com.sbgl.app.entity.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
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
  

  
  <body>

	
	
	<section id="main-wrap" class="clearfix"><!--main-wrap-->

      <!--admin-wrap start-->
      <div id="admin-wrap">
        <div id="admin-content" class="clearfix">    
            <!--control-bar start-->
            <div class="control-bar clearfix">
              <div class="title pull-left">Add</div>
              <div class="tips">Add new</div>
            </div><!--control-bar end-->
            <div class="panel panel-default">
              <div class="panel-body">
                <form action="" method="" name="AddComputermodelForm">
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">id</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computermodelid" name="id" placeholder="id">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">name</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computermodelname" name="name" placeholder="name">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">computercategoryid</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computermodelcomputercategoryid" name="computercategoryid" placeholder="computercategoryid">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">picpath</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computermodelpicpath" name="picpath" placeholder="picpath">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">createtime</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computermodelcreatetime" name="createtime" placeholder="createtime">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">createuserid</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computermodelcreateuserid" name="createuserid" placeholder="createuserid">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">status</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computermodelstatus" name="status" placeholder="status">
                    </div>
                  </div>

               </form>
              
			     <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-4">
                      <button class="btn btn-white" data-dismiss="modal" aria-hidden="true">取 消</button>
                      <button type="submit" onClick = "addComputermodelAjax()" class="btn btn-primary">添 加</button>
                    </div>
                  </div>
              </div>
            </div>
          </div>

      </div>
      <!--equip-wrap end-->
    
      <!--sidebar end-->

</section>
	
	
	
	    <!-- javascript
    ================================================== -->
<script src="http://localhost:8080/sbgl/js/jquery.js"></script>
<script src="http://localhost:8080/sbgl/js/loader.min.js"></script>
<script src="http://localhost:8080/sbgl/js/select2.js"></script>
<script src="http://localhost:8080/sbgl/js/bootstrap-hover-dropdown.min.js"></script>
	<script src="http://localhost:8080/sbgl/js/computer/computermodel.js"></script>
  </body>
</html>
