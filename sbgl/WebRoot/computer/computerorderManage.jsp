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
             

              <div class="page pull-right clearfix">
                <ul class="page-index nav nav-pills pull-left">
                  <li class="record"><strong>XX</strong>条记录</li>
                  <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" href="#">第1/3页 <b class="caret"></b></a>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="#">最新</a></li>
                      <li><a href="#">最旧</a></li>
                    </ul>
                  </li>              
                </ul>
                <div class="pagination pull-right">
                  <ul>
                    <li><a href="#" rel="tooltip" data-placement="bottom" data-original-title="较新"><i class="icon-chevron-left"></i></a></li>
                    <li><a href="#" rel="tooltip" data-placement="bottom" data-original-title="较旧"><i class="icon-chevron-right"></i></a></li>
                  </ul>
                </div>              
              </div>
            </div><!--control-bar end-->
            <div class="panel panel-default">

              <table class="table table-hover">
                <thead>
                  <tr>
                    <th class="chk-column"><input class="chk" id="chk-all" type="checkbox" rel="tooltip" data-original-title="全选"></th>

                    <th class="name">computerorderid</th>
                    <th class="name">computerordernumber</th>
                    <th class="name">computerorderuserid</th>
                    <th class="name">computerordercreatetime</th>
                    <th class="name">computerorderstatus</th>
                    <th class="name">computerorderstarttime</th>
                    <th class="name">computerorderendtime</th>
                    <th class="name">loginuserid</th>
                    <th class="name">loginusername</th>
                    <th class="name">loginusercreatetime</th>
                    <th class="name">loginuserstatus</th>
                  </tr>
                </thead>
                <tbody>
                  

				<!--遍历所有属性-->
				<s:iterator id="tempEntityFull" value="computerorderFullList" status="sta">
                  <tr>
                    <td><input class="chk" type="checkbox" name="chk-list" value="3"></td>                   
                                        <td>${ tempEntityFull.computerorderid }</td>
                                        <td>${ tempEntityFull.computerordernumber }</td>
                                        <td>${ tempEntityFull.computerorderuserid }</td>
                                        <td>${ tempEntityFull.computerordercreatetime }</td>
                                        <td>${ tempEntityFull.computerorderstatus }</td>
                                        <td>${ tempEntityFull.computerorderstarttime }</td>
                                        <td>${ tempEntityFull.computerorderendtime }</td>
                                        <td>${ tempEntityFull.loginuserid }</td>
                                        <td>${ tempEntityFull.loginusername }</td>
                                        <td>${ tempEntityFull.loginusercreatetime }</td>
                                        <td>${ tempEntityFull.loginuserstatus }</td>
                    					<td><a href="http://localhost:8080/sbgl/viewComputerorderFull.action?id=${ tempEntityFull.computerorderid }">查看</a></td> 
					<td><a href="http://localhost:8080/sbgl/${entityDelActionName}.action?id=${ tempEntityFull.computerorderid }">删除</a></td>			
					<td><a href="http://localhost:8080/sbgl/editComputerorder.action?id=${ tempEntityFull.computerorderid }">修改</a></td>
                  </tr>
				</s:iterator>               
           
                </tbody>
              </table>

            </div>
          </div>

      </div>
      <!--equip-wrap end-->
      <!--sidebar start-->
      <div id="lside">
        <form class="form-group">
          <input type="text" class="form-control" placeholder="搜索">
          <i class="icon-search"></i>
        </form>
        <div class="list-group">
          <a href=".html" class="list-group-item">管理</a>
          <a href=".html" class="list-group-item">添加</a>
          <a href=".html" class="list-group-item active">查看</a>
        </div>
      </div>
      <!--sidebar end-->

</section>



    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
<script src="http://localhost:8080/sbgl/js/jquery.js"></script>
<script src="http://localhost:8080/sbgl/js/loader.min.js"></script>
<script src="http://localhost:8080/sbgl/js/select2.js"></script>
<script src="http://localhost:8080/sbgl/js/bootstrap-hover-dropdown.min.js"></script>
<script>
	$("#modifyFaCategoryId").select2("val","2");
</script>
</body>
</html>