<%-- 
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：personnel.jsp
// 文件功能描述：人员注册页面
// 创建人：康榕元 
// 创建日期：2011/08/03
// 修改人：赵蓬
// 修改日期：2011/09/02
// 
//----------------------------------------------------------------*/
 --%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<link rel="stylesheet" href="../public/plugins/jquery/themes/cupertino/jquery-ui-1.8.11.custom.css"> 
	<script src="../public/plugins/jquery/ui/jquery.ui.tabs.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.resizable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.effects.core.js"></script>
	<script type="text/javascript" src="../public/plugins/jquery/jquery.acts_as_tree_table.js"></script>
	<script src="../public/plugins/jquery/jquery.autocomplete.js"></script>
	<link rel="stylesheet" href="../public/plugins/jquery/jquery.autocomplete.css">
	
	<script>
	$(document).ready(function(){

		var opertion={
		    	delay:1000,
		    	 max: 12,    //列表里的条目数
		         minChars: 0,   //自动完成激活之前填入的最小字符
		         width: 400,     //提示的宽度，溢出隐藏
		         scrollHeight: 300,   //提示的高度，溢出显示滚动条
		         matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
		         autoFill: false,    //自动填充
		         parse: function(data) { 
		        		data = eval(data);
		          	   	var rows = [];   
		             for(var i=0; i<data.length; i++){   
		               rows[rows.length] = {   
		            		   data:data[i].code+"--"+data[i].name +"--"+data[i].input,   
		                 value:data[i].code+"--"+data[i].name,   
		                 result:data[i].input 
		                 };   }    
		             return rows;   
		              },   
		           formatItem: function(row, i, n) {   
		            	 return row;
		           } };

			$("#dept_name").autocomplete("../servlet/auto?ops=py&op=dept",opertion);
			$("#dept_name").result(function(event, data, formatted){
					formatted=formatted.split("--");
				 $("#dept_name").attr("value",formatted[1]);
				$("#dept_id").attr("value",formatted[0]);
		//		alert($("#dept_id").val());
			});
	});
	</script>
<script type="text/javascript">
		
	function userIsNull(){
		var userNameTbx = document.getElementById("userNameTbx").value;
		var userNameTbx_msg = document.getElementById("userNameTbx_msg");
		
		if(userNameTbx.length == 0)
		{
			userNameTbx_msg.innerHTML="<font color=red>用户名不能为空！</font>";
		}
		else
		{
			userNameTbx_msg.innerHTML="";
			return true;
		}
		return false;
	}
	function loginIsNull(){
		var loginNameTbx = document.getElementById("loginNameTbx").value;
		var loginNameTbx_msg = document.getElementById("loginNameTbx_msg");
		
		if(loginNameTbx.length == 0)
		{
			loginNameTbx_msg.innerHTML="<font color=red>登录名不能为空！</font>";
		}
		else
		{
			loginNameTbx_msg.innerHTML="";
			return true;
		}
		return false;
	}
	function isNull(){
		var pwd = document.getElementById("pwd").value;
		var pd = document.getElementById("pd");
		
		if(pwd.length == 0)
		{
			pd.innerHTML="<font color=red>密码不能为空！</font>";
		}
		else
		{
			pd.innerHTML="";
			return true;
		}
		return false;
	}
	function checkPwd()
	{
		var pwd = document.getElementById("pwd").value;
		var confirmPwd = document.getElementById("confirmPwd").value;
		var cd = document.getElementById("cd");
		
		if(pwd != confirmPwd && confirmPwd.length != 0)
		{
			cd.innerHTML="<font color=red>两次密码不一致！</font>";
		}
		else if(confirmPwd.length == 0)
		{
			cd.innerHTML="<font color=red>确认密码不能为空！</font>";
		}
		else
		{
			cd.innerHTML="";
			return true;
		}
		return false;
	}
</script>
<table id="users" class="list1">
 	<tr><th colspan="2">基本信息</th></tr>
 	<tr>
  	<td width="40%" align="right">用 户 名：</td>
  	<td width="60%">
   		<input type="text" id="userNameTbx" oninput="checkUserName(this)" onpropertychange="checkUserName(this)" onblur="userIsNull()" />
   		<span id="userNameTbx_msg"></span>
		</td>
  </tr>
  <tr>
    <td align="right">登 录 名：</td>
    <td>
			<input type="text" id="loginNameTbx" oninput="checkLoginName(this)" onpropertychange="checkLoginName(this)" onblur="loginIsNull()" />
      <span id="loginNameTbx_msg"></span>
    </td>
  </tr>

  <tr>
    <td align="right">密　　码：</td>
    <td>
			<input type="password" id="pwd" onblur="isNull()"/>
      		<span id="pd"></span>
    </td>
  </tr>
    <tr>
    <td align="right">确认密码：</td>
    <td>
			<input type="password" id="confirmPwd" onblur="checkPwd()" />
      		<span id="cd"></span>
    </td>
  </tr>
 <tr>
        <td height="29" align="right">科室名称：</td>
        <td><input type="text" name="txt13" id="dept_name"  />
        <input type="hidden"  value="value" id="dept_id" /> 
       </td>
      </tr>
</table>

<div class="navi">
	<div class="navi_title">功能列表</div>
	<div class="navi_function">
	  <a href="javascript:void(0);" class="select_all" onClick="selectAll('powerCbx')">全选</a>
		<a href="javascript:void(0);" class="select_all" onClick="selectRe('powerCbx')">反选</a>
	</div>
</div>
<table class="list1">
<%
DatabaseClass db = LcpUtil.getDatabaseClass();
String sql = "select * from dcp_sys_funs order by funs_id";

DataSetClass ds = db.FunGetDataSetBySQL(sql);
int rn = ds.FunGetRowCount();
if (rn > 0){
	int index = 0;
	int pid = 0;
	for(int i = 0; i < rn; i++){
		int id = Integer.parseInt(ds.FunGetDataAsStringByColName(i, "FUNS_ID"));
		String name = ds.FunGetDataAsStringByColName(i, "FUNS_NAME");
		if (id % 10 == 0) { // 父级菜单
			pid = id;
			if (index>0 && index%4 != 0){
				for (int j = 0; j < 4-index%4; j++) out.println("<td width=\"25%\"></td>");
				out.println("</tr>");
				index = 0;
			}
%>
	<tr>
		<th colspan="4">
			<label>
	      <input type="checkbox" name="powerCbx" value="f_<%=id %>" 
	      	onclick="selectAllByParent('powerCbx', <%=id %>)" />
			  <%=name %>
			</label>
		</th>
	</tr>
	<%
		} else {
			if(index%4 == 0) out.println("<tr>"); 
	%>
		<td width="25%">
			<label>
	      <input type="checkbox" name="powerCbx" value="<%=pid %>_<%=id %>" 
	      	onclick="selectToParent('powerCbx', <%=pid %>)" />
				<%=name %>
			</label>
		</td>
		<%
			if(index%4 == 3) out.println("</tr>");
			index++;
		}
	}
	if (index>0 && index%4 != 0){
		for (int j = 0; j < 4-index%4; j++) out.println("<td width=\"25%\"></td>");
		out.println("</tr>");
		index = 0;
	}
}
	%>
</table>