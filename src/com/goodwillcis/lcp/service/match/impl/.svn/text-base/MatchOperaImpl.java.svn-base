/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： MatchOperaImpl.java
// 文件功能描述：手术匹配表调用的各种方法实现类
// 创建人：刘植鑫 
// 创建日期：2011/07/26
//  * 
 * 数据库字段发生变化修改sql语句和更新标志位
 * 修改时间：2011-08-25
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.match.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.model.LocalDBMatch;
import com.goodwillcis.lcp.model.RouteExecuteInfo;
import com.goodwillcis.lcp.service.match.MatchLocal;
import com.goodwillcis.lcp.service.match.MatchOpera;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

public class MatchOperaImpl implements MatchOpera {
	private String localCode_result;
	public void setLocalCode_result(String localCode_result) {
		this.localCode_result = localCode_result;
	}
	@Override
	public String funCreateTable(String sql, int start, int end) {
		// TODO Auto-generated method stub
		String table="";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql,start,end);
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String code=dataSet.funGetFieldByCol(i, "OPERATION_CODE");
			String name=dataSet.funGetFieldByCol(i, "OPERATION_NAME");
			String inputCodePY=dataSet.funGetFieldByCol(i, "INPUT_CODE_PY");
			String inputCodeWB=dataSet.funGetFieldByCol(i, "INPUT_CODE_WB");
			String match=dataSet.funGetFieldByCol(i, "MATCH_FLAG");
			if(match.equals("1")){
				match="√";
			}else{
				match="";
			}
			name=name.replace("\\", "\\\\");
			table=table+"<tr name='zuocelan'   style='cursor:hand' mingcheng='"+name+"'  id='"+code+"' onclick='zuocelanOnclick(this)' bgcolor='#FFFFFF' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
			"<td><div align='left'>"+code+"</div></td>" +
			"<td><div align='left'>"+name+"</div></td>" +
			"<td><div align='left'>"+inputCodePY+"</div></td>" +
			"<td><div align='left'>"+inputCodeWB+"</div></td>" +	
			"<td><div align='center'>"+match+"</div></td>" +	
			"</tr>";
		}
		return table.replace("\"", "&#34;");
	}

	@Override
	public String funCreateMatchTable(String code) {
		// TODO Auto-generated method stub
		String sql=" SELECT T.*,OPERATION_NAME FROM LCP_MATCH_OPERATION T,DCP_DICT_OPERATION A  WHERE T.OPERATION_CODE=A.OPERATION_CODE AND T.OPERATION_CODE='"+code+"'";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String localCodes=dataSet.funGetOneFieldStringValues("LOCAL_CODE");
//		System.out.println(localCodes);
		//查找本地编码的名称
		MatchLocal localTable=new MatchLocalImpl();
		ArrayList<LocalDBMatch> localList=new ArrayList<LocalDBMatch>();
		if(localCodes!=""){
			String localSql="SELECT * FROM lcp_local_operation T WHERE OPERATION_CODE IN("+localCodes+")";
			localList=localTable.funGetCoreList(localSql, "OPERATION_CODE", "OPERATION_NAME", "INPUT_CODE_PY", "INPUT_CODE_WB");
		}
		//用于显示的表格信息
		String table="";
		int listRow=localList.size();
		int dataSet_row=dataSet.getRowNum();

		if(dataSet_row>0){
			for(int ii=0;ii<dataSet_row;ii++){
				String zxbm=dataSet.funGetFieldByCol(0, "OPERATION_CODE");
				String zxmc=dataSet.funGetFieldByCol(0, "OPERATION_NAME");
				String local_code=dataSet.funGetFieldByCol(ii, "LOCAL_CODE");
				zxmc=zxmc.replace("\\", "\\\\");
				table=table+"<tr name='youxiajiao'  style='cursor:hand'  id='"+zxbm+"_and_"+local_code+"' bgcolor='#FFFFFF' onclick='onclickColor(this)' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
				"<td><div align='center'>"+zxbm+"</div></td>" +
				"<td><div align='center'>"+zxmc+"</div></td>" +
				"<td><div align='center'>"+local_code+"</div></td>" +
				"</tr>";
			}
		}
//		System.out.println(table);
		return table.replace("\"", "&#34;");
	}

	@Override
	public int funGetCount(String sql) {
		// TODO Auto-generated method stub
		String sql1="SELECT COUNT(*)HANG FROM ("+sql+") T";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql1);
		int aa=Integer.parseInt(dataSet.funGetFieldByCol(0, "HANG"));
		return aa;
	}

	@Override
	public int funGetCountByCode(String code) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(*)HANG FROM LCP_MATCH_OPERATION Tt WHERE OPERATION_CODE='"+code+"'";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int aa=Integer.parseInt(dataSet.funGetFieldByCol(0, "HANG"));
		return aa;
	}

	@Override
	public int funPiPeiInsert(String IDS) {
		// TODO Auto-generated method stub
		String sql="";
		IDS=IDS.substring(0, IDS.length()-2);
		String[] IDSArr=IDS.split(":;");
		int len=IDSArr.length;
		String coreCode="";
		RouteExecuteInfo info=new RouteExecuteInfo();
		int hospitalID=info.getHostipalIDFromSYS();
		String time=CommonUtil.getOracleToDate();
		String local_Code_result="";
		for(int i=0;i<len;i++){
			String coreCodeAndLocalCode=IDSArr[i];
			String [] coreCodeAndLocalCodeArr=coreCodeAndLocalCode.split("_and_");
			coreCode=coreCodeAndLocalCodeArr[0];
			String localCode=coreCodeAndLocalCodeArr[1];
			
			
			DataSet dataSet=new DataSet();
			String linshiSql="SELECT COUNT(*)HANG FROM LCP_MATCH_OPERATION T WHERE T.LOCAL_CODE='"+localCode+"'";
			dataSet.funSetDataSetBySql(linshiSql);
			int hang=Integer.parseInt(dataSet.funGetFieldByCol(0, "HANG"));
			if(hang>=1){
				sql="";
				local_Code_result=local_Code_result+localCode+",";
			}
			
			
			sql=sql+"INSERT INTO LCP_MATCH_OPERATION (HOSPITAL_ID,OPERATION_CODE,LOCAL_CODE,MATCH_TYPE,SYS_IS_DEL,SYS_LAST_UPDATE) " +
			"values("+hospitalID+",'"+coreCode+"','"+localCode+"',0,0,"+time+")\r\n";
			sql= sql+"update dcp_dict_OPERATION t set t.match_flag=1 where OPERATION_CODE='"+coreCode+"'\r\n";
			sql= sql+"update LCP_LOCAL_OPERATION t set t.match_flag=1 where OPERATION_CODE='"+localCode+"'\r\n";
		}
		if(local_Code_result!=""){
			local_Code_result=local_Code_result.substring(0, local_Code_result.length()-1);
			setLocalCode_result(local_Code_result);
			return -2;
		}
//		System.out.println(sql);
		DatabaseClass database=LcpUtil.getDatabaseClass();
		int aa=-1;
		try {
			aa=database.FunRunSqlByFile(sql.getBytes("GB2312"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			aa=-1;
		}
		return aa;
	}

	@Override
	public int funUpdateYDD(String code) {
		// TODO Auto-generated method stub
		int row=this.funGetCountByCode(code);
		int type=0;
		if(row>1){
			type=1;
		}
		DatabaseClass database=LcpUtil.getDatabaseClass();
		String key=database.FunGetSvrKey();
		String time=CommonUtil.getOracleToDate();
		String sql="UPDATE LCP_MATCH_OPERATION T SET T.MATCH_TYPE="+type+" ,T.SYS_LAST_UPDATE="+time+" WHERE OPERATION_CODE='"+code+"'";
		return database.FunRunSQLCommand(key, sql);
	}

	@Override
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
			sql=sql+"DELETE LCP_MATCH_OPERATION T WHERE OPERATION_CODE='"+coreCode+"' AND T.LOCAL_CODE='"+localCode+"'\r\n";
			sql=sql+"update LCP_LOCAL_OPERATION t set t.match_flag=0 where OPERATION_CODE='"+localCode+"'\r\n";
		}
//		System.out.println(sql);
		DatabaseClass database=LcpUtil.getDatabaseClass();
		int aa=-1;
		try {
			aa=database.FunRunSqlByFile(sql.getBytes("GB2312"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String dictSql="select count(*) hang from dcp_dict_OPERATION a, LCP_MATCH_OPERATION b where A.OPERATION_CODE=b.OPERATION_CODE and a.OPERATION_CODE='"+coreCode+"'";
		DataSet dictDataSet=new DataSet();
		dictDataSet.funSetDataSetBySql(dictSql);
		int dict_match_row=Integer.parseInt(dictDataSet.funGetFieldByCol(0, "HANG"));
		if(dict_match_row==0){
			sql="update dcp_dict_OPERATION t set t.match_flag=0 where OPERATION_CODE='"+coreCode+"'\r\n";
		}
		System.out.println(sql);
		dictDataSet.funRunSql(sql);
		
		return aa;
	}

	@Override
	public int funFindIsUsedBeforeDel(String IDS) {
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
		sql="SELECT COUNT(*)HANG FROM LCP_PATIENT_LOG_INCOME T WHERE T.LOCAL_CODE IN("+localCode+") AND T.INCOME_CODE='"+coreCode+"' AND T.INCOME_TYPE='手术'";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row=Integer.parseInt(dataSet.funGetFieldByCol(0, "HANG"));
		return row;
	}

	@Override
	public String funGetLocalCodeMatched() {
		// TODO Auto-generated method stub
		return this.localCode_result;
	}

}
