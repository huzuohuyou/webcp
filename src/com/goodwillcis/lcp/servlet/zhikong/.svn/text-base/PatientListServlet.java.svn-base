package com.goodwillcis.lcp.servlet.zhikong;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

/**
 * Servlet implementation class PatientList
 */
@WebServlet("/PatientListServlet")
public class PatientListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String op=request.getParameter("op");
		if("patientList".equals(op)) 	getPatientList(request, response);
		if("check".equals(op) )  getCheck(request, response);
	}
	/**
	  * 检查是否是已完成路径病人
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	 public void getCheck(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
			resp.setContentType("text/html; charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
			String patient_no = req.getParameter("patient_no");
			String chectSql = "select t.cp_state from lcp_patient_visit t where t.patient_no = '"+patient_no+"'";
			DatabaseClass db = LcpUtil.getDatabaseClass();
			DataSetClass ds = db.FunGetDataSetBySQL(chectSql);
			String state = "";
			if (ds.FunGetDataAsStringById(0, 0) != "") {
				for (int i = 0; i < ds.FunGetRowCount(); i++) {
					String cp_state = ds.FunGetDataAsStringById(i, 0);
					if(cp_state.equals("11")){
						state = "finish";
					}else if(cp_state.equals("21")){
						state = "finish";
					}else{
						state = "unfinish";
					}
				}
			}
			resp.getWriter().print(state);
	}

	/**
	  * 表单打印--病人列表
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void getPatientList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String sidx = req.getParameter("sidx");//按sidx字段排序
		sidx=(sidx==null||sidx==""?"admission_date":sidx);
		String sord = req.getParameter("sord");//排序方式
		String searchField = req.getParameter("radio");// 要查询的字段
		String searchString = req.getParameter("text");// 要查询的值
		String s=req.getParameter("s");//开始时间
		String e=req.getParameter("e");//结束时间
		String addSql="";
        
		if(searchField != null){
       	if(searchField.equals("hzmc")){
       		addSql=" and t.patient_name like '%"+searchString.trim()+"%'";
       	}
       	else if(searchField.equals("zyh")){
       		addSql=" and t.patient_no like '%"+searchString+"%'";
       	}
       	else if(searchField.equals("ryks")){
       		addSql=" and t.dept_admission_to like '%"+searchString.trim()+"%'";
       	}else {
       		addSql="";
       	}
		}
		 
		int pageRows = Integer.valueOf(req.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(req.getParameter("page")).intValue();// 当前页数
		int start = pageRows * (nowPage - 1) + 1;
		int end = pageRows * nowPage;
		String sql= "  select t.patient_name,t.patient_no,to_char( t.admission_date,'yyyy-MM-dd HH24:mi:ss') " +
				    "admission_date,to_char( t.discharged_date,'yyyy-MM-dd HH24:mi:ss') discharged_date," +
				    "t.dept_admission_to,d.cp_name,f.fee_total,t.cp_state"+
		            "  from lcp_patient_visit t,lcp_master d,lcp_patient_fee f                                 "+
		            " where   t.patient_no=f.patient_no(+)   and  t.cp_state in (1,11,21) and t.cp_id=d.cp_id"+
		            " and t.admission_date>= to_date('"+s+"','yyyy-MM-dd') "+
		            " and t.admission_date <= to_date('"+e+" 23:59:59','yyyy-MM-dd HH24:mi:ss')"+addSql +" order by "+sidx+" "+sord;

		DatabaseClass db = LcpUtil.getDatabaseClass();
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
				String patient_name = dc.FunGetDataAsStringById(i, 0);
				String patient_no = dc.FunGetDataAsStringById(i, 1);
				String admission_date = dc.FunGetDataAsStringById(i, 2);
				String discharged_date = dc.FunGetDataAsStringById(i, 3);
				String dept_admission_to = dc.FunGetDataAsStringById(i, 4);
				String cp_name = dc.FunGetDataAsStringById(i, 5);
				String fee_total = dc.FunGetDataAsStringById(i, 6);
				String cp_state = dc.FunGetDataAsStringById(i, 7);
				JSONObject cell = new JSONObject();
				cell.put("patient_name", patient_name);
				cell.put("patient_no", patient_no);
				cell.put("admission_date", admission_date);
				cell.put("discharged_date", discharged_date);
				cell.put("dept_admission_to", dept_admission_to);
				cell.put("cp_name", cp_name);
				cell.put("fee_total", fee_total);
				cell.put("cp_state", cp_state);

				rows.add(cell);
			}
			jsonObj.put("rows", rows);
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().print(jsonObj);

		}
	}
}
