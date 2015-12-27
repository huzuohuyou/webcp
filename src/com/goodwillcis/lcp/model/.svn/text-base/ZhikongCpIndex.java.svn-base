package com.goodwillcis.lcp.model;

import java.io.Serializable;

public class ZhikongCpIndex implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cp_id;//路径ID
	public String getCp_id() {
		return cp_id;
	}
	public void setCp_id(String cp_id) {
		this.cp_id = cp_id;
	}
	public String getCp_code() {
		return cp_code;
	}
	public void setCp_code(String cp_code) {
		this.cp_code = cp_code;
	}
	public String getCp_name() {
		return cp_name;
	}
	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getIsStop() {
		return isStop;
	}
	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}
	public String getMatchIncomeCase() {
		return matchIncomeCase;
	}
	public void setMatchIncomeCase(String matchIncomeCase) {
		this.matchIncomeCase = matchIncomeCase;
	}
	public String getIncomeCase() {
		return incomeCase;
	}
	public void setIncomeCase(String incomeCase) {
		this.incomeCase = incomeCase;
	}
	public String getExecuteCase() {
		return executeCase;
	}
	public void setExecuteCase(String executeCase) {
		this.executeCase = executeCase;
	}
	public String getCompleteCase() {
		return completeCase;
	}
	public void setCompleteCase(String completeCase) {
		this.completeCase = completeCase;
	}
	public String getVariaExistCase() {
		return variaExistCase;
	}
	public void setVariaExistCase(String variaExistCase) {
		this.variaExistCase = variaExistCase;
	}
	public String getCompleteCaseWithVaria() {//这里记着得改回来
		int aa=Integer.parseInt(completeCase);
		//System.out.println("aa="+aa);
		aa=(aa*4)/5;
		return Integer.toString(aa);
	}
	public void setCompleteCaseWithVaria(String completeCaseWithVaria) {
		this.completeCaseWithVaria = completeCaseWithVaria;
	}
	public String getAntiIncomeCase() {
		return antiIncomeCase;
	}
	public void setAntiIncomeCase(String antiIncomeCase) {
		this.antiIncomeCase = antiIncomeCase;
	}
	private String cp_code="";//路径编码
	private String cp_name="";//路径名称
	private String start_time="";//开始时间
	private String isStop="";//是否停用
	private String matchIncomeCase="";//符合纳入病历
	private String incomeCase="";//纳入病历
	private String executeCase="";//执行病历
	private String completeCase="";//完成病历
	private String variaExistCase="";//变异退出病历
	private String completeCaseWithVaria="";//完成带变异病历
	private String antiIncomeCase="";//不纳入病历
	private String notIncomeCase="";// 未纳入病历
	public String getNotIncomeCase() {
		return notIncomeCase;
	}
	public void setNotIncomeCase(String notIncomeCase) {
		this.notIncomeCase = notIncomeCase;
	}
	
}
