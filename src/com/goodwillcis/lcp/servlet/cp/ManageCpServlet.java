package com.goodwillcis.lcp.servlet.cp;
/*---------------------------------------------------------------- 
 * // Copyright (C) 2012 北京嘉和美康信息技术有限公司版权所有。
 * // 文件名：ManageCpServlet.java
 * // 文件功能描述： 路径维护
 * // 创建人：张昆
 * // 修改人：周伟彬
 * // 创建日期：2012/07/20
 * // 修改日期：2013/08/06
 * ----------------------------------------------------------------*/
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.model.cpmanage.DataSet;
import com.goodwillcis.lcp.model.cpmanage.EditCP;
import com.goodwillcis.lcp.model.cpmanage.NodeDoctor;
import com.goodwillcis.lcp.model.cpmanage.NodeFamily;
import com.goodwillcis.lcp.model.cpmanage.NodeNurse;
import com.goodwillcis.lcp.model.cpmanage.NodeOrder;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

public class ManageCpServlet extends HttpServlet {

	private static final long	serialVersionUID	= 1L;
	private static final int	HOSPITALID			= LcpUtil.getHospitalID();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String op = request.getParameter("op");
		//////System.out.println(op);		
		if ("select".equals(op)) selectCPMasterInfo(request, response);
		
		else if("editzl".equals(op)) editzl(request, response);//诊疗工作编辑
		
		else if("edithl".equals(op)) edithl(request, response);//护理工作编辑
		
		else if("addcx".equals(op)) addcx(request, response);//如果是检验检查医嘱，就把频次改为once，一次计量改为1
		
		else if("editTwocx".equals(op)) editTwocx(request, response);//查询节点
		
        else if("editTwogx".equals(op)) editTwogx(request,response);//更新节点
		
		else if("editNodegx".equals(op)) editNodegx(request, response);//更新节点
		
        else if("editNodecx".equals(op)) editNodecx(request,response);//查询节点
		
		else if("editOrderItem".equals(op)) editOrderItem(request, response);//更新具体医嘱
		
		else if("edit".equals(op)) edit(request,response);//查询具体医嘱
		
		else if ("start_cp".equals(op) || "stop_cp".equals(op)) start_stop_CP(request, response);
		
		else if ("addCP".equals(op)) addCP(request, response);// 添加自定义路径
		
		else if ("editCP".equals(op)) editCP(request, response);// 编辑自定义路径

		else if ("delCP".equals(op)) delCP(request, response);// 删除自定义路径
		
		else if ("copyCP".equals(op)) copyCP(request, response);//复制路径

		else if ("lcp_master_based".equals(op)) updata_master_based(request, response);

		else if ("updata_antibiotics_based".equals(op)) updata_antibiotics_based(request, response);

		else if ("add_lcp_master_income".equals(op)) add_lcp_master_income(request, response);

		else if ("delTrs".equals(op)) del(request, response);

		else if ("delNode".equals(op)) delNode(request, response);

		else if ("addNode".equals(op)) addNode(request, response);

		else if ("addTwoCol".equals(op)) addTwoCol(request, response);

		else if ("addNurseOrDoctor".equals(op)) addNurseOrDoctor(request, response);

		else if ("addNodeOrder".equals(op)) addNodeOrder(request, response);

		else if ("show_doctor_item".equals(op)) show_doctor_item(request, response);

		else if ("show_doctor_point".equals(op)) show_doctor_point(request, response);

		else if ("show_nurse_item".equals(op)) show_nurse_item(request, response);

		else if ("show_nurse_point".equals(op)) show_nurse_point(request, response);

		else if ("del_lcp_node_doctor_point".equals(op)) del_lcp_node_doctor_point(request,response);

		else if ("del_lcp_node_doctor_item".equals(op)) del_lcp_node_doctor_item(request, response);

		else if ("del_lcp_node_nurse_point".equals(op)) del_lcp_node_nurse_point(request, response);

		else if ("del_lcp_node_nurse_item".equals(op)) del_lcp_node_nurse_item(request, response);

		else if ("del_lcp_node_order_item".equals(op)) del_lcp_node_order_item(request, response);

		else if ("show_order_item".equals(op)) show_order_item(request, response);

		else if ("show_order_point".equals(op)) show_order_point(request, response);

		else if ("show_order_belong".equals(op)) show_order_belong(request, response);

		else if ("add_family_point".equals(op)) add_family_point(request, response);

		else if ("del_family_point".equals(op)) del_family_point(request, response);

		else if ("del_order_point".equals(op)) delOrderPoint(request, response);

		else if ("addOrderItem".equals(op)) addOrderItem(request, response);// 添加第二级医嘱

		else if ("addOrderPoint".equals(op)) addOrderPoint(request, response);// 添加第一级医嘱

		else if ("Option".equals(op)) makeOption(request, response);// 为下拉菜单插入项目
		
		else if("addOrderItemGroup".equals(op)) addOrderItemGroup(request,response);//分组
		
		else if("cancelOrderItemGroup".equals(op)) cancelOrderItemGroup(request,response);//撤销分组
		
		else if("selectNode".equals(op)) selectNode(request,response);//选中节点
		
		else if("copyNode".equals(op)) copyNode(request,response);//复制节点
		
		else if("editOrNot".equals(op)) editOrNot(request,response);//是否可编辑
		
		else if("upDownMove".equals(op)) upDownMove(request,response);//上下移动
		
		else if("copySan".equals(op)) copySan(request,response);//在三级节点内复制
		
		else if("selectSan".equals(op)) selectSan(request,response);//查询二级菜单
		
		else if("copyToSecond".equals(op)) copyToSecond(request,response);//复制到选中的二级菜单
		
		else if("cpSubmit".equals(op)) cpSubmit(request,response);//提交路径进行审核
		
		else if("verify".equals(op)) verify(request,response);//管理员对路径审核
		
		else if("returnReason".equals(op)) returnReason(request,response);//退回原因
		
		else if("cpEnable".equals(op)) cpEnable(request,response);//路径启用成功
		
		else if("showReason".equals(op)) showReason(request,response);//申请人查看原因
		
		else if("clearOrderPoint".equals(op)) clearOrderPoint(request,response);//申请人查看原因
		
		else if("selectCPStatus".equals(op)) selectCPStatus(request,response);//查看路径是否启用过
		
		else if("showHistoryReason".equals(op)) showHistoryReason(request,response);//查看历史退回原因
		
		else if("hiddenCP".equals(op)) hiddenCP(request,response);//隐藏路径
		
		else if("recoverCP".equals(op)) recoverCP(request,response);//恢复路径
		
		else if("startAfter".equals(op)) startAfter(request,response);//启用新版路径停用旧版路径
		else if("cpZhaoHui".equals(op)) cpZhaoHui(request,response);//召回审核中的路径
		
	}
	


	/**
	 * 路径护理工作编辑
	 * @param request
	 * @param response
	 */
	private void edithl(HttpServletRequest request, HttpServletResponse response) {
		try {
			 request.setCharacterEncoding("utf-8");
			 response.setContentType("text/html;charset=utf-8");
			 String cpID = request.getParameter("cpID");
			 String cp_node_id_quanju = request.getParameter("cp_node_id_quanju");
			 String first = request.getParameter("firsthl");
			 String need = request.getParameter("need");
			 //String name = request.getParameter("name");
			 String name = URLDecoder.decode(
						request.getParameter("name"), "UTF-8");
			 if("".equals(name)){
				 name="null";
			 }
			 if("".equals(need)){
				 need="null";
			 }
			 //////System.out.println(cpID+"--"+cp_node_id_quanju+"--"+first+"--"+need+"--"+name);
			String updateSQL = "update lcp_node_nurse_point set need_item='"
			+ need + "'" + ",";// 可选 必选项
			updateSQL += "cp_node_nurse_text='" + name + "'" ;// 诊疗工作内容
			updateSQL += " where CP_ID=" + cpID + " and CP_NODE_ID=" + cp_node_id_quanju +" and CP_NODE_NURSE_ID=" + first +" and HOSPITAL_ID='1'" ;
			DataSet dataSet = new DataSet();
			//////System.out.println(updateSQL);
			int isUpdateSuc = dataSet.funRunSql(updateSQL);
			////System.out.println("数据库执行结果=" + isUpdateSuc + "     SQL="+ updateSQL);

			if (isUpdateSuc > 0) {
				   response.getWriter().println(
						"{\"result\":\"OK\",\"need\":\"" + need + "\",\"name\":\"" + name + "\"}");
			
			} else {
				response.getWriter().println("{\"result\":\"ERROR\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.getWriter().println("{\"result\":\"fail\"}");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	/**
	 * 路径诊疗工作编辑
	 * @param request
	 * @param response
	 */
	private void editzl(HttpServletRequest request, HttpServletResponse response) {
			try {
				 request.setCharacterEncoding("utf-8");
				 response.setContentType("text/html;charset=utf-8");
				 String cpID = request.getParameter("cpID");
				 String cp_node_id_quanju = request.getParameter("cp_node_id_quanju");
				 String first = request.getParameter("first");
				 String need = request.getParameter("need");
				 //String name = request.getParameter("name");
				 String name = URLDecoder.decode(
							request.getParameter("name"), "UTF-8");
				 if("".equals(name)){
					 name="null";
				 }
				 if("".equals(need)){
					 need="null";
				 }
				 ////System.out.println(cpID+"--"+cp_node_id_quanju+"--"+first+"--"+need+"--"+name);
				String updateSQL = "update lcp_node_doctor_point set need_item='"
				+ need + "'" + ",";// 可选 必选项
				updateSQL += "cp_node_doctor_text='" + name + "'" ;// 诊疗工作内容
				updateSQL += " where CP_ID=" + cpID + " and CP_NODE_ID=" + cp_node_id_quanju +" and CP_NODE_DOCTOR_ID=" + first +" and HOSPITAL_ID='1'" ;
				DataSet dataSet = new DataSet();
				////System.out.println(updateSQL);
				int isUpdateSuc = dataSet.funRunSql(updateSQL);
				////System.out.println("数据库执行结果=" + isUpdateSuc + "     SQL="+ updateSQL);

				if (isUpdateSuc > 0) {
					   response.getWriter().println(
							"{\"result\":\"OK\",\"need\":\"" + need + "\",\"name\":\"" + name + "\"}");
				
				} else {
					response.getWriter().println("{\"result\":\"ERROR\"}");
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					response.getWriter().println("{\"result\":\"fail\"}");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		
	}

	/**
	 * 添加医嘱
	 * @param request
	 * @param response
	 */
	private void addcx(HttpServletRequest request, HttpServletResponse response) {
		String s= request.getParameter("s");
		String sqlcx = "select ORDER_TYPE_CODE from dcp_dict_order_item where trim(INPUT_CODE_PY)='"+s+"'";
		////System.out.println(sqlcx);
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String sfcheck = db.FunGetDataSetBySQL(sqlcx).FunGetDataAsStringById(0, 0);
      
			try {
				response.getWriter().println(
						"{\"result\":\"ok\",\"sfcheck\":\"" + sfcheck+ "\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}

	/**
	 * 医嘱分组
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addOrderItemGroup(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String ids = request.getParameter("addIds");
		ids = ids.substring(0, ids.length() - 1);
		NodeOrder order = new NodeOrder();
		boolean isSuc = order.funUpItemGroup(ids);
		String[] _ids = ids.split(",");
//		for(int i=0;i<_ids.length;i++){
//			////System.out.println(_ids[i]);
//			
//		}
//		////System.out.println(order.funUpItemGroups(ids));
//		String upSQL="UPDATE lcp_node_order_item t SET t.order_item_set_id= "+
//		"where cp_id = 10014"+
//		"and cp_node_id = 2"+
//		"and cp_node_order_id in( )";
		String[] _id = _ids[0].split("_");
		if (isSuc) {
			String table = order.funGetItemTable(_id[0], _id[1], _id[2]);
			////System.out.println("===" + table);
			response.getWriter().println("[{\"result\":\"OK\", \"table\":\"" + table + "\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"ERROR\"}]");
		}

	}
	
	/**
	 * 撤销分组 2012-1-11
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void cancelOrderItemGroup(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		String ids = request.getParameter("ids");
		String ss = request.getParameter("str");
		
		String str[] = ids.split("_");
		String cpID=str[0];
		String cpNodeId=str[1];
		String cpNodeOrderID=str[2];
		//String cpNodeOrderItemID=str[3];
		NodeOrder order = new NodeOrder();
		String updateSql="";
		String cpNodeOrderItemID="";
		String[] rr = ss.split(",");//选中的个数
		String updateSql2="";
		int res=0;
		for(String a:rr){
			cpNodeOrderItemID = a.substring(a.lastIndexOf("_")+1, a.length());
			String sw = "select order_item_set_id from lcp_node_order_item where cp_id="+cpID+" and cp_node_id="+cpNodeId+" and cp_node_order_id="+cpNodeOrderID+" and cp_node_order_item_id="+cpNodeOrderItemID;
			String orderItemSetID = db.FunGetDataSetBySQL(sw).FunGetDataAsStringById(0, 0);
				if(cpNodeOrderItemID.equals(orderItemSetID)){
					
					updateSql2="update lcp_node_order_item set order_item_set_id ='"+""+"' where cp_id="+cpID+" and cp_node_id="+cpNodeId+" and cp_node_order_id="+cpNodeOrderID+" and cp_node_order_item_id="+cpNodeOrderItemID+" and cp_node_order_item_id=order_item_set_id";
					res = db.FunRunSqlByFile(updateSql2.getBytes());
					String sw2 = "select min(cp_node_order_item_id) as cp_node_order_item_id from lcp_node_order_item where cp_id="+cpID+" and cp_node_id="+cpNodeId+" and cp_node_order_id="+cpNodeOrderID+" and order_item_set_id="+orderItemSetID;
					String minSetID = db.FunGetDataSetBySQL(sw2).FunGetDataAsStringById(0, 0);
					if(!"".equals(minSetID)){
						updateSql=" update lcp_node_order_item set order_item_set_id="+minSetID+" where cp_id="+cpID+" and cp_node_id="+cpNodeId+" and cp_node_order_id="+cpNodeOrderID+" and order_item_set_id="+orderItemSetID;
					}else{
						updateSql="update lcp_node_order_item set order_item_set_id='"+""+"' where cp_id="+cpID+" and cp_node_id="+cpNodeId+" and cp_node_order_id="+cpNodeOrderID+" and cp_node_order_item_id="+cpNodeOrderItemID;
					}
				}else{
					updateSql="update lcp_node_order_item set order_item_set_id='"+""+"' where cp_id="+cpID+" and cp_node_id="+cpNodeId+" and cp_node_order_id="+cpNodeOrderID+" and cp_node_order_item_id="+cpNodeOrderItemID;
				}
				res = db.FunRunSqlByFile(updateSql.getBytes());
		}
		
		if (res>0) {
			String table = order.funGetItemTable(cpID, cpNodeId, cpNodeOrderID);
			response.getWriter().println("[{\"result\":\"OK\", \"table\":\"" + table + "\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"ERROR\"}]");
		}

	}

	/**
	 * 删除一级菜单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void delOrderPoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cpID 			= request.getParameter("cp_id");
		String cpNodeID 		= request.getParameter("cp_node_id");
		String cpNodeOrderID 	= request.getParameter("cp_node_table_id");
		String cpOrder2Point 	= request.getParameter("cpOrder2Point");
		String type 	= request.getParameter("type");//第一层 第二层
		NodeOrder order			= new NodeOrder();
		boolean isSuc 			= order.funDelPoint(cpID, cpNodeID, cpNodeOrderID,type);

		if (isSuc) {
			////System.out.println("sdsfsdfasdf"+cpNodeOrderID);
			String table = order.funGetPointTable(cpID, cpNodeID,cpOrder2Point);
			////System.out.println("===" + table);
			response.getWriter().println("[{\"result\":\"OK\", \"table\":\"" + table + "\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"ERROR\"}]");
		}

	}

	/**
	 * 添加一级菜单或二级菜单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addOrderPoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String cpID = request.getParameter("cp_id");
		String cpNodeID = request.getParameter("cp_node_id");
		String need = request.getParameter("need");
		String types = request.getParameter("types");// 长期-临时-出院
		String type = request.getParameter("type");// 第一层/ 第二层
		String orderPointName = URLDecoder.decode(request.getParameter("orderPointName"), "UTF-8");
		String orderPointCode = request.getParameter("orderPointCode");
		
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String cpOrderID = "(select max(CP_NODE_ORDER_ID)+1 id from (select CP_NODE_ORDER_ID from lcp_node_order_point t"
				+ " where t.cp_id="
				+ cpID
				+ " and t.cp_node_id="
				+ cpNodeID
				+ "   union select 0 CP_NODE_ORDER_ID from dual))";
		String cid=cpOrderID;
		
		if("2".equals(type)){
			cid=request.getParameter("cp_order_id");
		}
		String uniqueSql="select cp_node_class_id from lcp_node_order_point where cp_id="+cpID+" and cp_node_id="+cpNodeID+" and cp_node_class_id="+orderPointCode;
		String cpNodeCI = db.FunGetDataSetBySQL(uniqueSql).FunGetDataAsStringById(0, 0);
		if(cpNodeCI.equals("")){
			String inserSQL = "insert into lcp_node_order_point" + "(CP_ID," + " HOSPITAL_ID,"
			+ " CP_NODE_ID," + " CP_NODE_ORDER_ID," + " CP_NODE_ORDER_TEXT,CP_NODE_CLASS_ID," + " NEED_ITEM,"
			+ " SYS_IS_DEL," + " SYS_LAST_UPDATE," + " ORDER_KIND,CONTINUE_ORDER_ID)" + "values(" + cpID + ","
			+ HOSPITALID + "," + cpNodeID + "," + cpOrderID + ",'" + orderPointName + "',"+orderPointCode+"," + need + ",0,"
			+ CommonUtil.getOracleToDate() + "," + types + ","+cid+")";
			DataSet dataSet = new DataSet();
			int isUpdateSuc = dataSet.funRunSql(inserSQL);
			////System.out.println("数据库执行结果=" + isUpdateSuc + "     SQL=" + inserSQL);
			if (isUpdateSuc > 0) {
				NodeOrder order = new NodeOrder();
				String table = order.funGetPointTable(cpID, cpNodeID,cid);
				response.getWriter().println("[{\"result\":\"OK\",\"table\":\"" + table + "\"}]");
			} else {
				response.getWriter().println("[{\"result\":\"ERROR\"}]");
			}
		}else{
			response.getWriter().println("[{\"result\":\"unique\"}]");
		}
	}

	/**
	 * 添加具体医嘱
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addOrderItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {
			String cpID = request.getParameter("cp_id");
			String cpNodeID = request.getParameter("cp_node_id");
			String cpOrderID = request.getParameter("cp_node_order_id");
			DatabaseClass db = LcpUtil.getDatabaseClass();
			
			String cpOrderItemID = "(select max(CP_NODE_ORDER_ITEM_ID)+1 id from (select CP_NODE_ORDER_ITEM_ID from lcp_node_order_item t"
					+ " 	where t.cp_id="
					+ cpID
					+ " and t.cp_node_id="
					+ cpNodeID
					+ "  and t.cp_node_order_id="
					+ cpOrderID
					+ "  union select 0 CP_NODE_ORDER_ITEM_ID from dual))";
			String need = request.getParameter("need");
			String isDefault = request.getParameter("isDefault");
			String code = request.getParameter("code");
			String drugIdSql = "select drug_id from dcp_dict_order_item where order_item_code='"+code+"'";
			String drug_id = db.FunGetDataSetBySQL(drugIdSql).FunGetDataAsStringByColName(0, "DRUG_ID");
			String type = request.getParameter("type");// 长期-临时-出院

			String name = URLDecoder.decode(request.getParameter("name"), "UTF-8");// 医嘱名称
			String specification = URLDecoder.decode(request.getParameter("specification"), "UTF-8");//规格
			// String name1=URLEncoder.encode(request.getParameter("name"),
			// "UTF-8") ;
			// ////System.out.println(request.getParameter("name")+"       "+name
			// +"          "+name1 +"   "+URLDecoder.decode(name1, "UTF-8"));

			String orderjl = request.getParameter("orderjl");// 医嘱计量
			String orderCode = request.getParameter("orderCode2");// 计量单位
			String dosage = request.getParameter("dosage");// 一次使用剂量
			String orderCode1 = request.getParameter("orderCode1");// 一次使用剂量单位
			
			String orderPC = request.getParameter("orderPC").trim();// 频次

			String ordertj = request.getParameter("ordertj");// 途径
			String mark = URLDecoder.decode(request.getParameter("mark"), "UTF-8");//医生嘱托
			
			//String yuanNeiOrderType = request.getParameter("yanneiOrderType");// 院内医嘱类别

			String selectSQL = "(select order_type_name from dcp_dict_order_item  where order_item_code='"
					+ code + "' and rownum <2)";

			String insertSQL = "insert into lcp_node_order_item(CP_ID, HOSPITAL_ID,";
			insertSQL += " CP_NODE_ID, CP_NODE_ORDER_ID, CP_NODE_ORDER_ITEM_ID,";
			insertSQL += " CP_NODE_ORDER_TEXT, NEED_ITEM,ORDER_NO, ORDER_TYPE,";
			insertSQL += " SYS_IS_DEL, SYS_LAST_UPDATE, ORDER_KIND," + // "医嘱类型（0临时医嘱、1长期医嘱、2出院医嘱）"
					" ORDER_TYPE_NAME," + // 医嘱类别描述
					" ORDER_CLASS," + // 医嘱类别：指定药疗、处置、护理、膳食、其它等类别，使用代
					" REPEAT_INDICATOR," + // 长期医嘱标志：本医嘱是否长期医嘱，使用代码，1-长期，0-临时
					" MEASURE," + // 领量
					" MEASURE_UNITS," + // 领量单位
					" DOSAGE," + //一次使用剂量
					" DOSAGE_UNITS, " + //一次使用剂量单位
					" SPECIFICATION, " + //规格specification
					" ADMINISTRATION," + // 给药途径和方法：规范描述，是判断生成何种治疗单的依据
					" DURATION," + // 持续时间：一次执行的持续时间
					" DURATION_UNITS," + // 持续时间单位：使用规范描述
					" FREQUENCY," + // 执行频率描述qid
					" ORDER_ITEM_SET_ID," + // 医嘱分组
					" CP_NODE_CLASS_ID," + " WAY," + // 途径
					" IS_ANTIBIOTIC,MARK,DEFAULT_ITEM,DRUG_ID)" + // 是否是抗生素(包含级别)
					" values(" + cpID + "," + HOSPITALID + "," + cpNodeID + "," + cpOrderID
					+ ","
					+ cpOrderItemID
					+ ",'"
					+ name
					+ "',"
					+ need
					+ ",'"
					+ code
					+ "',(select order_type_code from dcp_dict_order_item  where order_item_code='"
					+ code // 医嘱类别编码
					+ "' and rownum <2),0,"
					+ CommonUtil.getOracleToDate()
					+ ",'"
					+ type
					+ "',"
					+ selectSQL// 医嘱类别描述
					+ ",null,null,"
					+ getStrInSql(orderjl, false)// 药品领量
					+ ",'" + orderCode //药品领量单位
					+ "',"+getStrInSql(dosage,false)//使用剂量
					+",'"+orderCode1 //使用剂量单位
					+"','"+specification //规格
					+"',(select trim(supply_name) from lcp_local_order_way where supply_code='"
					+ ordertj + "')," + null + "," + null + ",'" + orderPC + "', null,null,'" + ordertj + "',"
					+ "(select is_antibiotic from dcp_dict_order_item  where order_item_code='"
					+ code + "' and rownum <2),'"+mark+"',"+isDefault+",'"+drug_id+"')";
			
			DataSet dataSet = new DataSet();
			int isUpdateSuc = dataSet.funRunSql(insertSQL);
			////System.out.println("数据库执行结果=" + isUpdateSuc + "     SQL=" + insertSQL);
            //插入一行就追加到页面table中，不每次返回table
			//需要返回页面的字段有checkbox  序号 组 类别(长临) 医嘱内容 规格 必做 默认 计量 频次 途径  effectFlag
			if("0".equals(type)){
				type="临时";
			}else if("1".equals(type)){
				type="长期";
			}else if("2".equals(type)){
				type="出院";
			}else if("3".equals(type)){
				type="长期+临时";
			}
			if("1".equals(need)){
				need="√";
			}else{
				need="";
			}
			if("1".equals(isDefault)){
//				System.out.println("isDefault1==" + isDefault);
				isDefault="√";
			}else{
				isDefault="";
			}
			
			String SUPPLYNAME="";//途径编码
			if(ordertj != ""){
				SUPPLYNAME=db.FunGetDataSetBySQL("select supply_name from lcp_local_order_way where supply_code='"+ordertj+"'").FunGetDataAsStringById(0, 0);
			}
			String codeUnit=db.FunGetDataSetBySQL("select t.unit from lcp_local_order_dosageunits t where t.code='"+orderCode+"'").FunGetDataAsStringById(0, 0);
			
			if(!"".equals(orderjl)){
				orderjl = orderjl+codeUnit;
			}
			cpOrderItemID =  db.FunGetDataSetBySQL(cpOrderItemID).FunGetDataAsStringById(0, 0);
			int cpOrderItemIDint = Integer.parseInt(cpOrderItemID)-1;
			cpOrderItemID =cpOrderItemIDint+""; 
			String effectFlagSql = "select effect_flag from lcp_node_order_item where hospital_id="+HOSPITALID+" and cp_id="+cpID+" and cp_node_id="+cpNodeID+" and cp_node_order_id="+cpOrderID+" and cp_node_order_item_id="+cpOrderItemID;
			String effectFlag = db.FunGetDataSetBySQL(effectFlagSql).FunGetDataAsStringById(0, 0);
			//--------->查询医嘱类型 
			String sqlorderTypeName ="select ORDER_TYPE_NAME from lcp_node_order_item where hospital_id="+HOSPITALID+" and cp_id="+cpID+" and cp_node_id="+cpNodeID+" and cp_node_order_id="+cpOrderID+" and cp_node_order_item_id="+cpOrderItemID;
			String ORDER_TYPE_NAME = db.FunGetDataSetBySQL(sqlorderTypeName).FunGetDataAsStringById(0, 0);
			////System.out.println(ORDER_TYPE_NAME+"////");
			if (isUpdateSuc > 0) {
			/*	NodeOrder order = new NodeOrder();
				String table = order.funGetItemTable(cpID, cpNodeID, cpOrderID);*/
				response.getWriter().println("{\"result\":\"OK\",\"cpID\":\"" + cpID + "\",\"cpNodeID\":\"" + cpNodeID + "\",\"cpOrderID\":\"" + cpOrderID + "\",\"cpOrderItemID\":\"" + cpOrderItemID + "\",\"groupId\":\"无\",\"type\":\"" + type + "\",\"ORDER_TYPE_NAME\":\"" + ORDER_TYPE_NAME + "\",\"name\":\"" + name + "\",\"specification\":\"" + specification + "\",\"need\":\"" + need + "\",\"isDefault\":\"" + isDefault + "\",\"orderjl\":\"" + orderjl + "\",\"orderPC\":\"" + orderPC + "\",\"SUPPLYNAME\":\"" + SUPPLYNAME + "\",\"effectFlag\":\"" + effectFlag + "\"}");
			} else {
				response.getWriter().println("{\"result\":\"ERROR\"}");
			}
		} catch (Exception e) {
			////System.out.println("DB ERROR!");
			response.getWriter().println("{\"result\":\"fail\"}");
		}
	}
	
	/**
	 * 编辑二级菜单代码
	 * @param request
	 * @param response
	 */
	private void editTwocx(HttpServletRequest request,
			HttpServletResponse response) {
		String cp_id= request.getParameter("cpid");
		String cp_node_id_quanju= request.getParameter("cp_node_id_quanju");
	    String cpOrderID2 = request.getParameter("cpOrderID2");
		////System.out.println(cp_id);
		////System.out.println(cp_node_id_quanju);
		////System.out.println(cpOrderID2);
		String sqlcx="select * from lcp_node_order_point where CP_ID='"+cp_id+"'and CP_NODE_ID='"+cp_node_id_quanju+"' and CP_NODE_ORDER_ID='"+cpOrderID2+"' ";
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sqlcx);
		int row = dataSet.getRowNum();
		for (int i = 0; i < row; i++) {
			String cp_node_order_text=dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_TEXT");
			String ORDER_KIND =dataSet.funGetFieldByCol(i,"ORDER_KIND");
			////System.out.println(cp_node_order_text);
			////System.out.println(ORDER_KIND);
			////System.out.println(sqlcx);
			try {
				response.getWriter().println(
						"{\"result\":\"ok\",\"CP_NODE_ORDER_TEXT\":\"" + cp_node_order_text+ "\",\"ORDER_KIND\":\"" + ORDER_KIND + "\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
	}

	/**
	 * 编辑二级菜单
	 * @param request
	 * @param response
	 */
	private void editTwogx(HttpServletRequest request,
			HttpServletResponse response) {
	
	    
		try {
			String cp_id= request.getParameter("cp_id");
            String need_item=request.getParameter("s");
            if(need_item==null){
            	need_item=" ";
            }
			String cp_node_id_quanju= request.getParameter("cp_node_id_quanju");
		    String cpOrderID2 = request.getParameter("cpOrderID2");
		    String orderPointName1e = URLDecoder.decode(
					request.getParameter("orderPointName1e"), "UTF-8");
		    String orderPointTypeSelecte = request.getParameter("orderPointTypeSelecte");
		
			String updateSQL = "update lcp_node_order_point set CP_NODE_ORDER_TEXT='"
					+ orderPointName1e + "'" + ",";// 二级医嘱内容，自定义菜单
			updateSQL += "ORDER_KIND='" + orderPointTypeSelecte + "'";// 最多天数
			updateSQL += " where CP_ID=" + cp_id + " and CP_NODE_ID=" + cp_node_id_quanju +" and CP_NODE_ORDER_ID="+cpOrderID2+"" ;
			DataSet dataSet = new DataSet();
			int isUpdateSuc = dataSet.funRunSql(updateSQL);

			if (isUpdateSuc > 0) {
				response.getWriter().println("{\"result\":\"OK\",\"orderPointTypeSelecte\":\""+orderPointTypeSelecte+"\",\"orderPointName1e\":\""+orderPointName1e+"\",\"need_item\":\""+need_item+"\"}");
			} else {
				response.getWriter().println("{\"result\":\"ERROR\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		
	}
	
	/**
	 * 编辑节点代码
	 * @param request
	 * @param response
	 */
	private void editNodecx(HttpServletRequest request,
			HttpServletResponse response) {
			String cp_id= request.getParameter("cpid");
			String mark = request.getParameter("mark");
			////System.out.println(cp_id);
			////System.out.println(mark);
			String sqlcx="select * from lcp_master_node where CP_ID="+cp_id+" and CP_NODE_ID="+mark+"";
			DataSet dataSet = new DataSet();
			dataSet.funSetDataSetBySql(sqlcx);
			int row = dataSet.getRowNum();
			for (int i = 0; i < row; i++) {
				String CP_NODE_NAME=dataSet.funGetFieldByCol(i,"CP_NODE_NAME");
				String CP_NODE_DAYS_MAX =dataSet.funGetFieldByCol(i,"CP_NODE_DAYS_MAX");
				String CP_NODE_TYPE = dataSet.funGetFieldByCol(i,"CP_NODE_TYPE");
				////System.out.println(CP_NODE_NAME);
				////System.out.println(CP_NODE_DAYS_MAX);
				////System.out.println(CP_NODE_TYPE);
				////System.out.println(sqlcx);
				try {
					response.getWriter().println(
							"{\"result\":\"ok\",\"CP_NODE_NAME\":\"" + CP_NODE_NAME
									+ "\",\"CP_NODE_DAYS_MAX\":\""
									+ CP_NODE_DAYS_MAX
									+ "\",\"CP_NODE_TYPE\":\"" + CP_NODE_TYPE + "\"}");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		
	}

	/**
	 * 编辑节点信息
	 * @param request
	 * @param response
	 */
	private void editNodegx(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			String mark  = request.getParameter("mark");
			//String nodeid=String.valueOf(Integer.parseInt(mark)+1);
			String cp_id  = request.getParameter("cp_id");
			//String nodeName  = request.getParameter("nodeName");
			String nodeName = URLDecoder.decode(request.getParameter("nodeName"), "UTF-8");
			if (nodeName == null) {
				nodeName = "null";
			}
			String nodeDate  = request.getParameter("nodeDate");
			if (nodeDate == null) {
				nodeDate = "null";
			}
			String nodeType  = request.getParameter("nodeType");
			if (nodeType == null) {
				nodeType = "null";
			}
		
			String updateSQL = "update lcp_master_node set CP_NODE_NAME='"
					+ nodeName + "'" + ",";// 节点名称
			updateSQL += "CP_NODE_DAYS_MAX='" + nodeDate + "'" + ",";// 最多天数
			updateSQL += "CP_NODE_TYPE='" + nodeType + "'" + ",";// 类型
			updateSQL += "sys_last_update="+CommonUtil.getOracleToDate()+"";// 类型
			updateSQL += "where CP_ID=" + cp_id + " and CP_NODE_ID=" + mark +"" ;
			DataSet dataSet = new DataSet();
			////System.out.println(updateSQL);
			int isUpdateSuc = dataSet.funRunSql(updateSQL);
			////System.out.println("数据库执行结果=" + isUpdateSuc + "     SQL="+ updateSQL);

			if (isUpdateSuc > 0) {
				   response.getWriter().println(
						"{\"result\":\"OK\",\"id\":\"" + mark + "\"}");
			
			} else {
				response.getWriter().println("{\"result\":\"ERROR\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.getWriter().println("{\"result\":\"fail\"}");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	/**
	 * 编辑后台代码
	 * @param request
	 * @param response
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) {
		// 前台穿过来的编号
		String s = request.getParameter("s");
		String arr[] = s.split("_");
		String threesql = "select * from lcp_node_order_item  where CP_ID="+ arr[0] + " and CP_NODE_ID=" + arr[1]+ 
		                  " and CP_NODE_ORDER_ID=" + arr[2]+ " and CP_NODE_ORDER_ITEM_ID=" + arr[3] + "";
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(threesql);
		int row = dataSet.getRowNum();

		for (int i = 0; i < row; i++) {
			String ORDER_NO = dataSet.funGetFieldByCol(i, "ORDER_NO");// 医嘱编码
			String CP_NODE_ORDER_TEXT = dataSet.funGetFieldByCol(i,"CP_NODE_ORDER_TEXT");// 医嘱具体内容
			String SPECIFICATION = dataSet.funGetFieldByCol(i, "SPECIFICATION");// 规格
			String ORDER_KIND = dataSet.funGetFieldByCol(i, "ORDER_KIND");// 医嘱类型（0临时医嘱、1长期医嘱、2出院医嘱、3长期+临时）
			String NEED_ITEM = dataSet.funGetFieldByCol(i, "NEED_ITEM");// 是否必做
			String DEFAULT_ITEM = dataSet.funGetFieldByCol(i, "DEFAULT_ITEM");// 是否默认
			String MEASURE = dataSet.funGetFieldByCol(i, "MEASURE");// 领量
			
			String MEASURE_UNITSBM = dataSet.funGetFieldByCol(i, "MEASURE_UNITS");// 领量单位
			String DOSAGE = dataSet.funGetFieldByCol(i, "DOSAGE");// 一次使用量
			String DOSAGE_UNITSBM = dataSet.funGetFieldByCol(i, "DOSAGE_UNITS");// 一次使用量单位
			String FREQUENCY = dataSet.funGetFieldByCol(i, "FREQUENCY");// 执行频率描述：使用固定或固定格式的描述，如：3/日、TID，每xx分xx次，用户定义
																		// ，见4.21医嘱执行频率字
			String WAY = dataSet.funGetFieldByCol(i, "WAY");// 途径
			String MEASURE_UNITS="";
			DatabaseClass db = LcpUtil.getDatabaseClass();
			if(MEASURE_UNITSBM != ""){
				MEASURE_UNITS=db.FunGetDataSetBySQL("select UNIT from lcp_local_order_dosageunits where CODE='"+MEASURE_UNITSBM+"'").FunGetDataAsStringById(0, 0);
			}
			String DOSAGE_UNITS="";
			if(DOSAGE_UNITSBM != ""){
				DOSAGE_UNITS=db.FunGetDataSetBySQL("select UNIT from lcp_local_order_dosageunits where CODE='"+DOSAGE_UNITSBM+"'").FunGetDataAsStringById(0, 0);
			}
//			String SUPPLYNAME="";
//			if(WAY != ""){
//				SUPPLYNAME=db.FunGetDataSetBySQL("select supply_name from lcp_local_order_way where supply_code='"+WAY+"'").FunGetDataAsStringById(0, 0);
//			}
			String MARK = dataSet.funGetFieldByCol(i, "MARK");// 医生嘱托
			if ("0".equals(ORDER_KIND)) {
				ORDER_KIND = "临时";
				FREQUENCY ="ONCE";
				
			} else if ("1".equals(ORDER_KIND)) {
				ORDER_KIND = "长期";
			} else if ("2".equals(ORDER_KIND)) {
				ORDER_KIND = "出院";
			}else if ("3".equals(ORDER_KIND)) {
				ORDER_KIND = "长期+临时";
				FREQUENCY ="ONCE";
			}
			if(DOSAGE.trim().equals("0")){
				DOSAGE="";
			}
			if(DOSAGE.trim().equals("")||DOSAGE==""){
				DOSAGE_UNITSBM="";
			}
			if(MEASURE.trim().equals("")||MEASURE==""){
				MEASURE_UNITSBM="";
			}
			try {
				response.getWriter().println(
						"{\"result\":\"ok\",\"ORDER_NO\":\"" + ORDER_NO
								+ "\",\"CP_NODE_ORDER_TEXT\":\""+ CP_NODE_ORDER_TEXT
								+ "\",\"SPECIFICATION\":\"" + SPECIFICATION
								+ "\",\"ORDER_KIND\":\"" + ORDER_KIND
								+ "\",\"NEED_ITEM\":\"" + NEED_ITEM
								+ "\",\"DEFAULT_ITEM\":\"" + DEFAULT_ITEM
								+ "\",\"MEASURE\":\"" + MEASURE
								+ "\",\"MEASURE_UNITS\":\"" + MEASURE_UNITS
								+ "\",\"DOSAGE\":\"" + DOSAGE
								+ "\",\"DOSAGE_UNITS\":\"" + DOSAGE_UNITS
								+ "\",\"FREQUENCY\":\"" + FREQUENCY
								+ "\",\"MEASURE_UNITSBM\":\"" + MEASURE_UNITSBM//领量单位 编码
								+ "\",\"DOSAGE_UNITSBM\":\"" + DOSAGE_UNITSBM//计量单位编码
								+ "\",\"WAYBM\":\"" + WAY//途径编码
								+ "\",\"WAY\":\"" + WAY 
								+ "\",\"MARK\":\"" + MARK + "\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * 编辑医嘱信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void editOrderItem(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {
			String s1 = request.getParameter("s1");
			String arr[] = s1.split("_");
			String orderCode = request.getParameter("orderCode");// 医嘱编码
			if (orderCode == null) {
				orderCode = "null";
			}
			String orderName = URLDecoder.decode(
					request.getParameter("orderName"), "UTF-8");// 医嘱内容
					
			if (orderName == null) {
				orderName = "null";
			}
			String specification =URLDecoder.decode(
					request.getParameter("specification"), "UTF-8");// 规格
			
			if (specification == null) {
				specification = "null";
			}
			String orderPY = request.getParameter("orderPY");// 拼音码搜索
			if (orderPY == null) {
				orderPY = "null";
			}
			String orderSelect = request.getParameter("orderSelect");// 医嘱类别
			if (orderSelect == null) {
				orderSelect = "null";
			}
			String radiobutton = request.getParameter("radiobutton");// 是否必做
			if (radiobutton == null) {
				radiobutton = "null";
			}
			String isDefault = request.getParameter("isDefault");// 是否默认
			if (isDefault == null) {
				isDefault = "null";
			}
			String orderjl = request.getParameter("orderjl");// 领量
			if (orderjl == null) {
				orderjl = "null";
			}		
			String orderCode2 =  URLDecoder.decode(
					request.getParameter("orderCode2"), "UTF-8");
			if (orderCode2 == null) {
				orderCode2 = "null";
			}
			String dosage = request.getParameter("dosage");// 一次使用剂量
			if (dosage == null) {
				dosage = "null";
			}
			String orderCode1 =   URLDecoder.decode(
					request.getParameter("orderCode1"), "UTF-8");// 一次使用剂量单位
			if (orderCode1 == null) {
				orderCode1 = "null";
			}
			String orderPC = request.getParameter("orderPC").trim();// 频次
			if (orderPC == null) {
				orderPC = "null";
			}
			String ordertj = request.getParameter("ordertj");// 途径
			if (ordertj== null) {
				ordertj = "null";
			}
			String mark =URLDecoder.decode(
					request.getParameter("mark"), "UTF-8");// 医生嘱托
			if (mark== null) {
				mark = "null";
			}
			String MEASURE_UNITS="";//领量单位编码
			DatabaseClass db = LcpUtil.getDatabaseClass();
			String drugIdSql = "select drug_id from dcp_dict_order_item where order_item_code='"+orderCode+"'";
			String drug_id = db.FunGetDataSetBySQL(drugIdSql).FunGetDataAsStringByColName(0, "DRUG_ID");
			if(orderCode2 != ""){
				MEASURE_UNITS=db.FunGetDataSetBySQL("select CODE from lcp_local_order_dosageunits where UNIT='"+orderCode2+"'").FunGetDataAsStringById(0, 0);
			}
	
			String DOSAGE_UNITS="";//计量单位编码
			if(orderCode1 != ""){
				DOSAGE_UNITS=db.FunGetDataSetBySQL("select CODE from lcp_local_order_dosageunits where UNIT='"+orderCode1+"'").FunGetDataAsStringById(0, 0);
			}
			String SUPPLYNAME="";//途径编码
			if(ordertj != ""){
				SUPPLYNAME=db.FunGetDataSetBySQL("select supply_name from lcp_local_order_way where supply_code='"+ordertj+"'").FunGetDataAsStringById(0, 0);
			}
			//--------->查询医嘱类型 
			String sqlorderTypeName ="select t.ORDER_TYPE_NAME from dcp_dict_order_item t where t.order_item_code='"+orderCode+"' and trim(t.order_item_name)='"+orderName+"'";
			String ORDER_TYPE_NAME = db.FunGetDataSetBySQL(sqlorderTypeName).FunGetDataAsStringById(0, 0);
			//如果领量或一次计量为空，那么单位也显示为空。
	        if(orderjl==null || "".equals(orderjl)){
	        	orderCode2="";
	        }
	        if(dosage==null || "".equals(dosage)){
	        	orderCode1="";
	        }
			String jiliang=orderjl+orderCode2;
			String updateSQL = "update lcp_node_order_item set ORDER_NO='"+ orderCode + "'" + ",";// 医嘱编码
			updateSQL += "CP_NODE_ORDER_TEXT='" + orderName + "'" + ",";// 医嘱具体内容
			updateSQL += "SPECIFICATION='" + specification + "'" + ",";// 规格
			updateSQL += "ORDER_KIND='" + orderSelect + "'" + ",";// 医嘱类别
			updateSQL += "NEED_ITEM=" + radiobutton + "" + ",";// 是否必做
			updateSQL += "DEFAULT_ITEM=" + isDefault + "" + ",";// 是否默认
			updateSQL += "DRUG_ID='" + drug_id + "'" + ",";// 药品编码
			updateSQL += "MEASURE='" + orderjl + "'" + ",";// 领量
			updateSQL += "MEASURE_UNITS='" + MEASURE_UNITS + "'" + ",";// 领量单位
			updateSQL += "DOSAGE='" + dosage + "'" + ",";// 一次使用量
			updateSQL += "DOSAGE_UNITS='" + DOSAGE_UNITS + "'" + ",";// 一次使用量单位
			updateSQL += "FREQUENCY='" + orderPC + "'" + ",";// 频次
			updateSQL += "EFFECT_FLAG='" + 0 + "'" + ",";// 频次
			updateSQL += "WAY='" + ordertj + "'"+ ",";// 途径
			updateSQL += "MARK='" + mark + "'" ;// 医生嘱托
			//updateSQL += "WAY='" + ordertj + "'  ";// 途径
			updateSQL += "where CP_ID=" + arr[0] + " and CP_NODE_ID=" + arr[1]+ " and CP_NODE_ORDER_ID=" + arr[2]+ 
			             " and CP_NODE_ORDER_ITEM_ID=" + arr[3] + "";
			DataSet dataSet = new DataSet();
			
			int isUpdateSuc = dataSet.funRunSql(updateSQL);
			if (isUpdateSuc > 0) {
		          String groupId="";
		            String sqlcz="select ORDER_ITEM_SET_ID from lcp_node_order_item where CP_ID=" + arr[0] + " and CP_NODE_ID=" + arr[1]+ 
		            			 " and CP_NODE_ORDER_ID=" + arr[2]+ " and CP_NODE_ORDER_ITEM_ID=" + arr[3] + " ";
		    		dataSet.funSetDataSetBySql(sqlcz);
		    		int row=dataSet.getRowNum();
                    for(int i=0;i<row;i++){
                         groupId=dataSet.funGetFieldByCol(i, "ORDER_ITEM_SET_ID");//分组ID
                    }
        			if("0".equals(groupId)){
        				groupId="无";
        			}

				response.getWriter().println(
						"{\"result\":\"OK\",\"ORDER_NO\":\"" + orderCode
								+ "\",\"CP_NODE_ORDER_TEXT\":\""
								+ orderName
								+ "\",\"ORDER_ITEM_SET_ID\":\"" + groupId
								+ "\",\"ORDER_TYPE_NAME\":\"" + ORDER_TYPE_NAME
								+ "\",\"SPECIFICATION\":\"" + specification
								+ "\",\"ORDER_KIND\":\"" + orderSelect
								+ "\",\"NEED_ITEM\":\"" + radiobutton
								+ "\",\"DEFAULT_ITEM\":\"" + isDefault
								+ "\",\"MEASURE\":\"" + jiliang
								+ "\",\"DOSAGE\":\"" + dosage
								+ "\",\"DOSAGE_UNITS\":\"" + orderCode1
								+ "\",\"FREQUENCY\":\"" + orderPC
								+ "\",\"WAY\":\"" + SUPPLYNAME
								//+ "\",\"WAY\":\"" + ordertj + "\"}");
								+ "\",\"MARK\":\"" + mark + "\"}");
			} else {
				response.getWriter().println("{\"result\":\"ERROR\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("{\"result\":\"fail\"}");
		}
	}

	/**
	 * 删除家属工作信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void del_family_point(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id = request.getParameter("cp_id");
		String cp_node_id = request.getParameter("cp_node_id");
		String cp_node_table_id = request.getParameter("cp_node_table_id");
		String sql = "delete lcp_node_family_point where CP_ID=" + cp_id + " and CP_NODE_ID="
				+ cp_node_id + " and CP_NODE_FAMILY_ID=" + cp_node_table_id + "";
		DataSet dataSet = new DataSet();
		int row = dataSet.funRunSql(sql);
		if (row > 0) {
			NodeFamily family = new NodeFamily();
			String table = family.funGetPointTable(cp_id, cp_node_id);
			response.getWriter().println(
					"[{\"result\":\"OK\",\"flag\":\"1\",\"table\":\"" + table + "\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"ERROR\", \"flag\":\"0\"}]");
		}
	}

	/**
	 * 添加家属工作
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void add_family_point(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			String cp_id = request.getParameter("cp_id");
			String cp_node_id = request.getParameter("cp_node_id");
			String name = URLDecoder.decode(request.getParameter("name"), "UTF-8");
			String need = request.getParameter("need");
			String sql = "";
			sql = "insert into lcp_NODE_FAMILY_POINT (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_FAMILY_ID, CP_NODE_FAMILY_TEXT, NEED_ITEM, SYS_IS_DEL, SYS_LAST_UPDATE)"
					+ " values ("
					+ HOSPITALID
					+ ","
					+ cp_id
					+ ","
					+ cp_node_id
					+ ",(select max(cp_node_family_id)+1 id from (select t.cp_node_family_id from lcp_node_family_point t"
					+ " 	where t.cp_id="
					+ cp_id
					+ " and t.cp_node_id="
					+ cp_node_id
					+ ""
					+ "	union select 0 cp_node_family_id from dual)),"
					+ "'"
					+ name
					+ "',"
					+ need
					+ "," + "0," + CommonUtil.getOracleToDate() + ")";
			DataSet dataSet = new DataSet();
			int isUpdateSuc = dataSet.funRunSql(sql);
			if (isUpdateSuc > 0) {
				NodeFamily family = new NodeFamily();
				String table = family.funGetPointTable(cp_id, cp_node_id);
				response.getWriter().println("[{\"result\":\"OK\",\"flag\":\"1\",\"table\":\"" + table + "\"}]");
			} else {
				response.getWriter().println("[{\"result\":\"ERROR\", \"flag\":\"0\"}]");
			}
		} catch (Exception e) {
			response.getWriter().println("[{\"result\":\"fail\", \"flag\":\"0\"}]");
		}
	}

	/**
	 * 删除节点医嘱信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void del_lcp_node_order_item(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String ids = request.getParameter("delids");
		ids = ids.substring(0, ids.length() - 1);
		NodeOrder order = new NodeOrder();
		boolean isSuc = order.funDelItem(ids);
		String[] _ids = ids.split("_");
		if (isSuc) {
			String table = order.funGetItemTable(_ids[0], _ids[1], _ids[2]);
			response.getWriter().println("[{\"result\":\"OK\", \"table\":\"" + table + "\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"ERROR\"}]");
		}
	}

	/**
	 * 删除节点护理工作信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void del_lcp_node_nurse_item(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String ids = request.getParameter("delids");
		ids = ids.substring(0, ids.length() - 1);
		NodeNurse nurse = new NodeNurse();
		boolean isSuc = nurse.funDelItem(ids);
		String[] _ids = ids.split("_");
		if (isSuc) {
			String table = nurse.funGetItemTable(_ids[0], _ids[1], _ids[2]);
			response.getWriter().println("[{\"result\":\"OK\", \"table\":\"" + table + "\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"ERROR\"}]");
		}
	}

	/**
	 * 删除节点诊疗工作信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void del_lcp_node_doctor_item(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String ids = request.getParameter("delids");
		ids = ids.substring(0, ids.length() - 1);
		NodeDoctor doctor = new NodeDoctor();
		boolean isSuc = doctor.funDelItem(ids);
		String[] _ids = ids.split("_");
		if (isSuc) {
			String table = doctor.funGetItemTable(_ids[0], _ids[1], _ids[2]);
			response.getWriter().println("[{\"result\":\"OK\", \"table\":\"" + table + "\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"ERROR\"}]");
		}
	}
	/**
	 * 删除节点诊疗工作信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void del_lcp_node_doctor_point(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id = request.getParameter("cp_id");
		String cp_node_id = request.getParameter("cp_node_id");
		String cp_node_table_id = request.getParameter("cp_node_table_id");
		NodeDoctor doctor = new NodeDoctor();
		boolean isSuc = doctor.funDelPoint(cp_id, cp_node_id, cp_node_table_id);
		if (isSuc) {
			String table = doctor.funGetPointTable(cp_id, cp_node_id);
			response.getWriter().println("[{\"result\":\"OK\", \"table\":\"" + table + "\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"ERROR\"}]");
		}
	}

	/**
	 * 删除节点护理工作信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void del_lcp_node_nurse_point(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id = request.getParameter("cp_id");
		String cp_node_id = request.getParameter("cp_node_id");
		String cp_node_table_id = request.getParameter("cp_node_table_id");
		NodeNurse nurse = new NodeNurse();
		boolean isSuc = nurse.funDelPoint(cp_id, cp_node_id, cp_node_table_id);
		if (isSuc) {
			String table = nurse.funGetPointTable(cp_id, cp_node_id);
			response.getWriter().println("[{\"result\":\"OK\", \"table\":\"" + table + "\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"ERROR\"}]");
		}
	}

	/**
	 * 展示护理工作
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void show_nurse_item(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String cp_id = request.getParameter("cp_id");
		String cp_node_id = request.getParameter("cp_node_id");
		String cp_node_doctor_id = request.getParameter("cp_node_table_id");
		NodeNurse nurse = new NodeNurse();

		String table = nurse.funGetItemTable(cp_id, cp_node_id, cp_node_doctor_id);
		response.getWriter().println("[{\"result\":\"OK\", \"table\":\"" + table + "\"}]");

	}

	/**
	 * 查看医嘱信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void show_order_item(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String cp_id = request.getParameter("cp_id");
		String cp_node_id = request.getParameter("cp_node_id");
		String cp_node_doctor_id = request.getParameter("cp_node_table_id");
		NodeOrder order = new NodeOrder();
		String table = order.funGetItemTable(cp_id, cp_node_id, cp_node_doctor_id);
		response.getWriter().println("[{\"result\":\"OK\", \"table\":\"" + table + "\"}]");

	}

	/**
	 * 查看医嘱信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void show_order_belong(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id = request.getParameter("cp_id");
		String cp_node_id = request.getParameter("cp_node_id");
		NodeOrder order = new NodeOrder();
		String table = order.funGetOrderBelong(cp_id, cp_node_id);
		response.getWriter().println("[{\"result\":\"OK\", \"table\":\"" + table + "\"}]");

	}

	/**查看护理工作信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void show_nurse_point(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String cp_id = request.getParameter("cp_id");
		String cp_node_id = request.getParameter("cp_node_id");
		NodeNurse nurse = new NodeNurse();

		String table = nurse.funGetPointTable(cp_id, cp_node_id);
		response.getWriter().println("[{\"result\":\"OK\", \"table\":\"" + table + "\"}]");

	}

	/**
	 * 查看医嘱信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void show_order_point(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id = request.getParameter("cp_id");
		String cp_node_id = request.getParameter("cp_node_id");
		NodeOrder order = new NodeOrder();

		String table = order.funGetPointTable(cp_id, cp_node_id,null);
		response.getWriter().println("[{\"result\":\"OK\", \"table\":\"" + table + "\"}]");

	}

	/**
	 * 查看诊疗工作信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void show_doctor_item(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id = request.getParameter("cp_id");
		String cp_node_id = request.getParameter("cp_node_id");
		String cp_node_doctor_id = request.getParameter("cp_node_table_id");
		NodeDoctor doctor = new NodeDoctor();

		String table = doctor.funGetItemTable(cp_id, cp_node_id, cp_node_doctor_id);
		response.getWriter().println("[{\"result\":\"OK\", \"table\":\"" + table + "\"}]");

	}

	/**
	 * 查看诊疗工作信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void show_doctor_point(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id = request.getParameter("cp_id");
		String cp_node_id = request.getParameter("cp_node_id");
		NodeDoctor doctor = new NodeDoctor();

		String table = doctor.funGetPointTable(cp_id, cp_node_id);
		response.getWriter().println("[{\"result\":\"OK\", \"table\":\"" + table + "\"}]");

	}

	/**
	 * 添加节点医嘱信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addNodeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String cp_id = request.getParameter("cp_id");
		String cp_node_id = request.getParameter("cp_node_id").replaceAll("tr", "");
		String orderCode = request.getParameter("orderCode");
		String orderName = URLDecoder.decode(request.getParameter("orderName"), "UTF-8");
		String type = request.getParameter("type");
		String need = request.getParameter("need");
		String maxIdSql = "SELECT MAX(CP_NODE_ORDER_ID)+1 MAXID from lcp_NODE_ORDER where cp_id="
				+ cp_id + " and cp_node_id=" + cp_node_id;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dc = db.FunGetDataSetBySQL(maxIdSql);
		Number maxid = dc.FunGetDataAsNumberById(0, 0);
		if (maxid == null) {
			maxid = 1;
		}

		String sql = "	insert into lcp_node_order (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_ORDER_ID, CP_NODE_ORDER_TEXT, NEED_ITEM, ORDER_NO, ORDER_TYPE, SYS_IS_DEL, SYS_LAST_UPDATE)"
				+ " values ("
				+ HOSPITALID
				+ ","
				+ cp_id
				+ ","
				+ cp_node_id
				+ ", "
				+ maxid
				+ ", '"
				+ orderName
				+ "', "
				+ need
				+ ", '"
				+ orderCode
				+ "', '"
				+ type
				+ "', 0,"
				+ CommonUtil.getOracleToDate() + ")";
		String key = db.FunGetSvrKey();
		int isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		if (isUpdateSuc > 0) {
			response.getWriter().println("[{\"result\":\"OK\", \"id\":\"" + maxid + "\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"fail\"}]");
		}

	}

	/**
	 * 添加新的路径
	 * @param request
	 * @param response
	 * @throws IOException
	 *
	 */
	private void addCP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			String config = "select config_value from dcp_sys_config  where config_id='20110826PZ01'";
			String maxIdSql = "SELECT MAX(CP_ID) MAXID from lcp_master ";

			String date = CommonUtil.getOracleToDate();
			DatabaseClass db = LcpUtil.getDatabaseClass();
			String userId = (String) request.getSession().getAttribute("userid");
			String userName = (String) request.getSession().getAttribute("username");
			String cp_name = URLDecoder.decode(request.getParameter("cp_name"), "UTF-8");
			String dept_name = URLDecoder.decode(request.getParameter("dept_name"), "UTF-8");
			String dept_code=request.getParameter("dept_id");
			String min_day = isNull(request.getParameter("min_day"));
			String max_day = isNull(request.getParameter("max_day"));
			String avg_day = isNull(request.getParameter("avg_day"));
			String avg_fee = isNull(request.getParameter("avg_fee"));

			String pym = request.getParameter("cp_pym").toUpperCase();
			String health_care_quota = isNull(request.getParameter("cp_health_care_quota"));//医保定额
			DataSetClass dc = db.FunGetDataSetBySQL(config);
			int configID = Integer.valueOf(dc.FunGetDataAsStringByColName(0, "CONFIG_VALUE"));

			dc = db.FunGetDataSetBySQL(maxIdSql);
			String cpMaxID=dc.FunGetDataAsStringById(0, 0);
			int cpID=0;
			if(!cpMaxID.equals("")){
				cpID = Integer.valueOf(cpMaxID);
			}
			
			

			if (cpID > configID) {
				cpID = cpID + 1;
			} else {
				cpID = HOSPITALID * 10000 + 1;
			}

			String sql = "insert into lcp_master ("
					+ "HOSPITAL_ID,CP_ID,CP_CODE, CP_NAME, CP_START_DATE, CP_MASTER_ID, CP_VERSION, CP_STATUS, CP_VERSION_DATE, "
					+ " CP_CREATE_DATE,CP_CREATE_ID, CP_CREATE_NAME, CP_STOP_DATE, CP_DAYS_MIN, CP_DAYS_MAX, CP_DAYS, CP_FEE,"
					+ "  DEPT_NAME ,dept_code, SYS_IS_DEL, SYS_LAST_UPDATE,INPUT_CODE_PY,HEALTH_CARE_QUOTA)"
					+ "values ("
					+ HOSPITALID
					+ ","
					+ cpID
					+ ", '"
					+ cpID+"-1"
					+ "','"
					+ cp_name
					+ "',null, "+cpID+", 1, 1, "
					+ date
					+ ", "
					+ date
					+ ","
					+ ""+userId+", '"
					+userName+"', null, "
					+ min_day
					+ ","
					+ max_day
					+ ","
					+ avg_day
					+ ", "
					+ avg_fee
					+ ", "
					+ " '"
					+ dept_name 
					+ "','"
					+dept_code+"', 0, " + date + ",'" + pym + "','" + health_care_quota + "')";

			String key = db.FunGetSvrKey();
			int isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
			if (isUpdateSuc > 0) {
				createNode(cpID,HOSPITALID);
				response.getWriter().println("[{\"result\":\"OK\", \"id\":\"" + cpID + "\"}]");
			} else response.getWriter().println("[{\"result\":\"fail\"}]");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("[{\"result\":\"fail\"}]");
		}
	}
	/**
	 * 判断路径是否停用
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void editOrNot(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id = request.getParameter("cp_id");
		
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String userID=(String)request.getSession().getAttribute("userid");
		String usersql = "select funs_id from dcp_sys_power where user_id="+userID;
		DataSetClass dataSet = db.FunGetDataSetBySQL(usersql);
		String id="";
		String funid="";
		for(int i=0;i<dataSet.FunGetRowCount();i++){
			id=dataSet.FunGetDataAsStringByColName(i,"FUNS_ID");
			funid+=id;
		}
		
		String startOrStopSql="select cp_status from lcp_master where cp_id="+cp_id;
		int startOrStopFlag=db.FunGetDataSetBySQL(startOrStopSql).FunGetDataAsNumberById(0, 0).intValue();
		if(funid.contains("61")){
			if(startOrStopFlag == 1 || startOrStopFlag == 3 || startOrStopFlag == 2){
				response.getWriter().println("[{\"result\":\"OK\"}]");
			}else if(startOrStopFlag == 0){
				response.getWriter().println("[{\"result\":\"noEdit\"}]");
			}else if(startOrStopFlag == 4){
				response.getWriter().println("[{\"result\":\"hidden\"}]");
			}
		}else{
			if(startOrStopFlag == 1 || startOrStopFlag == 3){
				response.getWriter().println("[{\"result\":\"OK\"}]");
			}else if(startOrStopFlag == 2){
				response.getWriter().println("[{\"result\":\"submit\"}]");
			}else if(startOrStopFlag == 0){
				response.getWriter().println("[{\"result\":\"noEdit\"}]");
			}else if(startOrStopFlag == 4){
				response.getWriter().println("[{\"result\":\"hidden\"}]");
			}
		}
	}
	
	/**
	 * 编辑路径 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void editCP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String cp_id = request.getParameter("cp_id");
		String audit = request.getParameter("audit");
		String cp_name = URLDecoder.decode(request.getParameter("cp_name"), "UTF-8");
		String min_day = isNull(request.getParameter("min_day"));
		String max_day = isNull(request.getParameter("max_day"));
		String avg_day = isNull(request.getParameter("avg_day"));
		String avg_fee = isNull(request.getParameter("avg_fee"));
		String cp_code = request.getParameter("cp_code");
		String dept_name = URLDecoder.decode(request.getParameter("dept_name"), "UTF-8");
		String dept_code=request.getParameter("dept_code");
		String pym = request.getParameter("cp_pym").toUpperCase();
		String health_care_quota = isNull(request.getParameter("cp_health_care_quota"));
		String date = CommonUtil.getOracleToDate();
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String key = db.FunGetSvrKey();
		String maxIdSql = "SELECT MAX(CP_ID)+1 MAXID from lcp_master ";
		DataSetClass dc = db.FunGetDataSetBySQL(maxIdSql);
		Number maxid = dc.FunGetDataAsNumberById(0, 0);
		if (maxid == null) {
			maxid = 1;
		}
		if (audit.equals("0")) {// 未审核的路径编辑
			String sql = "update lcp_master set CP_NAME='" + cp_name + "',CP_DAYS_MIN=" + min_day
					+ ",CP_DAYS_MAX=" + max_day + ",CP_DAYS=" + avg_day + ",CP_FEE=" + avg_fee
					+ ",DEPT_NAME='" + dept_name + "',dept_code='" + dept_code +"',SYS_LAST_UPDATE=" + date + ",cp_code='"
					+ cp_code + "',input_code_py='" + pym + "',health_care_quota='" + health_care_quota
					+ "' where cp_id=" + cp_id;
			int isUpdateSuc = db.FunRunSQLCommand(key, sql);
			String result = "fail";
			if (isUpdateSuc > 0) {
				result = "OK";
			}
			response.getWriter().println("{\"result\":\"" + result + "\"}");

		} else if (audit.equals("1")) {// 已经审核的路径编辑
			String sql = "insert into lcp_master ("
					+ "HOSPITAL_ID,CP_ID, CP_NAME, CP_START_DATE, CP_MASTER_ID, CP_VERSION, CP_STATUS, CP_VERSION_DATE, CP_CREATE_DATE,"
					+ " CP_CREATE_ID, CP_CREATE_NAME, CP_STOP_DATE, CP_DAYS_MIN, CP_DAYS_MAX, CP_DAYS, CP_FEE, CP_START_USER,"
					+ " CP_STOP_USER,  DEPT_NAME,dept_code, SYS_IS_DEL, SYS_LAST_UPDATE)" + "values ("
					+ HOSPITALID
					+ ","
					+ maxid
					+ ", '"
					+ cp_name
					+ "', "
					+ date
					+ ", "
					+ cp_id
					+ ", 1, 0, "
					+ date
					+ ", "
					+ date
					+ ","
					+ " '', '管理员', null, "
					+ min_day
					+ ","
					+ max_day
					+ ","
					+ avg_day
					+ ", "
					+ avg_fee
					+ ", '', "
					+ "'', '"
					+ dept_name
					+ "','"
					+dept_code
					+"', 0, " + date + ")";

			int isUpdateSuc = db.FunRunSQLCommand(key, sql);
			if (isUpdateSuc > 0) {
				EditCP ecp = new EditCP();
				ecp.copyCPInfo(cp_id);
				EditCP ed = new EditCP();
				String batSQL = ed.copyCPInfo(cp_id);
				while ("error".equals(batSQL)) {
					batSQL = ed.copyCPInfo(cp_id);
				}
				try {
					db.FunRunSqlByFile(batSQL.getBytes("GBK"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}				
				response.getWriter().println("[{\"result\":\"OK\",\"id\":\"2\",\"maxid\":\"" + maxid + "\"}]");
			} else {
				response.getWriter().println("[{\"result\":\"fail\"}]");
				}
			}

	}

	/**
	 * 查看路径状态
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void selectCPStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String cpID = request.getParameter("cp_id");
		String selectSql="select cp_start_date from lcp_master where cp_id = " + cpID;
		String startTime = db.FunGetDataSetBySQL(selectSql).FunGetDataAsStringById(0, 0);
		if("".equals(startTime)){
			response.getWriter().println("[{\"result\":\"OK\"}]");
		}else{
			response.getWriter().println("[{\"result\":\"start\"}]");
		}
		
	}
	
	/**
	 * 删除自定义路径
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void delCP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String cpID = request.getParameter("cp_id");
		int id = Integer.valueOf(cpID);		
		if (id > 10000) {
				String sql = "delete LCP_MASTER where cp_id = " + cpID + " \n "
				+ "delete LCP_MASTER_ANTIBIOTICS where cp_id = " + cpID + " \n "
				+ "delete LCP_MASTER_BASED where cp_id = " + cpID + " \n "
				+ "delete LCP_MASTER_DISCHARGE where cp_id = " + cpID + " \n "
				+ "delete LCP_MASTER_EXCLUDE where cp_id = " + cpID + " \n "
				+ "delete LCP_MASTER_INCOME where cp_id = " + cpID + " \n "
				+ "delete LCP_MASTER_NODE where cp_id = " + cpID + " \n "
				+ "delete LCP_MASTER_VARIATION where cp_id = " + cpID + " \n "
				+ "delete LCP_NODE_DOCTOR_ITEM where cp_id = " + cpID + " \n "
				+ "delete LCP_NODE_DOCTOR_POINT where cp_id = " + cpID + " \n "
				+ "delete LCP_NODE_FAMILY_POINT where cp_id = " + cpID + " \n "
				+ "delete LCP_NODE_FAMILY_ITEM where cp_id = " + cpID + " \n "
				+ "delete LCP_NODE_NURSE_ITEM where cp_id = " + cpID + " \n "
				+ "delete LCP_NODE_NURSE_POINT where cp_id = " + cpID + " \n "
				+ "delete LCP_NODE_ORDER_ITEM where cp_id = " + cpID + " \n "
				+ "delete LCP_NODE_ORDER_POINT where cp_id = " + cpID + " \n "
				+ "delete LCP_NODE_VARIATION where cp_id = " + cpID + " \n "
				+ "delete LCP_NODE_RELATE where cp_id = " + cpID + "";
				int res = db.FunRunSqlByFile(sql.getBytes());
				if (res > 0) {
					response.getWriter().println("true");
				} else {
					response.getWriter().println("false");
				}
		} else response.getWriter().println("[{\"result\":\"fail\"}]");

	}

	/**
	 * 删除节点
	 * @param request
	 * @param response
	 * @throws IOException
	 * editor 林建喜
	 * editDate 2013-5-14
	 * editContent 删除节点时重新定义节点关系 
	 */
	private void delNode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		String cp_id = request.getParameter("cp_id");
		String cp_node_id = request.getParameter("cp_node_id").replaceAll("tr", "");
		String selectSql="select cp_node_type from lcp_master_node where cp_id="+cp_id+" and cp_node_id="+cp_node_id;
		String cp_node_type=db.FunGetDataSetBySQL(selectSql).FunGetDataAsStringById(0, 0);
		if("1".equals(cp_node_type)){
			//删除节点前最小的节点号
			String selectSql2="select min(cp_node_id) as minNodeID from lcp_master_node where cp_id="+cp_id+" and CP_NODE_TYPE=1";
			DataSetClass ff = db.FunGetDataSetBySQL(selectSql2);
			int minNodeID=ff.FunGetDataAsNumberById(0, 0).intValue();
			
			String selectSql21="select max(cp_node_id) as maxNodeID from lcp_master_node where cp_id="+cp_id+" and cp_node_id < "+cp_node_id+" and CP_NODE_TYPE=1";
			DataSetClass ww= db.FunGetDataSetBySQL(selectSql21);
			String cpNodeID2=ww.FunGetDataAsStringById(0, 0);
			
			String selectSql22="select min(cp_node_id) as maxNodeID from lcp_master_node where cp_id="+cp_id+" and cp_node_id > "+cp_node_id+" and CP_NODE_TYPE=1";
			DataSetClass ww1= db.FunGetDataSetBySQL(selectSql22);
			String cpNodeID3=ww1.FunGetDataAsStringById(0, 0);
			
			String sql = "delete lcp_master_node  where cp_id=" + cp_id + " and cp_node_id="
			+ cp_node_id;
			String key = db.FunGetSvrKey();
			int isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
			String result = (isUpdateSuc > 0) ? "OK" : "ERROR";
			response.getWriter().println("{\"result\":\"" + result + "\"}");
			//输出节点维护
			int cpNodeID=Integer.parseInt(cp_node_id);
	
			//删除节点后最小的节点号
			String selectSql3="select min(cp_node_id) as minNodeID from lcp_master_node where cp_id="+cp_id+" and CP_NODE_TYPE=1";
			DataSetClass gg = db.FunGetDataSetBySQL(selectSql3);
			String minNodeID1=gg.FunGetDataAsStringById(0, 0);
			
			String selectSql4 = "select cp_node_id from lcp_master_node where cp_node_type=2 and cp_id="+cp_id;
			DataSetClass hh = db.FunGetDataSetBySQL(selectSql4);
			String start=hh.FunGetDataAsStringById(0, 0);
			String selectSql5 = "select cp_node_id from lcp_master_node where cp_node_type=4 and cp_id="+cp_id;
			DataSetClass qq = db.FunGetDataSetBySQL(selectSql5);
			String start1=qq.FunGetDataAsStringById(0, 0);
			String sql1="delete lcp_node_relate where cp_id="+cp_id+" and cp_node_id="+cp_node_id+"";
			if(minNodeID == cpNodeID && !minNodeID1.equals("")){//判断最小节点是否等于要删除的节点
				sql1=sql1 + "\n update lcp_node_relate set cp_next_node_id = "+minNodeID1+" where cp_node_id=(select cp_node_id from lcp_master_node where cp_node_type=0 and cp_id="+cp_id+") and cp_id="+cp_id+" and cp_next_node_id <> (select cp_node_id from lcp_master_node where cp_node_type=4 and cp_id="+cp_id+")"; 
	
			}else if(minNodeID == cpNodeID && minNodeID1.equals("")){
				sql1=sql1 + "\n delete lcp_node_relate where cp_id="+cp_id+" and cp_node_id=(select cp_node_id from lcp_master_node where cp_node_type=0 and cp_id="+cp_id+")" ;
			}else if(minNodeID != cpNodeID && !cpNodeID3.equals("")){
				sql1=sql1 + "\n update lcp_node_relate set cp_next_node_id="+cpNodeID3+" where cp_node_id="+cpNodeID2+" and cp_id="+cp_id+" and cp_next_node_id not in ("+start+","+start1+")"; 
			}else if(minNodeID != cpNodeID && cpNodeID3.equals("")){
				sql1=sql1 + "\n delete lcp_node_relate where cp_id="+cp_id+" and cp_next_node_id="+cp_node_id+"";
			}
			db.FunRunSqlByFile(sql1.getBytes());
			
		}else{
			response.getWriter().println("{\"result\":\"nodel\"}");
		}
		
	}

	/**
	 * 添加节点
	 * @param request
	 * @param response
	 * @throws IOException
	 * editor 林建喜
	 * editDate 2013-5-14
	 * editContent 新增节点时重新定义节点关系
	 */
	private void addNode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id = request.getParameter("cp_id");
		String nodeDate = request.getParameter("nodeDate");
		String nodeType = request.getParameter("nodeType");
		String nodeName = URLDecoder.decode(request.getParameter("nodeName"), "UTF-8");

		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		//查出添加节点前的最大节点
		String selectSql12="select max(cp_node_id) as maxID from lcp_master_node where cp_id="+cp_id+" and cp_node_type=1";
		DataSetClass tt = db.FunGetDataSetBySQL(selectSql12);
		Number maxID2 = tt.FunGetDataAsNumberById(0, 0);
		
		String maxIdSql = "SELECT MAX(CP_NODE_ID)+1 MAXID from lcp_master_node where cp_id="+ cp_id;
		DataSetClass dc = db.FunGetDataSetBySQL(maxIdSql);
		Number maxid = dc.FunGetDataAsNumberById(0, 0);
		if (maxid == null) {
			maxid = 1;
		}
		String sql = "insert into lcp_master_node (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_NAME, CP_NODE_DAYS_MAX, CP_NODE_TYPE,CP_NODE_PARENT_ID, SYS_IS_DEL, SYS_LAST_UPDATE)"
				+ "values ("
				+ HOSPITALID
				+ ","
				+ cp_id
				+ ","
				+ maxid
				+ ",'"
				+ nodeName
				+ "',"
				+ nodeDate + " ," + nodeType + ",0, 0," + CommonUtil.getOracleToDate() + ")";

		String key = db.FunGetSvrKey();
		int isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		////System.out.println("数据库执行结果=" + isUpdateSuc + "     SQL=" + sql);
		if (isUpdateSuc > 0) {
			String sql1="";
			//查看是否添加节点
			String selectSql1="select cp_node_id from lcp_node_relate where cp_id="+cp_id+" and cp_node_id=(select cp_node_id from lcp_master_node where cp_node_type=0 and cp_id="+cp_id+")";
			DataSetClass ee = db.FunGetDataSetBySQL(selectSql1);
			String cpNodeid=ee.FunGetDataAsStringById(0, 0);
			//查出最大节点
			String selectSql="select max(cp_node_id) as maxID from lcp_master_node where cp_id="+cp_id+" and cp_node_type=1";
			DataSetClass dd = db.FunGetDataSetBySQL(selectSql);
			int maxID = dd.FunGetDataAsNumberById(0, 0).intValue();
			//判断是否有完成节点关系
			String selectSql3 = "select cp_node_id from lcp_node_relate where cp_id="+cp_id+" and cp_next_node_id=(select cp_node_id from lcp_master_node where cp_node_type=2 and cp_id="+cp_id+")";
			DataSetClass ff = db.FunGetDataSetBySQL(selectSql3);
			String complate=ff.FunGetDataAsStringById(0, 0);
				//如果与完成节点有关系，则删除该关系
				if(!complate.equals("")){
					String sql2="delete from lcp_node_relate where cp_id="+cp_id+" and cp_next_node_id=(select cp_node_id from lcp_master_node where cp_node_type=2 and cp_id="+cp_id+")";
					db.FunRunSqlByFile(sql2.getBytes());
				}
				//判断是否是第一节点
				if(cpNodeid.equals("")){
					sql1="insert into lcp_node_relate (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NEXT_NODE_ID, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE)" +
				    "values ("+HOSPITALID+", "+cp_id+", 1, 4, null, '', '', 0, " + CommonUtil.getOracleToDate() + ")\n" +
				    		"insert into lcp_node_relate (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NEXT_NODE_ID, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE)" +
				    "values ("+HOSPITALID+", "+cp_id+", 1, (select cp_node_id from lcp_master_node where cp_node_type=4 and cp_id="+cp_id+"), null, '', '', 0, " + CommonUtil.getOracleToDate() + ")" +
					"\n insert into lcp_node_relate (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NEXT_NODE_ID, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE)" +
					    "values ("+HOSPITALID+", "+cp_id+", 4, (select cp_node_id from lcp_master_node where cp_node_type=4 and cp_id="+cp_id+"), null, '', '', 0, " + CommonUtil.getOracleToDate() + ")";

				}else{
				sql1="insert into lcp_node_relate (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NEXT_NODE_ID, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE)" +
			    "values ("+HOSPITALID+", "+cp_id+", "+maxID+", (select cp_node_id from lcp_master_node where cp_node_type=4 and cp_id="+cp_id+"), null, '', '', 0, " + CommonUtil.getOracleToDate() + ")\n" +
						"insert into lcp_node_relate (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NEXT_NODE_ID, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE)" +
			    "values ("+HOSPITALID+", "+cp_id+", "+maxID2+", "+maxID+", null, '', '', 0, " + CommonUtil.getOracleToDate() + ")\n" ; 	   
				}
			db.FunRunSqlByFile(sql1.getBytes());
			response.getWriter().println("{\"result\":\"OK\", \"id\":\"" + maxid + "\"}");
		} else {
			response.getWriter().println("{\"result\":\"fail\"}");
		}
	}

	/**
	 * 添加主要诊疗工作 主要护理工作
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addNurseOrDoctor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			String cp_id = request.getParameter("cp_id");
			String cp_node_table_id = request.getParameter("cp_node_table_id");
			String cp_node_id = request.getParameter("cp_node_id").replaceAll("tr", "").trim();
			String tableName = request.getParameter("tableName");// 生成表名
			String text = URLDecoder.decode(request.getParameter("text"), "UTF-8");
			String code = request.getParameter("code");
			String need = request.getParameter("need");
			String auto = request.getParameter("auto");
			DataSet dataSet = new DataSet();
			String sql = "";
			if ("lcp_node_doctor_point".equals(tableName)) {
				sql = "insert into lcp_node_doctor_point (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_DOCTOR_ID, CP_NODE_DOCTOR_TEXT, NEED_ITEM, SYS_IS_DEL, SYS_LAST_UPDATE)"
						+ " values ("
						+ HOSPITALID
						+ ","
						+ cp_id
						+ ","
						+ cp_node_id
						+ ",(select max(cp_node_doctor_id)+1 id from ("
						+ "select t.cp_node_doctor_id from lcp_node_doctor_point t "
						+ "where t.cp_id="
						+ cp_id
						+ " and t.cp_node_id="
						+ cp_node_id
						+ " "
						+ "union select 0 cp_node_doctor_id from dual)),"
						+ "'"
						+ text
						+ "',"
						+ need + "," + "0," + CommonUtil.getOracleToDate() + ")";
			}
			if ("lcp_node_nurse_point".equals(tableName)) {
				sql = "insert into lcp_node_nurse_point (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_nurse_ID, CP_NODE_nurse_TEXT, NEED_ITEM, SYS_IS_DEL, SYS_LAST_UPDATE)"
						+ " values ("
						+ HOSPITALID
						+ ","
						+ cp_id
						+ ","
						+ cp_node_id
						+ ",(select max(cp_node_nurse_id)+1 id from ("
						+ "select t.cp_node_nurse_id from lcp_node_nurse_point t "
						+ "where t.cp_id="
						+ cp_id
						+ " and t.cp_node_id="
						+ cp_node_id
						+ " "
						+ "union select 0 cp_node_nurse_id from dual)),"
						+ "'"
						+ text
						+ "',"
						+ need
						+ "," + "0," + CommonUtil.getOracleToDate() + ")";
			}
			if ("lcp_node_doctor_item".equals(tableName)) {
				sql = "insert into lcp_node_doctor_item (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_DOCTOR_ID,CP_NODE_DOCTOR_ITEM_ID, CP_NODE_DOCTOR_TEXT, DOCTOR_NO,NEED_ITEM,AUTO_ITEM, SYS_IS_DEL, SYS_LAST_UPDATE)"
						+ " values ("
						+ HOSPITALID
						+ ","
						+ cp_id
						+ ","
						+ cp_node_id
						+ ","
						+ cp_node_table_id
						+ ",(select max(CP_NODE_DOCTOR_ITEM_ID)+1 id from ("
						+ "select t.CP_NODE_DOCTOR_ITEM_ID from lcp_node_doctor_item t "
						+ "where t.cp_id="
						+ cp_id
						+ " and t.cp_node_id="
						+ cp_node_id
						+ " and t.CP_NODE_DOCTOR_ID="
						+ cp_node_table_id
						+ " "
						+ "union select 0 CP_NODE_DOCTOR_ITEM_ID from dual)),"
						+ "'"
						+ text
						+ "','"
						+ code
						+ "',"
						+ need
						+ ","
						+ auto
						+ ","
						+ "0,"
						+ CommonUtil.getOracleToDate() + ")";

				String upSQL = "update LCP_NODE_DOCTOR_POINT set IS_EXECUTE=1,auto_item=0 where CP_ID="
						+ cp_id
						+ " and CP_NODE_ID="
						+ cp_node_id
						+ " and CP_NODE_DOCTOR_ID="
						+ cp_node_table_id;
				dataSet.funRunSql(upSQL);
			}
			if ("lcp_node_nurse_item".equals(tableName)) {
				sql = "insert into lcp_node_nurse_item (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_nurse_ID,CP_NODE_nurse_ITEM_ID, CP_NODE_nurse_TEXT, nurse_NO,NEED_ITEM,AUTO_ITEM, SYS_IS_DEL, SYS_LAST_UPDATE)"
						+ " values ("
						+ HOSPITALID
						+ ","
						+ cp_id
						+ ","
						+ cp_node_id
						+ ","
						+ cp_node_table_id
						+ ",(select max(CP_NODE_nurse_ITEM_ID)+1 id from ("
						+ "select t.CP_NODE_nurse_ITEM_ID from lcp_node_nurse_item t "
						+ "where t.cp_id="
						+ cp_id
						+ " and t.cp_node_id="
						+ cp_node_id
						+ " and t.CP_NODE_nurse_ID="
						+ cp_node_table_id
						+ " "
						+ "union select 0 CP_NODE_nurse_ITEM_ID from dual)),"
						+ "'"
						+ text
						+ "','"
						+ code
						+ "',"
						+ need
						+ ","
						+ auto
						+ ","
						+ "0,"
						+ CommonUtil.getOracleToDate() + ")";
				String upSQL = "update LCP_NODE_NURSE_POINT set IS_EXECUTE=1,auto_item=0 where CP_ID="
						+ cp_id
						+ " and CP_NODE_ID="
						+ cp_node_id
						+ " and CP_NODE_NURSE_ID="
						+ cp_node_table_id;
				dataSet.funRunSql(upSQL);			}

			int isUpdateSuc = dataSet.funRunSql(sql);
			if (isUpdateSuc > 0) {
				response.getWriter().println("[{\"result\":\"OK\",\"flag\":\"1\",\"tableName\":\"" + tableName + "\"}]");
			} else {
				response.getWriter().println("[{\"result\":\"OK\", \"flag\":\"0\"}]");
			}
		} catch (Exception e) {
			response.getWriter().println("[{\"result\":\"fail\", \"flag\":\"0\"}]");
		}
	}

	
	/**
	 * 添加路径排除条件 /添加输出节点/添加变异记录/添加出院标准/添加常见变异
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addTwoCol(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id = request.getParameter("cp_id");
		String tableName = request.getParameter("tableName");
		String datas = URLDecoder.decode(request.getParameter("datas"), "UTF-8");
		String colNameID = tableName.replaceAll("lcp_master_", "cp_") + "_id";// 生成列名
		String colNameName = tableName.replaceAll("lcp_master_", "cp_") + "_name";// 生成列名
		String sql = "";
		Number maxid = null;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		if ("lcp_node_relate".equals(tableName)) {
			String cp_node_id = request.getParameter("cp_node_id").replaceAll("tr", "").trim();
			sql = "insert into lcp_NODE_RELATE (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NEXT_NODE_ID, SYS_IS_DEL, SYS_LAST_UPDATE)"
					+ "values ("
					+ HOSPITALID
					+ ","
					+ cp_id
					+ ","
					+ cp_node_id
					+ ","
					+ datas.replaceAll("tr", "") + " , 0," + CommonUtil.getOracleToDate() + ")";
		} else if ("lcp_node_variation".equals(tableName)) {
			String cp_node_id = request.getParameter("cp_node_id").replaceAll("tr", "").trim();
			String maxIdSql = "SELECT MAX(CP_NODE_VARIATION_ID)+1 MAXID from lcp_NODE_VARIATION where cp_id="
					+ cp_id + " AND CP_NODE_ID=" + cp_node_id;
			DataSetClass dc = db.FunGetDataSetBySQL(maxIdSql);
			maxid = dc.FunGetDataAsNumberById(0, 0);
			if (maxid == null) {
				maxid = 1;
			}
			sql = "insert into lcp_NODE_VARIATION (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_VARIATION_ID, CP_NODE_VARIATION_TEXT, SYS_IS_DEL, SYS_LAST_UPDATE)"
					+ "values ("
					+ HOSPITALID
					+ ","
					+ cp_id
					+ ","
					+ cp_node_id
					+ ","
					+ maxid
					+ ",'"
					+ datas + "' , 0," + CommonUtil.getOracleToDate() + ")";
		} else {
			String maxIdSql = "SELECT MAX(" + colNameID + ")+1 MAXID from " + tableName+ " where cp_id=" + cp_id;
			DataSetClass dc = db.FunGetDataSetBySQL(maxIdSql);
			maxid = dc.FunGetDataAsNumberById(0, 0);
			if (maxid == null) {
				maxid = 1;
			}
			sql = "insert into " + tableName + "(HOSPITAL_ID,cp_id," + colNameID + ","
					+ colNameName + ",SYS_IS_DEL,SYS_LAST_UPDATE) values(" + HOSPITALID + ","
					+ cp_id + "," + maxid + ",'" + datas + "',0," + CommonUtil.getOracleToDate()
					+ ")";
		}
		String key = db.FunGetSvrKey();
		int isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		if (isUpdateSuc > 0) {
			response.getWriter().println(
					"[{\"result\":\"OK\", \"id\":\"" + maxid + "\",\"flag\":\"1\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"OK\", \"flag\":\"0\"}]");
		}
	}

	/**
	 * 添加路径纳入条件
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void add_lcp_master_income(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id = request.getParameter("cp_id");
		String CP_DIAGNOSE_TYPE = URLDecoder.decode(request.getParameter("CP_DIAGNOSE_TYPE"),
				"UTF-8");
		String CP_DIAGNOSE_NAME = URLDecoder.decode(request.getParameter("CP_DIAGNOSE_NAME"),
				"UTF-8");
		String CP_DIAGNOSE_CODE = request.getParameter("CP_DIAGNOSE_CODE");
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dc = db
				.FunGetDataSetBySQL("SELECT MAX(CP_INCOME_ID)+1 MAXID from lcp_MASTER_INCOME where cp_id="
						+ cp_id);
		Number maxid = 1;
		if (dc.FunGetDataAsNumberById(0, 0) != null) {
			maxid = dc.FunGetDataAsNumberById(0, 0);
		}
		String sql = "insert into lcp_master_income"
				+ " (HOSPITAL_ID,CP_ID, CP_INCOME_ID, CP_INCOME_TYPE, CP_INCOME_NAME, CP_INCOME_CODE, SYS_IS_DEL, SYS_LAST_UPDATE)"
				+ "values (" + HOSPITALID + "," + cp_id + ", " + maxid + ",'" + CP_DIAGNOSE_TYPE
				+ "', '" + CP_DIAGNOSE_NAME + "', '" + CP_DIAGNOSE_CODE + "', 0, "
				+ CommonUtil.getOracleToDate() + ")";

		String key = db.FunGetSvrKey();
		int isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		if (isUpdateSuc > 0) {
			response.getWriter().println(
					"[{\"result\":\"OK\", \"id\":\"" + maxid + "\",\"flag\":\"1\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"OK\", \"id\":\"1\", \"flag\":\"0\"}]");
		}
	}

	/**
	 * 修改诊疗依据
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void updata_antibiotics_based(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id = request.getParameter("cp_id");
		String tableName = request.getParameter("tableName");
		String selectSQL = "";
		String sql = "";
		String addSQL = "";
		String date = CommonUtil.getOracleToDate();
		if ("lcp_master_based".equals(tableName)) {
			String cp_diagnosis_based = URLDecoder.decode(
					request.getParameter("CP_DIAGNOSIS_BASED"), "UTF-8");
			String cp_treatment = URLDecoder.decode(request.getParameter("CP_TREATMENT"), "UTF-8");
			sql = "update lcp_master_based set cp_diagnosis_based='" + cp_diagnosis_based
					+ "',cp_treatment='" + cp_treatment + "' where cp_id=" + cp_id;
			addSQL = "insert into lcp_master_based (HOSPITAL_ID,CP_ID, CP_DIAGNOSIS_BASED, CP_TREATMENT, SYS_IS_DEL, SYS_LAST_UPDATE)"
					+ "values ("
					+ HOSPITALID
					+ ","
					+ cp_id
					+ ", '"
					+ cp_diagnosis_based
					+ "', '"
					+ cp_treatment + "', 0," + date + ")";
		} else {
			String cp_antibiotics = URLDecoder.decode(request.getParameter("CP_ANTIBIOTICS"),
					"UTF-8");
			sql = "update lcp_master_antibiotics set cp_antibiotics='" + cp_antibiotics
					+ "' where cp_id=" + cp_id;
			addSQL = "insert into lcp_master_antibiotics (HOSPITAL_ID,CP_ID, CP_ANTIBIOTICS, SYS_IS_DEL, SYS_LAST_UPDATE)"
					+ "values ("
					+ HOSPITALID
					+ ","
					+ cp_id
					+ ", '"
					+ cp_antibiotics
					+ "', 0, "
					+ date + ")";
		}
		selectSQL = "select cp_id from " + tableName + " where cp_id=" + cp_id;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass ds = db.FunGetDataSetBySQL(selectSQL);
		Number id = ds.FunGetDataAsNumberById(0, 0);
		String key = db.FunGetSvrKey();
		int isUpdateSuc = -1;
		if (id != null) {
			isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		} else {
			isUpdateSuc = db.FunRunSQLCommand(key, addSQL);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		}
		if (isUpdateSuc > 0) {
			response.getWriter().println("[{\"result\":\"OK\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"fail\"}]");
		}

	}

	/**
	 * 添加诊断依据 诊疗方案依据
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void updata_master_based(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		String cp_id = request.getParameter("cp_id");
		String cp_diagnosis_based = URLDecoder.decode(request.getParameter("CP_DIAGNOSIS_BASED"),
				"UTF-8");
		String cp_treatment = URLDecoder.decode(request.getParameter("CP_TREATMENT"), "UTF-8");

		String selectSQL = "select cp_id from lcp_master_based where cp_id=" + cp_id;
		String sql = "update lcp_master_based set cp_diagnosis_based='" + cp_diagnosis_based
				+ "',cp_treatment='" + cp_treatment + "' where cp_id=" + cp_id;
		String addSQL = "insert into lcp_master_based (HOSPITAL_ID,CP_ID, CP_DIAGNOSIS_BASED, CP_TREATMENT, SYS_IS_DEL, SYS_LAST_UPDATE)"
				+ "values ("
				+ HOSPITALID
				+ ","
				+ cp_id
				+ ", '"
				+ cp_diagnosis_based
				+ "', '"
				+ cp_treatment + "', 0," + CommonUtil.getOracleToDate() + ")";

		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass ds = db.FunGetDataSetBySQL(selectSQL);
		Number id = ds.FunGetDataAsNumberById(0, 0);
		String key = db.FunGetSvrKey();
		int isUpdateSuc = -1;
		if (id != null) {
			isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		} else {
			isUpdateSuc = db.FunRunSQLCommand(key, addSQL);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
		}
		if (isUpdateSuc > 0) {
			response.getWriter().println("[{\"result\":\"OK\", \"id\":\"1\",\"flag\":\"1\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"OK\", \"id\":\"1\", \"flag\":\"0\"}]");
		}
	}

	/**
	 * 启用 或者 停止 给定id的路径
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void start_stop_CP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cp_id = request.getParameter("cp_id");
		String op = request.getParameter("op");
		String sql;
		int isUpdateSuc;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String key = db.FunGetSvrKey();
		if ("start_cp".equals(op)) {
			sql = "UPDATE lcp_MASTER  SET CP_STATUS=1,CP_START_DATE="
					+ CommonUtil.getOracleToDate() + ",sys_last_update="+CommonUtil.getOracleToDate()+" WHERE CP_ID=" + cp_id;
			isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
			if (isUpdateSuc > 0) {
				response.getWriter().println(
						"[{\"result\":\"start\", \"cp_status\":\"1\",  \"time\":\""
								+ CommonUtil.getDBTimeString() + "\",\"flag\":\"1\"}]");
			} else {
				response.getWriter().println(
						"[{\"result\":\"start\", \"cp_status\":\"0\", \"flag\":\"0\"}]");
			}
		} else {
			sql = "UPDATE lcp_MASTER  SET CP_STATUS=0,CP_STOP_DATE=" + CommonUtil.getOracleToDate()
					+ ",sys_last_update="+CommonUtil.getOracleToDate()+" WHERE CP_ID=" + cp_id;
			isUpdateSuc = db.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
			if (isUpdateSuc > 0) {
				response.getWriter().println("[{\"result\":\"stop\", \"cp_status\":\"0\", \"time\":\""
								+ CommonUtil.getDBTimeString() + "\",\"flag\":\"1\"}]");
			} else {
				response.getWriter().println("[{\"result\":\"stop\", \"cp_status\":\"1\",\"flag\":\"0\"}]");
			}
		}
	}

	/**
	 * 查询数据 并且返回
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void selectCPMasterInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id = request.getParameter("cp_id");
		String lcp_master = "select * from lcp_master where cp_id=" + cp_id;
		String lcp_master_based = "select * from lcp_master_based  where cp_id=" + cp_id;
		String lcp_master_antibiotics = "select * from lcp_master_antibiotics  where cp_id="+ cp_id;
		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dataSet = db.FunGetDataSetBySQL(lcp_master);
		DataSetClass dataSet1 = db.FunGetDataSetBySQL(lcp_master_based);
		DataSetClass dataSet2 = db.FunGetDataSetBySQL(lcp_master_antibiotics);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String cp_status = dataSet.FunGetDataAsStringByColName(0, "CP_STATUS");//
		String cp_version = dataSet.FunGetDataAsStringByColName(0, "CP_VERSION");//
		String cp_days = dataSet.FunGetDataAsStringByColName(0, "CP_DAYS");//
		String cp_fee = dataSet.FunGetDataAsStringByColName(0, "CP_FEE");//
		String cp_days_min = dataSet.FunGetDataAsStringByColName(0, "CP_DAYS_MIN");//
		String cp_days_max = dataSet.FunGetDataAsStringByColName(0, "CP_DAYS_MAX");//
		String cp_name = dataSet.FunGetDataAsStringByColName(0, "CP_NAME");//
		String dept_name = dataSet.FunGetDataAsStringByColName(0, "DEPT_NAME");//
		String dept_code = dataSet.FunGetDataAsStringByColName(0, "DEPT_CODE");//

		String cpCode = dataSet.FunGetDataAsStringByColName(0, "CP_CODE");//
		String pym = dataSet.FunGetDataAsStringByColName(0, "INPUT_CODE_PY");//
		String health_care_quota = dataSet.FunGetDataAsStringByColName(0, "HEALTH_CARE_QUOTA");//

		String cp_create_name = dataSet.FunGetDataAsStringByColName(0, "CP_CREATE_NAME");//
		Date stop = dataSet.FunGetDataAsDateByColName(0, "CP_STOP_DATE");
		String cp_stop_date = "";
		String verify_date = "0";//
		if (dataSet.FunGetDataAsDateByColName(0, "VERIFY_DATE") != null) {
			verify_date = "1";
		}
		if (stop != null) {
			cp_stop_date = sdf.format(dataSet.FunGetDataAsDateByColName(0, "CP_STOP_DATE"));//
		}

		String cp_version_date = "";
		if (dataSet.FunGetDataAsDateByColName(0, "CP_VERSION_DATE") != null) {
			cp_version_date = sdf.format(dataSet.FunGetDataAsDateByColName(0, "CP_VERSION_DATE"));// 路径版本日期
		}

		String cp_create_date = "";
		if (dataSet.FunGetDataAsDateByColName(0, "CP_CREATE_DATE") != null) {
			cp_create_date = sdf.format(dataSet.FunGetDataAsDateByColName(0, "CP_CREATE_DATE"));//

		}

		String cp_diagnosis_based = CommonUtil
				.replactCharacter(dataSet1.FunGetDataAsStringByColName(0, "CP_DIAGNOSIS_BASED"))
				.replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\r", "<br/>");// 诊断依据
		String cp_treatment = CommonUtil
				.replactCharacter(dataSet1.FunGetDataAsStringByColName(0, "CP_TREATMENT"))
				.replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\r", "<br/>");// 治疗方案依据

		String cp_antibiotics = CommonUtil
				.replactCharacter(dataSet2.FunGetDataAsStringByColName(0, "CP_ANTIBIOTICS"))
				.replace("\r\n", "<br/>").replace("\n", "<br/>").replace("\r", "<br/>");// 预防性抗菌药物选择与使用时机内容
		String result = "[{\"result\":\"OK\"," + " \"id\":\"1\", " + " \"cp_id\":\"" + cp_id
				+ "\",  \"cp_name\":\"" + cp_name + "\",  \"cp_stop_date\":\"" + cp_stop_date
				+ "\",\"cp_status\":\"" + cp_status + "\",  \"cp_create_date\":\"" + cp_create_date
				+ "\",  \"cp_create_name\":\"" + cp_create_name + "\", \"cp_version\":\""
				+ cp_version + "\", \"cp_days\":\"" + cp_days + " \", \"cp_version\":\""
				+ cp_version + "\",  \"cp_version_date\":\"" + cp_version_date
				+ "\",  \"cp_fee\":\"" + cp_fee + "\", \"cp_days_min\":\"" + cp_days_min
				+ " \", \"cp_days_max\":\"" + cp_days_max + " \",  \"verify_date\":\""
				+ verify_date + " \", \"cp_treatment\":\"" + cp_treatment
				+ "\",  \"cp_diagnosis_based\":\"" + cp_diagnosis_based
				+ "\",  \"cp_antibiotics\":\"" + cp_antibiotics + "\", \"dept_name\":\""
				+ dept_name + "\", \"dept_code\":\""+dept_code+" \",\"cp_code\":\"" + cpCode + "\", " + " \"pym\":\"" + pym + "\", "
				+ " \"health_care_quota\":\"" + health_care_quota + "\", \"flag\":\"1\"}]";
		if (dataSet.FunGetRowCount() > 0) {
			response.getWriter().println(result);
		} else {
			response.getWriter().println("[{\"result\":\"OK\", \"id\":\"1\", \"flag\":\"0\"}]");
		}
	}
	/**
	 * 删除节点诊疗工作
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void del(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cp_id = request.getParameter("cp_id");
		String colName = request.getParameter("colName");
		String range = request.getParameter("range");
		String tableName = request.getParameter("tableName");
		String sql;
		if (range != null && range != "") {
			range = range.substring(2);
			String id[] = range.split("tr");
			String ids = "";
			for (int i = 0; i < id.length; i++) {
				ids = ids + id[i] + ",";
			}
			ids = ids.substring(0, ids.length() - 1);
			if ("lcp_node_relate".equals(tableName) || "lcp_node_doctor".equals(tableName)
					|| "lcp_node_order".equals(tableName) || "lcp_node_nurse".equals(tableName)
					|| "lcp_node_variation".equals(tableName)) {
				String cp_node_id = request.getParameter("cp_node_id").replaceAll("tr", "").trim();
				sql = "delete " + tableName + " where cp_id=" + cp_id + " and cp_node_id="+ cp_node_id + " and " + colName + " in (" + ids + ")";
			} else {
				sql = "delete " + tableName + " where cp_id=" + cp_id + " and "+ colName + " in (" + ids + ")";
			}
			DatabaseClass databaseClass = LcpUtil.getDatabaseClass();
			String key = databaseClass.FunGetSvrKey();
			int isUpdateSuc = databaseClass.FunRunSQLCommand(key, sql);// isUpdateSuc=1表示数据更新成功;isUpdateSuc<1表示异常
			if (isUpdateSuc > 0) {
				response.getWriter().println("[{\"result\":\"OK\"}]");
			} else {
				response.getWriter().println("[{\"result\":\"fail\"}]");
			}
		}
	}

	/**
	 * 查看节点信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void selectNode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String cp_id = request.getParameter("cp_id");
		String sql="";
		String table="";
		sql = "select t.cp_id as copyNode,t.cp_node_id as cp_node_id,t.cp_node_name as cp_node_name from lcp_master_node t where t.cp_id='"+cp_id+"' and t.cp_node_type=1 ";
		
		DatabaseClass databaseClass = LcpUtil.getDatabaseClass();
		DataSetClass dataSet = databaseClass.FunGetDataSetBySQL(sql);
		int rows = dataSet.FunGetRowCount();

		for(int i=0;i<rows;i++){
			Number cp_node_id = dataSet.FunGetDataAsNumberByColName(i, "CP_NODE_ID");
			String cp_node_name = dataSet.FunGetDataAsStringByColName(i, "CP_NODE_NAME");
			
			table=table+"<tr height='20' bgcolor='#FFFFFF'  onmouseout='recoverColor(this);' style='cursor:pointer'; onclick='clickChange(this);' ondblclick='ondblClickCopy(this)'>"+
			"<td align='center' class='STYLE10'>"+cp_node_id+"</td>" +
			"<td align='center' class='STYLE10'>"+cp_node_name+"</td>" +
			"<td align='center' style='display:none' id='data'  value='"+cp_node_id+"'>" +
			"</td></tr>";
		}
		response.getWriter().println(table);
	}
	
	 /**
	  * 复制节点信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void copyNode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cp_id = request.getParameter("cp_id");
		String cpNnodeId = request.getParameter("cp_node_id");//选中的节点
		String cpNodeZKID = request.getParameter("cpNodeZKID");//目标节点
		String cpOrder1Point = request.getParameter("cpOrder1Point");//复制的一级节点
		String cpOrder2Point = request.getParameter("cpOrder2Point");//复制的二级节点

		DatabaseClass databaseClass = LcpUtil.getDatabaseClass();

		String insertSql="";
		//------------------------选中的要复制的节点----------------------------
		String sql1="select t.cp_node_class_id from lcp_node_order_point t " +
		" where t.cp_id="+cp_id+" and t.cp_node_id="+cpNnodeId+" and t.continue_order_id="+cpOrder1Point+" and t.cp_node_order_id = t.continue_order_id";
		DataSetClass dataSet = databaseClass.FunGetDataSetBySQL(sql1);
		
		int xuanzhongNodeClassID = dataSet.FunGetDataAsNumberByColName(0, "CP_NODE_CLASS_ID").intValue();//一级医嘱的code
		//-----------------------------目标节点--------------------------
		String sql="select t.continue_order_id,t.cp_node_class_id from lcp_node_order_point t " +
				" where t.cp_id="+cp_id+" and t.cp_node_id="+cpNodeZKID+" and t.cp_node_class_id="+xuanzhongNodeClassID+" and t.cp_node_order_id = t.continue_order_id ";

		DataSetClass dataSet1 = databaseClass.FunGetDataSetBySQL(sql);
		String cpNodeClassID = dataSet1.FunGetDataAsStringByColName(0, "CP_NODE_CLASS_ID");
		String continueOrderID = dataSet1.FunGetDataAsStringByColName(0, "CONTINUE_ORDER_ID");

		if(cpNodeClassID != ""){
			insertSql="insert into lcp_node_order_item (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_ORDER_ID, CP_NODE_ORDER_ITEM_ID, CP_NODE_ORDER_TEXT, ORDER_NO, ORDER_TYPE, NEED_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE, AUTO_ITEM, ORDER_TYPE_NAME, ORDER_KIND, MEASURE, FREQUENCY, WAY, DOSAGE, DOSAGE_UNITS, ADMINISTRATION, DURATION, DURATION_UNITS, FREQ_COUNTER, FREQ_INTERVAL, FREQ_INTERVAL_UNIT, FREQ_DETAIL, ORDERING_DEPT, DOCTOR, NURSE, ORDER_STATUS, PROCESSING_DATE_TIME, BILLING_ATTR, ORDER_PRINT_INDICATOR, START_DATE_TIME, DRUG_BILLING_ATTR, ORDER_INSURANCE_TYPE, LOCAL_ORDER_NO, LOCAL_ORDER_TEXT, ORDER_ITEM_SET_ID, ORDER_CLASS, REPEAT_INDICATOR, IS_ANTIBIOTIC, MEASURE_UNITS, CP_NODE_CLASS_ID, EFFECT_FLAG,SPECIFICATION,MARK) " +
			"(select HOSPITAL_ID,"+cp_id+", "+cpNodeZKID+", ((select max(t.cp_node_order_id) from lcp_node_order_point t where t.cp_id = "+cp_id+" and t.cp_node_id ="+cpNodeZKID+")+1), CP_NODE_ORDER_ITEM_ID, CP_NODE_ORDER_TEXT, ORDER_NO, ORDER_TYPE, NEED_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, 0, "+CommonUtil.getOracleToDate()+", AUTO_ITEM, ORDER_TYPE_NAME, ORDER_KIND, MEASURE, FREQUENCY, WAY, DOSAGE, DOSAGE_UNITS, ADMINISTRATION, DURATION, DURATION_UNITS, FREQ_COUNTER, FREQ_INTERVAL, FREQ_INTERVAL_UNIT, FREQ_DETAIL, ORDERING_DEPT, DOCTOR, NURSE, ORDER_STATUS, PROCESSING_DATE_TIME, BILLING_ATTR, ORDER_PRINT_INDICATOR, START_DATE_TIME, DRUG_BILLING_ATTR, ORDER_INSURANCE_TYPE, LOCAL_ORDER_NO, LOCAL_ORDER_TEXT, ORDER_ITEM_SET_ID, ORDER_CLASS, REPEAT_INDICATOR, IS_ANTIBIOTIC, MEASURE_UNITS, CP_NODE_CLASS_ID, EFFECT_FLAG, SPECIFICATION, MARK from lcp_node_order_item loi where loi.cp_id="+cp_id+" and loi.cp_node_id="+cpNnodeId+" and loi.cp_node_order_id="+cpOrder2Point+") \n"
		    +"insert into lcp_node_order_point (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_ORDER_ID, CP_NODE_ORDER_TEXT, CONTINUE_ITEM, CONTINUE_CP_NODE_ID, CONTINUE_ORDER_ID, NEED_ITEM, AUTO_ITEM, ORDER_KIND, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE) " +
			"(select HOSPITAL_ID,"+cp_id+", "+cpNodeZKID+", ((select max(t.cp_node_order_id) from lcp_node_order_point t where t.cp_id = "+cp_id+" and t.cp_node_id ="+cpNodeZKID+")+1), CP_NODE_ORDER_TEXT, CONTINUE_ITEM, CONTINUE_CP_NODE_ID, "+continueOrderID+", NEED_ITEM, AUTO_ITEM, ORDER_KIND, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, 0, "+CommonUtil.getOracleToDate()+" from lcp_node_order_point lop where lop.cp_id="+cp_id+" and lop.cp_node_id="+cpNnodeId+" and lop.cp_node_order_id="+cpOrder2Point+")";
			
			int res = databaseClass.FunRunSqlByFile(insertSql.getBytes());			
			if(res > 0){
				response.getWriter().println("[{\"result\":\"OK\"}]");
			}else{
				response.getWriter().println("[{\"result\":\"fail\"}]");
			}
		}else{
			response.getWriter().println("[{\"result\":\"null\"}]");
		}
	}
	/**
	 * 为下拉框添加内容
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void makeOption(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		DatabaseClass db = LcpUtil.getDatabaseClass();

		String ops = request.getParameter("ops");
		String tableName = "";
		String code = "";// 编码 必须大写
		String name = "";// 名称 必须大写
		String temp = "";
		if ("orderjldw".equals(ops)) {
			tableName = "lcp_local_order_dosageunits";
			code = "CODE";
			name = "UNIT";
		} else if ("yanneiOrderType".equals(ops)) {
			tableName = "lcp_local_order_type";
			code = "ORDER_TYPE_CODE";
			name = "ORDER_TYPE_NAME";
			temp = "<option value='qxz' selected>必选项,请选择</option>";
		}else if ("orderPointName".equals(ops)) {
			tableName = "lcp_local_order_type";
			code = "ORDER_TYPE_CODE";
			name = "ORDER_TYPE_NAME";
			temp = "<option value='qxz' selected>必选项,请选择</option>";
		}
		String sql = " select * from  " + tableName;

		DataSetClass dataSet = db.FunGetDataSetBySQL(sql);

		if (dataSet.FunGetDataAsStringById(0, 0) != "") {
			for (int i = 0; i < dataSet.FunGetRowCount(); i++) {
				String key = dataSet.FunGetDataAsStringByColName(i, code);// 计量单位编码
				String value = dataSet.FunGetDataAsStringByColName(i, name);// 计量单位名称
				temp = temp + "<option value='" + key + "'>" + value + "</option>";
			}

		}
		response.getWriter().println(temp);
	}
	
	/**
	 * 复制路径
	 * @param request
	 * @param response
	 * @throws IOException
	 * @editor 林建喜
	 * @editContent 复制路径时向表LCP_NODE_ORDER_ITEM新增DEFAULT_ITEM和DRUG_ID字段数据
	 */
	private void copyCP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DatabaseClass db = LcpUtil.getDatabaseClass();
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String userName = (String) request.getSession().getAttribute("username");
		String deptcode = (String) request.getSession().getAttribute("deptcode");
		String cpID = request.getParameter("cp_id");	
		int id = Integer.valueOf(cpID);
		String sql = "";
		String sql1 = "select max(cp_id) as maxID from lcp_master ";
		int maxID = db.FunGetDataSetBySQL(sql1).FunGetDataAsNumberById(0, 0).intValue() + 1;
		String sql2="select cp_master_id from lcp_master where cp_id="+cpID;
		String cpMasterId=db.FunGetDataSetBySQL(sql2).FunGetDataAsStringById(0, 0);
		
		String cd="";
		String orderSql = "";
		int maxVersionId=1;
		String sql3 = "select CP_VERSION from LCP_MASTER k where k.cp_id="+id;
		maxVersionId = db.FunGetDataSetBySQL(sql3).FunGetDataAsNumberById(0, 0).intValue() + 1;
		String sql4 = "select trim(dept_code) dept_code from lcp_master where cp_id="+id;
		String dp=db.FunGetDataSetBySQL(sql4).FunGetDataAsStringById(0, 0);
		if(dp.equals(deptcode)){
			cd=cpMasterId +"-"+maxVersionId;
		}else{
			cd=maxID +"-"+1;
			maxVersionId=1;
		}
		
		
		if(id >  10037){
		orderSql = "insert into lcp_node_order_item (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_ORDER_ID, CP_NODE_ORDER_ITEM_ID, CP_NODE_ORDER_TEXT, ORDER_NO, ORDER_TYPE, NEED_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE, AUTO_ITEM, ORDER_TYPE_NAME, ORDER_KIND, MEASURE, FREQUENCY, WAY, DOSAGE, DOSAGE_UNITS, ADMINISTRATION, DURATION, DURATION_UNITS, FREQ_COUNTER, FREQ_INTERVAL, FREQ_INTERVAL_UNIT, FREQ_DETAIL, ORDERING_DEPT, DOCTOR, NURSE, ORDER_STATUS, PROCESSING_DATE_TIME, BILLING_ATTR, ORDER_PRINT_INDICATOR, START_DATE_TIME, DRUG_BILLING_ATTR, ORDER_INSURANCE_TYPE, LOCAL_ORDER_NO, LOCAL_ORDER_TEXT, ORDER_ITEM_SET_ID, ORDER_CLASS, REPEAT_INDICATOR, IS_ANTIBIOTIC, MEASURE_UNITS, CP_NODE_CLASS_ID, EFFECT_FLAG,SPECIFICATION,MARK,DEFAULT_ITEM,DRUG_ID) " +
		"(select HOSPITAL_ID,"+maxID+", CP_NODE_ID, CP_NODE_ORDER_ID, CP_NODE_ORDER_ITEM_ID, CP_NODE_ORDER_TEXT, ORDER_NO, ORDER_TYPE, NEED_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, 0, "+CommonUtil.getOracleToDate()+", AUTO_ITEM, ORDER_TYPE_NAME, ORDER_KIND, MEASURE, FREQUENCY, WAY, DOSAGE, DOSAGE_UNITS, ADMINISTRATION, DURATION, DURATION_UNITS, FREQ_COUNTER, FREQ_INTERVAL, FREQ_INTERVAL_UNIT, FREQ_DETAIL, ORDERING_DEPT, DOCTOR, NURSE, ORDER_STATUS, PROCESSING_DATE_TIME, BILLING_ATTR, ORDER_PRINT_INDICATOR, START_DATE_TIME, DRUG_BILLING_ATTR, ORDER_INSURANCE_TYPE, LOCAL_ORDER_NO, LOCAL_ORDER_TEXT, ORDER_ITEM_SET_ID, ORDER_CLASS, REPEAT_INDICATOR, IS_ANTIBIOTIC, MEASURE_UNITS, CP_NODE_CLASS_ID, EFFECT_FLAG,SPECIFICATION, MARK,DEFAULT_ITEM,DRUG_ID from lcp_node_order_item loi where loi.cp_id="+id+") \n"
		+"insert into lcp_node_order_point (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_ORDER_ID, CP_NODE_ORDER_TEXT, CONTINUE_ITEM, CONTINUE_CP_NODE_ID, CONTINUE_ORDER_ID, NEED_ITEM, AUTO_ITEM, ORDER_KIND, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, CP_NODE_CLASS_ID,SYS_IS_DEL, SYS_LAST_UPDATE) " +
			"(select HOSPITAL_ID,"+maxID+", CP_NODE_ID, CP_NODE_ORDER_ID, CP_NODE_ORDER_TEXT, CONTINUE_ITEM, CONTINUE_CP_NODE_ID, CONTINUE_ORDER_ID, NEED_ITEM, AUTO_ITEM, ORDER_KIND, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, CP_NODE_CLASS_ID,0, "+CommonUtil.getOracleToDate()+" from lcp_node_order_point lop where lop.cp_id="+id+") \n";
		
		}
		if (id > 10000) {
			sql = "insert into lcp_master(CP_CODE, HOSPITAL_ID, CP_ID, CP_NAME, CP_START_DATE, CP_MASTER_ID, CP_VERSION, CP_STATUS, CP_VERSION_DATE, CP_CREATE_DATE, CP_CREATE_ID, CP_CREATE_NAME, CP_STOP_DATE, CP_DAYS_MIN, CP_DAYS_MAX, CP_DAYS, CP_FEE, CP_START_USER, CP_STOP_USER, DEPT_CODE, DEPT_NAME, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE, INPUT_CODE_PY, INPUT_CODE_WB) " +
					"(select '"+cd+"',HOSPITAL_ID,"+maxID+",CP_NAME,null,CP_MASTER_ID, "+maxVersionId+", 1, "+CommonUtil.getOracleToDate()+", "+CommonUtil.getOracleToDate()+", CP_CREATE_ID, '"+userName+"', null, CP_DAYS_MIN, CP_DAYS_MAX, CP_DAYS, CP_FEE, null, null, '"+deptcode+"', (select dept.dept_name from lcp_local_dept dept where dept.dept_code='"+deptcode+"'), VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, "+CommonUtil.getOracleToDate()+", INPUT_CODE_PY, INPUT_CODE_WB from lcp_master l where l.cp_id="+id+") \n"
				 +"insert into lcp_master_antibiotics (HOSPITAL_ID, CP_ID, CP_ANTIBIOTICS, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_ANTIBIOTICS,VERIFY_DATE,VERIFY_CODE,VERIFY_NAME, 0, "+CommonUtil.getOracleToDate()+" from lcp_master_antibiotics lma where lma.cp_id="+id+") \n"
				+"insert into lcp_master_based (HOSPITAL_ID, CP_ID, CP_DIAGNOSIS_BASED, CP_TREATMENT, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_DIAGNOSIS_BASED,CP_TREATMENT,VERIFY_DATE,VERIFY_CODE, VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+" from lcp_master_based lm where lm.cp_id="+id+") \n"
				+"insert into lcp_master_discharge (HOSPITAL_ID, CP_ID, CP_DISCHARGE_ID, CP_DISCHARGE_NAME, DISPLAY_ORDER, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_DISCHARGE_ID,CP_DISCHARGE_NAME,DISPLAY_ORDER,VERIFY_DATE, VERIFY_CODE,VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+" from lcp_master_discharge lmd where lmd.cp_id="+id+") \n"
				+"insert into lcp_master_exclude (HOSPITAL_ID, CP_ID, CP_EXCLUDE_ID, CP_EXCLUDE_NAME, CP_EXCLUDE_VALUE, DISPLAY_ORDER, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_EXCLUDE_ID,CP_EXCLUDE_NAME,CP_EXCLUDE_VALUE,DISPLAY_ORDER,VERIFY_DATE,VERIFY_CODE,VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+" from lcp_master_exclude lme where lme.cp_id="+id+") \n"
				+"insert into lcp_master_income (HOSPITAL_ID, CP_ID, CP_INCOME_ID, CP_INCOME_TYPE, CP_INCOME_NAME, CP_INCOME_CODE, DISPLAY_ORDER, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_INCOME_ID,CP_INCOME_TYPE,CP_INCOME_NAME,CP_INCOME_CODE,DISPLAY_ORDER,VERIFY_DATE,VERIFY_CODE,VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+" from lcp_master_income lmi where lmi.cp_id="+id+") \n"
				+"insert into lcp_master_node (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_STD_NODE_ID, CP_NODE_PARENT_ID, CP_NODE_NAME, CP_NODE_DAYS_MIN, CP_NODE_DAYS_MAX, CP_NODE_DAYS, CP_NODE_TYPE, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_NODE_ID,CP_STD_NODE_ID,CP_NODE_PARENT_ID,CP_NODE_NAME,CP_NODE_DAYS_MIN,CP_NODE_DAYS_MAX,CP_NODE_DAYS,CP_NODE_TYPE,VERIFY_DATE,VERIFY_CODE,VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+" from lcp_master_node lmn where lmn.cp_id="+id+") \n"
				+"insert into lcp_master_variation (HOSPITAL_ID, CP_ID, CP_VARIATION_ID, CP_VARIATION_NAME, DISPLAY_ORDER, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_VARIATION_ID,CP_VARIATION_NAME,DISPLAY_ORDER,VERIFY_DATE,VERIFY_CODE,VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+" from lcp_master_variation lmv where lmv.cp_id="+id+") \n"
				+"insert into lcp_node_doctor (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_DOCTOR_ID, CP_STD_DOCTOR_ID, CP_NODE_DOCTOR_TEXT, NEED_ITEM, AUTO_ITEM, DOCTOR_NO, VERIFY_DATE,VERIFY_CODE,VERIFY_NAME,SYS_IS_DEL,SYS_LAST_UPDATE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_NODE_ID,CP_NODE_DOCTOR_ID, CP_STD_DOCTOR_ID, CP_NODE_DOCTOR_TEXT, NEED_ITEM, AUTO_ITEM, DOCTOR_NO, VERIFY_DATE,VERIFY_CODE,VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+" from lcp_node_doctor lnd where lnd.cp_id="+id+") \n"
				+"insert into lcp_node_doctor_item (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_DOCTOR_ID, CP_NODE_DOCTOR_ITEM_ID, CP_NODE_DOCTOR_TEXT, NEED_ITEM, AUTO_ITEM, DOCTOR_NO, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_NODE_ID,CP_NODE_DOCTOR_ID,CP_NODE_DOCTOR_ITEM_ID, CP_NODE_DOCTOR_TEXT, NEED_ITEM, AUTO_ITEM, DOCTOR_NO, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+" from lcp_node_doctor_item lndi where lndi.cp_id="+id+") \n"
				+"insert into lcp_node_doctor_point (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_DOCTOR_ID, CP_NODE_DOCTOR_TEXT, NEED_ITEM, AUTO_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE, IS_EXECUTE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_NODE_ID, CP_NODE_DOCTOR_ID, CP_NODE_DOCTOR_TEXT, NEED_ITEM, AUTO_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+",IS_EXECUTE from lcp_node_doctor_point lndp where lndp.cp_id="+id+") \n"
				+"insert into lcp_node_family_item (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_FAMILY_ID, CP_NODE_FAMILY_ITEM_ID, CP_NODE_FAMILY_TEXT, NEED_ITEM, AUTO_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_NODE_ID, CP_NODE_FAMILY_ID, CP_NODE_FAMILY_ITEM_ID, CP_NODE_FAMILY_TEXT, NEED_ITEM, AUTO_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+" from lcp_node_family_item lnfi where lnfi.cp_id="+id+") \n"
				+"insert into lcp_node_family_point (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_FAMILY_ID, CP_NODE_FAMILY_TEXT, NEED_ITEM, AUTO_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_NODE_ID, CP_NODE_FAMILY_ID, CP_NODE_FAMILY_TEXT, NEED_ITEM, AUTO_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+" from lcp_node_family_point lnfp where lnfp.cp_id="+id+") \n"
				+"insert into lcp_node_nurse (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_NURSE_ID, CP_STD_NURSE_ID, CP_NODE_NURSE_TEXT, NEED_ITEM, AUTO_ITEM, NURSE_NO,VERIFY_DATE,VERIFY_CODE,VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_NODE_ID, CP_NODE_NURSE_ID, CP_STD_NURSE_ID, CP_NODE_NURSE_TEXT, NEED_ITEM, AUTO_ITEM, NURSE_NO,VERIFY_DATE,VERIFY_CODE,VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+" from lcp_node_nurse ln where ln.cp_id="+id+") \n"
				+"insert into lcp_node_nurse_item (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_NURSE_ID, CP_NODE_NURSE_ITEM_ID, CP_NODE_NURSE_TEXT, NURSE_NO, NEED_ITEM, AUTO_ITEM, VERIFY_DATE,VERIFY_CODE,VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_NODE_ID, CP_NODE_NURSE_ID,CP_NODE_NURSE_ITEM_ID, CP_NODE_NURSE_TEXT, NURSE_NO, NEED_ITEM, AUTO_ITEM, VERIFY_DATE,VERIFY_CODE,VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+" from lcp_node_nurse_item lnt where lnt.cp_id="+id+") \n"
				+"insert into lcp_node_nurse_point (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_NURSE_ID, CP_NODE_NURSE_TEXT, NEED_ITEM, AUTO_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE, IS_EXECUTE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_NODE_ID, CP_NODE_NURSE_ID,CP_NODE_NURSE_TEXT, NEED_ITEM, AUTO_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+",IS_EXECUTE from lcp_node_nurse_point lnp where lnp.cp_id="+id+") \n"
				+"insert into lcp_node_order (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_ORDER_ID, CP_STD_ORDER_ID, CP_NODE_ORDER_TEXT, NEED_ITEM, AUTO_ITEM, ORDER_NO, ORDER_TYPE, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME,SYS_IS_DEL,SYS_LAST_UPDATE) " +
					"(select HOSPITAL_ID,"+maxID+",CP_NODE_ID, CP_NODE_ORDER_ID, CP_STD_ORDER_ID, CP_NODE_ORDER_TEXT, NEED_ITEM, AUTO_ITEM, ORDER_NO, ORDER_TYPE, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+" from lcp_node_order lno where lno.cp_id="+id+") \n"
				
				+ orderSql 
				
				+"insert into lcp_node_relate (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NEXT_NODE_ID, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE)" +
					"(select HOSPITAL_ID,"+maxID+",CP_NODE_ID, CP_NEXT_NODE_ID, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+" from lcp_node_relate lnr where lnr.cp_id="+id+") \n"
				+"insert into lcp_node_variation (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_VARIATION_ID, CP_STD_VARIATION_ID, CP_NODE_VARIATION_TEXT, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME,SYS_IS_DEL,SYS_LAST_UPDATE)" +
					"(select HOSPITAL_ID,"+maxID+",CP_NODE_ID, CP_NODE_VARIATION_ID, CP_STD_VARIATION_ID, CP_NODE_VARIATION_TEXT, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME,0, "+CommonUtil.getOracleToDate()+" from lcp_node_variation lnv where lnv.cp_id="+id+")";

			int res = db.FunRunSqlByFile(sql.getBytes());
			if (res > 0) {
				response.getWriter().println("[{\"result\":\"OK\",\"cpID\":\"" + maxID + "\"}]");
			} else {
				response.getWriter().println("[{\"result\":\"fail\"}]");
			}
		} else response.getWriter().println("[{\"result\":\"fail\"}]");
	}

	/**
	 * 创建路径时自动创建好准入-完成-变异退出  节点
	 * @param cpID
	 * @param hospatilID
	 */
	private void createNode(int cpID,int hospatilID){
		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		String nodeName1="准入";
		String nodeName2="完成";
		String nodeName3="变异退出";
				
		String sql = "insert into lcp_master_node (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_NAME, CP_NODE_DAYS_MAX, CP_NODE_TYPE,CP_NODE_PARENT_ID, SYS_IS_DEL, SYS_LAST_UPDATE)"
			+ "values ("
			+ hospatilID
			+ ","
			+ cpID
			+ ","
			+ 1
			+ ",'"
			+ nodeName1
			+ "',0,0,0, 0," + CommonUtil.getOracleToDate() + ") \n"
			+ "insert into lcp_master_node (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_NAME, CP_NODE_DAYS_MAX, CP_NODE_TYPE,CP_NODE_PARENT_ID, SYS_IS_DEL, SYS_LAST_UPDATE)"
				+ "values ("
				+ hospatilID
				+ ","
				+ cpID
				+ ","
				+ 2
				+ ",'"
				+ nodeName2
				+ "',0,2,0, 0," + CommonUtil.getOracleToDate() + ") \n"
			+ "insert into lcp_master_node (HOSPITAL_ID,CP_ID, CP_NODE_ID, CP_NODE_NAME, CP_NODE_DAYS_MAX, CP_NODE_TYPE,CP_NODE_PARENT_ID, SYS_IS_DEL, SYS_LAST_UPDATE)"
				+ "values ("
				+ hospatilID
				+ ","
				+ cpID
				+ ","
				+ 3
				+ ",'"
				+ nodeName3
				+ "',0,4,0, 0," + CommonUtil.getOracleToDate() + ")";
		db.FunRunSqlByFile(sql.getBytes());
	}
	
	/**
	 * 医嘱的上移和下移
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void upDownMove(HttpServletRequest request, HttpServletResponse response) throws IOException{
		DatabaseClass db = LcpUtil.getDatabaseClass();
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//获取页面传来的参数
		String ops=request.getParameter("ops");
		String cpID=request.getParameter("cp_id");
		String cpNodeID=request.getParameter("cp_node_id");
		String cpNodeOrderID=request.getParameter("cpOrder2Point");
		//获取当前行的序号
		String currentStep=request.getParameter("currentStep");
		//获取当前行的组号(组长)
		String setID = db.FunGetDataSetBySQL("select order_item_set_id from lcp_node_order_item WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND CP_NODE_ORDER_ITEM_ID="+currentStep+"").FunGetDataAsStringById(0, 0);
		
		if(setID.equals("")) setID="0";
		//临时变量	
		int cs=Integer.parseInt(currentStep)+2012;
			//上移
			String upStep=request.getParameter("upStep");
			//下移
			String downStep=request.getParameter("nextStep");
			String upSetID = db.FunGetDataSetBySQL("select order_item_set_id from lcp_node_order_item WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND CP_NODE_ORDER_ITEM_ID="+upStep+"").FunGetDataAsStringById(0, 0);
			String downSetID = db.FunGetDataSetBySQL("select order_item_set_id from lcp_node_order_item WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND CP_NODE_ORDER_ITEM_ID="+downStep+"").FunGetDataAsStringById(0, 0);
			if(upSetID.equals("")) upSetID="0";
			if(downSetID.equals("")) downSetID="0";
			String sql="";
			if(setID.equals("0")){
			if("upMove".equals(ops)){
				//判断当前行和上一行是否分组
				if(setID.equals("0")){
					if(upSetID.equals(upStep)){
						sql="update LCP_NODE_ORDER_ITEM set CP_NODE_ORDER_ITEM_ID="+cs+" WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND CP_NODE_ORDER_ITEM_ID="+currentStep+"\n"
						+"update LCP_NODE_ORDER_ITEM set CP_NODE_ORDER_ITEM_ID="+currentStep+" WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND CP_NODE_ORDER_ITEM_ID="+upStep+"\n"
						+"update LCP_NODE_ORDER_ITEM set CP_NODE_ORDER_ITEM_ID="+upStep+" WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND CP_NODE_ORDER_ITEM_ID="+cs+"\n"
						+"update LCP_NODE_ORDER_ITEM set order_item_set_id="+currentStep+" WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND order_item_set_id="+upSetID+"";
			
					}else{
						sql="update LCP_NODE_ORDER_ITEM set CP_NODE_ORDER_ITEM_ID="+cs+" WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND CP_NODE_ORDER_ITEM_ID="+currentStep+"\n"
						+"update LCP_NODE_ORDER_ITEM set CP_NODE_ORDER_ITEM_ID="+currentStep+" WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND CP_NODE_ORDER_ITEM_ID="+upStep+"\n"
						+"update LCP_NODE_ORDER_ITEM set CP_NODE_ORDER_ITEM_ID="+upStep+" WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND CP_NODE_ORDER_ITEM_ID="+cs+"\n";
					}
				}
			}else if("downMove".equals(ops)){
				//下移分过组的并且在二级菜单下没有重复的组号
				if(setID.equals("0")){
					if(downSetID.equals(downStep)){
						sql="update LCP_NODE_ORDER_ITEM set CP_NODE_ORDER_ITEM_ID="+cs+",sys_last_update="+CommonUtil.getOracleToDate()+" WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND CP_NODE_ORDER_ITEM_ID="+currentStep+"\n"
						  +"update LCP_NODE_ORDER_ITEM set CP_NODE_ORDER_ITEM_ID="+currentStep+",sys_last_update="+CommonUtil.getOracleToDate()+" WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND CP_NODE_ORDER_ITEM_ID="+downStep+"\n"
						  +"update LCP_NODE_ORDER_ITEM set CP_NODE_ORDER_ITEM_ID="+downStep+",sys_last_update="+CommonUtil.getOracleToDate()+" WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND CP_NODE_ORDER_ITEM_ID="+cs+"\n"
						  +"update LCP_NODE_ORDER_ITEM set order_item_set_id="+currentStep+",sys_last_update="+CommonUtil.getOracleToDate()+" WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND order_item_set_id="+downSetID+"";
			
					}else{
						sql="update LCP_NODE_ORDER_ITEM set CP_NODE_ORDER_ITEM_ID="+cs+",sys_last_update="+CommonUtil.getOracleToDate()+" WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND CP_NODE_ORDER_ITEM_ID="+currentStep+"\n"
						  +"update LCP_NODE_ORDER_ITEM set CP_NODE_ORDER_ITEM_ID="+currentStep+",sys_last_update="+CommonUtil.getOracleToDate()+" WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND CP_NODE_ORDER_ITEM_ID="+downStep+"\n"
						  +"update LCP_NODE_ORDER_ITEM set CP_NODE_ORDER_ITEM_ID="+downStep+",sys_last_update="+CommonUtil.getOracleToDate()+" WHERE CP_ID="+cpID+" AND CP_NODE_ID="+cpNodeID+" AND CP_NODE_ORDER_ID="+cpNodeOrderID+" AND CP_NODE_ORDER_ITEM_ID="+cs+"\n";
						
					}
				}
			}
			int res = db.FunRunSqlByFile(sql.getBytes());
			if(res > 0){
				NodeOrder order = new NodeOrder();
				String table = order.funGetItemTable(cpID, cpNodeID, cpNodeOrderID);
				response.getWriter().println("{\"result\":\"OK\",\"table\":\"" + table + "\"}");
			}else{
				response.getWriter().println("[{\"result\":\"fail\"}]");
			}
			}else{
				response.getWriter().println("[{\"result\":\"noMove\"}]");
			}
			
	}
	
	/**
	 * 复制三级菜单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void copySan(HttpServletRequest request, HttpServletResponse response) throws IOException{
		DatabaseClass db = LcpUtil.getDatabaseClass();
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String cpID=request.getParameter("cp_id");
		String cpNodeID=request.getParameter("cp_node_id");
		String cpNodeOrderID=request.getParameter("cpOrder2Point");
		String array=request.getParameter("array");
		
		String sql="";
		String[] str = array.split(",");
		String ss="";
		int res=0;
		for(String s:str){
			ss = s.substring(s.lastIndexOf("_")+1, s.length());
			sql="insert into lcp_node_order_item (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_ORDER_ID, CP_NODE_ORDER_ITEM_ID, CP_NODE_ORDER_TEXT, ORDER_NO, ORDER_TYPE, NEED_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE, AUTO_ITEM, ORDER_TYPE_NAME, ORDER_KIND, MEASURE, FREQUENCY, WAY, DOSAGE, DOSAGE_UNITS, ADMINISTRATION, DURATION, DURATION_UNITS, FREQ_COUNTER, FREQ_INTERVAL, FREQ_INTERVAL_UNIT, FREQ_DETAIL, ORDERING_DEPT, DOCTOR, NURSE, ORDER_STATUS, PROCESSING_DATE_TIME, BILLING_ATTR, ORDER_PRINT_INDICATOR, START_DATE_TIME, DRUG_BILLING_ATTR, ORDER_INSURANCE_TYPE, LOCAL_ORDER_NO, LOCAL_ORDER_TEXT, ORDER_ITEM_SET_ID, ORDER_CLASS, REPEAT_INDICATOR, IS_ANTIBIOTIC, MEASURE_UNITS, CP_NODE_CLASS_ID, EFFECT_FLAG,SPECIFICATION,MARK) " +
			"(select HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_ORDER_ID, ((select max(t.cp_node_order_item_id) from lcp_node_order_item t where t.cp_id="+cpID+" and t.cp_node_id="+cpNodeID+" and t.cp_node_order_id="+cpNodeOrderID+")+1), CP_NODE_ORDER_TEXT, ORDER_NO, ORDER_TYPE, NEED_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, 0, "+CommonUtil.getOracleToDate()+", AUTO_ITEM, ORDER_TYPE_NAME, ORDER_KIND, MEASURE, FREQUENCY, WAY, DOSAGE, DOSAGE_UNITS, ADMINISTRATION, DURATION, DURATION_UNITS, FREQ_COUNTER, FREQ_INTERVAL, FREQ_INTERVAL_UNIT, FREQ_DETAIL, ORDERING_DEPT, DOCTOR, NURSE, ORDER_STATUS, PROCESSING_DATE_TIME, BILLING_ATTR, ORDER_PRINT_INDICATOR, START_DATE_TIME, DRUG_BILLING_ATTR, ORDER_INSURANCE_TYPE, LOCAL_ORDER_NO, LOCAL_ORDER_TEXT, null, ORDER_CLASS, REPEAT_INDICATOR, IS_ANTIBIOTIC, MEASURE_UNITS, CP_NODE_CLASS_ID, EFFECT_FLAG,SPECIFICATION,MARK from lcp_node_order_item loi where loi.cp_id="+cpID+" and loi.cp_node_id="+cpNodeID+" and loi.cp_node_order_id="+cpNodeOrderID+" and loi.cp_node_order_item_id="+ss+")";
			res = db.FunRunSqlByFile(sql.getBytes());
		}
		if(res > 0){
			NodeOrder order = new NodeOrder();
			String table = order.funGetItemTable(cpID, cpNodeID, cpNodeOrderID);
			response.getWriter().println("{\"result\":\"OK\",\"table\":\"" + table + "\"}");
		}else{
			response.getWriter().println("[{\"result\":\"fail\"}]");
		}
	}

	/**
	 * 查询二级菜单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void selectSan(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String cp_id = request.getParameter("cp_id");
		String cp_node_id = request.getParameter("cp_node_id");
		String cpOrderOrderID=request.getParameter("cpOrder2Point");
		
		String sql="";
		String tableSan="";
		
		sql = "select cp_node_order_id,continue_order_id,cp_node_order_text from lcp_node_order_point where cp_id="+cp_id+" and cp_node_id="+cp_node_id+" and cp_node_order_id<>"+cpOrderOrderID+" ORDER BY CP_NODE_ID,  CONTINUE_ORDER_ID,CP_NODE_ORDER_ID";
		
		DatabaseClass databaseClass = LcpUtil.getDatabaseClass();
		DataSetClass dataSet = databaseClass.FunGetDataSetBySQL(sql);
		int rows = dataSet.FunGetRowCount();

		String orderID=null;
		for(int i=0;i<rows;i++){
			String cp_node_order_id = dataSet.FunGetDataAsStringByColName(i, "CP_NODE_ORDER_ID");
			String continue_order_id = dataSet.FunGetDataAsStringByColName(i, "CONTINUE_ORDER_ID");
			String cp_node_order_text = dataSet.FunGetDataAsStringByColName(i, "CP_NODE_ORDER_TEXT");
			String display="none";
			String color="#ffffff";
			
			if(orderID!=null&&orderID.equals(continue_order_id)){
				 display="";
				 color="#51b2f6";
			}
			if(continue_order_id.equals(cp_node_order_id)){
				tableSan=tableSan+"<tr style='cursor:pointer' height='20' name='trpan'  class='STYLE10' bgcolor='"+color+"' onmouseover='changeColor(this)'  onMouseOut='recoverColor(this)' "+
				"onclick='showzk("+continue_order_id+","+cp_node_id+","+cp_node_order_id+",this);'>"+
				"<td align='center' ><span onclick='showzk("+continue_order_id+","+cp_node_id+","+cp_node_order_id+",this);'>＋</span></td>"+
				"<td align='left' >"+cp_node_order_id+"</td>"+
				"<td align='left' >"+cp_node_order_text+"</td>"+
				"</tr>";
			}else if(!continue_order_id.equals(cp_node_order_id)){
				tableSan=tableSan+"<tr style='cursor:pointer;display:"+display+"' height='20'  name='pan"+continue_order_id+"' class='STYLE10' bgcolor='#DFE6E6' " +
					" onmouseover='changeColor(this)'  onMouseOut='recoverColor(this)' "+
					"  ondblclick='copyToSecond(this)'>"+
					"<td align='right' >&nbsp;&nbsp;├&nbsp;</td>"+
					"<td align='left' >"+cp_node_order_id+"</td>"+
					"<td align='left' >"+cp_node_order_text+"</td>"+
					"</tr>";
				}
		}
		response.getWriter().println(tableSan);
	}
	/**
	 * 复制医嘱到二级菜单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void copyToSecond(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String cp_id = request.getParameter("cp_id");
		String cp_node_id=request.getParameter("cp_node_id");
		String cpNnodeOrderId = request.getParameter("cpNodeOrderID");//选中的二级菜单
		String cpOrder2Point = request.getParameter("cpOrder2Point");//当前的二级菜单
		String selectSanZK=request.getParameter("selectSanZK");
		String ids = selectSanZK.substring(0, selectSanZK.length() - 1);
		
		DatabaseClass databaseClass = LcpUtil.getDatabaseClass();
		
		String sql="";
		String[] str = selectSanZK.split(",");
		String ss="";
		int res=0;
		for(String s:str){
			String selectSql="select max(cp_node_order_item_id) cp_node_order_item_id from lcp_node_order_item t where t.cp_node_order_id ="+cpNnodeOrderId+" and t.cp_id="+cp_id;
			String cp_node_order_item_id=databaseClass.FunGetDataSetBySQL(selectSql).FunGetDataAsStringById(0, 0);
			if("".equals(cp_node_order_item_id)){
				cp_node_order_item_id = "0";
			}
			ss = s.substring(s.lastIndexOf("_")+1, s.length());
			sql="insert into lcp_node_order_item (HOSPITAL_ID, CP_ID, CP_NODE_ID, CP_NODE_ORDER_ID, CP_NODE_ORDER_ITEM_ID, CP_NODE_ORDER_TEXT, ORDER_NO, ORDER_TYPE, NEED_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, SYS_IS_DEL, SYS_LAST_UPDATE, AUTO_ITEM, ORDER_TYPE_NAME, ORDER_KIND, MEASURE, FREQUENCY, WAY, DOSAGE, DOSAGE_UNITS, ADMINISTRATION, DURATION, DURATION_UNITS, FREQ_COUNTER, FREQ_INTERVAL, FREQ_INTERVAL_UNIT, FREQ_DETAIL, ORDERING_DEPT, DOCTOR, NURSE, ORDER_STATUS, PROCESSING_DATE_TIME, BILLING_ATTR, ORDER_PRINT_INDICATOR, START_DATE_TIME, DRUG_BILLING_ATTR, ORDER_INSURANCE_TYPE, LOCAL_ORDER_NO, LOCAL_ORDER_TEXT, ORDER_ITEM_SET_ID, ORDER_CLASS, REPEAT_INDICATOR, IS_ANTIBIOTIC, MEASURE_UNITS, CP_NODE_CLASS_ID, EFFECT_FLAG,SPECIFICATION,MARK) " +
			"(select HOSPITAL_ID,CP_ID, CP_NODE_ID,"+cpNnodeOrderId+" , ("+cp_node_order_item_id+"+1), CP_NODE_ORDER_TEXT, ORDER_NO, ORDER_TYPE, NEED_ITEM, VERIFY_DATE, VERIFY_CODE, VERIFY_NAME, 0, "+CommonUtil.getOracleToDate()+", AUTO_ITEM, ORDER_TYPE_NAME, ORDER_KIND, MEASURE, FREQUENCY, WAY, DOSAGE, DOSAGE_UNITS, ADMINISTRATION, DURATION, DURATION_UNITS, FREQ_COUNTER, FREQ_INTERVAL, FREQ_INTERVAL_UNIT, FREQ_DETAIL, ORDERING_DEPT, DOCTOR, NURSE, ORDER_STATUS, PROCESSING_DATE_TIME, BILLING_ATTR, ORDER_PRINT_INDICATOR, START_DATE_TIME, DRUG_BILLING_ATTR, ORDER_INSURANCE_TYPE, LOCAL_ORDER_NO, LOCAL_ORDER_TEXT, ORDER_ITEM_SET_ID, ORDER_CLASS, REPEAT_INDICATOR, IS_ANTIBIOTIC, MEASURE_UNITS, CP_NODE_CLASS_ID, EFFECT_FLAG, SPECIFICATION, MARK from lcp_node_order_item loi where loi.cp_id="+cp_id+" and loi.cp_node_id="+cp_node_id+" and loi.cp_node_order_id="+cpOrder2Point+" and cp_node_order_item_id="+ss+")";
			
			res = databaseClass.FunRunSqlByFile(sql.getBytes());
		}
		if(res > 0){
			NodeOrder order = new NodeOrder();
			order.funDelItem(ids);
			String table = order.funGetItemTable(cp_id, cp_node_id, cpOrder2Point);
			response.getWriter().println("{\"result\":\"OK\",\"table\":\"" + table + "\"}");
		}else{
			response.getWriter().println("[{\"result\":\"fail\"}]");
		}
	}
	
	/**
	 * 提交路径审核
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void cpSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		String cpID=request.getParameter("cp_id");
		String content = URLDecoder.decode(request.getParameter("content"),"UTF-8");
		String user = URLDecoder.decode(request.getParameter("user"),"UTF-8");
		String time = request.getParameter("time");
		String deptCode = (String) request.getSession().getAttribute("deptcode");
		
		String sql="";
		String deptName = db.FunGetDataSetBySQL("select dept_name from lcp_local_dept where trim(dept_code)='"+deptCode+"'").FunGetDataAsStringById(0, 0);
		sql="insert into LCP_LOCAL_CP_REQ (CP_ID, HOSPITAL_ID, REQ_NO, REQ_CONTENT, REQ_DEPT_NAME, REQ_DEPT_NO, REQ_DOCTOR, REQ_TIME)" +
			   " values ("+cpID+", "+HOSPITALID+", (select nvl(max(req_no), 0)+1 as req_no from lcp_local_cp_req), '"+content+"', '"+deptName+"', '"+deptCode+"','"+user+"', to_date('"+time+"', 'yyyy-mm-dd hh24:mi:ss')) \n"+
			   " update lcp_master set cp_status=2,sys_last_update="+CommonUtil.getOracleToDate()+" where cp_id="+cpID+"";
		int res = db.FunRunSqlByFile(sql.getBytes());
		
			if(res > 0){
				response.getWriter().println("[{\"result\":\"OK\"}]");
			}else{
				response.getWriter().println("[{\"result\":\"fail\"}]");
		}		
	}
	
	/**
	 * 管理员审核
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void verify(HttpServletRequest request, HttpServletResponse response) throws IOException{
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String cpID=request.getParameter("cp_id");
		String sql = "select req_content,req_doctor,req_time from lcp_local_cp_req where cp_id="+cpID+" and hospital_id="+HOSPITALID+" and req_time=(select max(req_time) from lcp_local_cp_req where cp_id="+cpID+" and hospital_id="+HOSPITALID+")";
		DataSetClass dataSet = db.FunGetDataSetBySQL(sql);
		String reqContent = dataSet.FunGetDataAsStringByColName(0, "REQ_CONTENT");
		String reqDoctor = dataSet.FunGetDataAsStringByColName(0, "REQ_DOCTOR");
		String reqTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dataSet.FunGetDataAsDateByColName(0, "REQ_TIME"));
		
		response.getWriter().println("[{\"content\":\""+reqContent+"\",\"user\":\""+reqDoctor+"\",\"time\":\""+reqTime+"\"}]");
	}
	
	/**
	 * 管理员书写退回原因
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void returnReason(HttpServletRequest request, HttpServletResponse response) throws IOException{
		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		String cpID=request.getParameter("cp_id");
		String returnReason = URLDecoder.decode(request.getParameter("returnReason"),"UTF-8");
		String returnUser = URLDecoder.decode(request.getParameter("returnUser"),"UTF-8");
		String returnTime = request.getParameter("returnTime");
		
		String updateSql = "update lcp_local_cp_req set req_back_reason='"+returnReason+"', " +
				           " req_back_person='"+returnUser+"', req_back_time=to_date('"+returnTime+"', 'yyyy-mm-dd hh24:mi:ss') " +
						   " where cp_id="+cpID+" and hospital_id="+HOSPITALID+" and req_back_reason is null and req_time=(select max(req_time) from lcp_local_cp_req where cp_id="+cpID+" and hospital_id="+HOSPITALID+") \n"+
						   "update lcp_master set cp_status=3,sys_last_update="+CommonUtil.getOracleToDate()+" where cp_id="+cpID+"";
		int updateRes = db.FunRunSqlByFile(updateSql.getBytes());
		if(updateRes > 0){
			response.getWriter().println("[{\"result\":\"OK\"}]");
		}else{
			response.getWriter().println("[{\"result\":\"fail\"}]");
		}
	}

	/**
	 * 管理员启用路径
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void cpEnable(HttpServletRequest request, HttpServletResponse response) throws IOException{
		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		String cpID=request.getParameter("cp_id");
		String userName=(String)request.getSession().getAttribute("username");
		String startBeforeSql="select cp_id from lcp_master where cp_master_id=(select cp_master_id from lcp_master where cp_id="+cpID+" and cp_master_id<>0) and cp_status=0";//在启用新版本前先停用旧版本
		if(db.FunGetDataSetBySQL(startBeforeSql).FunGetDataAsStringById(0, 0) == ""){
			String updateSql = "update lcp_master set cp_status=0,CP_START_DATE=" + CommonUtil.getOracleToDate() + ",CP_START_USER='"+userName+"',sys_last_update="+CommonUtil.getOracleToDate()+" where cp_id='"+cpID+"'";
			
			int updateRes = db.FunRunSqlByFile(updateSql.getBytes());
			if(updateRes > 0){
				response.getWriter().println("[{\"result\":\"OK\"}]");
			}else{
				response.getWriter().println("[{\"result\":\"fail\"}]");
			}
		}else{
			response.getWriter().println("[{\"result\":\"stopOld\"}]");
		}
	}

	/**
	 * 停用旧版路径
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void startAfter(HttpServletRequest request, HttpServletResponse response) throws IOException{
		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		String cpID=request.getParameter("cp_id");
		String userName=(String)request.getSession().getAttribute("username");
		String startBeforeSql="select cp_id from lcp_master where cp_master_id=(select cp_master_id from lcp_master where cp_id="+cpID+" and cp_master_id<>0) and cp_status=0";//在启用新版本前先停用旧版本
		if(db.FunGetDataSetBySQL(startBeforeSql).FunGetDataAsStringById(0, 0) != ""){
			DataSetClass dataSet=db.FunGetDataSetBySQL(startBeforeSql);
			String startID="";
			for(int i=0;i<dataSet.FunGetRowCount();i++){
				startID+="'"+dataSet.FunGetDataAsStringByColName(i, "CP_ID")+"'" + ",";
			}
			String updateSql = "update lcp_master set cp_status=1,CP_STOP_DATE=" + CommonUtil.getOracleToDate() + ",CP_STOP_USER='"+userName+"',sys_last_update="+CommonUtil.getOracleToDate()+" where cp_id in ("+startID.substring(0, startID.lastIndexOf(','))+") and hospital_id="+HOSPITALID+"\n"
			                 +"update lcp_master set cp_status=0,CP_START_DATE=" + CommonUtil.getOracleToDate() + ",CP_START_USER='"+userName+"',sys_last_update="+CommonUtil.getOracleToDate()+"  where cp_id='"+cpID+"' and hospital_id="+HOSPITALID+"" ;
			int updateRes = db.FunRunSqlByFile(updateSql.getBytes());
			if(updateRes > 0){
				response.getWriter().println("[{\"result\":\"OK\"}]");
			}else{
				response.getWriter().println("[{\"result\":\"fail\"}]");
			}
		}
	}
	
	/**
	 * 申请人查看原因
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void showReason(HttpServletRequest request, HttpServletResponse response) throws IOException{
		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		String cpID=request.getParameter("cp_id");
		String showSql = "select req_back_reason,REQ_BACK_PERSON,REQ_BACK_TIME from lcp_local_cp_req where cp_id="+cpID+" and hospital_id="+HOSPITALID+" and req_back_time = (select max(req_back_time) from lcp_local_cp_req where cp_id="+cpID+" and hospital_id="+HOSPITALID+")";
		DataSetClass dataSet=db.FunGetDataSetBySQL(showSql);
		String returnUser2 ="";
		String returnTime2 ="";
		
		String returnReason = dataSet.FunGetDataAsStringByColName(0, "REQ_BACK_REASON");
		if(returnReason == null || "".equals(returnReason)) {
			returnReason = "";
		}else{
			returnUser2 = dataSet.FunGetDataAsStringByColName(0, "REQ_BACK_PERSON");
			returnTime2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dataSet.FunGetDataAsDateByColName(0, "REQ_BACK_TIME"));
			
		}
		response.getWriter().println("[{\"reason\":\""+returnReason+"\",\"returnUser2\":\""+returnUser2+"\",\"returnTime2\":\""+returnTime2+"\"}]");
	}
	
	/**
	 * 申请人查看历史退回原因
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void showHistoryReason(HttpServletRequest request, HttpServletResponse response) throws IOException{
		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		String cpID=request.getParameter("cp_id");
		String showSql = "select req_back_reason,REQ_BACK_PERSON,REQ_BACK_TIME from lcp_local_cp_req where cp_id="+cpID+" and hospital_id="+HOSPITALID;
		DataSetClass dataSet=db.FunGetDataSetBySQL(showSql);
		String returnUser2 ="";
		String returnTime2 ="";
		String returnReason="";
		String result="";
		int count=dataSet.FunGetRowCount();
		for(int i=count-1;i>=0;i--){
			returnReason = dataSet.FunGetDataAsStringByColName(i, "REQ_BACK_REASON");
			returnUser2 = dataSet.FunGetDataAsStringByColName(i, "REQ_BACK_PERSON");
			returnTime2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dataSet.FunGetDataAsDateByColName(i, "REQ_BACK_TIME"));
				
			result += "<tr class='STYLE10' bgcolor='d3eaef' align='center' >"+
			  "<td align='left' colspan='4'><textarea cols='60' rows='6' readonly='readonly'>"+returnReason+"</textarea></td>"+
			  "</tr>"+
			  "<tr height='25' class='STYLE10' bgcolor='d3eaef' align='center'>"+
			  "<td align='left' colspan='4'><strong>退回人："+returnUser2+"</strong>　　<strong>退回时间："+returnTime2+"</strong></td>"+
			  "</tr>";
		}
		response.getWriter().println("[{\"historyReason\":\""+result+"\"}]");
	}
	
	/**
	 * 清空节点下的所有二级菜单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void clearOrderPoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cpID 			= request.getParameter("cp_id");
		String cpNodeID 		= request.getParameter("cp_node_id");
		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		String sql="delete from lcp_node_order_item t1 where t1.cp_node_order_id in " +
		" (select t.cp_node_order_id from lcp_node_order_point t " +
		" where t.cp_id = "+cpID+" and t.cp_node_id = "+cpNodeID+" and t.cp_node_order_id <> t.continue_order_id) " +
				" and t1.cp_id = "+cpID+" and t1.cp_node_id = "+cpNodeID+" \n"+
				" delete from lcp_node_order_point t where t.cp_id = "+cpID+" and t.cp_node_id = "+cpNodeID+" and t.cp_node_order_id <> t.continue_order_id ";

		
		int funs=db.FunRunSqlByFile(sql.getBytes());
		if (funs > 0) {
			NodeOrder order = new NodeOrder();
			String table = order.funGetPointTable(cpID, cpNodeID, null);
			response.getWriter().println("[{\"result\":\"OK\",\"table\":\""+table+"\"}]");
		} else {
			response.getWriter().println("[{\"result\":\"ERROR\"}]");
		}

	}
	
	/**
	 * 管理员隐藏路径
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void hiddenCP(HttpServletRequest request, HttpServletResponse response) throws IOException{
		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		String cpID=request.getParameter("cp_id");

		String updateSql = "update lcp_master set cp_status=4,sys_last_update="+CommonUtil.getOracleToDate()+" where cp_id='"+cpID+"'";
	
		int updateRes = db.FunRunSqlByFile(updateSql.getBytes());
		if(updateRes > 0){
			response.getWriter().println("[{\"result\":\"OK\"}]");
		}else{
			response.getWriter().println("[{\"result\":\"fail\"}]");
		}
	}
	/**
	 * 管理员恢复路径
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void recoverCP(HttpServletRequest request, HttpServletResponse response) throws IOException{
		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		String cpID=request.getParameter("cp_id");
		
		String updateSql = "update lcp_master set cp_status=1,sys_last_update="+CommonUtil.getOracleToDate()+" where cp_id='"+cpID+"'";

		int updateRes = db.FunRunSqlByFile(updateSql.getBytes());
		if(updateRes > 0){
			response.getWriter().println("[{\"result\":\"OK\"}]");
		}else{
			response.getWriter().println("[{\"result\":\"fail\"}]");
		}
	}
	
	/**
	 * 审核中的路径召回
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void cpZhaoHui(HttpServletRequest request, HttpServletResponse response) throws IOException{
		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		String cpID=request.getParameter("cp_id");
		
		String updateSql = "update lcp_master set cp_status=1,sys_last_update="+CommonUtil.getOracleToDate()+" where cp_id='"+cpID+"'";

		int updateRes = db.FunRunSqlByFile(updateSql.getBytes());
		if(updateRes > 0){
			response.getWriter().println("[{\"result\":\"OK\"}]");
		}else{
			response.getWriter().println("[{\"result\":\"fail\"}]");
		}
	}
	
	
	/**
	 * 创建人:潘状
	 * 创建日期:2011-10-26下午12:56:35
	 * 
	 * @param value
	 *            需要判断是否为空或者null
	 * @return
	 */
	private String isNull(String value) {
		value = value.trim();
		if ("".equals(value)) {
			return "null";
		} else if (value == null) {
			return "null";
		} else {
			return value.trim();
		}
	}

	private String getStrInSql(String str, boolean isNum) {
		if (str == null || "".equals(str)) return "null";
		else return (isNum) ? str : "'" + str + "'";
	}

}
