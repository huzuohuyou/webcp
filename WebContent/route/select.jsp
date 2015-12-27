<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	DatabaseClass db =LcpUtil.getDatabaseClass();
	String patient_no = request.getParameter("patient_no");
	
	//更新执行科室
	      String sql = "select dept_admission_to from lcp_patient_visit where patient_no='" + patient_no + "'";
	      int rows = db.FunGetRowCountBySql(sql);
	      DataSetClass dataSet = db.FunGetDataSetBySQL(sql);
	      int count = dataSet.FunGetRowCount();
	      String dept = "";
	      String updateSql = "";
	      for (int i = 0; i < count; ++i) {
	        String dept_admission_to = dataSet.FunGetDataAsStringByColName(i, "DEPT_ADMISSION_TO");
	        if (!(dept_admission_to.equals("普外儿外病区"))) {
	          if (dept_admission_to.endsWith("一病区")) {
	            if (dept_admission_to.equals("妇科一病区"))
	              dept = "妇产科";
	            else
	              dept = dept_admission_to.replace("一病区", "");
	          }
	          else if (dept_admission_to.endsWith("二病区")) {
	            if (dept_admission_to.equals("妇科二病区"))
	              dept = "妇产科";
	            else
	              dept = dept_admission_to.replace("二病区", "");
	          }
	          else if ((dept_admission_to.endsWith("病区")) && (!(dept_admission_to.endsWith("一病区"))) && 
	            (!(dept_admission_to.endsWith("二病区"))) && (!(dept_admission_to.endsWith("VIP病区"))))
	            dept = dept_admission_to.replace("病区", "");
	          else if (dept_admission_to.endsWith("VIP病区")) {
	            if (dept_admission_to.equals("产科VIP病区"))
	              dept = "妇产科";
	            else
	              dept = dept_admission_to.replace("VIP病区", "");
	          }
	          else
	            dept = dept_admission_to;
	        }
	        else {
	          dept = dept_admission_to;
	        }
	        updateSql = "update lcp_patient_visit set execute_dept='" + dept + "' where patient_no='" + patient_no + "'";
	        db.FunRunSQLCommand(db.FunGetSvrKey(), updateSql);
	      }
	  //符合路径纳入
	int hosid = LcpUtil.getHospitalID();
	String sqlFuhe = "select cp_code, cp_id, cp_name, cp_version, cp_days, cp_fee, dept_name  from lcp_master "+ 
				     "where cp_status = 0   and cp_id in  (select t1.cp_id   from lcp_master_income t1, lcp_hospital_vs_cp t2,"+
				 	 "lcp_master t3   where ((t2.hospital_id = "+hosid+" and  t1.hospital_id = t2.hospital_id "+
				  	 "and t1.cp_id = t2.cp_id and  t2.cp_status = 1)  or  (t3.cp_id >= 10000 and t1.cp_id = t3.cp_id)) "+
				 	 "and t1.cp_income_code in (select income_code  from lcp_patient_log_income where patient_no = '"+patient_no+"' "+
	  			 	 "and (trim(local_key) in('2_1_0_初步诊断','3_1_0_最后诊断') or income_type='手术'))) order by cp_id";
	String executedept = "select execute_dept from lcp_patient_visit  where patient_no='"+patient_no+"'";
	String execute_dept = db.FunGetDataSetBySQL(executedept).FunGetDataAsStringByColName(0,"EXECUTE_DEPT");
	String sqlDept ="";
	if("".equals(execute_dept))//科室定义路径纳入
	{ 
	  sqlDept ="select cp_code, cp_id, cp_name, cp_version, cp_days, cp_fee, dept_name  from lcp_master where cp_status = 0 and dept_name like '"+execute_dept+"'";
	}else{
	  sqlDept ="select cp_code, cp_id, cp_name, cp_version, cp_days, cp_fee, dept_name  from lcp_master where cp_status = 0 and dept_name like '%"+execute_dept+"%'";
	}
  //String sqlDept="select cp_code, cp_id, cp_name, cp_version, cp_days, cp_fee, dept_name from lcp_master t "+
 // 	           "where t.dept_code = (select t1.dept_code from lcp_patient_visit t, lcp_master t1  "+
 //                 "where t.conform_cp_id = t1.cp_id and t.patient_no = '"+patient_no+"') and t.cp_status=0";        
	DataSetClass dataSetFuhe = db.FunGetDataSetBySQL(sqlFuhe);
	DataSetClass dataSetDept = db.FunGetDataSetBySQL(sqlDept);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet"
	href="../public/plugins/jquery/themes/base/jquery.ui.all.css">
	<link rel="stylesheet"
	href="../public/plugins/jquery/themes/base/jquery.ui.tabs.css">
<script src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
<script src="../public/plugins/jquery/external/jquery.bgiframe-2.1.2.js"></script>
<script src="../public/plugins/jquery/ui/jquery.ui.core.js"></script>
<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
<script src="../public/plugins/jquery/ui/jquery.ui.position.js"></script>
<script src="../public/plugins/jquery/ui/jquery.ui.dialog.js"></script>
<script src="../public/plugins/jquery/ui/jquery.ui.tabs.js"></script>


<title>临床路径纳入页面</title>


<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-size: 80%;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
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

.STYLE6 {
	color: #000000;
	font-size: 13px;
	height: 20px;
}

.STYLE19 {
	color: #344b50;
	font-size: 12px;
}

-->
</style>
<script>
var chdval ="";
var patient_no = '<%=request.getParameter("patient_no")%>';
var userid = '<%=request.getParameter("user_id")%>';
var username = '<%=request.getParameter("user_name")%>';
$(function() {
	$( "#div1" ).dialog({
		title:"路径信息",
		autoOpen: false,
		modal: true,
		height:700,
		width:1120,
		resizable:true,
		draggable:true,
		open: function(){

            $('#div1').html('<iframe src="../version/viewcp.jsp?cp_id='+chdval+'" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');
       }

		});
	
	$( "#div2" ).dialog({
		title:"不纳入路径登记",
		autoOpen: false,
		modal: true,
		height:300,
		width:450,
		resizable:true,
		draggable:true
		});
	
	$( "#div3" ).dialog({
		title:"操作成功！",
		autoOpen: false,
		modal: true,
		height:200,
		width:200,
		resizable:false,
		draggable:true,
		open:function(){
			$('a.ui-dialog-titlebar-close').hide();  
		}
		});
	
	$("#tabs").tabs();


});	

 

function xuanze(tr){
	$(tr).find("td").eq(0).find("input").attr("checked","checked");
}

function chakan(cpid){
		chdval = cpid;
		$('#div1').dialog("open");
		 
	}
	
function opendialog(){
		$('#div2').dialog("open");
		
	}
	
	
function changetxt(val){
	document.all.textarea1.value = val;
}

function naru(){
		if($('input[name="radio"]:checked').val()==null){
				alert("请选择路径！");
		}else{
			$.ajax({
				url:'../servlet/SelectServlet?op=naru',
				type:'POST',
				async: false,
				data:
				{cp_id:$('input[name="radio"]:checked').val(),
				patient_id:patient_no,
				userid:userid,
				username:username
				},
				dataType: "json",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				success:function(data, textStatus, XMLHttpRequest){
					//alert(data);
					if(data){
						window.external.FunCPSelCompleted();
					}else{
						alert("网络异常！请检查网络是否畅通！");
					}
				}
			});	
	}
}
function bunaru(){
	if($('#select2').val()==""){
		alert("请选不纳入路径的原因类型！");
	}else{
		if($('#textarea1').val().replace(/\s+/g,"")!=''){
			if(confirm("选择不纳入以后将不会再提示纳入路径，如暂时不纳入请将本页面关闭！\n您确定不纳入吗？")){
				$.ajax({
					url:'../servlet/SelectServlet?op=bunaru',
					type:'POST',
					async: false,
					data:
					{
					patient_id:patient_no,
					code:$('#select2').val(),
					exclude:$('#textarea1').val(),
					username:username
					},
					dataType: "json",
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					success:function(data, textStatus, XMLHttpRequest){
						if(data){
							$('#div2').dialog("close");
							window.external.FunCPSelCompleted();
						}else{
							alert("网络异常！请检查网络是否畅通！");
						}
					}
				});
			}
		}else{
			alert("不纳入原因不能为空！");
			return false;
		}
	}
}

function wait(){
	window.opener=null;//禁止关闭窗口的提示
	window.close();//自动关闭窗口
}

var  highlightcolor='#d5f4fe';
var recoveryColot='#FFFFFF';

function changeColor(event1){
	event1.style.backgroundColor=highlightcolor;
}
function recoverColor(event1){
	event1.style.backgroundColor=recoveryColot;
}

function tuichu(){
		$('#div2').dialog("close");
}
</script>
</head>

<body>
<div id="tabs">
	<table width="100%" style="margin-top: 0em;padding-top: 0em">
	  <tr>
		 <td>
			<ul>
				<li><a href="#fhlj"><span style="font-size: 12px">符合路径</span></a></li>
				<li><a href="#ksdylz"><span style="font-size: 12px">科室定义路径</span></a></li>
			</ul>

			<div id="fhlj"  style="height:auto;padding-top: 1.0em;padding-left:0.1em;padding-right: 0.1em " >
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td height="24" bgcolor="#353c44">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="4%" height="19" valign="bottom" align="center">
										<img src="../public/images/tb.gif" width="14" height="14" />
									</td>
									<td width="96%" valign="bottom" class="STYLE1">
										患者符合的临床路径列表 ， 请选择是否纳入
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td width="100%">
							<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
								<tr  class="STYLE6">
									<td width="2%"   bgcolor="d3eaef" align="center">&nbsp;</td>
									<td width="12%"  bgcolor="d3eaef" align="center">编号</td>
									<td width="20%"  bgcolor="d3eaef" align="center">名称</td>
									<td width="10%"  bgcolor="d3eaef" align="center">版本</td>
									<td width="12%"  bgcolor="d3eaef" align="center">天数</td>
									<td width="18%"  bgcolor="d3eaef" align="center">费用</td>
									<td width="15%"  bgcolor="d3eaef" align="center">科室</td>
									<td width="10%"   bgcolor="d3eaef" align="center">&nbsp;</td>
								</tr>
								<%
									if (dataSetFuhe.FunGetRowCount() > 0) {
										for (int i = 0; i < dataSetFuhe.FunGetRowCount(); i++) {
								%>
								<tr  bgcolor="#FFFFFF" onclick="xuanze(this)" id="<%= dataSetFuhe.FunGetDataAsNumberByColName(i, "CP_ID")%>" 
							        style="cursor:pointer" onmouseover="changeColor(this)" onmousemove="changeColor(this)" onMouseOut="recoverColor(this)">
									<td height="20" align="center">
										<input type="radio" name="radio" id="radio" value="<%= dataSetFuhe.FunGetDataAsNumberByColName(i, "CP_ID")%>">
									</td>
									<td height="20" align="center" class="STYLE6"><%= dataSetFuhe.FunGetDataAsStringByColName(i, "CP_CODE")%></td>
									<td height="20" align="center" class="STYLE19"><%= dataSetFuhe.FunGetDataAsStringByColName(i, "CP_NAME")%></td>
									<td height="20" align="center" class="STYLE19"><%= dataSetFuhe.FunGetDataAsStringByColName(i,"CP_VERSION")%></td>
									<td height="20" align="center" class="STYLE19"><%= dataSetFuhe.FunGetDataAsNumberByColName(i, "CP_DAYS")%></td>
									<td height="20" align="center" class="STYLE19"><%= dataSetFuhe.FunGetDataAsNumberByColName(i, "CP_FEE")%></td>
									<td align="center" class="STYLE19"><%= dataSetFuhe.FunGetDataAsStringByColName(i,"DEPT_NAME")%></td>
									<td height="20" align="center" class="STYLE19"><input type="submit" name="button3" value="查看"
										onClick="chakan(<%= dataSetFuhe.FunGetDataAsNumberByColName(i, "CP_ID")%>)">
									</td>
								</tr>
								<%
									}
									}
								%>
					
							</table>
						</td>
					</tr>
				</table>
				<br/>
				<div align="right"><input type="button" name="button5" id="button5" onClick="opendialog()" value="不纳入路径" /> 
					&nbsp;&nbsp;&nbsp;<input type="button" name="button4" id="button4" onClick="naru()" value="纳入路径"> 
			        &nbsp;&nbsp;&nbsp;<input type="button" name="button16" id="button16" onClick="wait()" value="稍后提醒"> 
			    </div>
		</div>
	    <div id="ksdylz"  style="height:auto;padding-top: 1.0em;padding-left:0.1em;padding-right: 0.1em">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td height="24" bgcolor="#353c44">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="4%" height="19" valign="bottom" align="center">
										<img src="../public/images/tb.gif" width="14" height="14" />
									</td>
									<td width="96%" valign="bottom" class="STYLE1">
										科室定义的临床路径列表，请选择是否纳入
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td width="100%">
							<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
								<tr  class="STYLE6">
									<td width="2%"   bgcolor="d3eaef" align="center">&nbsp;</td>
									<td width="12%"  bgcolor="d3eaef" align="center">编号</td>
									<td width="20%"  bgcolor="d3eaef" align="center">名称</td>
									<td width="10%"  bgcolor="d3eaef" align="center">版本</td>
									<td width="12%"  bgcolor="d3eaef" align="center">天数</td>
									<td width="12%"  bgcolor="d3eaef" align="center">费用</td>
									<td width="15%"  bgcolor="d3eaef" align="center">科室</td>
									<td width="10%"   bgcolor="d3eaef" align="center">&nbsp;</td>
								</tr>
								<%
									if (dataSetDept.FunGetRowCount() > 0) {
										for (int i = 0; i < dataSetDept.FunGetRowCount(); i++) {
								%>
								<tr  bgcolor="#FFFFFF" onclick="xuanze(this)" id="<%= dataSetDept.FunGetDataAsNumberByColName(i, "CP_ID")%>" 
							        style="cursor:pointer" onmouseover="changeColor(this)" onmousemove="changeColor(this)" onMouseOut="recoverColor(this)">
									<td height="20" align="center">
										<input type="radio" name="radio" id="radio" value="<%= dataSetDept.FunGetDataAsNumberByColName(i, "CP_ID")%>">
									</td>
									<td height="20" align="center" class="STYLE6"><%= dataSetDept.FunGetDataAsStringByColName(i, "CP_CODE")%></td>
									<td height="20" align="center" class="STYLE19"><%= dataSetDept.FunGetDataAsStringByColName(i, "CP_NAME")%></td>
									<td height="20" align="center" class="STYLE19"><%= dataSetDept.FunGetDataAsStringByColName(i,"CP_VERSION")%></td>
									<td height="20" align="center" class="STYLE19"><%= dataSetDept.FunGetDataAsNumberByColName(i, "CP_DAYS")%></td>
									<td height="20" align="center" class="STYLE19"><%= dataSetDept.FunGetDataAsNumberByColName(i, "CP_FEE")%></td>
									<td align="center" class="STYLE19"><%= dataSetDept.FunGetDataAsStringByColName(i,"DEPT_NAME")%></td>
									<td height="20" align="center" class="STYLE19"><input type="submit" name="button3" value="查看"
										onClick="chakan(<%=dataSetDept.FunGetDataAsNumberByColName(i, "CP_ID")%>)">
									</td>
								</tr>
								<%
									}
									}
								%>
					
							</table>
						</td>
					</tr>
				</table>
				<br/>
				<div align="right"><input type="button" name="button5" id="button5" onClick="opendialog()" value="不纳入路径" /> 
					&nbsp;&nbsp;&nbsp;<input type="button" name="button4" id="button4" onClick="naru()" value="纳入路径"> 
			        &nbsp;&nbsp;&nbsp;<input type="button" name="button16" id="button16" onClick="wait()" value="稍后提醒"> 
			    </div>
		     </div>
		  </td>
	   </tr>
	</table>
</div>

<div id="div1"></div>
<div id="div2" align="center">
	<table width="400" height="180" border="0">
		<tr>
			<td width="30%" height="29" align="right">原因类型：</td>
			<td width="70%" align="left">
				<select name="select2" size="1" id="select2" onChange="changetxt(this.options[this.selectedIndex].innerText)">
				    <option value="">请选择原因类型</option>
					<%
						String sql1 = "select t2.variation_code,t2.variation_name from dcp_dict_varia_type t1,dcp_dict_variation t2 "+ 
						"where t1.variation_type_code=t2.variation_type_sub and t1.variation_type_name = '不纳入原因' order by t2.variation_code";
						DataSetClass dataSet1 = db.FunGetDataSetBySQL(sql1);
						if (dataSet1.FunGetRowCount() > 0) {
							for (int i = 0; i < dataSet1.FunGetRowCount(); i++) {
								String exclude = dataSet1.FunGetDataAsStringById(i, 1);
								String value = dataSet1.FunGetDataAsStringById(i, 0);
					%>
					<option value="<%=value%>"><%=exclude%></option>
					<%
						}
						}
					%>
				</select>
			</td>
		</tr>
		<tr valign="top">
			<td height="135" align="right">不纳入原因：</td>
			<td align="left"><textarea name="textarea1" cols="35" rows="10" id="textarea1"></textarea></td>
		</tr>
		<tr>
			<td height="28" align="right">&nbsp;</td>
			<td align="left"><input type="button" name="button6" id="button6" onclick="bunaru()" value="提交">  
			     &nbsp;&nbsp;<input type="button" name="button7" id="button7" onClick="tuichu()" value="取消"></td>
		</tr>
	</table>
</div>

<div id = "div3">
请关闭当前页面！
</div>

</body>
</html>