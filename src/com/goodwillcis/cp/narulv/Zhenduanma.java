/*----------------------------------------------------------------
//Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
//文件名：CPshidian.java
//文件功能描述：临床路径管理试点调查评估表   自定义数据集
//创建人：潘状
//创建日期：2011/09/08
//
//----------------------------------------------------------------*/

package com.goodwillcis.cp.narulv;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.goodwillcis.cp.util.LcpUtil;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;

import bios.report.engine.api.CustomDataSet;


public class Zhenduanma implements CustomDataSet {

 private String[] metaData;

 private List<Object[]> data;
 private String deptCode="";
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
	 deptCode = (String) params[0];
	metaData = new String[] {
		"路径编码",
		"路径名称",
		"所属科室编码",
		"所属科室",
		"准入条件",  
		"ICD",     
		"类型"
		};
	data = new ArrayList<Object[]>();
	DatabaseClass db = LcpUtil.getDatabaseClass();
	//科室登陆
	String sql = "select t_1.dept_name,t.cp_id,t_1.cp_name,t.cp_income_name, t.cp_income_code,t.cp_income_type from lcp_master_income t join lcp_master t_1 on t.cp_id = t_1.cp_id where t_1.dept_code like '%"+deptCode+"%'";
	DataSetClass ds = db.FunGetDataSetBySQL(sql);
	int count = ds.FunGetRowCount();
	for (int i = 0; i < count; i++) {
		String cp_id = ds.FunGetDataAsStringByColName(i, "CP_ID");
		String cp_name = ds.FunGetDataAsStringByColName(i, "CP_NAME");
		String dept_name = ds.FunGetDataAsStringByColName(i, "DEPT_NAME");
		String cp_income_name = ds.FunGetDataAsStringByColName(i, "CP_INCOME_NAME");
		String cp_income_code = ds.FunGetDataAsStringByColName(i, "CP_INCOME_CODE");
		String cp_income_type = ds.FunGetDataAsStringByColName(i, "CP_INCOME_TYPE");
	    data.add(new Object[] {
	          cp_id,
	          cp_name,
	          deptCode,
	          dept_name,
	          cp_income_name,
	          cp_income_code,
	          cp_income_type
	    });
	}
	
 }
}

