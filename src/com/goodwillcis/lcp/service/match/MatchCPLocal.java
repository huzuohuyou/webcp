/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： MatchCPLocal.java
// 文件功能描述：查询本地数据库调用的各种方法接口
// 创建人：张昆 
// 创建日期：2012/05/10
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.match;

import java.util.ArrayList;

import com.goodwillcis.lcp.model.LocalDBMatch;


public interface MatchCPLocal {
	/**
	 * 生成表格	2012-05-10
	 * @param sql 查询的sql语句
	 * @param code 对应表中的编码字段名
	 * @param name 对应表中的名称字段名
	 * @param py 对应表中的拼音字段名
	 * @param wb 对应表中的五笔字段名
	 * @return
	 */
	public String funGetTable(String sql,String code,String name,String py,String wb);
		
	/**
	 * 取得中心端内容 	2012-05-10
	 * @param sql 查询的sql语句
	 * @param code 对应表中的编码字段名
	 * @param name 对应表中的名称字段名
	 * @param py 对应表中的拼音字段名
	 * @param wb 对应表中的五笔字段名
	 * @return
	 */
	public ArrayList<LocalDBMatch> funGetCoreList(String sql,String code,String name,String py,String wb);
}
