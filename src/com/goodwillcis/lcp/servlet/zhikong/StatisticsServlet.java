package com.goodwillcis.lcp.servlet.zhikong;

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
@WebServlet("/StatisticsServlet")
public class StatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public StatisticsServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op=request.getParameter("op");
		if("duibi".equals(op)) 	getDuiBiInfo(request, response);
		if("single".equals(op)) 	getSingleInfo(request, response);
		if("patientIndex".equals(op)) 	getPatientIndex(request, response);
	}

	/**
	 * 获取局发路径信息,进行各院数据对比
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void getDuiBiInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String sidx = req.getParameter("sidx");//要搜索的行
		sidx=(sidx==null||sidx==""?"cp_id":sidx);
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
		String sql = "select t.cp_id,t.cp_name,to_char(t.cp_create_date, 'yyyy-MM-dd') cp_create_date,"+
		"to_char(t.cp_version_date, 'yyyy-MM-dd') cp_version_date,t.dept_name "+
		" from ( select cp_master_id,count(hospital_id) s from lcp_master  where cp_master_id<10000 group by cp_master_id) a ,dcp_master t  where  a.s>1"+
		" and  t.sys_is_del=0 and t.cp_id=a.cp_master_id "+addSql+" order by "
			+sidx+" "+sord;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dc = db.FunGetPageDataSetBySQL(sql, start, end);

		JSONObject jsonObj = new JSONObject();
		int rowCount = db.FunGetRowCountBySql(sql);
		int countPage=rowCount / pageRows;
		if(rowCount%pageRows!=0){
			countPage++;
		}
		jsonObj.put("page", nowPage); // 当前页
		jsonObj.put("total", countPage); // 总页数
		jsonObj.put("records", rowCount); // 总记录数
		JSONArray rows = new JSONArray();
		if (dc.FunGetDataAsStringById(0, 0) != "") {
			for (int i = 0; i < dc.FunGetRowCount(); i++) {
				String cp_id = dc.FunGetDataAsStringById(i, 0);
				String cp_name = dc.FunGetDataAsStringById(i, 1);
				String create_date = dc.FunGetDataAsStringById(i, 2);
				String version_date = dc.FunGetDataAsStringById(i, 3);
				String dept = dc.FunGetDataAsStringById(i, 4);

				JSONObject cell = new JSONObject();
				cell.put("cp_id", cp_id);
				cell.put("cp_name", cp_name);
				cell.put("create_date", create_date);
				cell.put("version_date", version_date);
				cell.put("dept_name", dept);
				rows.add(cell);
			}
			jsonObj.put("rows", rows);
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().print(jsonObj);
		}
	}
	/**
	 * 单路径报表
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void getSingleInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String sidx = req.getParameter("sidx");//要搜索的行
		sidx=(sidx==null||sidx==""?"cp_id":sidx);
		String sord = req.getParameter("sord");//排序方式
		boolean _search=Boolean.valueOf(req.getParameter("_search"));//是否查询
		String opp=req.getParameter("opp");//1 全部路径  2 局发路径  3 自定义路径
		String addSql=" where cp_id";
		if("1".equals(opp))  addSql+="!=0";
			if("2".equals(opp)) addSql+="<10000";
				if("3".equals(opp)) addSql+=">10000";
		
		
        if(_search){
        	String searchField=req.getParameter("searchField");//查询的列
        	String searchString=req.getParameter("searchString");//查询的内容
        	String searchOper=req.getParameter("searchOper");//查询方式
        	if("cn".equals(searchOper)){
        		addSql+=" and "+searchField+" like '%"+searchString+"%'";
        	}
        	if("eq".equals(searchOper)){
        		addSql+=" and "+searchField+" = '"+searchString+"'";
        	}
        }
		
		int pageRows = Integer.valueOf(req.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(req.getParameter("page")).intValue();// 当前页数
		int start = pageRows * (nowPage - 1) + 1;
		int end = pageRows * nowPage;
		String sql = "select t.CP_ID,t.CP_NAME,t.CP_CREATE_DATE,t.DEPT_NAME,t.INPUT_CODE_PY from v_cp_master t "+addSql+" order by "
			+sidx+" "+sord;
		//System.out.println(sql);
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
				String cp_id = dc.FunGetDataAsStringById(i, 0);
				String cp_name = dc.FunGetDataAsStringById(i, 1);
				String create_date = dc.FunGetDataAsStringById(i, 2);
				String dept = dc.FunGetDataAsStringById(i, 3);
				String py = dc.FunGetDataAsStringById(i, 4);
				String cpType="局发路径";
				
				if(Integer.valueOf(cp_id)>10000){
					cpType="院内自定义";
				}
				// 放入数据
				JSONObject cell = new JSONObject();
				cell.put("cp_id", cp_id);
				cell.put("cp_name", cp_name);
				cell.put("create_date", create_date);
				cell.put("version_date", cpType);
				cell.put("dept_name", dept);
				cell.put("input_code_py", py);

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
	
	
	 /**
	  * 病例查看--患者列表信息
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void getPatientIndex(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String sidx = req.getParameter("sidx");//要搜索的行
		sidx=(sidx==null||sidx==""?"cp_id":sidx);
		String sord = req.getParameter("sord");//排序方式
		boolean _search=Boolean.valueOf(req.getParameter("_search"));//是否查询
		String s=req.getParameter("s");//开始时间
		String e=req.getParameter("e");//结束时间
		String cp_id=req.getParameter("cp_id");
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
		String sql= "  select t.patient_name,  to_char( t.admission_date,'yyyy-MM-dd HH24:mi:ss') admission_date,  "+
		" to_char( t.discharged_date,'yyyy-MM-dd HH24:mi:ss') discharged_date,d.hospital_name,f.fee_total,t.cp_state ,"+
		" t.patient_no,t.hospital_id from lcp_patient_visit t, dcp_sys_hospital d,lcp_patient_fee f                                 "+
		" where t.hospital_id = d.hospital_id       and t.patient_no=f.patient_no(+)    "+
		" and t.hospital_id=f.hospital_id (+)   "+
		" and t.cp_master_id="+cp_id+
		" and t.admission_date>= to_date('"+s+"','yyyy-MM-dd') "+
		" and t.admission_date <= to_date('"+e+" 23:59:59','yyyy-MM-dd HH24:mi:ss')"+addSql +" order by "+sidx+" "+sord;
		
		//System.out.println(sql);
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
				String admission_date = dc.FunGetDataAsStringById(i, 1);
				String discharged_date = dc.FunGetDataAsStringById(i, 2);
				String hospital_name = dc.FunGetDataAsStringById(i, 3);
				String fee_total = dc.FunGetDataAsStringById(i, 4);
				String cp_state = dc.FunGetDataAsStringById(i, 5);
				String patient_no = dc.FunGetDataAsStringById(i, 6);
				String hospital_id = dc.FunGetDataAsStringById(i, 7);
				JSONObject cell = new JSONObject();
				cell.put("patient_name", patient_name);
				cell.put("admission_date", admission_date);
				cell.put("discharged_date", discharged_date);
				cell.put("hospital_name", hospital_name);
				cell.put("fee_total", fee_total);
				cell.put("cp_state", cp_state);
				cell.put("patient_no", patient_no);
				cell.put("hospital_id", hospital_id);
				rows.add(cell);
			}
			jsonObj.put("rows", rows);
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().print(jsonObj);

		}
	}
	

}
