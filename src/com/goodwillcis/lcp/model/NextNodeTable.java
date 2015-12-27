/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：NextNodeTable.java    
// 文件功能描述：执行下一节点相关操作
//
// 创建人：刘植鑫
// 创建日期：2011-06-01
// 修改人:潘状
// 修改原因:需求更改,数据库表结构改变
// 修改日期:2011-7-28
// 完成日期:2011-8-1
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.model;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;
public class NextNodeTable {

	/**
	 * 执行下一节点弹出表格
	 * @param info
	 * @param cpNodeId
	 * @return
	 */
	public String createNextNodeTable(RouteExecuteInfo info){
		String sql="SELECT A.CP_ID,A.CP_NODE_ID,A.CP_NEXT_NODE_ID,B.CP_NODE_NAME,B.CP_NODE_DAYS,B.CP_NODE_TYPE  FROM LCP_NODE_RELATE A,LCP_MASTER_NODE B " +
				"WHERE A.CP_ID="+info.getCpID()+" AND A.CP_NODE_ID="+info.getMaxCpNodeID()+" AND A.CP_ID=B.CP_ID AND A.CP_NEXT_NODE_ID=B.CP_NODE_ID ORDER BY B.CP_NODE_TYPE,A.CP_NEXT_NODE_ID";
		String tableHTML="";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){		
			tableHTML=tableHTML+"<tr name='nextNodeTR' id='tr"+dataSet.funGetFieldByCol(i, "CP_NEXT_NODE_ID")+"'  height='20' bgcolor='#FFFFFF' class='STYLE19' onmouseover='changeColor(this)'onmouseout='recoverColor(this)'>" +
			"<td  onclick='onclickOnNextNodeColor(this)' ><div align='center'>"+dataSet.funGetFieldByCol(i, "CP_NEXT_NODE_ID")+"</div></td>" +				
			"<td  onclick='onclickOnNextNodeColor(this)' ><div align='center'>"+dataSet.funGetFieldByCol(i, "CP_NODE_NAME")+"</div></td>" +
			"<td  onclick='onclickOnNextNodeColor(this)' ><div align='center'>"+dataSet.funGetFieldByCol(i, "CP_NODE_DAYS")+"</div></td>" +
			"</tr>";
		}
		tableHTML=CommonUtil.replactCharacter(tableHTML);
		return tableHTML;
	}

	
	public int insertAllTable(RouteExecuteInfo info,int cpNodeID){
		DatabaseClass db = LcpUtil.getDatabaseClass();
		int aa=-1;
		NodeTable nodeTable=new NodeTable();
		DoctorTable doctorTable=new DoctorTable();
		NurseTable nurseTable=new NurseTable();
		OrderTable orderTable=new OrderTable();
		FamilyTable familyTable=new FamilyTable();
		String type=nodeTable.funGetNodeType(info, cpNodeID);
		VariationTable variationTable=new VariationTable();
		int aa_nodeTable=nodeTable.funInsertNodeTable(info, cpNodeID);//插入节点信息
		int aa_doctorTable=0;
		int aa_nurseTable=0;
		int aa_orderTable=0;
		int aa_variationTable=0;
		int aa_familyTable=0;
		int nextNodeId=0;
		if(aa_nodeTable>0){
			if(type.equals("1")){//正常节点
				nextNodeId=db.FunGetDataSetBySQL("select cp_next_node_id from lcp_node_relate where hospital_id="+info.getHostipalID()+" and cp_id="+info.getCpID()+" and cp_node_id="+info.getCpNodeID()+" and cp_next_node_id <> (select cp_node_id from lcp_master_node where hospital_id= "+info.getHostipalID()+" and cp_id="+info.getCpID()+" and cp_node_type=4)").FunGetDataAsNumberById(0, 0).intValue();
				String selectSql="select cp_node_order_id from lcp_patient_order_point where hospital_id="+info.getHostipalID()+" and patient_no='"+info.getPatientNo()+"' and cp_id="+info.getCpID()+" and cp_node_id="+nextNodeId+"";
				int rows = db.FunGetDataSetBySQL(selectSql).FunGetRowCount();
				if(rows == 0){
					aa_orderTable=orderTable.InsertOrderTable(info, cpNodeID);//更新医嘱表，暂时舍去，先留着以防以后使用 时间 2011-10-14
				}
				aa_doctorTable=doctorTable.InsertDoctorTable(info, cpNodeID);
				aa_nurseTable=nurseTable.InsertNurseTable(info, cpNodeID);
				aa_familyTable=familyTable.InsertFamilyTable(info, cpNodeID);
				aa_variationTable=variationTable.InsertVariationTable(info, cpNodeID);
			}else if(type.equals("3")){//变异退出节点
				return 0;
			}
			if(aa_doctorTable>=0&&aa_nurseTable>=0&&aa_orderTable>=0&&aa_familyTable>=0&&aa_variationTable>=0){
				aa=1;
			}else{
				TableException.rollBackAll(info, cpNodeID);
				aa=-1;
			}
		}
		return aa;
	}
		

}
