/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：CpNodeTableMaint.java
// 文件功能描述：路径维护提交时转化的model对象
// 创建人：刘植鑫 
// 创建日期：2011/08/04
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.model;

public class CpNodeTableMaint {

	private String table_index;//表的索引 1：诊疗2：护理3：医嘱
	private String cp_id;
	private String cp_node_id;
	private String cp_node_table_id;//一级索引
	private String cp_node_table_item_id;//二级索引
	public CpNodeTableMaint(String table_index,String cp_id,String cp_node_id ,String cp_node_table_id ,String cp_node_table_item_id,String auto_item){
		this.table_index=table_index;
		this.cp_id=cp_id;
		this.cp_node_id=cp_node_id;
		this.cp_node_table_id=cp_node_table_id;
		this.cp_node_table_item_id=cp_node_table_item_id;
		this.auto_item=auto_item;
	}
	public CpNodeTableMaint(String table_index,String cp_id,String cp_node_id ,String cp_node_table_id ,String auto_item){
		this.table_index=table_index;
		this.cp_id=cp_id;
		this.cp_node_id=cp_node_id;
		this.cp_node_table_id=cp_node_table_id;
		this.auto_item=auto_item;
	}
	public String getTable_index() {
		return table_index;
	}
	public String getCp_id() {
		return cp_id;
	}
	public String getCp_node_id() {
		return cp_node_id;
	}
	public String getCp_node_table_id() {
		return cp_node_table_id;
	}
	public String getCp_node_table_item_id() {
		return cp_node_table_item_id;
	}
	private String auto_item;//自动项编码
	public String getAuto_item() {
		return auto_item;
	}
}
