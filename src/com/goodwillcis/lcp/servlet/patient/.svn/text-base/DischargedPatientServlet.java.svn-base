package com.goodwillcis.lcp.servlet.patient;

import java.io.IOException;

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

/**
 * Servlet implementation class StatisticsServlet
 */
@WebServlet("/DischargedPatientServlet")
public class DischargedPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DatabaseClass db = LcpUtil.getDatabaseClass();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op=request.getParameter("op");
		if("discharged".equals(op))
			discharged(request, response);
	}
	
	 /**
	  * 已出院未完成路径的病例信息
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void discharged(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");		
		String deptname=(String) req.getSession().getAttribute("deptname");
		int pageRows = Integer.valueOf(req.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(req.getParameter("page")).intValue();// 当前页数
		String userID=(String) req.getSession().getAttribute("userid");//获取当前用户
		int start = pageRows * (nowPage - 1) + 1;
		int end = pageRows * nowPage;
		String patientName=req.getParameter("patientName");
		String patientNo=req.getParameter("patientNo");
		String cpName=req.getParameter("cpName");
		String ks=req.getParameter("ks");
		String s=req.getParameter("s");
		String e=req.getParameter("e") + " 23:59:59";
		String searchSql="";
		String addSql="";
		//搜索条件
		if(patientName!=null && (patientNo==null || patientNo.equals("")) && (cpName==null || cpName.equals("")) && (ks==null || ks.equals(""))){
			searchSql = " and patient_name like '%"+patientName.trim()+"%'";
		}
		else if((patientName==null || patientName.equals("")) && patientNo!=null && (cpName==null || cpName.equals("")) && (ks==null || ks.equals(""))){
			searchSql = " and patient_no like '%"+patientNo.trim()+"%'";
		}
		else if((patientName==null || patientName.equals("")) && (patientNo==null || patientNo.equals("")) && cpName!=null && (ks==null || ks.equals(""))){
			searchSql = " and cp_id in (select cp_id from lcp_master where cp_name like '%"+cpName.trim()+"%')";
		}
		else if((patientName==null || patientName.equals("")) && (patientNo==null || patientNo.equals("")) && (cpName==null || cpName.equals("")) && ks!=null){
			searchSql = " and trim(dept_admission_to) like '%"+ks.trim()+"%'";
		}
		//查询用户是否有“路径审核”权限，如果有则是路径审核管理员，否则是各科室管理员
		String usersql = "select funs_id from dcp_sys_power where user_id="+userID;
		DataSetClass dataSet = db.FunGetDataSetBySQL(usersql);
		String id="";
		String funid="";
		for(int i=0;i<dataSet.FunGetRowCount();i++){
			id=dataSet.FunGetDataAsStringByColName(i,"FUNS_ID");
			funid+=id;
		}
		if(!funid.contains("61")){
			addSql=" and trim(t.dept_admission_to) like '"+deptname.trim()+"%' ";
		}
		String sql= "select t.patient_name,t.patient_no,t.admission_date,t.discharged_date," +
				" (select cp_name from lcp_master where cp_id = t.cp_id) cp_name,dept_admission_to from lcp_patient_visit t " +
				" where t.discharged_date is not null and t.cp_state=1 and t.admission_date>=to_date('"+s+"','YYYY-MM-DD') and t.admission_date<=to_date('"+e+"','YYYY-MM-DD HH24:MI:SS') "+addSql+" "+searchSql + " order by t.discharged_date desc";

		DataSetClass dc = db.FunGetPageDataSetBySQL(sql, start, end);

		JSONObject jsonObj = new JSONObject();
		int rowCount = db.FunGetRowCountBySql(sql);
		// 根据jqGrid对JSON的数据格式要求给jsonObj赋值
		int countPage=rowCount / pageRows;
		if(rowCount%pageRows!=0){
			countPage++;
		}
		jsonObj.put("page", nowPage); // 当前页
		jsonObj.put("total", countPage); // 总页数
		jsonObj.put("records", rowCount); // 总记录数

		// 定义rows，存放数据
		JSONArray rows = new JSONArray();
		if (dc.FunGetDataAsStringById(0, 0) != "") {
			for (int i = 0; i < dc.FunGetRowCount(); i++) {
				String patient_name = dc.FunGetDataAsStringByColName(i, "PATIENT_NAME");
				String patient_no = dc.FunGetDataAsStringByColName(i, "PATIENT_NO");
				String admission_date = dc.FunGetDataAsStringByColName(i, "ADMISSION_DATE");
				String discharged_date = dc.FunGetDataAsStringByColName(i, "DISCHARGED_DATE");
				String cp_name = dc.FunGetDataAsStringByColName(i, "CP_NAME");
				String dept_admission_to = dc.FunGetDataAsStringByColName(i, "DEPT_ADMISSION_TO");
				
				JSONObject cell = new JSONObject();
				cell.put("patient_name", patient_name);
				cell.put("patient_no", patient_no);
				cell.put("admission_date", admission_date);
				cell.put("discharged_date", discharged_date);
				cell.put("cp_name", cp_name);
				cell.put("dept_admission_to", dept_admission_to);
				rows.add(cell);
			}
			jsonObj.put("rows", rows);
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().print(jsonObj);

		}
	}
	

}
