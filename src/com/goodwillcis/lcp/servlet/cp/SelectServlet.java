/**---------------------------------------------------------------- 
 * // Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
 *  // 文件名： SelectServlet.java
 *  // 文件功能描述：路径选择功能的servlet类 
 *  // 创建人：康榕元 
 *  // 创建日期：2011/07/27 
 *  // ----------------------------------------------------------------*/

package com.goodwillcis.lcp.servlet.cp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.LcpUtil;

public class SelectServlet extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String op = request.getParameter("op");
		if("naru".equals(op)){
			naru(request,response);
		}else if("bunaru".equals(op)){
			bunaru(request,response);
		}
	}
	
	private void naru(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DatabaseClass db = LcpUtil.getDatabaseClass();
			String cpid = request.getParameter("cp_id");
			String patientId=request.getParameter("patient_id");
			String userId=request.getParameter("userid");
			String userName=request.getParameter("username");
			Date dateNow = db.FunGetDbNow(true);
			String dateNowStr = sdf.format(dateNow);
			String sql = "insert into LCP_PATIENT_NODE select hospital_id,'"+patientId+"',cp_id,cp_node_id,cp_std_node_id,cp_node_parent_id,cp_node_name,cp_node_days_min,cp_node_days_max,cp_node_days,cp_node_type,to_date('"+dateNowStr+"','yyyy-mm-dd hh24:mi:ss'),null,'"+userId+"','"+userName+"',0,0,to_date('"+dateNowStr+"','yyyy-mm-dd hh24:mi:ss') from lcp_master_node where cp_id="+cpid+" and cp_node_id = (select cp_node_id from lcp_master_node where cp_id="+cpid+" and cp_node_type=0)\n";
			String sqlVisit = "update lcp_patient_visit  set cp_state =1,sys_last_update = to_date('"+dateNowStr+"','yyyy-mm-dd hh24:mi:ss'),cp_id="+cpid+",CONFORM_CP_ID="+cpid+",CP_MASTER_ID=nvl((select cp_master_id from lcp_master where cp_id="+cpid+"),0),CONFORM_MASTER_ID=nvl((select cp_master_id from lcp_master where cp_id="+cpid+"),0),attending_doctor='"+userName+"' where patient_no = '"+patientId+"'";
			String sqlCount = sql+sqlVisit;
			int res = db.FunRunSqlByFile(sqlCount.getBytes());
			if(res>0){
				response.getWriter().println("true");
			}else{
				response.getWriter().println("false");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			response.getWriter().println("false");
		}
	}
	private void bunaru(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DatabaseClass db = LcpUtil.getDatabaseClass();
			Date dateNow = db.FunGetDbNow(true);
			String dateNowStr = sdf.format(dateNow);
			String patientId=request.getParameter("patient_id");
			String exclude=request.getParameter("exclude");
			String code=request.getParameter("code");
			String userName=request.getParameter("username");
			
			String sql = "update lcp_patient_visit  set cp_state =99, cp_exclude='"+exclude+"',sys_last_update = to_date('"+dateNowStr+"','yyyy-mm-dd hh24:mi:ss'),CP_EXCLUDE_CODE = '"+code+"',attending_doctor='"+userName+"' where patient_no = '"+patientId+"'";

			int res = db.FunRunSQLCommand(db.FunGetSvrKey(), sql);

			if(res>0){
				response.getWriter().println("true");
			}else{
				response.getWriter().println("false");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().println("false");
		}
	}
	
}
