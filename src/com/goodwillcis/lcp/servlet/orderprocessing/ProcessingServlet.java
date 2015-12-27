/**---------------------------------------------------------------- 
 * // Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
 *  // 文件名： ProcessingServlet.java
 *  // 文件功能描述：处理下达医嘱的servlet类(记录日志,是否产生变异...)
 *  // 创建人：康榕元 
 *  // 创建日期：2011/08/25 
 *  // 修改人：周伟彬
 *  // 修改日期：2014/08/20 
 *  // ----------------------------------------------------------------*/

package com.goodwillcis.lcp.servlet.orderprocessing;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.File;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.DBHelper;
import com.goodwillcis.lcp.util.LcpUtil;
import com.goodwillcis.lcp.util.PropertiesUtil;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;

/**
 * Class description goes here.
 * 
 * @version 2014/08/14
 * @author 周伟彬
 */
public class ProcessingServlet extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private final int HOSPITAL_ID=LcpUtil.getHospitalID();
	String riqi = "";
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");//不允许有空字符
		request.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("gbk");
		String op = request.getParameter("op");
		if ("processing".equals(op)) {//医嘱下达
			try {
				processing(request, response);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else if ("stop".equals(op)) {//停止医嘱
			 try {
				stop(request, response);
			 } catch (JSONException e) {
			 e.printStackTrace();
			 }
		}else if("cpstate".equals(op)){//路径状态
			try {
				int i = cpstate(request, response);
				String state=Integer.toString(i);
				response.getWriter().write(state);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("switchcp".equals(op)){//调用临床路径执行单
			switchcp(request, response);
		}
		else if ("del".equals(op)) {//删除医嘱
		 try {
		 del(request, response);
		 } catch (JSONException e) {
		 e.printStackTrace();
		 }
		 }
		else if ("cancelStop".equals(op)) {//取消停止
			 try {
			 cancelStop(request, response);
			 } catch (JSONException e) {
			 e.printStackTrace();
			 }
			 }
	}
	/**
	 * 医嘱下达
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	private void processing(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			JSONException {
		riqi=sdf.format(new Date());
		

		FileWriter fileWriter = new FileWriter("e:\\log\\kang-rizhi/" + riqi
				+ "(下医嘱).txt", true);
		fileWriter.write("\r\n\r\n开始调用processing方法———————————————————————————————————————————————\r\n");

		DatabaseClass db = LcpUtil.getDatabaseClass();
		DataSetClass dataSet = new DataSetClass();
		DataSetClass dataSetva = new DataSetClass();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		int HospitalID = LcpUtil.getHospitalID();
		// 取出医嘱端传过来的参数
		String jsonStr = request.getParameter("order");
		String time = CommonUtil.getOracleToDate();
		JSONObject json = new JSONObject(jsonStr);
		String pa_id_local = json.getString("inpatientNo");
		String admissTimes = json.getString("admissTimes");
		String pa_id = pa_id_local + "_" + admissTimes;
		String doctorNo = json.getString("doctorNo");
		String doctorName = json.getString("doctorName");
		String cp_id = json.getString("cpId");
		String no_id = json.getString("cpNodeId");
		String enterTime = json.getString("enterTime");

		
		fileWriter.write("患者id：" + pa_id + "\r\n");
		fileWriter.write("his库的录入时间：" + enterTime + "\r\n");
		fileWriter.write("临床路径数据库的时间：" + time + "\r\n");
		fileWriter.write("传来的json串：" + jsonStr + "\r\n");
		JSONArray jArray = json.getJSONArray("advice");
		int count = jArray.length();
		fileWriter.write("传来的医嘱条数：" + count + "\r\n");

		String localtype = json.getString("type");// 检验检查其他
		String order_type_sql = "";
		String order_type = "";

		// 变异页面的参数，用来传给潘铁的串中
		String canshu = "pa_id=" + pa_id + "&cp_id=" + cp_id + "&no_id="
				+ no_id;
		fileWriter.flush();
		if (count > 0) {// 传来的医嘱要＞0，才往下执行

			String varia = "";// 变异表里已有的非新增的变异数目 point和items id值

			// ---------------------------------------------
			if ("inspect".equals(localtype)) {// 检验
				order_type_sql = "and t.order_type = 'C'";
				order_type = "C";
			} else if ("check".equals(localtype)) {// 检查
				order_type_sql = "and t.order_type = 'D'";
				order_type = "D";
			} else {// 其他
				order_type_sql = "and t.order_type not in ('C','D')";
				order_type = "Z";
			}
			try {
				conn = DBHelper.getConn();
				String sqllocal = "select act_order_no,order_code,order_name,drug_quan,frequ_code,supply_code,dose,dose_unit,mini_unit,parent_no from yz_act_order (nolock) where inpatient_no = ? and enter_time = ?";
				pst = conn.prepareStatement(sqllocal);
				pst.setObject(1, pa_id_local);
				pst.setObject(2, enterTime);
				rs = pst.executeQuery();
				int ik = 0;
				while (rs.next()) {
					ik++;
					@SuppressWarnings("rawtypes")
					Map map = new HashMap();
					String order_code = new String(rs.getString("order_code")
							.getBytes("iso-8859-1"), "gbk").trim();// 本地医嘱编码
					String order_name = new String(rs.getString("order_name")
							.getBytes("iso-8859-1"), "gbk").trim();// 本地医嘱名称
					Integer adf = rs.getInt("act_order_no");
					String act_order_no = new String(String.valueOf(adf).getBytes(
							"iso-8859-1"), "gbk").trim();// 本地医嘱主键
					String parent_no = new String(String.valueOf(rs.getInt("parent_no")).getBytes(
					"iso-8859-1"), "gbk").trim();// 本地医嘱主键
					String drug_quan = new String(rs.getString("drug_quan")
							.getBytes("iso-8859-1"), "gbk").trim();// 用量
					String frequ_code = new String(rs.getString("frequ_code")
							.getBytes("iso-8859-1"), "gbk").trim();// 频次
					String supply_code = rs.getString("supply_code") == null ? ""
							: new String(rs.getString("supply_code").getBytes(
									"iso-8859-1"), "gbk").trim();// 给药方式
					String dose = rs.getString("dose") == null ? null
							: new String(rs.getString("dose").getBytes(
									"iso-8859-1"), "gbk").trim();// 一次使用计量
					String dose_unit = rs.getString("dose_unit") == null ? ""
							: new String(rs.getString("dose_unit").getBytes(
									"iso-8859-1"), "gbk").trim();// 计量单位
					String mini_unit = rs.getString("mini_unit") == null ? ""
							: new String(rs.getString("mini_unit").getBytes(
									"iso-8859-1"), "gbk").trim();// 最小包装单位，领量单位
					String changqi = "1";

					if ("ONCE".equals(frequ_code))
						changqi = "0";
					map.put("order_code", order_code);
					map.put("order_name", order_name);
					map.put("act_order_no", act_order_no);
					map.put("drug_quan", drug_quan);
					map.put("frequ_code", frequ_code);
					map.put("supply_code", supply_code);
					map.put("dose", dose);
					map.put("dose_unit", dose_unit);
					map.put("changqi", changqi);
					map.put("mini_unit", mini_unit);
					map.put("parent_no", parent_no);
					list.add(map);
				}
				fileWriter.write("his库一共取出来" + ik + "条数据\r\n");

				if (ik > 0) {

					// -----------------------变异表已有的非新增变异----------------------
					String sqlva = "select t.cp_node_order_id,t.cp_node_order_item_id from lcp_patient_log_order_varia t where t.patient_no = '"
							+ pa_id
							+ "' and t.cp_id = "
							+ cp_id
							+ " and t.cp_node_id = "
							+ no_id
							+ " and t.cp_node_order_item_id is not null";
					dataSetva = db.FunGetDataSetBySQL(sqlva);

					int cou = dataSetva.FunGetRowCount();
					fileWriter.write("变异表里已有的非新增的变异数目：" + cou + "\r\n");

					for (int i = 0; i < cou; i++) {
						String cp_node_order_id = dataSetva
								.FunGetDataAsStringByColName(i,
										"CP_NODE_ORDER_ID");
						String cp_node_order_item_id = dataSetva
								.FunGetDataAsStringByColName(i,
										"CP_NODE_ORDER_ITEM_ID");
						varia += cp_node_order_id + "," + cp_node_order_item_id
								+ "||";
					}

					// 如果患者不在路径的
					if ("".equals(cp_id)) {
						// 只记录日志
						fileWriter.write("该患者没有纳入路径\r\n");
						String sql = "";

						for (int i = 0; i < jArray.length(); i++) {

							JSONObject data = (JSONObject) jArray.get(i);
							String stateItem = data.getString("stateItem");// 传来的本地医嘱编码
							for (int j = 0; j < list.size(); j++) {
								@SuppressWarnings("rawtypes")
								Map map = (Map) list.get(j);
								String order_code = (String) map
										.get("order_code");// 本地医嘱编码
								if (stateItem.equals(order_code)) {
									String order_name = (String) map
											.get("order_name");// 本地医嘱名称
									String act_order_no = (String) map
											.get("act_order_no");// 本地医嘱主键
									String parent_no = (String) map
									.get("parent_no");// 本地医嘱父医嘱号
									String drug_quan = (String) map
											.get("drug_quan");// 用量
									String frequ_code = (String) map
											.get("frequ_code");// 频次
									String supply_code = (String) map
											.get("supply_code");// 给药方式
									String dose = (String) map.get("dose");// 一次使用计量
									String dose_unit = (String) map
											.get("dose_unit");// 计量单位
									String measure_units = (String) map
											.get("mini_unit");// 最小包装单位，领量单位
									String changqi = (String) map
											.get("changqi");

									sql += "INSERT INTO lcp_patient_log_order t (t.hospital_id,"
											+ "t.patient_no,t.local_key,t.ORDER_CLASS,t.LOCAL_ORDER_NO,t.LOCAL_ORDER_TEXT,t.DOCTOR,t.repeat_indicator,"
											+ "t.measure,t.frequency,t.administration,t.dosage,"
											+ "t.dosage_units,t.measure_units,t.sys_is_del,t.sys_last_update,t.ORDER_NO,is_antibiotic,ENTER_DATE_TIME,parent_no) VALUES ("
											+ HospitalID
											+ ",'"
											+ pa_id
											+ "','"
											+ act_order_no
											+ "','"
											+ order_type
											+ "','"
											+ order_code
											+ "','"
											+ order_name
											+ "','"
											+ doctorName
											+ "',"
											+ changqi
											+ ",'"
											+ drug_quan
											+ "','"
											+ frequ_code
											+ "','"
											+ supply_code
											+ "',"
											+ dose
											+ ",'"
											+ dose_unit
											+ "','"
											+ measure_units
											+ "',"
											+ "0,"
											+ time
											+ ","
											+ "(select t.order_code from lcp_match_order t where t.local_code = '"
											+ order_code
											+ "')"
											+ ","
											+ "(select t.is_antibiotic from dcp_dict_order_item t where t.order_item_code = '"
											+ order_code
											+ "' and rownum <2)"   //查出第一条
											+ ","
											+ time
											+ ",'"
											+parent_no
											+ "'"
											+ ")\n";

									list.remove(j);
									break;
								}
							}

						}
						fileWriter.write("记录医嘱日志的sql语句：" + sql + "\r\n");

						int res = db.FunRunSqlByFile(sql.getBytes());
						fileWriter.write("写入医嘱日志表的返回值：" + res + "\r\n");
						response.getWriter().println("");

						// 在路径的患者
					} else {
						int sqlrow = 0;

						fileWriter.write("患者已纳入路径，id为：" + cp_id + "\r\n");
						// 记录日志
						String sqllog = "";

						// 为已下的医嘱打勾
						String sqlitem = "";

						// 必做 做了
						String bizuozuole = "";

						String bianyi = "";

						String delBianyi = "";

						String stateItem = "";// 本地医嘱编码
						String order_name = "";// 本地医嘱名称
						for (int i = 0; i < jArray.length(); i++) {
							JSONObject data = (JSONObject) jArray.get(i);
							stateItem = data.getString("stateItem");// 本地医嘱编码
							String CP_NODE_ORDER_ID = "".equals(data
									.getString("cpNodeOrderId")) ? null : data
									.getString("cpNodeOrderId");
							String CP_NODE_ORDER_ITEM_ID = "".equals(data
									.getString("cpNodeItemId")) ? null : data
									.getString("cpNodeItemId");
					//	    System.out.println("CP_NODE_ORDER_ITEM_ID=="+CP_NODE_ORDER_ITEM_ID);
							String needItem = data.getString("needItem");

//							System.out.println("----------------第" + (i + 1)
//									+ "条----------------------------------");
							for (int j = 0; j < list.size(); j++) {

//								System.out.println("比对第" + (j + 1)
//										+ "条--------");
								@SuppressWarnings("rawtypes")
								Map map = (Map) list.get(j);// list 她们的库
								String order_code = (String) map
										.get("order_code");// 本地医嘱编码
//								System.out.println("传来的编码：" + stateItem);
//								System.out.println("他的库里查的编码：" + order_code);
								if (stateItem.equals(order_code)) {
									sqlrow+=1;
									order_name = (String) map.get("order_name");// 本地医嘱名称
									String act_order_no = (String) map
											.get("act_order_no");// 本地医嘱主键
									String parent_no = (String) map
									.get("parent_no");// 本地医嘱父医嘱号
									String drug_quan = (String) map
											.get("drug_quan");// 用量
									String frequ_code = (String) map
											.get("frequ_code");// 频次
									String supply_code = (String) map
											.get("supply_code");// 给药方式
									String dose = (String) map.get("dose");// 一次使用计量
									String dose_unit = (String) map
											.get("dose_unit");// 计量单位
									String measure_units = (String) map
											.get("mini_unit");// 最小包装单位，领量单位
									String changqi = (String) map
											.get("changqi");
									sqllog += "INSERT INTO lcp_patient_log_order t (t.hospital_id,"
											+ "t.patient_no,t.local_key,t.ORDER_CLASS,t.cp_id,t.cp_node_id,t.cp_node_order_id,t.cp_node_order_item_id,t.LOCAL_ORDER_NO,t.LOCAL_ORDER_TEXT,t.DOCTOR,t.repeat_indicator,"
											+ "t.measure,t.frequency,t.administration,t.dosage,"
											+ "t.dosage_units,t.measure_units,t.sys_is_del,t.sys_last_update,t.ORDER_NO,is_antibiotic,ENTER_DATE_TIME,parent_no) VALUES ("
											+ HospitalID
											+ ",'"
											+ pa_id
											+ "','"
											+ act_order_no
											+ "','"
											+ order_type
											+ "',"
											+ cp_id
											+ ","
											+ no_id
											+ ","
											+ CP_NODE_ORDER_ID
											+ ","
											+ CP_NODE_ORDER_ITEM_ID
											+ ",'"
											+ order_code
											+ "','"
											+ order_name
											+ "','"
											+ doctorName
											+ "',"
											+ changqi
											+ ",'"
											+ drug_quan
											+ "','"
											+ frequ_code
											+ "','"
											+ supply_code
											+ "',"
											+ dose
											+ ",'"
											+ dose_unit
											+ "','"
											+ measure_units
											+ "',"
											+ "0,"
											+ time
											+ ","
											+ "(select t.order_code from lcp_match_order t where t.local_code = '"
											+ order_code
											+ "')"
											+ ","
											+ "(select t.is_antibiotic from dcp_dict_order_item t where t.order_item_code = '"
											+ order_code
											+ "' and rownum <2)"
											+ ","
											+ time
											+ ",'"
											+parent_no
											+ "'"
											+ ")\n";
									list.remove(j);
									break;
								}

							}
							if (CP_NODE_ORDER_ITEM_ID != null) {
								sqlrow+=2;
								// 打勾
								// 小项打勾
								sqlitem += "update lcp_patient_order_item t set t.user_id = '"
										+ doctorNo
										+ "',t.user_name = '"
										+ doctorName
										+ "',t.exe_date = "
										+ time
										+ ", t.exe_state = 1,t.sys_last_update = "
										+ time
										+ " where t.patient_no = '"
										+ pa_id
										+ "' and t.cp_id = "
										+ cp_id
										+ " and t.cp_node_id = "
										+ no_id
										+ " and t.cp_node_order_id = "
										+ CP_NODE_ORDER_ID
										+ " and t.cp_node_order_item_id = "
										+ CP_NODE_ORDER_ITEM_ID + "\n";
								// 大项打勾
								sqlitem += "update lcp_patient_order_point t set t.user_id = '"
										+ doctorNo
										+ "',t.user_name = '"
										+ doctorName
										+ "',t.exe_date = "
										+ time
										+ ", t.exe_state = 1,t.sys_last_update = "
										+ time
										+ " where t.patient_no = '"
										+ pa_id
										+ "' and t.cp_id = "
										+ cp_id
										+ " and t.cp_node_id = "
										+ no_id
										+ " and t.cp_node_order_id = "
										+ CP_NODE_ORDER_ID + "\n";
								if ("0".equals(needItem)) {
									// 选作，并且不在变异表里
									fileWriter.write("选做的医嘱不再弹出变异窗口！");
/*									if (varia.indexOf(CP_NODE_ORDER_ID + ","
											+ CP_NODE_ORDER_ITEM_ID + "||") < 0) {
										sqlrow+=1;
										System.out
												.println("-------xuanzuozuole-------");
										bianyi += "insert into lcp_patient_log_order_varia t values("
												+ HospitalID
												+ ",'"
												+ pa_id
												+ "',(select max (a)+1 hos from (select t.AUTO_ID as a from lcp_patient_log_order_varia t where t.patient_no = '"
												+ pa_id
												+ "' union select 0 a from dual)),'29001','',"
												+ cp_id
												+ ","
												+ no_id
												+ ","
												+ CP_NODE_ORDER_ID
												+ ","
												+ CP_NODE_ORDER_ITEM_ID
												+ ",'"
												+ doctorNo
												+ "','"
												+ doctorName
												+ "',to_date('"
												+ enterTime
												+ "','yyyy-mm-dd hh24:mi:ss'),0,"
												+ time
												+ ",'"
												+ stateItem
												+ "','" + order_name + "')\n";
									}*/
								} else {// 传来的数据中 必做的 他做了
									//然后删除已有的变异记录
									sqlrow+=1;
									delBianyi += "delete from lcp_patient_log_order_varia t where t.hospital_id ="
											+ HospitalID
											+ " and t.patient_no='"
											+ pa_id
											+ "' and  t.cp_id = "
											+ cp_id
											+ "  and t.cp_node_id="
											+ no_id
											+ " and t.cp_node_order_id = "
											+ CP_NODE_ORDER_ID
											+ " and t.cp_node_order_item_id = "
											+ CP_NODE_ORDER_ITEM_ID + "\n";
									bizuozuole += CP_NODE_ORDER_ID + ","
											+ CP_NODE_ORDER_ITEM_ID + "||";
								}
							} else {
								// 新增的
								sqlrow+=1;
								bianyi += "insert into lcp_patient_log_order_varia t values("
										+ HospitalID
										+ ",'"
										+ pa_id
										+ "',(select max (a)+1 hos from (select t.AUTO_ID as a from lcp_patient_log_order_varia t  where t.patient_no = '"
										+ pa_id
										+ "' union select 0 a from dual)),'29002','',"
										+ cp_id
										+ ","
										+ no_id
										+ ","
										+ CP_NODE_ORDER_ID
										+ ","
										+ CP_NODE_ORDER_ITEM_ID
										+ ",'"
										+ doctorNo
										+ "','"
										+ doctorName
										+ "',to_date('"
										+ enterTime
										+ "','yyyy-mm-dd hh24:mi:ss'),0,"
										+ time
										+ ",'"
										+ stateItem
										+ "','"
										+ order_name + "')\n";

							}
						}
						String sql = "select * from lcp_patient_order_item t where t.hospital_id = "
								+ HospitalID
								+ " and t.patient_no = '"
								+ pa_id
								+ "' and t.cp_id = "
								+ cp_id
								+ " and t.cp_node_id = "
								+ no_id
								+ " and t.need_item = 1 and (t.exe_state = 0 or t.exe_state is null) "
								+ order_type_sql;
						fileWriter.write("查item表里的必做项但是没做的sql语句：" + sql + "\r\n");
						dataSet = db.FunGetDataSetBySQL(sql);// item表里的必做项但是没做
						int result = dataSet.FunGetRowCount();
						fileWriter.write("item表里的必做项但是没做的条数：" + result + "\r\n");
						for (int i = 0; i < result; i++) {
							String CP_NODE_ORDER_ID = dataSet
									.FunGetDataAsStringByColName(i,
											"CP_NODE_ORDER_ID");
							String CP_NODE_ORDER_ITEM_ID = dataSet
									.FunGetDataAsStringByColName(i,
											"CP_NODE_ORDER_ITEM_ID");
							String LOCAL_ORDER_NO = dataSet
									.FunGetDataAsStringByColName(i, "ORDER_NO");
							if (bizuozuole.indexOf(CP_NODE_ORDER_ID + ","
									+ CP_NODE_ORDER_ITEM_ID + "||") < 0
									&& varia.indexOf(CP_NODE_ORDER_ID + ","
											+ CP_NODE_ORDER_ITEM_ID + "||") < 0) {
								String ORDER_TEXT = dataSet
										.FunGetDataAsStringByColName(i,
												"CP_NODE_ORDER_TEXT");
								//统计sql的总条数；
								sqlrow+=1;
								bianyi += "insert into lcp_patient_log_order_varia t values("
										+ HospitalID
										+ ",'"
										+ pa_id
										+ "',(select max (a)+1 hos from (select t.AUTO_ID as a from lcp_patient_log_order_varia t where t.patient_no = '"
										+ pa_id
										+ "'  union select 0 a from dual)),'29000','',"
										+ cp_id
										+ ","
										+ no_id
										+ ","
										+ CP_NODE_ORDER_ID
										+ ","
										+ CP_NODE_ORDER_ITEM_ID
										+ ",'"
										+ doctorNo
										+ "','"
										+ doctorName
										+ "',to_date('"
										+ enterTime
										+ "','yyyy-mm-dd hh24:mi:ss'),0,"
										+ time
										+ ",'"
										+ LOCAL_ORDER_NO
										+ "','"
										+ ORDER_TEXT + "')\n";
							}
						}

						fileWriter.write("记录医嘱日志的sql语句：" + sqllog + "\r\n");
						fileWriter.write("改变医嘱执行状态的sql语句：" + sqlitem + "\r\n");
						fileWriter.write("添加医嘱变异的sql语句：" + bianyi + "\r\n");
						fileWriter
								.write("删除已有医嘱变异的sql语句：" + delBianyi + "\r\n");

						String sqlcount = sqllog + sqlitem + bianyi + delBianyi;
						int res = db.FunRunSqlByFile(sqlcount.getBytes());
						fileWriter.write("sql总条数：" + sqlrow
								+ "\r\n");
						fileWriter.write("写入医嘱日志表，更改状态，添加变异，删除变异，的返回值为：" + res
								+ "\r\n");
						
					
					DataSetClass ds = db
							.FunGetDataSetBySQL("select t.variation_code from lcp_patient_log_order_varia t where t.patient_no = '"
									+ pa_id + "' and t.variation_content is null ");
					int var = ds.FunGetRowCount();
					String ip = LcpUtil.getConfigValue(PropertiesUtil
							.get(PropertiesUtil.ORDER_VAIRA));

					if (var > 0){
						fileWriter.write("变异医嘱有：" +var+ " 条");
						response.getWriter().println(
								ip + "LcpProject/service/varia.jsp?" + canshu);
						fileWriter.write("变异表有数据，发给潘铁地址为：" + ip + "LcpProject/service/varia.jsp?" + canshu+ "\r\n");
					}else{
						response.getWriter().println("");
					}
						
					
					
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				fileWriter.flush();
				fileWriter.close();
				DBHelper.closeAll(conn, pst, rs);
			}

		}
	}
	/**
	 * 电子医嘱调用医嘱停止
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws JSONException
	 */
	private void stop(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException,
			JSONException  {
		riqi=sdf.format(new Date());
		FileWriter fileWriter = new FileWriter("e:\\log\\kang-rizhi/" + riqi + "(停医嘱).txt", true);
		fileWriter
				.write("\r\n\r\n开始调用停止医嘱的stop方法———————————————————————————————————————————————\r\n");
		String jsonStr = request.getParameter("order");
		fileWriter.write("传来的json串：" + jsonStr + "\r\n");
		String time = CommonUtil.getOracleToDate();
		JSONArray array = new JSONArray(jsonStr);
		int cou = array.length();
		fileWriter.write("当前时间：" + time + "\r\n");
		fileWriter.write("传来的医嘱个数：" + cou + "\r\n");
		String sql = "";
		for (int i = 0; i < cou; i++) {
			JSONObject data = (JSONObject) array.get(i);
			String orderNo = data.getString("orderNo");
			String endTime = data.getString("endTime");
			sql += "update lcp_patient_log_order t set t.stop_date_time = to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss'),t.sys_last_update = "+time+" where t.local_key = '"+orderNo+"' or t.parent_no = '"+orderNo+"'\n"; 
		}
		DatabaseClass db = LcpUtil.getDatabaseClass();
		int res = db.FunRunSqlByFile(sql.getBytes());
		fileWriter.write("更新状态返回值：" + res + "\r\n");
		fileWriter.flush();
		fileWriter.close();
	}
	/**
	 * 电子医嘱调用取消停止医嘱
	 * @param cancelStop,orderno
	 * @return 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author 周伟彬 2014-08-20
	 */
    private void cancelStop(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException,
	JSONException  {
		riqi=sdf.format(new Date());
		FileWriter fileWriter = new FileWriter("e:\\log\\kang-rizhi/" + riqi + "(取消停止医嘱).txt", true);
		fileWriter
				.write("\r\n\r\n开始调用取消停止医嘱的del方法———————————————————————————————————————————————\r\n");
		String jsonStr = request.getParameter("order");
		fileWriter.write("传来的json串：" + jsonStr + "\r\n");
		String time = CommonUtil.getOracleToDate();
		JSONArray array = new JSONArray(jsonStr);
		int cou = array.length();
		fileWriter.write("当前时间：" + time + "\r\n");
		fileWriter.write("删除的医嘱个数：" + cou + "\r\n");
		String sql = "";
		for (int i = 0; i < cou; i++) {
			JSONObject data = (JSONObject) array.get(i);
			String orderNo = data.getString("orderNo");
			sql += "update lcp_patient_log_order t set t.stop_date_time = null,t.sys_last_update = "+time+" where t.local_key = '"+orderNo+"' or t.parent_no = '"+orderNo+"'\n"; 
		}
		DatabaseClass db = LcpUtil.getDatabaseClass();
		int res = db.FunRunSqlByFile(sql.getBytes());
		fileWriter.write("更新状态返回值：" + res + "\r\n");
		fileWriter.flush();
		fileWriter.close();
	}
	/**
	 * 电子医嘱删除医嘱
	 * @param del,orderno
	 * @return 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author 周伟彬 2014-08-20
	 */
    private void del(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException,
	JSONException  {
		riqi=sdf.format(new Date());
		FileWriter fileWriter = new FileWriter("e:\\log\\kang-rizhi/" + riqi + "(删除医嘱).txt", true);
		fileWriter
				.write("\r\n\r\n开始调用删除医嘱的del方法———————————————————————————————————————————————\r\n");
		String jsonStr = request.getParameter("order");
		//jsonStr = "{'data':[{'key':'12333333'},{'key':'12333333'}]}";
		fileWriter.write("传来的json串：" + jsonStr + "\r\n");
		String time = CommonUtil.getOracleToDate();
		JSONArray array = new JSONArray(jsonStr);
		int cou = array.length();
		fileWriter.write("当前时间：" + time + "\r\n");
		fileWriter.write("删除的医嘱个数：" + cou + "\r\n");
		String sql = "";
		for (int i = 0; i < cou; i++) {
			JSONObject data = (JSONObject) array.get(i);
			String orderNo = data.getString("orderNo");
			sql += "delete lcp_patient_log_order t  where t.local_key = '"+orderNo+"' or t.parent_no = '"+orderNo+"'\n"; 
		}
		DatabaseClass db = LcpUtil.getDatabaseClass();
		int res = db.FunRunSqlByFile(sql.getBytes());
		fileWriter.write("更新状态返回值：" + res + "\r\n");
		fileWriter.flush();
		fileWriter.close();
	}
	/**
	 * 路径状态
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private int cpstate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,JSONException{
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String jsonStr = request.getParameter("order");

		JSONObject json = new JSONObject(jsonStr);
		String pa_id_local = json.getString("inpatientNo");
		String admissTimes = json.getString("admissTimes");
		String patient_no = pa_id_local + "_" + admissTimes;
		String user_id = json.getString("doctorNo");
		String user_name = json.getString("doctorName");
		String cp_id = json.getString("cpId");
		String cp_node_id = json.getString("cpNodeId");
		request.getSession().setAttribute("patient_no", patient_no);
		request.getSession().setAttribute("cp_id", cp_id);
		request.getSession().setAttribute("cp_node_id", cp_node_id);
		request.getSession().setAttribute("user_id", user_id);
		request.getSession().setAttribute("user_name", user_name);
		FileWriter fileWriter = new FileWriter("e:\\log\\kang-rizhi/" + riqi + "(是否下达出院医嘱).txt", true);
		fileWriter.write("\r\n\r\n开始判断———————————————————————————————————————————————\r\n");
		int a=-1;
		String sql="select t.cp_state from lcp_patient_visit t where t.patient_no='"+patient_no+"'";
		DataSetClass dataSet=db.FunGetDataSetBySQL(sql);
		String cp_state=dataSet.FunGetDataAsStringById(0, 0);
		if(cp_state.equals("1")){
			fileWriter.write("该患者是路径内患者");
			String sql2="select cp_node_type from lcp_master_node where cp_id="+cp_id+" and cp_node_id="+cp_node_id+"";
			String type=db.FunGetDataSetBySQL(sql2).FunGetDataAsStringById(0, 0);
			if(type.equals("2")){
				fileWriter.write("当前节点是完成节点，可以下达出院医嘱！");
			}else{
				String cpNodeSql="select cp_node_type,cp_node_id from lcp_master_node where cp_id="+cp_id+" and cp_node_id=( "+
                " select t.cp_next_node_id from lcp_node_relate t where t.cp_id="+cp_id+" and t.cp_node_id="+cp_node_id+" " +
                " and t.cp_next_node_id<>(select cp_node_id from lcp_master_node where cp_id="+cp_id+" and cp_node_type=4))";
				String cp_node_type=db.FunGetDataSetBySQL(cpNodeSql).FunGetDataAsStringByColName(0, "CP_NODE_TYPE");
				String next_node_id=db.FunGetDataSetBySQL(cpNodeSql).FunGetDataAsStringByColName(0, "CP_NODE_ID");
				if(cp_node_type.equals("1")){
					fileWriter.write("当前节点的下一节点是正常节点，如果必须要下达出院医嘱，则提示其变异退出！");
					a=1;
				}
				if(cp_node_type.equals("2")){
					fileWriter.write("当前节点的下一节点是完成节点！");
					boolean b=checkSign(patient_no, cp_id, cp_node_id);
					if(b){
						fileWriter.write("提示进入完成节点！");
						//签名工作全部完成，直接修改为完成状态
						String updateSql="update lcp_patient_node set cp_node_end_time="+CommonUtil.getOracleToDate()+",cp_node_state=2,sys_last_update="+CommonUtil.getOracleToDate()+" where patient_no='"+patient_no+"' and cp_id="+cp_id+" and cp_node_id="+cp_node_id+" \n"+
										 "insert into lcp_patient_node (HOSPITAL_ID, PATIENT_NO, CP_ID, CP_NODE_ID, CP_STD_NODE_ID, CP_NODE_PARENT_ID, CP_NODE_NAME, CP_NODE_DAYS_MIN, CP_NODE_DAYS_MAX, CP_NODE_DAYS, CP_NODE_TYPE, CP_NODE_START_TIME, CP_NODE_END_TIME, USER_ID, USER_NAME, CP_NODE_STATE, SYS_IS_DEL, SYS_LAST_UPDATE) "+
										 "values("+HOSPITAL_ID+",'"+patient_no+"',"+cp_id+","+next_node_id+",0,0,(select cp_node_name from lcp_master_node where cp_id="+cp_id+" and cp_node_type=2),0,0,0,2,"+CommonUtil.getOracleToDate()+","+CommonUtil.getOracleToDate()+",'"+user_id+"','"+user_name+"',2,0,"+CommonUtil.getOracleToDate()+") \n"+
										 "update lcp_patient_visit set cp_state=11,sys_last_update="+CommonUtil.getOracleToDate()+" where patient_no='"+patient_no+"'";
//						System.out.println("updateSqllll==:"+updateSql);
						int res=db.FunRunSqlByFile(updateSql.getBytes());
						if(res>0){
							fileWriter.write("该患者路径状态修改成功！");
						}else{
							fileWriter.write("该患者路径状态修改失败！");
						}
					}else{
						fileWriter.write("提示其完成签名工作，完成后则进入完成节点或者变异退出节点！");
						a=2;
					}
				}
			}
		}else{
			fileWriter.write("该患者不在路径内");
			a=0;
		}
		return a;
	}	
	
	
	/**
	 * 查看此节点是否已经签名
	 * @param patient_no,cp_id,cp_node_id
	 * @return true 已经签名，false 未全部签名
	 */
	public boolean checkSign(String patient_no,String cp_id,String cp_node_id){
		//判断护理工作是否签名
		String sql="SELECT T.*  FROM LCP_PATIENT_NURSE_POINT T " +
		"WHERE T.PATIENT_NO = '"+patient_no+"'   AND T.HOSPITAL_ID = "+HOSPITAL_ID+"" +
				" AND T.SYS_IS_DEL=0 AND T.CP_ID="+cp_id+" AND T.USER_NAME IS NULL AND CP_NODE_ID="+cp_node_id+"";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int a=dataSet.getRowNum();
		//判断诊疗工作是否签名
		String sql2="SELECT T.*  FROM LCP_PATIENT_DOCTOR_POINT T " +
		"WHERE T.PATIENT_NO = '"+patient_no+"'   AND T.HOSPITAL_ID = "+HOSPITAL_ID+"" +
				" AND T.SYS_IS_DEL=0 AND T.CP_ID="+cp_id+" AND T.USER_NAME IS NULL AND CP_NODE_ID="+cp_node_id+"";
		DataSet dataSet2=new DataSet();
		dataSet2.funSetDataSetBySql(sql2);
		int b=dataSet2.getRowNum();
		//判断家属工作是否全部签名
		String sql3="SELECT T.*  FROM LCP_PATIENT_FAMILY_POINT T " +
		"WHERE T.PATIENT_NO = '"+patient_no+"'   AND T.HOSPITAL_ID = "+HOSPITAL_ID+"" +
				" AND T.SYS_IS_DEL=0 AND T.CP_ID="+cp_id+" AND T.USER_NAME IS NULL AND CP_NODE_ID="+cp_node_id+"";
		DataSet dataSet3=new DataSet();
		dataSet3.funSetDataSetBySql(sql3);
		int c=dataSet3.getRowNum();
		//判断变异记录是否签名
		String sql4="SELECT T.*  FROM lcp_patient_variation T " +
		"WHERE T.PATIENT_NO = '"+patient_no+"'   AND T.HOSPITAL_ID = "+HOSPITAL_ID+"" +
				" AND T.SYS_IS_DEL=0 AND T.CP_ID="+cp_id+" AND T.USER_NAME IS NULL AND CP_NODE_ID="+cp_node_id+"";
		DataSet dataSet4=new DataSet();
		dataSet4.funSetDataSetBySql(sql4);
		int d=dataSet4.getRowNum();
		if(a>0 || b>0 || c>0 || d>0){
			return false;
		}
		return true;
	}
	/**
	 * 从电子医嘱系统调用临床路径执行单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws JSONException
	 * @author 张昆 2012-11-07
	 */
	private void switchcp(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String inpatient_no=request.getParameter("patient_no");
		String doctorNo=request.getParameter("doctorNo");
		String doctorQX=request.getParameter("doctorQX");
		String user_id="";
		String user_name="";
		String patient_no="";
		String admissTimes="";
		String patientArea="";
		if(doctorNo.equals("20000")){
			user_id="HNQ";
			user_name="HNQ";
		}else{
			String sql="select t.user_id,t.user_name from users@jhemr t where t.account_status<>8 and t.pre_no='"+doctorNo+"'";
			DataSetClass dataSet=db.FunGetDataSetBySQL(sql);
			user_id=dataSet.FunGetDataAsStringByColName(0, "USER_ID");
			user_name=dataSet.FunGetDataAsStringByColName(0, "USER_NAME");
		}
		String sql2="select t.patient_no,(select dept_code from dept_dict@jhemr where dept_name=trim(t.dept_admission_to)) " +
	    			"dept_code from lcp_patient_visit t where t.cp_state=1 and t.inpatient_no='"+inpatient_no+"'";
		DataSetClass ds2=db.FunGetDataSetBySQL(sql2);
		patient_no=ds2.FunGetDataAsStringByColName(0, "PATIENT_NO");
		admissTimes=patient_no.substring(patient_no.indexOf("_"), patient_no.length()).replace("_", "");
		patientArea=ds2.FunGetDataAsStringByColName(0, "DEPT_CODE");
		response.sendRedirect("http://192.1.33.128:8080/LcpProject/route/execute.jsp?" +
				"patient_no="+patient_no+"&user_id="+user_id+"&user_name="+user_name+"&flag=1&patient_no_emr="+inpatient_no+"&" +
				"admissTimes="+admissTimes+"&doctorNo="+doctorNo+"&patientArea="+patientArea+"&doctorQX="+doctorQX+"");
	}
}
