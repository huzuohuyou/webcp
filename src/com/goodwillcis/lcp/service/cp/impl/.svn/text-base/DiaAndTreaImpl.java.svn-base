// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：DiaAndTreaImpl .java
// 文件功能描述：诊断和手术接口实现类
// 创建人：刘植鑫 
// 创建日期：2011/08/02
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.cp.impl;

import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.service.cp.DiaAndTrea;

public class DiaAndTreaImpl implements DiaAndTrea {

	@Override
	public String funGetDiaTable(int hostipalID, int cp_id) {
		// TODO Auto-generated method stub
		String sql="SELECT T.CP_DIAGNOSIS_BASED FROM LCP_MASTER_BASED T WHERE T.HOSPITAL_ID="+hostipalID+" AND T.CP_ID="+cp_id+"";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cp_diagnosis_based=dataSet.funGetFieldByCol(i, "CP_DIAGNOSIS_BASED");
			table=cp_diagnosis_based;
		}		
		return table;
	}

	@Override
	public String funGetTreaTable(int hostipalID, int cp_id) {
		// TODO Auto-generated method stub
		String sql="SELECT T.CP_TREATMENT FROM LCP_MASTER_BASED T WHERE T.HOSPITAL_ID="+hostipalID+" AND T.CP_ID="+cp_id+"";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cp_treatment=dataSet.funGetFieldByCol(i, "CP_TREATMENT");
			table=cp_treatment;
		}
		return table;
	}

}
