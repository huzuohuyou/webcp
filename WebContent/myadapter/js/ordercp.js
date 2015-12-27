/**
 * 医嘱执行频次对比脚本
 */
window.onload = function()
{	
	$.ajax({
		url : '../PdcaServlet?ran='+Math.random(),
		data : {
			op :"5"
		},
		dataType : 'json',
		error : function() {

		},
		success : function(data) {						
			var nodeflag = '-';
    		var parientid = 0;
    		for(var i=0;i<data["cp_orders"].length;i++)
   		    {	
    			var tr="";
    			if(nodeflag!=data["cp_orders"][i]["node_name"]){ 
    				parientid++;
    				tr ="<tr data-tt-id='"+parientid+"'><td>"+data["cp_orders"][i]["order_text"]+"</td><td>--</td><td>--</td></tr>";
    				$('#orderseqs').append(tr);             				
    			}
    			tr ="<tr data-tt-id='"+parientid+'-'+i+"' data-tt-parent-id='"+parientid+"'><td>"+data["cp_orders"][i]["order_text"]+"</td><td>"+data["cp_orders"][i]["order_no"]+"</td><td>"+data["cp_orders"][i]["mycount"]+"</td></tr>";            				
    			nodeflag=data["cp_orders"][i]["node_name"];
   				$('#orderseqs').append(tr);  
   		    }	   
		}
	})
}