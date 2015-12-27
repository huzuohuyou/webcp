/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： MatchCPImpl.java
// 文件功能描述：路径匹配表调用的各种方法实现类
// 创建人：张昆
// 创建日期：2012/05/10
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.match.impl;

import java.io.UnsupportedEncodingException;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.service.match.MatchCP;
import com.goodwillcis.lcp.util.LcpUtil;

public class MatchCPImpl implements MatchCP {

	private String localCode_result;
	public void setLocalCode_result(String localCode_result) {
		this.localCode_result = localCode_result;
	}

	public String funCreateTable(String sql, int start, int end) {
		// TODO Auto-generated method stub
		String table="";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql,start,end);
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cpID=dataSet.funGetFieldByCol(i, "CP_ID");
			String cpName=dataSet.funGetFieldByCol(i, "CP_NAME");
			String cpCreateDate=dataSet.funGetFieldByCol(i, "CP_CREATE_DATE");
			String cpMasterID=dataSet.funGetFieldByCol(i, "CP_MASTER_ID");
			if(cpMasterID.equals("0")){
				cpMasterID="";
			}else{
				cpMasterID="√";
			}
			cpName=cpName.replace("\\", "\\\\");
			table=table+"<tr name='zuocelan'   style='cursor:hand' mingcheng='"+cpName+"'  id='"+cpID+"' onclick='zuocelanOnclick(this)' bgcolor='#FFFFFF' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
			"<td><div align='center'>"+cpID+"</div></td>" +
			"<td><div align='left'>"+cpName+"</div></td>" +
			"<td><div align='center'>"+cpCreateDate+"</div></td>" +	
			"<td><div align='center'>"+cpMasterID+"</div></td>" +	
			"</tr>";
		}
		return table.replace("\"", "&#34;");
	}

	public String funCreateMatchTable(String cpID) {
		// TODO Auto-generated method stub
		String sql=" SELECT a.cp_id,t.cp_name TName,t.cp_id as ACPID FROM lcp_master t,dcp_master a  WHERE t.cp_master_id=a.cp_id and t.cp_master_id='"+cpID+"'";
		//System.out.println("sql===:"+sql);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
//		String localCodes=dataSet.funGetOneFieldStringValues("CP_ID");
//		//System.out.println(localCodes);
		//查找本地编码的名称
//		MatchLocal localTable=new MatchLocalImpl();
//		ArrayList<LocalDBMatch> localList=new ArrayList<LocalDBMatch>();
//		if(localCodes!=""){
//			String localSql="SELECT T.cp_ID,T.CP_NAME,T.INPUT_CODE_PY,T.INPUT_CODE_WB FROM LCP_MASTER T WHERE CP_ID IN("+localCodes+")";
//			localList=localTable.funGetCoreList(localSql, "CP_ID", "CP_NAME", "INPUT_CODE_PY", "INPUT_CODE_WB");
//		}
		//用于显示的表格信息
		String table="";

		int dataSet_row=dataSet.getRowNum();
		if(dataSet_row>0){
			for(int ii=0;ii<dataSet_row;ii++){
				String cPID=dataSet.funGetFieldByCol(ii, "CP_ID");
				String cpName=dataSet.funGetFieldByCol(ii, "TName");
				String localCPID=dataSet.funGetFieldByCol(ii, "ACPID");
				cpName=cpName.replace("\\", "\\\\");
				table=table+"<tr name='youxiajiao' height='12' style='cursor:hand'  id='"+cPID+"_and_"+localCPID+"' bgcolor='#FFFFFF' onclick='onclickColor(this)' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
				"<td><div align='center'>"+cPID+"</div></td>" +
				"<td><div align='center'>"+cpName+"</div></td>" +
				"<td><div align='center'>"+localCPID+"</div></td>" +
				"</tr>";
			}
			//System.out.println("table=:"+table);
		}
//		//System.out.println(table);
		return table.replace("\"", "&#34;");
	}

	public int funGetCount(String sql) {
		// TODO Auto-generated method stub
		String sql1="SELECT COUNT(*)HANG FROM ("+sql+") T";
		//System.out.println(sql1);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql1);
		int aa=Integer.parseInt(dataSet.funGetFieldByCol(0, "HANG"));
		return aa;
	}

	
	public int funGetCountByCode(String code) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(*)HANG FROM LCP_MASTER T WHERE CP_MASTER_ID='"+code+"'";
		//System.out.println("sqlsqlsql===:"+sql);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int aa=Integer.parseInt(dataSet.funGetFieldByCol(0, "HANG"));
		return aa;
	}

	
	public int funPiPeiInsert(String IDS) {
		DatabaseClass database=LcpUtil.getDatabaseClass();
		String sql="";
		String sql2="";
		IDS=IDS.substring(0, IDS.length()-2);
		String[] IDSArr=IDS.split(":;");
		int len=IDSArr.length;
		String coreCode="";
		for(int i=0;i<len;i++){
			String coreCodeAndLocalCode=IDSArr[i];
			String [] coreCodeAndLocalCodeArr=coreCodeAndLocalCode.split("_and_");
			coreCode=coreCodeAndLocalCodeArr[0];
			String localCode=coreCodeAndLocalCodeArr[1];
			sql= sql+"update lcp_master t set t.cp_master_id="+coreCode+" where cp_id="+localCode+"\r\n";
			sql= sql+"update dcp_master t set t.cp_master_id=1 where cp_id="+coreCode+"\r\n";
		}
		
		int aa=-1;
		int res=-1;
		try {
			aa=database.FunRunSqlByFile(sql.getBytes("GB2312"));
			if(aa >0){
				String selectSql = "select cp_id from lcp_master where  cp_master_id="+coreCode+" order by cp_create_date";
				DataSetClass dataSet = database.FunGetDataSetBySQL(selectSql);
				for(int j=0;j<dataSet.FunGetRowCount();j++){
					String id=dataSet.FunGetDataAsStringByColName(j, "CP_ID");
					sql2= sql2+"update lcp_master t set t.cp_version="+(j+1)+" where cp_id="+id+"\r\n";
					res=database.FunRunSqlByFile(sql2.getBytes("GB2312"));
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res=-1;
		}
		return res;
	}
	
	public String funGetLocalCodeMatched() {
		// TODO Auto-generated method stub
		return this.localCode_result;
	}

	
	public int funDelMatch(String IDS) {
		// TODO Auto-generated method stub
		String sql="";
		IDS=IDS.substring(0, IDS.length()-2);
		String[] IDSArr=IDS.split(":;");
		int len=IDSArr.length;
		String coreCode="";
		for(int i=0;i<len;i++){
			String coreCodeAndLocalCode=IDSArr[i];
			String [] coreCodeAndLocalCodeArr=coreCodeAndLocalCode.split("_and_");
			coreCode=coreCodeAndLocalCodeArr[0];
			String localCode=coreCodeAndLocalCodeArr[1];
			sql=sql+"update LCP_MASTER t set t.cp_master_id =0 where CP_ID='"+localCode+"'\r\n";
		}
		DatabaseClass database=LcpUtil.getDatabaseClass();
		int aa=-1;
		try {
			aa=database.FunRunSqlByFile(sql.getBytes("GB2312"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String dictSql="select count(*) hang from dcp_master a, LCP_MASTER b where A.CP_ID=b.CP_MASTER_ID and a.CP_ID='"+coreCode+"'";
		DataSet dictDataSet=new DataSet();
		dictDataSet.funSetDataSetBySql(dictSql);
		int dict_match_row=Integer.parseInt(dictDataSet.funGetFieldByCol(0, "HANG"));
		if(dict_match_row==0){
			sql="update dcp_master t set t.cp_master_id =0 where cp_id='"+coreCode+"'\r\n";
		}
		//System.out.println(sql);
		dictDataSet.funRunSql(sql);
		
		return aa;
	}
	
	public int funFindIsUsedBeforeDel(String IDS) {
		DatabaseClass database=LcpUtil.getDatabaseClass();
		// TODO Auto-generated method stub
		String sql="";
		IDS=IDS.substring(0, IDS.length()-2);
		String[] IDSArr=IDS.split(":;");
		int len=IDSArr.length;
		String coreCode="";
		String localCode="";
		for(int i=0;i<len;i++){
			String coreCodeAndLocalCode=IDSArr[i];
			String [] coreCodeAndLocalCodeArr=coreCodeAndLocalCode.split("_and_");
			coreCode=coreCodeAndLocalCodeArr[0];
			localCode=localCode+"'"+coreCodeAndLocalCodeArr[1]+"',";
		}
		if(len>0){
			localCode=localCode.substring(0, localCode.length()-1);
		}
		sql+="update lcp_master set cp_master_id=0 where cp_id in("+localCode+")";
		int res= database.FunRunSqlByFile(sql.getBytes());
		int update=-1;
		int returnValue=-1;
		if(res>0){
			String sql2="select cp_id from dcp_master where cp_master_id ="+coreCode+"";
			if(database.FunGetDataSetBySQL(sql2).FunGetDataAsStringById(0, 0) == ""){
				String sql3="update dcp_master set cp_master_id=0 where cp_id ="+coreCode+"";
				update= database.FunRunSqlByFile(sql3.getBytes());
				returnValue=update;
			}else{
				returnValue=res;
			}
		}else{
			returnValue=res;
		}
		return returnValue;
		
	}

}
