<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>患者信息报表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="public/plugins/jquery/jquery-1.5.1.js"></script>
    	<link rel="stylesheet" href="../public/plugins/jquery/themes/cupertino/jquery-ui-1.8.11.custom.css"> 
	<link rel="stylesheet" href="../public/plugins/FusionCharts/style.css" type="text/css" />
	<link rel="stylesheet" href="../public/styles/demos.css">
	
	
<style type="text/css">
<!--


.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	
	}
#tabs { height: 200px; } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 650px; overflow: auto;} 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0em 0em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -0px !important; margin-bottom: 0px !important; border-top: none; border-bottom-width: 0px; }
	.ui-tabs-selected { margin-top: -3px !important; }
.STYLE10 {color: #000000; font-size: 12px;font-weight:normal; }
.tabheightandwidge{
	height: 600px;
}

th{
	font-size=12px;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
.title {
	color: #e1e2e3;
	font-size: 12px;
}
-->
</style>

  </head>
  
  <body>
  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="title">
      		<tr>
        		<td height="24" bgcolor="#353c44">
        			<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          			<tr>
	            			<td width="16%" height="23">
	            				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					              <tr>
					                <td width="4%" height="19" valign="bottom"><div align="center"><img src="../public/images/tb.gif"
							width="14" height="14" /></div></td>
					                <td width="96%" valign="bottom"><span class="STYLE1"> 患者信息管理</span></td>
				                  </tr>
	            				</table>
	            			</td>
		        		</tr>
        			</table>
        		</td>
      		</tr>
    	</table>
     <table width="100%" id="tcp" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      	<tr height="20">
	        <td width="17%"  bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">报表编号</span></div></td>
	        <td width="83%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">报表名称</span></div></td>
        </tr>
        <%
        	int hospital_id = LcpUtil.getHospitalID();
        %>
				<tr bgcolor="#FFFFFF" class="STYLE19" >

					<td height="24"><div align="center">1</div></td>
					<td><div align="center"><a href="#"  onclick="window.open('statements1.jsp?height=600&rpt=unincome&hospital_id=<%=hospital_id%>','不纳入患者信息','width = 655,height = 670,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');">不纳入患者信息</a></div></td>
				</tr>
                <tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">2</div></td>
					<td><div align="center"><a href="#" onClick="window.open('statements1.jsp?height=490&rpt=varitation&hospital_id=<%=hospital_id%>','变异退出患者信息','width = 910,height = 560,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');" >变异退出患者信息</a></div></td>
				</tr>
				<tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">2</div></td>
					<td><div align="center"><a href="#" onClick="window.open('statements1.jsp?height=490&rpt=noincome&hospital_id=<%=hospital_id%>','未纳入患者信息','width = 910,height = 560,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');" >未纳入患者信息</a></div></td>
				</tr>
    	</table>
	<div id="pageHtmlDiv"></div>
  </body>
</html>
