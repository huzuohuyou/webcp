<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%-- <%@include file="../frames/power.jsp" %> --%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
String cp_id=request.getParameter("cp_id");
String type=request.getParameter("type");
if(type == null) type="";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<script type="text/javascript">
	var cp_id_liu="<%=cp_id%>";
	var cp_id=cp_id_liu;
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

 	<link rel="stylesheet" href="../public/plugins/jquery/themes/cupertino/jquery-ui-1.8.11.custom.css">
 	<link rel="stylesheet" href="../public/plugins/jquery/jquery.autocomplete.css"> 
	<script type="text/javascript" src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="../public/plugins/jquery/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="../public/plugins/jquery/ui/jquery.ui.tabs.js"></script>
	<script type="text/javascript" src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="../public/plugins/jquery/ui/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="../public/plugins/jquery/ui/jquery.ui.draggable.js"></script>
	<script type="text/javascript" src="../public/plugins/jquery/ui/jquery.ui.position.js"></script>
	<script type="text/javascript" src="../public/plugins/jquery/ui/jquery.ui.resizable.js"></script>
	<script type="text/javascript" src="../public/plugins/jquery/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="../public/javascripts/cpmanage/manage.js"></script>
	<script type="text/javascript" src="../public/javascripts/cpmanage/charscode.js"></script>
	<script type="text/javascript" src="nodetab.js"></script>
	<script type="text/javascript" src="../public/plugins/jquery/jquery.autocomplete.js"></script>
	
	
<title>临床路径制定平台</title>
<script type="text/javascript">

var xuhao=0;
//单击选择节点,行变色,加载右侧信息	
function NodeColor(event){
	$("#wait").dialog("open");
	var trs=$(".selectNode");
	var trsleng=trs.length;
	for(var i=0;i<trsleng;i++){
		var tr=trs[i];
		tr.bgColor=recoveryColor;
	}
	event.bgColor=clickcolor;
	tempColor=event.bgColor;
	cpNodeID=event.id;
	 $("#tabsLoad").empty();
	 $("#tabsLoad").load("nodetab.jsp",{cp_id:cpID,cp_node_id:event.id},hide);
}
//取行号
function getNum(line){
    xuhao=$(line).children().eq(0).html();
}


//编辑节点按钮
function editNode(){
     
	 var mark=cpNodeID;
		$.ajax({
		   type: "POST",
		   url: "../servlet/managecp",
		   data: "mark="+mark+"&cpid="+cp_id+"&op=editNodecx",
		   success: function(msg){
			 msg=eval('('+msg+')');

			  $("#dialog-eaddNode-txt1").val(msg["CP_NODE_NAME"]);
			  $("#dialog-eaddNode-txt2").val(msg["CP_NODE_DAYS_MAX"]);
		        if(msg["CP_NODE_TYPE"]!="1"){
		        	 $( "#dialog-eaddNode" ).dialog("close");
					 alert("您没有权限编辑除正常节点以外的节点名称!");
				  	 return;
			      }
				if(msg["CP_NODE_TYPE"]=="0"){
					 $("#dialog-eaddNode-select").val($("#dialog-eaddNode-select0").val());
				}else if(msg["CP_NODE_TYPE"]=="1"){
					 $("#dialog-eaddNode-select").val($("#dialog-eaddNode-select1").val()); 
				}else if(msg["CP_NODE_TYPE"]=="2"){
					 $("#dialog-eaddNode-select").val($("#dialog-eaddNode-select2").val());
				}else if(msg["CP_NODE_TYPE"]=="3"){
					 $("#dialog-eaddNode-select").val($("#dialog-eaddNode-select3").val());  
				}else if(msg["CP_NODE_TYPE"]=="4"){
					 $("#dialog-eaddNode-select").val($("#dialog-eaddNode-select4").val()); 
				}else if(msg["CP_NODE_TYPE"]=="5"){
					 $("#dialog-eaddNode-select").val($("#dialog-eaddNode-select5").val());
				}
		
			  
		   }
		}); 
	    $( "#dialog-eaddNode" ).dialog("open");
   
	    $( "#dialog-eaddNode" ).dialog({
		buttons: {
		   "修改": function() {
			 var mark=cpNodeID;
	    	 var nodeName=$( "#dialog-eaddNode-txt1" ).val();
			 var nodeDate=$( "#dialog-eaddNode-txt2" ).val();
			 var nodeType=$( "#dialog-eaddNode-select" ).val();
             
			  $.ajax({	   
					url : "../servlet/managecp",
					type : 'POST',
					data : {
						op : "editNodegx",
						mark:mark,
						cp_id:cp_id,
						nodeName : encodeURI(nodeName,"utf-8"), 
						nodeDate:nodeDate,
						nodeType:nodeType
					},
					dataType : "json",
					complete :show_result ,
					success :function(data, textStatus, XMLHttpRequest) {
						data = eval(data);
						 if (data.result === "OK") {
								var toHtml=
								"<td  class='STYLE10' align='center'>"+xuhao+"</td>"+
						        "<td  class='STYLE10' align='left'>&nbsp;&nbsp;&nbsp;"+nodeName+"</td>"+
						        "<td  class='STYLE10' align='center'>"+nodeDate+"</td>";
						     // $("#mark").html(toHtml);
								$("tr [id='"+mark+"']").html(toHtml);
/* 								 $("#tb tr").each(function(){
									  alert($(this).html());
									}); */

							} else	alert("无效操作");
						} 
				}); 
				$( this ).dialog( "close" );
		},
		"取消": function() {
			$( this ).dialog( "close" );
		}
	}
});
}

//返回到列表
function back1(){
	document.location.href="cplist.jsp";
}
</script>
<style type="text/css">
<!--
.STYLE23 {color: #FF0000}
-->
</style>
</head>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background: #F2F5F7;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 12px;font-weight:normal; }
#tabs { height: 200px; } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 650px; overflow: auto;} 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0em 0em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -0px !important; margin-bottom: 0px !important; border-top: none; border-bottom-width: 0px; }
	.ui-tabs-selected { margin-top: -3px !important; }
.onclickbgcolor{
	background-color: #51b2f6;
}
.ui-dialog-title{font-size:14px}
 button{ font-size: 14px;}
 a{font-size: 12px;}

-->
</style>
<script>                       

</script>
</head>

<body >
<%	
	    String cpName = "";
        String ss = "select t.cp_name from lcp_master t where t.cp_id="+cp_id;
		DatabaseClass db1 = LcpUtil.getDatabaseClass();
		cpName=db1.FunGetDataSetBySQL(ss).FunGetDataAsStringById(0,0);
		System.out.println(cpName);
%>

<div id="tabs1" style="width:auto">
    <ul>
        <li><a href="#fragment-1">路径基本信息</a></li>
        <li><a href="#fragment-2">路径纳入/排除条件</a></li>
        <li><a href="#fragment-3">诊断与治疗方案依据</a></li>
        <li><a href="#fragment-4">路径制定</a></li>
        <li><a href="#fragment-5">预防性抗菌药物与使用时机</a></li>
        <li><a href="#fragment-6">出院标准</a></li>
     	<li><a href="#fragment-7">常见变异</a></li>
   </ul>
 <div id="fragment-1" style="height:350">
   <table width="753" height="250"  border="0" style="font-size: 12px;">
     <tr height="25">
       <td align="right">路径编码：</td>
       <td width="232"><input  type="text" id="cp_code_input" style="width: 200px;"  readonly="readonly"/></td>
       <td width="118" align="right">路径序号：</td>
       <td width="200"><input name="cp_id_input" type="text" id="cp_id_input" style="width: 200px;"  readonly="readonly"/></td>
     </tr>
     <tr height="25">
       <td align="right">路径名称：</td>
       <td><input name="cp_name_input" type="text" id="cp_name_input" style="width: 200px;"   readonly="readonly"/></td>
       <td align="right">所属科室名称：</td>
       <td><input name="text5" type="text" id="dept_name"  style="width: 200px;"    readonly="readonly"/>
         <input type="hidden"  value="value" id="dept_code" /> </td>
     </tr>
     <tr height="25">
       <td align="right">路径版本：</td>
       <td><input name="text" type="text" id="cp_version_input"  style="width: 200px;"    readonly="readonly"/></td>
       <td align="right">版本日期：</td>
       <td><input name="text" type="text"id="cp_version_date_input"  style="width: 200px; "   readonly="readonly"/></td>
     </tr>
     <tr height="25">
       <td  align="right">平均住院日：</td>
       <td><input name="text" type="text" id="cp_days_input" style="width: 200px;"   readonly="readonly"/></td>
       <td align="right">平均费用：</td>
       <td><input name="text" type="text" id="cp_fee_input"  style="width: 200px;"    readonly="readonly"/></td>
     </tr>
     <tr height="25">
       <td align="right">最少住院日：</td>
       <td><input name="text3" type="text" id="cp_days_min" style="width: 200px;"  readonly="readonly"/></td>
       <td align="right">最大住院日：</td>
       <td><input name="text4" type="text" id="cp_days_max" style="width: 200px;"    readonly="readonly"/></td>
     </tr>
     <tr height="25"> 
       <td align="right">创建日期：</td>
       <td><input name="text" type="text" id="cp_create_date_input"  style="width: 200px;"  value=" "  readonly="readonly"/></td>
       <td align="right">创建人： </td>
       <td><input name="text" type="text" id="user_name_input"  style="width: 200px;" value=" " readonly="readonly"/></td></tr>
     <tr height="25">
       <td  align="right">拼音码：</td>
       <td><input  type="text" id="pym_input"  style="width: 200px;"  readonly="readonly"/></td>
       <td align="right">医保定额：</td>
       <td><input  type="text" id="health_care_quota"  style="width: 200px;"   readonly="readonly"/></td>
     </tr>

     <tr height="25">
       <td  align="right">&nbsp;</td>
       <td></td>
       <td align="right">&nbsp;</td>
       <td><input type="button" name="edit_cp" id="edit_cp" value="编辑路径" ></td>
     </tr>
   </table>
 </div>


<div id="fragment-2" style="height:400"></div>
 
<div id="fragment-3" style="height:400">
      <label><font color="#00AEAE" style="font-size: 14px;">诊断依据:</font></label>
      &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
      <input name="button_one" value="保存" type="button" id="button_second" onClick='antibiotics_based("lcp_master_based")'>
      <br>
    <textarea name="cp_diagnosis_based" cols="150" rows="10" id="cp_diagnosis_based" style="font-size:14px"></textarea>
	<br>
	<label><font color="#00AEAE" style="font-size: 14px;">治疗方案依据：</font></label>
	 <br />
    <textarea cols="150" rows="10" id="cp_treatment" style="font-size:14px"></textarea>
  </div>
  
<div id="fragment-4" style="height:350">
  <table width="100%" border="1" cellpadding="5" cellspacing="2">
  <tr >
    <td width="30%" height="350" align="left"  valign="top"><span style="font-size:15px;">(<%=cpName%>)</span><span style="font-size:15px;">执行列表:</span><br />   
	<div id='master_left'>
	</div> 
    </td>
    <td width="70%" rowspan="2" valign="top" style="height:auto">
    <div id="tabsLoad">
    <input type="hidden" value=>
    请选择左侧节点加载内容
    </div>
    </td>
  </tr>
  <tr >
    <td height="30" align="left"  valign="top"><div align="center">
      <input id="addNode" name="button_one" type="button" value="添加"  style="height:25px; width:auto; font-size:12px" onClick="addNode()" />
      <input id="editNode" name="button_one" type="button" value="编辑"  style="height:25px; width:auto; font-size:12px" onClick="editNode()" />
      <input id="delNode" name="button_one" type="button" value="删除"  style="height:25px; width:auto; font-size:12px" onClick="delNode()" />
    </div></td>
  </tr>
    </table>
  </div>
    
 <div id="fragment-5" style="height:350"><span style="height:auto">
      <label><font color="#00AEAE" style="font-size: 14px;">预防性抗菌药物选择与使用时机：</font></label>
      &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
	<input name="button_one" value="保存" type="button" id="button_cp_antibiotics" onClick='antibiotics_based("lcp_master_antibiotics")'>
		<br />
         <textarea name="cp_antibiotics" cols="150" rows="20" id="cp_antibiotics"  style="font-size:14px"></textarea>
 </span>
 
 
 
 </div> 
 
 
   
 <div id="fragment-6"></div>
 <div id="fragment-7"> </div>
</div>
<div id="returnDiv" align="right"> <input type="button" id="returnButton"  value="返回上页" onclick="back();"/>
<input type="button" id="returnButton1"  value="返回路径列表" onclick="back1();"/>
</div>


<div id="dialog1" style="background: #FFF;font-size:14px;">
	<form id="form1" name="form1" method="post" action="">
  <table width="317" height="181" border="0" align="center">
    <tr>
      <td width="101" height="39" align="right">诊断名称：</td>
      <td width="206"><input type="text" name="txt3" title="通过搜索获得,可以更改" id="diagnosisCode" />
        *</td>
    </tr>
    <tr>
      <td height="44" align="right">诊断编码：</td>
      <td><input type="text" name="txt4" id="diagnosisName" title="输入ICD编码搜索"/>
      *</td>
    </tr>
    <tr>
      <td height="44" align="right">拼音码检索:</td>
      <td><input type="text" name="diagnosisInput" id="diagnosisPY" /></td>
    </tr>
    <tr>
      <td height="44" align="right">五笔码检索:</td>
      <td><input type="text" name="diagnosisInput" id="diagnosisWB" /></td>
    </tr>
  </table>
</form>
</div>
<div id="dialog11" style="background: #FFF;font-size:14px;">
	<form id="form1" name="form1" method="post" action="">
	  
  <table width="326" height="181" border="0" align="center">
    <tr>
      <td width="101" height="39" align="right">手术名称：</td>
      <td width="215"><input type="text" name="txt3" title="通过搜索获得,可以更改" id="operationCode" />
        *</td>
    </tr>
    <tr>
      <td height="44" align="right">手术编码：</td>
      <td><input type="text" name="txt4" id="operationName"  title="输入编码搜索" />
      *</td>
    </tr>
    <tr>
      <td height="44" align="right">拼音码检索:</td>
      <td><input type="text" name="operationInput" id="operationPY" /></td>
    </tr>
    <tr>
      <td height="44" align="right">五笔码检索:</td>
      <td><input type="text" name="operationInput" id="operationWB" /></td>
    </tr>
  </table>
</form>
</div>


<div id="dialog-addNode" style="background: #FFF;font-size:14px;">
	<form id="form1" name="form1" method="post" action="">
	  <table width="344" height="145" border="0" align="center">
	   
	    <tr>
	      <td width="136" height="62" align="right">节点名称：</td>
	      <td width="198"><input type="text" name="txt5" id="dialog-addNode-txt1" />
	      *</td>
        </tr>
	    <tr>
	      <td width="136" height="50" align="right">最多天数：</td>
	      <td><input name="txt5" type="text" id="dialog-addNode-txt2" maxlength="2" />
	      *</td>
        </tr>
		 <tr>
	      <td width="136" height="50" align="right">节点类型：</td>
	      <td><label for="select"></label>
	        <select name="select2" size="1" id="dialog-addNode-select">
	      <!--     <option value="0">0准入</option> -->
	          <option value="1" selected>1正常节点</option>
	  <!--         <option value="2">2完成节点</option>
	          <option value="3">3变异</option>
	          <option value="4">4退出</option>
	          <option value="5">5自定义节点</option> -->
            </select>
           </td>
		 </tr>
      </table>
	</form>
</div>

<!-- //编辑节点 -->
<div id="dialog-eaddNode" style="background: #FFF;font-size:14px;">
	<form id="form1" name="form" method="post" action="">
	  <table width="344" height="145" border="0" align="center">
	   
	    <tr>
	      <td width="136" height="62" align="right">节点名称：</td>
	      <td width="198"><input type="text" name="txt5" id="dialog-eaddNode-txt1" />
	      *</td>
        </tr>
	    <tr>
	      <td width="136" height="50" align="right">最多天数：</td>
	      <td><input name="txt5" type="text" id="dialog-eaddNode-txt2" maxlength="2" />
	      *</td>
        </tr>
		 <tr>
	      <td width="136" height="50" align="right">节点类型：</td>
	      <td><label for="select"></label>
	        <select name="select2e" size="1" id="dialog-eaddNode-select">
	        <!--   <option value="0" id="dialog-eaddNode-select0">0准入</option> -->
	          <option value="1" id="dialog-eaddNode-select1" selected >1正常节点</option>
<!-- 	          <option value="2" id="dialog-eaddNode-select2">2完成节点</option>
	          <option value="3" id="dialog-eaddNode-select3">3变异</option>
	          <option value="4" id="dialog-eaddNode-select4">4退出</option>
	          <option value="5" id="dialog-eaddNode-select5">5自定义节点</option> -->
            </select>
           </td>
		 </tr>
      </table>
	</form>
</div>


<div id="dialog-form-2">
  <form action="">
		<table width="250" height="100" border="0" align="center">
			<tr>
		      <td width="67" height="55" align="right"> 内容：</td>
		      <td width="173"><input type="text" name="" id="dialog-form-2-tex" /></td>
	        </tr>
		</table>
	</form>
</div>
<div id="dialog-form-main" style="background: #FFF;font-size:14px;">
    <table width="369" height="238" border="0xp" align="center" cellspacing="0">
      <%if(type.equals("upCP") || type.equals("jfCP")){ %>
      <tr>
        <td height="32" align="right">路径名称：</td>
        <td><input type="text" name="txt112" id="cp_name"  onkeyup="document.getElementById('cp_pym').value = getCharsCode(this.value);" readonly="readonly"/>*
        </td>
      </tr>
      <%}else{ %>
       <tr>
        <td height="32" align="right">路径名称：</td>
        <td><input type="text" name="txt112" id="cp_name"  onkeyup="document.getElementById('cp_pym').value = getCharsCode(this.value);" />*
        </td>
      </tr>
      <%} %>
       <tr>
        <td height="32" align="right" >路径编码：</td>
        <td><input name="min_day" type="text" id="cp_code" readonly="readonly"/></td>
      </tr>
      <tr>
        <td width="144" height="32" align="right" >最少住院日：</td>
        <td width="221"><label><input name="txt11" type="text" id="min_day" maxlength="3" />
        </label></td>
      </tr>
      <tr>
        <td height="28" align="right">最大住院日： </td>
        <td width="221"><label><input name="txt6" type="text" id="max_day" maxlength="3" />
      </label>      </tr>
      <tr>
        <td height="29" align="right">平均住院日：</td>
        <td><input name="txt6" type="text" id="avg_day" maxlength="3" /></td>
      </tr>
      <tr>
        <td height="30" align="right">平均费用：</td>
        <td><input type="text" name="txt6" id="avg_fee"/></td>
      </tr>
      <tr>
        <td height="29" align="right">科室名称：</td>
        <td><input type="text" name="txt13" id="dept_name_input"  readonly="readonly"/>
         <input type="hidden"  value="value" id="dept_code_input" /> 
     </td>
      </tr>
       <tr>
        <td height="29" align="right">拼音码：</td>
        <td><input name="min_day2" type="text" id="cp_pym" /></td>
      </tr>
      <tr>
        <td height="29" align="right">医保定额：</td>
        <td><input name="min_day3" type="text" id="cp_health_care_quota" /></td>
      </tr>
  </table>
</div>
<div id="wait" style="width:500px;height:120px;position:absolute;top:25%;left:25%;padding:2px;overflow:hidden;">
	<!--  <span style="background:#FFFFFF;">数据正在加载中,请等待 ...</span>
	<br />
	<img src='../public/images/loading.gif' width="300" height="15" />   -->
	
</div>




<div id="dialogAddOutNode" style="background: #FFF">
 <form id="form1" name="form1" method="post" action="">
  <table width="395" height="36" border="0"  cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" align="center">
   <tr height="32">
        <td width="11%"  bgcolor="d3eaef" class="STYLE10"><div align="center">序号</div></td>
        <td width="60%"  bgcolor="d3eaef" class="STYLE10"><div align="center">节点名称</div></td>
        <td width="20%"  bgcolor="d3eaef" class="STYLE10"><div align="center">阶段最多天数</div></td>
   </tr>
   <tbody id="dialogAddOutNodeTbody">
  
   </tbody>
  </table>
 </form>
</div>



<div id="dialogFamily" style="background: #FFF;font-size:14px;">
  <table width="300" height="100" border="0" align="center">
    <tr height="35">
      <td width="99"  align="right">家属工作内容：</td>
      <td width="191"><input type="text" name="familyName" id="familyName">*</td>
    </tr>
    <tr height="35">
      <td  align="right">是否必做：</td>
      <td>
        <input name="family" type="radio" value="0" checked >可选项    
        <input type="radio" name="family" value="1">必做项
      </td>
    </tr>
  </table> 
</div>


<div id="dialogDoctor_point" style="background: #FFF;font-size:14px;">
	  <table width="300" height="100" border="0" align="center">
	    <tr height="35">
	      <td width="116"  align="right">诊疗工作内容：</td>
	      <td width="197"><input type="text" name="Input2" id="doctorName"  title="通过搜索获得,可以更改" />*</td>
	    </tr>
	    <tr height="35">
	      <td  align="right">是否必做：</td>
	      <td>
	        <input name="doctor" type="radio" value="0" checked > 可选项
	        <input type="radio" name="doctor" value="1">  必做项
	      </td>
        </tr>
	  <!--    <tr>
	    <td height="44" align="right">拼音码搜索:</td>
	      <td><input type="text" name="doctorInput" id="doctorPY" /></td>
        </tr>
	    <tr>
	      <td height="44" align="right">五笔码搜索:</td>
	      <td><input type="text" name="doctorInput" id="doctorWB" /></td>
        </tr>-->
  </table>
</div>



<div id="dialogDoctor_item" style="background: #FFF;font-size:14px;">
	  <table width="339" height="200" border="0" align="center">
	    <tr height="35">
	      <td width="113"  align="right">诊疗工作编码：</td>
	      <td width="216"><input type="text" name="" id="doctorCode1"  title="通过搜索获得,不能手动填写" readonly="readonly"/>*</td>
        </tr>
	    <tr height="35">
	      <td  align="right">诊疗工作内容：</td>
	      <td><input type="text" name="Input2" id="doctorName1"  title="通过搜索获得,可以更改" />*</td>
	    </tr>
	    <tr height="35">
	      <td  align="right">是否必做：</td>
	      <td><label>
	        <input name="doctor1" type="radio" value="0" checked >  可选项</label>
	        <label><input type="radio" name="doctor1" value="1"> 必做项</label>
          </td>
        </tr>
	    <tr height="35">
	      <td  align="right">自动项：</td>
	      <td><label>
	        <input name="doctor_auto" type="radio" value="1" checked >
	        手 动
	      </label>
            <label>
              <input type="radio" name="doctor_auto" value="0">
              自 动
            </label></td>
        </tr>
	    <tr height="35">
	      <td  align="right">拼音码搜索:</td>
	      <td><input type="text" name="doctorInput1" id="doctorPY1" /></td>
        </tr>
	    <tr height="35">
	      <td  align="right">五笔码搜索:</td>
	      <td><input type="text" name="doctorInput1" id="doctorWB1" /></td>
        </tr>
  </table>
</div>



<div id="dialogNurse_point" style="background: #FFF;font-size:14px;">
	  <table width="300" height="100" border="0" align="center">
	    <tr>
	      <td height="35" align="right">护理工作内容：</td>
	      <td><input type="text" name="txt5" id="nurseName" title="通过搜索获得,可以更改" />
	      *</td>
        </tr>
	    <tr>
	      <td height="35" align="right">是否必做：</td>
	      <td><label>
	        <input name="nurse" type="radio" value="0" checked >
          可选项</label>
	        <label>
	        <input type="radio" name="nurse" value="1">
          必做项</label></td>
        </tr>
	   <!-- <tr>
	      <td height="44" align="right">拼音码搜索:</td>
	      <td><input type="text" name="nurseInput" id="nursePY" /></td>
        </tr>
	    <tr>
	      <td height="44" align="right">五笔码搜索:</td>
	      <td><input type="text" name="nurseInput" id="nurseWB" /></td>
        </tr>-->
  </table>
</div>



<div id="dialogNurse_item" style="background: #FFF;font-size:14px;">
	  <table width="324" height="227" border="0" align="center">
	    <tr>
	      <td width="113" height="35" align="right">护理工作编码：</td>
	      <td width="201"><label>
	      <input type="text" name="txt52" id="nurseCode1" title="通过搜索获得,不能手动填写"  readonly="readonly"/>
	      </label>
	        *</td>
        </tr>
	    <tr>
	      <td height="35" align="right">护理工作内容：</td>
	      <td><input type="text" name="txt5" id="nurseName1" title="通过搜索获得,可以更改" />
	      *</td>
        </tr>
	    <tr>
	      <td height="35" align="right">是否必做：</td>
	      <td><label>
	        <input name="nurse1" type="radio" value="0" checked >
          可选项</label>
	        <label>
	        <input type="radio" name="nurse1" value="1">
          必做项</label></td>
        </tr>
	    <tr>
	      <td height="35" align="right">自动项：</td>
	      <td><label>
	        <input name="nurse_auto" type="radio" value="1" checked >
	        手 动 </label>
            <label>
              <input type="radio" name="nurse_auto" value="0">
          自 动 </label></td>
        </tr>
	    <tr>
	      <td height="35" align="right">拼音码搜索:</td>
	      <td><input type="text" name="nurseInput1" id="nursePY1" /></td>
        </tr>
	    <tr>
	      <td height="35" align="right">五笔码搜索:</td>
	      <td><input type="text" name="nurseInput1" id="nurseWB1" /></td>
        </tr>
  </table>
</div>


<div id="dialogOrderPoint" style="background: #FFF;font-size:14px;">
    <table width="311" height="105" border="0" align="center" cellspacing="0">
    <!-- 
      <tr height="35">
        <td width="104"  align="right">医嘱内容： </td>
        <td width="203"><input type="text" name="txt6" id="orderPointName"  /> *</td>      
      </tr>
      yanneiOrderSelect
       -->
      <tr id="type1" >
        <td height="35" align="right"> 院内医嘱：</td>
        <td> 
        <select name="orderPointName" size="1" id="orderPointName" >
          <option>请选择</option>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
      <tr id="type2">
        <td height="35" align="right"> 自定义菜单：</td>
        <td width="203"><input type="text" name="txt6" id="orderPointName1"  /> *</td>
      </tr>
      
      <tr height="35" id="typeFlag">
        <td align="right">医嘱类型：</td>
        <td><select name="orderPointTypeSelect" size="1" id="orderPointTypeSelect" >
          <option value="0">临时医嘱</option>
          <option value="1">长期医嘱</option>
          <option value="2">出院医嘱</option>
        </select>
        *</td>
      </tr>
       
       <!-- 
      <tr height="35">
        <td align="right">是否必做：</td>
        <td >&emsp;<input name="orderPointNeed" type="radio" id="radiobutton" value="0" checked>
          可选项&emsp;
         <input type="radio" name="orderPointNeed" value="1" id="radiobutton">
         必做项</td>
      </tr>
      -->
  </table>
</div>
<!-- //编辑二级菜单 -->
<div id="dialogOrderPointe" style="background: #FFF;font-size:14px;">
    <table width="311" height="105" border="0" align="center" cellspacing="0">
      </tr>
      <tr id="type2">
        <td height="35" align="right"> 自定义菜单：</td>
        <td width="203"><input type="text" name="txt6" id="orderPointName1e"  /> *</td>
      </tr>
      
      <tr height="35">
        <td align="right">医嘱类型：</td>
        <td><select name="orderPointTypeSelect" size="1" id="orderPointTypeSelecte" >
          <option value="0" id="orderPointTypeSelecte0">临时医嘱</option>
          <option value="1" id="orderPointTypeSelecte1">长期医嘱</option>
          <option value="2" id="orderPointTypeSelecte2">出院医嘱</option>
        </select>
        *</td>
      </tr>
       
       <!-- 
      <tr height="35">
        <td align="right">是否必做：</td>
        <td >&emsp;<input name="orderPointNeed" type="radio" id="radiobutton" value="0" checked>
          可选项&emsp;
         <input type="radio" name="orderPointNeed" value="1" id="radiobutton">
         必做项</td>
      </tr>
      -->
  </table>
</div>
<!-- 复制二级节点 -->
<div id="dialogNode">
	     <table width="100%" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    		<tr bgcolor="d3eaef" class="STYLE10" align="center">
		        <td width="10%" height="20"  >节点号</td>
		        <td width="40%" height="20" >节点名称</td>
      		</tr>
      		<tbody id="nodeNamezk">
      		
      		</tbody>
    	</table>
</div>
		

<div id="order_item" style="background: #FFF;font-size:14px;">
    <table width="311" height="385" border="0" align="center" cellspacing="0">
    
      <tr id="order1">
        <td height="35" align="right">拼音码搜索：</td>
        <td><input type="text" name="orderInput" id="orderPY" /><input type="button" value="刷新" onclick="showCheck();"></td>
      </tr>
       <tr id="order2">
        <td height="35" align="right">拼音码搜索：</td>
        <td><input type="text" name="orderInput" id="orderPY2" /></td>
      </tr>

      <tr>
        <td width="104" height="35" align="right">医嘱编码：</td>
        <td width="203"><input type="text" name="txt11" id="orderCode" title="通过搜索获得,不可更改" readonly="readonly"/>*</td>
      </tr>

      <tr>
        <td height="35" align="right">医嘱内容： </td>
        <td><label><input type="text" name="txt6" id="orderName" title="通过搜索获得,不可更改"  readonly="readonly" />
      </label> 
          *      
      </tr>
       <tr>
      <td height="35" align="right">规　格： </td>
        <td><label><input type="text" name="txt6" id="specification" title="通过搜索获得,不可更改"  readonly="readonly" />
      </label> 
          *      
      </tr>
      
	  <tr>
		<td height="35" align="right">单价：</td>
		<td><label><input type="text" name="txt6"
			id="charge_amount" title="通过搜索获得,不可更改" readonly="readonly" /> </label> *
				
	  </tr>
	  
      <tr>
        <td height="35" align="right">医嘱类别：</td>
        <td>
        <select name="orderSelect" size="1" id="orderSelect" onclick="show()" onload="showbefore()">
          <option value="0" id="orderSelect1">临时医嘱</option>
          <option value="1" id="orderSelect2">长期医嘱</option>
          <option value="2" id="orderSelect3">出院医嘱</option>
          <option value="3" id="orderSelect4">长期+临时</option>
        </select>
        *</td>
      </tr>
     <!-- 
       <tr>
        <td height="35" align="right"> 院内医嘱类别：</td>
        <td><select name="yanneiOrderSelect" size="1" id="yanneiOrderSelect" >
          
        </select>
        *</td>
      </tr>
 -->
      <tr>
        <td height="35" align="right">是否必做：</td>
        <td >&emsp;<input name="orderItem" type="radio" id="radiobutton" value="0" >
          可选项&emsp;
         <input type="radio" name="orderItem" value="1" id="radiobutton" checked>
         必做项</td>
         
      </tr>
      <tr>
        <td height="35" align="right">是否默认：</td>
        <td >&emsp;<input name="orderItemDefault" type="radio" id="radiobutton1" value="0" >
                  否&emsp;&emsp;&emsp;
                   <input type="radio" name="orderItemDefault" value="1" id="radiobutton1">
              是</td>
        
      </tr>
      <tr>
        <td height="35" align="right">领 &nbsp; 量：</td>
        <td align="left" >
        <input name="orderPY" type="text" id="orderjl" size="5" maxlength="5" /> 
        单位：
        <input name="orderPY" type="text" id="orderjldw" size="5" maxlength="5" />
        <input name="orderPY" type="hidden" id="orderCode2" size="5" maxlength="5" />      
        </td>
      </tr>
      <tr>
        <td height="35" align="right">一次使用剂量：</td>
        <td align="left" >
        <input name="orderPY" type="text" id="dosage" size="5" maxlength="5" /> 
        单位：
        <input name="orderPY" type="text" id="dosage_units" size="5" maxlength="5" />
        <input name="orderPY" type="hidden" id="orderCode1" size="5" maxlength="5" />      
        </td>
      </tr>
      <tr>
        <td height="35" align="right">频 &nbsp; 次：</td>
        <td align="left" ><input name="orderPY4" type="text" id="orderPC" />
       </td>
      </tr>
      <tr>
        <td height="35" align="right">途 &nbsp; 径：</td>
        <td  ><input name="orderPY3" type="text" id="ordertj" /></td>
      </tr>

      <!-- 
      <tr>
        <td height="35" align="right">五笔码搜素：</td>
        <td><input type="text" name="orderInput" id="orderWB" /></td>
      </tr>
       -->
       <tr>
        <td height="35" align="right">医生嘱托(50字符)：</td>
        <td>
        	<textarea rows="5" cols="30" id="doctorMark"></textarea>
        </td>
      </tr>
  </table>
</div>

<div id="order_itemb" style="background: #FFF;font-size:14px;">
    <table width="311" height="385" border="0" align="center" cellspacing="0">
    
      <tr id="edit1">
        <td height="35" align="right">拼音码搜索：</td>
        <td><input type="text" name="orderInput" id="orderPYb" /><input type="button" value="刷新" onclick="showCheck2();"></td>
      </tr>
      <tr id="edit2">
        <td height="35" align="right">拼音码搜索：</td>
        <td><input type="text" name="orderInput2" id="orderPYb2" /></td>
      </tr>

      <tr>
        <td width="104" height="35" align="right">医嘱编码：</td>
        <td width="203"><input type="text" name="txt11" id="orderCodeb" readonly="readonly"/>*</td>
      </tr>

      <tr>
        <td height="35" align="right">医嘱内容： </td>
        <td><label><input type="text" name="txt6" id="orderNameb" title="通过搜索获得,不可更改"  readonly="readonly" />
      </label> 
          *      
      </tr>
       <tr>
      <td height="35" align="right">规　格： </td>
        <td><label><input type="text" name="txt6" id="specificationb" title="通过搜索获得,不可更改"  readonly="readonly" />
      </label> 
          *      
      </tr>
      <tr>
	  <td height="35" align="right">单价：</td>
	  <td><label><input type="text" name="txt6"
						id="charge_amountb" title="通过搜索获得,不可更改" readonly="readonly" /> </label> *
				
	  </tr>
      <tr>
        <td height="35" align="right">医嘱类别：</td>
        <td><select name="orderSelectb" size="1" id="orderSelectb" onclick="show()" onload="showbefore()">
          <option value="0" id="orderSelectb1">临时医嘱</option>
          <option value="1" id="orderSelectb2">长期医嘱</option>
          <option value="2" id="orderSelectb3">出院医嘱</option>
          <option value="3" id="orderSelectb4">长期+临时</option>
        </select>
        *</td>
      </tr>
     <!-- 
       <tr>
        <td height="35" align="right"> 院内医嘱类别：</td>
        <td><select name="yanneiOrderSelect" size="1" id="yanneiOrderSelect" >
          
        </select>
        *</td>
      </tr>
 -->
      <tr>
        <td height="35" align="right">是否必做：</td>
        <td >&emsp;<input name="orderItem" type="radio" id="radiobuttonb" value="0" >
          可选项&emsp;
                   <input type="radio" name="orderItem" value="1" id="radiobuttonb" checked>
         必做项</td>
        
      </tr>
      <tr>
        <td height="35" align="right">是否默认：</td>
        <td >&emsp;<input name="orderItemDefaultb" type="radio" id="radiobutton2" value="0" >
                  否&emsp;&emsp;&emsp;
                   <input type="radio" name="orderItemDefaultb" value="1" id="radiobutton2">
              是</td>
        
      </tr>
      <tr>
        <td height="35" align="right">领 &nbsp; 量：</td>
        <td align="left" >
        <input name="orderPY" type="text" id="orderjlb" size="5" maxlength="5" /> 
        单位：
        <input name="orderPY" type="text" id="orderjldwb" size="5" maxlength="5" />
        <input name="orderPY" type="hidden" id="orderCode2b" size="5" maxlength="5" />      
        </td>
      </tr>
      <tr>
        <td height="35" align="right">一次使用剂量：</td>
        <td align="left" >
        <input name="orderPY" type="text" id="dosageb" size="5" maxlength="5" /> 
        单位：
        <input name="orderPY" type="text" id="dosage_unitsb" size="5" maxlength="5" />
        <input name="orderPY" type="hidden" id="orderCode1b" size="5" maxlength="5" />      
        </td>
      </tr>
      <tr>
        <td height="35" align="right">频 &nbsp; 次：</td>
        <td align="left" ><input name="orderPY4" type="text" id="orderPCb" />
       </td>
      </tr>
      <tr>
        <td height="35" align="right">途 &nbsp; 径：</td>
        <td  >
        <input name="orderPY3" type="text" id="ordertjb" />
        <input name="orderPY3" type="hidden" id="ordertjbhid" />
        </td>
      </tr>

      <!-- 
      <tr>
        <td height="35" align="right">五笔码搜素：</td>
        <td><input type="text" name="orderInput" id="orderWB" /></td>
      </tr>
       -->
       <tr>
        <td height="35" align="right">医生嘱托(50字符)：</td>
        <td>
        	<textarea rows="5" cols="30" id="doctorMarkb"></textarea>
        </td>
      </tr>
  </table>
</div>
<div style=" height:180px;overflow-y:auto;overflow-x:hidden" id="dialogNodeSan">
       <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
       
          <tr height="25" class="STYLE10" bgcolor="d3eaef" align="center" >
		  <td width="5%"  >&nbsp;</td>
		  <td width="10%"  >序号</td>
		  <td width="85%" >菜单名称</td>
		  </tr>
         
         <tbody id="nodeNameSan">
           
         </tbody>
       </table>
     </div>
</body>
</html>
