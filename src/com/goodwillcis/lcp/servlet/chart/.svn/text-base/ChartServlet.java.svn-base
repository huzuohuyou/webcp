/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：ChartServlet .java
// 文件功能描述：fusionCharts生成路径图表的servlet
// 创建人：段英华 
// 创建日期：2011/07/29
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.servlet.chart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodwillcis.lcp.service.chart.ChartService;
import com.goodwillcis.lcp.service.chart.impl.ChartServiceImpl;

public class ChartServlet extends HttpServlet {
	
	private static final long serialVersionUID = -4306405400231972536L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 接收前台AJAX请求，返回XML数据给页面。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ChartService chartService = new ChartServiceImpl();
		String   id  =  request.getParameter("cpId");
		String dataXml = chartService.getCpDataXML(id);
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(dataXml);
	}

}
