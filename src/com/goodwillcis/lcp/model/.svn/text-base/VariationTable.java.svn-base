/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：VariationTable.java    
// 文件功能描述：Variation(变异记录)相关操作相关操作
//
// 创建人：刘植鑫
// 创建日期：2011-06-01
// 修改人:潘状
// 修改原因:需求更改,数据库表结构改变
// 修改日期:2011-8-1   添加删除未签名变异信息功能  delRecord()
// 完成日期:2011-8-1
//----------------------------------------------------------------*/

package com.goodwillcis.lcp.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

public class VariationTable {
	/**
	 * 取得LCP_PATIENT_Variation 表格内容
	 * 2011-06-01
	 * @return 返回table表格中的行
	 */
	public String createTable(RouteExecuteInfo info){
	String tableSql="select t.*,L.VARIATION_NAME from lcp_patient_variation t,dcp_dict_variation l" +
	" WHERE t.cp_node_variation_code=l.variation_code(+) and T.PATIENT_NO = '"+info.getPatientNo()+"'   AND T.HOSPITAL_ID = "+info.getHostipalID()+"" +
	" AND T.SYS_IS_DEL=0  AND T.CP_ID="+info.getCpID()+"  AND CP_NODE_ID="+info.getCpNodeID()+" ORDER BY CP_NODE_VARIATION_ID";
	String tableHTML="";
	DataSet dataSet=new DataSet();
	dataSet.funSetDataSetBySql(tableSql);
	int row=dataSet.getRowNum();
	if(info.isExecute()){
		for(int i=0;i<row;i++){	
			String bgColor="#FFFFFF";
			String trName;
			boolean isSign=dataSet.funGetFieldByCol(i,"USER_NAME").length()>0?true:false;
			if(isSign){
				bgColor="#51b2f6";
				trName="unChangeColorVariation";
			}else{
				trName="changeColorVariation";//没有签名
			}
			tableHTML=tableHTML+"<tr name='"+trName+"' id='tr"+dataSet.funGetFieldByCol(i, "CP_NODE_VARIATION_ID")+"' height='20' bgcolor='"+bgColor+"' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
			
			"<td><div align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+dataSet.funGetFieldByCol(i, "CP_NODE_VARIATION_TEXT")+"</div></td>" +
			"<td><div align='center'>"+dataSet.funGetFieldByCol(i, "VARIATION_NAME")+"</div></td>" +
			"<td><div align='center'>"+dataSet.funGetFieldByCol(i, "USER_NAME")+"</div></td>" +
			"<td><div align='center'>"+dataSet.funGetFieldByCol(i, "EXE_DATE")+"</div></td>" +
			"</tr>";
		}
	}else{
		for(int i=0;i<row;i++){	
			String bgColor="#FFFFFF";
			boolean isSign=dataSet.funGetFieldByCol(i,"USER_NAME").length()>0?true:false;
			if(isSign){
				bgColor="#51b2f6";
			}		
			tableHTML=tableHTML+"<tr name='doctorTR' id='tr"+dataSet.funGetFieldByCol(i, "CP_NODE_VARIATION_ID")+"'   height='20' bgcolor='"+bgColor+"' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
			
			"<td><div align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+dataSet.funGetFieldByCol(i, "CP_NODE_VARIATION_TEXT")+"</div></td>" +
			"<td><div align='center'>"+dataSet.funGetFieldByCol(i, "CP_NODE_VARIATION_ID")+"</div></td>" +
			"<td><div align='center'>"+dataSet.funGetFieldByCol(i, "USER_NAME")+"</div></td>" +
			"</tr>";
		}
		
	}
	tableHTML=CommonUtil.replactCharacter(tableHTML);
	return tableHTML;
}
	/**
	 * 签名功能
	 * @param info
	 * @param idLists 修改的那几行
	 * @param time 数据库当前时间
	 * @return 修改的记录行数
	 */
	public int signTable(RouteExecuteInfo info,ArrayList<String> idLists,String extTime,String time){
		String ids="";
		for(int i=0;i<idLists.size();i++){
			ids=ids+idLists.get(i)+",";
		}
		if(ids.length()>0)
		ids=ids.substring(0, ids.length()-1);
		String sql="UPDATE LCP_PATIENT_VARIATION SET USER_ID='"+info.getVerifyCode()+"' ," +
				" USER_NAME='"+info.getVerifyName()+"' ," +
				" EXE_DATE="+extTime+" ," +
				" SYS_LAST_UPDATE="+time+" WHERE " +
				" HOSPITAL_ID="+info.getHostipalID()+" " +
				" AND PATIENT_NO='"+info.getPatientNo()+"'" +
				" AND CP_ID="+info.getCpID()+"" +
				" AND CP_NODE_ID="+info.getCpNodeID()+"" +
				" AND CP_NODE_VARIATION_ID IN ("+ids+")";
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		String key=databaseClass.FunGetSvrKey();
		int a=databaseClass.FunRunSQLCommand(key, sql);
		return a;
	}

	/**
	 * 从LCP_NODE_VARIATION表中查出输入插入到LCP_PATIENT_VARIATION中
	 * @param info
	 * @param cpNodeID
	 * @return 负数：错误，整数：插入的行数
	 */
	public int InsertVariationTable(RouteExecuteInfo info,int cpNodeID){
		String sql="SELECT T.HOSPITAL_ID,T.CP_ID,T.CP_NODE_ID,T.CP_NODE_VARIATION_ID," +
				"T.CP_STD_VARIATION_ID,T.CP_NODE_VARIATION_TEXT FROM LCP_NODE_VARIATION T " +
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
					"INSERT INTO LCP_PATIENT_VARIATION("+names+",PATIENT_NO," +
					"SYS_IS_DEL,SYS_LAST_UPDATE)" +
					"VALUES("+values+",'"+info.getPatientNo()+"',0,"+time+")\r\n";
		}
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		int aa=0;
		if(row>0){
			try {
				aa = databaseClass.FunRunSqlByFile(insertSql.getBytes("GB2312"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				aa=-1;
				TableException.rollBackAll(info, cpNodeID);
			}
		}
		return aa;
	}
	/**
	 * 查看此节点是否已经签名
	 * @param info
	 * @return true 已经签名，false 未全部签名
	 */
	public boolean checkSign(RouteExecuteInfo info){
		String sql="SELECT T.*  FROM lcp_patient_variation T " +
		"WHERE T.PATIENT_NO = '"+info.getPatientNo()+"'   AND T.HOSPITAL_ID = "+info.getHostipalID()+"" +
				" AND T.SYS_IS_DEL=0 AND T.CP_ID="+info.getCpID()+" AND T.USER_NAME IS NULL AND CP_NODE_ID="+info.getMaxCpNodeID()+"";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int a=dataSet.getRowNum();//一共多少行
		if(a>0){
			return false;
		}
		return true;
	}

	/**
	 * 插入一条变异记录
	 * @param info
	 * @param varContent 变异内容
	 * @return
	 */
	public int InsertOneRecord(RouteExecuteInfo info,String varContent,String varCode){
		int ID=funGetMaxRecord(info)+1;
		String time=CommonUtil.getOracleToDate();
		String sql="INSERT INTO LCP_PATIENT_VARIATION T " +
		"(T.HOSPITAL_ID,PATIENT_NO,CP_ID,CP_NODE_ID," +
		"CP_NODE_VARIATION_ID,CP_NODE_VARIATION_CODE," +
		"CP_NODE_VARIATION_TEXT,SYS_IS_DEL,SYS_LAST_UPDATE)" +
		"VALUES("+info.getHostipalID()+",'"+info.getPatientNo()+"'," +
		""+info.getCpID()+","+info.getCpNodeID()+","+ID+",'"+varCode+"','"+varContent+"',0,"+time+")";
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		String key=databaseClass.FunGetSvrKey();
		int a=databaseClass.FunRunSQLCommand(key, sql);
		return a;
	}
	
	/**
	 * 插入一条变异退出记录
	 * @param info
	 * @param varContent 变异内容
	 * @param varCode 变异编码
	 * @return
	 */
	public int insertVariationExit(RouteExecuteInfo info,String varContent,String varCode,String cpNodeID){
		String sqlMax="SELECT MAX(T.CP_NODE_VARIATION_ID)ID FROM LCP_PATIENT_VARIATION T" +
		" WHERE T.PATIENT_NO = '"+info.getPatientNo()+"'   AND T.HOSPITAL_ID = "+info.getHostipalID()+"" +
		" AND T.SYS_IS_DEL=0  AND T.CP_ID="+info.getCpID()+"  AND CP_NODE_ID="+cpNodeID+" ORDER BY CP_NODE_VARIATION_ID";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sqlMax);
		String temp=dataSet.funGetFieldByCol(0, "ID");
		int ID=1;
		if(!"".equals(temp)){
			ID=Integer.parseInt(temp)+1;
		}
	
		
		String time=CommonUtil.getOracleToDate();
		
		String sql="INSERT INTO LCP_PATIENT_VARIATION T " +
		"(T.HOSPITAL_ID,PATIENT_NO,CP_ID,CP_NODE_ID," +
		"CP_NODE_VARIATION_ID,CP_NODE_VARIATION_CODE," +
		"CP_NODE_VARIATION_TEXT,SYS_IS_DEL,SYS_LAST_UPDATE,USER_ID,USER_NAME,EXE_DATE)" +
		"VALUES("+info.getHostipalID()+",'"+info.getPatientNo()+"'," +
		""+info.getCpID()+","+cpNodeID+","+ID+",'"+varCode+"','"+varContent+"',0,"+time+",'"+info.getVerifyCode()+"','"+info.getVerifyName()+"',"+time+")";

		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		String key=databaseClass.FunGetSvrKey();
		int a=databaseClass.FunRunSQLCommand(key, sql);
		String updateSql="update lcp_patient_visit set attending_doctor='"+info.getVerifyName()+"' where patient_no='"+info.getPatientNo()+"'";
		databaseClass.FunRunSQLCommand(databaseClass.FunGetSvrKey(), updateSql);
		return a;
	}
	
	/**
	 * 删除选定的变异记录 
	 * @param info
	 * @param idLists
	 * @param extTime
	 * @param time
	 * @return
	 */
	public int delRecord(RouteExecuteInfo info,ArrayList<String> idLists){
		String ids="";
		for(int i=0;i<idLists.size();i++){
			ids=ids+idLists.get(i)+",";
		}
		if(ids.length()>0)
		ids=ids.substring(0, ids.length()-1);
		String sql="delete from LCP_PATIENT_VARIATION  WHERE " +
				" HOSPITAL_ID="+info.getHostipalID()+" " +
				" AND PATIENT_NO='"+info.getPatientNo()+"'" +
				" AND CP_ID="+info.getCpID()+"" +
				" AND CP_NODE_ID="+info.getCpNodeID()+"" +
				" AND CP_NODE_VARIATION_ID IN ("+ids+")";
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		String key=databaseClass.FunGetSvrKey();
		int a=databaseClass.FunRunSQLCommand(key, sql);
		return a;
		
		
	}
	/**
	 * 获取当前路径节点下的记录最大值ID
	 * @param info
	 * @return
	 */
	private int funGetMaxRecord(RouteExecuteInfo info){
		String sql="SELECT MAX(T.CP_NODE_VARIATION_ID)ID FROM LCP_PATIENT_VARIATION T" +
		" WHERE T.PATIENT_NO = '"+info.getPatientNo()+"'   AND T.HOSPITAL_ID = "+info.getHostipalID()+"" +
		" AND T.SYS_IS_DEL=0  AND T.CP_ID="+info.getCpID()+"  AND CP_NODE_ID="+info.getCpNodeID()+" ORDER BY CP_NODE_VARIATION_ID";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String a=dataSet.funGetFieldByCol(0, "ID");
		int aa=Integer.parseInt(a);
		return aa;
	}

}
