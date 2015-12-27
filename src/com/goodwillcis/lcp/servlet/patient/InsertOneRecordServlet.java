/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：InsertOneRecord.java    
// 文件功能描述：插入变异信息
//
// 创建人：潘状
// 创建日期：2011-08-01
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.servlet.patient;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodwillcis.lcp.model.RouteExecuteInfo;
import com.goodwillcis.lcp.model.VariationTable;
import com.goodwillcis.lcp.util.CommonUtil;
public class InsertOneRecordServlet extends HttpServlet {

	/**
	 * 
	 */
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
		String operate=request.getParameter("op");
		String para=URLDecoder.decode(request.getParameter("para"),"UTF-8");
		String code=request.getParameter("variationCode");
		para=CommonUtil.replactInsertDBApostrophe(para);
		HttpSession session=request.getSession();
		RouteExecuteInfo info=(RouteExecuteInfo) session.getAttribute("info");

		if("variation".equals(operate)){
			int a=insertVariation(info,para,code);
			if(a>0){
				response.getWriter().println("[{\"result\":\"OK\"," +
						"\"table\":\"variation\", " +
						"\"method\":\"InsertOneRecord\"}]");
			}else{
				response.getWriter().println("[{\"result\":\"ERROR\"," +
						"\"table\":\"variation\", " +
						"\"method\":\"InsertOneRecord\"}]");
			}
		}
		
		
		if("variationExit".equals(operate)){
			VariationTable variationTable=new VariationTable();
			String cpNodeID=request.getParameter("fun");
			int a=variationTable.insertVariationExit(info, para, code,cpNodeID);
			if(a>0){
				if(a>0){
					response.getWriter().println("[{\"result\":\"OK\"," +
							"\"table\":\"variationExit\", " +
							"\"method\":\"variationExit\"}]");
				}else{
					response.getWriter().println("[{\"result\":\"ERROR\"," +
							"\"table\":\"variationExit\", " +
							"\"method\":\"variationExit\"}]");
				}
				
			}
		}
	}

	/**
	 * 插入一条变异记录
	 * @author lzx 2011-05-03
	 * @param para
	 * @param patient
	 * @return
	 */
	private int insertVariation(RouteExecuteInfo info,String para,String code){
		VariationTable variationTable=new VariationTable();
		return variationTable.InsertOneRecord(info, para,code);
	}
}
