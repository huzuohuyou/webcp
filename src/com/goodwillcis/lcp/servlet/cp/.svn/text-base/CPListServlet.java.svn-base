package com.goodwillcis.lcp.servlet.cp;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

public class CPListServlet extends HttpServlet {

	private static final long serialVersionUID = -6032278660359323519L;
	private static final int HOSPITALID = LcpUtil.getHospitalID();
	Logger log = Logger.getLogger(CPListServlet.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		String oper = req.getParameter("oper");
		if ("del".equals(oper)) {

		}
		if ("edit".equals(oper)) {

		} else if (oper.equals("create")) {
			createCP(req, resp);
		} else if (oper.equals("copyCP")) {
			copyCP(req, resp);
		}else if (oper.equals("createLocalCP")) {
			 createLocalCP(req, resp);
		}
		
		// loadCPList(req,resp);
		log.info("执行方法oper=:"+oper);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		doGet(req, resp);
	}

	public void loadOrder(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		int pageRows = Integer.valueOf(req.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(req.getParameter("page")).intValue();// 当前页数
		int node = Integer.valueOf(req.getParameter("node")).intValue();// 当前节点(第几天)
		String diagnosisCode = req.getParameter("diagnosiscode");// 获取诊断编码
		int start = pageRows * (nowPage - 1) + 1;
		int end = pageRows * nowPage;
		// String nodeName = URLDecoder.decode(request.getParameter("nodeName"),
		// "UTF-8");
		if (node == 1) {
			node = 0;
		} else {
			node = node - 1;
		}
		String sql = "SELECT  t.local_code,t.order_code,t.order_text,t.order_class,t.repeat_indicator,t.is_antibiotic,count(t.order_code) counts "
				+ "   FROM LCP_PATIENT_LOG_ORDER T ,"
				+ "   (SELECT T.PATIENT_NO PS, (trunc(MIN(T.EXE_DATE))+"
				+ node
				+ ") FIRSTS"
				+ "    FROM LCP_PATIENT_LOG_ORDER T"
				+ "    WHERE T.PATIENT_NO IN"
				+ "    (SELECT T.PATIENT_NO"
				+ "     FROM LCP_PATIENT_LOG_INCOME T"
				+ "     WHERE T.LOCAL_CODE = '"
				+ diagnosisCode
				+ "')"
				+ "     GROUP BY T.PATIENT_NO) s"
				+ "     WHERE t.patient_no=s.ps"
				+ "    AND TRUNC(t.exe_date)=s.firsts"
				+ "    GROUP BY t.local_code,t.order_code,t.order_text,t.order_class,t.repeat_indicator,t.is_antibiotic  order by counts desc";

		////System.out.println(sql);
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dc = db.FunGetPageDataSetBySQL(sql, start, end);

		JSONObject jsonObj = new JSONObject();
		int rowCount = db.FunGetRowCountBySql(sql);
		// 根据jqGrid对JSON的数据格式要求给jsonObj赋值
		jsonObj.put("page", nowPage); // 当前页
		jsonObj.put("total", rowCount / pageRows); // 总页数
		jsonObj.put("records", rowCount); // 总记录数

		// 定义rows，存放数据
		JSONArray rows = new JSONArray();
		if (dc.FunGetDataAsStringById(0, 0) != "") {
			for (int i = 0; i < dc.FunGetRowCount(); i++) {
				String order_code = dc.FunGetDataAsStringById(i, 1);
				String cp_node_name = dc.FunGetDataAsStringById(i, 2);
				String order_class = dc.FunGetDataAsStringById(i, 3);
				String repeat_indicator = dc.FunGetDataAsStringById(i, 4);
				String is_antibiotic = dc.FunGetDataAsStringById(i, 5);
				String count = dc.FunGetDataAsStringById(i, 6);
				if ("0".equals(repeat_indicator)) {
					repeat_indicator = "临";
				}
				if ("1".equals(repeat_indicator)) {
					repeat_indicator = "长";
				}
				if ("0".equals(is_antibiotic)) {
					is_antibiotic = "";
				}
				if ("1".equals(is_antibiotic)) {
					is_antibiotic = "一级";
				}
				if ("2".equals(is_antibiotic)) {
					is_antibiotic = "二级";
				}
				if ("3".equals(is_antibiotic)) {
					is_antibiotic = "三级";
				}

				// 放入数据
				JSONObject cell = new JSONObject();
				// cell.put("id", start+i);
				cell.put("id", order_code);
				cell.put("count", count);
				cell.put("code", order_code);
				cell.put("name", cp_node_name);
				cell.put("type", order_class);
				cell.put("repeat", repeat_indicator);
				cell.put("antibiotic", is_antibiotic);

				// 将该记录放入rows中
				rows.add(cell);

			}

			// 将rows放入json对象中
			jsonObj.put("rows", rows);

			// 自控制台打印输出，以检验json对象生成是否正确
			////System.out.println("要返回的json对象：/n" + jsonObj.toString());

			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().print(jsonObj);

		}
	}

	public void loadNodeOrderItem(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		int pageRows = Integer.valueOf(req.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(req.getParameter("page")).intValue();// 当前页数
		String cpID = req.getParameter("cp_id");
		String cpNodeID = req.getParameter("cp_node_id");
		String cpNodeOrderID = req.getParameter("cp_node_order_id");
		int start = pageRows * (nowPage - 1) + 1;
		int end = pageRows * nowPage;
		// String nodeName = URLDecoder.decode(request.getParameter("nodeName"),
		// "UTF-8");

		String SQL = "select t.cp_node_order_item_id,t.order_no,t.cp_node_order_text,t.order_type_name,t.order_type,"
				+ "t.need_item,t.order_kind from lcp_node_order_item t"
				+ "  WHERE t.hospital_id=1 "
				+ "  AND t.cp_id="
				+ cpID
				+ "  AND t.cp_node_id= "
				+ cpNodeID
				+ "  AND t.cp_node_order_id= " + cpNodeOrderID;

		////System.out.println(SQL);
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dc = db.FunGetPageDataSetBySQL(SQL, start, end);

		JSONObject jsonObj = new JSONObject();
		int rowCount = db.FunGetRowCountBySql(SQL);
		// 根据jqGrid对JSON的数据格式要求给jsonObj赋值
		jsonObj.put("page", nowPage); // 当前页
		jsonObj.put("total", rowCount / pageRows); // 总页数
		jsonObj.put("records", rowCount); // 总记录数

		// 定义rows，存放数据
		JSONArray rows = new JSONArray();
		if (dc.FunGetDataAsStringById(0, 0) != "") {
			for (int i = 0; i < dc.FunGetRowCount(); i++) {
				String order_code = dc.FunGetDataAsStringById(i, 1);
				String cp_node_name = dc.FunGetDataAsStringById(i, 2);
				String order_class = dc.FunGetDataAsStringById(i, 3);
				String repeat_indicator = dc.FunGetDataAsStringById(i, 4);
				String is_antibiotic = dc.FunGetDataAsStringById(i, 5);
				String count = dc.FunGetDataAsStringById(i, 6);
				if ("0".equals(repeat_indicator)) {
					repeat_indicator = "临";
				}
				if ("1".equals(repeat_indicator)) {
					repeat_indicator = "长";
				}
				if ("0".equals(is_antibiotic)) {
					is_antibiotic = "";
				}
				if ("1".equals(is_antibiotic)) {
					is_antibiotic = "一级";
				}
				if ("2".equals(is_antibiotic)) {
					is_antibiotic = "二级";
				}
				if ("3".equals(is_antibiotic)) {
					is_antibiotic = "三级";
				}

				// 放入数据
				JSONObject cell = new JSONObject();
				// cell.put("id", start+i);
				cell.put("id", order_code);
				cell.put("count", count);
				cell.put("code", order_code);
				cell.put("name", cp_node_name);
				cell.put("type", order_class);
				cell.put("repeat", repeat_indicator);
				cell.put("antibiotic", is_antibiotic);

				// 将该记录放入rows中
				rows.add(cell);

			}

			// 将rows放入json对象中
			jsonObj.put("rows", rows);

			// 自控制台打印输出，以检验json对象生成是否正确
		//	//System.out.println("要返回的json对象：/n" + jsonObj.toString());

			// 设置字符编码
			resp.setCharacterEncoding("UTF-8");
			// 返回json对象（通过PrintWriter输出）
			resp.getWriter().print(jsonObj);

		}
	}

	/**
	 * @param req
	 * @param resp
	 * @throws IOException
	 *             方法说明:根据诊断编码统计符合该诊断的所有病人的最小住院日,最大住院日,平均住院日
	 */
	public void getMinOrMaxDay(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String diagnosisCode = req.getParameter("diagnosiscode");// 获取诊断编码
		String message = "没有获取到相关信息";
		String SQL = "SELECT MAX(FIRSTS) maxdays, MIN(FIRSTS) mindays, round(AVG(FIRSTS)) avgdays "
				+ " FROM (SELECT (TRUNC(MAX(T.EXE_DATE)) - TRUNC(MIN(T.EXE_DATE))) FIRSTS"
				+ " FROM LCP_PATIENT_LOG_ORDER T  WHERE T.PATIENT_NO IN ( SELECT T.PATIENT_NO"
				+ " FROM LCP_PATIENT_LOG_INCOME T"
				+ " WHERE T.LOCAL_CODE = '"
				+ diagnosisCode + "')" + " GROUP BY T.PATIENT_NO)";
		////System.out.println(SQL);
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dc = db.FunGetDataSetBySQL(SQL);
		if (dc.FunGetDataAsStringById(0, 0) != "") {
			String max = dc.FunGetDataAsStringById(0, 0);
			String min = dc.FunGetDataAsStringById(0, 1);
			String avg = dc.FunGetDataAsStringById(0, 2);
			message = "最小住院天数:<span id='minDay' name='" + min + "'>" + min
					+ "</span>,最大住院天数:<span id='maxDay'name='" + max + "'>"
					+ max + "</span>,平均天数:<span  id='avgDay'name='" + avg
					+ "'>" + avg + "</span>";
		}
		resp.setCharacterEncoding("UTF-8");
		////System.out.println("message:" + message);
		resp.getWriter().print(message);

	}

	public void addOrder(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String cpID = req.getParameter("cp_id");
		String cpNodeID = req.getParameter("cp_node_id");
		String orderCode = req.getParameter("order_code");
		String orderName = URLDecoder.decode(req.getParameter("order_name"),
				"UTF-8");
		String orderType = req.getParameter("order_type");
		//System.out.println(orderCode);
		//System.out.println(orderName);
		//System.out.println(orderType);
		String message = "没有获取到相关信息";
		// String SQL =
		// "SELECT MAX(FIRSTS) maxdays, MIN(FIRSTS) mindays, round(AVG(FIRSTS)) avgdays "
		// +
		// " FROM (SELECT (TRUNC(MAX(T.EXE_DATE)) - TRUNC(MIN(T.EXE_DATE))) FIRSTS"
		// +
		// " FROM LCP_PATIENT_LOG_ORDER T  WHERE T.PATIENT_NO IN ( SELECT T.PATIENT_NO"
		// + " FROM LCP_PATIENT_LOG_INCOME T"
		// + " WHERE T.LOCAL_CODE = '"+diagnosisCode+"')"
		// + " GROUP BY T.PATIENT_NO)";
		// //System.out.println(SQL);
		// DatabaseClass db = LcpUtil.getDatabaseClass();
		// DataSetClass dc = db.FunGetDataSetBySQL(SQL);
		// if (dc.FunGetDataAsStringById(0, 0) != "") {
		// String max = dc.FunGetDataAsStringById(0, 0);
		// String min = dc.FunGetDataAsStringById(0, 1);
		// String avg = dc.FunGetDataAsStringById(0, 2);
		// message="最小住院天数:<span id='minDay' name='"+min+"'>"+min
		// +"</span>,最大住院天数:<span id='maxDay'name='"+max+"'>"+max
		// +"</span>,平均天数:<span  id='avgDay'name='"+avg+"'>"+avg+"</span>";
		// }
		// resp.setCharacterEncoding("UTF-8");
		message = "{\"result\":\"OK\"," + "\"method\":\"getTable\","
				+ "\"tableHtml\":\"\"," + "\"pageHtml\":\"\","
				+ " \"flag\":\"1\"}";
		//System.out.println("message:" + message);
		resp.getWriter().print(message);

	}

	public void getCPList(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		int pageRows = Integer.valueOf(req.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(req.getParameter("page")).intValue();// 当前页数
		int start = pageRows * (nowPage - 1) + 1;
		int end = pageRows * nowPage;

		String SQL = "SELECT T.CP_ID," + " T.CP_CODE," + " T.CP_NAME,"
				+ " to_char(T.CP_CREATE_DATE,'yyyy-MM-dd') create_date,"
				+ " T.CP_MASTER_ID,"
				+ " to_char( T.VERIFY_DATE,'yyyy-MM-dd') VERIFY_DATE   "
				+ " FROM LCP_MASTER T" + " WHERE T.CP_MASTER_ID = 0";

		//System.out.println(SQL);
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dc = db.FunGetPageDataSetBySQL(SQL, start, end);

		JSONObject jsonObj = new JSONObject();
		int rowCount = db.FunGetRowCountBySql(SQL);
		// 根据jqGrid对JSON的数据格式要求给jsonObj赋值
		jsonObj.put("page", nowPage); // 当前页
		jsonObj.put("total", rowCount / pageRows); // 总页数
		jsonObj.put("records", rowCount); // 总记录数

		// 定义rows，存放数据
		JSONArray rows = new JSONArray();
		if (dc.FunGetDataAsStringById(0, 0) != "") {
			for (int i = 0; i < dc.FunGetRowCount(); i++) {
				String cpID = dc.FunGetDataAsStringById(i, 0);
				String cpCode = dc.FunGetDataAsStringById(i, 1);
				String cpName = dc.FunGetDataAsStringById(i, 2);
				String cpCreateDate = dc.FunGetDataAsStringById(i, 3);
				String verifyDate = dc.FunGetDataAsStringById(i, 4);
				String count = dc.FunGetDataAsStringById(i, 5);

				// 放入数据
				JSONObject cell = new JSONObject();
				// cell.put("id", start+i);
				cell.put("id", cpID);
				cell.put("code", cpCode);
				cell.put("name", cpName);
				cell.put("cpCreateDate", cpCreateDate);
				cell.put("verifyDate", verifyDate);
				cell.put("repeat", count);
				cell.put("antibiotic", count);

				// 将该记录放入rows中
				rows.add(cell);

			}

			// 将rows放入json对象中
			jsonObj.put("rows", rows);

			// 自控制台打印输出，以检验json对象生成是否正确
			//System.out.println("要返回的json对象：/n" + jsonObj.toString());

			// 设置字符编码
			resp.setCharacterEncoding("UTF-8");
			// 返回json对象（通过PrintWriter输出）
			resp.getWriter().print(jsonObj);

		}
	}

	public void addCP(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String cpCode = req.getParameter("code");
		String cpName = req.getParameter("name");
		String maxID = " (SELECT  NVL(MAX(cp_id)+1,1) FROM lcp_master) ";
		String nowDateTime = " (SELECT SYSDATE FROM dual";
		String SQL = "INSERT INTO LCP_MASTER" + "(CP_CODE," + "HOSPITAL_ID,"
				+ "CP_ID," + " CP_NAME," + " CP_START_DATE," + "CP_MASTER_ID,"
				+ "CP_VERSION," + "CP_STATUS," + "CP_VERSION_DATE,"
				+ "CP_CREATE_DATE," + "CP_CREATE_ID," + "CP_CREATE_NAME,"
				+ "CP_STOP_DATE," + "CP_DAYS_MIN," + "CP_DAYS_MAX,"
				+ "CP_DAYS," + "CP_FEE," + "CP_START_USER," + "CP_STOP_USER,"
				+ "DEPT_CODE," + "DEPT_NAME," + "VERIFY_DATE," + "VERIFY_CODE,"
				+ "VERIFY_NAME," + "SYS_IS_DEL," + "SYS_LAST_UPDATE,"
				+ "INPUT_CODE_PY," + "INPUT_CODE_WB)" + "VALUES" + "('"
				+ cpCode
				+ "',"
				+ "1,"
				+ maxID
				+ ","
				+ "'"
				+ cpName
				+ "',"
				+ "sysdate,"
				+ "0,"
				+ "2,"
				+ "0,"
				+ " TO_DATE('13-09-2011 10:43:17', 'dd-mm-yyyy hh24:mi:ss'),"
				+ " TO_DATE('13-09-2011 10:43:17', 'dd-mm-yyyy hh24:mi:ss'),"
				+ "'',"
				+ " '管理员',"
				+ "TO_DATE('25-10-2011 13:22:45', 'dd-mm-yyyy hh24:mi:ss'),"
				+ "11,"
				+ " 12,"
				+ " 12,"
				+ " 13000.00,"
				+ " '超级管理员',"
				+ " '超级管理员',"
				+ "'',"
				+ "'耳鼻喉科  ',"
				+ "NULL,"
				+ "'',"
				+ " '',"
				+ "0,"
				+ " TO_DATE('25-10-2011 14:00:38', 'dd-mm-yyyy hh24:mi:ss'),"
				+ " 'MXZEY'," + " '')";

		//System.out.println(cpCode + cpName);

		// //System.out.println(SQL);
		// DatabaseClass db = LcpUtil.getDatabaseClass();
		// DataSetClass dc = db.FunGetPageDataSetBySQL(SQL, , );

		JSONObject jsonObj = new JSONObject();
		// int rowCount = db.FunGetRowCountBySql(SQL);
		// 根据jqGrid对JSON的数据格式要求给jsonObj赋值

		// 设置字符编码
		resp.setCharacterEncoding("UTF-8");
		// 返回json对象（通过PrintWriter输出）
		resp.getWriter().print(jsonObj);

	}

	public enum SearchOpers {
		/*
		 * eq 等于( = ) ne 不等于( <> ) lt 小于( < ) le 小于等于( <= ) gt 大于( > ) ge 大于等于(
		 * >= ) bw 开始于 ( LIKE val% ) bn 不开始于 ( not like val%) in 在内 ( in ()) ni
		 * 不在内( not in ()) ew 结束于 (LIKE %val ) en 不结束于 cn 包含 (LIKE %val% ) nc
		 * 不包含
		 */

		eq, ne, lt, le, gt, ge, bw, bn, nu, nn, in, ni, ew, en, cn, nc;
		public static SearchOpers getType(String animal) {
			return valueOf(animal.toLowerCase());
		}
	}

	public void loadCPList(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		/*
		 * cp_name a dept_name b id _empty oper add
		 */
		// String pageRows = req.getParameter("rows"); //每页行数
		// String nowPage = req.getParameter("page");//当前页数
		String searchField; // 要查找的列
		String searchOper; // 查找方式
		String searchString;// 查找的字符串
		String sidx = req.getParameter("sidx");// 排序列
		String sord = req.getParameter("sord");// 排序方式 升序或降序
		boolean isSearch = Boolean.valueOf(req.getParameter("_search"));// 是否是搜索
		int pageRows = Integer.valueOf(req.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(req.getParameter("page")).intValue();// 当前页数
		int start = pageRows * (nowPage - 1) + 1;
		int end = pageRows * nowPage;
		// String nodeName = URLDecoder.decode(request.getParameter("nodeName"),
		// "UTF-8");
		String sql = "select cp_id,cp_name,dept_name,cp_version,cp_code,cp_status,cp_create_name from lcp_master ";

		if (isSearch) {
			searchField = req.getParameter("searchField");// cp_id
			searchOper = req.getParameter("searchOper");// eq
			searchString = req.getParameter("searchString"); // 1

			/*
			 * eq 等于( = ) ne 不等于( <> ) lt 小于( < ) le 小于等于( <= ) gt 大于( > ) ge
			 * 大于等于( >= ) bw 开始于 ( LIKE val% ) bn 不开始于 ( not like val%) in 在内 (
			 * in ()) ni 不在内( not in ()) ew 结束于 (LIKE %val ) en 不结束于 cn 包含 (LIKE
			 * %val% ) nc 不包含
			 */
			switch (SearchOpers.getType(searchOper)) {
			case ne:
				searchOper = " <> '" + searchString + "' ";
				break;
			case eq:
				searchOper = " =  '" + searchString + "' ";
				break;
			case lt:
				searchOper = " <  '" + searchString + "' ";
				break;
			case le:
				searchOper = " <= '" + searchString + "' ";
				break;
			case gt:
				searchOper = " >  '" + searchString + "' ";
				break;
			case ge:
				searchOper = " >= '" + searchString + "' ";
				break;
			case bw:
				searchOper = " LIKE '" + searchString + "%' ";
				break;
			case bn:
				searchOper = " not like '" + searchString + "%' ";
				break;
			case ew:
				searchOper = " like '%" + searchString + "'";
				break;
			case en:
				searchOper = " not like '%" + searchString + "'";
				break;
			case cn:
				searchOper = " LIKE '%" + searchString + "%' ";
				break;
			case nc:
				searchOper = " not like '%" + searchString + "%' ";
				break;
			case nu:
				searchOper = " is null ";
				break;
			case nn:
				searchOper = " is not null ";
				break;
			case in:
				searchOper = " in ('" + searchString + "') ";
				break;
			case ni:
				searchOper = " not in ('" + searchString + "') ";
				break;
			}
			sql += " where " + searchField + searchOper;
		}

		sql += " order by " + sidx + " " + sord;

		log.debug(sql);
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dc = db.FunGetPageDataSetBySQL(sql, start, end);

		JSONObject jsonObj = new JSONObject();
		int rowCount = db.FunGetRowCountBySql(sql);
		// 根据jqGrid对JSON的数据格式要求给jsonObj赋值
		jsonObj.put("page", nowPage); // 当前页
		jsonObj.put("total", rowCount / pageRows); // 总页数
		jsonObj.put("records", rowCount); // 总记录数

		// 定义rows，存放数据
		JSONArray rows = new JSONArray();
		if (dc.FunGetDataAsStringById(0, 0) != "") {
			for (int i = 0; i < dc.FunGetRowCount(); i++) {
				String id = dc.FunGetDataAsStringById(i, 0);
				String name = dc.FunGetDataAsStringById(i, 1);
				String dept = dc.FunGetDataAsStringById(i, 2);
				String version = dc.FunGetDataAsStringById(i, 3);
				String code = dc.FunGetDataAsStringById(i, 4);
				String status = dc.FunGetDataAsStringById(i, 5);
				String createName = dc.FunGetDataAsStringById(i, 6);

				// 放入数据
				JSONObject cell = new JSONObject();
				// cell.put("id", start+i);
				cell.put("cp_id", id);
				cell.put("cp_name", name);
				cell.put("dept_name", dept);
				cell.put("cp_version", version);
				cell.put("cp_code", code);
				cell.put("cp_status", status);
				cell.put("cp_create_name", createName);

				// 将该记录放入rows中
				rows.add(cell);

			}

			// 将rows放入json对象中
			jsonObj.put("rows", rows);

			// 自控制台打印输出，以检验json对象生成是否正确
			log.info("要返回的json对象：/n" + jsonObj.toString());

			// 设置字符编码
			resp.setCharacterEncoding("UTF-8");
			// 返回json对象（通过PrintWriter输出）
			resp.getWriter().print(jsonObj);

		}
	}

	/**
	 * 创建有模板的路径
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void createLocalCP(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String deptCode=(String) req.getSession().getAttribute("deptcode");
		String searchField; // 要查找的列
		String searchOper; // 查找方式
		String searchString;// 查找的字符串
		String sidx = req.getParameter("sidx");// 排序列
		String sord = req.getParameter("sord");// 排序方式 升序或降序
		boolean isSearch = Boolean.valueOf(req.getParameter("_search"));// 是否是搜索
		int pageRows = Integer.valueOf(req.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(req.getParameter("page")).intValue();// 当前页数
		int start = pageRows * (nowPage - 1) + 1;
		int end = pageRows * nowPage;
		String sql = "select cp_id,cp_name,cp_version,dept_name from lcp_master ";

		if (isSearch) {
			searchField = req.getParameter("searchField");// cp_id
			searchOper = req.getParameter("searchOper");// eq
			searchString = req.getParameter("searchString"); // 1

			/*
			 * eq 等于( = ) ne 不等于( <> ) lt 小于( < ) le 小于等于( <= ) gt 大于( > ) ge
			 * 大于等于( >= ) bw 开始于 ( LIKE val% ) bn 不开始于 ( not like val%) in 在内 (
			 * in ()) ni 不在内( not in ()) ew 结束于 (LIKE %val ) en 不结束于 cn 包含 (LIKE
			 * %val% ) nc 不包含
			 */
			switch (SearchOpers.getType(searchOper)) {
			case ne:
				searchOper = " <> '" + searchString.trim() + "' ";
				break;
			case eq:
				searchOper = " =  '" + searchString.trim() + "' ";
				break;
			case lt:
				searchOper = " <  '" + searchString.trim() + "' ";
				break;
			case le:
				searchOper = " <= '" + searchString.trim() + "' ";
				break;
			case gt:
				searchOper = " >  '" + searchString.trim() + "' ";
				break;
			case ge:
				searchOper = " >= '" + searchString.trim() + "' ";
				break;
			case bw:
				searchOper = " LIKE '" + searchString.trim() + "%' ";
				break;
			case bn:
				searchOper = " not like '" + searchString.trim() + "%' ";
				break;
			case ew:
				searchOper = " like '%" + searchString.trim() + "'";
				break;
			case en:
				searchOper = " not like '%" + searchString.trim() + "'";
				break;
			case cn:
				searchOper = " LIKE '%" + searchString.trim() + "%' ";
				break;
			case nc:
				searchOper = " not like '%" + searchString.trim() + "%' ";
				break;
			case nu:
				searchOper = " is null ";
				break;
			case nn:
				searchOper = " is not null ";
				break;
			case in:
				searchOper = " in ('" + searchString.trim() + "') ";
				break;
			case ni:
				searchOper = " not in ('" + searchString.trim() + "') ";
				break;
			}
			//sql += " where trim(" + searchField+ ")" + searchOper +" and cp_status=0 and trim(dept_code)<>'"+deptCode.trim()+"' order by " + sidx + " " + sord;
			sql += " where trim(" + searchField+ ")" + searchOper +" and cp_status=0 order by " + sidx + " " + sord;
		}else{
			//sql+="where cp_status=0 and trim(dept_code)<>'"+deptCode.trim()+"' order by dept_code, " + sidx + " " + sord;
			sql+="where cp_status=0 order by dept_code, " + sidx + " " + sord;
		}
		//sql += " order by " + sidx + " " + sord;

		////System.out.println("slq==:"+sql);
		log.debug(sql);
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dc = db.FunGetPageDataSetBySQL(sql, start, end);

		JSONObject jsonObj = new JSONObject();
		int rowCount = db.FunGetRowCountBySql(sql);
		int pageCount=0;
		if(rowCount%pageRows!=0){
			pageCount=rowCount / pageRows+1;
		}else{
			pageCount=rowCount / pageRows;
		}
		// 根据jqGrid对JSON的数据格式要求给jsonObj赋值
		jsonObj.put("page", nowPage); // 当前页
		jsonObj.put("total", pageCount); // 总页数
		jsonObj.put("records", rowCount); // 总记录数

		// 定义rows，存放数据
		JSONArray rows = new JSONArray();
		if (dc.FunGetDataAsStringById(0, 0) != "") {
			for (int i = 0; i < dc.FunGetRowCount(); i++) {
				String id = dc.FunGetDataAsStringById(i, 0);
				String name = dc.FunGetDataAsStringById(i, 1);
				String version = dc.FunGetDataAsStringById(i, 2);
				String deptName = dc.FunGetDataAsStringById(i, 3);

				// 放入数据
				JSONObject cell = new JSONObject();
				cell.put("cp_id", id);
				cell.put("cp_name", name);
				cell.put("cp_version", version);
				cell.put("dept_name", deptName);

				// 将该记录放入rows中
				rows.add(cell);

			}

			// 将rows放入json对象中
			jsonObj.put("rows", rows);

			// 自控制台打印输出，以检验json对象生成是否正确
			log.info("要返回的json对象：/n" + jsonObj.toString());

			// 设置字符编码
			resp.setCharacterEncoding("UTF-8");
			// 返回json对象（通过PrintWriter输出）
			resp.getWriter().print(jsonObj);

		}

	}

	private void copyCP(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String cpID = req.getParameter("cpId");
		String userID = (String) req.getSession().getAttribute("userid");
		String userName = (String) req.getSession().getAttribute("username");
		String deptcode = (String) req.getSession().getAttribute("deptcode");
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String selectSql="select cp_id from lcp_master where cp_master_id="+cpID + " and trim(dept_code)="+deptcode;
		if(db.FunGetDataSetBySQL(selectSql).FunGetDataAsStringById(0, 0) == ""){
			String maxCpID = db.FunGetDataSetBySQL("select max(cp_id) as cp_id from lcp_master").FunGetDataAsStringById(0, 0);
			int maxID = 0;
			if (maxCpID != "") {
				maxID = Integer.parseInt(maxCpID);
			} else {
				maxCpID = "10001";
			}
			String cpCode = (maxID + 1) + "-1";

			String nodeName1 = "准入";
			String nodeName2 = "完成";
			String nodeName3 = "变异退出";

			String sql = "insert into lcp_master (CP_CODE, HOSPITAL_ID, CP_ID, CP_NAME, CP_START_DATE, CP_MASTER_ID, CP_VERSION, CP_STATUS, CP_VERSION_DATE, CP_CREATE_DATE, CP_CREATE_ID, CP_CREATE_NAME, CP_STOP_DATE, CP_DAYS_MIN, CP_DAYS_MAX, CP_DAYS, CP_FEE, CP_START_USER, CP_STOP_USER, DEPT_CODE, DEPT_NAME, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE, INPUT_CODE_PY, INPUT_CODE_WB)"
					+ " values('"
					+ cpCode
					+ "', "
					+ HOSPITALID
					+ ", "
					+ (maxID + 1)
					+ ", (select cp_name from dcp_master where cp_id="
					+ cpID
					+ "), null, "
					+ cpID
					+ ", 1, 1, "
					+ CommonUtil.getOracleToDate()
					+ ", "
					+ CommonUtil.getOracleToDate()
					+ ", '"
					+ userID
					+ "', '"
					+ userName
					+ "', null, null, null, null, null, null, null, '"
					+ deptcode
					+ "', (select dept.dept_name from lcp_local_dept dept where dept.dept_code='"
					+ deptcode
					+ "'), null, null, null, 0, "
					+ CommonUtil.getOracleToDate()
					+ ", (select input_code_py from dcp_master where cp_id="
					+ cpID
					+ "), null)\n"
					+ "insert into lcp_master_node (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_NAME, CP_NODE_DAYS_MAX, CP_NODE_TYPE,CP_NODE_PARENT_ID, SYS_IS_DEL, SYS_LAST_UPDATE)"
					+ "values ("
					+ HOSPITALID
					+ ","
					+ (maxID + 1)
					+ ","
					+ 1
					+ ",'"
					+ nodeName1
					+ "',0,0,0, 0,"
					+ CommonUtil.getOracleToDate()
					+ ") \n"
					+ "insert into lcp_master_node (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_NAME, CP_NODE_DAYS_MAX, CP_NODE_TYPE,CP_NODE_PARENT_ID, SYS_IS_DEL, SYS_LAST_UPDATE)"
					+ "values ("
					+ HOSPITALID
					+ ","
					+ (maxID + 1)
					+ ","
					+ 2
					+ ",'"
					+ nodeName2
					+ "',0,2,0, 0,"
					+ CommonUtil.getOracleToDate()
					+ ") \n"
					+ "insert into lcp_master_node (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_NAME, CP_NODE_DAYS_MAX, CP_NODE_TYPE,CP_NODE_PARENT_ID, SYS_IS_DEL, SYS_LAST_UPDATE)"
					+ "values ("
					+ HOSPITALID
					+ ","
					+ (maxID + 1)
					+ ","
					+ 3
					+ ",'"
					+ nodeName3
					+ "',0,4,0, 0,"
					+ CommonUtil.getOracleToDate()
					+ ")";
			log.info("新建局发路径sql=："+sql);
			int res = db.FunRunSqlByFile(sql.getBytes());
			if (res > 0) {
				resp.getWriter().println("[{\"result\":\"OK\",\"cpID\":\"" + (maxID + 1) + "\"}]");
			} else {
				resp.getWriter().println("[{\"result\":\"fail\"}]");
			}
		}else{
			resp.getWriter().println("[{\"result\":\"exist\"}]");
		}
	}
	/**
	 * 创建路径
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void createCP(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String searchField; // 要查找的列
		String searchOper; // 查找方式
		String searchString;// 查找的字符串
		String sidx = req.getParameter("sidx");// 排序列
		String sord = req.getParameter("sord");// 排序方式 升序或降序
		boolean isSearch = Boolean.valueOf(req.getParameter("_search"));// 是否是搜索
		int pageRows = Integer.valueOf(req.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(req.getParameter("page")).intValue();// 当前页数
		int start = pageRows * (nowPage - 1) + 1;
		int end = pageRows * nowPage;
		// String nodeName = URLDecoder.decode(request.getParameter("nodeName"),
		// "UTF-8");
		String sql = "select cp_id,cp_name,cp_version,cp_create_date from dcp_master ";

		if (isSearch) {
			searchField = req.getParameter("searchField");// cp_id
			searchOper = req.getParameter("searchOper");// eq
			searchString = req.getParameter("searchString"); // 1

			/*
			 * eq 等于( = ) ne 不等于( <> ) lt 小于( < ) le 小于等于( <= ) gt 大于( > ) ge
			 * 大于等于( >= ) bw 开始于 ( LIKE val% ) bn 不开始于 ( not like val%) in 在内 (
			 * in ()) ni 不在内( not in ()) ew 结束于 (LIKE %val ) en 不结束于 cn 包含 (LIKE
			 * %val% ) nc 不包含
			 */
			switch (SearchOpers.getType(searchOper)) {
			case ne:
				searchOper = " <> '" + searchString + "' ";
				break;
			case eq:
				searchOper = " =  '" + searchString + "' ";
				break;
			case lt:
				searchOper = " <  '" + searchString + "' ";
				break;
			case le:
				searchOper = " <= '" + searchString + "' ";
				break;
			case gt:
				searchOper = " >  '" + searchString + "' ";
				break;
			case ge:
				searchOper = " >= '" + searchString + "' ";
				break;
			case bw:
				searchOper = " LIKE '" + searchString + "%' ";
				break;
			case bn:
				searchOper = " not like '" + searchString + "%' ";
				break;
			case ew:
				searchOper = " like '%" + searchString + "'";
				break;
			case en:
				searchOper = " not like '%" + searchString + "'";
				break;
			case cn:
				searchOper = " LIKE '%" + searchString + "%' ";
				break;
			case nc:
				searchOper = " not like '%" + searchString + "%' ";
				break;
			case nu:
				searchOper = " is null ";
				break;
			case nn:
				searchOper = " is not null ";
				break;
			case in:
				searchOper = " in ('" + searchString + "') ";
				break;
			case ni:
				searchOper = " not in ('" + searchString + "') ";
				break;
			}
			sql += " where " + searchField + searchOper;
		}

		sql += " order by " + sidx + " " + sord;

		log.debug(sql);
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dc = db.FunGetPageDataSetBySQL(sql, start, end);

		JSONObject jsonObj = new JSONObject();
		int rowCount = db.FunGetRowCountBySql(sql);
		int countPage = rowCount / pageRows;
		if (rowCount % pageRows != 0) {
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
				String id = dc.FunGetDataAsStringById(i, 0);
				String name = dc.FunGetDataAsStringById(i, 1);
				String version = dc.FunGetDataAsStringById(i, 2);
				//String cp_create_date = dc.FunGetDataAsStringById(i, 3);
				String cpDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dc.FunGetDataAsDateById(i, 3));

				// 放入数据
				JSONObject cell = new JSONObject();
				// cell.put("id", start+i);
				cell.put("cp_id", id);
				cell.put("cp_name", name);
				cell.put("cp_version", version);
				cell.put("cp_create_date", cpDate);

				// 将该记录放入rows中
				rows.add(cell);

			}

			// 将rows放入json对象中
			jsonObj.put("rows", rows);

			// 自控制台打印输出，以检验json对象生成是否正确
			////System.out.println("要返回的json对象：/n" + jsonObj.toString());

			// 设置字符编码
			resp.setCharacterEncoding("UTF-8");
			// 返回json对象（通过PrintWriter输出）
			resp.getWriter().print(jsonObj);

		}

	}
	
}