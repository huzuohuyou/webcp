<%-- 
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：varia.jsp
// 文件功能描述：填写变异页面
// 创建人：康榕元
// 创建日期：2011/08/24
// 修改日期：2011/09/02
// 
//----------------------------------------------------------------*/
 --%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("gbk");
DatabaseClass db = LcpUtil.getDatabaseClass();
DataSetClass dataSet = new DataSetClass();
String sql="select t.variation_code,t.variation_name,t.variation_type_sub from dcp_dict_variation t where t.variation_type_big = 'V2'";
dataSet = db.FunGetDataSetBySQL(sql);
String v21 = "";
String v22 = "";
String v23 = "";
for (int i = 0; i < dataSet.FunGetRowCount(); i++) {
	String name = dataSet.FunGetDataAsStringByColName(i,"VARIATION_NAME");
	String val = dataSet.FunGetDataAsStringByColName(i,"VARIATION_CODE");
	String v2 = dataSet.FunGetDataAsStringByColName(i,"VARIATION_TYPE_SUB");
	name = name.replace("\"","'");
	if("V21".equals(v2)){
		v21 += "<option value='"+val+"'>"+name+"</option>";
	}else if("V22".equals(v2)){
		v22 += "<option value='"+val+"'>"+name+"</option>";
	}else if("V23".equals(v2)){
		v23 += "<option value='"+val+"'>"+name+"</option>";
	}
}

String pa_id = request.getParameter("pa_id");
String cp_id = request.getParameter("cp_id");
String no_id = request.getParameter("no_id");
//jsonStr = new String(jsonStr.getBytes("iso-8859-1"),"gbk").trim();
String sqlvaria = "select t.auto_id,t.variation_code,t.variation_content,t.local_order_text,t1.VARIATION_TYPE_SUB,t1.variation_name from lcp_patient_log_order_varia t,dcp_dict_variation t1 where t.variation_code = t1.variation_code and t.patient_no = '"+pa_id+"' and t.cp_id = "+cp_id+" and t.cp_node_id = "+no_id;
System.out.println(sqlvaria);
DataSetClass dataSetVaria = new DataSetClass();
dataSetVaria = db.FunGetDataSetBySQL(sqlvaria);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="../public/plugins/jquery/themes/base/jquery.ui.all.css"></link>
<script src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
<script src="../public/plugins/jquery/external/jquery.bgiframe-2.1.2.js"></script>
<script src="../public/plugins/jquery/ui/jquery.ui.core.js"></script>
<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
<script src="../public/plugins/jquery/ui/jquery.ui.mouse.js"></script>
<script src="../public/plugins/jquery/ui/jquery.ui.draggable.js"></script>
<script src="../public/plugins/jquery/ui/jquery.ui.position.js"></script>
<script src="../public/plugins/jquery/ui/jquery.ui.resizable.js"></script>
<script src="../public/plugins/jquery/ui/jquery.ui.dialog.js"></script>
<script src="../public/plugins/jquery/ui/jquery.effects.core.js"></script>
<title>无标题文档</title>

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

body {
	font-size: 80%;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 350px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td,div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}

.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}

.STYLE2 {
	font-size: 12px;
	color: #0000FF;
}

.STYLE6 {
	color: #000000;
	font-size: 12;
}

.STYLE10 {
	color: #000000;
	font-size: 12px;
}

.STYLE19 {
	color: #344b50;
	font-size: 12px;
}

.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}

.STYLE22 {
	font-size: 12px;
	color: #295568;
}

.STYLE4 {
	font-size: 12px
}
-->
</style>

<script type="text/javascript">
var v21 = "<%=v21%>";
var v22 = "<%=v22%>";
var v23 = "<%=v23%>";
var pa_id = "<%=pa_id%>";
$(function() {
	$( "#div2" ).dialog({
		title:"变异原因记录",
		autoOpen: false,
		modal: true,
		height:345,
		width:470,
		resizable:true,
		draggable:true
		});
	
	
});	
function chakan(cpid){
	   idp = cpid.id;
		var yizhuming = $(cpid).find("td").eq(1).find("div").html();
		var yuanyinid = $(cpid).find("td").eq(2).find("div").attr("id");
		var shuoming = $(cpid).find("td").eq(3).find("div").html();
		var leixing = cpid.value;
		if(leixing=='必选未用'){
				$('#select2').html(v21); 
		}else if(leixing=='选择可选'){
				$('#select2').html(v22); 
		}else if(leixing=='新增医嘱'){
				$('#select2').html(v23); 
			}
		if(shuoming==''){
			$('#select2 option:first').attr('select','select');
			$("#textarea1").html($('#select2 option:first').text());
		}else{
			$('#select2').val(yuanyinid); 
			$("#textarea1").html(shuoming);
		}
		$("#yizhuming").html(yizhuming);
		$("#leixing").html(leixing);
		$('#div2').dialog("open");
	}
	
function queding(){
		var yuanyin = $('#select2 option:selected').text();
		var yuanyinid = $('#select2').val();
		var shuoming = $("#textarea1").html();
		if(shuoming.replace(/\s+/g,"")!=''){
			$("tr#"+idp).find("td").eq(2).find("div").attr("id",yuanyinid);
			$("tr#"+idp).find("td").eq(2).find("div").html(yuanyin);
			$("tr#"+idp).find("td").eq(3).find("div").html(shuoming);
			$('#div2').dialog("close");
		}else{
			alert("说明不能为空！");
		}
	}
function tijiao(){
		
		var reStr = "";
	$("#table1 tr[name='tr']").each(function(){  
		var id =  $(this).attr("id");
		var yuanyinid = $(this).find("td").eq(2).find("div").attr("id"); 
		var shuoming = $(this).find("td").eq(3).find("div").html(); 
		if(shuoming.replace(/\s+/g,"")!=''){
			reStr += ",{'id':'"+id+"','yuanyinid':'"+yuanyinid+"','shuoming':'"+shuoming+"'}";
		}
	});
	if(reStr==''){
	alert("您并没有填任何变异！");
	}else{
			reStr = reStr.substring(1);
			reStr = "{'pa_id':'"+pa_id+"','list':["+reStr+"]}";
			
			$.ajax({
						url:'../servlet/VariaServlet?op=varia',
						type:'POST',
						async: false,
						data:
						{reStr:reStr
						},
						dataType: "json",
						success:function(data, textStatus, XMLHttpRequest){
							if(data){
								alert("提交成功，此页面即将关闭！");
								window.opener=null;
								window.close();
							}else{
								alert("网络异常！数据没有成功提交！请检查网络是否畅通！");
							}
						}
					});	
			
		}
	
}
	
	
function changetxt(val){
	document.all.textarea1.value = val;
}


function tuichu(){
		$('#div2').dialog("close");
}


var  highlightcolor='#d5f4fe';
var recoveryColot='#FFFFFF';

function changeColor(event1){
	event1.style.backgroundColor=highlightcolor;
}
function recoverColor(event1){
	event1.style.backgroundColor=recoveryColot;
}
</script>
</head>

<body>


<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="1">&nbsp;</td>
		<td height="23">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="23" bgcolor="#353c44">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="23">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="34" height="19" valign="bottom">
								<div align="center"><img src="../public/images/tb.gif"
									width="14" height="14" /></div>
								</td>
								<td  valign="bottom"><span class="STYLE1">
								变异列表</span></td>
							</tr>
						</table>
						</td>
						<td>
						<div align="right"><span class="STYLE1">&nbsp;</span><span
							class="STYLE1"> &nbsp;</span></div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
		<td width="1">&nbsp;</td>
	</tr>
	<tr>
		<td width="1">&nbsp;</td>
		<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="1"
			bgcolor="#a8c7ce" id="table1" >
			<tr>
			  <td width="19%" bgcolor="d3eadf" class="STYLE6"><div align="center">变异类型</div></td>
			  <td width="30%" height="20" bgcolor="d3eadf" class="STYLE6">
				<div align="center"><span class="STYLE10">医嘱名称</span></div>
				</td>
				<td width="23%" bgcolor="d3eadf" class="STYLE6" align="center"><div align="center"><span class="STYLE10">原因</span></div></td>
				<td width="28%" bgcolor="d3eadf" class="STYLE6"><div align="center"><span class="STYLE10">说明</span></div></td>
			</tr>
            <tr bgcolor="#d3eaef"><td  class="STYLE19"><div align="center" style=" font-size:16px;color:#00f;font-weight:bold;">必选未用</div></td><td height="19"  colspan="4"   class="STYLE19">&nbsp;</td></tr>
            
            <%
			for(int i = 0;i<dataSetVaria.FunGetRowCount();i++){
				String variation_type_sub = dataSetVaria.FunGetDataAsStringByColName(i,"VARIATION_TYPE_SUB");
				System.out.println("变异类型："+variation_type_sub);
				if("V21".equals(variation_type_sub)){
					String auto_id = dataSetVaria.FunGetDataAsStringByColName(i,"AUTO_ID");
					String variation_content = dataSetVaria.FunGetDataAsStringByColName(i,"VARIATION_CONTENT");
					String local_order_text = dataSetVaria.FunGetDataAsStringByColName(i,"LOCAL_ORDER_TEXT");
					String variation_code = dataSetVaria.FunGetDataAsStringByColName(i,"VARIATION_CODE");
					String variation_name =  "";
					if(variation_content!=""){
					variation_name =  dataSetVaria.FunGetDataAsStringByColName(i,"VARIATION_NAME");
					}
			%>  
			<tr bgcolor="#FFFFFF" onclick="chakan(this)" name = "tr" id="<%=auto_id%>" value = "必选未用" style="cursor:pointer" onmouseover="changeColor(this)" onmousemove="changeColor(this)" onMouseOut="recoverColor(this)"><td class="STYLE19"><div align="center"><img src="../public/images/detail_s3.png" width="20" height="20" /></div></td><td height="20" class="STYLE19"><div align="center" id = ""><%=local_order_text %></div></td><td class="STYLE19"><div align="center" id="<%=variation_code%>"><%=variation_name %></div></td><td class="STYLE19"><div align="center"><%=variation_content%></div></td></tr>
			
			<%
			}
			}
			%>
            
            
            <tr bgcolor="#d3eaef"><td  class="STYLE19"><div align="center" style=" font-size:16px;color:#00f;font-weight:bold;">选择可选</div></td><td height="20"  colspan="4"  class="STYLE19"></td></tr>
            
            <%
			for(int i = 0;i<dataSetVaria.FunGetRowCount();i++){
				String variation_type_sub = dataSetVaria.FunGetDataAsStringByColName(i,"VARIATION_TYPE_SUB");
				System.out.println("变异类型："+variation_type_sub);
				if("V22".equals(variation_type_sub)){
					String auto_id = dataSetVaria.FunGetDataAsStringByColName(i,"AUTO_ID");
					String variation_content = dataSetVaria.FunGetDataAsStringByColName(i,"VARIATION_CONTENT");
					String local_order_text = dataSetVaria.FunGetDataAsStringByColName(i,"LOCAL_ORDER_TEXT");
					String variation_code = dataSetVaria.FunGetDataAsStringByColName(i,"VARIATION_CODE");
					String variation_name =  "";
					if(variation_content!=""){
					variation_name =  dataSetVaria.FunGetDataAsStringByColName(i,"VARIATION_NAME");
					}
			%>  
			<tr bgcolor="#FFFFFF" onclick="chakan(this)" name = "tr" id="<%=auto_id%>" value = "选择可选" style="cursor:pointer" onmouseover="changeColor(this)" onmousemove="changeColor(this)" onMouseOut="recoverColor(this)"><td class="STYLE19"><div align="center"><img src="../public/images/detail_s3.png" width="20" height="20" /></div></td><td height="20" class="STYLE19"><div align="center" id = ""><%=local_order_text %></div></td><td class="STYLE19"><div align="center" id="<%=variation_code%>"><%=variation_name %></div></td><td class="STYLE19"><div align="center"><%=variation_content%></div></td></tr>
			
			<%
			}
			}
			%>
            
            <tr bgcolor="#d3eaef"><td  class="STYLE19"><div align="center" style=" font-size:16px;color:#00f;font-weight:bold;">新增医嘱</div></td><td height="20" colspan="4"  class="STYLE19"></td></tr>
			
			<%
			for(int i = 0;i<dataSetVaria.FunGetRowCount();i++){
				String variation_type_sub = dataSetVaria.FunGetDataAsStringByColName(i,"VARIATION_TYPE_SUB");
				System.out.println("变异类型："+variation_type_sub);
				if("V23".equals(variation_type_sub)){
					String auto_id = dataSetVaria.FunGetDataAsStringByColName(i,"AUTO_ID");
					String variation_content = dataSetVaria.FunGetDataAsStringByColName(i,"VARIATION_CONTENT");
					String local_order_text = dataSetVaria.FunGetDataAsStringByColName(i,"LOCAL_ORDER_TEXT");
					String variation_code = dataSetVaria.FunGetDataAsStringByColName(i,"VARIATION_CODE");
					String variation_name =  "";
					if(variation_content!=""){
					variation_name =  dataSetVaria.FunGetDataAsStringByColName(i,"VARIATION_NAME");
					}
			%>  
			<tr bgcolor="#FFFFFF" onclick="chakan(this)" name = "tr" id="<%=auto_id%>" value = "新增医嘱" style="cursor:pointer" onmouseover="changeColor(this)" onmousemove="changeColor(this)" onMouseOut="recoverColor(this)"><td class="STYLE19"><div align="center"><img src="../public/images/detail_s3.png" width="20" height="20" /></div></td><td height="20" class="STYLE19"><div align="center" id = ""><%=local_order_text %></div></td><td class="STYLE19"><div align="center" id="<%=variation_code%>"><%=variation_name %></div></td><td class="STYLE19"><div align="center"><%=variation_content%></div></td></tr>
			
			<%
			}
			}
			%>
			
		</table>
</td>
		<td width="1">&nbsp;</td>
	</tr>

</table>
<div id="div1" align="center"><input type="button" value="提 交" onclick="tijiao()"/></div>
<div id="div2" align="center">
<label id="yizhuming" style="font-size:16px">请填写必选项&quot;XXXXX&quot;未做的原因</label>
<table width="441" border="0">
	<tr>
	  <td height="33" align="right">类 型：</td>
	  <td align="left"><label id="leixing">asdf&nbsp;&nbsp;&nbsp;</label></td>
    </tr>
	<tr>
		<td width="73" height="34" align="right">原 因：</td>
    <td width="358" align="left"><select name="select2" size="1" id="select2" onChange="changetxt(this.options[this.selectedIndex].innerText)">
	  </select></td>
	</tr>
	<tr valign="top">
		<td height="127" align="right">说 明：</td>
	  <td align="left"><textarea name="textarea1" cols="40" rows="9"
			id="textarea1"></textarea></td>
	</tr>
	<tr>
		<td height="34" align="right">&nbsp;</td>
	  <td align="left"><input type="button" name="button6" id="button6"
			onclick="queding()" value="确定" > &nbsp; <input type="button"
			name="button7" id="button7" onClick="tuichu()" value="取消"></td>
	</tr>
</table>
</div>
</body>
</html>
