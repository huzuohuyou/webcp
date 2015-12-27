/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：LocalDbUtil.java
// 文件功能描述：连接数据库用于匹配使用
// 创建人：刘植鑫 
// 创建日期：2011/07/26
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.util;

/*
 * 处理数据库访问
 * 创建时间：		2011-03-22
 * 创建人：			庞少军
 * 最后更新时间：	2011-04-21
 * 最后更新人：		庞少军
 * 版本：			1.0.1.4
 * 
 * 2011-03-23	1.0.1.1		庞少军	加入返回数据行数控制功能	
 * 2011-04-06	1.0.1.2		庞少军	调整了blob字段为空与日期类型无时间的问题	
 * 2011-04-18	1.0.1.3		 刘植鑫 	修改从String类型的字符串进行转化
 * 2011-04-21	1.0.1.4		庞少军	清除字符串中非法字符
 * 
 */

import java.sql.*;

public class LocalDbUtil {
	//JDBC数据库连接的驱动程序名称
    private String DbDriver = null;
    //JDBC数据库连接串
    private String DbConnString = null;
    //数据库连接用户名称
    private String DbUser = null;
    //数据库连接密码
    private String DbPassword = null;
    private Connection con=null;
    private Statement statement=null;
    private ResultSet rs=null;
    private  PreparedStatement pstat=null;
        
    /**
     * 取得数据行数
     * @param strSql
     * @return
     */
    public ResultSet funGetResultSetBySql(String strSql){
    	if(DbDriver==null) return null;
    	if(DbConnString==null) return null;
    	if(DbUser==null) return null;
    	if(DbPassword==null) return null;
    	if(strSql.isEmpty()) return null; 	
    	try{
    		Class.forName(DbDriver);    		
    	}catch(ClassNotFoundException classnotfoundexception){
    		return null;
    	}
    	try{
        	con=DriverManager.getConnection(DbConnString,DbUser,DbPassword);
        	statement=con.createStatement();
        	rs= statement.executeQuery(strSql);      	
    	}catch(SQLException sqlexception){
    		sqlexception.printStackTrace();
    		return null;
    	}    	
    	return rs;    	
    }  
    /**
     * 初始化数据库参数
     * @param strDbType 数据库类型
     * @param strDbDriver 数据库驱动名
     * @param strConnString 连接字符串
     * @param strUser 用户名
     * @param strPwd 密码
     * @return
     */
    public int FunInitDb(String strDbType,String strDbDriver,String strConnString,String strUser,String strPwd){
    	if(strDbType.isEmpty()) return -1;
    	if(strDbDriver.isEmpty()) return -1;
    	if(strConnString.isEmpty()) return -1;
    	if(strUser.isEmpty()) return -1;
    	int iRes=0;
    	DbDriver = strDbDriver;
    	DbConnString = strConnString;
    	DbUser = strUser;
    	DbPassword = strPwd;
    	iRes=1;
    	return iRes;
    }   
    

	/**
	 * 从开始算起取得前多少行数据
	 * @param sql 
	 * @param len 取得的行数
	 * @return
	 */
    public ResultSet funGetResultSetBySql(String sql,int len){
    	if(DbDriver==null) return null;
    	if(DbConnString==null) return null;
    	if(DbUser==null) return null;
    	if(DbPassword==null) return null;
    	if(sql.isEmpty()) return null; 	
    	try{
    		Class.forName(DbDriver);    		
    	}catch(ClassNotFoundException e){
    		e.printStackTrace();
    	}
    	
    	try{
        	con=DriverManager.getConnection(DbConnString,DbUser,DbPassword);
        	pstat = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	pstat.setMaxRows(len);
   		   	rs = pstat.executeQuery();
    	}catch(SQLException sqlexception){
    		sqlexception.printStackTrace();
    	}
    	return rs;
    }
    /**
     * 关闭连接
     */
    public void close(){
    	try {
			if(rs!=null){
				rs.close();
			}
			if(pstat!=null){
				pstat.close();
			}
			if(statement!=null){
				statement.close();
			}
			if(con!=null){
				con.close();
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
