package com.goodwillcis.lcp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public  class DBHelper {
	
    public static Connection getConn() throws ClassNotFoundException, SQLException{
        Class.forName("net.sourceforge.jtds.jdbc.Driver");                                                    //注册驱动
       
        String str = LcpUtil.getConfigValue(PropertiesUtil.get(PropertiesUtil.HIS_DB));
        //测试库 连接串
       // Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.1.33.96;databaseName=zsyydb_bak", "sa","nixpay007zs");        //获得数据库连接
        //正式库 连接串
        //Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.0.33.230;databaseName=zsyydb", "goodwill","goodwill");        //获得数据库连接
      
        
        Connection conn = DriverManager.getConnection(str, "goodwill","goodwill");        //获得数据库连接

        return conn ;                                                            //返回连接
    }
	public static void closeAll( Connection conn, PreparedStatement pstmt, ResultSet rs ) {
        /*  如果rs不空，关闭rs  */
        if(rs != null){
            try { rs.close();} catch (SQLException e) {e.printStackTrace();}
        }
        /*  如果pstmt不空，关闭pstmt  */
        if(pstmt != null){
            try { pstmt.close();} catch (SQLException e) {e.printStackTrace();}
        }
        /*  如果conn不空，关闭conn  */
        if(conn != null){
            try { conn.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }

}
