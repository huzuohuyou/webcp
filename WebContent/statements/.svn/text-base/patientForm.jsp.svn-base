<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.goodwillcis.lcp.model.DataSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>病人详细信息</title>
<link rel="stylesheet" href="../public/styles/demos.css">
	<style type="text/css">
	
	body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	
	}
	<!--
a:link{
text-decoration:none;
color: #000000;
font-size: 12px;
}
a:visited{
text-decoration:none;
color: #000000;
font-size: 12px;
}
-->
	</style>
</head>
<%
String patient_no=request.getParameter("patient_no");
	//路径信息
	DataSet cpInfo=new DataSet();
	String cpInfoSql="select a.cp_id,a.cp_name,a.cp_status from lcp_master a,lcp_patient_visit b,lcp_patient_node c where a.hospital_id=b.hospital_id and a.cp_id=c.cp_id and b.patient_no=c.patient_no and b.patient_no='"+patient_no+"'";
	cpInfo.funSetDataSetBySql(cpInfoSql);
	
	//路径编记录
	DataSet cpVariaInfo=new DataSet();
	String cpVariaInfoSql="select b.cp_node_name,a.cp_node_id,a.cp_node_variation_text,a.user_name,a.exe_date   from lcp_patient_variation a, lcp_master_node b  where a.patient_no = '"+patient_no+"'    and a.cp_id = b.cp_id    and a.cp_node_id = b.cp_node_id ";
	cpVariaInfo.funSetDataSetBySql(cpVariaInfoSql);
	int cpVariaRow=cpVariaInfo.getRowNum();
	
	
%>



<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
  	<td width="3">&nbsp;</td>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td valign="bottom"><span class="STYLE1"> 病例详细信息</span></td>
                <td width="*" align="right" valign="top">
                <a href="javascript:void(0);" class="info" onclick="history.go(-1)"><font style="color: white;">返回</font></a>
                </td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="1"></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <!-- 路径执行概况 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td><table width="100%" id="Table2" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    <tbody id="Tbody2">
      <tr>            
        <td height="24" bgcolor="353c44" class="STYLE6" colspan="5"><div align="center"><span class="STYLE1">路径基本信息</span></div></td>        
      </tr>
      <tr>
        <td width="10%" height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">路径状态</span></div></td>
        
        <%
        	String state=cpInfo.funGetFieldByCol1(0,"CP_STATUS");
        	int state_photo_flag=0;
        if(state.equals("0")){
        	state="可用";
        	state_photo_flag=3;
        }else{
        	state="停用";
        	state_photo_flag=5;
        }
        %>
        
        
        <td width="5%" bgcolor="#FFFFFF"><div align="center"><img src="../public/images/detail_s<%=state_photo_flag%>.png" width="24" height="24" alt="执行中" /></div></td>
        <td width="10%" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%= state%></span></div></td>
        <td width="10%" bgcolor="#d3eaef"><div align="center"><a href="t31.htm"><span class="STYLE10">执行的路径</span></a></div></td>
        <td width="65%" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=cpInfo.funGetFieldByCol1(0,"CP_NAME") %></span></div></td>
      </tr>
      </tbody>
    </table></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <tr>
  	<td height="1" width="3">&nbsp;</td>
    <td></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <!-- 变异记录 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td><table width="100%" id="Table4" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    <tbody id="Tbody4">
      <tr>            
        <td height="24" bgcolor="353c44" class="STYLE6" colspan="5"><div align="center"><span class="STYLE1">路径变异记录</span></div></td>        
      </tr>
      <tr bgcolor="#d3eaef">
        <td width="10%" height="24"><div align="center"><span class="STYLE10">节点序号</span></div></td>
        <td width="20%"><div align="center"><span class="STYLE10">节点说明</span></div></td>
        <td width="45%"><div align="center"><span class="STYLE10">变异描述</span></div></td>
        <td width="15%"><div align="center"><span class="STYLE10">记录时间</span></div></td>
        <td width="10%"><div align="center"><span class="STYLE10">记录人</span></div></td>
      </tr>
      <%
      	for(int i=0;i<cpVariaRow;i++){
      		
      		
      	%>
      	
      	<tr bgcolor="#FFFFFF">
            <td height="24"><div align="center"><span class="STYLE10"><%=(i+1)%></span></div></td>
            <td><div align="center"><span class="STYLE10"><%=cpVariaInfo.funGetFieldByCol1(0,"CP_NODE_NAME") %></span></div></td>
            <td><div align="center"><span class="STYLE10"><%=cpVariaInfo.funGetFieldByCol1(0,"CP_NODE_VARIATION_TEXT") %></span></div></td>
            <td><div align="center"><span class="STYLE10"><%=cpVariaInfo.funGetFieldByCol1(0,"EXE_DATE") %></span></div></td>
            <td><div align="center"><span class="STYLE10"><%=cpVariaInfo.funGetFieldByCol1(0,"USER_NAME") %></span></div></td>
          </tr>
      	
      	
      	<% 
      		
      	}
      %>
     
      </tbody>
    </table></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <tr>
  	<td height="1" width="3">&nbsp;</td>
    <td></td>
  	<td width="3">&nbsp;</td>
  </tr>  
  <!-- 路径执行单 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td><table width="100%" id="Table3" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    <tbody id="Tbody3">
      <tr>            
        <td height="24" bgcolor="353c44" class="STYLE6" colspan="8"><div align="center"><span class="STYLE1">路径执行情况</span></div></td>        
      </tr>
      </tbody>
    </table>
    <% String url= LcpUtil.getConfigValue(PropertiesUtil.get("report.url")) ;%>
    	<iframe width="100%" height="600" src="../ReportEmitter?rpt=zhixingdan.brt&params=pairentno=<%=patient_no%>"></iframe>
    </td>
  	<td width="3">&nbsp;</td>
  </tr>
  <tr>
  	<td height="1" width="3">&nbsp;</td>
    <td></td>
  	<td width="3">&nbsp;</td>
  </tr>
  
</table>
</body>
</html>