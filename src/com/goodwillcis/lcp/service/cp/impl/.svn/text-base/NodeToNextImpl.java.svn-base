package com.goodwillcis.lcp.service.cp.impl;

import java.io.UnsupportedEncodingException;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.service.cp.NodeToNext;
import com.goodwillcis.lcp.util.LcpUtil;

public class NodeToNextImpl implements NodeToNext {

	@Override
	public int synOrderData(String cpId, String cpNodeId, String patientId,
			int hospitalId) {
		// TODO Auto-generated method stub
		int result = 0;
		//lcp_node_order的数据同步到lcp_patient_order里(作废)
//		String synSql1 = "insert into lcp_patient_order (hospital_id,patient_no,cp_id,cp_node_id,cp_node_order_id,cp_std_order_id,cp_node_order_text,need_item,auto_item,order_no,order_type,sys_is_del,sys_last_update) "+
//						"(select t.hospital_id,'"+patientId+"',t.cp_id,t.cp_node_id,t.cp_node_order_id,t.cp_std_order_id,t.cp_node_order_text,t.need_item,t.auto_item,t.order_no,t.order_type,t.sys_is_del,t.sys_last_update from lcp_node_order t" +
//						" where t.hospital_id = "+hospitalId+" and t.cp_id='"+cpId+"' and t.cp_node_id='"+cpNodeId+"')\r\n";
		
		//lcp_node_order_point的数据同步到lcp_patient_order_point里
	    String synSql2 = "insert into lcp_patient_order_point (hospital_id,patient_no,cp_id,cp_node_id,cp_node_order_id,cp_node_order_text,continue_item,continue_cp_node_id,continue_order_id,need_item,auto_item,order_kind,sys_is_del,sys_last_update) "+
						"(select t.hospital_id,'"+patientId+"',t.cp_id,t.cp_node_id,t.cp_node_order_id,t.cp_node_order_text,t.continue_item,t.continue_cp_node_id,t.continue_order_id,t.need_item,t.auto_item,t.order_kind,t.sys_is_del,t.sys_last_update from lcp_node_order_point t" +
						" where t.hospital_id = "+hospitalId+" and t.cp_id='"+cpId+"' and t.cp_node_id='"+cpNodeId+"')\r\n";
		
		//lcp_node_order_item的数据同步到lcp_patient_order_item里
	    String synSql3 = "insert into lcp_patient_order_item (hospital_id,patient_no,cp_id,cp_node_id,cp_node_order_id,cp_node_order_item_id,cp_node_order_text,order_no,order_type,order_type_name,order_kind,frequency,way,order_item_set_id,SPECIFICATION,ADMINISTRATION,measure,MEASURE_UNITS,DOSAGE,DOSAGE_UNITS,UNIT_ID,MARK,need_item,auto_item,cp_node_class_id,IS_ANTIBIOTIC,effect_flag,sys_is_del,sys_last_update)"+
	    				 " (select a.hospital_id,'"+patientId+"',a.cp_id,a.cp_node_id, a.cp_node_order_id,a.cp_node_order_item_id,a.CP_NODE_ORDER_TEXT,a.ORDER_NO,a.ORDER_TYPE,a.ORDER_TYPE_NAME,a.ORDER_KIND,a.frequency,a.way,a.order_item_set_id,a.SPECIFICATION,a.ADMINISTRATION,a.measure,a.MEASURE_UNITS,a.DOSAGE,a.DOSAGE_UNITS,a.UNIT_ID,a.MARK,a.need_item,a.auto_item,a.cp_node_class_id,a.IS_ANTIBIOTIC,a.effect_flag,sys_is_del,sys_last_update from lcp_node_order_item a" +
	    				 " where a.hospital_id = "+hospitalId+" and a.cp_id='"+cpId+"' and a.cp_node_id='"+cpNodeId+"')\r\n";
	    
	    String synSql = synSql2 + synSql3;
	    
	    System.out.println(synSql);
	    
	    DatabaseClass dbc = LcpUtil.getDatabaseClass();
	    try {
			result = dbc.FunRunSqlByFile(synSql.getBytes("GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	    
	    
	    
	    
	    
	    
		
		return result;
	}
	
	public static void main(String[] args){
		NodeToNextImpl ntni = new NodeToNextImpl();
		int i = ntni.synOrderData("10146", "2", "428043_1", 1);
		
		System.out.println(i);
	}

}
