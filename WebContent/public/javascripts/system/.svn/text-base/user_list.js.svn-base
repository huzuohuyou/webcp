var nkey = "";					// 用户名称
var lkey = "";					// 登录名称
var selectid = "";				// 已选择的复选框的值
var isSearch = false;			// 查询标识
var highlightcolor = '#d5f4fe';
var clickcolor = '#add2da';
var recoveryColor = '#FFFFFF';
var usrNmFlg = false;
var loginNmFlg = false;
var isToggle = false;

//页面加载成功后运行
$(function() {
	$( "#wait" ).dialog({
	    autoOpen  : false,
	    height    : 0,
	    width     : 350,
	    modal     : true,
	    resizable : false,
	    title     : "信息正在加载中，请稍候……"
	});

	$("#wait").dialog("open");
	$('#registryDialog').load('personnel.jsp');
	
	list("", "");

	$( "#registryDialog" ).dialog({
		autoOpen: false,
		height: 520,
		width: 800,
		title: "用户注册窗口",
		modal: true,
		resizable: false,
		close : function(event, ui){
			$("#userNameTbx").val("");
			$("#loginNameTbx").val("");
			$("#pwd").val("");
			$("#confirmPwd").val("");
			$("dept_name").val("");
			$("input[name=powerCbx]").attr("checked", false);
			clear_message(["userNameTbx", "loginNameTbx","pwd","confirmPwd","dept_name"]);
			usrNmFlg = false;
			loginNmFlg = false;
		},
		buttons: {
			"注册" : function(event, ui){
				if (usrNmFlg && loginNmFlg)
				{
					$("#wait").dialog("open");
					selectid = "";
					$("input[name=powerCbx]").each(function(el){
						if ($(this).attr("checked") == true) 
						{
							var vl = $(this).val();
							vl = vl.substr(vl.indexOf("_")+1);
							selectid += "," + vl;
						}
					});
					$.ajax({
						url : "../servlet/PersonnelServlet",
						type : 'POST',
						data : {
							op : "registry",
							un : $("#userNameTbx").val(),
							ln : $("#loginNameTbx").val(),
							pwd : $("#pwd").val(),
							dept_id : $("#dept_id").val(),
							dept_name : $("#dept_name").val(),
							selectid : selectid
						},
						dataType: "text",
						contentType: "application/x-www-form-urlencoded; charset=UTF-8",
						complete: show_result,
						success: function(data, textStatus, XMLHttpRequest){
							$( "#registryDialog" ).dialog( "close" );
							if (data == "ok")
							{
								alert("用户注册成功");
								list("", "");
							} else {
								$( "#wait" ).dialog( "close" );
								alert("用户注册失败");
							}
						}
					});
				}
			},
			"取消" : function(event, ui){ $( this ).dialog( "close" ); }
		}
	});

	$( "#distributeDialog" ).dialog({
		autoOpen: false,
		height: 440,
		width: 800,
		title: "权限分配窗口",
		modal: true,
		resizable: false,
		close : function(event, ui){
			$("input[name=powerCbx_edit]").attr("checked", false);
		},
		buttons: {
			"分配" : function(event, ui){
				if ($("input[name=powerCbx_edit]:checked").length == 0)
					alert("请分配权限");
				else
				{
					$("#wait").dialog("open");
					var userId = selectid;
					selectid = "";
					$("input[name=powerCbx_edit][value!='f_']").each(function(el){
						if ($(this).attr("checked") == true) 
						{
							var vl = $(this).val();
							vl = vl.substr(vl.indexOf("_")+1);
							selectid += "," + vl;
						}
					});
					$.ajax({
						url : "../servlet/PermissionsServlet",
						type : 'POST',
						data : {
							op : "distribute",
							userid : userId,
							selectid : selectid
						},
						dataType: "text",
						contentType: "application/x-www-form-urlencoded; charset=UTF-8",
						complete: show_result,
						success: function(data, textStatus, XMLHttpRequest){
							$( "#distributeDialog" ).dialog( "close" );
							if (data == "error") {
								$("#wait").dialog("close");
								alert("用户分配权限失败");
							}
							else{
								alert("用户分配权限成功");
								list("", "");
							}
						}
					});
				}
			},
			"取消" : function(event, ui){ $( this ).dialog( "close" ); }
		}
	});
	
	// 注册功能
	$("#regBtn").click(function(){
		$( "#registryDialog" ).dialog("open");
	});
	
	// 删除功能
	$("#delBtn").click(function(){
		selectid = "";
		$(":checkbox[name='userCbx']:checked").each(function(){ 
			selectid += ", " + $(this).val();
		});
		
		if(selectid === "") alert("请您至少选择一项！");
		else if (confirm("您确定要删除所选用户吗？")){
			$("#wait").dialog("open");
			$.ajax({
				url : "../servlet/PermissionsServlet",
				type : 'POST',
				data : {
					op : "delete",
					selectid : selectid
				},
				dataType: "text",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				complete: show_result,
				success: function(data, textStatus, XMLHttpRequest){ 
					if (data == "error") {
						$("#wait").dialog("close");
						alert("用户删除失败");
					}
					else{
						alert("用户删除成功");
						list("", "");
					}
				}
			});
		}
	});
});

// 鼠标移进
var changeColor = function (element){
	tempColor = $(element).css("background-color");
	$(element).css("background-color", highlightcolor);
};

// 鼠标移出
var recoverColor = function (element){
	$(element).css("background-color", tempColor);
};

//显示或隐藏权限数据行
var togglePower = function (id)
{
	$("tr[id^='tr_']").hide();
	$("tr[id^='info_']").css("background-color", recoveryColor);
	if (selectid == id + "" && !isToggle || selectid != id)
	{
		$("#wait").dialog("open");
		$.ajax({
			url : "detail.jsp",
			type : 'POST',
			data : { userid : id },
			dataType: "html",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			complete: show_result,
			success: function(data, textStatus, XMLHttpRequest){
				$("#power_" + id).html(data);
				$("#wait").dialog("close");
				$("#info_" + id).css("background-color", clickcolor);
				tempColor = $("#info_" + id).css("background-color");
				$("#tr_" + id).show();
				isToggle = true;
				selectid = id + "";
			}
		});
	}
	else {
		$("#tr_" + id).hide();
		$("#info_" + id).css("background-color", recoveryColor);
		tempColor = $("#info_" + id).css("background-color");
		isToggle = false;
	}
};

//查询
var list = function (nkey, lkey){
	$.ajax({
		url : "../servlet/PermissionsServlet",
		type : 'POST',
		data : { op : "list", nkey: nkey, lkey : lkey },
		dataType: "html",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		complete: show_result,
		success: function(data, textStatus, XMLHttpRequest){
			$("#tableHtml").html(data);
			$("#wait").dialog("close"); 
		}
	});
};

// 按条件查询数据
var search = function(){
	$("#wait").dialog("open"); 
	var auto = $(":radio[name='radio11']:checked").val();
	var context = $("#key").val();
	if(auto == "nkey") nkey = context;
	else if(auto == "lkey") lkey = context;
	list(nkey, lkey);
	nkey = "";
	lkey = "";
};

// 全选功能
var selectAll = function(name){
    $("input[name=" + name + "]").each(function(){
		//alert($(this).val());
    	$(this).attr("checked", true);
    });
};

// 反选功能
var selectRe = function(name){
    $("input[name=" + name + "]").each(function(){
		//alert($(this).val());
    	$(this).attr("checked", !$(this).attr("checked"));
    });
};

// 按父级全选功能
var selectAllByParent = function(name, parentId){
	var pe = $("input[name=" + name + "][value='f_" + parentId + "']");
	$("input[name=" + name + "][value^='" + parentId + "_']").each(function(){
		$(this).attr("checked", pe.attr("checked"));
	});
};

// 按子级控制父级
var selectToParent = function(name, parentId){
	var flg = true;
	$("input[name='" + name + "'][value^='" + parentId + "_']").each(function(){
		flg = flg || $(this).attr("checked");
	});
	var pe = $("input[name='" + name + "'][value='f_" + parentId + "']");
	pe.attr("checked", flg);
};

var distribute = function(userId, userName){
	selectid = userId;
	$("#distributeDialog").dialog({
		title : userName + $("#distributeDialog").dialog("option", "title"),
		open : function(event, ui){
			$("#wait").dialog("open"); 
			$.ajax({
				url : "permissions.jsp",
				type : 'POST',
				data : { userid : userId },
				dataType: "html",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				complete: show_result,
				success: function(data, textStatus, XMLHttpRequest){
					$("#distributeDialog").html(data);
					$("#wait").dialog("close"); 
				}
			});
		}
	});
	$("#distributeDialog").dialog("open");
};

var lock = function(userId){
	if (confirm("您确定要冻结所选用户吗？")){
		$("#wait").dialog("open");
		$.ajax({
			url : "../servlet/PermissionsServlet",
			type : 'POST',
			data : {
				op : "lock",
				userid : userId
			},
			dataType: "text",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			complete: show_result,
			success: function(data, textStatus, XMLHttpRequest){ 
				if (data == "error") {
					$("#wait").dialog("close");
					alert("用户冻结失败");
				}
				else{
					alert("用户冻结成功");
					list("", "");
				}
			}
		});
	}
};

var unlock = function(userId){
	if (confirm("您确定要解冻所选用户吗？")){
		$("#wait").dialog("open");
		$.ajax({
			url : "../servlet/PermissionsServlet",
			type : 'POST',
			data : {
				op : "unlock",
				userid : userId
			},
			dataType: "text",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			complete: show_result,
			success: function(data, textStatus, XMLHttpRequest){ 
				if (data == "error") {
					$("#wait").dialog("close");
					alert("用户解冻失败");
				}
				else{
					alert("用户解冻成功");
					list("", "");
				}
			}
		});
	}
};

var checkUserName = function(element){
	element = $(element);
	var flag = checkRequired(element, "用 户 名");
	if (flag) flag = checkLength(element, "用 户 名", 1, 20);
	usrNmFlg = flag;
	
	if(flag){
		show_loading(element);
		$.ajax({
			url : "../servlet/PersonnelServlet",
			type : 'POST',
			data : {
				op : "check",
				un : element.val()
			},
			dataType : "text",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data, textStatus, XMLHttpRequest) {
				if (data == "error") {
					show_message(element, "error", "该用户名已存在");
					usrNmFlg = false;
				}
				else show_message(element, "ok", " ");
			}
		});
	}
};

var checkLoginName = function(element){
	element = $(element);
	var flag = checkRequired(element, "登录名");
	if (flag) flag = checkLength(element, "登录名", 1, 20);
	loginNmFlg = flag;
	
	if(flag){
		show_loading(element);
		$.ajax({
			url : "../servlet/PersonnelServlet",
			type : 'POST',
			data : {
				op : "check",
				ln : element.val()
			},
			dataType : "text",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data, textStatus, XMLHttpRequest) {
				if (data == "error") {
					show_message(element, "error", "该登录名已被使用");
					loginNmFlg = false;
				}
				else show_message(element, "ok", " ");
			}
		});
	}
};

var checkRequired = function(o, n) {
	if ( o.val() == null || o.val() == "" ) {
		show_message(o, "error", "请填写" + n);
		return false;
	} else {
		show_message(o, "ok", " ");
		return true;
	}
};

var checkLength = function( o, n, min, max ) {
	if ( o.val().length > max || o.val().length < min ) {
		if (min == 0) 
			show_message(o, "error", n + "的长度必须在" + max + "以内");
		else
			show_message(o, "error", n + "的长度必须在" + min + "和" + max + "之间");
		return false;
	} else {
		show_message(o, "ok", " ");
		return true;
	}
};

var show_message = function(element, type, msg){
	var el = "#" + element.attr("id") + "_msg";
	if (type === "error")
	{
		$(el).removeClass("loading");
		$(el).removeClass("ok");
		$(el).addClass("error");
		$(el).html(msg);
	} 
	else 
	{
		$(el).removeClass("loading");
		$(el).removeClass("error");
		$(el).addClass("ok");
		$(el).html(msg);
	}
};

var clear_message = function(list)
{
	for(var i = 0; i < list.length; i++)
	{
		var el = "#" + list[i] + "_msg";
		$(el).removeClass("loading");
		$(el).removeClass("error");
		$(el).removeClass("ok");
		$(el).html("");
	}
};

var show_loading = function(element)
{
	var el = "#" + element.attr("id") + "_msg";
	$(el).addClass("loading");
};

var show_result = function(XMLHttpRequest, textStatus){
	$( "#wait" ).dialog( "close" );
 	var msg = "";
 	if(textStatus == "error") msg = "请求出错！";
 	else if(textStatus == "timeout") msg = "请求超时！";
 	else if(textStatus == "parsererror") msg = "JSON数据格式有误！";
 	if (textStatus != "success") alert(msg);
};
