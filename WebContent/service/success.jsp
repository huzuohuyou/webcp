<%@ page language="java" contentType="text/html; charset=GBK"	pageEncoding="GBK"%>
<%
request.setCharacterEncoding("GBK");
	String json = request.getParameter("json");
%>
<%
	request.setCharacterEncoding("GBK");
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
</head>
<body>
	<%=json%>


	
</body>
</html>