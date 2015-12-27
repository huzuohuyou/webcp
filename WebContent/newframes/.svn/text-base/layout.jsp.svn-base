<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	DatabaseClass db = LcpUtil.getDatabaseClass();
	Date dateNow = db.FunGetDbNow(true);
	String dateNowStr = sdf.format(dateNow);
	String version= request.getHeader("User-Agent");//判断浏览器版本
	String browser=" height='600px'";
		if(version!=null&&version.indexOf("MSIE 6.0")>0){ //如果浏览器为IE6  传入相关信息
			browser=" fit='true'";
		}
	%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Strict//EN"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>厦门市区域临床路径系统</title>
<link rel="stylesheet" type="text/css" href="../public/plugins/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="../public/plugins/easyui/icon.css">
<script type="text/javascript" src="../public/plugins/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../public/plugins/easyui/jquery.easyui.min.js"></script>
<script>
var isHideTop=false;
var hideLeft=function(){
	$('body').layout('collapse','west');
	};
var hideTop=function(element){
	var hideImg = "btn_hide_top";
	var showImg = "btn_show_top";
	if(!isHideTop){//隐藏顶栏
		$('body').layout('collapse','north');
		$(element).attr("src", $(element).attr("src").replace(hideImg, showImg));
		isHideTop=true;
		}else{//显示顶栏
			$('body').layout('expand','north');
			$(element).attr("src", $(element).attr("src").replace(showImg, hideImg));
			isHideTop=false;
			}
};
//点击左侧功能菜单
var changeTitle=function(text,url){
   $("#daohang").html(text);
   top.kang.location.href=url;
};
	
//显示灰色 jQuery 遮罩层
var showBg=function () {
    var bh = $("body").height();
    var bw = $("body").width();
    $("#fullbg").css({
        height:bh,
        width:bw,
        display:"block"
    });
    $("#dialog").show();
};
//关闭灰色 jQuery 遮罩
var closeBg=function() {
	$("#fullbg,#dialog").hide();
};
var mouseOver=function(element){
	element.style.backgroundImage='url(../public/images/tab_bg.gif)';
	element.style.borderStyle='solid';
	element.style.borderWidth='1px';
	element.style.borderColor='#adb9c2'; 
};
var mouseout=function(element){
	element.style.backgroundImage='url()';
	element.style.borderStyle='none'
};
$(function(){
	    //domThis.style.setExpression('top', 'eval((document.documentElement).scrollTop + ' + wh + ') + "px"');
		$("#kang").change(function(){
			alert("gengxin");
			});
		$("#kang").load(function(){  
		closeBg() ;
    });   
		
});
</script>


<style type="text/css">
body {
    font-family:Arial, Helvetica, sans-serif;
    font-size:12px;
    margin:0;
}

#fullbg {
    background-color:Gray;
    left:0px;
    opacity:0.5;
    position:absolute;
    top:0px;
    z-index:3;
    filter:alpha(opacity=50); /* IE6 */
    -moz-opacity:0.5; /* Mozilla */
    -khtml-opacity:0.5; /* Safari */
}
#dialog {
    background-color:#FFF;
    border:1px solid #888;
    display:none;
    height:50px;
    left:50%;
    margin:-100px 0 0 -100px;
    padding:12px;
    position:fixed !important; /* 浮动对话框 */
    position:absolute;
    top:50%;
    width:200px;
    z-index:5;
}
#dialog p {
    margin:0 0 12px;
}


body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}


.STYLE4 {font-size: 12px}
.STYLE5 {font-size: 15px; font-weight: bold; }
.STYLE7 {font-size: 12px; color: #FFFFFF; }
a{color:#ffffff;  text-decoration:none}
a:hover{color:ffffff; 	text-decoration:none;}
</style>	
</head>
<body class="easyui-layout">
	<div region="north"  split="false" border="false" style="height: 97px; overflow: hidden;">
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
              <tr align="right">
                <td height="17"><img src="../public/images/pass.gif" width="69" height="17" style="cursor:pointer" onClick="top.kang.location.href='../login/changepwd.jsp';document.all.daohang.innerHTML='系统设置&gt;修改密码';"/></td>
                <td><img src="../public/images/user.gif" width="69" height="17" style="cursor:pointer" /></td>
                <td><img src="../public/images/quit.gif" width="69" height="17" style="cursor:pointer" onclick = "window.location.href='../LoginServlet?op=quit';"/></td>
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
          <tr class="STYLE7">
            <td width="21"><img src="../public/images/main_13.gif" width="19" height="14" /></td>
            <td width="35" ><div align="center"><a href="right.jsp" target="kang"  onClick="document.all.daohang.innerHTML='首页';">首页</a></div></td>
            <td width="21" ><img src="../public/images/main_19.gif" width="19" height="14" /></td>
            <td width="35" ><div align="center"><a href="#" onClick="top.kang.location.reload();showBg();" >刷新</a></div></td>
            <td width="21" ><img src="../public/images/main_21.gif" width="19" height="14" /></td>
            <td width="35" ><div align="center"><a href="../help/caption.doc">帮助</a></div></td>
            <td width="21" ><img src="../public/images/main_21.gif" width="19" height="14" /></td>
            <td width="70" align="center"><a href="../help/whatsnew.doc">最近更新</a></td>
            <td width="300" ><div align="center">点击"帮助"或者"最近更新"，选择保存，下载说明文档</div></td>
            <td width="21" >&nbsp;</td>
            <td width="35" ><div align="center"></div></td>
            <td width="21" ></td>
            <td width="35" ><div align="center"></div></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
       
        <td width="248" background="../public/images/main_11.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="16%"></td>
            <td width="75%"><div align="center"><span class="STYLE7">服务器时间：<%=dateNowStr%></span></div></td>
            <td width="9%">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
	</div>
<!-- 标题 -->
<div region="west"   style="width: 147px; padding1: 1px; overflow: hidden; ">
	    <table width="147px" height="30px" border="0" align="center" cellpadding="0" cellspacing="0" background="../public/images/main_29.gif" >
				<tr height="30">
					<td width="24%">&nbsp;</td>
					<td width="43%" valign="middle" style="font-size: 12px;color: #000000;">功能菜单</td>
					<td width="33%" style="cursor:pointer"  onClick="hideLeft();"  align="right" title="单击隐藏">
					<img src="../public/plugins/easyui/images/layout_button_left.gif" ></td>
				</tr>
		</table>
				
<!-- 功能菜单（动态生成） -->
<div class="easyui-accordion" <%=browser %> border="false" id="accordion">
<%
if(session.getAttribute("userid")!=null){
	String userid = session.getAttribute("userid").toString();

	DataSetClass dataSet = new DataSetClass();
	String shangji = "";
	String funs = "";
	String sql="select t2.funs_id,t2.funs_name,t2.funs_url from dcp_sys_power t1,dcp_sys_funs t2 where t1.funs_id=t2.funs_id and t1.user_id="+userid+" and t2.sys_is_del = 0 order by t2.funs_id";
    System.out.println(sql);
	dataSet = db.FunGetDataSetBySQL(sql);
	for (int i = 0; i < dataSet.FunGetRowCount(); i++) {
		int op = dataSet.FunGetDataAsNumberByColName(i,"FUNS_ID").intValue();
		String name = dataSet.FunGetDataAsStringByColName(i,"FUNS_NAME");
		String url = dataSet.FunGetDataAsStringByColName(i,"FUNS_URL");
		funs+=url+",";
		if(op%10==0){
			shangji = name;
			if(i==0){
%>
   <div title="<%=name%>" style="padding: 10px; overflow: auto;" selected="true" iconCls="icon-ok" height="100px" >
      <table>
<%		}else{
%>
      </table></div>
   <div title="<%=name%>" style="padding: 10px; overflow: auto;" iconCls="icon-ok">
      <table>
<%		}
	}else{
		if(i==dataSet.FunGetRowCount()-1){
%>
            <tr  height="38" onMouseOver="mouseOver(this); "
            onmouseout="mouseout(this)">
              <td width="33" height="28"><img src="../public/images/left_<%=op%>.gif" width="28" height="28"></td>
                    <td height="23" class="STYLE4" style="cursor:pointer"  onClick="changeTitle('<%=shangji+"&gt;"+name%>','<%=url %>'); "><%=name%></td>
                  </tr>
<%		}else{
//	<table width="100%" border="0" cellspacing="0" cellpadding="0">
%>		
        <tr height="38" onmouseout="mouseout(this)"
           style="cursor:pointer" onClick="showBg();changeTitle('<%=shangji+"&gt;"+name%>','<%=url %>');"
           onMouseOver="mouseOver(this);">
              <td width="33" height="28"><img src="../public/images/left_<%=op%>.gif" width="28" height="28"></td>
              <td width="99" height="23" class="STYLE4"><%=name%></td>
        </tr>
<%		}
	}
}
session.setAttribute("funs",funs);
}
%>
</table>
</div>
<!-- 版本说明 -->
	<div title="版本：V1.0.1" style="padding: 10px; overflow: auto;" iconCls="icon-tip">
	Copyright © 2011 - 2012 JH. All Rights Reserved. 中国仪器进出口(集团)公司  版权所有
	</div>
</div>
<!-- End 功能菜单 -->
</div>
	
<div region="center"  style="overflow: hidden;width: 100%; height: 100%; " border="false">
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
								<img src="../public/images/btn_hide_top.png" onclick="hideTop(this);" style="cursor: pointer;" />
							</td>
							<td width="9" style="background: url(../public/images/main_32a.gif) no-repeat;"></td>
						</tr>
					</table>
				</td>
			</tr>
  			<tr>
  				<td width="100%" height="100%" valign="top" >
  					<div  >
  						<iframe id="kang" name="kang" height="100%" width="100%" frameborder="0" src="../frames/right.jsp"></iframe>
  					</div>
  				</td>
  			</tr>
  			<tr ><td   width="18" bgcolor="#353c44"><div id="fullbg"></div>&nbsp;</td></tr>
		</table>
	
	
</div>  
<!--<div region="center"  style="overflow: hidden;width: 100%; height: 100%; " border="false">
<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="height: 30px; background: url(../public/images/main_31.gif) repeat-x;">
			<tr style="height: 30px; background: url(../public/images/main_31.gif) repeat-x;">
							<td height="30" style="background: url(../public/images/main_30b.gif) no-repeat; padding-left: 30px;">
								<span class="STYLE10">当前模块：</span>
								<span	class="STYLE2" id = "daohang">首页&gt; 概况报表</span>
							</td>
							<td  height="30" class="STYLE10" align="right">
								<img src="../public/images/sj.gif" width="6" height="7" /> 
								IP：<span class="STYLE2"><%=request.getRemoteAddr() %></span>
								&nbsp; &nbsp;&nbsp;<img src="../public/images/sj.gif" width="6" height="7" /> &nbsp;
								当前登录用户：<span class="STYLE2"><%=session.getAttribute("username")%></span>
								<img src="../public/images/btn_hide_top.png" onclick="hideTop(this);" style="cursor: pointer;" />
</td>
							<td width="9" style="background: url(../public/images/main_32a.gif) no-repeat;"></td>
		  </tr>
				
			
  			<tr  height="100%" >
  				<td  colspan="3"  valign="top" >
  					<div  >
  						<iframe id="kang" name="kang" height="100%" width="100%" frameborder="0" src="right.jsp"></iframe>
  					</div>
  				</td>
  			</tr>
  			<tr ><td  colspan="3"  width="8" bgcolor="#353c44"><div id="fullbg"></div>&nbsp;</td></tr>
		</table>
	
	
</div>  

--><!-- 对话框 -->
<div id="dialog">
  <p>正在加载，请稍后...</p>
  <img alt="" src="load.gif">
</div>
	
</body>
</html>