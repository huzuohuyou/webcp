/**---------------------------------------------------------------- 
 * // Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
 *  // 文件名： HospitalUtil.java
 *  // 文件功能描述：提供医院相关信息的工具类 
 *  // 创建人：赵蓬 
 *  // 创建日期：2011/07/25 
 *  // ----------------------------------------------------------------*/

package com.goodwillcis.cp.util;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;

public class LcpUtil {
	
	/**
	 * getHospitalID 获取医院编号
	 * @return 医院编号
	 *         -1 报错
	 *         -2 数据库访问报错
	 *         -3 数据有误
	 * @author 赵蓬 
	 * @since 2011/07/25 
	 */
	public static int getHospitalID()
	{
		try
		{
			String sql = new StringBuffer().append("select CONFIG_VALUE from dcp_sys_config where CONFIG_ID = '").append(PropertiesUtil.get(PropertiesUtil.HOSPITAL_ID_KEY)).append("'").toString();
			DataSetClass ds = getDatabaseClass().FunGetDataSetBySQL(sql);
			if (ds == null) return -2;
			String hospitalId = ds.FunGetDataAsStringById(0, 0);
			if (hospitalId == null || (hospitalId != null && "".equals(hospitalId))) return -3;
			return Integer.parseInt(hospitalId.toString());
		}
		catch(Exception ex)
		{
			return -1;
		}
	}
	
	/**
	 * getDatabaseClass 获取DatabaseClass实例
	 * @return DatabaseClass实例
	 * @author 赵蓬 
	 * @since 2011/07/25 
	 */
	public static DatabaseClass getDatabaseClass()
	{
		try
		{	
			String dbType = PropertiesUtil.get(PropertiesUtil.DB_TYPE);
			String dbConnString = PropertiesUtil.get(PropertiesUtil.DB_CONNSTRING);
			String dbDriver = PropertiesUtil.get(PropertiesUtil.DB_DRIVER);
			String dbUser = PropertiesUtil.get(PropertiesUtil.DB_USER);
			String dbPassword = PropertiesUtil.get(PropertiesUtil.DB_PASSWORD);
			
			String url = PropertiesUtil.get(PropertiesUtil.DATABASE_URL);
			DatabaseClass db = new DatabaseClass(dbType,dbDriver,dbConnString,dbUser,dbPassword);
			return db;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	/**
	 * getConfigValue 根据关键字，获取配置表中相应的值
	 * @param configKey 关键字
	 * @return 配置值
	 */
	public static String getConfigValue(String configKey)
	{
		try
		{
			DataSetClass ds = getDatabaseClass().FunGetDataSetBySQL("select CONFIG_VALUE from DCP_SYS_CONFIG where CONFIG_ID = '" + configKey + "'");
			return ds.FunGetDataAsStringByColName(0, "CONFIG_VALUE");
		}
		catch(Exception ex)
		{
			return null;
		}
	}
}
