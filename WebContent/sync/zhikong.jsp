<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../frames/power.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>同步统计</title>
    <script type="text/javascript" src="../public/plugins/FusionCharts/FusionCharts.js"></script>
    <link href="../public/plugins/FusionCharts/style.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
    <div id="wrapper" style="padding-top:10px; width:98%;">
      <span id="JanGrid" style="width:50%;">同步成功率</span>
      <span id="MarChart" style="width:50%;">上传成功分布情况</span>
      <span id="JanChart" style="width:50%;">下载成功分布情况</span>
      <span id="FebChart" style="width:50%;">全院路径排除情况</span>

     	<script type="text/javascript">
     	<!--
      var width = "49%";
			var JanGrid = new FusionCharts("../public/plugins/FusionCharts/Column2D.swf", "JanGridId", width, "300", "0", "1");
      JanGrid.setDataXML("<chart showShadow='true' baseFont='宋体' baseFontSize='14' caption='同步成功率' xAxisName='成功率' yAxisName='百分比' numberSuffix='%' formatNumberScale='0' useRoundEdges='1'><set label='上传成功率' value='98.47'/><set label='下载成功率' value='91.52'/><set label='更新成功率' value='95.88'/></chart>");
      JanGrid.render("JanGrid");

      var MarChart = new FusionCharts("../public/plugins/FusionCharts/Pie2D.swf", "MarChartId", width, "300", "0", "1");
      MarChart.setDataXML("<chart showShadow='true' baseFont='宋体' baseFontSize='14' caption='上传成功分布情况' bgColor='FFFFFF,CCCCCC' showPercentageValues='0' showShadow='1' plotBorderColor='FFFFFF' formatNumberScale='0' numberSuffix='%' isSmartLineSlanted='1' showValues='1' showLabels='0' showLegend='1'><set label='一次性成功率' value='67.28' color='333333' alpha='60' /><set label='由其它任务执行成功率' value='12.33' alpha='90' /><set label='独立重新执行成功率' value='20.39' alpha='90' /></chart>");
      MarChart.render("MarChart");										  

      var JanChart = new FusionCharts("../public/plugins/FusionCharts/Pie2D.swf", "JanChartId", width, "300", "0", "1");
      JanChart.setDataXML("<chart showShadow='true' baseFont='宋体' baseFontSize='14' caption='下载成功分布情况' bgColor='FFFFFF,CCCCCC' showPercentageValues='0' showShadow='1' plotBorderColor='FFFFFF' formatNumberScale='0' numberSuffix='%' isSmartLineSlanted='1' showValues='1' showLabels='0' showLegend='1'><set label='一次性成功率' value='59.88' color='333333' alpha='60' /><set label='由其它任务执行成功率' value='32.15' alpha='90' /><set label='独立重新执行成功率' value='7.97' alpha='90' /></chart>");
      JanChart.render("JanChart");
         
      var FebChart = new FusionCharts("../public/plugins/FusionCharts/MSColumn2D.swf", "FebChartId", width, "300", "0", "1");
      FebChart.setXMLUrl("../zhikong/fa.xml");
      FebChart.render("FebChart");
      // -->
      </script>
    </div>
	</body>
</html>