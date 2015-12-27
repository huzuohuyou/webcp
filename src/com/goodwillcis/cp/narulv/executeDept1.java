/*----------------------------------------------------------------
//Copyright (C) 2014 北京嘉和美康信息技术有限公司版权所有。 
//文件名：executeDept1.java
//文件功能描述：路径执行情况
//创建人：周伟彬
//创建日期：2014/08/14
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

/**
 * Class description goes here.
 * 
 * @version 2014/08/14
 * @author 周伟彬
 */
public class executeDept1 implements CustomDataSet {

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
		if (end_time == null||"".equals(end_time)) {
			end_time = sdf.format(new Date());
			start_time=end_time.substring(0,8)+"01";
		}
		end_time = end_time + " 23:59:59";
		metaData = new String[] {
		"执行科室",
		"符合人数",  
		"纳入人数",
		"完成人数",     
		"纳入率",   
		"完成率",   
		"下达医嘱总数",     
		"下达临床路径医嘱数",    
		"医嘱执行率"
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
	stmt = conn.prepareCall("{call GET_DATA.EXECUTE_DEPT1_PRO (?,?,?,?)}");
     stmt.registerOutParameter(1, OracleTypes.VARCHAR);
     stmt.registerOutParameter(2, OracleTypes.VARCHAR);
     stmt.registerOutParameter(3, OracleTypes.VARCHAR);
     stmt.registerOutParameter(4, OracleTypes.CURSOR);
     stmt.setString(1, start_time);
     stmt.setString(2, end_time);
     if(!"0".equals(deptcodevalue) && !"-1".equals(deptcodevalue) && !"---所有科室---".equals(deptcodevalue)){
 		String ss = "select dept_name from lcp_local_dept where dept_code='"+deptcodevalue+"'";
		DatabaseClass db1 = LcpUtil.getDatabaseClass();
		String deptName="";
	    deptName=db1.FunGetDataSetBySQL(ss).FunGetDataAsStringById(0,0);
        stmt.setString(3,deptName);
        stmt.execute(); 
        rs = (ResultSet) stmt.getObject(4);
    	while (rs.next()) {
    		String execute_dept = rs.getString("execute_dept");
    		int fhrs=rs.getInt("fhrs");
            int nrrs=rs.getInt("nrrs");
            int wcrs=rs.getInt("wcrs");
            int xdyzzs=rs.getInt("xdyzzs");
            int xdlcljyzs=rs.getInt("xdlcljyzs");
            double nvl=0.0;
            double wcl=0.0;
            double yzzxl=0.0;
			if(fhrs==0){
				nvl=0.0;
			}else{
				nvl=(double)nrrs/(double)fhrs;
				if(nvl>1){
					nvl=1.0;
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
			if(xdyzzs==0){
				yzzxl=0.0;
			}else{
				yzzxl=(double)xdlcljyzs/(double)xdyzzs;
				if(yzzxl>1){
					yzzxl=1.0;
				}
			}
    	    data.add(new Object[] {
    	    	execute_dept, 
    	    	fhrs,
    	    	nrrs,
    	    	wcrs,
    	    	nvl,
    	    	wcl,
    	    	xdyzzs,
    	    	xdlcljyzs,
    	    	yzzxl
    	    });
    	}
     }else{
    	stmt.setString(3,"sy");
        stmt.execute(); 
        rs = (ResultSet) stmt.getObject(4);
        while (rs.next()) {
      		String execute_dept = rs.getString("execute_dept");
    		int fhrs=rs.getInt("fhrs");
            int nrrs=rs.getInt("nrrs");
            int wcrs=rs.getInt("wcrs");
            int xdyzzs=rs.getInt("xdyzzs");
            int xdlcljyzs=rs.getInt("xdlcljyzs");
            double nvl=0.0;
            double wcl=0.0;
            double yzzxl=0.0;
			if(fhrs==0){
				nvl=0.0;
			}else{
				nvl=(double)nrrs/(double)fhrs;
				if(nvl>1){
					nvl=1.0;
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
			if(xdyzzs==0){
				yzzxl=0.0;
			}else{
				yzzxl=(double)xdlcljyzs/(double)xdyzzs;
				if(yzzxl>1){
					yzzxl=1.0;
				}
			}
    	    data.add(new Object[] {
        	    	execute_dept, 
        	    	fhrs,
        	    	nrrs,
        	    	wcrs,
        	    	nvl,
        	    	wcl,
        	    	xdyzzs,
        	    	xdlcljyzs,
        	    	yzzxl
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

