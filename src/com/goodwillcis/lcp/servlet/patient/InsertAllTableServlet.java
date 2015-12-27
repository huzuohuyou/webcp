/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：InsertAllTable.java    
// 文件功能描述：根据选择的节点插入所有的相关表格信息
//
// 创建人：潘状
// 创建日期：2011-08-01
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.servlet.patient;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodwillcis.lcp.model.NextNodeTable;
import com.goodwillcis.lcp.model.NodeTable;
import com.goodwillcis.lcp.model.RouteExecuteInfo;
import com.goodwillcis.lcp.model.TableException;

public class InsertAllTableServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8634723668987481133L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String nextNodeStr=request.getParameter("para");
		nextNodeStr=nextNodeStr.replace("tr", "");
		int selectCpNodeID=Integer.parseInt(nextNodeStr);
		HttpSession session=request.getSession();
		RouteExecuteInfo info=(RouteExecuteInfo) session.getAttribute("info");
		NodeTable nodeTable=new NodeTable();
		NextNodeTable nextNodeTable=new NextNodeTable();
		int aa=nodeTable.funOverNode(info);
		String result="OK";
		String variationExit="1";
		if(aa>0){
			int bb=nextNodeTable.insertAllTable(info, selectCpNodeID);
			if (bb < 0)
			{
				System.out.println("---------------------------------------------------------------------");
				//插入失败回滚
				TableException.rollBackNode(info, selectCpNodeID);
				TableException.rollBackAll(info, selectCpNodeID);
				result = "ERROR";
			} else if (bb == 0)
			{
				variationExit="0";
				}
		}else{
			result="ERROR";
		}
		response.getWriter().println("[{\"result\":\""+result+"\"," +"\"variationExit\":\""+variationExit+"\","
				+"\"method\":\"InsertAllTable\"}]");
	}
}
