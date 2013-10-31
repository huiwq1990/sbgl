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
              <div class="title pull-left">Add</div>
              <div class="tips">Add new</div>
            </div><!--control-bar end-->
            <div class="panel panel-default">
              <div class="panel-body">
                 <!-- form start -->
               <form action="" method="" name="AddComputerForm">
				
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">computerid</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computerid" value="${computerFull.computerid}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">computerserialnumber</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computerserialnumber" value="${computerFull.computerserialnumber}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">computername</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computername" value="${computerFull.computername}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">computercomputercategoryid</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computercomputercategoryid" value="${computerFull.computercomputercategoryid}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">computercreatetime</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computercreatetime" value="${computerFull.computercreatetime}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">computercreateuserid</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computercreateuserid" value="${computerFull.computercreateuserid}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">computerstatus</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computerstatus" value="${computerFull.computerstatus}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">computercategoryid</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computercategoryid" value="${computerFull.computercategoryid}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">computercategoryname</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computercategoryname" value="${computerFull.computercategoryname}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">computercategorycreatetime</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computercategorycreatetime" value="${computerFull.computercategorycreatetime}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">computercategorycreateuserid</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computercategorycreateuserid" value="${computerFull.computercategorycreateuserid}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">computercategorystatus</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="computercategorystatus" value="${computerFull.computercategorystatus}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">loginuserid</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="loginuserid" value="${computerFull.loginuserid}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">loginusername</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="loginusername" value="${computerFull.loginusername}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">loginusercreatetime</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="loginusercreatetime" value="${computerFull.loginusercreatetime}">
                    </div>
                  </div>
		<div class="form-group">
                    <label for="inputEquipId" class="col-lg-2 control-label">loginuserstatus</label>
                    <div class="col-lg-4">
                      <input type="text" class="form-control" id="loginuserstatus" value="${computerFull.loginuserstatus}">
                    </div>
                  </div>

               </form>
               <!-- form end -->
			  <!-- submit start -->
			   <div class="form-group">   
                  <div class="col-lg-offset-2 col-lg-4">
                     <button class="btn btn-white" data-dismiss="modal" aria-hidden="true">取 消</button>
                     <button type="submit" onClick = "addComputerAjax()" class="btn btn-primary">添 加</button>
                   </div>
               </div>
				<!-- submit end -->


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
</body>
</html>