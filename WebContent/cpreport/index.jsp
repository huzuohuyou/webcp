<%@page contentType="text/html; charset=gbk"%>
<%
  String root = request.getContextPath();
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk">
		<title>�z�ܱ�����ʾ����</title>
	</head>
	
	
		
		<frameset rows="*" cols="203,*" framespacing="1" frameborder="0" border="0" bordercolor="#333366">
			<frame src="<%=root%>/cpreport/left.jsp" name="leftFrame" noresize>
			<frame src="<%=root%>/cpreport/welcome.htm" name="mainFrame">
		</frameset>
	
	
	<noframes>
		<body>

		</body>
	</noframes>
	
</html>
