
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../frames/power.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>路径发布</title>
<link rel="stylesheet" href="../public/plugins/jquery/themes/base/jquery.ui.all.css">
	<script src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
	<script src="../public/plugins/jquery/external/jquery.bgiframe-2.1.2.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.core.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.mouse.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.button.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.draggable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.position.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.resizable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.dialog.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.effects.core.js"></script>
	<script src="../public/javascripts/sync/sync.js"></script>
	<link rel="stylesheet" href="../public/styles/demos.css">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
		body { font-size: 62.5%; }
		input.text { margin-bottom:12px; width:95%; padding: .4em; }
		fieldset { padding:0; border:0; margin-top:25px; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		div#users-contain { width: 350px; margin: 20px 0; }
		div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
		div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.validateTips { border: 1px solid transparent; padding: 0.3em; }


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
.STYLE4 {font-size: 12px}

.ssh { text-align: left; border-width: 1px 0 0 0; background-image: none; margin: .5em 0 0 0; padding: .3em 1em .5em .4em; }


<!-- 连接美化 -->
.tooltips{
position:relative; /*这个是关键*/
z-index:2;
}
.tooltips:hover{
z-index:3;
background:none; /*没有这个在IE中不可用*/
}
.tooltips span{
display: none;
}
.tooltips:hover span{ /*span 标签仅在 :hover 状态时显示*/
display:block;
position:absolute;
top:21px;
left:9px;
width:15em;      /*修改显示窗口的宽度，不加此语句自动调整窗口大小 */
border:1px solid black;
background-color:#ccFFFF;
padding: 3px;
color:black;
}
.tableHeight{
	width: 100%;
	height: 330px;
}
-->
</style>
<script>
//跳转到数据更新页面
function submitData(){
		window.location.href="update.jsp";
}
</script>
</head>
	<div id="fra-1" >
        <table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td height="30" background="../public/images/main_31.gif" colspan="3">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr bgcolor="#353c44">
				<td height="23">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="5%" height="19" valign="bottom">
						<div align="center"><img src="../public/images/tb.gif"
							width="14" height="14" /></div>
						</td>
						<td width="95%" valign="bottom"><span class="STYLE1">
						版本数据更新列表</span></td>
					</tr>
				</table>
				</td>
				<td>
				<div align="right">
	<div style="background:url(../public/images/update.gif); text-align: left; margin:0 5px; width:69px;height:17px;cursor:pointer;" onClick="submitData()">
	<div style="font-size: 12px;margin-left:15px;padding-top:2px;padding-top:2px;display:block;position:relative;">更新数据</div>
</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>


</table>
	      <td><table width="100%" id="users" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
	        <tr>   
	          <td width="5%" height="24" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">版本号</span></div></td>
	          <td width="25%" height="24" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">更新包</span></div></td>
	          <td width="5%" height="24" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">状态</span></div></td>
	          <td width="5%" height="24" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
            </tr>
	        <tbody id="updateList">
            </tbody>
	        </table></td>
	      <td width="3">&nbsp;</td>
        </tr>
	    <tr>
	      <td width="3">&nbsp;</td>
	      <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	        <tbody id="updateListPageHtml">
            </tbody>
	        </table></td>
	      <td width="3">&nbsp;</td>
        </tr>
      </table>
</div>
	<div id="detail">
	
	</div>

</body>
</html>
