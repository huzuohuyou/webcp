<%@page import="com.goodwillcis.lcp.model.cpmanage.NodeFamily"%>
<%@page import="com.goodwillcis.lcp.model.cpmanage.NodeOrder"%>
<%@page import="com.goodwillcis.lcp.model.cpmanage.NodeNurse"%>
<%@page import="com.goodwillcis.lcp.model.cpmanage.NodeDoctor"%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%! String cp_node_type=""; %>

<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

 
  <% 
  	final int HOSPITALID=LcpUtil.getHospitalID();
  	String cp_id=request.getParameter("cp_id");
  	String cp_node_id=request.getParameter("cp_node_id");
  	String op=request.getParameter("op");
	DatabaseClass db = LcpUtil.getDatabaseClass();
	 String sql21="select cp_node_type from lcp_master_node where cp_id="+cp_id+" and cp_node_id="+cp_node_id;
	  DataSetClass ds3=db.FunGetDataSetBySQL(sql21);
	  if(ds3.FunGetDataAsStringById(0,0)!=""){
		  cp_node_type=ds3.FunGetDataAsStringById(0,0);
	  }
	if("frag-1".equals(op)){
		NodeOrder order=new NodeOrder();
  %>

<table border="1" width="100%" height="100%">

  <tr>
    <th width="100%"  scope="col" height="180" valign="top" align="left">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr height="25"  bgcolor="d3eaef"> 
        <td width="94%" align="center" class="STYLE10">输入节点名称</td>
      </tr>
       <tbody id="">
       <%  String sql="SELECT r.cp_node_id, r.cp_next_node_id , n.cp_node_name"+
        	 " FROM LCP_NODE_RELATE r, LCP_MASTER_NODE n"+
        	"  where r.SYS_IS_DEL=0 and r.cp_id = "+cp_id+
        	   " and r.cp_next_node_id = "+cp_node_id+
        	   " and r.cp_node_id = n.cp_node_id"+
        	    " and r.cp_id = n.cp_id" +
        	    " and r.hospital_id = n.hospital_id" +
        	    " and r.hospital_id = "+HOSPITALID ; 
       try{
        	    DataSetClass ds1=db.FunGetDataSetBySQL(sql);
        	    if(ds1.FunGetDataAsStringById(0,0)!=""){
        	    for (int i = 0; i < ds1.FunGetRowCount(); i++) {
					String cp_node_name = ds1.FunGetDataAsStringById(i,2);
        	    %>
        	    
    <tr bgcolor="#FFFFFF" onmouseover='changeColor(this)' onMouseOut="recoverColor(this)" class="STYLE19" name="lcp_NODE_tr" id="tr">
	<td height="20" align="center" class="STYLE10"><%= cp_node_name%></td>
      </tr>
      <%} }}  catch(Exception e){
                	out.print("<script  type='text/javascript'> alert('网络连接失败!请重试')</script>");
       }%>
      </tbody>
    </table></th> 
  </tr>
  <tr>
    <th width="100%" height="25"  align="center" valign="top" scope="row"><input name="button_one" type="button" value="添加输出节点" onclick='addOutNode()'/>
      &nbsp;
  <input name="button_one" type="button" value="移除输出节点"  onClick='delTrs("lcp_node_relate","cp_next_node_id")'/>    </th></tr>
  <tr>
    <th height="120" align="left" valign="top" scope="row">
     <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
     <tr  bgcolor="d3eaef" height="25">
        <td width="6%" ></td>
        <td width="94%" align="center" class="STYLE10">输出节点名称</td>
      </tr>
       <tbody id="lcp_node_relate_tbody"> 
        <%  String sql1="SELECT r.cp_node_id cp_node_id, r.cp_next_node_id cp_next_node_id, n.cp_node_name cp_node_name,n.cp_node_type cp_node_type "+
        	 " FROM LCP_NODE_RELATE r, LCP_MASTER_NODE n"+
        	"  where r.SYS_IS_DEL=0 and r.cp_id = "+cp_id+
        	   " and r.cp_node_id = "+cp_node_id+
        	   " and r.cp_next_node_id = n.cp_node_id"+
        	    " and r.cp_id = n.cp_id" +
        	    " and r.hospital_id = n.hospital_id" +
        	    " and r.hospital_id = "+HOSPITALID ; 
    
      		try{
        	    DataSetClass ds2=db.FunGetDataSetBySQL(sql1);
        	    if(ds2.FunGetDataAsStringById(0,0)!=""){
        	    for (int i = 0; i < ds2.FunGetRowCount(); i++) {
					Number cp_next_node_id=ds2.FunGetDataAsNumberById(i,1);
					String cp_node_name2 = ds2.FunGetDataAsStringById(i,2);
        	    %>
        <tr bgcolor="#FFFFFF" onmouseover='changeColor(this)' onMouseOut="recoverColor(this)" class="STYLE10" name="lcp_node_relate_tr" id="tr<%=cp_next_node_id%>">
			<td align="center"><input type="checkbox" name="lcp_node_relate_checkbox" id="tr<%=cp_next_node_id%>" /></td>
	        <td height="20"  align="center"><%=cp_node_name2 %></td>
        </tr>
      <%} }}  catch(Exception e){
               out.print("<script  type='text/javascript'> alert('网络连接失败!请重试')</script>");
	 }%>
      </tbody>
    </table></th>
  </tr>
  </table>
    
 <% } else if("frag-2".equals(op)){ 
	 NodeDoctor doctor=new NodeDoctor();
		  if("1".equals(cp_node_type)){
 %>
 <table border="1" width="100%" >
   <tr>
     <th width="100%"  scope="col" height="150" valign="top" align="left"><div style=" height:150px;overflow-y:auto;overflow-x:hidden">
       <table width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
         <tr  height="25" bgcolor="d3eaef"  class="STYLE10">
          <td   width="5%" align="center">编码</td>
           <td   width="80%" align="center">诊疗工作</td>
            <td   width="15%" align="center">必做项</td>
         </tr>
         <tbody id="doctor_point_table">
           <%=doctor.funGetPointTable(cp_id,cp_node_id)%>
         </tbody>
       </table>
     </div></th>
   </tr>
   <tr>
     <th width="100%" height="18" align="center" valign="top" scope="row"><input name="input" type="button"  value="添加" onclick="dialogThreeCol('lcp_node_doctor_point');" />
     <input name="input" type="button"  value="删除" onclick="deldoctorpoint();" />
     <input name="input" type="button"  value="编辑" onclick="editDialogThreeCol('lcp_node_doctor_point');" /></th>
   </tr>
   <tr>
     <th height="150" align="left" valign="top" scope="row"><div style="font-size: 13px;">
       
       <div style=" height:150px;overflow-y:auto;overflow-x:hidden">
         <table width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
           <tr  height="25" bgcolor="d3eaef" align="center"  class="STYLE10">
             <td width="5%"  >&nbsp;</td>
             <td width="60%" >诊疗项目</td>
             <td width="25%" >诊疗编码</td>
             <td width="15%" >必选项</td>
           </tr>
           <tbody id="doctor_item_table">
           </tbody>
         </table>
       </div>
       <div align="center"></div>
     </div></th>
   </tr>
   <tr>
     <th width="100%" height="18" align="center" valign="top" scope="row"><input name="input2" type="button"  value="添加" onclick="dialogThreeCol('lcp_node_doctor_item');" />
     <input name="input2" type="button" value="删除" onclick="deldoctoritem();" />
     <!-- <input name="input2" type="button" value="编辑" onclick="editDialogThreeCol('lcp_node_doctor_item');" /> --></th>
 </table>
 <%   }else{%>
 	<tr>
	<th width="100%" height="18" align="left" valign="top" scope="row">
	内容不可编辑！
	</th>
   </tr>
 <%}} else if("frag-3".equals(op)){
	  NodeOrder order=new NodeOrder();
	  if("1".equals(cp_node_type)){
		  
  %>
   <table border="1" width="100%" >
  <tr>
     <th width="100%"  scope="col" height="180" valign="top" align="left"><div style=" height:180px;overflow-y:auto;overflow-x:hidden">
       <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
       
          <tr height="25" class="STYLE10" bgcolor="d3eaef" align="center" >
		  <td width="5%"  >&nbsp;</td>
		  <td width="10%" >长期</td>
		  <td width="75%" >医嘱内容</td>
		  <td width="10%" >必做</td>
		  </tr>
         
         <tbody id="order_point_table">
           <%=order.funGetPointTable(cp_id,cp_node_id,null)%>
         </tbody>
       </table>
     </div></th>
   </tr>
   <tr>
     <th width="100%" height="18" align="center" valign="top" scope="row">
     
     <input name="input6" type="button"  value="添加一级" onclick="addOrderPoint(1);" />
     <input name="input6" type="button"  value="添加二级" onclick="addOrderPoint(2);" />
     <input name="input6" type="button" value="删除一级" onclick="delOrderPoint(1);" />
     <input name="input6" type="button" value="删除二级" onclick="delOrderPoint(2);" />
     <input name="input7" type="button" value="复制二级" onclick="copy();" />
     <input name="input6" type="button" value="编辑二级" onclick="editOrderPoint();" />
     <input name="input6" type="button" value="清空二级" onclick="clearOrderPoint();" />
     <input name="input5" type="button" value="预览" onclick="showOrder();" /> 
     </th>
   </tr>
   <tr>
     <th height="180" align="left" valign="top" scope="row"><div style=" height:180px;overflow-y:auto;overflow-x:hidden">
       <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
   
           <tr height="25" class="STYLE10" bgcolor="d3eaef"  align="center">
		  <td width="4%"  ></td>
		 <td width="5%"  >序号</td>
		  <td width="5%"  >组</td>
		  <td width="5%"  >长期</td>
		 <!--  <td width="5%"  >类别</td> -->

		 <td width="5%"  >类别</td><!-- ----------------------------》  -->
		  <td width="*" >医嘱具体内容</td>
		  <td width="15%"  >规格</td>
		  <td width="5%"  >必选</td>
		  <td width="5%"  >默认</td>
		  <td width="10%" >领量</td>
           <td width="8%" >频次</td>
		  <td width="11%" >途径</td>
		  </tr>
         
         <tbody id="order_item_table">
         </tbody>
       </table>
     </div></th>
   </tr>
   <tr>
     <th width="100%" height="18" align="center" valign="top" scope="row"><input name="input3" type="button"  value="添加" onclick="addOrderItem();showbefore()" />
      <input name="input3" type="button" value="编辑" onclick="editOrderItem();" />
     <input name="input3" type="button" value="删除" onclick="delOrder();" />
      <input name="input5" type="button" value="存组" onclick="addOrderGroup();" />
      <input name="input5" type="button" value="撤组" onclick="cancelGroup();" />
      <input name="input5" type="button" value="复制" onclick="copySan();" />
      <input name="input5" type="button" value="移动" onclick="selectSan();" />
      <input name="input6" type="button" value="↑" onclick="up_exchange_line();" />
      <input name="input7" type="button" value="↓" onclick="down();">
      <!--<input name="input5" type="button" value="存组" onclick="addOrderGroup();" />
      <!-- <input name="input5" type="button" value="删组" onclick="delOrderGroup();" />--></th>
   </tr>
    </table>
   <%}else{%>
	<tr>
	<th width="100%" height="18" align="left" valign="top" scope="row">
	内容不可编辑！
	</th>
   </tr>
   <%}%>
   


   <%}else if("frag-4".equals(op)){
NodeNurse nurse=new NodeNurse();
if("1".equals(cp_node_type)){
%>

 <table border="1" width="100%" >
   <tr>
     <th width="100%"  scope="col" height="150" valign="top" align="left"><div style=" height:150px;overflow-y:auto">
       <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
         <tr  height="20" bgcolor="d3eaef"  class="STYLE10">
         <td  width="5%" align="center">编码</td>
           <td  width="80%" align="center">护理工作</td>
           <td width="15%" align="center">必做项</td>
         </tr>
         <tbody id="nurse_point_table">
           <%=nurse.funGetPointTable(cp_id,cp_node_id)%>
         </tbody>
       </table>
     </div></th>
   </tr>
   <tr>
     <th width="100%" height="18" align="center" valign="top" scope="row"><input name="input4" type="button"  value="添加" onclick="dialogThreeCol('lcp_node_nurse_point');" />
     <input name="input4" type="button" value="删除" onclick="delnursepoint();" />
     <input name="input4" type="button" value="编辑" onclick="editDialogThreeCol('lcp_node_nurse_point');" /></th>
   </tr>
   <tr>
     <th height="150" align="left" valign="top" scope="row"><div style=" height:150px;overflow-y:auto">
       <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
         <tr height="20" bgcolor="d3eaef" class="STYLE10" align="center">
             <td width="5%"  >&nbsp;</td>
             <td width="60%" >护理项目</td>
             <td width="25%" >护理编码</td>
             <td width="15%" >必选项</td>
         </tr>
         <tbody id="nurse_item_table">
         </tbody>
       </table>
     </div></th>
   </tr>
   <tr>
     <!-- <th width="100%" height="18" align="center" valign="top" scope="row"><input name="input5" type="button"  value="添加" onclick="dialogThreeCol('lcp_node_nurse_item');" />
     <input name="input5" type="button" value="删除" onclick="delnurseitem();" />
    	
     
     </th> -->
   </tr>
 </table>
 <%}else{%>
 	<tr>
	<th width="100%" height="18" align="left" valign="top" scope="row">
	内容不可编辑！
	</th>
   </tr>
 <%}} else if("frag-5".equals(op)){
	NodeFamily family=new NodeFamily();
	if("1".equals(cp_node_type)){
	%>
<table border="1" width="100%"  >
  <tr>
    <th width="100%" scope="col" height="300" valign="top">
    <table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce"  >
        <tr bgcolor="d3eaef" class="STYLE10" align="center" height="20">
        <td width="5%"  >编码</td>
          <td width="80%" >家属工作项内容</td>
          <td width="15%"  >必做项</td>
        </tr>
        <tbody id="lcp_node_family_point_tbody">
			<%=family.funGetPointTable(cp_id,cp_node_id) %>
        </tbody>
      </table></th>
  </tr>
  <tr>
    <th width="100%" height="20" valign="top" scope="row">
       <input name="button_one11" type="button" value="添加"  style="height:25px; width:auto; font-size:12px" onClick='addfamily()'/>&emsp;
       <input name="button_one11" type="button" value="删除"  style="height:25px; width:auto; font-size:12px" onClick='delfamily()'/>
       &nbsp;</th>
  </tr>
</table>
<%}else{%>
 	<tr>
	<th width="100%" height="18" align="left" valign="top" scope="row">
	内容不可编辑！
	</th>
   </tr>
<%}}%>