package com.goodwillcis.cp.patientmanager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.goodwillcis.cp.util.LcpUtil;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.DBConnection;

import bios.report.engine.api.CustomDataSet;

public class WnaruTongji implements CustomDataSet{
	 private String[] metaData;

	 private List<Object[]> data;
	 	 
	 private String start_time = "";
	 
	 private String end_time = "";
	 
	 private String deptcodevalue="";


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
	@Override
	public void applyParams(Object[] params) {
		// TODO Auto-generated method stub
		//hospital_id = (Integer) params[0];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		start_time = (String) params[0];
		end_time = (String) params[1];
		deptcodevalue = (String) params[2];
		
		if (start_time == null||"".equals(start_time)) {
			start_time = "2012-03-01";
		}
		if (end_time == null||"".equals(end_time)) {
			//end_time = sdf.format(new Date());
			end_time = "2012-04-01";
		}
	    
		end_time = end_time + " 23:59:59";
	metaData = new String[] {
         "患者编码",
         "患者姓名",
         "入院日期",
         "所属科室",
         "主治医生"
		};
	data = new ArrayList<Object[]>();
	  Connection conn = null;
	  Statement statement = null;
	  ResultSet rs = null; 
	  CallableStatement stmt = null;
	DBConnection dbs=new DBConnection();
	conn=dbs.getConnection();
	 try{
		 stmt = conn.prepareCall("{call GET_DATA.WNR_PRO (?,?,?,?)}");
		// stmt.registerOutParameter(1, java.sql.Types.FLOAT);
		// stmt.registerOutParameter(2, java.sql.Types.CHAR);
		 stmt.registerOutParameter(1, OracleTypes.VARCHAR);
		 stmt.registerOutParameter(2, OracleTypes.VARCHAR);
		 stmt.registerOutParameter(3, OracleTypes.VARCHAR);
		 stmt.registerOutParameter(4, OracleTypes.CURSOR);
		 stmt.setString(1, start_time);
		 stmt.setString(2, end_time);
			if(!"".equals(deptcodevalue) && Integer.parseInt(deptcodevalue)>0){
				String ss = "select dept_name from lcp_local_dept where dept_code='"+deptcodevalue+"'";
				DatabaseClass db1 = LcpUtil.getDatabaseClass();
				String deptName=db1.FunGetDataSetBySQL(ss).FunGetDataAsStringById(0,0);
		        stmt.setString(3,deptName);
		        stmt.execute(); 
		        rs = (ResultSet) stmt.getObject(4);
		    	while (rs.next()) {
		    		String patient_no=rs.getString(1);
		    		String patient_name=rs.getString(2);;
		    		String admission_date=rs.getString(3);;
		    		String dept_admission_to=rs.getString(4);;
		    		String attending_doctor=rs.getString(5);;
		    		data.add(new Object[] {
		    	    		patient_no,
		    	    		patient_name,
		    	    		admission_date,
		    	    		dept_admission_to,
		    	    		attending_doctor
		    	    });
		    	}
			}else{
				stmt.setString(3,"sy");
		        stmt.execute(); 
		        rs = (ResultSet) stmt.getObject(4);
		        while (rs.next()) {
		    		String patient_no=rs.getString(1);
		    		String patient_name=rs.getString(2);;
		    		String admission_date=rs.getString(3);;
		    		String dept_admission_to=rs.getString(4);;
		    		String attending_doctor=rs.getString(5);;
		
		    	    data.add(new Object[] {
		    	    		patient_no,
		    	    		patient_name,
		    	    		admission_date,
		    	    		dept_admission_to,
		    	    		attending_doctor
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
