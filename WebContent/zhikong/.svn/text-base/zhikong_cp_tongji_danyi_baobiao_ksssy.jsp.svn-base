<%@page import="com.goodwillcis.lcp.model.DataSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String cp_id=request.getParameter("cp_id");
	String cp_name="";
	cp_name=new String(cp_name.getBytes("GBK"),"UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>临床路径抗生素统计情况统计</title>
    <script type="text/javascript" src="../public/plugins/FusionCharts/FusionCharts.js"></script>
	<script src="../public/plugins/jquery/jquery-1.5.1.js"></script>
<link rel="stylesheet" href="../public/plugins/FusionCharts/style.css" type="text/css" />
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
<body>
<%
DataSet tianshu=new DataSet();
String sql="select t.cp_days, t.cp_name, t.cp_start_date, t.cp_fee,t.CP_STATUS, t.cp_stop_date from lcp_master t  where t.cp_id = "+cp_id+"";

tianshu.funSetDataSetBySql(sql);

%>
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
                <td valign="bottom"><span class="STYLE1"> <%=tianshu.funGetFieldByCol(0,"CP_NAME") %></span></td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <!-- 药物比率 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td><table width="100%" id="ttotal" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    <tbody id="tbtotal">
      <tr>
        <td width="40%" height="24" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">纳入路径病例使用抗菌药物比率</span></div></td>        
        <td width="10%" height="24" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=request.getParameter("naru")%>%</span></div></td>
        <td width="40%" height="24" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">不纳入病例使用抗菌药物比率</span></div></td>        
        <td width="10%" height="24" bgcolor="#FFFFFF"><div align="center"><span class="STYLE10"><%=request.getParameter("bunaru")%>%</span></div></td>        
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
  <!-- 药物使用 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="left">
                <span id="yaowushiyong">2型糖尿病临床路径抗菌药物使用统计</span>
                            <script type="text/javascript"> $.post("../ZhikongTongjiServlet?op=kangshengsu1&cp_id=<%=cp_id%>",function(data){
                         	     if(data!=""){
                        	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
                        	    	var MarChart = new FusionCharts("../public/plugins/FusionCharts/MSColumn2D.swf", "MarChartId", "1060", "300", "0", "1");
                                  MarChart.setDataXML(data);
                                  MarChart.render("yaowushiyong");	 
                        	        }
                             });
                              
                            </script>                
                </div></td>
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
  <!-- 药物使用分布 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="left">
                <span id="yaowufenbu">2型糖尿病临床路径抗菌药物使用各级分布率</span>
                            <script type="text/javascript">
                                 
                            $.post("../ZhikongTongjiServlet?op=kangshengsu2&cp_id=<%=cp_id%>",function(data){
                          	     if(data!=""){
                          	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
                          	    	var MarChart = new FusionCharts("../public/plugins/FusionCharts/MSColumn2D.swf", "MarChartId", "1060", "300", "0", "1");
                                    MarChart.setDataXML(data);
                                    MarChart.render("yaowufenbu");	 
                          	        }
                               });
                            </script>                
                </div></td>
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
  
</table>

</body>
</html>