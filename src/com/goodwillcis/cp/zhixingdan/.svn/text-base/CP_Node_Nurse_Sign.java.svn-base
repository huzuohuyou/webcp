/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：CP_Node_Nurse_Sign.java
// 文件功能描述：节点护士签名
// 创建人：周伟彬
// 创建日期：2013/04/03
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

public class CP_Node_Nurse_Sign implements CustomDataSet {

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
		pairentId = (String) params[0];
		metaData = new String[] { "CP_NODE_ID", "CP_NODE_NURSE_NAME" };
		data = new ArrayList<Object[]>();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		CallableStatement stmt = null;
		DBConnection dbread =SingleClass.GetInstance();
		conn=dbread.getConnection();
	    System.out.println("conection is ok");
	    try {
			statement = conn.createStatement();
			stmt = conn.prepareCall("{call ZXD.CP_NODE_NURSE_SIGN(?,?)}");
			stmt.registerOutParameter(1, OracleTypes.VARCHAR);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.setString(1,pairentId);
			stmt.execute();
			rs=(ResultSet) stmt.getObject(2);
			while(rs.next()){
				int cp_node_id = rs.getInt(2);

			    String userName=rs.getString(3);
			    data.add(new Object[] { cp_node_id, userName });
			}
		} catch (SQLException e) {
			System.out.println("存储过程出错!");
			e.printStackTrace();
		}finally{
			  if(stmt!=null){
				  try {
					  stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt=null;
			  }
			  if(rs!=null){
				  try {
					  rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs=null;
			  }
			  if(statement!=null){
				  try {
					  statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				statement=null;
			  }
			  if(conn!=null){
				  try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				  conn=null;
			  }
		  }
		// String sFuns = "";
		// DatabaseClass db = LcpUtil.getDatabaseClass();
		// String sqldate =
		// "select t.cp_node_id,t.cp_node_nurse_id,t.cp_node_nurse_text,t.exe_state from lcp_patient_nurse_point t where t.patient_no = '"+pairentId+"'"
		// ;
		// //String sqldate =
		// "select t.cp_node_id,t.cp_node_nurse_id,t.cp_node_nurse_text from lcp_node_nurse_point t where t.cp_id = (select distinct t.cp_id from lcp_patient_node t where t.patient_no = '"+pairentId+"') order by t.cp_node_id,t.cp_node_nurse_id"
		// ;
		// // DataSetClass funs = db.FunGetDataSetBySQL(sqlfuns);
		// // int cfuns = funs.FunGetRowCount();
		// // for(int i = 0;i<cfuns;i++){
		// // int cp_node_id = funs.FunGetDataAsNumberByColName(i,
		// "CP_NODE_ID").intValue();
		// // int cp_node_nurse_id = funs.FunGetDataAsNumberByColName(i,
		// "CP_NODE_NURSE_ID").intValue();
		// // sFuns += cp_node_id+","+cp_node_nurse_id+"||";
		// // }
		//
		// DataSetClass ds = db.FunGetDataSetBySQL(sqldate);
		// int count = ds.FunGetRowCount();
		// for (int i = 0; i < count; i++) {
		// int cp_node_id = ds.FunGetDataAsNumberByColName(i,
		// "CP_NODE_ID").intValue();
		// int cp_node_nurse_id = ds.FunGetDataAsNumberByColName(i,
		// "CP_NODE_NURSE_ID").intValue();
		// String cp_node_nurse_text = ds.FunGetDataAsStringByColName(i,
		// "CP_NODE_NURSE_TEXT");
		// String exe_state = ds.FunGetDataAsStringByColName(i, "EXE_STATE");
		// if(exe_state.equals("1")){
		// cp_node_nurse_text = "■"+cp_node_nurse_text;
		// }else{
		// cp_node_nurse_text = "□"+cp_node_nurse_text;
		// }
		// data.add(new Object[] { cp_node_id,cp_node_nurse_text });
		// }

	}

}
