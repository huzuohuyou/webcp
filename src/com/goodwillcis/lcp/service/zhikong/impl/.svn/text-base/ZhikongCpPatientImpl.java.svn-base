package com.goodwillcis.lcp.service.zhikong.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.model.ZhikongCpPatientIndex;
import com.goodwillcis.lcp.service.zhikong.ZhikongCpPatient;
import com.goodwillcis.lcp.util.CommonUtil;

public class ZhikongCpPatientImpl implements ZhikongCpPatient {
	private Logger logger=Logger.getLogger(ZhikongCpPatientImpl.class);
	private String sql_quanju;
	@Override
	public int funGetCount(String cp_id,String name, String inTime, String outTime) {
		// TODO Auto-generated method stub
		String sql="select * from (select aa.*, bb.cp_name from ( select distinct * from (select a.patient_no, a.patient_name, a.admission_date, a.discharged_date, b.hospital_name, d.fee_total, a.cp_state, c.cp_id, c.hospital_id from lcp_patient_visit a, dcp_sys_hospital  b, (select distinct * from (select t.hospital_id, t.patient_no, t.cp_id from lcp_patient_node t))  c, lcp_patient_fee   d where a.hospital_id = b.hospital_id(+)   and a.patient_no = c.patient_no(+)  and a.cp_state not in(0,99)  and a.hospital_id = c.hospital_id(+)   and a.hospital_id = d.hospital_id(+)   and a.patient_no = d.patient_no(+)   and c.cp_id = "+cp_id+")  ) aa, lcp_master bb where aa.cp_id = bb.cp_id(+)   and aa.hospital_id = bb.hospital_id(+))  where 1=1 ";
		if(name!=""){
			sql=sql+" and patient_name like '%"+name+"%'";
		}
		if(inTime!=""){
			sql=sql+" and to_char(admission_date,'mm/dd/yyyy')='"+inTime+"' ";
		}
		if(outTime!=""){
			sql=sql+" and  to_char(discharged_date,'mm/dd/yyyy')='"+inTime+"'";
		}
		sql_quanju=sql;
		sql="select count(*) hang from ("+sql+")";
		System.out.println(sql);
		logger.info("funGetCount()查询的语句="+sql);
		DataSet dataSet=new DataSet();
		int row=0;
		try {
			dataSet.funSetDataSetBySql(sql);
			row=Integer.parseInt(dataSet.funGetFieldByCol(0 ,"HANG"));
			logger.info("funGetCount()查询的行数="+row);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("出错，查询异常，sql="+sql);
			row=0;
		}	
		return row;
	}

	@Override
	public ArrayList<ZhikongCpPatientIndex> funGetZhikongByStartEndAndQuery(int start,
			int end) {
		ArrayList<ZhikongCpPatientIndex> zhikongCpList=null;
		String sql=sql_quanju;
		logger.info("funGetZhikongByStartEndAndQuery()查询的语句为"+sql);
		DataSet dataSet=new DataSet();
		try {
			dataSet.funSetDataSetBySql(sql,start,end);
			int row=dataSet.getRowNum();
			if(row>0){
				zhikongCpList=new ArrayList<ZhikongCpPatientIndex>();
				for(int i=0;i<row;i++){
					ZhikongCpPatientIndex cpPatientIndex=new ZhikongCpPatientIndex();
					String patient_no=dataSet.funGetFieldByCol(i,"PATIENT_NO");
					String patient_name=dataSet.funGetFieldByCol1(i,"PATIENT_NAME");
					String admission_date=dataSet.funGetFieldByCol1(i,"ADMISSION_DATE");
					String discharged_date=dataSet.funGetFieldByCol1(i,"DISCHARGED_DATE");
					String hospital_name=dataSet.funGetFieldByCol(i,"HOSPITAL_NAME");
					String fee_total=dataSet.funGetFieldByCol(i,"FEE_TOTAL");
					String cp_state=dataSet.funGetFieldByCol(i,"CP_STATE");
					
					if(cp_state.equals("0")){
						cp_state="未纳入";
					}
					if(cp_state.equals("1")){
						cp_state="已纳入";
					}
					if(cp_state.equals("11")){
						cp_state="已完成";
					}
					if(cp_state.equals("21")){
						cp_state="已退出";
					}
					if(cp_state.equals("99")){
						cp_state="不纳入";
					}
					
					
					
					
					String cp_id=dataSet.funGetFieldByCol(i,"CP_ID");
					String hospital_id=dataSet.funGetFieldByCol(i,"HOSPITAL_ID");
					String cp_name=dataSet.funGetFieldByCol(i,"CP_NAME");
					cpPatientIndex.setPatientNo(patient_no);
					cpPatientIndex.setPatientName(patient_name);
					cpPatientIndex.setPatientRuyuanTime(admission_date);
					cpPatientIndex.setPatientChuyuanTime(discharged_date);
					cpPatientIndex.setPatientCpName(cp_name);
					cpPatientIndex.setPatientTotalFee(fee_total);
					cpPatientIndex.setPatientCpStatus(cp_state);
					cpPatientIndex.setHospitalName(hospital_name);
					cpPatientIndex.setCp_id(cp_id);
					zhikongCpList.add(cpPatientIndex);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("出错，查询异常，funGetZhikongByStartEndAndQuery()的查询sql="+sql);
		}
		return zhikongCpList;
	}

}
