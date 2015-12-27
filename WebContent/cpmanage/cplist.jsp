<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：cplist.jsp  
// 文件功能描述：本地临床路径维护浏览页,显示临床路径列表
// 创建人：潘状
// 创建日期：2011-8-27
//----------------------------------------------------------------*/ 
%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil" %>
<%@page import="com.goodwillcis.general.DatabaseClass" %>
<%@page import="com.goodwillcis.general.DataSetClass" %>
<%! String deptCode="";
	String deptName="";
%>
<% 
	deptCode=(String)request.getSession().getAttribute("deptcode");
	String ss = "select dept_name from lcp_local_dept where dept_code="+deptCode;
	DatabaseClass db1 = LcpUtil.getDatabaseClass();
	deptName=db1.FunGetDataSetBySQL(ss).FunGetDataAsStringById(0,0);
%>
<%-- <%@include file="../frames/power.jsp" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="../public/plugins/jquery/themes/cupertino/jquery-ui-1.8.11.custom.css"> 
	<script type="text/javascript" src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>	
	<link rel="stylesheet"  type="text/css" href="../public/plugins/jqgrid/ui.jqgrid.css">
	<script type="text/javascript"  src="../public/plugins/jquery/ui/jquery.ui.core.js"></script>
	<script type="text/javascript"  src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript"  src="../public/plugins/jquery/ui/jquery.ui.position.js"></script>
	<script type="text/javascript"  src="../public/plugins/jquery/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="../public/plugins/jqgrid/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="../public/plugins/jqgrid/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="../public/plugins/jqgrid/jquery.form.js"></script>
	<script type="text/javascript"  src="../public/javascripts/cpmanage/charscode.js"></script>
	<script type="text/javascript"  src="../public/javascripts/cpmanage/cplist.js"></script>
	<script type="text/javascript" >
	var deptName="<%=deptName%>";
	var deptCode="<%=deptCode%>";
	</script>
	
<title>本地路径信息查看</title>
<style type="text/css">
<!--

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	
	}

.STYLE10 {color: #000000; font-size: 12px;font-weight:normal; }
.STYLE11 {color: #000000; font-size: 12px;font-weight:normal;background-color: #d3eaef; }
.tabheightandwidge{
	height: 600px;
}

th{
	font-size=12px;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
.title {
	color: #e1e2e3;
	font-size: 12px;
}
.ui-dialog-title {
	font-size: 14px;
}
-->
</style>
</head>

<body >
	<%
		String userID=(String)request.getSession().getAttribute("userid");
		String sql = "select funs_id from dcp_sys_power where user_id="+userID;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dataSet = db.FunGetDataSetBySQL(sql);
		String id="";
		String funid="";
		for(int i=0;i<dataSet.FunGetRowCount();i++){
			id=dataSet.FunGetDataAsStringByColName(i,"FUNS_ID");
			funid+=id;
		}
	%>
<div style=" height:180px;overflow-y:auto;overflow-x:hidden" id="cpSubmitContent">
       <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
       
          <tr height="25" class="STYLE10" bgcolor="d3eaef" align="center">
		  <td align="left" colspan="4"><strong>请填写申请原因（限500字）：</strong></td>
		  </tr>
		  <tr class="STYLE10" bgcolor="d3eaef" align="center" >
		  <td align="left" colspan="4"><textarea cols="60" rows="10" id="ctx"></textarea></td>
		  </tr>
		  <tr height="25" class="STYLE11">
		  <td align="left">申请人：<span id="user"><%=session.getAttribute("username") %></span></td>
		  <td align="left">申请时间：<span id="time"><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) %></span></td>
		  </tr>
       </table>
</div>
<div style=" height:180px;overflow-y:auto;overflow-x:hidden" id="verify">
       <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
       
          <tr height="25" class="STYLE10" bgcolor="d3eaef" align="center">
		  <td align="left" colspan="4"><strong  title="我也不知道">申请原因：</strong></td>
		  </tr>
		  <tr class="STYLE10" bgcolor="d3eaef" align="center" >
		  <td align="left" colspan="4"><textarea cols="60" rows="8" id="ctx2" readonly="readonly"></textarea></td>
		  </tr>
		  <tr height="25" class="STYLE11">
		  <td align="left">申请人：<span id="user2"> </span></td>
		  <td align="left">申请时间：<span id="time2"> </span></td>
		  </tr>
       </table>
</div>
<div style=" height:180px;overflow-y:auto;overflow-x:hidden" id="return">
       <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
       
          <tr height="25" class="STYLE10" bgcolor="d3eaef" align="center">
		  <td align="left" colspan="4"><strong>请填写退回原因（限500字）：</strong></td>
		  </tr>
		  <tr class="STYLE10" bgcolor="d3eaef" align="center" >
		  <td align="left" colspan="4"><textarea cols="60" rows="8" id="returnCtx"></textarea></td>
		  </tr>
		  <tr height="25" class="STYLE11">
		  <td align="left">退回人：<span id="returnUser"><%=session.getAttribute("username") %></span></td>
		  <td align="left">退回时间：<span id="returnTime"><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) %></span></td>
		  </tr>
       </table>
</div>
<div style=" height:180px;overflow-y:auto;overflow-x:hidden" id="showReason">
       <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
       
          <tr height="25" class="STYLE10" bgcolor="d3eaef" align="center">
		  <td align="left" colspan="4"><strong>查看退回原因：</strong></td>
		  </tr>
		  <tr class="STYLE10" bgcolor="d3eaef" align="center" >
		  <td align="left" colspan="4"><textarea cols="60" rows="8" id="returnCtx2" readonly="readonly"></textarea></td>
		  </tr>
		  <tr height="25" class="STYLE11">
		  <td align="left">退回人：<span id="returnUser2"></span></td>
		  <td align="left">退回时间：<span id="returnTime2"></span></td>
		  </tr>
       </table>
</div>
<div style=" height:180px;overflow-y:auto;overflow-x:hidden" id="showHistoryReason">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
       <tbody id="returnHistory"></tbody>
    </table>
</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="title">
      		<tr>
        		<td height="24" bgcolor="#353c44">
        			<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          			<tr>
	            			<td height="23">
	            				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					              <tr>
					                <td width="6%" height="19" valign="bottom"><div align="center"><img src="../public/images/tb.gif" width="14" height="14" /></div></td>
					                <td width="94%" valign="bottom">路径维护</td>
					              </tr>
	            				</table>
	            			</td>
	            
			        		<td><div align="right"> 搜索&gt;&gt; 按
			        			<input name="zccx" type="radio" value="1"/>编码
						        <input type="radio" name="zccx" value="2"/>名称
						        <input type="radio" name="zccx" value="3" checked="checked"/>拼音
						        <input type="radio" name="zccx" value="4"/>科室名称
						        <input type="text" id="chaxuntext" oninput="chaxun();" onpropertychange="chaxun();"/>
						        </div>
			        		</td>
			        		<td width="40%" align="center"><input type="button" id="createCP" value="新建"/>
			        		<input type="button" id="editCP" value="编辑" />	
			        		<%
			        			if(funid.contains("61")){
			        		%>
			        				<input type="button" id="delCP" value="删除"/>
			        				<input type="button" id="exportOrder" value="导出医嘱"/>
			        		<%
			        			}
			        		%>
			        									
							<input type="button" id="upGradeCP" value="版本升级"/>
							</td>
							<td>
							<select name="selectType" id="selectType">
							<%
			     				if(funid.contains("61")){
			 				%> 
								<option value="1" selected>全部路径</option>
                			 	<!-- <option value="2" >中心路径</option>
                				<option value="3">自定义路径</option> -->  
                				<option value="4">已启用</option>
                				<option value="5">已停用</option>
                				<option value="6">审核中</option>
                				<option value="7">已隐藏</option>
                				<option value="8">已退回</option> 
                				<%}else{ %> 
                				<option value="1">全部路径</option>
                			 	<!-- <option value="2" >中心路径</option>
                				<option value="3">自定义路径</option> -->  
                				<option value="4">已启用</option>
                				<option value="5">已停用</option>
                				<option value="6">审核中</option> 
                				<option value="7">已隐藏</option>
                				<option value="8">已退回</option>
                				<option value="9" selected>本科路径</option>
                				<%} %> 
						    </select></td>
	          			</tr>
        			</table>
        		</td>
      		</tr>
    	</table>
        <table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" style=" border-style:solid; border-width:0; border-color:#e1e1e1; ">
        		
        	
        	<%
			     if(funid.contains("61")){
			 %>
			    <tr height="25" bgcolor="d3eaef"  align="center">
        		<td width="20%" class="STYLE10" >路径名称</td>
        		<td width="15%" class="STYLE10">创建科室</td>
        		<td width="15%" class="STYLE10">路径编码</td>
        		<td width="10%" class="STYLE10">路径版本</td>
        		<td width="15%" class="STYLE10">使用权限</td>
        		<td width="10%" class="STYLE10">状态</td>
        		<td width="15%" class="STYLE10">操作</td>
        		</tr>
        	<%}else{ %>
        	<tr height="25" bgcolor="d3eaef"  align="center">
        		<td width="20%" class="STYLE10" >路径名称</td>
        		<td width="15%" class="STYLE10">创建科室</td>
        		<td width="15%" class="STYLE10">路径编码</td>
        		<td width="10%" class="STYLE10">路径版本</td>
        		<td width="15%" class="STYLE10">使用权限</td>
        		<td width="10%" class="STYLE10">状态</td>
        		<td width="15%" class="STYLE10">操作</td>
        	</tr>
        	<%} %>
        	<tbody id="viewTable" style="width: 100%;">        	
        	</tbody>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      	<tbody id="PageTable">
	     
	      	</tbody>
	    </table>
	    <div id="showCpDetail" style="background: #F2F5F7; height:400px"></div>
	    
	
		  
<div id="dialogAddCP" style=" height:180px;overflow-y:auto;overflow-x:auto">	
	<table id="AddCP" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
       
          <tr height="25" class="STYLE10" bgcolor="d3eaef" align="center" onclick="clickss(1);" onmouseover='changeColor(this)' onMouseOut="recoverColor(this)" >
		  <td align="left" colspan="4"><strong>1.新建局发路径</strong></td>
		  </tr>
		 <tr height="25" class="STYLE10" bgcolor="d3eaef" align="center" onclick="clickss(2);" onmouseover='changeColor(this)' onMouseOut="recoverColor(this)">
		  <td align="left" colspan="4"><strong>2.新建院内自定义路径(以现有路径为模版)</strong></td>
		  </tr>
		  <tr height="25" class="STYLE10" bgcolor="d3eaef" align="center" onclick="clickss(3);" onmouseover='changeColor(this)' onMouseOut="recoverColor(this)">
		  <td align="left" colspan="4"><strong>3.新建院内自定义路径(无模版)</strong></td>
		  </tr>
		  <!-- <tr height="25" class="STYLE10" bgcolor="d3eaef" align="center" onclick="clickss(4);" onmouseover='changeColor(this)' onMouseOut="recoverColor(this)">
		  <td align="left" colspan="4"><strong>4.版本升级</strong></td>
		  </tr> -->
       </table>
		<form id="form1" style="width: 100%; overflow: hide; position: relative; height: auto; " name="FormPost">
    <table width="369" height="270" border="0xp" align="center" cellspacing="0" bgcolor="#a8c7ce">
      <tr>
        <td height="32" align="right">路径名称：</td>
        <td><input type="text" name="txt112" id="cp_name"  onkeyup="document.getElementById('cp_pym').value = getCharsCode(this.value);"/>*
        <label for="radiobutton"></label></td>
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
        <td><input type="text" name="txt6" id="avg_fee" /></td>
      </tr>
      <tr>
        <td height="29" align="right">科室名称：</td>
        <td><input type="text" name="txt13" id="dept_name"  readonly="readonly"/>
        <input type="hidden"  value="value" id="dept_id" /> 
       </td>
      </tr>
      <tr>
        <td height="29" align="right">拼音码：</td>
        <td><input name="min_day2" type="text" id="cp_pym" /></td>
      </tr>
      <tr>
        <td height="29" align="right">        医保定额：</td>
        <td><input name="min_day3" type="text" id="cp_health_care_quota" /></td>
      </tr>
  </table>
  </form>
  
  <div id="table"  align="center">
	     <div id="gridPager"></div>
		<table id="gridTable" ondblclick='addContact()'></table>
	</div>
	 <div id="tableData"  align="center">
		 <div id="gridPager2"></div>
		<table id="gridTable2" ondblclick='addContact2()'></table>
		
	</div>
</div>
</body>
</html>
