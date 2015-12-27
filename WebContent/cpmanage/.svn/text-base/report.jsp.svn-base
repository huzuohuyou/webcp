<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="../public/plugins/jquery/themes/cupertino/jquery-ui-1.8.11.custom.css"> 
<!-- 	<link rel="stylesheet" href="../public/plugins/jquery/themes/base/jquery.ui.all.css"> -->
	<script src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
	<script src="../public/plugins/jquery/jquery.autocomplete.js"></script>
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
	
	<script src="../public/plugins/jquery/jquery.autocomplete.js"></script>
	<link rel="stylesheet" href="../public/plugins/jquery/jquery.autocomplete.css">
	
	 <script type="text/javascript">
	 $(document).ready(function(){
  $("#dialog-cp").dialog({
		open:function(){ 
				
				}, 
		autoOpen: false,
		height: 300,
		width: 450,
		modal: true,
		title:'信息查询'
	/* 	buttons: {
	"添加": function() {
			
			$( this ).dialog( "close" );			
	},
	"取消": function() {	$( this ).dialog( "close" );}	
	} */
}); 
  $("#query").click(function(){
	  $("#dialog-cp").dialog("open");
  });
  $("#selectid").change(function(){
	  alert( $("#selectid").find("option:selected").text());
  });
  $("option").dblclick (function(){
	 
	  $("#dialog-cp").dialog("close");
  });
	  
 
 
     var options = {
            minChars: 0, //最少输入多少字符开始查询
            max: 500, //最多显示多少
            width: 550, //宽度
            multiple:true,
            matchContains: true,
            matchSubset:false,
            extraParams:{q:function(){return encodeURI($("#dialog_cp_py").val());},
        				code:function(){return $(":radio[name='radiobutton'][checked]").val();}   
           				}, //传递参数
            dataType: 'json',   //返回JSON格式
            cacheLength:0, //缓存的长度.即对从数据库中取到的结果集要缓存多少条记录.设成1为不缓存.Default: 10

       //     delay :11400,击键后激活autoComplete的延迟时间(单位毫秒).Default: 远程为400 本地10
           parse: function(data) { 
        	   data = eval(data);
		         var rows = [];   
		         for(var i=0; i<data.length; i++){ 
		            rows[rows.length] = { 
		            		 data:data[i].code+"--"+data[i].name +"--"+data[i].py,   
	    	                 value:data[i].code+"--"+data[i].name, 
	    	                 result:data[i].py 
		            };    
		          }   
		       return rows;   
		        },    
            
		       formatItem: function(row, i, n) {   
	            	 return row;
	           } //,
           //  formatMatch: function(row){return row.name;}, 
           //  formatResult: function(row){return row.name; } 
        };
        			var query;
		 	  $(":radio[name='radiobutton']").change(function(){
		 		 $("#dialog_cp_py").val("");
				 }); 
  
             $("#dialog_cp_py").autocomplete("../servlet/reportAuto",options);
             $("#dialog_cp_py").result(function(event, data, formatted) {//data 选中的行json对象. formatted是formatMatch返回的值.
                $("#dialog_cp_py").val(formatted);
                $("#dialog_cp_py").val(data);
                alert(data.id.length);
             
           
        });  
        
        
        
        
     //   ------------------------------------------------------------
/*         var opertion={
    	    	delay:1000,
    	    	 max: 12,    //列表里的条目数
    	         minChars: 0,   //自动完成激活之前填入的最小字符
    	         width: 400,     //提示的宽度，溢出隐藏
    	         scrollHeight: 300,   //提示的高度，溢出显示滚动条
    	         matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
    	         autoFill: false,    //自动填充
    	         parse: function(data) { 
    	        		data = eval(data);
    	          	   	var rows = [];   
    	             for(var i=0; i<data.length; i++){   
    	               rows[rows.length] = {   
    	            		   data:data[i].code+"--"+data[i].name +"--"+data[i].input,   
    	                 value:data[i].code+"--"+data[i].name,   
    	                 result:data[i].input 
    	                 };   }    
    	             return rows;   
    	              },   
    	           formatItem: function(row, i, n) {   
    	            	 return row;
    	           } 
    	              };
    
    	 $("#diagnosisPY").autocomplete("../servlet/auto?ops=py&op=diagnosis",opertion);
    	 $("#diagnosisWB").autocomplete("../servlet/auto?ops=wb&op=diagnosis",opertion);
    	 $("[name='diagnosisInput']").result(function(event, data, formatted){
    			formatted=formatted.split("--");
    		 $("#diagnosisCode").attr("value",formatted[1]);
    		$("#diagnosisName").attr("value",formatted[0]);
    }); */

});  
</script> 
</head>
<body>
<% 
    	//String cp_id=request.getParameter("cp_id");
    	//String cp_node_id=request.getParameter("cp_node_id");
    	//String op=request.getParameter("op");
	//	DatabaseClass db = new DatabaseClass(PropertiesUtil.get(PropertiesUtil.DATABASE_URL));
	//	String sql="select cp_code,cp_id,cp_name,input_code_py,input_code_wb from dcp_master" ; 
		String sql=null;
  
   	   
		
    %>
<div id="dialog-cp" style="background: #FFF;font-size:14px;">
	<form id="form1" name="form1" method="post" action="">
	  <table width="360" height="184" border="0" align="center">
	    
		 <tr>
		   <td width="354" height="50" align="center"><input type="radio" name="radiobutton" value="cpcode">
路径编码
  <input type="radio" name="radiobutton" value="cpname">
路径名称
<input name="radiobutton" type="radio" value="cppy" checked>
拼 音 码
<input type="radio" name="radiobutton" value="cpwb">
五 笔 码</td>
	    </tr>
		 <tr>
	      <td height="50" align="center"><input name="masterInput" type="text" id="dialog_cp_py" value="" size="40">
           <input type="reset" name="Submit" value="清除"></td>
         </tr>
		  <tr>
	      <td height="50"><input type="hidden" name="hiddenField"></td>
         </tr>
		 <tr>
		 <td align="center">
	<!--	  <select name="select" size="10" id="selectid"> -->
		  <% // try{
		   	  //  DataSetClass ds=db.FunGetDataSetBySQL(sql);
		   	   // if(ds.FunGetDataAsStringById(0,0)!=""){
		  	/* 	for (int i = 0; i < ds.FunGetRowCount(); i++) {
				String cp_code = ds.FunGetDataAsStringById(i,0);
				String cp_id = ds.FunGetDataAsStringById(i,1);
				String cp_name = ds.FunGetDataAsStringById(i,2);
				String input_code_py = ds.FunGetDataAsStringById(i,3);
				String input_code_wb = ds.FunGetDataAsStringById(i,4);
				System.out.println(cp_id); */
				%>
              <%--   <option value='<%=cp_id %>' onDblClick="alert('aaaa');"><%=cp_name %></option> --%>
                
                <%/* }
		  		//}
		   	   }catch(Exception e){
                	e.printStackTrace();
                	
                } */%>
             <!--  </select>	 -->	 </td>
		 </tr>
      </table>
	</form>
</div>
<table height="100%" width="100%" border="1">
  <tr >
    <td ><div id="div11" align="left" style="border-style: Double; border-width: 3pt; border-color: #C1C1C1; height: 100px; width: auto; overflow-y: scroll">
      <table width="100%" border="0">
        <tr>
          <td width="81%"><form name="form2" method="post" action="">
           <div id="cpname"></div>
          </form>
          </td>
          <td width="19%"><input type="button" value="快速查询" id="query"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
    </div></td>
  </tr>
  

  <tr>
    <td >
    <div id="div1" style="height: 500px; width: auto;">
	</div></td>
  </tr>
</table>
<script>


$('#div1').html('<iframe src=" http://192.168.100.144:8080/DcpLocal/version/viewcp.jsp?cp_id=1" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>');


</script>

</body>
</html>