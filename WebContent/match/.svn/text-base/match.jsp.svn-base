<%--  

/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：match.jsp
// 文件功能描述：匹配页面
// 创建人：刘植鑫 
// 创建日期：2011/07/26
// 
//----------------------------------------------------------------*/

  --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../frames/power.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../public/plugins/jquery/themes/base/jquery.ui.all.css">
	<link rel="stylesheet" href="../public/plugins/jquery/themes/cupertino/jquery-ui-1.8.11.custom.css">
	<link rel="stylesheet" href="../public/plugins/jquery/themes/cupertino/jquery.ui.all.css">
	<script src="../public/plugins/jquery/jquery-1.5.1.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.core.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.tabs.js"></script>
	<script src="../public/plugins/jquery/external/jquery.bgiframe-2.1.2.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.mouse.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.button.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.draggable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.position.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.resizable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.dialog.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.effects.core.js"></script>
	<link rel="stylesheet" href="../public/plugins/jquery/demos/demos.css">
<title>Insert title here</title>
</head>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE2 {
	font-size: 12px;
	color: #0000FF;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
.tabheightandwidge{
	height:600px;
	margin-left: 0px;
}
#tabs { height: 200px; } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 550px; overflow: auto;} 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0em 0em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -0px !important; margin-bottom: 0px !important; border-top: none; border-bottom-width: 0px; }
	.ui-tabs-selected { margin-top: -3px !important; }
	.ui-tabs .ui-tabs-panel { display: block; border-width: 0; padding: 0em 0em; background: none; }
-->
</style>
<script>
tag_1=false;
tag_2=false;
tag_3=false;
tag_4=false;
tag_5=false;
$(function() {
    $("#tabs1").tabs();
    $('#tag1').html('<iframe src="match_dia.jsp" frameborder="0" height="100%" marginwidth=”0″ width="100%" id="dialogFrame" scrolling="auto"></iframe>');
    $('#tabs1').bind('tabsselect', function(event, ui) {
    	if(ui.index==1){
    		if(!tag_2){
    			tag_2=true;
    			 $('#tag2').html('<iframe src="match_opera.jsp" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');
    		}
    	}
    	if(ui.index==2){
    		if(!tag_3){
    			tag_3=true;
    			 $('#tag3').html('<iframe src="match_doctor.jsp" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');
    		}
    	}
    	if(ui.index==3){
    		if(!tag_4){
    			tag_4=true;
    			 $('#tag4').html('<iframe src="match_nurse.jsp" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');
    		}
    	}
    	if(ui.index==4){
    		if(!tag_5){
    			tag_5=true;
    			 $('#tag5').html('<iframe src="match_order.jsp" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');
    		}
    	}
    });
});
</script>


</head>

<body >
	<!-- 主体框架-->
	<div id="tabs1" style="width:auto">
	<!-- 选择菜单 -->
	    <ul>
	        <li><a href="#fragment-1"><span style="font-size:12px">诊断</span></a></li>
	        <li><a href="#fragment-2"><span style="font-size:12px">手术</span></a></li>
	        <li><a href="#fragment-3"><span style="font-size:12px">诊疗</span></a></li>
	        <li><a href="#fragment-4"><span style="font-size:12px">护理</span></a></li>
	        <li><a href="#fragment-5"><span style="font-size:12px">医嘱</span></a></li>
	  	</ul>
	  	<!-- 路径基本信息 -->
		<div id="fragment-1" style="height:auto;">
			<div id="tag1" class="tabheightandwidge">
	    	</div>
  		</div>
  		<!-- 路径纳入/排除条件 -->
		<div id="fragment-2" style="height:auto;">   
			<div  id="tag2" class="tabheightandwidge">
	    	</div>
		</div>
		<!-- 诊断与治疗方案依据 -->
		<div id="fragment-3" style="height:auto;">
			<div  id="tag3" class="tabheightandwidge">
	    	</div>
  		</div>
  		<!-- 路径指定 -->
  		<div id="fragment-4" style="height:auto;">
  			<div  id="tag4" class="tabheightandwidge">
	    	</div>
    	</div>
    	<!-- 预防性抗菌药物选择与使用时机 -->
    	<div id="fragment-5" style="height:auto;">
    		<div  id="tag5" class="tabheightandwidge">
	    	</div>
    	</div>
    	
	</div>
</body>
</html>
