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


public class CP_Node implements CustomDataSet {

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
	metaData = new String[] { "CP_NODE_ID", "CP_NODE_NAME" };
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
		stmt = conn.prepareCall("{call ZXD.CP_NODE(?,?)}");
		stmt.registerOutParameter(1, OracleTypes.VARCHAR);
		stmt.registerOutParameter(2, OracleTypes.CURSOR);
		stmt.setString(1,pairentId);
		stmt.execute();
		rs=(ResultSet) stmt.getObject(2);
		while(rs.next()){
		    int cp_node_id = rs.getInt(1);
		    String cp_node_name = rs.getString(2);
		    data.add(new Object[] { cp_node_id, cp_node_name });
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
//	DatabaseClass db = LcpUtil.getDatabaseClass();
//	//String sql = "select t.cp_node_id,t.cp_node_name from lcp_master_node t where t.cp_node_type = 1 and t.cp_id = (select distinct t.cp_id from lcp_patient_node t where t.patient_no = '"+pairentId+"') order by  t.cp_node_id" ;
//	String sql="select t.cp_node_id,t.cp_node_name from lcp_patient_node t where t.patient_no='"+pairentId+"' and t.cp_node_type = 1 order by  t.cp_node_id";
//	DataSetClass sd = db.FunGetDataSetBySQL(sql);
//	int count = sd.FunGetRowCount();
//	for (int i = 0; i < count; i++) {
//	    int cp_node_id = sd.FunGetDataAsNumberByColName(i, "CP_NODE_ID").intValue();
//	    String cp_node_name = sd.FunGetDataAsStringByColName(i, "CP_NODE_NAME");
//	   // System.out.println(cp_node_name);
//	    data.add(new Object[] { cp_node_id, cp_node_name });
//	}
	
    }

    
   
}
