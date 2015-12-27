/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：SignTable.java    
// 文件功能描述：项目签名
//
// 创建人：刘植鑫
// 创建日期：2011-06-01
// 修改人:潘状
// 修改原因:需求更改, 添加家属工作签名
// 修改日期:2011-7-29
// 完成日期:2011-7-29
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.servlet.patient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodwillcis.lcp.model.DoctorTable;
import com.goodwillcis.lcp.model.FamilyTable;
import com.goodwillcis.lcp.model.NurseTable;
import com.goodwillcis.lcp.model.OrderTable;
import com.goodwillcis.lcp.model.RouteExecuteInfo;
import com.goodwillcis.lcp.model.VariationTable;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.Conversion;


public class SignTableServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
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
		
		if("Doctor".equals(operate)){
			String[] ids=CommonUtil.getArrByString(para, ";");
			String edit=CommonUtil.replace(ids[0], "tr", ",");
			String blue=CommonUtil.replace(ids[1], "tr", ",");
			String check=CommonUtil.replace(ids[2], "checkbox"+operate, ",");
			int a=signDoctor(info, edit, blue, check);
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
		if("Order".equals(operate)){
			String [] paras=para.split("_o_p_i_t_");
			String[] ids=CommonUtil.getArrByString(paras[0], ";");
			String edit=CommonUtil.replace(ids[0], "tr", ",");
			String blue=CommonUtil.replace(ids[1], "tr", ",");
			String check=CommonUtil.replace(ids[2], "checkboxOrderPoint", ",");
			int a=signOrder(info, edit, blue, check);
			
			ids=CommonUtil.getArrByString(paras[1], ";");
			edit=CommonUtil.replace(ids[0], "tr", ",");
			blue=CommonUtil.replace(ids[1], "tr", ",");
			check=CommonUtil.replace(ids[2], "checkboxOrderItem", ",");
			int b=signOrderItem(info, edit, blue, check);
			
			String json="";
			if(a>0||b>0){
				json="[{\"result\":\"OK\"," +
				"\"method\":\"signTable\", " +
				"\"table\":\"Order\"}]";
			
			}else{
				json="[{\"result\":\"ERROR\"," +
				"\"method\":\"signTable\", " +
				"\"table\":\"Order\"}]";
			}
			response.getWriter().println(json);
		}
		if("Nurse".equals(operate)){
			String[] ids=CommonUtil.getArrByString(para, ";");
			String edit=CommonUtil.replace(ids[0], "tr", ",");
			String blue=CommonUtil.replace(ids[1], "tr", ",");
			String check=CommonUtil.replace(ids[2], "checkbox"+operate, ",");
			int a=signNurse(info, edit, blue, check);
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
		if("Family".equals(operate)){
			String[] ids=CommonUtil.getArrByString(para, ";");
			String edit=CommonUtil.replace(ids[0], "tr", ",");
			String blue=CommonUtil.replace(ids[1], "tr", ",");
			String check=CommonUtil.replace(ids[2], "checkbox"+operate, ",");
			int a=signFamily(info, edit, blue, check);
			String json="";
			if(a>0){
				json="[{\"result\":\"OK\"," +
				"\"method\":\"signTable\", " +
				"\"table\":\"Family\"}]";
			
			}else{
				json="[{\"result\":\"ERROR\"," +
				"\"method\":\"signTable\", " +
				"\"table\":\"Family\"}]";
			}
			response.getWriter().println(json);
		}
		if("Variation".equals(operate)){
			String[] ids=CommonUtil.getArrByString(para, ";");
			String edit=CommonUtil.replace(ids[0], "tr", ",");
			String blue=CommonUtil.replace(ids[1], "tr", ",");
			int a=signVariation(info, edit, blue);
			String json="";
			if(a>0){
				json="[{\"result\":\"OK\"," +
				"\"method\":\"signTable\", " +
				"\"table\":\"Variation\"}]";
			
			}else{
				json="[{\"result\":\"ERROR\"," +
				"\"method\":\"signTable\", " +
				"\"table\":\"Variation\"}]";
			}
			response.getWriter().println(json);
		}
	}
	
	private int signDoctor(RouteExecuteInfo info,String edit,String blue,String check){
		HashMap<String, String> map=Conversion.funGetSign(edit, blue, check, ",");
		DoctorTable table=new DoctorTable();
		String time=CommonUtil.getOracleToDate();
		int a=table.signTable(info, map, time, time);
		
		return a;
	}
	private int signNurse(RouteExecuteInfo info,String edit,String blue,String check){
		HashMap<String, String> map=Conversion.funGetSign(edit, blue, check, ",");
		NurseTable table=new NurseTable();
		String time=CommonUtil.getOracleToDate();
		int a=table.signTable(info, map, time, time);
		
		return a;
	}
	private int signOrder(RouteExecuteInfo info,String edit,String blue,String check){
		HashMap<String, String> map=Conversion.funGetSign(edit, blue, check, ",");
		OrderTable table=new OrderTable();
		String time=CommonUtil.getOracleToDate();
		int a=table.signTable(info, map, time, time);
		
		return a;
	}
	private int signOrderItem(RouteExecuteInfo info,String edit,String blue,String check){
		HashMap<String, String> map=Conversion.funGetSign(edit, blue, check, ",");
		OrderTable table=new OrderTable();
		String time=CommonUtil.getOracleToDate();
		int a=table.signTableItem(info, map, time, time);
		
		return a;
	}
	private int signFamily(RouteExecuteInfo info,String edit,String blue,String check){
		HashMap<String, String> map=Conversion.funGetSign(edit, blue, check, ",");
		FamilyTable table=new FamilyTable();
		String time=CommonUtil.getOracleToDate();
		int a=table.signTable(info, map, time, time);
		
		return a;
	}
	
	private int signVariation(RouteExecuteInfo info,String edit,String blue){
		ArrayList<String> list=Conversion.funGetSign(edit, blue, ",");
		VariationTable table=new VariationTable();
		String time=CommonUtil.getOracleToDate();
		int a=table.signTable(info, list,time, time);
		return a;
	}
	

}
