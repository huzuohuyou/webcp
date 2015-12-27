package com.goodwillcis.lcp.servlet.pdca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommonFunction {
	
	public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:DCPLOCAL";
	public static final String DBUSER = "jhdcp";
	public static final String DBPASS = "jhdcp";
	private static final long serialVersionUID = 1L;
	private ConnectionPool connPool= null;
	public static  CommonFunction cf = null;
	
	public CommonFunction(){
	} 
	
	/**
	 * 知心数据库操作
	 * 2015-12-24
	 * 吴海龙 
	 */
	public static ResultSet ExecuteQuery(String p_strSQL) {
		try {
			Connection conn = null;
			Statement stmt = null;
			String sql = p_strSQL;
			System.out.println(sql);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 执行增删改操作 2015-12-24 吴海龙
	 * @throws SQLException 
	 */
	public static int ExecuteNonQuery(String p_strSQL) {
		Statement stmt= null;
		try {
			String sql = p_strSQL;
			System.out.println(sql);
			ConnectionPool connpool = ConnectionPool.getInstance();
			Connection conn = connpool.getConnection();
		    stmt = conn.createStatement();
			int _n = stmt.executeUpdate(sql);
			connpool.returnConnection(conn);
			return _n;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 执行增删改操作 2015-12-24 吴海龙
	 * @throws SQLException 
	 
	public static int ExecuteNonQuery(String p_strSQL) {
		Connection conn = null;
		Statement stmt = null;
		try {
			String sql = p_strSQL;
			System.out.println(sql);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			stmt = conn.createStatement();
			int _n = stmt.executeUpdate(sql);
			return _n;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	*/
	
	/**
	 * 将为null 的字符串转为空字符串;
	 * 2015-12-25
	 * 吴海龙 
	 */
	public static String FixNull(String p_strValue){
		if(p_strValue==null||"null".equals(p_strValue)){
			return "";
		}
		return p_strValue;
	}
}
