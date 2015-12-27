/*----------------------------------------------------------------
//Copyright (C) 2015 北京嘉和美康信息技术有限公司版权所有。 
//文件名：ConversionRate.java
//文件功能描述：临床路径分析指标的转归率
//创建人：周伟彬
//创建日期：2015/4/23
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

public class PrescriptionClass implements CustomDataSet {
	
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
		///人均用药品数
		double ljrjyyps=0.0;
		double fljrjyyps=0.0;
		double rjyypszj=0.0;
		///人均药费
		double ljrjyf=0.0;
		double fljrjyf=0.0;
		double rjyfzj=0.0;
		///使用抗菌药物的百分率
		double ljkjywbfl=0.0;
		double fljkjywbfl=0.0;
		double kjywbfl=0.0;
		///药占比
		double ljyzb=0.0;
		double fljyzb=0.0;
		double yzbzj=0.0;
		///住院患者人均使用抗菌药物品种数
		double ljrjkjyws=0.0;
		double fljrjkjyws=0.0;
		double rjkjywszj=0.0;
		///住院患者使用抗菌药物的百分率
		double ljsykjywbfl=0.0;
		double fljsykjywbfl=0.0;
		double sykjywbflzj=0.0;
		System.out.println("conection is ok");
		// 填入数据
		metaData = new String[] { 
				"路径人均用药品数", 
				"非路径人均用药品数",
				"人均用药品数增减",
				"路径人均药费",
				"非路径人均药费",
				"人均药费增减",
				"路径使用抗菌药物的百分率",
				"非路径使用抗菌药物的百分率",
				"使用抗菌药物的百分率增减",
				"路径药占比",
				"非路径药占比",
				"药占比增减",
				"路径住院患者人均使用抗菌药物数",
				"非路径住院患者人均使用抗菌药物数",
				"住院患者人均使用抗菌药物数增减",
				"路径住院患者使用抗菌药物的百分率",
				"非路径住院患者使用抗菌药物的百分率",
				"住院患者使用抗菌药物的百分率增减"
		};
		data = new ArrayList<Object[]>();
		try {
			statement = conn.createStatement();
			stmt = conn.prepareCall("{call CP_ANALYZE_INDEX.PRESCRIPTION_CLASS(?,?,?)}");
			stmt.registerOutParameter(1, OracleTypes.VARCHAR);
			stmt.registerOutParameter(2, OracleTypes.VARCHAR);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.setString(1, start_time);
			stmt.setString(2, end_time);
			stmt.execute();
			rs=(ResultSet) stmt.getObject(3);
			while(rs.next()){
				ljrjyyps=rs.getDouble("ljrjyyps");
				fljrjyyps=rs.getDouble("fljrjyyps");
				rjyypszj=ljrjyyps-fljrjyyps;
				
				ljrjyf=rs.getDouble("ljrjyf");
				fljrjyf=rs.getDouble("fljrjyf");
				rjyfzj=ljrjyf-fljrjyf;
				
				ljkjywbfl=rs.getDouble("ljkjywbfl");
				fljkjywbfl=rs.getDouble("fljkjywbfl");
				kjywbfl=ljkjywbfl-fljkjywbfl;
				
				ljyzb=rs.getDouble("ljyzb");
				fljyzb=rs.getDouble("fljyzb");
				yzbzj=ljyzb-fljyzb;
				
				ljrjkjyws=rs.getDouble("ljrjkjyws");
				fljrjkjyws=rs.getDouble("fljrjkjyws");
				rjkjywszj=ljrjkjyws-fljrjkjyws;
				
				ljsykjywbfl=rs.getDouble("ljsykjywbfl");
				fljsykjywbfl=rs.getDouble("fljsykjywbfl");
				sykjywbflzj=ljsykjywbfl-fljsykjywbfl;
				
				data.add(new Object[] { 
						ljrjyyps,
						fljrjyyps,
						rjyypszj,
						ljrjyf,
						fljrjyf,
						rjyfzj,
						ljkjywbfl,
						fljkjywbfl,
						kjywbfl,
						ljyzb,
						fljyzb,
						yzbzj,
						ljrjkjyws,
						fljrjkjyws,
						rjkjywszj,
						ljsykjywbfl,
						fljsykjywbfl,
						sykjywbflzj
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
