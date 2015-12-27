<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="../public/plugins/jquery/cupertino/jquery-ui-1.8.11.custom.css" rel="stylesheet" /> 
<script type="text/javascript" src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="../public/plugins/jquery/jquery-ui-1.8.11.custom.min.js"></script>
<title>无标题文档</title>


<style type="text/css">
<!--
a:visited {
	color: #FFF;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}

.STYLE2 {
	font-size: 12px;
	color: #0000FF;
}

.STYLE6 {
	color: #000000;
	font-size: 12;
}

.STYLE10 {
	color: #000000;
	font-size: 12px;
}

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

.STYLE4 {
	font-size: 12px
}
-->
</style>
<script>
var biaoshi = true;
var data1;
var data2;
var data3;
var data4;
var data5;
var data6;
var data7;
var i = 0;
//var j = 0;

//js暂停函数
function Pause(obj,iMinSecond){ 
   if (window.eventList==null) window.eventList=new Array(); 
   var ind=-1; 
   for (var i=0;i<window.eventList.length;i++){ 
       if (window.eventList[i]==null) { 
         window.eventList[i]=obj; 
         ind=i; 
         break; 
        } 
    } 
   if (ind==-1){ 
   ind=window.eventList.length; 
   window.eventList[ind]=obj; 
   } 
  setTimeout("GoOn(" + ind + ")",iMinSecond); 
} 

//js继续函数
function GoOn(ind){ 
  var obj=window.eventList[ind]; 
  window.eventList[ind]=null; 
  if (obj.NextStep)obj.NextStep(); 
  else obj(); 
}


function upload(){
	biaoshi = true;
 	$("#button").attr("disabled",true);
//-------1------upload-------------------------------------------------------------------------------------------
 	$.ajax({
 	    url:'../servlet/updateData',
 	    type: 'POST',
 	    async: false,
 	    data: {op:'upload'
 	    	},
 	    dataType: "json",
 	    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
 	    success: function(data, textStatus, XMLHttpRequest){
			data1 = eval(data);
			}
 	  });
		if(!data1[0]['res']){
				alert("当前没有可用更新的版本包！");
				$("#button").attr("disabled",false);
				location.reload();
			}else{
				$('div#div1').html("＝＝＝＝＝＝＝＝ 版本更新开始 ＝＝＝＝＝＝＝＝<br/>");
				$('div#div1').append("正在加载可用的版本包......<br/>");
				$('div#div1').append(data1[1]['result1']);
				setTimeout("cycle()",1000);
			}
}

function cycle(){
	if(biaoshi){
		$.ajax({
 	    url:'../servlet/updateData',
 	    type: 'POST',
 	    async: false,
 	    dataType: "json",
 	    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
 	    success: function(data,textStatus,XMLHttpRequest){
			data2 = eval(data);
			}
 	  });
		if(data2[0]['res']=='a'){
				$('div#div1').append(data2[1]['result1']);
				div1.scrollTop=div1.scrollHeight;
		}else if(data2[0]['res']=='b'){
				$('div#div1').append(data2[1]['result1']);
				$('label#lab').html(data2[2]['result2']);
				div1.scrollTop=div1.scrollHeight;
		}else{
			$('div#div1').append(data2[1]['result1']);
			div1.scrollTop=div1.scrollHeight;
			$("#button").attr("disabled",false);
			biaoshi=false;
		}
	
	setTimeout("cycle()",700);
	}
}

/**
function uploadasdf(){

 	$("#button").attr("disabled",true);

//-------1------upload-------------------------------------------------------------------------------------------
 	$.ajax({
 	    url:'../servlet/updateData',
 	    type: 'POST',
 	    async: false,
 	    data: {op:'upload'
 	    	},
 	    dataType: "json",
 	    success: function(data, textStatus, XMLHttpRequest){
			data1 = eval(data);
			}
 	  });
		if(data1==null){
				alert("当前没有可用更新的版本包！");
				$("#button").attr("disabled",false);
				location.reload();
			}else{
				$('div#div1').html("＝＝＝＝＝＝＝＝ 版本更新开始 ＝＝＝＝＝＝＝＝<br/>");
				$('div#div1').append("正在加载可用的版本包......<br/>");
				
				$('div#div1').append("加载成功，共"+data1.length+"个版本包可以更新<br/>");
				//for(i = 0; i < data1.length; i++){}
					
				setTimeout("upload1()",1000);
			}
}




function upload1(){
	if(i<data1.length){
	
	
								
					
//-------2-----unzip--------------------------------------------------------------------------------------------
						$('div#div1').append("——————————————开始更新"+data1[i]['name']+"  版本包——————————————<br/>");
						$('label#lab').html("当前更新更新版本："+data1[i]['name']);
						$('div#div1').append("正在解压"+data1[i]['name']+" 版本包...<br/>");
						div1.scrollTop=div1.scrollHeight;
	//222					
								$.ajax({
								url:'../UpLoadServlet',
								type:'POST',
								async: false,
								data: 
								{op:'unzip',
								 path:data1[i]['file'],
								 name:data1[i]['name']
								},
								dataType: "json",
								success:function(data, textStatus, XMLHttpRequest){
									data2 = eval(data);
								}
								
		
							});
									if(data2[0]['res']=='e'){
									$('div#div1').append("<span style=\"color:#F00\">"+data1[i]['name']+" 版本包解压失败！</span><br/>");
									div1.scrollTop=div1.scrollHeight;
									$('div#div1').append("<span style=\"color:#F00\">请检查版本包物理文件是否被删除！然后重新操作！</span><br/>");
									div1.scrollTop=div1.scrollHeight;
									$("#button").attr("disabled",false);
									i=data1.length;
									}else{
										
									$('div#div1').append(data1[i]['name']+" 版本包解压完毕<br/>");
									div1.scrollTop=div1.scrollHeight;
									
												
//--------3--------loadcsv----------------------------------------------------------------------------------------
												$('div#div1').append(" 正在加载配置文件...<br/>");
												
												div1.scrollTop=div1.scrollHeight;
													$.ajax({
															url:'../UpLoadServlet',
															type:'POST',
															async: false,
															data:
															{op:'loadcsv'
															},
															dataType: "json",
															success:function(data, textStatus, XMLHttpRequest){
																	data5 = eval(data);
																}
													});
																if(data5[0]['res']=='e'){
																$('div#div1').append("<span style=\"color:#F00\"> 加载配置文件时出错!已停止更新！</span><br/>");
																div1.scrollTop=div1.scrollHeight;
																$("#button").attr("disabled",false);
																i=data1.length;
																}else if(data5==null){
																$('div#div1').append("<span style=\"color:#F00\"> 读取配置文件失败!</span><br/>");
																div1.scrollTop=div1.scrollHeight;
																$('div#div1').append("<span style=\"color:#F00\"> 请检查配置文件是否被删除！然后重新操作！</span><br/>");
																div1.scrollTop=div1.scrollHeight;
																$("#button").attr("disabled",false);
																i=data1.length;
																}else{
																$('div#div1').append(" 加载配置文件完毕!<br/>");
																div1.scrollTop=div1.scrollHeight;
//--------4------insertxml----------------------------------------------------------------------------------------------------
																	$('div#div1').append(" 开始更新表数据...<br/>");
																	div1.scrollTop=div1.scrollHeight;
																	for (var j = 0; j < data5.length;j++){
																		
																		$('div#div1').append("开始更新表"+data5[j]['tablename']+"...<br/>");
																		div1.scrollTop=div1.scrollHeight;
																			//alert(data5[j]['tablename']);
																	$.ajax({
																			url:'../UpLoadServlet',
																			type:'POST',
																			async: false,
																			data: 
																			{op:'insertxml',
																			 table:data5[j]['tablename'],
																			 uprow:data5[j]['uprow']
																			},
																			
																			dataType: "json",
																			success:function(data, textStatus, XMLHttpRequest){
																					data6 = eval(data);
																				}
																			
																	 });
																	if(data6[0]['res']=='e'){
																					$('div#div1').append("<span style=\"color:#F00\"> 更新表"+data5[j]['tablename']+"时出错！已停止更新！</span><br/>");
																					div1.scrollTop=div1.scrollHeight;
																					$("#button").attr("disabled",false);
																					i=data1.length;
																					j=data5.length;
																				}else if(data6[0]['res']=='fale'){
																					$('div#div1').append("<span style=\"color:#F00\"> 加载"+data5[j]['tablename']+".xml文件失败！已停止更新！</span><br/>");
																					div1.scrollTop=div1.scrollHeight;
																					$('div#div1').append("<span style=\"color:#F00\"> 请检查文件是否已被删除！然后重新操作！</span><br/>");
																					div1.scrollTop=div1.scrollHeight;
																					$("#button").attr("disabled",false);
																					i=data1.length;
																					j=data5.length;
																				}else{
																					$('div#div1').append("表"+data5[j]['tablename']+"更新完毕<br/>");
																					
																					div1.scrollTop=div1.scrollHeight;
																					if(j==data5.length-1){
																						
																						$('div#div1').append("——————————————"+data1[i]['name']+"版本更新完毕——————————————<br/>");
																						if(i==data1.length-1){
//--------5------del------------------------------------------------------------------------------------------------------------
																						$.ajax({
																								url:'../UpLoadServlet',
																								type:'POST',
																								async: false,
																								data: 
																								{op:'del'
																								},
																								dataType: "json",
																								
																								success:function(data, textStatus, XMLHttpRequest){
																										data7 = eval(data);
																									},
																									complete : function(XMLHttpRequest, textStatus){
																									if(data7[0]['res']=='fale'){
																										$('div#div1').append("<span style=\"color:#F00\"> 版本状态写入出错！</span><br/>");
																										div1.scrollTop=div1.scrollHeight;
																										$("#button").attr("disabled",false);
																										
																										
																									}else{
																										$('div#div1').append("＝＝＝＝＝＝＝＝ 所有版本更新完毕 ＝＝＝＝＝＝＝＝<br/>");
																										div1.scrollTop=div1.scrollHeight;
																							$("#button").attr("disabled",false);
																							alert("更新已全部完成！");
																							i=data1.length;
																							//location.reload();
																									}
																							
																										
																									
																									}
																							});	
																						}

																							
																					}
																				
																				}
															
															}
													}
									}
	
			i++;
		setTimeout("upload1()",2000);
	}
}

*/
//跳转到数据更新页面
function submitData(){
		window.location.href="update_list.jsp";
}


</script>



</head>

<body>


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
						待更新数据</span></td>
					</tr>
				</table>
				</td>
				<td>
				<div align="right">
					<div style="background:url(../public/images/update.gif); text-align: left; margin:0 5px; width:50px;height:17px;cursor:pointer;" onClick="submitData()">
					<div style="font-size: 12px;margin-left:15px;padding-top:2px;padding-top:2px;display:block;position:relative;">返&nbsp;回</div>
				</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>


</table>
<table width="100%" id="users" class="users" border="0" cellpadding="0"
	cellspacing="1" bgcolor="#a8c7ce">
	<tbody id="tbody">
		<tr>
			<td width="28%" height="24" bgcolor="d3eaef" class="STYLE6">
			<div align="center"><span class="STYLE10">版本号</span></div>
			</td>
			<td width="72%" height="24" bgcolor="d3eaef" class="STYLE6">
			<div align="center"><span class="STYLE10">更新包</span></div>
			</td>
		</tr>
		<%
			 DatabaseClass db = LcpUtil.getDatabaseClass();
			 String sql = "select download_name,download_file from dcp_syn_download where sys_is_del = 0 order by download_name";
			 DataSetClass dataSet = db.FunGetDataSetBySQL(sql);
			 for (int i = 0; i < dataSet.FunGetRowCount(); i++) {
				 String  download_name = dataSet.FunGetDataAsStringById(i,0);
				 String  download_file = dataSet.FunGetDataAsStringById(i,1);
 
 %>
		<tr bgcolor="#FFFFFF" height="20" class="STYLE19" >
			<td height="20">
			<div align="center"><span class="STYLE10"><%=download_name%></span></div>
			</td>
			<td>
			<div align="center"><span class="STYLE10"><%=download_file%></span></div>
			</td>
		</tr>
		<%} %>
	</tbody>
</table>
<div align="left">
  <input type="submit" name="button" id="button" value="更新"
	style="height: 30px; width: 80px; font-size: 16px" onClick="upload()">
    <label id="lab" style="font-size:16px"></label>
</div>
<div id="div1" align="left" style="border-style: Double; border-width: 3pt; border-color: #C1C1C1; height: 400px; width: auto; overflow-y: scroll"></div>
</body>
</html>