<%@page import="java.text.DecimalFormat"%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.cp.util.LcpUtil"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.CommonSQL"%>
<%@page import="com.goodwillcis.lcp.model.DataSet"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>路径统计</title>
	<link rel="stylesheet"  type="text/css" href="../public/plugins/jquery/themes/dcp/jquery-ui-1.8.16.custom.css">
	<link rel="stylesheet"  type="text/css" href="../public/plugins/jqgrid/ui.jqgrid.css">
	<link rel="stylesheet"  type="text/css" href="../public/styles/style.css">
	<link rel="stylesheet"  type="text/css" href="../public/plugins/FusionCharts/style.css"  />
    <script type="text/javascript" src="../public/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="../public/plugins/FusionCharts/FusionCharts.js"></script>
	<script type="text/javascript" src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="../public/plugins/jqgrid/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="../public/plugins/jqgrid/jquery.jqGrid.min.js"></script>
	<style type="text/css"> 
		a:link{
		text-decoration:none;
		font-size: 12px;
		}
		a:visited{
		text-decoration:none;
		font-size: 12px;
		}
		.navi_title {
			float: left;
			width: 300px;
			margin: 5px;
			padding-left: 20px;
			overflow-x: hidden;
}

		</style>
<script type="text/javascript">
		var s;
		var e;
		var dcpCount=0;
		var startCP=0;
		var selfCP=0;
		var nrrs=0;
		var total=0;
		var nrl=0;
		var wcl=0;
$(function(){
	 var queryStringByName= function (queryName) {
	        var str = location.href; //取得整个地址栏
	        if (str.indexOf("?") > -1) {
	            var queryParam = str.substring(str.indexOf("?") + 1);
	            //如果有多个参数
	            //if (queryParam.indexOf("&") > -1)
	            var param = queryParam.split("&");
	            for (var a = 0; a < param.length; a++) {
	                var query = param[a].split("=");
	                if (query[0] == queryName) {
	                    return query[1];
	                 }
	            }
	        }
	        return 0;
	     };
	     var title='厦门大学附属中山医院  路径统计图表';
	    
	     $(".navi_title").html(title);
		var d = new Date();
   		var vYear = d.getFullYear();
   		var vMon = d.getMonth() + 1;
   		var vDay = d.getDate();
   		e=vYear+'-'+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0"+ vDay : vDay);
   		s=vYear+'-'+(vMon<10 ? "0" + vMon : vMon)+"-01";
		$("#date1").val(s);
		$("#date2").val(e);
		$.jgrid.ajaxOptions.type = 'post';
		var searchtype=['cn','eq'];
		$("#gridTable").jqGrid({
		url:'../ZhikongTongjiServlet?op=getTable&s='+s+'&e='+e,
		datatype: "json",
		height:'100%',
		beforeRequest:function(){//每次读取前先清空表中数据
			 $(this).clearGridData("clearfooter");
		},
		colNames:['路径编码','路径名称', '启用时间', '路径类别','符合纳入病例','纳入病例','纳入率%','完成病例','完成率%','拼音码'],
		colModel:[
		      
					{name:'CPID',index:'cp_id', width:50,align:"center",searchoptions:{ sopt:searchtype},sortable:true },
					{name:'cpName',index:'cp_name', width:200,align:"center",searchoptions:{ sopt:searchtype},sortable:false,search:false},
					{name:'startDate',index:'CP_START_DATE', width:150,align:"center",search:false ,sortable:true}	,
					{name:'cpType',index:'cpType', width:80,align:"center",search:true,stype:"select",sortable:false,
						searchoptions:{ sopt:['eq'],value:{"1":"全部路径","2":"局发路径","3":"院内自定义路径"}}},
					{name:'fhrs',index:'fhrs', width:80,align:"center",search:false,sortable:true }	,
					{name:'nrrs',index:'nrrs', width:80,align:"center",search:false,sortable:true }	,
					{name:'nrl',index:'nrl', width:80,align:"center",sortable:true,search:false }	,
					{name:'wcrs',index:'wcrs', width:80,align:"center",sortable:true,search:false},
					{name:'wcl',index:'wcl', width:80,align:"center",sortable:true,search:false},	
					{name:'input_code_py',index:'input_code_py', width:80,align:"center",width:100,align:"center",search:true
						,hidedlg:true,hidden:true,searchoptions:{searchhidden:true,sopt:searchtype},sortable:false},		

				],
		width : $(document.body).width()-20,
		viewrecords:true,
		rowNum:15,
		rownumbers:true,
		rowList:[15,30],
		jsonReader:{
			repeatitems : false
		},
		pager:"#gridPager"}).navGrid('#gridPager', {
			edit : false,
			add : false,
			del : false,
			search:false
		});


		var funchar=function(s,e){
	    	$.post("../ZhikongTongjiServlet?op=topnaru&top=max&num=5&startdate="+s+"&enddate="+e+"",function(data){
	   	     if(data!=""){
	   	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
	                 var  MarChart = new FusionCharts("../public/plugins/FusionCharts/Column2D.swf", "CPMaxIncome",  $(document).width()/2-10, "300", "0", "1");
	    	    	  MarChart.setDataXML(data);
	    	    	  MarChart.render("max_cp_income");  
	   	        }
	        });
	         $.post("../ZhikongTongjiServlet?op=topnaru&top=min&num=5&startdate='"+s+"'&enddate='"+e+"'",function(data){
	       	     if(data!=""){
	       	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
	                     var  MarChart = new FusionCharts("../public/plugins/FusionCharts/Column2D.swf", "CPMinIncome",  $(document).width()/2-10, "300", "0", "1");
	        	    	  MarChart.setDataXML(data);
	        	    	  MarChart.render("min_cp_income");  
	       	        }
	            });
		};
		funchar(s,e);
		
		
		    	
	var c=function(){
		$.ajax({
			url : "../ZhikongTongjiServlet",
			type : 'POST',
			data : {
				op : "dljtj",
				s:s,
				e:e
			},
			dataType : "json",
			success : function(data, textStatus, XMLHttpRequest) {
				data = eval(data);
				data = data[0];
				dcpCount=data.dcpCount;
				startCP=data.startCP;
				selfCP=data.selfCP;
				nrrs=data.nrrs;
				total=data.total;
				nrl=data.nrl+"%";
				//alert(data.wc+"===");
				wcl=data.wc+"%";
				//alert(wcl);
				$("#dcpCount").html(dcpCount);
				$("#startCP").html(startCP);
				$("#selfCP").html(selfCP);
				$("#downCP").html(dcpCount);
				$("#nrcount").html(nrrs);
				$("#total").html(total);
				$("#nrl").html(nrl);
				$("#wc").html(wcl);
				
			}
		});	   
	};
	
	c();
	$("#chaxun").click(function(){
		s=$("#date1").val();
    	e=$("#date2").val();
    	c();
    	funchar(s,e);
		var url='../ZhikongTongjiServlet?op=getTable&s='+s+'&e='+e;
			jQuery("#gridTable").setGridParam({url:url}).trigger("reloadGrid");
	});
	
	
	$(window).resize(function(){//窗口大小变化时
		var hes=$(document.body).width()-15;
		$("#gridTable").setGridWidth(hes,true);
	//	$("#gridTable").setGridHeight("auto");
		
		});
	});
	var addContact = function() {
		s=$("#date1").val();
    	e=$("#date2").val();
		var rowid = $("#gridTable").jqGrid("getGridParam", "selrow");
		if (rowid) {
			var rowData = $("#gridTable").getRowData(rowid);
			var url='zhikong_cp_tongji_danyi.jsp?cp_id='+rowData.CPID+'&s='+s+'&e='+e;
			window.open(url,'_self');
		}
	};
	var searchTable = function(){
		 $("#gridTable").jqGrid("searchGrid");  
	};
	


</script> 
</head>
<body>
<div class="navi">
		<div class="navi_title">
			
		</div>
		
	<div class="navi_function">
           			起始日期：<input  type="text"  width="80%" style=" color:black;width:60px;" id="date1"  readonly onfocus="WdatePicker({isShowWeek:true})"/>&nbsp;&nbsp;
					结束日期：<input  type="text"  width="80%" style="color:black;width:60px;"  id="date2" readonly onfocus="WdatePicker({isShowWeek:true})"/>&nbsp;&nbsp;&nbsp;
					<input type="button" value="查询" id="chaxun" style="color:black"  />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 		</div>
	</div>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			  <tr>
			    <td colspan="2">
			    <table width="100%" id="ttotal" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
			    <tbody id="tbtotal">
			      <tr height="24" align="center">
			        <td width="40%"  bgcolor="d3eaef" >基本路径总数</td>        
			        <td width="10%"  bgcolor="#FFFFFF" id="dcpCount"></td>
			        <td width="40%"  bgcolor="d3eaef" >启用中的路径数</td>        
			        <td width="10%" bgcolor="#FFFFFF" id="startCP"></td>        
			      </tr>
			      <tr height="24" align="center">
			        <td  bgcolor="d3eaef" >院端自定义路径数</td>        
			        <td  bgcolor="#FFFFFF" id="selfCP"></td> 
			        <td  bgcolor="d3eaef" >中心下发路径数</td>        
			        <td  bgcolor="#FFFFFF" id="downCP"></td>     
			      </tr>
			    <tr height="24" align="center">
			        <td  bgcolor="d3eaef" >路径纳入总人数</td>        
			        <td  bgcolor="#FFFFFF" id="nrcount"></td>
			        <td  bgcolor="d3eaef" >住院总人数</td>        
			        <td  bgcolor="#FFFFFF" id="total"></td>        
			      </tr>
			      <tr height="24" align="center">
			        <td  bgcolor="d3eaef" >路径纳入百分比</td>        
			        <td  bgcolor="#00FF00"  id="nrl"></td> 
                    <td  bgcolor="d3eaef" >路径完成百分比</td>        
			        <td  bgcolor="#00FF00" id="wc"></td>
			      </tr>
			      </tbody>
			    </table></td>
			  </tr>
			  <!-- 最高纳入率、最高纳入人数排行图 -->
  <tr>
        <td bgcolor="#FFFFFF" align="center">
                <span id="max_cp_income">最高纳入率的 5 条路径</span>  
            </td>
            <td align="center">
                <span id="min_cp_income">最高纳入人数的 5 条路径</span>
            </td>
  </tr>

</table>
	<div class="navi">
		<div class="navi_title">
			各路径运行情况表(双击行查看详细信息)
		</div>
		
	<div class="navi_function">
				<a href="javascript:void(0);" class="infoss" onclick="searchTable();">查询</a>
	 		</div>
	</div>
	<div align="center">
		<div id="gridPager"></div>
		<table id="gridTable" ondblclick='addContact()'></table>
		
	</div>
  
</body>
</html>