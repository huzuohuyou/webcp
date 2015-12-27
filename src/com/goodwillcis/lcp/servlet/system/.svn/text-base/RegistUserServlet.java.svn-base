/**----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：RegistUserServlet .java
// 文件功能描述：人员注册的servlet
// 创建人：黄杰
// 创建日期：2012/08/29
// 修改人：黄杰
// 修改日期：2012/09/18
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.servlet.system;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.LcpUtil;

public class RegistUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseClass db = LcpUtil.getDatabaseClass();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("UTF-8");
		String op = request.getParameter("op");
		if ("add".equals(op)) {//用户的添加方法
			addUserregistry(request, response);
		}
		if ("getUserList".equals(op)) {//获取所有用户
			getUserList(request, response);
		}
		if ("getFunsInfo".equals(op)) {//获取权限列表
			getFunsInfo(request, response);
		}
		if ("getUserFunsInfo".equals(op)) {//获取用户相应的权限列表
			getUserFunsInfo(request, response);
		}
		if ("del".equals(op)) {//删除用户
			delUserByID(request, response);
		}
		if ("isExist".equals(op)) {//用户是否存在
			isExist(request, response);
		}

		if ("edit".equals(op)) {//修改用户
			editUser(request, response);
		}
		if ("resetPassword".equals(op)) {//密码重置
			resetPassword(request, response);
		}
		if ("query".equals(op)) {//按条件查询用户
			queryByCondition(request, response);
		}
		response.setCharacterEncoding("UTF-8");

	}
	
	/**
	 * 按条件查询用户方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void queryByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String searchField = request.getParameter("radio");// 要查询的字段
		String searchString = request.getParameter("text");// 要查询的值
		if(!searchString.equals("")){
			searchString=URLDecoder.decode(searchString, "UTF-8");
		}
		//System.out.println(searchString);
		String sidx = request.getParameter("sidx");// 按sidx字段排序
		String sord = request.getParameter("sord");// 排序方式
		String sql = "select * from dcp_sys_user where sys_is_del = 0 ";
		if(searchField.equals("yf")){
			sql = sql + "and USER_NAME like '%" + searchString +"%'";
		}
		if(searchField.equals("dl")){
			sql = sql + "and USER_LOGIN like '%" + searchString +"%' ";
		}
		if(searchField.equals("ks")){
			sql = sql + "and DEPT_NAME like '%" + searchString +"%'";
		}
		sql = sql + "order by " + sidx + " " + sord ;
		int rowNum = 0;
		if (request.getParameter("rows") != null) {
			rowNum = Integer.parseInt(request.getParameter("rows"));// 每页显示多少页
		}
		String curPage = request.getParameter("page");// 当前页
		int sqlTotalRecords = db.FunGetRowCountBySql(sql);// 获取数据库里的总纪录，用于分页;
		int startIndex = (Integer.parseInt(curPage) - 1) * rowNum + 1;// 查询开始索引;
		JSONObject jsonObj = new JSONObject();
		JSONArray rows = new JSONArray();// 用于存放从数据库传过来的数据
		jsonObj.put("rows", rowNum);
		jsonObj.put("page", curPage);

		int endIndex = startIndex + rowNum;
		int totalPage = 0;// 获取纪录有多少页的变量
		if (sqlTotalRecords % rowNum == 0) {
			totalPage = sqlTotalRecords / rowNum;// 整页
		} else {
			totalPage = (sqlTotalRecords / rowNum) + 1;// 有余加1页;
		}
		DataSetClass ds = db.FunGetPageDataSetBySQL(sql, startIndex, endIndex);
		int count = ds.FunGetRowCount();
		jsonObj.put("total", totalPage); // 总页数
		jsonObj.put("records", sqlTotalRecords);// 设置页面的总纪录数

		for (int i = 0; i < count; i++) {
			JSONObject cell = new JSONObject();
			cell.put("USER_ID", ds.FunGetDataAsStringByColName(i, "USER_ID"));
			cell.put("USER_NAME", ds.FunGetDataAsStringByColName(i, "USER_NAME"));
			cell.put("USER_LOGIN", ds.FunGetDataAsStringByColName(i, "USER_LOGIN"));
			String state = ds.FunGetDataAsStringByColName(i, "USER_STATE");
			if(state.equals("1")){
				cell.put("USER_STATE", "有效");
			}else{
				cell.put("USER_STATE", "无效");
			}
			
			cell.put("DEPT_CODE", ds.FunGetDataAsStringByColName(i, "DEPT_CODE"));
			cell.put("DEPT_NAME", ds.FunGetDataAsStringByColName(i, "DEPT_NAME"));
			rows.add(cell);
		}
		jsonObj.put("rows", rows);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonObj);
	}

	/**
	 * 判断用户是否存在方法
	 * @param request
	 * @param response
	 * @throws IOException
	 *             loginName 返回 1 为存在该用户 0 为不存在
	 */
	private void isExist(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String queryDeptId = "select count(*) COUNTS from dcp_sys_user where user_login='" + request.getParameter("USER_LOGIN") + "'";
		int loginName = Integer.parseInt(db.FunGetDataSetBySQL(queryDeptId).FunGetDataAsStringById(0, 0));
		response.getWriter().print(loginName);
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 * 密码重置函数
	 */
	public void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userID = request.getParameter("userID");
		String resetSql = "UPDATE DCP_SYS_USER T  SET T.User_Password='12345' WHERE T.User_Id=" + userID;
		response.getWriter().print(db.FunRunSQLCommand(db.FunGetSvrKey(), resetSql));
	}

	/**
	 * 用户信息修改方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String userID =request.getParameter("USER_ID");
		String userName=request.getParameter("USER_NAME");
		if(!userName.equals("")){
			userName=URLDecoder.decode(userName, "UTF-8");
		}
		String userLogin = request.getParameter("USER_LOGIN");
		if(!userLogin.equals("")){
			userLogin=URLDecoder.decode(userLogin, "UTF-8");
		}
		String userState = request.getParameter("USER_STATE");
		if(!userState.equals("")){
			userState=URLDecoder.decode(userState, "UTF-8");
		}
		String deptCode = request.getParameter("DEPT_CODE");
		if(!deptCode.equals("")){
			deptCode=URLDecoder.decode(deptCode, "UTF-8");
		}
		String deptName =request.getParameter("DEPT_NAME");
		if(!deptName.equals("")){
			deptName=URLDecoder.decode(deptName, "UTF-8");
		}
		String funs=request.getParameter("FUNS");
		String sql = "UPDATE DCP_SYS_USER T " + "SET T.USER_LOGIN = '" + userLogin + "', " + "T.USER_NAME = '" + userName
				+ "', " + "T.USER_STATE= " + userState + "," + "T.SYS_LAST_UPDATE = SYSDATE," + "T.DEPT_CODE= " + deptCode + ","
				+ "T.DEPT_NAME= '" + deptName + "'" + "WHERE T.USER_ID = " + userID+ "\r\n"
				+ distribute(userID, funs);;
		response.getWriter().print(db.FunRunSqlByFile(sql.getBytes("GB2312")));

	}

	/**
	 * 注册新用户方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addUserregistry(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String userName=request.getParameter("USER_NAME");
		if(!userName.equals("")){
			userName=URLDecoder.decode(userName, "UTF-8");
		}
		String userLogin = request.getParameter("USER_LOGIN");
		if(!userLogin.equals("")){
			userLogin=URLDecoder.decode(userLogin, "UTF-8");
		}
		String userState = request.getParameter("USER_STATE");
		if(!userState.equals("")){
			userState=URLDecoder.decode(userState, "UTF-8");
		}
		String dept_id = request.getParameter("DEPT_CODE");
		if(!dept_id.equals("")){
			dept_id=URLDecoder.decode(dept_id, "UTF-8");
		}
		String dept_name =request.getParameter("DEPT_NAME");
		if(!dept_name.equals("")){
			dept_name=URLDecoder.decode(dept_name, "UTF-8");
		}
		String loginName = request.getParameter("USER_LOGIN");
		if(!loginName.equals("")){
			loginName=URLDecoder.decode(loginName, "UTF-8");
		}
		String pwd = request.getParameter("USER_PASSWORD");
		String funs = request.getParameter("FUNS");
		DataSetClass ds1 = db.FunGetDataSetBySQL("SELECT nvl(MAX(USER_ID )+1,0) AS USER_ID FROM DCP_SYS_USER");
		String userID = ds1.FunGetDataAsStringByColName(0, "USER_ID");// 获取最大ID
		String sql = "insert into dcp_sys_user(user_id, user_name, user_login, "
				+ "user_password, user_state, sys_is_del, sys_last_update,dept_code,dept_name) " + "values ( " + userID + ", '" + userName
				+ "', '" + loginName + "', '" + pwd + "', " + userState + ", 0, sysdate," + dept_id + ",'" + dept_name + "')" + "\r\n"
				+ distribute(userID, funs);
		response.getWriter().print(db.FunRunSqlByFile(sql.getBytes("GB2312")));
	}

	/**
	 * 删除用户及用户权限方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delUserByID(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String userID = request.getParameter("ids");
		String delSql = "DELETE FROM DCP_SYS_USER T WHERE T.User_Id=" + userID + "\n"
				+ "update dcp_sys_user set sys_is_del = 1 where user_id=" + userID + "\n";
		response.getWriter().print(db.FunRunSqlByFile(delSql.getBytes()));
	}

	/**
	 * 权限分配sql
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private String distribute(String userId, String selectId) {

		String sql = "delete from dcp_sys_power where user_id = " + userId + "\r\n";
		sql += "insert into DCP_SYS_POWER select " + userId + " as USER_ID, " + "FUNS_ID, 1 as POWER_STATE, 0 as SYS_IS_DEL, "
				+ "sysdate as SYS_LAST_UPDATE from dcp_sys_funs where " + "FUNS_ID IN (" + selectId + ")" + "\r\n";
		return sql;
	}
	
	
	/**
	 * 查询用户列表方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String sidx = request.getParameter("sidx");// 按sidx字段排序
		String sord = request.getParameter("sord");// 排序方式
		int rowNum = 0;
		if (request.getParameter("rows") != null) {
			rowNum = Integer.parseInt(request.getParameter("rows"));// 每页显示多少页
		}
		String curPage = request.getParameter("page");// 当前页
		int sqlTotalRecords = db.FunGetRowCountBySql("select * from dcp_sys_user where sys_is_del = 0");// 获取数据库里的总纪录，用于分页;
		int startIndex = (Integer.parseInt(curPage) - 1) * rowNum + 1;// 查询开始索引;
		JSONObject jsonObj = new JSONObject();
		JSONArray rows = new JSONArray();// 用于存放从数据库传过来的数据
		jsonObj.put("rows", rowNum);
		jsonObj.put("page", curPage);

		int endIndex = startIndex + rowNum;
		String sql = "select * from dcp_sys_user order by " + sidx + " " + sord;
		int totalPage = 0;// 获取纪录有多少页的变量
		if (sqlTotalRecords % rowNum == 0) {
			totalPage = sqlTotalRecords / rowNum;// 整页
		} else {
			totalPage = (sqlTotalRecords / rowNum) + 1;// 有余加1页;
		}
		DataSetClass ds = db.FunGetPageDataSetBySQL(sql, startIndex, endIndex);
		int count = ds.FunGetRowCount();
		jsonObj.put("total", totalPage); // 总页数
		jsonObj.put("records", sqlTotalRecords);// 设置页面的总纪录数

		for (int i = 0; i < count; i++) {
			JSONObject cell = new JSONObject();
			cell.put("USER_ID", ds.FunGetDataAsStringByColName(i, "USER_ID"));
			cell.put("USER_NAME", ds.FunGetDataAsStringByColName(i, "USER_NAME"));
			cell.put("USER_LOGIN", ds.FunGetDataAsStringByColName(i, "USER_LOGIN"));
			String state = ds.FunGetDataAsStringByColName(i, "USER_STATE");
			if(state.equals("1")){
				cell.put("USER_STATE", "有效");
			}else{
				cell.put("USER_STATE", "无效");
			}
			cell.put("DEPT_CODE", ds.FunGetDataAsStringByColName(i, "DEPT_CODE"));
			cell.put("DEPT_NAME", ds.FunGetDataAsStringByColName(i, "DEPT_NAME"));
			rows.add(cell);
		}
		jsonObj.put("rows", rows);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonObj);

	}

	/**
	 * 获取权限树的json
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getFunsInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		JSONObject jsonObj = new JSONObject();
		JSONArray arrys = new JSONArray();
		JSONArray arry = new JSONArray();
		String sql = "select * from dcp_sys_funs order by funs_id asc";
		DataSetClass ds = db.FunGetDataSetBySQL(sql);
		int rn = ds.FunGetRowCount();
		//设定两个变量 用来存放父级菜单
		int _id = 0;
		String _name = null;
		if (rn > 0) {
			for (int i = 0; i < rn; i++) {
				int id = Integer.parseInt(ds.FunGetDataAsStringByColName(i, "FUNS_ID"));
				String name = ds.FunGetDataAsStringByColName(i, "FUNS_NAME");
				if (i==0 ) { // 父级菜单
					_id=id;
					_name=name;
				} 
				if(id%10!=0) {// 子选项
					JSONObject cell = new JSONObject();
					cell.put("id", id);
					cell.put("text", name);
					arry.add(cell);
				}
				if (id % 10 == 0 && i!=0) {// 第一项单独列出
					jsonObj.put("id", _id);
					jsonObj.put("text", _name);
					jsonObj.put("state", "closed");
					jsonObj.put("children", arry);
					arrys.add(jsonObj);
					arry.clear();
					_id=id;
					_name=name;
				}
				if(i == rn-1){//最后父节点与子节点的插入
					jsonObj.put("id", _id);
					jsonObj.put("text", _name);
					jsonObj.put("state", "closed");
					jsonObj.put("children", arry);
					arrys.add(jsonObj);
				}
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(arrys);
	}
	/**
	 * 获取用户所拥有的权限树
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getUserFunsInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		JSONObject jsonObj = new JSONObject();
		JSONArray arrys = new JSONArray();
		JSONArray arry = new JSONArray();
		String sql = "	select f.*, nvl(p.power_state,0) power_state from  dcp_sys_funs f   left outer join " +
		"dcp_sys_power p on f.funs_id = p.funs_id and p.user_id = " + 
		request.getParameter("user_id") +
		" order by f.funs_id";
		DataSetClass ds = db.FunGetDataSetBySQL(sql);
		int rn = ds.FunGetRowCount();
		//设定两个变量 用来存放父级菜单
		int _id = 0;
		String _name = null;
		if (rn > 0) {
			for (int i = 0; i < rn; i++) {
				int id = Integer.parseInt(ds.FunGetDataAsStringByColName(i, "FUNS_ID"));
				int power = Integer.parseInt(ds.FunGetDataAsStringByColName(i, "POWER_STATE"));
				String name = ds.FunGetDataAsStringByColName(i, "FUNS_NAME");
				if (i==0 ) { // 父级菜单
					_id=id;
					_name=name;
				} 
				if(id%10!=0) {// 子选项
					JSONObject cell = new JSONObject();
					cell.put("id", id);
					cell.put("text", name);
					if(power>0){
						cell.put("checked", true);
					}
					arry.add(cell);
				}
				if (id % 10 == 0 && i!=0) {// 第一项单独列出
					jsonObj.put("id", _id);
					jsonObj.put("text", _name);
					jsonObj.put("state", "closed");
					jsonObj.put("children", arry);
					arrys.add(jsonObj);
					arry.clear();
					_id=id;
					_name=name;
				}
				if(i == rn-1){//最后父节点与子节点的插入
					jsonObj.put("id", _id);
					jsonObj.put("text", _name);
					jsonObj.put("state", "closed");
					jsonObj.put("children", arry);
					arrys.add(jsonObj);
				}
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(arrys);
	}

}
