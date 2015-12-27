var cp_id_quanju="";
var cp_state_quanju="";
var pageNo_quanju="1";
var bianma_quanju="";
var mingcheng_quanju="";
var pinyin_quanju="";
var wubi_quanju="";
var keshi_quanju="";
var url="../CpMaintainServlet";
var operate="getViewTable";
var async=true;
var type="";
var  highlightcolor='#d5f4fe';
var  clickcolor='#51b2f6';
var recoveryColor='#FFFFFF';
var bgcolor="#51b2f6";
var tempColor="";
var green = "#BFFF00";

function changeColor(event) {
	tempColor = event.bgColor;
	event.bgColor = highlightcolor;
}

function recoverColor(event) {
	event.bgColor = tempColor;
	tempColor = recoveryColor;
}
function clickChange(event) {
	event.bgColor = green;
}

$(function()
		{
	$("#exportOrder").click(function(){
		if(cp_id_quanju != ""){
			window.open("../ReportEmitter?rpt=CPMasterNode.brt&params=cp_id="+cp_id_quanju+"");
		}else{
			alert("请选择要导出的路径");
		}
	});
			/*$("#gridTable").jqGrid({
				url:'../servlet/cplist',
				datatype: "json",
				height:480,
				mtype:"post",
			//	shrinkToFit:false, //是否缩小适应  
			rownumbers:true,
				autowidth:true,
				colNames:['ID','路径名称', '创建科室', '路径编码 ', '路径版本','使用权限','状态','操作'],
				colModel:[	
							{name:'cp_id',index:'cp_id', width:40, align:"center",sorttype:"int",key:true },
							{name:'cp_name',index:'cp_name', width:250, align:"left",editable:true	},
							{name:'dept_name',index:'dept_name', width:70, align:"center",editable:true,sorttype:"string"},
							{name:'cp_code',index:'cp_code', width:70, align:"center",sorttype:"string"},
							{name:'cp_version',index:'cp_version', width:80, align:"center",search:false},		
							{name:'cp_status',index:'cp_status', width:80, align:"center",search:false},		
							{name:'cp_status',index:'cp_status', width:80, align:"center",search:false},		
							{name:'cp_status',index:'cp_status', width:80, align:"center",search:false,sortable:false}		
						],
				
				editurl	:"../servlet/cplist",		  				
				sortname:'cp_id',
				sortorder:'asc',
				viewrecords:true,
				rowNum:20,
				rowList:[10,20,30],
				jsonReader:{
					repeatitems : false
				},
				toolbar: [true,"top"],
				pager:"#gridPager"
			}).navGrid('#gridPager',{edit:true,add:true,del:true}).navButtonAdd('#gridPager',{   
				   caption:"Add",    
				   buttonicon:"ui-icon-add",    
				   onClickButton: function(){    
				     alert("Adding Row");   
				   },    
				   position:"last"  
				});
			
			
			$("#test").click( function() {  
			    var selectedId = $("#gridTable").jqGrid("getGridParam", "selrow");  
			    var rowData = $("#gridTable").jqGrid("getRowData", selectedId);  
			    alert("First Name: " + rowData.userName);  
			});  
			 $("#form1").hide();
			 $("#table").hide();
		});*/
	
	$("#gridTable").jqGrid({
		url:'../servlet/cplist?oper=create',
		datatype: "json",
		height:232,
		mtype:"post",
		width:$(document).width()-200,
		//shrinkToFit:true, //是否缩小适应  
	    rownumbers:true,
		autowidth:false,
		colNames:['ID','路径名称','路径版本','创建时间'],
		colModel:[	
					{name:'cp_id',index:'cp_id', width:80, align:"center",sorttype:"int",key:true },
					{name:'cp_name',index:'cp_name', width:200, align:"left",editable:false	},
					{name:'cp_version',index:'cp_version', width:80, align:"center",search:false},
					{name:'cp_create_date',index:'cp_create_date', width:180, align:"center",search:true}
				],
		
		editurl	:"../servlet/cplist",		  				
		sortname:'cp_id',
		sortorder:'asc',
		viewrecords:true,
		rowNum:10,
		rowList:[10,20,30],
		jsonReader:{
			repeatitems : false
		},
		//toolbar: [true,"top"],
		pager:"#gridPager"
	}).navGrid('#gridPager',{edit:false,add:false,del:false});
//新建自定义路径（有模板）	
	$("#gridTable2").jqGrid({
		url:'../servlet/cplist?oper=createLocalCP',
		datatype: "json",
		height:232,
		width:$(document).width()-200,
		mtype:"post",  
	    rownumbers:true,
		autowidth:false,
		colNames:['ID','路径名称','路径版本','创建科室'],
		colModel:[	
					{name:'cp_id',index:'cp_id', width:100, align:"center",sorttype:"int",key:true },
					{name:'cp_name',index:'cp_name', width:200, align:"left",editable:true	},
					{name:'cp_version',index:'cp_version', width:80, align:"center",search:false},
					{name:'dept_name',index:'dept_name', width:200, align:"center",editable:true,sorttype:"string"}
				],
		
		editurl	:"../servlet/cplist",		  				
		sortname:'cp_id',
		sortorder:'asc',
		viewrecords:true,
		rowNum:10,
		rowList:[10,20,30],
		jsonReader:{
			repeatitems : false
		},
		//toolbar: [true,"top"],
		pager:"#gridPager2"
	}).navGrid('#gridPager2',{edit:false,add:false,del:false});
	
//	$("#test").click( function() {  
//	    var selectedId = $("#gridTable").jqGrid("getGridParam", "selrow");  
//	    var rowData = $("#gridTable").jqGrid("getRowData", selectedId);  
//	    alert("First Name: " + rowData.userName);  
//	});  
	 $("#form1").hide();
	 $("#table").hide();
	 $("#tableData").hide();
});

		function clicks(){
			 $("#dialogAddCP").dialog("open");
			}
		function clickss(type){
			if(type=="1"){
				 $("#AddCP").hide();
				 $("#table").show();
			}else if(type=="2"){
				 $("#AddCP").hide();
				 $("#tableData").show();
			}else if(type=="3"){
				 $("#AddCP").hide();
				 $("#table").hide();
				 $("#tableData").hide();
				 $("#form1").show();
				 $(".ui-dialog-buttonpane button").eq(0).show();
			}
		}
		var addContact = function() {    
		    var rowid = $("#gridTable").jqGrid("getGridParam", "selrow");
		    if(rowid){
		    var rowData = $("#gridTable").getRowData(rowid);
		    var name=encodeURI(rowData.cp_name,"UTF-8");  
		    //alert(rowData.cp_id);
		    //alert(name.cp_name);
		    //location.href='createCP.jsp?code='+rowData.cp_id+'&name='+name+'&node=1';
			$.ajax({	   
				url : "../servlet/cplist",
				type : 'POST',
				data : {
					oper : "copyCP",
					cpId : rowData.cp_id
				},
				dataType : "json",
				complete :show_result ,
				success : function(data, textStatus, XMLHttpRequest) {
					data = eval(data);
					data = data[0];
					if(data.result=="OK"){
						document.location.href="../cpmanage/manage.jsp?type=jfCP&cp_id="+data.cpID;
					}else if(data.result == "exist"){
						alert("该路径已经存在请进行版本升级！");
						$("#dialogAddCP").dialog("close");
						return false;
					}else{
						alert("新建局发路径失败！");
					}
				}
		 	});
		    }
		  
		};  
		
//新建有模板的自定义路径
		var addContact2 = function() {    
		    var rowid = $("#gridTable2").jqGrid("getGridParam", "selrow");
		    if(rowid){
		    var rowData = $("#gridTable2").getRowData(rowid);
			$.ajax({
				url : "../servlet/managecp",
				type : 'POST',
				data : {
					op : "copyCP",
					cp_id : rowData.cp_id
				},
				dataType : "json",
				complete :show_result ,
				success : function(data, textStatus, XMLHttpRequest) {
					data = eval(data);
					data = data[0];
					if(data.result=="OK"){
						document.location.href="../cpmanage/manage.jsp?cp_id="+data.cpID;
					}else{
						alert("test!!!!");
					}
				}
		 	});
		    }
		  
		};

		function P_login(){
			//登录按钮
			$("#form1").ajaxSubmit({
			//target: "../servlet/cplist",
			type: "post",
			url:"../servlet/cplist",
			dataType: "json", 
			  /**参数介绍
			    formData：是一个数组,我们使用$.param来把它转化成字符串显示,form提交的时候该插件可以自动为你转换;
			    jqForm：是一个jQuery对象，用来封装form元素，访问DOM元素可以这样：var formElement = jqForm[0];
			　　 options：就是上面ajaxSubmit()中的Options对象;
			   **/
			beforeSubmit: function(formData, jqForm, options){
			//var queryString = $.param(formData);
//			$("#result").val('登录中。。');
			        //alert('About to submit: \n\n' + queryString);
			        return true;
			},
			success:  function (msg) {   /* responseText：
			       通常作为html的相应，方法成功回调，XMLHttpRequest对象的responseText属性，
			       如果Options对象的dataType属性设置'xml'的话，该参数就是XMLHttpRequest对象的responseXML属性，
			       如果Options对象的dataType属性设置'json' 的话，该参数就是服务器返回的JSON对象 ;
			    statusText：状态码;
			　　 xhr：XMLHttpRequest对象;
			    $form:;*/      //alert(responseText.status+responseText.info);
			    alert(msg.status);
			      if(msg.status == 1){
			      //状态成功
			//$(".topTd").val("hello world!");
			//window.location = '/index.php/Public';
			      }else{
			alert(msg.info);
			//$("#result").val(msg.info);
			      }
			   }

			});
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

$(document).ready(function(){
	 $("#cpSubmitContent").dialog({
			title:"申请原因",
			autoOpen: false,
			height: 305,
			width: 400,
			modal: true
		});
	 $("#verify").dialog({
			title:"路径审核",
			autoOpen: false,
			height: 270,
			width: 400,
			modal: true
		});
	 $("#return").dialog({
			title:"退回路径",
			autoOpen: false,
			height: 270,
			width: 410,
			modal: true
		});
	 $("#showReason").dialog({
			title:"查看退回原因",
			autoOpen: false,
			height: 240,
			width: 400,
			modal: true
		});
	 $("#showHistoryReason").dialog({
			title:"历史记录",
			autoOpen: false,
			height: 300,
			width: 400,
			modal: true
		});
	 
	 
/* 	var opertion={
	    	delay:1000,
	    	 max: 12,    //列表里的条目数
	         minChars: 0,   //自动完成激活之前填入的最小字符
	         width: 400,     //提示的宽度，溢出隐藏
	         scrollHeight: 300,   //提示的高度，溢出显示滚动条
	         matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
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

		$("#dept_name").autocomplete("../servlet/auto?ops=py&op=dept",opertion);
		$("#dept_name").result(function(event, data, formatted){
				formatted=formatted.split("--");
			 $("#dept_name").attr("value",formatted[1]);
			$("#dept_id").attr("value",formatted[0]);
	//		alert($("#dept_id").val());
		}); */

    
	DBOperation();
	$( "#showCpDetail" ).dialog({
		title:"路径信息",
		autoOpen: false,
		modal: true,
		height:550,
		width:$(document).width()-150,
		resizable:false,
		draggable:true
		});
	
	$( "#dialogAddCP" ).dialog({
		autoOpen: false,
		height: 395,
		width: $(document).width()-150,
		title:"创建路径信息",
		modal: true
	});

	//创建路径
	$( "#createCP" ).click(function(){
//			$( "#dialogAddCP" ).dialog({
//				open:function(){
//					 $("#AddCP").show();
//					 $("#table").hide();
//					$("#dept_name").val(deptName);
//					$("#dept_id").val(deptCode);
//				},
//				buttons: {
//					"取消": function() {
//						$( this ).dialog( "close" );
//					}
//				}
//			});
			$( "#dialogAddCP" ).dialog({
				open:function(){
					$("#AddCP").show();
					$("#table").hide();
					$("#tableData").hide();
					$("#form1").hide();
					$("#dept_name").val(deptName);
					$("#dept_id").val(deptCode);
				},
				buttons: {
					"创建路径": function() {
						$.ajax({	   
							url : "../servlet/managecp",
							type : 'POST',
							data : {
								op : "addCP",
								cp_name : encodeURI($("#cp_name").val(),"utf-8"),
								cp_code:$("#dept_local_code").val(),
								min_day:$("#min_day").val(),
								max_day:$("#max_day").val(),
								avg_day:$("#avg_day").val()	,
								avg_fee:$("#avg_fee").val()	,
								cp_pym:$("#cp_pym").val()	,
								cp_wbm:$("#cp_wbm").val()	,
								dept_name:encodeURI($("#dept_name").val(),"utf-8"),
								dept_id:$("#dept_id").val(),
								cp_health_care_quota:$("#cp_health_care_quota").val()
							},
							dataType : "json",
							complete :show_result ,
							success :function(data, textStatus, XMLHttpRequest) {
								data = eval(data);
								data = data[0];
								 if (data.result === "OK") {
									 document.location.href="../cpmanage/manage.jsp?cp_id="+data.id;
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
				
		$(".ui-dialog-buttonpane button").eq(0).hide();
		$( "#dialogAddCP" ).dialog("open");
	});
/* 	$( "#createCP" ).click(function(){
		alert("create!");
		$("#create").dialog("open");
	}); */
	//编辑路径按钮
	$("#editCP").click(function(){
		if(cp_id_quanju!=""&&cp_id_quanju>10000){
			$.ajax({	   
				url : "../servlet/managecp",
				type : 'POST',
				data : {
					op : "editOrNot",
					cp_id : cp_id_quanju
				},
				dataType : "json",
				complete :show_result ,
				success : function(data, textStatus, XMLHttpRequest) {
					data = eval(data);
					data = data[0];
					if(data.result==="OK"){
						document.location.href="../cpmanage/manage.jsp?cp_id="+cp_id_quanju;
					}else if(data.result==="submit"){
						alert("该路径正在审核中，不可编辑！");
					}else if(data.result==="noEdit"){
						alert("该路径已经启用，不可编辑！");
					}else if(data.result==="hidden"){
						alert("该路径已经隐藏，不可编辑，请恢复后再编辑！");
					}
				}
		 });	
		}else if(cp_id_quanju==""){
			alert("请选择一条进行编辑!");
		}else if(cp_id_quanju<10000){
			alert("该路径禁止编辑!");
		}
	});
	
	//删除路径按钮
	$("#delCP").click(function(){
		if(cp_id_quanju!=""&&cp_id_quanju>10000){

			$.post("../servlet/managecp",{op:"selectCPStatus",cp_id:cp_id_quanju}, function(data){
				data=eval(data);
				data=data[0];
				if(data.result === "start"){
					alert("该路径已启用或申请启用过,不能删除！");
					return false;					
				}else if(data.result === "OK"){
					if(confirm("您确定要删除此路径?删除操作不可回滚!")){
					$.post("../servlet/managecp",{op:"delCP",cp_id:cp_id_quanju}, function(data){
						if(data){
							DBOperation();
						}else{
							alert("删除失败!");
							return false;
						}
						});	
					}
				}
				});
		}else if(cp_id_quanju==""){
			alert("请选择一条路径!");
		}else if(cp_id_quanju<10000){
			alert("该路径禁止删除!");
		}
	});
	
	//路径升级
	$("#upGradeCP").click(function(){
		if(cp_id_quanju!=""&&cp_id_quanju>10000){
			if(confirm("版本升级只能得到同病种的路径，若想要得到该模板新病种的路径请新建！")){
			$.post("../servlet/managecp",{op:"copyCP",cp_id:cp_id_quanju}, function(data){
				data = eval(data);
				data = data[0];
				if(data.result=="OK"){
					document.location.href="../cpmanage/manage.jsp?type=upCP&cp_id="+data.cpID;
				}else{
					alert("路径版本升级失败!");
				}
				}); }
		}else if(cp_id_quanju==""){
			alert("请选择一条路径!");
		}
	});
	
	//$("#copyCP").click(function(){
		
		 //if(cp_id_quanju!=""/* &&cp_id_quanju>10000 */){
			//alert("路径复制成功!");
			 // $('#showCpDetail').html('<iframe src=../cpmanage/manage.jsp?cp_id='+cp_id_quanju+' frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');
			  // var operate="getViewTable";
			  // DBOperation();
			  // $( "#showCpDetail" ).dialog('open');
		//}else if(cp_id_quanju==""){
		//	alert("请选择一条路径!");
		//}/* else if(cp_id_quanju<10000){
			//alert("该路径禁止删除!"); 
		//} */
	//});
	//显示全部路径/中心路径/自定义路径
	$("#selectType").change(function(){
			 DBOperation();
	});
	
	
});
function DBOperation(){
	
	var cpType=$("#selectType").val();
	if(cpType==1){
		 operate="getViewTable_all";
	}/* else if(cpType==2){
		 operate="getViewCoreTable";
	}else if(cpType==3){
		 operate="getViewLocalTable";
	} */else if(cpType==4){
		 operate="getViewTable_start";
	}else if(cpType==5){
		 operate="getViewTable_stop";
	}else if(cpType==6){
		 operate="getViewTable_verify";
	}else if(cpType==7){
		 operate="getViewTable_hidden";
	}else if(cpType==8){
		 operate="getViewTable_exit";
	}else if(cpType==9){
		 operate="getViewTable_dept";//本科路径
	}
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
	    	ks : encodeURI(keshi_quanju),
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
				DBOperation();
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
		mingcheng_quanju="";
		pinyin_quanju="";
		keshi_quanju="";
		bianma_quanju=context_0;
		}
	if(auto=="2"){
		bianma_quanju="";
		pinyin_quanju="";
		keshi_quanju="";
		mingcheng_quanju=context_0;
		}
	if(auto=="3"){
		bianma_quanju="";
		mingcheng_quanju="";
		keshi_quanju="";
		pinyin_quanju=context_0;
		}
	if(auto=="4"){
		bianma_quanju="";
		mingcheng_quanju="";
		pinyin_quanju="";
		keshi_quanju=context_0;
		}
	operate="getViewTable";
	pageNo_quanju=aa;
	DBOperation();	

}
function tiaozhuanzhiding(){
	var auto=$(":radio[name='zccx'][checked]").val();
	var context$=$("#chaxuntext");
	var context_0=context$[0].value+"";
	if(auto=="1"){
		mingcheng_quanju="";
		pinyin_quanju="";
		keshi_quanju="";
		bianma_quanju=context_0;
		}
	if(auto=="2"){
		bianma_quanju="";
		pinyin_quanju="";
		keshi_quanju="";
		mingcheng_quanju=context_0;
		}
	if(auto=="3"){
		bianma_quanju="";
		mingcheng_quanju="";
		keshi_quanju="";
		pinyin_quanju=context_0;
		}
	if(auto=="4"){
		bianma_quanju="";
		mingcheng_quanju="";
		pinyin_quanju="";
		keshi_quanju=context_0;
		}
	
	var _pageNo=$("#tiaozhuan_daohang");
	_pageNo=_pageNo[0].value;
	pageNo_quanju=_pageNo;
	operate="getViewTable";
	DBOperation();
}
function chaxun(){
	var auto=$(":radio[name='zccx'][checked]").val();
	var context$=$("#chaxuntext");
	var context_0=context$[0].value+"";
	if(auto=="1"){
		mingcheng_quanju="";
		pinyin_quanju="";
		keshi_quanju="";
		bianma_quanju=context_0;
		}
	if(auto=="2"){
		bianma_quanju="";
		pinyin_quanju="";
		keshi_quanju="";
		mingcheng_quanju=context_0;
		}
	if(auto=="3"){
		bianma_quanju="";
		mingcheng_quanju="";
		keshi_quanju="";
		pinyin_quanju=context_0;
		}
	if(auto=="4"){
		bianma_quanju="";
		mingcheng_quanju="";
		pinyin_quanju="";
		keshi_quanju=context_0;
		}
	pageNo_quanju=1;
	operate="getViewTable";
	DBOperation();	

}
function cp_state_op_view(id,obj){
	cp_id_quanju=id;
	var OKNO=false;
		if(obj==1){
			OKNO =confirm("确定要停用当前路径吗？");
		}else if(obj==3){
			alert("该路径已经退回，等待重新申请...");
			return false;
		}else if(obj==4){
			recovery();
		}else if(obj==2){
			$.ajax({	   
				url : "../servlet/managecp",
				type : 'POST',
				data : {
					op : "verify",
					cp_id : cp_id_quanju
				},
				dataType : "json",
				complete :show_result ,
				success : function(data, textStatus, XMLHttpRequest) {
					data = eval(data);
					data = data[0];
					var time=data.time;
					var user=data.user;
					var content=data.content;	
					$("#ctx2").html(content);
					$("#user2").html(user);
					$("#time2").html(time);
				}
		 	});
			$("#verify").dialog("open");
			$("#verify").dialog({
				open:function(){
					
				},
				buttons:{
					"启用" : function(){
						
						$.ajax({	   
							url : "../servlet/managecp",
							type : 'POST',
							data : {
								op : "cpEnable",
								cp_id : cp_id_quanju
							},
							dataType : "json",
							complete :show_result ,
							success : function(data, textStatus, XMLHttpRequest) {
								data = eval(data);
								data = data[0];
								if(data.result === "OK"){
									alert("路径启用成功！");
									$("#verify").dialog("close");
									DBOperation();
								}else if(data.result === "stopOld"){
									if(confirm("启用该版路径将会停用旧版本的路径，您确定要启用吗？")){
										alert("将会停用旧版本的路径！");
										$.ajax({
											url:"../servlet/managecp",
											type:'POST',
											data:{
												op:"startAfter",
												cp_id:cp_id_quanju
											},
											dataType:"json",
											complete:show_result,
											success : function(data, textStatus, XMLHttpRequest) {
												data = eval(data);
												data = data[0];
												if(data.result==="OK"){
													alert("新版本路径启用成功！");
													$("#verify").dialog("close");
													DBOperation();
												}else{
													alert("启用失败！");
													return false;
												}
											}
											
										});
									}							
								}else if(data.result === "fail"){
									alert("路径启用失败！");
									return false;
								}
							}
					 	});	
					},
					"退回" : function(){
						OKNO =confirm("确定要退回该路径吗？");
						if(OKNO){
							returnReason(cp_id_quanju);
						}
					},
					"取消" : function(){
						$(this).dialog("close");
					}
				}
			}); 
			
		}else{
			OKNO =confirm("确定要启用当前路径吗？");
		}
		if(OKNO){
			operate="startOrEndCpView";
			cp_state_quanju=obj;
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
	}
	
	//退回原因
	function returnReason(id){
		var cpID = id;
		$("#return").dialog("open");
		$("#returnCtx").keydown(function(event){
			var keycode = event.which;
			var markLength=$("#returnCtx").val().length;
			if(markLength>=500){
				var num=$("#returnCtx").val().substr(0,500);
				$("#returnCtx").val(num);
				if(keycode != 8){
					alert("超出字符限制，多余部分将被截去！");
					return false;
				}
			}
		});
		$("#return").dialog({
			open:function(){
				$("#returnCtx").val("");
			},
			buttons:{
				"确定" : function(){
					var returnReason = $("#returnCtx").val();
					var returnUser = $("#returnUser").html();
					var returnTime = $("#returnTime").html();
					if(returnReason == ""){
						alert("退回原因不能为空！");
						return false;
					}else{
						$.ajax({	   
							url : "../servlet/managecp",
							type : 'POST',
							data : {
								op : "returnReason",
								cp_id : cpID,
								returnReason : encodeURI(returnReason),
								returnUser : encodeURI(returnUser),
								returnTime : returnTime
							},
							dataType : "json",
							complete :show_result ,
							success : function(data, textStatus, XMLHttpRequest) {
								data = eval(data);
								data = data[0];
								if(data.result === "OK"){
									alert("退回成功！");
									$("#return").dialog("close");
									$("#verify").dialog("close");
									DBOperation();
								}else{
									alert("退回失败！");
									return false;
								}
							}
					 	});	
					}
				},
				"取消" : function(){
					$(this).dialog("close");
				}
			}
		});
		
	}
	function showReasons(){
		$.ajax({	   
			url : "../servlet/managecp",
			type : 'POST',
			data : {
				op : "showReason",
				cp_id : cp_id_quanju
			},
			dataType : "json",
			complete :show_result ,
			success : function(data, textStatus, XMLHttpRequest) {
				data = eval(data);
				data = data[0];
				var reason = data.reason;
				
				if(reason != ""){
					var returnUser2 = data.returnUser2;
					var returnTime2 = data.returnTime2;
					$("#returnCtx2").html(reason);
					$("#returnUser2").html(returnUser2);
					$("#returnTime2").html(returnTime2);
					$("#showReason").dialog("open");
				}else{
					return false;
				}	
			}
	 });
	}
	//申请人查看历史退回原因
	function showHistoryReasons(){
		$.ajax({	   
			url : "../servlet/managecp",
			type : 'POST',
			data : {
				op : "showHistoryReason",
				cp_id : cp_id_quanju
			},
			dataType : "json",
			complete :show_result ,
			success : function(data, textStatus, XMLHttpRequest) {
				data = eval(data);
				//data = data[0];
				var reason = data[0].historyReason;
				//alert(reason);
				if(reason != ""){
					$("#returnHistory").html(data[0].historyReason);
					$("#showHistoryReason").dialog("open");
				}else{
					return false;
				}	
			}
	 });
	}
	//路径提交审核
	function cpSubmit(id,obj){
	cp_id_quanju=id;
	var OKNO=false;
		if(obj==1 || obj==3){
			if(confirm("确定要提交当前路径吗？")){
				cp_state_quanju=obj;

				showReasons();
				$("#cpSubmitContent").dialog("open");
				$("#ctx").keydown(function(event){
					var keycode = event.which;
					var markLength=$("#ctx").val().length;
					if(markLength>=500){
						var num=$("#ctx").val().substr(0,500);
						$("#ctx").val(num);
						if(keycode != 8){
							alert("超出字符限制，多余部分将被截去！");
							return false;
						}
					}
				});
				
				$("#cpSubmitContent").dialog({
					open: function(){
						$("#ctx").val("");
						//content = $("#cpSubmitContent tr").children().eq(1).val();
					},
					buttons: {
						"查看原因" : function(){
							showHistoryReasons();
						},
						"确定": function(){
							var content = $("#ctx").val();
							var user = $("#user").html();
							var time = $("#time").html();
							if(content == ""){
								alert("申请原因不能为空！");
								return false;
							}else{
								$.ajax({	   
									url : "../servlet/managecp",
									type : 'POST',
									data : {
										op : "cpSubmit",
										cp_id : cp_id_quanju,
										cp_state : cp_state_quanju,
										content : encodeURI(content),
										user : encodeURI(user),
										time : time
									},
									dataType : "json",
									complete :show_result ,
									success : function(data, textStatus, XMLHttpRequest) {
										data = eval(data);
										data = data[0];
										if(data.result==="OK"){
											alert("提交成功,请等待审核！");
											$("#cpSubmitContent").dialog("close");
											DBOperation();
										}else if(data.result==="fail"){
											alert("提交失败！");
											$("#cpSubmitContent").dialog("close");
										}
									}
							 });
							}	
						},
					"取消": function() {
						$( this ).dialog("close");
						}
					}	
				});
			}
			
		}else if(obj == 0){
			alert("该路径已经启用！");
			return false;
		}else if(obj == 2){
			if(confirm("您确定要召回该路径吗？")){
				$.ajax({	   
					url : "../servlet/managecp",
					type : 'POST',
					data : {
						op : "cpZhaoHui",
						cp_id : cp_id_quanju
					},
					dataType : "json",
					complete :show_result ,
					success : function(data, textStatus, XMLHttpRequest) {
						data = eval(data);
						data = data[0];
						if(data.result==="OK"){
							alert("路径召回成功！");
							DBOperation();
						}else if(data.result==="fail"){
							alert("路径召回失败！");
						}
					}
			 });
			}
			return false;
		}
	}

	function showDeital(cpid){
		cp_id_quanju=cpid;
	//	$('#showCpDetail').html('<iframe src="../route/cp_maint_detail.jsp?cp_id='+cp_id_quanju+'" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>'); 

		  $('#showCpDetail').html('<iframe src="../version/viewcp.jsp?cp_id='+cp_id_quanju+'" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');
		$( "#showCpDetail").dialog( "open" );
	}
	function showHidden(){
		if(confirm("您确定要隐藏该路径吗？隐藏后只能管理员通过路径筛选才能找到！")){
			$.ajax({	   
				url : "../servlet/managecp",
				type : 'POST',
				data : {
					op : "hiddenCP",
					cp_id : cp_id_quanju
				},
				dataType : "json",
				complete :show_result ,
				success : function(data, textStatus, XMLHttpRequest) {
					data = eval(data);
					data = data[0];
					if(data.result==="OK"){
						alert("隐藏路径成功！");
						DBOperation();
					}else if(data.result==="fail"){
						alert("隐藏路径失败！");
					}
				}
		 });
		}
	}
	
	function recovery(){
		if(confirm("您确定要恢复该路径吗？恢复后该路径为停用状态！")){
			$.ajax({	   
				url : "../servlet/managecp",
				type : 'POST',
				data : {
					op : "recoverCP",
					cp_id : cp_id_quanju
				},
				dataType : "json",
				complete :show_result ,
				success : function(data, textStatus, XMLHttpRequest) {
					data = eval(data);
					data = data[0];
					if(data.result==="OK"){
						alert("恢复路径成功！");
						DBOperation();
					}else if(data.result==="fail"){
						alert("恢复路径失败！");
					}
				}
		 });
		}
	}