/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：IncludeExclude .java
// 文件功能描述：路径纳入排除条件接口
// 创建人：刘植鑫 
// 创建日期：2011/08/03
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.cp;

public interface IncludeExclude {

	/**
	 * 路径纳入条件 
	 * @param hostipalID
	 * @param cp_id
	 * @return
	 */
	public String funGetIncludeTable(int hostipalID,int cp_id,boolean isUse);
	/**
	 * 排除条件
	 * @param hostipalID
	 * @param cp_id
	 * @return
	 */
	public String funGetExcludeTable(int hostipalID,int cp_id);
	
	/**
	 * 取得替换的数据
	 * @param hostipalID
	 * @param cp_id
	 * @param cp_income_id
	 * @return
	 */
	public String funGetNewLocal(int hostipalID,int cp_id,int cp_income_id);
}
