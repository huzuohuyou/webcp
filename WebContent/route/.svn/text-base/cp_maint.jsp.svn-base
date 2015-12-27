<%
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：cp_maint.jsp  
// 文件功能描述：本地临床路径维护浏览页
// 接收参数: cp_id   
// 创建人：刘植鑫
// 创建日期：2011-8-5
// 修改日期：2011-8-5
// 完成日期：
//
// 添加编辑删除功能
// 添加人：潘状
//
//----------------------------------------------------------------*/ 
%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../frames/power.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="../public/plugins/jquery/themes/cupertino/jquery-ui-1.8.11.custom.css"> 
	<script src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.core.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.tabs.js"></script>
	<script src="../public/plugins/jquery/external/jquery.bgiframe-2.1.2.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.mouse.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.button.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.draggable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.position.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.resizable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.dialog.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.effects.core.js"></script>
	<script type="text/javascript" src="../public/plugins/jquery/jquery.acts_as_tree_table.js"></script>
<title>本地路径信息查看</title>
<style type="text/css">
<!--

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	
	}
#tabs { height: 200px; } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 650px; overflow: auto;} 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0em 0em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -0px !important; margin-bottom: 0px !important; border-top: none; border-bottom-width: 0px; }
	.ui-tabs-selected { margin-top: -3px !important; }
.STYLE10 {color: #000000; font-size: 12px;font-weight:normal; }
.tabheightandwidge{
	height: 600px;
}

th{
	font-size=12px;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.ui-dialog { position: absolute; padding: .0em; width: 300px; overflow: hidden; }
.ui-dialog .ui-dialog-titlebar { padding: .1em .1em; position: relative;  }
.ui-dialog .ui-dialog-title { float: left; margin: .1em 16px .1em 0; } 
.ui-dialog .ui-dialog-titlebar-close { position: absolute; right: .3em; top: 50%; width: 19px; margin: -10px 0 0 0; padding: 1px; height: 18px; }
.ui-dialog .ui-dialog-titlebar-close span { display: block; margin: 1px; }
.ui-dialog .ui-dialog-titlebar-close:hover, .ui-dialog .ui-dialog-titlebar-close:focus { padding: 0; }
.ui-dialog .ui-dialog-content { position: relative; border: 0; padding: .1em 0.1em; background: none; overflow: auto; zoom: 1; }
.ui-dialog .ui-dialog-buttonpane { text-align: left; border-width: 1px 0 0 0; background-image: none; margin: .5em 0 0 0; padding: .3em 1em .5em .4em; }
.ui-dialog .ui-dialog-buttonpane .ui-dialog-buttonset { float: right; }
.ui-dialog .ui-dialog-buttonpane button { margin: .5em .4em .5em 0; cursor: pointer; }
.ui-dialog .ui-resizable-se { width: 14px; height: 14px; right: 3px; bottom: 3px; }
.ui-draggable .ui-dialog-titlebar { cursor: move; }
-->
</style>

<script>
var cp_id_quanju="";
var cp_state_quanju="";
var pageNo_quanju="1";
var bianma_quanju="";
var mingcheng_quanju="";
var pinyin_quanju="";
var wubi_quanju="";
var url="../CpMaintainServlet";
var operate="getViewTable";
var async=true;

var  highlightcolor='#d5f4fe';
var  clickcolor='#51b2f6';
var recoveryColor='#FFFFFF';
var bgcolor="#51b2f6";
var tempColor="";


//行变色函数
function changeColor(event){
	tempColor=event.bgColor;
	event.bgColor=highlightcolor;
	}

function recoverColor(event){
	event.bgColor=tempColor;
	tempColor=recoveryColor;
	}


//单击选择节点,行变色,加载右侧信息	
function onclickColor(event){
	var trs=$("tr.STYLE10");
	var trsleng=trs.length;
	for(var i=0;i<trsleng;i++){
		var tr=trs[i];
		tr.bgColor=recoveryColor;
	}
	event.bgColor=clickcolor;
	tempColor=event.bgColor;
	
	cp_id_quanju=(event.id).split("_")[1];
}

function ondblclickLoad(event){
	var cpID=(event.id).split("_")[1];
	showDeital(cpID);
}
$(function() {
	DBOperation();
	$( "#showCpDetail" ).dialog({
		title:"路径信息",
		autoOpen: false,
		modal: true,
		height:600,
		width:1000,
		minHeight:600,
		minWidth:1000,
		resizable:false,
		draggable:true/* ,
		close: function(event, ui) {
			$('#showCpDetail').html('');
			operate="getNewViewTable";
			DBOperation();
		} */
		});
	

	
	
	$( "#dialogAddCP" ).dialog({
		autoOpen: false,
		height: 400,
		width: 400,
		title:"添加信息",
		modal: true
	});
	
	
	$( "#createCP" ).click(function(){//创建路径
		$( "#dialogAddCP" ).dialog("open");
		$( "#dialogAddCP" ).dialog({
			buttons: {
				"创建路径": function() {
					var name=$("#cp_name").val();
					$.ajax({	   
						url : "../servlet/managecp",
						type : 'POST',
						data : {
							op : "addCP",
							cp_name : encodeURI($("#cp_name").val(),"utf-8"),
							min_day:$("#min_day").val(),
							max_day:$("#max_day").val(),
							avg_day:$("#avg_day").val()	,
							avg_fee:$("#avg_fee").val()	,
							dept_name:encodeURI($("#dept_name").val(),"utf-8")	
						},
						dataType : "json",
						complete :show_result ,
						success :function(data, textStatus, XMLHttpRequest) {
							data = eval(data);
							data = data[0];
							 if (data.result === "OK") {
								 alert(data.id);
								   $('#showCpDetail').html('<iframe src=../cpmanage/manage.jsp?cp_id='+data.id+' frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');
								   var operate="getViewTable";
								   DBOperation();
								   $( "#showCpDetail" ).dialog('open');
								
							 } else			
								alert("无效操作");
						} 	});
					$( this ).dialog( "close" );			
					
				},
				"取消": function() {
					$( this ).dialog( "close" );
				}
			}
		});
		
	});
	
	$("#editCP").click(function(){
		if(cp_id_quanju!=""&&cp_id_quanju>10000){
			  $('#showCpDetail').html('<iframe src=../cpmanage/manage.jsp?cp_id='+cp_id_quanju+' frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');
			   $( "#showCpDetail" ).dialog('open');
		}else if(cp_id_quanju==""){
			alert("请选择一条进行编辑!");
		}else if(cp_id_quanju<10000){
			alert("该路径禁止编辑!");
		}
	});
	
	$("#delCP").click(function(){
		if(cp_id_quanju!=""&&cp_id_quanju>10000){
			alert(cp_id_quanju);
			 // $('#showCpDetail').html('<iframe src=../cpmanage/manage.jsp?cp_id='+cp_id_quanju+' frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');
			  // var operate="getViewTable";
			  // DBOperation();
			  // $( "#showCpDetail" ).dialog('open');
		}else if(cp_id_quanju==""){
			alert("请选择一条路径!");
		}else if(cp_id_quanju<10000){
			alert("该路径禁止删除!");
		}
	});
});
function DBOperation(){
	$.ajax({
	    url: url,
	    type: 'POST',
	    async : async,
	    data: {op : operate,
	    	pageNo_ajax : encodeURI(pageNo_quanju),
	    	cp_id_ajax : encodeURI(cp_id_quanju),
	    	bm : encodeURI(bianma_quanju),
	    	mc : encodeURI(mingcheng_quanju),
	    	py : encodeURI(pinyin_quanju),
	    	wb : encodeURI(wubi_quanju),
	    	cp_state_ajax : cp_state_quanju//启用停用路径状态
	    	},
	    dataType: "json",
	    complete: show_result,
	    success: onDataReceived
	  });
}
var msg = "";
var show_result = function(XMLHttpRequest, textStatus){
	
	if(textStatus == "error"){
	 	msg = "请求出错！";
	 	ajaxIsSuc=false;
	 	alert(msg);
	}
	else if(textStatus == "timeout"){
	 msg = "请求超时！";
	 ajaxIsSuc=false;
	 alert(msg);
	}
	else if(textStatus == "parsererror"){
	 msg = "JSON数据格式有误！";
	 alert(msg);
	 ajaxIsSuc=false;
	}else if (textStatus != "success"){
	 ajaxIsSuc=false;
	 msg = "失败";
	 alert(msg);
	}else if(textStatus == "success"){
	 ajaxIsSuc=true;
	}
	
  
};
var onDataReceived = function(data, textStatus, XMLHttpRequest){
	data = eval(data);
	data = data[0];
		if(data.method==="getViewTable"){
			if(data.result==="OK"){
				//alert(data.table);
				$("#viewTable").html("");
				$("#viewTable").html(data.table);
				$("#PageTable").html(data.pageHtml);
			}else{
				alert("没有对应的路径信息");
			}
		}
		if(data.method==="startOrEndCpView"){
			if(data.result==="OK"){
				$("#tr_"+cp_id_quanju).html(data.table);
				alert("操作成功");
			}else{
				alert("操作失败");
			}
		}
		if(data.method==="getNewViewTable"){
			$("#tr_"+cp_id_quanju).html(data.table);
		}
};
function tiaozhuan(aa){
	var auto=$(":radio[name='zccx'][checked]").val();
	var context$=$("#chaxuntext");
	var context_0=context$[0].value+"";
	if(auto=="1"){
		bianma_quanju=context_0;
		}
	if(auto=="2"){
		mingcheng_quanju=context_0;
		}
	if(auto=="3"){
		pinyin_quanju=context_0;
		}
	if(auto=="4"){
		wubi_quanju=context_0;
		}
	
	operate="getViewTable";
	pageNo_quanju=aa;
	DBOperation();	
	bianma_quanju="";
	mingcheng_quanju="";
	pinyin_quanju="";
	wubi_quanju="";

}
function tiaozhuanzhiding(){
	var auto=$(":radio[name='zccx'][checked]").val();
	var context$=$("#chaxuntext");
	var context_0=context$[0].value+"";
	if(auto=="1"){
		bianma_quanju=context_0;
		}
	if(auto=="2"){
		mingcheng_quanju=context_0;
		}
	if(auto=="3"){
		pinyin_quanju=context_0;
		}
	if(auto=="4"){
		wubi_quanju=context_0;
		}
	
	var _pageNo=$("#tiaozhuan_daohang");
	_pageNo=_pageNo[0].value;
	pageNo_quanju=_pageNo;
	operate="getViewTable";
	DBOperation();	
	bianma_quanju="";
	mingcheng_quanju="";
	pinyin_quanju="";
	wubi_quanju="";
}
function chaxun(){
	var auto=$(":radio[name='zccx'][checked]").val();
	var context$=$("#chaxuntext");
	var context_0=context$[0].value+"";
	if(auto=="1"){
		bianma_quanju=context_0;
		}
	if(auto=="2"){
		mingcheng_quanju=context_0;
		}
	if(auto=="3"){
		pinyin_quanju=context_0;
		}
	if(auto=="4"){
		wubi_quanju=context_0;
		}
	
	pageNo_quanju=1;
	operate="getViewTable";
	DBOperation();	
	bianma_quanju="";
	mingcheng_quanju="";
	pinyin_quanju="";
	wubi_quanju="";
}
function cp_state_op_view(id,obj){
	cp_id_quanju=id;
	var OKNO=false;
		if(obj==1){
			OKNO =confirm("确定要停用当前路径吗？");
		}else{
			OKNO =confirm("确定要启用当前路径吗？");
		}
		if(OKNO){
			operate="startOrEndCpView";
			cp_state_quanju=obj;
			DBOperation();
		}
	}
	function showDeital(cpid){
		cp_id_quanju=cpid;
		 $('#showCpDetail').html('<iframe src="cp_maint_detail.jsp?cp_id='+cp_id_quanju+'" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');

		$( "#showCpDetail").dialog( "open" );
	}
</script>
</head>

<body >
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      		<tr>
        		<td height="24" bgcolor="#353c44">
        			<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          			<tr>
	            			<td height="23">
	            				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					              <tr>
					                <td width="6%" height="19" valign="bottom"><div align="center"><img src="../public/images/tb.gif" width="14" height="14" /></div></td>
					                <td width="94%" valign="bottom"><span class="STYLE1"> 路径维护信息列表</span></td>
					              </tr>
	            				</table>
	            			</td>
	            
			        		<td><div align="right"><span class="STYLE1"> 搜索&gt;&gt; 按
			        			<input name="zccx" type="radio" value="1" checked="checked"/>编码
						        <input type="radio" name="zccx" value="2"/>名称
						        <input type="radio" name="zccx" value="3"/>拼音
						        <input type="radio" name="zccx" value="4"/>五笔
						        <input type="text" id="chaxuntext" oninput="chaxun();" onpropertychange="chaxun();"/>
						        </span></div>
			        		</td>
			        		<td width="40%" align="center"><input type="button" id="createCP" value="创建路径"/>
			        		<input type="button" id="editCP" value="编辑路径" />
	<input type="button" id="delCP" value="删除路径"/>
    </td><td style="font-size:12px">
	<input type="checkbox" name="checkLocal" id="checkLocal">自定义路径</td>
	          			</tr>
        			</table>
        		</td>
      		</tr>
    	</table>
        <table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" style=" border-style:solid; border-width:0; border-color:#e1e1e1; ">
        	<tr>
        		<td width="30%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">路径名称</span></div></td>
        		<td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">路径编码</span></div></td>
        		<td width="17%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">路径版本</span></div></td>
        		<td width="17%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">使用权限</span></div></td>
        		<td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">状态</span></div></td>
        		<td width="*" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
        	</tr>
        	<tbody id="viewTable" style="width: 100%;">        	
        	</tbody>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      	<tbody id="PageTable">
	     
	      	</tbody>
	    </table>
	    <div id="showCpDetail"></div>
	    
	    <div id="dialogAddCP" style="background: #FFF;font-size:14px;">
    <table width="369" height="238" border="0xp" align="center" cellspacing="0">
      <tr>
        <td height="32" align="right">路径名称：</td>
        <td><input type="text" name="txt112" id="cp_name" tip="不能为空" reg="^+$" />*
        <label for="radiobutton"></label></td>
      </tr>
      <tr>
        <td height="32" align="right">路径编码：</td>
        <td><input type="text" id="dept_local_code"/></td>
      </tr>
      <tr>
        <td width="144" height="32" align="right" >最少住院日：</td>
        <td width="221"><label><input name="txt11" type="text" id="min_day" maxlength="3" />
        </label></td>
      </tr>
      <tr>
        <td height="28" align="right">最大住院日： </td>
        <td width="221"><label><input name="txt6" type="text" id="max_day" maxlength="3" />
      </label>      </tr>
      <tr>
        <td height="29" align="right">平均住院日：</td>
        <td><input name="txt6" type="text" id="avg_day" maxlength="3" /></td>
      </tr>
      <tr>
        <td height="30" align="right">平均费用：</td>
        <td><input type="text" name="txt6" id="avg_fee" /></td>
      </tr>
      <tr>
        <td height="29" align="right">科室名称：</td>
        <td><input type="text" name="txt13" id="dept_name"  />
       </td>
      </tr>
  </table>
</div>
</body>
</html>
