/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：NodeTable.java    
// 文件功能描述：左边的nodetable相关操作
//
// 创建人：刘植鑫
// 创建日期：2011-06-01
// 修改人:潘状
// 修改原因:需求更改,数据库表结构改变
// 修改日期:2011-7-28
// 完成日期:2011-8-1
 * 
 * 修改 createTable函数，最大节点着色
 * 修改日期：2011-08-26
 
//修改人：林建喜
//修改原因：新增getNodeActualDays函数，取得各节点所用时间
//修改日期：2013-4-15
//完成时间：2013-4-16
//----------------------------------------------------------------*/

/**
 *************	2011-06-01
	函数1、createTable(RouteExecuteInfo info)
			描述：取得LCP_PATIENT_NODE表格内容
			创建日期：2011-06-01
			完成日期：2011-06-01
	函数2、funGetMaxCpNodeIDByCpID(RouteExecuteInfo info)
			描述：获取当前路径最大节点号
			创建日期：2011-06-02
			完成日期：2011-06-02
	函数3、funGetNodeType(RouteExecuteInfo info)
			描述：取得当前路径节点类型
			创建日期：2011-06-03
			完成日期：2011-06-03
	函数4、funOverNode(RouteExecuteInfo info)
			描述：完成最大节点，给最大节点添加上完成时间
			创建日期：2011-06-07
			完成日期：2011-06-07
	函数5、funInsertNodeTable(RouteExecuteInfo info,int cpNodeID)
			描述：从LCP_MASTER_NODE表中查出输入插入到LCP_PATIENT_NODE表中
			创建日期：2011-06-08
			完成日期：2011-06-08
	函数6、funGetNodeType(RouteExecuteInfo info,int cpNodeID)
			描述：从LCP_MASTER_NODE表中查出输入插入到LCP_PATIENT_NODE表中
			创建日期：2011-06-08
			完成日期：2011-06-08
 */
package com.goodwillcis.lcp.model;

import java.io.UnsupportedEncodingException;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

public class NodeTable {

	/**
	 * 取得LCP_PATIENT_NODE表格内容
	 * 2011-06-01
	 * @return 返回table表格中的行
	 */
	public String createTable(RouteExecuteInfo info){
		String tableSql="SELECT T.CP_ID, T.CP_NODE_ID, T.CP_NODE_NAME, T.CP_NODE_START_TIME, " +
				"T.CP_NODE_END_TIME FROM LCP_PATIENT_NODE T " +
				"WHERE T.PATIENT_NO='"+info.getPatientNo()+"' AND T.SYS_IS_DEL=0  AND T.CP_ID="+info.getCpID()+" AND T.HOSPITAL_ID="+info.getHostipalID()+" ORDER BY T.CP_NODE_TYPE,T.CP_NODE_ID";
		//System.out.println("tableSql:"+tableSql);
		String tableHTML="";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(tableSql);
		int row=dataSet.getRowNum();
		String color="#FFFFFF";
		for(int i=0;i<row;i++){		
			if(i==row-1){
				color="#51b2f6";
			}
			tableHTML=tableHTML+"<tr bgcolor='"+color+"' style='cursor:pointer' onmouseover='changeColor(this)'  onmouseout='recoverColor(this)' onClick='NodeColor(this)' class='selectNode' id='"+dataSet.funGetFieldByCol(i, "CP_NODE_ID")+"'>" +
					"<td height='20'><div align='center'>"+(i+1)+"</div></td>" +
					"<td ><div align='center'>"+dataSet.funGetFieldByCol(i, "CP_NODE_NAME")+"</div></td>" +
					"<td ><div align='center'>"+dataSet.funGetFieldByCol(i, "CP_NODE_START_TIME")+"</div></td>" +
					"<td ><div align='center'>"+dataSet.funGetFieldByCol(i, "CP_NODE_END_TIME")+"</div></td>" +
					"</tr>";
		}

		tableHTML=CommonUtil.replactCharacter(tableHTML);
		return tableHTML;
	}
	
	/**
	 * 取得LCP_PATIENT_NODE表格各节点所用时间
	 * @param info
	 * @return
	 */
	public String getNodeActualDays(RouteExecuteInfo info,int cp_node_id){
		String nodeActualDaysSql1 = "select t.cp_node_name,t.cp_node_end_time,t.cp_node_days,t.cp_node_days_max from lcp_patient_node t " +
				"where t.patient_no='"+info.getPatientNo()+"' and t.cp_node_id = '"+cp_node_id+"'";
		DataSet nodeActualDaysdataSet1=new DataSet();
		nodeActualDaysdataSet1.funSetDataSetBySql(nodeActualDaysSql1);
		String cp_node_end_time = nodeActualDaysdataSet1.funGetFieldByCol(0, "cp_node_end_time");
		String cp_node_days = nodeActualDaysdataSet1.funGetFieldByCol(0, "cp_node_days");
		String cp_node_days_max = nodeActualDaysdataSet1.funGetFieldByCol(0, "cp_node_days_max");
		String cp_node_name = nodeActualDaysdataSet1.funGetFieldByCol(0, "cp_node_name");
		String nodeActualDaysSql = "";
		
		if("".equals(cp_node_end_time)){
			nodeActualDaysSql = "select round(sysdate-t.cp_node_start_time) actual_days from lcp_patient_node t " +
					"where t.patient_no='"+info.getPatientNo()+"' and t.cp_node_id = '"+cp_node_id+"'";
		}else{
			nodeActualDaysSql = "select round(to_date('"+cp_node_end_time+"','yyyy-mm-dd hh24:mi:ss')" +
					"-t.cp_node_start_time) actual_days from lcp_patient_node t where t.patient_no='"+info.getPatientNo()+"' and t.cp_node_id = '"+cp_node_id+"'";
		}
		
		DataSet nodeActualDaysdataSet=new DataSet();
		nodeActualDaysdataSet.funSetDataSetBySql(nodeActualDaysSql);
		String nodeActualDays = nodeActualDaysdataSet.funGetFieldByCol(0, "actual_days");
		String nodeDays = "";
		int cp_node_days1 = Integer.parseInt(cp_node_days);
		int cp_node_days_max1 = Integer.parseInt(cp_node_days_max);
		int nodeActualDays1 = Integer.parseInt(nodeActualDays);
		int nodeDaysPass = nodeActualDays1 - cp_node_days_max1;
		if(nodeActualDays1<=cp_node_days1){
			nodeDays = "<td align='center' width='50%' ><span class='STYLE2'>"+nodeActualDays+"</span></td>"; 
		}else if(nodeActualDays1>cp_node_days1 && nodeActualDays1<=cp_node_days_max1){
			nodeDays = "<td align='center' width='50%' bgcolor='yellow'><span class='STYLE2'>"+nodeActualDays+"</span></td>" ;
		}else if(nodeActualDays1>cp_node_days_max1){
			nodeDays = "<td align='center' width='50%' bgcolor='red'><span class='STYLE2'>"+nodeActualDays+"(超过节点最大住院日"+nodeDaysPass+"天)</span></td>";
		}
		String tableHTML = "";
		
		tableHTML = tableHTML + 
		"<tr bgcolor='d3eaef'><th align='center' width='20%' >当前节点</td>" +
		"<th align='center' width='30%'>节点平均住院日</td>" +
		"<th align='center' width='50%'>节点实际住院日</td></tr>" +
		
		"<tr bgcolor='d3eaef'><td align='center' width='20%' ><span class='STYLE2'>"+cp_node_name+"</span></td>" +
		"<td align='center' width='30%'><span class='STYLE2'>"+cp_node_days+"</span></td>" +
		""+nodeDays+"" +
		"</tr>";
		
		
		tableHTML=CommonUtil.replactCharacter(tableHTML);
		return tableHTML;
	}
	
	/**
	 * 获取当前路径最大节点号
	 * @param info
	 * @return
	 */
	public int funGetMaxCpNodeIDByCpID(RouteExecuteInfo info){
		int maxNodeID=0;
		DataSet dataSet1=new DataSet();
		String sql2 = "select cp_node_id from LCP_PATIENT_NODE where PATIENT_NO='"+info.getPatientNo()+"' and CP_ID="+info.getCpID()+" and HOSPITAL_ID="+info.getHostipalID()+" and( cp_node_type=2 or cp_node_type=4)";
		dataSet1.funSetDataSetBySql(sql2);
		int rows = dataSet1.getRowNum();
		if(rows>0){
			maxNodeID=Integer.parseInt(dataSet1.funGetFieldByCol(0, "CP_NODE_ID"));
		}else{
			String cpNodeId="";
			String sql="SELECT CP_NODE_ID FROM LCP_PATIENT_NODE T " +
			"WHERE T.PATIENT_NO='"+info.getPatientNo()+"' " +
			"AND T.CP_ID="+info.getCpID()+" " +
			"AND T.HOSPITAL_ID="+info.getHostipalID();
			DataSet dataSet=new DataSet();
			dataSet.funSetDataSetBySql(sql);
			int counts=dataSet.getRowNum();
			if(counts==1){
				String sql3="SELECT CP_NODE_ID FROM LCP_PATIENT_NODE T " +
				"WHERE T.PATIENT_NO='"+info.getPatientNo()+"' " +
				"AND T.CP_ID="+info.getCpID()+" and cp_node_type=0 " +
				"AND T.HOSPITAL_ID="+info.getHostipalID();
				DataSet dataSet2=new DataSet();
				dataSet2.funSetDataSetBySql(sql3);
				cpNodeId=dataSet2.funGetFieldByCol(0, "CP_NODE_ID");
			}else{
				String sql4="SELECT max(CP_NODE_ID) CP_NODE_ID FROM LCP_PATIENT_NODE T " +
				"WHERE T.PATIENT_NO='"+info.getPatientNo()+"' " +
				"AND T.CP_ID="+info.getCpID()+" and cp_node_type=1 " +
				"AND T.HOSPITAL_ID="+info.getHostipalID();
				DataSet dataSet3=new DataSet();
				dataSet3.funSetDataSetBySql(sql4);
				cpNodeId=dataSet3.funGetFieldByCol(0, "CP_NODE_ID");
			}
			
			try {
				maxNodeID=Integer.parseInt(cpNodeId);
			} catch (Exception e) {
				maxNodeID=0;
			}
		}
		
		return maxNodeID;	
	}
	/**
	 * 取得当前路径节点类型
	 * @param info
	 * @return 节点类型 0准入 1正常节点 2完成节点 3变异4退出 5自定义节点
	 */
	public String funGetNodeType(RouteExecuteInfo info){
		String a="-1";
		String sql="SELECT T.CP_NODE_TYPE FROM LCP_PATIENT_NODE T " +
				"WHERE T.PATIENT_NO='"+info.getPatientNo()+"'" +
				" AND T.SYS_IS_DEL=0 " +
				" AND T.CP_ID="+info.getCpID()+
				" AND T.CP_NODE_ID="+info.getCpNodeID()+
				" AND T.HOSPITAL_ID="+info.getHostipalID();
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row=dataSet.getRowNum();
		if(row>0){
			a=dataSet.funGetFieldByCol(0, "CP_NODE_TYPE");
		}
		return a;
	}
	/**
	 * 查看选中的径节点类型
	 * @param info
	 * @return 节点类型 0准入 1正常节点 2完成节点 3变异4退出 5自定义节点
	 */
	public String funGetNodeType(RouteExecuteInfo info,int cpNodeID){
		String a="-1";
		String sql="SELECT T.CP_NODE_TYPE FROM LCP_MASTER_NODE T " +
				"WHERE T.SYS_IS_DEL=0 " +
				" AND T.CP_ID="+info.getCpID()+
				" AND T.CP_NODE_ID="+cpNodeID+
				" AND T.HOSPITAL_ID="+info.getHostipalID();
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row=dataSet.getRowNum();
		if(row>0){
			a=dataSet.funGetFieldByCol(0, "CP_NODE_TYPE");
		}
		return a;
	}
	/**
	 * 完成最大节点，给最大节点添加上完成时间
	 * @param info
	 * @return
	 */
	public int funOverNode(RouteExecuteInfo info){
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		String key=databaseClass.FunGetSvrKey();
		String time=CommonUtil.getOracleToDate();
		String sql="UPDATE LCP_PATIENT_NODE T SET T.CP_NODE_END_TIME= "+time+"" +
				",CP_NODE_STATE=2,USER_ID='"+info.getVerifyCode()+"',USER_NAME='"+info.getVerifyName()+"' " +
				"WHERE T.PATIENT_NO='"+info.getPatientNo()+"' AND T.SYS_IS_DEL=0 " +
				" AND T.CP_ID="+info.getCpID()+" AND T.CP_NODE_ID="+info.getCpNodeID()+" AND T.HOSPITAL_ID="+info.getHostipalID()+"";
		int a=databaseClass.FunRunSQLCommand(key, sql);
		return a;
	}
	/**
	 * 从LCP_MASTER_NODE表中查出输入插入到LCP_PATIENT_NODE表中
	 * @param info
	 * @param cpNodeID
	 * @return 负数：错误，整数：插入的行数
	 */
	public int funInsertNodeTable(RouteExecuteInfo info,int cpNodeID){
		String sql="SELECT T.HOSPITAL_ID,T.CP_ID,T.CP_NODE_ID," +
				"T.CP_STD_NODE_ID,T.CP_NODE_PARENT_ID,T.CP_NODE_NAME," +
				"T.CP_NODE_DAYS_MIN,T.CP_NODE_DAYS_MAX,T.CP_NODE_DAYS," +
				"T.CP_NODE_TYPE FROM LCP_MASTER_NODE T " +
				"WHERE T.HOSPITAL_ID="+info.getHostipalID()+" AND T.CP_ID="+info.getCpID()+" AND T.CP_NODE_ID="+cpNodeID+"";
		
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row=dataSet.getRowNum();
		String insertSql="";
		String time=CommonUtil.getOracleToDate();
		for(int i=0;i<row;i++){
			String names=dataSet.funGetFieldInsertSQL();
			String values=dataSet.funGetFieldValueInsertSQL(i);
			insertSql=insertSql+"" +
					"INSERT INTO LCP_PATIENT_NODE("+names+",PATIENT_NO," +
					"CP_NODE_START_TIME,CP_NODE_STATE,SYS_IS_DEL,SYS_LAST_UPDATE)" +
					"VALUES("+values+",'"+info.getPatientNo()+"',"+time+",1,0,"+time+")\r\n";
		}
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		int aa=0;
		if(row>0){
			try {
				aa = databaseClass.FunRunSqlByFile(insertSql.getBytes("GB2312"));
			} catch (UnsupportedEncodingException e) {
				TableException.rollBackNode(info, cpNodeID);
				e.printStackTrace();aa=-1;
			}
		}
		return aa;
	}
	/**
	 * 查看是否都已经签名
	 * @param info
	 * @return true 都已经签名
	 */
	public boolean funCheckExecuteNextNode(RouteExecuteInfo info){
		boolean nextNode=true;
		DoctorTable doctorTable=new DoctorTable();
		OrderTable orderTable =new OrderTable();
		NurseTable nurseTable=new NurseTable();
		VariationTable variationTable=new VariationTable();
		nextNode=nextNode&&doctorTable.checkSign(info)&&nurseTable.checkSign(info);//&&nurseTable.checkSign(info)&&variationTable.checkSign(info);暂时把护理和变异给去掉了
		return nextNode;
	}
	/**
	 * 查看是否都已经签名
	 * @param info
	 * @return true 都已经签名
	 */
	public String funGetNoSignTableName(RouteExecuteInfo info){
		boolean nextNode=true;
		DoctorTable doctorTable=new DoctorTable();
		OrderTable orderTable =new OrderTable();
		NurseTable nurseTable=new NurseTable();
		VariationTable variationTable=new VariationTable();
		nextNode=nextNode&&doctorTable.checkSign(info);//&&nurseTable.checkSign(info)&&variationTable.checkSign(info);暂时把护理和变异给去掉了
		String result="";
		if(!nextNode){
			result=result+"诊疗工作 ";
		}
		nextNode=nurseTable.checkSign(info);
		if(!nextNode){
			if(result!=""){
				result=result+"、护理工作";
			}else{
				result=result+"护理工作";
			}
			
		}
		if(result!="")
		result=result+"没有完全签名";
		return result;
	}
	
	/**
	 * 查看是否都已经执行
	 * @param info
	 * @return true 都已经签名
	 */
	public String funGetNoExecuteFieldName(RouteExecuteInfo info){
		boolean nextNode=true;
		DoctorTable doctorTable=new DoctorTable();
		OrderTable orderTable =new OrderTable();
		NurseTable nurseTable=new NurseTable();
		VariationTable variationTable=new VariationTable();
//		nextNode=nextNode&&doctorTable.checkExecuteSign(info);//&&nurseTable.checkSign(info)&&variationTable.checkSign(info);暂时把护理和变异给去掉了
		String result="";
		result=result+doctorTable.checkExecuteSign(info)+nurseTable.checkExecuteSign(info);
		if(!"".equals(result)){
			result=result.substring(0,result.length()-1)+"需要通过电子病历下达对应的文档进行自动签名";
		}
//		if(!nextNode){
//			result=result+"诊疗工作 ";
//		}
//		nextNode=nurseTable.checkExecuteSign(info);
//		if(!nextNode){
//			if(result!=""){
//				result=result+"、护理工作";
//			}else{
//				result=result+"护理工作";
//			}
//			
//		}
//		System.out.println(result);
//		result=result+"没有完全签名";
		return result;
	}
	/**
	 * 最大节点是否是最后节点
	 * @param info
	 * @return
	 */
	public boolean funIsEndNode(RouteExecuteInfo info){
		DataSet dataSet2 = new DataSet();
		String sql="select cp_node_type from LCP_MASTER_NODE where cp_id="+info.getCpID()+" and cp_node_id="+info.getCpNodeID()+" and hospital_id="+info.getHostipalID()+" and( cp_node_type=2 or cp_node_type=4)";

		dataSet2.funSetDataSetBySql(sql);
		int rows = dataSet2.getRowNum();
		if(rows>0){
			return true;
		}else{
//			String sql="SELECT A.CP_ID,A.CP_NODE_ID,A.CP_NEXT_NODE_ID,B.CP_NODE_NAME,B.CP_NODE_DAYS,B.CP_NODE_TYPE  FROM LCP_NODE_RELATE A,LCP_MASTER_NODE B " +
//			"WHERE A.CP_ID="+info.getCpID()+" AND A.CP_NODE_ID="+info.getCpNodeID()+" AND A.CP_ID=B.CP_ID AND A.CP_NEXT_NODE_ID=B.CP_NODE_ID AND (B.CP_NODE_TYPE=2 OR B.CP_NODE_TYPE=4)";
//			System.out.println("slq==:"+sql);
//			DataSet dataSet=new DataSet();
//			dataSet.funSetDataSetBySql(sql);
//			int row=dataSet.getRowNum();
//			if(row<=0){
//				return true;
//			}else{
//				return false;
//			}
			return false;
		}
		
	}
	
	/**
	 * 当前节点是否已完成(特指有结束时间)(如果已完成，那么就不能签名，不能增加)
	 * @param info
	 * @return true 已完成，false 未完成
	 */
	public boolean funIsOverNode(RouteExecuteInfo info){
		String sql="SELECT * FROM LCP_PATIENT_NODE T  WHERE " +
				"T.HOSPITAL_ID="+info.getHostipalID()+" AND T.PATIENT_NO='"+info.getPatientNo()+"' " +
				"AND T.CP_ID="+info.getCpID()+" AND T.CP_NODE_ID="+info.getCpNodeID()+" " +
				"AND T.CP_NODE_END_TIME IS  NULL";
		
		//System.out.println("sql====:"+sql);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row=dataSet.getRowNum();
		if(row>0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 当前节点是否有增加权限(只有没签名的才可以添加)
	 * @param info
	 * @return 
	 */
	public boolean funIsHaveAddButton(RouteExecuteInfo info){
		boolean add=false;
		NodeTable nodeTable=new NodeTable();
		if(info.getCpNodeID()==info.getMaxCpNodeID()){
			String type=nodeTable.funGetNodeType(info);
			if(type.equals("1")){
				add=true;
			}
		}
		return add;
	}
	
	/**
	 * 最大节点是否可以执行下一节点按钮
	 * @param info
	 * @return 1：此节点不是最后节点可以执行下一结点按钮，2，此节点是最后节点，但是没有完成，3，此节点是最后节点，并且已经完成
	 */
	public int funGetMaxNodeType(RouteExecuteInfo info){
		int a=-1;
		boolean isEnd=this.funIsEndNode(info);
		if(isEnd){//当前节点是否是最后节点
			boolean isOver=this.funIsOverNode(info);//当前节点是否完成
			if(isOver){
				a=3;
			}else{
				a=2;
			}
		}else{
			a=1;
		}
		return a;
	}
	
	/**
	 * 更改visit状态信息
	 * @param info
	 * @param type
	 * @return 
	 */
	public int funUpdateVisitStatus(RouteExecuteInfo info,String type){
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		String key=databaseClass.FunGetSvrKey();
		String sql="UPDATE lcp_patient_visit T SET T.CP_STATE= "+type+"" +
				" WHERE T.PATIENT_NO='"+info.getPatientNo()+"' AND T.SYS_IS_DEL=0" +
						"  AND T.HOSPITAL_ID="+info.getHostipalID()+"";
		int a=databaseClass.FunRunSQLCommand(key, sql);
		return a;
	}
}
