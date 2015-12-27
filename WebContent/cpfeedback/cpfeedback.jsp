<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String riqi = sdf.format(new Date());
String rpt =request.getParameter("rpt");
String begintime=riqi.substring(0,8)+"01";
String cpName=request.getParameter("cp_name");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>路径负反馈页面</title>
<link rel="stylesheet" type="text/css" href="../public/plugins/jquery/themes/dcp/jquery-ui-1.8.16.custom.css">
<link rel="stylesheet" type="text/css" href="../public/plugins/jqgrid/ui.jqgrid.css">
<link rel="stylesheet" href="../public/plugins/jquery/jquery.autocomplete.css"> 
<link rel="stylesheet" type="text/css" href="../public/plugins/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../public/plugins/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../public/styles/style.css">
<script type="text/javascript" src="../public/plugins/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../public/plugins/jqgrid/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="../public/plugins/jqgrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="../public/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../public/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../public/plugins/jquery/jquery.autocomplete.js"></script>
<link rel="stylesheet" type="text/css" href="../cpfeedback/cpfeedback.css">
<script type="text/javascript">
var cc=0;
var dd=0;
var ll=new Array();
var temp=0;
var ss=new Array();
function lap(event){
	document.all.item("bottom").style.cursor="pointer";
	var x = $(event).children().eq(0).html();
	var y = $(event).children().eq(0).attr("orderid");
	if(x == "-"){
		$($(event).children().eq(0)).html("+");
		var trs2=$("tr[parentid='"+y+"']");
		trs2.hide();
		for(var n in ll){
			if(ll[n].indexOf(y)==0){
				$(trs2.children().eq(0)).html("+");
				temp=ll[n].substr(ll[n].indexOf("_")+1,ll[n].length-((ll[n].substr(0,ll[n].indexOf("_"))).length+1));
				ss.push(temp);
				//alert("========================:"+temp);
				$("#"+temp).hide();
				//trs1.hide();
				//alert("0000000000::===:"+ll[n].substring(3,2));
			}
		}
	}else{
		cc=y;
		$($(event).children().eq(0)).html("-");
		var trs2=$("tr[parentid='"+y+"']");
		trs2.show();
	}	
};
</script>
</head>
<body>
	<div id="top">
		<table width="100%" class="title" border="0" cellspacing="0" cellpadding="0" bgcolor="#353c44">
			<tr>
				<td>
					<a id="search_patient" class="">查找病人</a>&nbsp;&nbsp;
					<a id="choose_order" class="">选择基准医嘱</a>&nbsp;&nbsp;
					<a id="reduce_advice" class="">精减建议</a>&nbsp;&nbsp;
					<a id="reduce_order" class="">精减医嘱</a>&nbsp;&nbsp;
					<a id="add_advice" class="">增加建议</a>&nbsp;&nbsp;
					<a id="add" class="">增加</a>&nbsp;&nbsp;
					<a id="confirm" class="">确定必选项</a>&nbsp;&nbsp;
					<a id="trim_sequence" class="">整理顺序</a>&nbsp;&nbsp;
					<a id="save" class="">保存</a>&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</div>
	<div id="center" style="background-color:#353c44;">
		<table width="50%" class="title"  border="0" cellspacing="8" cellpadding="0" bgcolor="#353c44">
			<tr>
				<td >开始入院时间<input  type="text"  style=" color:black;width:120px;" id="enter_date"  
				value="<%=begintime %>" readonly onfocus="WdatePicker({isShowWeek:true})"/></td>
				<td>截止入院时间<input  type="text"  style=" color:black;width:120px;" id="out_date"  
				value="<%=riqi %>" readonly onfocus="WdatePicker({isShowWeek:true})"/></td>
				<td>选择待建路径<input  type="text"  style=" color:black;width:120px;" id="choose_way" value="<%=cpName %>"/></td>
			</tr>
			<tr>
				<td>根据<input type="text" style="width:13px;"/>个病人的数据生成路径</td>
				<td>其中出现<input type="text" style="width:13px;"/>次以上的属于必选项</td>
				<td>出现<input type="text" style="width:13px;"/>次以上的医嘱属于可选项</td>
			</tr>
		</table>
	</div>
	<div id="bottom" class="ui-jqgrid ui-widget ui-widget-content ui-corner-all" dir="ltr" style="width: 100%;">
		<div  class="ui-jqgrid-view" style="width: 100%">
		<div class="ui-state-default ui-jqgrid-hdiv" style="width: 100%;">
		
		<table cellspacing="0" cellpadding="0" border="0" aria-labelledby="gbox_gridTable" role="grid" style="width:100%" class="ui-jqgrid-htable">
			<thead>
			<tr class="ui-jqgrid-labels" role="rowheader">
				<th class="ui-state-default ui-th-column ui-th-ltr" role="columnheader" style="width: 5%;" ></th>
				<th class="ui-state-default ui-th-column ui-th-ltr" role="columnheader" style="width: 9.5%;" >项目序号</th>
				<th class="ui-state-default ui-th-column ui-th-ltr" role="columnheader" style="width: 14.5%;">项目名称</th>
				<th class="ui-state-default ui-th-column ui-th-ltr" role="columnheader" style="width: 4.5%;">执行率</th>
				<th class="ui-state-default ui-th-column ui-th-ltr" role="columnheader" style="width: 9.5%%;">规格</th>
				<th class="ui-state-default ui-th-column ui-th-ltr" role="columnheader" style="width: 9.5%%;">必选</th>
				<th class="ui-state-default ui-th-column ui-th-ltr" role="columnheader" style="width: 9.5%%;">频率</th>
				<th class="ui-state-default ui-th-column ui-th-ltr" role="columnheader" style="width: 9.5%%;">剂量</th>
				<th class="ui-state-default ui-th-column ui-th-ltr" role="columnheader" style="width: 9.5%%;">单位</th>
				<th class="ui-state-default ui-th-column ui-th-ltr" role="columnheader" style="width: 9.5%;"> 数量</th>
				<th class="ui-state-default ui-th-column ui-th-ltr" role="columnheader" style="width: 9.5%;">单位</th>
			</tr>
			</thead>
	<tbody>
				
				<tr id="day1" onclick="lap(this)" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" orderid="day1">+</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484627</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >第1天</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >85%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr id="day2" onclick="lap(this)" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" orderid="day2">+</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484667</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >第2天</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >88%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484668</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >一次性空针20ml(洁璃)(透析纸)</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >89%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484669</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >静脉抽血</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >78%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484670</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >电解质分析(绿管)</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >75%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484671</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >环胞霉素A血药浓度测定</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >80%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484672</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >巨细胞病毒抗原检测(CMVpp65)</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >84%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484673</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >C-反应蛋白(进口试剂)</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >89%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484674</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >普通细菌涂片及染色</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >90%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484675</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >粪便常规(含潜血)</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >91%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484676</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >尿10项(含尿沉渣及自动分析)</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >86%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484677</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >普通细菌培养</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >88%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484678</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >普通细菌涂片及染色</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >79%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484679</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >粪便常规(含潜血)</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >86%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484680</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >尿10项(含尿沉渣及自动分析)</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >76%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484681</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >英脱利匹特注射液</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >82%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484682</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >万汶注射液</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >82%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484683</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >葡萄糖氯化钠注射液</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >79%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484684</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >氯化钾注射液</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >83%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484685</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >甲强龙粉针</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >86%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484686</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >高舒达片</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >76%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484687</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >高舒达粉针</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >79%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day2" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484688</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >一次性空针10ml(沽璃)(招)</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >80%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr id="day3" onclick="lap(this)" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" orderid="day3">+</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484689</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >第3天</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >83%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day3" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left" >&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484690</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >一次性空针10ml(沽璃)(招)</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >81%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
				<tr parentid="day3" class="ui-widget-content jqgrow ui-row-ltr" role="row" tabindex="-1" style="height:auto;display:none;"> 
					<td class="ui-state-default jqgrid-rownum" aria-describedby="gridTable_rn"  style="text-align:center;width: 5%;border-style:none;" role="gridcell" align="left">&nbsp;&nbsp;</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" >484691</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 14.5%;" role="gridcell" >静脉抽血</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 4.5%;" role="gridcell" >73%</td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
					<td aria-describedby="gridTable_USER_NAME" title="0672" style="text-align:center;width: 9.5%;" role="gridcell" ></td>
				</tr>
			</tbody>		
		</table>
		</div>
		</div>
	
	</div>
</body>
</html>