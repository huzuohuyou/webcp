<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%
	String deptCode = "";
	String deptcodelist = "";
	String deptnamelist = "";
	ArrayList listname = new ArrayList();
	ArrayList listcode = new ArrayList();
	String rpt = "";
	//deptCode=(String)request.getSession().getAttribute("deptcode");
	DatabaseClass db1 = LcpUtil.getDatabaseClass();
	String deptsql = "select t.dept_code, t.dept_name  from dcp_sys_user t where t.dept_code > 0   and t.dept_code is not null  group by t.dept_code, t.dept_name ";
	DataSetClass ds = db1.FunGetDataSetBySQL(deptsql);
	int count = ds.FunGetRowCount();
	int rows=0;
	for (int i = 0; i < count; i++) {
		deptcodelist = ds.FunGetDataAsStringByColName(i, "DEPT_CODE");
		deptnamelist = ds.FunGetDataAsStringByColName(i, "DEPT_NAME");
		if(!deptcodelist.equals("1140003") && !deptcodelist.equals("1040011") && !deptcodelist.equals("1140001")){
			listname.add(deptnamelist);
			listcode.add(deptcodelist);
			rows++;
		}
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String riqi = sdf.format(new Date());
	String begintime = riqi.substring(0, 8) + "01";
	String height = request.getParameter("height");
	rpt = request.getParameter("rpt");
	//System.out.println("rpt=:"+rpt);
	String hospital_id = request.getParameter("hospital_id");
	String cp_id = request.getParameter("cp_id");
	String canshu = "";
	if (!"".equals(hospital_id) && hospital_id != null) {
		canshu += "hospital_id=" + hospital_id + ";";
	}
	if (!"".equals(cp_id) && cp_id != null) {
		canshu += "cp_id=" + cp_id + ";";
	}
%>
<html>
<head>
<title>报表展示窗口</title>
<script type="text/javascript" src="../bios_web_res/component/date/WdatePicker.js"></script>
</head>
<body>

	<center style="font-size: 15px">
		起始时间： <input type="text" name="text1" style="width: 100px" id="text1" readonly="true" value="<%=begintime%>"
									onfocus="WdatePicker({isShowWeek:true})"> &nbsp;&nbsp;
			结束时间： <input type="text" name="text2" style="width: 100px" id="text2" readonly="true"
									value="<%=riqi%>" onfocus="WdatePicker({isShowWeek:true})"> 
<%
 	if ("narulv".equals(rpt)) {
 %>按
  <select name='Select1'  onchange="slt()">
			<!-- -1代表默认查询所有科室 -->
			<option value='-1'>
				---所有科室---
				<%
				for (int j = 0; j < rows; j++) {
				%>
			
			<option value='<%=listcode.get(j)%>'><%=listname.get(j)%>
				<%
					}
				%>
			
		</select> 统计<%
						}
					%>
		<label> <input type="button" name="button" onclick="subs()" id="button" value="查看"> </label>
	</center>
	<script language="javascript">
var deptcodevalueyw="-1";
var deptcodevalueks="<%=deptCode%>";
var calendar;
function slt()
{
 var obj=document.getElementById("Select1");
 var i=obj.selectedIndex;
 deptcodevalueyw=obj.options[i].value;
 //alert(deptcodevalueyw);
}
function subs(){
	/* alert("begin...");
	 alert(deptcodevalueyw); */
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
	  baobiao.location.href='../ReportEmitter?rpt=<%=rpt%>.brt&params=<%=canshu%>start_time='+ start_time.value+ ';end_time='+ end_time.value+ ';deptcodevalue=' + deptcodevalueyw;
}
	</script>
	<div>
		<iframe id="baobiao" frameBorder=0 scrolling=no width="100%" height="100%" src="../ReportEmitter?rpt=<%=rpt%>.brt&params=<%=canshu%>"></iframe>
	</div>
</body>
</html>