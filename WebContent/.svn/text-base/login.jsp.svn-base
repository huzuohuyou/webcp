<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>厦门市区域临床路径平台</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
.STYLE3 {font-size: 12px; color: #adc9d9; }
body,td,th {
	font-size: 12px;
	color: #FFFFFF;
}

-->
</style>



</head>
<script   language= "JavaScript"  type= "text/JavaScript"> 


document.onkeydown=function(event) 
{ 
e = event ? event :(window.event ? window.event : null); 
if(e.keyCode==13){ 
	yanzheng();
} 
};
function huiche(){
	if(event.keyCode ==13){
		yanzheng();
	}
}
function yanzheng(){ 
	
	var   username   =   document.form.textfield.value;
	var   password   =   document.form.textfield2.value; 
	var   code   =   document.form.textfield3.value; 
	if(username.length==0){ 
		alert( "请填写用户名！ "); 
		form.textfield.focus();
	}else if(password.length==0){ 
		alert( "请填写密码！ "); 
		form.textfield2.focus();  
	}else if(code.length==0){ 
		alert( "请填写验证码！ "); 
		form.textfield3.focus();  
	}else{
		document.form.submit();
	}

} 
 function f1(){
	 var div = document.getElementById("mydiv");
	 div.style.left=20;
	 div.style.top=50;
 }
	
 window.onload=function (){
	 f1();
 };

</script>
<body onload = "form.textfield.focus();f1()">
<form action="LoginServlet?op=login" method="post" id="form" name = "form" >

<table width="100%"  height="100%" border="0" cellspacing="0" cellpadding="0" background="public/images/00000000042.jpg">
<div id="mydiv" style="position: absolute;">
 <font size="5px">值班电话</font><br/><br/>
 <font size="4px">信息科&nbsp; 严&nbsp;&nbsp;&nbsp;&nbsp;武: &nbsp;13806063824</font><br/>
 <font size="4px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;周伟彬: &nbsp;18059234145</font>
</div>

  <tr>
    <td height="492"><table width="593" height="336" border="0" align="center" cellpadding="0" cellspacing="0" background="public/images/00000000041.jpg">  
      <tr>
        <td height="154" >&nbsp;</td>
      </tr>
      <tr>
        <td height="84"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="319" height="112">&nbsp;</td>
            <td width="274" valign="middle"><table width="100%" height="131" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="48" height="32" valign="middle"><div align="right">用户名：</div></td>
                <td height="32" colspan="3" valign="middle">
                  <div align="left">
                    <input type="text" name="textfield" id="textfield" style="width:180px; height:20px; font-size:12px; ">
                    </div></td>
                </tr>
              <tr>
                <td height="32" valign="middle"><div align="right">密&nbsp;&nbsp;&nbsp;&nbsp;码：</div></td>
                <td height="32" colspan="3" valign="middle"><div align="left"><input type="password" name="textfield2" id="textfield2" style="width:180px; height:20px; font-size:12px;"></div></td>
                </tr>
              <tr>
                <td height="34" valign="middle"><div align="right">验证码：</div></td>
                <td width="105" height="34" valign="middle"><div align="left"><input type="text" name="textfield3" id="textfield3" style="width:90px; height:20px; font-size:12px; "></div></td>
                <td colspan="2" valign="middle"><div align="left"><img src="login/code.jsp"></div></td>
                </tr>
              <tr>
              <td>&nbsp;</td>
              <td colspan="2" ><img src="public/images/00000000043.jpg" width="87" height="22" onClick="yanzheng();" style="cursor:pointer"></td>
              <td width="120" ><img src="public/images/00000000044.jpg" width="87" height="22" onClick="javascript:document.form.reset();" style="cursor:pointer"></td>
              </tr>
            </table></td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td height="62"><%
        String error=(String)request.getAttribute("error");
        if(error!=null){ //||"".equals(error)
        %><div align="center"><span style="font-size:14px; color:#F00"> <%=error%> </span></div>
        <%
        }
        %></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
