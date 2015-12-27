/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： OrderLcpServiceImpl .java
// 文件功能描述：自动签名调用函数接口实现类
// 创建人：刘植鑫 
// 创建日期：2011/07/27
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.LCPService.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.LCPService.service.OrderLcpService;
import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.model.RouteExecuteInfo;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

public class OrderLcpServiceImpl implements OrderLcpService {

	@Override
	public String funGetCpNodeOrderID(String OrderNo, RouteExecuteInfo info) {
		String sql="SELECT T.CP_NODE_ORDER_ID FROM LCP_PATIENT_ORDER_ITEM T " +
				"WHERE T.HOSPITAL_ID="+info.getHostipalID()+" AND T.PATIENT_NO='"+info.getPatientNo()+"'" +
						" AND T.CP_ID="+info.getCpID()+" AND " +
				"T.CP_NODE_ID="+info.getCpNodeID()+" AND T.ORDER_NO='"+OrderNo+"'";
		DataSet dataSet=new DataSet();
		String OrderID="";
		dataSet.funSetDataSetBySql(sql);
		OrderID=dataSet.funGetFieldByCol(0, "CP_NODE_ORDER_ID");
		if(OrderID==""||OrderID==null){
			return null;
		}
		return OrderID;
	}

	@Override
	public void funSignPatientOrder(String userID, String userName,
			RouteExecuteInfo info, ArrayList<String> OrderNos, String time) {
		ArrayList<String>OrderNosnew=funGetSameOrderNo(info, OrderNos);
		String sql="";
		if(OrderNosnew!=null){
			for(int i=0;i<OrderNosnew.size();i++){
				sql=sql+"update LCP_PATIENT_ORDER_ITEM set" +
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
						" AND ORDER_NO="+OrderNosnew.get(i)+"" +
						" AND AUTO_ITEM=0 " +
						"\r\n";
			}
			
			if(OrderNosnew.size()>0){
				DatabaseClass class1=LcpUtil.getDatabaseClass();
			try {
				class1.FunRunSqlByFile(sql.getBytes("GB2312"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}}
		}
	}

	@Override
	public ArrayList<String> funGetSameOrderNo(RouteExecuteInfo info,
			ArrayList<String> OrderNos) {
		String sql="SELECT T.ORDER_NO FROM LCP_PATIENT_ORDER_ITEM T WHERE" +
				" T.HOSPITAL_ID="+info.getHostipalID()+" AND T.PATIENT_NO='"+info.getPatientNo()+"' " +
				"AND T.CP_ID="+info.getCpID()+" AND T.CP_NODE_ID="+info.getCpNodeID();
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		ArrayList<String>_OrderNos=dataSet.funGetOneFieldListValues("ORDER_NO");
		CommonUtil util=new CommonUtil();
		ArrayList<String>OrderNosNew=util.funGetSameValues(OrderNos, _OrderNos);
		return OrderNosNew;	
	}

}
