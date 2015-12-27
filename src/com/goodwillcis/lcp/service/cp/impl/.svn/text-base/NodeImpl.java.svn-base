// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：NodeImpl .java
// 文件功能描述：节点接口实现类
// 创建人：刘植鑫 
// 创建日期：2011/08/03
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.cp.impl;

import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.service.cp.Node;

public class NodeImpl implements Node {

	@Override
	public String funGetTable(int hostipalID, int cp_id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM LCP_MASTER_NODE T WHERE T.HOSPITAL_ID="+hostipalID+" AND T.CP_ID="+cp_id+" ORDER BY T.CP_NODE_ID";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cp_node_id=dataSet.funGetFieldByCol(i, "CP_NODE_ID");
			String cp_node_name=dataSet.funGetFieldByCol(i, "CP_NODE_NAME");
			String cp_node_days_max=dataSet.funGetFieldByCol(i, "CP_NODE_DAYS_MAX");
			table=table+"<tr name='node' id='"+cp_node_id+"' bgcolor='#FFFFFF' onmouseover='changeColor(this)'  onmouseout='recoverColor(this)' onclick='onclickOnNextNodeColor(this)' style='cursor:hand' >"+
			"<td height='20'  align='right' class='STYLE6'><div align='left'><span class='STYLE10'>&nbsp;&nbsp;&nbsp;"+cp_node_id+"</span></div></td>"+
			"<td height='20'  align='right' class='STYLE6'><div align='left'><span class='STYLE10'>&nbsp;&nbsp;&nbsp;"+cp_node_name+"</span></div></td>"+
			"<td height='20'  align='right' class='STYLE6'><div align='left'><span class='STYLE10'>&nbsp;&nbsp;&nbsp;"+cp_node_days_max+"</span></div></td>"+
			"</tr>";
		}
		return table;
	}

	@Override
	public boolean funIsExistNodeIDByCPID(int hostipalID, int cp_id) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(*)	HANG FROM LCP_MASTER_NODE T WHERE T.HOSPITAL_ID="+hostipalID+" AND T.CP_ID="+cp_id+"";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String hang=dataSet.funGetFieldByCol(0, "HANG");
		if(hang.equals(0))
			return false;
		return true;
	}

}
