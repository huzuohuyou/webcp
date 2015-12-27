/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：CancelSignTable.java    
// 文件功能描述：取消诊疗、护理签名
//
// 创建人：韩金杰
// 创建日期：2013-03-01
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.servlet.patient;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodwillcis.lcp.model.DoctorTable;
import com.goodwillcis.lcp.model.NurseTable;
import com.goodwillcis.lcp.model.RouteExecuteInfo;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.Conversion;

/**
 * Servlet implementation class LogServlet
 */
@WebServlet("/servlet/CancelSignTable")
public class CancelSignTable extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String operate=request.getParameter("op");
		String para=request.getParameter("para");
		HttpSession session=request.getSession();
		RouteExecuteInfo info=(RouteExecuteInfo) session.getAttribute("info");
		int idslength;
		if("Doctor".equals(operate)){
			String[] ids=CommonUtil.getArrByString(para, "tr");
			idslength=ids.length;
			if("".equals(ids[ids.length-1])){
				idslength=ids.length-1;
			}
			int a=cancelSignDoctor(info, ids,idslength);
			String json="";
			if(a>0){
				json="[{\"result\":\"OK\"," +
				"\"method\":\"signTable\", " +
				"\"table\":\"Doctor\"}]";
			
			}else{
				json="[{\"result\":\"ERROR\"," +
				"\"method\":\"signTable\", " +
				"\"table\":\"Doctor\"}]";
			}
			response.getWriter().println(json);
		}
		if("Nurse".equals(operate)){
			String[] ids=CommonUtil.getArrByString(para, "tr");
			idslength=ids.length;
			if("".equals(ids[ids.length-1])){
				idslength=ids.length-1;
			}
			int a=cancelSignNurse(info, ids,idslength);
			String json="";
			if(a>0){
				json="[{\"result\":\"OK\"," +
				"\"method\":\"signTable\", " +
				"\"table\":\"Nurse\"}]";
			
			}else{
				json="[{\"result\":\"ERROR\"," +
				"\"method\":\"signTable\", " +
				"\"table\":\"Nurse\"}]";
			}
			response.getWriter().println(json);
		}
	}
	private int cancelSignDoctor(RouteExecuteInfo info,String [] ids,int idslength){
		DoctorTable table=new DoctorTable();
		String time=CommonUtil.getOracleToDate();
		int a=table.cancelSignTable(info, ids, idslength,time, time);
		
		return a;
	}
	private int cancelSignNurse(RouteExecuteInfo info,String[] ids,int idslength){
		NurseTable table=new NurseTable();
		String time=CommonUtil.getOracleToDate();
		int a=table.cancelSignTable(info, ids, idslength, time, time);
		
		return a;
	}
}
