<%
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：execute.jsp  
// 文件功能描述：本地临床路径维护浏览页
// 接收参数:    
// 创建人：潘状
// 创建日期：

// 添加flag参数，用于角色
// 修改日期：2011-08-26

// 增加取消签名功能
// 修改人：韩金杰
// 修改日期：2013-03-01
//----------------------------------------------------------------*/ 
%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil" %>
<%
String patientID=request.getParameter("patient_no");
String userID=request.getParameter("user_id");
String userName=request.getParameter("user_name");
String flag=request.getParameter("flag");
String patient_no_emr=request.getParameter("patient_no_emr");
String admissTimes=request.getParameter("admissTimes");
String doctorNo=request.getParameter("doctorNo");
String patientArea=request.getParameter("patientArea");
String doctorQX=request.getParameter("doctorQX");
String babyStatus="0";
//
//
//	flag说明	1：医生
//			2：护士
//			3:两个都显示
//
///LcpProject_二期/WebContent/route/execute.jsp
//
String CPID="";
String cp_state = "";
patientArea = patientArea.trim();
if(patientArea.equals("1130100")){
	response.sendRedirect("../route/error2.html"); 
}

if(patientID==null||"".equals(patientID)){
	response.sendRedirect("../route/error.html"); 
}else{
	DatabaseClass databaseClass=LcpUtil.getDatabaseClass(); 
	//String sql="SELECT T.* FROM lcp_patient_node T WHERE T.PATIENT_NO='"+patientID+"'";
	String sql = "select t.cp_state from lcp_patient_visit t where t.patient_no = '"+patientID+"'";
	DataSetClass dataSetClass=databaseClass.FunGetDataSetBySQL(sql,1);
	int row=dataSetClass.FunGetRowCount();
	if(row<=0){
		response.sendRedirect("../route/error.html"); 
	}else{
		//CPID=dataSetClass.FunGetDataAsStringByColName(0,"CP_ID");
		cp_state=dataSetClass.FunGetDataAsStringByColName(0,"CP_STATE");
	}
}
%>
<%@ page contentType="text/html; charset=utf-8" language="java"  errorPage="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>临床路径执行页面</title>
<link rel="stylesheet" href="../public/plugins/jquery/themes/base/jquery.ui.all.css" />
	<link rel="stylesheet" href="../public/plugins/jquery/demos/demos.css" />
	<script src="../public/plugins/jquery/jquery-1.5.1.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.core.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.tabs.js"></script>

	<script src="../public/plugins/jquery/external/jquery.bgiframe-2.1.2.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.mouse.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.draggable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.position.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.resizable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.dialog.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.effects.core.js"></script>
	
<style type="text/css">
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE6 {color: #000000; font-size: 12; }

.selectNode {color: #000000; font-size: 12; }
.STYLE2 {color: #0000cc; font-size: 12px; }


.STYLE11 {color: #000000; font-size: 12px; font-weight: bold; }

body{
	background: #ffffff;
	font: normal 12px "SimSun"; 
	margin: 1px;
	padding: 0;
}

table.list1 { width: 100%; margin: 2px auto 0; border: solid 1px #E8E8E8; border-collapse: collapse; color: #000; }
table.list1 th { border: solid 1px #E8E8E8; height: 26px;  font-size: 12px; color: #686868; overflow-x:hidden; background: url(../public/images/bg_tab_head.png) repeat-x;}
table.list1 td { border: solid 1px #E8E8E8; height: 26px; word-break:keep-all; padding-left:5px; font-size: 12px; color:#000000; font-family: inherit;}
</style>
 <script>
 	var async=false;
 	var highlightcolor='#d5f4fe';
 	var recoveryColor='#ffffff';
 	var clickcolor='#51b2f6';
 	var tempColor='#ffffff';
 	var tables = new Array("nodeTbody","nodeDaysTbody","doctorTable","orderTable","nurseTable","variationTable");
 	var ajax=false;
 	var trEditStr="";//存储当前所有可编辑行的ID值
 	var secTrStr="";//所有符合颜色的行ID值
 	var secCheckStr="";//所有勾选了执行的行ID值
 	var operate="";//当前操作表
 	var para="";//传到后台参数
 	var async=false;//异步同步
 	var url="../NodeExecute";//当前servlet
 	var fun="";
 	var addSuc=false;
 	var isOpenDiagNextNode=false;
 	var patientID_js="";
 	var  userID_js="";
 	var  userName_js="";
 	var variationCode_js="";//路径节点变异编码
 	var m_exitNode="";//保存变异退出节点编码
 	var cp_id_quanju="";
 	var cp_node_id_quanju="";
 	var existNode_quanju="";//变异退出节点，全局使用
 	var flag_js="<%=flag%>";
 	var next_Node_id_quanju="";
 	var state = <%=cp_state%>;
 	function NodeColor(event){
 		var trs=$(".selectNode");
 		var trsleng=trs.length;
 		for(var i=0;i<trsleng;i++){
 			var tr=trs[i];
 			tr.bgColor=recoveryColor;
 		}
 		event.bgColor=clickcolor;
 		tempColor=event.bgColor;
 		//alert(event.id);
 		
 			operate="changeNode";
 			para="cp_node="+event.id;
 			cp_node_id_quanju=event.id;
 			url="../NodeExecute";
 			async=true;
 			
 			DBOption();
 		
 		
 	}
	/**
	* 删除右侧表格中的所有内容  lzx 2011-06-01
	*/
	function delRightTable(){
		$("#doctorTable").html("");
		$("#orderTable").html("");
		$("#variationTable").html("");
		$("#nurseTable").html("");
		$("#familyTable").html("");
	}
	/**
	* 删除右侧表格中的所有内容  lzx 2011-06-01
	*/
	function delAllTable(){
		$("#nodeTbody").html("");
		delRightTable();
	}
	/**
	  * 2011-04-25
	  *	函数说明：数据库操作
	  */
	function DBOption(){
		 $("#wait").css("display","");
	 	$.ajax({
	 	    url: url,
	 	    type: 'POST',
	 	    async: async,
	 	    data: {op : operate,
	 	    	para:para,
	 	    	execute:true,
	 	    	fun : fun,
	 	    	patientID : patientID_js,
	 	    	userID : userID_js,
	 	    	userName : userName_js,
	 	    	variationCode:variationCode_js
	 	    	
	 	    	},
	 	    dataType: "json",
	 	    complete: show_result,
	 	    success: onDataReceived
	 	  });
	}
	function DBOption1(){
		 $("#wait").css("display","");
	 	$.ajax({
	 	    url: "../InsertTable",
	 	    type: 'POST',
	 	    async: async,
	 	    data: {op :	"insertAll",
	 	    	para: "tr"+existNode_quanju,
	 	    	execute:true,
	 	    	fun : fun,
	 	    	patientID : patientID_js,
	 	    	userID : userID_js,
	 	    	userName : userName_js,
	 	    	variationCode:variationCode_js
	 	    	
	 	    	},
	 	    dataType: "json",
	 	    complete: show_result,
	 	    success: onDataReceived
	 	  });
	}
	 /**
	  * 2011-04-25
	  *	函数说明：ajax操作完成调用此函数
	  */
	var show_result = function(XMLHttpRequest, textStatus){
	 	var msg = "";
	 	if(textStatus == "error"){
	 	 	msg = "请求出错！";
	 	}
	 	else if(textStatus == "timeout"){
	 	 msg = "请求超时！";
	 	}
	 	else if(textStatus == "parsererror"){
	 	 msg = "JSON数据格式有误！";
	 	}
	 	if (textStatus != "success"){
	 		ajax=false;
	 		alert(msg);
	 	}
	   
	};
	 /**
	  * 2011-06-03
	  *	函数说明：ajax操作成功调用此函数
	  */
	var onDataReceived = function(data, textStatus, XMLHttpRequest){
		 data = eval(data);
	 	data = data[0];
	 	ajax=true;
	 	//取得所有表格后的操作
		if(data.method==="getAllTable"){
			if(data.result==="OK"){
				$("#wait").hide();
				delAllTable();
				$("input[name^='bottomButton']").css("display","");
				$("#nodeTbody").html(data.nodeTableHtml);
				$("#nodeDaysTbody").html(data.nodeDaysTableHtml);
				$("#doctorTable").html(data.doctorTableHtml);
				$("#orderTable").html(data.orderPointTableHtml);
				//$("#orderTable1").html(data.orderItemTableHtml);
				$("#variationTable").html(data.variationTableHtml);
				$("#nurseTable").html(data.nurseTableHtml);
				$("#familyTable").html(data.familyTableHtml);
				$("#topTbody").html(data.tophtml);
				cp_id_quanju=data.cp_id;
				cp_node_id_quanju=data.cp_node_id;
				next_Node_id_quanju=data.next_Node_id;
				existNode_quanju=data.existNode;
				var $tr=$("tr[name^=changeColor]");
				$tr.bind("click",onclickColor);
				if(data.isHaveAddButton==="true"){
					if(flag_js==1){//医生
						$("input[name='bottomButtonNurse']").css("display","none");
					}else if(flag_js==2){
						$("input[name='bottomButtonDoctor']").css("display","none");
						$("input[name='bottomButtonOrder']").css("display","none");
					}else if(flag_js==3){
						$("input[name^='bottomButton']").css("display","");
					}
				}else{
					$("input[name^='bottomButton']").css("display","none");
				}
				var inputs$=$("input[name^='xxxyjd']");
				if(data.nodeExecuteRight==="1"){
					inputs$.unbind("click");
					inputs$.bind("click",executeNextNode);
					inputs$[0].value="执行下一节点";
					$("#lutcan").css("display","");
				}else if(data.nodeExecuteRight==="2"){
// 					inputs$.unbind("click");
// 					inputs$.bind("click",executeNextNode);
// 					inputs$[0].value="点击完成路径";
					executeNextNode();
				}else{
					inputs$.unbind("click");
					inputs$[0].value="已完成";
					$("#lutcan").css("display","none");
					state = "11";
				}
/* 				if(cp_node_id_quanju==0){
					if(confirm("当前是准入节点，是否进入第一天")){
						operate="insertAll";
						url="../InsertTable";
						para="tr"+next_Node_id_quanju;

						DBOption();
						$("#wait").show();
						
						
						
					}
				} */
				
			}else{
				$("#wait").hide();
				alert("加载失败");
				
			}
			 ajax=false;
			
		}
		//点击查看其他表格后的操作
			if(data.method==="changeAllTable"){
				if(data.result==="OK"){
					delRightTable();
					$("#nodeDaysTbody").html(data.nodeDaysTableHtml);
					$("#doctorTable").html(data.doctorTableHtml);
					$("#orderTable").html(data.orderPointTableHtml);
					//$("#orderTable1").html(data.orderItemTableHtml);
					$("#variationTable").html(data.variationTableHtml);
					$("#nurseTable").html(data.nurseTableHtml);	
					$("#familyTable").html(data.familyTableHtml);
					
					var $tr=$("tr[name^=changeColor]");
					$tr.bind("click",onclickColor);
					if(data.isHaveAddButton==="true"){
						if(flag_js==1){//医生
							
							$("input[name='bottomButtonDoctor']").css("display","");
							$("input[name='bottomButtonOrder']").css("display","");
							$("input[name='bottomButtonFamily']").css("display","");
							$("input[name='bottomButtonVariation']").css("display","");
						}else if(flag_js==2){
							$("input[name='bottomButtonNurse']").css("display","");
							$("input[name='bottomButtonFamily']").css("display","");
							$("input[name='bottomButtonVariation']").css("display","");

						}
						else if(flag_js==3){
							$("input[name^='bottomButton']").css("display","");
						}
					}else{
						$("input[name^='bottomButton']").css("display","none");
					}
				}else{
					alert("加载失败");
				}
				$("#wait").hide();
			}
		//签名后的操作
			if(data.method==="signTable"){
				if(data.result==="OK"){
					operate="getTable";
			  	  	url="../NodeExecute";
					fun="sign";
					async=false;
					if(data.table==="Doctor"){
				  	  	para="Doctor";
				  	  	DBOption();
					}
					if(data.table==="Nurse"){
				  	  	para="Nurse";
				  	  	DBOption();
					}
					if(data.table==="Family"){
				  	  	para="Family";
				  	  	DBOption();
					}
					if(data.table==="Order"){
				  	  	para="Order";
				  	  	DBOption();
					}
					if(data.table==="Variation"){
				  	  	para="Variation";
				  	  	DBOption();
					}
				}else{
					alert("签名失败");
				}
				$("#wait").hide();
			}
			//签名后更新表后的操作
			if(data.method==="changeOneTable"){
				if(data.result==="OK"){
					if(data.fun==="sign"){
					}
					if(data.fun==="add"){
						addSuc=true;
					}
					if(data.table==="Doctor"){
						$("#doctorTable").html(data.html);
						var $tr=$("tr[name^=changeColorDoctor]");
						$tr.bind("click",onclickColor);
					}
					if(data.table==="Order"){
						$("#orderTable").html(data.html);
						$("#orderTable1").html(data.orderItem);
						var $tr=$("tr[name^=changeColorOrder]");
						$tr.bind("click",onclickColor);
					}
					if(data.table==="Family"){
						$("#familyTable").html(data.html);
						var $tr=$("tr[name^=changeColorFamily]");
						$tr.bind("click",onclickColor);
					}
					if(data.table==="Nurse"){
						$("#nurseTable").html(data.html);
						var $tr=$("tr[name^=changeColorNurse]");
						$tr.bind("click",onclickColor);
					}
					if(data.table==="Variation"){
						$("#variationTable").html(data.html);
						var $tr=$("tr[name^=changeColorVariation]");
						$tr.bind("click",onclickColor);
					}
				}else{
					alert("添加或签名后更新表失败");
				}
				$("#wait").hide();
			}
			//添加数据到数据库后的操作
			if(data.method==="InsertOneRecord"){
				if(data.result==="OK"){
					if(data.table==="variation"){
						operate="getTable";
				  	  	url="../NodeExecute";
				  	  	fun="add";
				  	  	para="Variation";
				  	  	async=false;
				  	  	DBOption();
	 				}
				}else{
					alert("添加失败");
				}
				$("#wait").hide();
			}
			//执行下一节点显示表格
			if(data.method==="nextNodeTable"){
				if(data.result==="OK"){
					if(data.flag==="1"){					
						isOpenDiagNextNode=true;
						$("#nextNodeTable").html(data.html);
					}
					if(data.flag==="3"){
						isOpenDiagNextNode=false;
						operate="getAllTable";
					 	url="../NodeExecute";
					 	async=false;
					 	DBOption();
					}
					if(data.flag==="5"){
						isOpenDiagNextNode=false;
						var sign=data.noSignTableName;
						var executesign=data.noExecuteFieldContext;
						alert(sign+executesign);
					}
				}else{
					alert("出现异常");
				}
				$("#wait").hide();
			}
			//执行下一节点插入数据库后的操作
			if(data.method==="InsertAllTable"){
				if(data.result==="OK"){
					if(data.variationExit==="1"){
					operate="getAllTable";
				 	url="../NodeExecute";
				 	async=false;
				 	DBOption();
					}else if(data.variationExit==="0"){
						$( "#addVariationExit" ).dialog("open");
					}
				}else{
					alert("执行下一节点插入数据库出现异常");
				}
				$("#wait").hide();
			}
			
			if(data.method==="variationExit"){
				if(data.result==="OK"){
					addSuc=true;
					operate="getAllTable";
				 	url="../NodeExecute";
				 	async=false;
				 	DBOption();
					
					
				}else{
					alert("执行下一节点插入数据库出现异常");
				}
				$("#wait").hide();
			}
			
			
			//删除变异记录后的操作
			if(data.method==="DelVariation"){
				if(data.result==="OK"){
					operate="getTable";
			  	  	url="../NodeExecute";
			  	  	fun="add";
			  	  	para="Variation";
			  	  	async=false;
			  	  	DBOption();
					alert("删除变异信息成功!");
				}else{
					alert("删除变异信息失败!");
				}
				$("#wait").hide();
			}
			
			//$("#wait").hide();
	};
//签名函数-----开始
	function sign(table){
		if (confirm("您确定是否要签名吗？")) {	
			signClick(table);
		}
	}
	function signClick(table){
		var trs=$("tr[name='changeColor"+table+"']");
		var checks=$("input[name='checkbox"+table+"']");
		var trslen=trs.length;
		var checkLen=checks.length;
		for(var i=0;i<trslen;i++){
			var tr=trs[i];
			trEditStr=trEditStr+tr.id;
			if(tr.bgColor===clickcolor){
				secTrStr=secTrStr+tr.id;
			}
		}
		for(var jj=0;jj<checkLen;jj++){
			if(checks[jj].checked){
		 		var ss=checks[jj].id;
		 		secCheckStr+=ss;
		 	}		
		}
		if(secTrStr.length>0){
	  		operate=table;
	  	  	url="../SignTable";
	  	  	async=false;
	  	  	para=trEditStr+";"+secTrStr+";"+secCheckStr;
	  	  	DBOption();
	 	}else{
	 		alert("没有符合的签名");
	 	}
		trEditStr="";
		secTrStr="";
		secCheckStr="";
	}
//签名函数-----结束
//取消函数--开始
function cancelSign(table){
		if (confirm("您确定是否要取消签名吗？")) {	
			cancelSignClick(table);
		}
	}
	function cancelSignClick(table){
		//判断误签--开始
		var isTrs=$("tr[name='changeColor"+table+"']");
		var isTrslen=isTrs.length;
		for(var i=0;i<isTrslen;i++){
			var isTr=isTrs[i];
			if(isTr.bgColor===clickcolor){
				secTrStr=secTrStr+isTr.id;
			}
		}
		if(secTrStr.length>0){
			trEditStr="";
			secTrStr="";
			secCheckStr="";
			alert("您选择了未签名项");
			operate="getTable";
	  	  	url="../NodeExecute";
			fun="sign";
			async=false;
		  	para=table;
		  	DBOption();
		}
		else{
		//判断误签--结束
		secTrStr="";
		var trs=$("tr[name='unChangeColor"+table+"']");
		var checks=$("input[name='checkbox"+table+"']");
		var trslen=trs.length;
		var checkLen=checks.length;
		for(var i=0;i<trslen;i++){
			var tr=trs[i];
			trEditStr=trEditStr+tr.id;
			if(tr.bgColor===recoveryColor){
				secTrStr=secTrStr+tr.id;
			}
		}
		if(secTrStr.length>0){
	  		operate=table;
	  	  	url="../servlet/CancelSignTable";
	  	  	async=false;
	  	  	para=secTrStr;
	  	  	DBOption();
	 	}else{
	 		alert("没有要取消的签名");
	 	}
		}
		trEditStr="";
		secTrStr="";
		secCheckStr="";
	}
//取消函数--结束
function executeNextNode(){
	operate="nextNode";
   	url="../NextNode";
   	async=false;
   	para="";
   	DBOption();
   	if(confirm("您确定要执行下一节点吗？请先做好医护配合工作并确认医生已经下医嘱！")){	
   	if(isOpenDiagNextNode){
   		$( "#nextNode").dialog( "open" );	
   	}
   	}
}
//全签函数-----开始
	function signAll(table){
		if (confirm("您确定是否要签名吗？")) {	
			signAllClick(table);
		}
	}
	function signAllClick(table){
		var trs=$("tr[name^='changeColor"+table+"']");
		var checks=$("input[name^='checkbox"+table+"']");
		var trslen=trs.length;
		var checkLen=checks.length;
		for(var i=0;i<trslen;i++){
			var tr=trs[i];
			trEditStr=trEditStr+tr.id;
		}
		for(var jj=0;jj<checkLen;jj++){
			if(checks[jj].checked){
		 		var ss=checks[jj].id;
		 		secCheckStr+=ss;		
		 	}		
		}
		secTrStr=trEditStr;
		if(secTrStr.length>0){
	  		operate=table;
	  	  	url="../SignTable";
	  	  	async=false;
	  	  	para=trEditStr+";"+secTrStr+";"+secCheckStr;
			DBOption();
	 	}else{
	 		alert("没有符合的签名");
	 	}
		trEditStr="";
		secTrStr="";
		secCheckStr="";
	}
//全签函数-----结束
//添加变异信息-----开始
function add(table){
	$( "#"+table+"" ).dialog( "open" );	
}
function addToDB(table){
	if(table==="addVariation"){
		var table=$("textarea[name^='addbynr']");
		var bynr=table[0].value;
		table[0].value="";
		operate="variation";
	   	url="../InsertOneRecord";
	   	async=false;
	   	para=bynr;
	   	para=encodeURI(para);
	   	DBOption();
	}
	if(table==="VariationExit"){
		var exitText=$("#variationExitText").val();
		fun=m_exitNode;
		operate="variationExit";
	   	url="../InsertOneRecord";
	   	async=false;
	   	para=encodeURI(exitText);
	   	DBOption();
		
	}
	if(table==="VariationExit1"){
		var exitText=$("#variationExitText1").val();
		fun=existNode_quanju;
		operate="variationExit";
	   	url="../InsertOneRecord";
	   	async=false;
	   	para=encodeURI(exitText);
	   	DBOption();
		
	}
}
//添加变异信息------结束
//删除变异信息开始
function delVaration(){
		var trs=$("tr[name='changeColorVariation']");
		var trslen=trs.length;
		for(var i=0;i<trslen;i++){
			var tr=trs[i];
			trEditStr=trEditStr+tr.id;
			if(tr.bgColor===clickcolor){
				secTrStr=secTrStr+tr.id;
			}
		}
		if(secTrStr.length>0){
	  	  	url="../DelVariation";
	  	  	async=false;
	  	  	para=trEditStr+";"+secTrStr+";";
	  	 	DBOption();
	 	}else{
	 		alert("请选择要删除的数据!");
	 	}
		trEditStr="";
		secTrStr="";
	}

//变色函数-----开始
	function changeColor(event){
		tempColor=event.bgColor;
		event.bgColor=highlightcolor;
	}
	
	function recoverColor(event){
		event.bgColor=tempColor;
		tempColor=recoveryColor;
	}
	function onclickColor(){
		if(tempColor!=clickcolor)
		{
			var aa=$(this);
			var jibie=aa.attr("jibie");
			var b=aa.attr("bianse");
			
			if(jibie==="sc"){
				var tr$=$("tr[bianse^='"+b+"'][jibie='xc']");
				tr$.attr("bgColor",clickcolor);
			}
			if(jibie==="xc"){
				var tr$=$("tr[bianse^='"+b+"'][jibie='sc']");
				tr$.attr("bgColor",clickcolor);
			}
			this.bgColor=clickcolor;
	  		tempColor=clickcolor;
	  	}
	  	else{
	  		var aa=$(this);
			var jibie=aa.attr("jibie");
			var b=aa.attr("bianse");
			if(jibie==="sc"){
				var tr$=$("tr[bianse^='"+b+"'][jibie='xc']");
				tr$.attr("bgColor",recoveryColor);
			}
			if(jibie==="xc"){
				var tr$=$("tr[bianse^='"+b+"'][jibie='sc']");
				tr$.attr("bgColor",recoveryColor);
			}
	  		this.bgColor=recoveryColor;
	  		tempColor=recoveryColor;
	  	}
	}
//变色函数-----结束	
//点击执行下一节点弹出对话框点击相应记录变色函数
function onclickOnNextNodeColor(event){
	var trs=$("tr[name^='nextNode']");
	var trsleng=trs.length;
	for(var i=0;i<trsleng;i++){
		var tr=trs[i];
		tr.bgColor=recoveryColor;
		}
		event.parentNode.bgColor=clickcolor;
		tempColor=event.parentNode.bgColor;

}
		
//点击复选框后所在行变色函数-----开始
	function changeColorByCheckbox(check){
		var checkbox=$("#"+check+"");
		var isCheck=checkbox[0].checked;
		var tr=checkbox[0];
		
		while(tr.tagName!="TR"&&tr.tagName!="tr"){
			tr=tr.parentNode;
		}
		var tr1=$(tr);
		
 	 	if(isCheck)
 	  		{		
 	 		tr1.unbind("click");
 	 		tr1[0].bgColor=clickcolor;
 	 		tempColor=clickcolor;
 	  		}
 	  	else{
 	  		tr1.bind("click",onclickColor);
  	  		tr1[0].bgColor=recoveryColor;
  	 		tempColor=clickcolor;
	  	}
	}
//点击复选框后所在行变色函数-----结束

 //点击执行按钮调用函数
function nextNodeExecute(){
	var nextNodeExecuteTr="";
	var trs=$("tr[name^='nextNodeTR']");
	var trsleng=trs.length;
	for(var i=0;i<trsleng;i++){
		var tr=trs[i];
		if(tr.bgColor===clickcolor){
			nextNodeExecuteTr=nextNodeExecuteTr+tr.id;
		}
	}
	if(nextNodeExecuteTr.length>0){
		isSignDiagNextNode=true;
		operate="insertAll";
		url="../InsertTable";
		para=nextNodeExecuteTr;
		m_exitNode=para.replace("tr","");
		if(existNode_quanju==m_exitNode){
			return false;
		}
		async=true;
		return true;
	}else{
		isSignDiagNextNode=false;
		return false;
	}
}

//页面载入函数-----开始
 	$(document).ready(function() {
 		$("#lutcan").css("display","none");
 		$("#wait").show();
 		$("#dictorOperator").css("display","none");
		$("#nurseOperator").css("display","none");
		$("#variationOperator").css("display","none");
		operate="getAllTable";
	 	url="../NodeExecute";
	 	async=true;
	 	patientID_js=encodeURI("<%=patientID%>");
	 	userID_js=encodeURI("<%=userID%>");
	 	userName_js=encodeURI("<%=userName%>");
	 	DBOption();
 		$("#tabs").tabs();
		$( "#addVariationExit" ).dialog({//变异退出信息输入对话框
			autoOpen: false,
			height: 370,
			width: 430,
			modal: true,
			open: function(){ 
				$("#variationExit").load("../Variation",{variation_type:"variationExit"});
			},
			buttons: {
				"确定": function() {
					if($("#variationExitText").attr("value")==""){
						alert("请输入变异信息");
					}
					else{
						variationCode_js=$("select[name='variationExit']").val();
						DBOption();
						addToDB("VariationExit");
						if(addSuc){
							$( this ).dialog( "close" );
							alert("添加成功");
							addSuc=false;
						}
						
					}
				},
				"取消": function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
			}
		});
		
		$( "#addVariationExit1" ).dialog({//变异退出信息输入对话框
			autoOpen: false,
			height: 370,
			width: 430,
			modal: true,
			open: function(){ 
				$("#variationExit1").load("../Variation",{variation_type:"variationExit1"});
			},
			buttons: {
				"确定": function() {
					if($("#variationExitText1").attr("value")==""){
						alert("请输入变异信息");
					}
					else{
						variationCode_js=$("select[name='variationExit1']").val();
						$("#addVariationExit1").dialog( "close" );
						async=false;
 						DBOption1();
						addToDB("VariationExit1");
						if(addSuc){
							
							alert("添加成功");
							addSuc=false;
						}

//alert(variationCode_js);
						
					}
				},
				"取消": function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
			}
		});
		
		$( "#addVariation" ).dialog({//添加变异记录信息
			autoOpen: false,
			height: 370,
			width: 430,
			modal: true,
			open: function(){ 
				$("#variationNode").load("../Variation",{variation_type:"variationNode"});
			},
			buttons: {
				"确定": function() {
					variationCode_js=$("select[name='variationNode']").val();
					addToDB("addVariation");
					if(addSuc){
						$( this ).dialog( "close" );
						alert("添加成功");
						addSuc=false;
					}
				},
				"取消": function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
			}
		});
		$( "#nextNode" ).dialog({
			autoOpen: false,
			height: 370,
			width: 430,
			modal: true,
			buttons: {
				"执行": function() {
					var isNotExist=nextNodeExecute();
					
					if(isSignDiagNextNode){			
						if(isNotExist){
							DBOption();
							$("#wait").show();
						}else{
							$( "#addVariationExit" ).dialog("open");
						}
						$(this).dialog( "close" );
						$("#nextNodeTable").html("");				
					}else{
						alert("请双击需要选择的路径节点");
					
					}
				},
				"取消": function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
			}
		});
		$( "#div1" ).dialog({
 			title:"路径信息",
 			autoOpen: false,
 			modal: true,
 			height:650,
 			width:1024,
 			minHeight:732,
 			minWidth:1082,
 			resizable:false,
 			draggable:true,
 			open: function(){
 	     //        <%-- $('#div1').html('<iframe src="../route/cp_maint_detail.jsp?cp_id='+'<%=CPID%>'+'" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>'); --%>
 	             $('#div1').html('<iframe src="../version/viewcp.jsp?cp_id='+cp_id_quanju+'" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');
 	         }
 			});
		$( "#div2" ).dialog({
 			title:"路径报表信息",
 			autoOpen: false,
 			modal: true,
 			height:700,
 			width:680,
 			minHeight:700,
 			minWidth:700,
 			resizable:false,
 			draggable:true,
 			close: function(event, ui) { $('#div2').html(''); },
 			open: function(){
 				 if(state == "1"){
 					 alert("请先完成路径才可打印！");
 	 	             $('#div2').html('<iframe src=" ../ReportEmitter?rpt=zhixingdan.brt&params=pairentno=<%=patientID%>&toolbardisplay="none"" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');
 				 }else{
 					if (confirm("您确定是否要打印表单？")) {
 					 	$.ajax({
 					 	    url: "../PrintingTimeServlet",
 					 	    type: 'POST',
 					 	    async: async,
 					 	    data: {op : "formPrintingTime",
 					 	    	patientID : patientID_js	 	    	
 					 	    	},
 					 	    dataType: "json"
 					 	  });
 	 	             $('#div2').html('<iframe src=" ../ReportEmitter?rpt=zhixingdan.brt&params=pairentno=<%=patientID%>" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');
 					}else{
 	 	 	         $('#div2').html('<iframe src=" ../ReportEmitter?rpt=zhixingdan.brt&params=pairentno=<%=patientID%>&toolbardisplay="none"" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');	
 					}
 				 }
 	         }
 			});
		$( "#div3" ).dialog({
 			title:"路径变异信息",
 			autoOpen: false,
 			modal: true,
 			height:500,
 			width:780,
 			minHeight:500,
 			minWidth:700,
 			resizable:false,
 			draggable:true,
 			close: function(event, ui) { $('#div3').html(''); },
 			open: function(){
 	             $('#div3').html('<iframe src=" ../service/varia.jsp?pa_id='+patientID_js+'&no_id='+cp_node_id_quanju+'&cp_id='+cp_id_quanju+'" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');
 	         }
 			});
		
  });
 		
function chakan(){
	$( "#div1").dialog( "open" );	
}
function chakanbaobiao(){
	$( "#div2").dialog( "open" );	
}
//页面载入函数-----结束

//将变异信息类型 放到变异信息内容当中
function varation(name){
	$("#"+name+"Text").attr("value","["+$("select[name='"+name+"'] option[selected]").text()+"]");
	
}

//全选/反选
var check=true;
var tableName="";
//逻辑出错，当点击全选按钮的时候，自动签名项不应该打钩，并且打钩的所在行颜色不变蓝，导致无法签名、点击取消
//的时候，已经签名执行的勾也被去掉了，不符合逻辑
//已修改 修改人 ：刘植鑫 2011-08-15
function selectAllCheckbox(obj)
    {   
    	if(tableName==obj){
    		check=!check;
    	}else{
    		check=true;
    	}
    	if(check){
    		var checkboxs$=$("[name^='checkbox"+obj+"']");//全选
		     var len=checkboxs$.length;
		     for(var i=0;i<len;i++){
		    	 var checkbox=checkboxs$[i];
		    	 if(checkbox.disabled==false){
		    		 $(checkbox).attr("checked","true");
		    		
		    		 var tr$=$(checkbox).parent();
		    		 while(tr$[0].nodeName!="tr"&&tr$[0].nodeName!="TR"){
		    			 tr$=$(tr$[0]).parent();
		    		 }
		    		 tr$[0].bgColor=clickcolor;
		    		 tr$.unbind("click");
		    	 }
		     }
    	}else {
    		 var checkboxs$=$("[name^='checkbox"+obj+"']");//全不选
		     var len=checkboxs$.length;
		     for(var i=0;i<len;i++){
		    	 var checkbox=checkboxs$[i];
		    	 if(checkbox.disabled==false){
		    		 $(checkbox).removeAttr("checked");
		    		
		    		 var tr$=$(checkbox).parent();
		    		 while(tr$[0].nodeName!="tr"&&tr$[0].nodeName!="TR"){
		    			 tr$=$(tr$[0]).parent();
		    		 }
		    		 tr$[0].bgColor=recoveryColor;
		    		 tr$.bind("click",onclickColor);
		    	 }
		     }
    	}
		     
       tableName=obj;  
    }

function signOrderPointAnditem(){
	//签名函数-----开始
	if (confirm("您确定是否要签名吗？")) {	
		var secTrStr1="";
		var secTrStr2="";
		//point表查询符合要求的记录
		var trs=$("tr[name='changeColorOrderPoint']");
		var checks=$("input[name='checkboxOrderPoint']");
		var trslen=trs.length;
		var checkLen=checks.length;
		for(var i=0;i<trslen;i++){
			var tr=trs[i];
			trEditStr=trEditStr+tr.id;
			if(tr.bgColor===clickcolor){
				secTrStr1=secTrStr1+tr.id;
			}
		}
		for(var jj=0;jj<checkLen;jj++){
			if(checks[jj].checked){
		 		var ss=checks[jj].id;
		 		secCheckStr+=ss;		
		 	}		
		}
		var para1=trEditStr+";"+secTrStr1+";"+secCheckStr;
		trEditStr="";
		secCheckStr="";
		//item查询符合要求的记录
		var trs=$("tr[name='changeColorOrderItem']");
		var checks=$("input[name='checkboxOrderItem']");
		var trslen=trs.length;
		var checkLen=checks.length;
		for(var i=0;i<trslen;i++){
			var tr=trs[i];
			trEditStr=trEditStr+tr.id;
			if(tr.bgColor===clickcolor){
				secTrStr2=secTrStr2+tr.id;
			}
		}
		for(var jj=0;jj<checkLen;jj++){
			if(checks[jj].checked){
		 		var ss=checks[jj].id;
		 		secCheckStr+=ss;		
		 	}		
		}
		var para2=trEditStr+";"+secTrStr2+";"+secCheckStr;
		//alert(secCheckStr);
		trEditStr="";
		secCheckStr="";
		if(secTrStr1.length>0||secTrStr2.length>0){
	  		operate="Order";
	  	  	url="../SignTable";
	  	  	async=false;
	  	  	para=para1+"_o_p_i_t_"+para2;//_order_point_item_table
	  	  	//alert("tianjia");
	  	  	DBOption();
	 	}else{
	 		alert("没有符合的签名");
	 	}
		}
}
function selectOrderPointAnditem(){
	selectAllCheckbox("OrdeItem");
	selectAllCheckbox("OrdePoint");
}
function signAllOrderPointAnditem(){
	if (confirm("您确定是否要签名吗？")) {	
		var secTrStr1="";
		var secTrStr2="";
		//point表查询符合要求的记录
		var trs=$("tr[name='changeColorOrderPoint']");
		var checks=$("input[name='checkboxOrderPoint']");
		var trslen=trs.length;
		var checkLen=checks.length;
		for(var i=0;i<trslen;i++){
			var tr=trs[i];
			trEditStr=trEditStr+tr.id;
			secTrStr1=secTrStr1+tr.id;
		}
		for(var jj=0;jj<checkLen;jj++){
			if(checks[jj].checked){
		 		var ss=checks[jj].id;
		 		secCheckStr+=ss;		
		 	}		
		}
		var para1=trEditStr+";"+secTrStr1+";"+secCheckStr;
		trEditStr="";
		secCheckStr="";
		//item查询符合要求的记录
		var trs=$("tr[name='changeColorOrderItem']");
		var checks=$("input[name='checkboxOrderItem']");
		var trslen=trs.length;
		var checkLen=checks.length;
		for(var i=0;i<trslen;i++){
			var tr=trs[i];
			trEditStr=trEditStr+tr.id;
			secTrStr2=secTrStr2+tr.id;
		}
		for(var jj=0;jj<checkLen;jj++){
			if(checks[jj].checked){
		 		var ss=checks[jj].id;
		 		secCheckStr+=ss;		
		 	}		
		}
		var para2=trEditStr+";"+secTrStr2+";"+secCheckStr;
		//alert(secCheckStr);
		trEditStr="";
		secCheckStr="";
		if(secTrStr1.length>0||secTrStr2.length>0){
	  		operate="Order";
	  	  	url="../SignTable";
	  	  	async=false;
	  	  	para=para1+"_o_p_i_t_"+para2;//_order_point_item_table
	  	  	//alert("tianjia");
	  	  	DBOption();
	 	}else{
	 		alert("没有符合的签名");
	 	}
		}
}
function showVaria(){
	$( "#div3").dialog( "open" );	
}

function exitcp(){
	$( "#addVariationExit1" ).dialog("open");
}

function sendOrder(){
	var popup = window.open('', '');
	var url="../service/order.jsp?patient_no="+'<%=patient_no_emr%>'+"&admissTimes="+'<%=admissTimes%>'+"&doctorNo="+'<%=doctorNo%>'+"&patientArea="+'<%=patientArea%>'+"&doctorQX="+'<%=doctorQX%>'+"&babyStatus="+'<%=babyStatus%>';
	popup.location.href=url;
	//alert(url);
	//setTimeout(function() {popup.close();}, 5000);
}

function showHisOrder(){
	var popup = window.open('', '');
	var url="../service/record.jsp?patientNo="+'<%=patientID%>'+"";
	popup.location.href=url;
	
}

  </script>

</head>

<body style="padding-top: 2px;  margin-bottom: 0em">
<!--  <div>
	<table width="100%"  frame="vsides" cellpadding="3" cellspacing="0" style="border-style:solid solid none solid; border-width:1em; border-color:#e1e1e1; ">
	  <tr>
		  	<td >
		  		<table class="list1">
		  			<tbody id="topTbody" >
		
				  </tbody>
			  </table>
		  	</td>
	  </tr>
	</table>
</div>-->
<div id="tabs">
<table width="100%"  cellpadding="1" cellspacing="0" style=" border-style:solid; border-width:0.2em; border-color:#e1e1e1; ">
  <tr>
		  	<td colspan="2" height="10%">
		  		<table class="list1">
		  			<tbody id="topTbody" >
		
				  </tbody>
			  </table>
		  	</td>
	  </tr>
  <tr>
    <td width="35%" height="380" align="left"  valign="top"  style=" border-style:solid solid none none; border-width:0.2em; border-color:#e1e1e1 ">
      <br/>
      <div style="font-size:16px;color:#686868;font-weight: bold">路径执行列表:
      </div><br/>
      <table width="100%" id="node"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >    
        <tr  bgcolor="d3eaef" class="STYLE10">
          <td width="8%" height="20" ><div align="center"><span class="STYLE10"><font style="font-weight:bolder;font-size:10">序号</font></span></div></td>
          <td width="34%" ><div align="center"><span class="STYLE10"><font style="font-weight:bolder;font-size:10">节点名称</font></span></div></td>
          <td width="27%" ><div align="center"><span class="STYLE10"><font style="font-weight:bolder;font-size:10">开始时间</font></span></div></td>
          <td width="25%" ><div align="center"><span class="STYLE10"><font style="font-weight:bolder;font-size:10">结束时间</font></span></div></td>
        </tr>
        <tbody id="nodeTbody">
        </tbody>
    </table>
    <br/><br/>
    <div>
	    <table class="list1">    
	        <tbody id="nodeDaysTbody">
            </tbody>
	    </table>
    </div>
      <br /></td>
    <td width="65%" height="380"  valign="top"  style=" border-top-style:solid; border-width:0.2em; border-color:#e1e1e1 ">
	    <ul>
	        <li><a href="#zyzlgz"><span>诊疗工作</span></a></li>
	        <li><a href="#zyhlgz"><span>护理工作</span></a></li>
			<li><a href="#zzyz"><span>重要医嘱</span></a></li>
			<li><a href="#jsgz"><span>家属工作</span></a></li>
	        <li><a href="#byjl"><span>变异记录</span></a></li>
	    </ul>
    <div id="zyzlgz" style="OVERFLOW-Y: auto; OVERFLOW-X: auto;  HEIGHT:405">
        <table width="100%"   border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
          <tr>
            <td width="45%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">诊疗工作</span></div></td>
            <td width="6%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">执行</span></div></td>
            <td width="*" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">确认人</span></div></td>
            <td width="24%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">确认时间</span></div></td>
          </tr>
          <tbody id="doctorTable">
          </tbody>
        </table>
         <br />
    </div>
    
    <div id="zzyz" style="OVERFLOW-Y: auto; OVERFLOW-X: auto;  HEIGHT:300">
	<table  width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
	<tr>	 
          <th width="4%" height="18" bgcolor="d3eaef" class="STYLE10"><div align="center">编号</div></th>
          <th width="38%" height="18" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">医嘱</span></div></th>
          <th width="6%"  height="18" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">必填</span></div></th>      
          <th width="12%" height="18" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">持续节点/医嘱</span></div></th>
          <th width="6%"   height="18" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">执行</span></div></th>
          <th width="7%" height="18" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">确认人</span></div></th>
          <th width="27%" height="18" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">确认时间</span></div></th>
	</tr>
	<tr>
	  <td height="18" colspan="8" bgcolor="d3eaef" class="STYLE10">
	  <div  style="overflow-y:scroll;overflow-x:hidden; width:100%;height:300px; background-color:#FFF">
	  <table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
       
        <tbody id="orderTable">
        </tbody>
      </table>
	  </div>
	  </td>
	  </tr>

<!-- 	   <tr> <td height="10" colspan="8" bgcolor="#FFFFFF"></td> -->
<!-- 	   </tr> -->
	   
<!-- 	  <tr >	 -->
<!-- 	  <td colspan="8"> -->
<!-- 	  <table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce"> -->
<!--         <tr> -->
<!--           <td  width="4%" height="18" align="center" bgcolor="d3eaef" class="STYLE10"><strong>执行</strong></td> -->
<!--           <td width="60%" height="18" align="center" bgcolor="d3eaef" class="STYLE11">医嘱内容</td> -->
<!--           <td width="14%" height="18" align="center" bgcolor="d3eaef" class="STYLE11">长期</td> -->
<!--           <td width="*" height="18" align="center" bgcolor="d3eaef" class="STYLE11">类别</td> -->
<!--         </tr> -->
<!--       </table> -->
<!-- 	  </td>   -->
<!-- 	  </tr> -->
	  
	  
<!-- 	   <tr >	<td colspan="8"> -->
<!-- 	    <div  style="overflow-y:scroll;overflow-x:hidden; width:100%;height:150px; background-color:#FFF"> -->
<!-- 		 <table width="97%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce"> -->
       
<!--         <tbody id="orderTable1"> -->
<!--         </tbody> -->
<!--       </table> -->
<!-- 		</div> -->
	   
	   
	   
<!-- 	   </td></tr> -->
	  </table>
      <br />
    </div>
    <div id="zyhlgz" style=" HEIGHT:405">
	
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
        <tr>
            
            <td width="50%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">护理工作</span></div></td>
           
            <td width="6%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">执行</span></div></td>
            <td width="*" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">确认人</span></div></td>
            <td width="24%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">确认时间</span></div></td>
          </tr>
          <tbody id="nurseTable">
          </tbody>
      </table>
	 
	  <br />
    </div>
	 <div id="jsgz" style=" HEIGHT:405">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
        <tr>
           
            <td width="50%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">家属工作</span></div></td>
           
            <td width="6%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">执行</span></div></td>
            <td width="*" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">确认人</span></div></td>
            <td width="24%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">确认时间</span></div></td>
          </tr>
          <tbody id="familyTable">
          </tbody>
      </table><br />
    </div>
    <div id="byjl" style="OVERFLOW-Y: auto; OVERFLOW-X: auto;  HEIGHT:405">
      <table width="100%"   border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
        <tr>
         
          <td width="50%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">变异原因</span></div></td>
		   <td width="14%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">变异类型</span></div></td>
          <td width="12%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">确认人</span></div></td>
          <td width="24%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">确认时间</span></div></td>
         </tr>
        <tbody id="variationTable">
        </tbody>
      </table><br />
    </div>
    </td>
  </tr>
  <tr >
    <td height="15"  valign="top"  style="border-style:none solid none none;border-right-width:0.2em; border-right-color:#E2E2E2" >
    <div align="center">
    	<input id="xxxyjd" name="xxxyjd" class="xxxyjd" type="button" value="执行下一节点"/>
    	<input id="lutcan" name="lutcan"  type="button" value="退出路径" onclick="exitcp();"/>
    </div></td>
    <td height="15" >
    <div align="center"  id="zyzlgz"  style="height: 20px;padding-top: 0em">
  <input name="bottomButtonDoctor" type="button" value="签名"  onclick="sign('Doctor');"/>
  <input name="bottomButtonDoctor" type="button" value="全选" onclick="selectAllCheckbox('Doctor');"/> 
  <input name="bottomButtonDoctor" type="button" value="全签" onclick="signAll('Doctor');"/>
  <input name="bottomButtonDoctor" type="button" value="取消签名"  onclick="cancelSign('Doctor');"/>
    </div>
    <div  align="center" id="zzyz" style="height: 20px;padding-top: 0em">
<!--       <input name="bottomButtonOrder" type="button" value="签名"  onclick="signOrderPointAnditem();"/> -->
<!--        <input name="bottomButtonOrder" type="button" value="全选"  onclick="selectAllCheckbox('Orde');"/> -->
<!--       <input name="bottomButtonOrder" type="button" value="全签"  onclick="signAllOrderPointAnditem();"/> -->
      <input name="1_bottomButtonOrder" type="button" value="查看变异记录详细信息"  onclick="showVaria();"/>
      <input name="2_bottomButtonOrder" type="button" value="下医嘱"  onclick="sendOrder();"/>
      <input name="3_bottomButtonOrder" type="button" value="查看已下发医嘱列表"  onclick="showHisOrder();"/>
    </div>
     <div  align="center" id="jsgz"  style="height: 20px;padding-top: 0em">
      <input name="bottomButtonFamily" type="button" value="签名"  onclick="sign('Family');"/>
       <input name="bottomButtonFamily" type="button" value="全选"  onclick="selectAllCheckbox('Family');"/>
      <input name="bottomButtonFamily" type="button" value="全签"  onclick="signAll('Family');"/>
    </div>
     <div  align="center" id="zyhlgz"  style="height: 20px;padding-top: 0em">
      <input name="bottomButtonNurse" type="button" value="签名" onclick="sign('Nurse');"/>
       <input name="bottomButtonNurse" type="button" value="全选"  onclick="selectAllCheckbox('Nurse');"/> 
      <input name="bottomButtonNurse" type="button" value="全签"  onclick="signAll('Nurse');"/>
      <input name="bottomButtonNurse" type="button" value="取消签名" onclick="cancelSign('Nurse');"/>
    </div>
     <div  align="center" id="byjl"  style="height: 20px;padding-top: 0em">
       <input id="variationOperator"  name="bottomButtonVariation" type="button" value="添加" onclick="add('addVariation');"/>
	     <input id="delvariationOperator"  name="bottomButtonVariation" type="button" value="删除" onclick="delVaration();"/>
        <input name="bottomButtonVariation" type="button" value="签名" onclick="sign('Variation');"/> 
      <input name="bottomButtonVariation" type="button" value="全签"  onclick="signAll('Variation');"/> 
    </div></td>
  </tr>
   <tr>
	    <td height="15" colspan="2" bgcolor="#e1e1e1">      
		     <table>
		     	 <tr>    
			          <td width="80%"    align="center" valign="bottom">
						 <font size="4px" color="blue">信息科值班电话</font>
						 <font size="3px" color="blue">&nbsp;&nbsp;&nbsp;&nbsp;严武: &nbsp;13806063824</font>
						 <font size="3px" color="blue">&nbsp;&nbsp;周伟彬: &nbsp;18059234145</font>
			           </td>
			           <td width="10%"   align="center"  valign="bottom">
			           <form action='../help/whatsnew.doc'>
			           <input type="submit"  value="最近更新"/>
			           </form>
			           </td>
			           <td width="10%"  align="center" valign="bottom">
			           <form action='../help/usehelp.doc'>
			           <input type="submit" value="执行帮助"/>
			           </form>
			           </td>
        	     </tr> 
        	</table>
        </td>
   </tr>
</table>
   </div>
    <!--<table>
          <tr>
	          <td width="80%" align="center">
				 <font size="4px" color="red">信息科值班电话</font>
				 <font size="3px" color="red">&nbsp;&nbsp;&nbsp;&nbsp;严武: &nbsp;13806063824</font>
				 <font size="3px" color="red">&nbsp;&nbsp;张昆: &nbsp;13521128257</font>
	           </td>
	           <td width="10%" align="center">
	           <form action='../help/whatsnew.doc'>
	           <input type="submit"  value="最近更新"/>
	           </form>
	           </td>
	           <td width="10%" align="center">
	           <form action='../help/usehelp.doc'>
	           <input type="submit" value="执行帮助"/>
	           </form>
	           </td>
           </tr>
      </table> -->
<p>&nbsp;</p>
<div id="nextNode" style="background: #FFF">
	<form id="form1" name="form1" method="post" action="">
	  <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
        <tr>
          <td width="15%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">节点编号</span></div></td>
          <td width="60%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">可执行节点名称</span></div></td>
          <td width="25%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">天数</span></div></td>
        </tr>
          <tbody id="nextNodeTable">
          </tbody>
      </table>
	</form>
</div>

<div id="addVariation" style="background: #FFF">
<form id="form1" name="form1" method="post" action="">
	<H3 align="center">路径变异登记</H3>
	<H3 align="center" id="variationNode">
	  <select name="select">
	    <option value="1">正常变异</option>
	    <option value="2">并发症</option>
	    <option value="0" selected="selected">请选择普通节点变异类型</option>
      </select>
	</H3>
	<center>
	  <font style="font-size: 14px;">
	    <textarea name="addbynr" rows="8" cols="50" id="variationNodeText"></textarea>
      </font>
    </center>
  </form>
</div>
<div id="addVariationExit" style="background: #FFF">

	<H3 align="center">路径变异登记</H3>
	<table width="100%" border="0" cellpadding="0" cellspacing="1" >
      <tr>
        <td width="28%" height="26" nowrap="nowrap"><div align="right">
          退出路径&nbsp;<br />
          变异类型:
        </div></td>
        <td width="72%">
        <div id="variationExit">      
       
        </div>
        </td>
      </tr>
      <tr>
        <td nowrap="nowrap"><div align="right">变异原因:</div></td>
        <td><font style="font-size: 14px;">
          <textarea name="textarea" rows="8" cols="35" id="variationExitText"></textarea>
        </font></td>
      </tr>
    </table>
</div>

<div id="addVariationExit1" style="background: #FFF">

	<H3 align="center">路径变异登记</H3>
	<table width="100%" border="0" cellpadding="0" cellspacing="1" >
      <tr>
        <td width="28%" height="26" nowrap="nowrap"><div align="right">
          退出路径&nbsp;<br />
          变异类型:
        </div></td>
        <td width="72%">
        <div id="variationExit1">      
       
        </div>
        </td>
      </tr>
      <tr>
        <td nowrap="nowrap"><div align="right">变异原因:</div></td>
        <td><font style="font-size: 14px;">
          <textarea name="textarea" rows="8" cols="35" id="variationExitText1"></textarea>
        </font></td>
      </tr>
    </table>
</div>
<div id="div1"></div>
<div id="div2"></div>
<div id="div3"></div>
<div id="wait" style="display:none;width:500px;height:120px;position:absolute;top:35%;left:35%;padding:2px;overflow:hidden;">
	<span style="background:#FFFFFF;">数据正在加载中,请等待 ...</span>
	<br />
	<img src='../public/images/loading.gif' width="300" height="15" /> 
	
</div>
</body>
</html>