<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	final int HOSPITALID=LcpUtil.getHospitalID();
	String cp_id = request.getParameter("cp_id");
	DatabaseClass db = LcpUtil.getDatabaseClass(); 
      %> 
      
      <table border="1" width="100%"   >
        <tr>
          <th width="100%" scope="col" height="300" valign="top">
          <table border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" width="100%" >
            <tr>
              <td width="3%" bgcolor="d3eaef" class="STYLE6">&nbsp;</td>
              <td width="97%" height="19" bgcolor="d3eaef" class="STYLE6"><div align="center">变异信息</div></td>
            </tr>
            <tbody id="lcp_master_variation_tbody">
            <%
		            String sql = "select * from Lcp_master_variation  where SYS_IS_DEL=0 and cp_id="+ cp_id+" AND HOSPITAL_ID="+HOSPITALID;
			try{		
            DataSetClass dataSet = db.FunGetDataSetBySQL(sql);
					if(dataSet.FunGetDataAsStringById(0,0)!=""){
					for (int i = 0; i < dataSet.FunGetRowCount(); i++) {
						String CP_VARIATION_NAME = dataSet.FunGetDataAsStringByColName(i, "CP_VARIATION_NAME");
						String CP_VARIATION_ID = dataSet.FunGetDataAsStringByColName(i, "CP_VARIATION_ID");
            %>
            
            <tr bgcolor='#FFFFFF' onMouseMove='changeColor(this)' onMouseOut='recoverColor(this)' class='STYLE19' id="tr<%=CP_VARIATION_ID %>" name="lcp_master_variation_tr">
              <td bgcolor="#FFFFFF"><div align="center">
                <input type="checkbox" name="lcp_master_variation_checkbox" id="tr<%=CP_VARIATION_ID %>" />
              </div></td>
              <td height="20" bgcolor="#FFFFFF" class="STYLE10" align="left">&nbsp;&nbsp;&nbsp;&nbsp;<%=CP_VARIATION_NAME %></td>
            </tr>
            <%} }}  catch(Exception e){
                	out.print("<script  type='text/javascript'> alert('加载失败')</script>");
                }%>
            </tbody>
          </table></th>
        </tr>
        <tr>
          <th scope="row" width="100%" valign="top"> <input name="button_one" type="button" value="添加变异"  style="height:25px; width:auto; font-size:12px" onClick='dialogTwoCol("lcp_master_variation_tbody")'/>
            <input name="button_one" type="button" value="删除"  style="height:25px; width:auto; font-size:12px" onClick='delTrs("lcp_master_variation","cp_variation_id")'/></th>
        </tr>
      </table>