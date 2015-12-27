
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>  

<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>路径纳入条件报表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    
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
  <%
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
  %>
  
  <%! String deptCode="";
	String deptName="";
  %>
  <%
	deptCode=(String)request.getSession().getAttribute("deptcode");
	String ss = "select dept_name from lcp_local_dept where dept_code="+deptCode;
	DatabaseClass db1 = LcpUtil.getDatabaseClass();
	deptName=db1.FunGetDataSetBySQL(ss).FunGetDataAsStringById(0,0);
	System.out.println(deptCode+"--"+deptName);
  %>
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
					                <td width="96%" valign="bottom"><span class="STYLE1">路径诊断码报表</span></td>
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

                <tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">1</div></td>
					<td><div align="center"><a href ="#" onclick="window.open('../ReportEmitter?rpt=income.brt&params=deptCode=<%=deptCode%>')">路径纳入条件参照表</a></div></td>
				</tr>
    	</table>
	<div id="pageHtmlDiv"></div>
  </body>
</html>
