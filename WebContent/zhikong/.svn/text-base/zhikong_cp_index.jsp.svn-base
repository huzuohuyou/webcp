<%@page import="com.goodwillcis.lcp.model.ZhikongCpIndex"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String context=(String)session.getAttribute("zhikongCpIndexRadioContext");
if(context==null){
	context="";
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>质控路径信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="public/plugins/jquery/jquery-1.5.1.js"></script>
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
	<script type="text/javascript">
	var isOK=false;
	$(function() {
		$("#chaxun_context").focus();
		$("#chaxun_context").attr("value","<%=context%>");
		isOK=true;
	});
		function chaxun(){
			if(isOK){
				var auto=$(":radio[name='chaxun_radio'][checked]").val();
				document.all['queryFlag'].value=($(":radio[name='chaxun_radio'][checked]").val());
				document.all['queryValue'].value=($("#chaxun_context")[0].value);
				document.all['form_chaxun_111111'].submit();
			}
			
		}
		function tiaozhuan(page_no){
			var auto=$(":radio[name='chaxun_radio'][checked]").val();
			document.all['queryFlag'].value=($(":radio[name='chaxun_radio'][checked]").val());
			document.all['queryValue'].value=($("#chaxun_context")[0].value);
			document.all['pageNo'].value=(page_no);
			document.all['form_chaxun_111111'].submit();
		}
		
		function tiaozhuanzhiding(){
			var _pageNo=$("#tiaozhuan_daohang");
			var _pageNo=_pageNo[0].value;
			var auto=$(":radio[name='chaxun_radio'][checked]").val();
			document.all['queryFlag'].value=($(":radio[name='chaxun_radio'][checked]").val());
			document.all['queryValue'].value=($("#chaxun_context")[0].value);
			document.all['pageNo'].value=(_pageNo);
			document.all['form_chaxun_111111'].submit();
		}
	</script>
  </head>
  
  <body>
  	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="30" bgcolor="#353c44">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td height="20%">
	            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="6%" height="19" valign="bottom"><div align="center"><img src="public/images/tb.gif" width="14" height="14" /></div></td>
		                <td width="94%" valign="bottom"><span class="STYLE1">&nbsp;本院路径列表&nbsp;</span></td>
		              </tr>
	            	</table>
	            </td>
	            <td>
	            	<div align="left">
	            	<%
	            	String check=(String)session.getAttribute("zhikongCpIndexRadioCheck");
	            	String check_0="";
	            	String check_1="";
	            	String check_2="";
	            	String check_3="";
	            	String checked="checked='checked'";
	            	if(check==null||check.equals("0")){
	            		check_0=checked;
	            	}else{
		            	if(check.equals("1")){
		            		check_1=checked;
		            	}
						if(check.equals("2")){
							check_2=checked;          		
							            	}
						if(check.equals("3")){
							check_3=checked;
						}
	            	}
	            	
	            	%>
	            		<span class="STYLE1">搜索路径 >> 按 
	            			<input type="radio" name="chaxun_radio" <%=check_0%> value="0"/>编码
			              	<input type="radio" name="chaxun_radio" <%=check_1%> value="1"/>名称
			              	<input type="radio" name="chaxun_radio" <%=check_2%> value="2"/>拼音
			              	<input type="radio" name="chaxun_radio" <%=check_3%> value="3"/>五笔
			              	<input type="text" value="" id="chaxun_context" oninput="chaxun();" onpropertychange="chaxun();"/>
			            </span>
					</div>
					
	            </td>
	            <td width="20%">
	            </td>
	          </tr>
	        </table>
        </td>
      </tr>
     </table>
     <table width="100%" id="tcp" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      	<tr height="20">
	        <td width="7%"  bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">路径编码</span></div></td>
	        <td width="*" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">路径名称</span></div></td>
	        <td width="8%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">启用时间</span></div></td>
	        <td width="6%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">是否停用</span></div></td>
	        <td width="8%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">符合纳入病例</span></div></td>
	        <td width="6%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">纳入病例</span></div></td>
	        <td width="6%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">执行病例</span></div></td>
	        <td width="6%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">完成病例</span></div></td>
	        <td width="6%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">变异退出病例</span></div></td>
	        <td width="8%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">完成带变异病例</span></div></td>
	        <td width="6%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">未纳入病例</span></div></td>
	        <td width="6%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">不纳入病例</span></div></td>
	    </tr>
		<%
		
		ArrayList<ZhikongCpIndex> zhikongCpIndexs=(ArrayList<ZhikongCpIndex>)session.getAttribute("zhikongCpIndexs");
		if(zhikongCpIndexs!=null&&zhikongCpIndexs.size()>0){
			int row=zhikongCpIndexs.size();
			for(int i=0;i<row;i++){
				%>
				<tr bgcolor="#FFFFFF" class="STYLE19">
					<td height="24"><div align="center"><%=zhikongCpIndexs.get(i).getCp_code() %></div></td>
					<td><div align="center"><a href="ZhikongCpPatientIndexServlet?cp_id=<%=zhikongCpIndexs.get(i).getCp_id()%>"><%=zhikongCpIndexs.get(i).getCp_name() %></a></div></td>
					<td><div align="center"><%=zhikongCpIndexs.get(i).getStart_time() %></div></td>
					<td><div align="center"><%=zhikongCpIndexs.get(i).getIsStop() %></div></td>
					<td><div align="center"><%=zhikongCpIndexs.get(i).getMatchIncomeCase() %></div></td>
					<td><div align="center"><%=zhikongCpIndexs.get(i).getIncomeCase() %></div></td>
					<td><div align="center"><%=zhikongCpIndexs.get(i).getExecuteCase() %></div></td>
					<td><div align="center"><%=zhikongCpIndexs.get(i).getCompleteCase() %></div></td>
					<td><div align="center"><%=zhikongCpIndexs.get(i).getVariaExistCase() %></div></td>
					<td><div align="center"><%=zhikongCpIndexs.get(i).getCompleteCaseWithVaria()%></div></td>
					<td><div align="center"><%=zhikongCpIndexs.get(i).getNotIncomeCase()%></div></td>
					<td><div align="center"><%=zhikongCpIndexs.get(i).getAntiIncomeCase()%></div></td>
				</tr>

				
				<%
			}
		}
		
		%>		
    </table>
	<div id="pageHtmlDiv">
		<%=session.getAttribute("zhikongCpIndexPage") %>
	</div>
	<form method='post' id='form_chaxun_111111' action='ZhikongCpIndexServlet'>
	          	<input type="hidden" name='queryFlag' value=''>
	          	<input type="hidden" name='queryValue' value=''>
	          	<input type="hidden" name='pageNo' value=''>
	</form>
	
  </body>
</html>
