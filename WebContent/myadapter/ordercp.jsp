<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <meta charset="utf-8">
    <title>jQuery treetable</title>
    <link rel="stylesheet" type="text/css" href="css/screen.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/jquery.treetable.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.treetable.theme.default1.css" />
  </head>
  <body>

      <table id="example-advanced">               
	  <thead>
          <tr>
            <th>医嘱名称</th>
            <th>医嘱编号</th>
            <th>执行次数</th>
          </tr>
        </thead>
        <tbody id='orderseqs'>
		 
        </tbody>
      </table>
    </div>


<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.treetable.js"></script>
    <script>
      $("#example-basic").treetable({ expandable: true });

      $("#example-basic-static").treetable();

      $("#example-basic-expandable").treetable({ expandable: true });

      $("#example-advanced").treetable({ expandable: true });

      // Highlight selected row
      $("#example-advanced tbody tr").mousedown(function() {
        $("tr.selected").removeClass("selected");
        $(this).addClass("selected");
      });

      // Drag & Drop Example Code
      $("#example-advanced .file, #example-advanced .folder").draggable({
        helper: "clone",
        opacity: .75,
        refreshPositions: true, // Performance?
        revert: "invalid",
        revertDuration: 300,
        scroll: true
      });

      $("#example-advanced .folder").each(function() {
        $(this).parents("tr").droppable({
          accept: ".file, .folder",
          drop: function(e, ui) {
            var droppedEl = ui.draggable.parents("tr");
            $("#example-advanced").treetable("move", droppedEl.data("ttId"), $(this).data("ttId"));
          },
          hoverClass: "accept",
          over: function(e, ui) {
            var droppedEl = ui.draggable.parents("tr");
            if(this != droppedEl[0] && !$(this).is(".expanded")) {
              $("#example-advanced").treetable("expandNode", $(this).data("ttId"));
            }
          }
        });
      });

      $("form#reveal").submit(function() {
        var nodeId = $("#revealNodeId").val()

        try {
          $("#example-advanced").treetable("reveal", nodeId);
        }
        catch(error) {
          alert(error.message);
        }

        return false;
      });
    </script>
  </body>
</html>