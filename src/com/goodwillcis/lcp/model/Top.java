/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：Top.java    
// 文件功能描述：显示页面顶端病人信息表
//
// 创建人：刘植鑫
// 创建日期：2011-06-01
// 修改人:潘状
// 修改原因:需求更改,数据库表结构改变
// 修改日期:2011-7-28
// 完成日期:2011-8-1
 
//修改人：林建喜
//修改原因：新增住院日和费用预警功能
//修改日期：2013-4-15
//完成时间：2013-4-16
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.model;

import com.goodwillcis.lcp.util.CommonUtil;

public class Top {

	public String createTopTable(RouteExecuteInfo info){
		String html="";
		String patient_no = info.getPatientNo();
		String patientSQl="SELECT T.* FROM LCP_PATIENT_VISIT T WHERE T.PATIENT_NO='"+patient_no+"'";
		String cpSQL="SELECT * FROM LCP_MASTER T WHERE T.CP_ID="+info.getCpID()+"";
		
		String cpTypeSQL = "select t.cp_node_id,t.cp_node_type from lcp_patient_node t WHERE T.PATIENT_NO='"+patient_no+"'";
		DataSet cpTypeDataSet=new DataSet();
		cpTypeDataSet.funSetDataSetBySql(cpTypeSQL);
		String cpActualDaysSQL = "";
		String currNodeType="";
		String nodeType="";
		for(int n=0;n<cpTypeDataSet.getRowNum();n++){
			 nodeType = cpTypeDataSet.funGetFieldByCol(n,"CP_NODE_TYPE").toString().trim();
			 currNodeType+=nodeType;
		}
		if(currNodeType.equals("") || currNodeType.contains("2") || currNodeType.contains("3") || currNodeType.contains("4") ){
			String cpEndTimeSQL = "select t.cp_node_end_time from lcp_patient_node t WHERE T.PATIENT_NO='"+patient_no+"' and t.cp_node_type in(2,3,4)";
			DataSet cpEndTimeDataSet=new DataSet();
			cpEndTimeDataSet.funSetDataSetBySql(cpEndTimeSQL);
			String CP_NODE_END_TIME = cpEndTimeDataSet.funGetFieldByCol(0,"CP_NODE_END_TIME");
			cpActualDaysSQL = "select round(to_date('"+CP_NODE_END_TIME+"','yyyy-mm-dd hh24:mi:ss')-t.admission_date,0) actual_days from lcp_patient_visit t where t.patient_no='"+patient_no+"'";
			}else
		{
			cpActualDaysSQL ="select round(sysdate-t.admission_date,0) actual_days from lcp_patient_visit t where t.patient_no='"+patient_no+"'";
		}
		
		String feeTotalSQL = "select round(t.fee_total,0) fee_total from lcp_patient_fee t where t.patient_no='"+patient_no+"'";
		DataSet patientDataSet=new DataSet();
		DataSet cpDataSet=new DataSet();
		DataSet cpActualDaysDataSet=new DataSet();
		DataSet feeTotalDataSet=new DataSet();
		patientDataSet.funSetDataSetBySql(patientSQl);
		cpDataSet.funSetDataSetBySql(cpSQL);
		cpActualDaysDataSet.funSetDataSetBySql(cpActualDaysSQL);
		feeTotalDataSet.funSetDataSetBySql(feeTotalSQL);
		
		//String HOSPITAL_ID=patientDataSet.funGetFieldByCol(0, "HOSPITAL_ID");
		String PATIENT_NO=patientDataSet.funGetFieldByCol(0, "PATIENT_NO");
		String PATIENT_NAME=patientDataSet.funGetFieldByCol(0, "PATIENT_NAME");
		String PATIENT_SEX=patientDataSet.funGetFieldByCol(0, "PATIENT_SEX");
		String BIRTHDAY=patientDataSet.funGetFieldByCol(0, "BIRTHDAY");
		BIRTHDAY = BIRTHDAY.substring(0,10);
		//String OUTPATIENT_NO=patientDataSet.funGetFieldByCol(0, "OUTPATIENT_NO");
		String INPATIENT_NO=patientDataSet.funGetFieldByCol(0, "INPATIENT_NO");
		String ADMISSION_DATE=patientDataSet.funGetFieldByCol(0, "ADMISSION_DATE");
		String DISCHARGED_DATE=patientDataSet.funGetFieldByCol(0, "DISCHARGED_DATE");
		String DEPT_ADMISSION_TO=patientDataSet.funGetFieldByCol(0, "DEPT_ADMISSION_TO");
		String ATTENDING_DOCTOR=patientDataSet.funGetFieldByCol(0, "ATTENDING_DOCTOR");
		String CP_NAME=cpDataSet.funGetFieldByCol(0, "CP_NAME");
		String CP_FEE=cpDataSet.funGetFieldByCol(0, "CP_FEE");
		String CP_DAYS=cpDataSet.funGetFieldByCol(0, "CP_DAYS");
		String CP_DAYS_MAX=cpDataSet.funGetFieldByCol(0, "CP_DAYS_MAX");
		String CP_VERSION=cpDataSet.funGetFieldByCol(0, "CP_VERSION");
		String HEALTH_CARE_QUOTA = cpDataSet.funGetFieldByCol(0, "HEALTH_CARE_QUOTA");
		String CP_ACTUAL_DAYS =cpActualDaysDataSet.funGetFieldByCol(0, "ACTUAL_DAYS");
		String FEE_TOTAL =feeTotalDataSet.funGetFieldByCol(0, "FEE_TOTAL");
		
		
		String ACTUAL_DAYS = "";
		int CP_DAYS1 = Integer.parseInt(CP_DAYS);
		int CP_DAYS_MAX1 = Integer.parseInt(CP_DAYS_MAX);
		int CP_ACTUAL_DAYS1 = Integer.parseInt(CP_ACTUAL_DAYS);
		int CP_DAYS_PASS = CP_ACTUAL_DAYS1-CP_DAYS_MAX1;
		if(CP_ACTUAL_DAYS1 <= CP_DAYS1){
			ACTUAL_DAYS = "<th width='11%' align='center'>实际住院日" +
					"</th><td><span class='STYLE2'>"+CP_ACTUAL_DAYS+"</span></td>";
		}else if(CP_DAYS1<CP_ACTUAL_DAYS1 && CP_ACTUAL_DAYS1<= CP_DAYS_MAX1){
			ACTUAL_DAYS = "<th width='11%' align='center'>实际住院日" +
					"</th><td bgcolor='yellow'><span class='STYLE2' >"+CP_ACTUAL_DAYS+"</span></td>";
		}else if(CP_ACTUAL_DAYS1>CP_DAYS_MAX1){
			ACTUAL_DAYS = "<th width='11%' align='center'>实际住院日" +
			        "</th><td bgcolor='red'><span class='STYLE2' >"+CP_ACTUAL_DAYS+"(超过最大住院日"+CP_DAYS_PASS+"天)</span></td>";
		}
		
		
		String ACTUAL_FEE = "";
		int CP_FEE1 = Integer.parseInt(CP_FEE);
		int FEE_TOTAL1 = Integer.parseInt(FEE_TOTAL);
		int FEE_PASS = FEE_TOTAL1-CP_FEE1;
		if(FEE_TOTAL1 <= CP_FEE1){
			ACTUAL_FEE = "<th width='11%' align='center'>实际费用</th><td><span class='STYLE2'>"+FEE_TOTAL+"</span></td>";
		}else if(FEE_TOTAL1 > CP_FEE1 ){
			ACTUAL_FEE = "<th width='11%' align='center'>实际费用" +
					"</th><td bgcolor='red'><span class='STYLE2'>"+FEE_TOTAL+"(超出平均费用"+FEE_PASS+")</span></td>";
		}
		
		html="<tr>" +
		        "<th width='11%' align='center' colspan='4'><font color='black'>医保定额</font></th>" +
		        "<td width='10%' colspan='4'><span class='STYLE2'><font style='font-weight: bolder;' color='black'>"+HEALTH_CARE_QUOTA+"</font></span></td>" +
			    "</tr>" +
			    
			    "<tr>" +
		        "<th width='11%' align='center'>住 院 号</th><td width='10%'><span class='STYLE2'>"+INPATIENT_NO+"</span></td>" +
		        "<th width='11%' align='center'>入院科室</th><td><span class='STYLE2'>"+DEPT_ADMISSION_TO+"</span></td>" +
				"<th width='11%' align='center'>平均住院日</th><td><span class='STYLE2'>"+CP_DAYS+"</span></td>" +
				"<th width='11%' align='center'>路径名称</th><td width='16%'><span class='STYLE2'>"+CP_NAME+"</span></td>" +
				"</tr>" +
				
				"<tr>" +
				"<th width='11%' align='center'>患者编码</th><td width='10%'><span class='STYLE2'>"+PATIENT_NO+"</span></td>" +
				"<th width='11%' align='center'>主治医生</th><td><span class='STYLE2'>"+ATTENDING_DOCTOR+"</span></td>" +
				""+ACTUAL_DAYS+""+
				"<th width='11%' align='center'>路径版本</th><td><span class='STYLE2'>"+CP_VERSION+"</span></td>" +
				"</tr>" +
				
				"<tr>" +
				"<th width='11%' align='center'>患者姓名</th><td width='10%'><span class='STYLE2'>"+PATIENT_NAME+"</span></td>" +
				"<th width='11%' align='center'>入院时间</th><td width='15%'><span class='STYLE2'>"+ADMISSION_DATE+"</span></td>" +
				"<th width='11%' align='center'>平均费用</th><td><span class='STYLE2'>"+CP_FEE+"</span></td>" +
				"<th width='11%' align='center'>路径操作</th><td><input name='button' type='button' onclick='chakan();' id='button' value='路径定义信息' />" +
				"</tr>"+
				
				"<tr>"+
				"<th width='11%' align='center'>性      别</th><td width='10%'><span class='STYLE2'>"+PATIENT_SEX+"</span></td>" +
				"<th width='11%' align='center'>出院时间</th><td><span class='STYLE2'>"+DISCHARGED_DATE+"</span></td>" +
			    ""+ACTUAL_FEE+""+
			    "<th width='11%' align='center'>路径操作</th><td><input name=button' type='button' onclick='chakanbaobiao();' id='button' value='路径报表信息' /></td>" +
			    "</tr>";
		html=CommonUtil.replactCharacter(html);
		return html;
	}

}
