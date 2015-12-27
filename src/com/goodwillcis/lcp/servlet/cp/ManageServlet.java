/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：ManageServlet.java    
// URL:"../servlet/manage"
// 文件功能描述：处理院内路径定义查看信息,接受并处理所有增删改信息
//
// 创建人：潘状
// 创建日期：2011-7-25
// 修改日期:2011-7-25
//
//----------------------------------------------------------------*/

package com.goodwillcis.lcp.servlet.cp;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;
import com.goodwillcis.lcp.util.PropertiesUtil;


public class ManageServlet extends HttpServlet {
	
	//获取医院id
	private final int HOSPITAL_ID=LcpUtil.getHospitalID();
	private static final long serialVersionUID = 1L;
	DatabaseClass dbs = LcpUtil.getDatabaseClass();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html; charset=UTF-8");
		String op = request.getParameter("op");
		if ("select".equals(op)) {
			selectMaster(request, response);
		} else if ("start_cp".equals(op) || "stop_cp".equals(op)) {
			startOrStopCp(request, response);
		} else if ("addCP".equals(op)) {
			addCP(request, response);
		} else if ("editCP".equals(op)) {
			editCP(request, response);
		} else if ("lcp_master_based".equals(op)) {
			updataMasterBased(request, response);
		} else if ("updata_antibiotics_based".equals(op)) {
			updataAntibioticsBased(request, response);
		} else if ("add_lcp_master_income".equals(op)) {
			addLcpMasterIncome(request, response);
		} else if ("delTrs".equals(op)) {
			del(request, response);
		} else if ("delNode".equals(op)) {
			delNode(request, response);
		} else if ("addNode".equals(op)) {
			addNode(request, response);
		} else if ("addTwoCol".equals(op)) {
			addTwoCol(request, response);
		}else if ("addThreeCol".equals(op)) {
			addThreeCol(request, response);
		}else if ("addNodeOrder".equals(op)) {
			addNodeOrder(request, response);
	} 
	}
	private void addNodeOrder(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
	
		String cpId = request.getParameter("cpId");
		String cp_node_id = request.getParameter("cp_node_id").replaceAll("tr", "");
		String orderCode = request.getParameter("orderCode");
		String orderName=URLDecoder.decode(request.getParameter("orderName"),"UTF-8");
		String type = request.getParameter("type");
		String need = request.getParameter("need");
		String auto = request.getParameter("auto");
		String maxIdSql="SELECT MAX(CP_NODE_ORDER_ID)+1 MAXID from LCP_NODE_ORDER where cp_id="+cpId+" and cp_node_id="+cp_node_id;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dc=db.FunGetDataSetBySQL(maxIdSql);
		Number maxid=dc.FunGetDataAsNumberById(0, 0);
		if(maxid==null){ maxid=1;}
		
		String sql="	insert into lcp_node_order (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_ORDER_ID, CP_NODE_ORDER_TEXT, NEED_ITEM,AUTO_ITEM, ORDER_NO, ORDER_TYPE, SYS_IS_DEL, SYS_LAST_UPDATE)"+
					" values ("+HOSPITAL_ID+","+cpId+","+cp_node_id+", "+maxid+", '"+orderName+"', "+need+","+auto+", '"+orderCode+"', '"+type+"', 0,"+CommonUtil.getOracleToDate()+")";
		String key = db.FunGetSvrKey();
		int isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		if (isUpdateSuc > 0) {
			response.getWriter().println(
					"[{\"result\":\"OK\", \"id\":\""+maxid+"\"}]");
		} else {
			response.getWriter().println(
					"[{\"result\":\"fail\"}]");
		}
		
		
	}
	/**  方法说明:添加新的路径  @author 潘状*/
	private void addCP(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_name=URLDecoder.decode(request.getParameter("cp_name"),"UTF-8");
		
		String min_day = request.getParameter("min_day");
		String max_day = request.getParameter("max_day");
		String avg_day = request.getParameter("avg_day");
		String avg_fee = request.getParameter("avg_fee");
		String dept_name = request.getParameter("dept_name");
		String dept_code = request.getParameter("dept_name");
		String date=CommonUtil.getOracleToDate();
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String maxIdSql="SELECT MAX(CP_ID)+1 MAXID from LCP_master ";
		DataSetClass dc=db.FunGetDataSetBySQL(maxIdSql);
		Number maxid=dc.FunGetDataAsNumberById(0, 0);
		if(maxid==null){ maxid=1;}
		String sql="insert into LCP_master (" +
				"CP_ID, CP_NAME, CP_START_DATE, CP_MASTER_ID, CP_VERSION, CP_STATUS, CP_VERSION_DATE, CP_CREATE_DATE," +
				" CP_CREATE_ID, CP_CREATE_NAME, CP_STOP_DATE, CP_DAYS_MIN, CP_DAYS_MAX, CP_DAYS, CP_FEE, CP_START_USER," +
				" CP_STOP_USER, DEPT_CODE, DEPT_NAME, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE)"+
		"values ("+maxid+", '"+cp_name+"', "+date+", 0, 1, 0, "+date+", "+date+"," +
				" '', '管理员', null, "+min_day+","+max_day+","+avg_day+", "+avg_fee+", '', " +
				"'', '"+dept_code+"', '"+dept_name+"', null, '', '', 0, "+date+")";
		
		String key = db.FunGetSvrKey();
		int isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		if (isUpdateSuc > 0) {
			response.getWriter().println("[{\"result\":\"OK\", \"id\":\""+maxid+"\"}]");}
		else  response.getWriter().println("[{\"result\":\"fail\"}]");		

	}

	/**  方法说明:编辑路径  
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void editCP(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String cpId = request.getParameter("cp_id");
		String cp_name=URLDecoder.decode(request.getParameter("cp_name"),"UTF-8");
		String min_day = request.getParameter("min_day");
		String max_day = request.getParameter("max_day");
		String avg_day = request.getParameter("avg_day");
		String avg_fee = request.getParameter("avg_fee");
		String dept_name = URLDecoder.decode(request.getParameter("dept_name"),"UTF-8");
		String dept_code = request.getParameter("dept_code");
		
		
		String date=CommonUtil.getOracleToDate();
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String key = db.FunGetSvrKey();
		String maxIdSql="SELECT MAX(CP_ID)+1 MAXID from LCP_master ";
		DataSetClass dc=db.FunGetDataSetBySQL(maxIdSql);
		Number maxid=dc.FunGetDataAsNumberById(0, 0);
		if(maxid==null){ maxid=1;}
		
		String sql="update LCP_master set CP_NAME='"+cp_name+"',CP_DAYS_MIN="+min_day+",CP_DAYS_MAX="+max_day+
				",CP_DAYS="+avg_day+",CP_FEE="+avg_fee+",DEPT_CODE='"+dept_code+"',DEPT_NAME='"+dept_name+"',SYS_LAST_UPDATE="+date+
				" where cp_id="+cpId;
		int isUpdateSuc = db.FunRunSQLCommand(key, sql);
		if (isUpdateSuc > 0) {
				response.getWriter().println("[{\"result\":\"OK\",\"id\":\"1\"}]");}
			else{
				response.getWriter().println("[{\"result\":\"fail\"}]");		
				}
	}
	
	/**  方法说明:删除节点  @author 潘状*/
	private void delNode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cpId = request.getParameter("cp_id");
		String cp_node_id=request.getParameter("cp_node_id").replaceAll("tr", "");
		String sql="delete LCP_master_node  whercpIdid="+cpId+" and cp_node_id="+cp_node_id;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String key = db.FunGetSvrKey();
		int isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		if (isUpdateSuc > 0) {
			response.getWriter().println(
					"[{\"result\":\"OK\",\"flag\":\"1\"}]");
		} else {
			response.getWriter().println(
			"[{\"result\":\"OK\", \"flag\":\"0\"}]");		}
	}
	
	/**  方法说明:添加节点  
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addNode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		
		String cpId = request.getParameter("cp_id");	
		String nodeName=URLDecoder.decode(request.getParameter("nodeName"),"UTF-8");
		String nodeDate = request.getParameter("nodeDate");
		String nodeType = request.getParameter("nodeType");
		
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String maxIdSql="SELECT MAX(CP_NODE_ID)+1 MAXID from LCP_master_node where cp_id="+cpId;
		DataSetClass dc=db.FunGetDataSetBySQL(maxIdSql);
		Number maxid=dc.FunGetDataAsNumberById(0, 0);
		if(maxid==null){ maxid=1;}
		
		String sql="insert into LCP_MASTER_NODE (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_STD_NODE_ID,CP_NODE_NAME, CP_NODE_DAYS, CP_NODE_TYPE,CP_NODE_PARENT_ID, SYS_IS_DEL, SYS_LAST_UPDATE)"+
		"values ("+HOSPITAL_ID+","+cpId+","+maxid+",0,'"+nodeName+"',"+nodeDate+" ,"+nodeType+",0, 0," +CommonUtil.getOracleToDate()+")";
		
		String key = db.FunGetSvrKey();
		int isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		if (isUpdateSuc > 0) {
			response.getWriter().println(
					"[{\"result\":\"OK\", \"id\":\""+maxid+"\",\"flag\":\"1\"}]");
		} else {
			response.getWriter().println(
			"[{\"result\":\"OK\", \"flag\":\"0\"}]");		}
	}
		
	/**  方法说明:添加主要诊疗工作  主要护理工作 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addThreeCol(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			String cpId = request.getParameter("cp_id");	
			String cp_node_id=request.getParameter("cp_node_id").replaceAll("tr", "").trim();
			String tableName = request.getParameter("tableName");//生成表名
			String ColNameID = request.getParameter("tableName").replaceAll("lcp_", "cp_").trim()+"_id";//生成表名
			String text = URLDecoder.decode(request.getParameter("text"),"UTF-8");
			String code=request.getParameter("code");
			String need=request.getParameter("need");
			String auto=request.getParameter("auto");
			String maxIdSql="SELECT MAX("+ColNameID+")+1 MAXID from "+tableName+" where cp_id="+cpId+" and cp_node_id="+cp_node_id;
			DatabaseClass db = LcpUtil.getDatabaseClass();
			DataSetClass dc=db.FunGetDataSetBySQL(maxIdSql);
			Number maxid=dc.FunGetDataAsNumberById(0, 0);
			if(maxid==null){ maxid=1;}
			
			String sql="insert into LCP_NODE_DOCTOR (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_DOCTOR_ID, CP_NODE_DOCTOR_TEXT, NEED_ITEM,AUTO_ITEM, DOCTOR_NO, SYS_IS_DEL, SYS_LAST_UPDATE)"+
						" values ("+HOSPITAL_ID+","+cpId+","+cp_node_id+","+maxid+",'" +text+"'," +need+","+auto+",'" +code+"',"+"0,"+CommonUtil.getOracleToDate()+")";
			
			if(!"lcp_node_doctor".equals(tableName)){
				 sql="insert into LCP_NODE_NURSE (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_NURSE_ID, CP_NODE_NURSE_TEXT, NEED_ITEM,AUTO_ITEM, NURSE_NO, SYS_IS_DEL, SYS_LAST_UPDATE)"+
					 " values ("+HOSPITAL_ID+","+cpId+","+cp_node_id+","+maxid+",'" +text+"'," +need+","+auto+",'" +code+"',"+"0,"+CommonUtil.getOracleToDate()+")";
				 			
			}
			
			String key = db.FunGetSvrKey();
			int isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
			if (isUpdateSuc > 0) {
				response.getWriter().println(
						"[{\"result\":\"OK\", \"id\":\""+maxid+"\",\"flag\":\"1\"}]");
			} else {
				response.getWriter().println("[{\"result\":\"OK\", \"flag\":\"0\"}]");		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().println("[{\"result\":\"fail\", \"flag\":\"0\"}]");		}
	}
	
	
	/**方法说明:添加路径排除条件 /添加输出节点/添加变异记录/添加出院标准/添加常见变异
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addTwoCol(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cpId = request.getParameter("cp_id");		
		String tableName = request.getParameter("tableName");
		String datas=URLDecoder.decode(request.getParameter("datas"),"UTF-8");
		String colNameID=tableName.replaceAll("lcp_master_", "cp_")+"_id";//生成列名       cp_*_id
		String colNameName=tableName.replaceAll("lcp_master_", "cp_")+"_name";//生成列名 cp_*_name
		String sql="";
		Number maxid=null;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		if("lcp_node_relate".equals(tableName)){
			String cp_node_id=request.getParameter("cp_node_id").replaceAll("tr", "").trim();
			sql="insert into LCP_NODE_RELATE (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NEXT_NODE_ID, SYS_IS_DEL, SYS_LAST_UPDATE)"+
			"values ("+HOSPITAL_ID+","+cpId+","+cp_node_id+","+datas.replaceAll("tr", "")+" , 0,"+CommonUtil.getOracleToDate()+")";
		}else if("lcp_node_variation".equals(tableName)){
			String cp_node_id=request.getParameter("cp_node_id").replaceAll("tr", "").trim();
			String maxIdSql="SELECT MAX(CP_NODE_VARIATION_ID)+1 MAXID from LCP_NODE_VARIATION where cp_id="+cpId +" AND CP_NODE_ID="+cp_node_id;
			DataSetClass dc=db.FunGetDataSetBySQL(maxIdSql);
			 maxid=dc.FunGetDataAsNumberById(0, 0);
			if(maxid==null){maxid=1;}
			sql="insert into LCP_NODE_VARIATION (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_VARIATION_ID, CP_NODE_VARIATION_TEXT, SYS_IS_DEL, SYS_LAST_UPDATE)"+
				"values ("+HOSPITAL_ID+","+cpId+","+cp_node_id+","+maxid+",'"+datas+"' , 0,"+CommonUtil.getOracleToDate()+")";
		}else{
			String maxIdSql="SELECT MAX("+colNameID+")+1 MAXID from "+tableName+" where cp_id="+cpId;		
			DataSetClass dc=db.FunGetDataSetBySQL(maxIdSql);
			maxid=dc.FunGetDataAsNumberById(0, 0);
			if(maxid==null){	maxid=1;}
			 sql="insert into "+tableName+"(HOSPITAL_ID,cp_id,"+colNameID+","+colNameName+",SYS_IS_DEL,SYS_LAST_UPDATE) values("+HOSPITAL_ID+","
												+cpId+","+maxid+",'"+datas+"',0,"+CommonUtil.getOracleToDate()+")";
		}
		String key = db.FunGetSvrKey();
		int isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		if (isUpdateSuc > 0) {
			response.getWriter().println("[{\"result\":\"OK\", \"id\":\""+maxid+"\"}]");
		} else {
			response.getWriter().println(
					"[{\"result\":\"fail\"}]");
		}
	}
	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addLcpMasterIncome(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cpId = request.getParameter("cp_id");
		String CP_DIAGNOSE_TYPE=URLDecoder.decode(request.getParameter("CP_DIAGNOSE_TYPE"),"UTF-8");
		String CP_DIAGNOSE_NAME=URLDecoder.decode(request.getParameter("CP_DIAGNOSE_NAME"),"UTF-8");
		String CP_DIAGNOSE_CODE=request.getParameter("CP_DIAGNOSE_CODE");
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dc=db.FunGetDataSetBySQL("SELECT MAX(CP_INCOME_ID)+1 MAXID from LCP_MASTER_INCOME where cp_id="+cpId);
		Number maxid=1;
		if(dc.FunGetDataAsNumberById(0, 0)!=null){
			maxid=dc.FunGetDataAsNumberById(0, 0);
		}
		String sql="INSERT INTO LCP_MASTER_INCOME" +
				" (HOSPITAL_ID,CP_ID, CP_INCOME_ID, CP_INCOME_TYPE, CP_INCOME_NAME, CP_INCOME_CODE, SYS_IS_DEL, SYS_LAST_UPDATE)"+
				"values ("+HOSPITAL_ID+","+cpId+", "+maxid+",'"+CP_DIAGNOSE_TYPE+"', '"+CP_DIAGNOSE_NAME+"', '"+CP_DIAGNOSE_CODE+"', 0, "+CommonUtil.getOracleToDate()+")";
		
		String key = db.FunGetSvrKey();
		int isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		if (isUpdateSuc > 0) {
			response.getWriter().println("[{\"result\":\"OK\", \"id\":\""+maxid+"\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"fail\"}]");
		}
	}
	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void updataAntibioticsBased(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		String cpId = request.getParameter("cp_id");
		String tableName=request.getParameter("tableName");
		String selectSQL="";
		String sql="";
		String addSQL="";
		String date=CommonUtil.getOracleToDate();
		if("lcp_master_based".equals(tableName)){
			String cp_diagnosis_based=URLDecoder.decode(request.getParameter("CP_DIAGNOSIS_BASED"),"UTF-8");
			String cp_treatment=URLDecoder.decode(request.getParameter("CP_TREATMENT"),"UTF-8");
			sql="update lcp_master_based set cp_diagnosis_based='"+cp_diagnosis_based+"',cp_treatment='"+cp_treatment+"',sys_last_update="+CommonUtil.getOracleToDate()+" where cp_id="+cpId;
			addSQL="insert into lcp_master_based (HOSPITAL_ID,CP_ID, CP_DIAGNOSIS_BASED, CP_TREATMENT, SYS_IS_DEL, SYS_LAST_UPDATE)"+"values (1,"+cpId+", '"+cp_diagnosis_based+"', '"+cp_treatment+"', 0,"+date+ ")";
		}else {
			String cp_antibiotics=URLDecoder.decode(request.getParameter("CP_ANTIBIOTICS"),"UTF-8");
			sql="update LCP_master_antibiotics set cp_antibiotics='"+cp_antibiotics+"',sys_last_update="+CommonUtil.getOracleToDate()+" where cp_id="+cpId;
			addSQL="insert into lcp_master_antibiotics (HOSPITAL_ID,CP_ID, CP_ANTIBIOTICS, SYS_IS_DEL, SYS_LAST_UPDATE)"+
					"values (1,"+cpId+", '"+cp_antibiotics+"', 0, "+date+ ")";
		}
		selectSQL="select cp_id from "+tableName+" where cp_id="+cpId;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass ds=db.FunGetDataSetBySQL(selectSQL);
		Number id=ds.FunGetDataAsNumberById(0, 0);
		String key = db.FunGetSvrKey();
		
		int isUpdateSuc=-1;
		if(id!=null){
			 isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		}else{
			 isUpdateSuc = db.FunRunSQLCommand(key, addSQL);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		}
		
		if (isUpdateSuc > 0) {
			response.getWriter().println("[{\"result\":\"OK\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"fail\"}]");
		}
		
		
	}
	
	/*** 方法说明:添加诊断依据   诊疗方案依据
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void updataMasterBased(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		String cpId = request.getParameter("cp_id");
		//if(request.getParameter("CP_DIAGNOSIS_BASED")!=null && request.getParameter("CP_TREATMENT")!=null){
		String cp_diagnosis_based=URLDecoder.decode(request.getParameter("CP_DIAGNOSIS_BASED"),"UTF-8");
		String cp_treatment=URLDecoder.decode(request.getParameter("CP_TREATMENT"),"UTF-8");
		
		String selectSQL="select cp_id from LCP_master_based where cp_id="+cpId;
		String sql="update LCP_master_based set cp_diagnosis_based='"+cp_diagnosis_based+"',cp_treatment='"+cp_treatment+"',sys_last_update="+CommonUtil.getOracleToDate()+" where cp_id="+cpId;
		String addSQL="insert into LCP_master_based (CP_ID, CP_DIAGNOSIS_BASED, CP_TREATMENT, SYS_IS_DEL, SYS_LAST_UPDATE)"+"values ("+cpId+", '"+cp_diagnosis_based+"', '"+cp_treatment+"', 0,"+CommonUtil.getOracleToDate()+ ")";
		
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass ds=db.FunGetDataSetBySQL(selectSQL);
		Number id=ds.FunGetDataAsNumberById(0, 0);
		String key = db.FunGetSvrKey();
		int isUpdateSuc=-1;
		if(id!=null){
			 isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		}else{
			 isUpdateSuc = db.FunRunSQLCommand(key, addSQL);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		}
		if (isUpdateSuc > 0) {
			response.getWriter().println(
					"[{\"result\":\"OK\", \"id\":\"1\",\"flag\":\"1\"}]");
		} else {
			response.getWriter().println(
					"[{\"result\":\"OK\", \"id\":\"1\", \"flag\":\"0\"}]");
		}
	}

	
	/******** 方法说明: 启用  或者 停止 给定id的路径
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void startOrStopCp(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String cpId = request.getParameter("cp_id");
		String op = request.getParameter("op");
		String sql;
		int isUpdateSuc;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String key = db.FunGetSvrKey();
		if ("start_cp".equals(op)) {
			sql = "UPDATE LCP_MASTER  SET CP_STATUS=1,CP_START_DATE="+ CommonUtil.getOracleToDate() + ",sys_last_update="+CommonUtil.getOracleToDate()+" WHERE CP_ID=" + cpId;
			 isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
				if (isUpdateSuc > 0) {
					response.getWriter().println(
							"[{\"result\":\"start\", \"cp_status\":\"1\",  \"time\":\""+CommonUtil.getDBTimeString()+"\",\"flag\":\"1\"}]");
				} else {
					response.getWriter().println(
							"[{\"result\":\"start\", \"cp_status\":\"0\", \"flag\":\"0\"}]");
				}
		} else {
			sql = "UPDATE LCP_MASTER  SET CP_STATUS=0,CP_STOP_DATE="+ CommonUtil.getOracleToDate() + ",sys_last_update="+CommonUtil.getOracleToDate()+" WHERE CP_ID=" + cpId;
			 isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
				if (isUpdateSuc > 0) {
					response.getWriter().println(
							"[{\"result\":\"stop\", \"cp_status\":\"0\", \"time\":\""+CommonUtil.getDBTimeString()+"\",\"flag\":\"1\"}]");
				} else {
					response.getWriter().println(
							"[{\"result\":\"stop\", \"cp_status\":\"1\",\"flag\":\"0\"}]");
			}	}
	}

	
	/***方法说明: 查询数据  并且返回
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void selectMaster(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cpId = request.getParameter("cp_id");
		String lcp_master = "select * from lcp_master where cp_id=" + cpId;
		String lcp_master_based="select * from lcp_master_based  where cp_id="+cpId;
		String lcp_master_antibiotics="select * from lcp_master_antibiotics  where cp_id="+cpId;
		

		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dataSet = db.FunGetDataSetBySQL(lcp_master);
		DataSetClass dataSet1 = db.FunGetDataSetBySQL(lcp_master_based);
		DataSetClass dataSet2 = db.FunGetDataSetBySQL(lcp_master_antibiotics);

		String cp_code = dataSet.FunGetDataAsStringByColName(0, "CP_CODE");// 
		String cp_status = dataSet.FunGetDataAsStringByColName(0, "CP_STATUS");// 
		if(cp_status!=null||cp_status!=""){
			if(cp_status.equals("0")){
				cp_status="启用";
			}else{
				cp_status="停用";
			}
		}
		String cp_version = dataSet.FunGetDataAsStringByColName(0, "CP_VERSION");//
		String cp_days = dataSet.FunGetDataAsStringByColName(0, "CP_DAYS");//
		String cp_fee = dataSet.FunGetDataAsStringByColName(0, "CP_FEE");//
		String cp_days_min = dataSet.FunGetDataAsStringByColName(0,"CP_DAYS_MIN");//
		String cp_days_max = dataSet.FunGetDataAsStringByColName(0,"CP_DAYS_MAX");//
		String cp_name = dataSet.FunGetDataAsStringByColName(0, "CP_NAME");//
		String dept_name = dataSet.FunGetDataAsStringByColName(0, "DEPT_NAME");//
		String input_code_py = dataSet.FunGetDataAsStringByColName(0, "INPUT_CODE_PY");//
		String input_code_wb = dataSet.FunGetDataAsStringByColName(0, "INPUT_CODE_WB");//
		Date create_date = dataSet.FunGetDataAsDateByColName(0, "CP_CREATE_DATE");//
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String cp_create_date=sdf.format(create_date);
		
		String cp_diagnosis_based =CommonUtil.replactCharacter( dataSet1.FunGetDataAsStringByColName(0,"CP_DIAGNOSIS_BASED")).replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\r", "<br/>");;// 诊断依据
		String cp_treatment = CommonUtil.replactCharacter( dataSet1.FunGetDataAsStringByColName(0,"CP_TREATMENT")).replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\r", "<br/>");;// 治疗方案依据
		String cp_antibiotics = CommonUtil.replactCharacter( dataSet2.FunGetDataAsStringByColName(0,"CP_ANTIBIOTICS")).replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\r", "<br/>");;// 预防性抗菌药物选择与使用时机内容
		
		String vs_cp="SELECT * FROM LCP_HOSPITAL_VS_CP T WHERE T.CP_ID="+ cpId;
		DataSet vs_cpDataSet=new DataSet();
		vs_cpDataSet.funSetDataSetBySql(vs_cp);
		String vs_cp_state=vs_cpDataSet.funGetFieldByCol(0, "CP_STATUS");
		if(vs_cp_state.equals("0")){
			vs_cp_state="可用";
		}else{
			vs_cp_state="可用";
		}
		
		String result="[{\"result\":\"OK\"," +
				" \"id\":\"1\", " +
				" \"cp_id\":\"" +cpId + "\", " +
				" \"cp_code\":\"" +cp_code + "\", " +
				" \"cp_name\":\"" +cp_name + "\", " +
				" \"cp_status\":\"" +cp_status + "\", " +
				" \"cp_version\":\"" +cp_version + "\", " +
				" \"cp_days\":\"" +cp_days + " \", " +
				" \"cp_version\":\"" +cp_version + "\", " +
				" \"cp_fee\":\"" +cp_fee + "\", " +
				" \"cp_days_min\":\"" +cp_days_min + " \", " +
				" \"cp_days_max\":\"" +cp_days_max + " \", " +
				" \"dept_name\":\"" +dept_name + "\", " +
				" \"py\":\"" +input_code_py + "\", " +
				" \"wb\":\"" +cp_create_date + "\", " +
				" \"cp_treatment\":\"" +cp_treatment + "\", " +
				" \"cp_diagnosis_based\":\"" +cp_diagnosis_based + "\", " +
				" \"cp_antibiotics\":\"" +cp_antibiotics + "\", " +
				" \"vs_cp_state\":\"" +vs_cp_state + "\", " +
				"\"flag\":\"1\"}]";
		if(dataSet.FunGetRowCount()>0){
			response.getWriter().println(result);
		}else{
			response.getWriter().println("[{\"result\":\"OK\", \"id\":\"1\", \"flag\":\"0\"}]");
		}
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String autoid=request.getParameter("autoid");
		String operation_code=URLDecoder.decode(request.getParameter("operation_code"),"UTF-8");
		String operation_name=URLDecoder.decode(request.getParameter("operation_name"),"UTF-8");
		String operation_scale=URLDecoder.decode(request.getParameter("operation_scale"),"UTF-8");
		String input_code=URLDecoder.decode(request.getParameter("input_code"),"UTF-8");
		String description=URLDecoder.decode(request.getParameter("description"),"UTF-8");
		
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		
		String sql = "UPDATE LCP_DICT_operation set " + 
				"operation_code='"+ operation_code + "'," + 
				"operation_name='"+ operation_name + "'," + 
				"operation_scale='" + operation_scale + "',"+
				"input_code='" + input_code + "'," +
				"description='" + description + "'," +
				"SYS_LAST_UPDATE="+CommonUtil.getOracleToDate()+
				" WHERE autoid="+ autoid ;
		String key=databaseClass.FunGetSvrKey();
		
		int isUpdateSuc=databaseClass.FunRunSQLCommand(key, sql);//isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常		
		if(isUpdateSuc>0){
			response.getWriter().println("[{\"result\":\"OK\", \"id\":\"1\", \"flag\":\"1\"}]");
		}else{
			response.getWriter().println("[{\"result\":\"OK\", \"id\":\"1\", \"flag\":\"0\"}]");
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void del(HttpServletRequest request, HttpServletResponse response)throws IOException{
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cpId=request.getParameter("cp_id");
		String colName=request.getParameter("colName");
		String range=request.getParameter("range");
		String tableName=request.getParameter("tableName");
		
		String sql;
		if(range!=null&&range!=""){
			range=range.substring(2);
			String id[]=range.split("tr");
			String ids="";
			for(int i=0;i<id.length;i++){
				ids=ids+id[i]+",";
			}
		ids=ids.substring(0, ids.length()-1);
			if ("lcp_node_relate".equals(tableName)
					|| "lcp_node_doctor".equals(tableName)
					|| "lcp_node_order".equals(tableName)
					|| "lcp_node_nurse".equals(tableName)
					|| "lcp_node_variation".equals(tableName)){
				String cp_node_id=request.getParameter("cp_node_id").replaceAll("tr", "").trim();
				sql="delete "+tableName+" where cp_id="+cpId+" and cp_node_id="+cp_node_id+" and "+colName +" in ("+ids+")";
			}else{
				sql="delete "+tableName+"  where cp_id="+cpId+" and "+colName +" in ("+ids+")";
			}
				
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		String key=databaseClass.FunGetSvrKey();
		int isUpdateSuc=databaseClass.FunRunSQLCommand(key, sql);//isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		if(isUpdateSuc>0){
			response.getWriter().println("[{\"result\":\"OK\", \"flag\":\"1\"}]");
		}else{
			response.getWriter().println("[{\"result\":\"OK\", \"flag\":\"0\"}]");
		}
		}
	}
	
}
