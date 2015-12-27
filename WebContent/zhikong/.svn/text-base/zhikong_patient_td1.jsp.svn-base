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
	//病例基本信息使用
	DataSet baseInfo=new DataSet();
	String baseInfoSql="SELECT B.*,A.HOSPITAL_NAME,A.HOSPITAL_LV,C.VARIATION_NAME FROM DCP_SYS_HOSPITAL A,LCP_PATIENT_VISIT B , DCP_DICT_VARIATION C WHERE A.HOSPITAL_ID(+)=B.HOSPITAL_ID  AND B.CP_EXCLUDE_CODE=C.VARIATION_CODE(+) AND B.PATIENT_NO='"+patient_no+"'";

	baseInfo.funSetDataSetBySql(baseInfoSql);
	
	
	//病例费用情况使用
	DataSet feeInfo=new DataSet();
	String feeInfoSql="select t.* from lcp_patient_fee t where t.patient_no='"+patient_no+"'";
	feeInfo.funSetDataSetBySql(feeInfoSql);
	ArrayList<String> fields=new ArrayList<String>();

	
	
	//病例首页信息
	DataSet firstPage=new DataSet();
	String firstPageSql="SELECT T.* FROM LCP_PATIENT_FIRSTPAGE T WHERE T.PATIENT_NO='"+patient_no+"'";
	firstPage.funSetDataSetBySql(firstPageSql);
	
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
  <!-- 基础资料 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td><table width="100%" id="Table2" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    <tbody id="Tbody2">
      <tr>            
        <td height="24" bgcolor="353c44" class="STYLE6" colspan="8"><div align="center"><span class="STYLE1">病例基本信息</span></div></td>        
      </tr>
      <tr>
        <td width="10%" height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">患者编码</span></div></td>
        <td width="15%" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=baseInfo.funGetFieldByCol1(0,"PATIENT_NO")%></span></div></td>
        <td width="10%" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">患者姓名</span></div></td>
        <td width="15%" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=baseInfo.funGetFieldByCol1(0,"PATIENT_NAME")%></span></div></td>
        <td width="10%" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">性别</span></div></td>
        <td width="15%" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=baseInfo.funGetFieldByCol1(0,"PATIENT_SEX")%></span></div></td>
        <td width="10%" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">出生日期</span></div></td>
        <td width="15%" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=baseInfo.funGetFieldByCol1(0,"BIRTHDAY")%></span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">入住医院</span></div></td>
        <td bgcolor="#FFFFFF" colspan="7"><div align="center"><span class="STYLE10"><%=baseInfo.funGetFieldByCol1(0,"HOSPITAL_NAME")%></span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">住院号</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=baseInfo.funGetFieldByCol1(0,"INPATIENT_NO")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">门诊号</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=baseInfo.funGetFieldByCol1(0,"OUTPATIENT_NO")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">入院时间</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=baseInfo.funGetFieldByCol1(0,"ADMISSION_DATE")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">出院时间</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=baseInfo.funGetFieldByCol1(0,"DISCHARGED_DATE")%></span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">入院科室</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=baseInfo.funGetFieldByCol1(0,"DEPT_ADMISSION_TO")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">主治医生</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=baseInfo.funGetFieldByCol1(0,"ATTENDING_DOCTOR")%></span></div></td>
        <td bgcolor="#d3eaef" colspan="2"><div align="center"><span class="STYLE10">路径状态</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center">
        	<span class="STYLE10">
        		<%
        		String	cp_state=baseInfo.funGetFieldByCol1(0,"CP_STATE");
        		
        		int cp_state_flag=1;
        		
        		if(cp_state.equals("0")){
        			cp_state="未纳入";
        			cp_state_flag=1;
        		}else if(cp_state.equals("1")){
        			cp_state="已纳入";
        			cp_state_flag=3;
        		}else if(cp_state.equals("11")){
        			cp_state="已完成";
        			cp_state_flag=2;
        		}else if(cp_state.equals("21")){
        			cp_state="已退出";
        			cp_state_flag=5;
        		}else if(cp_state.equals("99")){
        			cp_state="不纳入";
        			cp_state_flag=4;
        		}
        		%>
        		<img src="../public/images/detail_s<%=cp_state_flag%>.png" width="24" height="24" alt="变异退出" />
        		
        	</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10">
        
        
        		<%=cp_state %>
        
        
        </span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">不纳入原因类型</span></div></td>
        <td bgcolor="#FFFFFF" colspan="2"><div align="center"><span class="STYLE10"><%=baseInfo.funGetFieldByCol1(0,"VARIATION_NAME")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">不纳入原因描述</span></div></td>
        <td bgcolor="#FFFFFF"colspan="4"><div align="center"><span class="STYLE10"><%=baseInfo.funGetFieldByCol1(0,"CP_EXCLUDE")%></span></div></td>
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
  <!-- 费用部分 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td><table width="100%" id="Table3" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    <tbody id="Tbody3">
      <tr>            
        <td height="24" bgcolor="353c44" class="STYLE6" colspan="8"><div align="center"><span class="STYLE1">病例费用情况</span></div></td>        
      </tr>
      <tr>
        <td width="15%" height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">床位费</span></div></td>
        <td width="10%" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_BED")%></span></div></td>
        <td width="15%" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">护理费</span></div></td>
        <td width="10%" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_CARE")%></span></div></td>
        <td width="15%" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">西药费</span></div></td>
        <td width="10%" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_DRUG")%></span></div></td>
        <td width="15%" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">中成药费</span></div></td>
        <td width="10%" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_CN_DRUG")%></span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">中草药费</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_CN_HERB")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">放射费</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_RADIATION")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">化验费</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_TEST")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">输氧费</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_OXYGEN")%></span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">输血费</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_BLOOD")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">诊疗费</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_TREATMENT")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">手术费</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_OPERATION")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">接生费</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_DELIVERY")%></span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">检查费</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_EXAMINATION")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">麻醉费</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_NARCOSIS")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">婴儿费</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_BABY")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">陪床费</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_ACCOMPANY")%></span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">其他费用</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_OTHER")%></span></div></td>
        <td bgcolor="#d3eaef" colspan="3"><div align="center"><span class="STYLE10">合计</span></div></td>
        <td bgcolor="#FFFF00" colspan="3"><div align="center"><span class="STYLE10"><%=feeInfo.funGetFieldByCol1(0,"FEE_TOTAL")%></span></div></td>
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
  <!-- 首页部分 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td><table width="100%" id="Table4" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    <tbody id="Tbody4">
      <tr>            
        <td height="24" bgcolor="353c44" class="STYLE6" colspan="8"><div align="center"><span class="STYLE1">病例首页信息</span></div></td>        
      </tr>      
      <tr>
        <td width="10%" height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">入院科室</span></div></td>
        <td width="15%" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=firstPage.funGetFieldByCol1(0,"DEPT_ADMISSION_TO")%></span></div></td>
        <td width="10%" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">出院科室</span></div></td>
        <td width="15%" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=firstPage.funGetFieldByCol1(0,"DEPT_DISCHARGE_FROM")%></span></div></td>
        <td width="10%" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">国籍</span></div></td>
        <td width="15%" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=firstPage.funGetFieldByCol1(0,"CITIZENSHIP")%></span></div></td>
        <td width="10%" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">民族</span></div></td>
        <td width="15%" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=firstPage.funGetFieldByCol1(0,"NATION")%></span></div></td>
      </tr>
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">身份证号</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=firstPage.funGetFieldByCol1(0,"ID_NO")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">职业</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=firstPage.funGetFieldByCol1(0,"OCCUPATION")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">出生地</span></div></td>
        <td bgcolor="#FFFFFF" colspan="3"><div align="center"><span class="STYLE10"><%=firstPage.funGetFieldByCol1(0,"BIRTH_PLACE")%></span></div></td>
      </tr>      
      <tr>
        <td height="24" bgcolor="#d3eaef"><div align="center"><span class="STYLE10">婚姻状况</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=firstPage.funGetFieldByCol1(0,"MARITAL_STATUS")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">费别</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=firstPage.funGetFieldByCol1(0,"CHARGE_TYPE")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">医保类别</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=firstPage.funGetFieldByCol1(0,"INSURANCE_TYPE")%></span></div></td>
        <td bgcolor="#d3eaef"><div align="center"><span class="STYLE10">医疗保险号</span></div></td>
        <td bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=firstPage.funGetFieldByCol1(0,"INSURANCE_NO")%></span></div></td>
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
  
</table>
</body>
</html>