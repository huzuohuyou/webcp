<!-- 
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：viewcp.jsp    
// 文件功能描述：显示本地临床路径定义信息
// 接收参数: cp_id   
// 创建人：潘状
// 创建日期：2011-7-25
// 修改日期:2011-7-26
//
//----------------------------------------------------------------*/ 
-->

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil" %>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil" %>
<% String url= LcpUtil.getConfigValue(PropertiesUtil.get("report.url")) ;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="../public/plugins/jquery/themes/cupertino/jquery-ui-1.8.11.custom.css"> 
	<script src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.core.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.tabs.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.mouse.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.button.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.draggable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.position.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.resizable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="../public/plugins/jquery/jquery.acts_as_tree_table.js"></script>
<title>本地路径信息查看</title>
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
	background:#F2F5F7;
	
	}

.STYLE10 {color: #000000; font-size: 12px;font-weight:normal; }

a { font-size:12px}

.ui-dialog-title{ font-size:12px}

tr{height: 20}
-->
</style>
<script>     
<%  String cp_id=request.getParameter("cp_id");   %>
var cp_id_quanju="<%=cp_id%>";
var cp_id=<%=cp_id%>;
var  highlightcolor='#d5f4fe';
var  clickcolor='#51b2f6';
var recoveryColor='#FFFFFF';

var bgcolor="#51b2f6";
var tempColor="";

function changeColor(event){
	tempColor=event.bgColor;
	event.bgColor=highlightcolor;
	
	}

function recoverColor(event){
	event.bgColor=tempColor;
	tempColor=recoveryColor;
	}
	

function NodeColor(event){
	var trs=$(".selectNode");
	var trsleng=trs.length;
	for(var i=0;i<trsleng;i++){
		var tr=trs[i];
		tr.bgColor=recoveryColor;
	}
	event.bgColor=clickcolor;
	tempColor=event.bgColor;
	$("#wait").css("display","");
	 $("#tabsLoad").load("../version/nodetab.jsp",{cp_id:cp_id,cp_node_id:event.id},hide);
	 $("#tabs").tabs('select', 0);
	
}

function hide(){
	$( "#wait" ).dialog( "close" );
}

$(function() {
	
	$( "#wait" ).dialog({
		autoOpen: false,
		height: 350,
		width: 350,
		modal: true,
		title:"信息正在加载中,请稍候……"
		});
	$("#wait").dialog("open");
	// $('#fragment-7').html('<iframe src= <%=url%>/report/ReportEmitter?rpt=%C2%B7%BE%B6%B1%A8%B1%ED.brt&params=cp_id='+cp_id_quanju+' frameborder="0" height="450" width='+($(document).width()-20)+' id="dialogFrame" scrolling="no"></iframe>');
	 $('#fragment-7').html('<iframe src=../ReportEmitter?rpt=%C2%B7%BE%B6%B1%A8%B1%ED.brt&params=cp_id='+cp_id_quanju+' frameborder="0" height="450" width='+($(document).width()-20)+' id="dialogFrame" scrolling="no"></iframe>');
	 $('#fragment-8').html('<iframe src=../charts/Chart.jsp?cp_id='+cp_id_quanju+' frameborder="0" height="450" width='+($(document).width()-20)+' id="dialogFrame" scrolling="no"></iframe>');
    $("#tabs1").tabs();
    $('#tabs1').bind('tabsselect', function(event, ui) {
    	if(ui.index==3){
    		 $("#tabs").tabs('select', 0);
    	}
    	});
		$.ajax({	   
			url : "../servlet/manage",
			type : 'POST',
			async:true,
			data : {
				op : "select",
				cp_id : cp_id
			},
			dataType : "json",
			complete : show_result,
			success : onDataReceived
		});
		});	

function loadMaster(trName,trId){
	 $("#tabsLoad").load("../version/nodetab.jsp",{cp_id:cp_id,cp_node_id:trId});
		 $("#tabs").tabs('select', 0);
}
var show_result = function(XMLHttpRequest, textStatus){	
	var msg;
	if(textStatus == "error"){msg = "请求出错！";ajaxIsSuc=false;}
	else if(textStatus == "timeout"){ msg = "请求超时！"; ajaxIsSuc=false;}
	else if(textStatus == "parsererror"){ msg = "JSON数据格式有误！"; ajaxIsSuc=false;
	}else if (textStatus != "success"){ alert(msg); ajaxIsSuc=false;
	}else if(textStatus == "success"){	 ajaxIsSuc=true;	}
	$("#wait").hide();
};

var onDataReceived = function(data, textStatus, XMLHttpRequest){
		data = eval(data);
		data = data[0];
		hide();
	
		$("#cp_id_input").html(data.cp_code);
		$("#cp_name_input").html(data.cp_name);
		 $("#cp_start_date_input").html(data.cp_start_date);
		$("#cp_stop_date_input").html(data.cp_stop_date);
		$("#cp_status_input").html(data.cp_status);
		$("#cp_create_date_input").html(data.cp_create_date);
		$("#cp_version_input").html(data.cp_version);
		$("#cp_days_input").html(data.cp_days);
		$("#cp_version_input").html(data.cp_version);
		$("#user_name_input").html(data.cp_create_name);
		$("#cp_version_date_input").html(data.cp_version_date);
		$("#cp_fee_input").html(data.cp_fee);
		$("#cp_days_max").html(data.cp_days_max);
		$("#cp_days_min").html(data.cp_days_min);
		$("#dept_name").html(data.dept_name);
		$("#dept_code").html(data.dept_code);
		$("#input_py").html(data.py);
		$("#input_wb").html(data.wb); 
		$("#vs_cp_state").html(data.vs_cp_state); 
		
		$("#cp_treatment").html(data.cp_treatment);//填充治疗方案依据
		$("#cp_diagnosis_based").html(data.cp_diagnosis_based);//填充诊断方案依据
		
		$("#cp_antibiotics").html(data.cp_antibiotics);//填充诊断方案依据 */
		$("#fragment-2").load("income.jsp", {cp_id : cp_id});
		$("#fragment-6").load("discharge.jsp", {cp_id : cp_id});
		$("#master_left").load("nodelist.jsp", {cp_id : cp_id});
		
};
</script>
</head>

<body >


<div id="tabs1" style="width:auto">
    <ul>
        <li><a href="#fragment-1">路径基本信息</a></li>
        <li><a href="#fragment-2">路径纳入/排除条件</a></li>
        <li><a href="#fragment-3">诊断与治疗方案依据</a></li>
        <li><a href="#fragment-4">路径制定</a></li>
        <li><a href="#fragment-5">预防性抗菌药物与使用时机</a></li>
        <li><a href="#fragment-6">出院标准</a></li>
        <li><a href="#fragment-7">执行单预览</a></li>
        <li><a href="#fragment-8">路径流程图</a></li>
       
        
  </ul>
 <div id="fragment-1" style="height:350">
				<table width="750" border="0" style="font-size: 14px;">
					<tr>
						<th height="30" align="right">路径名称：</th>
						<td width="232"><span id="cp_name_input"> </span>
						</td>
						<th width="118" align="right">路径编码：</th>
						<td width="200"><span id="cp_id_input"> </span>
						</td>
					</tr>
					<tr>
						<th height="30" align="right">拼音简码：</th>
						<td><span id="input_py"> </span>
						</td>
						<th align="right">创建时间：</th>
						<td><span id="input_wb"> </span>
						</td>
					</tr>
					<tr>
						<th height="30" align="right">路径版本：</th>
						<td><span id="cp_version_input"> </span>
						</td>
						<th align="right">平均费用：</th>
						<td><span id="cp_fee_input"> </span>
						</td>
					</tr>
					<tr>
						<th height="30" align="right">平均住院日：</th>
						<td><span id="cp_days_input"> </span>
						</td>
						<th align="right">最大住院日：</th>
						<td><span id="cp_days_max"> </span>
						</td>
					</tr>
					<tr>
						<th height="30" align="right">最少住院日：</th>
						<td><span id="cp_days_min"> </span>
						</td>
						<th align="right">所属科室：</th>
						<td><span id="dept_name"> </span>
						</td>
					</tr>
					<tr>
						<th height="30" align="right">使用权限：</th>
						<td><span id="vs_cp_state"> </span>
						</td>
						<th align="right">路径状态：</th>
						<td><span id="cp_status_input"> </span>
						</td>
					</tr>
				</table>
			</div>

<div id="fragment-2" style="height:350; width:95%"></div>
 
<div id="fragment-3" style="height:350; width:95%">
			<label><font color="#00AEAE" style="font-size: 16px;">诊断依据:</font>
			</label>
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<br>
			<div id="cp_diagnosis_based" style="height: 100; font-size: 14px"></div>

			<br> <label><font color="#00AEAE" style="font-size: 16px;">治疗方案依据：</font>
			</label> <br />
			<div id="cp_treatment" style="height: 200; font-size: 14px"></div>

		</div>
 	 <div id="fragment-4" style="height:350; width:95%" >
 	 <table width="100%" height="100%" border="1" cellpadding="3" cellspacing="3">
 	       <tr >
 	         <td width="36%" height="350" align="left"  valign="top">路径执行列表：<br />
 	           <div id='master_left'></div></td>
 	         <td width="64%" rowspan="2" valign="top" style="height:auto"><div id="tabsLoad">
 	           <div id="tabs"  style="height:auto">请选择左侧节点加载内容
	 	           <div id="frag-1"></div>
	 	           <div id="frag-2"> </div>
	 	           <div id="frag-3"> </div>
	 	           <div id="frag-4"></div>
	 	           <div id="frag-5"></div>
 	           </div></div></td>
            </tr>
 	       <tr >
 	         <td height="47" align="left"  valign="top"><div align="center"></div></td>
            </tr>
         </table>
    </div>
    
 <div id="fragment-5"  style="height:350; width:95%"><span style="height:auto">
      <label><font color="#00AEAE" style="font-size: 16px;">预防性抗菌药物选择与使用时机：</font></label>
      &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<br />
	  </span>
	 <div id="cp_antibiotics" style="height:350; font-size:14px"></div>
</div>   
 <div id="fragment-6"  style="height:350; width:95%"></div>
 <div id="fragment-7"  style="height:350; width:95%"></div>
 <div id="fragment-8"  style="height:350; width:95%"></div>
 
</div>

<div id="wait" align="center"  style="display:none;width:500px;height:120px;position:absolute;top:35%;left:35%;padding:2px;overflow:hidden;">
	<!-- <span style="background:#FFFFFF;"  > 数 据 正 在 加 载 中,请 等 待  . . .</span>
	<br />
	<img src='../public/images/loading.gif' width="400" height="15" />  -->
</div>
</body>
</html>
