/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： DoctorLcpService .java
// 文件功能描述：自动签名调用函数接口
// 创建人：刘植鑫 
// 创建日期：2011/07/27
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.LCPService.service;

import java.util.ArrayList;

import com.goodwillcis.lcp.model.RouteExecuteInfo;

public interface DoctorLcpService {

	/**
	 * 取得LCP_PATIENT_DOCTOR表中的doctorID	2011-06-21
	 * @param doctorNo
	 * @param info
	 * @return
	 */
	public String funGetCpNodeDoctorID(String doctorNo,RouteExecuteInfo info);
	/**
	 * 签名patientDoctor表 中对应的自动检测项	2011-06-22
	 * @param userID
	 * @param userName
	 * @param info
	 * @param doctorNo
	 */
	public void funSignPatientDoctor(String userID,String userName,RouteExecuteInfo info,ArrayList<String> doctorNos,String time);
	/**
	 * 根据doctorNos查询patientDoctor中相同的doctorNo
	 * @param info
	 * @param doctorNos
	 * @return
	 */
	public ArrayList<String>funGetSameDoctorNo(RouteExecuteInfo info,ArrayList<String> doctorNos);
}
