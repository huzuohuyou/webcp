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
import com.goodwillcis.lcp.util.DBConnection;

import bios.report.engine.api.CustomDataSet;

public class DeptOrderRate implements CustomDataSet {
	 private String[] metaData;

	 private List<Object[]> data;
	 
	 private String start_time = "";
	 
	 private String end_time = "";

	 // 参数列表,必须与设计器端的参数数量匹配


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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			start_time = (String) params[0];
			end_time = (String) params[1];
			deptcodevalue = (String) params[2];
			if (end_time == null||"".equals(end_time)) {
				end_time = sdf.format(new Date());
				start_time=end_time.substring(0,8)+"01";
			}
			end_time = end_time + " 23:59:59";
			metaData = new String[] {
			"所属科室",
			"下达医嘱总数",
			"下达临床路径医嘱数",
			"医嘱执行率"
			};
			data = new ArrayList<Object[]>();
		  Connection conn = null;
		  Statement statement = null;
		  ResultSet rs = null; 
		  CallableStatement stmt = null;
	    try{
	    	DBConnection dbs=new DBConnection();
	    	conn=dbs.getConnection();
		stmt = conn.prepareCall("{call GET_DATA.DEPT_ORDER_RATE_PRO (?,?,?,?)}");
	     stmt.registerOutParameter(1, OracleTypes.VARCHAR);
	     stmt.registerOutParameter(2, OracleTypes.VARCHAR);
	     stmt.registerOutParameter(3, OracleTypes.VARCHAR);
	     stmt.registerOutParameter(4, OracleTypes.CURSOR);
	     stmt.setString(1, start_time);
	     stmt.setString(2, end_time);
	     if(!"0".equals(deptcodevalue) && !"-1".equals(deptcodevalue) && !"---所有科室---".equals(deptcodevalue)){
			String deptName=deptcodevalue;
	        stmt.setString(3,deptName);
	        stmt.execute(); 
	        rs = (ResultSet) stmt.getObject(4);
	    	while (rs.next()) {
	            String execute_dept = rs.getString(1);
                int xdyzzs = rs.getInt(2);
                int xdlcljyzs = rs.getInt(3);
                double zxl = rs.getDouble(4);
	    	    data.add(new Object[] {
	    	    		execute_dept,
	    	    		xdyzzs,
	    	    		xdlcljyzs,
	    	    		zxl
	    	    });
	    	}
	     }else{
	    	stmt.setString(3,"sy");
	        stmt.execute(); 
	        rs = (ResultSet) stmt.getObject(4);
	        while (rs.next()) {
	            String execute_dept = rs.getString(1);
                int xdyzzs = rs.getInt(2);
                int xdlcljyzs = rs.getInt(3);
                double zxl = rs.getDouble(4);
	    	    data.add(new Object[] {
	    	    		execute_dept,
	    	    		xdyzzs,
	    	    		xdlcljyzs,
	    	    		zxl
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
