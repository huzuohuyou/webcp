/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：DownloadInfo.java    
// 文件功能描述：下载信息相关操作
//
// 创建人：赵蓬
// 创建日期：2011-08-16
//----------------------------------------------------------------*/

package com.goodwillcis.lcp.model;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.lcp.util.LcpUtil;

public class DownloadInfo {
	/**
	 * getVersion 根据下载编号返回相应的下载版本号
	 * @param id 下载编号
	 * @return 版本号
	 */
	public static String getVersion(int id)
	{
		DataSetClass ds = LcpUtil.getDatabaseClass().FunGetDataSetBySQL("select REMARK from dcp_log_download where download_id = " + id);
		return ds.FunGetDataAsStringByColName(0, "REMARK");
	}
}
