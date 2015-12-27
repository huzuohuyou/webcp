/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：MyDataSetParam.java
// 文件功能描述：皕杰报表自定义数据集示例代码
// 创建人：潘状
// 创建日期：2011/09/03
// 
//----------------------------------------------------------------*/

package com.goodwillcis.cp.zhixingdan;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import bios.report.engine.api.CustomDataSet;

import com.goodwillcis.cp.util.LcpUtil;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.DBConnection;
import com.goodwillcis.lcp.util.SingleClass;

public class CP_Node_Point implements CustomDataSet {

	private String[] metaData;

	private List<Object[]> data;

	// 参数列表,必须与设计器端的参数数量匹配
	private String pairentId;

	@Override
	public String[] getMetaData() {

		return metaData;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object[] getRowData(int rowIndex) {
		return data.get(rowIndex);
	}

	@Override
	public void applyParams(Object[] params) {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		CallableStatement stmt = null;
		pairentId = (String) params[0];
		metaData = new String[] { "CP_NODE_ID", "USER_NAME" };
		data = new ArrayList<Object[]>();
		DBConnection dbread = SingleClass.GetInstance();
		conn = dbread.getConnection();
		System.out.println("conection is ok");
		try {
			statement = conn.createStatement();
			stmt = conn.prepareCall("{call ZXD.CP_NODE_POINT (?,?)}");
			stmt.registerOutParameter(1, OracleTypes.VARCHAR);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.setString(1, pairentId);
			stmt.execute();
			rs = (ResultSet) stmt.getObject(2);
			while (rs.next()) {
				int cp_node_id = rs.getInt(1);
				String user_name = rs.getString(2);
				data.add(new Object[] { cp_node_id, user_name });
			}
		} catch (SQLException e) {
			System.out.println("存储过程出错!");
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				statement = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}

}
