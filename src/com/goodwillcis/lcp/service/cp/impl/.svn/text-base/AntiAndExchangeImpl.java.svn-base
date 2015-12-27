// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：AntiAndExchangeImpl .java
// 文件功能描述：预防性药物和出院接口实现类
// 创建人：刘植鑫 
// 创建日期：2011/08/02
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.cp.impl;

import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.service.cp.AntiAndExchange;

public class AntiAndExchangeImpl implements AntiAndExchange {

	@Override
	public String funGetAntTable(int hostipalID, int cp_id) {
		// TODO Auto-generated method stub
		String sql="SELECT T.CP_ANTIBIOTICS FROM LCP_MASTER_ANTIBIOTICS t WHERE T.HOSPITAL_ID="+hostipalID+" AND T.CP_ID="+cp_id+"";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cp_antibiotics=dataSet.funGetFieldByCol(i, "CP_ANTIBIOTICS");
			table=cp_antibiotics;
		}		
		return table;
	}

	@Override
	public String funGetExcharge(int hostipalID, int cp_id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM LCP_MASTER_DISCHARGE T WHERE T.CP_ID="+cp_id+" AND T.HOSPITAL_ID="+hostipalID+"";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cp_discharge_name=dataSet.funGetFieldByCol(i, "CP_DISCHARGE_NAME");
			table=table+"<tr bgcolor='#FFFFFF'>"+
			"<td height='20'  align='right' class='STYLE6'><div align='left'><span class='STYLE10'>&nbsp;&nbsp;&nbsp;"+cp_discharge_name+"</span></div></td>"+
			"</tr>";
		}
		return table;
	}

}
