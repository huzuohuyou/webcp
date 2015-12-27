package com.goodwillcis.cp.bingzhong;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.jdbc.OracleTypes;
import com.goodwillcis.lcp.util.DBConnection;
import com.goodwillcis.lcp.util.SingleClass;

import bios.report.engine.api.CustomDataSet;

public class BZOrderRate implements CustomDataSet {
	private String[] metaData;

	private List<Object[]> data;
	private String start_time = "";
	private String end_time = "";
	

	@Override
	public void applyParams(Object[] params) {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		CallableStatement stmt = null;
		start_time = (String) params[0];
		end_time = (String) params[1] + " 23:59:59";
		DBConnection dbread = SingleClass.GetInstance();
		conn = dbread.getConnection();
		String cpName="";
		int xdyzzs=0;
		int xdlcljyzs=0;
		double zxl=0.0;
		System.out.println("conection is ok");
		// 填入数据
		metaData = new String[] { 
				"病种", 
				"下达医嘱总数",
				"下达临床路径医嘱数",
				"医嘱执行率"
		};
		data = new ArrayList<Object[]>();
		try {
			statement = conn.createStatement();
			stmt = conn.prepareCall("{call PAK_CPSTATISTICS.BINGZHONG_ORDER_RATE_PRO(?,?,?)}");
			stmt.registerOutParameter(1, OracleTypes.VARCHAR);
			stmt.registerOutParameter(2, OracleTypes.VARCHAR);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.setString(1, start_time);
			stmt.setString(2, end_time);
			stmt.execute();
			rs=(ResultSet) stmt.getObject(3);
			while(rs.next()){
				cpName=rs.getString("CP_NAME");
				xdyzzs=rs.getInt("xdyzzs");
				xdlcljyzs=rs.getInt("xdlcljyzs");
				zxl=rs.getDouble("zxl");
				data.add(new Object[] { 
						cpName,
						xdyzzs,
						xdlcljyzs,
						zxl
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
