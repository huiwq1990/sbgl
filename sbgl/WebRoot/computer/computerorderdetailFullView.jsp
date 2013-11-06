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
                    <td>computerorderdetailid</td>
                    <td>${computerorderdetailFull.computerorderdetailid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computerorderdetailcomputerorderid</td>
                    <td>${computerorderdetailFull.computerorderdetailcomputerorderid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computerorderdetailcomputerid</td>
                    <td>${computerorderdetailFull.computerorderdetailcomputerid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computerorderdetailcomputernumber</td>
                    <td>${computerorderdetailFull.computerorderdetailcomputernumber}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computerorderdetailcreatetime</td>
                    <td>${computerorderdetailFull.computerorderdetailcreatetime}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computerorderdetailborrowday</td>
                    <td>${computerorderdetailFull.computerorderdetailborrowday}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computerorderdetailborrowperiod</td>
                    <td>${computerorderdetailFull.computerorderdetailborrowperiod}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computerorderdetailstatus</td>
                    <td>${computerorderdetailFull.computerorderdetailstatus}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computerorderid</td>
                    <td>${computerorderdetailFull.computerorderid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computerorderserialnumber</td>
                    <td>${computerorderdetailFull.computerorderserialnumber}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computerorderuserid</td>
                    <td>${computerorderdetailFull.computerorderuserid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computerordercreatetime</td>
                    <td>${computerorderdetailFull.computerordercreatetime}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computerorderstatus</td>
                    <td>${computerorderdetailFull.computerorderstatus}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computerid</td>
                    <td>${computerorderdetailFull.computerid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computerserialnumber</td>
                    <td>${computerorderdetailFull.computerserialnumber}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computercomputermodelid</td>
                    <td>${computerorderdetailFull.computercomputermodelid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computercreatetime</td>
                    <td>${computerorderdetailFull.computercreatetime}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computercreateuserid</td>
                    <td>${computerorderdetailFull.computercreateuserid}</td>                    
                  </tr>               
		          <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>
                    <td>computerstatus</td>
                    <td>${computerorderdetailFull.computerstatus}</td>                    
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


