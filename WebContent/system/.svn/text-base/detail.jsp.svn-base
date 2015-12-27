<%-- 
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：detail.jsp
// 文件功能描述：用户权限显示页面
// 创建人：赵蓬
// 创建日期：2011/09/09
// 
//----------------------------------------------------------------*/
 --%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil" %>
<%@ page language="java" contentType="text/html; charset=utf-8" 
	pageEncoding="UTF-8"%>
<table class="list1">
<%
DatabaseClass db = LcpUtil.getDatabaseClass();
String sql = "select f.* from dcp_sys_funs f inner join " +
						"dcp_sys_power p on f.funs_id = p.funs_id and p.user_id = " + 
						request.getParameter("userid") +" where p.power_state = 1 " +
						"order by f.funs_id";
DataSetClass ds = db.FunGetDataSetBySQL(sql);
int rn = ds.FunGetRowCount();
if (rn > 0){
	int index = 0;
	int pid = 0;
	for(int i = 0; i < rn; i++){
		int id = Integer.parseInt(ds.FunGetDataAsStringByColName(i, "FUNS_ID"));
		String name = ds.FunGetDataAsStringByColName(i, "FUNS_NAME");
		boolean checked = "1".equals(ds.FunGetDataAsStringByColName(i, "POWER_STATE"));
		if (id % 10 == 0) { // 父级菜单
			pid = id;
			if (index>0 && index%4 != 0){
				for (int j = 0; j < 4-index%4; j++) out.println("<td width=\"25%\"></td>");
				out.println("</tr>");
				index = 0;
			}
%>
	<tr><th colspan="4"><%=name %></th></tr>
	<%
		} else {
			if(index%4 == 0) out.println("<tr>"); 
	%>
		<td width="25%"><%=name %></td>
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