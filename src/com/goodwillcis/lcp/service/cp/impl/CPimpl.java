// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：CPimpl .java
// 文件功能描述：路径信息接口实现类
// 创建人：刘植鑫 
// 创建日期：2011/08/01
// 修改人:潘状
//修改日期:2011/08/26
//修改原因:不兼容火狐浏览器,返回的html格式有问题
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.cp.impl;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.service.cp.CP;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

public class CPimpl implements CP {

	@Override
	public String funGetTable(int hostipalID, int cp_id, boolean isUse) {

		String sql = "SELECT A.*,B.CP_STATUS CP_POWER FROM LCP_MASTER A,(SELECT A.HOSPITAL_ID, A.CP_ID, A.CP_STATUS   FROM LCP_HOSPITAL_VS_CP A "
				+ "UNION SELECT B.HOSPITAL_ID, B.CP_ID, 1 CP_STATUS   FROM LCP_MASTER B  WHERE B.CP_ID >= 10000) B "
				+ "WHERE A.HOSPITAL_ID=B.HOSPITAL_ID AND A.CP_ID=B.CP_ID AND A.CP_ID="
				+ cp_id + " AND A.HOSPITAL_ID=" + hostipalID + "";
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String cp_power = dataSet.funGetFieldByCol(0, "CP_POWER");
		if (cp_power.equals("1")) {
			cp_power = "可用";
		} else {
			cp_power = "不可用";
		}
		String cp_state = dataSet.funGetFieldByCol(0, "CP_STATUS");
		int cp_op_flag = 0;
		String cp_opera_method = "";
		if (cp_state.equals("0")) {
			cp_state = "启用";
			cp_opera_method = "停用";
			cp_op_flag = 1;
		} else {
			cp_opera_method = "启用";
			cp_state = "停用";
			cp_op_flag = 0;
		}
		String table = "<table bgcolor='#a8c7ce'cellpadding='0' cellspacing='1'   border='0' style='font-size: 16px;' align='center'>"
				+ "<tr  bgcolor='#ffffff'>"
				+ "<td  width='150' height='20' align='right'>路径名称：</td>"
				+ " <td  width='250'>"
				+ dataSet.funGetFieldByCol(0, "CP_NAME")
				+ "</td><td  width='150'  align='right'>路径编码：</td>"
				+ " <td   width='250'>"
				+ dataSet.funGetFieldByCol(0, "CP_CODE")
				+ "</td></tr><tr bgcolor='#ffffff'>"
				+ "<td align='right'>拼音简码：</td>"
				+ "<td>"
				+ dataSet.funGetFieldByCol(0, "INPUT_CODE_PY")
				+ "</td>"
				+ "<td align='right'>五笔简码：</td>"
				+ "<td>"
				+ dataSet.funGetFieldByCol(0, "INPUT_CODE_WB")
				+ "</td>"
				+ "</tr><tr bgcolor='#ffffff'>"
				+ "<td height='20' align='right'>路径版本：</td>"
				+ "<td>"
				+ dataSet.funGetFieldByCol(0, "CP_VERSION")
				+ "</td>"
				+ "<td   align='right'>平均费用：</td>"
				+ "<td>"
				+ dataSet.funGetFieldByCol(0, "CP_FEE")
				+ "</td>"
				+ "</tr><tr  bgcolor='#ffffff'>"
				+ "<td  height='20' align='right'>平均住院日：</td>"
				+ "<td>"
				+ dataSet.funGetFieldByCol(0, "CP_DAYS")
				+ "</td>"
				+ "<td    height='20' align='right'>最大住院日：</td>"
				+ "<td>"
				+ dataSet.funGetFieldByCol(0, "CP_DAYS_MAX")
				+ "</td>"
				+ "</tr><tr  bgcolor='#ffffff'>"
				+ "<td  height='20' align='right'>最少住院日：  </td>"
				+ "<td> "
				+ dataSet.funGetFieldByCol(0, "CP_DAYS_MIN")
				+ "</td>"
				+ "<td   align='right'>所属科室：</td>"
				+ "<td>"
				+ dataSet.funGetFieldByCol(0, "DEPT_NAME")
				+ "</td>"
				+ "</tr><tr  bgcolor='#ffffff'>"
				+ " <td    height='20' align='right'>使用权限：  </td>"
				+ "<td>"
				+ cp_power
				+ "</td>"
				+ " <td  align='right'>路径状态：</td>"
				+ "<td>" + cp_state + "</td>" + "</tr>" + "</table>";
		if (isUse) {
			table = table + "<table width='70%' align='center'>"
					+ "<tbody id='cp_state_op'>" + "<tr align='right'>"
					+ "<td><input type='button' value='" + cp_opera_method
					+ "路径' onclick='cp_state_op(" + cp_op_flag + ");'/></td>"
					+ "</tr></tbody></table>";
		}
		return table;
	}

	@Override
	public boolean funGetCpState(int hostipalID, int cp_id) {

		String sql = "SELECT T.CP_STATUS FROM LCP_MASTER T WHERE T.HOSPITAL_ID="
				+ hostipalID + " AND T.CP_ID=" + cp_id + "";
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String cp_status = dataSet.funGetFieldByCol(0, "CP_STATUS");
		if (cp_status.equals("1"))
			return false;
		return true;
	}

	@Override
	public boolean funStartOrEndCpRoute(int hostipalID, int cp_id,
			String isStart, String fieldName, String userName,
			String startOrEndFieldName) {
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String selectSql = "select cp_start_date,cp_stop_date from lcp_master where cp_id='"
				+ cp_id + "' and hospital_id=" + hostipalID;
		String sql = "";

		String time = CommonUtil.getOracleToDate();
		if (startOrEndFieldName.equals("CP_START_DATE")) {
			String cpstartTime = db.FunGetDataSetBySQL(selectSql)
					.FunGetDataAsStringByColName(0, "CP_START_DATE");
			String cpstopTime = db.FunGetDataSetBySQL(selectSql)
					.FunGetDataAsStringByColName(0, "CP_STOP_DATE");
			if (("".equals(cpstartTime) && "".equals(cpstopTime))
					|| (cpstartTime == null && cpstopTime == null)) {
				sql = "UPDATE LCP_MASTER A SET A.CP_STATUS=" + isStart
						+ " ,A.SYS_LAST_UPDATE=" + time + " ,A." + fieldName
						+ "='" + userName + "' ,A." + startOrEndFieldName + "="
						+ time + "   WHERE A.CP_ID = '" + cp_id
						+ "' AND A.HOSPITAL_ID=" + hostipalID + "";
			} else {
				//System.out.println("该路径已经有启用历史......");
				sql = "UPDATE LCP_MASTER A SET A.CP_STATUS=" + isStart
						+ " ,A.SYS_LAST_UPDATE=" + time + " ,A." + fieldName
						+ "='" + userName + "' WHERE A.CP_ID = '" + cp_id
						+ "' AND A.HOSPITAL_ID=" + hostipalID + "";
			}
		} else {
			sql = "UPDATE LCP_MASTER A SET A.CP_STATUS=" + isStart
					+ " ,A.SYS_LAST_UPDATE=" + time + " ,A." + fieldName + "='"
					+ userName + "' ,A." + startOrEndFieldName + "=" + time
					+ "   WHERE A.CP_ID = '" + cp_id + "' AND A.HOSPITAL_ID="
					+ hostipalID + "";
		}

		DataSet dataSet = new DataSet();
		int row = dataSet.funRunSql(sql);
		if (row > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean funCpIsExist(int hostipalID, int cp_id) {

		String sql = "SELECT COUNT(*)HANG  FROM LCP_MASTER A WHERE A.CP_ID = '"
				+ cp_id + "' AND A.HOSPITAL_ID=" + hostipalID + "";
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row = Integer.parseInt(dataSet.funGetFieldByCol(0, "HANG"));
		if (row > 0)
			return true;
		return false;
	}

	@Override
	public boolean funCpIsForbit(int hostipalID, int cp_id) {

		String sql = "SELECT T.CP_STATUS FROM LCP_HOSPITAL_VS_CP T WHERE T.CP_ID="
				+ cp_id + " AND T.HOSPITAL_ID=" + hostipalID + "";
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int cp_status = Integer.parseInt(dataSet.funGetFieldByCol(0,
				"CP_STATUS"));
		if (cp_status == 1)
			return true;
		return false;
	}

	@Override
	public String funGetAllCPViewTableAdmin(String sql, int start, int end) {
		String hid="";
		String input="";
		System.out.println("admin..................................");
		//System.out.println("admin=:" + sql);
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql, start, end);
		String table = "";
		int row = dataSet.getRowNum();
		for (int i = 0; i < row; i++) {
			String cp_id = dataSet.funGetFieldByColPan(i, "CP_ID");
			String dept_name = dataSet.funGetFieldByColPan(i, "DEPT_NAME");
			String cp_name = dataSet.funGetFieldByColPan(i, "CP_NAME");
			String cp_code = dataSet.funGetFieldByColPan(i, "CP_CODE");
			String cp_version = dataSet.funGetFieldByColPan(i, "CP_VERSION");
			String forbid_estate = dataSet.funGetFieldByColPan(i,
					"FORBID_ESTATE");
			String cp_status = dataSet.funGetFieldByColPan(i, "CP_STATUS");
			String cpMasterId = dataSet.funGetFieldByColPan(i, "CP_MASTER_ID");
			int cp_op_flag = 0;
			String cp_opera_method = "";
			if (cp_status.equals("0")) {
				cp_status = "启用";
				cp_opera_method = "停用";
				cp_op_flag = 1;
				input="<input type='button' value='"
					+ cp_opera_method + "' onclick='cp_state_op_view("
					+ cp_id + "," + cp_op_flag + ");'/>";
			} else if(cp_status.equals("1")){
				cp_opera_method = "启用";
				cp_status = "停用";
				cp_op_flag = 0;
				hid="<input type='button' value='隐藏' onclick='showHidden();'/>";
				input="<input type='button' value='"
					+ cp_opera_method + "' onclick='cp_state_op_view("
					+ cp_id + "," + cp_op_flag + ");'/> "+hid+"";
			}else if(cp_status.equals("2")){
				cp_opera_method = "审核";
				cp_status = "审核中";
				cp_op_flag = 2;
				input="<input type='button' value='"
					+ cp_opera_method + "' onclick='cp_state_op_view("
					+ cp_id + "," + cp_op_flag + ");'/> "+hid+"";
			}else if(cp_status.equals("3")){
				cp_opera_method = "审核";
				cp_status = "已退回";
				cp_op_flag = 3;
				hid="<input type='button' value='隐藏' onclick='showHidden();'/>";
				input=hid;
			}else if(cp_status.equals("4")){
				cp_opera_method = "恢复";
				cp_status = "已隐藏";
				cp_op_flag = 4;
				input="<input type='button' value='"
					+ cp_opera_method + "' onclick='cp_state_op_view("
					+ cp_id + "," + cp_op_flag + ");'/> "+hid+"";
			}
			boolean showOp = false;
			if (forbid_estate.equals("1")) {
				forbid_estate = "可用";
				showOp = true;
			} else if (forbid_estate.equals("0")) {
				forbid_estate = "禁用";
				cp_status = "--------";
				showOp = false;
			} else {
				if(Integer.parseInt(cpMasterId) > 0 && Integer.parseInt(cpMasterId)<=10000){
					forbid_estate = "中心可用";
				}else{
					forbid_estate = "自定义可用";
				}
				showOp = true;
			}

			table = table
					+ "<tr onClick='onclickColor(this)' ondblclick='ondblclickLoad(this)' class='STYLE10' bgcolor='#FFFFFF' height='20' id='tr_"
					+ cp_id + "' style='cursor:pointer'>"
					+ "<td   align='left'>&nbsp;&nbsp;&nbsp;" + cp_name
					+ "</td>" + "<td   align='center' >" + dept_name + "</td>"
					+ "<td   align='center' >" + cp_code + "</td>"
					+ "<td   align='left' >&nbsp;&nbsp;&nbsp;" + cp_version
					+ "</td>" + "<td   align='center' >" + forbid_estate
					+ "</td>" + "<td   align='center' ><span id=cp_state_'"
					+ cp_id + "'>" + cp_status + "</span></td>"
					+ "<td   align='center' >" + "<div id='cp_op_" + cp_id
					+ "'>";
			if (showOp)
					table = table + input;
				
			table = table + "</div></td>" +
					"</tr>";
		}
		return table;
	}

	@Override
	public String funGetAllCPViewTable(String sql, int start, int end) {
		System.out.println("other..............................");
		String hid="";
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql, start, end);
		String table = "";
		int row = dataSet.getRowNum();
		for (int i = 0; i < row; i++) {
			String cp_id = dataSet.funGetFieldByColPan(i, "CP_ID");
			String dept_name = dataSet.funGetFieldByColPan(i, "DEPT_NAME");
			String cp_name = dataSet.funGetFieldByColPan(i, "CP_NAME");
			String cp_code = dataSet.funGetFieldByColPan(i, "CP_CODE");
			String cp_version = dataSet.funGetFieldByColPan(i, "CP_VERSION");
			String forbid_estate = dataSet.funGetFieldByColPan(i,
					"FORBID_ESTATE");
			String cp_status = dataSet.funGetFieldByColPan(i, "CP_STATUS");
			String cpMasterId = dataSet.funGetFieldByColPan(i, "CP_MASTER_ID");
			int cp_op_flag = 0;
			String cp_opera_method = "";
			String input="";
			if (cp_status.equals("0")) {
				cp_status = "启用";
				//cp_opera_method = "";
				cp_op_flag = 0;
				input = "";
			} else if(cp_status.equals("2")){
				//cp_opera_method = "";
				cp_status = "审核中";
				cp_op_flag = 2;
				input = "<input type='button' value='召回' onclick='cpSubmit("
				+ cp_id + "," + cp_op_flag + ");'/>";
			}else if(cp_status.equals("1")){
				cp_opera_method = "提交";
				cp_status = "停用";
				cp_op_flag = 1;
				hid="<input type='button' value='隐藏' onclick='showHidden();'/>";
				input = "<input type='button' value='"+cp_opera_method+"' onclick='cpSubmit("
				+ cp_id + "," + cp_op_flag + ");'/> "+hid+"";
			}else if(cp_status.equals("3")){
				cp_opera_method = "提交";
				cp_status = "已退回";
				cp_op_flag = 3;
				hid="<input type='button' value='隐藏' onclick='showHidden();'/>";
				input = "<input type='button' value='"+cp_opera_method+"' onclick='cpSubmit("
				+ cp_id + "," + cp_op_flag + ");'/> "+hid+"";
			}else if(cp_status.equals("4")){
				cp_opera_method = "恢复";
				cp_status = "已隐藏";
				cp_op_flag = 4;
				input="<input type='button' value='"
					+ cp_opera_method + "' onclick='cp_state_op_view("
					+ cp_id + "," + cp_op_flag + ");'/> "+hid+"";
			}
			boolean showOp = false;
			if (forbid_estate.equals("1")) {
				forbid_estate = "可用";
				showOp = true;
			} else if (forbid_estate.equals("0")) {
				forbid_estate = "禁用";
				cp_status = "--------";
				showOp = false;
			} else {
				if(Integer.parseInt(cpMasterId) > 0 && Integer.parseInt(cpMasterId)<=10000){
					forbid_estate = "中心可用";
				}else{
					forbid_estate = "自定义可用";
				}
				showOp = true;
			}

//			table = table
//					+ "<tr onClick='onclickColor(this)' ondblclick='ondblclickLoad(this)' class='STYLE10' bgcolor='#FFFFFF' height='20' id='tr_"
//					+ cp_id + "' style='cursor:pointer'>"
//					+ "<td   align='left'>&nbsp;&nbsp;&nbsp;" + cp_name
//					+ "</td>" + "<td   align='center' >" + dept_name + "</td>"
//					+ "<td   align='center' >" + cp_code + "</td>"
//					+ "<td   align='left' >&nbsp;&nbsp;&nbsp;" + cp_version
//					+ "</td>" + "<td   align='center' >" + forbid_estate
//					+ "</td>" + "<td   align='center' ><span id=cp_state_'"
//					+ cp_id + "'>" + cp_status + "</span></td>"
//					+ "<td   align='center' ></tr>";
			
			table = table
			+ "<tr onClick='onclickColor(this)' ondblclick='ondblclickLoad(this)' class='STYLE10' bgcolor='#FFFFFF' height='20' id='tr_"
			+ cp_id + "' style='cursor:pointer'>"
			+ "<td   align='left'>&nbsp;&nbsp;&nbsp;" + cp_name
			+ "</td>" + "<td   align='center' >" + dept_name + "</td>"
			+ "<td   align='center' >" + cp_code + "</td>"
			+ "<td   align='left' >&nbsp;&nbsp;&nbsp;" + cp_version
			+ "</td>" + "<td   align='center' >" + forbid_estate
			+ "</td>" + "<td   align='center' ><span id=cp_state_'"
			+ cp_id + "'>" + cp_status + "</span></td>"
			+ "<td   align='center' >" + "<div id='cp_op_" + cp_id
			+ "'>";
	if (showOp)
		table = table + input;
	table = table + "</div></td></tr>";
		}
		return table;
	}

	@Override
	public String funGetCoreCPViewTable(String sql, int start, int end) {
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql, start, end);
		String table = "";
		int row = dataSet.getRowNum();
		for (int i = 0; i < row; i++) {
			String cp_id = dataSet.funGetFieldByColPan(i, "CP_ID");
			String cp_name = dataSet.funGetFieldByColPan(i, "CP_NAME");
			String cp_code = dataSet.funGetFieldByColPan(i, "CP_CODE");
			String cp_version = dataSet.funGetFieldByColPan(i, "CP_VERSION");
			String forbid_estate = dataSet.funGetFieldByColPan(i,
					"FORBID_ESTATE");
			String cp_status = dataSet.funGetFieldByColPan(i, "CP_STATUS");
			int cp_op_flag = 0;
			String cp_opera_method = "";
			if (cp_status.equals("0")) {
				cp_status = "启用";
				cp_opera_method = "停用";
				cp_op_flag = 1;
			} else {
				cp_opera_method = "启用";
				cp_status = "停用";
				cp_op_flag = 0;
			}
			boolean showOp = false;
			if (forbid_estate.equals("1")) {
				forbid_estate = "可用";
				showOp = true;
			} else if (forbid_estate.equals("0")) {
				forbid_estate = "禁用";
				cp_status = "--------";
				showOp = false;
			} else {
				forbid_estate = "自定义可用";
				showOp = true;
			}

			table = table
					+ "<tr onClick='onclickColor(this)' ondblclick='ondblclickLoad(this)' class='STYLE10' bgcolor='#FFFFFF' height='20' id='tr_"
					+ cp_id + "' style='cursor:pointer'>"
					+ "<td   align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ cp_name + "</td>" + "<td   align='center' >" + cp_code
					+ "</td>" + "<td   align='left' >&nbsp;&nbsp;&nbsp;"
					+ cp_version + "</td>" + "<td   align='center' >"
					+ forbid_estate + "</td>"
					+ "<td   align='center' ><span id=cp_state_'" + cp_id
					+ "'>" + cp_status + "</span></td>"
					+ "<td   align='center' >" + "<div id='cp_op_" + cp_id
					+ "'>";
			if (showOp)
				table = table + "<input type='button' value='"
						+ cp_opera_method + "' onclick='cp_state_op_view("
						+ cp_id + "," + cp_op_flag + ");'/>";
			table = table + "</div></td></tr>";
		}
		return table;
	}

	@Override
	public String funGetLocalCPViewTable(String sql, int start, int end) {
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql, start, end);
		String table = "";
		int row = dataSet.getRowNum();
		for (int i = 0; i < row; i++) {
			String cp_id = dataSet.funGetFieldByColPan(i, "CP_ID");
			String cp_name = dataSet.funGetFieldByColPan(i, "CP_NAME");
			String cp_code = dataSet.funGetFieldByColPan(i, "CP_CODE");
			String cp_version = dataSet.funGetFieldByColPan(i, "CP_VERSION");
			String forbid_estate = dataSet.funGetFieldByColPan(i,
					"FORBID_ESTATE");
			String cp_status = dataSet.funGetFieldByColPan(i, "CP_STATUS");
			int cp_op_flag = 0;
			String cp_opera_method = "";
			if (cp_status.equals("0")) {
				cp_status = "启用";
				cp_opera_method = "停用";
				cp_op_flag = 1;
			} else {
				cp_opera_method = "启用";
				cp_status = "停用";
				cp_op_flag = 0;
			}
			boolean showOp = false;
			if (forbid_estate.equals("1")) {
				forbid_estate = "可用";
				showOp = true;
			} else if (forbid_estate.equals("0")) {
				forbid_estate = "禁用";
				cp_status = "--------";
				showOp = false;
			} else {
				forbid_estate = "自定义可用";
				showOp = true;
			}

			table = table
					+ "<tr onClick='onclickColor(this)' ondblclick='ondblclickLoad(this)' class='STYLE10' bgcolor='#FFFFFF' height='20' id='tr_"
					+ cp_id + "' style='cursor:pointer'>"
					+ "<td   align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ cp_name + "</td>" + "<td   align='center' >" + cp_code
					+ "</td>" + "<td   align='left' >&nbsp;&nbsp;&nbsp;"
					+ cp_version + "</td>" + "<td   align='center' >"
					+ forbid_estate + "</td>"
					+ "<td   align='center' ><span id=cp_state_'" + cp_id
					+ "'>" + cp_status + "</span></td>"
					+ "<td   align='center' >" + "<div id='cp_op_" + cp_id
					+ "'>";
			if (showOp)
				table = table + "<input type='button' value='"
						+ cp_opera_method + "' onclick='cp_state_op_view("
						+ cp_id + "," + cp_op_flag + ");'/>";
			table = table + "</div></td></tr>";
		}
		return table;
	}

	@Override
	public int funGetCountCp(int hostipalID, String sql) {
		DataSet dataSet = new DataSet();
		sql = "SELECT COUNT(*)HANG FROM(" + sql + ") WHERE HOSPITAL_ID="
				+ hostipalID + "";
		dataSet.funSetDataSetBySql(sql);
		int row = Integer.parseInt(dataSet.funGetFieldByCol(0, "HANG"));

		return row;
	}

	@Override
	public String funGetStartOrEndTable(int hostipalID, int cp_id) {

		String sql = "SELECT * FROM (SELECT A.*,B.CP_STATUS FORBID_ESTATE FROM LCP_MASTER A,LCP_HOSPITAL_VS_CP B WHERE A.HOSPITAL_ID=B.HOSPITAL_ID(+) AND A.CP_ID=B.CP_ID(+))  WHERE CP_ID="
				+ cp_id + " AND HOSPITAL_ID=" + hostipalID + "";
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String cp_name = dataSet.funGetFieldByColPan(0, "CP_NAME");
		String cp_code = dataSet.funGetFieldByColPan(0, "CP_CODE");
		String cp_version = dataSet.funGetFieldByColPan(0, "CP_VERSION");
		String forbid_estate = dataSet.funGetFieldByColPan(0, "FORBID_ESTATE");
		String cp_status = dataSet.funGetFieldByColPan(0, "CP_STATUS");
		int cp_op_flag = 0;
		String cp_opera_method = "";
		boolean showOp = false;
		if (cp_status.equals("0")) {
			cp_status = "启用";
			cp_opera_method = "停用";
			cp_op_flag = 1;
		} else {
			cp_opera_method = "启用";
			cp_status = "停用";
			cp_op_flag = 0;
		}
		if (forbid_estate.equals("1")) {
			forbid_estate = "可用";
			showOp = true;
		} else if (forbid_estate.equals("0")) {
			forbid_estate = "禁用";
			cp_status = "--------";
			showOp = false;
		} else {
			forbid_estate = "自定义可用";
			showOp = true;
		}

		String table = ""
				+ "<td  align='left' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ cp_name + "</td>" + "<td  align='center' >" + cp_code
				+ "</td>" + "<td  align='left' >&nbsp;&nbsp;&nbsp;"
				+ cp_version + "</td>" + "<td  align='center' >"
				+ forbid_estate + "</td>"
				+ "<td  align='center' ><span id=cp_state_'" + cp_id + "' >"
				+ cp_status + "</span></td>"
				+ "<td height='20'  align='center' >" + "<div id='cp_op_"
				+ cp_id + "'>";

		if (showOp)
			table = table + "<input type='button' value='" + cp_opera_method
					+ "' onclick='cp_state_op_view(" + cp_id + "," + cp_op_flag
					+ ");'/>";
		table = table + "</div></td>";
		return table;
	}

	@Override
	public String funGetAllSql(String bm, String mc, String pym, String wbm,
			String dc, String ks) {
		String sql = "SELECT * FROM (SELECT A.*,B.CP_STATUS FORBID_ESTATE FROM LCP_MASTER A,LCP_HOSPITAL_VS_CP B WHERE A.HOSPITAL_ID=B.HOSPITAL_ID(+) AND A.CP_ID=B.CP_ID(+)) T WHERE 1=1 ";
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
		if (pym != "") {
			sql = sql + " AND (T.INPUT_CODE_PY like '%" + pym.toLowerCase()
					+ "%' or T.INPUT_CODE_PY like '%" + pym.toUpperCase()
					+ "%' or T.INPUT_CODE_PY like '%" + pym + "%' )";
		}
		if (wbm != "") {
			sql = sql + " AND (T.INPUT_CODE_WB like '%" + wbm.toLowerCase()
					+ "%' or T.INPUT_CODE_WB like '%" + wbm.toUpperCase()
					+ "%' or T.INPUT_CODE_WB like '%" + wbm + "%') ";
		}
		if (ks != "") {
			sql = sql + " AND TRIM(T.DEPT_NAME) like '%" + ks + "%'";
		}
		if (dc != "" && dc.equals("0")) {

		}
//		else if (dc != "" && !dc.equals("0")) {
//			sql = sql + " AND TRIM(T.DEPT_CODE)='"+dc+"' ";
//		}
//		sql = sql + " ORDER BY T.CP_STATUS,T.CP_ID ASC";

		//System.out.println(sql);
		return sql;
	}

	@Override
	public String funGetCoreSql(String bm, String mc, String py, String wb) {
		String sql = "SELECT * FROM (SELECT A.*,B.CP_STATUS FORBID_ESTATE FROM LCP_MASTER A,LCP_HOSPITAL_VS_CP B WHERE A.HOSPITAL_ID=B.HOSPITAL_ID AND A.CP_ID=B.CP_ID) T WHERE 1=1 ";
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
		sql = sql + "ORDER BY T.CP_ID ASC";

		//System.out.println(sql);
		return sql;
	}

	@Override
	public String funGetLocalSql(String bm, String mc, String py, String wb) {
		String sql = "SELECT * FROM LCP_MASTER T  WHERE CP_ID>10000 ";
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
		sql = sql + "ORDER BY T.CP_ID ASC";

		//System.out.println(sql);
		return sql;
	}

	@Override
	public boolean copyCP(String cpID) {
		// TODO Auto-generated method stub
		return false;
	}

}
