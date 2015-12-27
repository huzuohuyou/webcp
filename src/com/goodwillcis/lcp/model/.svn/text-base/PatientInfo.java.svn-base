package com.goodwillcis.lcp.model;

import com.goodwillcis.lcp.util.LcpUtil;

public class PatientInfo {

	/**
	 * 根据病人编号，获取病人的所有信息
	 * @param patientNo
	 * @return
	 */
	public RouteExecuteInfo funGetPatientInfo(String patientNo){
		RouteExecuteInfo info=new RouteExecuteInfo();
		int cpID=info.getCpIDByPatientNo(patientNo);
		int hostipalID=info.getHostipalIDFromSYS();
		NodeTable nodeTable=new NodeTable();
		int maxCpNodeID=nodeTable.funGetMaxCpNodeIDByCpID(info);//当前路径最大节点号
		
		String sql="";
		info.setCpID(cpID);
		info.setHostipalID(hostipalID);
		info.setPatientNo(patientNo);
		info.setCpNodeID(maxCpNodeID);
		info.setMaxCpNodeID(maxCpNodeID);
		
		
		return info;
	}
	
	/**
	 * 根据病人编号，获取病人的所有信息
	 * @param patientNo
	 * @return
	 */
	public RouteExecuteInfo funGetPatientInfo1(String patientNo){
		RouteExecuteInfo info=new RouteExecuteInfo();
		String config=LcpUtil.getHospitalIConfig();
		String sql="select *  from (select CONFIG_VALUE          " +
				"from dcp_sys_config         " +
				"where CONFIG_ID = '"+config+"')aa,     " +
				"  (select cp_id, max(cp_node_id) cp_node_id,hospital_id     " +
				"     from lcp_patient_node t         where t.patient_no = '"+patientNo+"'     " +
				"    group by cp_id,hospital_id)bb       " +
				"  where aa.CONFIG_VALUE=bb.hospital_id(+)";
		System.out.println(sql);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		
		
		int cpID=Integer.parseInt(dataSet.funGetFieldByCol(0, "CP_ID"));
		int hostipalID=Integer.parseInt(dataSet.funGetFieldByCol(0, "CONFIG_VALUE"));
		int maxCpNodeID=Integer.parseInt(dataSet.funGetFieldByCol(0, "CP_NODE_ID"));
		
		System.out.println(cpID+hostipalID+maxCpNodeID);
		
		info.setCpID(cpID);
		info.setHostipalID(hostipalID);
		info.setPatientNo(patientNo);
		info.setCpNodeID(maxCpNodeID);
		info.setMaxCpNodeID(maxCpNodeID);
		
		
		return info;
	}
}
