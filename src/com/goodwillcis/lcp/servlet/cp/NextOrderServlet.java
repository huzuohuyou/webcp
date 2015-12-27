package com.goodwillcis.lcp.servlet.cp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.service.cp.NodeToNext;
import com.goodwillcis.lcp.service.cp.impl.NodeToNextImpl;
import com.goodwillcis.lcp.util.*;

public class NextOrderServlet extends HttpServlet {
	
	/**
	 * 
	 */
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
		System.out.println(op);
		if ("toNextOrder".equals(op)) {
			toNextOrder(request, response);
		}
	}
	
	private void toNextOrder(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//路径ID
		String cpId = request.getParameter("cpId");
		//路径节点ID
		String cpNodeId = request.getParameter("cpNodeId");
		
		String patientId = request.getParameter("patientId");
		//医院编号
		int hospitalId = LcpUtil.getHospitalID();
		
		//当数据为空的时候  表明什么都没选， 为空
		if(patientId == "" || cpId == "" || cpNodeId == ""){
			//神马也不做
			response.getWriter().println(
			"[{\"result\":\"fail\"}]");
		}
		else{
			//查找该路径对应的最后一个正常节点
			String selectSql="select a.cp_node_id from lcp_node_relate a where a.cp_id="+cpId+" and a.cp_next_node_id=" +
					" (select b.cp_node_id from lcp_master_node b where b.cp_id="+cpId+" and b.cp_node_type=2)";
			String zhCpNode=dbc.FunGetDataSetBySQL(selectSql).FunGetDataAsStringById(0, 0);//最后一个正常节点
			//查找临床路径执行单中执行的最大节点
			String sql="select t.cp_node_id from lcp_patient_node t where t.patient_no = '"+patientId+"' and t.cp_id = "+cpId+" and t.cp_node_type = 1 and t.cp_node_start_time = "+
            "(select max(cp_node_start_time) from lcp_patient_node where patient_no = '"+patientId+"' and cp_id = "+cpId+" and cp_node_type = 1)";
			//System.out.println("sql==:"+sql);
			String maxCpNode=dbc.FunGetDataSetBySQL(sql).FunGetDataAsStringById(0, 0);
			String successNodeId=dbc.FunGetDataSetBySQL("select cp_node_id from lcp_master_node where cp_id="+cpId+" and cp_node_type=2").FunGetDataAsStringById(0, 0);
			
			//把执行医嘱的当前节点和该路径的最后节点比较，如果医嘱执行到最后节点而临床路径执行单还没有执行到最后节点就提示返回执行单
			if(cpNodeId.equals(zhCpNode) && !maxCpNode.equals(zhCpNode)){
				response.getWriter().println("[{\"result\":\"noExecute\"}]");
			}else if(cpNodeId.equals(successNodeId)){
				response.getWriter().println("[{\"result\":\"success\"}]");
			}else{
			NodeToNext nt = new NodeToNextImpl();
			int result = nt.synOrderData(cpId, cpNodeId, patientId, hospitalId);
			System.out.println(result);
			
			//response.getWriter().println("<script type=\"text/javascript\">location.href=\"login.jsp\";</script>");
			
//			try {
//				request.getRequestDispatcher("/service/record.jsp").forward(request,response);
//			} catch (ServletException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//response.sendRedirect("http://www.baidu.com");
			
			if (result > 0) {
				response.getWriter().println(
						"[{\"result\":\"OK\"}]");
			} else {
				response.getWriter().println(
						"[{\"result\":\"fail\"}]");
			}
			}
		}		
		
   	}		
}
