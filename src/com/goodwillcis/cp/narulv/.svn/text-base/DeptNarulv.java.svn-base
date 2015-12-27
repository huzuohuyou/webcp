/*----------------------------------------------------------------
//Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
//文件名：CPshidian.java
//文件功能描述：临床路径管理试点调查评估表   自定义数据集
//创建人：潘状
//创建日期：2011/09/08
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


public class DeptNarulv implements CustomDataSet {

 private String[] metaData;

 private List<Object[]> data;
 
 private String start_time = "";
 
 private String end_time = "";

 // 参数列表,必须与设计器端的参数数量匹配
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
		if (end_time == null||"".equals(end_time)) {
			end_time = sdf.format(new Date());
			start_time=end_time.substring(0,8)+"01";
		}
		end_time = end_time + " 23:59:59";
		//System.out.println("start_time===:"+start_time);
		//System.out.println("start_time===:"+end_time);
		metaData = new String[] {
		"科室名称",
		"符合纳入病例",       
		"已纳入病例",   
		"执行中病例",   
		"完成病例",     
		"变异退出病例",    
		"不纳入病例",
		"未纳入病例",
		"纳入率",
		"完成率",
		"变异退出率"
		};
		data = new ArrayList<Object[]>();
	Connection conn = null;
	Statement statement = null;
	ResultSet rs = null; 
	CallableStatement stmt = null;
    try{
    	DBConnection dbs=new DBConnection();
    	conn=dbs.getConnection();
	stmt = conn.prepareCall("{call GET_DATA.GET_ALL_DEPT(?,?,?)}");
     stmt.registerOutParameter(1, OracleTypes.VARCHAR);
     stmt.registerOutParameter(2, OracleTypes.VARCHAR);
     stmt.registerOutParameter(3, OracleTypes.CURSOR);
     stmt.setString(1, start_time);
     stmt.setString(2, end_time);
     stmt.execute(); 
     rs = (ResultSet) stmt.getObject(3);
    	while (rs.next()) {
    		String dept_name=rs.getString(1);
    		String fhrs=rs.getString(2);
    		String nrrs=rs.getString(3);
    		String zxrs=rs.getString(4);
    		String wcrs=rs.getString(5);
    		String byrs=rs.getString(6);
    		String bnrrs=rs.getString(7);
    		String wnrrs=rs.getString(8);
    		double nrl=rs.getDouble(9);
    		double wcl=rs.getDouble(10);
    		double byl=rs.getDouble(11);
    	    data.add(new Object[] {
    	    		dept_name,
    	    		fhrs,
    	    		nrrs,
    	    		zxrs,
    	    		wcrs,
    	    		byrs,
    	    		bnrrs,
    	    		wnrrs,
    	    		nrl,
    	    		wcl,
    	    		byl
    	    });
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

