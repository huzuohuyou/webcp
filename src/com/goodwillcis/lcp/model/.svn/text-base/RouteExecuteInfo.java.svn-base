/*----------------------------------------------------------------
//Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
//文件名：RouteExecuteInfo.java
//文件功能描述：路径执行相关工具类
//创建人：刘植鑫 
//创建日期：2011/07/26
//
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.model;

import com.goodwillcis.lcp.util.LcpUtil;

public class RouteExecuteInfo {

	private String verifyCode;//执行人编码
	private String verifyName;//执行人姓名
	private String verifyRole;//执行人角色
	private int cpID;
	private int cpNodeID;
	private String patientNo;
	private int hostipalID;//医院ID值
	private int maxCpNodeID;//病人当前最大cpNode
	public int getMaxCpNodeID() {
		return maxCpNodeID;
	}

	public void setMaxCpNodeID(int maxCpNodeID) {
		this.maxCpNodeID = maxCpNodeID;
	}
	private boolean execute;
	
	public boolean isExecute() {
		return execute;
	}

	public void setExecute(boolean execute) {
		this.execute = execute;
	}

	public int getHostipalID() {
		return hostipalID;
	}

	public void setHostipalID(int hostipalID) {
		this.hostipalID = hostipalID;
	}

	public String getPatientNo() {
		return patientNo;
	}

	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}

	public RouteExecuteInfo(){
		
	}
	
	public RouteExecuteInfo(String verifyCode, String verifyName,
			String verifyRole,int cpID, int cpNodeID,String patientNo,
			int hostipalID,int maxCpNodeID,boolean execute){
		this.verifyCode=verifyCode;
		this.verifyName=verifyName;
		this.verifyRole=verifyRole;
		this.cpID=cpID;
		this.cpNodeID=cpNodeID;
		this.patientNo=patientNo;
		this.hostipalID=hostipalID;
		this.execute=execute;
		this.maxCpNodeID=maxCpNodeID;

	}
	public int getCpID() {
		return cpID;
	}
	public int getCpNodeID() {
		return cpNodeID;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public String getVerifyName() {
		return verifyName;
	}
	public String getVerifyRole() {
		return verifyRole;
	}
	public void setCpID(int cpID) {
		this.cpID = cpID;
	}
	public void setCpNodeID(int cpNodeID) {
		this.cpNodeID = cpNodeID;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public void setVerifyName(String verifyName) {
		this.verifyName = verifyName;
	}
	public void setVerifyRole(String verifyRole) {
		this.verifyRole = verifyRole;
	}
	public int getCpIDByPatientNo(String patientNo){
		String sql="SELECT  DISTINCT(CP_ID)CP_ID  FROM LCP_PATIENT_NODE T WHERE T.PATIENT_NO='"+patientNo+"'";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String cpID=dataSet.funGetFieldByCol(0, "CP_ID");
		int id=Integer.parseInt(cpID);
		return id;
	}
	public int getHostipalIDFromSYS(){
		
		int id=LcpUtil.getHospitalID();
		return id;
	}
}
