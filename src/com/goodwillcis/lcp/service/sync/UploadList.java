/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：UploadList.java
// 文件功能描述：院内更新页面所需方法的接口
// 创建人：段英华
// 创建日期：2011/08/16
// 
//----------------------------------------------------------------*/

package com.goodwillcis.lcp.service.sync;

import java.util.ArrayList;

public interface UploadList {
	
	/**
	 * 获取更新页面版本列表
	 * @param start
	 * @param end
	 * @return
	 */
	public String getUploadList(int start,int end);
	
	
	/**
	 * 取得总行数
	 * @return
	 */
	public int getTotalNum();
	
	/**
	 * 加载CSV文件
	 * @param hospitalId
	 * @param pathName
	 * @return
	 */
	public ArrayList<String> loadcsv(int hospitalId,String pathName);
	
	
	/**
	 * 总的更新数据的方法
	 * @param hospitalId
	 * @param pathName
	 * @param tableName
	 * @param uprow
	 * @return
	 */
	public int updateData(int hospitalId,String pathName,String tableName,String uprow);
	
	/**
	 * 获取要更新的版本列表
	 * @param lastVerNo
	 * @param hospitalId
	 * @param outIntKey
	 * @param versionNos
	 */
	public void getVersionList(java.lang.String lastVerNo, int hospitalId, javax.xml.rpc.holders.IntHolder outIntKey, javax.xml.rpc.holders.ByteArrayHolder versionNos);
	
	/**
	 * 根据版本号返回包的大小
	 * @param curVerNo
	 * @return
	 */
	public int buildDataByVer(java.lang.String curVerNo);
	
	
	/**
	 * 根据页号返回对应的包数据
	 * @param curVerNo
	 * @param curPageNo
	 * @param totalPageSize
	 * @param hospitalId
	 * @param outIntKey
	 * @param curVersionBagData
	 */
	public void getVersionBagByPack(java.lang.String curVerNo, int curPageNo, int totalPageSize, int hospitalId, javax.xml.rpc.holders.IntHolder outIntKey, javax.xml.rpc.holders.ByteArrayHolder curVersionBagData);
	
	
	/**
	 * 解压文件
	 * @param filePath
	 * @param hospitalId
	 * @param loadName
	 * @return
	 */
	public int unZipFile(String filePath,int hospitalId,String loadName);
	
	
	

}
