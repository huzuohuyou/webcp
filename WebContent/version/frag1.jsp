<!-- 
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：frag.jsp    
// 文件功能描述：处理显示    路径定义信息查看中的信息
//
// 创建人：潘状
// 创建日期：2011-7-25
// 修改日期:2011-7-26
//
//----------------------------------------------------------------*/ 
-->
<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.PropertiesUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	
    final int HOSPITALID=LcpUtil.getHospitalID();
	DatabaseClass db = LcpUtil.getDatabaseClass(); 
	String cp_id = request.getParameter("cp_id");
	

 //路径制定标签中的所有内容  包括 输入输出节点/诊疗工作/护理工作/重要医嘱/家属工作
			String ops=request.getParameter("ops");
			String cp_node_id=request.getParameter("cp_node_id");
			if("frag-1".equals(ops)){//输入输出节点模块
    %>
<table border="1" width="100%" height="100%">
  <tr>
    <th width="100%"  scope="col" height="186" valign="top" align="left">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
     <tbody id="">
      <tr>
        <td width="94%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">输入节点名称</div></td>
      </tr>
       <%  String sql="SELECT r.cp_node_id, r.cp_next_node_id , n.cp_node_name"+
        	 " FROM LCP_NODE_RELATE r, LCP_MASTER_NODE n"+
        	"  where r.SYS_IS_DEL=0 and r.cp_id = "+cp_id+
        	   " and r.cp_next_node_id = "+cp_node_id+
        	   " and r.cp_node_id = n.cp_node_id"+
        	   " and r.HOSPITAL_ID="+HOSPITALID+
        	    " and r.cp_id = n.cp_id" ; 
       try{
        	    DataSetClass ds1=db.FunGetDataSetBySQL(sql);
        	    if(ds1.FunGetDataAsStringById(0,0)!=""){
        	    for (int i = 0; i < ds1.FunGetRowCount(); i++) {
					String cp_node_name = ds1.FunGetDataAsStringById(i,2);
        	    %>
        	    
    <tr bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" class="STYLE19"  id="tr">
	<td height="20" bgcolor="#FFFFFF" class="STYLE10" align="center"><%= cp_node_name%></td>
      </tr>
      
      <%} }}  catch(Exception e)
      				{out.print("<script  type='text/javascript'> alert('网络连接失败!请重试')</script>");
                }%>
      </tbody>
    </table></th> 
  </tr>
 
  <tr>
    <th height="213" align="left" valign="top" scope="row">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
   		 <tbody id="lcp_node_relate_tbody">
	      <tr>
	        <td width="94%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">输出节点名称</div></td>
	      </tr>
     
     <%  String sql1="SELECT r.cp_node_id cp_node_id, r.cp_next_node_id cp_next_node_id, n.cp_node_name cp_node_name"+
				     	 " FROM LCP_NODE_RELATE r, LCP_MASTER_NODE n where r.SYS_IS_DEL=0 and r.cp_id = "+cp_id+
				     	   " and r.cp_node_id = "+cp_node_id+" and r.HOSPITAL_ID="+HOSPITALID+
				     	   " and r.cp_next_node_id = n.cp_node_id and r.cp_id = n.cp_id" ; 
   		try{
     	    DataSetClass ds2=db.FunGetDataSetBySQL(sql1);
     	    if(ds2.FunGetDataAsStringById(0,0)!=""){
     	   	 for (int i = 0; i < ds2.FunGetRowCount(); i++) {
				Number cpNextNodeId=ds2.FunGetDataAsNumberById(i,1);
				String cpNodeName2 = ds2.FunGetDataAsStringById(i,2);
     	    %>
      
        <tr bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)" class="STYLE19" id="tr<%=cpNextNodeId%>">
		<td height="21" align="center" class="STYLE10"><%=cpNodeName2 %></td>
     	</tr>
      
      <%} }}  catch(Exception e){ System.out.println(sql);
                	out.print("<script  type='text/javascript'> alert('网络连接失败!请重试')</script>");
      }%>
      </tbody>
    </table></th>
  </tr>
</table>
    
    <% } else if("frag-2".equals(ops)){// 诊疗工作内容 %>
    
<table border="1" width="100%" height="100%">
  <tr>
    <th width="100%" scope="col" height="400" valign="top">
    <table width="100%"   border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="example" >
    	<thead>
          <tr>
            <th bgcolor="d3eaef"></th>
            <th height="20" colspan="4" bgcolor="d3eaef" class="STYLE10">诊疗工作内容</th>
          </tr>
          <tr>
          	<th width="3%" bgcolor="d3eaef"></th>
            <th width="32%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">诊疗项目</div></th>
            <th width="21%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">项目编码</div></th>
            <th width="23%" align="center" bgcolor="d3eaef" class="STYLE10">自动项目</th>
            <th width="21%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">对照本地编码</div></th>
          </tr>
       </thead>   
       <tbody id="lcp_node_doctor_tbody">
          <% 	 
          String sql="select CP_NODE_DOCTOR_ID,CP_NODE_DOCTOR_TEXT,NEED_ITEM,AUTO_ITEM from lcp_node_doctor_point t "
       					+  " where SYS_IS_DEL=0 and  t.cp_id="+cp_id+" and t.cp_node_id="+cp_node_id+" and HOSPITAL_ID="+HOSPITALID;
     	  String itemSql="select cp_node_doctor_item_id,cp_node_doctor_text,need_item,auto_item,doctor_no from lcp_node_doctor_item "
     	  			+"where sys_is_del=0 and cp_id="+cp_id+" and cp_node_id="+cp_node_id+" and HOSPITAL_ID="+HOSPITALID+" and CP_NODE_DOCTOR_ID=";
          try{ 
         	  DataSetClass ds=db.FunGetDataSetBySQL(sql);
          	  if(ds.FunGetDataAsStringById(0,0)!=""){
          		for (int i = 0; i < ds.FunGetRowCount(); i++) {
					String cpNodeDoctorId=ds.FunGetDataAsStringByColName(i,"CP_NODE_DOCTOR_ID");
					String needItem=ds.FunGetDataAsStringByColName(i,"NEED_ITEM");
					String cpNodeDoctorText = ds.FunGetDataAsStringByColName(i, "CP_NODE_DOCTOR_TEXT");
					DataSetClass ds1=db.FunGetDataSetBySQL(itemSql+cpNodeDoctorId);
					
					if("1".equals(needItem)){needItem="必做项";
						}else { needItem="可选项";}
					
					%>
					
	<tr bgcolor="#FFFFFF"  onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)"  id="node-<%= cpNodeDoctorId%>">
			<td></td>
			<td colspan="4" class="STYLE10" align="left" >&nbsp;<%= cpNodeDoctorText%></td>
          </tr>
					
	<%if(ds1.FunGetDataAsStringById(0,0)!=""){
			for (int j = 0; j < ds1.FunGetRowCount(); j++) {
				String cpNodeDoctorItemId=ds1.FunGetDataAsStringByColName(j,"CP_NODE_DOCTOR_ITEM_ID");
				String doctorText=ds1.FunGetDataAsStringByColName(j,"CP_NODE_DOCTOR_TEXT");
				String needItem1 = ds1.FunGetDataAsStringByColName(j, "NEED_ITEM");
				String autoItem = ds1.FunGetDataAsStringByColName(j, "AUTO_ITEM");
				String doctorNo = ds1.FunGetDataAsStringByColName(j, "DOCTOR_NO");
				if("0".equals(autoItem)){
					autoItem="自动";
				}else if("1".equals(autoItem)){
					autoItem="手动";
				}
				String matchSql="select local_code,match_type from lcp_match_doctor where sys_is_del=0 and doctor_code="+doctorNo+" and HOSPITAL_ID="+HOSPITALID;
				
				DataSetClass matchDs=db.FunGetDataSetBySQL(matchSql); 
				String localCodeTemp="";
				if(matchDs.FunGetDataAsStringById(0,0)!=""){
					for(int s=0;s<matchDs.FunGetRowCount();s++){
						String localCode=matchDs.FunGetDataAsStringByColName(s,"LOCAL_CODE");
						if(s<matchDs.FunGetRowCount()-1){
							localCodeTemp=localCodeTemp+localCode+"、";
						}else {
							localCodeTemp+=localCode;
						}
					}
				}
			%>
	<tr class="child-of-node-<%= cpNodeDoctorId%>" bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)"   id="<%=cpNodeDoctorItemId%>">
		<td></td>
		<td align="left" 	class="STYLE10">&nbsp;&nbsp;&nbsp;&nbsp;<%= doctorText%></td>
        <td align="center" class="STYLE10"><%= doctorNo%></td>
        <td align="center" class="STYLE10"><%= autoItem%></td>
        <td align="center" class="STYLE10"><%= localCodeTemp%></td>
     </tr>					
		<%	
					}}
 	    } }}  catch(Exception e){ 
                	out.print("<script  type='text/javascript'> alert('网络连接失败!请重试')</script>");
                }%>
        </tbody>
      </table></th>
  </tr>
</table>

	<% } else if("frag-3".equals(ops)){%>

<table border="1" width="100%" height="100%" >
  <tr>
    <th width="100%" scope="col" height="389" valign="top">
    <table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="frag-3" >
       <thead>
		<tr>
		  <th bgcolor="d3eaef" class="STYLE10">&nbsp;</th>
		  <th height="19" colspan="2" align="center" bgcolor="d3eaef" class="STYLE10">医嘱工作内容</th>
		  <!-- <th width="10%" rowspan="2" align="center" bgcolor="d3eaef" class="STYLE10">必做项目</th> -->
		  <th colspan="2" align="center" bgcolor="d3eaef" class="STYLE10">&nbsp;</th>
		  </tr>
		<tr>
          <th width="4%" bgcolor="d3eaef" class="STYLE10">&nbsp;</th>
          <th width="28%" align="center" height="19" bgcolor="d3eaef" class="STYLE10" colspan="2">医嘱项目</th>
          <th width="10%" rowspan="2" align="center" bgcolor="d3eaef" class="STYLE10">医嘱类型</th>
          <!-- <th width="10%" align="center" height="19" bgcolor="d3eaef" class="STYLE10">项目编码</th> -->
          <!--<th width="13%" align="center" bgcolor="d3eaef" class="STYLE10">自动项目</th>
          <th width="16%" align="center" bgcolor="d3eaef" class="STYLE10">对照本地编码</th> -->
        </tr>
        </thead>
        <tbody id="lcp_node_order_tbody">
       
          <% 
          
          String sql="select CP_NODE_ORDER_ID,CP_NODE_ORDER_TEXT,NEED_ITEM,AUTO_ITEM,ORDER_KIND,continue_order_id from lcp_node_order_point  "
					+  " where SYS_IS_DEL=0 and  cp_id="+cp_id+" and cp_node_id="+cp_node_id+" and HOSPITAL_ID="+HOSPITALID+" and continue_order_id = CP_NODE_ORDER_ID";
          String itemSql="select CP_NODE_ORDER_ID,CP_NODE_ORDER_TEXT,NEED_ITEM,AUTO_ITEM,ORDER_KIND,continue_order_id from lcp_node_order_point  "
				+  " where SYS_IS_DEL=0 and  cp_id="+cp_id+" and cp_node_id="+cp_node_id+" and HOSPITAL_ID="+HOSPITALID+" and continue_order_id <> CP_NODE_ORDER_ID and continue_order_id=";
 					
				try{ 
					DataSetClass ds=db.FunGetDataSetBySQL(sql);
					if(ds.FunGetDataAsStringById(0,0)!=""){
						for (int i = 0; i < ds.FunGetRowCount(); i++) {
							String cpNodeOrderId=ds.FunGetDataAsStringByColName(i,"CP_NODE_ORDER_ID");
							String needItem=ds.FunGetDataAsStringByColName(i,"NEED_ITEM");
							String cpNodeOrderText = ds.FunGetDataAsStringByColName(i, "CP_NODE_ORDER_TEXT");
							String autoItem = ds.FunGetDataAsStringByColName(i, "AUTO_ITEM");
							String kind=ds.FunGetDataAsStringByColName(i,"ORDER_KIND");
							String continueOrderID=ds.FunGetDataAsStringByColName(i,"CONTINUE_ORDER_ID");
							
							/* 
							if("1".equals(needItem)){needItem="必做";
								}else { needItem="可做";}
							
							String orderType=""; 
			      
							if("0".equals(kind)){
								orderType="临时医嘱";
							}else if("1".equals(kind)){
								orderType="长期医嘱";
							}else if("2".equals(kind)){
								orderType="出院医嘱";
							}
							*/
 	     %>
 	     
         <tr bgcolor="#d5f4fe"   id="node-<%= cpNodeOrderId%>">
			<td align="center" class="STYLE10" >一级</td>
           	<td  colspan="2" align="left" class="STYLE10"><%=cpNodeOrderText %></td>
          	<td align="center"  class="STYLE10" colspan="2"></td>
          </tr>
        
        <%
        	//System.out.println("itemSql+cpNodeOrderId=:"+itemSql+cpNodeOrderId);
        	DataSetClass ds1=db.FunGetDataSetBySQL(itemSql+cpNodeOrderId);
        	for(int j=0;j<ds1.FunGetRowCount();j++){
              	String orderType=""; 
              	orderType=ds1.FunGetDataAsStringByColName(j,"ORDER_KIND");
				if("0".equals(orderType)){
					orderType="临时";
				}else if("1".equals(orderType)){
					orderType="长期";
				}else if("2".equals(orderType)){
					orderType="出院";
				}
        %>
		<tr bgcolor="#FFFFFF" >
          <td align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
           	<td  colspan="2" align="left" class="STYLE10">&nbsp;&nbsp;&nbsp;&nbsp;<%=ds1.FunGetDataAsStringByColName(j,"CP_NODE_ORDER_TEXT") %></td>
          	<td align="center"  class="STYLE10" colspan="2"><%=orderType %></td>
         </tr>
			
          <%//}}
          }  }}}catch(Exception e){
          	out.print("<script  type='text/javascript'> alert('网络连接失败!请重试')</script>");
          } %>
        </tbody>
      </table></th>
  </tr>
</table>

   <%}else if("frag-4".equals(ops)){%>

 <table border="1" width="100%" height="100%">
        <tr>
          <th width="100%" scope="col" height="400" valign="top">
          <table width="100%"   border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="frag-4" >
            <thead>
              <tr>
                <th bgcolor="d3eaef" class="STYLE10">&nbsp;</th>
                <th colspan="4"  align="center" bgcolor="d3eaef" class="STYLE10">护理工作内容</th>
              </tr>
              <tr>
                <th width="3%" bgcolor="d3eaef" class="STYLE10"><div align="center"></div></th>
                <th width="34%"  align="center" bgcolor="d3eaef" class="STYLE10">护理项目</th>
                <th width="22%"  align="center" bgcolor="d3eaef" class="STYLE10">项目编码</th>
                <th width="18%" align="center" bgcolor="d3eaef" class="STYLE10">自动项目</th>
                <th width="23%"  align="center" bgcolor="d3eaef" class="STYLE10">对照本地编码</th>
              </tr>
              </thead>
              <tbody id="lcp_node_nurse_tbody">
         <% 
        
             String sql="select CP_NODE_NURSE_ID, CP_NODE_NURSE_TEXT,NEED_ITEM "+
           				  " from lcp_node_nurse_point  where SYS_IS_DEL=0 and cp_id="+cp_id+" and cp_node_id="+cp_node_id+" and HOSPITAL_ID="+HOSPITALID;
             String itemSql="select cp_node_nurse_item_id,cp_node_nurse_text,need_item,auto_item,nurse_no from lcp_node_nurse_item "
   	  			+"where sys_is_del=0 and cp_id="+cp_id+" and cp_node_id="+cp_node_id+" and HOSPITAL_ID="+HOSPITALID+" and CP_NODE_nurse_ID=";
          	 try{
          		DataSetClass ds=db.FunGetDataSetBySQL(sql);
             	if(ds.FunGetDataAsStringById(0,0)!=""){
	              	for (int i = 0; i < ds.FunGetRowCount(); i++) {
						String cpNodeNurseText = ds.FunGetDataAsStringByColName(i, "CP_NODE_NURSE_TEXT");//路径节点护理工作内容
						Number cpNodeNurseId=ds.FunGetDataAsNumberByColName(i,"CP_NODE_NURSE_ID");
						DataSetClass ds1=db.FunGetDataSetBySQL(itemSql+cpNodeNurseId);
			
 	     %>
              <tr bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onmouseout="recoverColor(this)" class="STYLE19"  id="node-<%=cpNodeNurseId%>">
                <td><div align="center"></div></td>
                <td height="20" colspan="4" align="left" class="STYLE10"><%=cpNodeNurseText%></td>
              </tr>
         <%
              if(ds1.FunGetDataAsStringById(0,0)!=""){
				for (int j = 0; j < ds1.FunGetRowCount(); j++) {
					String cpNodeNurseItemId=ds1.FunGetDataAsStringByColName(j,"CP_NODE_NURSE_ITEM_ID");
					String nurseText=ds1.FunGetDataAsStringByColName(j,"CP_NODE_NURSE_TEXT");
					String need = ds1.FunGetDataAsStringByColName(j, "NEED_ITEM");
					String auto = ds1.FunGetDataAsStringByColName(j, "AUTO_ITEM");
					String nurseNo = ds1.FunGetDataAsStringByColName(j, "NURSE_NO");
					String matchSql="select local_code,match_type from lcp_match_nurse where sys_is_del=0 and nurse_code="+nurseNo+" and HOSPITAL_ID="+HOSPITALID;
					auto=("0".equals(auto)?"自动":"手动");
					DataSetClass matchDs=db.FunGetDataSetBySQL(matchSql); 
					String localCodeTemp="";
					if(matchDs.FunGetDataAsStringById(0,0)!=""){
						for(int s=0;s<matchDs.FunGetRowCount();s++){
							String localCode=matchDs.FunGetDataAsStringByColName(s,"LOCAL_CODE");
							if(s<matchDs.FunGetRowCount()-1){
								localCodeTemp=localCodeTemp+localCode+"、";
							}else {
								localCodeTemp+=localCode;
							}
						}
					}
			%>
			<tr class="child-of-node-<%= cpNodeNurseId%>" bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onmouseout="recoverColor(this)"  id="tr<%=cpNodeNurseId%>">
                <td><div align="center"></div></td>
                <td  align="left" class="STYLE10"> &nbsp;&nbsp;&nbsp;&nbsp;<%=nurseText%></td>
                <td  align="center" class="STYLE10"><%=nurseNo %>  </td>
                <td  align="center" class="STYLE10"><%=auto %>  </td>
                <td  align="center" class="STYLE10"><%=localCodeTemp %>  </td>
              </tr>
              <%}} } }} catch(Exception e){ 
                	out.print("<script  type='text/javascript'> alert('网络连接失败!请重试')</script>");
                }%>
            </tbody>
          </table></th>
        </tr>
</table>
	
	<%} else if("frag-5".equals(ops)){%>
	
<table border="1" width="100%"  height="100%">
  <tr>
    <th width="100%" scope="col" height="350" valign="top">
    <table width="100%"   border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce"  >
        <tr >
          <td  height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">家属工作内容</div></td>
        </tr>
        <tbody id="lcp_node_variation_tbody">
            <%   
            String sql="select * from lcp_node_family_point  where SYS_IS_DEL=0 and cp_id="+cp_id+" and cp_node_id="+cp_node_id+" and HOSPITAL_ID="+HOSPITALID;
            try{
                DataSetClass ds=db.FunGetDataSetBySQL(sql);
                if(ds.FunGetDataAsStringById(0,0)!=""){
            	for (int i = 0; i < ds.FunGetRowCount(); i++) {
				String cpNodeFamilyId = ds.FunGetDataAsStringByColName(i, "CP_NODE_FAMILY_ID");
				String cpNodeFamilyText = ds.FunGetDataAsStringByColName(i, "CP_NODE_FAMILY_TEXT");
 	     %>
        <tr bgcolor="#FFFFFF" onMouseOver="changeColor(this)" onMouseOut="recoverColor(this)"  id="tr<%=cpNodeFamilyId%>">
        	<td height="20"  align="left" class="STYLE10">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= cpNodeFamilyText%></td>
         </tr>
           
           <%} }}  catch(Exception e){ 
                	out.print("<script  type='text/javascript'> alert('网络连接失败!请重试')</script>");
                }%>
                
        </tbody>
    </table></th>
  </tr>
</table>
	<%}%>