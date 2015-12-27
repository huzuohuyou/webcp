package com.goodwillcis.cp.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;



public class ExecuteProcedure {
	private Logger LOG=Logger.getLogger(ExecuteProcedure.class);
	private String driver=PropertiesUtil.get(PropertiesUtil.DB_DRIVER);
	private String url=PropertiesUtil.get(PropertiesUtil.DB_CONNSTRING);
	private String user = PropertiesUtil.get(PropertiesUtil.DB_USER);
	private String password = PropertiesUtil.get(PropertiesUtil.DB_PASSWORD);
	private Connection conn=null;
	private CallableStatement c=null;
	private static ExecuteProcedure proc=null;
	
	public static synchronized ExecuteProcedure getInstance(){
		if(proc==null){
			proc=new ExecuteProcedure();
		}
		return proc;
	}
	public void executeProc(String startDate,String endDate){
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url,user,password);
			c = conn.prepareCall("{call PRO_REPORT_TJ(?,?)}");
			c.setString(1, startDate);
			c.setString(2, endDate);
			c.execute();
			conn.close();
			c.close();
		} catch (ClassNotFoundException e) {
			closeAll(conn,c);
			LOG.info("无法加载驱动!!");
			e.printStackTrace();
		} catch (SQLException e) {
			closeAll(conn,c);
			LOG.info("无法加载驱动!!");
			e.printStackTrace();
		}finally{
			closeAll(conn,c);
		}
	}
	
	public Connection getConn(){
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			LOG.info("无法加载驱动!!");
			closeConnection(conn);
			e.printStackTrace();
		} catch (SQLException e) {
			LOG.info("无法加载驱动!!");
			closeConnection(conn);
			e.printStackTrace();
		}
		return conn;
	}

	private void closeAll(Connection conn,CallableStatement c){
		
		if(c != null){
			try {
				c.close();
			} catch (SQLException e) {
				LOG.info("存储过程执行异常！");
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				LOG.info("数据库连接异常！");
				e.printStackTrace();
			}
		}
	}
	
	public void closeConnection(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				LOG.info("数据库连接关闭异常！");
				e.printStackTrace();
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		ExecuteProcedure exe=new ExecuteProcedure();
//		ResultSet rs=exe.getResultSet("2011-10-01","2012-07-11");
//		try {
//			while(rs.next()){
//				String cpName=rs.getString("CP_NAME");
//				System.out.println("cpName=:"+cpName);
//			}
//		} catch (SQLException e) {
//			exe.closeAll();
//			e.printStackTrace();
//		}finally{
//			exe.closeAll();
//		}
	}

}
