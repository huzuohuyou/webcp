<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.CommonUtil"%>
<% 
final int HOSPITALID = LcpUtil.getHospitalID();
DatabaseClass db = LcpUtil.getDatabaseClass();
String pid = request.getParameter("id");
String sql = "SELECT T.DOWNLOAD_ID,T.DOWNLOAD_TIME,T.DOWNLOAD_STATE,T.REMARK,D.DOWNLOAD_FILE FROM DCP_LOG_DOWNLOAD T,DCP_SYN_DOWNLOAD D " +
						 "where t.DOWNLOAD_id=d.DOWNLOAD_id(+) and t.hospital_id=" + HOSPITALID + " and t.DOWNLOAD_ID in (" + pid + ") order by t.DOWNLOAD_time";
DataSetClass dataSet = db.FunGetDataSetBySQL(sql);
%>
  <%
if(!"".equals(dataSet.FunGetDataAsStringById(0,0))){
	for (int i = 0; i < dataSet.FunGetRowCount(); i++) {
		String id = dataSet.FunGetDataAsStringByColName(i, "DOWNLOAD_ID");
		String time = CommonUtil.traTimeStringByDate(dataSet.FunGetDataAsDateByColName(i, "DOWNLOAD_TIME"));
		String state = dataSet.FunGetDataAsStringByColName(i, "DOWNLOAD_STATE");
		String file = dataSet.FunGetDataAsStringByColName(i, "DOWNLOAD_FILE");
		String remark = dataSet.FunGetDataAsStringByColName(i, "REMARK");
		String status = null;
		if ("".equals(state)) status = "";
		else if ("0".equals(state) || "2".equals(state) || "4".equals(state)) status = "<font color=\"red\">失败</font>"; 
		else status = "完成";
		%>
  <tr height="20" id="<%=id%>" onmouseover="changeColor(this)" onmouseout="recoverColor(this)">
    <td align="center"><%=time%></td>
    <td align="center"><%=remark%></td>
    <td align="center"><%=status%></td>
    <td><%=file%></td>
    <td align="center">
    	<a href="javascript:void(0);" class="command" id="info" onClick="loadInfo('dn_info.jsp', 'dn', {syn_id:'<%=id%>', version_no:'<%=remark %>'})">详细信息</a>
   		<%
		if (status.indexOf("失败") >= 0){
    	%>
    	<a href="javascript:void(0);" class="command" id="reexecute" onClick="executeInfo('<%=id%>', 'download')">重新执行</a>
    	<%
		}
			%>
    </td>
  </tr>
	<%
  }
}
  %>
