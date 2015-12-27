// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：PatientFamilyImpl .java
// 文件功能描述：家属接口实现类
// 创建人：刘植鑫 
// 创建日期：2011/08/03
// 修改数据显示问题
// 修改时间：2011-08-25
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.cp.impl;

import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.service.cp.PatientFamily;

public class PatientFamilyImpl implements PatientFamily {

	@Override
	public String funGetTable(int hostipalID, int cp_id, int cp_node_id) {
		// TODO Auto-generated method stub
		String sql="SELECT T.CP_NODE_FAMILY_TEXT FROM LCP_NODE_FAMILY_POINT T WHERE T.CP_ID="+cp_id+" AND T.HOSPITAL_ID="+hostipalID+" AND T.CP_NODE_ID='"+cp_node_id+"' ORDER BY CP_NODE_FAMILY_ID ASC";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cp_node_family_text=dataSet.funGetFieldByCol(i, "CP_NODE_FAMILY_TEXT");
			table=table+"<tr bgcolor='#FFFFFF'>"+
			"<td height='20'  align='right' class='STYLE6'><div align='left'><span class='STYLE10'>&nbsp;&nbsp;&nbsp;"+cp_node_family_text+"</span></div></td>"+
			"</tr>";
		}
		return table;
	}

	@Override
	public String funGetNodeItemTable(int hostipalID, int cp_id,
			int cp_node_id, int cp_node_doctor_id, int CP_NODE_DOCTOR_ITEM_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean funSetNodeItem(int hostipalID, int cp_id, int cp_node_id,
			int cp_node_doctor_id, int CP_NODE_DOCTOR_ITEM_ID, int auto_item) {
		// TODO Auto-generated method stub
		return false;
	}

}
