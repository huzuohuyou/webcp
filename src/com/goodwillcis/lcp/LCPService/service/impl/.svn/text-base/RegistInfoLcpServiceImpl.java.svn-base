/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： RegistInfoLcpServiceImpl .java
// 文件功能描述：查询对应表的总数调用函数接口实现类
// 创建人：刘植鑫 
// 创建日期：2011/07/27
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.LCPService.service.impl;

import com.goodwillcis.lcp.LCPService.service.RegistInfoLcpService;
import com.goodwillcis.lcp.model.DataSet;

public class RegistInfoLcpServiceImpl implements RegistInfoLcpService {

	@Override
	public int funGetLogDoctor(String patientNo) {
		// TODO Auto-generated method stub
		String sql="select count(*)totalNum from LCP_PATIENT_LOG_DOCTOR where PATIENT_NO="+patientNo;
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row=dataSet.getRowNum();
		return row;
	}

	@Override
	public int funGetLogNurse(String patientNo) {
		// TODO Auto-generated method stub
		String sql="select count(*)totalNum from LCP_PATIENT_LOG_NURSE where PATIENT_NO="+patientNo;
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row=dataSet.getRowNum();
		return row;
	}

	@Override
	public int funGetLogOrder(String patientNo) {
		// TODO Auto-generated method stub
		String sql="select count(*)totalNum from LCP_PATIENT_LOG_ORDER where PATIENT_NO="+patientNo;
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row=dataSet.getRowNum();
		return row;
	}

	@Override
	public int funGetNode(String patientNo) {
		// TODO Auto-generated method stub
		String sql="select count(*)totalNum from LCP_PATIENT_NODE where PATIENT_NO="+patientNo;
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row=dataSet.getRowNum();
		return row;
	}

	@Override
	public String funGetPatientCPStatus(String patientNo) {
		// TODO Auto-generated method stub
		String sql="select CP_STATE from LCP_PATIENT_VISIT where PATIENT_NO="+patientNo;
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String type=dataSet.funGetFieldByCol(0, "CP_STATE");
		return type;
	}

}
