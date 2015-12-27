<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
if(session.getAttribute("userid")==null){
	response.getWriter().println("<script type=\"text/javascript\">top.location.href=\"../login.jsp\";</script>");
}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
	<title>无标题文档</title>
	<style type="text/css">
	<!--
	body { margin: 0; overflow:hidden; font-size: 62.5%; }
	
	.STYLE2 { font-size: 12px; color: #0000FF;}
	.STYLE10 { color: #000000; font-size: 12px;}
	.navPoint { COLOR: white; CURSOR: hand; FONT-FAMILY: Webdings; FONT-SIZE: 9pt}
	-->
	</style>
	<script type="text/javascript">
	var tmpRows = "";

	var switchSysBar = function (){
		var menuBar = $("#frmTitle");
		menuBar.css("display") != "none" ? menuBar.hide() : menuBar.show();
	};

	var toggleTop = function(element){
		var hideRows = "0,*,11";
		var hideImg = "btn_hide_top";
		var showImg = "btn_show_top";

		var topFrm = parent.document.getElementById("frm");

		if (topFrm.rows == hideRows) { // 显示顶栏
			topFrm.rows = tmpRows;
			$(element).attr("src", $(element).attr("src").replace(showImg, hideImg));
		}
		else { // 隐藏顶栏
			tmpRows = topFrm.rows;
			topFrm.rows = hideRows;
			$(element).attr("src", $(element).attr("src").replace(hideImg, showImg));
		}
	};
	</script>
</head>

<body>
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  	<tr>
    	<td width="8" bgcolor="#353c44">&nbsp;</td>
    	<td width="147" id="frmTitle" nowrap valign="top">
    		<iframe id="left" height="100%" width="100%" frameborder="0" src="left.jsp"></iframe>
    	</td>
    	<td width="10" bgcolor="#add2da" valign="top">
    		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
        	<tr height="30">
        		<td valign="top" >
        			<img src="../public/images/main_30a.gif" name="imgtt" width=10 height=30 id=imgtt>
        		</td>
        	</tr>
          <tr>
          	<td>
          		<span class=navPoint id=switchPoint title=关闭/打开左栏>
          			<img src="../public/images/main_41.gif" style="cursor:pointer" width=9 height=52 onclick=switchSysBar() />
          		</span>
          	</td>
          </tr>
        </table>
    	</td>    
    	<td valign="top">
    		<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td style="height: 30px; background: url(../public/images/main_31.gif) repeat-x;">
							<table width="100%" style="height: 30px;" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="background: url(../public/images/main_30b.gif) no-repeat; padding-left: 30px;">
										<span class="STYLE10">当前模块：</span>
										<span	class="STYLE2" id = "daohang">首页&gt; 概况报表</span>
									</td>
									<td class="STYLE10" align="right">
										<img src="../public/images/sj.gif" width="6" height="7" /> 
										IP：<span class="STYLE2"><%=request.getRemoteAddr() %></span>
										&nbsp; &nbsp;&nbsp;<img src="../public/images/sj.gif" width="6" height="7" /> &nbsp;
										当前登录用户：<span class="STYLE2"><%=session.getAttribute("username")%></span>
										<img src="../public/images/btn_hide_top.png" onclick="toggleTop(this);" style="cursor: pointer;" />
									</td>
									<td width="9" style="background: url(../public/images/main_32a.gif) no-repeat;"></td>
								</tr>
							</table>
						</td>
					</tr>
    			<tr>
    				<td>
    					<div style="height:100%">
    						<iframe id="kang" name="kang" height="100%" width="100%" 
    							frameborder="0" src="right.jsp"></iframe>
    					</div>
    				</td>
    			</tr>
				</table>
			</td>
    	<td width="8" bgcolor="#353c44">&nbsp;</td>
  	</tr>
	</table>
</body>
</html>