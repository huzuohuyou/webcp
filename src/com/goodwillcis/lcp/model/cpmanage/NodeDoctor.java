/*----------------------------------------------------------------
//Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
//文件名：NodeDoctor.java
//文件功能描述：在页面显示诊疗工作第一层跟第二层信息
//修改人:潘状
//修改日期:2011/08/24
//修改内容:调整生成的表格格式
//----------------------------------------------------------------*/

package com.goodwillcis.lcp.model.cpmanage;

import java.io.UnsupportedEncodingException;

import com.goodwillcis.lcp.util.LcpUtil;

public class NodeDoctor {

	private final int	HOSPITALID	= LcpUtil.getHospitalID();

	public String funGetPointTable(String cp_id, String cp_node_id) {
		String sql = "SELECT * FROM LCP_NODE_DOCTOR_POINT T WHERE  CP_ID=" + cp_id
				+ " AND CP_NODE_ID=" + cp_node_id + " AND HOSPITAL_ID=" + HOSPITALID
				+ " order by CP_NODE_DOCTOR_ID";
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table = "";
		int row = dataSet.getRowNum();
		for (int i = 0; i < row; i++) {
			String cp_node_doctor_id = dataSet.funGetFieldByCol(i, "CP_NODE_DOCTOR_ID");
			String cp_node_doctor_text = dataSet.funGetFieldByCol(i, "CP_NODE_DOCTOR_TEXT");
			String need_item = dataSet.funGetFieldByCol(i, "NEED_ITEM");
			need_item = (need_item.equals("0")) ? "" : "&radic;";
			table = table
					+ "<tr height='20' id='"
					+ cp_id
					+ "_"
					+ cp_node_id
					+ "_"
					+ cp_node_doctor_id
					+ "' bgcolor='#FFFFFF'onmouseover='changeColor(this)'  onMouseOut='recoverColor(this)' onclick='showdoctoritem("
					+ cp_id + "," + cp_node_id + "," + cp_node_doctor_id
					+ ",this);' style='cursor:pointer'>" + "<td  align='center' class='STYLE10'>"
					+ cp_node_doctor_id + "</td>"
					+ "<td  align='left' class='STYLE10'><span  id='2_1_" + cp_id + "_"
					+ cp_node_id + "_" + cp_node_doctor_id
					+ "'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + cp_node_doctor_text
					+ "</span></td>" + "<td  align='center' class='STYLE10'>" + need_item + "</td>"
					+ "</tr>";
		}
		return table;
	}

	public String funGetItemTable(String cp_id, String cp_node_id, String cp_node_doctor_id) {
		String sql = "SELECT * FROM LCP_NODE_DOCTOR_ITEM T WHERE  CP_ID=" + cp_id
				+ " AND CP_NODE_ID=" + cp_node_id + " AND CP_NODE_DOCTOR_ID=" + cp_node_doctor_id
				+ " AND HOSPITAL_ID=" + HOSPITALID + " order by CP_NODE_DOCTOR_ITEM_ID";
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table = "";
		int row = dataSet.getRowNum();
		for (int i = 0; i < row; i++) {
			String cp_node_doctor_item_id = dataSet.funGetFieldByCol(i, "CP_NODE_DOCTOR_ITEM_ID");
			String cp_node_doctor_text = dataSet.funGetFieldByCol(i, "CP_NODE_DOCTOR_TEXT");
			String need_item = dataSet.funGetFieldByCol(i, "NEED_ITEM");
			String doctor_no = dataSet.funGetFieldByCol(i, "DOCTOR_NO");
			need_item = (need_item.equals("0")) ? "" : "&radic;";
			table = table
					+ "<tr class='STYLE10' align='left' height='20' id='"
					+ cp_id
					+ "_"
					+ cp_node_id
					+ "_"
					+ cp_node_doctor_id
					+ "_"
					+ cp_node_doctor_item_id
					+ "' bgcolor='#FFFFFF'onmouseover='changeColor(this)'  onMouseOut='recoverColor(this)'>"
					+ "<td  align='center' ><input type='checkbox' name='chekcbox_doctoritem' id='"
					+ cp_id + "_" + cp_node_id + "_" + cp_node_doctor_id + "_"
					+ cp_node_doctor_item_id + "'></td>" + "<td  ><span  id='2_1_" + cp_id + "_"
					+ cp_node_id + "_" + cp_node_doctor_id + "_" + cp_node_doctor_item_id
					+ "'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + cp_node_doctor_text
					+ "</span></td>" + "<td  align='center'>" + doctor_no + "</td>"
					+ "<td  align='center'>" + need_item + "</td>" + "</tr>";
		}
		return table;
	}

	public boolean funDelPoint(String cp_id, String cp_node_id, String cp_node_doctor_id) {
		String sql = "delete LCP_NODE_DOCTOR_POINT where CP_ID=" + cp_id + " AND CP_NODE_ID="
				+ cp_node_id + " AND CP_NODE_DOCTOR_ID=" + cp_node_doctor_id + " AND HOSPITAL_ID="
				+ HOSPITALID + "\r\n";
		sql = sql + "delete LCP_NODE_DOCTOR_ITEM where CP_ID=" + cp_id + " AND CP_NODE_ID="
				+ cp_node_id + " AND CP_NODE_DOCTOR_ID=" + cp_node_doctor_id + " AND HOSPITAL_ID="
				+ HOSPITALID + "\r\n";
		DataSet dataSet = new DataSet();
		int row = -1;
		try {
			row = dataSet.FunRunSqlByFile(sql.getBytes("GB2312"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (row > 0) return true;
		return false;
	}

	public boolean funDelItem(String ids) {
		String[] _ids = ids.split(",");
		String sql = "";
		String upSQL = "";// 如果point中无item,则point的is_execute置为0
		String count = "";// 统计LCP_NODE_DOCTOR_ITEM中有多少数据
		for (int i = 0; i < _ids.length; i++) {
			String[] id = _ids[i].split("_");
			sql = sql + "delete LCP_NODE_DOCTOR_ITEM where CP_ID=" + id[0] + " AND CP_NODE_ID="
					+ id[1] + " AND CP_NODE_DOCTOR_ID=" + id[2] + " AND CP_NODE_DOCTOR_ITEM_ID="
					+ id[3] + " AND HOSPITAL_ID=" + HOSPITALID + "\r\n";
			if (i == _ids.length - 1) {
				count = "select * from LCP_NODE_DOCTOR_ITEM where CP_ID=" + id[0]
						+ " and CP_NODE_ID=" + id[1] + " and CP_NODE_DOCTOR_ID=" + id[2];
				upSQL = "update LCP_NODE_DOCTOR_POINT set IS_EXECUTE=0,auto_item=1 where CP_ID="
						+ id[0] + " and CP_NODE_ID=" + id[1] + " and CP_NODE_DOCTOR_ID=" + id[2];
			}
		}
		System.out.println(sql);
		DataSet dataSet = new DataSet();

		int row = -1;
		try {
			row = dataSet.FunRunSqlByFile(sql.getBytes("GB2312"));
			dataSet.funSetDataSetBySql(count);
			int countrow = dataSet.getRowNum();
			if (countrow == 0) {
				countrow = dataSet.funRunSql(upSQL);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (row > 0) return true;
		return false;
	}
}
