/**
 * 
 */
$.ajax({
		url : 'PdcaServlet?ran=Math.random()',
		data : {
			op : "5",
			cpone : $("#cponeid").text(),
			cptwo : $("#cptwoid").text(),
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
					var tr = "<tr data-tt-id='" + parientidone+ "'>" +
							"<td><span class='folder'>"+ data["cp_disorderone"][i]["node_name"]+ "</span></td>" +
							"<td>--</td>" +
							"</tr>";
					$('#cpone').append(tr);
				}
				var tr = "<tr data-tt-id='" + parientidone + '-' + i+ "' data-tt-parent-id='" + parientidone+ "'>" +
						"<td><span class='file'>"+ data["cp_disorderone"][i]["order_text"]+ "</span></td>" +
						"<td>"+ data["cp_disorderone"][i]["order_no"]+ "</td>" +
						"</tr>";
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
					tr = "<tr data-tt-id='" + parientidtwo+ "'>" +
							"<td><span class='folder'>"+ data["cp_disordertwo"][i]["node_name"]+ "</span></td>" +
							"<td>--</td>" +
							"</tr>";
					$('#cptwo').append(tr);
				}
				tr = "<tr data-tt-id='" + parientidtwo + '-' + i+ "' data-tt-parent-id='" + parientidtwo+ "'>" +
						"<td><span class='file'>"+ data["cp_disordertwo"][i]["order_text"]+ "</span></td>" +
						"<td>"+ data["cp_disordertwo"][i]["order_no"]+ "</td>" +
						"</tr>";
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
	});