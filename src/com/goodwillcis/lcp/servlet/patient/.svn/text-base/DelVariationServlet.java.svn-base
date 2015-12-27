/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：DelVariation.java    
// 文件功能描述：删除未签名的变异信息
//
// 创建人：潘状
// 创建日期：2011-08-01
//----------------------------------------------------------------*/


package com.goodwillcis.lcp.servlet.patient;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodwillcis.lcp.model.RouteExecuteInfo;
import com.goodwillcis.lcp.model.VariationTable;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.Conversion;

public class DelVariationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String para=request.getParameter("para");
		HttpSession session=request.getSession();
		RouteExecuteInfo info=(RouteExecuteInfo) session.getAttribute("info");
		String[] ids=CommonUtil.getArrByString(para, ";");
		String edit=CommonUtil.replace(ids[0], "tr", ",");
		String blue=CommonUtil.replace(ids[1], "tr", ",");
		ArrayList<String> list=Conversion.funGetSign(edit, blue, ",");
		VariationTable table=new VariationTable();
		int a=table.delRecord(info, list);
		if(a>0){
			response.getWriter().println("[{\"result\":\"OK\"," +
					"\"method\":\"DelVariation\"}]");
		}else{
			response.getWriter().println("[{\"result\":\"ERROR\"," +
					"\"method\":\"DelVariation\"}]");
		}
	}

}
