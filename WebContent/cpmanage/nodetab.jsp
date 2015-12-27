<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript"><!--
var cp_id='<%=request.getParameter("cp_id")%>';
var trId='<%=request.getParameter("cp_node_id")%>';
var type = <%=request.getParameter("type")%>;

	
$(document).ready(function(){
	var ui1=true;
	var ui2=true;
	var ui3=true;
	var ui4=true;
	$("#frag-1").load("frag.jsp", {cp_id : cp_id,cp_node_id:trId,op:"frag-1"},hide );
	$("#tabs").tabs();
	$('#tabs').bind('tabsselect', function(event, ui) {
	onclickdoctorpoint_quanju=false;
	event.stopPropagation(); 
	if(ui.index==0){
		}else if(ui.index==1){
			  		 	
  		 	if(ui1){
  		 	 $("#wait").dialog("open");
    		 $("#frag-2").empty();
	    	 $("#frag-2").load("frag.jsp", {cp_id : cp_id,cp_node_id:trId,op:"frag-2"}, hide);
  		 	}
  		 	ui1=false;
	     }else if(ui.index==2){
	   		if(ui2){
		   		$("#wait").dialog("open");
		    	$("#frag-3").empty();
		    	$("#frag-3").load("frag.jsp", {cp_id : cp_id,cp_node_id:trId,op:"frag-3"},hide );
	    	}
	    	ui2=false;
	     }else if(ui.index==3){
		     if(ui3){
			   	 $("#wait").dialog("open");
			   	 $("#frag-4").empty();
			   	 $("#frag-4").load("frag.jsp", {cp_id : cp_id,cp_node_id:trId,op:"frag-4"}, hide);
		     }
		     ui3=false;
	     }else if(ui.index==4){
	    	  if(ui4){
	   	 $("#wait").dialog("open");
	   	 $("#frag-5").empty();
	  	 $("#frag-5").load("frag.jsp", {cp_id : cp_id,cp_node_id:trId,op:"frag-5"}, hide ); 
	    	  }
	    	  ui4=false;
    	 }else{
	    	alert("请选择左侧节点!");
	    	}  
   	}); 
	$("#order1").css("display","");
	$("#order2").css("display","none");
	//编辑拼音搜索
	$("#edit1").css("display","");
	$("#edit2").css("display","none");
	
});

--></script>
</head>
<body onload="showbefore()">

<div id="tabs"  style="height:auto">
 	           <ul>
 	             <li><a href="#frag-1">输入/输出节点</a></li>
 	             <li><a href="#frag-2">诊疗工作</a></li>
 	             <li><a href="#frag-3">重要医嘱</a></li>
 	             <li><a href="#frag-4">护理工作</a></li>
 	             <li><a href="#frag-5">家属工作</a></li>
  			</ul>
 	           <div id="frag-1" ></div>
 	           <div id="frag-2" > </div>
 	           <div id="frag-3" > </div>
 	           <div id="frag-4"></div>
 	           <div id="frag-5"></div>
</div>

</body>
</html>
