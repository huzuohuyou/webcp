/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：CP .java
// 文件功能描述：路径接口
// 创建人：刘植鑫 
// 创建日期：2011/08/01
// 修改人:潘状
 // 创建日期：2011/08/29
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.cp;

public interface CP {

	/**
	 * 取得路径基本信息
	 * @param hostipalID
	 * @param cp_id
	 * @return
	 */
	
	
	public String funGetTable(int hostipalID,int cp_id, boolean isUse);
	/**
	 * 查看路径的启用停用状态
	 * @param hostipalID
	 * @param cp_id
	 * @return
	 */
	public boolean funGetCpState(int hostipalID,int cp_id);
	/**
	 * 启用或者停用路径
	 * @param hostipalID
	 * @param cp_id
	 * @param isStart 0 启用 1 停用
	 * @param fieldName
	 * @param userName
	 * @return true 成功；false 失败
	 */
	public boolean funStartOrEndCpRoute(int hostipalID,int cp_id,String isStart,String fieldName,String userName,String startOrEndFieldName);
	/**
	 * 查询的cp_id是否存在
	 * @param hostipalID
	 * @param cp_id
	 * @return
	 */
	public boolean funCpIsExist(int hostipalID,int cp_id);
	/**
	 * 是否禁用并不是停用
	 * @param hostipalID
	 * @param cp_id
	 * @return
	 */
	public boolean funCpIsForbit(int hostipalID,int cp_id);
	/**
	 * 浏览页所有table内容
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 * 修改人:潘状
	 * 修改日期：2011/08/29
	 */
	public String funGetAllCPViewTable(String sql,int start,int end);
	
	public String funGetAllCPViewTableAdmin(String sql,int start,int end);
	
	/**
	 * 浏览页中心定义table内容
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 * 添加人:潘状
	 * 添加日期:2011/08/29
	 */
	public String funGetCoreCPViewTable(String sql,int start,int end);
	
	
	/**
	 * 浏览页本地自定义table内容
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 *  添加人:潘状
	 * 添加日期:2011/08/29
	 */
	public String funGetLocalCPViewTable(String sql,int start,int end);
	/**
	 * 一共多少路径
	 * @param sql
	 * @return
	 */
	public int funGetCountCp(int hostipalID,String sql);
	/**
	 * 取得第几个启用还是停用表格
	 * @param cp_id
	 * @return
	 */
	public String funGetStartOrEndTable(int hostipalID,int cp_id);
	
	public String funGetAllSql(String bm,String mc,String py,String wb,String dc,String ks);
	
	
	/**
	 * @param bm
	 * @param mc
	 * @param py
	 * @param wb
	 * @return
	 * 添加人:潘状
	 * 添加日期:2011/08/29
	 */
	public String funGetCoreSql(String bm,String mc,String py,String wb);
	
	/**
	 * @param bm
	 * @param mc
	 * @param py
	 * @param wb
	 * @return
	 * 添加人:潘状
	 * 添加日期:2011/08/29
	 */
	public String funGetLocalSql(String bm,String mc,String py,String wb);
	
	
	/**
	 * 根据cpID进行复制  cpID号必须小于1w
	 * @param cpID
	 * @return  是否成功
	 */
	public boolean copyCP(String cpID);
	
	
	
	
	
}
