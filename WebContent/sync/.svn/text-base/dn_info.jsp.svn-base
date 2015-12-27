<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.CommonUtil"%>
<% 
final int HOSPITALID=LcpUtil.getHospitalID();
DatabaseClass db = LcpUtil.getDatabaseClass();
String synID=request.getParameter("syn_id");
String versionNo=request.getParameter("version_no");
String sql="SELECT LOG_DATE,LOG_ID,LOG_SCR,LOG_CONTENT1,LOG_CONTENT2 FROM DCP_LOG_INFO "+
			"where log_type='Download' and syn_id="+synID+" order by log_id";
DataSetClass dataSet = db.FunGetDataSetBySQL(sql);
%>
<span style="font-size:12px">&nbsp;&nbsp;&nbsp;版本号:<%=versionNo %></span>
<table class="list">
  <tr bgcolor="#d3eaef" height="30">
    <th width="20%"  scope="col">日志发生时间</th>
    <th width="20%"  scope="col">日志产生源</th>
    <th width="30%"  scope="col">日志内容一</th>
    <th width="30%"  scope="col">日志内容二</th>
  </tr>
  <%
if(dataSet.FunGetDataAsStringById(0,0)!=""){
	for (int i = 0; i < dataSet.FunGetRowCount(); i++) {
		String id = dataSet.FunGetDataAsStringByColName(i, "LOG_ID");
		String time = CommonUtil.traTimeStringByDate(dataSet.FunGetDataAsDateByColName(i, "LOG_DATE"));
		String scr = dataSet.FunGetDataAsStringByColName(i, "LOG_SCR");
		String conient1=dataSet.FunGetDataAsStringByColName(i, "LOG_CONTENT1");
		String conient2=dataSet.FunGetDataAsStringByColName(i, "LOG_CONTENT2");
  %>
  <tr height="20" id="<%=id%>" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)">
    <td align="center"><%=time %></td>
    <td align="center"><%=scr %></td>
    <td><%= conient1%></td>
    <td><%= conient2%></td>
  </tr>
  <%}} %>
</table>