<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	
    final int HOSPITALID=LcpUtil.getHospitalID();
	DatabaseClass db = LcpUtil.getDatabaseClass();
	String cp_id = request.getParameter("cp_id");
%>

<table border="1" width="95%"  height="100%">
       <tr>
         <th width="100%" scope="col" height="350" valign="top">
         <table border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" width="100%" >
           <tbody id="lcp_master_discharge_tbody">
           <tr>
             <td width="97%" height="19" bgcolor="d3eaef" class="STYLE10"><div align="center">出院标准名称</div></td>
           </tr>
           	<%
           		String sql = "select * from LCP_MASTER_DISCHARGE  where SYS_IS_DEL=0 and cp_id="+ cp_id +" and HOSPITAL_ID="+HOSPITALID;
           		try{
					DataSetClass dataSet = db.FunGetDataSetBySQL(sql);
					 if(dataSet.FunGetDataAsStringById(0,0)!=""){
						for (int i = 0; i < dataSet.FunGetRowCount(); i++) {
							Number dischargeId=dataSet.FunGetDataAsNumberByColName(i,"CP_DISCHARGE_ID");
							String dischargeName = dataSet.FunGetDataAsStringByColName(i, "CP_DISCHARGE_NAME");
		%>
           <tr bgcolor='#FFFFFF' onMouseOver='changeColor(this)' onMouseOut='recoverColor(this)' id="tr<%=dischargeId%>">
             <td height="20" bgcolor="#FFFFFF"  align="left" class="STYLE10">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=dischargeName %></td>
           </tr>
           
        <%
         }}}  catch(Exception e){
                	out.print("<script  type='text/javascript'> alert('网络连接失败!请重试')</script>");
          }
        %>
           </tbody>
           </table>
          </th></tr>
    </table>