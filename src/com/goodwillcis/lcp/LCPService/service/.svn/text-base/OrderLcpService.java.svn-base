/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： OrderLcpService .java
// 文件功能描述：自动签名调用函数接口
// 创建人：刘植鑫 
// 创建日期：2011/07/27
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.LCPService.service;

import java.util.ArrayList;

import com.goodwillcis.lcp.model.RouteExecuteInfo;

public interface OrderLcpService {

	/**
	 * 取得LCP_PATIENT_Order表中的OrderID	2011-06-21
	 * @param OrderNo
	 * @param info
	 * @return
	 */
	public String funGetCpNodeOrderID(String OrderNo,RouteExecuteInfo info);
	/**
	 * 签名patientOrder表 中对应的自动检测项	2011-06-22
	 * @param userID
	 * @param userName
	 * @param info
	 * @param OrderNo
	 */
	public void funSignPatientOrder(String userID,String userName,RouteExecuteInfo info,ArrayList<String> OrderNos,String time);
	/**
	 * 根据OrderNos查询patientOrder中相同的OrderNo
	 * @param info
	 * @param OrderNos
	 * @return
	 */
	public ArrayList<String>funGetSameOrderNo(RouteExecuteInfo info,ArrayList<String> OrderNos);
}
