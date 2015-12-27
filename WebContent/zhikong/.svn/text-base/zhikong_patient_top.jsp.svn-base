<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>病人信息头部</title>
<link rel="stylesheet" href="../public/styles/demos.css">
<script src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
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
<%
String patient_no=request.getParameter("patient_no");
String flag=request.getParameter("flag");
%>
<script type="text/javascript">
$(document).ready(function() {
	
	$(index_<%=flag%>).css("background-color","#0080C0");
});
var patient_no_js="<%=patient_no%>";
function tiaozhuan(page){
	alert(page);
	url="zhikong_patient_main.jsp?url=zhikong_patient_td"+page+".jsp?patient_no="+patient_no_js+"&flag="+page;
	this.location.href=url;
}
function fanhui(){
<%-- 	var url="zhikong_patient_cp_index.jsp?cp_id=<%=cp_id%>&page_no=<%=page_no%>"; --%>
// 	parent.location.href=url;
}
</script>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <!-- 病例详细信息 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="30" colspan="2"><table width="100%" border="0" cellspacing="0" cellpadding="0">
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
    <td align="left"><table width="400" id="Table1" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    <tbody id="Tbody1">    
      <tr>
        <td id="index_1" style="cursor:pointer" width="80" height="80" bgcolor="#FFFFFF" > <div align="center"><a  onclick="tiaozhuan('1')"  target="mainFrame">
            <img src="../public/images/detail_1.png" style="border:0px" width="48" height="48" alt="病例概况" /><br />病例概况</a></div></td>
        <td id="index_2" style="cursor:pointer" width="80" height="80" bgcolor="#FFFFFF"><div align="center"><a  onclick="tiaozhuan('2')"  target="mainFrame">
            <img src="../public/images/detail_2.png" style="border:0px" width="48" height="48" alt="临床路径执行情况" /><br />路径执行情况</a></div></td>
        <td id="index_3" style="cursor:pointer" width="80" height="80" bgcolor="#FFFFFF"><div align="center"><a  onclick="tiaozhuan('3')"  target="mainFrame">
            <img src="../public/images/detail_3.png" style="border:0px" width="48" height="48" alt="治疗记录" /><br />治疗记录</a></div></td>
        <td id="index_4"  style="cursor:pointer" width="80" height="80" bgcolor="#FFFFFF"><div align="center"><a  onclick="tiaozhuan('4')"  target="mainFrame">
            <img src="../public/images/detail_4.png" style="border:0px" width="48" height="48" alt="检验检查结果" /><br />检验检查结果</a></div></td>
        <td id="index_5"  style="cursor:pointer" width="80" height="80" bgcolor="#FFFFFF"><div align="center"><a  onclick="tiaozhuan('5')"  target="mainFrame">
            <img src="../public/images/detail_5.png" style="border:0px" width="48" height="48" alt="相关病历内容" /><br />相关病历</a></div></td>
      </tr>      				
      </tbody>
    </table></td>
  	<td width="100"></td>
  	<td width="3">&nbsp;</td>
  </tr>
  </table>