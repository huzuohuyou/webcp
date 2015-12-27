<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<script type="text/javascript">
	var cp_id='<%=request.getParameter("cp_id")%>';
	var trId='<%=request.getParameter("cp_node_id")%>';
	$(function() {
		var ui1=true;
		var ui2=true;
		var ui3=true;
		var ui4=true;
		
		$("#frag-1").load("../version/frag.jsp", {cp_id : cp_id,cp_node_id:trId,ops:"frag-1"});
		$("#tabs").tabs();
	 	 $('#tabs').bind('tabsselect', function(event, ui) {
	 		event.stopPropagation(); 
		    	if(ui.index==0){
		    		$("#wait").hide();
		    	 }else if(ui.index==1){
		    		 if(ui1){
		    		 $("#wait").dialog("open");
			    		$("#frag-2").load("../version/frag1.jsp", {cp_id : cp_id,cp_node_id:trId,ops:"frag-2"},hide);
		    		 }
		   		 	ui1=false;
			     }else if(ui.index==2){
			    	 if(ui2){
			    	 $("#wait").dialog("open");
			    		$("#frag-3").load("../version/frag1.jsp", {cp_id : cp_id,cp_node_id:trId,ops:"frag-4"},hide);
			    	 }
			  		 	ui2=false;
			     }else if(ui.index==3){
			    	 if(ui3){
				     $("#wait").dialog("open");
			    		$("#frag-4").load("../version/frag1.jsp", {cp_id : cp_id,cp_node_id:trId,ops:"frag-3"},hide);
			    	 }
			  		 	ui3=false;
			     }else if(ui.index==4){
			    	 if(ui4){
			    	 $("#wait").dialog("open");
			    		$("#frag-5").load("../version/frag1.jsp", {cp_id : cp_id,cp_node_id:trId,ops:"frag-5"},hide); 
			    	 }
			  		 	ui4=false;
			    		
		    	 }else{
			    	alert("请选择左侧节点!");
			    	}
		    	}); 
	});
	</script>
</head>
<body>
<div id="tabs"  style="height:auto">
 	           <ul>
 	             <li><a href="#frag-1"><span style="font-size:11px">输入/输出节点</span></a></li>
 	             <li><a href="#frag-2"><span style="font-size:11px">诊疗工作</span></a></li>
 	             <li><a href="#frag-3"><span style="font-size:11px">护理工作</span></a></li>
 	             <li><a href="#frag-4"><span style="font-size:11px">重要医嘱</span></a></li>
 	             <li><a href="#frag-5"><span style="font-size:11px">家属工作</span></a></li>
 	             </ul>
 	           <div id="frag-1" style="overflow-y:scroll;overflow-x:hidden; width:100%;height:350px;"></div>
 	           <div id="frag-2" style="overflow-y:scroll;overflow-x:hidden; width:100%;height:350px;"> </div>
 	           <div id="frag-3" style="overflow-y:scroll;overflow-x:hidden; width:100%;height:350px;"> </div>
 	           <div id="frag-4"  style="overflow-y:scroll;overflow-x:hidden; width:100%;height:350px;"></div>
 	           <div id="frag-5" style="overflow-y:scroll;overflow-x:hidden; width:100%;height:350px;"></div>
 	           </div>
</body>
</html>