/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：TableException.java    
// 文件功能描述：同步节点信息/表信息出现错误进行回滚操作
//
// 创建人：潘状
// 创建日期：2011-08-01
//----------------------------------------------------------------*/



package com.goodwillcis.lcp.model;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.LcpUtil;

public class TableException 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6037045655732327088L;
	
	
	public static int rollBackAll(RouteExecuteInfo info,int cpNodeID){
		String doctorPoint="delete from lcp_patient_doctor_point where hospital_id="+info.getHostipalID()+
		" and patient_no='"+info.getPatientNo()+"' and cp_id= "+info.getCpID()+" and cp_node_id="+cpNodeID+"\r\n";
	
		String doctorItem="delete from lcp_patient_doctor_item where hospital_id="+info.getHostipalID()+
		" and patient_no='"+info.getPatientNo()+"' and cp_id= "+info.getCpID()+" and cp_node_id="+cpNodeID+"\r\n";
		
		String familyPoint="delete from LCP_PATIENT_FAMILY_POINT where hospital_id="+info.getHostipalID()+
			" and patient_no='"+info.getPatientNo()+"' and cp_id= "+info.getCpID()+" and cp_node_id="+cpNodeID+"\r\n";
		String familyItem="delete from LCP_PATIENT_FAMILY_ITEM where hospital_id="+info.getHostipalID()+
			" and patient_no='"+info.getPatientNo()+"' and cp_id= "+info.getCpID()+" and cp_node_id="+cpNodeID+"\r\n";
		
		String nursePoint="delete from LCP_PATIENT_NURSE_POINT where hospital_id="+info.getHostipalID()+
			" and patient_no='"+info.getPatientNo()+"' and cp_id= "+info.getCpID()+" and cp_node_id="+cpNodeID+"\r\n";
		String nurseItem="delete from LCP_PATIENT_NURSE_ITEM where hospital_id="+info.getHostipalID()+
		" and patient_no='"+info.getPatientNo()+"' and cp_id= "+info.getCpID()+" and cp_node_id="+cpNodeID+"\r\n";
		
		String orderPoint="delete from LCP_PATIENT_ORDER_POINT where hospital_id="+info.getHostipalID()+
			" and patient_no='"+info.getPatientNo()+"' and cp_id= "+info.getCpID()+" and cp_node_id="+cpNodeID+"\r\n";
		String orderItem="delete from LCP_PATIENT_ORDER_ITEM where hospital_id="+info.getHostipalID()+
			" and patient_no='"+info.getPatientNo()+"' and cp_id= "+info.getCpID()+" and cp_node_id="+cpNodeID+"\r\n";
	
		String variation="delete from lcp_patient_variation where hospital_id="+info.getHostipalID()+
			" and patient_no='"+info.getPatientNo()+"' and cp_id= "+info.getCpID()+" and cp_node_id="+cpNodeID+"\r\n";
		
		String sqlBat=doctorPoint+doctorItem+familyPoint+familyItem+nursePoint+nurseItem+orderPoint+orderItem+variation;

		int a=0;
		try {
			DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
			a=databaseClass.FunRunSqlByFile(sqlBat.getBytes("GB2312"));
		} catch (Exception e) {
		}
		
		return a;
	}
	
	public static int rollBackNode(RouteExecuteInfo info,int cpNodeID){
		String patientNode="delete from lcp_patient_node where hospital_id="+info.getHostipalID()+
		" and patient_no='"+info.getPatientNo()+"' and cp_id= "+info.getCpID()+" and cp_node_id="+cpNodeID+"\r\n";
		int a=0;
		try {
			DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
			a=databaseClass.FunRunSqlByFile(patientNode.getBytes("GB2312"));
		} catch (Exception e) {
		}
		
		return a;
	}

	

}
