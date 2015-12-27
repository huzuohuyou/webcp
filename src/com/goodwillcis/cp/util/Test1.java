package com.goodwillcis.cp.util;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.cp.util.LcpUtil;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pairentId = "262440_3";
    	DatabaseClass db = LcpUtil.getDatabaseClass();
    	String sql = "select CP_NODE_ID,CP_NODE_ORDER_TEXT,EXE_STATE,ORDER_KIND from LCP_PATIENT_ORDER_POINT where patient_no = '"+pairentId+"' ORDER BY CP_NODE_ID, CP_NODE_ORDER_ID" ;
    	DataSetClass sd = db.FunGetDataSetBySQL(sql);
    	int count = sd.FunGetRowCount();
    	String order_kind = "";
    	for (int i = 0; i < count; i++) {
    	    int cp_node_id = sd.FunGetDataAsNumberByColName(i, "CP_NODE_ID").intValue();
    	    int exe_state = sd.FunGetDataAsNumberByColName(i, "EXE_STATE").intValue();
    	    String leixing = sd.FunGetDataAsStringByColName(i, "ORDER_KIND");
    	    String cp_node_order_text = sd.FunGetDataAsStringByColName(i, "CP_NODE_ORDER_TEXT");
    	    System.out.println(leixing);
    	    if(exe_state==1){
    	    	cp_node_order_text = "■"+cp_node_order_text;
    	    }else{
    	    	cp_node_order_text = "□"+cp_node_order_text;
    	    }
    	    
    	    if("0".equals(leixing)){
    	    	order_kind = "临时医嘱";
    	    }else if("1".equals(leixing)){
    	    	order_kind = "长期医嘱";
    	    }else{
    	    	order_kind = "出院医嘱";
    	    }
	}

}
}
