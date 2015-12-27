<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.goodwillcis.lcp.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>厦门市区域临床路径平台</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	font-size: 12px;
	color: #000000;
}
.STYLE2 {
	font-size: 12px;
	color: #0000FF;
}
.STYLE5 {font-size: 12}
.STYLE7 {font-size: 12px; color: #FFFFFF; }
a{color:#ffffff;  text-decoration:none}
a:hover{color:ffffff; 	text-decoration:none;}
-->
</style>

</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="57" background="../public/images/main_03.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="378" height="57" background="../public/images/main_01.gif">&nbsp;</td>
        <td align="right"><img src="../public/images/zhongyijiahe.png" width="80" height="30" /></td>
        <td width="281" valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="33" height="27"><img src="../public/images/main_05.gif" width="33" height="27" /></td>
            <td width="248" background="../public/images/main_06.gif"><table width="225" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td height="17"><div align="right"><img src="../public/images/pass.gif" width="69" height="17" style="cursor:pointer" onClick="top.mainFrame.kang.location.href='../login/changepwd.jsp';top.mainFrame.document.all.daohang.innerHTML='系统设置&gt;修改密码';"/></div></td>
                <td><div align="right"><img src="../public/images/user.gif" width="69" height="17" style="cursor:pointer" /></div></td>
                <td><div align="right"><img src="../public/images/quit.gif" width="69" height="17" style="cursor:pointer" onclick = "window.location.href='../LoginServlet?op=quit';"/></div></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="40" background="../public/images/main_10.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="194" height="40" background="../public/images/main_07.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="21"><img src="../public/images/main_13.gif" width="19" height="14" /></td>
            <td width="35" class="STYLE7"><div align="center"><a href="right.jsp" target="kang"  onClick="top.mainFrame.document.all.daohang.innerHTML='首页';">首页</a></div></td>
            <td width="21" class="STYLE7"><img src="../public/images/main_19.gif" width="19" height="14" /></td>
            <td width="35" class="STYLE7"><div align="center"><a href="#" onClick="top.mainFrame.kang.location.reload();" >刷新</a></div></td>
            <td width="21" class="STYLE7"><img src="../public/images/main_21.gif" width="19" height="14" /></td>
            <td width="35" class="STYLE7"><div align="center"><a href="../help/caption.doc">帮助</a></div></td>
            <td width="21" class="STYLE7"><img src="../public/images/main_21.gif" width="19" height="14" /></td>
            <td width="70" class="STYLE7"><div align="center"><a href="../help/whatsnew.doc">最近更新</a></div></td>
            <td width="300" class="STYLE7"><div align="center">点击"帮助"或者"最近更新"，选择保存，下载说明文档</a></div></td>
            <td width="21" class="STYLE7">&nbsp;</td>
            <td width="35" class="STYLE7"><div align="center"></div></td>
            <td width="21" class="STYLE7"></td>
            <td width="35" class="STYLE7"><div align="center"></div></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
        <%
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DatabaseClass db = LcpUtil.getDatabaseClass();
		Date dateNow = db.FunGetDbNow(true);
		String dateNowStr = sdf.format(dateNow);
        %>
        <td width="248" background="../public/images/main_11.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="16%"><span class="STYLE5"></span></td>
            <td width="75%"><div align="center"><span class="STYLE7">服务器时间：<%=dateNowStr%></span></div></td>
            <td width="9%">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
