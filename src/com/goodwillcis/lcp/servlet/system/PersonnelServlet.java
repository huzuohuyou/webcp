/**----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：PersonnelServlet .java
// 文件功能描述：人员注册的servlet
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

public class PersonnelServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DatabaseClass db = LcpUtil.getDatabaseClass();
	String key = db.FunGetSvrKey();
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String op = request.getParameter("op");

		if("check".equals(op)){
			check(request, response);
		}else if("registry".equals(op)){
			registry(request,response);
		}
	}

	/**
	 * 用户重复检查方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void check(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		try
		{
			String un = request.getParameter("un");
			String ln = request.getParameter("ln");
			String sql = "select user_id from dcp_sys_user where ";
			if (un != null) sql += "user_name = '" + un + "'";
			if (ln != null) sql += "user_login = '" + ln + "'";
			sql += " and sys_is_del = 0";
			DataSetClass dataSet = db.FunGetDataSetBySQL(sql);
			int result = dataSet.FunGetRowCount();
			if(result == 0) response.getWriter().print("ok");
			else response.getWriter().print("error");
		} catch (Exception e) {
			response.getWriter().print("error");
		}
	}

	/**
	 * 用户注册方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void registry(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {

			// 基本信息
			String userName = request.getParameter("un");
			String loginName = request.getParameter("ln");
			String pwd = request.getParameter("pwd");
			String dept_name = request.getParameter("dept_name");
			String dept_id = request.getParameter("dept_id");
			// 权限
			String selectId = request.getParameter("selectid");

			String sql = "insert into dcp_sys_user(user_id, user_name, user_login, " +
						"user_password, user_state, sys_is_del, sys_last_update,dept_code,dept_name) " +
						"values ((select max(user_id) from DCP_SYS_USER ) + 1, '" +
						userName + "', '" + loginName + "', '"+pwd+"', 1, 0, sysdate,'"+dept_id+"','"+dept_name+"')";
			int result = db.FunRunSQLCommand(key, sql);
			
			if (result > 0){
				if(!"".equals(selectId)) {
					sql = "select user_id from DCP_SYS_USER where USER_LOGIN = '" + 
									loginName + "'";
					DataSetClass dataSet = db.FunGetDataSetBySQL(sql);
					int userId = Integer.parseInt(dataSet
							.FunGetDataAsStringByColName(0, "USER_ID"));
					sql = "";
					selectId = selectId.substring(1);
					sql = "insert into DCP_SYS_POWER select " + userId + " as USER_ID, " + 
							"FUNS_ID, 1 as POWER_STATE, 0 as SYS_IS_DEL, " +
							"sysdate as SYS_LAST_UPDATE from dcp_sys_funs where " +
							"FUNS_ID IN (" + selectId + ")";
					result = db.FunRunSQLCommand(key, sql);
					if(result > 0)
						response.getWriter().print("ok");
					else
						response.getWriter().print("error");
				} else
					response.getWriter().print("ok");
			} else
				response.getWriter().print("error");
		} catch (Exception e) {
			response.getWriter().print("error");
		}
	}
}
