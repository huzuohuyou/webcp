package com.goodwillcis.lcp.model;

import java.io.Serializable;

public class ZhikongCpPatientIndex implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String patientNo;
	private String patientName;
	private String patientRuyuanTime;
	private String patientChuyuanTime;
	private String patientTotalFee;
	private String patientCpStatus;
	private String patientCpName;
	private String hospitalName;
	private String cp_id;
	public String getCp_id() {
		return cp_id;
	}
	public void setCp_id(String cp_id) {
		this.cp_id = cp_id;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getPatientNo() {
		return patientNo;
	}
	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientRuyuanTime() {
		return patientRuyuanTime;
	}
	public void setPatientRuyuanTime(String patientRuyuanTime) {
		this.patientRuyuanTime = patientRuyuanTime;
	}
	public String getPatientChuyuanTime() {
		return patientChuyuanTime;
	}
	public void setPatientChuyuanTime(String patientChuyuanTime) {
		this.patientChuyuanTime = patientChuyuanTime;
	}
	public String getPatientTotalFee() {
		return patientTotalFee;
	}
	public void setPatientTotalFee(String patientTotalFee) {
		this.patientTotalFee = patientTotalFee;
	}
	public String getPatientCpStatus() {
		return patientCpStatus;
	}
	public void setPatientCpStatus(String patientCpStatus) {
		this.patientCpStatus = patientCpStatus;
	}
	public String getPatientCpName() {
		return patientCpName;
	}
	public void setPatientCpName(String patientCpName) {
		this.patientCpName = patientCpName;
	}
	
}
