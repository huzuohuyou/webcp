/*----------------------------------------------------------------
//Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
//文件名：NodeNurse.java
//文件功能描述：在页面显示护理第一层跟第二层信息
//创建人：段英华 
//创建日期：2011/08/10
//
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.model.cpmanage;

import com.goodwillcis.lcp.util.LcpUtil;

public class NodeFamily {
	private final int HOSPITALID=LcpUtil.getHospitalID();
	public String funGetPointTable(String cp_id,String cp_node_id)
	{
		String sql="SELECT * FROM LCP_NODE_FAMILY_POINT T WHERE  CP_ID="+cp_id+" AND CP_NODE_ID="+cp_node_id+" AND HOSPITAL_ID="+HOSPITALID +" order by CP_NODE_FAMILY_ID";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cp_node_family_id=dataSet.funGetFieldByCol(i, "CP_NODE_FAMILY_ID");
			String cp_node_family_text=dataSet.funGetFieldByCol(i, "CP_NODE_FAMILY_TEXT");
			String need_item=dataSet.funGetFieldByCol(i, "NEED_ITEM");
			need_item=(need_item.equals("0"))?"":"&radic;";
			
				table=table+"<tr style='cursor:pointer' class='STYLE10' align='left'  id='"+cp_id+"_"+cp_node_id+"_"+cp_node_family_id+"' bgcolor='#FFFFFF'onmouseover='changeColor(this)'  onMouseOut='recoverColor(this)' onclick='showfamilyitem("+cp_id+","+cp_node_id+","+cp_node_family_id+",this);'>"+
				"<td  align='center' class='STYLE10'>"+cp_node_family_id+"</td>"+
				"<td height='20' ><span  id='2_1_"+cp_id+"_"+cp_node_id+"_"+cp_node_family_id+"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+cp_node_family_text+"</span></td>"+
				"<td height='20' align='center'>"+need_item+"</td>"+
				"</tr>";
			}
		return table;
	}
}
