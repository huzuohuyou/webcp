<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%
DataSetClass ds = (DataSetClass)request.getAttribute("list");
if (ds != null) {
	int rn = ds.FunGetRowCount();
	if (rn > 0){
		for(int i = 0; i < rn; i++) {
			int id = Integer.parseInt(ds.FunGetDataAsStringByColName(i, "USER_ID"));
			String user_name = ds.FunGetDataAsStringByColName(i, "USER_NAME");
			String login_name = ds.FunGetDataAsStringByColName(i, "USER_LOGIN");
			String st = ds.FunGetDataAsStringByColName(i, "USER_STATE");
			boolean isValid = "1".equals(st);
			String status = isValid ? "有效" : "无效";
%>
<tr onmouseover="changeColor(this);" onmouseout="recoverColor(this);"
	onclick="togglePower(<%=id%>);" class="hand" id="info_<%=id%>">
	<td align="center">
		<input type="checkbox" name="userCbx" value="<%=id %>" />
	</td>
	<td align="center"><%=user_name %></td>
	<td align="center"><%=login_name %></td>
	<td align="center" style="color: <%=isValid ? "#000000" : "#ff0000" %>">
		<%=status %></td>
	<td align="center">
		<a href="javascript:void(0);" class="command" 
			onclick="distribute(<%=id %>, '<%=user_name %>');">权限分配</a>
		<%if (isValid){%>
		<a href="javascript:void(0);" class="command" onclick="lock(<%=id %>);">冻结</a>
		<%}else{%>
		<a href="javascript:void(0);" class="command" onclick="unlock(<%=id %>);">解冻</a>
		<%}%>
		<a href="javascript:void(0);" class="command" onclick="rspassword(<%=id %>);">密码重置</a>
	</td>
</tr>
<tr id="tr_<%=id%>" class="hide">
	<td align="center" colspan="5" id="power_<%=id%>" class="gray" 
		style="padding: 10px;"></td></tr>
<%
		}
	}
}
%>