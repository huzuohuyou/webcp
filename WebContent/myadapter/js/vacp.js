var eventFlag = 1;
window.onload = function() {
	
	$(".checkOrder").click(function() {
		$("input:radio[name='identity']").eq(0).attr("checked",true);
		eventFlag=1;
		$("#mygraph").animate({
			height : "0px"
		});
		$("#mygraph").hide();
		$("#myorders").animate({
			height : "450px"
		});
		$('#orderseqs').empty();
		$("#myorders").show();
		
		var rows = document.getElementById("cps").rows;
		if (rows.length > 0) {
			for ( var i = 0; i < rows.length; i++) {
				(function(i) {
					var cp_id = rows[i].cells[0].innerText;
					var cp_name = rows[i].cells[1].innerText;
					var obj = rows[i];
					obj.onclick = getOrdersSeqs();
					//obj.onclick =getOrdersSeqs2(obj);
				})(i);
			}
		}
	});
	
	$(".forwardpdca").click(function() {
		$("input:radio[name='identity']").eq(2).attr("checked",true);
		eventFlag=0;
		$("#myorders").animate({
			height : "0px"
		});
		$("#myorders").hide();
		$("#mygraph").empty({
			height : "0px"
		});
		$("#mygraph").hide();
		$('#mygraph').empty();
		
		var rows = document.getElementById("cps").rows;
		if (rows.length > 0) {
			for ( var i = 0; i < rows.length; i++) {
				(function(i) {
					var cp_id = rows[i].cells[0].innerText;
					var cp_name = rows[i].cells[1].innerText;
					var obj = rows[i];
					obj.onclick = forwardPdca();
				})(i);
			}
		}				
	});
	
	$(".checkNode").click(function() {
		$("input:radio[name='identity']").eq(1).attr("checked",true);
		eventFlag=0;
		$("#myorders").animate({
			height : "0px"
		});
		$("#myorders").hide();
		$("#mygraph").animate({
			height : "450px"
		});
		$("#mygraph").show();
		$('#mygraph').empty();
		
		var rows = document.getElementById("cps").rows;
		if (rows.length > 0) {
			for ( var i = 0; i < rows.length; i++) {
				(function(i) {
					var cp_id = rows[i].cells[0].innerText;
					var cp_name = rows[i].cells[1].innerText;
					var obj = rows[i];
					obj.onclick = getVaNode();
				})(i)
			}
		}		
	});
	
	$.ajax({
		url : '../PdcaServlet?ran=' + Math.random(),
		data : {
			op : "1",
		    dept_name:""
		    
		},
		dataType : 'json',
		error : function() {

		},
		success : function(data) {
			for ( var i = 0; i < data["cp_va"].length; i++) {
				var tr = "<tr onclick=forwardPdca(); id=option; style='font-size:12px,font-family:Helvetica, Arial, sans-serif'>" +
							"<td style='display: none'>"+ data["cp_va"][i]["master_id"] + "</td>" +
							"<td>"+ data["cp_va"][i]["cp_id"] + "</td>" +
							"<td>"+ data["cp_va"][i]["cp_name"] + "</td>" +
							"<td style='display: none'>"+ data["cp_va"][i]["cp_code"] + "</td>" +
							"<td>"+ data["cp_va"][i]["fcount"] + "</td>" +
							"<td>"+ data["cp_va"][i]["tcount"] + "</td>" +
							"<td>"+ data["cp_va"][i]["va"] + "</td>" +
							"<td>"+ data["cp_va"][i]["dept_name"] + "</td>" +
							"<td>"+data["cp_va"][i]["cp_status"]+"</td>" +
						"</tr>";
				$('#cps').append(tr);
			}
			var tr = "<tr style='display: none'><td ></td><td></td><td ></td><td></td><td></td><td></td><td></td></tr>";
			$('#cps').append(tr);
		}
	})
}

function getInfoByFlag(){
	if(0==eventFlag){		
		getVaNode();
	}else {
		getOrdersSeqs();
	}	
}

function forwardPdca(){		
	var rows = document.getElementById("cps").rows;
	if (rows.length > 0) {
		for ( var i = 0; i < rows.length; i++) {
			(function(i) {
				var cp_id = rows[i].cells[1].innerText;				
				var obj = rows[i];
				obj.onclick = function() {
					for ( var j = 0; j < rows.length; j++) {
						if (rows[j] == this) { // 判断是不是当前选择的行
							rows[j].style.background = "#D1EEEE";
						} else {
							rows[j].style.background = "white";
						}
					}
					window.open('../cppdca/index.jsp?master_id:'+cp_id);							
				};
			})(i);
		}
	}
}


function getOrdersSeqs() {
	var rows = document.getElementById("cps").rows;
	if (rows.length > 0) {
		for ( var i = 0; i < rows.length; i++) {
			(function(i) {				
				var cp_id = rows[i].cells[1].innerText;
				var cp_name = rows[i].cells[1].innerText;
				var obj = rows[i];
				obj.onclick = function() {
					$('#orderseqs').empty();
					for ( var j = 0; j < rows.length; j++) {
						if (rows[j] == this) { // 判断是不是当前选择的行
							rows[j].style.background = "#D1EEEE";
						} else {
							rows[j].style.background = "white";
						}
					}
					$.ajax(
					{
						url : '../PdcaServlet?ran=' + Math.random(),
						data : {
							op : "8",
							cp_id : cp_id
						},
						dataType : 'json',
						error : function() {
						},
						success : function(data) {
							var nodeflag = '-';
							var parientid = 0;
							var ordergroupid =0;
							var ordergroupflag ='-';
							var ordersubgroupflag =-1;
							for ( var i = 0; i < data["cp_orders"].length; i++) {
								if (nodeflag != data["cp_orders"][i]["node_name"]) {
									parientid++;
									tr = "<tr data-tt-id='"+ parientid+ "' style='font-size:12px,font-family:Helvetica, Arial, sans-serif' ><td><span class='folder'>"+ data["cp_orders"][i]["node_name"]+ "</span></td><td>--</td><td>--</td><td>--</td></tr>";
									$('#orderseqs').append(tr);
								}										
								if (ordergroupflag != data["cp_orders"][i]["order_group"]) {
									ordergroupid++;
									tr = "<tr data-tt-id='"+ parientid+'-'+ordergroupid+ "' data-tt-parent-id='"+ parientid+ "' style='font-size:12px,font-family:Helvetica, Arial, sans-serif' ><td><span class='folder'>"+ data["cp_orders"][i]["order_group"]+ "</span></td><td>--</td><td>--</td><td>--</td></tr>";
								  //tr = "<tr data-tt-id='"+ parientid+'-'+ordergroupid+ "' data-tt-parent-id='"+parientid+"><td>123<span class='folder'>"+ data["cp_orders"][i]["order_group"]+ "</span></td><td>--</td><td>--</td></tr>";
									$('#orderseqs').append(tr);
								}
								var _subgroup ='';
								if(data["cp_orders"][i]["sub_group_id"]==ordersubgroupflag&&data["cp_orders"][i]["sub_group_id"]!=-1)
								{
									_subgroup='组'+data["cp_orders"][i]["sub_group_id"]+':';
								}else{
									_subgroup='';
								}
								tr = "<tr data-tt-id='"+ parientid+'-'+ordergroupid+ '-'+ i+ "' data-tt-parent-id='"+ parientid+'-'+ordergroupid+ "' style='font-size:12px,font-family:Helvetica, Arial, sans-serif'><td><span class='file'>"+_subgroup+ data["cp_orders"][i]["order_text"]+ "</span></td><td>"+ data["cp_orders"][i]["order_no"]+ "</td><td>"+ data["cp_orders"][i]["mycount"]+ "</td><td>"+ data["cp_orders"][i]["lv"]+ "</td></tr>";
								$('#orderseqs').append(tr);
								ordergroupflag=data["cp_orders"][i]["order_group"];
								nodeflag = data["cp_orders"][i]["node_name"];
								ordersubgroupflag=data["cp_orders"][i]["sub_group_id"];								
							}
							$("#example-advanced").treetable({
								expandable : true
							});
							// Highlight selected row
							$("#example-advanced tbody tr").mousedown(
								function() {
									$("tr.selected").removeClass(
											"selected");
									$(this).addClass("selected");
								});
						}
					});
				};
			})(i)
		}
	}

}

function getVaNode() {
	var rows = document.getElementById("cps").rows;
	if (rows.length > 0) {
		for ( var i = 0; i < rows.length; i++) {
			(function(i) {
				var cp_id = rows[i].cells[1].innerText;
				
				var cp_name = rows[i].cells[2].innerText;
				var obj = rows[i];
				obj.onclick = function() {
					//alert(cp_id);
					$("#cp_id").text(cp_id);
					for ( var j = 0; j < rows.length; j++) {
						if (rows[j] == this) { // 判断是不是当前选择的行
							rows[j].bgColor= "#D1EEEE";
							//rows[j].style.background = "#D1EEEE";
						} else {
							rows[j].bgColor= "white";
							//rows[j].style.background = "white";
						}
					}
					/* 获取节点变异 */
					$.ajax(
					{
						url : '../PdcaServlet?ran=' + Math.random(),
						data : {
							op : "0",
							cp_id : cp_id
						},
						dataType : 'json',
						error : function() {
						},
						success : function(data) {
							$("#graphTitle").text(cp_name + "变异节点分析");
							var myData = new Array();
							var myLable = new Array();
							for ( var j = 0; j < data["cp"].length; j++) {
								myData.push([ j,
										data["cp"][j]["vacount"] ]);
								myLable.push([ j,
												data["cp"][j]["node_name"],data["cp"][j]["vacount"] ]);
							}
							var myChart = new JSChart('mygraph', 'line');
							myChart.patchMbString();
							myChart.setFontFamily("微软雅黑");
							myChart.setTitle("");
							myChart.setTitleFontSize(20);
							myChart.setAxisValuesFontSize(12);
							myChart.setTitleColor("#000000");
							myChart.setDataArray(myData);
							for ( var m = 0; m < myLable.length; m++) {
								myChart.setLabelX([
										myLable[m][0],
										myLable[m][1] ]);
								myChart
										.setTooltip([
												myLable[m][0],
												'count:'
														+ myLable[m][2] ]);
							}
							myChart.setAxisPaddingBottom(50);// 设置x轴和容器底部的距离，默认30。
							myChart.setAxisPaddingLeft(60);// 设置y轴和容器左边距的距离，默认40。
							myChart.setAxisPaddingRight(50);// 设置图表右边和容器的距离，默认30。
							myChart.setAxisPaddingTop(50);
							myChart.setIntervalStartY(0);
							myChart.setIntervalEndY(data['endy']);
							myChart
									.setAxisValuesNumberX(data['numberx']);
							myChart.setGraphExtend(true);
							myChart.setLineWidth(2);
							myChart.setAxisNameX('');
							myChart.setAxisNameY('');
							myChart.setShowXValues(false);
							myChart.setFlagColor('#8B0000');
							myChart.setLineColor('#A52A2A');
							myChart.setGridColor('#c2c2c2');
							myChart.setAxisColor('#C4C4C4');
							myChart.setAxisValuesColor('#343434');
							myChart
									.setSize(
											120 * data['numberx'] > 600 ? 120 * data['numberx']
													: 600, 321);
							myChart.draw();
							myChart
									.setBackgroundImage('pic/chart_bg.jpg');
						}
					});
					/*
					 * 获取节点下医嘱 $.ajax({ url :
					 * '../PdcaServlet?ran='+Math.random(), data : { op :"4",
					 * cp_id:cp_id }, dataType : 'json', error : function() { },
					 * success : function(data) { var nodeflag = '-'; var
					 * parientid =0; $('#orderseqs').empty(); for(var i=0;i<data["cp_orders"].length;i++) {
					 * if(nodeflag!=data["cp_orders"][i]["node_name"]){
					 * parientid++; tr="<tr data-tt-id='"+parientid+"'><td><span
					 * class='folder'>"+data["cp_orders"][i]["node_name"]+"</span></td><td>--</td><td>--</td></tr>"
					 * $('#orderseqs').append(tr); } tr="<tr data-tt-id='"+parientid+'-'+i+"' data-tt-parent-id='"+parientid+"'><td><span
					 * class='file'>"+data["cp_orders"][i]["order_text"]+"</span></td><td>"+data["cp_orders"][i]["order_no"]+"</td><td>"+data["cp_orders"][i]["mycount"]+"</td></tr>"
					 * nodeflag=data["cp_orders"][i]["node_name"];
					 * $('#orderseqs').append(tr); }
					 * $("#example-advanced").treetable({ expandable: true }); //
					 * Highlight selected row $("#example-advanced tbody
					 * tr").mousedown(function() {
					 * $("tr.selected").removeClass("selected");
					 * $(this).addClass("selected"); }); } });
					 */

				};
			})(i)
		}
	}
}

//显示进度条
var showProgress = function() {
	$.messager.progress({
		title : '<span style="color: black; ">请稍等</span>',
		msg : '正在加载数据...',
		interval : 250
	});
};
