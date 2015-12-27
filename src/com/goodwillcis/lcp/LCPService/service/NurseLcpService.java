/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： NurseLcpService .java
// 文件功能描述：自动签名调用函数接口
// 创建人：刘植鑫 
// 创建日期：2011/07/27
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.LCPService.service;

import java.util.ArrayList;

import com.goodwillcis.lcp.model.RouteExecuteInfo;

public interface NurseLcpService {

	/**
	 * 取得LCP_PATIENT_NURSE_ITEM表中的NURSEID	2011-06-21
	 * @param NURSENo
	 * @param info
	 * @return
	 */
	public String funGetCpNodeNURSEID(String NURSENo,RouteExecuteInfo info);
	/**
	 * 签名patientNURSE表 中对应的自动检测项	2011-06-22
	 * @param userID
	 * @param userName
	 * @param info
	 * @param NURSENo
	 */
	public void funSignPatientNURSE(String userID,String userName,RouteExecuteInfo info,ArrayList<String> NURSENos,String time);
	/**
	 * 根据NURSENos查询patientNURSE中相同的NURSENo
	 * @param info
	 * @param NURSENos
	 * @return
	 */
	public ArrayList<String>funGetSameNURSENo(RouteExecuteInfo info,ArrayList<String> NURSENos);
}
