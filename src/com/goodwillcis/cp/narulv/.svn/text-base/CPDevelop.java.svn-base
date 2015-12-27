/*----------------------------------------------------------------
//Copyright (C) 2015 北京嘉和美康信息技术有限公司版权所有。 
//文件名：CPDevelop.java
//文件功能描述：临床路径开展情况
//创建人：周伟彬
//创建日期：2015/6/20
//
//----------------------------------------------------------------*/
package com.goodwillcis.cp.narulv;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.goodwillcis.lcp.util.DBConnection;
import com.goodwillcis.lcp.util.SingleClass;

import bios.report.engine.api.CustomDataSet;

public class CPDevelop implements CustomDataSet {
	
	private String[] metaData;

	private List<Object[]> data;
	private String start_time = "";
	private String end_time = "";
	@Override
	public void applyParams(Object[] params) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		CallableStatement stmt = null;
		start_time = (String) params[0];
		end_time = (String) params[1] + " 23:59:59";
		DBConnection dbread = SingleClass.GetInstance();
		conn = dbread.getConnection();
		String execute_dept="";
		String cp_name="";
		String sssj=(String) params[0]+"~"+(String) params[1];
		int zrs=0;
		int zttcrs=0;
		int wcrs=0;
		System.out.println("conection is ok");
		// 填入数据
		metaData = new String[] { 
				"执行科室", 
				"临床路径名称",
				"临床路径实施时间",
				"实施临床路径总例数",
				"中途退出临床路径例数",
				"完成临床路径总例数"
		};
		data = new ArrayList<Object[]>();
		try {
			statement = conn.createStatement();
			stmt = conn.prepareCall("{call GET_DATA.CP_DEVELOP_PRO(?,?,?)}");
			stmt.registerOutParameter(1, OracleTypes.VARCHAR);
			stmt.registerOutParameter(2, OracleTypes.VARCHAR);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.setString(1, start_time);
			stmt.setString(2, end_time);
			stmt.execute();
			rs=(ResultSet) stmt.getObject(3);
			while(rs.next()){
				execute_dept=rs.getString("execute_dept");
				cp_name=rs.getString("cp_name");
				zrs=rs.getInt("zrs");
				zttcrs=rs.getInt("zttcrs");
				wcrs=rs.getInt("wcrs");
				data.add(new Object[] {	
						execute_dept,
						cp_name,
						sssj,
						zrs,
						zttcrs,
						wcrs
				});
			}
		} catch (SQLException e) {
			closeAll(stmt, rs, statement, conn);
			e.printStackTrace();
		}finally{
			closeAll(stmt, rs, statement, conn);
		}
		
	}

	@Override
	public String[] getMetaData() {
		// TODO Auto-generated method stub
		return metaData;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object[] getRowData(int rowIndex) {
		// TODO Auto-generated method stub
		return data.get(rowIndex);
	}
	public void closeAll(CallableStatement stmt,ResultSet rs,Statement statement,Connection conn){

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
}
