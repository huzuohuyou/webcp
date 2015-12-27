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
<title>临床路径效果统计</title>
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
  <!-- 变异率 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="left">
                <span id="bianyilv"> <%=tianshu.funGetFieldByCol(0,"CP_NAME") %></span>
                            <script type="text/javascript">      
                                $.post("../ZhikongTongjiServlet?op=bianyi1&cp_id=<%=cp_id%>",function(data){
                          	     if(data!=""){
                          	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
                          	    	var MarChart = new FusionCharts("../public/plugins/FusionCharts/Column2D.swf", "MarChartId", "1060", "300", "0", "1");
                                    MarChart.setDataXML(data);
                                    MarChart.render("bianyilv");	 
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
  <!-- 不纳入原因出现率 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="left">
                <span id="bunayuanyin"> <%=tianshu.funGetFieldByCol(0,"CP_NAME") %>不纳入原因出现率统计</span>
                            <script type="text/javascript">
                            $.post("../ZhikongTongjiServlet?op=bianyi2&cp_id=<%=cp_id%>",function(data){
                         	     if(data!=""){
                         	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
                         	    	var MarChart = new FusionCharts("../public/plugins/FusionCharts/Column2D.swf", "MarChartId", "1060", "300", "0", "1");
                                   MarChart.setDataXML(data);
                                   MarChart.render("bunayuanyin");	 
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
  <!-- 退出原因出现率 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="left">
                <span id="tuichuyuanyin"> <%=tianshu.funGetFieldByCol(0,"CP_NAME") %>退出原因出现率统计</span>
                            <script type="text/javascript">
                                 $.post("../ZhikongTongjiServlet?op=bianyi3&cp_id=<%=cp_id%>",function(data){
                          	     if(data!=""){
                          	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
                          	    	var MarChart = new FusionCharts("../public/plugins/FusionCharts/Column2D.swf", "MarChartId", "1060", "300", "0", "1");
                                    MarChart.setDataXML(data);
                                    MarChart.render("tuichuyuanyin");	 
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
  <!-- 医嘱变异原因分类出现率 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="left">
                <span id="yizhuyuanyin"> <%=tianshu.funGetFieldByCol(0,"CP_NAME") %>医嘱变异原因分类出现率统计</span>
                            <script type="text/javascript">
                                $.post("../ZhikongTongjiServlet?op=bianyi4&cp_id=<%=cp_id%>",function(data){
                          	     if(data!=""){
                          	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
                          	    	var MarChart = new FusionCharts("../public/plugins/FusionCharts/Column2D.swf", "MarChartId", "1060", "300", "0", "1");
                                    MarChart.setDataXML(data);
                                    MarChart.render("yizhuyuanyin");	 
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