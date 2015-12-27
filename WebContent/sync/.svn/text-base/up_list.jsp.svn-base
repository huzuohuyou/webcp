<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.CommonUtil"%>
<% 
final int HOSPITALID = LcpUtil.getHospitalID();
DatabaseClass db = LcpUtil.getDatabaseClass();
String sql = "SELECT T.UPLOAD_ID,T.UPDATE_TIME,T.UPDATE_STATE,T.REMARK,D.UPLOAD_FILE FROM DCP_LOG_UPLOAD T,DCP_SYN_UPLOAD D " +
						 "where t.upload_id=d.upload_id(+) and t.hospital_id=" + HOSPITALID + " order by t.UPLOAD_ID";

int count = db.FunGetRowCountBySql(sql);
int pageSize = 15;
int pageCount = (count % pageSize == 0 ? count / pageSize : count / pageSize + 1);

int pageNext = 1;
if(request.getParameter("pageNext") != null)
	pageNext = Integer.parseInt(request.getParameter("pageNext"));

DataSetClass dataSet = db.FunGetPageDataSetBySQL(sql, (pageNext - 1) * pageSize + 1, pageNext * pageSize);
%>
<script type="text/javascript">
$(function(){
	var pageNext = <%=pageNext%>;
	var pageCount = <%=pageCount%>;
	
	if(pageNext == pageCount){
		$("#pageAfter").remove();
		$("#endPage").remove();
	}
	if(pageNext == 1){
		$("#pageBefore").remove();
		$("#firstPage").remove();
	}
});
</script>
<%
String sql1 = "SELECT count(UPLOAD_ID) as CNT FROM DCP_LOG_UPLOAD where UPDATE_STATE in (0,2,4)";
DataSetClass ds1 = LcpUtil.getDatabaseClass().FunGetDataSetBySQL(sql1);
Number cnt = ds1.FunGetDataAsNumberById(0, 0);
if (Integer.parseInt(cnt.toString()) > 3){
%>
<div class="warning"><span class="ui-icon ui-icon-alert"></span>&nbsp;警告：当前任务已经失败三次以上，请重新执行相关任务！</div>
<%} %>
<table class="list">
  <tr height="30">
    <th width="11%" scope="col">上传时间</th>
    <th width="23%" scope="col">时间范围</th>
    <th width="6%" scope="col">上传结果</th>
    <th width="44%" scope="col">数据包</th>
    <th width="12%" scope="col">操作</th>
  </tr>
  <%
if(dataSet.FunGetDataAsStringById(0,0)!=""){
	boolean flag = false;
	for (int i = 0; i < dataSet.FunGetRowCount(); i++) {
		String id = dataSet.FunGetDataAsStringByColName(i, "UPLOAD_ID");
		String time = CommonUtil.traTimeStringByDate(dataSet.FunGetDataAsDateByColName(i, "UPDATE_TIME"));
		String state = dataSet.FunGetDataAsStringByColName(i, "UPDATE_STATE");
		String remark = dataSet.FunGetDataAsStringByColName(i, "REMARK");
		String file = dataSet.FunGetDataAsStringByColName(i, "UPLOAD_FILE");
		String status = null;
		if ("".equals(state)) status = "";
		else if ("0".equals(state) || "2".equals(state) || "4".equals(state)) status = "<font color=\"red\">失败</font>"; 
		else status = "完成";
 	%>
 	<tr height="20" id="<%=id%>" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)">
   	<td align="center"><%=time %></td>
   	<td align="center"><%=remark %></td>
   	<td align="center"><%=status %></td>
   	<td align="left"><%=file %></td>
   	<td align="center">
   		<a href="javascript:void(0);" class="command" onClick="loadInfo('up_info.jsp', 'up', {syn_id:'<%=id%>', times:'<%=remark %>'})">详细信息</a>
   		<%
		if (status.indexOf("失败") >= 0 && !flag){
    	%>
    	<a href="javascript:void(0);" class="command" onClick="executeInfo('<%=id%>', 'upload')">重新执行</a>
    	<%
    	flag = true;
		}
			%>
    </td>
  </tr>
 	<%
	 }
} 
	%>
</table>
<%if (count > 0) {%>
<div class="pager">
	<div class="left">
		共有<strong><%=count %></strong> 条记录，当前第<strong> <%=pageNext %></strong> 页，共 <strong><%=pageCount %></strong> 页
	</div>
	<%if (pageCount > 1) {%>
	<div class="right">
		<a id="firstPage" href="javascript:void(0);" onclick="show_page('frag-1', 'up_list.jsp', 1)"><img src="../public/images/main_54.gif" width="40" height="15" border=0 /></a>
		<a id="pageBefore" href="javascript:void(0);" onclick="show_page('frag-1', 'up_list.jsp', <%=pageNext-1%>)"><img src="../public/images/main_56.gif" width="45" height="15" border=0/></a>
		<a id="pageAfter" href="javascript:void(0);" onclick="show_page('frag-1', 'up_list.jsp', <%=pageNext+1%>)"><img src="../public/images/main_58.gif" width="45" height="15" border=0/></a>
		<a id="endPage" href="javascript:void(0);" onclick="show_page('frag-1', 'up_list.jsp', <%=pageCount%>)"><img src="../public/images/main_60.gif" width="40" height="15" border=0/></a>
		&nbsp;&nbsp;&nbsp;
		转到&nbsp;<input type="text" id="page_up" style="width:20px; height:12px; font-size:12px; border:solid 1px #7aaebd;"/>
		&nbsp;页&nbsp; <img src="../public/images/main_62.gif" width="26" height="15" onclick="page_select('frag-1', 'up_list.jsp', $('#page_up').val(), <%=pageCount%>)" style="cursor:pointer;" />
	</div>
	<%} %>
</div>
<%} %>
