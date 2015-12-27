<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>变异退出信息输入框</title>
	<link rel="stylesheet" href="../public/plugins/jquery/themes/cupertino/jquery-ui-1.8.11.custom.css"> 
	<script src="../public/plugins/jquery/jquery-1.7.1.min.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.core.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.position.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.resizable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$( "#addVariationExit" ).dialog({//变异退出信息输入对话框
				autoOpen: false,
				height: 370,
				width: 430,
				modal: true,
				open: function(){ 
					$("#variationExit").load("../Variation",{variation_type:"variationExit"});
				},
				buttons: {
					"确定": function() {
						var variationCode_js=$("select[name='variationExit']").val();
						var text=$("#variationExitText").attr("value");
						if(variationCode_js=="0"){
							alert("请选择变异退出类型！");
						}else if(text.replace(/\s+/g, "")==""){
							alert("请输入变异原因！");
						}else{
							$.post("../ProcessingVariation",{op : "variation",variationCode:variationCode_js,text:encodeURI(text)},function(result){
								if(result>0){
									alert("添加变异退出原因成功！");
									window.opener=null;//禁止关闭窗口的提示
									window.close();//自动关闭窗口
								}else{
									alert("添加变异退出原因失败！");
									window.opener=null;//禁止关闭窗口的提示
									window.close();//自动关闭窗口
								}
							},"text");
						}
					},
					"取消": function() {
						$( this ).dialog( "close" );
						window.opener=null;//禁止关闭窗口的提示
						window.close();//自动关闭窗口
					}
				},
				close: function() {
					window.opener=null;//禁止关闭窗口的提示
					window.close();//自动关闭窗口
				}
			});
			$( "#addVariationExit" ).dialog("open");
		});
	</script>
</head>
<body>
<div id="addVariationExit" style="background: #FFF">

	<H3 align="center">路径变异登记</H3>
	<table width="100%" border="0" cellpadding="0" cellspacing="1" >
      <tr>
        <td width="28%" height="26" nowrap="nowrap"><div align="right">
          退出路径&nbsp;<br />
          变异类型:
        </div></td>
        <td width="72%">
        <div id="variationExit">      
       
        </div>
        </td>
      </tr>
      <tr>
        <td nowrap="nowrap"><div align="right">变异原因:</div></td>
        <td><font style="font-size: 14px;">
          <textarea name="textarea" rows="8" cols="35" id="variationExitText"></textarea>
        </font></td>
      </tr>
    </table>
</div>
</body>
</html>