/**---------------------------------------------------------------- 
 * // Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
 *  // 文件名： LogServlet.java
 *  // 文件功能描述：登录日志及框架部分功能的servlet类 
 *  // 创建人：周伟彬 
 *  // 创建日期：2012/08/28 
 *  // 修改人：黄杰
 *  // 创建日期：2012/10/12
 *  // ----------------------------------------------------------------*/

package com.goodwillcis.lcp.servlet.system;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.goodwillcis.lcp.util.LcpUtil;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;

/**
 * Servlet implementation class LogServlet
 */
@WebServlet("/servlet/logServlet")
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = request.getParameter("op");

		if("getlogin".equals(op)){
			getLoginInfo(request,response);
		}

	}	
	
	public void getLoginInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String sidx = req.getParameter("sidx");//要搜索的行
		String sDate = req.getParameter("s");//开始日期;
		String eDate = req.getParameter("e");//结束日期;
		sidx=(sidx==null||sidx==""?"login_time":sidx);
		String sord = req.getParameter("sord");//排序方式
		boolean _search=Boolean.valueOf(req.getParameter("_search"));//是否查询
		String addSql="";
        if(_search){
        	String searchField=req.getParameter("searchField");//查询的列
        	String searchString=req.getParameter("searchString");//查询的内容
        	String searchOper=req.getParameter("searchOper");//查询方式
        	if("cn".equals(searchOper)){
        		addSql=" and "+searchField+" like '%"+searchString+"%'";
        	}
        	if("eq".equals(searchOper)){
        		addSql=" and "+searchField+" = '"+searchString+"'";
        	}
        }
		int pageRows = Integer.valueOf(req.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(req.getParameter("page")).intValue();// 当前页数
		int start = pageRows * (nowPage - 1) + 1;
		int end = pageRows * nowPage;
		
		String sql = "select user_id,user_name,dept_code,dept_name,user_login,ip,to_char(login_time,'yyyy-MM-dd HH24:mi:ss') login_time,to_char(logout_time,'yyyy-MM-dd HH24:mi:ss') logout_time" +
				" from dcp_user_login_log t where 1=1 and t.login_time between  to_date('"+sDate+"', 'YYYY-MM-DD') and to_date('"+eDate+"', 'YYYY-MM-DD') "+addSql+" order by  "+sidx+" "+sord;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dc = db.FunGetPageDataSetBySQL(sql, start, end);

		JSONObject jsonObj = new JSONObject();
		int rowCount = db.FunGetRowCountBySql(sql);
		int countPage=rowCount / pageRows;
		if(rowCount%pageRows!=0){
			countPage++;
		}
		// 根据jqGrid对JSON的数据格式要求给jsonObj赋值
		jsonObj.put("page", nowPage); // 当前页
		jsonObj.put("total", countPage); // 总页数
		jsonObj.put("records", rowCount); // 总记录数

		// 定义rows，存放数据
		JSONArray rows = new JSONArray();
		if (dc.FunGetDataAsStringById(0, 0) != "") {
			for (int i = 0; i < dc.FunGetRowCount(); i++) {
				String user_id = dc.FunGetDataAsStringById(i, 0);
				String user_name = dc.FunGetDataAsStringById(i, 1);
				String dept_code = dc.FunGetDataAsStringById(i, 2);
				String dept_name = dc.FunGetDataAsStringById(i, 3);
				String user_login = dc.FunGetDataAsStringById(i, 4);
				String ip=dc.FunGetDataAsStringById(i, 5);
				String login_time = dc.FunGetDataAsStringById(i, 6);
				String logout_time = dc.FunGetDataAsStringById(i, 7);

				// 放入数据
				JSONObject cell = new JSONObject();
				cell.put("user_id", user_id);
				cell.put("user_name", user_name);
				cell.put("dept_code", dept_code);
				cell.put("dept_name", dept_name);
				cell.put("user_login", user_login);
				cell.put("ip", ip);
				cell.put("login_time", login_time);
				cell.put("logout_time", logout_time);

				// 将该记录放入rows中
				rows.add(cell);

			}

			// 将rows放入json对象中
			jsonObj.put("rows", rows);

			// 设置字符编码
			resp.setCharacterEncoding("UTF-8");
			// 返回json对象（通过PrintWriter输出）
			resp.getWriter().print(jsonObj);

		}
	}
}
