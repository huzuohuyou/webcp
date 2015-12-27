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
<table width="100%"   border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="table_master">
      <tr>
        <td width="11%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">序号</div></td>
        <td width="60%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">节点名称</div></td>
        <td width="22%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">阶段最多天数</div></td>
      </tr>
      <tbody id="lcp_master_node_tbody">
    <% 
    String sql="SELECT * FROM LCP_MASTER_NODE  WHERE SYS_IS_DEL=0 and CP_ID="+cp_id+" AND HOSPITAL_ID="+HOSPITALID+" order by cp_node_type, cp_node_id" ;
		// System.out.println(sql);
		try{
	    DataSetClass dataSet = db.FunGetDataSetBySQL(sql);
	 if(dataSet.FunGetDataAsStringById(0,0)!=""){
		for (int i = 0; i < dataSet.FunGetRowCount(); i++) {
				Number cpNodeId=dataSet.FunGetDataAsNumberByColName(i,"CP_NODE_ID");//路径节点编号
				Number cpNodeDays=dataSet.FunGetDataAsNumberByColName(i,"CP_NODE_DAYS_MAX");//节点天数
				String cpNodeName = dataSet.FunGetDataAsStringByColName(i, "CP_NODE_NAME");//节点名称
    %>
	   
    
   <tr bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" onClick="NodeColor(this)"  id="<%=cpNodeId%>" class="selectNode" style='cursor:pointer'>
		<td ><div align="center"><span class="STYLE10"><%=cpNodeId%></span></div></td>
        <td align="left" ><span class="STYLE10">&nbsp;&nbsp;&nbsp;<%=cpNodeName%></span></td>
      <td ><div align="center"><span class="STYLE10"><%=cpNodeDays%></span></div></td>
</tr>
<%}} }  catch(Exception e){
                	out.print("<script  type='text/javascript'> alert('网络连接失败!请重试')</script>");
                }%>

</tbody>
</table>