/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：CpMaintainServlet.java    
// 文件功能描述：路径维护servlet
//
// 创建人：刘植鑫
// 创建日期：2011/08/01
//
//----------------------------------------------------------------*/

package com.goodwillcis.lcp.servlet.cp;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.model.Page;
import com.goodwillcis.lcp.model.PageTable;
import com.goodwillcis.lcp.service.cp.AntiAndExchange;
import com.goodwillcis.lcp.service.cp.CP;
import com.goodwillcis.lcp.service.cp.DiaAndTrea;
import com.goodwillcis.lcp.service.cp.Doctor;
import com.goodwillcis.lcp.service.cp.IncludeExclude;
import com.goodwillcis.lcp.service.cp.Node;
import com.goodwillcis.lcp.service.cp.NodeIncludeAndExclude;
import com.goodwillcis.lcp.service.cp.Nurse;
import com.goodwillcis.lcp.service.cp.Order;
import com.goodwillcis.lcp.service.cp.PatientFamily;
import com.goodwillcis.lcp.service.cp.impl.AntiAndExchangeImpl;
import com.goodwillcis.lcp.service.cp.impl.CPimpl;
import com.goodwillcis.lcp.service.cp.impl.DiaAndTreaImpl;
import com.goodwillcis.lcp.service.cp.impl.DoctorImpl;
import com.goodwillcis.lcp.service.cp.impl.IncludeExcludeImpl;
import com.goodwillcis.lcp.service.cp.impl.NodeImpl;
import com.goodwillcis.lcp.service.cp.impl.NodeIncludeAndExcludeImpl;
import com.goodwillcis.lcp.service.cp.impl.NurseImpl;
import com.goodwillcis.lcp.service.cp.impl.OrderImpl;
import com.goodwillcis.lcp.service.cp.impl.PatientFamilyImpl;
import com.goodwillcis.lcp.util.LcpUtil;

public class CpMaintainServlet extends HttpServlet {

	private static final int PAGESIZE=20;
	//获取医院id
	private final int HOSPITAL_ID=LcpUtil.getHospitalID();
	private static final long serialVersionUID = 1L;
	private String userName="";
	private String deptCode = "";
	
	CP cp=new CPimpl();
	IncludeExclude includeExclude=new IncludeExcludeImpl();
	DiaAndTrea diaAndTrea=new DiaAndTreaImpl();
	AntiAndExchange antiAndExchange=new AntiAndExchangeImpl();
	Node node=new NodeImpl();
	NodeIncludeAndExclude includeAndExclude=new NodeIncludeAndExcludeImpl();
	Doctor doctor=new DoctorImpl();
	Nurse nurse=new NurseImpl();
	Order order=new OrderImpl();
	PatientFamily family=new PatientFamilyImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{   
		
		HttpSession session=request.getSession();
		userName=(String)session.getAttribute("username");
		
		response.setContentType("text/html; charset=UTF-8");
		String op = request.getParameter("op");
		if(op.startsWith("getViewTable_")){
			op = op.substring(0, op.indexOf("_"));
		}
		//System.out.println("op=:"+op);
		if ("getAllTable".equals(op)) {
			getAllTable(request, response);
		} else if ("startOrEndCp".equals(op)) {
			//userName=(String)session.getAttribute("username");
			startOrEndCp(request, response);
		} else if ("getOneNewCode".equals(op)) {
			getOneNewCode(request, response);
		} else if ("changeNode".equals(op)) {
			changeNode(request, response);
		} else if ("getOneNewNodeTable".equals(op)) {
			getOneNewNodeTable(request, response);
		} else if ("updateTable".equals(op)) {
			updateTable(request, response);
		} else if ("getViewTable".equals(op)) {//查询所有
			deptCode = (String)session.getAttribute("deptcode").toString();
			//System.out.println("部门号码是-----------" +deptCode);
			getViewTable(request, response);
		}else if ("getViewCoreTable".equals(op)) {
			getViewCoreTable(request, response);
		} else if ("getViewLocalTable".equals(op)) {
			getViewLocalTable(request, response);
		}else if ("startOrEndCpView".equals(op)) {
			startOrEndCpView(request, response);
		} else if ("getNewViewTable".equals(op)) {
			getNewViewTable(request, response);
		} else if ("addTwoCol".equals(op)) {
		}else if ("addThreeCol".equals(op)) {
		}else if ("addNodeOrder".equals(op)) {
	} 
	}
	private void getNewViewTable(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cpId = request.getParameter("cp_id_ajax");
		int cp_id=Integer.parseInt(cpId);
		String table=cp.funGetStartOrEndTable(HOSPITAL_ID,cp_id);
		response.getWriter().println(
				"[{\"result\":\"OK\", " +
				"\"table\":\""+table+"\", " +
				"\"method\":\"getNewViewTable\"}]");
	}
	private void getViewTable(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String bm = URLDecoder.decode(request.getParameter("bm"),"UTF-8").replace("'", "''");
		String mc = URLDecoder.decode(request.getParameter("mc"),"UTF-8").replace("'", "''");
		String py = URLDecoder.decode(request.getParameter("py"),"UTF-8").replace("'", "''");
		//String wb = URLDecoder.decode(request.getParameter("wb"),"UTF-8").replace("'", "''");
		String wb="";
		String ks = URLDecoder.decode(request.getParameter("ks"),"UTF-8").replace("'", "''");
		String page_no=URLDecoder.decode(request.getParameter("pageNo_ajax"),"UTF-8");
		String tableHtml="";
		String op = request.getParameter("op");
		String userID=(String)request.getSession().getAttribute("userid");
		String usersql = "select funs_id from dcp_sys_power where user_id="+userID;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dataSet = db.FunGetDataSetBySQL(usersql);
		String id="";
		String funid="";
		for(int i=0;i<dataSet.FunGetRowCount();i++){
			id=dataSet.FunGetDataAsStringByColName(i,"FUNS_ID");
			funid+=id;
		}
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(page_no);
		} catch (Exception e) {
			pageNo=1;
		}
//		String sql=cp.funGetAllSql(bm, mc, py, wb, deptCode,ks);
//		int totalRow=cp.funGetCountCp(HOSPITAL_ID,sql);
//		Page page=new Page(totalRow, pageNo, PAGESIZE);
//		int nowPage=page.getNowPage();
//		int start=page.getStart();
//		int end=page.getEnd();
//		int totalPage=page.getTotolPage();
//		PageTable pageTable=new PageTable();
//		String pageHtml=pageTable.funGetPageHtml(totalRow, nowPage, totalPage);
		String pageHtml="";
		//op = request.getParameter("op");
		//System.out.println("op==:"+op);
		if(funid.contains("61")){
			String sql=cp.funGetAllSql(bm, mc, py, wb, deptCode,ks);
			//System.out.println("sql11=:"+sql);
			
			
			if((op.substring(op.indexOf("_")+1, op.length())).equals("start")){
				sql = sql + " and T.cp_status=0 ";
			}
			if((op.substring(op.indexOf("_")+1, op.length())).equals("stop")){
				sql = sql + " and T.cp_status =1 ";
			}
			if((op.substring(op.indexOf("_")+1, op.length())).equals("verify")){
				sql = sql + " and T.cp_status=2 ";
			}
			if((op.substring(op.indexOf("_")+1, op.length())).equals("hidden")){
				sql = sql + " and T.cp_status=4 ";
			}
			if((op.substring(op.indexOf("_")+1, op.length())).equals("exit")){
				sql = sql + " and T.cp_status=3 ";
			}
			
			if((op.substring(op.indexOf("_")+1, op.length())).equals("all")){
				sql = sql + " and T.cp_status in(2,3) ORDER BY T.CP_STATUS,T.CP_ID DESC";
			
				sql = "select * from ("+sql+") " +
				" union all " +
				" select * from (SELECT * FROM " +
				" (SELECT A.*, B.CP_STATUS FORBID_ESTATE FROM LCP_MASTER A, LCP_HOSPITAL_VS_CP B " +
				" WHERE A.HOSPITAL_ID = B.HOSPITAL_ID(+) AND A.CP_ID = B.CP_ID(+)) T WHERE 1 = 1 and T.cp_status not in (2,3,4) ";
			
				if (bm != "") {
					sql = sql + " AND (CP_CODE like '%" + bm.toLowerCase()
							+ "%' or CP_CODE like '%" + bm.toUpperCase()
							+ "%' or CP_CODE like '%" + bm + "%' ) ";
				}
				if (mc != "") {
					sql = sql + " AND (CP_NAME like '%" + mc.toLowerCase()
							+ "%' or CP_NAME like '%" + mc.toUpperCase()
							+ "%' or CP_NAME like '%" + mc + "%' ) ";
				}
				if (py != "") {
					sql = sql + " AND (T.INPUT_CODE_PY like '%" + py.toLowerCase()
							+ "%' or T.INPUT_CODE_PY like '%" + py.toUpperCase()
							+ "%' or T.INPUT_CODE_PY like '%" + py + "%' )";
				}
				if (wb != "") {
					sql = sql + " AND (T.INPUT_CODE_WB like '%" + wb.toLowerCase()
							+ "%' or T.INPUT_CODE_WB like '%" + wb.toUpperCase()
							+ "%' or T.INPUT_CODE_WB like '%" + wb + "%') ";
				}
				if (ks != "") {
					sql = sql + " AND TRIM(T.DEPT_NAME) like '%" + ks + "%'";
				}
			
				sql = sql + " ORDER BY T.CP_STATUS,T.CP_id ASC)";
			}
			if(!(op.substring(op.indexOf("_")+1, op.length())).equals("all")){
				if (bm != "") {
					sql = sql + " AND (CP_CODE like '%" + bm.toLowerCase()
							+ "%' or CP_CODE like '%" + bm.toUpperCase()
							+ "%' or CP_CODE like '%" + bm + "%' ) ";
				}
				if (mc != "") {
					sql = sql + " AND (CP_NAME like '%" + mc.toLowerCase()
							+ "%' or CP_NAME like '%" + mc.toUpperCase()
							+ "%' or CP_NAME like '%" + mc + "%' ) ";
				}
				if (py != "") {
					sql = sql + " AND (T.INPUT_CODE_PY like '%" + py.toLowerCase()
							+ "%' or T.INPUT_CODE_PY like '%" + py.toUpperCase()
							+ "%' or T.INPUT_CODE_PY like '%" + py + "%' )";
				}
				if (wb != "") {
					sql = sql + " AND (T.INPUT_CODE_WB like '%" + wb.toLowerCase()
							+ "%' or T.INPUT_CODE_WB like '%" + wb.toUpperCase()
							+ "%' or T.INPUT_CODE_WB like '%" + wb + "%') ";
				}
				if (ks != "") {
					sql = sql + " AND TRIM(T.DEPT_NAME) like '%" + ks + "%'";
				}
				sql = sql + " ORDER BY T.CP_id DESC";
			}
			int totalRow=cp.funGetCountCp(HOSPITAL_ID,sql);
			Page page=new Page(totalRow, pageNo, PAGESIZE);
			int nowPage=page.getNowPage();
			int start=page.getStart();
			int end=page.getEnd();
			int totalPage=page.getTotolPage();
			PageTable pageTable=new PageTable();
			pageHtml=pageTable.funGetPageHtml(totalRow, nowPage, totalPage);
			//System.out.println("sql22=:"+sql);
			tableHtml=cp.funGetAllCPViewTableAdmin(sql, start, end);
			//System.out.println("sqlsssssssss=:" + sql);
		}else{
			String sql=cp.funGetAllSql(bm, mc, py, wb, deptCode,ks);
			if((op.substring(op.indexOf("_")+1, op.length())).equals("start")){
				sql = sql + " and TRIM(T.dept_code) = '"+deptCode+"' and T.cp_status=0 ORDER BY T.CP_ID";
			}
			if((op.substring(op.indexOf("_")+1, op.length())).equals("stop")){
				sql = sql + " and TRIM(T.dept_code) = '"+deptCode+"' and T.cp_status=1 ORDER BY T.CP_ID";
			}
			if((op.substring(op.indexOf("_")+1, op.length())).equals("verify")){
				sql = sql + " and TRIM(T.dept_code) = '"+deptCode+"' and T.cp_status=2 ORDER BY T.CP_ID";
			}
			if((op.substring(op.indexOf("_")+1, op.length())).equals("exit")){
				sql = sql + " and TRIM(T.dept_code) = '"+deptCode+"' and T.cp_status=3 ORDER BY T.CP_ID";
			}
			if((op.substring(op.indexOf("_")+1, op.length())).equals("all")){
				String sql1= sql + " and TRIM(T.dept_code) = '"+deptCode+"' and cp_status<>4 order by T.cp_status ";
				String sql2= "select * from ("+sql1 + ")  ";
				sql = sql2 + 
						" union all  " +
						" SELECT * FROM (SELECT A.*, B.CP_STATUS FORBID_ESTATE FROM LCP_MASTER A, LCP_HOSPITAL_VS_CP B " +
						" WHERE A.HOSPITAL_ID = B.HOSPITAL_ID(+) AND A.CP_ID = B.CP_ID(+)) T WHERE 1 = 1  " +
						" and T.cp_status =0 and TRIM(T.dept_code) <> '"+deptCode+"'";
			}if((op.substring(op.indexOf("_")+1, op.length())).equals("dept")){//显示本科室路径
				sql = sql + " and TRIM(T.dept_code) = '"+deptCode+"' and T.cp_status<>4 ORDER BY T.CP_STATUS";
			}if((op.substring(op.indexOf("_")+1, op.length())).equals("hidden")){//显示本科室路径
				sql = sql + " and TRIM(T.dept_code) = '"+deptCode+"' and T.cp_status=4 ORDER BY T.CP_ID";
			}
			//System.out.println("sql=:"+sql);
			int totalRow=cp.funGetCountCp(HOSPITAL_ID,sql);
			Page page=new Page(totalRow, pageNo, PAGESIZE);
			int nowPage=page.getNowPage();
			int start=page.getStart();
			int end=page.getEnd();
			int totalPage=page.getTotolPage();
			PageTable pageTable=new PageTable();
			pageHtml=pageTable.funGetPageHtml(totalRow, nowPage, totalPage);
			tableHtml=cp.funGetAllCPViewTable(sql, start, end);
			//System.out.println("sql=:" + sql);
		}
		response.getWriter().println(
				"[{\"result\":\"OK\", " +
				"\"table\":\""+tableHtml+"\", " +
				"\"pageHtml\":\""+pageHtml+"\", " +
				"\"method\":\"getViewTable\"}]");
	}
	
	private void getViewCoreTable(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String bm = URLDecoder.decode(request.getParameter("bm"),"UTF-8").replace("'", "''");
		String mc = URLDecoder.decode(request.getParameter("mc"),"UTF-8").replace("'", "''");
		String py = URLDecoder.decode(request.getParameter("py"),"UTF-8").replace("'", "''");
		String wb = URLDecoder.decode(request.getParameter("wb"),"UTF-8").replace("'", "''");
		String page_no=URLDecoder.decode(request.getParameter("pageNo_ajax"),"UTF-8");
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(page_no);
		} catch (Exception e) {
			pageNo=1;
		}
		String sql=cp.funGetCoreSql(bm, mc, py, wb);
		int totalRow=cp.funGetCountCp(HOSPITAL_ID,sql);
		Page page=new Page(totalRow, pageNo, PAGESIZE);
		int nowPage=page.getNowPage();
		int start=page.getStart();
		int end=page.getEnd();
		int totalPage=page.getTotolPage();
		PageTable pageTable=new PageTable();
		String pageHtml=pageTable.funGetPageHtml(totalRow, nowPage, totalPage);
		String tableHtml=cp.funGetCoreCPViewTable(sql, start, end);
		response.getWriter().println(
				"[{\"result\":\"OK\", " +
				"\"table\":\""+tableHtml+"\", " +
				"\"pageHtml\":\""+pageHtml+"\", " +
				"\"method\":\"getViewTable\"}]");
	}
	
	
	private void getViewLocalTable(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String bm = URLDecoder.decode(request.getParameter("bm"),"UTF-8").replace("'", "''");
		String mc = URLDecoder.decode(request.getParameter("mc"),"UTF-8").replace("'", "''");
		String py = URLDecoder.decode(request.getParameter("py"),"UTF-8").replace("'", "''");
		String wb = URLDecoder.decode(request.getParameter("wb"),"UTF-8").replace("'", "''");
		String page_no=URLDecoder.decode(request.getParameter("pageNo_ajax"),"UTF-8");
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(page_no);
		} catch (Exception e) {
			pageNo=1;
		}
		String sql=cp.funGetLocalSql(bm, mc, py, wb);
		int totalRow=cp.funGetCountCp(HOSPITAL_ID,sql);
		Page page=new Page(totalRow, pageNo, PAGESIZE);
		int nowPage=page.getNowPage();
		int start=page.getStart();
		int end=page.getEnd();
		int totalPage=page.getTotolPage();
		PageTable pageTable=new PageTable();
		String pageHtml=pageTable.funGetPageHtml(totalRow, nowPage, totalPage);
		String tableHtml=cp.funGetLocalCPViewTable(sql, start, end);
		response.getWriter().println(
				"[{\"result\":\"OK\", " +
				"\"table\":\""+tableHtml+"\", " +
				"\"pageHtml\":\""+pageHtml+"\", " +
				"\"method\":\"getViewTable\"}]");
	}
	private void getAllTable(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cpId = request.getParameter("cp_id_ajax");
		int cp_id=Integer.parseInt(cpId);
		boolean isExist=cp.funCpIsExist(HOSPITAL_ID, cp_id);
		boolean isForbid=cp.funCpIsForbit(HOSPITAL_ID, cp_id);
		boolean isUse=cp.funGetCpState(HOSPITAL_ID, cp_id);
		if(isExist){
			String cp_info_table=cp.funGetTable(HOSPITAL_ID, cp_id,isForbid);
			String include_exclude_table=includeExclude.funGetIncludeTable(HOSPITAL_ID,cp_id,isForbid);
			String exclude_table=includeExclude.funGetExcludeTable(HOSPITAL_ID, cp_id);
			String dia_table=diaAndTrea.funGetDiaTable(HOSPITAL_ID, cp_id);
			String trea_table=diaAndTrea.funGetTreaTable(HOSPITAL_ID, cp_id);
			String excharge_table=antiAndExchange.funGetExcharge(HOSPITAL_ID, cp_id);
			String anti_table=antiAndExchange.funGetAntTable(HOSPITAL_ID, cp_id);
			boolean isNodeExist=node.funIsExistNodeIDByCPID(HOSPITAL_ID, cp_id);
			String node_table="";
			String node_include_table="";
			String node_exclude_table="";
			String doctor_table="";
			String nurse_table="";
			String order_table="";
			String family_table="";
			if(isNodeExist){
				node_table=node.funGetTable(HOSPITAL_ID, cp_id);
				node_include_table=includeAndExclude.funGetNodeInTable(HOSPITAL_ID, cp_id, 1);
				node_exclude_table=includeAndExclude.funGetNodeOutTable(HOSPITAL_ID, cp_id, 1);
				doctor_table=doctor.funGetTable(HOSPITAL_ID, cp_id, 1,isForbid);
				nurse_table=nurse.funGetTable(HOSPITAL_ID, cp_id, 1,isForbid);
				order_table=order.funGetTable(HOSPITAL_ID, cp_id, 1,isForbid);
				family_table=family.funGetTable(HOSPITAL_ID, cp_id, 1);
			}
			
			response.getWriter().println(
					"[{\"result\":\"OK\", " +
					"\"cp_info_table\":\""+cp_info_table+"\", " +
					"\"include_exclude_table\":\""+include_exclude_table+"\", " +
					"\"exclude_table\":\""+exclude_table+"\", " +
					"\"trea_table\":\""+trea_table+"\", " +
					"\"dia_table\":\""+dia_table+"\", " +
					"\"excharge_table\":\""+excharge_table+"\", " +
					"\"anti_table\":\""+anti_table+"\", " +
					"\"node_table\":\""+node_table+"\", " +
					"\"node_include_table\":\""+node_include_table+"\", " +
					"\"node_exclude_table\":\""+node_exclude_table+"\", " +
					"\"doctor_table\":\""+doctor_table+"\", " +
					"\"nurse_table\":\""+nurse_table+"\", " +
					"\"order_table\":\""+order_table+"\", " +
					"\"family_table\":\""+family_table+"\", " +
					"\"isForbid\":\""+isForbid+"\", " +
					"\"isUse\":\""+isUse+"\", " +
					"\"method\":\"getAllTable\"}]");
		}else{
			response.getWriter().println(
					"[{\"result\":\"ERROR\", " +
					"\"method\":\"getAllTable\"}]");
		}
	}
	private void startOrEndCp(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cpId = request.getParameter("cp_id_ajax");
		String cp_state = request.getParameter("cp_state_ajax");
		String fileName="";
		String startOrEndField="";
		boolean isUse=false;
		if(cp_state.equals("1")){
			fileName="CP_STOP_USER";
			startOrEndField="CP_STOP_DATE";
		}else{
			fileName="CP_START_USER";
			startOrEndField="CP_START_DATE";
			isUse=true;
		}	
		int cp_id=Integer.parseInt(cpId);
		boolean isForbid=cp.funCpIsForbit(HOSPITAL_ID, cp_id);
		boolean isSuc=cp.funStartOrEndCpRoute(HOSPITAL_ID, cp_id, cp_state, fileName, userName,startOrEndField);
		if(isSuc){
			String cp_info_table=cp.funGetTable(HOSPITAL_ID, cp_id,isForbid);
			response.getWriter().println(
					"[{\"result\":\"OK\", " +
					"\"cp_info_table\":\""+cp_info_table+"\", " +
					"\"isUse\":\""+isUse+"\", " +
					"\"method\":\"startOrEndCp\"}]");
		}else{
			response.getWriter().println(
					"[{\"result\":\"ERROR\", " +
					"\"method\":\"startOrEndCp\"}]");
		}
	}
	private void startOrEndCpView(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cpId = request.getParameter("cp_id_ajax");
		String cp_state = request.getParameter("cp_state_ajax");
		String fileName="";
		String startOrEndField="";
		if(cp_state.equals("1")){
			fileName="CP_STOP_USER";
			startOrEndField="CP_STOP_DATE";
		}else{
			fileName="CP_START_USER";
			startOrEndField="CP_START_DATE";
		}	
		int cp_id=Integer.parseInt(cpId);
		boolean isSuc=cp.funStartOrEndCpRoute(HOSPITAL_ID, cp_id, cp_state, fileName, userName,startOrEndField);
		if(isSuc){
			String table=cp.funGetStartOrEndTable(HOSPITAL_ID,cp_id);
			response.getWriter().println(
					"[{\"result\":\"OK\", " +
					"\"table\":\""+table+"\", " +
					"\"method\":\"startOrEndCpView\"}]");
		}else{
			response.getWriter().println(
					"[{\"result\":\"ERROR\", " +
					"\"method\":\"startOrEndCpView\"}]");
		}
	}
	private void getOneNewCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id_ajax = request.getParameter("cp_id_ajax");
		int cp_id=Integer.parseInt(cp_id_ajax);
		String cp_node_id_ajax = request.getParameter("cp_node_id_ajax");
		int cp_node_id=Integer.parseInt(cp_node_id_ajax);
		String cp_node_table_id_ajax = request.getParameter("cp_node_table_id_ajax");
		int cp_node_table_id=Integer.parseInt(cp_node_table_id_ajax);
		String cp_node_table_item_id_ajax = request.getParameter("cp_node_table_item_id_ajax");
		int cp_node_table_item_id=Integer.parseInt(cp_node_table_item_id_ajax);
		String cp_or_node_ajax = request.getParameter("cp_or_node_ajax");
		int cp_or_node=Integer.parseInt(cp_or_node_ajax);
		String tab_index_ajax = request.getParameter("tab_index_ajax");
		int tab_index=Integer.parseInt(tab_index_ajax);
		String code="";
		if(cp_or_node==1){
			//上层标签tab
			code=includeExclude.funGetNewLocal(HOSPITAL_ID, cp_id, cp_node_table_id);
			
		}else{
			//下层层标签tab	
			if(tab_index==1){
				code=doctor.funGetNewCode(HOSPITAL_ID, cp_id, cp_node_id, cp_node_table_id, cp_node_table_item_id);
			}
			if(tab_index==2){
				code=nurse.funGetNewCode(HOSPITAL_ID, cp_id, cp_node_id, cp_node_table_id, cp_node_table_item_id);
			}
			if(tab_index==3){
				code=order.funGetNewCode(HOSPITAL_ID, cp_id, cp_node_id, cp_node_table_id, cp_node_table_item_id);
			}
		}
		
		
		response.getWriter().println(
				"[{\"result\":\"OK\", " +
				"\"code\":\""+code+"\", " +
				"\"method\":\"getOneNewCode\"}]");
		
	}
	private void getOneNewNodeTable(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id_ajax = request.getParameter("cp_id_ajax");
		int cp_id=Integer.parseInt(cp_id_ajax);
		String cp_node_id_ajax = request.getParameter("cp_node_id_ajax");
		int cp_node_id=Integer.parseInt(cp_node_id_ajax);
		String tab_index_ajax = request.getParameter("tab_index_ajax");
		int tab_index=Integer.parseInt(tab_index_ajax);
		String table="";
		boolean isForbid=cp.funCpIsForbit(HOSPITAL_ID, cp_id);
		if(tab_index==1){
			table=doctor.funGetTable(HOSPITAL_ID, cp_id, cp_node_id, isForbid);
		}
		if(tab_index==2){
			table=nurse.funGetTable(HOSPITAL_ID, cp_id, cp_node_id, isForbid);
		}
		if(tab_index==3){
			table=order.funGetTable(HOSPITAL_ID, cp_id, cp_node_id, isForbid);
		}
		
		boolean isUse=cp.funGetCpState(HOSPITAL_ID, cp_id);
		response.getWriter().println(
				"[{\"result\":\"OK\", " +
				"\"table\":\""+table+"\", " +
				"\"isUse\":\""+isUse+"\", " +
				"\"method\":\"getOneNewNodeTable\"}]");
		
	}
	private void updateTable(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String update_quanju = request.getParameter("update_quanju_ajax");
		String table_index=update_quanju.substring(9, 10);
		String cpId = request.getParameter("cp_id_ajax");
		int cp_id=Integer.parseInt(cpId);
		String _cp_node_id = request.getParameter("cp_node_id_ajax");
		int cp_node_id=Integer.parseInt(_cp_node_id);
		String table="";
		boolean isUpdateSuc=false;
		if(table_index.equals("1")){
			isUpdateSuc=doctor.funSetNodeItem(HOSPITAL_ID, update_quanju);
		}
		if(table_index.equals("2")){
			isUpdateSuc=nurse.funSetNodeItem(HOSPITAL_ID, update_quanju);
		}
		if(table_index.equals("3")){
			isUpdateSuc=order.funSetNodeItem(HOSPITAL_ID, update_quanju);
		}
		if(isUpdateSuc){
			if(table_index.equals("1")){
				table=doctor.funGetTable(HOSPITAL_ID, cp_id, cp_node_id, true);
			}
			if(table_index.equals("2")){
				table=nurse.funGetTable(HOSPITAL_ID, cp_id, cp_node_id, true);
			}
			if(table_index.equals("3")){
				table=order.funGetTable(HOSPITAL_ID, cp_id, cp_node_id, true);
			}
			response.getWriter().println(
					"[{\"result\":\"OK\", " +
					"\"table\":\""+table+"\", " +
					"\"method\":\"updateTable\"}]");
		}else{
			response.getWriter().println(
					"[{\"result\":\"ERROR\", " +
					"\"method\":\"updateTable\"}]");
		}
		
	}
	private void changeNode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cpId = request.getParameter("cp_id_ajax");
		int cp_id=Integer.parseInt(cpId);
		String _cp_node_id = request.getParameter("cp_node_id_ajax");
		int cp_node_id=Integer.parseInt(_cp_node_id);
		//System.out.println(cp_node_id);
		String node_include_table="";
		String node_exclude_table="";
		String doctor_table="";
		String nurse_table="";
		String order_table="";
		String family_table="";
		long start=new Date().getTime();
		boolean isForbid=cp.funCpIsForbit(HOSPITAL_ID, cp_id);
		long end=new Date().getTime();
		//System.out.println("1"+(end-start));
		node_include_table=includeAndExclude.funGetNodeInTable(HOSPITAL_ID, cp_id, cp_node_id);
		end=new Date().getTime();
		//System.out.println("2"+(end-start));
		node_exclude_table=includeAndExclude.funGetNodeOutTable(HOSPITAL_ID, cp_id, cp_node_id);
		end=new Date().getTime();
		//System.out.println("3"+(end-start));
		doctor_table=doctor.funGetTable(HOSPITAL_ID, cp_id, cp_node_id,isForbid);
		end=new Date().getTime();
		//System.out.println("4"+(end-start));
		nurse_table=nurse.funGetTable(HOSPITAL_ID, cp_id, cp_node_id,isForbid);
		end=new Date().getTime();
		//System.out.println("5"+(end-start));
		order_table=order.funGetTable(HOSPITAL_ID, cp_id, cp_node_id,isForbid);
		end=new Date().getTime();
		//System.out.println("6"+(end-start));
		family_table=family.funGetTable(HOSPITAL_ID, cp_id, cp_node_id);
		boolean isUse=cp.funGetCpState(HOSPITAL_ID, cp_id);
		end=new Date().getTime();
		//System.out.println("1"+(end-start));
		response.getWriter().println(
				"[{\"result\":\"OK\", " +
				"\"node_include_table\":\""+node_include_table+"\", " +
				"\"node_exclude_table\":\""+node_exclude_table+"\", " +
				"\"doctor_table\":\""+doctor_table+"\", " +
				"\"nurse_table\":\""+nurse_table+"\", " +
				"\"order_table\":\""+order_table+"\", " +
				"\"family_table\":\""+family_table+"\", " +
				"\"isUse\":\""+isUse+"\", " +
				"\"method\":\"changeNode\"}]");
	}
	
	/**
	 * 已启用
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void getViewStartTable(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
	}
	
	/**
	 * 已停用
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void getViewStopTable(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
	}
	
	/**
	 * 审核中
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void getViewVerifyTable(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
	}
}
