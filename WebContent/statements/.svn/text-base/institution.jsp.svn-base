<%
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：institution.jsp  
// 文件功能描述：全院路径报表页面
// 创建人：康榕元
// 创建日期：2011-10-9
// 修改日期：2011-12-12 12：00
//----------------------------------------------------------------*/ 
%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>全院路径报表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	<link rel="stylesheet" href="../public/plugins/FusionCharts/style.css" type="text/css" />
	<link rel="stylesheet" href="../public/styles/demos.css">  
	<script type="text/javascript" src="../public/plugins/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript">
		function update(){
			$.post("../servlet/managecp",{op : "updateExecute"},function(){
			},"text");
		}
	</script>
  </head>
  <body>
  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="title">
      		<tr>
        		<td height="24" bgcolor="#353c44">
        			<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          			<tr class="STYLE1">
					       <td width="2%" height="19" valign="bottom" align="right"><img src="../public/images/tb.gif"
							width="14" height="14" /></td>
					                <td width="96%" valign="bottom" align="left">全院报表</td>
		        		</tr>
        			</table>
        		</td>
      		</tr>
    	</table>
     <table width="100%" id="tcp" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      	<tr height="20">
	        <td width="17%"  bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">报表编号</span></div></td>
	        <td width="83%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">报表名称</span></div></td>
        </tr>
        <%
        	int hospital_id = LcpUtil.getHospitalID();
        %>
				<tr bgcolor="#FFFFFF" class="STYLE19" >

					<td height="24"><div align="center">1</div></td>
					<td><div align="center"><a href="#"  onclick="window.open('statements.jsp?height=600&rpt=shidian&hospital_id=<%=hospital_id%>','临床路径管理试点调查评估表','width = 655,height = 670,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');">临床路径管理试点调查评估表</a></div></td>
				</tr>
               <!-- <tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">2</div></td>
					<td><div align="center"><a href="#" onClick="update();window.open('statements.jsp?height=490&rpt=narulv&hospital_id=<%=hospital_id%>','全院路径执行情况统计','width = 910,height = 560,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');" >全院路径执行情况统计</a></div></td>
				</tr>-->
				<!--<tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">3</div></td>
					<td><div align="center"><a href="#" onClick="update();window.open('statements2.jsp?height=490&rpt=narulv2&hospital_id=<%=hospital_id%>','按执行科室统计纳入率','width = 910,height = 560,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');" >按执行科室统计纳入率</a></div></td>
				</tr>
				<tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">4</div></td>
					<td><div align="center"><a href="#" onClick="window.open('statements3.jsp?height=490&rpt=executeDept','全院科室纳入率统计','width = 910,height = 560,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');" >全院科室纳入率统计</a></div></td>
				</tr>-->
			   <tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">2</div></td>
					<td><div align="center"><a href="#" onClick="window.open('statements4.jsp?height=490&rpt=narulv4&hospital_id=<%=hospital_id%>','按入院科室统计纳入率','width = 910,height = 560,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');" >按入院科室统计纳入率</a></div></td>
				</tr> 
				<!--<tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">4</div></td>
					<td><div align="center"><a href="#" onClick="window.open('statements5.jsp?height=490&rpt=narulv5&hospital_id=<%=hospital_id%>','按入院科室查看节点医嘱签名','width = 910,height = 560,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');" >按入院科室查看节点医嘱下达情况</a></div></td>
				</tr>-->
	            <!--<tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">5</div></td>
					<td><div align="center"><a href="#" onClick="window.open('statements6.jsp?height=490&rpt=orderexecute&hospital_id=<%=hospital_id%>','临床路径已确认医嘱项目统计','width = 910,height = 560,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');" >临床路径已确认医嘱项目统计</a></div></td>
				</tr>-->
				<tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">3</div></td>
					<td><div align="center"><a href="#" onClick="window.open('cpstatistics.jsp?height=490&rpt=cpstatistics','病种信息统计','width = 910,height = 560,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');" >病种信息统计</a></div></td>
				</tr>
				<tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">4</div></td>
					<td><div align="center"><a href="#" onClick="window.open('statementsKS.jsp?height=490&rpt=dept_order_rate&hospital_id=<%=hospital_id%>','科室医嘱执行率','width = 910,height = 560,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');" >科室医嘱执行率</a></div></td>
				</tr>
				<tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">5</div></td>
					<td><div align="center"><a href="#" onClick="window.open('BZOrderRate.jsp?height=490&rpt=bingzhong_order_rate','病种医嘱执行率','width = 910,height = 560,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');" >病种医嘱执行率</a></div></td>
				</tr>
                <tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">6</div></td>
					<td><div align="center"><a href="#" onClick="window.open('executeDept1.jsp?height=490&rpt=executeDept1&hospital_id=<%=hospital_id%>','科室路径执行情况','width = 910,height = 560,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');" >科室路径执行情况</a></div></td>
			    </tr>
			    <tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">7</div></td>
			   	    <td><div align="center"><a href="#" onClick="window.open('cpAnalyze.jsp?height=490&rpt=CPAnalyze','路径执行情况分析','width = 910,height = 560,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');" >路径执行情况分析</a></div></td> 
			    </tr>
		        <tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">8</div></td>
			   	    <td><div align="center"><a href="#" onClick="window.open('cpAnalyzeIndex.jsp?height=490&rpt=CPAnalyzeIndex','临床路径分析指标','width = 910,height = 560,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');" >临床路径分析指标</a></div></td> 
			    </tr>
			    <tr bgcolor="#FFFFFF" class="STYLE19"  >
					<td height="24"><div align="center">9</div></td>
			   	    <td><div align="center"><a href="#" onClick="window.open('CPDevelop.jsp?height=490&rpt=cp_develop','临床路径开展情况','width = 910,height = 560,top=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');" >临床路径开展情况</a></div></td> 
			    </tr>
    	</table>
  </body>
</html>
