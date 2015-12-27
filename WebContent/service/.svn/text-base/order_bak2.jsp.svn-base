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
	//病人ID
	String patientID=request.getParameter("patient_no");
	//住院次数
	String admissTimes = request.getParameter("admissTimes");
	
	//纳入医嘱调用地址
	
	String order = PropertiesUtil.get(PropertiesUtil.ORDER_URL);
	String orderAddress = LcpUtil.getConfigValue(order);
	
	//不纳入医嘱调用地址
	String nullOrder = "";//PropertiesUtil.get(PropertiesUtil.NULLORDER_URL);
	String nullOrderAddress = LcpUtil.getConfigValue(nullOrder);
	
	//不纳入医嘱但是调到肿瘤病区地址
	String nullOrder1 = "";//PropertiesUtil.get(PropertiesUtil.NULLORDER1_URL);
	String nullOrderAddress1 = LcpUtil.getConfigValue(nullOrder1);
	
	//老年病一病区和二病区调用医嘱地址
	String nullOrder2 = "";// PropertiesUtil.get(PropertiesUtil.NULLORDER2_URL);
	String nullOrderAddress2 = LcpUtil.getConfigValue(nullOrder2);
	

	//路径表内查询ID
	String patientId1 = patientID + "_" + admissTimes;	

	//医生工号
	String doctorNo = request.getParameter("doctorNo");
	//病区
	String patientArea = request.getParameter("patientArea");
	
	//医生级别
	String doctorQX = request.getParameter("doctorQX");
	String alertMsg = "";
	//如果没取到值，则先给他最大权限
	if(doctorQX == null || doctorQX == ""){
		doctorQX = "2";
	}
	if(doctorQX.equals("0")){
		alertMsg = "请注意：您所在的医生组不可以下抗生素相关医嘱！";
	}else if(doctorQX.equals("1")){
		alertMsg = "请注意：您所在的医生组不可以下高级抗生素相关医嘱！";
	}
	
	String dosageSql = "select code, unit from lcp_local_order_dosageunits";
	 DataSetClass ds2 = db.FunGetDataSetBySQL(dosageSql);
	String freSql = "select code,comm from lcp_local_order_frequency";
	 DataSetClass ds3 = db.FunGetDataSetBySQL(freSql);
	String waySql = "select supply_code,supply_name from lcp_local_order_way";
	DataSetClass ds4 =  db.FunGetDataSetBySQL(waySql);
	DataSetClass ds = null;	
	DataSetClass ds7 = null;

	String cpNodeID="";
	String cpID="";
	String cpName = "";
	String cpNodeName="";
	String json = "";
	String nextNodeType = "";
	String nextNodeId = "";
	List list = new ArrayList();
	
	UpdateUtil uu = new UpdateUtil(); 
	String deptNo = uu.findCurrDeptNo(patientId1);
	
	if(patientID==null||"".equals(patientID)){
		response.sendRedirect("../route/error.html"); 
	}	
	
	//这个是为肿瘤科室特别准备的地址		
	if(patientArea.equals("1200200")){
		String orderAddress1 = nullOrderAddress1 + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1";
		response.getWriter().print(
				" <script language='javascript' type='text/javascript'>           window.location.href='"+orderAddress1+"';     </script>"
				); 
	//老年病科室
	}else if(patientArea.equals("1060000") || patientArea.equals("1060002")){
		String orderAddress2 = nullOrderAddress2 + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1";
		response.getWriter().print(
				" <script language='javascript' type='text/javascript'>           window.location.href='"+orderAddress2+"';     </script>"
				); 
	}else if(deptNo.equals("1130100")){	
		//如果是手术麻醉科，则直接跳转到医嘱系统。
		String orderAddress1 = nullOrderAddress + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1";
		response.getWriter().print(
				" <script language='javascript' type='text/javascript'>           window.location.href='"+orderAddress1+"';     </script>"
				); 
	}
	
		//查当前患者所在路径的最大节点，判断节点类型
		String sql = "select a.CP_NODE_TYPE,a.CP_ID from LCP_PATIENT_NODE a where a.CP_NODE_ID = (select max(CP_NODE_ID) from LCP_PATIENT_NODE t where t.PATIENT_NO = '"+patientId1+"') and a.PATIENT_NO = '"+patientId1+"' and a.HOSPITAL_ID = "+HOSPITALID;
		
		String currNodeType = db.FunGetDataSetBySQL(sql).FunGetDataAsStringByColName(0,"CP_NODE_TYPE").toString().trim();
		cpID = db.FunGetDataSetBySQL(sql).FunGetDataAsStringByColName(0,"CP_ID").toString().trim();
		if(cpID!= ""){
			int cpID1 = Integer.valueOf(cpID).intValue();
		}
		//if(cpID1 < 10037){
			//response.sendRedirect("../route/error1.html"); 
		//}
		
		//如果医嘱类型是0  准入节点  则自动跳转到第一天，如果是2,3,4 (完成 变异 退出) 跳到医嘱系统界面
		if(currNodeType.equals("0")){
			cpNodeID = "2";	
		//如果在node表查不到值，表示这个人没在路径内，则直接跳到医嘱页面
		}else if(currNodeType.equals("") || currNodeType.equals("2") || currNodeType.equals("3") ||currNodeType.equals("4")){	
			//如果类型是完成或者变异退出状态，则跳转到医嘱系统。
			String orderAddress1 = nullOrderAddress + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1";
			response.getWriter().print(
					" <script language='javascript' type='text/javascript'>           window.location.href='"+orderAddress1+"';     </script>"
					); 
		}
		else{
		
		//查出当前医嘱处在的最大节点
			String sql1 = "select max(cp_node_id) as cpNodeId from lcp_patient_order_point t where t.patient_no = '"+patientId1+"' and t.hospital_id = '"+HOSPITALID+"'";
			
			cpNodeID = db.FunGetDataSetBySQL(sql1).FunGetDataAsStringById(0,0).toString();
			
			if(cpNodeID == ""){
				String oneNodeSql = "select min(t.cp_node_id) as minNodeId from lcp_master_node t where t.cp_id = "+cpID+" and t.cp_node_type = 1 order by t.cp_node_id";
				
				cpNodeID = db.FunGetDataSetBySQL(oneNodeSql).FunGetDataAsStringById(0,0).trim();

				//lcp_node_order_point的数据同步到lcp_patient_order_point里
				String synSql2 = "insert into lcp_patient_order_point (hospital_id,patient_no,cp_id,cp_node_id,cp_node_order_id,cp_node_order_text,continue_item,continue_cp_node_id,continue_order_id,need_item,auto_item,order_kind,sys_is_del,sys_last_update) "+
				"(select t.hospital_id,'"+patientId1+"',t.cp_id,t.cp_node_id,t.cp_node_order_id,t.cp_node_order_text,t.continue_item,t.continue_cp_node_id,t.continue_order_id,t.need_item,t.auto_item,t.order_kind,t.sys_is_del,t.sys_last_update from lcp_node_order_point t" +
				" where t.hospital_id = "+HOSPITALID+" and t.cp_id='"+cpID+"' and t.cp_node_id='"+cpNodeID+"')\r\n";

				//lcp_node_order_item的数据同步到lcp_patient_order_item里
				String synSql3 = "insert into lcp_patient_order_item (hospital_id,patient_no,cp_id,cp_node_id,cp_node_order_id,cp_node_order_item_id,cp_node_order_text,order_no,order_type,order_type_name,order_kind,frequency,way,order_item_set_id,ADMINISTRATION,SPECIFICATION,measure,MEASURE_UNITS,DOSAGE,DOSAGE_UNITS,UNIT_ID,MARK,need_item,auto_item,cp_node_class_id,IS_ANTIBIOTIC,sys_is_del,sys_last_update,effect_flag)"+
				 " (select a.hospital_id,'"+patientId1+"',a.cp_id,a.cp_node_id, a.cp_node_order_id,a.cp_node_order_item_id,a.CP_NODE_ORDER_TEXT,a.ORDER_NO,a.ORDER_TYPE,a.ORDER_TYPE_NAME,a.ORDER_KIND,a.frequency,a.way,a.order_item_set_id,a.ADMINISTRATION,a.SPECIFICATION,a.measure,a.MEASURE_UNITS,a.DOSAGE,a.DOSAGE_UNITS,a.UNIT_ID,a.MARK,a.need_item,a.auto_item,a.cp_node_class_id,a.IS_ANTIBIOTIC,sys_is_del,sys_last_update,a.effect_flag from lcp_node_order_item a" +
				 " where a.hospital_id = "+HOSPITALID+" and a.cp_id='"+cpID+"' and a.cp_node_id='"+cpNodeID+"')\r\n";

				String synSql = synSql2 + synSql3;

try {
	 int result = db.FunRunSqlByFile(synSql.getBytes("GBK"));
	 
			 
	 
	 
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect("../route/error.html"); 
				}
			}
			
		//查出下个节点医嘱节点	
			String sql4 = "select min(t.cp_node_id) as minNodeId from lcp_master_node t where t.cp_id = "+cpID+" and t.cp_node_type = 1 and t.cp_node_id > "+cpNodeID+" order by t.cp_node_id" ;

			DataSetClass ds6 = db.FunGetDataSetBySQL(sql4);
			if(ds6.FunGetRowCount()<=0){
				//一般不应该出现这种情况，但也有可能顺序定义时搞错的，先让他定义为最后一个节点
			}else{
				nextNodeId = ds6.FunGetDataAsStringByColName(0,"MINNODEID").toString();

				//如果是空值  则不处理
				if(cpNodeID == null||"".equals(cpNodeID)){
					response.sendRedirect("../route/error.html"); 
				}
				
			}
			
			String sql2 = "select * from lcp_master_node a where a.cp_node_id = (select max(cp_node_id) from lcp_patient_order_point t where t.patient_no = '"+patientId1+"' and t.hospital_id = '"+HOSPITALID+"') "+
			"and a.cp_id = (select distinct cp_id from lcp_patient_order_point t where t.patient_no = '"+patientId1+"' and t.hospital_id = '"+HOSPITALID+"')";
			
			//查出对应的node表的数据，取到路径ID，医嘱对应节点号，名字
			DataSetClass ds5=db.FunGetDataSetBySQL(sql2);
	   	   
				//取出路径名字
				cpName = db.FunGetDataSetBySQL("select CP_NAME from lcp_master  where CP_ID = "+cpID).FunGetDataAsStringById(0,0).toString();
				cpNodeID=ds5.FunGetDataAsStringByColName(0,"CP_NODE_ID");
				//取出路径节点名字
				cpNodeName=ds5.FunGetDataAsStringByColName(0,"CP_NODE_NAME");
				
				//把point表内容从这取出
				 String sql3="select * from lcp_patient_order_point t "
						+  " where SYS_IS_DEL=0 and  cp_id="+cpID+" and cp_node_id="+cpNodeID+" and HOSPITAL_ID="+HOSPITALID+" and PATIENT_NO='"+patientId1+"' and t.continue_order_id = t.cp_node_order_id order by CP_NODE_ORDER_ID";
					ds=db.FunGetDataSetBySQL(sql3);
					
				       //组出当前节点下所有二级的ID 放入list中备用
					//把point表内容从这取出
					 String conSql="select * from lcp_patient_order_point t "
							+  " where SYS_IS_DEL=0 and  cp_id="+cpID+" and cp_node_id="+cpNodeID+" and HOSPITAL_ID="+HOSPITALID+" and PATIENT_NO='"+patientId1+"' and t.continue_order_id != t.cp_node_order_id order by CP_NODE_ORDER_ID";
						DataSetClass dsc8=db.FunGetDataSetBySQL(conSql);
						int rowTwo = dsc8.FunGetRowCount();
						for(int x=0; x<rowTwo; x++){
							list.add(dsc8.FunGetDataAsStringByColName(x,"CP_NODE_ORDER_ID").trim());
						}
				
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
<title>临床路径医嘱下发页面</title>
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

//打开已经下发的医嘱列表
function openRecord(){
	window.open('record.jsp?patientNo=<%=patientId1%>');
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

//跳转到医嘱系统时，只去掉打勾，而保留变色
function unSelectAll1(event){

	$("tr").each(function(){
		changeSignNull1(this);
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

//选择方案按钮
function selectSchema(){
	$("#table").load("../servlet/NodeUpdate",{op:"getSchema",cpId:"<%=cpID%>",cpNodeId:"<%=cpNodeID%>",doctorNo:"<%=doctorNo%>"});
	$("#div1").dialog("open");
}

//折叠操作(第一级菜单专用)
function lap(event){
	var x = $(event).children().eq(0).html();
	var y = $(event).children().eq(0).attr("orderid");
	if(x == "-"){
		$($(event).children().eq(0)).html("+");
		var trs2=$("tr[name^='Con_"+y+"']");

		trs2.hide();
		
		for (var x in arrList){
	        var trs1=$("tr[name^='"+arrList[x]+"_']");
	        $(trs2.children().eq(0)).html("+");
			
	        var trs1=$("tr[fristid^='"+y+"']");
	        
			trs1.hide();
		}
	}else{
		$($(event).children().eq(0)).html("-");

		var trs2=$("tr[name^='Con_"+y+"']");
		
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


//执行保存操作
function save1(){
	var comments = "";
	msg=$("#comment").val();
	$.ajax({
	   url: "../servlet/NodeUpdate",
	   type: "POST",
	   async: false,
	   data: {op:"updateOrder",
		   cpId:"<%=cpID%>",
		   cpNodeId:"<%=cpNodeID%>",
		   doctorNo:"<%=doctorNo%>",
		   orderIds: orderIds,
		   comments: msg	   
 	},
	   dataType: "json",
	   complete: show_result,
	   success: function(data, textStatus, XMLHttpRequest){
	   }
 });
}

//执行到下一节点
function toNext(){
	$.ajax({
	   url: "../servlet/NextOrder",
	   type: "POST",
	   async: false,
	   data: {op:"toNextOrder",
		   cpId:"<%=cpID%>",
		   cpNodeId:"<%=nextNodeId%>",
		   patientId:"<%=patientId1%>"		   
		   
 	},
	   dataType: "json",
	   complete: show_result1,
	   success: function(data, textStatus, XMLHttpRequest){
		   if(data[0].result === "OK"){
			   //window.location.href = "http://www.baidu.com";
			   var url = "../service/order.jsp?patient_no="+<%=patientID%>+"&admissTimes="+<%=admissTimes%>+"&doctorNo="+<%=doctorNo%>+"&patientArea="+<%=patientArea%>+"&doctorQX="+<%=doctorQX%>;
			   window.location.href=url;

		   }
		   if(data[0] === "fail"){
			   alert("执行失败，请与管理员联系！");
		   }		   
	   }
 });
}

//点击下一节点时候的操作
function gotoNext(){
	if(confirm("确定要执行到下一节点的医嘱吗?")) 
	{ 
	    toNext();
	} 
	
}






function NodeColor(event){
	var a=event.bgColor;
	event.bgColor=(tempColor==='#51b2f6'?recoveryColor:clickcolor);
	tempColor=event.bgColor;
	var html="<img src='../public/images/success.png' width='18' height='18'/>";
	
	var check=(tempColor==='#51b2f6'?html:"");
	$($(event).children().eq(7)).html(check);
	
}


function changeSign(event){
	if(event.bgColor == '#aaaaaa'){
		
	}else{
	event.bgColor=clickcolor;
	var html="<img src='../public/images/success.png' width='18' height='18'/>";

	$($(event).children().eq(7)).html(html);
	}
}

function changeSignNull(event){

	var x = $(event).children().eq(0).html();
	
	if(x == "-" || x == "+"){		
		
	}else if(event.bgColor == '#aaaaaa'){
	}else{
		event.bgColor="#FFFFFF";
		$($(event).children().eq(7)).html("");	
	}
}

function changeSignNull1(event){

	var x = $(event).children().eq(0).html();
	
	if(x == "-" || x == "+"){		
		
	}else if(event.bgColor == '#aaaaaa'){
	}else{
		$($(event).children().eq(7)).html("");	
	}
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
	
	$("#sub").click(function(){
	checkTrs="";
	var checkTrs1 = "";
	var checkTrs2 = "";
	var checkTrs3 = "";
	var order = "";
	var jianyan = "";
	var jiancha = "";
	var other = "";
	var jsons = "";
	$("tr").each(function(){
		var html=$(this).children().eq(7).html();
		var typeName = $(this).children().eq(3).html();
		//判断本次操作里发生变化的所有值
		if(html !="" && html != null){
			var orderName=$(this).children().eq(4).html().replace( /^\s*/, '');
			var mark = $(this).children().eq(12).html();

			if(typeName == "检验" ){
				
				checkTrs1+="{";
				checkTrs1+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
				checkTrs1+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
				checkTrs1+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
				checkTrs1+="'orderName':'"+orderName+"',";
				checkTrs1+="'mark':'" +mark+"',";
				checkTrs1+="'orderKind':'"+$(this).children().eq(10).attr("advicetype")+"',";
				checkTrs1+="'stateItem':'"+$(this).children().eq(5).attr("orderNo")+"',";
				checkTrs1+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
				checkTrs1+="'measure':'"+$(this).children().eq(8).attr("measure")+"',";
				checkTrs1+="'measureUnits':'"+$(this).children().eq(8).attr("measureUnits")+"',";
				checkTrs+="'frequency':'"+$(this).children().eq(9).attr("frequency")+"',";
				checkTrs1+="'way':'" +$(this).children().eq(10).attr("way")+"',";
				checkTrs1+="'dosage':'" +$(this).children().eq(11).attr("dosage")+"',";
				checkTrs1+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
				checkTrs1+="'advicetype':'"+$(this).children().eq(10).attr("advicetype")+"',";
				checkTrs1+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
				checkTrs1+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
				
			    

			}else if(typeName == "检查"){
				
				checkTrs2+="{";
				checkTrs2+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
				checkTrs2+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
				checkTrs2+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
				checkTrs2+="'orderName':'"+$(this).children().eq(4).html()+"',";
				checkTrs2+="'mark':'" +mark+"',";
				checkTrs2+="'orderKind':'"+$(this).children().eq(10).attr("advicetype")+"',";
				checkTrs2+="'stateItem':'"+$(this).children().eq(5).attr("orderNo")+"',";
				checkTrs2+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
				checkTrs2+="'measure':'"+$(this).children().eq(8).attr("measure")+"',";
				checkTrs2+="'measureUnits':'"+$(this).children().eq(8).attr("measureUnits")+"',";
				checkTrs2+="'frequency':'"+$(this).children().eq(9).attr("frequency")+"',";
				checkTrs2+="'way':'" +$(this).children().eq(10).attr("way")+"',";
				checkTrs2+="'dosage':'" +$(this).children().eq(11).attr("dosage")+"',";
				checkTrs2+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
				checkTrs2+="'advicetype':'"+$(this).children().eq(10).attr("advicetype")+"',";
				checkTrs2+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
				checkTrs2+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
				

			}else if(typeName != "类别"){
				
				checkTrs3+="{";
				checkTrs3+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
				checkTrs3+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
				checkTrs3+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
				checkTrs3+="'orderName':'"+$(this).children().eq(4).html()+"',";
				checkTrs3+="'mark':'" +mark+"',";
				checkTrs3+="'orderKind':'"+$(this).children().eq(10).attr("advicetype")+"',";
				checkTrs3+="'stateItem':'"+$(this).children().eq(5).attr("orderNo")+"',";
				checkTrs3+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
				checkTrs3+="'measure':'"+$(this).children().eq(8).attr("measure")+"',";
				checkTrs3+="'measureUnits':'"+$(this).children().eq(8).attr("measureUnits")+"',";
				checkTrs3+="'frequency':'"+$(this).children().eq(9).attr("frequency")+"',";
				checkTrs3+="'way':'" +$(this).children().eq(10).attr("way")+"',";
				checkTrs3+="'dosage':'" +$(this).children().eq(11).attr("dosage")+"',";
				checkTrs3+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
				checkTrs3+="'advicetype':'"+$(this).children().eq(10).attr("advicetype")+"',";
				checkTrs3+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
				checkTrs3+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
				
				}
			}
			
		
	});	
	
	checkTrs1=checkTrs1.substr(0,checkTrs1.length-1);

	checkTrs2=checkTrs2.substr(0,checkTrs2.length-1);

	checkTrs3=checkTrs3.substr(0,checkTrs3.length-1);

	if(checkTrs1 != ""){
		jianyan = "'inspect':["+checkTrs1+"],";
	}
	if(checkTrs2 != ""){
		jiancha = "'check':["+checkTrs2+"],";
	}
	if(checkTrs3 != ""){
		other = "'advice':["+checkTrs3+"],";
	}
	order = jianyan + jiancha + other;
	
	order = order.substr(0,order.length-1);
	if(order == ""){
		jsons = "{'inpatientNo':'<%=patientID%>','cpId':'<%=cpID%>','cpNodeId':'<%=cpNodeID%>','admissTimes':'<%=admissTimes%>','doctorNo':'<%=doctorNo%>','patientArea':'<%=patientArea%>','doctorQX':'<%=doctorQX%>'}";
	}else{
		jsons="{'inpatientNo':'<%=patientID%>','cpId':'<%=cpID%>','cpNodeId':'<%=cpNodeID%>','admissTimes':'<%=admissTimes%>','doctorNo':'<%=doctorNo%>','patientArea':'<%=patientArea%>','doctorQX':'<%=doctorQX%>',"+ order +"}";
	}
	json = jsons;
	<%
	 java.net.URLEncoder.encode(json);
	%>
	//alert(json);
	//往后台提交选中状态
//	$.ajax({
//		   url: "../servlet/NodeUpdate",
//		   type: "POST",
//		   async: false,
//		   data: {op:"updateOrder",
//			   cpId:"<%=cpID%>",
//			   nodeId:"<%=cpNodeID%>",
//			   orderIds: orderIds,
//			   patientNo:"<%=patientId1%>"
//	    	},
//		   dataType: "json",
//		   complete: show_result,
//		   success: function(data, textStatus, XMLHttpRequest){
//		   }
//	 });


//中山医院json格式[{患者编号:123},{cpID:1},{cpNodeID:2},{项目:[{项目编号:aaaa,频次:bbbb,计量:cccc,项目类型:检查|检验|护理|处置},{项目编号:aaaa,频次:bbbb,计量:cccc,项目类型:检查|检验|护理|处置}]}]
  	//document.write("<form id='post1' name='post1' action='<%=orderAddress%>' method='POST'>"); 
 	//document.write('<input type="hidden" name="order" value="'+json+'" />');
 	//document.write('</form>');  
 	//document.getElementById('post1').submit();
 	
	
	
	openPostWindow('<%=orderAddress%>',json,'order'); 

	unSelectAll1(this);
	
	});
});

function openPostWindow(url, data, name){  	
	
	var tempForm = document.createElement("form");  
	tempForm.id="tempForm1";  
	tempForm.method="post";  
	tempForm.action=url;  
	tempForm.target=name;  
	    
	var hideInput = document.createElement("input");  
	hideInput.type="hidden";  
	hideInput.name= "order"
	hideInput.value= data;
	tempForm.appendChild(hideInput);   
	tempForm.attachEvent("onsubmit",function(){ openWindow(name); });
	document.body.appendChild(tempForm);  
	    
	tempForm.fireEvent("onsubmit");
	tempForm.submit();
	document.body.removeChild(tempForm);
}


function openWindow(name)  
      {  
         window.open('about:blank',name,'height=768, width=1024, top=0, left=0, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');   
      } 

jQuery.fn.Scrollable = function(tableHeight, tableWidth) {
	this.each(function(){
		if (jQuery.browser.msie || jQuery.browser.mozilla) {
			// var table = new ScrollableTable(this, tableHeight, tableWidth);
		}
	});
};


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
<h1 align="center" style="font-size:20px;"><%=cpName %>临床路径医嘱方案(<%=cpNodeName %>)</h1>

 <br>
 <h2 align="center" style="font-size:18px; color:red;"><%=alertMsg %></h2>
 <h3 align="center" style="font-size:14px; color:red;">说明：1. 如有颜色是 <font size="14" color="#AAAAAA">■</font> 的医嘱项是不能下发的，因为在医嘱系统里这些项是作废的或者库存为0，请大家总结并选择替代这些的医嘱项，整理好发给信息科，谢谢！</h3>
 <h4 align="center" style="font-size:14px; color:red;">2. 医嘱下发时请不要把检验，检查，其他分类一起提交，医嘱系统中检验检查其他这三个分类是在三个不同界面里，混在一起提交的话会只能看到一个类别的医嘱而引起误会，希望大家认真按照规定执行。</h4>
<div style="font-size:14px;" align="center">
 &nbsp;&nbsp;&nbsp;
 状态说明: 
 
 <img src="../public/images/detail_s3.png" height="16" width="16"/>已执行

&nbsp;&nbsp;&nbsp; 
 必选说明:
 <img src="../public/images/detail_s4.png" height="16" width="16"/>单项选一
  &nbsp;&nbsp;&nbsp; 
 <img src="../public/images/detail_s5.png" height="16" width="16"/>此项目必选,否则填变异
  &nbsp;&nbsp;&nbsp; 
  ★&nbsp;必选未下达的医嘱
  &nbsp;&nbsp;&nbsp;
  患者编码:<strong><%=patientID%></strong>&nbsp;
    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp; 
<!--    <input type="button" id="sub" value="提交"/> -->
<br/>
<br/>
<input type="button"  onclick="selectSchema()"; value="选择模板"/> 
<input type="button"  onclick="openRecord()"; value="查看已下发医嘱"/> 
<input type="button"  onclick="move()"; value="跳至底部"/>  
<input type="button"  onclick="selectAll(this)"; value="全选"/>  
<input type="button"  onclick="unSelectAll()"; value="全不选"/>
<input type="button"  onclick="selectOthers()"; value="选中未执行项"/> 
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
    
 		  String itemSql="select * from lcp_patient_order_item "
 						+"where sys_is_del=0 and cp_id="+cpID+" and cp_node_id="+cpNodeID+" and HOSPITAL_ID="+HOSPITALID+" and PATIENT_NO='"+patientId1+"' and CP_NODE_ORDER_ID =";
 		  
 		 String itemSql2="select * from lcp_patient_order_item "
				+"where sys_is_del=0 and cp_id="+cpID+" and cp_node_id="+cpNodeID+" and HOSPITAL_ID="+HOSPITALID+" and PATIENT_NO='"+patientId1+"' and CP_NODE_ORDER_ID in";

 			
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
									+  " where SYS_IS_DEL=0 and  cp_id="+cpID+" and cp_node_id="+cpNodeID+" and HOSPITAL_ID="+HOSPITALID+" and PATIENT_NO='"+patientId1+"' and t.continue_order_id = "+ conId+" and t.continue_order_id != t.cp_node_order_id"; 
							//int needCount1=db.FunGetRowCountBySql(itemSql2+"("+needSql+")"+" and need_item=1");
							int needAndexeState1=db.FunGetRowCountBySql(itemSql2+"("+needSql+")"+" and need_item=1 and (exe_state is null or exe_state=0)");
							
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
 			
         String continueSql ="select * from lcp_patient_order_point t "
				+  " where SYS_IS_DEL=0 and  cp_id="+cpID+" and cp_node_id="+cpNodeID+" and HOSPITAL_ID="+HOSPITALID+" and PATIENT_NO='"+patientId1+"' and t.continue_order_id = "+ conId+" and t.continue_order_id != t.cp_node_order_id order by CP_NODE_ORDER_ID";    
      	ds7 = db.FunGetDataSetBySQL(continueSql);

         	if(ds7.FunGetDataAsStringById(0,0)!=""){
				for (int k = 0; k < ds7.FunGetRowCount(); k++) {
					String cpNodeOrderId1=ds7.FunGetDataAsStringByColName(k,"CP_NODE_ORDER_ID");

					String cpNodeOrderText1 = ds7.FunGetDataAsStringByColName(k, "CP_NODE_ORDER_TEXT");
					//int needCount=db.FunGetRowCountBySql(itemSql+cpNodeOrderId1+" and need_item=1");
					int needAndexeState=db.FunGetRowCountBySql(itemSql+cpNodeOrderId1+" and need_item=1 and (exe_state is null or exe_state=0)");      	
         %>
         <%
         if(needAndexeState != 0){
				
			
         %>
         <tr  height="25" bgcolor="#7FFFD4" style="font-size:14px; cursor:pointer;" name="<%="Con_"+cpNodeOrderId%>"  onclick="lap2(this);">
           <td align="center" orderid="<%= cpNodeOrderId1%>">+</td>
           <td align="center">★</td>
           <%}else if(needAndexeState==0){ %>
            <tr  height="25" bgcolor="#7FFFD4" style="font-size:14px; cursor:pointer;" name="<%="Con_"+cpNodeOrderId%>"  onclick="lap2(this);">
           <td align="center" orderid="<%= cpNodeOrderId1%>">+</td>
           <td align="center"></td>
           <%} %>
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
				String effectFlag = ds1.FunGetDataAsStringByColName(j,"EFFECT_FLAG");
				String visFlag = "#FFFFFF";
				if(effectFlag.equals("1")){
					visFlag = "#AAAAAA";
					checkType = "";
				}else if(!effectFlag.equals("1")){
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
  <div id="commit" style="height:30px" align="center">
    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
  <input type="button" style="width: auto; height: auto; font-size: 20px" id="sub" value="提交"/>
  <input type="button" style="width: auto; height: auto; font-size: 20px" onclick="saveSchema()" value="保存为模板"  />
  <input type="button" style="width: auto; height: auto; font-size: 20px" onclick="gotoNext()" value="执行下一节点医嘱"  />
  
<br/>
</div>
<div id="div">
	<textarea id="comment" rows="10" cols="50"></textarea>

</div>
<div id="div1">
	     <table width="100%" id="users" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    		<tr bgcolor="d3eaef" class="STYLE10" align="center">
		        <td width="10%" height="20"  >医嘱方案编号</td>
		        <td width="40%" height="20" >医嘱方案备注</td>
      		</tr>
      		<tbody id="table">
      		</tbody>
    	</table>
</div>


</body>
</html>