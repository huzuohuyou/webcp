// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：NodeIncludeAndExcludeImpl .java
// 文件功能描述：节点输入输出接口实现类
// 创建人：刘植鑫 
// 创建日期：2011/08/03
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.cp.impl;

import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.service.cp.NodeIncludeAndExclude;

public class NodeIncludeAndExcludeImpl implements NodeIncludeAndExclude {

	@Override
	public String funGetNodeInTable(int hostipalID, int cp_id, int cp_node_id) {
		// TODO Auto-generated method stub
		String sql="SELECT A.CP_NODE_NAME   FROM LCP_MASTER_NODE A,      " +
				"  (SELECT *           FROM LCP_NODE_RELATE B        " +
				"  WHERE B.HOSPITAL_ID = "+hostipalID+"            AND B.CP_ID = "+cp_id+"    " +
				"       AND B.CP_NEXT_NODE_ID = "+cp_node_id+" ) C  WHERE A.HOSPITAL_ID = C.HOSPITAL_ID  " +
				"  AND A.CP_ID = C.CP_ID    AND A.CP_NODE_ID = C.CP_NODE_ID  ";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cp_node_name=dataSet.funGetFieldByCol(i, "CP_NODE_NAME");
			table=table+"<tr bgcolor='#FFFFFF'>"+
			"<td height='20'  align='center' class='STYLE6'><div align='center'><span class='STYLE10'>"+cp_node_name+"</span></div></td>"+
			"</tr>";
		}
		return table;
	}

	@Override
	public String funGetNodeOutTable(int hostipalID, int cp_id, int cp_node_id) {
		// TODO Auto-generated method stub
		String sql="SELECT A.CP_NODE_NAME   FROM LCP_MASTER_NODE A,      " +
				"  (SELECT *           FROM LCP_NODE_RELATE B        " +
				"  WHERE B.HOSPITAL_ID = "+hostipalID+"            AND B.CP_ID = "+cp_id+"    " +
				"       AND B.CP_NODE_ID = "+cp_node_id+" ) C  WHERE A.HOSPITAL_ID = C.HOSPITAL_ID  " +
				"  AND A.CP_ID = C.CP_ID    AND A.CP_NODE_ID = C.CP_NEXT_NODE_ID  ";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cp_node_name=dataSet.funGetFieldByCol(i, "CP_NODE_NAME");
			table=table+"<tr bgcolor='#FFFFFF'>"+
			"<td height='20'  align='center' class='STYLE6'><div align='center'><span class='STYLE10'>"+cp_node_name+"</span></div></td>"+
			"</tr>";
		}
		return table;
	}

}
