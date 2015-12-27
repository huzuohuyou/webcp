<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../frames/power.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="../public/plugins/jquery/themes/cupertino/jquery-ui-1.8.11.custom.css" />
		<link rel="stylesheet" href="../public/styles/style.css" />
		<script src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
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
		<script type="text/javascript">
		$(function(){
			$.ajaxSetup ({
				cache: false
			});
			$("#tabs").tabs({ ajaxOptions: { async: true } });
			$('#tabs').bind('tabsselect', function(event, ui) {
	    	if(ui.index == 0) $("#frag-1").load("up_list.jsp");
	    	else $("#frag-2").load("dn_list.jsp");
		  });
			$("#frag-1").load("up_list.jsp");
			$("#dialog").dialog({
				autoOpen : false,
				height : 450,
				width :800,
				modal : true,
				resizable: false,
				close:function(){
					$("#dialog").empty();
				}
			});
		});
		var show_page = function(source, url, pageIndex)
		{
			$("#" + source).load(url, {pageNext: pageIndex});
		};
		var page_select = function(source, url, pageIndex, pageCount){
			if (pageIndex == null || pageIndex < 1 || pageIndex > pageCount) alert("请正确输入页码！");
			else show_page(source, url, pageIndex);
		};
		var changeColor = function(event) {
			$(event).css("background-color", '#d5f4fe');
		};

		var recoverColor = function(event) {
			$(event).css("background-color", '#FFFFFF');
		};

		var loadInfo = function(url, type, options){
			$("#dialog").load(url, options);
			var title = (type == "up") ? "上传日志详细信息" : "下载日志详细信息";
			$("#dialog").dialog("option", "title", title);
			$("#dialog").dialog('open');
		};
		var executeInfo = function(id, type)
		{
			$("#wait").css("display","");
			$.ajax({	   
				url : "../ReExecute",
				type : 'POST',
				async: true,
				data : {
					type : type,
					id : id
				},
				dataType : "text",
				complete : show_result,
				success : onDataReceived
			});
		};

		var show_result = function(XMLHttpRequest, textStatus){	
			if(textStatus == "error") alert("请求出错！");
			else if(textStatus == "timeout") alert("请求超时！");
			$("#wait").hide();
		};

		var onDataReceived = function(data, textStatus, XMLHttpRequest){
			if (data.indexOf("complete") >= 0) {alert("重新执行完成！"); document.location.reload();}
			else alert("重新执行失败！");
		};
		</script>
		<title>人工同步</title>
	</head>

	<body>
		<div id="tabs" style="width:auto" >
			<ul>
				<li><a href="#frag-1"><span style="font-size: 12px">上 传</span></a></li>
				<li><a href="#frag-2"><span style="font-size: 12px">下 载</span></a></li>
			</ul>
			<div id="frag-1" style="height:auto;"></div>
			<div id="frag-2" style="height:auto;"></div>
		</div>
		<div id="dialog"> </div>
		<div id="wait" align="center" style="display:none;width:500px;height:120px;position:absolute;top:35%;left:35%;padding:2px;overflow:hidden;">
			<span style="background:#FFFFFF;"  > 数 据 正 在 加 载 中,请 等 待  . . .</span>
			<br />
			<img src='../public/images/loading.gif' width="400" height="15" /> 
		</div>
	</body>
</html>