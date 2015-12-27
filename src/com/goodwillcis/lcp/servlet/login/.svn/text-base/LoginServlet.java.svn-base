/**---------------------------------------------------------------- 
 * // Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
 *  // 文件名： LoginServlet.java
 *  // 文件功能描述：登录及框架部分功能的servlet类 
 *  // 创建人：潘状
 *  // 创建日期：2012/02/28 
 *  // ----------------------------------------------------------------*/

package com.goodwillcis.lcp.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.LcpUtil;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		String op = request.getParameter("op");
		if ("login".equals(op)) {
			login(request, response);
		} else if ("quit".equals(op)) {
			quit(request, response);
		} else if ("password".equals(op)) {
			password(request, response);
		} else if ("changePassword".equals(op)) {
			changePassword(request, response);
		} else if ("resetPassword".equals(op)) {
			resetPassword(request, response);
		} else if ("isSessionExpired".equals(op)) {
			isSessionExpired(request, response);
		}else if ("isExecute".equals(op)) {
			isExecute(request, response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dataSet = new DataSetClass();

		// 取出页面传过来的参数
		String user = request.getParameter("textfield");
		String pwd = request.getParameter("textfield2");
		String code = request.getParameter("textfield3");
		HttpSession session = request.getSession();
		String rand = (String) session.getAttribute("rand");

		if (!code.equals(rand)) {
			request.setAttribute("error", "对不起！验证码输入错误或已过期！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			String sql = "select USER_ID,USER_NAME,USER_STATE,DEPT_CODE,DEPT_NAME  from DCP_SYS_USER where USER_LOGIN = '" + user
					+ "' and USER_PASSWORD = '" + pwd + "' and SYS_IS_DEL != 1";
			dataSet = db.FunGetDataSetBySQL(sql);
			Number state = dataSet.FunGetDataAsNumberByColName(0, "USER_STATE");
			if (state == null) {
				request.setAttribute("error", "用户名或密码错误！");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else if (state.intValue() == 0) {
				request.setAttribute("error", "您的帐户已被冻结，请联系管理员解冻！");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				String userID = dataSet.FunGetDataAsNumberByColName(0, "USER_ID").toString();
				String userName = dataSet.FunGetDataAsStringByColName(0, "USER_NAME");
				String deptCode = dataSet.FunGetDataAsStringByColName(0, "DEPT_CODE");
				String deptName = dataSet.FunGetDataAsStringByColName(0, "DEPT_NAME");
				// 增加登录日志记录功能
				String inserLog = "insert into dcp_user_login_log (USER_ID, USER_NAME, USER_LOGIN, IP, LOGIN_TIME,DEPT_CODE,DEPT_NAME) values(" + userID + ",'"
						+ userName + "','" + user + "','" + getIpAddr(request) + "',sysdate,'"+deptCode+"','"+deptName+"')";
				db.FunRunSQLCommand(db.FunGetSvrKey(), inserLog);
				session.setAttribute("userid", userID);
				session.setAttribute("username", userName);
				session.setAttribute("deptcode", deptCode);
				session.setAttribute("deptname", deptName);
				response.getWriter().println("<script type=\"text/javascript\">top.location.href=\"frames/main.jsp\";</script>");
			}
		}
	}

	private void quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		String userID="";
		try {
			userID = session.getAttribute("userid").toString();
		} catch (Exception e) {
			
		}
		if(userID!=null&&!userID.equals("")){
			String insertLog = "update dcp_user_login_log s set s.logout_time=sysdate where s.user_id=" + userID
			+ " and s.login_time=(select max(t.login_time) from dcp_user_login_log t where t.user_id=" + userID + ") ";
			db.FunRunSQLCommand(db.FunGetSvrKey(), insertLog);
		}
		session.invalidate();
		response.getWriter().println("<script type=\"text/javascript\">top.location.href=\"login.jsp\";</script>");
	}

	private void password(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dataSet = new DataSetClass();
		String user = session.getAttribute("userid").toString();
		String pwd = request.getParameter("mima");
		String sql = "select USER_ID from DCP_SYS_USER where USER_ID = '" + user + "' and USER_PASSWORD = '" + pwd + "'";

		dataSet = db.FunGetDataSetBySQL(sql);

		if (dataSet.FunGetDataAsNumberById(0, 0) == null) {
			response.getWriter().print("true");
		} else {
			response.getWriter().print("false");
		}
	}

	private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user = session.getAttribute("userid").toString();
		String pwd = request.getParameter("txt3");
		String sql = "update dcp_sys_user set user_password = '" + pwd + "' where user_id=" + user;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		int res = db.FunRunSQLCommand(db.FunGetSvrKey(), sql);
		if (res > 0) {
			request.setAttribute("res", "恭喜！您的密码修改成功！");
			request.getRequestDispatcher("login/changepwd_1.jsp").forward(request, response);
		} else {
			request.setAttribute("res", "对不起！您的密码修改失败！");
			request.getRequestDispatcher("login/changepwd_1.jsp").forward(request, response);
		}
	}

	private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("userid");
		String pwd = request.getParameter("txt3");
		String sql = "update dcp_sys_user set user_password = '" + pwd + "' where user_id=" + user;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		int res = db.FunRunSQLCommand(db.FunGetSvrKey(), sql);
		if (res > 0) {
			response.getWriter().println("true");
		} else {
			response.getWriter().println("false");
		}
	}

	/**
	 * 潘状 2012年8月28日 新加用户登录日志功能，该方法为获取用户真实ip
	 * 
	 * @param request
	 * @return 用户真实ip
	 */
	protected String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 潘状 2012年8月28日 判断session是否过期 过期返回0
	 * 
	 * @param req
	 * @param rsp
	 * @throws IOException
	 */
	protected void isSessionExpired(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("userid");
		String user = "";
		if (obj == null || obj.equals("")) {
			rsp.getWriter().println("0");
			return;
		} else {
			user = obj.toString();
		}

		if (user == null || user.equals("")) {// 过期返回0
			rsp.getWriter().println("0");
		} else {
			rsp.getWriter().println("1");

		}

	}
	
	private void isExecute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String userID=(String) request.getSession().getAttribute("userid");
		String deptname=(String) request.getSession().getAttribute("deptname");
		String usersql = "select funs_id from dcp_sys_power where user_id="+userID;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dataSet = db.FunGetDataSetBySQL(usersql);
		String id="";
		String funid="";
		for(int i=0;i<dataSet.FunGetRowCount();i++){
			id=dataSet.FunGetDataAsStringByColName(i,"FUNS_ID");
			funid+=id;
		}
		if(!funid.contains("61")){
			String sql="select * from lcp_patient_visit t where t.discharged_date is not null and t.cp_state=1 and t.dept_admission_to like '"+deptname.trim()+"%' ";
			DataSetClass ds=db.FunGetDataSetBySQL(sql);
			int rows=ds.FunGetRowCount();
			if(rows>0){
				response.getWriter().print(rows);
			}else{
					response.getWriter().print(0);
				 }
		}else{
				response.getWriter().print(0);
			}
	}
}
