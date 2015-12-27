/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： NurseLcpServiceImpl .java
// 文件功能描述：自动签名调用函数接口实现类
// 创建人：刘植鑫 
// 创建日期：2011/07/27
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.LCPService.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.LCPService.service.NurseLcpService;
import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.model.RouteExecuteInfo;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

public class NurseLcpServiceImpl implements NurseLcpService {
	private static Logger logger = Logger.getLogger ( NurseLcpServiceImpl.class.getName () ) ; 
	@Override
	public String funGetCpNodeNURSEID(String NURSENo, RouteExecuteInfo info) {
		String sql="SELECT T.CP_NODE_NURSE_ID FROM LCP_PATIENT_NURSE_ITEM T " +
				"WHERE T.HOSPITAL_ID="+info.getHostipalID()+" AND T.PATIENT_NO='"+info.getPatientNo()+"'" +
						" AND T.CP_ID="+info.getCpID()+" AND " +
				"T.CP_NODE_ID="+info.getCpNodeID()+" AND T.NURSE_NO='"+NURSENo+"'";
		DataSet dataSet=new DataSet();
		String NURSEID="";
		try {
			dataSet.funSetDataSetBySql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			logger.fatal("nurseID查询异常，sql语句为sql="+sql);
		}
		
		NURSEID=dataSet.funGetFieldByCol(0, "CP_NODE_NURSE_ID");
		if(NURSEID==""||NURSEID==null){
			return null;
		}
		return NURSEID;
	}

	@Override
	public void funSignPatientNURSE(String userID, String userName,
			RouteExecuteInfo info, ArrayList<String> NURSENos, String time) {
		ArrayList<String>NURSENosnew=funGetSameNURSENo(info, NURSENos);
		String sql="";
		if(NURSENosnew!=null&&NURSENosnew.size()>0){
			String nurse_nos=CommonUtil.funGetStringByArraylist(NURSENosnew, true);
			sql=sql+"UPDATE LCP_PATIENT_NURSE_ITEM SET" +
					" USER_ID='"+userID+"'," +
					" USER_NAME='"+userName+"'," +
				" EXE_DATE="+time+"," +
				" SYS_LAST_UPDATE="+time+"" +
				" WHERE " +
				" HOSPITAL_ID="+info.getHostipalID()+" " +
				" AND PATIENT_NO='"+info.getPatientNo()+"'" +
				" AND CP_ID="+info.getCpID()+"" +
				" AND CP_NODE_ID="+info.getCpNodeID()+"" +
				" AND NURSE_NO IN("+nurse_nos+")" +
				" AND AUTO_ITEM=0 " +
				"\r\n";
			DataSet dataSet=new DataSet();
			String sql1="SELECT T.CP_NODE_NURSE_ID FROM LCP_PATIENT_NURSE_ITEM T WHERE T.HOSPITAL_ID="+info.getHostipalID()+" AND T.CP_ID="+info.getCpID()+" " +
						"AND T.PATIENT_NO='"+info.getPatientNo()+"' AND T.CP_NODE_ID="+info.getCpNodeID()+" AND" +
								" T.NURSE_NO IN("+nurse_nos+")";
			dataSet.funSetDataSetBySql(sql1);
			String cp_node_nurse_id =dataSet.funGetOneFieldStringValues("CP_NODE_NURSE_ID");
			sql=sql+"UPDATE LCP_PATIENT_NURSE_POINT SET" +
					" USER_ID='"+userID+"'," +
					" USER_NAME='"+userName+"'," +
					" EXE_STATE="+1+"," +
				" EXE_DATE="+time+"," +
				" SYS_LAST_UPDATE="+time+"" +
				" WHERE " +
				" HOSPITAL_ID="+info.getHostipalID()+" " +
				" AND PATIENT_NO='"+info.getPatientNo()+"'" +
				" AND CP_ID="+info.getCpID()+"" +
				" AND CP_NODE_ID="+info.getCpNodeID()+"" +
				" AND CP_NODE_NURSE_ID IN("+cp_node_nurse_id+")" +
				" AND AUTO_ITEM=0 " +
				"\r\n";
		}
		
		if(NURSENosnew!=null&&NURSENosnew.size()>0){
			DatabaseClass class1=LcpUtil.getDatabaseClass();
		try {
			int row1=class1.FunRunSqlByFile(sql.getBytes("GB2312"));
		} catch (Exception e) {
			logger.fatal("自动签名更新异常，sql语句为sql="+sql);
		}}
	}

	@Override
	public ArrayList<String> funGetSameNURSENo(RouteExecuteInfo info,
			ArrayList<String> NURSENos) {
		String sql="SELECT T.NURSE_NO FROM LCP_PATIENT_NURSE_ITEM T WHERE" +
				" T.HOSPITAL_ID="+info.getHostipalID()+" AND T.PATIENT_NO='"+info.getPatientNo()+"' " +
				"AND T.CP_ID="+info.getCpID()+" AND T.CP_NODE_ID="+info.getCpNodeID();
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		ArrayList<String>_NURSENos=dataSet.funGetOneFieldListValues("NURSE_NO");
		CommonUtil util=new CommonUtil();
		ArrayList<String>NURSENosNew=util.funGetSameValues(NURSENos, _NURSENos);
		return NURSENosNew;
	}

}
