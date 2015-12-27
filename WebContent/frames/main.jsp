<%@page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<title>厦门市区域临床路径平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../public/plugins/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../public/plugins/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../public/styles/frames/main.css">
	<script type="text/javascript" src="../public/plugins/jquery/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="../public/plugins/easyui/jquery.easyui.min.js"></script>

<%

if(session.getAttribute("userid")==null){
	response.sendRedirect("../login.jsp"); 
}

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DatabaseClass db = LcpUtil.getDatabaseClass();
		Date dateNow = db.FunGetDbNow(true);
		String dateNowStr = sdf.format(dateNow);
        %>
<script type="text/javascript">
	$(function() {
		showProgress();
		var he = $(window).height();
		$("#kang,#left").height((he - 137));//根据窗口自适应
		$(window).resize(
				function() {//窗口大小变化时
					var hes = $(window).height();
					$("#top").css("display") != "none" ? $("#kang,#left").height((hes - 140)) : $("#kang,#left").height((hes - 45));
				});
		//查看是否有已出院路径还在执行
		$.post("../LoginServlet",{op : "isExecute"},function(result){
			if(result>0){
				var msg = '<span onclick="msClicks(\'../statements/patientInfo.html\', \'name\')" style="cursor:pointer">本科室有已出院未完成路径'+result+'条,请点击查看详细信息！</span>';
				showMessage(msg);	
			}
		},"text");
		$("#kang").load(function() { //iframe加载完毕关闭遮罩层
			$.messager.progress('close');
		});

		$(".list").hover(function() {
			$(this).addClass("over");
		}, function() {
			$(this).removeClass("over");
		});
	});

	var switchSysBar = function(text) {//隐藏左侧或顶部
		var name = "#" + text;
		var $menuBar = $(name);
		$menuBar.css("display") != "none" ? $menuBar.hide() : $menuBar.show();

	};

	var msClick = function(url, name, element) {
		$.post('../LoginServlet?op=isSessionExpired', function(data) {
			if (data == 0) {
				alert("页面过期，请重新登陆！");
				window.location.href = '../LoginServlet?op=quit';
			} else {
				$(".click").removeClass('click');
				$(element).addClass("click");
				showProgress();
				$("#daohang").html(name);
				//释放内存操作
				var ifm=$("#kang");
				ifm.src="";
				$("#kang").attr("src", url);
			}
		});
	};

	var msClicks = function(url, name) {
		$.post('../LoginServlet?op=isSessionExpired', function(data) {
			if (data == 0) {
				alert("页面过期，请重新登陆！");
				window.location.href = '../LoginServlet?op=quit';
			} else {
				$(".click").removeClass('click');
				showProgress();
				$("#daohang").html(name);
				//释放内存操作
				var ifm=document.getElementById("kang");
				ifm.src="";
				del_ff(ifm);
				$("#kang").attr("src", url);
			}
		});
	};

	function del_ff(elem) {
		var elem_child = elem.childNodes;
		for ( var i = 0; i < elem_child.length; i++) {
			elem.removeChild(elem_child);
		}
	}

	//显示进度条
	var showProgress = function() {
		$.messager.progress({
			title : '<span style="color: black; ">请稍等</span>',
			msg : '正在加载数据...',
			interval : 250
		});
	};

	//弹出信息
	var showMessage = function(msg) {
		$.messager.show({
			title : '<span style="color: black; ">临床路径系统消息</span>',
			msg : msg,
			timeout : 0,
			height : 200
		});

	};
</script>
</head>

<body>
  <table>
   <tr id="top"><td ><table>
	  <tr>
	    <td background="../public/images/main_03.gif">
    <table>
      <tr>
        <td width="378" height="57" background="../public/images/main_01.gif">&nbsp;</td>
        <td align="right"><img src="../public/images/zhongyijiahe.png" width="80" height="30" /></td>
        <td width="281" valign="bottom">
        <table>
          <tr>
            <td width="33" height="27"><img src="../public/images/main_05.gif" width="33" height="27" /></td>
            <td width="248" background="../public/images/main_06.gif">
            <table width="225"  align="center"  >
              <tr>
                <td height="17"><div align="right"><img src="../public/images/pass.gif" width="69" height="17" style="cursor:pointer" onClick="msClick('../login/changepwd.jsp','系统设置&gt;修改密码');"/></div></td>
                <td><div align="right"><img src="../public/images/user.gif" width="69" height="17" style="cursor:pointer" /></div></td>
                <td><div align="right"><img src="../public/images/quit.gif" width="69" height="17" style="cursor:pointer" onclick = "window.location.href='../LoginServlet?op=quit';"/></div></td>
              </tr>
            </table>
            </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="40" background="../public/images/main_10.gif">
    <table>
      <tr>
       <td width="194" height="40" background="../public/images/main_07.gif">&nbsp;</td>
        <td><table >
          <tr >
            <td width="21"><img src="../public/images/main_13.gif" width="19" height="14" /></td>
            <td width="35"><div align="center"><a href="../frames/main.jsp">首页</a></div></td>
            <td width="21"><img src="../public/images/main_19.gif" width="19" height="14" /></td>
            <td width="35"><div align="center"><a href="#" onClick="top.kang.location.reload();" >刷新</a></div></td>
           <td width="21"><img src="../public/images/main_21.gif" width="19" height="14" /></td>
         <td width="35" ><div align="center"><a href="../help/caption.doc">帮助</a></div></td>
            <td width="21" ><img src="../public/images/main_21.gif" width="19" height="14" /></td>
            <td width="70" ><div align="center"><a href="../help/whatsnew.doc">最近更新</a></div></td>
            <td width="300" ><div align="center"><a href="#">点击"帮助"或者"最近更新"，选择保存，下载说明文档</a></div></td>
            <td width="35"></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
       
        <td width="248" background="../public/images/main_11.gif">
        <table width="100%">
          <tr >
            <td width="16%"></td>
            <td width="75%"><div align="center"><a href="#">服务器时间：<%=dateNowStr%></a></div></td>
            <td width="9%">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
    
   </td></tr>
   <tr><td>

<table>
  	<tr>
    	<td width="8" bgcolor="#353c44">&nbsp;</td>
    	<td width="147" id="frmTitle" nowrap valign="top">
       
       <!-- 标题 -->
<div style="width:147px; height:30px;" >
	<table  border="0" align="center">
		<tr>
			<td width="100%" height="30" background="../public/images/main_29.gif" ><table>
				<tr>
					<td width="24%">&nbsp;</td>
					<td width="43%" valign="bottom" class="STYLE1">功能菜单</td>
					<td width="33%">&nbsp;</td>
				</tr>
			</table></td>
		</tr>
	</table>
</div>


<!-- 功能菜单（动态生成） -->
<%
if(session.getAttribute("userid")!=null){
	String userid = session.getAttribute("userid").toString();
DataSetClass dataSet = new DataSetClass();
String shangji = "";
String funs = "";
String sql="select t2.funs_id,t2.funs_name,t2.funs_url from dcp_sys_power t1,dcp_sys_funs t2 where t1.funs_id=t2.funs_id and t1.user_id="+userid+" and t2.sys_is_del = 0 order by t2.funs_id";
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
<div id="aa" class="easyui-accordion" style="width:147px;height:350px;">
		<div title="<%=name%>"  style="overflow:auto;padding:10px;">
			
	<table  align="center">
		
<%		}else{
%>
 </table>
			
	</div>
    <div title="<%=name%>"  style="overflow:auto;padding:10px;">
	
		  		<table  id="tableList">
<%		}
	}else{
		if(i==dataSet.FunGetRowCount()-1){
%>
            <tr class="list"  onClick="msClick('<%=url%>','<%=shangji+"&gt;"+name%>',this); ">
              <td ><img src="../public/images/left_<%=op%>.gif" width="24" height="24"></td>
              <td  ><%=name%></td>
            </tr>
          </table>
			
	</div>
			
<%		}else{
%>		
            <tr  class="list"   onClick="msClick('<%=url%>','<%=shangji+"&gt;"+name%>',this); ">
             <td ><img src="../public/images/left_<%=op%>.gif" width="24" height="24"></td>
             <td  ><%=name%></td>
            </tr>
	
<%		}
	}
}
session.setAttribute("funs",funs);
}
%>
		</div>
	
	
	
<!-- End 功能菜单 -->

<!-- 版本说明 -->
<div  style="position:absolute; width:147px; height:19px;background-color: #add2da; " align="center">
	版本：V2.0.0
</div>


<!-- 动态菜单结束-->
       
    	</td>
    	<td width="10" bgcolor="#add2da" valign="top">
    		<table >
        	<tr height="30">
        		<td  >
        			<img src="../public/images/main_30a.gif" name="imgtt" width=10 height=30 id=imgtt>
        		</td>
        	</tr>
          <tr>
          	<td  valign="middle">
          		<span id="switchPoint" title="关闭/打开左栏" >
          			<img src="../public/images/main_41.gif" style="cursor:pointer" width="9" height="52" onclick="switchSysBar('frmTitle');" />
          		</span>
          	</td>
          </tr>
        </table>
    	</td>    
    	<td valign="top">
    		<table align="center">
					<tr>
						<td style="height: 30px; background: url(../public/images/main_31.gif) repeat-x;">
							<table  style="height: 30px;" >
								<tr>
									<td style="background: url(../public/images/main_30b.gif) no-repeat; padding-left: 30px;">
										<span class="STYLE1">当前模块：</span>
										<span	class="STYLE2" id = "daohang">首页&gt; 概况报表</span>
										<a href="javascript:history.go(-1);"><span	class="STYLE2" >[返回上一页]</span></a>
									</td>
									<td class="STYLE1" align="right">
										<img src="../public/images/sj.gif" width="6" height="7" /> 
										IP：<span class="STYLE2"><%=request.getRemoteAddr() %></span>
										&nbsp; &nbsp;&nbsp;<img src="../public/images/sj.gif" width="6" height="7" /> &nbsp;
										当前登录用户：<span class="STYLE2"><%=session.getAttribute("username")%></span>
										<img src="../public/images/btn_hide_top.png" onclick="switchSysBar('top');"style="cursor: pointer;" />
 									</td>
									<td width="9" style="background: url(../public/images/main_32a.gif) no-repeat;"></td>
								</tr>
							</table>
						</td>
					</tr>
    			<tr>
    				<td><div >
</div>

    					<div id="_iframe">
    						<iframe id="kang" name="kang"  width="100%" height="600px" frameborder="0" src="right.jsp"></iframe>
    					</div>

    				</td>
    			</tr>
				</table>
			</td>
    	<td width="8" bgcolor="#353c44"><div id="fullbg"></div>&nbsp;</td>
  	</tr>
  	
  	 <tr> <td colspan="5">
  	 
  	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td background="../public/images/main_71.gif"  style="line-height:11px; table-layout:fixed" width="165">&nbsp;</td>
    <td background="../public/images/main_72.gif"  style="line-height:11px; table-layout:fixed">&nbsp;</td>
    <td background="../public/images/main_74.gif"  style="line-height:11px; table-layout:fixed" width="17">&nbsp;</td>
  </tr>
</table>
  	 </td></tr>
	</table>

</td></tr>
   </table> 
</body>
</html>