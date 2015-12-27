/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：NodeExecute.java    
// 文件功能描述：切换路径节点和加载页面全部表格
//
// 创建人：刘植鑫
// 创建日期：2011-06-02
// 修改人:潘状
// 修改原因:需求更改,数据库表结构改变
// 修改日期:2011-7-29
// 完成日期:2011-7-29

//修改人：林建喜
//修改原因：修改getAllTable和changeAllTable函数，新增节点住院日预警功能
//修改时间：2013-4-15
//完成时间：2013-4-16
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.servlet.patient;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.model.DoctorTable;
import com.goodwillcis.lcp.model.FamilyTable;
import com.goodwillcis.lcp.model.NodeTable;
import com.goodwillcis.lcp.model.NurseTable;
import com.goodwillcis.lcp.model.OrderTable;
import com.goodwillcis.lcp.model.RouteExecuteInfo;
import com.goodwillcis.lcp.model.Top;
import com.goodwillcis.lcp.model.VariationTable;

public class NodeExecuteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operate=request.getParameter("op");
		if("getAllTable".equals(operate)){
			if("false".equals(request.getParameter("execute"))){
				getAllTable(request, response,false);
			}else{
				getAllTable(request, response,true);
			}
			
		}else if("changeNode".equals(operate)){
			if("false".equals(request.getParameter("execute"))){
				changeAllTable(request, response,false);
			}else{
				changeAllTable(request, response,true);
			}
		}else if("getTable".equals(operate)){
			getTable(request, response,true);
		}
	}
	public void getTable(HttpServletRequest request, HttpServletResponse response,boolean execute) throws IOException{
		response.setCharacterEncoding("UTF-8");
		String optable=request.getParameter("para");
		String fun=request.getParameter("fun");
		HttpSession session=request.getSession();
		RouteExecuteInfo info=(RouteExecuteInfo) session.getAttribute("info");
		info.setExecute(execute);
		if("Nurse".equals(optable)){
			NurseTable table=new NurseTable();
			String nursetableString=table.createTable(info);
			String result="";
			if(nursetableString!=""){
				result="OK";
			}else{
				result="ERROR";
			}
			response.getWriter().println("[{\"result\":\""+result+"\"," +
					"\"method\":\"changeOneTable\", " +
					"\"table\":\"Nurse\", " +
					"\"fun\":\""+fun+"\", " +
					"\"html\":\""+nursetableString+"\"}]");
		}
		if("Doctor".equals(optable)){
			DoctorTable table=new DoctorTable();
			String nursetableString=table.createTable(info);
			String result="";
			if(nursetableString!=""){
				result="OK";
			}else{
				result="ERROR";
			}
			response.getWriter().println("[{\"result\":\""+result+"\"," +
					"\"method\":\"changeOneTable\", " +
					"\"table\":\"Doctor\", " +
					"\"fun\":\""+fun+"\", " +
					"\"html\":\""+nursetableString+"\"}]");
		}
		if("Family".equals(optable)){
			FamilyTable table=new FamilyTable();
			String nursetableString=table.createTable(info);
			String result="";
			if(nursetableString!=""){
				result="OK";
			}else{
				result="ERROR";
			}
			response.getWriter().println("[{\"result\":\""+result+"\"," +
					"\"method\":\"changeOneTable\", " +
					"\"table\":\"Family\", " +
					"\"fun\":\""+fun+"\", " +
					"\"html\":\""+nursetableString+"\"}]");
		}
		if("Order".equals(optable)){
			OrderTable table=new OrderTable();
			String ordertableString=table.createTable(info);
			String orderItemString=table.funGetItem(info);
			String result="";
			if(ordertableString!=""||orderItemString!=""){
				result="OK";
			}else{
				result="ERROR";
			}
			response.getWriter().println("[{\"result\":\""+result+"\"," +
					"\"method\":\"changeOneTable\", " +
					"\"table\":\"Order\", " +
					"\"fun\":\""+fun+"\", " +
					"\"orderItem\":\""+orderItemString+"\", " +
					"\"html\":\""+ordertableString+"\"}]");
		}
		if("Variation".equals(optable)){
			VariationTable table=new VariationTable();
			String variationtableString=table.createTable(info);
			String result="";
			if(variationtableString!=""){
				result="OK";
			}else{
				result="OK";
			}
			response.getWriter().println("[{\"result\":\""+result+"\"," +
					"\"method\":\"changeOneTable\", " +
					"\"table\":\"Variation\", " +
					"\"fun\":\""+fun+"\", " +
					"\"html\":\""+variationtableString+"\"}]");
		}
	}
	/**
	 *	取得全部的表格内容
	 *  2011-06-02
	 */
	public void getAllTable(HttpServletRequest request, HttpServletResponse response,boolean isExecute) throws IOException{
		response.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String patientID=URLDecoder.decode(request.getParameter("patientID"),"UTF-8");
		String userID=URLDecoder.decode(request.getParameter("userID"),"UTF-8");
		String userName=URLDecoder.decode(request.getParameter("userName"),"UTF-8");
		RouteExecuteInfo info=new RouteExecuteInfo();
		NodeTable nodeTable=new NodeTable();
		int cpID=info.getCpIDByPatientNo(patientID);
		int hostipalID=info.getHostipalIDFromSYS();
		//相关信息
		info.setCpID(cpID);
		info.setExecute(isExecute);
		info.setHostipalID(hostipalID);
		info.setPatientNo(patientID);
		info.setVerifyCode(userID);
		info.setVerifyName(userName);
		int maxCpNodeID=nodeTable.funGetMaxCpNodeIDByCpID(info);//当前路径最大节点号
		info.setMaxCpNodeID(maxCpNodeID);
		info.setCpNodeID(maxCpNodeID);
		session.setAttribute("info", info);
		boolean isHaveAddButton=nodeTable.funIsHaveAddButton(info);//当前节点是否有增加权限
		int nodeExecuteRight=nodeTable.funGetMaxNodeType(info);//最大节点是否有执行下一节点权限 
		//System.out.println("nodeExecuteRight=:"+nodeExecuteRight);
		//1：此节点不是最后节点可以执行下一结点按钮，
		//2，此节点是最后节点，但是没有完成，
		//3，此节点是最后节点，并且已经完成
		String nodeTableHtml=nodeTable.createTable(info);
		String nodeDaysTableHtml = nodeTable.getNodeActualDays(info, maxCpNodeID);
		String tables=funGetAllRightTables(info);//右边的四个表格
		Top top=new Top();
		String tophtml=top.createTopTable(info);
		String result="";
		
		String sql="SELECT CP_NODE_ID FROM LCP_MASTER_NODE T  " +
				"WHERE T.CP_ID="+info.getCpID()+" " +
				"AND T.CP_NODE_TYPE=4 " +
				"AND T.HOSPITAL_ID="+info.getHostipalID()+" ";
		DataSet existDataset=new DataSet();
		existDataset.funSetDataSetBySql(sql);
		String existNode=existDataset.funGetFieldByCol(0, "CP_NODE_ID");
		
		//System.out.println(existNode);
		
		String nextNodeId_sql="SELECT A.CP_ID,A.CP_NODE_ID,A.CP_NEXT_NODE_ID,B.CP_NODE_NAME,B.CP_NODE_DAYS,B.CP_NODE_TYPE  FROM LCP_NODE_RELATE A,LCP_MASTER_NODE B " +
				"WHERE A.CP_ID="+info.getCpID()+" AND A.CP_NODE_ID="+info.getMaxCpNodeID()+" AND A.CP_ID=B.CP_ID AND A.CP_NEXT_NODE_ID=B.CP_NODE_ID ORDER BY CP_NEXT_NODE_ID";
		DataSet nextDataSet=new DataSet();
		nextDataSet.funSetDataSetBySql(nextNodeId_sql);
//		System.out.println("nextNodeId_sql=:"+nextNodeId_sql);
		String next_Node_id=nextDataSet.funGetFieldByCol(0, "CP_NEXT_NODE_ID");
		if(nodeTableHtml!=""){
			result="OK";
		}else{
			result="ERROR";
		}
		String cpNodeType_sql="select cp_node_type from lcp_master_node where CP_ID="+info.getCpID()+" and CP_NODE_ID="+info.getCpNodeID()+"";
		DataSet typeDataSet=new DataSet();
		typeDataSet.funSetDataSetBySql(cpNodeType_sql);
//		System.out.println("cpNodeType_sql=:"+cpNodeType_sql);
		String cpNodeType=typeDataSet.funGetFieldByCol(0, "CP_NODE_TYPE");
		String json="[{\"result\":\""+result+"\"," +
			"\"method\":\"getAllTable\", " +
			"\"isHaveAddButton\":\""+isHaveAddButton+"\", " +
			"\"nodeExecuteRight\":\""+nodeExecuteRight+"\", " +
			"\"tophtml\":\""+tophtml+"\", " +
			"\"next_Node_id\":\""+next_Node_id+"\", " +
			"\"existNode\":\""+existNode+"\", " +
			"\"cp_id\":\""+info.getCpID()+"\", " +
			"\"cp_node_id\":\""+cpNodeType+"\", " +
			""+tables+", " +
			"\"nodeTableHtml\":\""+nodeTableHtml+"\","+
			"\"nodeDaysTableHtml\":\""+nodeDaysTableHtml+"\"}]";
		response.getWriter().println(json);
	}
	/**
	 * 改变右边的表格内容 2011-06-02
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void changeAllTable(HttpServletRequest request, HttpServletResponse response,boolean isExecute) throws IOException{
		response.setCharacterEncoding("UTF-8");
		String cpnode=request.getParameter("para");
		cpnode=cpnode.replace("cp_node=", "");
		int node=Integer.parseInt(cpnode);
		HttpSession session=request.getSession();
		RouteExecuteInfo info=(RouteExecuteInfo) session.getAttribute("info");
		info.setExecute(isExecute);
		info.setCpNodeID(node);
		NodeTable nodeTable=new NodeTable();
		String nodeDaysTableHtml = nodeTable.getNodeActualDays(info, node);
		boolean isHaveAddButton=nodeTable.funIsHaveAddButton(info);//当前节点是否有增加权限
		String tables=this.funGetAllRightTables(info);//右边的五个表格
		String json="[{\"result\":\"OK\"," +
			"\"method\":\"changeAllTable\", " +
			"\"isHaveAddButton\":\""+isHaveAddButton+"\", " +
			"\"nodeDaysTableHtml\":\""+nodeDaysTableHtml+"\", " +
			""+tables+"}]";
		response.getWriter().println(json);
	}
	/**
	 * 取得右边的五个表格，最后没有逗号，2011-07-29
	 * @param info
	 * @return
	 */
	private String funGetAllRightTables(RouteExecuteInfo info){
		String result="";
		DoctorTable doctorTable=new DoctorTable();
		String doctorTableHtml=doctorTable.createTable(info);
		OrderTable orderTable=new OrderTable();
		String orderPointTableHtml=orderTable.createTable(info);
		//String orderItemTableHtml=orderTable.funGetItem(info);
		FamilyTable familyTable=new FamilyTable();
		String familyTableHtml=familyTable.createTable(info);
		VariationTable variationTable=new VariationTable();
		String variationTableHtml=variationTable.createTable(info);
		NurseTable nurseTable=new NurseTable();
		String nurseTableHtml=nurseTable.createTable(info);
		result="\"doctorTableHtml\":\""+doctorTableHtml+"\", " +
			"\"orderPointTableHtml\":\""+orderPointTableHtml+"\", " +
			//"\"orderItemTableHtml\":\""+orderItemTableHtml+"\", " +
			"\"familyTableHtml\":\""+familyTableHtml+"\", " +
			"\"variationTableHtml\":\""+variationTableHtml+"\", " +
			"\"nurseTableHtml\":\""+nurseTableHtml+"\"";
		return result;
	}
}
