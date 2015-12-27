/*----------------------------------------------------------------
//Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
//文件名：CPshidian.java
//文件功能描述：临床路径管理试点调查评估表   自定义数据集
//创建人：潘状
//创建日期：2011/09/08
//
//----------------------------------------------------------------*/

package com.goodwillcis.cp.shidianpinggu;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.goodwillcis.cp.util.LcpUtil;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;

import bios.report.engine.api.CustomDataSet;


public class CPshidian implements CustomDataSet {

 private String[] metaData;

 private List<Object[]> data;
 
 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 private int hospital_id;
 private String start_time = "";
 
 private String end_time = "";


 // 参数列表,必须与设计器端的参数数量匹配


 @Override
public String[] getMetaData() {

	return metaData;
 }


 @Override
public int getRowCount() {
	return data.size();
 }

 

 @Override
public Object[] getRowData(int rowIndex) {
	return data.get(rowIndex);
 }

 
 @Override
public void applyParams(Object[] params) {
	 
	hospital_id =(Integer)params[2];
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	start_time = (String) params[0];
	end_time = (String) params[1];
	if (start_time == null||"".equals(start_time)) {
		start_time = "2011-10-01";
	}
	if (end_time == null||"".equals(end_time)) {
		end_time = sdf.format(new Date());
	}
	end_time = end_time + " 23:59:59";
	DatabaseClass db = LcpUtil.getDatabaseClass();
	String sqlchuang = "select t.hospital_name,t.beds,t.actual_beds,t.year_nnt,t.year_outpatient_nnt,t.avg_bed_rate,t.avg_bed_turn,t.sum_revenue,t.inhos_revenue,t.out_revenue,t.drug_revenue,t.con_revenue from dcp_sys_hospital t where t.hospital_id ="+hospital_id;
	DataSetClass dschuang = db.FunGetDataSetBySQL(sqlchuang);
	String hospital_name = dschuang.FunGetDataAsStringByColName(0,"HOSPITAL_NAME");
	String beds = dschuang.FunGetDataAsStringByColName(0,"BEDS");
	String actual_beds = dschuang.FunGetDataAsStringByColName(0,"ACTUAL_BEDS");
	String year_nnt = dschuang.FunGetDataAsStringByColName(0,"YEAR_NNT");
	String year_outpatient_nnt = dschuang.FunGetDataAsStringByColName(0,"YEAR_OUTPATIENT_NNT");
	String avg_bed_rate = dschuang.FunGetDataAsStringByColName(0,"AVG_BED_RATE");
	String avg_bed_turn = dschuang.FunGetDataAsStringByColName(0,"AVG_BED_TURN");
	String sum_revenue = dschuang.FunGetDataAsStringByColName(0,"SUM_REVENUE");
	String inhos_revenue = dschuang.FunGetDataAsStringByColName(0,"INHOS_REVENUE");
	String out_revenue = dschuang.FunGetDataAsStringByColName(0,"OUT_REVENUE");
	String drug_revenue = dschuang.FunGetDataAsStringByColName(0,"DRUG_REVENUE");
	String con_revenue = dschuang.FunGetDataAsStringByColName(0,"CON_REVENUE");
	
	String sqlrenshu = "select t.cp_state, count(t.patient_no) cou from lcp_patient_visit t where t.admission_date BETWEEN to_date('"+start_time+"', 'yyyy-mm-dd hh24:mi:ss') AND to_date('"+end_time+"', 'yyyy-mm-dd hh24:mi:ss') and t.hospital_id = "+hospital_id+" group by t.cp_state";
	DataSetClass dsrenshu = db.FunGetDataSetBySQL(sqlrenshu);
	int shishi = 0;
	int wancheng = 0;
	int bianyi = 0;
	
	int countrenshu = dsrenshu.FunGetRowCount();
	for(int i = 0;i<countrenshu;i++){
		String cp_state = dsrenshu.FunGetDataAsStringByColName(i,"CP_STATE");
		int cou = dsrenshu.FunGetDataAsNumberByColName(i,"COU").intValue();
		if("1".equals(cp_state)){
			shishi+=cou;
		}else if("11".equals(cp_state)){
			wancheng=cou;
			shishi+=cou;
		}else if("21".equals(cp_state)){
			bianyi=cou;
			shishi+=cou;
		}
	}
	
	
	String sqllujing = "select count(distinct trim(t.dept_name))keshi,count(distinct trim(t.cp_name))lujing from lcp_master t  where t.cp_status = 0 and t.cp_start_date BETWEEN to_date('"+start_time+"', 'yyyy-mm-dd hh24:mi:ss') AND to_date('"+end_time+"', 'yyyy-mm-dd hh24:mi:ss') and t.hospital_id = "+hospital_id;
	DataSetClass dslujing = db.FunGetDataSetBySQL(sqllujing);
	int keshi = dslujing.FunGetDataAsNumberByColName(0,"KESHI").intValue();
	int lujing = dslujing.FunGetDataAsNumberByColName(0,"LUJING").intValue();
	
	
	String sqlbi = "select count(t.patient_no) a, 1 b from lcp_patient_visit t where t.hospital_id = "+hospital_id+" and t.cp_state in (1, 11, 21) and t.patient_no in (select distinct t1.patient_no from lcp_patient_node t1 where t1.cp_id in (select cp_id from lcp_master where hospital_id =  "+hospital_id+") and t1.hospital_id =  "+hospital_id+") and t.admission_date BETWEEN to_date('"+start_time+"', 'yyyy-mm-dd hh24:mi:ss') AND to_date('"+end_time+"', 'yyyy-mm-dd hh24:mi:ss') union select count(distinct t.patient_no) a, 2 b from lcp_patient_log_income t where t.hospital_id = "+hospital_id+" and t.income_code in (select distinct t.cp_income_code from lcp_master_income t where t.hospital_id =  "+hospital_id+") and t.exe_date BETWEEN to_date('"+start_time+"', 'yyyy-mm-dd hh24:mi:ss') AND to_date('"+end_time+"', 'yyyy-mm-dd hh24:mi:ss')";
	DataSetClass dsbi = db.FunGetDataSetBySQL(sqlbi);
	double zongnaru = 0;
	double yingnaru = 0;
	for(int i = 0;i<2;i++){
		double a = dsbi.FunGetDataAsNumberByColName(i,"A").doubleValue();
		double b = dsbi.FunGetDataAsNumberByColName(i,"B").doubleValue();
		if(b==1){
			zongnaru=a;
		}else if(b==2){
			yingnaru=a;
		}
	}
	double narubi =zongnaru/yingnaru;
	metaData = new String[] { 
		"现有编制床位数",      //
		"实际使用床位数",      //
		"医院年住院人次数",      //
		"医院年门诊诊疗人次数",      //
		"平均床位使用率",      //
		"平均病床周转次数",      //
		"医院总收入",      //
		"住院收入",      //
		"门诊收入",      //
		"药品收入",      //
		"耗材收入",      //
		"医院实施临床路径的专业数目",      //科室
		"医院实施临床路径的病种数目",      //路径
		"实施临床路径的患者数",      //纳入的
		"完成临床路径的患者数",      //完成的
		"出现变异的患者数",      //变异退出
		"实施临床路径的患者总数占试点病种全院患者总数的比例",      //纳入 比 入院总人数
		"临床路径相关变异分析记录",      //
		"采取临床路径相关的动态监督",      //
		"组织院级临床路径实施自评次数",      //
		"组织院级临床路径修订会议次数",      //
		"组织院级临床路径管理培训次数",      //
		"培训内容主要包括",      //
		"医院信息系统能准确反映的内容",
		"开始时间",
		"结束时间",
		"医院名称"
		};
	data = new ArrayList<Object[]>();
	
	    data.add(new Object[] { 
	    		beds,
	    		actual_beds,
	    		year_nnt,
	    		year_outpatient_nnt,
	    		avg_bed_rate,
	    		avg_bed_turn,
	    		sum_revenue,
	    		inhos_revenue,
	    		out_revenue,
	    		drug_revenue,
	    		con_revenue,
			keshi,
			lujing,
			shishi,
			wancheng,
			bianyi,
			Math.round(narubi*1000)/10.0,
			"临床路径相关变异分析记录",
			"采取临床路径相关的动态监督",
			null,
			null,
			null,
			"培训内容主要包括",
			"医院信息系统能准确反映的内容",
			start_time,
			end_time,
			hospital_name
	    });
	
 }

}

