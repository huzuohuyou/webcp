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

 <table border="1" width="100%"  >
        <tr height="25"> 
          <th width="100%" scope="col" height="350" valign="top"><div style=" height:350px;overflow-y:auto;overflow-x:hidden">
          <table border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" width="99%" >
            <tr>
              <th width="3%" bgcolor="d3eaef" class="STYLE6">&nbsp;</th>
              <th width="97%" height="25" bgcolor="d3eaef"  align="center" class="STYLE10">出院标准名称</th>
            </tr>
             <tbody id="lcp_master_discharge_tbody">
            	<%
            		String sql = "select * from lcp_master_discharge  where SYS_IS_DEL=0 and cp_id="+ cp_id +" AND HOSPITAL_ID="+HOSPITALID;;
            	try{
					DataSetClass dataSet = db.FunGetDataSetBySQL(sql);
					 if(dataSet.FunGetDataAsStringById(0,0)!=""){
					for (int i = 0; i < dataSet.FunGetRowCount(); i++) {
						Number CP_DISCHARGE_ID=dataSet.FunGetDataAsNumberByColName(i,"CP_DISCHARGE_ID");
						String CP_DISCHARGE_NAME = dataSet.FunGetDataAsStringByColName(i, "CP_DISCHARGE_NAME");
			%>
            <tr  height="20" bgcolor='#FFFFFF' onMouseOver='changeColor(this)' onMouseOut='recoverColor(this)' class='STYLE19' name="lcp_master_discharge_tr" id="tr<%=CP_DISCHARGE_ID%>">
              <td align="center">
                <input type="checkbox" name="lcp_master_discharge_checkbox" id="tr<%=CP_DISCHARGE_ID%>" />
              </td>
              <td class="STYLE10" align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%=CP_DISCHARGE_NAME %></td>
            </tr>
            
            <%}}}  catch(Exception e){
                	out.print("<script  type='text/javascript'> alert('网络连接失败!请重试')</script>");
                }%>
           </tbody></table></div>
          </th></tr>
        <tr>
          <th scope="row" width="100%" valign="top"> <input name="button_one" type="button" value="添加标准"  style="height:25px; width:auto; font-size:12px" onClick='dialogTwoCol("lcp_master_discharge_tbody")'/>
            <input name="button_one" type="button" value="删除标准"  style="height:25px; width:auto; font-size:12px" onClick='delTrs("lcp_master_discharge","cp_discharge_id")'/></th>
        </tr>
      </table>