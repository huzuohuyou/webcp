<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 临床路径版本之间执行效果对比 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<link href="css/va.css" rel="stylesheet" type="text/css" />
<link href="css/layout.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="css/screen.css" media="screen" />
<link rel="stylesheet" href="css/jquery.treetable.css" />
<link rel="stylesheet" href="css/jquery.treetable.theme.default.css" />

<body>
	<div id="container2">
		<div id="sidebar2">
			<table class="bordered" id="optionContainer">
				<thead>
					<tr>
						<th>路径主ID</th>
						<th style="display: none">路径ID</th>
						<th>路径名称</th>
						<th style="display: none">路径编码</th>
						<th style="display: none">版本号</th>
						<th >科室</th>
					</tr>
				</thead>
				<tbody id="cplist"></tbody>
			</table>
		</div>
		<div id="rightbar2">
			<div>
				<table class="bordered" id="cpversions">
					<thead>
						<tr>
							<th style="width: 5%">#</th>
							<th style="width: 10%">路径ID</th>
							<th style="width: 26%">路径名称</th>
							<th style="width: 10%">路径编码</th>
							<th style="width: 13%">有效率(%)</th>
							<th style="width: 13%">平均住院日</th>
							<th style="width: 13%">平均住院费</th>
							<th style="width: 10%">状态</th>
						</tr>
					</thead>
					<tbody id="cpversionscontain"></tbody>
				</table>

			</div>
			<div>
				<input onClick="getCompare()" type="button" value="对比">
			</div>
			<div id='divcompare'>
				<div id='divcpone'>
					<table class="bordered" id="example-advancedone">
						<thead>
							<tr>
								<th id="mytitle1" width="100%">路径：</th>
							</tr>
						</thead>
						<tbody id='cpone'></tbody>
					</table>
				</div>
				<div id='divcptwo'>
					<table class="bordered" id="example-advancedtwo">
						<thead>
							<tr>
								<th id="mytitle2" width="100%">路径：</th>
							</tr>
						</thead>
						<tbody id='cptwo'></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/cpcp.js"></script>
<script type="text/javascript" src="js/jscharts.js"></script>
<script type="text/javascript" src="js/jscharts.plug.mb.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.treetable.js"></script>
</html>