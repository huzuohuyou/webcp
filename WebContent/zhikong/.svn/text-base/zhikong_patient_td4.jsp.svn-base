<%@ page import="com.goodwillcis.lcp.model.DataSet"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>病人详细信息</title>
<link rel="stylesheet" href="../public/styles/demos.css">
<link rel="stylesheet" href="public/styles/demos.css">
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

	//检验单point表
	DataSet lis_point_info=new DataSet();
	String lis_point_info_sql="select t.* from lcp_patient_log_lis_point t where t.patient_no='"+patient_no+"'";
	lis_point_info.funSetDataSetBySql(lis_point_info_sql);
	int lis_point_info_row=lis_point_info.getRowNum();

	//检查报告单
	
	DataSet pacs_dataset=new DataSet();
	String  pacs__sql="select t.* from lcp_patient_log_pacs t where t.patient_no='"+patient_no+"'";
	pacs_dataset.funSetDataSetBySql(pacs__sql);
	int pacs__row=pacs_dataset.getRowNum();
	
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
                <td width="24" height="19" valign="bottom"><div align="center"><img src="../public/images/tb.gif" width="14" height="14" /></div></td>
                <td valign="bottom"><span class="STYLE1"> 病例详细信息</span></td>
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
    <td align="left"><table width="100%" id="Table1" class="users" border="0" cellpadding="0" cellspacing="1" >
    
       <tbody id="Tbody1">    
      <tr>
        <td width="80" height="80" bgcolor="#FFFFFF"><div align="center"><a href="zhikong_patient_td1.jsp?patient_no=<%=patient_no%>&cp_id=<%=request.getParameter("cp_id")%>">
            <img src="../public/images/detail_1.png" style="border:0px" width="48" height="48" alt="病例概况" /><br />病例概况</a></div></td>
        <td width="80" height="80" bgcolor="#FFFFFF"><div align="center"><a href="zhikong_patient_td2.jsp?patient_no=<%=patient_no%>&cp_id=<%=request.getParameter("cp_id")%>">
            <img src="../public/images/detail_2.png" style="border:0px" width="48" height="48" alt="临床路径执行情况" /><br />路径执行情况</a></div></td>
        <td width="100" height="80" bgcolor="#FFFFFF"><div align="center"><a href="zhikong_patient_td3.jsp?patient_no=<%=patient_no%>&cp_id=<%=request.getParameter("cp_id")%>">
            <img src="../public/images/left_16.gif" style="border:0px" width="48" height="48" alt="诊断与手术记录" /><br />诊断与手术记录</a></div></td>
        <td width="80" height="80" bgcolor="#FFFFFF"><div align="center"><a href="zhikong_patient_td6.jsp?patient_no=<%=patient_no%>&cp_id=<%=request.getParameter("cp_id")%>">
            <img src="../public/images/detail_3.png" style="border:0px" width="48" height="48" alt="治疗记录" /><br />治疗记录</a></div></td>
        <td width="80" height="80" bgcolor="#FFFFFF"><div align="center"><a href="zhikong_patient_td7.jsp?patient_no=<%=patient_no%>&cp_id=<%=request.getParameter("cp_id")%>">
            <img src="../public/images/left_14.gif" style="border:0px" width="48" height="48" alt="医嘱记录" /><br />医嘱记录</a></div></td>
         <td width="80" height="80" bgcolor="#FFFFFF"><div align="center"><a href="zhikong_patient_td8.jsp?patient_no=<%=patient_no%>&cp_id=<%=request.getParameter("cp_id")%>">
            <img src="../public/images/left_15.gif" style="border:0px" width="48" height="48" alt="护理记录" /><br />护理记录</a></div></td>
           <td width="80" height="80" bgcolor="#FFFFFF"><div align="center"><a href="zhikong_patient_td4.jsp?patient_no=<%=patient_no%>&cp_id=<%=request.getParameter("cp_id")%>">
            <img src="../public/images/detail_4.png" style="border:0px" width="48" height="48" alt="检验检查结果" /><br />检验检查结果</a></div></td>
        <td width="80" height="80" bgcolor="#FFFFFF"><div align="center"><a href="zhikong_patient_td5.jsp?patient_no=<%=patient_no%>&cp_id=<%=request.getParameter("cp_id")%>">
            <img src="../public/images/detail_5.png" style="border:0px" width="48" height="48" alt="相关病历内容" /><br />相关病历</a></div></td>
     	<td width="*" align="right" valign="top">
			<a href="../statements/singleCPStatistics.html"><font style="font-size: 16px; color: red;">【返回路径列表】</font></a>
			<font style="font-size: 16px; color: black;">||</font>
			<a href="../statements/patientIndex.html?cp_id=<%=request.getParameter("cp_id")%>"><font style="font-size: 16px; color: red;">【返回病人列表】</font></a>
		</td>
      </tr>     				
      </tbody>
    </table></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="1"></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <!-- 检验结果 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td><table width="100%" id="Table2" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    <tbody id="Tbody2">
      <tr>            
        <td height="24" bgcolor="353c44" class="STYLE6" colspan="3"><div align="center"><span class="STYLE1">检验结果</span></div></td>        
      </tr>
      
      
      
      <%
      	for(int i=0;i<lis_point_info_row;i++){
      		 String applyno=lis_point_info.funGetFieldByCol1(i,"APPLYNO");
      		 String lis_name=lis_point_info.funGetFieldByCol1(i,"LIS_NAME");
      		 String send_date=lis_point_info.funGetFieldByCol1(i,"SEND_DATE");
      		 String user_name=lis_point_info.funGetFieldByCol1(i,"USER_NAME");
      		 String exe_date=lis_point_info.funGetFieldByCol1(i,"EXE_DATE");
      %>
      <!-- 第一个结果 -->
      <tr>
        <td width="100%" height="24" bgcolor="#A6FFA6" colspan="3"><div align="center"><span class="STYLE10">第 <%=(i+1) %> 份检验结果</span></div></td>
      </tr>
      <tr>
        <td width="10%" height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">检验单号</span></div></td>
        <td width="4%" bgcolor="#FFFF00"><div align="center"><span class="STYLE10">＞＞</span></div></div></td>        
        <td width="86%" bgcolor="#d3eaef"><div align="center"><span class="STYLE10"><%=lis_name %></span></div></td>        
      </tr>
      <tr>
        <td height="24" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=applyno %></span></div></td>
        <td bgcolor="#FFFFFF" rowspan="7" colspan="2"><table width="100%" id="Table1_1" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
               <tr bgcolor="#d3eaef">
                <td width="55%" height="24"><div align="center"><span class="STYLE10">项目名</span></div></td>
                <td width="10%"><div align="center"><span class="STYLE10">结果值</span></div></td>        
                <td width="10%"><div align="center"><span class="STYLE10">结果单位</span></div></td>        
                <td width="15%"><div align="center"><span class="STYLE10">参考范围</span></div></td>        
                <td width="10%"><div align="center"><span class="STYLE10">提示</span></div></td>        
            </tr>
           <%
           		DataSet lis_item_dataSet=new DataSet();
           		String lis_item_sql="select * from lcp_patient_log_lis_item t where  t.applyno='"+applyno+"' and t.patient_no='"+patient_no+"'";
           		System.out.println("lis_item_sql"+lis_item_sql);
           		lis_item_dataSet.funSetDataSetBySql(lis_item_sql);
           		int lis_item_row=lis_item_dataSet.getRowNum();
           		for(int j=0;j<lis_item_row;j++){
           			String lis_item_name=lis_item_dataSet.funGetFieldByCol(j,"LIS_ITEM_NAME");
           			String item_result_value=lis_item_dataSet.funGetFieldByCol(j,"ITEM_RESULT_VALUE");
           			String item_result_unit=lis_item_dataSet.funGetFieldByCol(j,"ITEM_RESULT_UNIT");
           			String item_result_range=lis_item_dataSet.funGetFieldByCol(j,"ITEM_RESULT_RANGE");
           			String item_result_point=lis_item_dataSet.funGetFieldByCol(j,"ITEM_RESULT_POINT");
           		
           	%>
           
           
           

            <tr bgcolor="#FFFFFF">
                <td height="24"><div align="center"><span class="STYLE10"><%=lis_item_name %></span></div></td>
                <td><div align="center"><span class="STYLE10"><%=item_result_value %></span></div></td>        
                <td><div align="center"><span class="STYLE10"><%=item_result_unit %></span></div></td>        
                <td><div align="center"><span class="STYLE10"><%=item_result_range %></span></div></td>        
                <td><div align="center"><span class="STYLE10"><%=item_result_point %></span></div></td>        
            </tr>
            <%} %>
            
        </table>
        </td>
      </tr>
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">送检时间</span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=send_date%></span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">检验结果人</span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=user_name%></span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">检验结果时间</span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=exe_date%></span></div></td>
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
  <!-- 检查报告 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td><table width="100%" id="Table3" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    <tbody id="Tbody3">
      <tr>            
        <td height="24" bgcolor="353c44" class="STYLE6" colspan="3"><div align="center"><span class="STYLE1">检查报告</span></div></td>        
      </tr>
      
      
      
     <%
     	for(int k=0;k<pacs__row;k++){
     		String local_key=pacs_dataset.funGetFieldByCol1(k,"LOCAL_KEY");
     		String pacs_name=pacs_dataset.funGetFieldByCol1(k,"PACS_NAME");
     		String exe_date=pacs_dataset.funGetFieldByCol1(k,"EXE_DATE");
     		String send_date=pacs_dataset.funGetFieldByCol1(k,"SEND_DATE");
     		String pacs_report=pacs_dataset.funGetFieldByCol1(k,"PACS_REPORT");
     		String user_name=pacs_dataset.funGetFieldByCol1(k,"USER_NAME");
     %>
     <tr>
        <td width="100%" height="24" bgcolor="#A6FFA6" colspan="3"><div align="center"><span class="STYLE10">第 <%=(k+1) %> 份检查报告</span></div></td>
      </tr>
      <tr>
        <td width="15%" height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">检查报告单号</span></div></td>
        <td width="5%" bgcolor="#FFFF00"><div align="center"><span class="STYLE10">＞＞</span></div></td>        
        <td width="80%" bgcolor="#d3eaef"><div align="center"><span class="STYLE10"><%=pacs_name %></span></div></td>        
      </tr>
      <tr>
        <td height="24" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=local_key %></span></div></td>
        <td bgcolor="#FFFFFF" rowspan="7" colspan="2" ><div align="center"><span class="STYLE10"><%=pacs_report %></span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">检查时间</span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=send_date %></span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">报告人</span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=user_name %></span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">报告时间</span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=exe_date %></span></div></td>
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
</table>
</body>
</html>