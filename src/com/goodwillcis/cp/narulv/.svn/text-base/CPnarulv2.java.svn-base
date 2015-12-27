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


public class CPnarulv2 implements CustomDataSet {

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
/*		if (start_time == null||"".equals(start_time)) {
			start_time = "2011-10-01";
		}*/
		if (end_time == null||"".equals(end_time)) {
			end_time = sdf.format(new Date());
			start_time=end_time.substring(0,8)+"01";
		}
		end_time = end_time + " 23:59:59";
		metaData = new String[] {
		"路径名称",
		"符合纳入病例",  
		"未确定病例",
		"拒绝纳入病例",     
		"已纳入病例",   
		"执行中病例",   
		"完成病例",     
		"变异退出病例",    
		"医院名称",
		"纳入率",
		"完成率",
		"变异率",
		"标示",
		"科室名称"
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
	stmt = conn.prepareCall("{call GET_DATA.EXECUTE_UPDATE (?,?,?,?)}");
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
		//System.out.println("deptName===================:"+deptName);
        stmt.setString(3,deptName);
        stmt.execute(); 
        rs = (ResultSet) stmt.getObject(4);
    	while (rs.next()) {
    		int zxzlj=0;
    		int wcbl=0;
    		int bytc=0;
    		int bnrrs=0;
    		int wnrrs=0;
    		int ynrlj=0;
    		int fhrs=0;
    		//int cp_id=0;
    		String flag="";
    		double nrbfb=0.0;
    		double wcbfb=0.0;
    		double bybfb=0.0;
    		String cp_name=rs.getString(2);
    		fhrs=rs.getInt(6);
    		ynrlj=rs.getInt(7);
    		zxzlj=rs.getInt(8);
    		wcbl=rs.getInt(9);
    		bytc=rs.getInt(10);
    		wnrrs=rs.getInt(11);
    		bnrrs=rs.getInt(12);
    		//cp_id=rs.getInt(11);
    /*		if(cp_id>10000){
    			flag="自定义路径";
    		}else{
    			flag="局发路径";
    		}*/
    			if(fhrs==0){
    				nrbfb=0.0;
    			}else{
    				nrbfb=(double)ynrlj/(double)fhrs;
    				if(nrbfb>1){
    					nrbfb=1.0;
    				}
    			}
    			if(ynrlj==0){
    				wcbfb=0.0;
    			}else{
    				wcbfb=(double)wcbl/(double)ynrlj;
    				if(wcbfb>1){
    					wcbfb=1.0;
    				}
    			}
    			if(ynrlj==0){
    				bybfb=0.0;
    			}else{
    				bybfb=(double)bytc/(double)ynrlj;
    				if(bybfb>1){
    					bybfb=1.0;
    				}
    			}

    	    data.add(new Object[] {
    	    		cp_name,
    	    		fhrs,
    	    		wnrrs,
    	    		bnrrs,
    	    		ynrlj,
    	    		zxzlj,
    	    		wcbl,
    	    		bytc,
    	    		hospital_name,
    	    		nrbfb,
    	    		wcbfb,
    	    		bybfb,
    	    		flag,
    	    		deptName
    	    });
    	}
     }else{
    	stmt.setString(3,"sy");
        stmt.execute(); 
        rs = (ResultSet) stmt.getObject(4);
        while (rs.next()) {
    		int zxzlj=0;
    		int wcbl=0;
    		int bytc=0;
    		int bnrrs=0;
    		int wnrrs=0;
    		int ynrlj=0;
    		int fhrs=0;
    		int cp_id=0;
    		String flag="";
    		double nrbfb=0.0;
    		double wcbfb=0.0;
    		double bybfb=0.0;
    		String cp_name=rs.getString(2);
    		String deptName=rs.getString(5);
    		fhrs=rs.getInt(6);
    		ynrlj=rs.getInt(7);
    		zxzlj=rs.getInt(8);
    		wcbl=rs.getInt(9);
    		bytc=rs.getInt(10);
    		wnrrs=rs.getInt(11);
    		bnrrs=rs.getInt(12);
    		//cp_id=rs.getInt(10);
    	/*	if(cp_id>10000){
    			flag="自定义路径";
    		}else{
    			flag="局发路径";
    		}*/
    			if(fhrs==0){
    				nrbfb=0.0;
    			}else{
    				nrbfb=(double)ynrlj/(double)fhrs;
    				if(nrbfb>1){
    					nrbfb=1.0;
    				}
    			}
    			if(ynrlj==0){
    				wcbfb=0.0;
    			}else{
    				wcbfb=(double)wcbl/(double)ynrlj;
    				if(wcbfb>1){
    					wcbfb=1.0;
    				}
    			}
    			if(ynrlj==0){
    				bybfb=0.0;
    			}else{
    				bybfb=(double)bytc/(double)ynrlj;
    				if(bybfb>1){
    					bybfb=1.0;
    				}
    			}

    	    data.add(new Object[] {
    	    		cp_name,
    	    		fhrs,
    	    		wnrrs,
    	    		bnrrs,
    	    		ynrlj,
    	    		zxzlj,
    	    		wcbl,
    	    		bytc,
    	    		hospital_name,
    	    		nrbfb,
    	    		wcbfb,
    	    		bybfb,
    	    		flag,
    	    		deptName
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

