<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
  <head>
    <style type="text/css">
<!--


body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
    </style>
    <title>路径图表</title>
<%String cp_id=request.getParameter("cp_id");
%>    
	<script type="text/javascript" src="../public/plugins/jquery/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="../public/plugins/FusionCharts/FusionCharts.js"></script>
	<script type="text/javascript"> 

		$(document).ready(function(){
		     $.post('../servlet/ChartServlet?cpId=<%=cp_id%>',function(data){
			     if(data!=""){
			           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
						var myChart = new FusionCharts("../public/plugins/FusionCharts/DragNode.swf","myCharts", "800","400"); 
			           /* 数据源 URL  */
						myChart.setDataXML(data);
					    //myChart.setDataURL("data.xml");	通过xml文件地址指定。
			           /* 图形渲染在指定的地方 通过ID 来确定 */       
						myChart.render("myDiv");
			        }
		     });
		});
	</script>

  </head>
<body>
	<div id="myDiv" align="center"></div>
</body>
	
</html>