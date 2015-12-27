/**----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：DistributionServlet .java
// 文件功能描述：路径列表的servlet
// 创建人：康榕元
// 创建日期：2011/08/01
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.servlet.cp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.model.PageTable;
import com.goodwillcis.lcp.model.cpmanage.CpPathmaintenance;
import com.goodwillcis.lcp.model.cpmanage.CpPathmaintenanceImpl;
import com.goodwillcis.lcp.model.cpmanage.Page;
import com.goodwillcis.lcp.util.LcpUtil;
import com.goodwillcis.lcp.util.PropertiesUtil;

public class PathMaintenanceServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PAGESIZE = 20;
	DatabaseClass dbc = LcpUtil.getDatabaseClass();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String op = request.getParameter("op");
		if ("chavs".equals(op)) {
			chavs(request, response);
		} else if ("del".equals(op)) {
			del(request, response);
		}
	}

	private void chavs(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		CpPathmaintenance cpPathmaintenance = new CpPathmaintenanceImpl();
		int pageNo = 1;

		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		} catch (Exception e) {
			pageNo = 1;
		}
		String sql = "";
		String bh = request.getParameter("bh");
		String mc = request.getParameter("mc");
		String py = request.getParameter("py");
		String wb = request.getParameter("wb");
		sql = cpPathmaintenance.funGetCpSql(bh, mc, py, wb);
		int totalRow = dbc.FunGetRowCountBySql(sql);
		Page page = new Page(totalRow, pageNo, PAGESIZE);
		int start = page.getStart();
		int end = page.getEnd();
		int nowPage = page.getNowPage();
		int totalPage = page.getTotolPage();
		String htmlTable = cpPathmaintenance.funGetCpTable(sql, start, end);
		PageTable pageTable = new PageTable();
		String pageHtml = pageTable.funGetPageHtml(totalRow, nowPage, totalPage);

		response.getWriter().println(
				"[{\"result\":\"OK\"," + "\"tableHtml\":\"" + htmlTable + "\","
						+ "\"pageHtml\":\"" + pageHtml + "\"}]");
		
	}

	private void del(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String ssid = request.getParameter("ssid");
		String sql = 
		"delete DCP_MASTER where cp_id = "+ssid+" \n "+
		"delete DCP_MASTER_ANTIBIOTICS where cp_id = "+ssid+" \n "+
		"delete DCP_MASTER_BASED where cp_id = "+ssid+" \n "+
		"delete DCP_MASTER_DISCHARGE where cp_id = "+ssid+" \n "+
		"delete DCP_MASTER_EXCLUDE where cp_id = "+ssid+" \n "+
		"delete DCP_MASTER_INCOME where cp_id = "+ssid+" \n "+
		"delete DCP_MASTER_NODE where cp_id = "+ssid+" \n "+
		"delete DCP_NODE_DOCTOR_ITEM where cp_id = "+ssid+" \n "+
		"delete DCP_NODE_FAMILY_POINT where cp_id = "+ssid+" \n "+
		"delete DCP_NODE_NURSE_ITEM where cp_id = "+ssid+" \n "+
		"delete DCP_NODE_NURSE_POINT where cp_id = "+ssid+" \n "+
		"delete DCP_NODE_ORDER_ITEM where cp_id = "+ssid+" \n "+
		"delete DCP_NODE_ORDER_POINT where cp_id = "+ssid+" \n "+
		"delete DCP_NODE_RELATE where cp_id = "+ssid+"";
		int res = dbc.FunRunSqlByFile(sql.getBytes());
		if (res > 0) {
			response.getWriter().println("true");
		} else {
			response.getWriter().println("false");
		}
	}
}
