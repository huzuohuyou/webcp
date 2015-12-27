<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择要配置的诊断</title>
<link rel="stylesheet" type="text/css"  href="../public/plugins/jquery/cupertino/jquery-ui-1.8.11.custom.css"  />  
<link rel="stylesheet"  type="text/css" href="../public/plugins/jqgrid/ui.jqgrid.css">
<script type="text/javascript" src="../public/plugins/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript"  src="../public/plugins/jquery/ui/jquery.ui.core.js"></script>
<script type="text/javascript"  src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"  src="../public/plugins/jquery/ui/jquery.ui.dialog.js"></script>
<script type="text/javascript"  src="../public/plugins/jquery/ui/jquery.ui.position.js"></script>
<script type="text/javascript" src="../public/plugins/jqgrid/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="../public/plugins/jqgrid/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="../public/plugins/jqgrid/jquery.form.js"></script>

<script type="text/javascript"  src="../public/javascripts/cpmanage/charscode.js"></script>
<style type="text/css">

.ui-dialog{
font-size: 14px;}
</style>
<script type="text/javascript">
$(function()
{
	$("#gridTable").jqGrid({
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
		})   
			;
	
	
	$("#test").click( function() {  
	    var selectedId = $("#gridTable").jqGrid("getGridParam", "selrow");  
	    var rowData = $("#gridTable").jqGrid("getRowData", selectedId);  
	    alert("First Name: " + rowData.userName);  
	});  

	$("#t_gridTable").append("<input type='button' id='button' onclick='clicks();' value='name'>");
	$("#t_gridTable").append("<input type='button' id='buttons' onclick='clickss();'>");


	$( "#dialogAddCP" ).dialog({
		autoOpen: false,
		height: 300,
		width: 300,
		title:"添加信息",
		modal: true,
		open:function(){
			 $("#form1").hide();
			 $("#AddCP").show();
			
			$("#dept_name").val(deptName);
			$("#dept_id").val(deptCode);
		},
		buttons: {
			"创建路径": function() {
				P_login();
				$('#form1').clearForm();				
				$( this ).dialog( "close" );
									
			},
			"取消": function() {
				$( this ).dialog( "close" );
			}
		}
	});
	 $("#form1").hide();

	
		
$("#aa").click(function(){
	 $( "#table" ).dialog({
			autoOpen: false,
			height: 400,
			width: 600,
			title:"添加信息",
			modal: true
			
		});
	 $( "#table" ).dialog("open");
	
});
});

function clicks(){
	//var rowid = $("#gridTable").jqGrid('getGridParam','selrow'); 
	//var rowData = $("#gridTable").getRowData(rowid); 
	
	//alert(rowData.QQ);
	 $("#dialogAddCP").dialog("open");
	}
function clickss(){
	 $("#AddCP").hide();
	 $("#form1").show();
	}
var addContact = function() {  
    var rowid = $("#gridTable").jqGrid("getGridParam", "selrow");  
    if(rowid){
    var rowData = $("#gridTable").getRowData(rowid);
    var name=encodeURI(rowData.name,"UTF-8");  
    location.href='chooseOrder.jsp?code='+rowData.code+'&name='+rowData.name+'&node=1';
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
//	$("#result").val('登录中。。');
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

</script>
</head>
<body>
	<div id="table"  align="center">
	     <div id="gridPager"></div>
		<table id="gridTable" ondblclick='addContact()'></table>
		
	</div>


	<div id="dialogAddCP"  style="background: #FFF;font-size:12px;">
	<table id="AddCP" style="font-size:14px;">
	<tr>
	<td><a href="#" onclick="clickss();">1.新建局发路径</a></td>
	</tr>
	<tr>
	<td><a id="aa" >2.新建院内自定义路径(以现有路径为模版)</a></td>
	</tr>
	<tr>
	<td><a href="#" onclick="clickss();">3.新建院内自定义路径(无模版)</a></td>
	</tr>
	<tr>
	<td><a>4.版本升级</a></td>
	</tr>
	</table>
	
   	<form id="form1" style="width: 100%; overflow: hide; position: relative; height: auto; " name="FormPost">
    <table align="center">
      <tr>
        <td>路径名称：</td>
        <td><input type="text"  id="cp_name" name="cp_name" onkeyup="document.getElementById('cp_pym').value = getCharsCode(this.value);"/>*
        </td>
      </tr>
      <tr>
        <td >最少住院日：</td>
        <td ><input type="text" id="min_day" name="min_day" maxlength="3" />
        </td>
      </tr>
      <tr>
        <td >最大住院日： </td>
        <td ><input  type="text" id="max_day" name="max_day"maxlength="3" />
        </tr>
      <tr>
        <td >平均住院日：</td>
        <td><input  type="text" id="avg_day" name="avg_day" maxlength="3" /></td>
      </tr>
      <tr>
        <td >平均费用：</td>
        <td><input type="text"  id="avg_fee" name="avg_fee"/>
        </td>
      </tr>
        <tr>
        <td >拼音码：</td>
        <td><input type="text" id="cp_pym" name="cp_pym"/>
        </td>
      </tr>
     
  </table>
        <input type="hidden" id="dept_name" name="dept_name" />
        <input type="hidden" id="dept_id" name="dept_id"/> 
        <input type="hidden" id="cp_pym" name="cp_pym"/>
        <input type="hidden" id="cp_wbm" name="cp_wbm"/>
  </form>
</div>
</body>
</html>