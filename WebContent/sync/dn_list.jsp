<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.CommonUtil"%>
<%@page import="com.goodwillcis.lcp.model.DownloadInfo"%>
<% 
final int HOSPITALID = LcpUtil.getHospitalID();
DatabaseClass db = LcpUtil.getDatabaseClass();
String sql = "SELECT DOWNLOAD_ID,DOWNLOAD_TIME,DOWNLOAD_STATE,REMARK FROM DCP_LOG_DOWNLOAD " +
						 "where hospital_id=" + HOSPITALID + " and DOWNLOAD_IP = 'master' order by download_time";

int count = db.FunGetRowCountBySql(sql);
int pageSize = 15;
int pageCount = (count % pageSize == 0 ? count / pageSize : count / pageSize + 1);

int pageNext = 1;
if(request.getParameter("pageNext") != null)
	pageNext = Integer.parseInt(request.getParameter("pageNext"));

DataSetClass dataSet = db.FunGetPageDataSetBySQL(sql, (pageNext - 1) * pageSize + 1, pageNext * pageSize);
%>
<script type="text/javascript">
var show_list = function(id)
{
	if (id == "0") $("#sub").hide();
	else
	{
		$("#sub").show();
		$("#sub_list").html();
		$("#sub_list").load("dn_sub.jsp", {id:id});
	}
};

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
	
	$("#sub").hide();
});
</script>

<%
String sql1 = "SELECT count(DOWNLOAD_ID) as CNT FROM DCP_LOG_DOWNLOAD where DOWNLOAD_IP <> 'master' and DOWNLOAD_STATE in (0, 2, 4)";
DataSetClass ds1 = LcpUtil.getDatabaseClass().FunGetDataSetBySQL(sql1);
Number cnt = ds1.FunGetDataAsNumberById(0, 0);
if (Integer.parseInt(cnt.toString()) > 3){
%>
<div class="warning"><span class="ui-icon ui-icon-alert"></span>&nbsp;警告：当前任务已经失败三次以上，请重新执行相关任务！</div>
<%} %>
<table class="list">
  <tr bgcolor="#d3eaef" height="30">
    <th width="12%"  scope="col">下载时间</th>
    <th width="20%"  scope="col">数据包版本</th>
    <th width="6%"  scope="col">下载结果</th>
    <th width="12%"  scope="col">操作</th>
  </tr>
  <%
if(!"".equals(dataSet.FunGetDataAsStringById(0,0))){
	for (int i = 0; i < dataSet.FunGetRowCount(); i++) {
		String id = dataSet.FunGetDataAsStringByColName(i, "DOWNLOAD_ID");
		String time = CommonUtil.traTimeStringByDate(dataSet.FunGetDataAsDateByColName(i, "DOWNLOAD_TIME"));
		String state = dataSet.FunGetDataAsStringByColName(i, "DOWNLOAD_STATE");
		String remark = dataSet.FunGetDataAsStringByColName(i, "REMARK");
		String status = null;
		if ("".equals(state)) status = "";
		else if ("0".equals(state) || "2".equals(state) || "4".equals(state)) status = "<font color=\"red\">失败</font>"; 
		else status = "完成";
		
		String version = "";
		if (!"".equals(remark))
		{
			String[] list = remark.split(",");
			for(int j = 0; j < list.length; j++)
				version += ("、" + DownloadInfo.getVersion(Integer.parseInt(list[j])));
			if (version.indexOf("、")>=0) version = version.substring(1);
		}
		%>
  <tr height="20" id="<%=id%>" onclick="show_list('<%= (remark == "" || remark == null ? "0" : remark) %>');" onmouseover="changeColor(this)" onmouseout="recoverColor(this)">
    <td align="center"><%=time%></td>
    <td align="center"><%=version%></td>
    <td align="center"><%=status%></td>
    <td align="center">
    	<a href="javascript:void(0);" class="command" id="info" onClick="loadInfo('dn_info.jsp', 'dn', {syn_id:'<%=id%>', version_no:'<%=remark %>'})">详细信息</a>
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
		<a id="firstPage" href="javascript:void(0);" onclick="show_page('frag-2', 'dn_list.jsp', 1)"><img src="../public/images/main_54.gif" width="40" height="15" border=0 /></a>
		<a id="pageBefore" href="javascript:void(0);" onclick="show_page('frag-2', 'dn_list.jsp', <%=pageNext-1%>)"><img src="../public/images/main_56.gif" width="45" height="15" border=0/></a>
		<a id="pageAfter" href="javascript:void(0);" onclick="show_page('frag-2', 'dn_list.jsp', <%=pageNext+1%>)"><img src="../public/images/main_58.gif" width="45" height="15" border=0/></a>
		<a id="endPage" href="javascript:void(0);" onclick="show_page('frag-2', 'dn_list.jsp', <%=pageCount%>)"><img src="../public/images/main_60.gif" width="40" height="15" border=0/></a>
		&nbsp;&nbsp;&nbsp;
		转到&nbsp;<input type="text" id="page_dn"  style="width:20px; height:12px; font-size:12px; border:solid 1px #7aaebd;"/>
		&nbsp;页&nbsp;<img src="../public/images/main_62.gif" width="26" height="15" onclick="page_select('frag-2', 'dn_list.jsp', $('#page_dn').val(), <%=pageCount%>)" style="cursor:pointer;" />
	</div>
	<%} %>
</div>
<%} %>
<table id="sub" class="list">
  <tr bgcolor="#d3eaef" height="30">
    <th width="12%"  scope="col">下载时间</th>
    <th width="6%"  scope="col">数据包版本</th>
    <th width="6%"  scope="col">下载结果</th>
    <th width="40%"  scope="col">数据包</th>
    <th width="12%"  scope="col">操作</th>
  </tr>
  <tbody id="sub_list"></tbody>
</table>
