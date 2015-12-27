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
import java.util.Date;
import java.util.List;

import oracle.jdbc.OracleTypes;

import bios.report.engine.api.CustomDataSet;

import com.goodwillcis.cp.util.LcpUtil;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.DBConnection;
import com.goodwillcis.lcp.util.SingleClass;


public class CP_Title1 implements CustomDataSet {

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
	metaData = new String[] { "PATIENT_NO","PATIENT_NAME","DEPT_ADMISSION_TO","CP_NAME","PATIENT_SEX","ADMISSION_DATE","DISCHARGED_DATE","LOCAL_CODE","LOCAL_NAME","CP_DAYS"};
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
		stmt = conn.prepareCall("{call ZXD.CP_TITLE1(?,?)}");
		stmt.registerOutParameter(1, OracleTypes.VARCHAR);
		stmt.registerOutParameter(2, OracleTypes.CURSOR);
		stmt.setString(1,pairentId);
		stmt.execute();
		rs=(ResultSet) stmt.getObject(2);
		while(rs.next()){
			String patient_no = rs.getString(1);
			String []patient = patient_no.split("_");
			String patient_name = rs.getString(2);
			String dept_admission_to = rs.getString(3);
			String cp_name = rs.getString(4);
			String patient_sex = rs.getString(5);
			Date admission_date = rs.getDate(6);
			Date discharged_date = rs.getDate(7);
			String local_code = rs.getString(8);
			String local_name = rs.getString(9);
			int cp_day = rs.getInt(10);
			String cp_days = cp_day + "天";
			data.add(new Object[] { patient[0], patient_name,dept_admission_to,cp_name,patient_sex,admission_date,discharged_date,local_code,local_name,cp_days });
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
//	String sql1 = "select t.patient_no,t.patient_name,t.dept_admission_to,(select cp_name from lcp_master where cp_id=t.cp_id) cp_name from lcp_patient_visit t where t.patient_no = '"+pairentId+"'" ;
//	//String sql2 = "select cp_name from lcp_master where cp_id = (select distinct cp_id from lcp_patient_node t where t.patient_no = '"+pairentId+"')" ;
//	DataSetClass sd1 = db.FunGetDataSetBySQL(sql1);
//	//DataSetClass sd2 = db.FunGetDataSetBySQL(sql2);
//
//	String patient_no = sd1.FunGetDataAsStringByColName(0, "PATIENT_NO");
//	String []patient = patient_no.split("_");
//	String patient_name = sd1.FunGetDataAsStringByColName(0, "PATIENT_NAME");
//	String dept_admission_to = sd1.FunGetDataAsStringByColName(0, "DEPT_ADMISSION_TO");
//	String cp_name = sd1.FunGetDataAsStringByColName(0, "CP_NAME");
//	   // System.out.println(cp_node_name);
//	    data.add(new Object[] { patient[0], patient_name,dept_admission_to,cp_name });
	
    }

    
   
}
