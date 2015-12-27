/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：matchDia.js
// 文件功能描述：诊断js文件
// 创建人：刘植鑫 
// 创建日期：2011/07/26
// 
//----------------------------------------------------------------*/
var addmap=new Map();
var delmap=new Map();
var oldRemainMap=new Map();//选中左侧栏的时候加载匹配数据，整个过程可以有变化
var oldMap=new Map();//选中左侧栏的时候加载匹配数据，整个过程必须一直没变化，用于比较使用
var del_linshi_Arr=new Array();//用于临时存储需要删除的数据，因为删除前需要验证是否数据可以 被删除
var delStr="";//用于临时存储需要删除的数据，因为删除前需要验证是否数据可以 被删除
var zxksbm_ajax="";
var zxksmc_ajax="";
var zxkspy_ajax="";
var zxkswb_ajax="";
var bdksbm_ajax="";
var bdksmc_ajax="";
var bdkspy_ajax="";
var bdkswb_ajax="";
var zcl_ajax="";//左侧栏选中的ID值 
var ysj_ajax="";//右上角栏选中的ID值 
var yxj_add_ajax="";
var yxj_del_ajax="";
var ysj_y="";
var operate="";
var url="";
var isHaveSel=false;//左侧栏是否有选中的内容
var pageNo="1";
var highlightcolor='#d5f4fe';
var recoveryColor='#ffffff';
var clickcolor='#51b2f6';
var tempColor;
 	$(document).ready(function() {
 		operate="getAllTable";
	 	url="../MatchCPServlet";
	 	async=false;
	 	DBOption();
  });
 	 /**
	  * zhangkun	 2012-05-10
	  *	函数说明：数据库操作
	  */
	function DBOption(){
		//alert(zxksbm_ajax.replace(/\+/g,"%2B"));
		$.ajax({
			url: url,
	 	    type: 'POST',
	 	    async: async,
	 	    data: {op : operate,
	 	    	zxksbm_ajax : encodeURI(zxksbm_ajax.replace(/\+/g,"%2B")),
	 	    	zxksmc_ajax : encodeURI(zxksmc_ajax.replace(/\+/g,"%2B")),
	 	    	zxkspy_ajax : encodeURI(zxkspy_ajax.replace(/\+/g,"%2B")),
	 	    	zxkswb_ajax : encodeURI(zxkswb_ajax.replace(/\+/g,"%2B")),
	 	    	bdksbm_ajax :encodeURI(bdksbm_ajax.replace(/\+/g,"%2B")),
	 	    	bdksmc_ajax : encodeURI(bdksmc_ajax.replace(/\+/g,"%2B")),
	 	    	bdkspy_ajax : encodeURI(bdkspy_ajax.replace(/\+/g,"%2B")),
	 	    	bdkswb_ajax : encodeURI(bdkswb_ajax.replace(/\+/g,"%2B")),
	 	    	zcl_ajax : encodeURI(zcl_ajax.replace(/\+/g,"%2B")),
	 	    	ysj_ajax : encodeURI(ysj_ajax.replace(/\+/g,"%2B")),
	 	    	yxj_add_ajax : encodeURI(yxj_add_ajax.replace(/\+/g,"%2B")),
	 	    	yxj_del_ajax : encodeURI(yxj_del_ajax.replace(/\+/g,"%2B")),
	 	    	delStr : delStr,
				pageNo : pageNo,
	 	    	execute:false
	 	    	},
	 	    dataType: "json",
	 	    complete: show_result,
	 	    success: onDataReceived
	 	  });
	}
	 /**
	  * 张昆 2012-05-10
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
	  * 张昆 2012-05-10
	  *	函数说明：ajax操作完成调用此函数
	  */
	var onDataReceived = function(data, textStatus, XMLHttpRequest){
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
 				removeAll();
 				addOldList();
 			}
 			if(data.method==="tijiao"){
 				if (data.result === "OK"){
					alert("保存成功！");
					removeYSJAllColor();
					$("#ppjgTable").html(data.youxiajiaoHtml);
 					removeAll();
 	 				addOldList();
 				}else if(data.result==="ERROR"){
 					if(data.localCode===""){
 						alert("插入数据错误，请查看是否重复插入，若不是请联系管理员");
 					}else{
 						alert("【"+data.localCode+"】 已经匹配过，不允许再次匹配");
 					}
 					
 				}
 			}
 			if(data.method==="befordDel"){
 				var OKNO =confirm("您确定要取消匹配吗？");
 				if(OKNO){
 						var len=del_linshi_Arr.length;
 	 					for(var i=0;i<len;i++){
 	 						var aa=	del_linshi_Arr.pop();
 	 						delList(aa);
 	 						var zcl_xz$=$("tr[name^='youxiajiao'][id='"+aa+"']");
 	 						zcl_xz$.remove();
 	 					}
 	 					delStr="";
 					}else{
 						del_linshi_Arr=new Array();
 					}
 			}
	};

	//更新左侧栏匹配内容项
	function updateZCLPP(pipei){
		var zcl$=$("tr[name^='zuocelan']");
		var zclleng=zcl$.length;
		for(var i=0;i<zclleng;i++){
			var tr=zcl$[i];
			if(tr.bgColor==clickcolor){
				zcl$[i].childNodes[4].childNodes[0].innerHTML=pipei;
			}
		}
	}
	//清楚右上角所有行的颜色
	function removeYSJAllColor(){
		//取出右上角选中行
		var ysj$=$("tr[name^='youshangjiao']");
		var ysjleng=ysj$.length;
		for(var i=0;i<ysjleng;i++){
			var tr=ysj$[i];
			tr.bgColor=recoveryColor;
		}
	}
	//记录行变颜色函数
	function onclickColor(event){
		if(tempColor!=clickcolor)
			{
		  		event.bgColor=clickcolor;
		  		tempColor=event.bgColor;
		  	}
		  	else{
		  		event.bgColor=recoveryColor;
		  		tempColor=event.bgColor;
		  	}
	}
	function changeColor(event){
		tempColor=event.bgColor;
		event.bgColor=highlightcolor;
	}
	
	function recoverColor(event){
		event.bgColor=tempColor;
	}
	function tiaozhuan(aa){
		var auto=$(":radio[name='zccx'][checked]").val();
		var zxksbm$=$("#zctext");
		var zxksbm_0=zxksbm$[0].value+"";
		if(auto=="1"){
			zxksbm_ajax=zxksbm_0;
			}
		if(auto=="2"){
			zxksmc_ajax=zxksbm_0;
			}
		if(auto=="3"){
			zxkspy_ajax=zxksbm_0;
			}
		if(auto=="4"){
			zxkswb_ajax=zxksbm_0;
			}
		operate="changeTable";
		pageNo=aa;
		DBOption();
	}
	function tiaozhuanzhiding(){
		var auto=$(":radio[name='zccx'][checked]").val();
		var zxksbm$=$("#zctext");
		var zxksbm_0=zxksbm$[0].value+"";
		if(auto=="1"){
			zxksbm_ajax=zxksbm_0;
			}
		if(auto=="2"){
			zxksmc_ajax=zxksbm_0;
			}
		if(auto=="3"){
			zxkspy_ajax=zxksbm_0;
			}
		if(auto=="4"){
			zxkswb_ajax=zxksbm_0;
			}
		
		var _pageNo=$("#tiaozhuan_daohang");
		_pageNo=_pageNo[0].value;
		operate="changeTable";
		pageNo=_pageNo;
		DBOption();
	}
	function chaxunycl(){
		var auto=$(":radio[name='yccx'][checked]").val();
		var bdksbm$=$("#yctext");
		var bdksbm_0=bdksbm$[0].value+"";
		if(auto=="1"){
			bdksbm_ajax=bdksbm_0;
			}
		if(auto=="2"){
			bdksmc_ajax=bdksbm_0;
			}
		if(auto=="3"){
			bdkspy_ajax=bdksbm_0;
			}
		if(auto=="4"){
			bdkswb_ajax=bdksbm_0;
			}
		operate="ycchaxun";
		DBOption();	
		bdksbm_ajax="";
		bdksmc_ajax="";
		bdkspy_ajax="";
		bdkswb_ajax="";
	}
	
	function chaxunzcl(){
		var auto=$(":radio[name='zccx'][checked]").val();
		var zxksbm$=$("#zctext");
		var zxksbm_0=zxksbm$[0].value+"";
		if(auto=="1"){
			zxksbm_ajax=zxksbm_0;
			}
		if(auto=="2"){
			zxksmc_ajax=zxksbm_0;
			}
		if(auto=="3"){
			zxkspy_ajax=zxksbm_0;
			}
		if(auto=="4"){
			zxkswb_ajax=zxksbm_0;
			}
		operate="zcchaxun";
		DBOption();
		zxksbm_ajax="";
		zxksmc_ajax="";
		zxkspy_ajax="";
		zxkswb_ajax="";	
	}
//左侧只允许点击一次
	function zuocelanOnclick(event){
		if(event.bgColor==clickcolor){
			isHaveSel=true;
		}else{
			isHaveSel=false;
		}
		var trs=$("tr[name^='zuocelan']");
		var trsleng=trs.length;
		for(var i=0;i<trsleng;i++){
			var tr=trs[i];
			tr.bgColor=recoveryColor;
		}
		if(isHaveSel){
			event.bgColor=recoveryColor;
			tempColor=event.bgColor;
		}else{
			event.bgColor=clickcolor;
			tempColor=event.bgColor;
		}
		for(var i=0;i<trsleng;i++){
			var tr=trs[i];
			if(tr.bgColor==clickcolor){
				zcl_ajax=tr.id;
			}
		}
		operate="xuanzhongzcl";//选中左侧栏
		DBOption();
		zcl_ajax="";
	}
	
	/**
	 * 添加
	 * 2011-05-11
	 */
	function tianjia(){
		//取出错侧栏选中行
		var zcl$=$("tr[name^='zuocelan']");
		var zclleng=zcl$.length;
		for(var i=0;i<zclleng;i++){
			var tr=zcl$[i];
			if(tr.bgColor==clickcolor){
				zcl_ajax=tr.id;
			}
		}
		var ycl_array_zx=new Array;
		var ycl_array_dx=new Array;
		//取出右上角选中行
		$("#youshangjiao").html();
		var ysj$=$("tr[name^='youshangjiao']");
		var ysjleng=ysj$.length;
		for(var i=0;i<ysjleng;i++){
			var tr=ysj$[i];
			if(tr.bgColor==clickcolor){
				ycl_array_zx.push(zcl_ajax+"_and_"+tr.id);
				ysj_y=tr.id;
			}
		}
		
		if(ycl_array_zx.length==0||ysj_y==""){
			alert("请选择需要匹配的路径!");
		}else{
			
			var ycl_array_zxlen=ycl_array_zx.length;
			for(var jj=0;jj<ycl_array_zxlen;jj++){
				var aa=ycl_array_zx.pop();
				var isSuc=addList(aa);
				if(!isSuc){
					var ycl_array_dx_length=ycl_array_dx.length;
					for(var kk=0;kk<ycl_array_dx_length;kk++){
						var isDelSuc=delList(ycl_array_dx.pop());
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
				var zcl_xz$=$("tr[name^='youshangjiao'][id='"+ee+"']");
				var mingcheng=zcl_xz$.attr('mingcheng');
				trcontent=trcontent+"<tr name='youxiajiao'  style='cursor:hand'  id='"+zcl_ajax+"_and_"+ee+"' bgcolor='#FFFFFF' onclick='onclickColor(this)' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
				"<td><div align='center'>"+zcl_ajax+"</div></td>" +
				"<td><div align='center'>"+mingcheng+"</div></td>" +
				"<td><div align='center'>"+ee+"</div></td>" +
				"</tr>";
			}
			$( "#ppjgTable" ).append(trcontent);	
			zcl_ajax="";
			qingchuyoushangjiao();
		}
	}
	//删除匹配项
	function shanchu(){
		var yxj$=$("tr[name^='youxiajiao']");
		var yxjleng=yxj$.length;
		var len=0;
		for(var i=0;i<yxjleng;i++){
			var tr=yxj$[i];
			if(tr.bgColor==clickcolor){
				len++;
				del_linshi_Arr.push(tr.id);
				delStr=delStr+tr.id+":;";
				//delStr="";
			}
		}
		operate="delMatchCP";
		DBOption();
		if(len==0){
			alert("请选择需要取消匹配的路径！");
		}
	}
	/**
	 *  添加匹配 如果存在这个值，那么返回false，并且不存入内存
	 * @param id
	 * @returns {Boolean}
	 * date  2012-05-11
	 */
	function addList(id){
		var nowExist=oldRemainMap.containsKey(id);//判断原来是否已经有了此匹配
		if(!nowExist){//如果没有匹配
			var addExist=addmap.containsKey(id);
			if(!addExist){
				addmap.put(id, id);//添加
				var delExist=delmap.containsKey(id);//判断已删除的里面是否有此匹配
				if(delExist){
					delmap.remove(id);
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
	 * date 2012-05-11
	 */
	function delList(id){
		var nowExist=oldRemainMap.containsKey(id);
		if(nowExist){
			oldRemainMap.remove(id);
			delmap.put(id, id);
			return true;
		}else{
			var addExist=addmap.containsKey(id);
			if(addExist){
				addmap.remove(id);
				delmap.put(id, id);
				return true;
			}
			return false;
		}
	}
	function addOldList(){
		var youxiajiao$=$("tr[name^='youxiajiao']");
		var youxiajiaolen=youxiajiao$.length;
		for(var i=0;i<youxiajiaolen;i++){
			var tr=youxiajiao$[i];
			oldRemainMap.put(tr.id, tr.id);
			oldMap.put(tr.id, tr.id);
		}
	}
	/**
	 * 清空所有 匹配
	 */
	function removeAll(){
		oldRemainMap.clear();
		addmap.clear();
		delmap.clear();
		oldMap.clear();
	}
	function tijiao(){
		var addarr=addmap.values();
		var delarr=delmap.values();
		var addarr_len=addarr.length;
		var delarr_len=delarr.length;
		for(var i=0;i<addarr_len;i++){
			var add=addarr.pop();
			if(!oldMap.containsKey(add)){
				yxj_add_ajax=yxj_add_ajax+add+":;";
			}
		}
		for(var i=0;i<delarr_len;i++){
			var del=delarr.pop();
			if(oldMap.containsKey(del)){
				yxj_del_ajax=yxj_del_ajax+del+":;";
			}
		}
		if(yxj_add_ajax==""&&yxj_del_ajax==""){
			alert("数据和原来一样，不需要提交");
			return ;
		}
		operate="tijiao";//选中左侧栏
		DBOption();
		yxj_add_ajax="";
		yxj_del_ajax="";
	}
	/**
	 * 清楚右上角的颜色
	 */
	function qingchuyoushangjiao(){
		var ysj$=$("tr[name^='youshangjiao']");
		var ysjleng=ysj$.length;
		for(var i=0;i<ysjleng;i++){
			var tr=ysj$[i];
			tr.bgColor=recoveryColor;
			
		}
	}
	