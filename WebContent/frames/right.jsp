<%@page import="com.goodwillcis.lcp.util.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String year=request.getParameter("year");
    String month=request.getParameter("month");
    //out.print(year);
	//String month=request.getParameter("month");
	if(month==null &&year==null ){
		month=CommonUtil.funGetYearAndMonth();
	}
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

         <title>全院路径概要</title>
        <script type="text/javascript" src="../public/plugins/FusionCharts/FusionCharts.js"></script>
        <script src="../public/plugins/jquery/jquery-1.5.1.js"></script>
        <link href="../public/plugins/FusionCharts/style.css" rel="stylesheet" type="text/css" />

        <style type="text/css">
            h2.headline {
                font: normal 110%/137.5% "宋体", Arial, Helvetica, sans-serif;
                padding: 0;
                margin: 25px 0 25px 0;
                color: #7d7c8b;
                text-align: center;
            }
            p.small {
                font: normal 68.75%/150% Verdana, Geneva, sans-serif;
                color: #919191;
                padding: 0;
                margin: 0 auto;
                width: 664px;
                text-align: center;
            }
        </style>

    </head>
    <body>

        <div id="wrapper" style="width:90%;">

            <div class="content-area" style="width:100%;">
                <div id="content-area-inner-main" style="width:100%;">
                    <h2 class="headline">全院路径概要</h2>

                    <div class="gen-chart-render" style="width:100%;">

                        <center>
                            <span id="listlujing">路径启用情况</span>
                            <span id="pienarulv">全院路径纳入率</span>
                            <span id="chartlujingwancheng">全院路径完成情况</span>
                            <span id="chartlujingpaichu">全院路径排除情况</span>

                            <script type="text/javascript"><!--
                            var width = "48%";
// 							var JanGrid = new FusionCharts("../public/plugins/FusionCharts/SSGrid.swf", "JanGridId", width, "300", "0", "1");
//                             JanGrid.setXMLUrl("../zhikong/d1.xml");
//                             JanGrid.render("listlujing");
                            $.post("../ZhikongTongjiServlet?op=fg1&&year=<%=year%>&&month=<%=month%>",function(data){
                         	     if(data!=""){
                         	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
                         	    	var MarChart = new FusionCharts("../public/plugins/FusionCharts/SSGrid.swf", "JanGridId", width, "300", "0", "1");
                                   MarChart.setDataXML(data);
                                   MarChart.render("listlujing");	 
                         	        }
                              });

//                             var MarChart = new FusionCharts("../public/plugins/FusionCharts/Pie2D.swf", "MarChartId", width, "300", "0", "1");
//                             MarChart.setDataXML("");
//                             MarChart.render("pienarulv");		
                            
                            $.post("../ZhikongTongjiServlet?op=fg2&&year=<%=year%>&&month=<%=month%>",function(data){
                        	     if(data!=""){
                        	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
                        	    var MarChart = new FusionCharts("../public/plugins/FusionCharts/Pie2D.swf", "MarChartId", width, "300", "0", "1");
                                  MarChart.setDataXML(data);
                                  MarChart.render("pienarulv");	 
                        	        }
                             });

//                             var JanChart = new FusionCharts("../public/plugins/FusionCharts/Column2D.swf", "JanChartId", width, "300", "0", "1");
//                             JanChart.setDataXML("<chart showShadow='true' baseFont='宋体' baseFontSize='14' caption='全院路径完成情况' xAxisName='路径状态' yAxisName='病例数' numberSuffix='例' formatNumberScale='0' useRoundEdges='1'><set label='路径完成' value='10261' /><set label='路径退出' value='5247' /><set label='路径执行中' value='1365' /></chart>");
//                             JanChart.render("chartlujingwancheng");
                             
                            $.post("../ZhikongTongjiServlet?op=fg3&&year=<%=year%>&&month=<%=month%>",function(data){
                       	     if(data!=""){
                       	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
                       	    var MarChart = new FusionCharts("../public/plugins/FusionCharts/Column2D.swf", "JanChartId", width, "300", "0", "1");
                                 MarChart.setDataXML(data);
                                 MarChart.render("chartlujingwancheng");	 
                       	        }
                            });

                            
                            
//                             var FebChart = new FusionCharts("../public/plugins/FusionCharts/Column2D.swf", "FebChartId", width, "300", "0", "1");
//                             FebChart.setDataXML("<chart showShadow='true' baseFont='宋体' baseFontSize='12' caption='全院路径排除情况' xAxisName='纳入条件合符情况' yAxisName='病例数' numberSuffix='例' formatNumberScale='0' useRoundEdges='1'><set label='不符合条件' value='35644' /><set label='合符但不纳入' value='10089' /><set label='合符未处理' value='164' /></chart>");
//                             FebChart.render("chartlujingpaichu");
                            $.post("../ZhikongTongjiServlet?op=fg4&&year=<%=year%>&&month=<%=month%>",function(data){
                          	     if(data!=""){
                          	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
                          	    var MarChart = new FusionCharts("../public/plugins/FusionCharts/Column2D.swf", "JanChartId", width, "300", "0", "1");
                                    MarChart.setDataXML(data);
                                    MarChart.render("chartlujingpaichu");	 
                          	        }
                               });

                            // -->
                            </script>
                        </center>
                    </div>
                    <div class="clear"></div>
                    <p>&nbsp;</p>
                    <p class="small">    </p>
                    <div class="underline-dull"></div>
                </div>
            </div>

            <div id="footer">
                <ul>
                    <li><a href="right.jsp?month=<%=CommonUtil.funGetYearAndMonth()%>"><span> 本月纳入概要 </span></a></li>
                    <li class="pipe">|</li>
                    <li><a href="right.jsp?year=<%=CommonUtil.funGetYear()%>"><span> 本年纳入概要 </span></a></li>
                </ul>
            </div>
        </div>
    </body>
</html>