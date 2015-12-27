package com.goodwillcis.lcp.util;

import java.util.HashMap;
import java.util.Map;

import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.model.JiekouModel;
public class JiekouUtils {

	public static HashMap<String,JiekouModel > fungetCode(DataSet dataSet){
		
		HashMap<String,JiekouModel > map=new HashMap<String, JiekouModel>();
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			JiekouModel model=new JiekouModel();
			String code=dataSet.funGetFieldByCol(i, "CODE");
			String table_id=dataSet.funGetFieldByCol(i, "TABLE_ID");
			String table_item_id=dataSet.funGetFieldByCol(i, "TABLE_ITEM_ID");
			model.setCp_node_table_id(table_id);
			model.setCp_node_table_item_id(table_item_id);
			model.setCode(code);
			map.put(code, model);
		}
		
		return map;
	}
/**
 * 
 * @param dataSet
 * @return local_code,core_code
 */
public static HashMap<String,String > fungetCode1(DataSet dataSet){
		
		HashMap<String,String > map=new HashMap<String, String>();
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			JiekouModel model=new JiekouModel();
			String local_code=dataSet.funGetFieldByCol(i, "LOCAL_CODE");
			String core_code=dataSet.funGetFieldByCol(i, "CORE_CODE");
			map.put(local_code, core_code);
		}
		
		return map;
	}
}
