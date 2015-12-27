window.onload = function() {
	$.ajax({
		url : 'PdcaServlet?ran=+Math.random()',
		data : {
			op : "2"
		},
		dataType : 'json',
		error : function() {

		},
		success : function(data) {
			for ( var i = 0; i < data["cp_cp"].length; i++) {
				var tr = "<tr onclick=getCpVersions(); id=option" + i + ">" +
						"<td>"+ data["cp_cp"][i]["cp_master_id"]+ "</td>" +
						"<td style='display:none'>"+ data["cp_cp"][i]["cp_id"] + "</td>" +
						"<td>"+ data["cp_cp"][i]["cp_name"]+ "</td>" +
						"<td style='display:none'>"+ data["cp_cp"][i]["cp_code"]+ "</td>" +
						"<td style='display:none'>"+ data["cp_cp"][i]["cp_version"] + "</td>" +
						"<td>"+ data["cp_cp"][i]["dept_name"]+ "</td>" +
						"</tr>";
				$('#cplist').append(tr);
			}
		}
	})
}
var myArray =new Array();
function setCheck(o) {
	var tr = o.parentNode.parentNode;
	var tds = tr.cells;
	var str = tds[1].innerText;
	if (!o.checked) {
		for(var m =0;m<myArray.length;m++){
			if(myArray[m]==str){
				myArray[m]='-';
				}
		}			
		return;
	} else {
		for(var n =0;n<myArray.length;n++){
			if(myArray[n]=='-'){				
				myArray[n]=str;
				break;
			}
		}
	}
}

function getCpVersions() {
	var rows = document.getElementById("optionContainer").rows;
	if (rows.length > 0) {
		for ( var i = 0; i < rows.length; i++) {
			(function(i) {
				var cp_master_id = rows[i].cells[0].innerText;
				var cp_id = rows[i].cells[1].innerText;
				var cp_name = rows[i].cells[2].innerText;
				var obj = rows[i];
				obj.onclick = function() {
					myArray =new Array("-","-");
					$('#cpversionscontain').empty();
					$('#cpone').empty();
					$('#cptwo').empty();
					$('#mytitle1').text('路径：');
					$('#mytitle2').text('路径：');
					for ( var j = 0; j < rows.length; j++) {
						if (rows[j] == this) { // 判断是不是当前选择的行
							rows[j].style.background = "#D1EEEE";
						} else {
							rows[j].style.background = "white";
						}
					}
					$
							.ajax({
								url : 'PdcaServlet?ran=' + Math.random(),
								data : {
									op : "3",
									master_id : cp_master_id
								},
								dataType : 'json',
								error : function() {
									
								},
								success : function(data) {																	
									for ( var i = 0; i < data["cp_cp"].length; i++) {
										var tr = "<tr id=option"
												+ i
												+ ">"
												+ "<td><input name='ck' type='checkbox' onclick='setCheck(this)'/></td>"
												+ "<td name='cp_id'>"
												+ data["cp_cp"][i]["cp_id"]
												+ "</td>" + "<td>"
												+ data["cp_cp"][i]["cp_name"]
												+ "</td>" + "<td>"
												+ data["cp_cp"][i]["cp_code"]
												+ "</td>" + "<td>"
												+ data["cp_cp"][i]["cp_hzl"]
												+ "</td>" + "<td>"
												+ data["cp_cp"][i]["cp_pjzyr"]
												+ "</td>" + "<td>"
												+ data["cp_cp"][i]["cp_pjzyf"]
												+ "</td>" + "<td>"
												+ data["cp_cp"][i]["cp_status"]
												+ "</td>" + "</tr>";
										$('#cpversionscontain').append(tr);
									}
								}
							});
				};
			})(i)
		}
	}
}

function getCompare() {
	var selectedData = [];
	var a =$("input[name='ck']:checked");
	$("input[name='ck']:checked").each(function(){
		var tablerow = $(this).parent("td").parent("tr");
		var code = tablerow.find("[name='cp_id']").text();
		selectedData.push(code);
	});
	if (selectedData.length == 2) {
		$('#cpone').empty();
		$('#cptwo').empty();
		$('#mytitle1').text('路径：'+selectedData[0]);
		$('#mytitle2').text('路径：'+selectedData[1]);
		$.ajax({
			url : 'PdcaServlet?ran=+Math.random()',
			data : {
				op : "5",
				cpone : selectedData[0],
				cptwo : selectedData[1],
		        flag:"one"
			},
			dataType : 'json',
			error : function(data) {
			},
			success : function(data) {
				var nodeflagone = '-';
				var parientidone = 0;
				for ( var i = 0; i < data["cp_disorderone"].length; i++) {
					if (nodeflagone != data["cp_disorderone"][i]["node_name"]) {
						parientidone++;
						var tr = "<tr data-tt-id='" + parientidone
								+ "'><td><span class='folder'>"
								+ data["cp_disorderone"][i]["node_name"]
								+ "</span></td></tr>"
						$('#cpone').append(tr);
					}
					var tr = "<tr data-tt-id='" + parientidone + '-' + i
							+ "' data-tt-parent-id='" + parientidone
							+ "'><td><span class='file'>"
							+ data["cp_disorderone"][i]["order_text"]
							+ "</span></td></tr>"
					nodeflagone = data["cp_disorderone"][i]["node_name"];
					$('#cpone').append(tr);
				}
				$("#example-advancedone").treetable({
					expandable : true
				});
				$("#example-advancedone tbody tr").mousedown(function() {
					$("tr.selected").removeClass("selected");
					$(this).addClass("selected");
				});
				
				var nodeflagtwo = '-';
				var parientidtwo = 0;

				for ( var i = 0; i < data["cp_disordertwo"].length; i++) {
					if (nodeflagtwo != data["cp_disordertwo"][i]["node_name"]) {
						parientidtwo++;
						tr = "<tr data-tt-id='" + parientidtwo
								+ "'><td><span class='folder'>"
								+ data["cp_disordertwo"][i]["node_name"]
								+ "</span></td></tr>"
						$('#cptwo').append(tr);
					}
					tr = "<tr data-tt-id='" + parientidtwo + '-' + i
							+ "' data-tt-parent-id='" + parientidtwo
							+ "'><td><span class='file'>"
							+ data["cp_disordertwo"][i]["order_text"]
							+ "</span></td></tr>";
					nodeflagtwo = data["cp_disordertwo"][i]["node_name"];
					$('#cptwo').append(tr);
				}
				$("#example-advancedtwo").treetable({
					expandable : true
				});
				$("#example-advancedtwo tbody tr").mousedown(function() {
					$("tr.selected").removeClass("selected");
					$(this).addClass("selected");
				});
			}
		})
	
	}
}

var t1=null;
/* 显示遮罩层 */
function showOverlay() {
     t1 = window.setTimeout("plusProcessValue()",100);//使用字符串执行方法 
    $("#overlay").height(pageHeight());
    $("#overlay").width(pageWidth());
    // fadeTo第一个参数为速度，第二个为透明度
    // 多重方式控制透明度，保证兼容性，但也带来修改麻烦的问题
    $("#overlay").fadeTo(200, 0.5);
}

function plusProcessValue(){
	var mywidth =$("#myprocessbar").css('width');
	mywidth+=10;
	if(mywidth>=100)
	{
		mywidth=100;
	}
	$("#myprocessbar").width(mywidth); 
	window.clearTimeout(t1);
}

/* 隐藏覆盖层 */
function hideOverlay() {
	$("#myprocessbar").width(100);
    $("#overlay").fadeOut(200);
    var t1
}

/* 当前页面高度 */
function pageHeight() {
    return document.body.scrollHeight;
}

/* 当前页面宽度 */
function pageWidth() {
    return document.body.scrollWidth;
}

/* 定位到页面中心 */
function adjust(id) {
    var w = $(id).width();
    var h = $(id).height();
    
    var t = scrollY() + (windowHeight()/2) - (h/2);
    if(t < 0) t = 0;
    
    var l = scrollX() + (windowWidth()/2) - (w/2);
    if(l < 0) l = 0;
    
    $(id).css({left: l+'px', top: t+'px'});
}

//浏览器视口的高度
function windowHeight() {
    var de = document.documentElement;

    return self.innerHeight || (de && de.clientHeight) || document.body.clientHeight;
}

//浏览器视口的宽度
function windowWidth() {
    var de = document.documentElement;

    return self.innerWidth || (de && de.clientWidth) || document.body.clientWidth
}

/* 浏览器垂直滚动位置 */
function scrollY() {
    var de = document.documentElement;

    return self.pageYOffset || (de && de.scrollTop) || document.body.scrollTop;
}

/* 浏览器水平滚动位置 */
function scrollX() {
    var de = document.documentElement;

    return self.pageXOffset || (de && de.scrollLeft) || document.body.scrollLeft;
}
