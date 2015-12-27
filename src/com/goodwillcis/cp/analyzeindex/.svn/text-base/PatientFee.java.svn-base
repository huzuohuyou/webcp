/*----------------------------------------------------------------
//Copyright (C) 2015 北京嘉和美康信息技术有限公司版权所有。 
//文件名：PatientFee.java
//文件功能描述：临床路径分析指标的患者费用
//创建人：周伟彬
//创建日期：2015/4/24
//
//----------------------------------------------------------------*/
package com.goodwillcis.cp.analyzeindex;

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

public class PatientFee implements CustomDataSet {
	
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
		double ljmzyrcfy=0.0;
		double ljmzyrcyf=0.0;
		double fljmzyrcfy=0.0;
		double fljmzyrcyf=0.0;
		double mzyrcfyzj=0.0;
		double mzyrcyfzj=0.0;
		System.out.println("conection is ok");
		// 填入数据
		metaData = new String[] { 
				"路径每住院人次费用", 
				"路径每住院人次药费",
				"非路径每住院人次费用",
				"非路径每住院人次药费",
				"每住院人次费用增减",
				"每住院人次药费增减"
		};
		data = new ArrayList<Object[]>();
		try {
			statement = conn.createStatement();
			stmt = conn.prepareCall("{call CP_ANALYZE_INDEX.PATIENT_FEE(?,?,?)}");
			stmt.registerOutParameter(1, OracleTypes.VARCHAR);
			stmt.registerOutParameter(2, OracleTypes.VARCHAR);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.setString(1, start_time);
			stmt.setString(2, end_time);
			stmt.execute();
			rs=(ResultSet) stmt.getObject(3);
			while(rs.next()){
				ljmzyrcfy=rs.getDouble("ljmzyrcfy");
				ljmzyrcyf=rs.getDouble("ljmzyrcyf");
				fljmzyrcfy=rs.getDouble("fljmzyrcfy");
				fljmzyrcyf=rs.getDouble("fljmzyrcyf");
				mzyrcfyzj=ljmzyrcfy-fljmzyrcfy;
				mzyrcyfzj=ljmzyrcyf-fljmzyrcyf;
				data.add(new Object[] { 
						ljmzyrcfy,
						ljmzyrcyf,
						fljmzyrcfy,
						fljmzyrcyf,
						mzyrcfyzj,
						mzyrcyfzj
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
