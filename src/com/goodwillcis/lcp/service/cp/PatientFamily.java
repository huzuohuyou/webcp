// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：Patient .java
// 文件功能描述：家属工作接口
// 创建人：刘植鑫 
// 创建日期：2011/08/03
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.cp;

public interface PatientFamily {

	/**
	 * 取得表格
	 * @param cp_id
	 * @param cp_node_id
	 * @return
	 */
	public String funGetTable(int hostipalID,int cp_id,int cp_node_id);
	
	/**
	 * 取得对应的某一条记录并且是可编辑状态
	 * @param cp_id
	 * @param cp_node_id
	 * @param cp_node_doctor_id
	 * @param CP_NODE_DOCTOR_ITEM_ID
	 * @return
	 */
	public String funGetNodeItemTable(int hostipalID,int cp_id,int cp_node_id,int cp_node_doctor_id,int CP_NODE_DOCTOR_ITEM_ID);
	/**
	 * 更新自动项
	 * @param cp_id
	 * @param cp_node_id
	 * @param cp_node_doctor_id
	 * @param CP_NODE_DOCTOR_ITEM_ID
	 * @param auto_item
	 * @return
	 */
	public boolean funSetNodeItem(int hostipalID,int cp_id,int cp_node_id,int cp_node_doctor_id,int CP_NODE_DOCTOR_ITEM_ID,int auto_item);
}
