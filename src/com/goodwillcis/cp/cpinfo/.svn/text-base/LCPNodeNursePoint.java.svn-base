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


public class LCPNodeNursePoint implements CustomDataSet {

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
	
	
	/*HOSPITAL_ID	NUMBER(5)	N			医院编号
	CP_ID	NUMBER(5)	N			路径编号
	CP_NODE_ID	NUMBER(5)	N			路径节点编号
	CP_NODE_NURSE_ID	NUMBER(5)	N			路径节点护理编号
	CP_NODE_NURSE_TEXT	VARCHAR2(100)	Y			路径节点护理工作内容
	NEED_ITEM	NUMBER(4)	Y			必做项目（0可选项，1必做项）
	AUTO_ITEM	NUMBER(4)	Y			自动检测项（0自动，1手动）
	VERIFY_DATE	DATE	Y			审核时间（有时间表示已经审核过了）
	VERIFY_CODE	VARCHAR2(20)	Y			审核人编号
	VERIFY_NAME	VARCHAR2(50)	Y			审核人名称
	SYS_IS_DEL	NUMBER(4)	N			是否已经删除
	SYS_LAST_UPDATE	DATE	N			最新更新时间*/
	cpID = (String) params[0];
	metaData = new String[] { "CP_NODE_ID", "CP_NODE_NURSE_ID","CP_NODE_NURSE_TEXT" };
	data = new ArrayList<Object[]>();
	DatabaseClass db = LcpUtil.getDatabaseClass();
	String sql = "select CP_NODE_ID, CP_NODE_NURSE_ID,CP_NODE_NURSE_TEXT from LCP_NODE_NURSE_POINT where CP_ID =  "+ cpID+" ORDER BY CP_NODE_ID, CP_NODE_NURSE_ID" ;
	
	DataSetClass sd = db.FunGetDataSetBySQL(sql);
	int count = sd.FunGetRowCount();
	for (int i = 0; i < count; i++) {
	    int cp_node_id = sd.FunGetDataAsNumberByColName(i, "CP_NODE_ID").intValue();
	    int cp_node_doctor_id = sd.FunGetDataAsNumberByColName(i, "CP_NODE_NURSE_ID").intValue();
	    String cp_node_doctor_text = sd.FunGetDataAsStringByColName(i, "CP_NODE_NURSE_TEXT");
	    data.add(new Object[] { cp_node_id, cp_node_doctor_id,cp_node_doctor_text });
	}
	
    }

}
