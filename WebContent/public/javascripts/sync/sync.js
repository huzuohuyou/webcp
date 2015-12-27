/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：publish.js
// 文件功能描述：publish.jsp的js代码
// 创建人：刘植鑫 
// 创建日期：2011/07/29
// 
//----------------------------------------------------------------*/
var isUpdate=false;
var isSelectAll=true;
var operate="getAllTable";
var msg = "";
var bz="";
var ajaxIsSuc=false;//ajax调用是否正确
var dbOperSuc=false;//数据库操作是否成功
var pageNo=1;
var id=-1;
/**
 * 	刘植鑫 2011-04-19
 *	函数说明：取得表格的内容并且发送到后台的servlet
 */
function selectOrPublish(){
	$.ajax({
	    url: "../servlet/updateData",
	    type: 'POST',
	    async:false,
	    data: {op : operate,
	    	pageNo: pageNo,
	    	msg:  encodeURI(msg)
	    	},
	    dataType: "json",
	    complete: show_result,
	    success: onDataReceived
	  });
}
/**
 * 	刘植鑫 2011-04-20
 *	函数说明：ajax操作完成调用此函数
 */
var show_result = function(XMLHttpRequest, textStatus){
	delId_one=-1;
	delId_all="";
	msg = "";
	if(textStatus == "error"){
	 	msg = "请求出错！";
	 	ajaxIsSuc=false;
	}
	else if(textStatus == "timeout"){
	 msg = "请求超时！";
	 ajaxIsSuc=false;
	}
	else if(textStatus == "parsererror"){
	 msg = "JSON数据格式有误！";
	 ajaxIsSuc=false;
	}else if (textStatus != "success"){
	 alert(msg);
	 ajaxIsSuc=false;
	}else if(textStatus == "success"){
	 ajaxIsSuc=true;
	}
};
/**
 * 	刘植鑫 2011-04-20
 *	函数说明：ajax操作成功调用此函数
 */
var onDataReceived = function(data, textStatus, XMLHttpRequest){
	data = eval(data);
	data = data[0];
			if(data.method==="getAllTable"){
				dbOperSuc=true;
				$("#cpUnPublish").html(data.cpUnPublish);
				$("#updateList").html(data.updateList);
				$("#updateListPageHtml").html(data.updateListPageHtml);
			}
			if(data.method==="publish"){
				if(data.result==="OK"){
					$("#cpUnPublish").html("");
					$("#updateList").html(data.updateList);
					$("#updateListPageHtml").html(data.updateListPageHtml);
					alert("路径发布成功");
				}else{
					alert("路径发布失败");
				}
			}
			if(data.method==="chagePageHistory"){
				if(data.flag==1){
					dbOperSuc=true;
					$("#updateList").html(data.updateList);
					$("#updateListPageHtml").html(data.updateListPageHtml);
				}
				else{
					dbOperSuc=true;
				}
			}
};

$(function() {
	operate="getAllTable";
	selectOrPublish();
	$( "#detail" ).dialog({
		title:"详细信息",
		autoOpen: false,
		modal: true,
		height:300,
		width:600,
		minHeight:300,
		minWidth:600,
		resizable:false,
		draggable:true,
		close: function(event, ui) { $('#detail').html(''); },
		open: function(){
             $('#detail').html('<iframe src="publish_detail.jsp?id='+id+'&isHaveButton=true" frameborder="0" height="100%" width="100%" id="dialogFrame"></iframe>');
         }
		});
	$( "#div2" ).dialog({
		title:"备注信息",
		autoOpen: false,
		height: 280,
		width: 370,
		modal: true,
		buttons: {
			"确定": function() {
				publish1();
				$( this ).dialog( "close" );
			},
			"取消": function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
		}
	}		
	);
});
//打包发布
function publish1(){
	msg=$("#message").val();
//	alert(msg);
	operate="publish";
	selectOrPublish();
}
//打包发布
function publish(){
	$("#div2").dialog("open");
}
var  highlightcolor='#d5f4fe';
var  clickcolor='#51b2f6';
var recoveryColot='#FFFFFF';
/**
 * 	刘植鑫 2011-04-19
 *	函数说明：表格行变色
 */
function changeColor(event){
	event.style.backgroundColor=highlightcolor;
}
/**
 * 	刘植鑫 2011-04-19
 *	函数说明：表格行恢复颜色
 */
function recoverColor(event){
	event.style.backgroundColor=recoveryColot;
}
function cpPublishHistorytiaozhuan(aa){
	operate="chagePageHistory";
	pageNo=aa;
	selectOrPublish();
}
function cpPublishHistorytiaozhuanzhiding(){
	var _pageNo=$("#cpPublishHistory_daohang");
	_pageNo=_pageNo[0].value;
	operate="chagePageHistory";
	pageNo=_pageNo;
	selectOrPublish();
}
function tiaozhuan(aa){
	operate="chagePageHistory";
	pageNo=aa;
	selectOrPublish();	

}
function tiaozhuanzhiding(){
	var _pageNo=$("#tiaozhuan_daohang");
	_pageNo=_pageNo[0].value;
	pageNo=_pageNo;
//	alert(pageNo);
	operate="chagePageHistory";
	selectOrPublish();	
}
function showDetail(obj){
	id=obj;
	$( "#detail").dialog( "open" );	
}