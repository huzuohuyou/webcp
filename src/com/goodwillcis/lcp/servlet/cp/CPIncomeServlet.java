/**----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：CPIncomeServlet .java
// 文件功能描述：纳入路径初步诊断修改的servlet
// 创建人：黄杰
// 创建日期：2012/10/19
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.servlet.cp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.model.Page;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

@WebServlet("/CPIncomeServlet")
public class CPIncomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseClass db = LcpUtil.getDatabaseClass(); 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op = request.getParameter("op");
		if("getIncomeList".equals(op)){//获取病人的初步诊断的方法入口
			getIncomeList(request, response);
		}
		if("edit".equals(op)){//初步诊断与手术修改方法入口
			editLocalCode(request, response);
		}
	}
	
	/**
	 * 修改诊断码与手术的方法
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author 黄杰
	 */
	private void editLocalCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String patientNO = request.getParameter("PATIENT_NO");//病人号
		String localCode = request.getParameter("LOCAL_CODE");
		String localName = request.getParameter("LOCAL_NAME");
		String incomeCode = request.getParameter("INCOME_CODE");
		String incomeType = request.getParameter("INCOME_TYPE");//判断是手术OR诊断进行修改
		String updateSql = "update LCP_PATIENT_LOG_INCOME t set t.local_code = '"+localCode+"',t.local_name='"+localName+"',t.income_code='"+incomeCode+"',t.sys_last_update="+CommonUtil.getOracleToDate()+" where t.patient_no='"+patientNO+"' and t.local_key = '2_1_0_初步诊断'";
		if(incomeType.equals("手术")){
			updateSql = "update LCP_PATIENT_LOG_INCOME t set t.local_code = '"+localCode+"',t.local_name='"+localName+"',t.income_code='"+incomeCode+"',t.sys_last_update="+CommonUtil.getOracleToDate()+" where t.patient_no='"+patientNO+"'";
		}
		int result = db.FunRunSQLCommand(db.FunGetSvrKey(),updateSql);
		if(result > 0){
			response.getWriter().print("success");
		}else{
			response.getWriter().print("error");
		}
	}

	/**
	 * 获取病人的初步诊断相关信息的方法
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author 黄杰
	 */
	private void getIncomeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int curPage = 1;//显示第几页内容
		int rowNum = 20;//一页显示多少条纪录
		try{
		 curPage = Integer.parseInt(request.getParameter("page"));//当前页
		 rowNum = Integer.parseInt(request.getParameter("rows"));//总纪录条数
		}catch(Exception e){
			e.printStackTrace();
		}
		String audit = request.getParameter("audit");
		List<String> list = getSqlAndCount(request, response);//默认查询诊断的sql生成方法
		if(audit.equals("2")){
			list = getOpSqlAndCount(request, response);//生成手术的sql方法;
		}
		String listSqlCount = list.get(1);
		String listSql = list.get(0);
		DataSetClass dataCount = db.FunGetDataSetBySQL(listSqlCount);
		int totalCount = Integer.parseInt(dataCount.FunGetDataAsStringById(0, 0));//根据sql查询总纪录条数
		Page page= new Page(totalCount, curPage, rowNum);//对数据集进行分页类
		int start = page.getStart();
		int end = page.getEnd();
		int nowPage = page.getNowPage();
		int totalPage = page.getTotolPage();
		JSONObject json = getJson(listSql,start, end, nowPage, totalPage, totalCount, rowNum);//根据sql查询，并返回为json对象
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
	}
	
	/**
	 * 获取病人的初步诊断相关信息的JSON方法
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author 黄杰
	 */
	private JSONObject getJson(String sql, int start, int end, int nowPage, int totalPage,int totalCount, int rowNum) {
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql, start, end);
		int row = dataSet.getRowNum();//获取当前页查询结果的纪录条数
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("rows", rowNum);//每页显示多少条纪录
		jsonObj.put("page", nowPage);//当前页
		jsonObj.put("total", totalPage); // 总页数
		jsonObj.put("records", totalCount);// 设置总共页面的总纪录数
		JSONArray rows = new JSONArray();
		for (int i = 0; i < row; i++) {
			JSONObject cell = new JSONObject();
			cell.put("PATIENT_NO",dataSet.funGetFieldByCol(i, "PATIENT_NO"));
			cell.put("LOCAL_CODE", dataSet.funGetFieldByCol(i, "LOCAL_CODE"));
			cell.put("LOCAL_NAME",dataSet.funGetFieldByCol(i, "LOCAL_NAME"));
			cell.put("INCOME_CODE", dataSet.funGetFieldByCol(i, "INCOME_CODE"));
			cell.put("ADMISSION_DATE", dataSet.funGetFieldByCol(i, "ADMISSION_DATE"));
			cell.put("INCOME_TYPE", dataSet.funGetFieldByCol(i, "INCOME_TYPE"));
			cell.put("DEPT_ADMISSION_TO", dataSet.funGetFieldByCol(i, "DEPT_ADMISSION_TO"));
			cell.put("PATIENT_NAME", dataSet.funGetFieldByCol(i, "PATIENT_NAME"));
			rows.add(cell);
		}
		jsonObj.put("rows", rows);
		return jsonObj;
	}

	/**
	 * 按条件生成手术sql语句方法
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author 黄杰
	 */
	private List<String> getOpSqlAndCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> list = new ArrayList<String>();
		String s = request.getParameter("s");//开始日期
		String e1 = request.getParameter("e");//结束日期
		String sidx = request.getParameter("sidx");//排序字段
		String sord = request.getParameter("sord");//排序方式
		String searchString = request.getParameter("radio");//查询的方式，有按病人号，诊断码及科室
		String searchValue = request.getParameter("text");//查询内容
		String auditValue = "手术";//查询的查为手术
		HttpSession session=request.getSession();
		String userID=(String)session.getAttribute("userid");//通过session获取当前用户的ID
		String deptName = (String)session.getAttribute("deptname");//获取科室名称
		String listSql = "";
		String listSqlCount = "";
		String usersql = "select funs_id from dcp_sys_power where user_id="+userID;
		DataSetClass dataSet = db.FunGetDataSetBySQL(usersql);
		String id="";
		String funid="";
		for(int i=0;i<dataSet.FunGetRowCount();i++){
		id=dataSet.FunGetDataAsStringByColName(i,"FUNS_ID");
		funid+=id;
		}
		if(funid.contains("61")){//判断是否有管理员权限 
			if(searchValue == null){
				listSqlCount = "select count(*) count from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v " +
						       "where t.income_type='"+auditValue+"' and t.patient_no = v.patient_no " +
							   "and v.admission_date >= to_date('"+s+"','yyyy-mm-dd') and v.admission_date <= to_date('"+e1+"', 'yyyy-mm-dd') ";
				listSql = "select t.patient_no,t.local_code,t.local_name,t.income_code,v.admission_date,t.income_type," +
						  "t.local_key,v.dept_admission_to,v.patient_name from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v " +
						  "where t.income_type='"+auditValue+"' and t.patient_no = v.patient_no " +
						  "and v.admission_date >= to_date('"+s+"','yyyy-mm-dd') and v.admission_date <= to_date('"+e1+"', 'yyyy-mm-dd')  " +
						  "order by " + sidx +" " + sord;
			}else{
				if(searchString.equals("brh")){
					searchString = "t.patient_no";
				}if(searchString.equals("cbzdm")){
					searchString = "t.local_code";
				}if(searchString.equals("ksmc")){
					searchString = "v.execute_dept";
				}
				listSql = "select t.patient_no,t.local_code,t.local_name,t.income_code,v.admission_date,t.income_type,t.local_key," +
						  "v.dept_admission_to,v.patient_name from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v " +
						  "where t.income_type='"+auditValue+"' and v.patient_no = t.patient_no " +
						  "and "+searchString+" like '"+searchValue.toUpperCase()+"%' and  v.admission_date >= to_date('"+s+"','yyyy-mm-dd') " +
						  "and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss')  order by " + sidx +" " + sord;
				listSqlCount = "select count(*) count from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v " +
							   "where t.income_type='"+auditValue+"' and t.patient_no = v.patient_no  " +
							   "and v.admission_date >= to_date('"+s+"','yyyy-mm-dd') and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') " +
							   "and  "+searchString+" like '"+searchValue.toUpperCase()+"%'" ;
			}
		}else{
			if(searchValue == null){//如果没有搜索项值为空，则执行下列语句
				listSqlCount = "select count(*) count from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v " +
							   "where t.income_type='"+auditValue+"' and t.patient_no = v.patient_no and v.execute_dept='"+deptName+"' " +
							   "and v.admission_date >= to_date('"+s+"','yyyy-mm-dd') and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') ";
				listSql = "select t.patient_no,t.local_code,t.local_name,t.income_code,v.admission_date,t.income_type,t.local_key," +
						  "v.dept_admission_to,v.patient_name from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v " +
						  "where t.income_type='"+auditValue+"' and t.patient_no = v.patient_no and v.execute_dept='"+deptName+"' " +
						  "and v.admission_date >= to_date('"+s+"','yyyy-mm-dd') and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') " +
						  "order by " + sidx +" " + sord;
			}else{
				if(searchString.equals("brh")){
					searchString = "t.patient_no";
				}if(searchString.equals("cbzdm")){
					searchString = "t.local_code";
				}
				listSql = "select t.patient_no,t.local_code,t.local_name,t.income_code,v.admission_date,t.income_type,t.local_key," +
						  "v.dept_admission_to,v.patient_name from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v " +
						  "where t.income_type='"+auditValue+"' and v.patient_no = t.patient_no and v.execute_dept='"+deptName+"' " +
						  "and "+searchString+" like '"+searchValue.toUpperCase()+"%' and  v.admission_date >= to_date('"+s+"','yyyy-mm-dd') " +
						  "and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss')  order by " + sidx +" " + sord;
				listSqlCount = "select count(*) count from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v " +
						       "where t.income_type='"+auditValue+"' and t.patient_no = v.patient_no and v.execute_dept='"+deptName+"' " +
							   "and v.admission_date >= to_date('"+s+"','yyyy-mm-dd') and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') " +
							   "and  "+searchString+" like '"+searchValue.toUpperCase()+"%'  " ;
				if(searchString.equals("ksmc")){
					searchString = "v.execute_dept";
					searchValue = deptName;
					listSql = "select t.patient_no,t.local_code,t.local_name,t.income_code,v.admission_date,t.income_type,t.local_key," +
							  "v.dept_admission_to,v.patient_name from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v " +
							  "where t.income_type='"+auditValue+"' and v.patient_no = t.patient_no and v.execute_dept='"+deptName+"' " +
							  "and  v.admission_date >= to_date('"+s+"','yyyy-mm-dd') and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss')  " +
							  "order by " + sidx +" " + sord;
					listSqlCount = "select count(*) count from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v " +
							       "where t.income_type='"+auditValue+"' and t.patient_no = v.patient_no and v.execute_dept='"+deptName+"' " +
								   "and v.admission_date >= to_date('"+s+"','yyyy-mm-dd') and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss')  " ;
				}
			}
		}
		list.add(0, listSql);
		list.add(1, listSqlCount);
		return list;
	}

	/**
	 * 按条件生成诊断sql语句方法
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author 黄杰
	 */
	private List<String> getSqlAndCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> list = new ArrayList<String>();
		String s = request.getParameter("s");//开始日期
		String e1 = request.getParameter("e");//结束日期
		String sidx = request.getParameter("sidx");//排序字段
		String sord = request.getParameter("sord");//排序方式
		String searchString = request.getParameter("radio");
		String searchValue = request.getParameter("text");
		String auditValue = "诊断";//按诊断查询
		HttpSession session=request.getSession();//通过session获取用户ID和科室名称
		String userID=(String)session.getAttribute("userid");
		String deptName = (String)session.getAttribute("deptname");
		String listSql = "";
		String listSqlCount = "";
		String usersql = "select funs_id from dcp_sys_power where user_id="+userID;
		DataSetClass dataSet = db.FunGetDataSetBySQL(usersql);
		String id="";
		String funid="";
		for(int i=0;i<dataSet.FunGetRowCount();i++){
		id=dataSet.FunGetDataAsStringByColName(i,"FUNS_ID");
		funid+=id;
		}
		if(funid.contains("61")){//判断是否有管理员权限 
			if(searchValue == null){//如果没有搜索项值为空，则执行下列语句
				listSqlCount = "select count(*) count from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v " +
						       "where t.income_type='"+auditValue+"' and t.patient_no = v.patient_no " +
							   "and v.admission_date >= to_date('"+s+"','yyyy-mm-dd') and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') " +
							   "and t.local_key = '2_1_0_初步诊断'";
				listSql = "select t.patient_no,t.local_code,t.local_name,t.income_code,v.admission_date,t.income_type,t.local_key," +
						  "v.dept_admission_to,v.patient_name from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v " +
						  "where t.income_type='"+auditValue+"' and t.patient_no = v.patient_no " +
						  "and v.admission_date >= to_date('"+s+"','yyyy-mm-dd') and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') " +
						  "and t.local_key = '2_1_0_初步诊断' order by " + sidx +" " + sord;
			}else{
				if(searchString.equals("brh")){
					searchString = "t.patient_no";
				}if(searchString.equals("cbzdm")){
					searchString = "t.local_code";
				}if(searchString.equals("ksmc")){
					searchString = "v.execute_dept";
				}
				listSql = "select t.patient_no,t.local_code,t.local_name,t.income_code,v.admission_date,t.income_type,t.local_key," +
						  "v.dept_admission_to,v.patient_name from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v " +
						  "where t.income_type='"+auditValue+"' and v.patient_no = t.patient_no and "+searchString+" " +
						  "like '"+searchValue.toUpperCase()+"%' and  v.admission_date >= to_date('"+s+"','yyyy-mm-dd') " +
						  "and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') and t.local_key = '2_1_0_初步诊断'" +
						  " order by " + sidx +" " + sord;
				listSqlCount = "select count(*) count from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v where t.income_type='"+auditValue+"' " +
						       "and t.patient_no = v.patient_no  and v.admission_date >= to_date('"+s+"','yyyy-mm-dd') " +
							   "and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') and  "+searchString+" " +
							   "like '"+searchValue.toUpperCase()+"%' and t.local_key = '2_1_0_初步诊断' " ;
			}
		}else{
			if(searchValue == null){
				listSqlCount = "select count(*) count from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v where t.income_type='"+auditValue+"' " +
						       "and t.patient_no = v.patient_no and v.execute_dept='"+deptName+"' " +
							   "and v.admission_date >= to_date('"+s+"','yyyy-mm-dd') and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') " +
							   "and t.local_key = '2_1_0_初步诊断'";
				listSql = "select t.patient_no,t.local_code,t.local_name,t.income_code,v.admission_date,t.income_type,t.local_key," +
						  "v.dept_admission_to,v.patient_name from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v " +
						  "where t.income_type='"+auditValue+"' and t.patient_no = v.patient_no and v.execute_dept='"+deptName+"' " +
						  "and v.admission_date >= to_date('"+s+"','yyyy-mm-dd') and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') " +
						  "and t.local_key = '2_1_0_初步诊断' order by " + sidx +" " + sord;
			}else{
				if(searchString.equals("brh")){
					searchString = "t.patient_no";
				}if(searchString.equals("cbzdm")){
					searchString = "t.local_code";
				}
				listSql = "select t.patient_no,t.local_code,t.local_name,t.income_code,v.admission_date,t.income_type,t.local_key," +
						  "v.dept_admission_to,v.patient_name from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v" +
						  " where t.income_type='"+auditValue+"' and v.patient_no = t.patient_no and v.execute_dept='"+deptName+"' " +
						  "and "+searchString+" like '"+searchValue.toUpperCase()+"%' and  v.admission_date >= to_date('"+s+"','yyyy-mm-dd') " +
						  "and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') and t.local_key = '2_1_0_初步诊断' " +
						  "order by " + sidx +" " + sord;
				listSqlCount = "select count(*) count from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v where t.income_type='"+auditValue+"' " +
						       "and t.patient_no = v.patient_no and v.execute_dept='"+deptName+"' " +
							   "and v.admission_date >= to_date('"+s+"','yyyy-mm-dd') and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') " +
							   "and  "+searchString+" like '"+searchValue.toUpperCase()+"%' and t.local_key = '2_1_0_初步诊断' " ;
				if(searchString.equals("ksmc")){
					searchString = "v.execute_dept";
					searchValue = deptName;
					listSql = "select t.patient_no,t.local_code,t.local_name,t.income_code,v.admission_date,t.income_type,t.local_key," +
							  "v.dept_admission_to,v.patient_name from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v " +
							  "where t.income_type='"+auditValue+"' and v.patient_no = t.patient_no and v.execute_dept='"+deptName+"' " +
						      "and  v.admission_date >= to_date('"+s+"','yyyy-mm-dd') and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') " +
							  "and t.local_key = '2_1_0_初步诊断' order by " + sidx +" " + sord;
					listSqlCount = "select count(*) count from LCP_PATIENT_LOG_INCOME t,LCP_PATIENT_VISIT v where t.income_type='"+auditValue+"' " +
							       "and t.patient_no = v.patient_no and v.execute_dept='"+deptName+"' " +
								   "and v.admission_date >= to_date('"+s+"','yyyy-mm-dd') and v.admission_date <= to_date('"+e1+" 23:59:59', 'yyyy-mm-dd hh24:mi:ss') " +
								   "and t.local_key = '2_1_0_初步诊断' " ;
				}
			}
		}
		list.add(0, listSql);
		list.add(1, listSqlCount);
		return list;
	}
}
