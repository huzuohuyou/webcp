/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：NextNode.java    
// 文件功能描述：NextNode(下一节点)相关操作相关操作
//
// 创建人：刘植鑫
// 创建日期：2011-06-01
// 修改人:潘状
// 修改原因:需求更改,数据库表结构改变
// 修改日期:2011-7-29
// 完成日期:2011-7-29
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
import com.goodwillcis.lcp.model.NurseTable;
import com.goodwillcis.lcp.model.RouteExecuteInfo;

public class NextNodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		RouteExecuteInfo info=(RouteExecuteInfo) session.getAttribute("info");
		NextNodeTable nextNodeTable=new NextNodeTable();
		NodeTable nodeTable=new NodeTable();
		int type=-1;//最大节点类型
		int flag=-1;//执行下一节点状态,
			//1：有执行下一结点权限并且完成最大节点，并取得html表格，
			//-2: 有执行下一结点权限并且完成最大节点出错，
			//3: 无执行下一节点权限，并且完成最大节点
			//-4: 无执行下一节点权限，并且完成最大节点出错
			//5：有执行下一节点权限，但是没有全签名
		//boolean isNurseSign=false;//护理是否已经签名
		String html="";
		NurseTable nurseTable=new NurseTable();
		//isNurseSign=nurseTable.checkSign(info);
		String noSignTableName="";
		String noExecuteFieldContext="";
		try {
			type=nodeTable.funGetMaxNodeType(info);//取得最大节点类型
			//1：此节点不是最后节点可以执行下一结点按钮，
			//2，此节点是最后节点，但是没有完成，
			//3，此节点是最后节点，并且已经完成
			if(type==1){
				boolean isSignAll=nodeTable.funCheckExecuteNextNode(info);//查看是否都已经签名
				noExecuteFieldContext=nodeTable.funGetNoExecuteFieldName(info);
				if(!"".equals(noExecuteFieldContext)){
					isSignAll=false;
				}
				if(isSignAll){//已签名
					flag=1;
					html=nextNodeTable.createNextNodeTable(info);
				}else{//未签名
					noSignTableName=nodeTable.funGetNoSignTableName(info);//那个表没有签名
					flag=5;
				}
			}else if(type==2){
				int a=nodeTable.funOverNode(info);
				String node_type=nodeTable.funGetNodeType(info);// 节点类型 0准入 1正常节点 2完成节点 3变异4退出 5自定义节点
				if(node_type.equals("2")){
					node_type="11";
				}
				if(node_type.equals("4")){
					node_type="21";
				}
				nodeTable.funUpdateVisitStatus(info, node_type);
				if(a>0){
					flag=3;
				}else{
					flag=-4;
				}
			}
		} catch (Exception e) {
			flag=-1;
		}
		if(flag>0){
			response.getWriter().println("[{\"result\":\"OK\"," +
					"\"method\":\"nextNodeTable\", " +
					"\"table\":\"nextNode\", " +
					"\"flag\":\""+flag+"\", " +
					//"\"isNurseSign\":\""+isNurseSign+"\", " +
					"\"noSignTableName\":\""+noSignTableName+"\", " +
					"\"noExecuteFieldContext\":\""+noExecuteFieldContext+"\", " +
					"\"html\":\""+html+"\"}]");
		}else{
			response.getWriter().println("[{\"result\":\"ERROR\"," +
					"\"method\":\"nextNodeTable\", " +
					"\"flag\":\""+flag+"\"}]");
		}
	}
	

	
	
	
	
}
