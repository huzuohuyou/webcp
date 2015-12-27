/*---------------------------------------------------------------- 
 * // Copyright (C) 2012 北京嘉和美康信息技术有限公司版权所有。
 * // 文件名： PatientInfo.java
 * // 文件功能描述： 统计住院患者信息并提醒护士签名
 * // 创建人：张昆 
 * // 创建日期：2012/11/19
 * ----------------------------------------------------------------*/
package com.goodwillcis.lcp.servlet.orderprocessing;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.LcpUtil;

@WebServlet("/PatientInfo")
public class PatientInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseClass db=LcpUtil.getDatabaseClass();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		if(op.equals("getPatientList")){
			getPatientList(request,response);
		}
	}

	/**
	 * 获取病例信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @author 张昆     2012-11-19
	 */
	private void getPatientList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String searchField = request.getParameter("radio");// 要查询的字段
		String searchString = request.getParameter("text");// 要查询的值
		searchString=((searchString!=null&&searchString!="")?URLDecoder.decode(searchString, "UTF-8"):searchString);
		String dept_name=request.getParameter("dept_name");
		dept_name=((dept_name!=null&&dept_name!="")?URLDecoder.decode(dept_name, "UTF-8"):dept_name);
		int pageRows = Integer.valueOf(request.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(request.getParameter("page")).intValue();// 当前页数
		JSONObject jsonObj = new JSONObject();
		JSONArray rows = new JSONArray();
		int countPage=0;
		int rowCount=0;
		String addSql="";
		if(searchString!=null && searchString!=""){
			if(searchField.equals("zy")){
				searchField="t.inpatient_no";
				addSql="and "+searchField+" like '"+searchString+"%'";
		}else if(searchField.equals("hz")){
				searchField="t.patient_name";
				addSql="and "+searchField+" like '"+searchString+"%'";
		}else if(searchField.equals("lj")){
				searchField="t.cp_name";
				addSql="and "+searchField+" like '"+searchString+"%'";
		}
		}

		String sql="select t.inpatient_no,t.patient_name,t.dept_admission_to,t.cp_name,t.cp_node_name,t.is_sign " +
				   "from v_patient_info t where t.dept_admission_to='"+dept_name+"' and t.is_sign='未签名' "+addSql;
		int startNum = pageRows * (nowPage - 1) + 1;// 开始查询页数
		int endNum = pageRows * nowPage;// 结束查询页数
		rowCount = db.FunGetRowCountBySql(sql);
		countPage = rowCount / pageRows;
		if (rowCount % pageRows != 0) {
			countPage++;
		}

		DataSetClass dataSet = db.FunGetPageDataSetBySQL(sql, startNum, endNum);
		int counts = dataSet.FunGetRowCount();

		for(int i=0;i<counts;i++){
			String inpatient_no = dataSet.FunGetDataAsStringByColName(i, "INPATIENT_NO");
			String patient_name = dataSet.FunGetDataAsStringByColName(i, "PATIENT_NAME");
			String dept_admission_to = dataSet.FunGetDataAsStringByColName(i, "DEPT_ADMISSION_TO");
			String cp_name = dataSet.FunGetDataAsStringByColName(i, "CP_NAME");
			String cp_node_name = dataSet.FunGetDataAsStringByColName(i, "CP_NODE_NAME");
			String is_sign = dataSet.FunGetDataAsStringByColName(i, "IS_SIGN");
			
			JSONObject cell = new JSONObject();
			cell.put("inpatient_no", inpatient_no);
			cell.put("patient_name", patient_name);
			cell.put("dept_admission_to", dept_admission_to);
			cell.put("cp_name", cp_name);
			cell.put("cp_node_name", cp_node_name);
			cell.put("is_sign", is_sign);
			rows.add(cell);
		}
		jsonObj.put("page", nowPage); // 当前页
		jsonObj.put("total", countPage); // 总页数
		jsonObj.put("records", rowCount); // 总记录数
		jsonObj.put("rows", rows);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(jsonObj.toString());
	}
}
