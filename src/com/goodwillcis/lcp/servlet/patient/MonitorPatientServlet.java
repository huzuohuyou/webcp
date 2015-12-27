package com.goodwillcis.lcp.servlet.patient;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class StatisticsServlet
 */
@WebServlet("/MonitorPatientServlet")
public class MonitorPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DatabaseClass db = LcpUtil.getDatabaseClass();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op=request.getParameter("op");
		if("monitor".equals(op))
			monitor(request, response);
		if("cpNodeTrend".equals(op))
			cpNodeTrend(request, response);	
		if("monitorCP".equals(op))
			monitorCP(request, response);	
		if("cpTrend".equals(op))
			cpTrend(request, response);	
		if("orderVariation".equals(op)) //新增医嘱变异
			orderVariation(request,response);
	}
	
	 /**
	  * 监控病人路径节点执行情况列表
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void monitor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		int pageRows = Integer.valueOf(req.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(req.getParameter("page")).intValue();// 当前页数
		String patientName=req.getParameter("patientName");
		String patientNo=req.getParameter("patientNo");
		//String cpName=req.getParameter("cpName");
		String ks=req.getParameter("ks");
		String cp_id=req.getParameter("cp_id");
		int start = pageRows * (nowPage - 1) + 1;
		int end = pageRows * nowPage;
		String searchSql="";
		//搜索条件
		if(patientName!=null && (patientNo==null || patientNo.equals("")) && (ks==null || ks.equals(""))){
			searchSql = " and patient_name like '%"+patientName.trim()+"%'";
		}
		else if((patientName==null || patientName.equals("")) && patientNo!=null && (ks==null || ks.equals(""))){
			searchSql = " and patient_no like '%"+patientNo.trim()+"%'";
		}
		else if((patientName==null || patientName.equals("")) && (patientNo==null || patientNo.equals("")) && ks!=null){
			searchSql = " and trim(dept_admission_to) like '%"+ks.trim()+"%'";
		}
		String sql= "select t.patient_name,t.patient_no,t.admission_date,t.discharged_date,t.cp_id," +
				" (select cp_name from lcp_master where cp_id = t.cp_id) cp_name,dept_admission_to from lcp_patient_visit t " +
				" where t.cp_state=11 "+searchSql + " and cp_id="+cp_id+" order by t.discharged_date desc";
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
				cell.put("cp_id", cp_id);
				cell.put("cp_name", cp_name);
				cell.put("dept_admission_to", dept_admission_to);
				rows.add(cell);
			}
			jsonObj.put("rows", rows);
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().print(jsonObj);

		}
	}
	
	 /**
	  * 监控路径节点执行情况列表
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void monitorCP(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		int pageRows = Integer.valueOf(req.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(req.getParameter("page")).intValue();// 当前页数
		String cpName=req.getParameter("cpName");
		String py=req.getParameter("py");
		String ks=req.getParameter("ks");
		String s=req.getParameter("s");
		String e=req.getParameter("e") + " 23:59:59";
		//System.out.println("s==:"+s);
		//System.out.println("e==:"+e);
		
		int start = pageRows * (nowPage - 1) + 1;
		int end = pageRows * nowPage;
		String searchSql="";
		//搜索条件
		if(cpName!=null && (py==null || py.equals("")) && (ks==null || ks.equals(""))){
			searchSql = " and cp_name like '%"+cpName.trim()+"%'";
		}
		else if((cpName==null || cpName.equals("")) && py!=null && (ks==null || ks.equals(""))){
			searchSql = " and input_code_py like '%"+py.trim().toUpperCase()+"%'";
		}
		else if((cpName==null || cpName.equals("")) && (py==null || py.equals("")) && ks!=null){
			searchSql = " and trim(dept_name) like '%"+ks.trim()+"%'";
		}
		String sql= "select * from lcp_master a where a.cp_id in (select distinct(t.cp_id) from lcp_patient_visit t " +
				" where t.cp_state=11 and t.admission_date>=to_date('"+s+"','YYYY-MM-DD') and t.admission_date<=to_date('"+e+"','YYYY-MM-DD HH24:MI:SS')) "+searchSql+" order by a.cp_status,a.cp_name";
		//System.out.println("sql==:"+sql);
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
				String cp_id = dc.FunGetDataAsStringByColName(i, "CP_ID");
				String cp_name = dc.FunGetDataAsStringByColName(i, "CP_NAME");
				String cp_version = dc.FunGetDataAsStringByColName(i, "CP_VERSION");
				String cp_start_date = dc.FunGetDataAsStringByColName(i, "CP_START_DATE");
				//String cp_status = dc.FunGetDataAsStringByColName(i, "CP_STATUS");
				String dept_name = dc.FunGetDataAsStringByColName(i, "DEPT_NAME");

				JSONObject cell = new JSONObject();
				cell.put("cp_id", cp_id);
				cell.put("cp_name", cp_name);
				cell.put("cp_version", cp_version);
				cell.put("cp_start_date", cp_start_date);
				//cell.put("cp_status", cp_status);
				cell.put("dept_name", dept_name);
				rows.add(cell);
			}
			jsonObj.put("rows", rows);
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().print(jsonObj);

		}
	}
	
	/**
	 * 路径执行节点趋势
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void cpNodeTrend(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String patient_no=req.getParameter("patient_no");
		String cp_id=req.getParameter("cp_id");
		String patient_sql="select (select patient_name from lcp_patient_visit where patient_no=t.patient_no) patient_name,t.cp_node_id,t.cp_node_name,(t.cp_node_end_time-(select cp_node_end_time from lcp_patient_node " +
				" where patient_no='"+patient_no+"' and cp_node_type=0)) cp_date from lcp_patient_node t where t.patient_no='"+patient_no+"' order by cp_date ";
		String patient_name=db.FunGetDataSetBySQL(patient_sql).FunGetDataAsStringById(0, 0);
		String cp_name=db.FunGetDataSetBySQL("select cp_name from lcp_master where cp_id="+cp_id+"").FunGetDataAsStringById(0, 0);
		//System.out.println("patient_sql====:"+patient_sql);
		DataSetClass dataSet1 = db.FunGetDataSetBySQL(patient_sql);
		int rows = dataSet1.FunGetRowCount();

		String xml_nodes="";
		String xml_days1="";
		String xml_days2="";
		int days=0;
		String xml_before = "<chart caption='"+ patient_name+" 已完成"+cp_name+"路径' subCaption='节点趋势对比图' palette='2' animation='1'  formatNumberScale='0' formatNumber='0' showShadow='true' baseFont='宋体' baseFontSize='14' snumberSuffix='天' numberSuffix='天' " +
				" alternateVGridColor='AFD8F8' baseFontColor='114B78'	toolTipBorderColor='114B78' toolTipBgColor='E7EFF6' plotBorderDashed='0' plotBorderDashLen='2' plotBorderDashGap='2' useRoundEdges='1' showBorder='0'	bgColor='FFFFFF,FFFFFF' "
				+		" seriesNameInToolTip='0'><categories> ";
		String add_xml = "</categories><dataset seriesname='实际天数' parentYAxis='S' Color='AFD8F8' showValues='1'>";
		String nr_xml = "</dataset><dataset seriesName='路径制定平均天数' Color='F6BD0F' showValues='0'>";
		String end_xml = "</dataset><styles><definition><style type='font' color='666666' name='CaptionFont' size='20' /><style type='font' name='SubCaptionFont' bold='0' /></definition><application><apply toObject='caption' styles='CaptionFont' /><apply toObject='SubCaption' styles='SubCaptionFont' /></application></styles></chart>";
		for (int i = 0; i < rows; i++) {
			String nodesID = dataSet1.FunGetDataAsStringByColName(i, "CP_NODE_ID");
			String nodes = dataSet1.FunGetDataAsStringByColName(i, "CP_NODE_NAME");
			String days1 = dataSet1.FunGetDataAsStringByColName(i, "CP_DATE");
			if(!days1.equals("")){
				days=Math.round(Float.parseFloat(days1));
			}
			String cp_sql="select t.cp_node_id,t.cp_node_name,t.cp_node_days from lcp_master_node t where t.cp_id="+cp_id+" and t.cp_node_id="+nodesID+" order by t.cp_node_type,t.cp_node_id";
			//System.out.println("cp_sql===:"+cp_sql);
			DataSetClass dataSet2 = db.FunGetDataSetBySQL(cp_sql);
			String days2 = dataSet2.FunGetDataAsStringById(0,0);
			xml_nodes += "<category label='" + nodes + "' /> ";
			xml_days1 += " <set value='" + days + "' /> ";
			xml_days2 += "<set value='" + days2 + "' />";
		}
		String xml = xml_before + xml_nodes + add_xml + xml_days1 + nr_xml + xml_days2 + end_xml;
		resp.setCharacterEncoding("GBK");
		PrintWriter out = resp.getWriter();
		out.write(xml);
		out.close();
	}
	
	/**
	 * 路径执行节点趋势
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void cpTrend(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String cp_id=req.getParameter("cp_id");
		String patient_sql="select cp_node_id,max(t.cp_node_name) cp_node_name,max(t.cp_node_type) cp_node_type,sum(t.cp_node_end_time -(select cp_node_end_time " +
				" from lcp_patient_node where patient_no = t.patient_no and cp_node_type = 0))/(select count(distinct(patient_no)) " +
				" from lcp_patient_node where cp_id = "+cp_id+" and patient_no in(select patient_no from lcp_patient_visit where cp_state=11 and cp_id = "+cp_id+")) as cp_date " +
				" from lcp_patient_node t where t.cp_id = "+cp_id+" and patient_no in(select patient_no from lcp_patient_visit where cp_state=11 and cp_id = "+cp_id+") group by t.cp_node_id order by cp_node_type,t.cp_node_id";
		String cp_name=db.FunGetDataSetBySQL("select cp_name from lcp_master where cp_id="+cp_id+"").FunGetDataAsStringById(0, 0);
		//System.out.println("patient_sql====:"+patient_sql);
		DataSetClass dataSet1 = db.FunGetDataSetBySQL(patient_sql);
		int rows = dataSet1.FunGetRowCount();

		String xml_nodes="";
		String xml_days1="";
		String xml_days2="";
		int days=0;
		String xml_before = "<chart caption='"+cp_name+"路径' subCaption='节点趋势对比图' palette='2' animation='1'  formatNumberScale='0' formatNumber='0' showShadow='true' baseFont='宋体' baseFontSize='14' snumberSuffix='天' numberSuffix='天' " +
				" alternateVGridColor='AFD8F8' baseFontColor='114B78'	toolTipBorderColor='114B78' toolTipBgColor='E7EFF6' plotBorderDashed='0' plotBorderDashLen='2' plotBorderDashGap='2' useRoundEdges='1' showBorder='0'	bgColor='FFFFFF,FFFFFF' "
				+		" seriesNameInToolTip='0'><categories> ";
		String add_xml = "</categories><dataset seriesname='实际平均天数' parentYAxis='S' Color='AFD8F8' showValues='1'>";
		String nr_xml = "</dataset><dataset seriesName='路径制定平均天数' Color='F6BD0F' showValues='0'>";
		String end_xml = "</dataset><styles><definition><style type='font' color='666666' name='CaptionFont' size='20' /><style type='font' name='SubCaptionFont' bold='0' /></definition><application><apply toObject='caption' styles='CaptionFont' /><apply toObject='SubCaption' styles='SubCaptionFont' /></application></styles></chart>";
		for (int i = 0; i < rows; i++) {
			String nodesID = dataSet1.FunGetDataAsStringByColName(i, "CP_NODE_ID");
			String nodes = dataSet1.FunGetDataAsStringByColName(i, "CP_NODE_NAME");
			String days1 = dataSet1.FunGetDataAsStringByColName(i, "CP_DATE");
			if(!days1.equals("")){
				days=Math.round(Float.parseFloat(days1));
			}
			String cp_sql="select t.cp_node_id,t.cp_node_name,t.cp_node_days from lcp_master_node t where t.cp_id="+cp_id+" and t.cp_node_id="+nodesID+" order by t.cp_node_type,t.cp_node_id";
			//System.out.println("cp_sql===:"+cp_sql);
			DataSetClass dataSet2 = db.FunGetDataSetBySQL(cp_sql);
			String days2 = dataSet2.FunGetDataAsStringById(0,0);
			xml_nodes += "<category label='" + nodes + "' /> ";
			xml_days1 += " <set value='" + days + "' /> ";
			xml_days2 += "<set value='" + days2 + "' link='../statements/patientMonitor.html?cp_id="+cp_id+"'/>";
		}
		String xml = xml_before + xml_nodes + add_xml + xml_days1 + nr_xml + xml_days2 + end_xml;
		resp.setCharacterEncoding("GBK");
		PrintWriter out = resp.getWriter();
		out.write(xml);
		out.close();
	}
	/**
	 * 新增医嘱变异
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void orderVariation(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		int pageRows = Integer.valueOf(req.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(req.getParameter("page")).intValue();// 当前页数
		String sidx = req.getParameter("sidx");//排序字段
		String sord = req.getParameter("sord");//排序方式
		String patientName=req.getParameter("patientName");
		String patientNo=req.getParameter("patientNo");
		String dept=req.getParameter("dept");
		String userName=req.getParameter("userName");
		String s=req.getParameter("s");
		String e=req.getParameter("e") + " 23:59:59";
		//System.out.println("s==:"+s);
		//System.out.println("e==:"+e);
		
		int start = pageRows * (nowPage - 1) + 1;
		int end = pageRows * nowPage;
		String searchSql="";
		//搜索条件
		if(patientName!=null && (patientNo==null || patientNo.equals("")) && (userName==null || userName.equals("")) && (dept==null || dept.equals(""))){
			searchSql = " and patient_name like '%"+patientName.trim()+"%'";
		}
		else if((patientName==null || patientName.equals("")) && patientNo!=null && (userName==null || userName.equals("")) && (dept==null || dept.equals(""))){
			searchSql = " and t.patient_no like '%"+patientNo.trim()+"%'";
		}
		else if((patientName==null || patientName.equals("")) && (patientNo==null || patientNo.equals("")) && userName!=null && (dept==null || dept.equals(""))){
			searchSql = " and user_name like '%"+userName.trim()+"%'";
		}
		else if((patientName==null || patientName.equals("")) && (patientNo==null || patientNo.equals("")) && (userName==null || userName.equals("")) && dept!=null){
			searchSql = " and trim(dept_admission_to) like '%"+dept.trim()+"%'";
		}
		String sql = "select t.patient_no,v.patient_name,v.dept_admission_to,(select cp_name from lcp_master where cp_id = v.cp_id) CP_NAME,"
				+ "(select cp_node_name from lcp_master_node where cp_id = v.cp_id and cp_node_id = t.cp_node_id) CP_NODE_NAME,"
				+ "t.local_order_text,t.exe_date,t.user_name,t.zl_variation,t.yw_variation,t.fyl_variation,t.hb_variation,"
				+ "t.bf_variation,t.qt_variation from lcp_patient_order_varia t, lcp_patient_visit v where t.patient_no = v.patient_no"
				+ " and t.exe_date>=to_date('"
				+ s
				+ "','YYYY-MM-DD') and t.exe_date<=to_date('"
				+ e
				+ "','YYYY-MM-DD HH24:MI:SS') "
				+ searchSql
				+ "order by "
				+ sidx + " " + sord;
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
				String patient_name = dc.FunGetDataAsStringByColName(i, "PATIENT_NO");
				String patient_no = dc.FunGetDataAsStringByColName(i, "PATIENT_NAME");
				String dept_admission_to = dc.FunGetDataAsStringByColName(i, "DEPT_ADMISSION_TO");
				String cp_name = dc.FunGetDataAsStringByColName(i, "CP_NAME");
				String cp_node_name = dc.FunGetDataAsStringByColName(i, "CP_NODE_NAME");
				String local_order_text = dc.FunGetDataAsStringByColName(i, "LOCAL_ORDER_TEXT");
				String zlVar = dc.FunGetDataAsStringByColName(i, "ZL_VARIATION");
				String ywVar = dc.FunGetDataAsStringByColName(i, "YW_VARIATION");
				String fylVar = dc.FunGetDataAsStringByColName(i,"FYL_VARIATION");
				String hbVar = dc.FunGetDataAsStringByColName(i, "HB_VARIATION");
				String bfVar = dc.FunGetDataAsStringByColName(i, "BF_VARIATION");
				String qtVar = dc.FunGetDataAsStringByColName(i, "QT_VARIATION");
				String str[] = {zlVar,ywVar,fylVar,hbVar,bfVar,qtVar};
				String variation = "";
				for(int n = 0; n <str.length;n++){
					if(str[n] != null && str[n] !=""){
						variation += str[n] + "  ";
					}
				}
				String exe_date = dc.FunGetDataAsStringByColName(i, "EXE_DATE");
				String user_name = dc.FunGetDataAsStringByColName(i, "USER_NAME");

				JSONObject cell = new JSONObject();
				cell.put("patient_no", patient_no);
				cell.put("patient_name", patient_name);
				cell.put("dept_admission_to", dept_admission_to);
				cell.put("cp_name", cp_name);
				cell.put("cp_node_name", cp_node_name);
				cell.put("local_order_text", local_order_text);
				cell.put("variation", variation);
				cell.put("exe_date", exe_date);
				cell.put("user_name", user_name);
				rows.add(cell);
			}
			jsonObj.put("rows", rows);
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().print(jsonObj);
		}
	}
}
