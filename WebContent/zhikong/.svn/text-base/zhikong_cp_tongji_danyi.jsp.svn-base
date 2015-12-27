<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String cpID=request.getParameter("cp_id");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>路径统计</title>
<script type="text/javascript" src="../public/plugins/FusionCharts/FusionCharts.js"></script>
<script src="../public/plugins/jquery/jquery-1.5.1.js"></script>
<link rel="stylesheet" href="../public/plugins/FusionCharts/style.css" type="text/css" />
<link rel="stylesheet" href="../public/styles/demos.css">
<style type="text/css">
a:link {
	text-decoration: none;
	color: #000000;
	font-size: 12px;
}

a:visited {
	text-decoration: none;
	color: #000000;
	font-size: 12px;
}

.tr1class {
	background-color: #d3eaef;
	text-align: center;
	font-size: 12px;
}

.tr2class  td {
	font-size: 12px;
	background-color: #FFFFFF;
	text-align: center;
}

td {
	height: 24px;
}
</style>
<script type="text/javascript">
$(function(){
	
	function to2bits(flt) { 
		if(parseFloat(flt) == flt) 
		return Math.round(flt * 100) / 100; 
		// 到4位小数, return Math.round(flt * 10000) / 10000; 
		else 
		return 0; 
		} 
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
	        return "";
	     };
	     var cp_id=queryStringByName('cp_id');
	     var s=queryStringByName('s');
	     var e=queryStringByName('e');
	var url="../ZhikongTongjiServlet?op=getZhiKongCPTongJidan";
	 $.post(url,{"one":1,"cp_id":cp_id,"s":s,"e":e},function(data){
		 if(data){
			$("#cp_days").html(" 平均住院日统计（路径定义平均住院日："+data.cp_days+" 天）");
			$("#times").html(data.cp_name+" （统计时段："+s+"至"+e+"）");
			$("#cp_fee").html("临床路径卫生经济学统计（路径定义平均总费用："+data.cp_fee+"元）");
			$("#ts0").html(data.ts0);
			$("#ts1").html(data.ts1);
			$("#ts2").html(data.ts2);
			$("#ts3").html(data.ts3);
			$("#ts4").html(data.ts4);
			$("#ts5").html(data.ts5);

			$("#wsjj0").html(data.wsjj0);
			$("#wsjj1").html(data.wsjj1);
			$("#wsjj2").html(data.wsjj2);
			$("#wsjj3").html(data.wsjj3);
			$("#wsjj4").html(data.wsjj4);
			$("#wsjj5").html(data.wsjj5);
		 }
	 },"json");
	 
	 
	 $.post(url,{"one":2,"cp_id":cp_id,"s":s,"e":e},function(data){
		 if(data){
			$("#ryzs").html(data.ryzs);
			$("#fhbl").html(data.fhbl);
			$("#nrbl").html(data.nrbl);
			$("#zxzbl").html(data.zxzbl);
			$("#wcbl").html(data.wcbl);
			$("#bytcbl").html(data.bytcbl);
			
			$("#kjywrs").html(data.kjywrs+"%");
			$("#bsykj").html(data.bsykj+"%");
			
			var bnrl=to2bits(((data.fhbl-data.nrbl)/data.fhbl)*100);
			var bytcl=to2bits((data.bytcbl/data.nrbl)*100);
			$("#bnrl").html(bnrl+"%");
			$("#bytcl").html(bytcl+"%");

		 }
	 },"json");
	 
	// {"cp_name":"GRAVES鐥?,"cp_days":"15","cp_fee":"3700","":4,"bnrl":"83.75","bytcl":"30.77","kjywrs":15.38,"bsykj":2. 
});

</script>
</head>
<body>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td height="30" bgcolor="#353c44" colspan="3">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="24" height="19" valign="bottom" align="center"><img src="../public/images/tb.gif" width="14" height="14" />
						</td>
						<td valign="bottom" id="times" class="STYLE1">  </td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- 平均住院日统计 -->
		<tr>
			<td colspan="3"><table width="100%" id="ttotal" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
					<tbody id="tbtotal">
						<tr>
							<td width="120" bgcolor="#FFFFFF" rowspan="5" align="center">
							 <a href="zhikong_cp_tongji_danyi_baobiao_zyr.jsp?cp_id= <%=cpID %>"> <img
									src="../public/images/t31_1.png" width="64" height="64" alt="进入详细的平均住院日统计" /><br />平均住院日统计
									 </a> 
							</td>
							<td id="cp_days" bgcolor="353c44" colspan="3" align="center" class="STYLE1"></td>
						</tr>
						<tr class="tr1class">
							<td>纳入病例的平均住院日</td>
							<td>纳入病例的最小住院日</td>
							<td>纳入病例的最大住院日</td>
						</tr>
						<tr class="tr2class">
							<td id="ts0">计算中……</td>
							<td id="ts1">计算中……</td>
							<td id="ts2">计算中……</td>
						</tr>
						<tr class="tr1class">
							<td>不纳入路径的平均住院日</td>
							<td>不纳入路径的最小住院日</td>
							<td>不纳入路径的最大住院日</td>
						</tr>
						<tr class="tr2class">
							<td id="ts3">计算中……</td>
							<td id="ts4">计算中……</td>
							<td id="ts5">计算中……</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr height="12px"></tr>

		<!-- 工作量统计 -->

		<tr>
			<td colspan="3"><table width="100%" id="Table2" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
					<tbody id="Tbody2">
						<tr>
							<td width="120" bgcolor="#FFFFFF" rowspan="5"><div align="center">
									<img src="../public/images/t31_3.png" width="64" height="64" alt="进入详细的工作量统计" /><br />工作量详细统计
								</div>
							</td>
							<td bgcolor="353c44" colspan="3" align="center"><span class="STYLE1">临床路径工作量统计</span>
							</td>
						</tr>
						<tr class="tr1class">
							<td>入院总病例数</td>
							<td>符合纳入条件病例数</td>
							<td>纳入路径病例数</td>
						</tr>
						<tr class="tr2class">
							<td id="ryzs">计算中……</td>
							<td id="fhbl">计算中……</td>
							<td id="nrbl">计算中……</td>
						</tr>
						<tr class="tr1class">
							<td>路径执行中病例</td>
							<td>完成路径病例数</td>
							<td>变异退出路径病例数</td>
						</tr>
						<tr class="tr2class">
							<td id="zxzbl">计算中……</td>
							<td id="wcbl">计算中……</td>
							<td id="bytcbl">计算中……</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr height="12px"></tr>
		<!-- 抗菌药物使用统计 -->

		<tr>

			<td colspan="3"><table width="100%" id="Table3" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
					<tbody id="Tbody3">
						<tr align="center" class="STYLE10">
							<td width="120" bgcolor="#FFFFFF" rowspan="5">
							<%-- <a href="zhikong_cp_tongji_danyi_baobiao_ksssy.jsp?cp_id=&naru=<%=0%>&bunaru=<%=0%>"> --%><img
									src="../public/images/t31_4.png" width="64" height="64" alt="进入详细的抗菌药物使用统计" /><br />抗菌药物详细统计
									<!-- </a> -->
							</td>
							<td bgcolor="353c44" colspan="2"><div align="center">
									<span class="STYLE1">临床路径抗菌药物使用统计</span>
								</div>
							</td>
						</tr>
						<tr class="tr1class">
							<td>纳入病例中平均使用抗菌药物的比率</td>
							<td>纳入病例中平均使用抗菌药物的天数</td>
						</tr>
						<tr class="tr2class">
							<td id="kjywrs">计算中……</td>
							<td>0</td>
						</tr>
						<tr class="tr1class">
							<td >不纳入路径中平均使用抗菌药物的比率</td>
							<td>不纳入路径中平均使用抗菌药物的天数</td>
						</tr>
						<tr class="tr2class">
							<td id="bsykj">计算中……</td>
							<td>0</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr height="12px"></tr>
		<!-- 卫生经济学统计 -->

		<tr>

			<td colspan="3">
				<table width="100%" id="Table4" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
					<tbody id="Tbody4">
						<tr align="center" class="STYLE10">
							<td width="120" bgcolor="#FFFFFF" rowspan="5">
							<!-- <a href="zhikong_cp_tongji_danyi_baobiao_jingjixue.jsp?cp_id="> -->
							<img src="../public/images/t31_5.png" width="64" height="64" alt="进入详细的卫生经济学统计" /><br />经济学详细统计
							<!-- </a> -->
							</td>
							<td bgcolor="353c44" colspan="3" id="cp_fee" class="STYLE1">
							</td>
						</tr>
						<tr class="tr1class">
							<td>纳入病例的平均总费用</td>
							<td>纳入病例的最大总费用</td>
							<td>纳入病例的最小总费用</td>
						</tr>
						<tr class="tr2class">
							<td id="wsjj0">计算中……</td>
							<td id="wsjj1">计算中……</td>
							<td id="wsjj2">计算中……</td>
						</tr>
						<tr class="tr1class">
							<td>不纳入路径的平均总费用</td>
							<td>不纳入路径的最大总费用</td>
							<td>不纳入路径的最小总费用</td>
						</tr>
						<tr class="tr2class">
							<td id="wsjj3">计算中……</td>
							<td id="wsjj4">计算中……</td>
							<td id="wsjj5">计算中……</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr height="12px"></tr>
		<!-- 变异统计 -->
		<tr>

			<td colspan="3"><table width="100%" id="Table5" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
					<tbody id="Tbody5">
						<tr align="center">
							<td width="120" bgcolor="#FFFFFF" rowspan="5">
							<!-- <a href="zhikong_cp_tongji_danyi_baobiao_bianyi.jsp?cp_id="> -->
							<img src="../public/images/t31_6.png" width="64" height="64" alt="进入详细的变异统计" /><br />变异详细统计
							<!-- </a> -->
							</td>
							<td bgcolor="353c44" class="STYLE6" colspan="3"><span class="STYLE1">临床路径变异统计</span>
							</td>
						</tr>
						<tr class="tr1class">
							<td>不纳入率（符合条件但不纳入的比率）</td>
							<td>路径退出率（由于变异而退出路径比率）</td>
						</tr>
						<tr class="tr2class">
							<td id="bnrl"></td>
							<td id="bytcl"></td>
						</tr>
						<tr class="tr1class">
							<td>完成路径变异率（完成路径病例中出现变异的比率）</td>
							<td>出现率最高的医嘱变异原因及出现率</td>
						</tr>
						<tr class="tr2class">
							<td>0%</td>
							<td>（0%）</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr height="12px"></tr>

	</table>

</body>
</html>