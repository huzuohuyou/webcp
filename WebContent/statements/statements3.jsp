<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%
	String rpt = "";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String riqi = sdf.format(new Date());
	String begintime = riqi.substring(0, 8) + "01";
	String height = request.getParameter("height");
	rpt = request.getParameter("rpt");
%>
<html>
<head>
<title>报表展示窗口</title>
<script type="text/javascript" src="../bios_web_res/component/date/WdatePicker.js"></script>
<script type="text/javascript" src="../public/plugins/jquery/jquery-1.7.1.min.js"></script>
</head>
<body>

	<center style="font-size: 15px">
		起始时间： <input type="text" name="text1" style="width: 100px" id="text1" readonly="true" value="<%=begintime%>"
									onfocus="WdatePicker({isShowWeek:true})"> &nbsp;&nbsp;
			结束时间： <input type="text" name="text2" style="width: 100px" id="text2" readonly="true"
									value="<%=riqi%>" onfocus="WdatePicker({isShowWeek:true})"> 
		<label> <input type="button" name="button" onclick="subs()" id="button" value="查看"> </label>
	</center>
<script language="javascript"> 
var calendar;
function subs(){
	var start_time = document.getElementById('text1');
	var end_time = document.getElementById('text2');
	var d1Arr=start_time.value.split('-');
	var d2Arr=end_time.value.split('-');
	var dt1=new Date(d1Arr[0],d1Arr[1],d1Arr[2]);
	var dt2=new Date(d2Arr[0],d2Arr[1],d2Arr[2]);
    if(dt1>dt2){//比较日期
       alert("开始日期不能晚于结束日期!");
       return;
    }
	  baobiao.location.href='../ReportEmitter?rpt=<%=rpt%>.brt&params=<%=""%>start_time='+ start_time.value+ ';end_time='+ end_time.value;
  }
	</script>
	<div>
		<iframe id="baobiao" frameBorder=0 scrolling=no width="100%" height="100%" src="../ReportEmitter?rpt=<%=rpt%>.brt&params=<%=""%>"></iframe>
	</div>
</body>
</html>