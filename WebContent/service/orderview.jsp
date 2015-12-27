<%
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：order.jsp  
// 文件功能描述：院内医嘱下发页面
// 接收参数: 病人IDpatient_no   住院号admissTimes  医生工号doctorNo  病区patientArea
// 创建人：段英华
// 创建日期：2011-8-25
// 修改日期：2011-8-25
// 完成日期：
//----------------------------------------------------------------*/ 
%>

<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	final int HOSPITALID=LcpUtil.getHospitalID();
	DatabaseClass db=LcpUtil.getDatabaseClass(); 
	
	//演示页面需要的两个参数   一个是路径cp_id
	String cpId = request.getParameter("cpId");
	//另外一个是节点id
	String cpNodeId = request.getParameter("cpNodeId");


	
	String dosageSql = "select code, unit from lcp_local_order_dosageunits";
	 DataSetClass ds2 = db.FunGetDataSetBySQL(dosageSql);
	String freSql = "select code,comm from lcp_local_order_frequency";
	 DataSetClass ds3 = db.FunGetDataSetBySQL(freSql);
	String waySql = "select supply_code,supply_name from lcp_local_order_way";
	DataSetClass ds4 =  db.FunGetDataSetBySQL(waySql);
	DataSetClass ds = null;	
	DataSetClass ds7 = null;

	String cpName = "";
	String cpNodeName="";
	String json = "";
	String nextNodeType = "";
	String nextNodeId = "";
	List list = new ArrayList();
	
				//取出路径名字
				String nameSql = "select CP_NAME from lcp_master  where CP_ID = "+cpId;
				cpName = db.FunGetDataSetBySQL(nameSql).FunGetDataAsStringById(0,0).toString();

				
				//把point表内容从这取出
				 String sql3="select * from lcp_node_order_point t "
						+  " where SYS_IS_DEL=0 and  cp_id="+cpId+" and cp_node_id="+cpNodeId+" and HOSPITAL_ID="+HOSPITALID+" and t.continue_order_id = t.cp_node_order_id order by CP_NODE_ORDER_ID";
					ds=db.FunGetDataSetBySQL(sql3);
					cpNodeName=db.FunGetDataSetBySQL("select cp_node_name from lcp_master_node where cp_id="+cpId+" and cp_node_id="+cpNodeId).FunGetDataAsStringById(0,0);	

		      	
		       //组出当前节点下所有二级的ID 放入list中备用
		        String sql4="select * from lcp_node_order_point t "
						+  " where SYS_IS_DEL=0 and  cp_id="+cpId+" and cp_node_id="+cpNodeId+" and HOSPITAL_ID="+HOSPITALID+" and t.continue_order_id != t.cp_node_order_id order by CP_NODE_ORDER_ID";
					
		       		DataSetClass dsc8 = db.FunGetDataSetBySQL(sql4);
		       		int rowTwo = dsc8.FunGetRowCount();
					for(int x=0; x<rowTwo; x++){
						list.add(dsc8.FunGetDataAsStringByColName(x,"CP_NODE_ORDER_ID").trim());
					}
			
			
			
			
			
			
		
	

	    
	%>
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
<title>临床路径医嘱方案预览页面</title>
<script type="text/javascript">

var highlightcolor='#d5f4fe';
var clickcolor='#51b2f6';
var recoveryColor='#FFFFFF';
var bgcolor="#51b2f6";
var tempColor="";
var orderIds = "";
var arrList = new Array();

function changeColor(event){
	tempColor=event.bgColor;
	event.bgColor=highlightcolor;
	}

function recoverColor(event){
	event.bgColor=tempColor;
	tempColor=recoveryColor;
	}

//双击
function ondblclickLoad(event){
	
	$("tr").each(function(){
		changeSignNull(this);
	});
	var tr=$(event);
	var data = tr.children().eq(1).children().eq(0).children().eq(1).children().eq(0).val();
	var   strArray=new   Array();
	strArray=data.split("|"); 
	for(var i =0; i<strArray.length; i++){
		strArray1 = strArray[i].split(",");
		$("tr").each(function(){
			var orderId = $(this).children().eq(0).attr("name");
			var orderItemId = $(this).children().eq(0).attr("id");
			if(strArray1[0] === orderId && strArray1[1] === orderItemId){
				changeSign(this);
				}
			}
		);	
		
	}
	$("#div1").dialog("close");
	
}



//全选
function selectAll(event){
	
	$("tr").each(function(){
		var nodeCode = $(this).children().eq(0).attr("id");
		
		if(nodeCode != ""){
		changeSign(this);
		}
	});	
}


//选中组
function selectSet(event){
    var x = $(event).children().eq(0).html();
    var setIdnow = $(event).children().eq(8).attr("orderItemSetNo");
	var setIdOld;
	$("tr").each(function(){
		var setId = $(this).children().eq(8).attr("orderItemSetNo");
		var itemId = $(this).children().eq(0).attr("id");
		var orderId = $(this).children().eq(0).attr("name");

		var setNo =  orderId + itemId ;
		setIdOld = setId;
		if(setId !=undefined){
			     if(setId == setIdOld && x=="◇" && setId==setIdnow){
			    	 $(event).children().eq(0).html("◆");
			           changeSign(this);
			     }else if(setId == setIdOld && x=="◆" && setId==setIdnow){
				        changeSignNull(this);
				        $(event).children().eq(0).html("◇");
				      
			       }
		}
	});	
}
//选中非组
  function selectSet1(event){
	  var setId = $(this).children().eq(8).attr("orderItemSetNo");
	    var x = $(event).children().eq(0).html();
	    var y = $(event).children().eq(7).html();
        if(x=="&nbsp;"){
    	    if(y!=""){
    	         event.bgColor="#FFFFFF";
    	 		 $($(event).children().eq(7)).html("");	
          
            }else if(setId==null || "".equals(setId)){
     
      	      event.bgColor=clickcolor;
    	      var html="<img src='../public/images/success.png' width='18' height='18'/>";
              $($(event).children().eq(7)).html(html);
            }
		}
	
  }

//选中所有未执行的项
function selectOthers(event){
	
	$("tr").each(function(){
		var exestate1 = $(this).attr("exestate1");
		
		if(exestate1 == ""){
		changeSign(this);
		}
	});	
}

//全不选
function unSelectAll(event){

	$("tr").each(function(){
		changeSignNull(this);
	});	
}

//保存为模板按钮
function saveSchema(){

	$("tr").each(function(){
		var html=$(this).children().eq(7).html();
		if(html !="" && html != "选择" && html != null){
			orderIds +=  $(this).children().eq(0).attr("name") + "," ; 	
			orderIds +=  $(this).children().eq(0).attr("id") + "|";
			
		}
		
	});
	orderIds=orderIds.substr(0,orderIds.length-1);
	$("#div").dialog("open");

}


//折叠操作(第一级菜单专用)
function lap(event){
	var x = $(event).children().eq(0).html();
	var y = $(event).children().eq(0).attr("orderid");

	if(x == "-"){
		$($(event).children().eq(0)).html("+");
		var trs2=$("tr[name^='Con_"+y+"']");

		trs2.hide();
		

	        var trs1=$("tr[fristid^='"+y+"']");
	        
			trs1.hide();
		
	}else{
		$($(event).children().eq(0)).html("-");

		var trs2=$("tr[name^='Con_"+y+"']");
		$(trs2.children().eq(0)).html("+");
		trs2.show();
	}

	
}

//折叠子项(第二级菜单用)
function lap2(event){
	var x = $(event).children().eq(0).html();
	var y = $(event).children().eq(0).attr("orderid");


	if(x == "-"){
		$($(event).children().eq(0)).html("+");
		var trs1=$("tr[name^='"+ y +"_']");

		trs1.hide();

	}else{
		$($(event).children().eq(0)).html("-");

		var trs1=$("tr[name^='"+ y +"_']");
		trs1.show();
	}

	
}


//把焦点转移到底部
function move(){	 
	commit.focus();
 }


function NodeColor(event){
	var a=event.bgColor;
	event.bgColor=(tempColor==='#51b2f6'?recoveryColor:clickcolor);
	tempColor=event.bgColor;

	
	var check=(tempColor==='#51b2f6'?html:"");

	
}


function changeSign(event){
	
	event.bgColor=clickcolor;

}

function changeSignNull(event){

	     event.bgColor="#FFFFFF";
}

function NodeColorOne(event){
	var html="<img src='../public/images/success.png' width='18' height='18'/>";
	var check=(tempColor===clickcolor?"":html);
	var name=event.id.split("_")[0];
	var trs=$("tr[id^="+name+"]");
	var trsleng=trs.length;
	for(var i=0;i<trsleng;i++){
		var tr=trs[i];
		tr.bgColor=recoveryColor;
		$($(tr).children().eq(7)).html("");
	}
	$($(event).children().eq(7)).html(check);
	event.bgColor=(tempColor===clickcolor?recoveryColor:clickcolor);
	tempColor=event.bgColor;
}

jQuery.fn.Scrollable = function(tableHeight, tableWidth) {
	this.each(function(){
		if (jQuery.browser.msie || jQuery.browser.mozilla) {
			// var table = new ScrollableTable(this, tableHeight, tableWidth);
		}
	});
};

var checkTrs1 = "";
var checkTrs2 = "";
var checkTrs3 = "";
var order = "";
var jianyan = "";
var jiancha = "";
var other = "";
var jsons = "";
var cpID = "";

var lastState = "";
$(document).ready(function(){
	
	arrList = "<%=list%>".replace('[','').replace(']','').split(', ');

	for (var x in arrList){
        var trs1=$("tr[name^='"+arrList[x]+"_']");
        
		trs1.hide();
		
		var trs2=$("tr[name^='Con_']");
		trs2.hide();
        
    }




	jQuery('table').Scrollable(550, 900);
	//保存方案时的DIV
	$("#div").dialog({
		title:"请填写一个备注信息",
		autoOpen: false,
		height: 280,
		width: 370,
		modal: true,
		buttons: {
			"确定": function() {
				save1();
				alert("保存成功");
				$( this ).dialog( "close" );
			},
			"取消": function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
		}
	});
	//选择方案时的DIV
	$("#div1").dialog({
		title:"方案列表",
		autoOpen: false,
		modal: true,
		height:300,
		width:500
	});

});
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

 	}
   
};

var show_result1 = function(XMLHttpRequest, textStatus){
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

 	}
   
};

</script>
<style type="text/css">
<!--
/* 树形表格样式*/
.acts_as_tree_table tr td .expander {
	background-image: url(../public/plugins/jquery/images/bullet_toggle_minus.png);
	background-position: bottom right ;
	background-repeat: no-repeat;
	cursor: pointer;
	padding: 1;
	zoom: 1; /* Hack for IE, works in IE7, I refuse to check in IE6 or older  调整加减号图片大小*/
}

.acts_as_tree_table tr.collapsed td .expander {
	background-image: url(../public/plugins/jquery/images/bullet_toggle_plus.png);
}

.acts_as_tree_table tr.expanded td .expander {
	background-image: url(../public/plugins/jquery/images/bullet_toggle_minus.png);
}
/*--- 树形表格样式----*/

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
-->
</style>

</head>

<body >
<h1 align="center" style="font-size:20px;"><%=cpName %>临床路径医嘱方案预览(<%=cpNodeName %>)</h1>

 <br>
<div style="font-size:14px;" align="center">
 &nbsp;&nbsp;&nbsp;
 状态说明: 
 
&nbsp; 
 必选说明:
 <img src="../public/images/detail_s4.png" height="16" width="16"/>单项选一
  &nbsp;&nbsp;&nbsp; 
 <img src="../public/images/detail_s5.png" height="16" width="16"/>此项目必选,否则填变异
    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp; 
<!--    <input type="button" id="sub" value="提交"/>

<input type="button"  onclick="move()"; value="跳至底部"/>   -->
<br/>
</div>
<!-- style=" height:550px; width:80%;OVERFLOW-x:hide; overflow-y:auto"  -->
<div align="center">

    <table width="1120" id="main" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="frag-3" >
       <thead>
		<tr height="30" style=" font-size:14px;" bgcolor="d3eaef" >
		  <th width="20"  >&nbsp;</th>
		  <th width="40"  >状态</th>
		  <th width="80"  >类型</th>
		  <th width="80"  >类别</th>
		  <th width="240" >项目名称</th>
		  <th width="70"  >规格</th>
		  <th width="50"  >必选</th>
		  <th width="50" >选择</th>
		  <th width="100" >用量</th>
		  <th width="80" >频次</th>
		  <th width="80" >途径</th>
		  <th width="150" >药品一次使用剂量</th>
		  <th width="80" >医生嘱托</th>		  
		  </tr>
        </thead>
        <tbody id="lcp_node_order_tbody">
       
    <% 
         
		 
          //String sql3 = "select distinct (t.cp_node_class_id) as AA,  (select a.order_type_name from lcp_local_order_type a where a.order_type_code = (t.cp_node_class_id)) as BB" +
                     //   " from lcp_patient_order_item t where t.patient_no = '"+patientId1+"' and t.cp_id = "+cpID+" and t.cp_node_id = "+cpNodeID+" order by t.cp_node_class_id";
    
 		  String itemSql="select * from lcp_node_order_item "
 						+"where sys_is_del=0 and cp_id="+cpId+" and cp_node_id="+cpNodeId+" and HOSPITAL_ID="+HOSPITALID+" and CP_NODE_ORDER_ID =";
 		  
 		 String itemSql2="select * from lcp_node_order_item "
				+"where sys_is_del=0 and cp_id="+cpId+" and cp_node_id="+cpNodeId+" and HOSPITAL_ID="+HOSPITALID+" and CP_NODE_ORDER_ID in";

 			
				try{ 
					if(ds.FunGetDataAsStringById(0,0)!=""){
						for (int i = 0; i < ds.FunGetRowCount(); i++) {
							String cpNodeOrderId=ds.FunGetDataAsStringByColName(i,"CP_NODE_ORDER_ID");
							String conId=ds.FunGetDataAsStringByColName(i,"CONTINUE_ORDER_ID");

							String cpNodeOrderText = ds.FunGetDataAsStringByColName(i, "CP_NODE_ORDER_TEXT");
							//String autoItem = ds.FunGetDataAsStringByColName(i, "AUTO_ITEM");
							String kind="";
							//String state=ds.FunGetDataAsStringByColName(i,"STATE_ITEM");
							//String exe_State=ds.FunGetDataAsStringByColName(i,"EXE_STATE");
							
							String _needItem=("");
							String checkType=("NodeColor(this)");
							String trName=("tr");
							String orderType="";
							 String needSql ="select cp_node_order_id from lcp_patient_order_point t "
									+  " where SYS_IS_DEL=0 and  cp_id="+cpId+" and cp_node_id="+cpNodeId+" and HOSPITAL_ID="+HOSPITALID+" and t.continue_order_id = "+ conId+" and t.continue_order_id != t.cp_node_order_id"; 
							//int needCount1=db.FunGetRowCountBySql(itemSql2+"("+needSql+")"+" and need_item=1");
							int needAndexeState1=db.FunGetRowCountBySql(itemSql2+"("+needSql+")"+" and need_item=1");
							
							//System.out.println("eeeeeeeee==:"+(itemSql2+"("+needSql+")"+" and need_item=1"));
							//System.out.println("fffffff==:"+(itemSql2+"("+needSql+")"+" and need_item=1 and exe_state<>1"));
 	     %>
 	     <%
         if(needAndexeState1 != 0){
             %>
         <tr id="node-<%= cpNodeOrderId%>"  height="25" bgcolor="#FFE4B5" style="font-size:14px; cursor:pointer;"  onclick="lap(this);">
           <td align="center" orderid="<%= cpNodeOrderId%>">+</td>
           <td align="center">★</td>
           <%}else{ %>
           <tr id="node-<%= cpNodeOrderId%>"  height="25" bgcolor="#FFE4B5" style="font-size:14px; cursor:pointer;"  onclick="lap(this);">
           <td align="center" orderid="<%= cpNodeOrderId%>">+</td>
           <td align="center"></td>
           <%} %>
           <td align="center"><span class="STYLE10"><%=orderType %></span></td>
			<td align="center"></td>
           	<td align="left" class="STYLE10" bgcolor="#FFE4B5">&nbsp;&nbsp;<%=cpNodeOrderText %></td>
           	<td align="left" class="STYLE10" bgcolor="#FFE4B5">&nbsp;</td>
           	<td align="center" class="STYLE10"><%=_needItem %></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
       	  </tr>
         <%
 			
         String continueSql ="select * from lcp_node_order_point t "
				+  " where SYS_IS_DEL=0 and  cp_id="+cpId+" and cp_node_id="+cpNodeId+" and HOSPITAL_ID="+HOSPITALID+" and t.continue_order_id = "+ conId+" and t.continue_order_id != t.cp_node_order_id order by CP_NODE_ORDER_ID";    
      	ds7 = db.FunGetDataSetBySQL(continueSql);

         	if(ds7.FunGetDataAsStringById(0,0)!=""){
				for (int k = 0; k < ds7.FunGetRowCount(); k++) {
					String cpNodeOrderId1=ds7.FunGetDataAsStringByColName(k,"CP_NODE_ORDER_ID");

					String cpNodeOrderText1 = ds7.FunGetDataAsStringByColName(k, "CP_NODE_ORDER_TEXT");
					int needCount=db.FunGetRowCountBySql(itemSql+cpNodeOrderId1+" and need_item=1");
					//int needAndexeState=db.FunGetRowCountBySql(itemSql+cpNodeOrderId1+" and need_item=1 and (exe_state is null or exe_state=0)");      	
         %>

            <tr  height="25" bgcolor="#7FFFD4" style="font-size:14px; cursor:pointer;" name="<%="Con_"+cpNodeOrderId%>"  onclick="lap2(this);">
           <td align="center" orderid="<%= cpNodeOrderId1%>">+</td>
           <td align="center"></td>
           <td align="center"><span class="STYLE10"><%=orderType %></span></td>
			<td align="center"></td>
           	<td align="left" class="STYLE10" bgcolor="#7FFFD4">&nbsp;&nbsp;<%=cpNodeOrderText1 %></td>
           	<td align="left" class="STYLE10" bgcolor="#7FFFD4">&nbsp;</td>
           	<td align="center" class="STYLE10"><%=_needItem %></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
       	  </tr>
         
           <%
           //System.out.println(itemSql+cpNodeOrderId1);
           DataSetClass ds1=db.FunGetDataSetBySQL(itemSql+cpNodeOrderId1);
           if(ds1.FunGetDataAsStringById(0,0)!=""){
			for (int j = 0; j < ds1.FunGetRowCount(); j++) {
				boolean x = false;
				String cpNodeOrderOrderId = ds1.FunGetDataAsStringByColName(j,"CP_NODE_ORDER_ID");
				String cpNodeOrderItemId=ds1.FunGetDataAsStringByColName(j,"CP_NODE_ORDER_ITEM_ID");
				String orderText=ds1.FunGetDataAsStringByColName(j,"CP_NODE_ORDER_TEXT");
			
				String need = ds1.FunGetDataAsStringByColName(j, "NEED_ITEM");
				String need1 = need;
				String auto = ds1.FunGetDataAsStringByColName(j, "AUTO_ITEM");
				//现在指定的院内医嘱编码
				String orderNo = ds1.FunGetDataAsStringByColName(j, "ORDER_NO");
				String exeState = ds1.FunGetDataAsStringByColName(j, "EXE_STATE");
				
				String type=ds1.FunGetDataAsStringByColName(j,"ORDER_TYPE_NAME");
//				if(type.equals("1")){
//					type = "其他";
//				}else if(type.equals("2")){
//					type = "检查";
//				}else if(type.equals("0")){
//					type = "检验";
//				}
				String specification = ds1.FunGetDataAsStringByColName(j,"SPECIFICATION");
				String orderKind=ds1.FunGetDataAsStringByColName(j,"ORDER_KIND");
				
				String stateItem=ds1.FunGetDataAsStringByColName(j,"STATE_ITEM");
				String measure=ds1.FunGetDataAsStringByColName(j,"MEASURE");
				String measureUnits="";
				String measureUnit_name = "";
				String measure1 = "0";
				String dosage1 = "0";
				if(measure != ""){
					measureUnits=ds1.FunGetDataAsStringByColName(j,"MEASURE_UNITS");
					measureUnit_name = CommonUtil.FunGetDataByValue(ds2,"CODE",measureUnits,"UNIT");
					measure1 = measure;
				}
				
				String frequency=ds1.FunGetDataAsStringByColName(j,"FREQUENCY");
				if(orderKind.equals("0") && frequency == ""){
					x = true;
					frequency = "ONCE";
				}

				String way=ds1.FunGetDataAsStringByColName(j,"WAY");

				String dosage=ds1.FunGetDataAsStringByColName(j,"DOSAGE");
				String dosageUnits= "";
				String dosageUnits_name = "";
				if(dosage != ""){
					dosageUnits=ds1.FunGetDataAsStringByColName(j,"DOSAGE_UNITS");
					dosageUnits_name = CommonUtil.FunGetDataByValue(ds2, "CODE", dosageUnits, "UNIT");
					dosage1 = dosage;
				}
				
				String administration=ds1.FunGetDataAsStringByColName(j,"ADMINISTRATION");
				
				String way_name = CommonUtil.FunGetDataByValue(ds4, "SUPPLY_CODE", way, "SUPPLY_NAME");
				
				String frequency_name = null;
				if(x == true){
					frequency_name = "";
				}else{
					frequency_name = CommonUtil.FunGetDataByValue(ds3, "CODE", frequency, "COMM");
				}
				

				
				
				
				String mark = ds1.FunGetDataAsStringByColName(j,"MARK");
				String orderItemSetNo = ds1.FunGetDataAsStringByColName(j,"ORDER_ITEM_SET_ID");
				//如果组号跟子项的医嘱号一致  则为这一组的开端
				
				//分组标志
				String setFlag = "&nbsp;";
				if(orderItemSetNo.trim().equals(cpNodeOrderItemId.trim())){
					setFlag = "◇";
					checkType=("selectSet(this)");
				}else{
					if(orderItemSetNo != null && !"".equals(orderItemSetNo)){
						setFlag = "├";
						checkType=("");
					}else{
						checkType=("selectSet1(this)");
					}
				}
				
				//加入新的项，该医嘱是否可用。
				String orderSql="";
				String updateSql="";
				String dictItemSql="select t.effect_flag from dcp_dict_order_item t where t.order_item_name='"+orderText+"' and t.order_item_code='"+orderNo+"'";
				String effectFlag = db.FunGetDataSetBySQL(dictItemSql).FunGetDataAsStringById(0,0);
				
				//String effectFlag = ds1.FunGetDataAsStringByColName(j,"EFFECT_FLAG");
				String visFlag = "#FFFFFF";
				//System.out.println("dictItemSql==:"+dictItemSql);
				//System.out.println("effectFlag==:"+effectFlag);
				if(effectFlag.equals("")){
										
					//查找名字正确但Code不正确
					if(!specification.equals("")){
						orderSql="select t.order_item_code from dcp_dict_order_item t where t.order_item_name='"+orderText+"' and t.specification ='"+specification+"'";
					
					}else{
						orderSql="select t.order_item_code from dcp_dict_order_item t where t.order_item_name='"+orderText+"' and t.specification is null";
					}
					//System.out.println(orderSql);
					String dictOrderCode=db.FunGetDataSetBySQL(orderSql).FunGetDataAsStringByColName(0,"ORDER_ITEM_CODE");
					if(!(orderNo.trim()).equals(dictOrderCode.trim()) && !dictOrderCode.equals("")){
						if(!specification.equals("")){
							updateSql="update lcp_node_order_item set order_no='"+dictOrderCode+"',effect_flag=0 where cp_id="+cpId+" and hospital_id="+HOSPITALID+" and cp_node_order_text='"+orderText+"' and specification='"+specification+"'";
						}else{
							updateSql="update lcp_node_order_item set order_no='"+dictOrderCode+"',effect_flag=0 where cp_id="+cpId+" and hospital_id="+HOSPITALID+" and cp_node_order_text='"+orderText+"' and specification is null";
						}
						//System.out.println(updateSql);
						int res = db.FunRunSqlByFile(updateSql.getBytes());
						if(res>0)
						visFlag = "#FFFFFF";
						//System.out.println("res====:"+res);
					}else{
						visFlag = "#AAAAAA";
						checkType = "";
					}
				}else if(!effectFlag.equals("")){
					String dictSql="select specification from dcp_dict_order_item where order_item_name='"+orderText+"' and order_item_code='"+orderNo+"'";
					String spec=db.FunGetDataSetBySQL(dictSql).FunGetDataAsStringById(0,0);
					if(!specification.equals(spec)){
						String updateSql2="update lcp_node_order_item set specification='"+spec+"',effect_flag=0 where cp_id="+cpId+" and hospital_id="+HOSPITALID+" and cp_node_order_text='"+orderText+"' and order_no='"+orderNo+"'";
		                
						int res = db.FunRunSqlByFile(updateSql2.getBytes());
						if(res>0)
							visFlag = "#FFFFFF";	
					}
					visFlag = "#FFFFFF";
				}
			
				
				
				String _exeState=("1".equals(exeState)?"<img src='../public/images/detail_s3.png' width='18' height='18'/>":"");
				if("1".equals(need)&& _needItem == ""){need="<img src='../public/images/detail_s5.png' width='18' height='18'/>";
				}else { need="";}
				String orderTypeItem="";
				if("0".equals(orderKind)){
					orderTypeItem="临时";
				}else if("1".equals(orderKind)){
					orderTypeItem="长期";
				}else if("2".equals(orderKind)){
					orderTypeItem="出院";
				}else if("3".equals(orderKind)){
					orderTypeItem="长期+临时";
				}
				String check="1".equals(stateItem)?"<img src='../public/images/success.png' width='18' height='18'/>":"";
			%>
		<tr align="center" 
		 	bgcolor="<%=visFlag %>"  onClick="<%=checkType %>" exestate1="<%=_exeState %>"  selectId="<%=orderNo %>" fristid="<%=conId %>"  id="<%=trName+"_"+cpNodeOrderItemId%>" name="<%=cpNodeOrderId1+"_"+cpNodeOrderItemId%>" style='cursor:pointer'>
		  <td id="<%=cpNodeOrderItemId %>" name="<%=cpNodeOrderOrderId %>"><%= setFlag%></td>
		  <td><%=_exeState %></td>
		  <td class="STYLE10"><%=orderTypeItem %></td>
		  <td class="STYLE10"><%=type %></td>
          <td align="center" class="STYLE10" ><%=orderText %></td>
          <td class="STYLE10" orderNo="<%=orderNo%>" ><%=specification%></td>
          
          <td class="STYLE10" need="<%=need1 %>" exestate1="<%=_exeState %>"><%=need %></td>
          
          <td class="STYLE10" stateItem="<%=stateItem %>"><%=check %></td>
          
          <td class="STYLE10" orderItemSetNo="<%=cpNodeOrderId1+orderItemSetNo%>" measureUnits = "<%=measureUnits %>" measure="<%=measure1 %> "><%=measure %><%=measureUnit_name %></td>
          
          <td class="STYLE10" frequency="<%=frequency%>"> <%=frequency_name%></td>
          
          <td class="STYLE10" way="<%=way%>" advicetype="<%=orderKind%>"> <%=way_name %></td>
          
          <td class="STYLE10" unitId="<%=cpNodeOrderId1+cpNodeOrderItemId%>" dosage="<%=dosage1 %>"><%=dosage %><%=dosageUnits_name%></td>
          
          <td class="STYLE10" dosageUnits="<%=dosageUnits%>"> <%=mark%></td>
          </tr>
			
         

          <%}}}}}}}
				catch(Exception e){ System.out.println(e);
          	out.print("<script  type='text/javascript'> alert('网络异常!请联系管理员')</script>");
          } %>
        </tbody>
      </table>
</div>


</body>
</html>