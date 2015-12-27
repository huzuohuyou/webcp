<%--  

/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：match.jsp
// 接受参数 bianma:路径编号,flag 对应的那个表
//								:1诊断
//								:2手术
//								:3诊疗
//								:4护理
//								:5医嘱
// 文件功能描述：匹配页面
// 创建人：刘植鑫 
// 创建日期：2011/07/26
//修改页面功能，当字典表数据不存在的时候不允许操作
//修改人：刘植鑫
//修改时间：2011-08-25
// 
//----------------------------------------------------------------*/

  --%>
<%@page import="com.goodwillcis.lcp.service.match.impl.MatchOrderImpl"%>
<%@page import="com.goodwillcis.lcp.service.match.impl.MatchOperaImpl"%>
<%@page import="com.goodwillcis.lcp.service.match.impl.MatchNurseImpl"%>
<%@page import="com.goodwillcis.lcp.service.match.impl.MatchDoctorImpl"%>
<%@page import="com.goodwillcis.lcp.service.match.impl.MatchDiaImpl"%>
<%@page import="com.goodwillcis.lcp.service.match.impl.MatchLocalImpl"%>
<%@ page import="com.goodwillcis.lcp.model.DataSet"%>
<%@ page import="com.goodwillcis.lcp.service.match.MatchLocal"%>
<%@ page import="com.goodwillcis.lcp.service.match.MatchDia"%>
<%@ page import="com.goodwillcis.lcp.service.match.MatchDoctor"%>
<%@ page import="com.goodwillcis.lcp.service.match.MatchNurse"%>
<%@ page import="com.goodwillcis.lcp.service.match.MatchOpera"%>
<%@ page import="com.goodwillcis.lcp.service.match.MatchOrder"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String bianma=request.getParameter("bianma");
	String flag=request.getParameter("flag");
	String parent=request.getParameter("parent");
	DataSet dataSet_code_info=new DataSet();
	String info_sql="";
	String match_sql="";
	String ppjg="";
	MatchDia dia=new MatchDiaImpl();
	MatchDoctor doctor=new MatchDoctorImpl();
	MatchNurse nurse=new MatchNurseImpl();
	MatchOpera opera=new MatchOperaImpl();
	MatchOrder order=new MatchOrderImpl();
	if(flag.equals("1")){
		ppjg=dia.funCreateMatchTable(bianma);
		match_sql="select DIAGNOSIS_CODE CODE,DIAGNOSIS_NAME NAME,INPUT_CODE_PY,INPUT_CODE_WB from lcp_local_diagnosis t";
		info_sql="SELECT DIAGNOSIS_CODE CODE,DIAGNOSIS_NAME NAME,INPUT_CODE_PY,INPUT_CODE_WB FROM DCP_DICT_DIAGNOSIS T WHERE T.DIAGNOSIS_CODE='"+bianma+"'";
	}else if(flag.equals("2")){
		ppjg=opera.funCreateMatchTable(bianma);
		match_sql="select  OPERATION_CODE CODE,OPERATION_NAME NAME,INPUT_CODE_PY,INPUT_CODE_WB from lcp_local_operation t";
		info_sql="SELECT OPERATION_CODE CODE,OPERATION_NAME NAME,INPUT_CODE_PY,INPUT_CODE_WB FROM DCP_DICT_OPERATION T WHERE T.operation_CODE='"+bianma+"'";
	}else if(flag.equals("3")){
		ppjg=doctor.funCreateMatchTable(bianma);
		match_sql="select DOCTOR_CODE CODE,DOCTOR_NAME NAME,INPUT_CODE_PY,INPUT_CODE_WB from lcp_local_doctor t";
		info_sql="SELECT DOCTOR_CODE CODE,DOCTOR_NAME NAME,INPUT_CODE_PY,INPUT_CODE_WB FROM DCP_DICT_DOCTOR T WHERE T.DOCTOR_CODE='"+bianma+"'";
	}else if(flag.equals("4")){
		ppjg=nurse.funCreateMatchTable(bianma);
		match_sql="select NURSE_CODE CODE,NURSE_NAME NAME,INPUT_CODE_PY,INPUT_CODE_WB from lcp_local_nurse t";
		info_sql="SELECT NURSE_CODE CODE,NURSE_NAME NAME,INPUT_CODE_PY,INPUT_CODE_WB FROM DCP_DICT_NURSE T WHERE T.NURSE_CODE='"+bianma+"'";
	}else if(flag.equals("5")){
		ppjg=order.funCreateMatchTable(bianma);
		match_sql="select ORDER_CODE CODE,ORDER_NAME NAME,INPUT_CODE_PY,INPUT_CODE_WB from lcp_local_order t";
		info_sql="SELECT ORDER_ITEM_CODE CODE,ORDER_ITEM_NAME NAME,INPUT_CODE_PY,INPUT_CODE_WB FROM DCP_DICT_ORDER_ITEM T WHERE T.ORDER_ITEM_CODE='"+bianma+"'";
	}
	dataSet_code_info.funSetDataSetBySql(info_sql);
	String info_code=dataSet_code_info.funGetFieldByCol(0, "CODE");
	String info_mingcheng=dataSet_code_info.funGetFieldByCol(0, "NAME");
	String info_pinyin=dataSet_code_info.funGetFieldByCol(0, "INPUT_CODE_PY");
	String info_wubi=dataSet_code_info.funGetFieldByCol(0, "INPUT_CODE_WB");
%>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-size: 12px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE2 {
	font-size: 12px;
	color: #0000FF;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 14px; }
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
.tabheightandwidge{
	height:600px;
}
#tabs { height: 200px; } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 550px; overflow: auto;} 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0em 0em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -0px !important; margin-bottom: 0px !important; border-top: none; border-bottom-width: 0px; }
	.ui-tabs-selected { margin-top: -3px !important; }
-->
</style>
<script>
var match_util_addmap=new Map();
var match_util_delmap=new Map();
var match_util_oldRemainMap=new Map();//选中左侧栏的时候加载匹配数据，整个过程可以有变化
var match_util_oldMap=new Map();//选中左侧栏的时候加载匹配数据，整个过程必须一直没变化，用于比较使用
var match_util_del_linshi_Arr=new Array();//用于临时存储需要删除的数据，因为删除前需要验证是否数据可以 被删除
var match_util_delStr="";//用于临时存储需要删除的数据，因为删除前需要验证是否数据可以 被删除
var match_util_zxksbm_ajax="";
var match_util_zxksmc_ajax="";
var match_util_zxkspy_ajax="";
var match_util_zxkswb_ajax="";
var match_util_bdksbm_ajax="";
var match_util_bdksmc_ajax="";
var match_util_bdkspy_ajax="";
var match_util_bdkswb_ajax="";
var match_util_zcl_ajax="";//左侧栏选中的ID值 
var match_util_ysj_ajax="";//右上角栏选中的ID值 
var match_util_yxj_add_ajax="";
var match_util_yxj_del_ajax="";
var match_util_operate="";
var match_util_url="";
var match_util_isHaveSel=false;//左侧栏是否有选中的内容
var match_util_pageNo="1";
var match_util_mingcheng="";
var match_util_highlightcolor='#d5f4fe';
var match_util_recoveryColor='#ffffff';
var match_util_clickcolor='#51b2f6';
var match_util_tempColor;
var match_util_async=false;
 	$(document).ready(function() {
 		
 		var code="<%=info_code%>";
 		if(code==""){
 			alert("字典中没有此编码");
 			$("#yctext")[0].disabled=true;
 			$("#bdTable").html("");
 			$("#ppjg").html("");
 			return;
 		}
 		match_util_zcl_ajax="<%=bianma%>";
 		if("<%=flag%>"==1){
 			match_util_url="../MatchDiaServlet";
 		}
 		if("<%=flag%>"==2){
 			match_util_url="../MatchOperaServlet";
 		}
 		if("<%=flag%>"==3){
 			match_util_url="../MatchDoctorServlet";
 		}
 		if("<%=flag%>"==4){
 			match_util_url="../MatchNurseServlet";
 		}
 		if("<%=flag%>"==5){
 			match_util_url="../MatchOrderServlet";
 		}
 		match_util_operate="pipeiutil";	
 		match_util_async=false;
	 	//match_util_DBOption();
 		match_util_removeAll();
		match_util_addOldList();
  });
 	 /**
	  * lzx	 2011-06-01
	  *	函数说明：数据库操作
	  */
	function match_util_DBOption(){
		$.ajax({
			url: match_util_url,
	 	    type: 'POST',
	 	    async: true,
	 	    data: {op : match_util_operate,
	 	    	zxksbm_ajax : encodeURI(match_util_zxksbm_ajax.replace(/\+/g,"%2B")),
	 	    	zxksmc_ajax : encodeURI(match_util_zxksmc_ajax.replace(/\+/g,"%2B")),
	 	    	zxkspy_ajax : encodeURI(match_util_zxkspy_ajax.replace(/\+/g,"%2B")),
	 	    	zxkswb_ajax : encodeURI(match_util_zxkswb_ajax.replace(/\+/g,"%2B")),
	 	    	bdksbm_ajax : encodeURI(match_util_bdksbm_ajax.replace(/\+/g,"%2B")),
	 	    	bdksmc_ajax : encodeURI(match_util_bdksmc_ajax.replace(/\+/g,"%2B")),
	 	    	bdkspy_ajax : encodeURI(match_util_bdkspy_ajax.replace(/\+/g,"%2B")),
	 	    	bdkswb_ajax : encodeURI(match_util_bdkswb_ajax.replace(/\+/g,"%2B")),
	 	    	zcl_ajax : encodeURI(match_util_zcl_ajax.replace(/\+/g,"%2B")),
	 	    	ysj_ajax : encodeURI(match_util_ysj_ajax.replace(/\+/g,"%2B")),
	 	    	yxj_add_ajax : encodeURI(match_util_yxj_add_ajax.replace(/\+/g,"%2B")),
	 	    	yxj_del_ajax : encodeURI(match_util_yxj_del_ajax.replace(/\+/g,"%2B")),
	 	    	delStr : match_util_delStr,
				pageNo : match_util_pageNo,
	 	    	execute:false
	 	    	},
	 	    dataType: "json",
	 	    complete: match_util_show_result,
	 	    success: match_util_onDataReceived
	 	  });
	}
	 /**
	  * 	刘植鑫 2011-04-25
	  *	函数说明：ajax操作完成调用此函数
	  */
	var match_util_show_result = function(XMLHttpRequest, textStatus){
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
	  * 	刘植鑫 2011-04-25
	  *	函数说明：ajax操作成功调用此函数
	  */
	var match_util_onDataReceived = function(data, textStatus, XMLHttpRequest){
		data = eval(data);
	 	data = data[0];
	 	ajax=true;
	 	
 		//取得所有表格后的操作
 			if(data.method==="getAllTable"){
 				$("#zxTable").html(data.tableHtml);
 				$("#zxPage").html(data.pageHtml);
 				$("#bdTable").html(data.localTable);
 			}
 			//点击查看其他表格后的操作
 			if(data.method==="changeTable"){
 				$("#zxTable").html(data.tableHtml);
 				$("#zxPage").html(data.pageHtml);
 			}
 			if(data.method==="ycchaxun"){
 				$("#bdTable").html(data.localTable);
 			}
 			if(data.method==="zcchaxun"){
 				$("#zxTable").html(data.tableHtml);
 				$("#zxPage").html(data.pageHtml);
 			}
 			if(data.method==="xuanzhongzcl"){
 				$("#ppjgTable").html(data.youxiajiaoHtml);
 				match_util_removeAll();
 				match_util_addOldList();
 			}
 			if(data.method==="pipeiutil"){
 				$("#table_bianma").html(data._code);
 				$("#table_mingcheng").html(data._mingcheng);
 				match_util_mingcheng=data._mingcheng;
 				$("#table_pinyin").html(data._pinyin);
 				$("#table_wubi").html(data._wubi);
 				$("#ppjgTable").html(data.youxiajiaoHtml);
 				$("#bdTable").html(data.localTable);
 			}
 			if(data.method==="tijiao"){
 				if (data.result === "OK"){
					alert("保存成功");
					if(data.row==="0"){
						match_util_updateZCLPP("");
					}
					else{
						match_util_updateZCLPP("√");
					}
					match_util_removeYSJAllColor();
					$("#ppjgTable").html(data.youxiajiaoHtml);
 					match_util_removeAll();
 	 				match_util_addOldList();
 	 				$("#<%=parent%>").dialog( "close" );
 				}else{
 					if(data.localCode===""){
 						alert("插入数据错误，请查看是否重复插入，若不是请联系管理员");
 					}else{
 						alert(data.localCode+" 已经匹配过，不允许再次匹配");
 					} 				}
 			}
 			if(data.method==="befordDel"){
 				if(data.row==="0"){
 					var len=match_util_del_linshi_Arr.length;
 					for(var i=0;i<len;i++){
 						var aa=	match_util_del_linshi_Arr.pop();
 						match_util_delList(aa);
 						var zcl_xz$=$("tr[name^='youxiajiao'][id='"+aa+"']");
 						zcl_xz$.remove();
 					}
 				
 				}else{
 					var OKNO =confirm("您所删除的数据正在使用中，是否强制删除！");
 					if(OKNO){
 						var len=match_util_del_linshi_Arr.length;
 	 					for(var i=0;i<len;i++){
 	 						var aa=	match_util_del_linshi_Arr.pop();
 	 						match_util_delList(aa);
 	 						var zcl_xz$=$("tr[name^='youxiajiao'][id='"+aa+"']");
 	 						zcl_xz$.remove();
 	 					}
 					}else{
 						match_util_del_linshi_Arr=new Array();
 					}
 				}
 			}
	};

	//更新左侧栏匹配内容项
	function match_util_updateZCLPP(pipei){
		var zcl$=$("tr[name^='zuocelan']");
		var zclleng=zcl$.length;
		for(var i=0;i<zclleng;i++){
			var tr=zcl$[i];
			if(tr.bgColor==match_util_clickcolor){
				zcl$[i].childNodes[4].childNodes[0].innerHTML=pipei;
			}
		}
	}

	//清楚右上角所有行的颜色
	function match_util_removeYSJAllColor(){
		//取出右上角选中行
		var ysj$=$("tr[name^='youshangjiao']");
		var ysjleng=ysj$.length;
		for(var i=0;i<ysjleng;i++){
			var tr=ysj$[i];
			tr.bgColor=match_util_recoveryColor;
		}
	}
	function tiaozhuan(aa){
		var auto=$(":radio[name='zccx'][checked]").val();
		var zxksbm$=$("#zctext");
		var zxksbm_0=zxksbm$[0].value+"";
		if(auto=="1"){
			match_util_zxksbm_ajax=zxksbm_0;
			}
		if(auto=="2"){
			match_util_zxksmc_ajax=zxksbm_0;
			}
		if(auto=="3"){
			match_util_zxkspy_ajax=zxksbm_0;
			}
		if(auto=="4"){
			match_util_zxkswb_ajax=zxksbm_0;
			}
		match_util_operate="changeTable";
		match_util_pageNo=aa;
		match_util_DBOption();
	}
	function tiaozhuanzhiding(){
		var auto=$(":radio[name='zccx'][checked]").val();
		var zxksbm$=$("#zctext");
		var zxksbm_0=zxksbm$[0].value+"";
		if(auto=="1"){
			match_util_zxksbm_ajax=zxksbm_0;
			}
		if(auto=="2"){
			match_util_zxksmc_ajax=zxksbm_0;
			}
		if(auto=="3"){
			match_util_zxkspy_ajax=zxksbm_0;
			}
		if(auto=="4"){
			match_util_zxkswb_ajax=zxksbm_0;
			}
		
		var _pageNo=$("#tiaozhuan_daohang");
		_pageNo=_pageNo[0].value;
		match_util_operate="changeTable";
		match_util_pageNo=_pageNo;
		match_util_DBOption();
	}
	function match_util_chaxunycl(){
		var auto=$(":radio[name='yccx'][checked]").val();
		var bdksbm$=$("#yctext");
		var bdksbm_0=bdksbm$[0].value+"";
		if(auto=="1"){
			match_util_bdksbm_ajax=bdksbm_0;
			}
		if(auto=="2"){
			match_util_bdksmc_ajax=bdksbm_0;
			}
		if(auto=="3"){
			match_util_bdkspy_ajax=bdksbm_0;
			}
		if(auto=="4"){
			match_util_bdkswb_ajax=bdksbm_0;
			}
		match_util_operate="ycchaxun";
		match_util_DBOption();	
		match_util_bdksbm_ajax="";
		match_util_bdksmc_ajax="";
		match_util_bdkspy_ajax="";
		match_util_bdkswb_ajax="";
	}
	
	/**
	 * 添加
	 * 2011-07-25
	 */
	function match_util_tianjia(){
		//取出错侧栏选中行
		var zcl$=$("tr[name^='zuocelan']");
		var zclleng=zcl$.length;
		for(var i=0;i<zclleng;i++){
			var tr=zcl$[i];
			if(tr.bgColor==match_util_clickcolor){
				match_util_zcl_ajax=tr.id;
			}
		}
		var ycl_array_zx=new Array;
		var ycl_array_dx=new Array;
		//取出右上角选中行
		var ysj$=$("tr[name^='youshangjiao']");
		var ysjleng=ysj$.length;
		for(var i=0;i<ysjleng;i++){
			var tr=ysj$[i];
			if(tr.bgColor==match_util_clickcolor){
				ycl_array_zx.push(match_util_zcl_ajax+"_and_"+tr.id);
			}
		}
		if(ycl_array_zx.length==0||match_util_zcl_ajax==""){
			alert("你的选择不符合要求");
		}else{
			var zcl_xz$=$("tr[name^='zuocelan'][id='"+match_util_zcl_ajax+"']");
			var ycl_array_zxlen=ycl_array_zx.length;
			for(var jj=0;jj<ycl_array_zxlen;jj++){
				var aa=ycl_array_zx.pop();
				var isSuc=match_util_addList(aa);
				if(!isSuc){
					var ycl_array_dx_length=ycl_array_dx.length;
					for(var kk=0;kk<ycl_array_dx_length;kk++){
						var isDelSuc=match_util_delList(ycl_array_dx.pop());
					}
					alert("您选择的里面有的数据已经匹配过，请重新选择");
					return ;
				}else{
					ycl_array_dx.push(aa);
				}
			}
			var trcontent="";
			var len=ycl_array_dx.length;
			for(var jj=0;jj<len;jj++){
				var ee=ycl_array_dx.pop().split("_and_")[1];
				trcontent=trcontent+"<tr name='youxiajiao'  style='cursor:hand'  id='"+match_util_zcl_ajax+"_and_"+ee+"' bgcolor='#FFFFFF' onclick='onclickColor(this)' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
				"<td><div align='center'>"+match_util_zcl_ajax+"</div></td>" +
				"<td><div align='center'><%=info_mingcheng%></div></td>" +
				"<td><div align='center'>"+ee+"</div></td>" +
				"</tr>";
			}
			$( "#ppjgTable" ).append(trcontent);	
			match_util_qingchuyoushangjiao();
		}
	}
	//删除匹配项
	function match_util_shanchu(){
		var yxj$=$("tr[name^='youxiajiao']");
		var yxjleng=yxj$.length;
		var len=0;
		for(var i=0;i<yxjleng;i++){
			var tr=yxj$[i];
			if(tr.bgColor==match_util_clickcolor){
				len++;
				match_util_del_linshi_Arr.push(tr.id);
				match_util_delStr=match_util_delStr+tr.id+":;";
				match_util_operate="befordDel";//删除前验证
				match_util_DBOption();
				match_util_delStr="";
//				match_util_delList(tr.id);
//				$(tr).remove();
			}
		}
		if(len==0){
			alert("请选择需要删除的数据");
		}
	}
	/**
	 *  添加匹配 如果存在这个值，那么返回false，并且不存入内存
	 * @param id
	 * @returns {Boolean}
	 */
	function match_util_addList(id){
		var nowExist=match_util_oldRemainMap.containsKey(id);//判断原来是否已经有了此匹配
		if(!nowExist){//如果没有匹配
			var addExist=match_util_addmap.containsKey(id);
			if(!addExist){
				match_util_addmap.put(id, id);//添加
				var delExist=match_util_delmap.containsKey(id);//判断已删除的里面是否有此匹配
				if(delExist){
					match_util_delmap.remove(id);
				}
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	/**
	 * 删除数据
	 * @param id
	 * @returns {Boolean}
	 */
	function match_util_delList(id){
		var nowExist=match_util_oldRemainMap.containsKey(id);
		if(nowExist){
			match_util_oldRemainMap.remove(id);
			match_util_delmap.put(id, id);
			return true;
		}else{
			var addExist=match_util_addmap.containsKey(id);
			if(addExist){
				match_util_addmap.remove(id);
				return true;
			}
			return false;
		}
	}
	function match_util_addOldList(){
		var youxiajiao$=$("tr[name^='youxiajiao']");
		var youxiajiaolen=youxiajiao$.length;
		for(var i=0;i<youxiajiaolen;i++){
			var tr=youxiajiao$[i];
			match_util_oldRemainMap.put(tr.id, tr.id);
			match_util_oldMap.put(tr.id, tr.id);
		}
	}
	/**
	 * 清空所有 匹配
	 */
	function match_util_removeAll(){
		match_util_oldRemainMap.clear();
		match_util_addmap.clear();
		match_util_delmap.clear();
		match_util_oldMap.clear();
	}
	function match_util_tijiao(){
// 		var expires = new Date();
// 			expires.setTime(expires.getTime() + 3 * 24 * 60 * 60 * 1000);
// 			document.cookie ='match_util=false_end;expires='+ expires.toGMTString();
		var addarr=match_util_addmap.values();
		var delarr=match_util_delmap.values();
		var addarr_len=addarr.length;
		var delarr_len=delarr.length;
		for(var i=0;i<addarr_len;i++){
			var add=addarr.pop();
			if(!match_util_oldMap.containsKey(add)){
				match_util_yxj_add_ajax=match_util_yxj_add_ajax+add+":;";
			}
		}
		for(var i=0;i<delarr_len;i++){
			var del=delarr.pop();
			if(match_util_oldMap.containsKey(del)){
				match_util_yxj_del_ajax=match_util_yxj_del_ajax+del+":;";
			}
		}
		if(match_util_yxj_add_ajax==""&&match_util_yxj_del_ajax==""){
			alert("数据和原来一样，不需要提交");
			return ;
		}
//		alert(match_util_yxj_add_ajax);
//		alert(match_util_yxj_del_ajax);
		match_util_operate="tijiao";//选中左侧栏
		match_util_DBOption();
		match_util_yxj_add_ajax="";
		match_util_yxj_del_ajax="";
		//document.cookie ="match_util=false_end";
	}
	/**
	 * 清楚右上角的颜色
	 */
	function match_util_qingchuyoushangjiao(){
		var ysj$=$("tr[name^='youshangjiao']");
		var ysjleng=ysj$.length;
		for(var i=0;i<ysjleng;i++){
			var tr=ysj$[i];
			tr.bgColor=match_util_recoveryColor;
			
		}
	}
</script>

	<table width="100%" border="1">
		<tr>
			<td width="40%" valign="top">
				<table width="200"  border="0" cellpadding="0" cellspacing="1" bgcolor="#000000" style=" border-style:solid; border-width:0; border-color:#e1e1e1;">
					<tr>
						<td bgcolor="#a8c7ce"  width="40%">编码</td>
						<td bgcolor="d3eaef" width="60%" class="STYLE6"><div id="table_bianma"><%=info_code %></div></td>
					</tr>
					<tr>
						<td bgcolor="#a8c7ce" >名称</td>
						<td bgcolor="d3eaef" class="STYLE6"><div id="table_mingcheng"><%=info_mingcheng %></div></td>
					</tr>
					<tr>
						<td bgcolor="#a8c7ce" >拼音简码</td>
						<td bgcolor="d3eaef" class="STYLE6"><div id="table_pinyin"><%=info_pinyin %></div></td>
					</tr>
					<tr>
						<td bgcolor="#a8c7ce" >五笔简码</td>
						<td bgcolor="d3eaef" class="STYLE6"><div id="table_wubi"><%=info_wubi %></div></td>
					</tr>
				</table>
			</td>
			<td>
				<div id="cxtj" style="height: 20px;">
		    		<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" style=" border-style:solid; border-width:0; border-color:#e1e1e1; width: 100%;">
			        	<tr>
			        		<td>
			        			<input name="yccx" type="radio" value="1" checked="checked"/>编码
						        <input type="radio" name="yccx" value="2"/> 名称
						        <input type="radio" name="yccx" value="3"/> 拼音
						        <input type="radio" name="yccx" value="4"/> 五笔
						        <input type="text" id="yctext" oninput="match_util_chaxunycl();" onpropertychange="match_util_chaxunycl();"/>
			        		</td>
			        	</tr>
		        	</table>
				</div>
				<div id="ppnr" style="height: 220px; overflow-y: scroll;">
			       <table width="100%" id="node"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce"  style=" height:auto">
			        	<tr>
			        		<td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">编码</span></div></td>
			        		<td width="*" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">名称</span></div></td>
			        		<td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">拼音简码</span></div></td>
			        		<td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">五笔简码</span></div></td>
			        	</tr>
			        	<tbody id="bdTable">
			        		<%
			        		MatchLocal local=new MatchLocalImpl();
			        		String localTable=local.funGetTable(match_sql,"CODE","NAME","INPUT_CODE_PY","INPUT_CODE_WB");        		
			        		%>
			        		<%=localTable %>
			        	</tbody>
		          </table>
		       	</div>
			    <table width="100%">
			    	<tr>
			     		<td align="right"><input type="button" value="添加" onclick="match_util_tianjia();"/>
			     		<input type="button" value="删除" onclick="match_util_shanchu();"/></td>
			     	</tr>
			    </table>
			   	<div id="ppjg" style="height: 200px; overflow-y: scroll;">
			    	<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" style=" border-style:solid; border-width:0; border-color:#e1e1e1; width: 100%;">
			        	<tr>
			        		<td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">编码</span></div></td>
			        		<td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">名称</span></div></td>
			        		<td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">对照本地编码</span></div></td>
			        	</tr>
			        	<tbody id="ppjgTable">
			        		<%=ppjg%>
			        	</tbody>
			        </table>
				</div>
				<table width="100%">
				      	<tr>
				      		<td colspan="4" align="right"><input type="button" value="提交" onclick="match_util_tijiao();"/></td>
				      	</tr>
		      	</table>
			</td>
		</tr>
    	
	</table>

