/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：MyDataSetParam.java
// 文件功能描述：皕杰报表自定义数据集示例代码
// 创建人：潘状
// 创建日期：2011/09/03
// 
//----------------------------------------------------------------*/

package com.goodwillcis.cp.cpinfo;

import java.util.ArrayList;
import java.util.List;

import bios.report.engine.api.CustomDataSet;

import com.goodwillcis.cp.util.LcpUtil;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;


public class LCPNode implements CustomDataSet {

    private String[] metaData;

    private List<Object[]> data;

    // 参数列表,必须与设计器端的参数数量匹配
    private String cpID;

  
   
    @Override
	public String[] getMetaData() {

	return metaData;
    }
   
   
    @Override
	public int getRowCount() {
	return data.size();
    }

    
   
    @Override
	public Object[] getRowData(int rowIndex) {
	return data.get(rowIndex);
    }

    
    @Override
	public void applyParams(Object[] params) {
	cpID = (String) params[0];
	metaData = new String[] { "CP_NODE_ID", "CP_NODE_NAME" };
	data = new ArrayList<Object[]>();
	DatabaseClass db = LcpUtil.getDatabaseClass();
	String sql = "select cp_node_id, cp_node_name from lcp_master_node where cp_id = "+ cpID+" and cp_node_type in (1,2) order by cp_node_id " ;
	DataSetClass sd = db.FunGetDataSetBySQL(sql);
	int count = sd.FunGetRowCount();
	for (int i = 0; i < count; i++) {
	    int cp_node_id = sd.FunGetDataAsNumberByColName(i, "CP_NODE_ID").intValue();
	    String cp_node_name = sd.FunGetDataAsStringByColName(i, "CP_NODE_NAME");
	   // System.out.println(cp_node_name);
	    data.add(new Object[] { cp_node_id, cp_node_name });
	}
	
    }

    
   
}
