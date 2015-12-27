/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： MatchOrderImpl.java
// 文件功能描述：医嘱匹配表调用的各种方法实现类
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
import com.goodwillcis.lcp.service.match.MatchOrder;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

public class MatchOrderImpl implements MatchOrder {
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
			String code=dataSet.funGetFieldByCol(i, "ORDER_ITEM_CODE");
			String name=dataSet.funGetFieldByCol(i, "ORDER_ITEM_NAME");
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
		String sql=" SELECT A.ORDER_ITEM_CODE,A.ORDER_ITEM_NAME, T.* FROM LCP_MATCH_ORDER T,DCP_DICT_ORDER_ITEM A  WHERE T.ORDER_CODE = A.ORDER_ITEM_CODE AND T.ORDER_CODE='"+code+"'";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String localCodes=dataSet.funGetOneFieldStringValues("LOCAL_CODE");
//		System.out.println(localCodes);
		//查找本地编码的名称
		MatchLocal localTable=new MatchLocalImpl();
		ArrayList<LocalDBMatch> localList=new ArrayList<LocalDBMatch>();
		if(localCodes!=""){
			String localSql="SELECT * FROM lcp_local_order T WHERE ORDER_CODE IN("+localCodes+")";
			localList=localTable.funGetCoreList(localSql, "ORDER_CODE", "ORDER_NAME", "INPUT_CODE_PY", "INPUT_CODE_WB");
		}
		//用于显示的表格信息
		String table="";
		int listRow=localList.size();
		if(listRow>0){
			for(int ii=0;ii<listRow;ii++){
				String zxbm=dataSet.funGetFieldByCol(0, "ORDER_ITEM_CODE");
				String zxmc=dataSet.funGetFieldByCol(0, "ORDER_ITEM_NAME");
				zxmc=zxmc.replace("\\", "\\\\");
				table=table+"<tr name='youxiajiao'  style='cursor:hand'  id='"+zxbm+"_and_"+localList.get(ii).getCode()+"' bgcolor='#FFFFFF' onclick='onclickColor(this)' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
				"<td><div align='center'>"+zxbm+"</div></td>" +
				"<td><div align='center'>"+zxmc+"</div></td>" +
				"<td><div align='center'>"+localList.get(ii).getCode()+"</div></td>" +
				"</tr>";
			}
		}
	//	System.out.println(table);
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
		String sql="SELECT COUNT(*)HANG FROM LCP_MATCH_ORDER Tt WHERE ORDER_CODE='"+code+"'";
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
			String linshiSql="SELECT COUNT(*)HANG FROM LCP_MATCH_ORDER T WHERE T.LOCAL_CODE='"+localCode+"'";
			dataSet.funSetDataSetBySql(linshiSql);
			int hang=Integer.parseInt(dataSet.funGetFieldByCol(0, "HANG"));
			if(hang>=1){
				sql="";
				local_Code_result=local_Code_result+localCode+",";
			}
			
			
			sql=sql+"INSERT INTO LCP_MATCH_ORDER (HOSPITAL_ID,ORDER_CODE,LOCAL_CODE,MATCH_TYPE,SYS_IS_DEL,SYS_LAST_UPDATE) " +
			"values("+hospitalID+",'"+coreCode+"','"+localCode+"',0,0,"+time+")\r\n";
			sql= sql+"update dcp_dict_order_item t set t.match_flag=1 where t.order_item_code='"+coreCode+"'\r\n";
			sql= sql+"update lcp_local_order t set t.match_flag=1 where t.order_code='"+localCode+"'\r\n";
		}
	//	System.out.println(sql);
		if(local_Code_result!=""){
			local_Code_result=local_Code_result.substring(0, local_Code_result.length()-1);
			setLocalCode_result(local_Code_result);
			return -2;
		}
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
		String sql="UPDATE LCP_MATCH_ORDER T SET T.MATCH_TYPE="+type+" ,T.SYS_LAST_UPDATE="+time+" WHERE ORDER_CODE='"+code+"'";
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
			sql=sql+"DELETE LCP_MATCH_ORDER T WHERE ORDER_CODE='"+coreCode+"' AND T.LOCAL_CODE='"+localCode+"'\r\n";
			sql=sql+"update lcp_local_order t set t.match_flag=0 where t.order_code='"+localCode+"'\r\n";
		}
		
		DatabaseClass database=LcpUtil.getDatabaseClass();
		int aa=-1;
		try {
			aa=database.FunRunSqlByFile(sql.getBytes("GB2312"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dictSql="select count(*) hang from dcp_dict_order_item a, lcp_match_order b where a.order_item_code=b.order_code and a.order_item_code='"+coreCode+"'";
		DataSet dictDataSet=new DataSet();
		dictDataSet.funSetDataSetBySql(dictSql);
		int dict_match_row=Integer.parseInt(dictDataSet.funGetFieldByCol(0, "HANG"));
		if(dict_match_row==0){
			sql="update dcp_dict_order_item t set t.match_flag=0 where t.order_item_code='"+coreCode+"'\r\n";
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
		sql="SELECT COUNT(*)HANG FROM LCP_PATIENT_LOG_ORDER T WHERE T.LOCAL_CODE IN("+localCode+")AND ORDER_NO ='"+coreCode+"' ";
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
