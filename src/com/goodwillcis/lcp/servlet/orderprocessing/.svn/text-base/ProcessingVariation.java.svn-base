package com.goodwillcis.lcp.servlet.orderprocessing;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

/**
 * Servlet implementation class ProcessingVariation
 */
@WebServlet("/ProcessingVariation")
public class ProcessingVariation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final int HOSPITAL_ID=LcpUtil.getHospitalID();
	private DatabaseClass db=LcpUtil.getDatabaseClass();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String state="1";//request.getParameter("state");
		//if(state.equals("1")){
			
			String op=request.getParameter("op");
			if(op==null) op="";
			if(op.equals("variation")){
				variation(request, response);
			}
		//}
	}
	private void variation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String patient_no=(String) request.getSession().getAttribute("patient_no");
		String cp_id=(String) request.getSession().getAttribute("cp_id");
		String cp_node_id=(String) request.getSession().getAttribute("cp_node_id");
		String user_id=(String) request.getSession().getAttribute("user_id");
		String user_name=(String) request.getSession().getAttribute("user_name");
		String variationCode=request.getParameter("variationCode");
		String text=request.getParameter("text");
		if(!text.equals("")){
			text=URLDecoder.decode(text, "UTF-8");
		}
		System.out.println("patient_no==:"+patient_no);
		System.out.println("cp_id==:"+cp_id);
		System.out.println("user_id==:"+user_id);
		System.out.println("user_name==:"+user_name);
		System.out.println("variationCode==:"+variationCode);
		System.out.println("text==:"+text);
		String cp_node_id_sql="(select cp_node_id from lcp_master_node where cp_id="+cp_id+" and cp_node_type=4)";
		String cp_node_variation_id_sql="(select nvl(max(CP_NODE_VARIATION_ID),0)+1 from lcp_patient_variation where patient_no='"+patient_no+"')";
		String cp_node_name_sql="(select cp_node_name from lcp_master_node where cp_id="+cp_id+" and cp_node_type=4)";
		
		String sql="insert into lcp_patient_node (HOSPITAL_ID, PATIENT_NO, CP_ID, CP_NODE_ID, CP_STD_NODE_ID, CP_NODE_PARENT_ID, CP_NODE_NAME, CP_NODE_DAYS_MIN, CP_NODE_DAYS_MAX, CP_NODE_DAYS, CP_NODE_TYPE, CP_NODE_START_TIME, CP_NODE_END_TIME, USER_ID, USER_NAME, CP_NODE_STATE, SYS_IS_DEL, SYS_LAST_UPDATE) "+
				   " values("+HOSPITAL_ID+",'"+patient_no+"',"+cp_id+","+cp_node_id_sql+",0,0,"+cp_node_name_sql+",0,0,0,4,"+CommonUtil.getOracleToDate()+","+CommonUtil.getOracleToDate()+",'"+user_id+"','"+user_name+"',2,0,"+CommonUtil.getOracleToDate()+") \n"+
			       "insert into lcp_patient_variation (HOSPITAL_ID, PATIENT_NO, CP_ID, CP_NODE_ID, CP_NODE_VARIATION_ID, CP_STD_VARIATION_ID, CP_NODE_VARIATION_TEXT, USER_ID, USER_NAME, SYS_IS_DEL, SYS_LAST_UPDATE, EXE_DATE, CP_NODE_VARIATION_CODE) "+
		 		   "values("+HOSPITAL_ID+",'"+patient_no+"',"+cp_id+","+cp_node_id_sql+","+cp_node_variation_id_sql+",null,'"+text+"','"+user_id+"','"+user_name+"',0,"+CommonUtil.getOracleToDate()+","+CommonUtil.getOracleToDate()+",'"+variationCode+"') \n"+
		 		   "update lcp_patient_node set cp_node_end_time="+CommonUtil.getOracleToDate()+",cp_node_state=2,sys_last_update="+CommonUtil.getOracleToDate()+" where patient_no='"+patient_no+"' and cp_id="+cp_id+" and cp_node_id="+cp_node_id+" \n"+
		 		   "update lcp_patient_visit set cp_state=21,sys_last_update="+CommonUtil.getOracleToDate()+" where patient_no='"+patient_no+"'";
		int res=db.FunRunSqlByFile(sql.getBytes());
		response.getWriter().print(res);
	}
}
