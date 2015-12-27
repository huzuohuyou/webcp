/*----------------------------------------------------------------
//Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
//文件名：nodetab.js
//文件功能描述：节点内详细定义内容js
//创建人:潘状
//创建:2011/08/25
//----------------------------------------------------------------*/



/**
 * 添加输出节点  按钮单击事件
 */


$(function() {
	
$("#wait").dialog("open");
$("#frag-1").load("frag.jsp", {cp_id : cp_id,cp_node_id:trId,op:"frag-1"},hide );


$("#tabs").tabs();

	 $('#tabs').bind('tabsselect', function(event, ui) {
		 onclickdoctorpoint_quanju=false;
		 event.stopPropagation(); 
    	if(ui.index==0){
    	 }else if(ui.index==1){
    		 $("#wait").dialog("open");
	    		$("#frag-2").load("frag.jsp", {cp_id : cp_id,cp_node_id:trId,op:"frag-2"}, hide);
	    	
	     }else if(ui.index==2){
	    	 $("#wait").dialog("open");
	    	 $("#orderjldw").load("../servlet/managecp?op=Option&ops=orderjldw");
	    	 $("#yanneiOrderSelect").load("../servlet/managecp?op=Option&ops=yanneiOrderType");
	    	 $("#orderPointName").load("../servlet/managecp?op=Option&ops=orderPointName");
	    		$("#frag-3").load("frag.jsp", {cp_id : cp_id,cp_node_id:trId,op:"frag-3"}, hide );
	     }else if(ui.index==3){
	    	 $("#wait").dialog("open");
	    	$("#frag-4").load("frag.jsp", {cp_id : cp_id,cp_node_id:trId,op:"frag-4"}, hide);
	     }else if(ui.index==4){
	    	 $("#wait").dialog("open");
	    		$("#frag-5").load("frag.jsp", {cp_id : cp_id,cp_node_id:trId,op:"frag-5"}, hide ); 
    	 }else{
	    	alert("请选择左侧节点!");
	    	}
    	
    	}); 

});

