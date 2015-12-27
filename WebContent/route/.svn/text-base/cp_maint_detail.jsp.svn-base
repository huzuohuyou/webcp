<%
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：cp_maint_detail.jsp  
// 文件功能描述：本地临床路径维护详细信息
// 接收参数: cp_id   
// 创建人：刘植鑫
// 创建日期：2011-8-1
// 修改日期：2011-8-4
// 完成日期：
//----------------------------------------------------------------*/ 
%>
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
	<script src="../public/plugins/jquery/external/jquery.bgiframe-2.1.2.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.mouse.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.button.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.draggable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.position.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.resizable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.dialog.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.effects.core.js"></script>
	<script src="../public/javascripts/match/map.js"></script> 
	<script type="text/javascript" src="../public/plugins/jquery/jquery.acts_as_tree_table.js"></script>
	<script type="text/javascript" src="../public/plugins/FusionCharts/FusionCharts.js"></script>

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
.ui-tabs { position: relative; padding: .1em; zoom: 1; } /* position: relative prevents IE scroll bug (element with position: relative inside container with overflow: auto appear as "fixed") */
.ui-tabs .ui-tabs-nav { margin: 0; padding: .1em .1em 0; }
.ui-tabs .ui-tabs-nav li { list-style: none; float: left; position: relative; top: 1px; margin: 0 .2em 1px 0; border-bottom: 0 !important; padding: 0; white-space: nowrap; }
.ui-tabs .ui-tabs-nav li a { float: left; padding: .2em 2px; text-decoration: none; }
.ui-tabs .ui-tabs-nav li.ui-tabs-selected { margin-bottom: 0; padding-bottom: 1px; }
.ui-tabs .ui-tabs-nav li.ui-tabs-selected a, .ui-tabs .ui-tabs-nav li.ui-state-disabled a, .ui-tabs .ui-tabs-nav li.ui-state-processing a { cursor: text; }
.ui-tabs .ui-tabs-nav li a, .ui-tabs.ui-tabs-collapsible .ui-tabs-nav li.ui-tabs-selected a { cursor: pointer; } /* first selector in group seems obsolete, but required to overcome bug in Opera applying cursor: text overall if defined elsewhere... */
.ui-tabs .ui-tabs-panel { display: block; border-width: 0; padding: 1em 1.4em; background: none; }
.ui-tabs .ui-tabs-hide { display: none !important; }
.ui-dialog { position: absolute; padding: .0em; width: 300px; overflow: hidden; }
.ui-dialog .ui-dialog-titlebar { padding: .0em 0em; position: relative;  }
.ui-dialog .ui-dialog-title { float: left; margin: .1em 16px .1em 0; } 
.ui-dialog .ui-dialog-titlebar-close { position: absolute; right: .3em; top: 50%; width: 19px; margin: -10px 0 0 0; padding: 1px; height: 18px; }
.ui-dialog .ui-dialog-titlebar-close span { display: block; margin: 1px; }
.ui-dialog .ui-dialog-titlebar-close:hover, .ui-dialog .ui-dialog-titlebar-close:focus { padding: 0; }
.ui-dialog .ui-dialog-content { position: relative; border: 0; padding: .1em 0.1em; background: none; overflow: auto; zoom: 1; }
.ui-dialog .ui-dialog-buttonpane { text-align: left; border-width: 1px 0 0 0; background-image: none; margin: .5em 0 0 0; padding: .3em 1em .5em .4em; }
.ui-dialog .ui-dialog-buttonpane .ui-dialog-buttonset { float: right; }
.ui-dialog .ui-dialog-buttonpane button { margin: .5em .4em .5em 0; cursor: pointer; }
.ui-dialog .ui-resizable-se { width: 14px; height: 14px; right: 3px; bottom: 3px; }
.ui-draggable .ui-dialog-titlebar { cursor: move; }
-->
</style>
<%
	String cp_id=request.getParameter("cp_id");
%>
<script>
var cp_id_quanju="<%=cp_id%>";
var cp_state_quanju="";// 路径启用停用标志位
var code_quanju="";
var flag_quanju="";
var cp_node_id_quanju="1";
var cp_node_table_id_quanju="1";
var cp_node_table_item_id_quanju="1";
var cp_or_node_quanju="1";//对照是cp级别的还是node级别的 1表示cp级别，2表示node级别
var tab_index_quanju="1";//点击的是第几个标签
var update_quanju="";
///对照使用变量结束
var highlightcolor='#d5f4fe';
var recoveryColor='#ffffff';
var clickcolor='#51b2f6';
var tempColor='#ffffff';
var url="../CpMaintainServlet";
var operate="getAllTable";
var async=true;
var cpIsForbid=0;
var dingshi_id="";
$(function() {
	async=true;
	$("#wait").show();
    $("#tabs1").tabs();
	$("#tabs").tabs();
	DBOperation();
		
	  $('#tabs1').bind('tabsselect', function(event, ui) {
	    	 if (ui.index == 6){
	    		 $('#fragment-7').html('<iframe src=../ReportEmitter?rpt=%C2%B7%BE%B6%B1%A8%B1%ED.brt&params=cp_id='+cp_id_quanju+' frameborder="0" height="450" width="100%" id="dialogFrame" scrolling="no"></iframe>');
	    	}else if(ui.index==7){
	    		 $('#fragment-8').html('<iframe src=../charts/Chart.jsp?cp_id='+cp_id_quanju+' frameborder="0" height="450" width="100%" id="dialogFrame" scrolling="no"></iframe>');
	    			
	    	}
	    	 
	  
	  
	  });
	  
	//$("#fragment-8").load("../charts/Chart.jsp", {cp_id : cp_id_quanju});
		
	 $( "#duizhao" ).dialog({
			title:"匹配工具",
			autoOpen: false,
			modal: true,
			height:600,
			width:900,
			minHeight:600,
			minWidth:900,
			resizable:false,
			draggable:true,
			modal : true,
			close: function(event, ui) {
				$('#duizhao').html('');
				operate="getOneNewCode";
				DBOperation();
			}
			});
	var aa=$('.tableText');
	
	
});
/**
 * 	刘植鑫 2011-05-18
 *	函数说明：
 */
function DBOperation(){
	
	$.ajax({
	    url: url,
	    type: 'POST',
	    async : async,
	    data: {op : operate,
	    	cp_id_ajax : cp_id_quanju,
	    	cp_state_ajax : cp_state_quanju,//启用停用路径状态
	    	cp_node_id_ajax : cp_node_id_quanju,
	    	cp_node_table_id_ajax : cp_node_table_id_quanju,
	    	cp_node_table_item_id_ajax : cp_node_table_item_id_quanju,
	    	cp_or_node_ajax : cp_or_node_quanju ,
	    	tab_index_ajax : tab_index_quanju,
	    	update_quanju_ajax : update_quanju
	    	},
	    dataType: "json",
	    complete: show_result,
	    success: onDataReceived
	  });
}
var msg = "";
var show_result = function(XMLHttpRequest, textStatus){
	
	if(textStatus == "error"){
	 	msg = "请求出错！";
	 	ajaxIsSuc=false;
	 	alert(msg);
	}
	else if(textStatus == "timeout"){
	 msg = "请求超时！";
	 ajaxIsSuc=false;
	 alert(msg);
	}
	else if(textStatus == "parsererror"){
	 msg = "JSON数据格式有误！";
	 alert(msg);
	 ajaxIsSuc=false;
	}else if (textStatus != "success"){
	 ajaxIsSuc=false;
	 msg = "失败";
	 alert(msg);
	}else if(textStatus == "success"){
	 ajaxIsSuc=true;
	}
	
  
};
var onDataReceived = function(data, textStatus, XMLHttpRequest){
	data = eval(data);
	data = data[0];
		if(data.method==="getAllTable"){
			if(data.result==="OK"){
				
				$("#cp_node_info").html(data.cp_info_table);
				$("#cpInclude").html(data.include_exclude_table);	
				$("#cpExclude").html(data.exclude_table);	
				$("#cpTreatment").html(data.trea_table);
				$("#cpDischarge").html(data.excharge_table);
				$("#cpTreatment").html(data.trea_table);
				$("#cpNode").html(data.node_table);
				//alert(data.node_exclude_table);
				$("#cpNodeInclude").html(data.node_include_table);
				$("#cpNodeExclude").html(data.node_exclude_table);
				$("#cpNodeDoctor").html(data.doctor_table);
				$("#cpNodeNurse").html(data.nurse_table);
				$("#cpNodeOrder").html(data.order_table);
				$("#cpNodeFamily").html(data.family_table);
					$("#cpAntibiotic").html(data.anti_table);
					$("#cpDia").html(data.dia_table);//填充治疗方案依据
					$("#cpTreatment").html(data.trea_table);//填充诊断方案依据
				if(data.isForbid==="false"){
					cpIsForbid=0;
				}else{
					cpIsForbid=1;
				}
				if(data.isUse==="false"){
					whenCpIsNotUse();
				}else{
					whenCpIsUse();
				}
			}else{
				alert("没有对应的路径信息");
			}
			$("#wait").hide();
		}
		if(data.method==="startOrEndCp"){
			if(data.result==="OK"){
				$("#cp_node_info").html(data.cp_info_table);
				if(data.isUse==="false"){
					whenCpIsNotUse();
				}else{
					whenCpIsUse();
				}
				alert("操作成功");
			}else{
				alert("操作失败");
			}
		}
		if(data.method==="getOneNewCode"){
			var id=cp_or_node_quanju+"_"+tab_index_quanju+"_"+cp_id_quanju+"_"+cp_node_id_quanju+"_"+cp_node_table_id_quanju+"_"+cp_node_table_item_id_quanju;
			//alert(id);
			$("#"+id).html(data.code);
		}
		if(data.method==="changeNode"){
			$("#cpNodeInclude").html(data.node_include_table);
			$("#cpNodeExclude").html(data.node_exclude_table);
		//	alert(data.doctor_table);
			$("#cpNodeDoctor").html(data.doctor_table);
			$("#cpNodeNurse").html(data.nurse_table);
			$("#cpNodeOrder").html(data.order_table);
			$("#cpNodeFamily").html(data.family_table);
			if(data.isUse==="false"){
				whenCpIsNotUse();
			}else{
				whenCpIsUse();
			}
			$("#wait").hide();
		}
		if(data.method==="getOneNewNodeTable"){
			if(tab_index_quanju==1){
				$("#cpNodeDoctor").html(data.table);
			}
			if(tab_index_quanju==2){
				$("#cpNodeNurse").html(data.table);
			}
			if(tab_index_quanju==3){
				$("#cpNodeOrder").html(data.table);
			}
			if(data.isUse==="false"){
				whenCpIsNotUse();
			}else{
				editButton(tab_index_quanju,true);
				saveButton(tab_index_quanju,false);
				hideDuizhao(tab_index_quanju,false);
			}
		}
		if(data.method==="updateTable"){
			if(data.result==="OK"){
				if(tab_index_quanju==1){
					$("#cpNodeDoctor").html(data.table);
				}
				if(tab_index_quanju==2){
					$("#cpNodeNurse").html(data.table);
				}
				if(tab_index_quanju==3){
					$("#cpNodeOrder").html(data.table);
				}
				editButton(tab_index_quanju,true);
				saveButton(tab_index_quanju,false);
				hideDuizhao(tab_index_quanju,false);
				alert("提交成功");
			}else{
				alert("提交错误");
			}
		}
		
};
///路径基本信息操作函数
function cp_state_op(obj){
var OKNO=false;
	if(obj==1){
		OKNO =confirm("确定要停用当前路径吗？");
	}else{
		OKNO =confirm("确定要启用当前路径吗？");
	}
	if(OKNO){
		operate="startOrEndCp";
		cp_state_quanju=obj;
		DBOperation();
	}
}
//变色函数-----开始
function changeColor(event){
	tempColor=event.bgColor;
	event.bgColor=highlightcolor;
}

function recoverColor(event){
	event.bgColor=tempColor;
	tempColor=recoveryColor;
}
function onclickColor(){
	if(tempColor!=clickcolor)
	{
		this.bgColor=clickcolor;
  		tempColor=clickcolor;
  	}
  	else{
  		this.bgColor=recoveryColor;
  		tempColor=recoveryColor;
  	}
}
function onclickOnNextNodeColor(event){
	var trs=$("tr[name^='node']");
	var trsleng=trs.length;
	for(var i=0;i<trsleng;i++){
		var tr=trs[i];
		tr.bgColor=recoveryColor;
	}
	event.bgColor=clickcolor;
	tempColor=event.bgColor;
	cp_node_id_quanju=event.id;
	operate="changeNode";
	$("#wait").show();
	DBOperation();
}
function editButtonClick(cp_or_node,obj){
	cp_or_node_quanju=cp_or_node;
	tab_index_quanju=obj;
	var selects_jqu=$("select[name^='select_2_"+obj+"']");
	var selects_len=selects_jqu.length;
	for(var i=0;i<selects_len;i++)
		selects_jqu[i].disabled=false;
	var edit=$("div[name$='editButton_2_"+obj+"']");
	edit.hide();
	var save=$("div[name$='saveButton_2_"+obj+"']");
	save.show();
	var duizhao=$("input[name^='duizhao_2_"+obj+"']");
	duizhao.show();
	
}
/**
 * 		0,0	隐藏所有按钮
 *		1,1	隐藏上层的
 *		2,0	隐藏所有下层的
 *		2,1，隐藏下层诊疗的
 *		类推
 */
function whenCpIsUse(){
	var savebutton=$("div[name^='saveButton_']");
	savebutton.hide();
	var editButton=$("div[name^='editButton_']");
	editButton.hide();
	var duizhao=$("input[name^='duizhao_']");
	duizhao.hide();
}
/**
 * 		0,0	显示所有按钮 下层表格中的button除外
 *		1,1	显示上层的button
 *		2,0	显示所有下层的Div 		editButton_2_2		saveButton_2_2
 *		2,1	显示下层诊疗的Div
 *		类推
 */
function whenCpIsNotUse(){
	var savebutton=$("div[name^='saveButton_']");
		savebutton.hide();
		var editButton=$("div[name^='editButton_']");
		editButton.show();
		var duizhao=$("input[name^='duizhao_2_']");
		duizhao.hide();
		var duizhao1=$("input[name^='duizhao_1_']");
		duizhao1.show();
}
/**
 * 	obj	第几个
 *	flag true显示false不显示
 */
function saveButton(obj,flag){
	var savebutton=$("div[name^='saveButton_2_"+obj+"']");
	if(flag){
		savebutton.show();
	}else{
		savebutton.hide();
	}
	
}
/**
 * 	obj	第几个
 *	flag true显示false不显示
 */
function editButton(obj,flag){
	var savebutton=$("div[name^='editButton_2_"+obj+"']");
	if(flag){
		savebutton.show();
	}else{
		savebutton.hide();
	}
	
}
/**
 * 	obj	第几个
 *	flag true显示false不显示
 */
function hideDuizhao(obj,flag){
	var duizhao=$("input[name^='duizhao_2_"+obj+"']");
	if(flag){
		duizhao.show();
	}else{
		duizhao.hide();
	}
}


function duizhaoButton(cp_or_node,tab_index,cp_id,cp_node_id,cp_node_table_id,cp_node_table_item_id,match_flag, event){
	//alert("对照");
	cp_or_node_quanju=cp_or_node;
	tab_index_quanju=tab_index;
	cp_node_id_quanju=cp_node_id;
	cp_node_table_id_quanju=cp_node_table_id;
	cp_node_table_item_id_quanju=cp_node_table_item_id;
	code_quanju=$(event).attr('code');
	flag_quanju=match_flag;
	$( "#duizhao").dialog( "open" );
	$("#duizhao").load("../match/match_util.jsp", {parent:'duizhao',bianma:code_quanju, flag : flag_quanju});

}
function quxiao(cp_or_node,obj){
	cp_or_node_quanju=cp_or_node;
	tab_index_quanju=obj;
	operate="getOneNewNodeTable";
	DBOperation();
}
function dingshichaxun(){
	dingshi_id=window.setInterval(getCookie, 5000);
}
function getCookie() {
	  var cookieName="match_util";
	  var cookieString = document.cookie;
	  var start = cookieString.indexOf(cookieName + '=');
	  // 加上等号的原因是避免在某些 Cookie 的值里有
	  // 与 cookieName 一样的字符串。
	  if (start == -1) // 找不到
	    return null;
	  start += cookieName.length + 1;
	  var end = cookieString.indexOf('_end', start);
	  var value="";
	  if (end == -1) value= unescape(cookieString.substring(start));
	  value= unescape(cookieString.substring(start, end));
	  alert(value);
	}
function tijiao(cp_or_node,obj){
	cp_or_node_quanju=cp_or_node;
	tab_index_quanju=obj;
	var selects_jqu=$("select[name^='select_2_"+obj+"']");
	var selects_len=selects_jqu.length;
	for(var i=0;i<selects_len;i++){
		var a=selects_jqu[i].id;
		var b=selects_jqu[i].value;
		var c=a.replace("select_","");
		var d$=$("#"+c);
		var code_=d$.html();
		if(b=="0"){
			if(code_==""){
				update_quanju="";
				alert("您提交的表单中含有自动项，自动项必须有本地匹配编码！");
				return ;
			}
		}
		update_quanju=update_quanju+a+"_"+b+":;";
	}
	//alert(update_quanju);
	if(update_quanju==""){
		alert("不需要保存提交");
		return ;
	}
	operate="updateTable";
	DBOperation();
	update_quanju="";
}
//变色函数-----结束	

</script>
</head>

<body >

<!-- 主体框架-->
	<div id="tabs1" style="width:auto">
	<!-- 选择菜单 -->
	    <ul>
	        <li><a href="#fragment-1"><span style="font-size:12px">路径基本信息</span></a></li>
	        <li><a href="#fragment-2"><span style="font-size:12px">路径纳入/排除条件</span></a></li>
	        <li><a href="#fragment-3"><span style="font-size:12px">诊断与治疗方案依据</span></a></li>
	        <li><a href="#fragment-4"><span style="font-size:12px">路径制定</span></a></li>
	        <li><a href="#fragment-5"><span style="font-size:12px">预防性抗菌药物与使用时机</span></a></li>
	        <li><a href="#fragment-6"><span style="font-size:12px">出院标准</span></a></li>
	        <li><a href="#fragment-7"><span style="font-size:12px">执行单预览</span></a></li>
	        <li><a href="#fragment-8"><span style="font-size:12px">路径流程图</span></a></li>
	        
	  	</ul>
	  	<!-- 路径基本信息 -->
		<div id="fragment-1" style="height:auto">
			<div class="tabheightandwidge">
	    		<div id="cp_node_info">
					
	    		</div>
    			
	    	</div>
  		</div>
  		<!-- 路径纳入/排除条件 -->
  		
		<div id="fragment-2" style="height:auto">   
			<table border="2" width="100%" height="100%">
	 			<tr>
	    			<td scope="col" height="290" valign="top">
	    				<div align="left" ><font color="#00AEAE" style=" font-size: 14px;">进入路径标准:</font></div>
		    			<table width="100%"   border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">    
					        <tr>
					        	  <td width="10%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">类别</span></div></td>
						          <td width="30%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">名称</span></div></td>
						          <td width="10%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">编码</span></div></td>
						          <td width="*" colspan="2" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">本地对照编码</span></div></td>
					        </tr>
		        			<tbody id="cpInclude">
		        			
		        			</tbody>
		    			</table>
	    			</td>
	  			</tr>
			  	<tr>
			     	<td scope="col" height="290" valign="top">
			     		<div align="left" ><font color="#00AEAE" style=" font-size: 14px;">排除条件：</font></div>
			            <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
			              	<tr class="STYLE6" height="20"  bgcolor="#d3eaef" >
					          	<td width="*" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">名称</span></div></td>
			              	</tr>
			             	<tbody id="cpExclude">
				        	</tbody>
			            </table>
			   		</td>
			  </tr>
			</table>
			
		</div>
		<!-- 诊断与治疗方案依据 -->
		<div id="fragment-3" style="height:auto">
			<div class="tabheightandwidge">
	      		<label><font color="#00AEAE" style="font-size: 14px;">诊断依据:</font></label>
	      		<br>
	    		<div id="cpDia"></div>
				<br>
				<label><font color="#00AEAE" style="font-size: 14px;">治疗方案依据：</font></label>
				<br />
	    		<div id="cpTreatment"></div>
	    	</div>
  		</div>
  		<!-- 路径指定 -->
  		<div id="fragment-4" >
  			<div class="tabheightandwidge">
	    		<table width="100%" height="100%" border="3" cellpadding="10" cellspacing="10">
	  				<tr >
					    <td width="30%" align="left"  valign="top">路径执行列表：<br />
					      	<table width="100%"   border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
						        <tr>
						          <td width="10%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">序号</span></div></td>
						          <td width="28%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">节点名称</span></div></td>
						          <td width="20%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">节点平均天数</span></div></td>
						        </tr>
					        	<tbody id="cpNode">
					    		</tbody>
					    	</table>
					    </td>
	    				<td width="70%" rowspan="2" valign="top" style="height:auto">
	    					<div id="tabs"  style="height:auto">
							    <ul>
							        <li><a href="#frag-1"><span style="font-size:12px">输入/输出节点</span></a></li>
							        <li><a href="#frag-2"><span style="font-size:12px">诊疗工作</span></a></li>
							        <li><a href="#frag-3"><span style="font-size:12px">护理工作</span></a></li>
							        <li><a href="#frag-4"><span style="font-size:12px">重要医嘱</span></a></li>
							        <li><a href="#frag-5"><span style="font-size:12px">家属工作</span></a></li>
							    </ul>
		    					<!-- 输入输出节点 -->
		    					<div id="frag-1">
		    						<table border="1" width="100%" height="100%">
								  		<tr>
									    	<td width="100%"  scope="col" height="230" valign="top" align="left">
									      		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
										      		<tr>
										        		<td width="*" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">输入节点名称</span></div></td>
										       		</tr>
									       			<tbody id="cpNodeInclude">
									    			</tbody>
									    		</table>
									    	</td>
								  		</tr>
								  		<tr>
									    	<td height="230" align="left" valign="top" scope="row">
									      		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
										      		<tr>
												        <td width="*" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">输出节点名称</span></div></td>
												    </tr>
											      	<tbody id="cpNodeExclude">
									    			</tbody>
									    		</table>
									    	</td>
								  		</tr>
		    						</table>
			    				</div>
			    				<!-- 主要诊疗工作 -->
							    <div id="frag-2">
							    	<table border="1" width="100%" height="100%">
									  	<tr>
									    	<td width="550" scope="col" height="400" valign="top">
									    		<table width="550"   border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
										          	<tr>
										          		<td width="3%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10"></span></div></td>
										          	
											            <td colspan="4" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">诊疗工作内容</span></div></td>
											        </tr>
										           <tr>
											            <td width="3%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10"></span></div></td>
											            <td width="30%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">诊疗项目</span></div></td>
											            <td width="*" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">诊疗编码</span></div></td>
											            <td width="15%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">自动项目</span></div></td>
											            <td width="30%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">对照本地编码</span></div></td>
											        </tr>
									        	</table>
									        	<div  width="550"  style="height: 350px; overflow-y: scroll;">
									        		<table class="tableText" width="550"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
									        				<tbody id="cpNodeDoctor">
										        			</tbody>
									        		</table>
									        	</div>
									  	</tr>
									</table>
									<div id="edit_save_2_1" name="edit_save_2_1">
										<table width="100%">
											<tr align="right">
												<td>
													<div id="editButton_2_1" name="editButton_2_1">
														<input type="button" value="编辑" onclick="editButtonClick(2,1);">
													</div>
													<div id="saveButton_2_1" name="saveButton_2_1">
														<input type="button" value="提交" onclick="tijiao(2,1);">
														<input type="button" value="取消 " onclick="quxiao(2,1);">
													</div>
												</td>
											</tr>
										</table>
									</div>
							    </div>
		  						<!-- 主要护理工作-->
							    <div id="frag-3">
							      	<table border="1" width="100%" height="100%">
							        	<tr>
							          		<td width="550" scope="col" height="400" valign="top">
							          			<table width="550"   border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
									            	<tr>
									            		<td width="3%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10"></span></div></td>
									            	
											            <td colspan="4" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">护理工作内容</span></div></td>
											        </tr>
									            	<tr>
									              		<td width="3%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10"></span></div></td>
									              		<td width="*" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">护理项目</span></div></td>
									              		<td width="10%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">项目编码</span></div></td>
									              		<td width="15%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">自动项目</span></div></td>
									              		<td width="30%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">对照本地编码</span></div></td>
									            	</tr>
							          			</table>
							          			<div  width="550"  style="height: 350px; overflow-y: scroll;">
									        		<table class="tableText"  width="550"   border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
									        				<tbody id="cpNodeNurse">
							            			</tbody>
									        		</table>
									        	</div>
							          		</td>
							        	</tr>
							      	</table>
							      	<div id="edit_save_2_2" name="edit_save_2_2">
										<table width="100%">
											<tr align="right">
												<td>
													<div id="editButton_2_2" name="editButton_2_2">
														<input type="button" value="编辑" onclick="editButtonClick(2,2);">
													</div>
													<div id="saveButton_2_2" name="saveButton_2_2">
														<input type="button" value="提交" onclick="tijiao(2,2);">
														<input type="button" value="取消" onclick="quxiao(2,2);">
													</div>
												</td>
											</tr>
										</table>
									</div>
							    </div>
								<!-- 重要医嘱-->
								<div id="frag-4">
								    <table border="1" width="100%" height="100%" >
									  	<tr>
									    	<td width="550" scope="col" height="389" valign="top">
									      		<table width="550"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
												  <tr>
												    <td width="3%" height="20" bgcolor="#d3eaef" class="STYLE6"><span class="STYLE10"></span></td>
												    <td width="50%" height="20" bgcolor="#d3eaef" class="STYLE6" colspan="2" align="center"><span class="STYLE10">护理工作内容</span></td>
												    <td width="5%" rowspan="2" bgcolor="#d3eaef" class="STYLE6" align="center"><span class="STYLE10">必做<BR/>项目</span></td>
												    <td width="5%" rowspan="2" bgcolor="#d3eaef" class="STYLE6" align="center"><span class="STYLE10">医嘱<BR/>类型</span></td>
												    <td width="*" colspan="2"  bgcolor="#d3eaef" ></td>
												  </tr>
												  <tr>
												    <td width="3%"  bgcolor="#d3eaef" >&nbsp;</td>
												    <td width="35%" height="19" bgcolor="#d3eaef" class="STYLE10"><div align="center">医嘱项目</div></td>
												    <td height="15%" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">项目编码</span></div></td>
												    <td width="15%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">自动选项</span></div></td>
												    <td width="22%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">对照本地编码</span></div></td>
												  </tr>
												</table>
												<div  width="550"  style="height: 350px; overflow-y: scroll;">
									        		<table class="tableText"  width="550"   border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >									        			
									        			<tbody id="cpNodeOrder">
									      				</tbody>
									        		</table>
									        	</div>
									      	</td>
									  	</tr>
									</table>
									<div id="edit_save_2_3" name="edit_save_2_3">
										<table width="100%">
											<tr align="right">
												<td>
													<div id="editButton_2_3" name="editButton_2_3">
														<input type="button" value="编辑" onclick="editButtonClick(2,3);">
													</div>
													<div id="saveButton_2_3" name="saveButton_2_3">
														<input type="button" value="提交" onclick="tijiao(2,3);">
														<input type="button" value="取消" onclick="quxiao(2,3);">
													</div>
												</td>
											</tr>
										</table>
									</div>
							    </div>
							    
		 						<!-- 家属工作-->
							    <div id="frag-5">
								    <table border="1" width="100%" height="100%">
								        <tr>
								          	<td width="100%" scope="col" height="400" valign="top">
								          		<table width="100%"   border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
								            		<tr>
								         		 		<td width="80%" height="20" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">家属工作内容</span></div></td>
								            		</tr>
								            		<tbody id="cpNodeFamily">
								          			</tbody>
								          		</table>
								          	</td>
								        </tr>
								     </table>
							    </div>
							</div>
	    				</td>
	  				</tr>
	    		</table>
	    	</div>
    	</div>
    	<!-- 预防性抗菌药物选择与使用时机 -->
    	<div id="fragment-5">
    		<div class="tabheightandwidge">
		    	<span style="height:auto">
		      		<label><font color="#00AEAE" style="font-size: 14px;">预防性抗菌药物选择与使用时机：</font></label>
					<br />
		      		<div id="cpAntibiotic"  ></div>
		    	</span>
    		</div>
    	</div>
    	<!-- 出院标准-->
    	<div id="fragment-6">
    		<div class="tabheightandwidge">
      			<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
            		<tr>
	              		<td width="*" height="19" bgcolor="#d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">出院标准名称</span></div></td>
            		</tr>
            		<tbody id="cpDischarge">
         			</tbody>
          		</table>
          	</div>
    	</div>
    	<!-- 执行单预览-->
    	<div id="fragment-7">
    		<div class="tabheightandwidge">
    		<%String url_1= LcpUtil.getConfigValue(PropertiesUtil.get("report.url")) ; %>
    			<iframe src="../ReportEmitter?rpt=%C2%B7%BE%B6%B1%A8%B1%ED.brt&params=cp_id='<%=cp_id%>'" frameborder="0" height="450" width="100%" id="dialogFrame" scrolling="no"></iframe>
      		</div>
    	</div>
    	<!-- 路径流程图-->
    	<div id="fragment-8">
    	</div>
	</div>
	<div id="wait" style="display:none;width:500px;height:120px;position:absolute;top:35%;left:35%;padding:2px;overflow:hidden;">
	<span style="background:#FFFFFF;">数据正在加载中,请等待 ...</span>
	<br />
	<img src='../public/images/loading.gif' width="300" height="15" /> 
	<div id="duizhao"></div>
	
</div>
</body>
</html>
