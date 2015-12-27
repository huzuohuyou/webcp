<%-- 
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：permissions.jsp
// 文件功能描述：用户权限页面
// 创建人：康榕元 
// 创建日期：2011/08/03
// 修改人：赵蓬
// 修改日期：2011/09/05
// 
//----------------------------------------------------------------*/
 --%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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
String sql = "select f.*, p.power_state from dcp_sys_funs f left outer join dcp_sys_power p " +
						"on f.funs_id = p.funs_id and p.user_id = " + request.getParameter("userid") +
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
	<tr>
		<th colspan="4">
			<label>
	      <input type="checkbox" name="powerCbx_edit" value="f_<%=id %>" 
	      	onclick="selectAllByParent('powerCbx_edit', <%=id %>)" <%if(checked) out.print("checked"); %> />
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
	      <input type="checkbox" name="powerCbx_edit" value="<%=pid %>_<%=id %>" 
	      	onclick="selectToParent('powerCbx_edit', <%=pid %>)" <%if(checked) out.print("checked"); %> />
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