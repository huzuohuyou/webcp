/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：Variation.java    
// 文件功能描述：生成变异信息选项
//
// 创建人：潘状
// 创建日期：2011-08-01
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.servlet.patient;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodwillcis.lcp.model.DataSet;

/**
 * Servlet implementation class Variation
 */
@WebServlet("/Variation")
public class VariationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String variationType=request.getParameter("variation_type");//处理变异信息类别选择框
		response.getWriter().println(this.getVariation(variationType));
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	public String getVariation(String type){
		String selectName="";
		
			if(type.equals("variationExit")){
				selectName="variationExit";
				type="V3";
			}if(type.equals("variationExit1")){
				selectName="variationExit1";
				type="V3";
			}			
			else if(type.equals("variationNode")){
				selectName="variationNode";
				type="V4";
			}
		
		String variationTypeHtml=" <select name='"+selectName+"' onchange=\"varation('"+selectName+"')\"><option value='0' selected='selected'>请选择普通节点变异类型</option>";
		String sql= "select VARIATION_CODE,VARIATION_NAME from dcp_dict_variation where variation_type_big='"+
					type+"'";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String code=dataSet.funGetFieldByCol(i, "VARIATION_CODE");
			String name=dataSet.funGetFieldByCol(i, "VARIATION_NAME");
			variationTypeHtml=variationTypeHtml+" <option value='"+code+"'>"+name+"</option>";
		}
		return variationTypeHtml+"</select>";
	}
	

}
