<%-- 
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：user_list.jsp
// 文件功能描述：用户管理列表页面
// 创建人：赵蓬
// 创建日期：2011/09/02
//----------------------------------------------------------------*/
 --%>
<%@page language="java" contentType="text/html; charset=utf-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>无标题文档</title>
	<link rel="stylesheet" href="../public/plugins/jquery/themes/dcp/jquery-ui-1.8.16.custom.css">
	<link rel="stylesheet" href="../public/styles/style1.css">
	<script src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
	<script src="../public/plugins/jquery/external/jquery.bgiframe-2.1.2.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.core.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.mouse.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.button.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.draggable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.position.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.dialog.js"></script>
	<script src="../public/javascripts/system/user_list.js"></script>
</head>

<body>
	<div id="main">
	 	<div class="navi">
	 		<div class="navi_title">用户管理列表</div>
	 		<div class="navi_search">
	 			搜索&gt;&gt; 按
			  <INPUT id="radio1" CHECKED type="radio" name="radio11" value="lkey">登录名
				<INPUT id="radio2" type="radio" name="radio11" value="nkey">用户名
				<INPUT type="text" id="key" oninput="search()" onpropertychange="search()" />
	 		</div>
	 		<div class="navi_function">
			  <a href="javascript:void(0);" class="select_all" onClick="selectAll('userCbx')">全选</a>
				<a href="javascript:void(0);" class="select_all" onClick="selectRe('userCbx')">反选</a>
				&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0);" class="add" id="regBtn">注册</a>
				<a href="javascript:void(0);" class="del" id="delBtn">删除</a>
	 		</div>
	 	</div>
	 	<table id="users" class="list1">
	 		<tr>
        <th width="10%"></th>
        <th width="25%">用户名</th>
        <th width="30%">登录名</th>
        <th width="15%">用户状态</th>
        <th width="20%">操作</th>
	   	</tr>
	 		<tbody id="tableHtml">
	   	</tbody>
	 	</table>
	</div>
	<div id="registryDialog"></div>
	<div id="distributeDialog"></div>
	<div id="wait" style="position:absolute;overflow:hidden;"></div>
	
	<div id="xinxi" >
	<form action="" name="form" method="post" id="form">
  <table width="100%" id="users2" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    <tbody id="tbody2">
      <tr  bgcolor="#FFFFFF" height="20" class="STYLE19">
        <td height="35" width="40%" align="center">请输入新密码：</td>
        <td width="60%" ><label>
          <input type="password" name="txt2" id="txt2" onBlur="ajaxmi1()">
          </label>
          <label id="lab2"></label></td>
      </tr>
      <tr  bgcolor="#FFFFFF" height="20" class="STYLE19">
        <td width="40%" height="37" align="center">再次输入密码：</td>
        <td width="60%"><label>
          <input type="password" name="txt3" id="txt3" onBlur="ajaxmi2()">
          </label>
          <label id="lab3"></label></td>
      </tr>
    </tbody>
  </table>
  </form>
</div>
	
	
</body>
</html>