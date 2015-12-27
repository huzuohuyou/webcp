<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"    
    %>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//病人ID
	String patientNo=request.getParameter("patientNo");
	
	int hospitalId = LcpUtil.getHospitalID();
	DatabaseClass db=LcpUtil.getDatabaseClass();
	
	String logSql = "select * from lcp_patient_log_order t where t.patient_no = '"+patientNo+"' and  t.hospital_id = "+hospitalId +" order by t.repeat_indicator desc, "
	+"t.cp_node_order_item_id asc, t.enter_date_time desc,t.stop_date_time desc";
	
	//单位
	String dosageSql = "select code, unit from lcp_local_order_dosageunits";
	 DataSetClass ds2 = db.FunGetDataSetBySQL(dosageSql);
	//用法
	String freSql = "select code,comm from lcp_local_order_frequency";
	 DataSetClass ds3 = db.FunGetDataSetBySQL(freSql);
	//途径
	String waySql = "select supply_code,supply_name from lcp_local_order_way";
	DataSetClass ds4 =  db.FunGetDataSetBySQL(waySql);
	
	//医嘱类别
	String typeSql = "select order_type_code,order_type_name from dcp_dict_order_type";
	DataSetClass ds5 =  db.FunGetDataSetBySQL(typeSql);
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>临床路径患者医嘱历史记录</title>
<script type="text/javascript">

var highlightcolor='#d5f4fe';
var clickcolor='#51b2f6';
var recoveryColor='#FFFFFF';
var bgcolor="#51b2f6";
function changeColor(event){
	tempColor=event.bgColor;
	event.bgColor=highlightcolor;
	}

function recoverColor(event){
	event.bgColor=tempColor;
	tempColor=recoveryColor;
	}
</script>
</head>
<body>
<h2 align="center" style="font-size:14px;">说明： <font size=5 color="#00CCFF">■</font>为临床路径医嘱，<font size=5 color="#FFFF66">■</font>为临床路径停止的医嘱。<font size=5 color="#000000">□</font>为非临床路径医嘱，<font size=5 color="#999999">■</font>为非临床路径停止的医嘱。</h2>
<div align="center">

    <table width="1200" id="main" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="frag-3" >
       <thead>
		<tr height="30" style=" font-size:14px;" bgcolor="d3eaef" >
		  <th width="40"  >序号</th>
		  <th width="80"  >医嘱类型</th>
		  <th width="80"  >医嘱类别</th>
		  <th width="330" >项目名称</th>
		  <th width="120">下发时间</th>
		  <th width="120">停止时间</th>
		  <th width="60" >领量</th>
		  <th width="120" >一次使用剂量</th>
		  <th width="90" >频次</th>
		  <th width="100" >途径</th>	  
		  </tr>
        </thead>
        <tbody>
        <%
        try{
        	DataSetClass dsc1 = db.FunGetDataSetBySQL(logSql);
        	String orderKind = "";
        	String measure1 = "";
        	String dosage1 = "";
        	String orderClassName = "";
        	String orderText = "";
        	String orderTime = "";
        	String orderTime2 = "";
        	String frequencyName = "";
        	String adminiName = "";
        	int num = 1;
        	String bgColor = "#FFFFFF";
        	if(dsc1.FunGetDataAsStringById(0,0)!=""){
        		for(int i = 0; i < dsc1.FunGetRowCount(); i++) {
        			//医嘱类型，是长期还是临时
        			int repeatIndicator = dsc1.FunGetDataAsNumberByColName(i,"REPEAT_INDICATOR").intValue();
        			if(repeatIndicator == 1){
        			orderKind = "长期";
        			}else if(repeatIndicator == 0){
        				orderKind = "临时";
        			}
        			//医嘱类别
        			String orderClass = dsc1.FunGetDataAsStringByColName(i,"ORDER_CLASS").toString();
        			orderClassName = LcpUtil.FunGetDataByValue(ds5,"ORDER_TYPE_CODE",orderClass,"ORDER_TYPE_NAME").get(0);
        			
        			//项目名字
        			orderText = dsc1.FunGetDataAsStringByColName(i,"LOCAL_ORDER_TEXT").toString();
        			
        			//领量
        			String measure = dsc1.FunGetDataAsStringByColName(i,"MEASURE").toString();
        			String measureUnit = "";
        			String measureUnitName = "";
        			if(measure.equals("")){
        				measure1 = "";
        			}else{
        				measureUnit = dsc1.FunGetDataAsStringByColName(i,"MEASURE_UNIT").toString();
        				if(measureUnit.equals("")){
        					measureUnitName = "";
        				}else{
        					measureUnitName = LcpUtil.FunGetDataByValue(ds2,"CODE",measureUnit,"UNIT").get(0);
        				}
        				measure1 = measure + measureUnitName;
        			}
        			
        			//一次使用剂量
        			String dosage = dsc1.FunGetDataAsStringByColName(i,"DOSAGE").toString();
        			if(dosage.equals("")){
        				dosage1 = "";
        			}else{
        				String dosageUnit = dsc1.FunGetDataAsStringByColName(i,"DOSAGE_UNITS").toString();
        				if(dosageUnit.equals("")){
        					dosage1 = "";
        				}else{
        				String dosageUnitName = LcpUtil.FunGetDataByValue(ds2,"CODE",dosageUnit,"UNIT").get(0);
        				dosage1 = dosage +" " + dosageUnitName;
        				}
        			}
       				
        			
        			
        			//频次(用法)
        			String frequency = dsc1.FunGetDataAsStringByColName(i,"FREQUENCY").toString();
        			if(frequency.equals("")){
        				frequencyName = "";
        			}else{
        			    if(!LcpUtil.FunGetDataByValue(ds3,"CODE",frequency,"COMM").isEmpty()){
        			    	frequencyName = "";
        			    }
        				frequencyName = LcpUtil.FunGetDataByValue(ds3,"CODE",frequency,"COMM").get(0);
        			}
        			//途径
        			String administration = dsc1.FunGetDataAsStringByColName(i,"ADMINISTRATION").toString();
        			if(administration.equals("")){
        				adminiName = "";
        			}else{
        				adminiName = LcpUtil.FunGetDataByValue(ds4,"SUPPLY_CODE",administration,"SUPPLY_NAME").get(0);
        			}        			
        			//医嘱下发时间
        			Date orderTime1 = dsc1.FunGetDataAsDateByColName(i,"ENTER_DATE_TIME");
        			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        			orderTime = dateformat.format(orderTime1);
        			
        			//医嘱停止时间
        			Date orderStopTime = dsc1.FunGetDataAsDateByColName(i,"STOP_DATE_TIME");

        			//是否路径医嘱
        			String orderItemId = dsc1.FunGetDataAsStringByColName(i,"CP_NODE_ORDER_ITEM_ID");
        			String local_order_no = dsc1.FunGetDataAsStringByColName(i,"LOCAL_ORDER_NO");
        			if( orderItemId == null || orderItemId ==""){
        				bgColor = "#FFFFFF";
            			if(orderStopTime != null){
            				orderTime2 = dateformat.format(orderStopTime);
            				bgColor = "#999999";
            			}else{
            					orderTime2 = "";
            			}
        			}else{
        				bgColor = "#00CCFF";
            			if(orderStopTime != null){
            				orderTime2 = dateformat.format(orderStopTime);
            				bgColor = "#FFFF66";
            			}else{
            					orderTime2 = "";
            			}
        			}

        	
        %>			
        			<tr align="center" style="height:25px;font-size:12px;font-family:Calibri;" bgcolor="<%= bgColor%>" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)">
        			<td class="STYLE10"><%=num %></td>
          
		            <td class="STYLE10" ><%=orderKind %>></td>
		          
		            <td class="STYLE10"><%=orderClassName %></td>
		          
		            <td class="STYLE10"><%=orderText%></td>
		            
		             <td class="STYLE10"><%=orderTime%></td>
		             
		             <td class="STYLE10"><%=orderTime2%></td>
		          
		            <td class="STYLE10"><%=measure1 %></td>
		            
		            <td class="STYLE10"><%=dosage1%></td>
		          
		            <td class="STYLE10"><%=frequencyName %></td>
		          
		            <td class="STYLE10"><%=adminiName%></td>
		            </tr>
		            
		           
		<%  num++;
        	}
        	}
         }catch(Exception e){ 
        	e.printStackTrace();
        	 //System.out.println(e);
       		out.print("<script  type='text/javascript'> alert('网络异常!请联系管理员')</script>");
        	 
         }
		%>
        </tbody>
     </table>
</div>
</body>
</html>