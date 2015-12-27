 
 

 
 
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"  >
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
	window.open('record.jsp?patientNo=394934_2');
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
	$("#table").load("../servlet/NodeUpdate",{op:"getSchema",cpId:"10014",cpNodeId:"2",doctorNo:"20000"});
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
		   cpId:"10014",
		   cpNodeId:"2",
		   doctorNo:"20000",
		   orderIds: orderIds,
		   comments: msg	   
 	},
	   dataType: "json",
	   complete: show_result,
	   success: function(data, textStatus, XMLHttpRequest){
	   }
 });
}
 
//执行到下一节点
function toNext(){
	$.ajax({
	   url: "../servlet/NextOrder",
	   type: "POST",
	   async: false,
	   data: {op:"toNextOrder",
		   cpId:"10014",
		   cpNodeId:"3",
		   patientId:"394934_2"		   
		   
 	},
	   dataType: "json",
	   complete: show_result1,
	   success: function(data, textStatus, XMLHttpRequest){
		   if(data[0].result === "OK"){
			   //window.location.href = "http://www.baidu.com";
			   var url = "../service/order.jsp?patient_no="+394934+"&admissTimes="+2+"&doctorNo="+20000+"&patientArea="+1010100+"&doctorQX="+1;
			   window.location.href=url;
 
		   }
		   if(data[0] === "fail"){
			   alert("执行失败，请与管理员联系！");
		   }		   
	   }
 });
}
 
//点击下一节点时候的操作
function gotoNext(){
	if(confirm("确定要执行到下一节点的医嘱吗?")) 
	{ 
	    toNext();
	} 
	
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
	
	var trs=$(".main tr[id='tr_1']");
	trs.hide();
	
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
				checkTrs1+="'orderKind':'"+$(this).children().eq(10).attr("advicetype")+"',";
				checkTrs1+="'stateItem':'"+$(this).children().eq(5).html()+"',";
				checkTrs1+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
				checkTrs1+="'measure':'"+$(this).children().eq(8).html()+"',";
				checkTrs+="'frequency':'"+$(this).children().eq(9).attr("frequency")+"',";
				checkTrs1+="'way':'" +$(this).children().eq(10).attr("way")+"',";
				checkTrs1+="'dosage':'" +$(this).children().eq(11).html()+"',";
				checkTrs1+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
				checkTrs1+="'advicetype':'"+$(this).children().eq(10).attr("advicetype")+"',";
				checkTrs1+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
				checkTrs1+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
			    
 
			}else if(typeName == "检查"){
				
				checkTrs2+="{";
				checkTrs2+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
				checkTrs2+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
				checkTrs2+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
				checkTrs2+="'orderKind':'"+$(this).children().eq(10).attr("advicetype")+"',";
				checkTrs2+="'stateItem':'"+$(this).children().eq(5).html()+"',";
				checkTrs2+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
				checkTrs2+="'measure':'"+$(this).children().eq(8).html()+"',";
				checkTrs2+="'frequency':'"+$(this).children().eq(9).attr("frequency")+"',";
				checkTrs2+="'way':'" +$(this).children().eq(10).attr("way")+"',";
				checkTrs2+="'dosage':'" +$(this).children().eq(11).html()+"',";
				checkTrs2+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
				checkTrs2+="'advicetype':'"+$(this).children().eq(10).attr("advicetype")+"',";
				checkTrs2+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
				checkTrs2+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
				
 
			}else if(typeName != "类别"){
				
				checkTrs3+="{";
				checkTrs3+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
				checkTrs3+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
				checkTrs3+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
				checkTrs3+="'orderKind':'"+$(this).children().eq(10).attr("advicetype")+"',";
				checkTrs3+="'stateItem':'"+$(this).children().eq(5).html()+"',";
				checkTrs3+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
				checkTrs3+="'measure':'"+$(this).children().eq(8).html()+"',";
				checkTrs3+="'frequency':'"+$(this).children().eq(9).attr("frequency")+"',";
				checkTrs3+="'way':'" +$(this).children().eq(10).attr("way")+"',";
				checkTrs3+="'dosage':'" +$(this).children().eq(11).html()+"',";
				checkTrs3+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
				checkTrs3+="'advicetype':'"+$(this).children().eq(10).attr("advicetype")+"',";
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
		jsons = "{'inpatientNo':'394934','cpId':'10014','cpNodeId':'2','admissTimes':'2','doctorNo':'20000','patientArea':'1010100','doctorQX':'1'}";
	}else{
		jsons="{'inpatientNo':'394934','cpId':'10014','cpNodeId':'2','admissTimes':'2','doctorNo':'20000','patientArea':'1010100','doctorQX':'1',"+ order +"}";
	}
	json = jsons;
	
	alert(json);
	//往后台提交选中状态
//	$.ajax({
//		   url: "../servlet/NodeUpdate",
//		   type: "POST",
//		   async: false,
//		   data: {op:"updateOrder",
//			   cpId:"10014",
//			   nodeId:"2",
//			   orderIds: orderIds,
//			   patientNo:"394934_2"
//	    	},
//		   dataType: "json",
//		   complete: show_result,
//		   success: function(data, textStatus, XMLHttpRequest){
//		   }
//	 });
 
 
//中山医院json格式[{患者编号:123},{cpID:1},{cpNodeID:2},{项目:[{项目编号:aaaa,频次:bbbb,计量:cccc,项目类型:检查|检验|护理|处置},{项目编号:aaaa,频次:bbbb,计量:cccc,项目类型:检查|检验|护理|处置}]}]
  	document.write("<form id='post1' name='post1' action='http://192.1.33.62:8080/EMRClinicPath/clinicPathAdvice.do' method='POST'>"); 
 	document.write('<input type="hidden" name="order" value="'+json+'" />');
 	document.write('</form>');  
 	document.getElementById('post1').submit();
 	
	
	
//	window.location.href="http://192.1.33.62:8080/EMRClinicPath/clinicPathAdvice.do?order="+json;    target= '_blank'
 
 
	
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
 
var show_result1 = function(XMLHttpRequest, textStatus){
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
<h1 align="center" style="font-size:20px;">轻症急性胰腺炎临床路径医嘱方案(第1天)</h1>
 
 <br>
 <h2 align="center" style="font-size:18px; color:red;">请注意：您所在的医生组不可以下高级抗生素相关医嘱！</h2>
 <h3 align="center" style="font-size:14px; color:red;">更新说明：如有颜色是 <font size="14" color="#AAAAAA">■</font> 的医嘱项是不能下发的，因为在医嘱系统里这些项是作废的或者库存为0，请大家总结并选择替代这些的医嘱项，整理好发给信息科，谢谢！</h3>
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
  患者编码:<strong>394934</strong>&nbsp;
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
<div align="center" class="main">
 
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
       
    
         <tr id="node-1"  height="25" bgcolor="#FFDEAD" style="font-size:14px; cursor:pointer;"  onclick="lap(this);">
           <td align="center" orderid="1">-</td>
           <td align="center"></td>
           <td align="center"><span class="STYLE10"></span></td>
			<td align="center"></td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;&nbsp;护理常规</td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;</td>
           	<td align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
       	  </tr>
         
         
         
		<tr align="center" class="child-of-node-1" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="N0015"  id="tr_1" name="1_1" style='cursor:pointer'>
		  <td id="1" name="1">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;按内科一般护理常规</td>
          <td class="STYLE10" >N0015</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="11"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-1" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="N0354"  id="tr_1" name="1_1" style='cursor:pointer'>
		  <td id="1" name="5">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;测血压</td>
          <td class="STYLE10" >N0354</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="11"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
         <tr id="node-2"  height="25" bgcolor="#FFDEAD" style="font-size:14px; cursor:pointer;"  onclick="lap(this);">
           <td align="center" orderid="2">-</td>
           <td align="center"></td>
           <td align="center"><span class="STYLE10"></span></td>
			<td align="center"></td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;&nbsp;护理等级</td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;</td>
           	<td align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
       	  </tr>
         
         
         
		<tr align="center" class="child-of-node-2" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="N0003"  id="tr_1" name="2_1" style='cursor:pointer'>
		  <td id="1" name="2">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;二级护理</td>
          <td class="STYLE10" >N0003</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="21"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
         <tr id="node-3"  height="25" bgcolor="#FFDEAD" style="font-size:14px; cursor:pointer;"  onclick="lap(this);">
           <td align="center" orderid="3">-</td>
           <td align="center"></td>
           <td align="center"><span class="STYLE10"></span></td>
			<td align="center"></td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;&nbsp;饮食</td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;</td>
           	<td align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
       	  </tr>
         
         
         
		<tr align="center" class="child-of-node-3" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="Y0084"  id="tr_1" name="3_1" style='cursor:pointer'>
		  <td id="1" name="3">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;禁食</td>
          <td class="STYLE10" >Y0084</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="31"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
         <tr id="node-5"  height="25" bgcolor="#FFDEAD" style="font-size:14px; cursor:pointer;"  onclick="lap(this);">
           <td align="center" orderid="5">-</td>
           <td align="center"></td>
           <td align="center"><span class="STYLE10"></span></td>
			<td align="center"></td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;&nbsp;管道护理</td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;</td>
           	<td align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
       	  </tr>
         
         
         
		<tr align="center" class="child-of-node-5" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="E1647"  id="tr_1" name="5_1" style='cursor:pointer'>
		  <td id="1" name="12">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;持续胃肠减压</td>
          <td class="STYLE10" >E1647</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="51"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-5" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="E4453"  id="tr_1" name="5_1" style='cursor:pointer'>
		  <td id="1" name="13">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;负压引流器</td>
          <td class="STYLE10" >E4453</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="51"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-5" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3035"  id="tr_1" name="5_1" style='cursor:pointer'>
		  <td id="1" name="17">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性胃管(硅胶，成人1208)</td>
          <td class="STYLE10" >F3035</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="51"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-5" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F4024"  id="tr_1" name="5_1" style='cursor:pointer'>
		  <td id="1" name="18">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;鼻饲管置管</td>
          <td class="STYLE10" >F4024</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="51"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-5" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="E4453"  id="tr_3" name="5_3" style='cursor:pointer'>
		  <td id="3" name="18">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;负压引流器</td>
          <td class="STYLE10" >E4453</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="53"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
         <tr id="node-7"  height="25" bgcolor="#FFDEAD" style="font-size:14px; cursor:pointer;"  onclick="lap(this);">
           <td align="center" orderid="7">-</td>
           <td align="center"></td>
           <td align="center"><span class="STYLE10"></span></td>
			<td align="center"></td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;&nbsp;其他</td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;</td>
           	<td align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
       	  </tr>
         
         
         
		<tr align="center" class="child-of-node-7" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="E0004"  id="tr_1" name="7_1" style='cursor:pointer'>
		  <td id="1" name="15">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;过敏试验</td>
          <td class="STYLE10" >E0004</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="71"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-7" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="00659"  id="tr_2" name="7_2" style='cursor:pointer'>
		  <td id="2" name="15">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠针(10ml)</td>
          <td class="STYLE10" >00659</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="72"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
         <tr id="node-9"  height="25" bgcolor="#FFDEAD" style="font-size:14px; cursor:pointer;"  onclick="lap(this);">
           <td align="center" orderid="9">-</td>
           <td align="center"></td>
           <td align="center"><span class="STYLE10"></span></td>
			<td align="center"></td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;&nbsp;抗生素</td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;</td>
           	<td align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
       	  </tr>
         
         
         
		<tr align="center" class="child-of-node-9" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="03573"  id="tr_2" name="9_2" style='cursor:pointer'>
		  <td id="2" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;头孢曲松钠粉针(泛生舒复)</td>
          <td class="STYLE10" >03573</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="92"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-9" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="03331"  id="tr_5" name="9_5" style='cursor:pointer'>
		  <td id="5" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;头孢噻肟钠粉针(凯福隆)</td>
          <td class="STYLE10" >03331</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="95"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-9" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="03563"  id="tr_8" name="9_8" style='cursor:pointer'>
		  <td id="8" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;头孢他啶粉针.</td>
          <td class="STYLE10" >03563</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="98"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-9" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="04693"  id="tr_11" name="9_11" style='cursor:pointer'>
		  <td id="11" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;头孢哌酮钠粉针H</td>
          <td class="STYLE10" >04693</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="911"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-9" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="03604"  id="tr_14" name="9_14" style='cursor:pointer'>
		  <td id="14" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;头孢哌酮钠舒巴坦钠粉针</td>
          <td class="STYLE10" >03604</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="914"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-9" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="04639"  id="tr_17" name="9_17" style='cursor:pointer'>
		  <td id="17" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;头孢唑肟钠粉针H</td>
          <td class="STYLE10" >04639</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="917"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-9" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="03330"  id="tr_20" name="9_20" style='cursor:pointer'>
		  <td id="20" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;亚胺培南西司他丁钠粉针(速能)</td>
          <td class="STYLE10" >03330</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="920"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-9" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="02181"  id="tr_22" name="9_22" style='cursor:pointer'>
		  <td id="22" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;替硝唑针(福药)</td>
          <td class="STYLE10" >02181</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="922"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-9" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05590"  id="tr_23" name="9_23" style='cursor:pointer'>
		  <td id="23" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;甲硝唑针</td>
          <td class="STYLE10" >05590</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="923"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
         <tr id="node-10"  height="25" bgcolor="#FFDEAD" style="font-size:14px; cursor:pointer;"  onclick="lap(this);">
           <td align="center" orderid="10">-</td>
           <td align="center"></td>
           <td align="center"><span class="STYLE10"></span></td>
			<td align="center"></td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;&nbsp;输液</td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;</td>
           	<td align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
       	  </tr>
         
         
         
		<tr align="center" class="child-of-node-10" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="E0006"  id="tr_1" name="10_1" style='cursor:pointer'>
		  <td id="1" name="6">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;静脉输液</td>
          <td class="STYLE10" >E0006</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="101"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-10" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3002"  id="tr_2" name="10_2" style='cursor:pointer'>
		  <td id="2" name="6">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性输液器(4.5#,6#,7#)</td>
          <td class="STYLE10" >F3002</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="102"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-10" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="E2552"  id="tr_3" name="10_3" style='cursor:pointer'>
		  <td id="3" name="6">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;静脉输液(连续输液)(住院)</td>
          <td class="STYLE10" >E2552</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="103"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
         <tr id="node-12"  height="25" bgcolor="#FFDEAD" style="font-size:14px; cursor:pointer;"  onclick="lap(this);">
           <td align="center" orderid="12">-</td>
           <td align="center"></td>
           <td align="center"><span class="STYLE10"></span></td>
			<td align="center"></td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;&nbsp;辅助用药(营养、支持等)</td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;</td>
           	<td align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
       	  </tr>
         
         
         
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_1" name="12_1" style='cursor:pointer'>
		  <td id="1" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="121"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_3" name="12_3" style='cursor:pointer'>
		  <td id="3" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="123"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_4" name="12_4" style='cursor:pointer'>
		  <td id="4" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="124"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_6" name="12_6" style='cursor:pointer'>
		  <td id="6" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="126"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_7" name="12_7" style='cursor:pointer'>
		  <td id="7" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="127"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_9" name="12_9" style='cursor:pointer'>
		  <td id="9" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="129"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_10" name="12_10" style='cursor:pointer'>
		  <td id="10" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1210"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_12" name="12_12" style='cursor:pointer'>
		  <td id="12" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1212"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_13" name="12_13" style='cursor:pointer'>
		  <td id="13" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1213"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_15" name="12_15" style='cursor:pointer'>
		  <td id="15" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1215"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_16" name="12_16" style='cursor:pointer'>
		  <td id="16" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1216"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_18" name="12_18" style='cursor:pointer'>
		  <td id="18" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1218"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_19" name="12_19" style='cursor:pointer'>
		  <td id="19" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1219"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_21" name="12_21" style='cursor:pointer'>
		  <td id="21" name="7">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1221"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_1" name="12_1" style='cursor:pointer'>
		  <td id="1" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="121"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="00294"  id="tr_2" name="12_2" style='cursor:pointer'>
		  <td id="2" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;奥美拉唑钠粉针(洛赛克粉针</td>
          <td class="STYLE10" >00294</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="122"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_3" name="12_3" style='cursor:pointer'>
		  <td id="3" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="123"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_4" name="12_4" style='cursor:pointer'>
		  <td id="4" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="124"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="05762"  id="tr_5" name="12_5" style='cursor:pointer'>
		  <td id="5" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;埃索美拉唑粉针</td>
          <td class="STYLE10" >05762</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="125"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_6" name="12_6" style='cursor:pointer'>
		  <td id="6" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="126"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_7" name="12_7" style='cursor:pointer'>
		  <td id="7" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="127"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="02637"  id="tr_8" name="12_8" style='cursor:pointer'>
		  <td id="8" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;泮托拉唑钠粉针(潘妥洛克)</td>
          <td class="STYLE10" >02637</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="128"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_9" name="12_9" style='cursor:pointer'>
		  <td id="9" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="129"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_10" name="12_10" style='cursor:pointer'>
		  <td id="10" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1210"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="04112"  id="tr_11" name="12_11" style='cursor:pointer'>
		  <td id="11" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;雷尼替丁注射液</td>
          <td class="STYLE10" >04112</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1211"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_12" name="12_12" style='cursor:pointer'>
		  <td id="12" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1212"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_13" name="12_13" style='cursor:pointer'>
		  <td id="13" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1213"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="05947"  id="tr_14" name="12_14" style='cursor:pointer'>
		  <td id="14" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;西米替丁针(泰胃美针</td>
          <td class="STYLE10" >05947</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1214"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_15" name="12_15" style='cursor:pointer'>
		  <td id="15" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1215"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_16" name="12_16" style='cursor:pointer'>
		  <td id="16" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1216"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="03452"  id="tr_17" name="12_17" style='cursor:pointer'>
		  <td id="17" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;法莫替丁粉针.</td>
          <td class="STYLE10" >03452</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1217"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_18" name="12_18" style='cursor:pointer'>
		  <td id="18" name="8">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1218"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_1" name="12_1" style='cursor:pointer'>
		  <td id="1" name="9">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="121"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="03227"  id="tr_2" name="12_2" style='cursor:pointer'>
		  <td id="2" name="9">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;奥曲肽针（善宁针）</td>
          <td class="STYLE10" >03227</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="122"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3007"  id="tr_3" name="12_3" style='cursor:pointer'>
		  <td id="3" name="9">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(50ML)</td>
          <td class="STYLE10" >F3007</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="123"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_4" name="12_4" style='cursor:pointer'>
		  <td id="4" name="9">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="124"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="03591"  id="tr_5" name="12_5" style='cursor:pointer'>
		  <td id="5" name="9">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;奥曲肽粉针(培新)</td>
          <td class="STYLE10" >03591</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="125"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3007"  id="tr_6" name="12_6" style='cursor:pointer'>
		  <td id="6" name="9">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(50ML)</td>
          <td class="STYLE10" >F3007</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="126"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05596"  id="tr_7" name="12_7" style='cursor:pointer'>
		  <td id="7" name="9">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0.9%氯化钠注射液（软袋100ml)</td>
          <td class="STYLE10" >05596</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="127"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="01295"  id="tr_8" name="12_8" style='cursor:pointer'>
		  <td id="8" name="9">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生长抑素粉针(思他宁</td>
          <td class="STYLE10" >01295</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="128"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3007"  id="tr_9" name="12_9" style='cursor:pointer'>
		  <td id="9" name="9">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(50ML)</td>
          <td class="STYLE10" >F3007</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="129"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05612"  id="tr_1" name="12_1" style='cursor:pointer'>
		  <td id="1" name="10">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5%葡萄糖注射液(软袋500ml)</td>
          <td class="STYLE10" >05612</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="121"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="03348"  id="tr_2" name="12_2" style='cursor:pointer'>
		  <td id="2" name="10">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;10%氯化钾针</td>
          <td class="STYLE10" >03348</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="122"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_3" name="12_3" style='cursor:pointer'>
		  <td id="3" name="10">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="123"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05604"  id="tr_4" name="12_4" style='cursor:pointer'>
		  <td id="4" name="10">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5%葡萄糖氯化钠注射液500ml软袋H</td>
          <td class="STYLE10" >05604</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="124"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="03348"  id="tr_5" name="12_5" style='cursor:pointer'>
		  <td id="5" name="10">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;10%氯化钾针</td>
          <td class="STYLE10" >03348</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="125"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_6" name="12_6" style='cursor:pointer'>
		  <td id="6" name="10">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="126"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05610"  id="tr_7" name="12_7" style='cursor:pointer'>
		  <td id="7" name="10">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;10%葡萄糖注射液(软袋500ml）</td>
          <td class="STYLE10" >05610</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="127"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="03348"  id="tr_8" name="12_8" style='cursor:pointer'>
		  <td id="8" name="10">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;10%氯化钾针</td>
          <td class="STYLE10" >03348</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="128"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_9" name="12_9" style='cursor:pointer'>
		  <td id="9" name="10">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="129"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="03294"  id="tr_10" name="12_10" style='cursor:pointer'>
		  <td id="10" name="10">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;乳酸钠林格(安徽双鹤,塑瓶)</td>
          <td class="STYLE10" >03294</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1210"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05612"  id="tr_11" name="12_11" style='cursor:pointer'>
		  <td id="11" name="10">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5%葡萄糖注射液(软袋500ml)</td>
          <td class="STYLE10" >05612</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1211"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05604"  id="tr_12" name="12_12" style='cursor:pointer'>
		  <td id="12" name="10">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5%葡萄糖氯化钠注射液500ml软袋H</td>
          <td class="STYLE10" >05604</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1212"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05610"  id="tr_13" name="12_13" style='cursor:pointer'>
		  <td id="13" name="10">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;10%葡萄糖注射液(软袋500ml）</td>
          <td class="STYLE10" >05610</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1213"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="03595"  id="tr_14" name="12_14" style='cursor:pointer'>
		  <td id="14" name="10">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;羟乙基淀粉130/0.4氯化钠注射液</td>
          <td class="STYLE10" >03595</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="1214"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05612"  id="tr_1" name="12_1" style='cursor:pointer'>
		  <td id="1" name="11">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5%葡萄糖注射液(软袋500ml)</td>
          <td class="STYLE10" >05612</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="121"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="05713"  id="tr_2" name="12_2" style='cursor:pointer'>
		  <td id="2" name="11">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;丹参注射液</td>
          <td class="STYLE10" >05713</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="122"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_3" name="12_3" style='cursor:pointer'>
		  <td id="3" name="11">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="123"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="05611"  id="tr_4" name="12_4" style='cursor:pointer'>
		  <td id="4" name="11">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5%葡萄糖注射液(软袋250ml)</td>
          <td class="STYLE10" >05611</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="124"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="05713"  id="tr_5" name="12_5" style='cursor:pointer'>
		  <td id="5" name="11">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;丹参注射液</td>
          <td class="STYLE10" >05713</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="125"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_6" name="12_6" style='cursor:pointer'>
		  <td id="6" name="11">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="126"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="02441"  id="tr_1" name="12_1" style='cursor:pointer'>
		  <td id="1" name="16">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;屈他维林针(诺仕帕针</td>
          <td class="STYLE10" >02441</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="121"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="00149"  id="tr_2" name="12_2" style='cursor:pointer'>
		  <td id="2" name="16">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">西药</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;哌替啶注射液(杜冷丁50mg)</td>
          <td class="STYLE10" >00149</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="122"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-12" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F3005"  id="tr_2" name="12_2" style='cursor:pointer'>
		  <td id="2" name="18">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一次性注射器(20ML)</td>
          <td class="STYLE10" >F3005</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="122"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
         <tr id="node-14"  height="25" bgcolor="#FFDEAD" style="font-size:14px; cursor:pointer;"  onclick="lap(this);">
           <td align="center" orderid="14">-</td>
           <td align="center"></td>
           <td align="center"><span class="STYLE10"></span></td>
			<td align="center"></td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;&nbsp;检验、检查</td>
           	<td align="left" class="STYLE10" bgcolor="#FFDEAD">&nbsp;</td>
           	<td align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
       	  </tr>
         
         
         
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0080"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="19">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">长期</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;血气分析</td>
          <td class="STYLE10" >D0080</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency=""> </td>
          
          <td class="STYLE10" way="" advicetype="1"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0672"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="20">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;淀粉酶测定(血液)</td>
          <td class="STYLE10" >D0672</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0023"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="22">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;淀粉酶测定(尿液)</td>
          <td class="STYLE10" >D0023</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D3046"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="23">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;急诊凝血四项(PT、APTT、FIB、TT)(组合)</td>
          <td class="STYLE10" >D3046</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D4064"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="24">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C—反应蛋白测定(CRP)(急诊)(各种免疫?</td>
          <td class="STYLE10" >D4064</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="B0508"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="25">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检查</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;腹部平片(DR)(组合)(立位)</td>
          <td class="STYLE10" >B0508</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="B0676"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="26">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检查</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CT平扫(肝胆脾、多层CT)(组合)</td>
          <td class="STYLE10" >B0676</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0674"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="27">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ABO血型鉴定(集凝胺法)</td>
          <td class="STYLE10" >D0674</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0726"  id="tr_2" name="14_2" style='cursor:pointer'>
		  <td id="2" name="27">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ABO血型鉴定(微柱法)</td>
          <td class="STYLE10" >D0726</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="142"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D2005"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="28">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;尿常规</td>
          <td class="STYLE10" >D2005</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D2003"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="29">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大便常规(组合)</td>
          <td class="STYLE10" >D2003</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0260"  id="tr_2" name="14_2" style='cursor:pointer'>
		  <td id="2" name="29">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;隐血试验</td>
          <td class="STYLE10" >D0260</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="142"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0499"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="30">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;血脂(组合)</td>
          <td class="STYLE10" >D0499</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D3053"  id="tr_2" name="14_2" style='cursor:pointer'>
		  <td id="2" name="30">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;肝功能(十四项、组合)</td>
          <td class="STYLE10" >D3053</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="142"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F4696"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="31">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;乙肝两对半(组合)(湖里)</td>
          <td class="STYLE10" >F4696</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F0410"  id="tr_2" name="14_2" style='cursor:pointer'>
		  <td id="2" name="31">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特殊采血管(通用2504)</td>
          <td class="STYLE10" >F0410</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="142"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0517"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="32">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;凝血四项(PT、APTT、FIB、TT)(组合)</td>
          <td class="STYLE10" >D0517</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F0411"  id="tr_2" name="14_2" style='cursor:pointer'>
		  <td id="2" name="32">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特殊采血管(通用2502)</td>
          <td class="STYLE10" >F0411</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="142"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0292"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="33">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;糖基抗原199（CA-199）</td>
          <td class="STYLE10" >D0292</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F0410"  id="tr_3" name="14_3" style='cursor:pointer'>
		  <td id="3" name="33">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特殊采血管(通用2504)</td>
          <td class="STYLE10" >F0410</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="143"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0414"  id="tr_4" name="14_4" style='cursor:pointer'>
		  <td id="4" name="33">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CEA癌胚抗原</td>
          <td class="STYLE10" >D0414</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="144"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0289"  id="tr_5" name="14_5" style='cursor:pointer'>
		  <td id="5" name="33">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;甲胎蛋白测定(AFP)</td>
          <td class="STYLE10" >D0289</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="145"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0349"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="34">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;抗核抗体测定ANA</td>
          <td class="STYLE10" >D0349</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0819"  id="tr_2" name="14_2" style='cursor:pointer'>
		  <td id="2" name="34">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ENA(组合)</td>
          <td class="STYLE10" >D0819</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="142"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0311"  id="tr_3" name="14_3" style='cursor:pointer'>
		  <td id="3" name="34">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;免疫球蛋白定量测定IgA(各种免疫学方法)</td>
          <td class="STYLE10" >D0311</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="143"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0312"  id="tr_4" name="14_4" style='cursor:pointer'>
		  <td id="4" name="34">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;免疫球蛋白定量测定IgM(各种免疫学方法)</td>
          <td class="STYLE10" >D0312</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="144"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="F0410"  id="tr_5" name="14_5" style='cursor:pointer'>
		  <td id="5" name="34">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">其他</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特殊采血管(通用2504)</td>
          <td class="STYLE10" >F0410</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="145"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0310"  id="tr_6" name="14_6" style='cursor:pointer'>
		  <td id="6" name="34">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;免疫球蛋白定量测定IgG(各种免疫学方法)</td>
          <td class="STYLE10" >D0310</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="146"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="B4783"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="36">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检查</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;磁共振水成象(MRCP)(组合)</td>
          <td class="STYLE10" >B4783</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="B0597"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="37">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检查</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;胸部正侧位片(DR)(组合)</td>
          <td class="STYLE10" >B0597</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="C0019"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="38">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检查</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;常规心电图(功能检查室)(组合)</td>
          <td class="STYLE10" >C0019</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="C0018"  id="tr_2" name="14_2" style='cursor:pointer'>
		  <td id="2" name="38">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检查</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;常规心电图检查(床边)(功能室)</td>
          <td class="STYLE10" >C0018</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="142"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D4046"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="39">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;急诊血常规(五分类)</td>
          <td class="STYLE10" >D4046</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D4066"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="40">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;急诊生化(组合)</td>
          <td class="STYLE10" >D4066</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D4076"  id="tr_2" name="14_2" style='cursor:pointer'>
		  <td id="2" name="40">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大急诊生化(组合)</td>
          <td class="STYLE10" >D4076</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="142"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D4016"  id="tr_3" name="14_3" style='cursor:pointer'>
		  <td id="3" name="40">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;小急诊生化(组合)</td>
          <td class="STYLE10" >D4016</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="143"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="C4192"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="41">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检查</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;肝 胆 胰 脾 肾 彩超</td>
          <td class="STYLE10" >C4192</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)" exestate1=""  selectId="D0500"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="42">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检验</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;心功能(五项)(组合)</td>
          <td class="STYLE10" >D0500</td>
          
          <td class="STYLE10" need="0" exestate1=""></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="B0676"  id="tr_1" name="14_1" style='cursor:pointer'>
		  <td id="1" name="43">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检查</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CT平扫(肝胆脾、多层CT)(组合)</td>
          <td class="STYLE10" >B0676</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="141"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
		<tr align="center" class="child-of-node-14" 
		 	bgcolor="#AAAAAA" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="" exestate1=""  selectId="B0674"  id="tr_2" name="14_2" style='cursor:pointer'>
		  <td id="2" name="43">&nbsp;</td>
		  <td></td>
		  <td class="STYLE10">临时</td>
		  <td class="STYLE10">检查</td>
          <td align="left"   class="STYLE10" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CT平扫(胰、多层CT)(组合)</td>
          <td class="STYLE10" >B0674</td>
          
          <td class="STYLE10" need="1" exestate1=""><img src='../public/images/detail_s5.png' width='18' height='18'/></td>
          
          <td class="STYLE10" stateItem=""></td>
          
          <td class="STYLE10" orderItemSetNo=""></td>
          
          <td class="STYLE10" frequency="ONCE"> </td>
          
          <td class="STYLE10" way="" advicetype="0"> </td>
          
          <td class="STYLE10" unitId="142"></td>
          
          <td class="STYLE10" dosageUnits=""> </td>
          </tr>
			
          
        </tbody>
      </table>
</div>
  <div id="commit" style="height:30px" align="center">
    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
  <input type="button" style="width: auto; height: auto; font-size: 20px" id="sub" value="提交"/>
  <input type="button" style="width: auto; height: auto; font-size: 20px" onclick="saveSchema()" value="保存为模板"  />
  <input type="button" style="width: auto; height: auto; font-size: 20px" onclick="gotoNext()" value="执行下一节点医嘱"  />
  
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
