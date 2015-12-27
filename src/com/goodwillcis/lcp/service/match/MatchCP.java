/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： MatchCP.java
// 文件功能描述：路径匹配表调用的各种方法接口
// 创建人：张昆
// 创建日期：2011/05/10
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.match;

public interface MatchCP {
	/**
	 * 生成左侧栏的表格	2012-05-10
	 * @param sql 查询表格的sql语句
	 * @param start 开始位置
	 * @param end 结束位置
	 * @return
	 */
	public String funCreateTable(String sql,int start,int end);
	/**
	 * 取得匹配结果用于显示右下角	2012-05-10
	 * @param code 编码
	 * @return 表格内容
	 */
	public String funCreateMatchTable(String code);
	/**
	 * 取得总行数	2012-05-10
	 * @param sql 查询表格的sql语句
	 * @return 对应行数
	 */
	public int funGetCount(String sql);
	/**
	 * 取得某个编码的匹配行数2012-05-10
	 * @param code
	 * @return 对应行数
	 */
	public int funGetCountByCode(String code);
	/**
	 * 修改本地路径cp_master_id数据	2012-05-10
	 */
	public int funPiPeiInsert(String IDS);
	/**
	 * 删除匹配结果	2012-05-10
	 * @param IDS	中心编码和本地编码值 必须是13_and_0189:;13_and_0700:;类型的
	 * @return 影响行数
	 */
	public int funDelMatch(String IDS);
	/**
	 * 删除以前查询是否被使用，若被占用，那么返回>0的数据
	 * @param IDS
	 * @return
	 */
	public int funFindIsUsedBeforeDel(String IDS);
	/**
	 * 取得已经匹配过的数据
	 * @return
	 */
	public String funGetLocalCodeMatched();
}
