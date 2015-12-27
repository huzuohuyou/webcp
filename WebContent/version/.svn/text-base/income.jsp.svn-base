<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	
  final int HOSPITALID=LcpUtil.getHospitalID();
DatabaseClass db = LcpUtil.getDatabaseClass();
String cp_id = request.getParameter("cp_id");
%>
<div align="left" ><font color="#00AEAE" style=" font-size: 14px;">进入路径标准:</font><br>
      <table width="100%" border="1">
      
        <tr> 
            <th height="150" scope="col" valign="top">
           <div style=" height:150px;overflow-y:auto; overflow-x:hidden"> <table border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" width="100%" >
              <tr  bgcolor="d3eaef" height="25">
                <th width="20%" align="center">类别</th>
                <th width="45%" align="center">名称</th>
                <th width="30%" align="center">编码</th>
              </tr>
               <tbody id="lcp_master_income_tbody">
				<%
					String sql = "SELECT * FROM LCP_MASTER_INCOME  WHERE SYS_IS_DEL=0 AND CP_ID="+ cp_id+" AND HOSPITAL_ID="+HOSPITALID;
					String sql1="SELECT * FROM LCP_MASTER_EXCLUDE  WHERE SYS_IS_DEL=0 and CP_ID="+ cp_id+" AND HOSPITAL_ID="+HOSPITALID;
					try{
					DataSetClass dataSet = db.FunGetDataSetBySQL(sql);
					DataSetClass dataSet1 = db.FunGetDataSetBySQL(sql1);
					 if(dataSet.FunGetDataAsStringById(0,0)!=""){
						for (int i = 0; i < dataSet.FunGetRowCount(); i++) {
								Number CP_DIAGNOSE_ID=dataSet.FunGetDataAsNumberByColName(i,"CP_INCOME_ID");
								String CP_DIAGNOSE_TYPE = dataSet.FunGetDataAsStringByColName(i, "CP_INCOME_TYPE");
								String CP_DIAGNOSE_NAME = dataSet.FunGetDataAsStringByColName(i, "CP_INCOME_NAME");
								String CP_DIAGNOSE_CODE = dataSet.FunGetDataAsStringByColName(i, "CP_INCOME_CODE");
				%>
				<tr bgcolor="#FFFFFF" onmouseover='changeColor(this)' onMouseOut="recoverColor(this)" class="STYLE10" name="lcp_master_income_tr" id="tr<%=CP_DIAGNOSE_ID%>">
				<td align="center"><%=CP_DIAGNOSE_TYPE%></td>
                <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=CP_DIAGNOSE_NAME%></td>
                <td align="center"><%=CP_DIAGNOSE_CODE%></td>
                </tr>
                <%}}
                	%>            
           </tbody>
            </table>
          </div></th>
        </tr>
  </table>
         <div align="center"></div>
        
    </div>
  <div align="left">
        <font color="#00AEAE" style=" font-size: 14px;">排除条件：</font> <br>
        <table width="100%"  border="1">
        
          <tr>
            <th height="150" scope="col" valign="top">
            <div style=" height:150px;overflow-y:auto;overflow-x:hidden">
            <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
              <tr  bgcolor="d3eaef" height="25">
                <td align="center" width="95%">条件名称</td>
              </tr>
              <tbody id="lcp_master_exclude_tbody">
              
           <%  if(dataSet1.FunGetDataAsStringById(0,0)!=""){   
           			for (int i = 0; i < dataSet1.FunGetRowCount(); i++) {
           				
						String CP_EXCLUDE_NAME = dataSet1.FunGetDataAsStringByColName(i, "CP_EXCLUDE_NAME");
						Number CP_EXCLUDE_ID=dataSet1.FunGetDataAsNumberByColName(i,"CP_EXCLUDE_ID");			
								%>
								
              <tr  bgcolor="#FFFFFF" onmouseover='changeColor(this)' onMouseOut="recoverColor(this)" class="STYLE10" name="lcp_master_exclude_tr" id="tr<%=CP_EXCLUDE_ID%>">
               <td align="left" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=CP_EXCLUDE_NAME %></td></tr>
                <%}}}  catch(Exception e){
                	out.print("<script  type='text/javascript'> alert('网络连接失败!请重试')</script>");
                }%>
              
              </tbody>
            </table>
            </div></th>
          </tr>
         
        </table>
    </div>

