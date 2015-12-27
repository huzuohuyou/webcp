<%-- 
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：match_cp.jsp
// 文件功能描述：匹配本地路径页面
// 创建人：张昆
// 创建日期：2012-05-10
// 
//----------------------------------------------------------------*/
 --%>
<%@ page contentType="text/html; charset=utf-8" language="java"  errorPage="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>路径匹配表</title>
	<script src="../public/plugins/jquery/jquery-1.5.1.js"></script>
	<link rel="stylesheet" href="../public/plugins/jquery/demos/demos.css"/>
	<script src="../public/javascripts/match/map.js"></script> 
	<script src="../public/javascripts/match/matchcp.js"></script>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background: none;
}
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {	color: #344b50;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12; }
</style>
</head>

<body>
<div id="ks" style="height: 100%; width: 100%;">
<table border="0"  cellpadding="3" cellspacing="0" style=" border-style:solid; border-width:0; border-color:#e1e1e1; height: 100%; width: 100%;">
  <tr>
    <td width="50%" valign="top">
    <table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" style=" border-style:solid; border-width:0; border-color:#e1e1e1; width: 100%;">
	        	<tr>
	        		<td>
	        			<input name="zccx" type="radio" value="1"/>编码
				        <input type="radio" name="zccx" value="2"/> 名称
				        <input type="radio" name="zccx" value="3" checked="checked"/> 拼音
				        <input type="radio" name="zccx" value="4"/> 五笔
				        <input type="text" id="zctext" oninput="chaxunzcl();" onpropertychange="chaxunzcl();"/>
				        中心路径
	        		</td>
	        	</tr>
        </table>
        <table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" style=" border-style:solid; border-width:0; border-color:#e1e1e1; height: 100%; width: 100%;">
        	<tr>
        		<td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">路径编号</span></div></td>
        		<td width="*" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">路径名称</span></div></td>
        		<td width="25%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">创建时间</span></div></td>
        		<td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">是否匹配</span></div></td>
        		</tr>
        	<tbody id="zxTable">        	
        	</tbody>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      	<tbody id="zxPage">
	     
	      	</tbody>
	    </table>
    </td>
    <td  valign="top">
    	<div id="cxtj" style="height: 20px;">
    		<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" style=" border-style:solid; border-width:0; border-color:#e1e1e1; width: 100%;">
	        	<tr>
	        		<td>
	        			<input name="yccx" type="radio" value="1"/>编码
				        <input type="radio" name="yccx" value="2"/> 名称
				        <input type="radio" name="yccx" value="3" checked="checked"/> 拼音
				        <input type="radio" name="yccx" value="4"/> 五笔
				        <input type="text" id="yctext" oninput="chaxunycl();" onpropertychange="chaxunycl();"/>
				       本地路径
	        		</td>
	        	</tr>
        	</table>
		</div>
		<div id="ppnr" style="height: 220px; overflow-y: scroll;">
	       <table width="100%" id="node"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce"  style=" height:auto">
	        	<tr>
	        		<td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">路径编号</span></div></td>
	        		<td width="*" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">路径名称</span></div></td>
	        		<td width="25%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">路径版本</span></div></td>
	        		<td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">是否匹配</span></div></td>
	        	</tr>
	        	<tbody id="bdTable"></tbody>
          </table>
       	</div>
	    <table width="100%">
	    	<tr>
	     		<td align="right"><input type="button" value="添加" onclick="tianjia();"/>
	     		<input type="button" value="取消匹配" onclick="shanchu();"/></td>
	     	</tr>
	    </table>
	   	<div id="ppjg" style="height: 150px; overflow-y: scroll;">
	    	<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" style=" border-style:solid; border-width:0; border-color:#e1e1e1; width: 100%;">
	        	<tr>
	        		<td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">路径编号</span></div></td>
	        		<td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">路径名称</span></div></td>
	        		<td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">对照本地编号</span></div></td>
	        	</tr>
	        	<tbody id="ppjgTable">
	        	
	        	</tbody>
	        </table>
		</div>
		<table width="100%">
		      	<tr>
		      		<td colspan="4" align="right"><input type="button" value="提交" onclick="tijiao();"/></td>
		      	</tr>
      	</table>
    </td>
    <td width="10"></td>
  </tr>
</table>
</div>
</body>
</html>