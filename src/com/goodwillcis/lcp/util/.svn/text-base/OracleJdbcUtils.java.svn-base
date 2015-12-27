package com.goodwillcis.lcp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class OracleJdbcUtils {
	
//	private static String url = "jdbc:oracle:thin:@192.1.33.124:1521:DCPLOCAL";
//	private static String user = "jhdcp";
//	private static String psw = "jhdcp";
	
	private static String url = "jdbc:oracle:thin:@192.1.33.126:1521:JHEMR";
	private static String user = "emr";
	private static String psw = "emr";
	
	private OracleJdbcUtils(){
		
	}

	static{
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, psw);
	}
	
//	public static Connection getConnection1(int type) throws SQLException{
//		if(type==0){
//			
//		}else if(type==1){
//			
//			return DriverManager.getConnection(url1, user1, psw1);
//		}
//		return DriverManager.getConnection(url, user, psw);
//	}
	
	public static void free(ResultSet rs,PreparedStatement st,Connection conn){
		try{
			if(rs != null)
				rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
			finally{
				try{
					if(st != null)
						st.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
					finally{
						if(conn != null)
						try{
							conn.close();
						}catch(SQLException e){
							e.printStackTrace();
						}
					}
			}
	}
	
	public static void free(PreparedStatement st,Connection conn){
		
			try{
				if(st != null)
					st.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
				finally{
					if(conn != null)
					try{
						conn.close();
					}catch(SQLException e){
						e.printStackTrace();
					}
			}
	}
	
	public static void free1(ResultSet rs,Statement st,Connection conn){
		try{
			if(rs != null)
				rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
			finally{
				try{
					if(st != null)
						st.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
					finally{
						if(conn != null)
						try{
							conn.close();
						}catch(SQLException e){
							e.printStackTrace();
						}
					}
			}
	}
}
