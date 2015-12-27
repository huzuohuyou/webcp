/**----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：PermissionsServlet .java
// 文件功能描述：权限管理的servlet
// 创建人：康榕元
// 创建日期：2011/08/03
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.servlet.system;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.LcpUtil;

public class PermissionsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DatabaseClass db = LcpUtil.getDatabaseClass();
	String key = db.FunGetSvrKey();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if ("list".equals(op)) {
			list(request, response);
		} else if ("delete".equals(op)) {
			delete(request, response);
		} else if ("distribute".equals(op)) {
			distribute(request, response);
		} else if ("lock".equals(op)) {
			lock(request, response);
		} else if ("unlock".equals(op)) {
			unlock(request, response);
		}
	}

	/**
	 * 列表方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {
			String nkey = request.getParameter("nkey");
			String lkey = request.getParameter("lkey");
			String sql = "select * from dcp_sys_user where sys_is_del = 0";
			if (!"".equals(nkey))
				sql += " and user_name like '%" + nkey + "%'";
			if (!"".equals(lkey))
				sql += " and user_login like '%" + lkey + "%'";
			sql += " order by user_id";

			DataSetClass ds = db.FunGetDataSetBySQL(sql);
			request.setAttribute("list", ds);
			request.getRequestDispatcher("../system/list.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("list", null);
			request.getRequestDispatcher("../system/list.jsp").forward(request, response);
		}
	}

	/**
	 * 冻结方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void lock(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {
			String userId = request.getParameter("userid");
			String sql = "update DCP_SYS_USER set user_state = 0 where user_id = " + userId;

			int result = db.FunRunSQLCommand(key, sql);
			if (result > 0)
				response.getWriter().println("ok");
			else
				response.getWriter().println("error");
		} catch (Exception e) {
			response.getWriter().println("error");
		}
	}

	/**
	 * 冻结方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void unlock(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {
			String userId = request.getParameter("userid");
			String sql = "update DCP_SYS_USER set user_state = 1 where user_id = " + userId;

			int result = db.FunRunSQLCommand(key, sql);
			if (result > 0)
				response.getWriter().println("ok");
			else
				response.getWriter().println("error");
		} catch (Exception e) {
			response.getWriter().println("error");
		}
	}

	/**
	 * 权限分配方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void distribute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {

			String userId = request.getParameter("userid");
			String selectId = request.getParameter("selectid");
			selectId = selectId.substring(1);
			
			String sql = "delete from dcp_sys_power where user_id = " + userId + "\r\n";
			sql += "insert into DCP_SYS_POWER select " + userId + " as USER_ID, " + 
					"FUNS_ID, 1 as POWER_STATE, 0 as SYS_IS_DEL, " +
					"sysdate as SYS_LAST_UPDATE from dcp_sys_funs where " +
					"FUNS_ID IN (" + selectId + ")";

			int result = db.FunRunSqlByFile(sql.getBytes("GB2312"));
			if (result > 0)
				response.getWriter().println("ok");
			else
				response.getWriter().println("error");
		} catch (Exception e) {
			response.getWriter().println("error");
		}
	}

	/**
	 * 删除用户方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {
			String selectId = request.getParameter("selectid");
			selectId = selectId.substring(1);

			String sql = "delete dcp_sys_power where user_id in (" + selectId + ")\r\n";
			sql += "update dcp_sys_user set sys_is_del = 1 where user_id in (" + selectId + ")";

			int result = db.FunRunSqlByFile(sql.getBytes("GB2312"));
			if (result > 0) response.getWriter().print("ok");
			else response.getWriter().print("error");
		} catch (Exception e) {
			response.getWriter().println("error");
		}
	}
}
