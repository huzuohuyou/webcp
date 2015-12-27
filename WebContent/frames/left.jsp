<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Strict//EN"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>嘉和区域临床路径--功能菜单</title>
<link type="text/css" href="../public/plugins/jquery/cupertino/jquery-ui-1.8.11.custom.css" rel="stylesheet" /> 
<script type="text/javascript" src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="../public/plugins/jquery/jquery-ui-1.8.11.custom.min.js"></script>

<style type="text/css">

/*滚动条向上的箭头*/


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
.STYLE3 {
	font-size: 12px;
	color: #435255;
}
.STYLE4 {font-size: 12px}
.STYLE5 {font-size: 15px; font-weight: bold; }
</style>	

</head>


<script type="text/javascript">
	$(function(){
		// Accordion
		$("#accordion").accordion({ 
			header: "h3",
			//autoHeight:true
			//fillSpace:true
			autoHeight: false
		});
	});
	$(function() {
		$( "#accordionResizer" ).resizable({
			minHeight: 140,
			resize: function() {
				$( "#accordion" ).accordion( "resize" );
			}
		});
	});
	
	

</script>
<body>
<!-- 标题 -->
<div style="width:147px; height:30px;" >
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%" height="30" background="../public/images/main_29.gif" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="24%">&nbsp;</td>
					<td width="43%" valign="bottom" class="STYLE1">功能菜单</td>
					<td width="33%">&nbsp;</td>
				</tr>
			</table></td>
		</tr>
	</table>
</div>

<div >
<!-- 功能菜单（动态生成） -->
<div id="accordion" style="width:147px; " >
	<%
if(session.getAttribute("userid")!=null){
	String userid = session.getAttribute("userid").toString();

	DatabaseClass db = LcpUtil.getDatabaseClass();
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
<div >
		<h3>
        <table width="100%" height="23" border="0" cellspacing="0" cellpadding="0" style="margin:0px;">
      <tr>
        
        <td width="100%" height="23" background="../public/images/main_34.gif" ><div align="center" class="STYLE5"><%=name%></div></td>
        
      </tr>
    </table></h3>
    	
		<div >
		  	<table width="82%"  border="0" align="center" cellpadding="0" cellspacing="0">
        

		
<%		}else{
%>
 </table>
			</div>
        
	</div>
    <div >
		<h3>
        <table width="100%" height="23" border="0" cellspacing="0" cellpadding="0" style="margin:0px;">
      <tr>
        
        <td width="100%" height="23" background="../public/images/main_34.gif" ><div align="center" class="STYLE5"><%=name%></div></td>
        
      </tr>
    </table></h3>
    	
			<div >
		  		<table width="82%"  border="0" align="center" cellpadding="0" cellspacing="0">
        


<%		}
		
	}else{
		if(i==dataSet.FunGetRowCount()-1){
%>
<tr>
          <td height="38"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="33" height="28"><img src="../public/images/left_<%=op%>.gif" width="28" height="28"></td>
              <td width="99"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="23" class="STYLE4" style="cursor:pointer" onMouseOver="this.style.backgroundImage='url(../public/images/tab_bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1px';this.style.borderColor='#adb9c2'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'" onClick="top.mainFrame.kang.location.href='<%=url%>';top.mainFrame.document.all.daohang.innerHTML='<%=shangji+"&gt;"+name%>'; "><%=name%></td>
                  </tr>
              </table></td>
            </tr>
          </table></td>
        </tr>
          
      </table>
			</div>
        
	</div>
			
<%		}else{
%>		
        <tr>
          <td height="38"><table width="100%"   border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="33" height="28"><img src="../public/images/left_<%=op%>.gif" width="28" height="28"></td>
              <td width="99"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="23" class="STYLE4" style="cursor:pointer" onMouseOver="this.style.backgroundImage='url(../public/images/tab_bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1px';this.style.borderColor='#adb9c2'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'" onClick="top.mainFrame.kang.location.href='<%=url%>';top.mainFrame.document.all.daohang.innerHTML='<%=shangji+"&gt;"+name%>';"><%=name%></td>
                  </tr>
              </table></td>
            </tr>
          </table></td>
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
<div style="position:absolute; width:147px; height:19px;">
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td height="19" background="../public/images/main_69.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="24%">&nbsp;</td>
					<td width="76%" valign="bottom"><span class="STYLE3">版本：V1.0.1 </span></td>
				</tr>
			</table></td>
		</tr>
	</table>
</div>
</div>
</body>
</html>
