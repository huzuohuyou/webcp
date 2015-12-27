/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： DoctorLcpServiceImpl .java
// 文件功能描述：自动签名调用函数接口实现类
// 创建人：刘植鑫 
// 创建日期：2011/07/27
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.LCPService.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.LCPService.service.DoctorLcpService;
import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.model.RouteExecuteInfo;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

public class DoctorLcpServiceImpl implements DoctorLcpService {
	
	private static Logger logger = Logger.getLogger ( DoctorLcpServiceImpl.class.getName () ) ; 
	@Override
	public String funGetCpNodeDoctorID(String doctorNo, RouteExecuteInfo info) {
		// TODO Auto-generated method stub
		String sql="SELECT T.CP_NODE_DOCTOR_ID FROM LCP_PATIENT_DOCTOR_ITEM T " +
				"WHERE T.HOSPITAL_ID="+info.getHostipalID()+" AND T.PATIENT_NO='"+info.getPatientNo()+"'" +
						" AND T.CP_ID="+info.getCpID()+" AND " +
				"T.CP_NODE_ID="+info.getCpNodeID()+" AND T.DOCTOR_NO='"+doctorNo+"'";
		DataSet dataSet=new DataSet();
		String doctorID="";
		try {
			dataSet.funSetDataSetBySql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			logger.fatal("自动签名查询doctorID出错，sql语句为"+sql);
		}
		
		doctorID=dataSet.funGetFieldByCol(0, "CP_NODE_DOCTOR_ID");
		if(doctorID==""||doctorID==null){
			return null;
		}
		return doctorID;
	}

	@Override
	public void funSignPatientDoctor(String userID, String userName,
			RouteExecuteInfo info, ArrayList<String> doctorNos, String time) {
			ArrayList<String>doctorNosnew=funGetSameDoctorNo(info, doctorNos);
			String sql="";
			if(doctorNosnew!=null&&doctorNosnew.size()>0){
				String doctor_nos=CommonUtil.funGetStringByArraylist(doctorNosnew, true);
				sql=sql+"UPDATE LCP_PATIENT_DOCTOR_ITEM SET" +
					" USER_ID='"+userID+"'," +
					" USER_NAME='"+userName+"'," +
					" EXE_STATE="+1+"," +
					" EXE_DATE="+time+"," +
					" SYS_LAST_UPDATE="+time+"" +
					" WHERE " +
					" HOSPITAL_ID="+info.getHostipalID()+" " +
					" AND PATIENT_NO='"+info.getPatientNo()+"'" +
					" AND CP_ID="+info.getCpID()+"" +
					" AND CP_NODE_ID="+info.getCpNodeID()+"" +
					" AND DOCTOR_NO IN("+doctor_nos+")" +
					" AND AUTO_ITEM=0 " +
					"\r\n";
				DataSet dataSet=new DataSet();
				String sql1="SELECT CP_NODE_DOCTOR_ID FROM LCP_PATIENT_DOCTOR_ITEM T " +
						"WHERE T.HOSPITAL_ID="+info.getHostipalID()+" AND T.CP_ID="+info.getCpID()+" " +
						"AND T.PATIENT_NO='"+info.getPatientNo()+"' AND T.CP_NODE_ID="+info.getCpNodeID()+" AND DOCTOR_NO IN("+doctor_nos+")";
				//dataSet.funSetDataSetBySql(sql1);
				//String cp_node_doctor_id =dataSet.funGetOneFieldStringValues("CP_NODE_DOCTOR_ID");
				sql=sql+"UPDATE LCP_PATIENT_DOCTOR_POINT SET" +
					" USER_ID='"+userID+"'," +
					" USER_NAME='"+userName+"'," +
					" EXE_STATE="+1+"," +
					" EXE_DATE="+time+"," +
					" SYS_LAST_UPDATE="+time+"" +
					" WHERE " +
					" HOSPITAL_ID="+info.getHostipalID()+" " +
					" AND PATIENT_NO='"+info.getPatientNo()+"'" +
					" AND CP_ID="+info.getCpID()+"" +
					" AND CP_NODE_ID="+info.getCpNodeID()+"" +
					" AND CP_NODE_DOCTOR_ID IN("+sql1+")" +
					" AND AUTO_ITEM=0 " +
					"\r\n";
			}
			if(doctorNosnew!=null&&doctorNosnew.size()>0){
				DatabaseClass class1=LcpUtil.getDatabaseClass();
				try {
					int row1=class1.FunRunSqlByFile(sql.getBytes("GB2312"));
					logger.info("自动签名行数="+row1);
				} catch (Exception e) {
					logger.fatal("自动签名update出错，sql语句为"+sql);
				}
			}
	}

	@Override
	public ArrayList<String> funGetSameDoctorNo(RouteExecuteInfo info,
			ArrayList<String> doctorNos) {
		String sql="SELECT T.DOCTOR_NO FROM LCP_PATIENT_DOCTOR_ITEM T WHERE" +
				" T.HOSPITAL_ID="+info.getHostipalID()+" AND T.PATIENT_NO='"+info.getPatientNo()+"' " +
				"AND T.CP_ID="+info.getCpID()+" AND T.CP_NODE_ID="+info.getCpNodeID();
		//System.out.println(sql);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		ArrayList<String>_doctorNos=dataSet.funGetOneFieldListValues("DOCTOR_NO");
		CommonUtil util=new CommonUtil();
		ArrayList<String>doctorNosNew=util.funGetSameValues(doctorNos, _doctorNos);
		return doctorNosNew;
	}

}
