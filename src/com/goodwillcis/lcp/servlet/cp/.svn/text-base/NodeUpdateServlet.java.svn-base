package com.goodwillcis.lcp.servlet.cp;

import java.io.IOException;
import java.sql.Array;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.service.cp.NodeUpdate;
import com.goodwillcis.lcp.service.cp.impl.NodeUpdateImpl;
import com.goodwillcis.lcp.util.*;

public class NodeUpdateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	DatabaseClass dbc = LcpUtil.getDatabaseClass();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op = request.getParameter("op");
//		System.out.println("op==" + op);
		if ("saveSchema".equals(op)) {
			saveSchema(request, response);
		}else if("getSchema".equals(op)){
			getSchema(request,response);
		}else if("delSchema".equals(op)){
			delSchema(request,response);
		}else if("editSchema".equals(op)){
			editSchema(request,response);
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @editor 林建喜
	 * @editContent 新增一级菜单orderId获取功能
	 */
	private void saveSchema(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//医嘱方案备注信息
		String comments = request.getParameter("comments");
		//方案数据
		String orderIds = request.getParameter("orderIds");
        //病人号
		String patientNo = request.getParameter("patientNo");
		//医生工号
		String doctorNo = request.getParameter("doctorNo");
		//路径ID
		String cpId = request.getParameter("cpId");
		//路径节点ID
		String cpNodeId = request.getParameter("cpNodeId");
		//模板类型
		String typeName = request.getParameter("typeName");
		//医院编号
		int hospitalId = LcpUtil.getHospitalID();
		//一级菜单orderId
		String sqlCpMasterId = "select cp_master_id from lcp_master where cp_id = "+cpId+"";
		String cp_master_id= dbc.FunGetDataSetBySQL(sqlCpMasterId).FunGetDataAsStringByColName(0, "CP_MASTER_ID");
		String commentsSql = "select * from lcp_patient_order_schema t where hospital_id = "+hospitalId+" and doctor_no = "+doctorNo+" and cp_master_id = "+cp_master_id+" and cp_node_id = "+ cpNodeId+" and comments = '"+comments+"'";
		int row = dbc.FunGetRowCountBySql(commentsSql);
//		System.out.println("row=="+row);
//		System.out.println("commentsSql=="+commentsSql);
		if(row>0){
			response.getWriter().println("[{\"flag\":\"2\"}]");
		}else{
		DataSetClass usersSet= dbc.FunGetDataSetBySQL("select t.user_name from users@jhemr t where t.account_status<>8 and t.pre_no='"+doctorNo+"'");
		String doctor_name = usersSet.FunGetDataAsStringByColName(0, "USER_NAME");
		
		String orderIds2 = "";
	    String[] a = orderIds.split("/");

	    for (int i = 0; i < a.length; i++) {//获得一级菜单orderId
	    	String[] b = a[i].split(",");
			String orderId2 = b[0];
			String sqlOrderId ="select continue_order_id from lcp_patient_order_point where patient_no='"+patientNo+"' and cp_id="+cpId+" and cp_node_id="+cpNodeId+" and cp_node_order_id="+orderId2+"";
			DataSetClass dsOrderId = dbc.FunGetDataSetBySQL(sqlOrderId);
			String orderId1 = dsOrderId.FunGetDataAsStringByColName(0, "CONTINUE_ORDER_ID");
			orderIds2 = orderIds2+orderId1+","+a[i]+"/";
		}
		
		int schemaId = 1;
		//当数据为空的时候  表明什么都没选， 为空
		if(orderIds == "" || doctorNo == "" || cpId == "" || cpNodeId == ""){
			//神马也不做
		}
		else{	
			String selectSql = "select max(schema_id) as maxId from lcp_patient_order_schema where hospital_id = "+hospitalId+" and doctor_no = "+doctorNo+" and cp_master_id = "+cp_master_id+" and cp_node_id = " + cpNodeId;
			DataSetClass dsc = dbc.FunGetDataSetBySQL(selectSql);
			if(dsc.FunGetDataAsStringById(0, 0) != ""){
				schemaId = dsc.FunGetDataAsNumberById(0, 0).intValue() + 1;
			}

		String insertSql = "";
		
		insertSql = "insert into lcp_patient_order_schema(SCHEMA_ID, hospital_id, doctor_no, schema_data, comments, cp_id, cp_master_id, cp_node_id,order_type_name,doctor_name,sys_last_update) values ("+schemaId+", "+hospitalId+", "+doctorNo+", '"+orderIds2+"', '"+comments+"', "+cpId+", "+cp_master_id+" , "+cpNodeId+",'"+typeName+"','"+doctor_name+"',"+CommonUtil.getOracleToDate()+") ";
//		System.out.println("insertSql==" + insertSql);
		int result = dbc.FunRunSqlByFile(insertSql.getBytes("GB2312"));

	    if(result > 0){
	    	  response.getWriter().println("[{\"flag\":\"1\"}]");
	    	}else if(result < 0){
	    	  response.getWriter().println("[{\"flag\":\"0\"}]");
	    	}
		
		 }
		
		}
   	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author 林建喜
	 * @content 编辑医嘱模板功能
	 * @date   2013-8-7
	 */
	private void editSchema(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//医嘱方案备注信息
		String comments = request.getParameter("comments");
		//方案数据
		String orderIds = request.getParameter("orderIds");
        //病人号
		String patientNo = request.getParameter("patientNo");
		//医生工号
		String doctorNo = request.getParameter("doctorNo");
		//路径ID
		String cpId = request.getParameter("cpId");
		//路径节点ID
		String cpNodeId = request.getParameter("cpNodeId");
		//模板类型
		String typeName = request.getParameter("typeName");
		//医院编号
		int hospitalId = LcpUtil.getHospitalID();
		String oldComments = request.getParameter("oldComments");
		//一级菜单orderId
		String sqlCpMasterId = "select cp_master_id from lcp_master where cp_id = "+cpId+"";
		String cp_master_id= dbc.FunGetDataSetBySQL(sqlCpMasterId).FunGetDataAsStringByColName(0, "CP_MASTER_ID");
		int row;
		if(comments.equals(oldComments)){
			row = 0;
		}else{
			row = dbc.FunGetRowCountBySql("select * from lcp_patient_order_schema t where hospital_id = "+hospitalId+" and doctor_no = "+doctorNo+" and cp_master_id = "+cp_master_id+" and cp_node_id = "+ cpNodeId+" and comments = '"+comments+"'");
		}
		if(row>0){
			response.getWriter().println("[{\"flag\":\"2\"}]");
		}else{
		
		String orderIds2 = "";
	    String[] a = orderIds.split("/");

	    for (int i = 0; i < a.length; i++) {//获得一级菜单orderId
	    	String[] b = a[i].split(",");
			String orderId2 = b[0];
			String sqlOrderId ="select continue_order_id from lcp_patient_order_point where patient_no='"+patientNo+"' and cp_id="+cpId+" and cp_node_id="+cpNodeId+" and cp_node_order_id="+orderId2+"";
			DataSetClass dsOrderId = dbc.FunGetDataSetBySQL(sqlOrderId);
			String orderId1 = dsOrderId.FunGetDataAsStringByColName(0, "CONTINUE_ORDER_ID");
			orderIds2 = orderIds2+orderId1+","+a[i]+"/";
		}
		
		int schemaId = 1;
		//当数据为空的时候  表明什么都没选， 为空
		if(orderIds == "" || doctorNo == "" || cpId == "" || cpNodeId == ""){
			//神马也不做
		}
		else{	
			
		String updateSql = "";
		
		updateSql = "update lcp_patient_order_schema set schema_data='"+orderIds2+"',comments='"+comments+"',order_type_name='"+typeName+"',sys_last_update="+CommonUtil.getOracleToDate()+" where hospital_id=1 and doctor_no="+doctorNo+" and cp_master_id="+cp_master_id+" and cp_node_id="+cpNodeId+" and comments='"+oldComments+"'";
//		System.out.println("updateSql==" + updateSql);
		int result = dbc.FunRunSqlByFile(updateSql.getBytes("GB2312"));
//        System.out.println("result=="+ result);  
	    if(result > 0){
	    	  response.getWriter().println("[{\"flag\":\"1\"}]");
	    	}else if(result < 0){
	    	  response.getWriter().println("[{\"flag\":\"0\"}]");
	    	}
		
		 }
		
		}
   	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @editor 林建喜
	 * @editContent 新增获取“科室他人模板”功能
	 */
	private void getSchema(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		//医生工号
		String doctorNo = request.getParameter("doctorNo");
		//路径ID
		String cpId = request.getParameter("cpId");
		//路径节点ID
		String cpNodeId = request.getParameter("cpNodeId");
		//模板从属标识
		String flag = request.getParameter("flag");
//		System.out.println("flag=="+flag);
		//医院编号
		int hospitalId = LcpUtil.getHospitalID();
		String sqlCpMasterId = "select cp_master_id from lcp_master where cp_id = "+cpId+"";
		String cp_master_id= dbc.FunGetDataSetBySQL(sqlCpMasterId).FunGetDataAsStringByColName(0, "CP_MASTER_ID");
		NodeUpdate nu = new NodeUpdateImpl();
		
		String table = "";
		table = nu.getSchemaData(cp_master_id, cpNodeId, hospitalId, doctorNo, flag);
		//System.out.println(table);
		response.getWriter().println(table);
		
	}
	
	private void delSchema(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		//医生工号
		String doctorNo = request.getParameter("doctorNo");
		//路径ID
		String cpId = request.getParameter("cpId");
		//路径节点ID
		String cpNodeId = request.getParameter("cpNodeId");
		//医院编号
		int hospitalId = LcpUtil.getHospitalID();
		//方案编号
		String schema_ids=request.getParameter("schemaIds");

		
		String sqlCpMasterId = "select cp_master_id from lcp_master where cp_id = "+cpId+"";
		String cp_master_id= dbc.FunGetDataSetBySQL(sqlCpMasterId).FunGetDataAsStringByColName(0, "CP_MASTER_ID");
		String sqlDelSchema = "delete from lcp_patient_order_schema where hospital_id='"+hospitalId+"' and " +
			"doctor_no="+doctorNo+" and cp_master_id="+cp_master_id+" and cp_node_id="+cpNodeId+" and schema_id in ("+schema_ids+") ";

	    int isUpdateSuc = dbc.FunRunSqlByFile(sqlDelSchema.getBytes());
		
		if (isUpdateSuc > 0) {
			String delflag = "0";
			NodeUpdate nu = new NodeUpdateImpl();
			String table = "";
			table = nu.getSchemaData(cp_master_id, cpNodeId, hospitalId, doctorNo, delflag);
		response.getWriter().println(
					"[{\"result\":\"OK\",\"flag\":\"1\",\"count\":\""+isUpdateSuc+"\",\"table\":\""+table+"\"}]");
		} else {
			response.getWriter().println(
			"[{\"result\":\"OK\", \"flag\":\"0\"}]");		}
		
	}
	
	
}
