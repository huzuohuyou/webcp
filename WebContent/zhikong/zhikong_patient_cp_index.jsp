<%@page import="java.util.ArrayList"%>
<%@page import="com.goodwillcis.lcp.model.ZhikongCpPatientIndex"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList<ZhikongCpPatientIndex> zhikongCpPatientIndexs=(ArrayList<ZhikongCpPatientIndex>)session.getAttribute("zhikongCpPatientIndexs");
int row=0;
String cp_name="";
if(zhikongCpPatientIndexs!=null&&zhikongCpPatientIndexs.size()>0){
	row=zhikongCpPatientIndexs.size();
	cp_name=zhikongCpPatientIndexs.get(0).getPatientCpName();
}

String patient_name=(String)session.getAttribute("patient_name");
if(patient_name==null){
	patient_name="";
}

String ruyuantime=(String)session.getAttribute("ruyuantime");
if(ruyuantime==null){
	ruyuantime="";
}


%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="public/plugins/jquery/jquery-1.5.1.js"></script>
<script src="public/plugins/jquery/jquery-1.5.1.min.js"></script>
<script src="public/plugins/jquery/jquery-ui-1.8.11.custom.min.js"></script>
<script src="../public/plugins/jquery/ui/jquery.ui.datepicker.js"></script>
<title>临床路径分类查询</title>
	<link rel="stylesheet" href="public/plugins/FusionCharts/style.css" type="text/css" />
	<link rel="stylesheet" href="public/plugins/jquery/themes/cupertino/jquery-ui-1.8.11.custom.css" type="text/css"/>
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
<%

%>
<script type="text/javascript">
$(function() {
	$("#ruyuan_time_1").datepicker({
		autoSize:true,
		changeMonth: true,
		changeYear: true,
		monthNamesShort: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		dayNamesMin: ['日', '一', '二', '三', '四', '五', '六']
	});
});

var cp_id_js="<%=(String)session.getAttribute("cp_id")%>";
function tiaozhuan(aa){
	//alert(aa);
	var patient_name=$("#patient_name_1").val();
	var ruyuan_time=$("#ruyuan_time_1").val();
	document.all['patient_name'].value=(patient_name);
	document.all['ruyuan_time'].value=(ruyuan_time);
	document.all['page_no'].value=aa;
	document.all['form_chaxun_222222'].submit();
}
function tiaozhuanzhiding(){

	var _pageNo=$("#tiaozhuan_daohang");
	_pageNo=_pageNo[0].value;
	var patient_name=$("#patient_name_1").val();
	var ruyuan_time=$("#ruyuan_time_1").val();
	document.all['patient_name'].value=(patient_name);
	document.all['ruyuan_time'].value=(ruyuan_time);
	document.all['page_no'].value=_pageNo;
	document.all['form_chaxun_222222'].submit();
}
function chaxun(){
	//alert(aa);
	var patient_name=$("#patient_name_1").val();
	var ruyuan_time=$("#ruyuan_time_1").val();
	document.all['patient_name'].value=(patient_name);
	document.all['ruyuan_time'].value=(ruyuan_time);
	document.all['page_no'].value=1;
	document.all['form_chaxun_222222'].submit();
}
</script>
<body>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <!-- 住院10日的病例 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="font-size: 13px;">
	          <tr>
	            <td height="23">
	            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	              		<tr>
	                		<td width="24" height="19" valign="bottom"><div align="center"><img src="public/images/tb.gif" width="14" height="14" /></div></td>
	                		<td valign="bottom"><span class="STYLE1">&nbsp;<%=cp_name%>&nbsp;</span></td>
	              		</tr>
	            	</table>
	          	</td>
	            <td>
		            <div align="left">
		            	<%
		            	%>
	            		<span class="STYLE1">搜索路径 >> 按 
	            			人名
	            			<input type="text" id="patient_name_1" name="patient_name_1" value="<%=patient_name%>"/>
			              	入院时间
			              	<input type="text" id="ruyuan_time_1" name="ruyuan_time_1" value="<%=ruyuantime%>"/>
			              	<input type="submit" value="查询" onclick="chaxun();">
			            </span>
					</div>	
		        </td>
	          </tr>
	        </table>
      	</td>
      </tr>
    </table></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <tr>
  	<td width="3">&nbsp;</td>
    <td><table width="100%" id="tcp" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    <tbody id="tbcp">    
      <tr>
        <td width="10%" height="24" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">患者名称</span></div></td>
        <td width="10%" height="24" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">入院时间</span></div></td>
        <td width="10%" height="24" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">出院时间</span></div></td>
        <td width="20%" height="24" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">入住医院</span></div></td>
        <td width="10%" height="24" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">总费用</span></div></td>
        <td width="15%" height="24" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">路径状态</span></div></td>
        <td width="25%" height="24" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">纳入路径</span></div></td>        
      </tr>
	<%
		for(int i=0;i<row;i++){
			
	
	%>
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center"><a href="zhikong/zhikong_patient_td1.jsp?patient_no=<%=zhikongCpPatientIndexs.get(i).getPatientNo()%>&&cp_id=<%=zhikongCpPatientIndexs.get(i).getCp_id()%>"><%=zhikongCpPatientIndexs.get(i).getPatientName()%></a></div></td>
		<td><div align="center"><%=zhikongCpPatientIndexs.get(i).getPatientRuyuanTime()%></div></td>
		<td><div align="center"><%=zhikongCpPatientIndexs.get(i).getPatientChuyuanTime() %></div></td>
		<td><div align="center"><%=zhikongCpPatientIndexs.get(i).getHospitalName() %></div></td>
		<td><div align="center"><%=zhikongCpPatientIndexs.get(i).getPatientTotalFee()%></div></td>
		<td><div align="center"><%=zhikongCpPatientIndexs.get(i).getPatientCpStatus() %></div></td>
		<td><div align="center"><%=zhikongCpPatientIndexs.get(i).getPatientCpName()%></div></td>
		</tr>
	<%
	}
	%>
		
				
      </tbody>
    </table></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="1"></td>
  	<td width="3">&nbsp;</td>
  </tr>

</table>
<table width="100%">
	  <%=(String)session.getAttribute("zhikongCpPatientIndexPage")%>
</table>
	<form method='post' id='form_chaxun_222222' action='ZhikongCpPatientIndexServlet'>
       	<input type="hidden" name='patient_name' value=''>
       	<input type="hidden" name='ruyuan_time' value=''>
       	<input type="hidden" name='page_no' value=''>
       	<input type="hidden" name='cp_id' value='<%=(String)session.getAttribute("cp_id")%>'>
	</form>
</body>
</html>