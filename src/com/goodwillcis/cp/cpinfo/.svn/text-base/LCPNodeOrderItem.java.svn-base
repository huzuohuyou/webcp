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


public class LCPNodeOrderItem implements CustomDataSet {

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
	
	
	/*
	HOSPITAL_ID	NUMBER(5)	N			医院编号
	CP_ID	NUMBER(5)	N			路径编号
	CP_NODE_ID	NUMBER(5)	N			路径节点编号
	CP_NODE_ORDER_ID	NUMBER(5)	N			路径节点医嘱项编号
	CP_NODE_ORDER_ITEM_ID	NUMBER(5)	N			路径节点医嘱明细项编号
	CP_NODE_ORDER_TEXT	VARCHAR2(100)	Y			路径节点医嘱明细项内容
	ORDER_NO	VARCHAR2(20)	Y			医嘱编码
	ORDER_TYPE	VARCHAR2(20)	Y			医嘱类型（0临时医嘱 1长期医嘱 2出院医嘱）
	NEED_ITEM	NUMBER(4)	Y			必做项目（0可选项，1必做项）
	VERIFY_DATE	DATE	Y			审核时间（有时间表示已经审核过了）
	VERIFY_CODE	VARCHAR2(20)	Y			审核人编号
	VERIFY_NAME	VARCHAR2(50)	Y			审核人名称
	SYS_IS_DEL	NUMBER(4)	Y			是否已经删除
	SYS_LAST_UPDATE	DATE	Y			最新更新时间
	AUTO_ITEM	NUMBER(4)	Y			自动检测项（0自动，1手动）
	ORDER_TYPE_NAME	VARCHAR2(100)	Y			医嘱类别描述
	ORDER_KIND	VARCHAR2(20)	Y			医嘱类型（0临时医嘱、1长期医嘱、2出院医嘱）*/
	
	
	cpID = (String) params[0];
	metaData = new String[] { "CP_NODE_ID", "CP_NODE_ORDER_ID","ORDER_KIND","ORDER_TYPE_NAME","CP_NODE_ORDER_TEXT" };
	data = new ArrayList<Object[]>();
	DatabaseClass db = LcpUtil.getDatabaseClass();
	String sql = "select CP_NODE_ID, CP_NODE_ORDER_ID, ORDER_KIND, ORDER_TYPE_NAME, CP_NODE_ORDER_TEXT"+
                        " from LCP_NODE_ORDER_ITEM "+
                        " where CP_ID = "+
                         cpID+" order by CP_NODE_ORDER_ID, CP_NODE_ORDER_ITEM_ID " ;
	System.out.println(sql);
	DataSetClass sd = db.FunGetDataSetBySQL(sql);
	int count = sd.FunGetRowCount();
	for (int i = 0; i < count; i++) {
	    int cp_node_id = sd.FunGetDataAsNumberByColName(i, "CP_NODE_ID").intValue();
	    int cp_node_doctor_id = sd.FunGetDataAsNumberByColName(i, "CP_NODE_ORDER_ID").intValue();
	    String ORDER_KIND = sd.FunGetDataAsStringByColName(i, "ORDER_KIND");
	    String ORDER_TYPE_NAME = sd.FunGetDataAsStringByColName(i, "ORDER_TYPE_NAME");
	    String CP_NODE_ORDER_TEXT = sd.FunGetDataAsStringByColName(i, "CP_NODE_ORDER_TEXT");
	    data.add(new Object[] { cp_node_id, cp_node_doctor_id,ORDER_KIND ,ORDER_TYPE_NAME,CP_NODE_ORDER_TEXT});
	}
    }

}
