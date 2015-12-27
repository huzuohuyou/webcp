<%
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：order.jsp  
// 文件功能描述：院内医嘱下发页面
// 接收参数: 病人IDpatient_no   住院号admissTimes  医生工号doctorNo  病区patientArea
// 创建人：段英华
// 创建日期：2011-8-25
// 修改日期：2011-8-25
// 完成日期：
//----------------------------------------------------------------*/ 
%>

<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@page import="com.goodwillcis.lcp.util.CommonUtil"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	final int HOSPITALID=LcpUtil.getHospitalID();
	DatabaseClass db=LcpUtil.getDatabaseClass(); 
	//病人ID
	String patientID=request.getParameter("patient_no");
	//住院次数
	String admissTimes = request.getParameter("admissTimes");
	
	//纳入医嘱调用地址
	
	String order = PropertiesUtil.get(PropertiesUtil.ORDER_URL);
	String orderAddress = LcpUtil.getConfigValue(order);
	
	//不纳入医嘱调用地址
	String nullOrder = "";//PropertiesUtil.get(PropertiesUtil.NULLORDER_URL);
	String nullOrderAddress = LcpUtil.getConfigValue(nullOrder);
	
	//不纳入医嘱但是调到肿瘤病区地址
	String nullOrder1 = "";//PropertiesUtil.get(PropertiesUtil.NULLORDER1_URL);
	String nullOrderAddress1 = LcpUtil.getConfigValue(nullOrder1);
	
	//老年病一病区和二病区调用医嘱地址
	String nullOrder2 = "";//PropertiesUtil.get(PropertiesUtil.NULLORDER2_URL);
	String nullOrderAddress2 = LcpUtil.getConfigValue(nullOrder2);
	

	//路径表内查询ID
	String patientId1 = patientID + "_" + admissTimes;	

	//医生工号
	String doctorNo = request.getParameter("doctorNo");
	//病区
	String patientArea = request.getParameter("patientArea");
	//医生级别
	String doctorQX = request.getParameter("doctorQX");
	String alertMsg = "";
	if(doctorQX.equals("0")){
		alertMsg = "请注意：您所在的医生类别不可以下抗生素相关医嘱！";
	}else if(doctorQX.equals("1")){
		alertMsg = "请注意：您所在的医生类别不可以下高级抗生素相关医嘱！";
	}
	
	
	String dosageSql = "select code, unit from lcp_local_order_dosageunits";
	 DataSetClass ds2 = db.FunGetDataSetBySQL(dosageSql);
	String freSql = "select code,comm from lcp_local_order_frequency";
	 DataSetClass ds3 = db.FunGetDataSetBySQL(freSql);
	String waySql = "select supply_code,supply_name from lcp_local_order_way";
	DataSetClass ds4 =  db.FunGetDataSetBySQL(waySql);
		

	String cpNodeID="";
	String cpID="";
	String cpName = "";
	String cpNodeName="";
	String json = "";

	if(patientID==null||"".equals(patientID)){
		response.sendRedirect("../route/error.html"); 
	}else{
		
		String sql="select * from lcp_patient_node where cp_node_id in "+
					"(SELECT max(cp_node_id) as CP_NODE_ID FROM lcp_patient_node T WHERE T.PATIENT_NO='"+
					patientId1+"' and HOSPITAL_ID="+HOSPITALID+") and PATIENT_NO='"+patientId1+"' and HOSPITAL_ID="+HOSPITALID;


		DataSetClass ds=db.FunGetDataSetBySQL(sql);
   	   
		
		int row=ds.FunGetRowCount();
		//如果能取到值，表示改患者在路径里，取出对应路径信息
		if(row>0){
			cpID=ds.FunGetDataAsStringByColName(0,"CP_ID");
			//取出路径名字
			cpName = db.FunGetDataSetBySQL("select CP_NAME from lcp_master  where CP_ID = "+cpID).FunGetDataAsStringById(0,0).toString();
			cpNodeID=ds.FunGetDataAsStringByColName(0,"CP_NODE_ID");
			//取出路径节点名字
			cpNodeName=ds.FunGetDataAsStringByColName(0,"CP_NODE_NAME");
			//查是否有数据同步过来，如果没有数据则直接跳到医嘱系统页面，解决某些患者退出后进入医嘱下发页面是空页面的问题。
			String orderSql = "select count(*) as AA from lcp_patient_order_item t where t.patient_no = '"+patientId1+"' and t.cp_id = "+cpID+" and t.cp_node_id = "+cpNodeID;
			
			int dataNum = db.FunGetDataSetBySQL(orderSql).FunGetDataAsNumberById(0,0).intValue();
			if(dataNum <=0 ){
				String orderAddress1 = nullOrderAddress + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1";
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+orderAddress1+"';     </script>"
						); 
			}
			
			
		//如果不在路径 则直接跳到医嘱页面	
		//这个是为肿瘤科室特别准备的地址
		}else if(patientArea.equals("1200200")){
			String  orderAddress1 = nullOrderAddress1 + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1";
			response.getWriter().print(
					" <script language='javascript' type='text/javascript'>           window.location.href='"+orderAddress1+"';     </script>"
					); 
		//如果是老年科一病区或者二病区的，则转到下边的地址。
		}else if(patientArea.equals("1060000") || patientArea.equals("1060002")){
			String orderAddress2 = nullOrderAddress2 + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1";
			response.getWriter().print(
					" <script language='javascript' type='text/javascript'>           window.location.href='"+orderAddress2+"';     </script>"
					); 
		} else {
			String orderAddress1 = nullOrderAddress + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1";
			response.getWriter().print(
					" <script language='javascript' type='text/javascript'>           window.location.href='"+orderAddress1+"';     </script>"
					); 
		}
	}
	    
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="../public/plugins/jquery/themes/cupertino/jquery-ui-1.8.11.custom.css"> 
	<script src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.core.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.tabs.js"></script>
	<script src="../public/plugins/jquery/external/jquery.bgiframe-2.1.2.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>

	<script src="../public/plugins/jquery/ui/jquery.ui.mouse.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.button.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.draggable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.position.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.resizable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.dialog.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.effects.core.js"></script>
	<script type="text/javascript" src="../public/plugins/jquery/jquery.acts_as_tree_table.js"></script>
<title>临床路径医嘱下发页面</title>
<script type="text/javascript">

var highlightcolor='#d5f4fe';
var clickcolor='#51b2f6';
var recoveryColor='#FFFFFF';
var bgcolor="#51b2f6";
var tempColor="";
var orderIds = "";

function changeColor(event){
	tempColor=event.bgColor;
	event.bgColor=highlightcolor;
	}

function recoverColor(event){
	event.bgColor=tempColor;
	tempColor=recoveryColor;
	}

//双击
function ondblclickLoad(event){
	
	$("tr").each(function(){
		changeSignNull(this);
	});
	var tr=$(event);
	var data = tr.children().eq(1).children().eq(0).children().eq(1).children().eq(0).val();
	var   strArray=new   Array();
	strArray=data.split("|"); 
	for(var i =0; i<strArray.length; i++){
		strArray1 = strArray[i].split(",");
		$("tr").each(function(){
			var orderId = $(this).children().eq(0).attr("name");
			var orderItemId = $(this).children().eq(0).attr("id");
			if(strArray1[0] === orderId && strArray1[1] === orderItemId){
				changeSign(this);
				}
			}
		);	
		
	}
	$("#div1").dialog("close");
	
}

//打开已经下发的医嘱列表
function openRecord(){
	window.open("record.jsp");
}


//全选
function selectAll(event){
	
	$("tr").each(function(){
		var nodeCode = $(this).children().eq(0).attr("id");
		
		if(nodeCode != ""){
		changeSign(this);
		}
	});	
}
//选中所有未执行的项
function selectOthers(event){
	
	$("tr").each(function(){
		var exestate1 = $(this).attr("exestate1");
		
		if(exestate1 == ""){
		changeSign(this);
		}
	});	
}

//全不选
function unSelectAll(event){

	$("tr").each(function(){
		changeSignNull(this);
	});	
}

//保存为模板按钮
function saveSchema(){

	$("tr").each(function(){
		var html=$(this).children().eq(7).html();
		if(html !="" && html != "选择" && html != null){
			orderIds +=  $(this).children().eq(0).attr("name") + "," ; 	
			orderIds +=  $(this).children().eq(0).attr("id") + "|";
			
		}
		
	});
	orderIds=orderIds.substr(0,orderIds.length-1);
	$("#div").dialog("open");

}

//选择方案按钮
function selectSchema(){
	$("#table").load("../servlet/NodeUpdate",{op:"getSchema",cpId:"<%=cpID%>",cpNodeId:"<%=cpNodeID%>",doctorNo:"<%=doctorNo%>"});
	$("#div1").dialog("open");
}

//折叠操作
function lap(event){
	var x = $(event).children().eq(0).html();
	var y = $(event).children().eq(0).attr("orderid");
	if(x == "-"){
		$($(event).children().eq(0)).html("+");
		var trs=$("tr[name^='"+ y +"_']");
		trs.hide();
	}else{
		$($(event).children().eq(0)).html("-");
		var trs=$("tr[name^='"+ y +"_']");
		trs.show();
	}

	
}
//把焦点转移到底部
function move(){	 
	commit.focus();
 }


//执行保存操作
function save1(){
	var comments = "";
	msg=$("#comment").val();
	$.ajax({
	   url: "../servlet/NodeUpdate",
	   type: "POST",
	   async: false,
	   data: {op:"updateOrder",
		   cpId:"<%=cpID%>",
		   cpNodeId:"<%=cpNodeID%>",
		   doctorNo:"<%=doctorNo%>",
		   orderIds: orderIds,
		   comments: msg
		   
		   
 	},
	   dataType: "json",
	   complete: show_result,
	   success: function(data, textStatus, XMLHttpRequest){
	   }
 });
}





function NodeColor(event){
	var a=event.bgColor;
	event.bgColor=(tempColor==='#51b2f6'?recoveryColor:clickcolor);
	tempColor=event.bgColor;
	var html="<img src='../public/images/success.png' width='18' height='18'/>";
	
	var check=(tempColor==='#51b2f6'?html:"");
	$($(event).children().eq(7)).html(check);
	
}


function changeSign(event){
	var html="<img src='../public/images/success.png' width='18' height='18'/>";

	$($(event).children().eq(7)).html(html);

}

function changeSignNull(event){
		$($(event).children().eq(7)).html("");	
}

function NodeColorOne(event){
	var html="<img src='../public/images/success.png' width='18' height='18'/>";
	var check=(tempColor===clickcolor?"":html);
	var name=event.id.split("_")[0];
	var trs=$("tr[id^="+name+"]");
	var trsleng=trs.length;
	for(var i=0;i<trsleng;i++){
		var tr=trs[i];
		tr.bgColor=recoveryColor;
		$($(tr).children().eq(7)).html("");
	}
	$($(event).children().eq(7)).html(check);
	event.bgColor=(tempColor===clickcolor?recoveryColor:clickcolor);
	tempColor=event.bgColor;
}

jQuery.fn.Scrollable = function(tableHeight, tableWidth) {
	this.each(function(){
		if (jQuery.browser.msie || jQuery.browser.mozilla) {
			// var table = new ScrollableTable(this, tableHeight, tableWidth);
		}
	});
};

var checkTrs1 = "";
var checkTrs2 = "";
var checkTrs3 = "";
var order = "";
var jianyan = "";
var jiancha = "";
var other = "";
var jsons = "";
var cpID = "";

var lastState = "";
$(document).ready(function(){
	jQuery('table').Scrollable(550, 900);
	//保存方案时的DIV
	$("#div").dialog({
		title:"请填写一个备注信息",
		autoOpen: false,
		height: 280,
		width: 370,
		modal: true,
		buttons: {
			"确定": function() {
				save1();
				alert("保存成功");
				$( this ).dialog( "close" );
			},
			"取消": function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
		}
	});
	//选择方案时的DIV
	$("#div1").dialog({
		title:"方案列表",
		autoOpen: false,
		modal: true,
		height:300,
		width:500
	});
	
	$("#sub").click(function(){
	checkTrs="";

	$("tr").each(function(){
		var html=$(this).children().eq(7).html();
		var typeName = $(this).children().eq(3).html();
		//判断本次操作里发生变化的所有值
		if(html !="" && html != null){

			if(typeName == "检验" ){
				
				checkTrs1+="{";
				checkTrs1+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
				checkTrs1+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
				checkTrs1+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
				checkTrs1+="'orderKind':'"+$(this).children().eq(3).html()+"',";
				checkTrs1+="'stateItem':'"+$(this).children().eq(5).html()+"',";
				checkTrs1+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
				checkTrs1+="'measure':'"+$(this).children().eq(8).html()+"',";
				checkTrs+="'frequency':'"+$(this).children().eq(9).attr("frequency")+"',";
				checkTrs1+="'way':'" +$(this).children().eq(10).attr("way")+"',";
				checkTrs1+="'dosage':'" +$(this).children().eq(11).html()+"',";
				checkTrs1+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
				
				checkTrs1+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
				checkTrs1+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
			    

			}else if(typeName == "检查"){
				
				checkTrs2+="{";
				checkTrs2+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
				checkTrs2+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
				checkTrs2+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
				checkTrs2+="'orderKind':'"+$(this).children().eq(3).html()+"',";
				checkTrs2+="'stateItem':'"+$(this).children().eq(5).html()+"',";
				checkTrs2+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
				checkTrs2+="'measure':'"+$(this).children().eq(8).html()+"',";
				checkTrs2+="'frequency':'"+$(this).children().eq(9).attr("frequency")+"',";
				checkTrs2+="'way':'" +$(this).children().eq(10).attr("way")+"',";
				checkTrs2+="'dosage':'" +$(this).children().eq(11).html()+"',";
				checkTrs2+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
				checkTrs2+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
				checkTrs2+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
				

			}else if(typeName != "类别"){
				
				checkTrs3+="{";
				checkTrs3+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
				checkTrs3+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
				checkTrs3+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
				checkTrs3+="'orderKind':'"+$(this).children().eq(3).html()+"',";
				checkTrs3+="'stateItem':'"+$(this).children().eq(5).html()+"',";
				checkTrs3+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
				checkTrs3+="'measure':'"+$(this).children().eq(8).html()+"',";
				checkTrs3+="'frequency':'"+$(this).children().eq(9).attr("frequency")+"',";
				checkTrs3+="'way':'" +$(this).children().eq(10).attr("way")+"',";
				checkTrs3+="'dosage':'" +$(this).children().eq(11).html()+"',";
				checkTrs3+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
				checkTrs3+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
				checkTrs3+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
				
				}
			}
			
		
	});	
	
	checkTrs1=checkTrs1.substr(0,checkTrs1.length-1);

	checkTrs2=checkTrs2.substr(0,checkTrs2.length-1);

	checkTrs3=checkTrs3.substr(0,checkTrs3.length-1);

	if(checkTrs1 != ""){
		jianyan = "'inspect':["+checkTrs1+"],";
	}
	if(checkTrs2 != ""){
		jiancha = "'check':["+checkTrs2+"],";
	}
	if(checkTrs3 != ""){
		other = "'advice':["+checkTrs3+"],";
	}
	order = jianyan + jiancha + other;
	
	order = order.substr(0,order.length-1);
	if(order == ""){
		jsons = "{'inpatientNo':'<%=patientID%>','cpId':'<%=cpID%>','cpNodeId':'<%=cpNodeID%>','admissTimes':'<%=admissTimes%>','doctorNo':'<%=doctorNo%>','patientArea':'<%=patientArea%>','doctorQX':'<%=doctorQX%>'}";
	}else{
		jsons="{'inpatientNo':'<%=patientID%>','cpId':'<%=cpID%>','cpNodeId':'<%=cpNodeID%>','admissTimes':'<%=admissTimes%>','doctorNo':'<%=doctorNo%>','patientArea':'<%=patientArea%>','doctorQX':'<%=doctorQX%>',"+ order +"}";
	}
	json = jsons;
	<%
	 java.net.URLEncoder.encode(json);
	%>

	//往后台提交选中状态
//	$.ajax({
//		   url: "../servlet/NodeUpdate",
//		   type: "POST",
//		   async: false,
//		   data: {op:"updateOrder",
//			   cpId:"<%=cpID%>",
//			   nodeId:"<%=cpNodeID%>",
//			   orderIds: orderIds,
//			   patientNo:"<%=patientId1%>"
//	    	},
//		   dataType: "json",
//		   complete: show_result,
//		   success: function(data, textStatus, XMLHttpRequest){
//		   }
//	 });


//中山医院json格式[{患者编号:123},{cpID:1},{cpNodeID:2},{项目:[{项目编号:aaaa,频次:bbbb,计量:cccc,项目类型:检查|检验|护理|处置},{项目编号:aaaa,频次:bbbb,计量:cccc,项目类型:检查|检验|护理|处置}]}]
  	document.write("<form id='post1' name='post1' action='<%=orderAddress%>' method='POST'>"); 
 	document.write('<input type="hidden" name="order" value="'+json+'" />');
 	document.write('</form>');  
 	document.getElementById('post1').submit();
	
	
//	window.location.href="<%=orderAddress%>?order="+json;


	
	});
});
var show_result = function(XMLHttpRequest, textStatus){
 	var msg = "";
 	if(textStatus == "error"){
 	 	msg = "请求出错！";
 	}
 	else if(textStatus == "timeout"){
 	 msg = "请求超时！";
 	}
 	else if(textStatus == "parsererror"){
 	 msg = "JSON数据格式有误！";
 	}
 	if (textStatus != "success"){
 		ajax=false;

 	}
   
};

</script>
<style type="text/css">
<!--
/* 树形表格样式*/
.acts_as_tree_table tr td .expander {
	background-image: url(../public/plugins/jquery/images/bullet_toggle_minus.png);
	background-position: bottom right ;
	background-repeat: no-repeat;
	cursor: pointer;
	padding: 1;
	zoom: 1; /* Hack for IE, works in IE7, I refuse to check in IE6 or older  调整加减号图片大小*/
}

.acts_as_tree_table tr.collapsed td .expander {
	background-image: url(../public/plugins/jquery/images/bullet_toggle_plus.png);
}

.acts_as_tree_table tr.expanded td .expander {
	background-image: url(../public/plugins/jquery/images/bullet_toggle_minus.png);
}
/*--- 树形表格样式----*/

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
-->
</style>

</head>

<body >
<h1 align="center" style="font-size:25px;"><%=cpName %>临床路径医嘱方案(<%=cpNodeName %>)</h1>
 <br>
 <h2 align="center" style="font-size:18px; color:red;"><%=alertMsg %></h2>

<div style="font-size:14px;" align="center">
 &nbsp;&nbsp;&nbsp;
 状态说明: 
 
 <img src="../public/images/detail_s3.png" height="16" width="16"/>已执行

&nbsp;&nbsp;&nbsp; 
 必选说明:
 <img src="../public/images/detail_s4.png" height="16" width="16"/>单项选一
  &nbsp;&nbsp;&nbsp; 
 <img src="../public/images/detail_s5.png" height="16" width="16"/>此项目必选,否则填变异
  &nbsp;&nbsp;&nbsp; 
  患者编码:<strong><%=patientID%></strong>&nbsp;
    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp; 
<!--    <input type="button" id="sub" value="提交"/> -->

<input type="button"  onclick="selectSchema()"; value="选择模板"/> 
<input type="button"  onclick="openRecord()"; value="查看已下发医嘱"/> 
<input type="button"  onclick="move()"; value="跳至底部"/>  
<input type="button"  onclick="selectAll(this)"; value="全选"/>  
<input type="button"  onclick="unSelectAll()"; value="全不选"/>
<input type="button"  onclick="selectOthers()"; value="选中未执行项"/> 
<br/>
</div>
<!-- style=" height:550px; width:80%;OVERFLOW-x:hide; overflow-y:auto"  -->
<div align="center">

    <table width="1120" id="main" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="frag-3" >
       <thead>
		<tr height="30" style=" font-size:14px;" bgcolor="d3eaef" >
		  <th width="20"  >&nbsp;</th>
		  <th width="40"  >状态</th>
		  <th width="80"  >类型</th>
		  <th width="80"  >类别</th>
		  <th width="240" >项目名称</th>
		  <th width="70"  >项目编码</th>
		  <th width="50"  >必选</th>
		  <th width="50" >选择</th>
		  <th width="100" >用量</th>
		  <th width="80" >频次</th>
		  <th width="80" >途径</th>
		  <th width="150" >药品一次使用剂量</th>
		  <th width="80" >剂量单位</th>		  
		  </tr>
        </thead>
        <tbody id="lcp_node_order_tbody">
       
    <% 
          String sql="select * from lcp_patient_order_point  "
					+  " where SYS_IS_DEL=0 and  cp_id="+cpID+" and cp_node_id="+cpNodeID+" and HOSPITAL_ID="+HOSPITALID+" and PATIENT_NO='"+patientId1+"' order by CP_NODE_ORDER_ID";

 		  String itemSql="select * from lcp_patient_order_item "
 						+"where sys_is_del=0 and cp_id="+cpID+" and cp_node_id="+cpNodeID+" and HOSPITAL_ID="+HOSPITALID+" and PATIENT_NO='"+patientId1+"' and CP_NODE_order_ID=";
 		  

 			
				try{ 
					DataSetClass ds=db.FunGetDataSetBySQL(sql);

					if(ds.FunGetDataAsStringById(0,0)!=""){
						for (int i = 0; i < ds.FunGetRowCount(); i++) {
							String cpNodeOrderId=ds.FunGetDataAsStringByColName(i,"CP_NODE_ORDER_ID");
							String needItem=ds.FunGetDataAsStringByColName(i,"NEED_ITEM");

							String cpNodeOrderText = ds.FunGetDataAsStringByColName(i, "CP_NODE_ORDER_TEXT");
							String autoItem = ds.FunGetDataAsStringByColName(i, "AUTO_ITEM");
							String kind=ds.FunGetDataAsStringByColName(i,"ORDER_KIND");
							String state=ds.FunGetDataAsStringByColName(i,"STATE_ITEM");
							String exe_State=ds.FunGetDataAsStringByColName(i,"EXE_STATE");
						
							DataSetClass ds1=db.FunGetDataSetBySQL(itemSql+cpNodeOrderId);
							String _needItem=("1".equals(needItem)?"<img src='../public/images/detail_s4.png' width='18' height='18'/>":"");
							String checkType=("1".equals(needItem)?"NodeColorOne(this)":"NodeColor(this)");
							String trName=("1".equals(needItem)?"trs"+cpNodeOrderId:"tr");
							String orderType="";
							if("0".equals(kind)){
								orderType="临时";
							}else if("1".equals(kind)){
								orderType="长期";
							}else if("2".equals(kind)){
								orderType="出院";
							}
			
 	     %>
         <tr id="node-<%= cpNodeOrderId%>"  height="25" bgcolor="#FFE4B5" style="font-size:14px; cursor:pointer;"  onclick="lap(this);">
           <td align="center" orderid="<%= cpNodeOrderId%>">-</td>
           <td align="center"></td>
           <td align="center"><span class="STYLE10"><%=orderType %></span></td>
			<td align="center"></td>
           	<td align="left" class="STYLE10" bgcolor="#FFE4B5">&nbsp;&nbsp;<%=cpNodeOrderText %></td>
           	<td align="left" class="STYLE10" bgcolor="#FFE4B5">&nbsp;</td>
           	<td align="center" class="STYLE10"><%=_needItem %></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
       	  </tr>
         
         
         <%if(ds1.FunGetDataAsStringById(0,0)!=""){
			for (int j = 0; j < ds1.FunGetRowCount(); j++) {
				String cpNodeOrderItemId=ds1.FunGetDataAsStringByColName(j,"CP_NODE_ORDER_ITEM_ID");
				String orderText=ds1.FunGetDataAsStringByColName(j,"CP_NODE_ORDER_TEXT");
				String need = ds1.FunGetDataAsStringByColName(j, "NEED_ITEM");
				String need1 = need;
				String auto = ds1.FunGetDataAsStringByColName(j, "AUTO_ITEM");
				//现在指定的院内医嘱编码y
				String orderNo = ds1.FunGetDataAsStringByColName(j, "ORDER_NO");
				String exeState = ds1.FunGetDataAsStringByColName(j, "EXE_STATE");
				
				String type=ds1.FunGetDataAsStringByColName(j,"ORDER_TYPE_NAME");
//				if(type.equals("1")){
//					type = "其他";
//				}else if(type.equals("2")){
//					type = "检查";
//				}else if(type.equals("0")){
//					type = "检验";
//				}
				
				String orderKind=ds1.FunGetDataAsStringByColName(j,"ORDER_KIND");
				String stateItem=ds1.FunGetDataAsStringByColName(j,"STATE_ITEM");
				String measure=ds1.FunGetDataAsStringByColName(j,"MEASURE");
				String frequency=ds1.FunGetDataAsStringByColName(j,"FREQUENCY");

				String way=ds1.FunGetDataAsStringByColName(j,"WAY");

				String dosage=ds1.FunGetDataAsStringByColName(j,"DOSAGE");
				String dosageUnits=ds1.FunGetDataAsStringByColName(j,"DOSAGE_UNITS");

				String administration=ds1.FunGetDataAsStringByColName(j,"ADMINISTRATION");
				
				String way_name = CommonUtil.FunGetDataByValue(ds4, "SUPPLY_CODE", way, "SUPPLY_NAME_NAME");

				String frequency_name = CommonUtil.FunGetDataByValue(ds3, "CODE", frequency, "COMM");

				String dosageUnits_name = CommonUtil.FunGetDataByValue(ds2, "CODE", dosageUnits, "UNIT");

				String orderItemSetNo = ds1.FunGetDataAsStringByColName(j,"ORDER_ITEM_SET_ID");

				
			
				
				
				String _exeState=("1".equals(exeState)?"<img src='../public/images/detail_s3.png' width='18' height='18'/>":"");
				if("1".equals(need)&& _needItem == ""){need="<img src='../public/images/detail_s5.png' width='18' height='18'/>";
				}else { need="";}
				String orderTypeItem="";
				if("0".equals(orderKind)){
					orderTypeItem="临时";
				}else if("1".equals(orderKind)){
					orderTypeItem="长期";
				}else if("2".equals(orderKind)){
					orderTypeItem="出院";
				}
				String check="1".equals(stateItem)?"<img src='../public/images/success.png' width='18' height='18'/>":"";
			%>
		<tr align="center" class="child-of-node-<%= cpNodeOrderId%>" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="<%=checkType %>" exestate1="<%=_exeState %>"  selectId="<%=orderNo %>"  id="<%=trName+"_"+cpNodeOrderItemId%>" name="<%=cpNodeOrderId+"_"+cpNodeOrderItemId%>" style='cursor:pointer'>
		  <td id="<%=cpNodeOrderItemId %>" name="<%=cpNodeOrderId %>">&nbsp;</td>
		  <td><%=_exeState %></td>
		  <td class="STYLE10"><%=orderTypeItem %></td>
		  <td class="STYLE10"><%=type %></td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=orderText %></td>
          <td class="STYLE10" ><%=orderNo%></td>
          
          <td class="STYLE10" need="<%=need1 %>" exestate1="<%=_exeState %>"><%=need %></td>
          
          <td class="STYLE10" stateItem="<%=stateItem %>"><%=check %></td>
          
          <td class="STYLE10" orderItemSetNo="<%=orderItemSetNo%>"><%=measure %></td>
          
          <td class="STYLE10" frequency="<%=frequency%>"> <%=frequency_name%></td>
          
          <td class="STYLE10" way="<%=way%>"> <%=way_name %></td>
          
          <td class="STYLE10" unitId="<%=cpNodeOrderId+cpNodeOrderItemId%>"><%=dosage %></td>
          
          <td class="STYLE10" dosageUnits="<%=dosageUnits%>"> <%=dosageUnits_name%></td>
          </tr>
			
          <%}} }}}
				catch(Exception e){ System.out.println(e);
          	out.print("<script  type='text/javascript'> alert('网络异常!请联系管理员')</script>");
          } %>
        </tbody>
      </table>
</div>
  <div id="commit" style="height:30px" align="center">
    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
  <input type="button" style="width: auto; height: auto; font-size: 20px" id="sub" value="提交"/>
  <input type="button" style="width: auto; height: auto; font-size: 20px" onclick="saveSchema()" value="保存为模板"  />
  
<br/>
</div>
<div id="div">
	<textarea id="comment" rows="10" cols="50"></textarea>

</div>
<div id="div1">
	     <table width="100%" id="users" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    		<tr bgcolor="d3eaef" class="STYLE10" align="center">
		        <td width="10%" height="20"  >医嘱方案编号</td>
		        <td width="40%" height="20" >医嘱方案备注</td>
      		</tr>
      		<tbody id="table">
      		</tbody>
    	</table>
</div>


</body>
</html>