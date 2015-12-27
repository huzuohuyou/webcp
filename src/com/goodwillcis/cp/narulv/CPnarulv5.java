/*----------------------------------------------------------------
//Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
//文件名：CPnarulv5.java
//文件功能描述：按入院科室统计servlet
//创建人：周伟彬
//创建日期：2013/04/03
//
//----------------------------------------------------------------*/

package com.goodwillcis.cp.narulv;
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
import bios.report.engine.api.CustomDataSet;

import com.goodwillcis.cp.util.LcpUtil;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.DBConnection;


public class CPnarulv5 implements CustomDataSet {

 private String[] metaData;

 private List<Object[]> data;
 
 private String start_time = "";
 
 private String end_time = "";

 // 参数列表,必须与设计器端的参数数量匹配

 private int hospital_id = 0;

 private String deptcodevalue="";
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
		hospital_id = (Integer) params[0];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		start_time = (String) params[1];
		end_time = (String) params[2];
		deptcodevalue = (String) params[3];
		//System.out.println("deptcodevalue==="+deptcodevalue);
/*		if (start_time == null||"".equals(start_time)) {
			start_time = "2011-10-01";
		}*/
		if (end_time == null||"".equals(end_time)) {
			end_time = sdf.format(new Date());
			start_time=end_time.substring(0,8)+"01";
		}
		end_time = end_time + " 23:59:59";
		metaData = new String[] {
		"科室名称",
		"路径名称",
		"患者编码",
		"确认人名称",
		"节点名称"
		};
		data = new ArrayList<Object[]>();
	  Connection conn = null;
	  Statement statement = null;
	  ResultSet rs = null; 
	  CallableStatement stmt = null;
	DatabaseClass db = LcpUtil.getDatabaseClass();
	String sqlyy = "select t.hospital_name from dcp_sys_hospital t where t.hospital_id = "+hospital_id;
	DataSetClass dsyy = db.FunGetDataSetBySQL(sqlyy);
	String hospital_name = dsyy.FunGetDataAsStringByColName(0, "HOSPITAL_NAME");
    try{
    	DBConnection dbs=new DBConnection();
    	conn=dbs.getConnection();
	stmt = conn.prepareCall("{call GET_DATA.GET_PATIENT_ORDER (?,?,?,?)}");
    // stmt.registerOutParameter(1, java.sql.Types.FLOAT);
    // stmt.registerOutParameter(2, java.sql.Types.CHAR);
     stmt.registerOutParameter(1, OracleTypes.VARCHAR);
     stmt.registerOutParameter(2, OracleTypes.VARCHAR);
     stmt.registerOutParameter(3, OracleTypes.VARCHAR);
     stmt.registerOutParameter(4, OracleTypes.CURSOR);
     stmt.setString(1, start_time);
     stmt.setString(2, end_time);
     if(!"0".equals(deptcodevalue) && !"-1".equals(deptcodevalue) && !"---所有科室---".equals(deptcodevalue)){
 		//String ss = "select dept_name from lcp_local_dept where dept_code='"+deptcodevalue+"'";
		//DatabaseClass db1 = LcpUtil.getDatabaseClass();
	    //String deptName=db1.FunGetDataSetBySQL(ss).FunGetDataAsStringById(0,0);
		String deptName=deptcodevalue;
		//System.out.println("deptName"+deptName);
		//System.out.println("deptName===================:"+deptName);
        stmt.setString(3,deptName);
        stmt.execute(); 
        rs = (ResultSet) stmt.getObject(4);
    	while (rs.next()) {
    		String patient_no=rs.getString(1);
    		String user_name=rs.getString(2);
    		String cp_name=rs.getString(3);
    		String cp_node_name=rs.getString(4);
    	    data.add(new Object[] {
    	    		deptName,
    	    		cp_name,
    	    		patient_no,
    	    		user_name,
    	    		cp_node_name
    	    });
    	}
     }else{
    	stmt.setString(3,"sy");
        stmt.execute(); 
        rs = (ResultSet) stmt.getObject(4);
        while (rs.next()) {
        	String patient_no=rs.getString(1);
    		String user_name=rs.getString(2);
    		String cp_name=rs.getString(3);
    		String cp_node_name=rs.getString(4);
    		String deptName=rs.getString(5);
    	    data.add(new Object[] {
    	    		deptName,
    	    		cp_name,
    	    		patient_no,
    	    		user_name,
    	    		cp_node_name
    	    });
    	}
     }


    }catch(Exception e){
    	System.out.println("存储过程取数据出错!!");
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
	
 }
}

