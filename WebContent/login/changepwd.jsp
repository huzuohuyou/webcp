<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>不换行空格</title>
<link rel="stylesheet" href="../public/plugins/jquery/themes/base/jquery.ui.all.css">
<link rel="stylesheet" href="../public/plugins/jquery/jquery.autocomplete.css">
<script src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
<script src="../public/plugins/jquery/jquery.autocomplete.js"></script>
<style type="text/css">
<!--
a:visited {
	color: #FFF;
}
a {text-decoration:none}

a:hover {color="#ff0000";text-decoration:none}


body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

body {
	font-size: 62.5%;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 350px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td,div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}

.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}

.STYLE2 {
	font-size: 12px;
	color: #0000FF;
}

.STYLE6 {
	color: #000000;
	font-size: 12;
}

.STYLE10 {
	color: #000000;
	font-size: 12px;
}

.STYLE19 {
	color: #344b50;
	font-size: 12px;
}

.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}

.STYLE22 {
	font-size: 12px;
	color: #295568;
}

.STYLE4 {
	font-size: 12px
}
-->
</style>
<script>
var mima0 = false;
var mima1 = false;
var mima2 = false;
//密码框验证
function ajaxmi0(){
	var   password1   =   document.form.txt1.value;
		if(password1.length==0){ 
		$('label#lab1').html('<span style="color:#F00; font-size:14px">请输入原始密码！</span>');
		mima0 = false;
		}else{
			$.ajax({
			url : "../LoginServlet",
			type : 'POST',
			async : true,
			data : {
				op : "password",
				mima:$("#txt1").attr("value")
			},
			dataType : "json",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data, textStatus, XMLHttpRequest) {
				if (data) {
					$('label#lab1').html('<span style="color:#F00; font-size:14px">原始密码错误！</span>');
					mima0 = false;
				    
				} else{	
					$('label#lab1').html('<span style="color:#0F0; font-size:14px">原始密码输入正确！</span>');
					mima0 = true;
				}
			}
		});
		}
	}

function ajaxmi1(){
	var   password1   =   document.form.txt2.value;
	var   password2   =   document.form.txt3.value;
		if(password1.length==0){ 
		$('label#lab2').html('<span style="color:#F00; font-size:14px">请输入密码！</span>');
		mima2 = false;
		}else if(password1!=password2){
			$('label#lab3').html('<span style="color:#F00; font-size:14px">两次输入的密码不一致</span>');
			$('label#lab2').html('');
			mima2 = false;
		}else{
			$('label#lab3').html('');
			mima2 = true;
		}
	}
function ajaxmi2(){
	var   password1   =   document.form.txt2.value;
	var   password2   =   document.form.txt3.value;
		if(password2.length==0){ 
		$('label#lab3').html('<span style="color:#F00; font-size:14px">请再次输入密码！</span>');
		mima2 = false;
		}else if(password1!=password2){
			$('label#lab3').html('<span style="color:#F00; font-size:14px">两次输入的密码不一致</span>');
			mima2 = false;
		}else{
			$('label#lab3').html('');
			mima2 = true;
		}
	}

//表单提交验证
function yanzheng(){
	document.form.txt1.focus(); 
	document.form.txt2.focus()
	document.form.txt3.focus();
	document.form.but1.focus();
	if(!mima0||!mima2){
		
		alert("请正确填写数据后提交！");
	}else{
	document.form.submit();
	}
}
</script>



<!-- Copyright 2000,2001,2002 Macromedia, Inc. All rights reserved. -->
<!-- Copyright 2000,2001,2002 Macromedia, Inc. All rights reserved. -->
<!-- Copyright 2000,2001,2002 Macromedia, Inc. All rights reserved. -->
</head>

<body>

<form action="../LoginServlet?op=changePassword" name="form" method="post" id="form">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30" background="../public/images/main_31.gif" colspan="3">
		
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr bgcolor="#353c44">
								<td height="23">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="25" height="19" valign="bottom">
										<div align="center"><img src="../public/images/tb.gif"
											width="14" height="14" /></div>
										</td>
										<td width="850" valign="bottom"><span class="STYLE1">
									  <a href="#" style="color:#FFF">
									  修改密码</a></span></td>
									</tr>
								</table>
								</td>
							  <td>&nbsp;</td>
		  </tr>
	    </table></td>
	</tr>
</table>
<div id="xinxi" >
  <table width="100%" id="users2" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    <tbody id="tbody2">
      <tr  bgcolor="#FFFFFF" height="20" class="STYLE19">
        <td height="36"><div align="center">请输入原始密码：</div></td>
        <td><label>
          <input type="password" name="txt1" id="txt1" onBlur="ajaxmi0()">
        </label>
          <label id="lab1"></label></td>
      </tr>
      <tr  bgcolor="#FFFFFF" height="20" class="STYLE19">
        <td height="35"><div align="center">请输入新密码：</div></td>
        <td><label>
          <input type="password" name="txt2" id="txt2" onBlur="ajaxmi1()">
          </label>
          <label id="lab2"></label></td>
      </tr>
      <tr  bgcolor="#FFFFFF" height="20" class="STYLE19">
        <td width="15%" height="37"><div align="center">再次输入密码：</div></td>
        <td width="85%"><label>
          <input type="password" name="txt3" id="txt3" onBlur="ajaxmi2()">
          </label>
          <label id="lab3"></label></td>
      </tr>
    </tbody>
  </table>
</div>

  
  <br>
  <div align="center">
    <input name="but1"  type="button"
	style="width: auto; height: auto; font-size: 18px" id="but1" value="提交" onClick="yanzheng()">
  &nbsp;&nbsp;
  <input name="but2" type="reset"
	style="width: auto; height: auto; font-size: 18px" id="but2" value="重置">
  </div>
</form>
</body>
</html>