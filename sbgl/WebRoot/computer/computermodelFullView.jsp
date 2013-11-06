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
<link href="./css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="./css/global.css" rel="stylesheet" type="text/css" />

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
                    <th class="name">属性名称</th>
                    <th class="f-category">属性值</th>
                    
                  </tr>
                </thead>
                <tbody>					
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computermodelid</td>
                    <td>${computermodelFull.computermodelid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computermodelname</td>
                    <td>${computermodelFull.computermodelname}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computermodelcomputercategoryid</td>
                    <td>${computermodelFull.computermodelcomputercategoryid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computermodelpicpath</td>
                    <td>${computermodelFull.computermodelpicpath}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computermodelcreatetime</td>
                    <td>${computermodelFull.computermodelcreatetime}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computermodelcreateuserid</td>
                    <td>${computermodelFull.computermodelcreateuserid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computermodelstatus</td>
                    <td>${computermodelFull.computermodelstatus}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computercategoryid</td>
                    <td>${computermodelFull.computercategoryid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computercategoryparentcomputercategoryid</td>
                    <td>${computermodelFull.computercategoryparentcomputercategoryid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computercategoryname</td>
                    <td>${computermodelFull.computercategoryname}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computercategorycreatetime</td>
                    <td>${computermodelFull.computercategorycreatetime}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computercategorycreateuserid</td>
                    <td>${computermodelFull.computercategorycreateuserid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computercategorystatus</td>
                    <td>${computermodelFull.computercategorystatus}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>loginuserid</td>
                    <td>${computermodelFull.loginuserid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>loginusername</td>
                    <td>${computermodelFull.loginusername}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>loginusercreatetime</td>
                    <td>${computermodelFull.loginusercreatetime}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>loginuserstatus</td>
                    <td>${computermodelFull.loginuserstatus}</td>                    
                  </tr>               
                </tbody>
              </table>
			  
			  
            </div>
          </div>

      </div>
     

</section>
 

 
<script src="../js/jquery.js"></script>
<script src="../js/loader.min.js"></script>
<script src="../js/select2.js"></script>
<script src="../js/bootstrap-hover-dropdown.min.js"></script>
<script src="../js/bfa.js"></script>
<script>
	$("#modifyFaCategoryId").select2("val","2");
</script>
</body>
</html>


