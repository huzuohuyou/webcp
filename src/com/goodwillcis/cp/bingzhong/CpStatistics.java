package com.goodwillcis.cp.bingzhong;

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

public class CpStatistics implements CustomDataSet {

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
		int fhrs=0;
		int nrrs=0;
		int wcrs=0;
		int byrs=0;
		double nrl=0.0;
		double wcl=0.0;
		double nr_avg_days=0.0;
		double wnr_avg_days=0.0;
		double nr_avg_fee=0.0;
		double wnr_avg_fee=0.0;
		double nr_all_days=0.0;
		double wnr_all_days=0.0;
		double nr_all_fee=0.0;
		double wnr_all_fee=0.0;
		System.out.println("conection is ok");
		// 填入数据
		metaData = new String[] { 
				"病种", 
				"符合人数", 
				"纳入人数", 
				"完成人数", 
				"变异退出人数", 
				"纳入率",
				"完成率",
				"纳入平均天数", 
				"未纳入平均天数", 
				"纳入平均费用", // 1
				"未纳入平均费用",
				"单路径纳入总天数",
				"单路径未纳入总天数",
				"单路径纳入总费用",
				"单路径未纳入总费用"
		};
		data = new ArrayList<Object[]>();
		try {
			statement = conn.createStatement();
			stmt = conn.prepareCall("{call PAK_CPSTATISTICS.CPSTATISTICS(?,?,?)}");
			stmt.registerOutParameter(1, OracleTypes.VARCHAR);
			stmt.registerOutParameter(2, OracleTypes.VARCHAR);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.setString(1, start_time);
			stmt.setString(2, end_time);
			stmt.execute();
			rs=(ResultSet) stmt.getObject(3);
			while(rs.next()){
				cpName=rs.getString("CP_NAME");
				fhrs=rs.getInt("FHRS");
				nrrs=rs.getInt("NRRS");
				wcrs=rs.getInt("WCRS");
				byrs=rs.getInt("BYRS");
    			if(fhrs==0){
    				nrl=0.0;
    			}else{
    				nrl=(double)nrrs/(double)fhrs;
    				if(nrl>1){
    					nrl=1.0;
    				}
    			}
    			if(nrrs==0){
    				wcl=0.0;
    			}else{
    				wcl=(double)wcrs/(double)nrrs;
    				if(wcl>1){
    					wcl=1.0;
    				}
    			}
				nr_avg_days=rs.getDouble("NR_AVG_DAYS");
				wnr_avg_days=rs.getDouble("WNR_AVG_DAYS");
				nr_avg_fee=rs.getDouble("NR_AVG_FEE");
				wnr_avg_fee=rs.getDouble("WNR_AVG_FEE");
				nr_all_days=rs.getDouble("NR_ALL_DAYS");
				wnr_all_days=rs.getDouble("WNR_ALL_DAYS");
				nr_all_fee=rs.getDouble("NR_ALL_FEE");
				wnr_all_fee=rs.getDouble("WNR_ALL_FEE");
				data.add(new Object[] { 
						cpName,
						fhrs,
						nrrs,
						wcrs,
						byrs,
						nrl,
						wcl,
						nr_avg_days,
						wnr_avg_days,
						nr_avg_fee,
						wnr_avg_fee,
						nr_all_days,
						wnr_all_days,
						nr_all_fee,
						wnr_all_fee
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
