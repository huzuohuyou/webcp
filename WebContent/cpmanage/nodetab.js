$(function() {
	// 添加输出节点弹窗
	$("#dialogAddOutNode")
			.dialog(
					{
						autoOpen : false,
						height : 430,
						width : 430,
						modal : true,
						title : '添加输出节点',
						buttons : {
							"添加" : function() {
								$
										.ajax({
											url : "../servlet/managecp",
											type : 'POST',
											data : {
												op : "addTwoCol",
												tableName : "lcp_node_relate",
												cp_id : cpID,
												cp_node_id : cpNodeID,
												datas : encodeURI(outNodeID,
														"utf-8")
											},
											dataType : "json",
											complete : show_result,
											success : function(data,
													textStatus, XMLHttpRequest) {
												data = eval(data);
												data = data[0];
												var toHtml = "<tr bgcolor='#FFFFFF' onMouseOver='changeColor(this)' onMouseOut='recoverColor(this)' class='STYLE10'"
														+ "name='lcp_node_relate_tr' id='tr"
														+ outNodeID
														+ "'>"
														+ "<td align='center'><input type='checkbox' name='lcp_node_relate_checkbox' id='tr"
														+ outNodeID
														+ "' /></td>"
														+ "<td align='center'>"
														+ outNodeText
														+ "</td></tr>";
												if (data.result === "OK") {
													if (data.flag == 1) {
														$(
																"#lcp_node_relate_tbody")
																.append(toHtml);
													} else {
														alert("添加数据失败!");
													}
												} else {
													alert("无效操作");
												}
											}
										});
								$(this).dialog("close");
							},
							"取消" : function() {
								$(this).dialog("close");
							}
						}
					});

	/** ***********************************所有弹出窗口初始化*************************************************** */

	$("#order_itemb").dialog({
		autoOpen : false,
		height : 450,
		width : 350,
		modal : true
	});
	$("#dialogOrderPoint").dialog({
		autoOpen : false,
		height : 350,
		width : 400,
		modal : true
	});
	$("#dialogOrderPointe").dialog({
		autoOpen : false,
		height : 350,
		width : 400,
		modal : true
	});

	$("#dialogDoctor_point").dialog({
		autoOpen : false,
		height : 300,
		width : 350,
		modal : true
	});
	$("#dialogDoctor_item").dialog({
		autoOpen : false,
		height : 430,
		width : 370,
		modal : true
	});
	$("#dialogNurse_point").dialog({
		autoOpen : false,
		height : 300,
		width : 350,
		modal : true
	});
	$("#dialogNurse_item").dialog({
		autoOpen : false,
		height : 430,
		width : 370,
		modal : true
	});
	$("#order_item").dialog({
		autoOpen : false,
		height : 450,
		width : 350,
		modal : true
	});

	$("#dialogFamily").dialog({
		autoOpen : false,
		height : 350,
		width : 400,
		modal : true
	});

	$("#dialogNodeSan").dialog({
		title : "双击选中的二级菜单",
		autoOpen : false,
		height : 300,
		width : 400,
		modal : true
	});

	$("#dialogNode").dialog({
		title : "选中节点双击复制二级菜单",
		autoOpen : false,
		height : 300,
		width : 400,
		modal : true
	});

	$("#orderjldw").load("../servlet/managecp?op=Option&ops=orderjldw");
	$("#orderPointName").load(
			"../servlet/managecp?op=Option&ops=orderPointName");
	// $("#yanneiOrderSelect").load("../servlet/managecp?op=Option&ops=yanneiOrderType");
	var opertion = {
		delay : 400,
		max : 80, // 列表里的条目数
		minChars : 1, // 自动完成激活之前填入的最小字符
		width : 400, // 提示的宽度，溢出隐藏
		scrollHeight : 300, // 提示的高度，溢出显示滚动条
		matchContains : true, // 包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
		autoFill : false, // 自动填充
		parse : function(data) {
			data = eval(data);
			var rows = [];
			for ( var i = 0; i < data.length; i++) {
				rows[rows.length] = {
					// data:data[i].code+"--"+data[i].name
					// +"--"+data[i].spec+"--"+data[i].dosunits+"--"+data[i].input,
					data : data[i].code + "--" + data[i].name + "--"
							+ data[i].spec + "--"  +data[i].charge_amount + "--" + data[i].input,
					// value:data[i].code+"--"+data[i].name+"--"+data[i].spec+"--"+data[i].dosunits,
					value : data[i].code + "--" + data[i].name + "--"
							+ data[i].spec + "--"  +data[i].charge_amount,
					result : data[i].input
				};
			}
			return rows;
		},
		formatItem : function(row, i, n) {
			return row;
		}
	};

	/*
	 * //按编码搜索
	 * $("#orderCode").autocomplete("../servlet/auto?ops=bm&op=codeBM",opertion);
	 * $("#orderCode").result(function(event, data, formatted){
	 * formatted=formatted.split("--");
	 * $("#orderCode").attr("value",formatted[0]);
	 * $("#orderName").attr("value",formatted[1]);
	 * $("#specification").attr("value",formatted[2]);
	 * //$("#dosage_units").attr("value",formatted[3]); });
	 */
	// 按拼音搜索
	$("#orderPY").autocomplete("../servlet/auto?ops=cd&op=codePY", opertion);

	$("#orderPY").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#orderCode").attr("value", formatted[0]);
		$("#orderName").attr("value", formatted[1]);
		$("#specification").attr("value", formatted[2]);
		$("#charge_amount").attr("value",formatted[3]);
		$("#orderPY").attr("value", formatted[4]);
		fsdata();
		$("#orderjl").val("");

	});
	// 完全模糊查询
	$("#orderPY2").autocomplete("../servlet/auto?ops=cd&op=codePY&option=dim",
			opertion);

	$("#orderPY2").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#orderCode").attr("value", formatted[0]);
		$("#orderName").attr("value", formatted[1]);
		$("#specification").attr("value", formatted[2]);
		 $("#charge_amount").attr("value",formatted[3]);
		$("#orderPY2").attr("value", formatted[4]);
		fsdata();
		$("#orderjl").val("");

	});
	// 按拼音搜索
	$("#orderPYb").autocomplete("../servlet/auto?ops=cd&op=codePY", opertion);
	$("#orderPYb").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#orderCodeb").attr("value", formatted[0]);
		$("#orderNameb").attr("value", formatted[1]);
		$("#specificationb").attr("value", formatted[2]);
		$("#charge_amount").attr("value",formatted[3]);
		$("#orderPYb").attr("value", formatted[4]);

	});
	// 完全模糊查询
	$("#orderPYb2").autocomplete("../servlet/auto?ops=cd&op=codePY&option=dim",
			opertion);
	$("#orderPYb2").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#orderCodeb").attr("value", formatted[0]);
		$("#orderNameb").attr("value", formatted[1]);
		$("#specificationb").attr("value", formatted[2]);
		$("#charge_amountb").attr("value",formatted[3]);
		$("#orderPYb2").attr("value", formatted[4]);
	});

	/** ***********************************所有自动填表内容初始化*************************************************** */
	var opertion = {
		delay : 1000,
		max : 12, // 列表里的条目数
		minChars : 0, // 自动完成激活之前填入的最小字符
		width : 400, // 提示的宽度，溢出隐藏
		scrollHeight : 300, // 提示的高度，溢出显示滚动条
		matchContains : true, // 包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
		autoFill : false, // 自动填充
		cacheLength : 1,
		parse : function(data) {
			data = eval(data);
			var rows = [];
			for ( var i = 0; i < data.length; i++) {
				rows[rows.length] = {
					data : data[i].code + "--" + data[i].name + "--"
							+ data[i].input,
					value : data[i].code + "--" + data[i].name,
					result : data[i].input
				};
			}
			return rows;
		},
		formatItem : function(row, i, n) {
			return row;
		}
	};
	$("#nursePY").autocomplete("../servlet/auto?ops=py&op=nurse", opertion);
	$("#nurseWB").autocomplete("../servlet/auto?ops=wb&op=nurse", opertion);
	$("[name='nurseInput']").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#nurseCode").attr("value", formatted[0]);
		$("#nurseName").attr("value", formatted[1]);
	});

	$("#nursePY1").autocomplete("../servlet/auto?ops=py&op=nurse", opertion);
	$("#nurseWB1").autocomplete("../servlet/auto?ops=wb&op=nurse", opertion);
	$("[name='nurseInput1']").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#nurseCode1").attr("value", formatted[0]);
		$("#nurseName1").attr("value", formatted[1]);
	});

	$("#doctorPY").autocomplete("../servlet/auto?ops=py&op=doctor", opertion);
	$("#doctorWB").autocomplete("../servlet/auto?ops=wb&op=doctor", opertion);
	$("[name='doctorInput']").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#doctorCode").attr("value", formatted[0]);
		$("#doctorName").attr("value", formatted[1]);
	});
	$("#doctorPY1").autocomplete("../servlet/auto?ops=py&op=doctor", opertion);
	$("#doctorWB1").autocomplete("../servlet/auto?ops=wb&op=doctor", opertion);
	$("[name='doctorInput1']").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#doctorCode1").attr("value", formatted[0]);
		$("#doctorName1").attr("value", formatted[1]);
	});
	$("#orderPC").autocomplete("../servlet/auto?ops=orderPC", opertion);
	$("#orderPC").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#orderPC").attr("value", formatted[0]);
	});

	$("#ordertj").autocomplete("../servlet/auto?ops=orderWay", opertion);
	$("#ordertj").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#ordertj").attr("value", formatted[0]);
	});
	// bianjiyemian
	$("#orderPCb").autocomplete("../servlet/auto?ops=orderPC", opertion);
	$("#orderPCb").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#orderPCb").attr("value", formatted[0]);
	});

	$("#ordertjb").autocomplete("../servlet/auto?ops=orderWay", opertion);
	$("#ordertjb").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#ordertjb").attr("value", formatted[0]);
	});
	// $("#orderPY").autocomplete("../servlet/auto?ops=py&op=order",opertion);
	// $("#orderWB").autocomplete("../servlet/auto?ops=wb&op=order",opertion);

	$("[name='orderInput']").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#orderCode").attr("value", formatted[0]);
		$("#orderName").attr("value", formatted[1]);
	});
	$("#dialog_cp_py").autocomplete("../servlet/auto?ops=py&op=lcpmaster",
			opertion);
	$("#dialog_cp_wb").autocomplete("../servlet/auto?ops=wb&op=lcpmaster",
			opertion);
	$("[name='masterInput']").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#dialog_cp_id").attr("value", formatted[0]);
		$("#dialog_cp_name").attr("value", formatted[1]);
	});
	$("#orderjldw").autocomplete("../servlet/auto?ops=jl&op=selectUnit",
			opertion);
	$("#orderjldw").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#orderjldw").attr("value", formatted[1]);
		$("#orderCode2").attr("value", formatted[0]);
	});
	$("#dosage_units").autocomplete("../servlet/auto?ops=jl&op=selectUnit",
			opertion);
	$("#dosage_units").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#dosage_units").attr("value", formatted[1]);
		$("#orderCode1").attr("value", formatted[0]);
	});
	// bianji
	$("#orderjldwb").autocomplete("../servlet/auto?ops=jl&op=selectUnit",
			opertion);
	$("#orderjldwb").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#orderjldwb").attr("value", formatted[1]);
		$("#orderCode2b").attr("value", formatted[0]);
	});
	$("#dosage_unitsb").autocomplete("../servlet/auto?ops=jl&op=selectUnit",
			opertion);
	$("#dosage_unitsb").result(function(event, data, formatted) {
		formatted = formatted.split("--");
		$("#dosage_unitsb").attr("value", formatted[1]);
		$("#orderCode1b").attr("value", formatted[0]);
	});

});
function addOutNode() {
	var htmls = "";
	var id = 0;
	$("#lcp_master_node_tbody")
			.children()
			.each(
					function() {
						id = $(this).attr("id");
						var html = $(this).html();
						htmls = htmls
								+ "<tr onmouseover='changeColor(this)' onmouseout='recoverColor(this)' onclick='outNodeColor(this)' id='"
								+ id
								+ "' class='selectOutNode' style='cursor:pointer' bgcolor='#FFFFFF' height='20'>"
								+ html + "</tr>";
					});
	$("#dialogAddOutNodeTbody").empty();
	$("#dialogAddOutNodeTbody").append(htmls);// 填充添加输出节点对话框
	$("#dialogAddOutNode").dialog('open');
}

/**
 * @param id
 *            待开发^ 判断输出节点是否已经存在
 */
function isHaving(id) {
	var temp = 0;
	$("#lcp_node_relate_tbody").children().each(function() {
		var relateID = $(this).attr("id").replace("tr", "");
		temp = relateID;
		alert(temp === id);
		return temp === id;
	});

}

function delOrderPoint(type) {
	var tmpID = "";
	if (type == 1) {
		tmpID = cp_node_table_id_quanju;
	} else if (type == 2) {
		tmpID = cpOrderID2;
		if (!onclick2point_quanju) {
			alert("请选择要删除的第二级菜单");
			return;
		}
	}
	if (confirm("您确定要删除吗?")) {
		$.ajax({
			url : "../servlet/managecp",
			type : 'POST',
			data : {
				op : "del_order_point",
				cp_id : cpID,
				type : type,
				cp_node_id : cp_node_id_quanju,
				cpOrder2Point : cp_node_table_id_quanju,
				cp_node_table_id : tmpID
			},
			dataType : "json",
			complete : show_result,
			success : function(data, textStatus, XMLHttpRequest) {
				data = eval(data);
				data = data[0];
				onclick2point_quanju = false;
				if (data.result === "OK") {
					$("#order_item_table").html("");
					$("#order_point_table").html(data.table);
					alert("删除成功");
				} else {
					alert("删除失败");
				}

			}
		});
	}
}

function clearOrderPoint() {
	if (confirm("您确定要清空该节点下的所有二级菜单吗?")) {
		$.ajax({
			url : "../servlet/managecp",
			type : 'POST',
			data : {
				op : "clearOrderPoint",
				cp_id : cpID,
				cp_node_id : cp_node_id_quanju
			},
			dataType : "json",
			complete : show_result,
			success : function(data, textStatus, XMLHttpRequest) {
				data = eval(data);
				data = data[0];
				if (data.result === "OK") {
					$("#order_item_table").html("");
					$("#order_point_table").html(data.table);
					alert("清空二级菜单成功！");
				} else {
					alert("清空二级菜单失败");
				}

			}
		});
	}
}

/**
 * 添加医嘱point信息
 */
function addOrderPoint(type) {
	if (type == 2) {// 添加第二层
		if (!onclick1point_quanju) {
			// $("#type1").css("display","none");
			alert("请选择第一级菜单后再进行添加");
			return;
		}
	}
	$("#dialogOrderPoint").dialog("open");
	if (type == 1) {
		$("#orderPointName").val("");
		$("#type2").css("display", "none");
		$("#typeFlag").css("display", "none");
		$("#orderPointTypeSelect").attr("value", '');

	} else {
		$("#orderPointName").val("");
		$("#type1").css("display", "none");
		$("#typeFlag").css("display", "");
		$("#orderPointTypeSelect").attr("value", '0');
	}

	$("#dialogOrderPoint").dialog(
			{
				open : function() {
					$("#orderPointName").attr("value", 'qxz');
					$("#orderPointName1").val("");
					// $("#orderPointTypeSelect").attr("value",'');
				},
				buttons : {
					"添加" : function() {
						var need = $(":radio[name='orderPointNeed'][checked]")
								.val();
						var types = $("#orderPointTypeSelect").val();
						if (type == 1) {
							var orderPointName = $("#orderPointName").find(
									"option:selected").text();
							var orderPointCode = $("#orderPointName").val();
						} else {
							var orderPointName = $("#orderPointName1").val();
							if (orderPointName.length == 0) {
								alert("自定义菜单不能为空！");
								return;
							}
						}
						if (orderPointCode == "qxz") {
							alert("院内医嘱类别  为 必选项,请选择!" + orderPointName);
						} else {
							$.ajax({
								url : "../servlet/managecp",
								type : 'POST',
								data : {
									op : "addOrderPoint",
									cp_id : cpID,
									cp_node_id : cpNodeID,
									cp_order_id : cp_node_table_id_quanju,
									type : type,
									types : types,
									need : need,
									orderPointName : encodeURIComponent(
											orderPointName, "utf-8"),
									orderPointCode : orderPointCode
								},
								dataType : "json",
								complete : show_result,
								success : function(data, textStatus,
										XMLHttpRequest) {
									data = eval(data);
									data = data[0];
									if (data.result === "OK") {
										$("#dialogOrderPoint").dialog("close");
										$("#order_item_table").html("");
										$("#order_point_table")
												.html(data.table);
									} else if (data.result === "unique") {
										alert("该一级菜单已经添加！");
									} else {
										alert("添加失败");
									}
								}
							});
						}
					},
					"取消" : function() {
						$(this).dialog("close");
					}
				},
				close : function() {
					if (type == 1) {
						$("#type2").css("display", "");
					} else {
						$("#type1").css("display", "");
					}

				}
			});
	onclick2point_quanju = false;
}

/**
 * 添加医嘱Item信息
 */
function addOrderItem() {
	var trcontents;
	var cpIDs;
	var cpNodeIDs;
	var cpOrderIDs;
	var cpOrderItemIDs;
	var groupIds;
	var types;
	// var name;
	var specifications;
	var needs;
	var isDefaults;
	var orderjls;
	var orderPCs;
	var SUPPLYNAMEs;
	$("#order1").css("display", "");
	$("#order2").css("display", "none");
	if (!onclick2point_quanju) {
		alert("请选择主表中第二级菜单内容后再进行添加");
		return;
	}
	$("#doctorMark").keydown(function(event) {
		var keycode = event.which;
		var markLength = $("#doctorMark").val().length;
		if (markLength >= 50) {
			var num = $("#doctorMark").val().substr(0, 50);
			$("#doctorMark").val(num);
			if (keycode != 8) {
				alert("超出字符限制，多余部分将被截去！");
				return false;
			}
		}
	});

	$("#order_item")
			.dialog(
					{
						open : function() {
							$("#orderCode").val("");
							// $("#orderCode")[0].focus();
							$("#orderName").val("");
							$("#orderPY").val("");
							$("#orderWB").val("");
							$("#ordertj").val("");
							$("#orderjl").val("");// 领量
							$("#orderjldw").val("");// 领量单位
							$("#dosage").val("");// 使用剂量
							$("#dosage_units").val("");// 使用剂量单位
							$("#specification").val("");
							$("#charge_amount").val("");//单价
							$("#radiobutton").attr("value", '0');
							$("#radiobutton1").attr("value", '0');
							$("#orderSelect").attr("value", '0');
							$("#doctorMark").val("");
						},
						buttons : {
							"添加" : function() {

								// var
								// yanneiOrderType=$("#yanneiOrderSelect").val();
								// if(yanneiOrderType=="qxz"){
								// alert("院内医嘱类别 为 必选项,请选择!"+yanneiOrderType);
								// }else{

								var need = $(":radio[name='orderItem'][checked]").val();
								var itemDefault = $(":radio[name='orderItemDefault'][checked]").val();//是否默认医嘱
								var code = $("#orderCode").val();
								var type = $("#orderSelect").val();
								// var belong=$("#orderBelongSelect").val();
								var name = $("#orderName").val();
								var specification = $("#specification").val();
								var charge_amount = $("#charge_amount").val();
								var orderjl = $("#orderjl").val();
								var orderCode2 = $("#orderCode2").val();
								var dosage = $("#dosage").val();
								var orderCode1 = $("#orderCode1").val();
								// var
								// orderTypeSelect=$("#orderTypeSelect").val();
								var orderPC = $("#orderPC").val();
								var ordertj = $("#ordertj").val();
								var orderpcdw = $("#orderpcdw").val();
								var mark = $("#doctorMark").val();

								if (name == "") {
									alert("医嘱内容不能为空！");
									return false;
								}
								var reg = new RegExp("[0-9]+");
								// 校验
								if (code == "") {
									alert("医嘱编码为空!");
									$("#order_itemb").dialog("close");
									return;
								}
								// 领量校验
								if (orderjl == "") {
									/*
									 * if(orderCode2==""){ } else
									 * if(orderCode2!=""){ alert("请先输入领量!");
									 * return; }
									 */
								} else if (orderjl != "") {
									if (reg.test(orderjl)) {
										if (orderCode2 == "") {
											/*
											 * alert("请输入领量单位!"); return;
											 */
										} else if (orderCode2 != "") {

										}
									} else {
										alert("请输入正确格式领量!");
										return;
									}
								}
								// 一次计量校验
								if (dosage == "") {
									/*
									 * if(orderCode1==""){ } else
									 * if(orderCode1!=""){ alert("请先输入一次计量!");
									 * return; }
									 */
								} else if (dosage != "") {
									if (reg.test(dosage)) {
										if (orderCode1 == "") {
											alert("请输入一次剂量单位!");
											return;
										} else if (orderCode1 != "") {
										}
									} else {
										alert("请输入正确格式一次剂量!");
										return;
									}
								}
								// 途径校验

								if (!reg.test(ordertj) && ordertj != "") {
									alert("请输入正确途径!");
									return;
								}

								$
										.ajax({
											url : "../servlet/managecp",
											type : 'POST',
											data : {
												op : "addOrderItem",
												cp_id : cpID,
												cp_node_id : cpNodeID,
												cp_node_order_id : cpOrderID2,
												need : need,// 必做项
												isDefault: itemDefault,//是否默认
												code : code,// 医嘱编码
												type : type,// 长期-临时-出院-长+临
												name : encodeURIComponent(name,
														"utf-8"),// encodeURI(name,"utf-8"),//医嘱名称
												specification : encodeURIComponent(
														specification, "utf-8"),// 规格
												orderjl : orderjl,// 医嘱计量
												orderCode2 : orderCode2,// 计量单位
												dosage : dosage,// 一次使用剂量
												orderCode1 : orderCode1,// 一次使用剂量单位
												orderPC : orderPC,// 频次
												ordertj : ordertj,// 途径
												// yanneiOrderType:yanneiOrderType,//院内医嘱类别
												orderpcdw : encodeURI(
														orderpcdw, "utf-8"),
												mark : encodeURI(mark, "utf-8")
											},
											/*
											 * dataType : "json", complete
											 * :show_result ,
											 * 修改返回方式----------不返回table只追加一行
											 */
											success : function(msg) {
												msg = eval('(' + msg + ')');
												if (msg["result"] == "OK") {
													cpIDs = msg["cpID"];
													cpNodeIDs = msg["cpNodeID"];
													cpOrderIDs = msg["cpOrderID"];
													cpOrderItemIDs = msg["cpOrderItemID"];
													groupIds = msg["groupId"];
													types = msg["type"];
													ORDER_TYPE_NAME=msg["ORDER_TYPE_NAME"];//-------------->医嘱类型名字
													names = msg["name"];
													specifications = msg["specification"];
													needs = msg["need"];
													isDefaults = msg["isDefault"];
													orderjls = msg["orderjl"];
													orderPCs = msg["orderPC"];
													SUPPLYNAMEs = msg["SUPPLYNAME"];
													$("#order_item").dialog(
															"close");
													// $("#order_item_table").html(data.table);
													var effectFlag = msg["effectFlag"];
													if (effectFlag === "")
														effectFlag = "0";
													if (effectFlag === "1") {
														trcontents = "<tr class='STYLE10' height='20' name='mytb'  id='"
																+ cpIDs
																+ "_"
																+ cpNodeIDs
																+ "_"
																+ cpOrderIDs
																+ "_"
																+ cpOrderItemIDs
																+ "' bgcolor='#FFFFFF'onmouseover='changeColor(this)'  onMouseOut='recoverColor(this)' onclick='lineclick(this);'>"
																+ "<td  align='center' ><input type='checkbox' name='chekcbox_orderitem' id='"
																+ cpIDs
																+ "_"
																+ cpNodeIDs
																+ "_"
																+ cpOrderIDs
																+ "_"
																+ cpOrderItemIDs
																+ "'></td>"
																+
																// "<td
																// align='center'
																// >"+cp_node_order_item_id+"</td>"+//序号
																"<td  align='center'>"
																+ cpOrderItemIDs
																+ "</td>"
																+ // 序号
																"<td  align='center' >"
																+ groupIds
																+ "</td>"
																+ // 组
																"<td  align='center' >"
																+ types
																+ "</td>"
																+ // 类别
																// 长期-临时-出院
																"<td align='center'>"+ORDER_TYPE_NAME+"</td>"+//
																"<td  align='left' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
																+ names
																+ "<span style='color: #FF0000'>(不可用)</span></td>"
																+ // 医嘱内容
																"<td  align='center' >"
																+ specifications
																+ "</td>"
																+ // 规格
																"<td  align='center' >"
																+ needs
																+ "</td>"
																+ // 必做
																"<td  align='center' >"
																+ isDefaults
																+ "</td>"
																+ // 默认
																"<td  align='center' >"
																+ orderjls
																+ "</td>"
																+ // 计量
																"<td  align='center' >"
																+ orderPCs
																+ "</td>"
																+ // 频次
																// "<td
																// align='center'
																// >"+WAY+"</td>"+//途径
																"<td  align='center' >"
																+ SUPPLYNAMEs
																+ "</td>" + // 途径
																"</tr>";
														$(trcontents)
																.appendTo(
																		"#order_item_table");

													} else {
														trcontents = "<tr  class='STYLE10' height='20' name='mytb'  id='"
																+ cpIDs
																+ "_"
																+ cpNodeIDs
																+ "_"
																+ cpOrderIDs
																+ "_"
																+ cpOrderItemIDs
																+ "' bgcolor='#FFFFFF'onmouseover='changeColor(this)'  onMouseOut='recoverColor(this)' onclick='lineclick(this);'>"
																+ "<td  align='center' ><input type='checkbox' name='chekcbox_orderitem' id='"
																+ cpIDs
																+ "_"
																+ cpNodeIDs
																+ "_"
																+ cpOrderIDs
																+ "_"
																+ cpOrderItemIDs
																+ "'></td>"
																+
																// "<td
																// align='center'
																// >"+cp_node_order_item_id+"</td>"+//序号
																"<td  align='center'>"
																+ cpOrderItemIDs
																+ "</td>"
																+ // 序号
																"<td  align='center' >"
																+ groupIds
																+ "</td>"
																+ // 组
																"<td  align='center' >"
																+ types
																+ "</td>"
																+ // 类别
																// 长期-临时-出院
															    "<td align='center'>"+ORDER_TYPE_NAME+"</td>"+//
																"<td  align='left' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
																+ names
																+ "</td>"
																+ // 医嘱内弄
																"<td  align='center' >"
																+ specifications
																+ "</td>"
																+ // 规格
																"<td  align='center' >"
																+ needs
																+ "</td>"
																+ // 必做
																"<td  align='center' >"
																+ isDefaults
																+ "</td>"
																+ // 默认
																"<td  align='center' >"
																+ orderjls
																+ "</td>"
																+ // 计量
																"<td  align='center' >"
																+ orderPCs
																+ "</td>"
																+ // 频次
																// "<td
																// align='center'
																// >"+WAY+"</td>"+//途径
																"<td  align='center' >"
																+ SUPPLYNAMEs
																+ "</td>" + // 途径
																"</tr>";
														$(trcontents)
																.appendTo(
																		"#order_item_table");
													}

												} else {
													alert("添加失败");
												}

											}
										});
							},

							"取消" : function() {
								$(this).dialog("close");
							}
						}
					});
	$("#order_item").dialog("open");
}

/**
 * 显示doctoritem内容 刘植鑫 2011-08-10
 */
function shownurseitem(cp_id, cpNodeID, cp_node_doctor_id, event) {
	cp_node_table_id_quanju = cp_node_doctor_id;
	onclickdoctorpoint_quanju = true;
	cp_node_id_quanju = cpNodeID;
	$.ajax({
		url : "../servlet/managecp",
		type : 'POST',
		data : {
			op : "show_nurse_item",
			cp_id : cp_id,
			cp_node_id : cpNodeID,
			cp_node_table_id : cp_node_doctor_id
		},
		dataType : "json",
		complete : show_result,
		success : function(data, textStatus, XMLHttpRequest) {
			data = eval(data);
			data = data[0];
			$("#nurse_item_table").html(data.table);
		}
	});
	var tr$ = $(event);
	tr$.css("background-color", "#51b2f6");
	tr$.siblings().css("background-color", "#ffffff");
	tempColor = "#51b2f6";

	// 2.护理工作编辑

	$(event).each(function(d) {
		$(this).click(function() {
			valuehl = $(this).text();
			// flag=$(this).css('background-color');
			/* alert(valuehl); */
		});

	});
}
/** ******************-----------------------***************************** */
// 编辑诊疗和护理工作<---------------------调用窗口 增加列数为3的通用方法----------------------->
function editDialogThreeCol(tbodyName) {
	// 诊疗
	var valtext = '';
	var first = '';
	var second = '';
	var val = value.split("");
	/* alert(val); */
	var leng = val.length;
	if (val[leng - 1] == "√") {
		for ( var i = 7; i < leng - 1; i++) {
			valtext += val[i];
		}
		first = val[0];
		second = val[leng - 1];
		/*
		 * alert(first); alert(valtext); alert(second);
		 */
	} else {
		for ( var i = 7; i < leng; i++) {
			valtext += val[i];
		}
		first = val[0];
		/*
		 * alert(first); alert(valtext);
		 */
	}
	// 护理
	var valtexthl = '';
	var firsthl = '';
	var secondhl = '';
	var valhl = valuehl.split("");
	/* alert(val); */
	var lenghl = valhl.length;
	if (valhl[lenghl - 1] == "√") {
		for ( var i = 7; i < lenghl - 1; i++) {
			valtexthl += valhl[i];
		}
		firsthl = valhl[0];
		secondhl = valhl[lenghl - 1];
		/*
		 * alert(first); alert(valtext); alert(second);
		 */
	} else {
		for ( var i = 7; i < lenghl; i++) {
			valtexthl += valhl[i];
		}
		firsthl = valhl[0];
		/*
		 * alert(first); alert(valtext);
		 */
	}
	// alert(cpID+"---"+cp_node_id_quanju+"---"+first);nurse_point_table
	// doctor_point_table
	var tableName = tbodyName.replace("_tbody", "");
	if (tableName == "lcp_node_doctor_point") {
		trArr = [];
		$("#doctor_point_table").find("tr").each(function() {
			if ($(this).attr("bgcolor") == "#51b2f6")
				trArr.push($(this));
		});
		if (trArr.length > 0) {
			$("#dialogDoctor_point").dialog("open");
			$("#doctorName").val(valtext);
			var checked = document.getElementsByName("doctor");// 是否必做
			if ("√" == second) {
				checked[1].checked = true;
			} else {
				checked[0].checked = true;
			}

			$("#dialogDoctor_point")
					.dialog(
							{
								open : function() {
									$("#doctorCode").val("");
									$("#doctorName").attr("value", "");

								},
								buttons : {
									"修改" : function() {
										var need = $(
												":radio[name='doctor'][checked]")
												.val();// 0可选 1 必选
										// var code="";
										var name = $("#doctorName").val();
										$
												.ajax({
													type : "POST",
													url : "../servlet/managecp",
													data : {
														op : "editzl",
														cpID : cpID,
														cp_node_id_quanju : cp_node_id_quanju,
														first : first,
														name : encodeURIComponent(
																name, "utf-8"),
														need : need
													},
													// "need="+need+"&cpID="+cpID+"&cp_node_id_quanju="+cp_node_id_quanju+"&first="+first+"&name="+name+"&op=editzl",
													success : function(msg) {
														msg = eval('(' + msg
																+ ')');
														var need = msg["need"];
														var name = msg["name"];
														if (need == "1") {
															need = "√";
														} else {
															need = "";
														}
														var tohtml = "<td  align='center' class='STYLE10'>"
																+ first
																+ "</td>"
																+ "<td  align='left' class='STYLE10'><span  id='2_1_"
																+ cpID
																+ "_"
																+ cp_node_id_quanju
																+ "_"
																+ first
																+ "'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
																+ name
																+ "</span></td>"
																+ "<td  align='center' class='STYLE10'>"
																+ need
																+ "</td>";
														// $("tr
														// [id='"+arry[0]+"_"+arry[1]+"_"+arry[2]+"_"+arry[3]+"']").html(toHtml);
														$("#dialogDoctor_point")
																.dialog("close");
														$(
																"tr [id='"
																		+ cpID
																		+ "_"
																		+ cp_node_id_quanju
																		+ "_"
																		+ first
																		+ "']")
																.html(tohtml);
													}
												});

									},
									"取消" : function() {
										$(this).dialog("close");
									}
								}
							});

		} else {
			alert("请选择一条编辑!");
		}
	} else if (tableName == "lcp_node_nurse_point") {
		trArr1 = [];
		$("#nurse_point_table").find("tr").each(function() {
			if ($(this).attr("bgcolor") == "#51b2f6")
				trArr1.push($(this));
		});
		if (trArr1.length > 0) {
			$("#dialogNurse_point").dialog("open");
			$("#nurseName").val(valtexthl);
			var checked = document.getElementsByName("nurse");// 是否必做
			if ("√" == secondhl) {
				checked[1].checked = true;
			} else {
				checked[0].checked = true;
			}

			$("#dialogNurse_point")
					.dialog(
							{
								open : function() {
									$("#nurseCode").attr("value", "");
									$("#nurseName").attr("value", "");
								},
								buttons : {
									"修改" : function() {
										var need = $(
												":radio[name='nurse'][checked]")
												.val();
										// var code="";
										var name = $("#nurseName").val();
										// alert(need+"----"+name);
										$
												.ajax({
													type : "POST",
													url : "../servlet/managecp",
													data : {
														op : "edithl",
														cpID : cpID,
														cp_node_id_quanju : cp_node_id_quanju,
														firsthl : firsthl,
														name : encodeURIComponent(
																name, "utf-8"),
														need : need
													},
													// "need="+need+"&cpID="+cpID+"&cp_node_id_quanju="+cp_node_id_quanju+"&first="+first+"&name="+name+"&op=editzl",
													success : function(msg) {
														msg = eval('(' + msg
																+ ')');
														var need = msg["need"];
														var name = msg["name"];
														if (need == "1") {
															need = "√";
														} else {
															need = "";
														}
														var tohtml = "<td  align='center' class='STYLE10'>"
																+ firsthl
																+ "</td>"
																+ "<td  align='left' class='STYLE10'><span  id='2_1_"
																+ cpID
																+ "_"
																+ cp_node_id_quanju
																+ "_"
																+ firsthl
																+ "'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
																+ name
																+ "</span></td>"
																+ "<td  align='center' class='STYLE10'>"
																+ need
																+ "</td>";
														// $("tr
														// [id='"+arry[0]+"_"+arry[1]+"_"+arry[2]+"_"+arry[3]+"']").html(toHtml);
														$("#dialogNurse_point")
																.dialog("close");
														$(
																"tr [id='"
																		+ cpID
																		+ "_"
																		+ cp_node_id_quanju
																		+ "_"
																		+ firsthl
																		+ "']")
																.html(tohtml);
													}
												});
									},
									"取消" : function() {
										$(this).dialog("close");
									}
								}
							});
		} else {
			alert("请选择一条编辑!");
		}
	}
}

/** ********************------------------------***************************** */
function deldoctorpoint() {
	if (confirm("您确定要删除吗?")) {
		$.ajax({
			url : "../servlet/managecp",
			type : 'POST',
			data : {
				op : "del_lcp_node_doctor_point",
				cp_id : cpID,
				cp_node_id : cp_node_id_quanju,
				cp_node_table_id : cp_node_table_id_quanju
			},
			dataType : "json",
			complete : show_result,
			success : function(data, textStatus, XMLHttpRequest) {
				data = eval(data);
				data = data[0];
				if (data.result === "OK") {
					$("#doctor_item_table").html("");
					$("#doctor_point_table").html(data.table);
					alert("删除成功");
				} else {
					alert("删除失败");
				}

			}
		});
	}
}
function delnursepoint() {
	if (confirm("您确定要删除吗?")) {
		$.ajax({
			url : "../servlet/managecp",
			type : 'POST',
			data : {
				op : "del_lcp_node_nurse_point",
				cp_id : cpID,
				cp_node_id : cp_node_id_quanju,
				cp_node_table_id : cp_node_table_id_quanju
			},
			dataType : "json",
			complete : show_result,
			success : function(data, textStatus, XMLHttpRequest) {
				data = eval(data);
				data = data[0];
				if (data.result === "OK") {
					$("#nurse_item_table").html("");
					$("#nurse_point_table").html(data.table);
					alert("删除成功");
				} else {
					alert("删除失败");
				}

			}
		});
	}
}

function deldoctoritem() {
	var check$ = $("input[name='chekcbox_doctoritem']");
	var leng = check$.length;
	var delStr = "";
	for ( var jj = 0; jj < leng; jj++) {
		if (check$[jj].checked) {
			delStr = delStr + "" + check$[jj].id + ",";
		}
	}
	if (delStr === "") {
		alert("您没有勾选复选框");
		return false;
	} else {
		if (confirm("您确定要删除吗?")) {
			$.ajax({
				url : "../servlet/managecp",
				type : 'POST',
				data : {
					op : "del_lcp_node_doctor_item",
					delids : delStr
				},
				dataType : "json",
				complete : show_result,
				success : function(data, textStatus, XMLHttpRequest) {
					data = eval(data);
					data = data[0];
					if (data.result === "OK") {
						$("#doctor_item_table").html(data.table);
						alert("删除成功");
					} else {
						alert("删除失败");
					}

				}
			});
		}
	}
}
function delnurseitem() {
	var check$ = $("input[name='chekcbox_nurseitem']");
	var leng = check$.length;
	var delStr = "";
	for ( var jj = 0; jj < leng; jj++) {
		if (check$[jj].checked) {
			delStr = delStr + "" + check$[jj].id + ",";
		}
	}
	if (delStr === "") {
		alert("您没有勾选复选框");
		return false;
	} else {
		if (confirm("您确定要删除吗?")) {
			$.ajax({
				url : "../servlet/managecp",
				type : 'POST',
				data : {
					op : "del_lcp_node_nurse_item",
					delids : delStr
				},
				dataType : "json",
				complete : show_result,
				success : function(data, textStatus, XMLHttpRequest) {
					data = eval(data);
					data = data[0];
					if (data.result === "OK") {
						$("#nurse_item_table").html(data.table);
						alert("删除成功");
					} else {
						alert("删除失败");
					}

				}
			});
		}
	}
}
// 显示第二层医嘱point
function showpan(c_id, cpNodeID, cp_node_order_id, event) {
	cp_node_table_id_quanju = cp_node_order_id;
	onclick1point_quanju = true;// 已经选择第一层
	onclick2point_quanju = false;// 第二层未选择
	cp_node_id_quanju = cpNodeID;

	var cid = "tbody[id='order_point_table'] > tr[name='pan" + c_id + "']";// 要显示的第二层
	var ncid = "tbody[id='order_point_table'] > tr[name^=pan]:not([name='pan"
			+ c_id + "'])";// 要隐藏的第二层

	$(cid).each(function() {// 显示隐藏表格
		$(this).css("display", "");
	});
	$(ncid).each(function() {// 隐藏其他显示
		$(this).css("display", "none");
	});

	$("tr[name='trpan']").each(function() {
		$(this).css("background-color", "#ffffff");
	});
	$(event).css("background-color", "#51b2f6");
	tempColor = "#51b2f6";

}

// 显示医嘱item
function showorderitem(cp_id, cpNodeID, cp_node_order_id, event) {
	cpOrderID2 = cp_node_order_id;
	onclick2point_quanju = true;
	cp_node_id_quanju = cpNodeID;
	// alert(cp_id+" "+cpNodeID+" "+cp_node_order_id);
	$.ajax({
		url : "../servlet/managecp",
		type : 'POST',
		data : {
			op : "show_order_item",
			cp_id : cp_id,
			cp_node_id : cpNodeID,
			cp_node_table_id : cp_node_order_id
		},
		dataType : "json",
		complete : show_result,
		success : function(data, textStatus, XMLHttpRequest) {
			data = eval(data);
			data = data[0];
			// alert(data.table);
			$("#order_item_table").html(data.table);
		}
	});
	var cid = "tbody[id='order_point_table'] > tr[name^='pan']";
	$(cid).each(function() {
		$(this).css("background-color", "#DFE6E6");
	});

	var tr$ = $(event);
	tr$.css("background-color", "#51b2f6");
	tempColor = "#51b2f6";
}

function addfamily() {
	$("#dialogFamily").dialog("open");
	$("#dialogFamily").dialog({
		open : function() {
			$("#familyName").attr("value", "");
			$("#familyName")[0].focus();
		},
		buttons : {
			"添加" : function() {
				var need = $(":radio[name='family'][checked]").val();
				var name = $("#familyName").val();
				$.ajax({
					url : "../servlet/managecp",
					type : 'POST',
					data : {
						op : "add_family_point",
						cp_id : cpID,
						cp_node_id : cpNodeID,
						need : need,
						name : encodeURI(name, "utf-8")
					},
					dataType : "json",
					complete : show_result,
					success : function(data, textStatus, XMLHttpRequest) {
						data = eval(data);
						data = data[0];

						if (data.result === "OK") {
							$("#dialogFamily").dialog("close");
							$("#lcp_node_family_point_tbody").html(data.table);
						} else {
							alert("添加失败");

						}

					}
				});
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		}
	});
}
function delfamily() {
	if (!onclickdoctorpoint_quanju) {
		alert("请选择要删除的数据");
		return;
	}
	if (confirm("您确定要删除吗?")) {
		$.ajax({
			url : "../servlet/managecp",
			type : 'POST',
			data : {
				op : "del_family_point",
				cp_id : cpID,
				cp_node_id : cp_node_id_quanju,
				cp_node_table_id : cp_node_table_id_quanju
			},
			dataType : "json",
			complete : show_result,
			success : function(data, textStatus, XMLHttpRequest) {
				data = eval(data);
				data = data[0];
				if (data.result === "OK") {
					$("#lcp_node_family_point_tbody").html(data.table);
					alert("删除成功");
				} else {
					alert("删除失败");
				}

			}
		});
	}
}
function showfamilyitem(cp_id, cpNodeID, cp_node_order_id, event) {
	cp_node_table_id_quanju = cp_node_order_id;
	var tr$ = $(event);
	tr$.css("background-color", "#51b2f6");
	tr$.siblings().css("background-color", "#ffffff");
	tempColor = "#51b2f6";
	onclickdoctorpoint_quanju = true;
}
/**
 * 显示doctoritem内容 刘植鑫 2011-08-10
 */
var value = '';
var valuehl = '';
var flag = '';
function showdoctoritem(cp_id, cpNodeID, cp_node_doctor_id, event) {
	cp_node_table_id_quanju = cp_node_doctor_id;
	onclickdoctorpoint_quanju = true;
	cp_node_id_quanju = cpNodeID;
	$.ajax({
		url : "../servlet/managecp",
		type : 'POST',
		data : {
			op : "show_doctor_item",
			cp_id : cp_id,
			cp_node_id : cpNodeID,
			cp_node_table_id : cp_node_doctor_id
		},
		dataType : "json",
		complete : show_result,
		success : function(data, textStatus, XMLHttpRequest) {
			data = eval(data);
			data = data[0];
			$("#doctor_item_table").html(data.table);
		}
	});
	var tr$ = $(event);
	tr$.css("background-color", "#51b2f6");
	tr$.siblings().css("background-color", "#ffffff");
	tempColor = "#51b2f6";
	// 点击取值
	// 1.诊疗工作编辑
	$(event).each(function(d) {
		$(this).click(function() {
			value = $(this).text();
			/* alert(value); */
		});

	});
}
/**
 * 显示doctor_point内容 刘植鑫 2011-08-10
 */
function showdoctorpoint(cp_id, cpNodeID) {
	$.ajax({
		url : "../servlet/managecp",
		type : 'POST',
		data : {
			op : "show_doctor_point",
			cp_id : cp_id,
			cp_node_id : cpNodeID
		},
		dataType : "json",
		complete : show_result,
		success : function(data, textStatus, XMLHttpRequest) {
			data = eval(data);
			data = data[0];
			$("#doctor_point_table").html(data.table);
		}
	});
}
/**
 * 显示doctor_point内容 刘植鑫 2011-08-10
 */
function shownursepoint(cp_id, cpNodeID) {
	$.ajax({
		url : "../servlet/managecp",
		type : 'POST',
		data : {
			op : "show_nurse_point",
			cp_id : cp_id,
			cp_node_id : cpNodeID
		},
		dataType : "json",
		complete : show_result,
		success : function(data, textStatus, XMLHttpRequest) {
			data = eval(data);
			data = data[0];
			$("#nurse_point_table").html(data.table);
		}
	});
}

function show() {
	if ($("#orderSelectb").val() == $("#orderSelectb1").val()) {
		$("#orderPCb").val("ONCE");
	} else if ($("#orderSelectb").val() == $("#orderSelectb2").val()) {
		$("#orderPCb").val("");
	} else if ($("#orderSelectb").val() == $("#orderSelectb3").val()) {
		$("#orderPCb").val("");
	}else if ($("#orderSelectb").val() == $("#orderSelectb4").val()) {
		$("#orderPCb").val("ONCE");
	}
	if ($("#orderSelect").val() == $("#orderSelect1").val()) {
		$("#orderPC").val("ONCE");
	} else if ($("#orderSelect").val() == $("#orderSelect2").val()) {
		$("#orderPC").val("");
	}else if ($("#orderSelect").val() == $("#orderSelect3").val()) {
		$("#orderPC").val("");
	}else if ($("#orderSelect").val() == $("#orderSelect4").val()) {
		$("#orderPC").val("ONCE");
	}
}
function showbefore() {

	if ($("#orderSelectb").val() == $("#orderSelectb1").val()) {
		$("#orderPCb").val("ONCE");
	} else if ($("#orderSelectb").val() == $("#orderSelectb2").val()) {
		$("#orderPCb").val("");
	} else if ($("#orderSelectb").val() == $("#orderSelectb3").val()) {
		$("#orderPCb").val("");
	}else if ($("#orderSelectb").val() == $("#orderSelectb4").val()) {
		$("#orderPCb").val("ONCE");
	}
	if ($("#orderSelect").val() == $("#orderSelect1").val()) {
		$("#orderPC").val("ONCE");
	} else if ($("#orderSelect").val() == $("#orderSelect2").val()) {
		$("#orderPC").val("");
	}else if ($("#orderSelect").val() == $("#orderSelect3").val()) {
		$("#orderPC").val("");
	}else if ($("#orderSelect").val() == $("#orderSelect4").val()) {
		$("#orderPC").val("ONCE");
	}
}

function copy() {
	if (!onclick2point_quanju) {
		alert("请选中二级菜单后，点击复制二级菜单按钮！");
	} else if (onclick2point_quanju) {
		$("#nodeNamezk").load("../servlet/managecp", {
			op : "selectNode",
			cp_id : cpID,
			cp_node_id : cpNodeID
		});
		$("#dialogNode").dialog("open");
	}
}

function showCheck() {
	$("#order1").css("display", "none");
	$("#order2").css("display", "");
	$("#orderPY2").val("");
}
function showCheck2() {
	$("#edit1").css("display", "none");
	$("#edit2").css("display", "");
	$("#orderPYb2").val("");
}

// 判断是否检验检查
function fsdata() {
	var s = $("#orderPY").val();
	$.ajax({
		type : "POST",
		url : "../servlet/managecp",
		data : "s=" + s + "&op=addcx",
		success : function(msg) {
			msg = eval('(' + msg + ')');
			// 第一级菜单是否为检验检查
			var sfcheck = msg["sfcheck"];
			if (sfcheck == "C" || sfcheck == "D") {
				$("#orderjl").val("1");
			}
		}
	});
}

var highlightcolor = '#d5f4fe';
var clickcolor = '#51b2f6';
var recoveryColor = '#FFFFFF';
var bgcolor = "#51b2f6";
var tempColor = "";
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
// 双击复制节点
function ondblClickCopy(event) {
	event.bgColor = green;
	// var cpNodeZKID=
	// $(event).children().eq(1).children().eq(0).children().eq(1).children().eq(0).val();
	var cpNodeZKID = $(event).children().eq(0).html();
	var cpNodeZKName = $(event).children().eq(1).html();
	// alert(cpNodeZKName);
	if (confirm("您确定要把选中的二级节点复制到【" + cpNodeZKName + "】吗?")) {
		$.ajax({
			url : "../servlet/managecp",
			type : 'POST',
			data : {
				op : "copyNode",
				cp_id : cpID,
				cp_node_id : cp_node_id_quanju,
				cpOrder1Point : cp_node_table_id_quanju,
				cpOrder2Point : cpOrderID2,
				cpNodeZKID : cpNodeZKID
			},
			dataType : "json",
			complete : show_result,
			success : function(data, textStatus, XMLHttpRequest) {
				data = eval(data);
				data = data[0];
				if (data.result === "OK") {
					alert("复制二级菜单成功！");
					$("#dialogNode").dialog("close");
				} else if (data.result === "null") {
					alert("目标一级菜单不存在，请检查复制的菜单与目标菜单是否一致！");
					$("#dialogNode").dialog("close");
				} else if (data.result === "fail") {
					alert("复制二级菜单失败！");
					$("#dialogNode").dialog("close");
				}
			}
		});
	}

}

// frag.js

// 链接到医嘱方案预览页面
function showOrder() {
	// alert(cpID);
	// alert(cp_node_id_quanju);
	if (cp_node_id_quanju === "") {
		alert("请选中菜单后进行预览！");
		return false;
	} else {
		window.open('../service/orderview.jsp?cpId=' + cpID + '&&cpNodeId='
				+ cp_node_id_quanju);
	}
}
var currentStep = 0;
var max_line_num = 0;
var llUp = 0;
var llNext = 0;
// var line = "'"+cp_id+"_"+cp_node_id+"_"+cp_node_nurse_id+"_"+currentStep+"'";

// 选中一行后，取得当前行号和最大行号
function lineclick(line) {
	$('#order_item_table tr').each(function() {
		$(this).css("background-color", "#ffffff");
	});
	var seq = $(line).children().eq(1).html();
	max_line_num = $('#order_item_table tr:last-child').children("td").eq(1)
			.html();
	// alert(max_line_num);
	// $(line).css("background-color","#BFFF00");
	currentStep = seq;
}

// 编辑按钮
function editOrderItem() {
	$("#edit1").css("display", "");
	$("#edit2").css("display", "none");
	// var s=""+cp_id+"_"+cp_node_id_quanju+"_"+cpOrderID2+"_"+currentStep+"";
	$("#doctorMarkb").keydown(function(event) {
		var keycode = event.which;
		var markLength = $("#doctorMarkb").val().length;
		if (markLength >= 50) {
			var num = $("#doctorMarkb").val().substr(0, 50);
			$("#doctorMarkb").val(num);
			if (keycode != 8) {
				alert("超出字符限制，多余部分将被截去！");
				return false;
			}
		}
	});
	// var s=""+cp_id+"_"+cp_node_id_quanju+"_"+cpOrderID2+"_"+currentStep+"";
	var check$ = $("input[name='chekcbox_orderitem']");
	var num = 0;
	var leng = check$.length;

	for ( var i = 0; i < check$.length; i++) {
		if (check$[i].checked) {
			num++;
			if (num == 1) {
				var s = 0;
				for ( var jj = 0; jj < leng; jj++) {
					if (check$[jj].checked) {
						s = check$[jj].id;
					}
				}
				// alert("cx。。。。。。。"+s);
				$.ajax({
					type : "POST",
					url : "../servlet/managecp",
					data : "s=" + s + "&op=edit",
					success : function(msg) {
						msg = eval('(' + msg + ')');
						// 第一级菜单是否为检验检查
						var sfcheck = msg["sfcheck"];
						// alert(sfcheck);
						// var MEASURE_UNITSBM = msg["MEASURE_UNITSBM"];//领量单位编码
						// var DOSAGE_UNITSBM = msg["DOSAGE_UNITSBM"];//计量单位编码
						// var WAYBM = msg["WAYBM"];//途径编码
						var MEASURE_UNITS = msg["MEASURE_UNITS"];// 领量单位中文

						if (msg["MEASURE"] == "") {
							MEASURE_UNITS = "";
						}
						if (msg["DOSAGE"] == "") {
							msg["DOSAGE_UNITS"] = "";
						}
						// alert(MEASURE_UNITS);
						$("#orderCode2b").val(msg["MEASURE_UNITSBM"]);
						$("#orderCode1b").val(msg["DOSAGE_UNITSBM"]);
						$("#ordertjbhid").val(msg["WAYBM"]);
						var FREQUENCY = msg["FREQUENCY"];
						$("#orderCodeb").val(msg["ORDER_NO"]);// 编码
						$("#orderNameb").val(msg["CP_NODE_ORDER_TEXT"]);// 具体医嘱
						$("#ordertjb").val(msg["WAY"]);// 途径
						$("#doctorMarkb").val(msg["MARK"]);// 医生嘱托
						if (msg["ORDER_KIND"] == "长期") {// 类型
							$("#orderSelectb").val($("#orderSelectb2").val());
						} else if (msg["ORDER_KIND"] == "临时") {
							$("#orderSelectb").val($("#orderSelectb1").val());
							FREQUENCY = "ONCE";
						} else if (msg["ORDER_KIND"] == "出院") {
							$("#orderSelectb").val($("#orderSelectb3").val());
						}else if (msg["ORDER_KIND"] == "长期+临时") {
							$("#orderSelectb").val($("#orderSelectb4").val());
							FREQUENCY = "ONCE";
						}
						if (sfcheck == "C" || sfcheck == "D") {
							$("#orderPCb").val("ONCE");
							$("#dosageb").val("1");
						} else {
							$("#orderPCb").val(FREQUENCY);// 频次
							$("#dosageb").val(msg["DOSAGE"]);// 使用剂量
						}

						$("#orderjlb").val(msg["MEASURE"]);// 领量
						$("#orderjldwb").val(MEASURE_UNITS);// 领量单位

						$("#dosage_unitsb").val(msg["DOSAGE_UNITS"]);// 使用剂量单位
						$("#specificationb").val(msg["SPECIFICATION"]);// 规格
						$("charge_amountb").val(msg["CHARGE_AMOUNT"]);// 单价
//						alert("NEED_ITEM=="+msg["NEED_ITEM"]);
//						alert("DEFAULT_ITEM=="+msg["DEFAULT_ITEM"]);
						var checked = document.getElementsByName("orderItemb");// 是否必做
						if (msg["NEED_ITEM"] == "0") {
							checked[0].checked = true;
						} else if (msg["NEED_ITEM"] == "1") {
							checked[1].checked = true;
						}
					
						var checked1 = document.getElementsByName("orderItemDefaultb");// 是否默认
						if (msg["DEFAULT_ITEM"] == "0") {
							checked1[0].checked = true;
						} else if (msg["DEFAULT_ITEM"] == "1") {
							checked1[1].checked = true;
						}
						
						/*
						 * if (checked[0].value == "0") { window.alert("可选项"); }
						 * else if (checked[0].value == "1") {
						 * window.alert("必做项"); }
						 */
						// $("#radiobuttonb").attr("value",'0');
					}
				});
				$("#order_itemb").dialog("open");

				$("#order_itemb")
						.dialog(
								{
									open : function() {
										$("#orderCodeb")[0].focus();
										$("#orderCodeb").val("");
										// $("#orderCodeb")[0].focus();
										$("#orderNameb").val("");
										$("#orderPYb").val("");
										$("#orderWBb").val("");
										$("#ordertjb").val("");
										$("#orderjlb").val("");// 领量
										$("#orderjldwb").val("");// 领量单位
										$("#dosageb").val("");// 使用剂量
										$("#dosage_unitsb").val("");// 使用剂量单位
										$("#specificationb").val("");
										$("#charge_amountb").val("");//单价
										$("#orderCode2b").val("");
										$("#orderCode1b").val("");
										$("#ordertjbhid").val("");
										$("#doctorMarkb").val("");

									},

									buttons : {
										"确定" : function() {
											// seq=$(line).children().eq(1).html();
											// alert(currentStep);

											// var s
											// =""+cp_id+"_"+cp_node_id_quanju+"_"+cpOrderID2+"_"+currentStep+"";
											var check$ = $("input[name='chekcbox_orderitem']");
											var leng = check$.length;
											var s1 = 0;
											for ( var jj = 0; jj < leng; jj++) {
												if (check$[jj].checked) {
													s1 = check$[jj].id;
												}
											}

											var arry = s1.split("_");
											var need = $(
													":radio[name='orderItem'][checked]")
													.val();
											var itemDefault = $(":radio[name='orderItemDefaultb'][checked]").val();//是否默认
											var code = $("#orderCodeb").val();
											var type = $("#orderSelectb").val();
											// var
											// belong=$("#orderBelongSelectb").val();
											var name = $("#orderNameb").val();
											var specification = $(
													"#specificationb").val();
											var charge_amount = $(
											"#charge_amountb").val();//单价
											var orderjl = $("#orderjlb").val();
											var orderCode2 = $("#orderjldwb")
													.val();// 领量单位
											var dosage = $("#dosageb").val();
											var orderCode1 = $("#dosage_unitsb")
													.val();// 一次计量单位
											// var
											// orderTypeSelect=$("#orderTypeSelectb").val();
											var orderPC = $("#orderPCb").val();
											var ordertj = $("#ordertjb").val();
											var orderpcdw = $("#orderpcdwb")
													.val();
											var mark = $("#doctorMarkb").val();
											var reg = new RegExp("[0-9]+");
											// 校验
											if (code == "") {
												alert("医嘱编码为空!");
												$("#order_itemb").dialog(
														"close");
												return;
											}
											// 领量校验
											if (orderjl == "") {
												if (orderCode2 == "") {

												}
												/*
												 * else if(orderCode2!=""){
												 * alert("请先输入领量!"); return; }
												 */
											} else if (orderjl != "") {
												if (reg.test(orderjl)) {
													if (orderCode2 == "") {
														/*
														 * alert("请输入领量单位!");
														 * return;
														 */
													} else if (orderCode2 != "") {
													}
												} else {
													alert("请输入正确格式领量!");
													return;
												}
											}
											// 一次计量校验
											if (dosage == "") {
												if (orderCode1 == "") {

												}
												/*
												 * else if(orderCode1!=""){
												 * alert("请先输入一次计量!"); return; }
												 */
											} else if (dosage != "") {
												if (reg.test(dosage)) {
													if (orderCode1 == "") {
														alert("请输入一次使用剂量单位!");
														return;
													} else if (orderCode1 != "") {

													}
												} else {
													alert("请输入正确格式一次计量!");
													return;
												}
											}
											// 途径校验

											if (!reg.test(ordertj)
													&& ordertj != "") {
												alert("请输入正确途径!");
												return;
											}
											$
													.ajax({
														url : "../servlet/managecp",
														type : 'POST',
														data : {
															op : "editOrderItem",
															cp_id : cpID,
															cp_node_id : cpNodeID,
															cp_node_order_id : cpOrderID2,
															radiobutton : need,// 必做项
															isDefault: itemDefault,//默认项
															orderCode : code,// 医嘱编码
															orderSelect : type,// 长期-临时-出院
															/*
															 * orderName:name,//encodeURI(name,"utf-8"),//医嘱名称
															 * specification:specification,//规格
															 */
															orderName : encodeURIComponent(
																	name,
																	"utf-8"),// encodeURI(name,"utf-8"),//医嘱名称
															specification : encodeURIComponent(
																	specification,
																	"utf-8"),// 规格
															orderjl : orderjl,// 医嘱计量
															orderCode2 : encodeURIComponent(
																	orderCode2,
																	"utf-8"),// 计量单位
															dosage : dosage,// 一次使用剂量
															orderCode1 : encodeURIComponent(
																	orderCode1,
																	"utf-8"),// 一次使用剂量单位
															orderPC : orderPC,// 频次
															mark : encodeURIComponent(
																	mark,
																	"utf-8"),
															ordertj : encodeURIComponent(
																	ordertj,
																	"utf-8"),// 途径
															// yanneiOrderType:yanneiOrderType,//院内医嘱类别
															orderpcdw : encodeURI(
																	orderpcdw,
																	"utf-8"),
															s1 : s1,// 行编号
															mark : encodeURI(
																	mark,
																	"utf-8")
														},
														dataType : "json",
														complete : show_result,
														success : function(
																data,
																textStatus,
																XMLHttpRequest) {
															data = eval(data);
															// var FREQUENCY =
															// data.FREQUENCY;
															// var tujing =
															// data.WAY;

															if (data.result === "OK") {
																if (data.ORDER_KIND == "0") {
																	orderkind = "临时";
																} else if (data.ORDER_KIND == "1") {
																	orderkind = "长期";

																} else if (data.ORDER_KIND == "2") {
																	orderkind = "出院";
																}else if (data.ORDER_KIND == "3") {
																	orderkind = "长期+临时";
																}
																if (data.NEED_ITEM == "0") {
																	marked = "";
																} else if (data.NEED_ITEM == "1") {
																	marked = "√";
																}
																if (data.DEFAULT_ITEM == "0") {
																	marked1 = "";
																} else if (data.DEFAULT_ITEM == "1") {
																	marked1 = "√";
																}

																var toHtml = "<td  align='center' ><input type='checkbox' name='chekcbox_orderitem' id='"
																		+ arry[0]
																		+ "_"
																		+ arry[1]
																		+ "_"
																		+ arry[2]
																		+ "_"
																		+ arry[3]
																		+ "'></td>"
																		+ "<td  align='center' >"
																		+ arry[3]
																		+ "</td>"
																		+ // 序号
																		"<td  align='center' >"
																		+ data.ORDER_ITEM_SET_ID
																		+ "</td>"
																		+ // 组
																		"<td  align='center' >"
																		+ orderkind
																		+ "</td>"
																		+ // 类别
																		// 长期-临时-出院
																		"<td align='center'>"+data.ORDER_TYPE_NAME+"</td>"+//
																		"<td  align='left' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
																		+ data.CP_NODE_ORDER_TEXT
																		+ "</td>"
																		+ // 医嘱内弄
																		"<td  align='center' >"
																		+ data.SPECIFICATION
																		+ "</td>"
																		+ // 规格
																		"<td  align='center' >"
																		+ marked
																		+ "</td>"
																		+ // 必做
																		"<td  align='center' >"
																		+ marked1
																		+ "</td>"
																		+ // 默认  
																		"<td  align='center' >"
																		+ data.MEASURE
																		+ "</td>"
																		+ // 计量
																		"<td  align='center' >"
																		+ data.FREQUENCY
																		+ "</td>"
																		+ // 频次
																		"<td  align='center' >"
																		+ data.WAY
																		+ "</td>";// 途径
																$(
																		"#order_itemb")
																		.dialog(
																				"close");
																$(
																		"tr [id='"
																				+ arry[0]
																				+ "_"
																				+ arry[1]
																				+ "_"
																				+ arry[2]
																				+ "_"
																				+ arry[3]
																				+ "']")
																		.html(
																				toHtml);
																// $("#order_item_table").html(toHtml);
																$("#orderCodeb").val("");
																// $("#orderCodeb")[0].focus();
																$("#orderNameb").val("");
																$("#orderPYb").val("");
																$("#orderWBb").val("");
																$("#orderPCb").val("");
																$("#ordertjb").val("");
																$("#orderjlb").val("");// 领量
																$("#orderjldwb").val("");// 领量单位
																$("#dosageb").val("");// 使用剂量
																$("#dosage_unitsb").val("");// 使用剂量单位
																$("#specificationb").val("");
																$("#charge_amountb").val("");//单价
																$("#orderCode2b").val("");
																$("#orderCode1b").val("");
																$("#ordertjbhid").val("");
																$("#radiobuttonb").attr("value",'0');
															} else {
																alert("编辑失败");
															}

														}
													});
										},
										"取消" : function() {
											$(this).dialog("close");
										}
									}

								});
			} else {
				$("#order_itemb").dialog("close");
				alert("请勾中一个复选框编辑");
				return;
			}
		}
	}

}

// 在三级菜单内进行复制
function copySan() {
	var check$ = $("input[name='chekcbox_orderitem']");
	// var array = new Array();
	var copyStr = "";
	var leng = check$.length;
	for ( var n = 0; n < leng; n++) {
		if (check$[n].checked) {
			copyStr = copyStr + "" + check$[n].id + ",";
		}
	}
	if (copyStr.length == 0) {
		alert("请勾中复选框一条或多条复制！");
		return false;
	}
	$.ajax({
		url : "../servlet/managecp",
		type : 'POST',
		data : {
			op : "copySan",
			cp_id : cpID,
			cp_node_id : cp_node_id_quanju,
			cpOrder2Point : cpOrderID2,
			array : copyStr
		},
		dataType : "json",
		complete : show_result,
		success : function(data, textStatus, XMLHttpRequest) {
			data = eval(data);
			// data = data[0];
			if (data.result === "OK") {
				alert("复制成功！");
				$("#order_item_table").html(data.table);
			} else {
				alert("复制失败");
				return false;
			}
		}
	});
}

function selectSan() {
	var check$ = $("input[name='chekcbox_orderitem']");
	var copyStr = "";
	var ss = "";
	var leng = check$.length;
	for ( var n = 0; n < leng; n++) {
		if (check$[n].checked) {
			copyStr = copyStr + "" + check$[n].id + ",";
			ss=ss + $("#"+check$[n].id).children().eq(2).html();
		}		
	}
	    if (copyStr.length == 0) {
			alert("请勾中复选框一条或多条移动！");
			return false;
		} else if(/[0-9]/.test(ss)){
			alert("您选中的医嘱包含已经存组的，请撤组或勾掉存组后再移动！");
			return false;
		}else{
			$("#nodeNameSan").load("../servlet/managecp", {
				op : "selectSan",
				cp_id : cpID,
				cp_node_id : cp_node_id_quanju,
				cpOrder2Point : cpOrderID2
			});
			$("#dialogNodeSan").dialog("open");
		}
}

// 将三级菜单复制到二级菜单
function copyToSecond(event) {
	event.bgColor = green;
	var cpNodeZKID = $(event).children().eq(1).html();
	var cpNodeZKName = $(event).children().eq(2).html();

	var check$ = $("input[name='chekcbox_orderitem']");
	var copyStr = "";
	var leng = check$.length;
	for ( var n = 0; n < leng; n++) {
		if (check$[n].checked) {
			copyStr = copyStr + "" + check$[n].id + ",";
		}
	}
	if (confirm("您确定要把选中的项目移动到【" + cpNodeZKName + "】吗?")) {
		$.ajax({
			url : "../servlet/managecp",
			type : 'POST',
			data : {
				op : "copyToSecond",
				cp_id : cpID,
				cp_node_id : cp_node_id_quanju,
				cpOrder2Point : cpOrderID2,// 当前的二级菜单
				cpNodeOrderID : cpNodeZKID,// 选中的二级菜单
				selectSanZK : copyStr
			},
			dataType : "json",
			complete : show_result,
			success : function(data, textStatus, XMLHttpRequest) {
				data = eval(data);
				// data = data[0];
				if (data.result === "OK") {
					alert("移动成功！");
					$("#dialogNodeSan").dialog("close");
					$("#order_item_table").html(data.table);
				}
			}
		});
	}
}

// 显示第二层医嘱point
function showzk(c_id, cpNodeID, cp_node_order_id, event) {
	cp_node_table_id_quanju = cp_node_order_id;
	onclick1point_quanju = true;// 已经选择第一层
	onclick2point_quanju = false;// 第二层未选择
	cp_node_id_quanju = cpNodeID;

	var cid = "tbody[id='nodeNameSan'] > tr[name='pan" + c_id + "']";// 要显示的第二层
	var ncid = "tbody[id='nodeNameSan'] > tr[name^=pan]:not([name='pan" + c_id
			+ "'])";// 要隐藏的第二层

	$(cid).each(function() {// 显示隐藏表格
		$(this).css("display", "");
	});
	$(ncid).each(function() {// 隐藏其他显示
		$(this).css("display", "none");
	});

	$("tr[name='trpan']").each(function() {
		$(this).css("background-color", "#ffffff");
	});
	$(event).css("background-color", "#51b2f6");
	tempColor = "#51b2f6";

}

// 编辑二级菜单
function editOrderPoint() {
	$.ajax({
		type : "POST",
		url : "../servlet/managecp",
		data : "cpid=" + cp_id + "&cp_node_id_quanju=" + cp_node_id_quanju
				+ "&cpOrderID2=" + cpOrderID2 + "&op=editTwocx",
		success : function(msg) {
			msg = eval('(' + msg + ')');
			$("#orderPointName1e").val(msg["CP_NODE_ORDER_TEXT"]);
			if (msg["ORDER_KIND"] == "0") {
				$("#orderPointTypeSelecte").val(
						$("#orderPointTypeSelecte0").val());
			} else if (msg["ORDER_KIND"] == "1") {
				$("#orderPointTypeSelecte").val(
						$("#orderPointTypeSelecte1").val());
			} else if (msg["ORDER_KIND"] == "2") {
				$("#orderPointTypeSelecte").val(
						$("#orderPointTypeSelecte2").val());
			}

		}
	});
	$("#dialogOrderPointe").dialog("open");
	$("#dialogOrderPointe")
			.dialog(
					{
						buttons : {
							"修改" : function() {
								var orderPointName1e = $("#orderPointName1e")
										.val();
								var orderPointTypeSelecte = $(
										"#orderPointTypeSelecte").val();
								var need_item = " ";
								var orderkind = "";
								$
										.ajax({
											url : "../servlet/managecp",
											type : 'POST',
											data : {
												op : "editTwogx",
												cp_id : cp_id,
												cp_node_id_quanju : cp_node_id_quanju,
												cpOrderID2 : cpOrderID2,
												orderPointName1e : encodeURI(
														orderPointName1e,
														"utf-8"),
												orderPointTypeSelecte : orderPointTypeSelecte,
												need_item : need_item
											},
											dataType : "json",
											complete : show_result,
											success : function(data,
													textStatus, XMLHttpRequest) {
												data = eval(data);
												if (data.result === "OK") {
													if (data.orderPointTypeSelecte == "0") {
														orderkind = "临时";
													} else if (data.orderPointTypeSelecte == "1") {
														orderkind = "长期";
													} else if (data.orderPointTypeSelecte == "2") {
														orderkind = "出院";
													}
													var toHtml = "<td colspan=2 align='right' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├&nbsp;&nbsp;&nbsp;"
															+ orderkind
															+ "&nbsp;&nbsp;&nbsp;</td>"
															+ "<td align='left' ><span  id='2_1_"
															+ cp_id
															+ "_"
															+ cp_node_id_quanju
															+ "_"
															+ cpOrderID2
															+ "'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
															+ data.orderPointName1e
															+ "</span></td>"
															+ "<td align='center' >"
															+ data.need_item
															+ "</td>";
													/*
													 * ;
													 * $("#order_point_table").html(data.table);
													 */
													$("#dialogOrderPointe")
															.dialog("close");
													$(
															"tr [id='"
																	+ cp_id
																	+ "_"
																	+ cp_node_id_quanju
																	+ "_"
																	+ cpOrderID2
																	+ "']")
															.html(toHtml);

												}
											}
										});

							},
							"取消" : function() {
								$(this).dialog("close");
							}
						}
					});

}

// <!-- 表格的上下移动 -->

function up_exchange_line() {
	// var llCurrent = ""+cpID+"_"+cpNodeID+"_"+cpOrderID2+"_"+currentStep+"";
	if (currentStep == 0) {
		alert('请选择一项!');
		return false;
	}
	if (currentStep <= 1) {
		alert('已经是第一行，不能上移!');
		return false;
	}
	var upStep = currentStep - 1;
	$.ajax({
		url : "../servlet/managecp",
		type : 'POST',
		data : {
			op : "upDownMove",
			ops : "upMove",
			currentStep : currentStep,
			upStep : upStep,
			cp_id : cpID,
			cp_node_id : cp_node_id_quanju,
			cpOrder2Point : cpOrderID2
		},
		dataType : "json",
		complete : show_result,
		success : function(data, textStatus, XMLHttpRequest) {
			data = eval(data);
			// data = data[0];
			if (data.result === "OK") {
				$("#order_item_table").html(data.table);
				llUp = "" + cpID + "_" + cpNodeID + "_" + cpOrderID2 + "_"
						+ upStep + "";
				$('#order_item_table tr').each(function() {
					$(this).css("background-color", "#ffffff");
				});
				$('#' + llUp).css("background-color", "#BFFF00");
				currentStep = upStep;
			} else if (data[0].result === "noMove") {
				alert("请撤组后再移动！");
				return false;
			} else {
				alert("上移失败或选中了多条上移！");
				return false;
			}
		}
	});
}

function down() {
	// var downCurrent = ""+cpID+"_"+cpNodeID+"_"+cpOrderID2+"_"+currentStep+"";
	if (currentStep == 0) {
		alert('请选择一项!');
		return false;
	}
	if (currentStep >= max_line_num) {
		alert('已经是最后一行，不能下移!');
		return false;
	}
	var nextStep = parseInt(currentStep) + 1;
	$.ajax({
		url : "../servlet/managecp",
		type : 'POST',
		data : {
			op : "upDownMove",
			ops : "downMove",
			currentStep : currentStep,
			nextStep : nextStep,
			cp_id : cpID,
			cp_node_id : cp_node_id_quanju,
			cpOrder2Point : cpOrderID2
		},
		dataType : "json",
		complete : show_result,
		success : function(data, textStatus, XMLHttpRequest) {
			data = eval(data);
			// data = data[0];
			if (data.result === "OK") {
				$("#order_item_table").html(data.table);
				llNext = "" + cpID + "_" + cpNodeID + "_" + cpOrderID2 + "_"
						+ nextStep + "";
				$('#order_item_table tr').each(function() {
					$(this).css("background-color", "#ffffff");
				});
				$('#' + llNext).css("background-color", "#BFFF00");
				currentStep = nextStep;
			} else if (data[0].result === "noMove") {
				alert("请撤组后再移动！");
				return false;
			} else {
				alert("下移失败或选中了多条下移！");
				return false;
			}
		}
	});
}
