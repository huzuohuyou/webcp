<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>厦门市区域临床路径平台</title>
<%
	
String url=request.getParameter("url");
String patient_no=request.getParameter("patient_no");
String flag=request.getParameter("flag");
if(flag==null){
	flag="1";
}
//out.println(patient_no);
if(url==null){
	url="zhikong_patient_td1.jsp";
	
}
url=url+"?patient_no="+patient_no;
//out.println(url);
%>
<script src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
<script>
$(document).ready(function() {
	
	$(top1).load("zhikong_patient_top.jsp?patient_no=<%=patient_no%>&flag="+<%=flag%>);
	$(content).load("<%=url%>");
});
</script>
	<link rel="stylesheet" href="public/plugins/FusionCharts/style.css" type="text/css" />
	<link rel="stylesheet" href="public/styles/demos.css">
	<style type="text/css">
	
	body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	
	}
	</style>
</head>
<body>
	<table width="100%">
		<tr>
			<td width="100%">
				<div id="top1"></div>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<div id="content"></div>
			</td>
		</tr>
	</table>
</body>
</html>
