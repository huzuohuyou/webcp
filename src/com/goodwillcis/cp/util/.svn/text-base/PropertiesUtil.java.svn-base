/**---------------------------------------------------------------- 
 * // Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
 *  // 文件名：PropertiesUtil.java
 *  // 文件功能描述：读取配置文件里属性的类
 *  // 创建人：段英华 
 *  // 创建日期：2011/07/26 
 *  // ----------------------------------------------------------------*/

package com.goodwillcis.cp.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesUtil {
	
	private static HashMap<String, String> property;
	
	/**
	 * Web服务DatabaseProject的地址常量
	 */
	public static final String DATABASE_URL = "database.url";
	/**
	 * 配置表中，当前医院编号的关键字常量
	 */
	public static final String HOSPITAL_ID_KEY = "hospital.id.key";
	/**
	 * 配置表中，上传应用程序地址的关键字常量
	 */
	public static final String UPLOAD_EXE_KEY = "upload.exe.key";
	/**
	 * 配置表中，下载应用程序地址的关键字常量
	 */
	public static final String DOWNLOAD_EXE_KEY = "download.exe.key";
	
	/**
	 * 配置表中，医嘱下发对应的URL地址常量
	 */
	public static final String ORDER_URL = "order.url";
	
	/**
	 * 配置表中，如果不纳入路径，医嘱下发对应的URL地址常量
	 */
	public static final String NULLORDER_URL = "nullorder.url";

//2011.09.22新加   修改jhDatabase为JDBC方式，以下为连接参数
	
	/**
	 * 配置文件里数据库类型
	 */
	public static final String DB_TYPE = "Db_Type";
	
	/**
	 * 配置文件里数据库驱动名
	 */
	public static final String DB_DRIVER = "Db_Driver";
	
	/**
	 * 配置文件里连接串
	 */
	public static final String DB_CONNSTRING = "Db_ConnString";
	
	/**
	 * 配置文件里用户名
	 */
	public static final String DB_USER = "Db_User";
	
	/**
	 * 配置文件里用户密码
	 */
	public static final String DB_PASSWORD = "Db_Password";
	

	public PropertiesUtil() {
		property = new HashMap<String, String>();
	}
	
	static {
		try{
			reloadAll();
		} catch(Exception e) {	
			throw new RuntimeException(e.toString());
		}
    }
    
	/**
	 * get 根据关键字，获取配置文件中相关的值
	 * @param key 关键字
	 * @return 配置值
	 */
	public static String get(String key) {
		String str = "";
		if(property != null && property.containsKey(key))
			str = property.get(key);            	
		return str;
	}
	
	private static void reloadAll() {
    	if(property == null)property = new HashMap<String, String>();
    	strageMsg();
    }

	private static void strageMsg() {
    	HashMap<String, String> map = new HashMap<String, String>();

    	Properties p = new Properties();
        try {
            p.load((PropertiesUtil.class).getClassLoader().getResourceAsStream("config.properties"));
            
            String key;
            String value;
            for(Enumeration<?> enu = p.propertyNames(); enu.hasMoreElements(); map.put(key, value))
            {
                key = (String)enu.nextElement();
                value = p.getProperty(key);
            }
        } catch(FileNotFoundException e) {
        	throw new RuntimeException(e.toString());
        } catch(IOException io){
        	throw new RuntimeException(io.toString());
        }
    	property = map;
    }
}
