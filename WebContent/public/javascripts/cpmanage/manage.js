var cpID=cp_id_liu;
var cp_node_table_id_quanju="";//一级菜单ID
var cp_node_id_quanju="";
var onclickdoctorpoint_quanju=false;
var onclick1point_quanju=false;//判断一级菜单
var onclick2point_quanju=false;//判断二级菜单
var cpOrderID2="";//二级菜单ID

var  highlightcolor='#d5f4fe';
var  clickcolor='#51b2f6';
var recoveryColor='#FFFFFF';
var bgcolor="#51b2f6";
var tempColor="";
var cpNodeID="";
var auto=1;

//行变色函数
function changeColor(event){
	tempColor=event.bgColor;
	event.bgColor=highlightcolor;
	}

function recoverColor(event){
	event.bgColor=tempColor;
	tempColor=recoveryColor;
	}

var back = function(){
	//alert("--------");
    history.back();
};
//单击选择节点,行变色,加载右侧信息	
/*function NodeColor(event){
	$("#wait").dialog("open");
	var trs=$(".selectNode");
	var trsleng=trs.length;
	for(var i=0;i<trsleng;i++){
		var tr=trs[i];
		tr.bgColor=recoveryColor;
	}
	event.bgColor=clickcolor;
	tempColor=event.bgColor;
	cpNodeID=event.id;
	 $("#tabsLoad").load("nodetab.jsp",{cp_id:cpID,cp_node_id:event.id},hide);
}*/

//隐藏正在加载信息
function hide(){
	$( "#wait" ).dialog( "close" );
}




/**
 *  修改保存诊断方案依据,治疗方案依据
 *  修改保存预防性抗菌药物选择与使用时机
 */	
function antibiotics_based(tableName){
	var cp_diagnosis_based=encodeURI($("#cp_diagnosis_based").val(),"utf-8");//获取诊断方案依据
	var cp_treatment=encodeURI($("#cp_treatment").val(),"utf-8");//获取治疗方案依据
	var cp_antibiotics=encodeURI($("#cp_antibiotics").val(),"utf-8");//获取预防性抗菌药物选择与使用时机
	$.ajax({	   
		url : "../servlet/managecp",
		type : 'POST',
		data : {
			op : "updata_antibiotics_based",
			tableName:tableName,
			cp_id : cpID,
			CP_DIAGNOSIS_BASED:cp_diagnosis_based,
			CP_TREATMENT:cp_treatment,
			CP_ANTIBIOTICS:cp_antibiotics
		},
		dataType : "json",
		complete :show_result ,
		success :function(data, textStatus, XMLHttpRequest) {
				data = eval(data);
				data = data[0];
				 if (data.result === "OK") {
					 alert("保存信息成功!");
				} else			alert("无效操作");
			} 	
	});}


var outNodeID="";
var outNodeText="";
function outNodeColor(event){
	var trs=$(".selectOutNode");
	var trsleng=trs.length;
	for(var i=0;i<trsleng;i++){
		var tr=trs[i];
		tr.bgColor=recoveryColor;
	}
	event.bgColor=clickcolor;
	tempColor=event.bgColor;
	outNodeID= event.id;
	outNodeText= $(event).children().eq(1).html();
	
	
	
};



$(function() {
	$( "#wait" ).dialog({
		autoOpen: false,
		height: 350,
		width: 350,
		modal: true,
		title:"信息正在加载中,请稍候……"
		});
	$("#wait").dialog("open");
	/*$("#wait").ajaxStart(function(){
		$(this).show();
	}).ajaxStop(function(){
		$(this).hide();
	});*/
	
    $("#tabs1").tabs();
    $('#tabs1').bind('tabsselect', function(event, ui) {
    	if(cpID!=0){
    if(ui.index==3){
    		 $("#tabs").tabs('select', 0);
    	}}
    	});

/*********************************自动填表初始化   start   ***************************************/	    	
	var opertion={
	    	delay:400,
	    	 max: 12,    //列表里的条目数
	         minChars: 1,   //自动完成激活之前填入的最小字符
	         width: 400,     //提示的宽度，溢出隐藏
	         scrollHeight: 300,   //提示的高度，溢出显示滚动条
	         matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
	         cacheLength:10,  //缓存长度
	         autoFill: false,    //自动填充
	         parse: function(data) { 
	        		data = eval(data);
	          	   	var rows = [];   
	             for(var i=0; i<data.length; i++){   
	               rows[rows.length] = {   
	            		   data:data[i].code+"--"+data[i].name +"--"+data[i].input,   
	                 value:data[i].code+"--"+data[i].name,   
	                 result:data[i].input 
	                 };   }    
	             return rows;   
	              },   
	           formatItem: function(row, i, n) {   
	            	 return row;
	           } };
	var opertionCode={
	    	delay:400,
	    	 max: 12,    //列表里的条目数
	         minChars: 1,   //自动完成激活之前填入的最小字符
	         width: 400,     //提示的宽度，溢出隐藏
	         scrollHeight: 300,   //提示的高度，溢出显示滚动条
	         matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
	         cacheLength:10,  //缓存长度
	         autoFill: false,    //自动填充
	         parse: function(data) { 
	        		data = eval(data);
	          	   	var rows = [];   
	             for(var i=0; i<data.length; i++){   
	               rows[rows.length] = {   
	            		   data:data[i].code+"--"+data[i].name ,   
	                 value:data[i].code+"--"+data[i].name,   
	                 result:data[i].code 
	                 };   }    
	             return rows;   
	              },   
	           formatItem: function(row, i, n) {   
	            	 return row;
	           } };
	
	
	$("#dept_name_input").autocomplete("../servlet/auto?ops=py&op=dept",opertion);
	$("#dept_name_input").result(function(event, data, formatted){
			formatted=formatted.split("--");
		 $("#dept_name_input").attr("value",formatted[1]);
		$("#dept_code_input").attr("value",formatted[0]);
	});
	 $("#diagnosisPY").autocomplete("../servlet/auto?ops=py&op=diagnosis",opertion);
	 $("#diagnosisWB").autocomplete("../servlet/auto?ops=wb&op=diagnosis",opertion);
	 $("[name='diagnosisInput']").result(function(event, data, formatted){
			formatted=formatted.split("--");
		 $("#diagnosisCode").attr("value",formatted[1]);
		$("#diagnosisName").attr("value",formatted[0]);
});
	 $("#diagnosisName").autocomplete("../servlet/auto?ops=codes&op=diagnosisCode",opertionCode).result(function(event, data, formatted){
		 formatted=formatted.split("--");
		 $("#diagnosisCode").attr("value",formatted[1]);
	 });
	 $("#operationPY").autocomplete("../servlet/auto?ops=py&op=operation",opertion);	
	 $("#operationWB").autocomplete("../servlet/auto?ops=wb&op=operation",opertion);	
	 $("[name='operationInput']").result(function(event, data, formatted){
			formatted=formatted.split("--");
		 $("#operationCode").attr("value",formatted[1]);
		$("#operationName").attr("value",formatted[0]);
});
	 
	 $("#operationName").autocomplete("../servlet/auto?ops=codes&op=operationCode",opertionCode).result(function(event, data, formatted){
		 formatted=formatted.split("--");
		 $("#operationCode").attr("value",formatted[1]);
	 });
	
	 /******************************自动填表初始化   end   ***************************************/	  	 
	
	 
	 
	

function selectAjax(cp_id){
	$.ajax({	   
		url : "../servlet/managecp",
		type : 'POST',
		async:true,
		data : {
			op : "select",
			cp_id : cp_id
		},
		dataType : "json",
		complete : show_result,
		success : onDataReceived
	});
	
}
$("#edit_cp").click(function(){//编辑路径
	$("#cp_name").val($("#cp_name_input").val());
	$("#min_day").val($("#cp_days_min").val());
	$("#max_day").val($("#cp_days_max").val());
	$("#avg_day").val($("#cp_days_input").val());
	$("#avg_fee").val($("#cp_fee_input").val());
	$("#cp_code").val($("#cp_code_input").val());
	$("#dept_name_input").val($("#dept_name").val());
	$("#dept_code_input").val($("#dept_code").val());
	$("#cp_pym").val($("#pym_input").val())	;
	$("#cp_health_care_quota").val($("#health_care_quota").val())	;
	
	
	$( "#dialog-form-main" ).dialog("open");
	$( "#dialog-form-main" ).dialog({
		open:function(){$("#cp_name")[0].focus();},
		buttons: {
			"确定": function() {
				$.ajax({	   
					url : "../servlet/managecp",
					type : 'POST',
					data : {
						op : "editCP",
						cp_id:$("#cp_id_input").val(),
						audit:"0",
						cp_name : encodeURI($("#cp_name").val(),"utf-8"),
						min_day:$("#min_day").val(),
						max_day:$("#max_day").val(),
						avg_day:$("#avg_day").val()	,
						avg_fee:$("#avg_fee").val()	,
						cp_code:$("#cp_code").val()	,
						cp_pym:$("#cp_pym").val()	,
						cp_health_care_quota:$("#cp_health_care_quota").val()	,
						dept_name:encodeURI($("#dept_name_input").val(),"utf-8"),
						dept_code:$("#dept_code_input").val()
					},
					dataType : "json",
					complete :show_result ,
					 	success :function(data, textStatus, XMLHttpRequest) {
					 		
						data = eval(data);
				 if (data.result === "OK") {
						alert("修改路径信息成功!");	
						$("#cp_name_input").val($("#cp_name").val());
						$("#cp_days_min").val($("#min_day").val());
						$("#cp_days_max").val($("#max_day").val());
						$("#cp_days_input").val($("#avg_day").val());
						$("#cp_fee_input").val($("#avg_fee").val());
						$("#dept_name").val($("#dept_name_input").val());
						$("#dept_code").val($("#dept_code_input").val());
						
						$("#cp_code_input").val($("#cp_code").val());
						$("#cp_fee_input").val($("#avg_fee").val());
						$("#pym_input").val($("#cp_pym").val());
						$("#health_care_quota").val($("#cp_health_care_quota").val());
						}else			alert("无效操作");
					 }	});
				$( this ).dialog( "close" );			
			},
			"取消": function() {
				$( this ).dialog( "close" );
			}
		}
	});
});

selectAjax(cpID);//加载路径基本信息



});
 
 /******************--------------the code start-------------------***************************/
 
//添加新的节点窗口
 function addNode(){
	 $( "#dialog-addNode" ).dialog("open");
	 $( "#dialog-addNode-txt1" ).attr("value","");
	 $( "#dialog-addNode-txt2" ).attr("value","");
	 $( "#dialog-addNode" ).dialog({
		buttons: {
		   "添加": function() {
			 var nodeName=$( "#dialog-addNode-txt1" ).val();
			 var nodeDate=$( "#dialog-addNode-txt2" ).val();
			 var nodeType=$( "#dialog-addNode-select" ).val();
			  $.ajax({	   
					url : "../servlet/managecp",
					type : 'POST',
					data : {
						op : "addNode",
						cp_id:cpID,
						nodeName : encodeURI(nodeName,"utf-8"), 
						nodeDate:nodeDate,
						nodeType:nodeType
					},
					dataType : "json",
					complete :show_result ,
					success :function(data, textStatus, XMLHttpRequest) {
						data = eval(data);
						 if (data.result === "OK") {
								var toHtml="<tr height='20' bgcolor='#FFFFFF' onMouseOver='changeColor(this)' onMouseOut='recoverColor(this)' onClick='NodeColor(this)'  class='selectNode' style='cursor:pointer' name='lcp_master_node_tr' id='"+data.id+"'>"+
								"<td  class='STYLE10' align='center'>"+data.id+"</td>"+
						        "<td  class='STYLE10' align='left'>&nbsp;&nbsp;&nbsp;"+nodeName+"</td>"+
						        "<td  class='STYLE10' align='center'>"+nodeDate+"</td></tr>";
						      $("#lcp_master_node_tbody").append(toHtml);
							} else	alert("无效操作");
						} 
				}); 
				$( this ).dialog( "close" );
		},
		"取消": function() {
			$( this ).dialog( "close" );
		}
	}
});
}
 

 
 //删除节点按钮
 function delNode(){
  if(confirm("您确定要删除此节点?如果确定,请先清空节点中所有的内容?再删除该节点!")){
	$.ajax({	   
		url : "../servlet/managecp",
		type : 'POST',
		data : {
			op : "delNode",
			cp_id:cpID,
			cp_node_id:cpNodeID
		},
		dataType : "json",
		complete :show_result ,
		success :function(data, textStatus, XMLHttpRequest) {
			data = eval(data);
			if (data.result === "OK") {
				$(".selectNode[id='"+cpNodeID+"']").remove();//删除当前行
			}else if(data.result === "nodel"){
				alert("该节点不能删除!");
				return false;
			}else { alert("删除数据失败!");	}
				
			}
	});}}
/******************--------------the code end-------------------***************************/
 
/********删除选定的行  根据checkbox的name 与tr的name******---the code start---****************************************************/
/***
 * checkboxName  要删除的表的名称
 * colName 	  要删除的列的名称  需要传入到服务器中使用
 */
function delTrs(tableName,colName){
 var range="";
 $( ":checkbox[name='"+tableName+"_checkbox']" ). each ( function() {
	 if($(this).attr('checked')==true){			 
		var temp=$(this).attr("id");
		range+=temp;
	 }		
	 });
//	alert(range);	
 if(confirm("您确定要删除所选择的数据?")){
	$.ajax({	   
		url : "../servlet/managecp",
		type : 'POST',
		data : {
			op : "delTrs",
			cp_id:cpID,
			cp_node_id:cpNodeID,
			colName:colName,
			range : range,
			tableName:tableName
		},
		complete :show_result ,
		success :function(data, textStatus, XMLHttpRequest) {
			data = eval(data);
			data=data[0];
			if (data.result === "OK") {
			 $( ":checkbox[name='"+tableName+"_checkbox']" ). each ( function() {
				 if($(this).attr('checked')==true){			 
					var temp=$(this).attr("id");
				$("tr[id='"+temp+"'][name='"+tableName+"_tr']").remove();//删除当前行
				 }		
				 });
				} else	alert("无效操作");
			}
	});}}
		/*********************************----the code end----************************************************/

//调用窗口  增加列数为2的通用方法
//添加路径排除条件/添加变异记录/添加出院标准/添加常见变异
function dialogTwoCol(tbodyName){
	var tableName=tbodyName.replace("_tbody","");
	$( "#dialog-form-2" ).dialog("open");
	$( "#dialog-form-2" ).dialog({
		open:function(){$("#dialog-form-2-tex").attr("value","");},
	buttons: {
		"添加": function() {
		$.ajax({	   
			url : "../servlet/managecp",
			type : 'POST',
			data : {
				op : "addTwoCol",
				tableName:tableName,
				cp_id : cpID,
				cp_node_id:cpNodeID,
				datas:encodeURI($("#dialog-form-2-tex").val(),"utf-8")
			},
			dataType : "json",
			complete :show_result ,
			success : function(data, textStatus, XMLHttpRequest) {
				data = eval(data);
				data = data[0];
				var toHtml="<tr bgcolor='#FFFFFF' onMouseOver='changeColor(this)' onMouseOut='recoverColor(this)' class='STYLE10'"+
				"name='"+tableName+"_tr' id='tr"+data.id+"'>"+
				"<td align='center'><input type='checkbox' name='"+tableName+"_checkbox' id='tr"+data.id+"' /></td>"+            
				"<td align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + $("#dialog-form-2-tex").val() + "</td></tr>" ; 
				if (data.result === "OK") {
					if (data.flag == 1) {
						$( "#" +tbodyName ).append( toHtml);} else {alert("添加数据失败!");}
					} else   {alert("无效操作");   }
				}   });  	
			$( this ).dialog( "close" );		
		},
		"取消": function() {$( this ).dialog( "close" );}
	}
});
}
 //调用窗口  增加列数为3的通用方法
function dialogThreeCol(tbodyName){
	var tableName=tbodyName.replace("_tbody","");
	if(tableName=="lcp_node_doctor_point"){
	$( "#dialogDoctor_point" ).dialog("open");
	$( "#dialogDoctor_point" ).dialog({
		open:function(){
			$("#doctorCode").val("");
			$("#doctorName").attr("value","");
			
		},
	buttons: {
		"添加": function() {
			var need=$(":radio[name='doctor'][checked]").val();//0可选 1 必选
			var code="";
			var name=$("#doctorName").val();
			nurseOrDoctorAjax(tbodyName,cpID,cpNodeID,code,name,need,auto);				
					$( this ).dialog( "close" );		
		},
		"取消": function() {$( this ).dialog( "close" );}}
});
	}else if(tableName=="lcp_node_doctor_item"){
	
		if(!onclickdoctorpoint_quanju){
			alert("请选择主表再进行添加");
			return ;
		}
		$( "#dialogDoctor_item" ).dialog("open");
		$( "#dialogDoctor_item" ).dialog({
			open:function(){
				$("#doctorCode1").attr("value","");
				$("#doctorName1").attr("value","");
				$("#doctorPY1").attr("value","");
				$("#doctorWB1").attr("value","");
				$("#doctorPY1")[0].focus();
			},
		buttons: {
			"添加": function() {
				var need=$(":radio[name='doctor1'][checked]").val();
				var code=$("#doctorCode1").val();
				var name=$("#doctorName1").val();
				var auto=$(":radio[name='doctor_auto'][checked]").val();
				nurseOrDoctorAjax(tbodyName,cpID,cpNodeID,code,name,need,auto);				
						$( this ).dialog( "close" );			
			},
			"取消": function() {$( this ).dialog( "close" );}}
	});
	
	
	}else if(tableName=="lcp_node_nurse_point"){
	
		$( "#dialogNurse_point" ).dialog("open");
		$( "#dialogNurse_point" ).dialog({
			open:function(){
				$("#nurseCode").attr("value","");
				$("#nurseName").attr("value","");
			},
		buttons: {
			"添加": function() {
				var need=$(":radio[name='nurse'][checked]").val();
				var code="";
				var name=$("#nurseName").val();
				nurseOrDoctorAjax(tbodyName,cpID,cpNodeID,code,name,need,auto);				
						$( this ).dialog( "close" );			
			},
			"取消": function() {$( this ).dialog( "close" );}}
	});
	}else if(tableName=="lcp_node_nurse_item"){
		if(!onclickdoctorpoint_quanju){
			alert("请选择主表再进行添加");
			return ;
		}
	$( "#dialogNurse_item" ).dialog("open");
	$( "#dialogNurse_item" ).dialog({
		open:function(){
			$("#nurseCode1").attr("value","");
			$("#nurseName1").attr("value","");
			$("#nursePY1").attr("value","");
			$("#nurseWB1").attr("value","");
			$("#nursePY1")[0].focus();
		},
	buttons: {
		"添加": function() {
			var need=$(":radio[name='nurse1'][checked]").val();
			var code=$("#nurseCode1").val();
			var name=$("#nurseName1").val();
			var auto=$(":radio[name='nurse_auto'][checked]").val();
			nurseOrDoctorAjax(tbodyName,cpID,cpNodeID,code,name,need,auto);				
					$( this ).dialog( "close" );			
		},
		"取消": function() {$( this ).dialog( "close" );}}
});
}
 } 

 function  nurseOrDoctorAjax(tbodyName,cpID,cpNodeID,code,name,need,auto){
	 var tableName=tbodyName.replace("_tbody","");
	 $.ajax({	   
			url : "../servlet/managecp",
			type : 'POST',
			data : {
				op : "addNurseOrDoctor",
				tableName:tableName,
				cp_id : cpID,
				cp_node_id:cpNodeID,
				cp_node_table_id : cp_node_table_id_quanju,
				text:encodeURI(name,"utf-8"),
				code:code,
				need:need,
				auto:auto
			},
			dataType : "json",
			complete :show_result ,
			success : function(data, textStatus, XMLHttpRequest) {
				data = eval(data);
				data = data[0];
				
				if (data.result === "OK") {
					if (data.flag == 1) {
						if(data.tableName==="lcp_node_doctor_point"){
							showdoctorpoint(cpID, cpNodeID);
						}
						if(data.tableName==="lcp_node_doctor_item"){
							showdoctoritem(cpID, cpNodeID, cp_node_table_id_quanju);
						}
						if(data.tableName==="lcp_node_nurse_point"){
							shownursepoint(cpID, cpNodeID);
						}
						if(data.tableName==="lcp_node_nurse_item"){
							shownurseitem(cpID, cpNodeID, cp_node_table_id_quanju);
						}
					}
				} else   {
						alert("无法访问中心服务器,请稍后再次尝试!");   
					
				} 
				
			}   });   
 }
 
 
 function delOrder(){
	 var check$=$("input[name='chekcbox_orderitem']");
		var leng=check$.length;
		var delStr="";
		for(var jj=0;jj<leng;jj++){
			if(check$[jj].checked){
				delStr=delStr+""+check$[jj].id+",";
			}
		}
		if(delStr===""){
			alert("您没有勾选复选框");
			return false;
		}else{
			 if(confirm("您确定要删除吗?")){
			$.ajax({	   
				url : "../servlet/managecp",
				type : 'POST',
				data : {
					op : "del_lcp_node_order_item",
					delids : delStr
				},
				dataType : "json",
				complete :show_result ,
				success : function(data, textStatus, XMLHttpRequest) {
					data = eval(data);
					data = data[0];
					if(data.result==="OK"){
						$("#order_item_table").html(data.table);
						alert("删除成功");
					}else{
						alert("删除失败,请检查选中项目中是否包含存组项目,若有请撤组后再删除！");
					}
					
				}
			});
		}
		}
		
		
 }

	function addOrderGroup(){
		 var check$=$("input[name='chekcbox_orderitem']");
			var leng=check$.length;
			var addIds="";
			for(var jj=0;jj<leng;jj++){
				if(check$[jj].checked){
					addIds=addIds+""+check$[jj].id+",";
				}
			}
			if(addIds===""){
				alert("您没有勾选复选框");
				return false;
			}else{
				 if(confirm("您确定要存组吗?")){
				
				$.ajax({	   
					url : "../servlet/managecp",
					type : 'POST',
					data : {
						op : "addOrderItemGroup",
						addIds : addIds
					},
					dataType : "json",
					complete :show_result ,
					success : function(data, textStatus, XMLHttpRequest) {
						data = eval(data);
						data = data[0];
						if(data.result==="OK"){
							$("#order_item_table").html(data.table);
						}else{
							alert("存组失败");
						}
						
					}
				});
			}
			}
 
	}
	/**
	 * 撤销分组
	 */
	function cancelGroup(){
		 var check$=$("input[name='chekcbox_orderitem']");
			var leng=check$.length;
			var ids="";
			var str="";
			for(var jj=0;jj<leng;jj++){
				if(check$[jj].checked){
					ids=check$[jj].id;
					str=str+""+check$[jj].id+",";
				}
			}
			var text = $("#"+ids).children().eq(2).html();
		 	 			if(ids===""){
		 					alert("您没有勾选复选框");
		 					return false;
		 				}else if(text === "无"){
		 					alert("您还没有存组！");
		 					return false;
		 				}else{
		 					 if(confirm("您确定要撤组吗?")){
		 					
		 					$.ajax({	   
		 						url : "../servlet/managecp",
		 						type : 'POST',
		 						data : {
		 							op : "cancelOrderItemGroup",
		 							ids : ids,
		 							str:str
		 						},
		 						dataType : "json",
		 						complete :show_result ,
		 						success : function(data, textStatus, XMLHttpRequest) {
		 							data = eval(data);
		 							data = data[0];
		 							if(data.result==="OK"){
		 								$("#order_item_table").html(data.table);
		 							}else{
		 								alert("撤组失败");
		 							}
		 						}
		 					});
		 				}
		 	 	}
	}

var show_result = function(XMLHttpRequest, textStatus){	
	var msg;
	if(textStatus == "error"){msg = "请求出错！";ajaxIsSuc=false;}
	else if(textStatus == "timeout"){ msg = "请求超时！"; ajaxIsSuc=false;}
	else if(textStatus == "parsererror"){ msg = "JSON数据格式有误！"; ajaxIsSuc=false;
	}else if (textStatus != "success"){ ajaxIsSuc=false;
	}else if(textStatus == "success"){	 ajaxIsSuc=true;	}
};

//处理加载页面时载入的信息
var onDataReceived = function(data, textStatus, XMLHttpRequest){
		data = eval(data);
		data = data[0];
		$("#cp_id_input").val(data.cp_id);
		$("#cp_code_input").val(data.cp_code);
		$("#cp_name_input").val(data.cp_name);
		$("#cp_create_date_input").val(data.cp_create_date);
		$("#cp_version_input").val(data.cp_version);
		$("#cp_days_input").val(data.cp_days);
		$("#cp_version_input").val(data.cp_version);
		$("#user_name_input").val(data.cp_create_name);
		$("#cp_version_date_input").val(data.cp_version_date);
		$("#cp_fee_input").val(data.cp_fee);
		$("#cp_days_max").val(data.cp_days_max);
		$("#cp_days_min").val(data.cp_days_min);
		$("#dept_name").val(data.dept_name);
		$("#dept_code").val(data.dept_code);
		$("#pym_input").val(data.pym);
		$("#health_care_quota").val(data.health_care_quota);
		
		$("#cp_treatment").html(data.cp_treatment);//填充治疗方案依据
		$("#cp_diagnosis_based").html(data.cp_diagnosis_based);//填充诊断方案依据
		$("#cp_antibiotics").html(data.cp_antibiotics);//填充抗菌方案依据 */

		if($.browser.msie){
			$("#cp_treatment").html(data.cp_treatment);//填充治疗方案依据
			$("#cp_diagnosis_based").html(data.cp_diagnosis_based);//填充诊断方案依据
			$("#cp_antibiotics").html(data.cp_antibiotics);//填充抗菌方案依据 */
		}else{
			var regS = new RegExp("<br/>","g");
			var zhenduan=data.cp_diagnosis_based;
			var zhiliao=data.cp_treatment;
			var kangjun=data.cp_antibiotics;
			zhenduan=zhenduan.replace(regS,"\r\n");
			zhiliao=zhiliao.replace(regS,"\r\n");
			kangjun=kangjun.replace(regS,"\r\n");
			$("#cp_diagnosis_based").html(zhenduan);
			$("#cp_treatment").html(zhiliao);
			$("#cp_antibiotics").html(kangjun);
		}
		
		hide();
		$("#fragment-2").load("income.jsp", {cp_id : cpID});
		$("#fragment-6").load("discharge.jsp", {cp_id : cpID});
		$("#fragment-7").load("variation.jsp", {cp_id : cpID});
		$("#master_left").load("nodelist.jsp", {cp_id : cpID});
};


function useAjax(operate,type,name,code){
	
	$.ajax({	   
		url : "../servlet/managecp",
		type : 'POST',
		data : {
			op : operate,
			cp_id : cpID,
			CP_DIAGNOSE_TYPE:encodeURI(type,"utf-8"),
			CP_DIAGNOSE_NAME:encodeURI(name,"utf-8"),
			CP_DIAGNOSE_CODE:code
		},
		dataType : "json",
		complete :show_result ,
		success : function(data, textStatus, XMLHttpRequest) {
			data = eval(data);
			data = data[0];
			if (data.result === "OK") {
		var toHtml="<tr bgcolor='#FFFFFF' onMouseOver='changeColor(this)' onMouseOut='recoverColor(this)' class='STYLE10' "+
				"name='lcp_master_income_tr' id='tr"+data.id+"'>"+
				"<td align='center'><input type='checkbox' name='lcp_master_income_checkbox' id='tr"+data.id+"' /></td>"+            
				"<td align='center'>" + type + "</td>" + 
				"<td align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +name + "</td>" + 
				"<td align='center'>" +code + "</td></tr>";
					$( "#lcp_master_income_tbody" ).append( toHtml); 
			} else	alert("无效操作");
		}	});
}


/*****************************弹窗初始化代码*****************************************************************/
$(function(){
	$( "#dialog1" ).dialog({
		open:function(){
					 $("#diagnosisCode").attr("value","") ;
					$("#diagnosisName").attr("value","") ;
					$("#diagnosisPY").attr("value","") ;
					$("#diagnosisWB").attr("value","") ;
					$("#diagnosisPY")[0].focus();
				}, 
		autoOpen: false,
		height: 430,
		width: 370,
		modal: true,
		title:'添加诊断信息', 
		buttons: {
	"添加": function() {
			var operate="add_lcp_master_income";
			useAjax(operate,"诊断",$("#diagnosisCode").val(),$("#diagnosisName").val());
			$( this ).dialog( "close" );			
	},
	"取消": function() {	$( this ).dialog( "close" );}	
	}
});
	
	
	
$( "#dialog11" ).dialog({
		open:function(){
					 $("#operationCode").attr("value","") ;
					$("#operationName").attr("value","") ;
					$("#operationPY").attr("value","") ;
					$("#operationWB").attr("value","") ;
					$("#operationPY")[0].focus();
				}, 
		autoOpen: false,
		height: 430,
		width: 370,
		modal: true,
		title:'添加信息', 
		buttons: {
	"添加": function() {
			var operate="add_lcp_master_income";
			useAjax(operate,"手术",$("#operationCode").val(),$("#operationName").val());
			$( this ).dialog( "close" );
	},
	"取消": function() {	$( this ).dialog( "close" );}	
	}
});


$( "#dialog-form-main" ).dialog({
	autoOpen: false,
	height: 400,
	width: 400,
	title:"编辑路径基本信息",
	modal: true
});
$( "#dialog-form-2" ).dialog({
autoOpen: false,
height: 300,
width: 350,
title:"添加信息",
modal: true
});

$( "#dialog-addNode" ).dialog({
	 autoOpen: false,
	 height: 350,
	 width: 400,
	 modal: true
	 });
	$( "#dialog-eaddNode" ).dialog({
		 autoOpen: false,
		 height: 350,
		 width: 400,
		 modal: true
		 });
	
});
