<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 临床路径节点变异数统计 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="css/va.css" />
<link rel="stylesheet" type="text/css" href="css/layout.css" />

<link rel="stylesheet" href="css/screen1.css" media="screen" />
<link rel="stylesheet" href="css/jquery.treetable.css" />
<link rel="stylesheet" href="css/jquery.treetable.theme.default.css" />
<body >
	<div id="container">
		<div id="sidebar"><!-- <div class="mytopbar">&nbsp科室名称:<input id="dept_name" type="text"  /> <input id="mysearch" type="button"  value="搜索" /> </div> -->
			<table class="bordered" id="optionContainer">
				<thead>
					<tr style="font-size:12px,font-family:Helvetica, Arial, sans-serif">
						<th style="display: none">主路径ID</th>
						<th width="12%">路径ID</th>
						<th width="20%">路径名称</th>
						<th style="display: none">路径编码</th>
						<th width="12%">变异个数</th>
						<th width="12%">完成个数</th>
						<th width="12%">变异率</th>
						<th width="12%">科室</th>
						<th width="10%">状态</th>
					</tr>
				</thead>
				<tbody id="cps"></tbody>
			</table>
		</div>
		<div id="rightbar">
			<div  class="mytopbar" id="mybtn">
			<form>
				<input type="radio" name="identity" class='checkOrder' value="" />查看医嘱执行率 
				<input type="radio" name="identity" class='checkNode' value="" />查看路径节点变异
				<!-- <input type="button" onclick="window.location.href='../cpmanage/cplist.jsp';" value="修改临床路径" /> -->
				<input  type="radio" name="identity" checked="checked" class='forwardpdca'  value="" />持续改进
			</form>
			</div>
			<div id="mygraph"></div>
			<div id="myorders">
				<table class="bordered" id="example-advanced">
					<thead>
						<tr style='font-size:12px,font-family:Helvetica, Arial, sans-serif'>
							<th width="50%">医嘱名称</th>
							<th width="20%">医嘱编码</th>
							<th width="15%">执行次数</th>
							<th width="15%">执行率</th>
						</tr>
					</thead>
					<tbody id='orderseqs'></tbody>
				</table>
			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript" src="js/vacp.js"></script>
<script type="text/javascript" src="js/jscharts.js"></script>
<script type="text/javascript" src="js/jscharts.plug.mb.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.treetable.js"></script>
<script type="text/javascript" src="../public/plugins/easyui/jquery.easyui.min.js"></script>
</html>